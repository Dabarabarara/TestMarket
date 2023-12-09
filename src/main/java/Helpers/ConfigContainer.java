package Helpers;

import cucumber.api.Scenario;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Содержит настройки (контекст) для всех тестов ( синглтон ).
 * Created by Dima Makieiev on 08.12.2015.
 * Updated by Alexander S. Vasyurenko on 15.04.2021.
 */
public class ConfigContainer {
    /*******************************************************************************************************************
     * Константы класса.
     ******************************************************************************************************************/
    // Относительный путь к файлам с настройками тестовой среды
    private static final String PATH_TO_PROPERTIES_FILES = "src\\test\\resources\\config\\";

    // Имя файла с настройками тестовой среды по умолчанию (этот файл всегда должен существовать)
    private static final String DEFAULT_PROPERTIES_FILE_NAME = "config.properties";

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    // Статический экземпляр этого класса (собственно сам ConfigContainer)
    private static volatile ConfigContainer instance;

    // Экземпляр WebDriver
    private WebDriver driver;

    // Экземпляр класса Scenario (Сucumber JVM Scenario object)
    private Scenario scenario;

    // Активность режима снятия скриншотов
    private boolean screenshotActive = false;

    // Вспомогательный класс для работы с временными интервалами
    private final Timer timer = new Timer();

    // Настройки тестовой среды (считываются из файла config.properties и используются во всех тестовых сценариях)
    private final Properties properties = new Properties();

    // Параметры конкретного тестового сценария (id, name и прочее, что генерируется в ходе теста) для передачи между
    // шагами теста. Существуют в памяти только во время выполнения теста.
    private final Map<String, String> parameters = new HashMap<>();

    // Указатель на основное окно приложения в браузере
    private String mainWindowHandle;

    // Путь к временной папке со случайно сгенерированным именем
    private final String pathToTempFolderWithRandomName =
            System.getProperty("user.dir") + "\\temp" + timer.getCurrentDateTime("ddMMyyyyHHmmSS");

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * Делаем конструктор класса приватным, чтобы не было возможности создать экземпляр класса извне.
     ******************************************************************************************************************/
    private ConfigContainer() {
    }

    /*******************************************************************************************************************
     * Метод доступа к экземпляру этого класса.
     ******************************************************************************************************************/
    /**
     * Возвращает экземпляр этого класса ( синглтон ).
     *
     * @return экземпляр этого класса
     */
    public static ConfigContainer getInstance() {
        if (instance == null) {
            synchronized (ConfigContainer.class) {
                if (instance == null) {
                    instance = new ConfigContainer();
                }
            }
        }
        return instance;
    }

    /*******************************************************************************************************************
     * Методы доступа к экземпляру WebDriver.
     ******************************************************************************************************************/
    /**
     * Устанавливает экземпляр WebDriver.
     *
     * @param driver экземпляр WebDriver
     */
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Возвращает экземпляр WebDriver.
     *
     * @return экземпляр WebDriver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /*******************************************************************************************************************
     * Методы доступа к настройкам тестовой среды.
     ******************************************************************************************************************/
    /**
     * Возвращает относительный путь к файлу с настройками тестовой среды [*.properties].
     *
     * @return относительный путь к файлу с настройками тестовой среды [*.properties]
     */
    private String getPropertiesFileName() {
        String pref = ">>> (getPropertiesFileName) ";
        String fileName = System.getenv("223_RTS_AUTOTEST_CONFIG");
        System.out.println(pref + "Переменная [223_RTS_AUTOTEST_CONFIG] содержит значение: [" + fileName + "].");
        System.out.println(pref + "Настройки тестовой среды по умолчанию: [" + DEFAULT_PROPERTIES_FILE_NAME + "].");

        fileName = fileName == null ? DEFAULT_PROPERTIES_FILE_NAME : fileName;
        Assert.assertNotEquals(pref + "Имя файла с настройками тестовой среды пусто.", "", fileName);
        fileName = PATH_TO_PROPERTIES_FILES + fileName;
        System.out.println(pref + "Используемый файл с настройками тестовой среды: [" + fileName + "].");

        File file = new File(fileName);
        Assert.assertTrue(pref + "Указанный файл с настройками тестовой среды не существует.", file.exists());
        Assert.assertTrue(pref + "Длина файла с настройками тестовой среды равна нулю.", file.length() > 0);

        return fileName;
    }

