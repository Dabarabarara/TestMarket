package ru.PageObjects.Customer.Protocol;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.PrintFormsPreview.ApplicationViewPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Класс описывающий страницу [Протокол открытия доступа .........] (customer/lk/Protocols/ProtocolForm/).
 * Created by Vladimir V. Klochkov on 21.10.2016.
 * Updated by Alexander S. Vasyurenko on 18.06.2021.
 */
public class OpenAccessCustomerProtocolPage extends CommonCustomerProtocolPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //region Заголовок страницы [Список запросов]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы [Протокол открытия доступа конкурса]
    private static final String OPEN_ACCESS_PROTOCOL_PAGE_HEADER_XPATH =
            "//h1[contains(., 'Протокол открытия доступа конкурса')]";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Раздел [Общие сведения о лоте]

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Раздел [Общие сведения о протоколе]

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Таблица [Сведения протокола открытия доступа]

    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола открытия доступа], строка с наименованиями столбцов
    private static final String TABLE_ROW_WITH_ALL_COLUMN_NAMES_XPATH =
            "//h2[contains(., 'Сведения протокола открытия доступа')]/following-sibling::div[1]//table//tr/th";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола открытия доступа], первая строка с данными, все колонки таблицы
    private static final String TABLE_FIRST_DATA_ROW_ALL_COLUMNS_XPATH =
            "//h2[contains(., 'Сведения протокола открытия доступа')]/following-sibling::div[1]//table//tr[2]/td";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола открытия доступа], заголовок столбца [Ценовое предложение]
    private static final String TABLE_HEADER_PRICE_OFFER_COLUMN_HEADER_XPATH =
            "//h2[contains(., 'Сведения протокола открытия доступа')]/following-sibling::div[1]" +
                    "//th[contains(., 'Ценовое') and contains(., 'предложение')]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола открытия доступа], ссылки в столбце [Заявка]
    private static final String LINKS_IN_COLUMN_APPLICATION_XPATH =
            "//table//td/a[@class='DownloadApplicationLink' and contains(@href, 'TradeLotApplicationPrintForm?')]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола открытия доступа], ссылки в столбце [Запрос на разъяснение заявки]
    private static final String LINKS_IN_COLUMN_REGISTER_INQUIRIES_XPATH =
            "//table//td//a[@class='DownloadApplicationLink' and contains(@href, 'RegisterOfInquiries/CreateInquiry')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Скачать заявки]
    private static final String DOWNLOAD_REQUESTS_BUTTON_ID = "downloadAppsTemplate";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Раздел [Формирование протокола из шаблона]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок [Формирование протокола из шаблона]
    private static final String FORM_PROT_FROM_TPL_HDR_XPATH = "//div[@id='filesItemTemplate']/preceding-sibling::*[2]";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final ApplicationViewPage applicationViewPage = new ApplicationViewPage(driver);

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public OpenAccessCustomerProtocolPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Делает щелчок мышью по основному заголовку страницы [Протокол открытия доступа конкурса].
     */
    public void clickOnMainPageHeader() throws Exception {
        $(By.xpath(OPEN_ACCESS_PROTOCOL_PAGE_HEADER_XPATH)).waitUntil(visible, timeout).click();
        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * Перемещает страницу к разделу [Формирование протокола из шаблона].
     */
    public void moveToFormingProtocolFromTemplateHeader() throws Exception {
        $(By.xpath(FORM_PROT_FROM_TPL_HDR_XPATH)).waitUntil(visible, timeout).click();
        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * Проверяет возможность скачать прикрепленные к протоколу заявки в разделе [Сведения протокола открытия доступа].
     */
    public void checkPossibilityToDownloadRequestsFromOpenAccessProtocolPage() throws Exception {
        this.checkPossibilityToDownloadFileWithGeneratedName(By.id(DOWNLOAD_REQUESTS_BUTTON_ID),
                "Скачать заявки");
    }

    /**
     * Проверяет отсутствие столбца [Ценовое предложение] в разделе [Сведения протокола открытия доступа].
     *
     * @param expectedColumns ожидаемое количество столбцов в таблице раздела [Сведения протокола открытия доступа]
     */
    public void checkThatThePriceOfferColumnHiddenInTableWhenProtocolHasFormingState(int expectedColumns) {
        ElementsCollection allColumns = $$(By.xpath(TABLE_FIRST_DATA_ROW_ALL_COLUMNS_XPATH));
        allColumns.get(0).waitUntil(visible, timeout, polling);
        System.out.printf("[ИНФОРМАЦИЯ]: ожидаемое количество столбцов в таблице: [%d], реальное [%d].%n",
                expectedColumns, allColumns.size());
        Assert.assertEquals("[ОШИБКА]: количество столбцов в таблице не соответствует ожидаемому",
                expectedColumns, allColumns.size());

        SelenideElement columnHeader = $(By.xpath(TABLE_HEADER_PRICE_OFFER_COLUMN_HEADER_XPATH));
        System.out.println("[ИНФОРМАЦИЯ]: проверяем отсутствие столбца [Ценовое предложение].");
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        columnHeader.shouldBe(hidden);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        System.out.println("[ИНФОРМАЦИЯ]: столбец [Ценовое предложение] скрыт - проверка прошла успешно.");
    }

    /**
     * Проверяет наличие столбца [Ценовое предложение] в разделе [Сведения протокола открытия доступа].
     *
     * @param expectedColumns ожидаемое количество столбцов в таблице раздела [Сведения протокола открытия доступа]
     */
    public void checkThatThePriceOfferColumnVisibleInTableWhenProtocolHasFormingState(int expectedColumns) {
        ElementsCollection allColumns = $$(By.xpath(TABLE_FIRST_DATA_ROW_ALL_COLUMNS_XPATH));
        allColumns.get(0).waitUntil(visible, timeout, polling);
        System.out.printf("[ИНФОРМАЦИЯ]: ожидаемое количество столбцов в таблице: [%d], реальное [%d].%n",
                expectedColumns, allColumns.size());
        Assert.assertEquals("[ОШИБКА]: количество столбцов в таблице не соответствует ожидаемому",
                expectedColumns, allColumns.size());

        SelenideElement columnHeader = $(By.xpath(TABLE_HEADER_PRICE_OFFER_COLUMN_HEADER_XPATH));
        System.out.println("[ИНФОРМАЦИЯ]: проверяем наличие столбца [Ценовое предложение].");
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        columnHeader.shouldBe(visible);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        System.out.println("[ИНФОРМАЦИЯ]: столбец [Ценовое предложение] не скрыт - проверка прошла успешно.");
    }

    /**
     * Проверяет наличие таблицы [Ценовое предложение] в печатных формах заявок на участие от Поставщиков.
     */
    public void checkThatThePriceOfferTableVisibleInApplicationPrintForms() throws Exception {
        // Операции в окне [Протокол открытия доступа ...] (основное окно приложения)
        String mainWindow = getWebDriver().getWindowHandle();
        ElementsCollection links = $$(By.xpath(LINKS_IN_COLUMN_APPLICATION_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] ссылок на печатные формы заявок.%n", links.size());

        for (SelenideElement link : links) {
            link.waitUntil(clickable, timeout, polling);
            this.clickInElementJS(link);
            for (String winHandle : getWebDriver().getWindowHandles()) getWebDriver().switchTo().window(winHandle);

            //==========================================================================================================
            // Операции в окне [Заявка на участие в ...] (Окно было открыто по ссылке, печатная форма заявки)
            //==========================================================================================================
            System.out.println("[ИНФОРМАЦИЯ]: произошло переключение на окно с печатной формой заявки.");
            this.waitForPageLoad();
            applicationViewPage.checkThatThePriceOfferTableVisibleInApplicationPrintForm();
            getWebDriver().close();
            //==========================================================================================================

            // Возвращаемся в окно [Протокол открытия доступа ...] (основное окно приложения)
            getWebDriver().switchTo().window(mainWindow);
            System.out.println("[ИНФОРМАЦИЯ]: произошло переключение на основное окно приложения.");
            this.checkPageUrl("Один из протоколов");
        }
    }

    /**
     * Проверяет отсутствие таблицы [Ценовое предложение] в печатных формах заявок на участие от Поставщиков.
     */
    public void checkThatThePriceOfferTableHiddenInApplicationPrintForms() throws Exception {
        // Операции в окне [Протокол открытия доступа ...] (основное окно приложения)
        String mainWindow = getWebDriver().getWindowHandle();
        ElementsCollection links = $$(By.xpath(LINKS_IN_COLUMN_APPLICATION_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] ссылок на печатные формы заявок.%n", links.size());

        for (SelenideElement link : links) {
            link.waitUntil(clickable, timeout, polling);
            this.clickInElementJS(link);
            for (String winHandle : getWebDriver().getWindowHandles()) getWebDriver().switchTo().window(winHandle);

            //==========================================================================================================
            // Операции в окне [Заявка на участие в ...] (Окно было открыто по ссылке, печатная форма заявки)
            //==========================================================================================================
            System.out.println("[ИНФОРМАЦИЯ]: произошло переключение на окно с печатной формой заявки.");
            this.waitForPageLoad();
            applicationViewPage.checkThatThePriceOfferTableHiddenInApplicationPrintForm();
            getWebDriver().close();
            //==========================================================================================================

            // Возвращаемся в окно [Протокол открытия доступа ...] (основное окно приложения)
            getWebDriver().switchTo().window(mainWindow);
            System.out.println("[ИНФОРМАЦИЯ]: произошло переключение на основное окно приложения.");
            this.checkPageUrl("Один из протоколов");
        }
    }

    /**
     * Проверяет наименование столбца в таблице [Сведения протокола открытия доступа].
     *
     * @param positionColumnInTable - позиция столбца в таблице
     * @param expectedColumnName    - наименование столбца
     */
    public void checkTableColumnName(int positionColumnInTable, String expectedColumnName) {
        String errorMessage = String.format("[ОШИБКА]: имя столбца [%d] не соответствует ожидаемому значению [%s]",
                positionColumnInTable, expectedColumnName);
        ElementsCollection columns = $$(this.getBy(TABLE_ROW_WITH_ALL_COLUMN_NAMES_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] столбцов в таблице [Сведения протокола открытия доступа].%n",
                columns.size());
        String actualColumnName = columns.get(positionColumnInTable - 1).getText().replaceAll("\n", " ");
        Assert.assertEquals(errorMessage, actualColumnName, expectedColumnName);
    }

    /**
     * Открывает страницу просмотра печатной формы заявки на участие в конкурсе.
     *
     * @param printFormNumber - номер заявки печатной формы в таблице [Сведения протокола открытия доступа]
     */
    public void openApplicationPrintForms(int printFormNumber) throws Exception {
        ElementsCollection links = $$(By.xpath(LINKS_IN_COLUMN_APPLICATION_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] ссылок на печатные формы заявок.%n", links.size());
        this.clickInElementJS(links.get(printFormNumber - 1));
        System.out.printf("[ИНФОРМАЦИЯ]: выбрана [%d] ссылка на печатную форму заявки.%n", printFormNumber);
        this.waitForPageLoad();
        for (String winHandle : getWebDriver().getWindowHandles()) getWebDriver().switchTo().window(winHandle);
        this.checkPageUrl("Печатная форма заявки на участие от Поставщика (ПОД)");
    }

    /**
     * Открывает страницу [Запрос на разъяснение заявки]
     *
     * @param registerInquiriesNumber - номер запроса на разъяснение заявки [Сведения протокола открытия доступа]
     */
    public void openCreateInquiryPage(int registerInquiriesNumber) throws Exception {
        ElementsCollection links = $$(By.xpath(LINKS_IN_COLUMN_REGISTER_INQUIRIES_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] ссылок на создание запроса на разъяснение заявки.%n",
                links.size());
        this.clickInElementJS(links.get(registerInquiriesNumber - 1));
        System.out.printf("[ИНФОРМАЦИЯ]: выбрана [%d] ссылка на печатную форму заявки.%n", registerInquiriesNumber);
        this.waitForPageLoad();
        for (String winHandle : getWebDriver().getWindowHandles()) getWebDriver().switchTo().window(winHandle);
        this.checkPageUrl("Запрос на разъяснение заявки - создание");
    }
}
