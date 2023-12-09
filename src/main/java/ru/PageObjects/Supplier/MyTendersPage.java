package ru.PageObjects.Supplier;

import Helpers.Dictionary;
import Helpers.SoftAssert;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Класс описывающий страницу [ Мои Торги/Переторжки ]
 * ( .kontur.ru/supplier/auction/Private/MyTenders.aspx ).
 * Created by Vladimir V. Klochkov on 06.02.2020.
 * Updated by Vladimir V. Klochkov on 11.03.2021.
 */
public class MyTendersPage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы [Мои Торги/Переторжки]
    private static final String PAGE_HEADER_ID = "BaseMainContent_MainContent_lblTitle";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Блок критериев поиска

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки]
    private static final String TRADE_NUMBER_ID = "BaseMainContent_MainContent_txtNumber_txtText";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование закупки]
    private static final String TRADE_NAME_ID = "BaseMainContent_MainContent_txtTradeName_txtText";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование предмета договора]
    private static final String LOT_NAME_ID = "BaseMainContent_MainContent_txtLotName_txtText";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Показывать только идущие торги]
    private static final String SHOW_ONLY_GOING_TRADES_ID = "BaseMainContent_MainContent_cbxShowOnlyGoingTrades";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Показывать только торги, ожидаемые сегодня]
    private static final String SHOW_ONLY_TODAY_TRADES_ID = "BaseMainContent_MainContent_cbxShowOnlyTodayTrades";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата начала торгов/переторжки]
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Субполе [От]
    private static final String AUCTION_DATE_FROM_ID = "BaseMainContent_MainContent_txtAuctionDate_txtDateFrom";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Субполе [До]
    private static final String AUCTION_DATE_TO_ID = "BaseMainContent_MainContent_txtAuctionDate_txtDateTo";
    //------------------------------------------------------------------------------------------------------------------
    // Блок радиокнопок [Показать]
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Радиокнопка [торги]
    private static final String SHOW_AUCTIONS_ID = "BaseMainContent_MainContent_rbShowAuctions";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Радиокнопка [переторжки]
    private static final String SHOW_RETRADINGS_ID = "BaseMainContent_MainContent_rbShowRetradings";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Поиск]
    private static final String SEARCH_BUTTON_ID = "BaseMainContent_MainContent_btnSearch";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Очистить]
    private static final String CLEAR_BUTTON_ID = "BaseMainContent_MainContent_btnClear";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Область закладок над таблицей с результатами поиска

    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Все]
    private static final String ALL_TAB_XPATH = "//*[@id='gridTabsId']//a[text() = 'Все']";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Формируется]
    private static final String FORMING_TAB_XPATH = "//*[@id='gridTabsId']//a[text() = 'Формируется']";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Ожидает начала торгов]
    private static final String WAITING_FOR_TRADES_TAB_XPATH =
            "//*[@id='gridTabsId']//a[text() = 'Ожидает начала торгов']";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Идут торги]
    private static final String TRADES_IN_PROGRESS_TAB_XPATH = "//*[@id='gridTabsId']//a[text() = 'Идут торги']";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Идут торги (вторая фаза)]
    private static final String TRADES_2ND_PHASE_TAB_XPATH =
            "//*[@id='gridTabsId']//a[text() = 'Идут торги (вторая фаза)']";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Закрыт]
    private static final String CLOSED_TAB_XPATH = "//*[@id='gridTabsId']//a[text() = 'Закрыт']";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Отменён заказчиком]
    private static final String REJECTED_TAB_XPATH = "//*[@id='gridTabsId']//a[text() = 'Отменён заказчиком']";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Не состоялся]
    private static final String NOT_TAKE_PLACE_TAB_XPATH = "//*[@id='gridTabsId']//a[text() = 'Не состоялся']";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Приостановлены]
    private static final String PAUSED_TAB_XPATH = "//*[@id='gridTabsId']//a[text() = 'Приостановлены']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Результаты поиска]

    //------------------------------------------------------------------------------------------------------------------
    // Строки в колонке [Номер закупки] (для всех строк таблицы)
    private static final String TRADE_NUMBERS_COLUMN_XPATH =
            "//td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_Number']/a";
    //------------------------------------------------------------------------------------------------------------------
    // Строки в колонке [Наименование предмета договора] (для всех строк таблицы)
    private static final String SUBJECT_NAMES_COLUMN_XPATH =
            "//td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_SubjectName']/a";
    //------------------------------------------------------------------------------------------------------------------
    // Строки в колонке [Начальная (максимальная) цена договора] (для всех строк таблицы)
    private static final String LOT_PRICES_COLUMN_XPATH =
            "//td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_LotPrice']";
    //------------------------------------------------------------------------------------------------------------------
    // Строки в колонке [Организация, осуществляющая закупку] (для всех строк таблицы)
    private static final String ORGANIZER_COLUMN_XPATH =
            "//td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_Organizer']/a";
    //------------------------------------------------------------------------------------------------------------------
    // Строки в колонке [Дата и время публикации] (для всех строк таблицы)
    private static final String PUBLISH_DATE_COLUMN_XPATH =
            "//td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_PublishDate']";
    //------------------------------------------------------------------------------------------------------------------
    // Строки в колонке [Дата и время начала торгов] (для всех строк таблицы)
    private static final String AUCTION_START_DATE_COLUMN_XPATH =
            "//td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_AuctionStartDate']";
    //------------------------------------------------------------------------------------------------------------------
    // Строки в колонке [Статус] (для всех строк таблицы)
    private static final String AUCTION_STATE_COLUMN_XPATH =
            "//td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_AuctionState']";
    //------------------------------------------------------------------------------------------------------------------
    // Строки в колонке [До начала торгов осталось] (для всех строк таблицы)
    private static final String AUCTION_REMAIN_COLUMN_XPATH =
            "//td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_AuctionRemain']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary titleNames = new Dictionary();       // все заголовки на странице
    private final Dictionary fieldNames = new Dictionary();       // все поля на странице
    private final Dictionary checkBoxNames = new Dictionary();    // все флажки на странице
    private final Dictionary radioButtonNames = new Dictionary(); // все переключатели на странице
    private final Dictionary buttonNames = new Dictionary();      // все кнопки на странице
    private final Dictionary tabNames = new Dictionary();         // закладки над таблицей с результатами поиска
    private final Dictionary resultsColumns = new Dictionary();   // все колонки таблицы с результатами поиска

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public MyTendersPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        titleNames.add("Заголовок страницы", PAGE_HEADER_ID);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Номер закупки", TRADE_NUMBER_ID);
        fieldNames.add("Наименование закупки", TRADE_NAME_ID);
        fieldNames.add("Наименование предмета договора", LOT_NAME_ID);
        fieldNames.add("От", AUCTION_DATE_FROM_ID);
        fieldNames.add("До", AUCTION_DATE_TO_ID);
        //--------------------------------------------------------------------------------------------------------------
        checkBoxNames.add("Показывать только идущие торги", SHOW_ONLY_GOING_TRADES_ID);
        checkBoxNames.add("Показывать только торги, ожидаемые сегодня", SHOW_ONLY_TODAY_TRADES_ID);
        //--------------------------------------------------------------------------------------------------------------
        radioButtonNames.add("торги", SHOW_AUCTIONS_ID);
        radioButtonNames.add("переторжки", SHOW_RETRADINGS_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Поиск", SEARCH_BUTTON_ID);
        buttonNames.add("Очистить", CLEAR_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
        tabNames.add("Все", ALL_TAB_XPATH);
        tabNames.add("Формируется", FORMING_TAB_XPATH);
        tabNames.add("Ожидает начала торгов", WAITING_FOR_TRADES_TAB_XPATH);
        tabNames.add("Идут торги", TRADES_IN_PROGRESS_TAB_XPATH);
        tabNames.add("Идут торги (вторая фаза)", TRADES_2ND_PHASE_TAB_XPATH);
        tabNames.add("Закрыт", CLOSED_TAB_XPATH);
        tabNames.add("Отменён заказчиком", REJECTED_TAB_XPATH);
        tabNames.add("Не состоялся", NOT_TAKE_PLACE_TAB_XPATH);
        tabNames.add("Приостановлены", PAUSED_TAB_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        resultsColumns.add("Номер закупки", TRADE_NUMBERS_COLUMN_XPATH);
        resultsColumns.add("Наименование предмета договора", SUBJECT_NAMES_COLUMN_XPATH);
        resultsColumns.add("Начальная (максимальная) цена договора", LOT_PRICES_COLUMN_XPATH);
        resultsColumns.add("Организация, осуществляющая закупку", ORGANIZER_COLUMN_XPATH);
        resultsColumns.add("Дата и время публикации", PUBLISH_DATE_COLUMN_XPATH);
        resultsColumns.add("Дата и время начала торгов", AUCTION_START_DATE_COLUMN_XPATH);
        resultsColumns.add("Статус", AUCTION_STATE_COLUMN_XPATH);
        resultsColumns.add("До начала торгов осталось", AUCTION_REMAIN_COLUMN_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     *               Методы страницы
     ******************************************************************************************************************/
    // region Раздел [Результаты поиска]

    /**
     * Проверяет количество лотов в найденной закупке.
     *
     * @param expectedLots ожидаемое количество лотов в найденной закупке
     */
    public void checkNumberOfLotsInPurchase(int expectedLots) {
        ElementsCollection tradeNumbers = $$(this.getBy(resultsColumns.find("Номер закупки")));
        int actualLots = tradeNumbers.size();
        System.out.printf("[ИНФОРМАЦИЯ]: ожидаемое количество лотов: [%d], реальное количество лотов: [%d].%n",
                expectedLots, actualLots);
        Assert.assertEquals("[ОШИБКА]: неверное количество лотов в найденной закупке", expectedLots, actualLots);
    }

    /**
     * Нажимает на ссылку с номером закупки в указанной строке результатов поиска.
     *
     * @param rowNumber номер строки в результатах поиска ( от 1 до N ).
     */
    public void clickOnTradeNumberLinkInRowByNumber(int rowNumber) throws Exception {
        ElementsCollection tradeNumbers = $$(this.getBy(resultsColumns.find("Номер закупки")));

        Assert.assertNotEquals("[ОШИБКА]: результаты поиска закупки пустые", 0, tradeNumbers.size());
        Assert.assertTrue("[ОШИБКА]: передан неверный номер строки",
                (rowNumber > 0) && (rowNumber <= tradeNumbers.size()));

        SelenideElement tradeNumber = tradeNumbers.get(rowNumber - 1);
        tradeNumber.waitUntil(clickable, timeout, polling);

        Assert.assertTrue("[ОШИБКА]: номер закупки в указанной строке не совпадает с номером закупки теста",
                tradeNumber.getText().contains(config.getParameter("PurchaseNumber")));
        System.out.printf("[ИНФОРМАЦИЯ]: переходим по ссылке с номером закупки: [%s].%n", tradeNumber.getText());

        this.scrollToCenterAndclickInElementJS(tradeNumber);
        this.waitForPageLoad();
    }

    // endregion

    // region Общие методы для работы с элементами этой страницы

    /**
     * Устанавливает значение полей с предварительной их очисткой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     */
    public void setFieldClearClickAndSendKeys(String fieldName, String value) throws Exception {
        this.waitClearClickAndSendKeysById(fieldNames.find(fieldName), value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
    }

    /**
     * Выбирает в переключателе опцию с указанным именем.
     *
     * @param radioButtonPosition имя опции
     */
    public void selectRadioButton(String radioButtonPosition) throws Exception {
        $(this.getBy(radioButtonNames.find(radioButtonPosition))).waitUntil(exist, timeout, polling).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено переключение в положение [%s].%n", radioButtonPosition);
        this.waitForPageLoad();
        this.verifyRadioButtonSelected(radioButtonPosition);
    }

    /**
     * Проверяет, что в переключателе выбрана опция с указанным именем.
     *
     * @param radioButtonName имя опции
     */
    public void verifyRadioButtonSelected(String radioButtonName) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем положение переключателя [%s] - ", radioButtonName);
        $(this.getBy(radioButtonNames.find(radioButtonName))).waitUntil(exist, timeout, polling).shouldBe(selected);
        System.out.println("Ok, выбран.");
    }

    /**
     * Делает щелчок мышью по указанной кнопке.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.clickInElementJS(this.getBy(buttonNames.find(buttonName)));
        this.logPressedButtonName(buttonName);
        this.waitForPageLoad();
    }

    // endregion
}