    /**
     * Загружает настройки тестовой среды из файла [*.properties].
     */
    public void loadConfig() {
        String pref = ">>> (loadConfig) ";
        InputStream input = null;
        try {
            input = new FileInputStream(this.getPropertiesFileName());
            this.properties.load(input);
            System.out.println(pref + "URL сайта для тестов открытой части : [" + this.getOpenPartSiteUrl() + "].");
            System.out.println(pref + "URL сайта для тестов без ускорений  : [" + this.getOnProdSiteUrl() + "].");
            System.out.println(pref + "URL сайта для остальных тестов      : [" + this.getSiteUrl() + "].");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Обертка метода getProperty() для того, чтобы сделать код более понятным.
     *
     * @param key ключ
     * @return значение
     */
    public String getConfigParameter(String key) {
        String configParameter = this.properties.getProperty(key);

        Assert.assertNotNull(String.format(
                ">>> (getConfigParameter) Параметр [%s] не найден в конфигурационном файле.", key), configParameter);
        Assert.assertNotEquals(String.format(">>> (getConfigParameter) Параметр [%s] является пустой строкой.", key),
                "", configParameter);

        return configParameter;
    }

    /**
     * Возвращает адрес сайта, где расположен стенд.
     *
     * @return адрес сайта, где расположен стенд
     */
    private String getSiteUrl() {
        String siteUrl = this.getConfigParameter("SiteUrl");
        return siteUrl.endsWith("/") ? siteUrl : siteUrl + "/";
    }

    /**
     * Возвращает адрес страницы входа на площадку для Заказчиков.
     *
     * @return адрес страницы входа на площадку для Заказчиков
     */
    String getCustomerLoginUrl() {
        return this.getSiteUrl() + this.getConfigParameter("CustomerLoginUrl");
    }

    /**
     * Возвращает адрес страницы входа на площадку для Поставщиков.
     *
     * @return адрес страницы входа на площадку для Поставщиков
     */
    public String getSupplierLoginUrl() {
        return this.getSiteUrl() + this.getConfigParameter("SupplierLoginUrl");
    }

    /**
     * Возвращает адрес страницы входа на площадку для Аккредитации.
     *
     * @return адрес страницы входа на площадку для Аккредитации
     */
    public String getAccreditationLoginUrl() {
        return getSiteUrl() + this.getConfigParameter("AccreditationLoginUrl");
    }

    /**
     * Возвращает адрес страницы входа на площадку для Восстановления пароля.
     *
     * @return адрес страницы входа на площадку для Восстановления пароля
     */
    public String getPasswordRecoveryUrl() {
        return getSiteUrl() + this.getConfigParameter("PasswordRecoveryUrl");
    }

    /**
     * Возвращает адрес страницы перехвата e-mail сообщений площадки.
     *
     * @return адрес страницы перехвата e-mail сообщений площадки
     */
    public String getFakeSmtpUrl() {
        return getSiteUrl() + this.getConfigParameter("FakeSMTP");
    }

    /**
     * Возвращает адрес боевого сервера [Production] (открытая часть).
     *
     * @return адрес боевого сервера [Production] (открытая часть)
     */
    private String getOpenPartSiteUrl() {
        String siteUrl = this.getConfigParameter("OpenPartSiteUrl");
        return siteUrl.endsWith("/") ? siteUrl : siteUrl + "/";
    }

    /**
     * Возвращает адрес боевого сервера [Production].
     *
     * @return адрес боевого сервера [Production]
     */
    private String getOnProdSiteUrl() {
        String siteUrl = this.getConfigParameter("OnProdSiteUrl");
        return siteUrl.endsWith("/") ? siteUrl : siteUrl + "/";
    }

    /**
     * Возвращает адрес страницы входа на площадку для Заказчиков боевого сервера [Production].
     *
     * @return адрес страницы входа на площадку для Заказчиков боевого сервера [Production]
     */
    String getOnProdCustomerLoginUrl() {
        return this.getOnProdSiteUrl() + this.getConfigParameter("OpenPartCustomerLoginUrl");
    }

    /**
     * Возвращает адрес страницы входа на площадку для Поставщиков боевого сервера [Production].
     *
     * @return адрес страницы входа на площадку для Поставщиков боевого сервера [Production]
     */
    String getOnProdSupplerLoginUrl() {
        return this.getOnProdSiteUrl() + this.getConfigParameter("OpenPartSupplerLoginUrl");
    }

    /**
     * Возвращает адрес страницы входа на площадку для Заказчиков (открытая часть).
     *
     * @return адрес страницы входа на площадку для Заказчиков (открытая часть)
     */
    public String getOpenPartCustomerLoginUrl() {
        return this.getOpenPartSiteUrl() + this.getConfigParameter("OpenPartCustomerLoginUrl");
    }

    /**
     * Возвращает адрес страницы входа на площадку для Поставщиков (открытая часть).
     *
     * @return адрес страницы входа на площадку для Поставщиков (открытая часть)
     */
    public String getOpenPartSupplerLoginUrl() {
        return this.getOpenPartSiteUrl() + this.getConfigParameter("OpenPartSupplerLoginUrl");
    }

    /**
     * Возвращает адрес страницы [Поиск закупок] для Поставщиков (открытая часть).
     *
     * @return адрес страницы [Поиск закупок] для Поставщиков (открытая часть)
     */
    public String getOpenPartSupplierTradeSearchUrl() {
        return this.getOpenPartSiteUrl() + this.getConfigParameter("OpenPartSupplier1TradeSearchUrl");
    }

    /**
     * Возвращает полный путь к файлу.
     *
     * @param fileName имя файла
     * @return полный путь к файлу
     */
    private String getFullPathTo(String fileName) {
        return new File(fileName).getAbsolutePath();
    }

    /**
     * Возвращает каталог с документами.
     *
     * @return каталог с документами
     */
    private String getAttachmentsFolder() {
        return this.getConfigParameter("AttachmentsFolder");
    }

    /**
     * Возвращает учредительный документ (1.txt).
     *
     * @return учредительный документ (1.txt)
     */
    public String getAbsolutePathToFoundationDoc() {
        return getFullPathTo(this.getAttachmentsFolder() + this.getConfigParameter("FoundationDoc"));
    }

    /**
     * Возвращает полномочия руководителя (2.txt).
     *
     * @return полномочия руководителя (2.txt)
     */
    public String getAbsolutePathToDirectorAuthorityDoc() {
        return getFullPathTo(this.getAttachmentsFolder() + this.getConfigParameter("DirectorAuthorityDoc"));
    }

    /**
     * Возвращает выписку ЕГРЮЛ (3.txt).
     *
     * @return выписку ЕГРЮЛ (3.txt)
     */
    public String getAbsolutePathToEGRJLdoc() {
        return getFullPathTo(this.getAttachmentsFolder() + this.getConfigParameter("EgrjlDoc"));
    }

    /**
     * Возвращает полномочия на получение аккредитации (4.txt).
     *
     * @return полномочия на получение аккредитации (4.txt)
     */
    public String getAbsolutePathToAccreditationRequestAuthorityDoc() {
        return getFullPathTo(this.getAttachmentsFolder() + this.getConfigParameter("AccrRequestAuthorityDoc"));
    }

    /**
     * Возвращает доверенность (5.txt).
     *
     * @return доверенность (5.txt)
     */
    public String getAbsolutePathToAttorneyLetterDoc() {
        return getFullPathTo(this.getAttachmentsFolder() + this.getConfigParameter("AttorneyLetterDoc"));
    }

    /**
     * Возвращает документ, удостоверяющий личность (6.txt).
     *
     * @return документ, удостоверяющий личность (6.txt)
     */
    public String getAbsolutePathToUserIdDoc() {
        return getFullPathTo(this.getAttachmentsFolder() + this.getConfigParameter("UserIdDoc"));
    }

    /**
     * Возвращает выписку из ЕГРИП (7.txt).
     *
     * @return выписку из ЕГРИП (7.txt)
     */
    public String getAbsolutePathToUserEGRIPDoc() {
        return getFullPathTo(this.getAttachmentsFolder() + this.getConfigParameter("UserEgripDoc"));
    }

    /**
     * Возвращает полномочия пользователя (8.txt).
     *
     * @return полномочия пользователя (8.txt)
     */
    public String getAbsolutePathToUserAuthorityDoc() {
        return getFullPathTo(this.getAttachmentsFolder() + this.getConfigParameter("UserAuthorityDoc"));
    }

    /**
     * Возвращает документ, удостоверяющий личность (9.txt).
     *
     * @return документ, удостоверяющий личность (9.txt)
     */
    public String getAbsolutePathToUserEntityDoc() {
        return getFullPathTo(this.getAttachmentsFolder() + this.getConfigParameter("UserEntityDoc"));
    }

    /**
     * Возвращает документ с позициями Плана Закупок для импорта (PurchasePlan.xlsx).
     *
     * @return документ с позициями Плана Закупок для импорта (PurchasePlan.xlsx)
     */
    public String getAbsolutePathToTradePlanPositions() {
        return getFullPathTo(this.getAttachmentsFolder() + this.getConfigParameter("TradePlanPositionsDoc"));
    }

    /**
     * Возвращает документ с позициями Плана Закупок ТРУ для импорта (ProcurementPlanOfGoodsWorksAndServices.xlsx).
     *
     * @return документ с позициями Плана Закупок ТРУ для импорта (ProcurementPlanOfGoodsWorksAndServices.xlsx)
     */
    public String getAbsolutePathToProcurPlanOfGoodsWorksAndServicesPositions() {
        return getFullPathTo(this.getAttachmentsFolder() +
                this.getConfigParameter("ProcurementPlanOfGoodsWorksAndServices"));
    }

    /**
     * Возвращает документ с позициями Ценовых Предложений Поставщика для импорта (SupplierPriceOffer1.xlsx).
     *
     * @return документ с позициями Ценовых Предложений Поставщика для импорта (SupplierPriceOffer1.xlsx)
     */
    public String getAbsolutePathToSupplierPriceOffer() {
        return getFullPathTo(this.getAttachmentsFolder() + this.getConfigParameter("SupplierPriceOfferDoc"));
    }

    /**
     * Возвращает файл с с изображением в формате .png (Attachment.png).
     *
     * @return файл с с изображением в формате .png (Attachment.png)
     */
    public String getAbsolutePathToSimplePingPicture() {
        return getFullPathTo(this.getAttachmentsFolder() + this.getConfigParameter("SimplePingPicture"));
    }

    /**
     * Устанавливает параметр для конкретного теста.
     *
     * @param key   ключ
     * @param value значение
     */
    public void setParameter(String key, String value) {
        Assert.assertNotNull(">>> (setParameter) Ключ параметра не определен [null].", key);
        Assert.assertNotEquals(">>> (setParameter) Ключ параметра является пустой строкой.", "", key);
        Assert.assertNotNull(String.format(">>> (setParameter) Значение параметра [%s] не определено [null].", key),
                value);
        Assert.assertNotEquals(String.format(">>> (setParameter) Значение параметра [%s] является пустой строкой.", key),
                "", value);

        System.out.println(">>> (setParameter) Ключ: [" + key + "], Значение: [" + value + "].");
        this.parameters.put(key, value);
    }

    /**
     * Читает параметр для конкретного теста.
     *
     * @param key ключ
     * @return значение
     */
    public String getParameter(String key) {
        String value = this.parameters.get(key);

        Assert.assertNotNull(String.format("<<< (getParameter) Параметр [%s] не найден.", key), value);
        Assert.assertNotEquals(String.format("<<< (getParameter) Параметр [%s] является пустой строкой.", key),
                "", value);

        System.out.printf("<<< (getParameter) Ключ: [%s], Значение: [%s].%n", key, value);
        return value;
    }

    /**
     * Проверяет существование параметра для конкретного теста.
     *
     * @param key ключ
     * @return true если параметр существует
     */
    public boolean checkParameter(String key) {
        return this.parameters.get(key) != null;
    }

    /**
     * Печатает список всех параметров, использованных в текущем тесте.
     */
    public void printParameters() {
        System.out.println();
        System.out.printf(
                "=========   С П И С О К   И С П О Л Ь З О В А Н Н Ы Х   П А Р А М Е Т Р О В   [%d]   =========%n",
                parameters.size());
        for (Map.Entry<String, String> parameter : parameters.entrySet())
            System.out.printf(
                    ">>> (printParameters) Ключ: [%s], Значение: [%s].%n", parameter.getKey(), parameter.getValue());
    }

    /**
     * Устанавливает ссылку на экземпляр класса Scenario (Сucumber JVM Scenario object).
     *
     * @param scenario ссылка на экземпляр класса Scenario (Сucumber JVM Scenario object)
     */
    public void setScenario(Scenario scenario) {
        Assert.assertNotNull("Параметр scenario содержит null !", scenario);
        System.out.printf("[ИНФОРМАЦИЯ]: ссылка на экземпляр класса Scenario [%s] получена успешно.%n",
                scenario.getName());
        this.scenario = scenario;
    }

    /**
     * Возвращает ссылку на экземпляр класса Scenario (Сucumber JVM Scenario object).
     *
     * @return ссылка на экземпляр класса Scenario (Сucumber JVM Scenario object)
     */
    public Scenario getScenario() {
        System.out.println("[ИНФОРМАЦИЯ]: ссылка на экземпляр класса Scenario возвращена успешно.");
        System.out.println("[ИНФОРМАЦИЯ]: Стек вызова методов:");
        System.out.print("[ИНФОРМАЦИЯ]: ");
        Exception ex = new Exception();
        StackTraceElement[] stackTraceElements = ex.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            if (stackTraceElement.getMethodName().equals("invoke0")) break;
            System.out.print(stackTraceElement.getMethodName() + " <-- ");
        }
        System.out.println(" ");
        return this.scenario;
    }

    /**
     * Устанавливает состояние режима снятия скриншотов.
     *
     * @param screenshotActive состояние режима снятия скриншотов
     */
    public void setScreenshotActive(boolean screenshotActive) {
        this.screenshotActive = screenshotActive;
    }

    /**
     * Возвращает состояние режима снятия скриншотов.
     *
     * @return состояние режима снятия скриншотов
     */
    boolean isScreenshotActive() {
        return screenshotActive;
    }

    /**
     * Сохраняет указатель на основное окно (вкладку) приложения в браузере.
     *
     * @param mainWindowHandle указатель на основное окно (вкладку) приложения в браузере
     */
    public void setMainWindowHandle(String mainWindowHandle) {
        String pref = ">>> (setMainWindowHandle)";

        Assert.assertTrue(pref + "Попытка установить пустой указатель на основное окно приложения в браузере.",
                mainWindowHandle != null && !mainWindowHandle.equals(""));

        if (this.mainWindowHandle != null && !this.mainWindowHandle.equals(""))
            System.out.printf("%s Перезапись указателя на основное окно приложения [%s].%n", pref, mainWindowHandle);
        else
            System.out.printf("%s Сохранение указателя на основное окно приложения [%s].%n", pref, mainWindowHandle);
        System.out.printf("%s Окно приложения имеет заголовок [%s].%n", pref, title());

        this.mainWindowHandle = mainWindowHandle;
    }

    /**
     * Возвращает указатель на основное окно (вкладку) приложения в браузере.
     *
     * @return указатель на основное окно (вкладку) приложения в браузере
     */
    private String getMainWindowHandle() {
        Assert.assertTrue(">>> (getMainWindowHandle) Попытка использовать пустой указатель на основное окно" +
                " приложения в браузере.", mainWindowHandle != null && !mainWindowHandle.equals(""));

        return this.mainWindowHandle;
    }

    /**
     * Переключается на открывшееся в браузере новое окно (вкладку) приложения.
     */
    public void switchToNewWindowInBrowser() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        for (String winHandle : driver.getWindowHandles())
            driver.switchTo().window(winHandle);
    }

