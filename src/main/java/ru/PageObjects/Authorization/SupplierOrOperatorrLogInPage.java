package ru.PageObjects.Authorization;

import Helpers.TestSets;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonPage;
import ru.PageObjects.Supplier.CommonSupplierPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Класс, реализующий функционал входа в личный кабинет Поставщика или Оператора / Администратора с помощью сертификата.
 * Created by Vladimir V. Klochkov on 22.05.2020.
 * Updated by Vladimir V. Klochkov on 11.03.2021.
 */
public class SupplierOrOperatorrLogInPage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Имя пользователя (логин)]
    private static final String LOGIN_ID = "MainContent_txtUserName";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Пароль]
    private static final String PASSWORD_ID = "MainContent_txtUserPassword";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Войти]
    private static final String LOGIN_BUTTON_ID = "MainContent_btnLogin";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Войти по ЭЦП]
    private static final String SELECT_CERTIFICATE_BUTTON_ID = "btnSelectCert";
    //------------------------------------------------------------------------------------------------------------------
    // Окно [Выберите сертификат]
    private static final String CERTIFICATE_WINDOW_HEADER_XPATH =
            "//*[@class='ui-dialog-title' and contains(., 'Выберите сертификат')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Сертификат] в таблице со списком сертификатов
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Список всех сертификатов в столбце
    private static final String CERTIFICATE_COL_XPATH =
            "//*[@id='jqgh_dlgSelectCertificate_CertTable_Name']/tbody/tr/td[@class='name']";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Подсвеченный (выбранный) сертификат в столбце
    private static final String SELECTED_CERT_NAME_XPATH = "//*[@id='jqgh_dlgSelectCertificate_CertTable_Name']//" +
            "tr[contains(@class, 'ui-state-highlight')]/td[@class='name']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Организация] в таблице со списком сертификатов
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Список всех организаций в столбце
    private static final String ORGANIZATION_COL_XPATH =
            "//*[@id='jqgh_dlgSelectCertificate_CertTable_Name']/tbody/tr/td[@class='organization']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [ОК] в окне [Выберите сертификат]
    private static final String ОК_BUTTON_ID = "btnOK";
    //------------------------------------------------------------------------------------------------------------------
    /// Имя Пользователя на странице в правом верхнем углу после входа в личный кабинет по сертификату
    private static final String USER_NAME_CERTIFICATE_XPATH = "//*[@id='BaseMainContent_ucHeaderControl_hlEmployee']";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final long maxtime = 900000; // время ожидания длительных операций (миллисекунды)

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public SupplierOrOperatorrLogInPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Осуществляет переход на страницу входа в систему.
     *
     * @return ссылка на экземпляр этого класса
     */
    public SupplierOrOperatorrLogInPage goToUserLoginPage() {
        String url = TestSets.getInstance().getSuppliersLoginPageUrl(config.getParameter("TestType"));

        System.out.printf(">>> (goToUserLoginPage) Переходим на страницу входа с адресом: [%s].%n", url);
        open(url);
        $(By.id(LOGIN_ID)).waitUntil(clickable, 300000, 1000);

        // Проверка открытия нужной страницы
        Assert.assertTrue(
                ">>> (goToUserLoginPage) Страница входа на площадку проекта 223 Поставщиком не открыта !",
                url().contains(".kontur.ru/supplier/"));

        return this;
    }

    /**
     * Открывает страницу входа в Личный Кабинет для Поставщика или Администратора ( Оператора ) в открытой части.
     *
     * @return ссылка на экземпляр этого класса
     */
    public SupplierOrOperatorrLogInPage goToOpenPartPage() throws Exception {
        open(config.getOpenPartSupplerLoginUrl());
        this.checkPageUrl("Авторизация Поставщиком");

        return this;
    }

    /**
     * Заполняет поле [Логин] требуемым значением с помощью JS.
     *
     * @param login значение поля
     * @return ссылка на экземпляр этого класса
     */
    public SupplierOrOperatorrLogInPage setLogin(String login) throws Exception {
        SelenideElement loginField = $(By.id(LOGIN_ID));
        loginField.waitUntil(clickable, timeout, polling);
        this.setValueInElementJS(LOGIN_ID, login);
        this.logFilledFieldName("Имя пользователя (логин)", login);
        loginField.shouldHave(value(login));

        return this;
    }

    /**
     * Заполняет поле [Пароль] требуемым значением с помощью JS.
     *
     * @param password значение поля
     * @return ссылка на экземпляр этого класса
     */
    public SupplierOrOperatorrLogInPage setPassword(String password) throws Exception {
        SelenideElement passwordField = $(By.id(PASSWORD_ID));
        passwordField.waitUntil(clickable, timeout, polling);
        this.setValueInElementJS(PASSWORD_ID, password);
        this.logFilledFieldName("Пароль", password);
        passwordField.shouldHave(value(password));

        return this;
    }

    /**
     * Делает клик по кнопке [Войти] с помощью JS.
     */
    public void clickOnLoginButton() throws Exception {
        $(By.id(LOGIN_BUTTON_ID)).waitUntil(clickable, timeout, polling).scrollTo();
        this.logButtonNameToPress("Войти");
        this.clickInElementJS(By.id(LOGIN_BUTTON_ID));
        this.logPressedButtonName("Войти");

        this.waitForPageLoad();
        this.checkPageUrl("Главная страница-Поставщик", "Предложения для Вас");
        this.checkAdvertisingDialogWindow();
    }

    /**
     * Делает клик по кнопке [Войти по ЭЦП].
     */
    public void clickSelectCertificateButton() {
        this.logButtonNameToPress("Войти по ЭЦП");
        $(By.id(SELECT_CERTIFICATE_BUTTON_ID)).waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName("Войти по ЭЦП");
    }

    /**
     * Осуществляет вход в личный кабинет Поставщика или Оператора / Администратора с помощью сертификата.
     *
     * @param certName имя сертификата
     */
    public void loginAsSupplierOrOperator(String certName) throws Exception {

        // Устанавливаем короткое время ожидания для WebDriver
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        // Ожидаем появления кнопки [Войти по ЭЦП] и нажимаем её
        SelenideElement button = $(By.id(SELECT_CERTIFICATE_BUTTON_ID));
        button.waitUntil(clickable, maxtime, polling);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

        // Ожидаем появления окна [Выберите сертификат]
        $(By.xpath(CERTIFICATE_WINDOW_HEADER_XPATH)).waitUntil(clickable, maxtime, polling);

        // Получаем список доступных сертификатов из столбца [Сертификат]
        ElementsCollection listOfExistedCertificates = $$(By.xpath(CERTIFICATE_COL_XPATH));

        // Получаем список доступных организаций из столбца [Организация]
        ElementsCollection listOfExistedOrganizations = $$(By.xpath(ORGANIZATION_COL_XPATH));

        System.out.printf("[ИНФОРМАЦИЯ]: ======= Таблица содержит [%d] сертификатов =======%n",
                listOfExistedCertificates.size());

        for (int i = 0; i < listOfExistedCertificates.size(); i++)
            System.out.printf("[ИНФОРМАЦИЯ]: в наличии сертификат: [%s], для организации [%s]...%n",
                    listOfExistedCertificates.get(i).getText(), listOfExistedOrganizations.get(i).getText());

        System.out.printf("[ИНФОРМАЦИЯ]: ------- Ищем сертификат с именем: [%s].%n", certName);

        // Проходим по списку сертификатов до сертификата с указанным именем и выбираем его
        boolean okButtonIsEnabled = false;
        String reallySelectedText = "";

        for (SelenideElement certificate : listOfExistedCertificates) {
            System.out.printf("[ИНФОРМАЦИЯ]: проверка имени сертификата: [%s]...%n", certificate.getText());

            // Нашли требуемый сертификат, пытаемся его выбрать
            if (certificate.getText().equals(certName)) {
                System.out.printf("[ИНФОРМАЦИЯ]: нашли, выбираем сертификат: [%s].%n", certificate.getText());
                this.clickInElementJS(certificate);
                TimeUnit.SECONDS.sleep(1);
                reallySelectedText = $(By.xpath(SELECTED_CERT_NAME_XPATH)).getText();
                okButtonIsEnabled = $(By.id(ОК_BUTTON_ID)).waitUntil(clickable, maxtime, polling).isEnabled();
                break;
            }
        }

        // Проверяем, что сертификат точно был выбран и кнопка [OK] стала активной
        Assert.assertEquals("[ОШИБКА]: не удалось выбрать сертификат в таблице", certName, reallySelectedText);
        Assert.assertTrue("[ОШИБКА]: кнопка [OK] не активна", okButtonIsEnabled);
        System.out.println("[ИНФОРМАЦИЯ]: сертификат был выбран успешно и кнопка [OK] стала активной.");

        // Нажимаем на кнопку [OK]
        System.out.println("[ИНФОРМАЦИЯ]: нажимаем на кнопку [OK].");
        $(By.id(ОК_BUTTON_ID)).waitUntil(clickable, maxtime, polling).click();

        // Дожидаемся окончания загрузки страницы
        this.waitLoadingImage();

        // Проверяем, что мы зашли в личный кабинет Пользователя
        $(By.xpath(USER_NAME_CERTIFICATE_XPATH)).waitUntil(appear, maxtime, polling).shouldBe(visible);
        System.out.println("[ИНФОРМАЦИЯ]: вход в личный кабинет был выполнен успешно.");

        if (!$(By.xpath(USER_NAME_CERTIFICATE_XPATH)).getText().contains("Администратор")) {
            CommonSupplierPage commonSupplierPage = new CommonSupplierPage(driver);
            commonSupplierPage.checkAdvertisingDialogWindow();
        }

        // Устанавливаем время ожидания по умолчанию для WebDriver
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }
}
