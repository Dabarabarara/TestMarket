package ru.PageObjects.Customer.Protocol;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.PrintFormsPreview.ApplicationViewPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Класс описывающий страницу [Протокол рассмотрения (и оценки) заявок ...]
 * ( .kontur.ru/customer/lk/Protocols/ProtocolForm/?tradeId= ).
 * Created by Evgeniy Glushko on 24.03.2016.
 * Updated by Alexander S. Vasyurenko on 30.06.2021.
 */
public class ConsiderationRequestCustomerProtocolPage extends CommonCustomerProtocolPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Раздел [Страница целиком]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы [Протокол рассмотрения (и оценки) заявок ...]
    private static final String CONSIDERATION_PROTOCOL_PAGE_HEADER_XPATH = "//h1[contains(., 'Протокол рассмотрения')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Общие сведения о лоте]

    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Провести переторжку]
    private static final String RETRAIDING_ENABLED_CHECKBOX_XPATH = "//input[@class='js-retrading-enabled']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Общие сведения о протоколе]

    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Закупка признана несостоявшейся]
    private static final String MISSED_CONTEST_CHECKBOX_ID = "cbxMissedContest";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения протокола рассмотрения (и оценки) заявок]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок [Сведения протокола рассмотрения заявок]
    private static final String CONS_REQ_PROT_INFO_HEADER_XPATH = "//h2[contains(.,'Сведения протокола рассмотрения')]";
    //------------------------------------------------------------------------------------------------------------------
    // Переключатель [Заключить договор с единственным участником]
    private static final String SINGLE_PARTICIPANT_CONTRACT_XPATH =
            "//table[contains(@class, 'js-single-participant-contract table')]/tbody/tr//input";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола рассмотрения и оценки заявок], строка с наименованиями столбцов
    private static final String TABLE_ROW_WITH_ALL_COLUMN_NAMES_XPATH =
            "//h2[contains(., 'Сведения протокола рассмотрения и оценки заявок')]/following-sibling::div[1]//table//tr/th";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола рассмотрения (и оценки) заявок], первая строка с данными, все колонки таблицы
    private static final String TABLE_FIRST_DATA_ROW_ALL_COLUMNS_XPATH = CONS_REQ_PROT_INFO_HEADER_XPATH +
            "/following-sibling::div[1]/table[@class='table table7 apps-grid']/tbody/tr[2]/td";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола рассмотрения (и оценки) заявок], заголовок столбца [Ценовое предложение]
    private static final String TABLE_HEADER_PRICE_OFFER_COLUMN_HEADER_XPATH = CONS_REQ_PROT_INFO_HEADER_XPATH +
            "/following-sibling::div[1]//th[contains(., 'Ценовое') and contains(., 'предложение')]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола рассмотрения (и оценки) заявок], ссылки в столбце [Заявка]
    private static final String TABLE_APPLICATION_LINKS_XPATH = "//table//td/a[@class='DownloadApplicationLink' " +
            "and contains(@href, 'GetTradeLotApplicationPrintFormContentForProtocol?')]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола рассмотрения (и оценки) заявок], ссылки в столбце [Сведения об участнике закупки]
    private static final String PURCHASE_INFORM_LINKS_XPATH = "//table//td/a[@class='DownloadApplicationLink' " +
            "and contains(@href, 'TradeLotApplicationPrintFormPurchaserInfo?')]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола рассмотрения (и оценки) заявок], ссылки в столбце
    // [Запрос на разъяснение заявки]
    private static final String TABLE_REQUEST_FOR_EXPLANATION_LINKS_XPATH =
            "//table//a[@class='DownloadApplicationLink' and contains(.,'Отправить')]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола рассмотрения (и оценки) заявок], флажки в столбце [Субъект МСП]
    private static final String CHECKBOXES_IN_SMB_ENTITY_COLUMN_XPATH =
            "//table//td[8]/input[@type='checkbox']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения протокола рассмотрения первых частей заявок по лоту 1] аукцион (заявка в двух частях)

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок [Сведения протокола рассмотрения первых частей заявок по лоту 1]
    // private static final String A2_PROTOCOL_DETAILS_HEADER_XPATH =
    //        "//h2[contains(., 'Сведения протокола рассмотрения первых частей заявок')]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола рассмотрения первых частей заявок], все заголовки колонок таблицы
    private static final String APPLICATION_TABLE_HEADERS_XPATH = "//*[@id='layoutwrapper']//table/tbody/tr/th";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Решение комиссии] аукцион (заявка в двух частях):
    //------------------------------------------------------------------------------------------------------------------
    // Строка 1
    private static final String OPEN_DECISION_LIST_2P1_XPATH = "//*/table/tbody/tr[2]/td/p/span/span/span";
    private static final String DECISION_LIST_2P1_ROW_3_XPATH =
            "//div[@id='LotInfos_0__TemplateId-list']/preceding-sibling::div[3]/div/ul/li[3]";
    private static final String OPEN_DECISION_REASON_LIST_2P1_XPATH = "//*/table/tbody/tr[2]/td/p/span[2]/span/span/span";
    private static final String DECISION_REASON_LIST_2P1_ROW_1_XPATH = "html/body/div/div/ul/li[1]/span";
    private static final String DECISION_REASON_TEXT_2P1_XPATH = "//*/table/tbody/tr[2]/td/div/textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Строка 2
    private static final String OPEN_DECISION_LIST_2P2_XPATH = "//*/table/tbody/tr[4]/td/p/span/span/span";
    private static final String DECISION_LIST_2P2_ROW_3_XPATH =
            "//div[@id='LotInfos_0__TemplateId-list']/preceding-sibling::div[2]/div/ul/li[3]";
    private static final String OPEN_DECISION_REASON_LIST_2P2_XPATH = "//*/table/tbody/tr[4]/td/p/span[2]/span/span/span";
    private static final String DECISION_REASON_LIST_2P2_ROW_1_XPATH = "html/body/div/div/ul/li[1]/span";
    private static final String DECISION_REASON_TEXT_2P2_XPATH = "//*/table/tbody/tr[4]/td/div/textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Строка 3
    private static final String OPEN_DECISION_LIST_2P3_XPATH = "//*/table/tbody/tr[6]/td/p/span/span/span";
    private static final String DECISION_LIST_2P3_ROW_3_XPATH =
            "//div[@id='LotInfos_0__TemplateId-list']/preceding-sibling::div[1]/div/ul/li[3]";
    private static final String OPEN_DECISION_REASON_LIST_2P3_XPATH = "//*/table/tbody/tr[6]/td/p/span[2]/span/span/span";
    private static final String DECISION_REASON_LIST_2P3_ROW_1_XPATH = "html/body/div/div/ul/li[1]/span";
    private static final String DECISION_REASON_TEXT_2P3_XPATH = "//*/table/tbody/tr[6]/td/div/textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Решение комиссии] Проведение полного электронного запроса котировок с попозиционным сравнением ценовых
    // предложенией:
    //------------------------------------------------------------------------------------------------------------------
    // Строка 1
    private static final String OPEN_DECISION_LIST_PC1_XPATH =
            "//table[@class='table table7 apps-grid']/tbody/tr[3]/td[4]/p/span/span/span[2]";
    private static final String DECISION_LIST_PC1_ROW_3_XPATH =
            "//div[@id='CommissionId-list']/following-sibling::div[1]/div/ul/li[3]";
    private static final String OPEN_DECISION_REASON_LIST_PC1_XPATH =
            "//table[@class='table table7 apps-grid']/tbody/tr[3]/td[4]/p/span/span/span/span[2]";
    private static final String DECISION_REASON_LIST_PC1_ROW_1_XPATH = "html/body/div/div/ul/li[1]/span";
    private static final String DECISION_REASON_TEXT_PC1_XPATH =
            "//table[@class='table table7 apps-grid']/tbody/tr[3]/td[4]/div/textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Строка 2
    private static final String OPEN_DECISION_LIST_PC2_XPATH =
            "//table[@class='table table7 apps-grid']/tbody/tr[5]/td[4]/p/span/span/span[2]";
    private static final String DECISION_LIST_PC2_ROW_3_XPATH =
            "//div[@id='CommissionId-list']/following-sibling::div[2]/div/ul/li[3]";
    private static final String OPEN_DECISION_REASON_LIST_PC2_XPATH =
            "//table[@class='table table7 apps-grid']/tbody/tr[5]/td[4]/p/span/span/span/span[2]";
    private static final String DECISION_REASON_LIST_PC2_ROW_1_XPATH = "html/body/div/div/ul/li[1]/span";
    private static final String DECISION_REASON_TEXT_PC2_XPATH =
            "//table[@class='table table7 apps-grid']/tbody/tr[5]/td[4]/div/textarea";
    //==================================================================================================================
    // Столбец со ссылками на печатные формы заявок в таблице протоколов - Аукцион (заявка в двух частях):
    // Сведения протокола рассмотрения первых частей заявок
    //==================================================================================================================
    // Строка 1
    private static final String OPEN_APPLICATION_1_1STPP_XPATH = "//*/table/tbody/tr[2]/td/" +
            "a[contains(@href, '/customer/lk/Iframe/TradeLotApplicationPrintForm?applicationId')]";
    //------------------------------------------------------------------------------------------------------------------
    // Строка 2
    private static final String OPEN_APPLICATION_2_1STPP_XPATH = "//*/table/tbody/tr[4]/td/" +
            "a[contains(@href, '/customer/lk/Iframe/TradeLotApplicationPrintForm?applicationId')]";
    //------------------------------------------------------------------------------------------------------------------
    // Строка 3
    private static final String OPEN_APPLICATION_3_1STPP_XPATH = "//*/table/tbody/tr[6]/td/" +
            "a[contains(@href, '/customer/lk/Iframe/TradeLotApplicationPrintForm?applicationId')]";
    //==================================================================================================================
    // Протокол проведения аукциона
    //==================================================================================================================
    // Строка 1
    private static final String OPEN_APPLICATION_1_AP_XPATH = "//*/table/tbody/tr[2]/td/" +
            "a[contains(@href, '/customer/lk/Iframe/TradeLotApplicationPrintForm?applicationId')]";
    //------------------------------------------------------------------------------------------------------------------
    // Строка 2
    private static final String OPEN_APPLICATION_2_AP_XPATH = "//*/table/tbody/tr[3]/td/" +
            "a[contains(@href, '/customer/lk/Iframe/TradeLotApplicationPrintForm?applicationId')]";
    //==================================================================================================================
    // Протокол рассмотрения вторых частей заявок аукциона
    //==================================================================================================================
    // Строка 1
    private static final String OPEN_APPLICATION_1_2NDPP_XPATH = "//*/table/tbody/tr[2]/td/" +
            "a[contains(@href, '/customer/lk/Iframe/TradeLotApplicationResultPrintForm?applicationId')]";
    //------------------------------------------------------------------------------------------------------------------
    // Строка 2
    private static final String OPEN_APPLICATION_2_2NDPP_XPATH = "//*/table/tbody/tr[4]/td/" +
            "a[contains(@href, '/customer/lk/Iframe/TradeLotApplicationResultPrintForm?applicationId')]";
    //==================================================================================================================
    // Протокол подведения итогов аукциона
    //==================================================================================================================
    // Строка 1
    private static final String OPEN_APPLICATION_1_SP_XPATH = "//*/table/tbody/tr[2]/td/" +
            "a[contains(@href, '/customer/lk/Iframe/GetTradeLotApplicationPrintFormContentForProtocol?applicationId')]";
    //------------------------------------------------------------------------------------------------------------------
    // Строка 2
    private static final String OPEN_APPLICATION_2_SP_XPATH = "//*/table/tbody/tr[3]/td/" +
            "a[contains(@href, '/customer/lk/Iframe/GetTradeLotApplicationPrintFormContentForProtocol?applicationId')]";
    //==================================================================================================================
    // Кнопка [Допустить всех]
    private static final String ALLOW_ALL_BUTTON_ID = "allAllowSTemplate";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Скачать заявки]
    private static final String DOWNLOAD_REQUESTS_BUTTON_ID = "downloadAppsTemplate";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Даты переторжки]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Даты переторжки]
    private static final String RETRAIDING_DATES_HEADER_XPATH = "//h2[contains(.,'Даты переторжки')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата начала переторжки]
    private static final String RETRAIDING_START_DATE_ID = "js-retrading-start-date";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата окончания переторжки]
    private static final String RETRAIDING_END_DATE_ID = "js-retrading-end-date";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о квалификационном отборе]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о квалификационном отборе]
    private static final String QUALIFICATION_INFORMATION_HEADER_XPATH =
            "//h2[contains(.,'Сведения о квалификационном отборе')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Допустить всех]
    private static final String QINFO_ALLOW_ALL_BUTTON_XPATH =
            "//h2[contains(.,'Сведения о квалификационном отборе')]/following-sibling::table[1]" +
                    "//*[@id='allAllowSTemplate']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения протокола рассмотрения вторых частей заявок]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения протокола рассмотрения вторых частей заявок]
    private static final String DETAILS_OF_THE_REVIEW_OF_THE_SECOND_PART_OF_APPLICATIONS_HEADER_XPATH =
            "//h2[contains(.,'Сведения протокола рассмотрения вторых частей заявок')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Допустить всех]
    private static final String DRSPAPP_ALLOW_ALL_BUTTON_XPATH =
            "//h2[contains(.,'Сведения протокола рассмотрения вторых частей заявок')]/following-sibling::table[1]" +
                    "//*[@id='allAllowSTemplate']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Оценка заявок ]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Оценка заявок]
    private static final String APPLICATION_RATING_XPATH = "//h2[contains(.,'Оценка заявок')]";
    //------------------------------------------------------------------------------------------------------------------
    // Строки таблицы раздела [Оценка заявок]
    private static final String ROWS_IN_APPLICATION_RATING_TABLE_XPATH = "//tr[@class='applicationRateTr ']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Формирование протокола из шаблона]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок [Формирование протокола из шаблона]
    private static final String FORM_PROT_FROM_TPL_HDR_XPATH = "//div[@id='filesItemTemplate']/preceding-sibling::*[2]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final ApplicationViewPage applicationViewPage = new ApplicationViewPage(driver);

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public ConsiderationRequestCustomerProtocolPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет, что информация о Поставщиках (Наименование организации, ИНН, номер в реестре) не отображается.
     * Только для сценария [Аукцион (заявка в двух частях)].
     */
    public void checkThatSuppliersInfoIsHiddenBeforeTheBidding() throws Exception {
        System.out.println("[ИНФОРМАЦИЯ]: проверяем, что информация о Поставщиках (Наименование организации, ИНН, КПП" +
                ") не отображается.");

        // Проверяем отсутствие столбца [Наименование организации]
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        ElementsCollection columns = $$(By.xpath(APPLICATION_TABLE_HEADERS_XPATH));
        this.checkColumnAbsenceInColumnsCollectionByText(columns, "Наименование");
        this.checkColumnAbsenceInColumnsCollectionByText(columns, "организации");
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        // Проверяем первую часть заявки от Поставщика 1
        this.checkThatSuppliersInfoIsHiddenInApplicationView(OPEN_APPLICATION_1_1STPP_XPATH);

        // Проверяем первую часть заявки от Поставщика 2
        this.checkThatSuppliersInfoIsHiddenInApplicationView(OPEN_APPLICATION_2_1STPP_XPATH);

        // Проверяем первую часть заявки от Поставщика 3
        this.checkThatSuppliersInfoIsHiddenInApplicationView(OPEN_APPLICATION_3_1STPP_XPATH);
    }

    /**
     * Проверяет, что информация о Поставщиках (Наименование организации, ИНН, номер в реестре) не отображается.
     * Только для сценария [Аукцион (заявка в двух частях)].
     */
    public void checkThatSuppliersInfoIsHiddenAfterTheBidding() throws Exception {
        System.out.println("[ИНФОРМАЦИЯ]: проверяем, что информация о Поставщиках (Наименование организации, ИНН, КПП" +
                ") не отображается.");

        // Проверяем отсутствие столбца [Наименование организации]
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        ElementsCollection columns = $$(By.xpath(APPLICATION_TABLE_HEADERS_XPATH));
        this.checkColumnAbsenceInColumnsCollectionByText(columns, "Наименование");
        this.checkColumnAbsenceInColumnsCollectionByText(columns, "организации");
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        // Проверяем первую часть заявки от Поставщика 1
        this.checkThatSuppliersInfoIsHiddenInApplicationView(OPEN_APPLICATION_1_AP_XPATH);

        // Проверяем первую часть заявки от Поставщика 2
        this.checkThatSuppliersInfoIsHiddenInApplicationView(OPEN_APPLICATION_2_AP_XPATH);
    }

    /**
     * Проверяет, что информация о Поставщиках (Наименование организации, ИНН, номер в реестре) отображается.
     * Только для сценария [Аукцион (заявка в двух частях)].
     */
    public void checkThatSuppliersInfoIsShownAfterTheBidding() throws Exception {
        System.out.println("[ИНФОРМАЦИЯ]: проверяем, что информация о Поставщиках " +
                "(Наименование организации, ИНН, КПП) отображается.");

        // Проверяем присутствие столбца [Наименование организации]
        ElementsCollection columns = $$(By.xpath(APPLICATION_TABLE_HEADERS_XPATH));
        this.checkColumnPresenceInColumnsCollectionByText(columns, "Наименование");
        this.checkColumnPresenceInColumnsCollectionByText(columns, "организации");

        // Проверяем вторую часть заявки от Поставщика 1
        this.checkThatSuppliersInfoIsShownInApplicationView(OPEN_APPLICATION_1_2NDPP_XPATH);

        // Проверяем вторую часть заявки от Поставщика 2
        this.checkThatSuppliersInfoIsShownInApplicationView(OPEN_APPLICATION_2_2NDPP_XPATH);
    }

    /**
     * Проверяет, что информация о Поставщиках (Наименование организации, ИНН, номер в реестре) отображается в ППИ.
     * Только для сценария [Аукцион (заявка в двух частях)].
     */
    public void checkThatSuppliersInfoIsShownAfterTheBiddingInSummaryProtocol() throws Exception {
        System.out.println("[ИНФОРМАЦИЯ]: проверяем, что информация о Поставщиках " +
                "(Наименование организации, ИНН, КПП) отображается.");

        // Проверяем присутствие столбца [Наименование организации]
        ElementsCollection columns = $$(By.xpath(APPLICATION_TABLE_HEADERS_XPATH));
        this.checkColumnPresenceInColumnsCollectionByText(columns, "Наименование");
        this.checkColumnPresenceInColumnsCollectionByText(columns, "организации");

        // Проверяем вторую часть заявки от Поставщика 1
        this.checkThatSuppliersInfoIsShownInApplicationView(OPEN_APPLICATION_1_SP_XPATH);

        // Проверяем вторую часть заявки от Поставщика 2
        this.checkThatSuppliersInfoIsShownInApplicationView(OPEN_APPLICATION_2_SP_XPATH);
    }

    /**
     * Проверяет, что информация о Поставщиках (Наименование организации, ИНН, номер в реестре) не отображается в заявке.
     * Только для сценария [Аукцион (заявка в двух частях)].
     *
     * @param linkXpath ссылка [Открыть] в столбце [Первая часть заявки]
     */
    private void checkThatSuppliersInfoIsHiddenInApplicationView(String linkXpath) throws Exception {
        // Операции в окне [Протокол рассмотрения первых частей заявок аукциона] (основное окно приложения)
        String mainWindow = driver.getWindowHandle();
        SelenideElement element = $(By.xpath(linkXpath));
        element.waitUntil(clickable, timeout, polling);
        this.clickInElementJS(element);
        for (String winHandle : driver.getWindowHandles()) driver.switchTo().window(winHandle);

        //==============================================================================================================
        // Операции в окне [Заявка на участие в аукционе] (Окно было открыто по ссылке)
        //==============================================================================================================
        this.waitForPageLoad();
        applicationViewPage.checkThatSuppliersInfoIsHidden();
        driver.close();
        //==============================================================================================================

        // Возвращаемся в окно [Протокол рассмотрения первых частей заявок аукциона] (основное окно приложения)
        driver.switchTo().window(mainWindow);
        this.checkPageUrl("Один из протоколов");
    }

    /**
     * Проверяет, что информация о Поставщиках (Наименование организации, ИНН, номер в реестре) отображается в заявке.
     * Только для сценария [Аукцион (заявка в двух частях)].
     *
     * @param linkXpath ссылка [Открыть] в столбце [Вторая часть заявки]
     */
    private void checkThatSuppliersInfoIsShownInApplicationView(String linkXpath) throws Exception {
        // Операции в окне [Протокол рассмотрения вторых частей заявок аукциона] (основное окно приложения)
        String mainWindow = driver.getWindowHandle();
        SelenideElement element = $(By.xpath(linkXpath));
        element.waitUntil(clickable, timeout, polling);
        this.clickInElementJS(element);
        for (String winHandle : driver.getWindowHandles()) driver.switchTo().window(winHandle);

        //==============================================================================================================
        // Операции в окне [Заявка на участие в аукционе] (Окно было открыто по ссылке)
        //==============================================================================================================
        this.waitForPageLoad();
        applicationViewPage.checkThatSuppliersInfoIsShown();
        driver.close();
        //==============================================================================================================

        // Возвращаемся в окно [Протокол рассмотрения вторых частей заявок аукциона] (основное окно приложения)
        driver.switchTo().window(mainWindow);
        this.checkPageUrl("Один из протоколов");
    }

    /**
     * Делает щелчок по кнопке [Допустить всех] для первого лота.
     */
    public void clickAllowAllButton() throws Exception {
        this.clickAllowAllButton("1");
    }

    /**
     * Делает щелчок по кнопке [Допустить всех] для лота с указанным порядковым номером.
     *
     * @param lotNumber порядковый номер лота
     */
    public void clickAllowAllButton(String lotNumber) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: нажимаем кнопку [Допустить всех] для лота с номером [%s].%n", lotNumber);
        int index = Integer.parseInt(lotNumber) - 1;
        ElementsCollection allowAllButtons = $$(By.id(ALLOW_ALL_BUTTON_ID));
        this.logButtonNameToPress("Допустить всех");
        this.scrollToCenter(allowAllButtons.get(index));
        allowAllButtons.get(index).waitUntil(clickable, timeout, polling);
        this.clickInElementJS(allowAllButtons.get(index));
        this.logPressedButtonName("Допустить всех");
    }

    /**
     * Делает щелчок по кнопке [Допустить всех] в разделе [Сведения о квалификационном отборе].
     */
    public void clickAllowAllButtonInQualificationInformationPartition() throws Exception {
        this.scrollToCenter(By.xpath(QUALIFICATION_INFORMATION_HEADER_XPATH));
        TimeUnit.SECONDS.sleep(1);
        ElementsCollection allowAllButtons = $$(By.xpath(QINFO_ALLOW_ALL_BUTTON_XPATH));
        this.logButtonNameToPress("Допустить всех");
        allowAllButtons.get(0).waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName("Допустить всех");
    }

    /**
     * Делает щелчок по кнопке [Допустить всех] в разделе [Сведения протокола рассмотрения вторых частей заявок].
     */
    public void clickAllowAllButtonInDetailsOfTheReviewSecondPartOfApplicationsPartition() throws Exception {
        $(By.xpath(DETAILS_OF_THE_REVIEW_OF_THE_SECOND_PART_OF_APPLICATIONS_HEADER_XPATH)).
                waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(1);
        ElementsCollection allowAllButtons = $$(By.xpath(DRSPAPP_ALLOW_ALL_BUTTON_XPATH));
        this.logButtonNameToPress("Допустить всех");
        allowAllButtons.get(0).waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName("Допустить всех");
    }

    /**
     * Отклоняет заявку от Поставщика с указанным порядковым номером с указанием оснований отказа.
     * Только для сценария
     * [Проведение полного электронного запроса котировок с попозиционным сравнением ценовых предложенией].
     *
     * @param supplierOrderNumber порядковый номер Поставщика
     */
    public void rejectSupplierRequestForPositionalComparison(String supplierOrderNumber) throws Exception {
        // Открываем список [Решение комиссии] для указанного Поставщика по его порядковому номеру
        this.goToPartition("Попозиционное сравнение заявок");
        System.out.printf("[ИНФОРМАЦИЯ]: открываем список [Решение комиссии] для указанного Поставщика" +
                " по его порядковому номеру [%s].%n", supplierOrderNumber);
        Dictionary openDecisionListButtons = new Dictionary();
        openDecisionListButtons.add("1", OPEN_DECISION_LIST_PC1_XPATH);
        openDecisionListButtons.add("2", OPEN_DECISION_LIST_PC2_XPATH);
        $(By.xpath(openDecisionListButtons.find(supplierOrderNumber))).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);

        // В списке [Решение комиссии] для указанного Поставщика выбираем [Не соответствует требованиям]
        System.out.println("[ИНФОРМАЦИЯ]: в списке [Решение комиссии] для указанного Поставщика выбираем " +
                "[Не соответствует требованиям].");
        Dictionary decisionListValues = new Dictionary();
        decisionListValues.add("1", DECISION_LIST_PC1_ROW_3_XPATH);
        decisionListValues.add("2", DECISION_LIST_PC2_ROW_3_XPATH);
        $(By.xpath(decisionListValues.find(supplierOrderNumber))).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(1);

        // Открываем список [Выбраны основания] для указанного Поставщика по его порядковому номеру
        this.goToPartition("Попозиционное сравнение заявок");
        System.out.printf("[ИНФОРМАЦИЯ]: открываем список [Выбрать] для указанного Поставщика" +
                " по его порядковому номеру [%s].%n", supplierOrderNumber);
        Dictionary openDecisionReasonListButtons = new Dictionary();
        openDecisionReasonListButtons.add("1", OPEN_DECISION_REASON_LIST_PC1_XPATH);
        openDecisionReasonListButtons.add("2", OPEN_DECISION_REASON_LIST_PC2_XPATH);
        SelenideElement openListButton = $(By.xpath(openDecisionReasonListButtons.find(supplierOrderNumber)));
        this.scrollToCenter(openListButton);
        //--------------------------------------------------------------------------------------------------------------
        openListButton.waitUntil(visible, timeout, polling);
        this.clickInElementJS(openListButton); // список [Выбраны основания] открыт
        TimeUnit.SECONDS.sleep(3);
        //--------------------------------------------------------------------------------------------------------------

        // В списке [Выбраны основания] для указанного Поставщика выбираем первый пункт и закрываем список
        System.out.println(
                "[ИНФОРМАЦИЯ]: в списке [Выбрать] для указанного Поставщика выбираем первый пункт и закрываем список.");
        Dictionary decisionReasonListValues = new Dictionary();
        decisionReasonListValues.add("1", DECISION_REASON_LIST_PC1_ROW_1_XPATH);
        decisionReasonListValues.add("2", DECISION_REASON_LIST_PC2_ROW_1_XPATH);
        $(By.xpath(decisionReasonListValues.find(supplierOrderNumber))).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        //--------------------------------------------------------------------------------------------------------------
        openListButton.waitUntil(visible, timeout, polling);
        this.clickInElementJS(openListButton); // список [Выбраны основания] закрыт
        TimeUnit.SECONDS.sleep(3);
        //--------------------------------------------------------------------------------------------------------------

        // Проверяем поле [Комментарий] для указанного Поставщика по его порядковому номеру
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем поле [Комментарий] для указанного Поставщика по его" +
                " порядковому номеру [%s].%n", supplierOrderNumber);
        Dictionary decisionReasonTexts = new Dictionary();
        decisionReasonTexts.add("1", DECISION_REASON_TEXT_PC1_XPATH);
        decisionReasonTexts.add("2", DECISION_REASON_TEXT_PC2_XPATH);
        SelenideElement textAtea = $(By.xpath(decisionReasonTexts.find(supplierOrderNumber)));
        String val = textAtea.getValue();
        Assert.assertFalse("Поле [Комментарий] содержит пустое значение !", val.isEmpty());
        System.out.printf("[ИНФОРМАЦИЯ]: поле [Комментарий] получило значение [%s].%n", val);
    }

    /**
     * Отклоняет заявку от Поставщика с указанным порядковым номером с указанием оснований отказа.
     * Только для сценария [Аукцион (заявка в двух частях)].
     *
     * @param supplierOrderNumber порядковый номер Поставщика
     */
    public void rejectSupplierRequestForAuctionWithRequestsInTwoParts(String supplierOrderNumber) throws Exception {
        // Открываем список [Решение комиссии] для указанного Поставщика по его порядковому номеру
        System.out.printf("[ИНФОРМАЦИЯ]: открываем список [Решение комиссии] для указанного Поставщика" +
                " по его порядковому номеру [%s].%n", supplierOrderNumber);
        Dictionary openDecisionListButtons = new Dictionary();
        openDecisionListButtons.add("1", OPEN_DECISION_LIST_2P1_XPATH);
        openDecisionListButtons.add("2", OPEN_DECISION_LIST_2P2_XPATH);
        openDecisionListButtons.add("3", OPEN_DECISION_LIST_2P3_XPATH);
        $(By.xpath(openDecisionListButtons.find(supplierOrderNumber))).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);

        // В списке [Решение комиссии] для указанного Поставщика выбираем [Не допущен]
        System.out.println("[ИНФОРМАЦИЯ]: в списке [Решение комиссии] для указанного Поставщика выбираем [Не допущен].");
        Dictionary decisionListValues = new Dictionary();
        decisionListValues.add("1", DECISION_LIST_2P1_ROW_3_XPATH);
        decisionListValues.add("2", DECISION_LIST_2P2_ROW_3_XPATH);
        decisionListValues.add("3", DECISION_LIST_2P3_ROW_3_XPATH);
        $(By.xpath(decisionListValues.find(supplierOrderNumber))).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(1);

        // Открываем список [Выбраны основания] для указанного Поставщика по его порядковому номеру
        System.out.printf("[ИНФОРМАЦИЯ]: открываем список [Выбраны основания] для указанного Поставщика" +
                " по его порядковому номеру [%s].%n", supplierOrderNumber);
        Dictionary openDecisionReasonListButtons = new Dictionary();
        openDecisionReasonListButtons.add("1", OPEN_DECISION_REASON_LIST_2P1_XPATH);
        openDecisionReasonListButtons.add("2", OPEN_DECISION_REASON_LIST_2P2_XPATH);
        openDecisionReasonListButtons.add("3", OPEN_DECISION_REASON_LIST_2P3_XPATH);
        $(By.xpath(openDecisionReasonListButtons.find(supplierOrderNumber))).
                waitUntil(visible, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);

        // В списке [Выбраны основания] для указанного Поставщика выбираем первый пункт и закрываем список
        System.out.println("[ИНФОРМАЦИЯ]: в списке [Выбраны основания] для указанного Поставщика выбираем " +
                "первый пункт и закрываем список.");
        Dictionary decisionReasonListValues = new Dictionary();
        decisionReasonListValues.add("1", DECISION_REASON_LIST_2P1_ROW_1_XPATH);
        decisionReasonListValues.add("2", DECISION_REASON_LIST_2P2_ROW_1_XPATH);
        decisionReasonListValues.add("3", DECISION_REASON_LIST_2P3_ROW_1_XPATH);
        $(By.xpath(decisionReasonListValues.find(supplierOrderNumber))).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(1);
        SelenideElement a2ProtocolDetailsHeader = $(By.xpath(FORM_PROT_FROM_TPL_HDR_XPATH));
        a2ProtocolDetailsHeader.waitUntil(visible, timeout, polling);
        this.clickInElementJS(a2ProtocolDetailsHeader);
        TimeUnit.SECONDS.sleep(3);

        // Проверяем поле [Комментарий] для указанного Поставщика по его порядковому номеру
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем поле [Комментарий] для указанного Поставщика по его" +
                " порядковому номеру [%s].%n", supplierOrderNumber);
        Dictionary decisionReasonTexts = new Dictionary();
        decisionReasonTexts.add("1", DECISION_REASON_TEXT_2P1_XPATH);
        decisionReasonTexts.add("2", DECISION_REASON_TEXT_2P2_XPATH);
        decisionReasonTexts.add("3", DECISION_REASON_TEXT_2P3_XPATH);
        SelenideElement decisionReasonText = $(By.xpath(decisionReasonTexts.find(supplierOrderNumber)));
        decisionReasonText.waitUntil(visible, timeout, polling);
        this.clickInElementJS(decisionReasonText);
        TimeUnit.SECONDS.sleep(3);
        String val = decisionReasonText.getValue();
        Assert.assertFalse("Поле [Комментарий] содержит пустое значение !", val.isEmpty());
        System.out.printf("[ИНФОРМАЦИЯ]: поле [Комментарий] получило значение [%s].%n", val);
    }

    /**
     * Устанавливает переключатель [Заключить договор с единственным участником] в положение [Да].
     */
    public void setSingleParticipantContractSwitchToYesPosition() throws Exception {
        ElementsCollection singleParticipantContractSwitches = $$(By.xpath(SINGLE_PARTICIPANT_CONTRACT_XPATH));
        System.out.println("[ИНФОРМАЦИЯ]: устанавливаем переключатель " +
                "[Заключить договор с единственным участником] в положение [Да].");
        SelenideElement singleParticipantContractSwitch = singleParticipantContractSwitches.get(0);
        this.scrollToCenter(singleParticipantContractSwitch);
        this.clickInElementJS(singleParticipantContractSwitch);
    }

    /**
     * Устанавливает переключатель [Заключить договор с единственным участником] в положение [Провести переторжку].
     */
    public void setSingleParticipantContractSwitchToRetradingPosition() throws Exception {
        ElementsCollection singleParticipantContractSwitches = $$(By.xpath(SINGLE_PARTICIPANT_CONTRACT_XPATH));
        System.out.println("[ИНФОРМАЦИЯ]: устанавливаем переключатель " +
                "[Заключить договор с единственным участником] в положение [Провести переторжку].");
        SelenideElement singleParticipantContractSwitch = singleParticipantContractSwitches.get(2);
        this.scrollToCenter(singleParticipantContractSwitch);
        this.clickInElementJS(singleParticipantContractSwitch);
    }

    /**
     * Устанавливает флажок [Закупка признана несостоявшейся].
     */
    public void turnOnMissedContestCheckBox() throws Exception {
        SelenideElement missedContestCheckbox = $(By.id(MISSED_CONTEST_CHECKBOX_ID));
        System.out.println("[ИНФОРМАЦИЯ]: устанавливаем флажок [Закупка признана несостоявшейся].");
        missedContestCheckbox.waitUntil(clickable, timeout, polling);
        this.scrollToCenterAndclickInElementJS(missedContestCheckbox);
        missedContestCheckbox.shouldBe(selected);
    }

    /**
     * Снимает флажок [Закупка признана несостоявшейся].
     */
    public void turnOffMissedContestCheckBox() throws Exception {
        SelenideElement missedContestCheckbox = $(By.id(MISSED_CONTEST_CHECKBOX_ID));
        System.out.println("[ИНФОРМАЦИЯ]: снимаем флажок [Закупка признана несостоявшейся].");
        missedContestCheckbox.waitUntil(clickable, timeout, polling);
        this.scrollToCenterAndclickInElementJS(missedContestCheckbox);
        missedContestCheckbox.shouldNotBe(selected);
    }

    /**
     * Делает щелчок мышью по основному заголовку страницы [Протокол рассмотрения первых частей заявок аукциона].
     */
    public void clickOnMainPageHeader() throws Exception {
        $(By.xpath(CONSIDERATION_PROTOCOL_PAGE_HEADER_XPATH)).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * Перемещает страницу к разделу [Даты переторжки].
     */
    public void moveToRetraidingDatesHeader() throws Exception {
        SelenideElement retraidingDatesHeader = $(By.xpath(RETRAIDING_DATES_HEADER_XPATH));
        retraidingDatesHeader.waitUntil(visible, timeout, polling);
        this.scrollToCenterAndclickInElementJS(retraidingDatesHeader);
    }

    /**
     * Перемещает страницу к разделу [Сведения протокола рассмотрения заявок].
     */
    public void moveToConsiderationRequestInfoHeader() throws Exception {
        $(By.xpath(CONS_REQ_PROT_INFO_HEADER_XPATH)).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * Проверяет дату начала переторжки.
     *
     * @param expectedValue ожидаемая дата
     */
    public void checkRetraidingStartDate(String expectedValue) {
        String actualValue = $(By.id(RETRAIDING_START_DATE_ID)).waitUntil(visible, timeout, polling).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем дату начала переторжки: ожидаемая - [%s], реальная - [%s].%n",
                expectedValue, actualValue);
        Assert.assertTrue(String.format("[ОШИБКА]: некорректная дата начала переторжки: [%s]", actualValue),
                actualValue.contains(expectedValue));
    }

    /**
     * Проверяет дату окончания переторжки.
     *
     * @param expectedValue ожидаемая дата
     */
    public void checkRetraidingEndDate(String expectedValue) {
        String actualValue = $(By.id(RETRAIDING_END_DATE_ID)).waitUntil(visible, timeout, polling).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем дату окончания переторжки: ожидаемая - [%s], реальная - [%s].%n",
                expectedValue, actualValue);
        Assert.assertTrue(String.format("[ОШИБКА]: некорректная дата окончания переторжки: [%s]", actualValue),
                actualValue.contains(expectedValue));
    }

    /**
     * Перемещает страницу к разделу [Формирование протокола из шаблона].
     */
    public void moveToFormingProtocolFromTemplateHeader() throws Exception {
        this.moveToFormingProtocolFromTemplateHeaderByIndex("1");
    }

    /**
     * Перемещает страницу к разделу [Формирование протокола из шаблона] для лота с указанным порядковым номером.
     *
     * @param lotNumber порядковый номер лота
     */
    public void moveToFormingProtocolFromTemplateHeaderByIndex(String lotNumber) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: перемещаем страницу к лоту с порядковым номером [%s].%n", lotNumber);
        int partition = Integer.parseInt(lotNumber) - 1;
        ElementsCollection partitions = $$(By.xpath(FORM_PROT_FROM_TPL_HDR_XPATH));
        partitions.get(partition).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * Проверяет возможность скачать прикрепленные к протоколу заявки в разделе
     * [Сведения протокола рассмотрения заявок] -> кнопка [Скачать заявки] для лота с указанным порядковым номером.
     *
     * @param lotNumber порядковый номер лота
     */
    public void checkPossibilityToDownloadRequestsFromProtocolConsiderationRequest(String lotNumber) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: скачиваем заявки для лота с порядковым номером [%s].%n", lotNumber);
        int link = Integer.parseInt(lotNumber) - 1;
        ElementsCollection links = $$(By.id(DOWNLOAD_REQUESTS_BUTTON_ID));
        this.checkPossibilityToDownloadFileWithGeneratedName(links.get(link), "Скачать заявки");
    }

    /**
     * Проверяет возможность скачать прикрепленные к протоколу заявки в разделе
     * [Сведения протокола рассмотрения первых частей заявок по лоту 1], кнопка [Скачать заявки].
     */
    public void checkPossibilityToDownloadRequestsFromOpenAccessProtocolPage() throws Exception {
        this.checkPossibilityToDownloadFileWithGeneratedName(By.id(DOWNLOAD_REQUESTS_BUTTON_ID),
                "Скачать заявки");
    }

    /**
     * Устанавливает флажок [Провести переторжку].
     */
    public void setRetraidingEnabledCheckbox() {
        SelenideElement checkBox = $(By.xpath(RETRAIDING_ENABLED_CHECKBOX_XPATH));
        this.scrollToCenter(checkBox);
        checkBox.waitUntil(clickable, timeout, polling).click();
    }

    /**
     * Проверяет отсутствие столбца [Ценовое предложение] в разделе [Сведения протокола рассмотрения (и оценки) заявок].
     *
     * @param expectedColumns ожидаемое количество столбцов в таблице раздела
     *                        [Сведения протокола рассмотрения (и оценки) заявок]
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
     * Проверяет наличие столбца [Ценовое предложение] в разделе [Сведения протокола рассмотрения (и оценки) заявок].
     *
     * @param expectedColumns ожидаемое количество столбцов в таблице раздела
     *                        [Сведения протокола рассмотрения (и оценки) заявок]
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
     * Проверяет отсутствие таблицы [Ценовое предложение] в печатных формах заявок на участие от Поставщиков.
     */
    public void checkThatThePriceOfferTableHiddenInApplicationPrintForms() throws Exception {

        // Операции в окне [Протокол рассмотрения (и оценки) заявок ...] (основное окно приложения)
        String mainWindow = getWebDriver().getWindowHandle();
        ElementsCollection links = $$(By.xpath(TABLE_APPLICATION_LINKS_XPATH));
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

            // Возвращаемся в окно [Протокол рассмотрения (и оценки) заявок ...] (основное окно приложения)
            getWebDriver().switchTo().window(mainWindow);
            System.out.println("[ИНФОРМАЦИЯ]: произошло переключение на основное окно приложения.");
            this.checkPageUrl("Один из протоколов");
        }
    }

    /**
     * Проверяет наличие таблицы [Ценовое предложение] в печатных формах заявок на участие от Поставщиков.
     */
    public void checkThatThePriceOfferTableVisibleInApplicationPrintForms() throws Exception {
        // Операции в окне [Протокол открытия доступа ...] (основное окно приложения)
        String mainWindow = getWebDriver().getWindowHandle();
        ElementsCollection links = $$(By.xpath(TABLE_APPLICATION_LINKS_XPATH));
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
     * Делает щелчок мышью по ссылке [Отправить] в указанной строке столбца [Запрос на разъяснение заявки].
     *
     * @param rowNumber порядковый номер строки в таблице
     */
    public void clickOnRequestForExplanationOfApplicationLinkByRowNumber(int rowNumber) {
        ElementsCollection rows = $$(By.xpath(TABLE_REQUEST_FOR_EXPLANATION_LINKS_XPATH));
        int actualRows = rows.size();
        int index = rowNumber - 1;
        String table = "Сведения протокола рассмотрения (и оценки) заявок";

        System.out.printf(
                "[ИНФОРМАЦИЯ]: в таблице [%s] [%d] строк, номер строки для перехода по ссылке [Отправить]: [%d].%n",
                table, actualRows, rowNumber);
        Assert.assertTrue("[ОШИБКА]: передан некорректный порядковый номер строки",
                (index >= 0) && (rowNumber <= actualRows));
        rows.get(index).waitUntil(clickable, timeout, polling).click();
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
     * Открывает страницы просмотра печатной формы заявкм на участие в конкурсе.
     *
     * @param printFormNumber - номер заявки печатной формы в таблице [Сведения протокола открытия доступа]
     */
    public void openApplicationPrintForms(int printFormNumber) throws Exception {
        ElementsCollection links = $$(By.xpath(TABLE_APPLICATION_LINKS_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] ссылок на печатные формы заявок.%n", links.size());
        this.clickInElementJS(links.get(printFormNumber - 1));
        System.out.printf("[ИНФОРМАЦИЯ]: выбрана [%d] ссылка на печатную форму заявки.%n", printFormNumber);
        this.waitForPageLoad();
        for (String winHandle : getWebDriver().getWindowHandles()) getWebDriver().switchTo().window(winHandle);
        this.checkPageUrl("Печатная форма заявки на участие от Поставщика (ПРОЗ)");
    }

    /**
     * Открывает страницы просмотра печатной формы сведения об участнике закупки.
     *
     * @param printFormNumber - номер сведения об участнике закупки в таблице [Сведения протокола открытия доступа]
     */
    public void openPurchaserApplicationPrintForms(int printFormNumber) throws Exception {
        ElementsCollection links = $$(By.xpath(PURCHASE_INFORM_LINKS_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] ссылок на печатные формы сведения об участнике закупки.%n",
                links.size());
        this.clickInElementJS(links.get(printFormNumber - 1));
        System.out.printf("[ИНФОРМАЦИЯ]: выбрана [%d] ссылка на печатную форму.%n", printFormNumber);
        this.waitForPageLoad();
        for (String winHandle : getWebDriver().getWindowHandles()) getWebDriver().switchTo().window(winHandle);
        this.checkPageUrl("Печатная форма сведения об участнике закупки (ПРОЗ)");
    }

    /**
     * Проверяет наличие флажков в столбце [Субъект МСП] в разделе [Сведения протокола рассмотрения и оценки заявок].
     *
     * @param checkboxesNumber - ожидаемое колическтво флажков
     */
    public void checkCheckboxesNumberInSMBEntityColumn(int checkboxesNumber) {
        ElementsCollection links = $$(By.xpath(CHECKBOXES_IN_SMB_ENTITY_COLUMN_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] флажков в столбце [Субъект МСП].%n",
                links.size());
        Assert.assertEquals("Количество флажков не соответствует ожидаемому", checkboxesNumber, links.size());
    }

    /**
     * Открывает свернутый раздел [Оценка заявок].
     */
    public void openApplicationRatingBlock() throws Exception {
        SelenideElement block = $(this.getBy(APPLICATION_RATING_XPATH));
        this.scrollToCenter(block);
        block.shouldBe(visible).click();
        System.out.println("[ИНФОРМАЦИЯ]: произведено открытие раздела [Оценка заявок].");
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * Проверяет количество строк в таблице участников в разделе [Оценка заявок].
     *
     * @param rows требуемое количество строк в таблице участников в разделе [Оценка заявок].
     */
    public void checkNumberOfRowsFromRequestListTable(int rows) throws Exception {
        TimeUnit.SECONDS.sleep(5);
        int rowsActual = $$(this.getBy(ROWS_IN_APPLICATION_RATING_TABLE_XPATH)).size();
        Assert.assertEquals("[ОШИБКА]: неверное количество строк в таблице участников в разделе [Оценка заявок]",
                rows, rowsActual);
        System.out.printf("[ИНФОРМАЦИЯ]: произведена проверка количества строк [%s] в таблице участников в разделе " +
                "[Оценка заявок].%n", rows);
    }
}