    /**
     * Переключается на основное окно (вкладку) приложения в браузере.
     */
    public void switchToMainWindowInBrowser() {
        driver.switchTo().window(this.getMainWindowHandle());
    }

    /**
     * Закрывает все открытые окна (вкладки) приложения в браузере кроме первой и переключается на нее.
     *
     * @param driver экземпляр WebDriver
     */
    public void closeActiveWindowsAndSwitchToMainWindowInBrowser(WebDriver driver) throws Exception {
        int totalHandles = driver.getWindowHandles().size();
        String pref = ">>> (closeActiveWindowsAndSwitchToMainWindowInBrowser) ";
        String message0 = pref + "На момент вызова метода в браузере нет ни одной открытой вкладки.";
        String message1 = pref + "Произведено закрытие вкладки [%s], осталось открытых вкладок: [%d].";
        String message2 = pref + "В браузере открыто более одной вкладки.";
        String message3 = pref + "Текущая активная вкладка не является основной для приложения.";
        String message4 = pref + "Произведено закрытие всех вкладок, кроме вкладки: [%s].";
        String message5 = pref + "На момент вызова метода в браузере была открыта только одна вкладка [%s].";

        Assert.assertNotEquals(message0, 0, totalHandles);

        if (totalHandles > 1) {
            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(this.getMainWindowHandle())) {
                    getWebDriver().switchTo().window(handle);
                    System.out.printf((message1) + "%n", getWebDriver().getTitle(),
                            getWebDriver().getWindowHandles().size() - 1);
                    driver.close();
                    TimeUnit.SECONDS.sleep(3);
                }
            }
        }

        getWebDriver().switchTo().window(this.getMainWindowHandle());

        Assert.assertEquals(message2, 1, driver.getWindowHandles().size());
        Assert.assertEquals(message3, this.getMainWindowHandle(), driver.getWindowHandle());

        System.out.printf((totalHandles == 1 ? message5 : message4) + "%n", getWebDriver().getTitle());
    }

    /**
     * Возвращает путь к временной папке со случайно сгенерированным именем.
     *
     * @return путь к временной папке со случайно сгенерированным именем
     */
    public String getPathToTempFolderWithRandomName() {
        return this.pathToTempFolderWithRandomName;
    }

    /**
     * Возвращает минимально допустимую длину загруженного на диск файла.
     *
     * @return минимально допустимая длина загруженного на диск файла
     */
    public long getMinDownloadedFileLength() {
        return Long.parseLong(this.getConfigParameter("MinDownloadedFileLength"));
    }

    /**
     * Возвращает номер счёта в зависимости от типа аккредитуемого Пользователя.
     *
     * @return номер счёта
     */
    public String getBankAccountNumber() {
        Dictionary bankAccountNumbers = new Dictionary();
        bankAccountNumbers.add("Поставщик Физическое лицо", "40815222222222222222");
        bankAccountNumbers.add("Поставщик Индивидуальный предприниматель", "40805222222222222222");
        bankAccountNumbers.add("Поставщик Юридическое лицо", "22222222222222222222");
        bankAccountNumbers.add("Заказчик Юридическое лицо", "22222222222222222222");
        bankAccountNumbers.add("Заказчик Индивидуальный предприниматель", "40805222222222222222");

        return bankAccountNumbers.find(this.getParameter("AccreditationUserType"));
    }
}
