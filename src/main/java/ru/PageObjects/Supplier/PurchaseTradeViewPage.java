package ru.PageObjects.Supplier;

import Helpers.Dictionary;
import Helpers.SoftAssert;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonDialogs.AttentionDialog;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывающий страницу [Закупка № XXXX (Поставщик)]
 * ( .kontur.ru/supplier/auction/Trade/View.aspx? ).
 * Created by Vladimir V. Klochkov on 08.04.2016.
 * Updated by Vladimir V. Klochkov on 11.03.2021.
 */
public class PurchaseTradeViewPage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы [Закупка № XXXX]
    private static final String PAGE_HEADER_XPATH = "//div[@id='workContainer']//h2[@class = 'auction_title2']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Связать с закупкой в ЕИС] в заголовке формы
    private static final String LINK_TO_EIS_PURCHASE_BUTTON_ID = "BaseMainContent_MainContent_btnBindPurchase";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Запросить разъяснение] в заголовке формы
    private static final String CREATE_CLARIFICATION_REQUEST_TOP_BUTTON_ID =
            "BaseMainContent_MainContent_btnCreateClarificationRequestTop";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Общие сведения о закупке]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Общие сведения о закупке]
    private static final String COMMON_INFO_ID = "BaseMainContent_MainContent_hCommonInfo";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер редакции]
    private static final String MODIFICATION_NUMBER_ID = "BaseMainContent_MainContent_fvModificationNumber_lblValue";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки]
    private static final String TRADE_NUMBER_ID = "BaseMainContent_MainContent_fvTradeNumber_lblValue";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование закупки]
    private static final String TRADE_NAME_ID = "BaseMainContent_MainContent_fvName_lblValue";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Лот № X]

    //==================================================================================================================
    // Кнопка [Подать заявку] в разделе формы [Лот №1] для закупки по 223-ФЗ
    private static final String SUBMIT_REQUEST_LOT1_223_BUTTON_ID =
            "BaseMainContent_MainContent_ucTradeLotViewList_rptLots_ucTradeLotView_0_btnAddApplication_0";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Подать заявку] в разделе формы [Лот №2] для закупки по 223-ФЗ
    private static final String SUBMIT_REQUEST_LOT2_223_BUTTON_ID =
            "BaseMainContent_MainContent_ucTradeLotViewList_rptLots_ucTradeLotView_1_btnAddApplication_1";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Подать заявку] в разделе формы [Лот №3] для закупки по 223-ФЗ
    private static final String SUBMIT_REQUEST_LOT3_223_BUTTON_ID =
            "BaseMainContent_MainContent_ucTradeLotViewList_rptLots_ucTradeLotView_2_btnAddApplication_2";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопки [Подать заявку] в разделе формы [Лот №X] для закупки по 223-ФЗ
    private static final String SUBMIT_REQUEST_LOTS_223_BUTTONS_XPATH = "//a[contains(@id, '_btnAddApplication_')]";
    //==================================================================================================================
    // Кнопка [Подать заявку] в разделе формы [Лот №1] для закупки по 44-ФЗ
    private static final String SUBMIT_REQUEST_LOT1_44_BUTTON_XPATH =
            "//*[@id='BaseMainContent_MainContent_btnAddApplication' or " +
                    "(@class='btn btn-grey' and contains(., 'Подать заявку'))]";
    //==================================================================================================================
    // Кнопка [Запросить разъяснение] в разделе формы [Лот №1] для закупки по 223-ФЗ
    private static final String CREATE_RESULT_CLARIFICATION_REQUEST_LOT1_223_BUTTON_XPATH =
            "//a[contains(@id, '_btnCreateResultClarificationRequest_0')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Запросить разъяснение] в разделе формы [Лот №2] для закупки по 223-ФЗ
    private static final String CREATE_RESULT_CLARIFICATION_REQUEST_LOT2_223_BUTTON_XPATH =
            "//a[contains(@id, '_btnCreateResultClarificationRequest_1')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Запросить разъяснение] в разделе формы [Лот №3] для закупки по 223-ФЗ
    private static final String CREATE_RESULT_CLARIFICATION_REQUEST_LOT3_223_BUTTON_XPATH =
            "//a[contains(@id, '_btnCreateResultClarificationRequest_2')]";
    //==================================================================================================================

    // endregion

    // region Раздел [Запросы на разъяснение документации]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Запросы на разъяснение документации]
    private static final String CLARIFICATION_REQUEST_PARTITION_HEADER_XPATH = "//*[@id='explain']/h2";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица [Запросы на разъяснение документации] - столбец [Текст разъяснения]
    private static final String RESPONSE_TEXT_COLUMN_XPATH =
            "//table[@id='BaseMainContent_MainContent_crgRequests_jqgRequests']//td[4]/a";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary blockNames = new Dictionary();           // основные разделы страницы
    private final Dictionary fieldNames = new Dictionary();           // все поля на странице
    private final Dictionary submitRequestButtons = new Dictionary(); // все кнопки [Подать заявку] в разделе формы [Лот №X]
    private final Dictionary resClarificatButtons = new Dictionary(); // все кнопки [Запросить разъяснение] в разделе формы [Лот №X]
    private final Dictionary clarificationRequests = new Dictionary();// колонки таблицы [Запросы на разъяснение документации]

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public PurchaseTradeViewPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Запрос на разъяснение положений документации о закупке", PAGE_HEADER_XPATH);
        blockNames.add("Общие сведения о закупке", COMMON_INFO_ID);
        blockNames.add("Запросы на разъяснение документации", CLARIFICATION_REQUEST_PARTITION_HEADER_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Номер редакции", MODIFICATION_NUMBER_ID);
        fieldNames.add("Номер закупки", TRADE_NUMBER_ID);
        fieldNames.add("Наименование закупки", TRADE_NAME_ID);
        //--------------------------------------------------------------------------------------------------------------
        submitRequestButtons.add("1", SUBMIT_REQUEST_LOT1_223_BUTTON_ID);
        submitRequestButtons.add("2", SUBMIT_REQUEST_LOT2_223_BUTTON_ID);
        submitRequestButtons.add("3", SUBMIT_REQUEST_LOT3_223_BUTTON_ID);
        submitRequestButtons.add("1-44-ФЗ", SUBMIT_REQUEST_LOT1_44_BUTTON_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        resClarificatButtons.add("1", CREATE_RESULT_CLARIFICATION_REQUEST_LOT1_223_BUTTON_XPATH);
        resClarificatButtons.add("2", CREATE_RESULT_CLARIFICATION_REQUEST_LOT2_223_BUTTON_XPATH);
        resClarificatButtons.add("3", CREATE_RESULT_CLARIFICATION_REQUEST_LOT3_223_BUTTON_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        clarificationRequests.add("Текст разъяснения", RESPONSE_TEXT_COLUMN_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Раздел [Лот № X]

    /**
     * Делает клик по кнопке [Подать заявку] в разделе формы [Лот №X].
     *
     * @param lotNumber порядковый номер лота, который содержит данную кнопку
     */
    public void clickOnAddApplicationButtonByLotNumber(String lotNumber) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: нажимаем на кнопку [Подать заявку] в разделе формы [Лот №%s].%n", lotNumber);
        SelenideElement button = $(this.getBy(submitRequestButtons.find(lotNumber)));
        Assert.assertTrue(
                "[ОШИБКА]: кнопка [Подать заявку] отсутствует, возможно время подачи заявок уже истекло",
                button.exists());
        $(button).waitUntil(clickable, timeout, polling).click();
        //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        AttentionDialog attentionDialog = new AttentionDialog(driver);
        if (attentionDialog.dialogDisplayed())
            attentionDialog.
                    check1stLineInWindowText("Текст окна 1 строка",
                            "Для подачи заявки на данную процедуру необходимо купить подписку").
                    clickOnButton("Продолжить формирование");
        //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Делает клик по кнопке [Запросить разъяснение] в разделе формы [Лот №X].
     *
     * @param lotNumber порядковый номер лота, который содержит данную кнопку
     */
    public void clickOnCreateResultClarificationRequestButtonByLotNumber(String lotNumber) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: нажимаем на кнопку [Запросить разъяснение] в разделе формы [Лот №%s].%n",
                lotNumber);
        SelenideElement button = $(this.getBy(resClarificatButtons.find(lotNumber)));
        Assert.assertTrue("[ОШИБКА]: кнопка [Запросить разъяснение] отсутствует", button.exists());
        $(button).waitUntil(clickable, timeout, polling).click();
        this.waitForPageLoad(5);
    }

    /**
     * Проверяет отображение кнопки [Подать заявку] в разделе формы [Лот №X].
     */
    public void checkForForAnyAddApplicationButton() {
        System.out.println("[ИНФОРМАЦИЯ]: проверяем отображение кнопки [Подать заявку].");
        ElementsCollection buttons =
                $$(this.getBy(SUBMIT_REQUEST_LOTS_223_BUTTONS_XPATH)).filterBy(visible);
        SoftAssert.assertEquals(
                "[ОШИБКА]: в открытом лоте нет кнопки [Подать заявку]", 1, buttons.size());
    }

    /**
     * Проверяет осутствие кнопки [Подать заявку] в разделе формы [Лот №X]/[Предмет предварительного отбора №X]
     */
    public void checkForTheAbsenceAddApplicationButton() {
        System.out.println("[ИНФОРМАЦИЯ]: проверят отсутствие кнопки [Подать заявку].");
        ElementsCollection buttons =
                $$(this.getBy(SUBMIT_REQUEST_LOTS_223_BUTTONS_XPATH)).filterBy(visible);
        SoftAssert.assertEquals(
                "[ОШИБКА]: в открытом лоте есть кнопка [Подать заявку]", 0, buttons.size());
    }

    /**
     * Проверяет наличие кнопки [Связать с закупкой в ЕИС] на карточке закупки.
     */
    public void checkThatLinkToEisPurchaseButtonExist() {
        System.out.println("[ИНФОРМАЦИЯ]: проверяем наличие кнопки [Связать с закупкой в ЕИС] на карточке закупки.");
        $(By.id(LINK_TO_EIS_PURCHASE_BUTTON_ID)).waitUntil(clickable, timeout, polling).shouldBe(clickable);
    }

    /**
     * Нажимает на кнопку [Связать с закупкой в ЕИС] на карточке закупки.
     */
    public void clickOnLinkToEisPurchaseButton() {
        this.logButtonNameToPress("Связать с закупкой в ЕИС");
        $(By.id(LINK_TO_EIS_PURCHASE_BUTTON_ID)).waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName("Связать с закупкой в ЕИС");
    }

    /**
     * Нажимает на кнопку [Запросить разъяснение] на карточке закупки.
     */
    public void clickOnCreateClarificationRequestTopButton() {
        this.logButtonNameToPress("Запросить разъяснение");
        $(By.id(CREATE_CLARIFICATION_REQUEST_TOP_BUTTON_ID)).waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName("Запросить разъяснение");
    }

    // endregion

    // region Раздел [Запросы на разъяснение документации]

    /**
     * Переходит по ссылке в таблице 'Запросы на разъяснение документации' для столбца с указанным именем и
     * номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param lineNumber порядковый номер строки в таблице
     */
    public void clickOnLinkInLineByNumberFromClarificationRequestsTable(String columnName, int lineNumber)
            throws Exception {
        int line = lineNumber - 1;
        ElementsCollection linesWithLinks = $$(this.getBy(clarificationRequests.find(columnName)));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] строк с со ссылками в столбце [%s].%n",
                linesWithLinks.size(), columnName);
        Assert.assertTrue(String.format("[ОШИБКА]: передан некорректный номер строки: [%d]", lineNumber),
                line >= 0 && line < linesWithLinks.size());
        this.scrollToCenter(linesWithLinks.get(line));
        linesWithLinks.get(line).waitUntil(visible, timeout, polling).shouldBe(clickable);
        this.clickInElementJS(linesWithLinks.get(line));
        System.out.printf("[ИНФОРМАЦИЯ]: произведен переход по ссылке в строке [%d] столбца [%s].%n",
                linesWithLinks.size(), columnName);
    }

    // endregion

    // region Общие методы для работы с элементами этой страницы

    /**
     * Переходит к блоку полей на форме.
     *
     * @param blockName имя блока
     * @return страницу [Закупка № XXXX (Поставщик)]
     */
    public void gotoBlock(String blockName) throws Exception {
        SelenideElement block = $(this.getBy(blockNames.find(blockName)));
        this.scrollToCenter(block);
        block.shouldBe(visible);
        System.out.printf("[ИНФОРМАЦИЯ]: Произведен переход к блоку полей: [%s].%n", blockName);
        TimeUnit.SECONDS.sleep(3);
    }

    // endregion
}
