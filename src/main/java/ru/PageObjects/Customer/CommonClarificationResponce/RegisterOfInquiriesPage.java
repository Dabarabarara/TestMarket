package ru.PageObjects.Customer.CommonClarificationResponce;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывающий страницу [Реестр запросов на разъяснение документации/результатов]
 * ( .kontur.ru/customer/lk/RegisterOfInquiries/Index/Documentation )
 * ( .kontur.ru/customer/lk/RegisterOfInquiries/Index/Result ).
 * Created by Vladimir V. Klochkov on 29.11.2019.
 * Updated by Vladimir V. Klochkov on 11.03.2021.
 */
public class RegisterOfInquiriesPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы [Реестр запросов на разъяснение документации/результатов]
    private static final String PAGE_HEADER_XPATH = "//*[@id='layoutwrapper']/div/h2";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Фильтр запросов]

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Фильтр]
    private static final String HIDDEN_FILTER_BUTTON_ID = "hiddenFilterButton";
    //------------------------------------------------------------------------------------------------------------------
    // Вкладка [Выбрать]
    private static final String SELECT_TAB_XPATH = "//a[@role='tab' and contains(., 'Выбрать')]";
    //------------------------------------------------------------------------------------------------------------------
    // Вкладка [Требует разъяснения]
    private static final String CLARIFICATION_REQUIRED_TAB_XPATH =
            "//a[@role='tab' and contains(., 'Требует разъяснения')]";
    //------------------------------------------------------------------------------------------------------------------
    // Вкладка [Отправлено разъяснение]
    private static final String CLARIFICATION_SEND_TAB_XPATH =
            "//a[@role='tab' and contains(., 'Отправлено разъяснение')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер запроса]
    private static final String REQUEST_NUMBER_ID = "RequestNumber";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки]
    private static final String TRADE_NUMBER_ID = "TradeNumber";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование закупки]
    private static final String TRADE_NAME_ID = "TradeName";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата поступления с]
    private static final String REQUEST_DATE_FROM_ID = "RequestDateFrom";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата поступления по]
    private static final String REQUEST_DATE_TO_ID = "RequestDateTo";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Рабочая группа - первое из выбранных значений]
    private static final String SELECTED_WORK_GROUPS_XPATH = "//*[@id='SelectedWorkGroups_taglist']/li/span[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Показать]
    private static final String SHOW_BUTTON_XPATH = "//*[@id='ClearFilterButton']/preceding-sibling::button[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Очистить фильтр]
    private static final String CLEAR_FILTER_BUTTON_ID = "ClearFilterButton";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел Раздел [Результаты поиска (найденные запросы на разъяснение документации/результатов)]

    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Номер запроса]
    private static final String REQUEST_NUMBER_LINKS_XPATH = "//*[@id='RegisterGrid']/table//tr/td[1]/p/a";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Номер закупки]
    private static final String TRADE_NUMBERS_XPATH = "//*[@id='RegisterGrid']/table//tr/td[2]/p";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Статус]
    private static final String STATUSES_XPATH = "//*[@id='RegisterGrid']/table//tr/td[3]/p";
    //------------------------------------------------------------------------------------------------------------------
    // Значение или значения [Номер запроса] по [Номер закупки]
    private static final String REQUEST_NUMBER_LINK_BY_TRADE_NUMBER_XPATH =
            "//*[@id='RegisterGrid']/table//td[2][contains(., '%s')]/../td[1]/p/a";
    //------------------------------------------------------------------------------------------------------------------
    // Значение [Статус] по [Номер запроса]
    private static final String REQUEST_STATUS_BY_REQUEST_NUMBER_XPATH =
            "//*[@id='RegisterGrid']/table//td[1][contains(., '%s')]/../td[3]/p";
    //------------------------------------------------------------------------------------------------------------------
    // Значение [Просмотрен поставщиком] по [Номер запроса]
    private static final String IS_READ_BY_SUPPLIER_BY_REQUEST_NUMBER_XPATH =
            "//*[@id='RegisterGrid']/table//td[1][contains(., '%s')]/../td[4]/p";
    //------------------------------------------------------------------------------------------------------------------
    // Значение [Текст запроса] по [Номер запроса]
    private static final String REQUEST_TEXT_BY_REQUEST_NUMBER_XPATH =
            "//*[@id='RegisterGrid']/table//td[1][contains(., '%s')]/../td[6]/p";
    //------------------------------------------------------------------------------------------------------------------
    // Значение [Текст разъяснения] по [Номер запроса]
    private static final String EXPLANATION_TEXT_BY_REQUEST_NUMBER_XPATH =
            "//*[@id='RegisterGrid']/table//td[1][contains(., '%s')]/../td[7]/p";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary blockNames = new Dictionary();  // основные разделы страницы
    private final Dictionary fieldNames = new Dictionary();  // все поля на странице
    private final Dictionary tabNames = new Dictionary();    // все вкладки на странице
    private final Dictionary buttonNames = new Dictionary(); // все кнопки на странице
    private final Dictionary columnNames = new Dictionary(); // все столбцы в результатах роиска

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public RegisterOfInquiriesPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Заголовок страницы", PAGE_HEADER_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Номер запроса", REQUEST_NUMBER_ID);
        fieldNames.add("Номер закупки", TRADE_NUMBER_ID);
        fieldNames.add("Наименование закупки", TRADE_NAME_ID);
        fieldNames.add("Дата поступления с", REQUEST_DATE_FROM_ID);
        fieldNames.add("Дата поступления по", REQUEST_DATE_TO_ID);
        fieldNames.add("Рабочая группа - первое из выбранных значений", SELECTED_WORK_GROUPS_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        tabNames.add("Выбрать", SELECT_TAB_XPATH);
        tabNames.add("Требует разъяснения", CLARIFICATION_REQUIRED_TAB_XPATH);
        tabNames.add("Отправлено разъяснение", CLARIFICATION_SEND_TAB_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Фильтр", HIDDEN_FILTER_BUTTON_ID);
        buttonNames.add("Показать", SHOW_BUTTON_XPATH);
        buttonNames.add("Очистить фильтр", CLEAR_FILTER_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
        columnNames.add("Столбец Номер запроса", REQUEST_NUMBER_LINKS_XPATH);
        columnNames.add("Столбец Номер запроса по номеру закупки", REQUEST_NUMBER_LINK_BY_TRADE_NUMBER_XPATH);
        columnNames.add("Столбец Номер закупки", TRADE_NUMBERS_XPATH);
        columnNames.add("Столбец Статус", STATUSES_XPATH);
        columnNames.add("Столбец Статус по номеру запроса", REQUEST_STATUS_BY_REQUEST_NUMBER_XPATH);
        columnNames.add("Столбец Просмотрен поставщиком по номеру запроса", IS_READ_BY_SUPPLIER_BY_REQUEST_NUMBER_XPATH);
        columnNames.add("Столбец Текст запроса по номеру запроса", REQUEST_TEXT_BY_REQUEST_NUMBER_XPATH);
        columnNames.add("Столбец Текст разъяснения по номеру запроса", EXPLANATION_TEXT_BY_REQUEST_NUMBER_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Раздел [Таблица результатов поиска запроса]

    /**
     * Проверяет количество строк в результатах поиска запроса.
     *
     * @param expectedRows ожидаемое количество строк в результатах поиска запроса
     */
    public void checkRowsNumberInSearchResults(int expectedRows) {
        ElementsCollection rows = $$(this.getBy(columnNames.find("Столбец Номер запроса")));
        System.out.printf("[ИНФОРМАЦИЯ]: результаты поиска содержат: [%d] строк(у).%n", rows.size());
        Assert.assertEquals("[ОШИБКА]: ожидаемое количество строк в результатах поиска не совпадает с реальным",
                expectedRows, rows.size());
    }

    /**
     * Проверяет значение ячейки в таблице с результатами поиска в реестре запросов по имени столбца и номеру строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  номер строки в таблице
     * @param expected   ожидаемое значение ячейки в таблице
     */
    public void checkCellInSearchResultsByColumnNameAndRowNumber(String columnName, String rowNumber, String expected) {
        String locator = String.format(columnNames.find(columnName),
                config.getParameter("CommonClarificationRequestNumber"));
        System.out.printf("[ИНФОРМАЦИЯ]: сформирован локатор: [%s].%n", locator);
        this.verifyCellInTableByColumnElementsAndLineNumberForText(
                "Реестр запросов на разъяснение документации/результатов", columnName, $$(this.getBy(locator)),
                rowNumber, expected);
    }

    /**
     * Переходит по ссылке с номером запроса в результатах поиска запроса.
     */
    public void clickOnRequestNumberLinkInSearchResultsUsingPurchaseNumber() throws Exception {
        String locator = String.format(columnNames.find("Столбец Номер запроса по номеру закупки"),
                config.getParameter("PurchaseNumber"));
        System.out.printf("[ИНФОРМАЦИЯ]: сформирован локатор: [%s].%n", locator);
        SelenideElement link = $(this.getBy(locator));
        link.waitUntil(clickable, timeout, polling);
        Assert.assertEquals("[ОШИБКА]: номер запроса в результатах поиска не совпадает с ожидаемым",
                config.getParameter("CommonClarificationRequestNumber"), link.getText());
        System.out.printf("[ИНФОРМАЦИЯ]: переходим по ссылке с номером запроса: [%s].%n", link.getText());
        this.scrollToCenterAndclickInElementJS(link);
    }

    // endregion

    // region Общие методы для работы с элементами этой страницы

    /**
     * Переходит к блоку полей на форме.
     *
     * @param blockName имя блока
     * @return ссылка на экземпляр этого класса
     */
    public RegisterOfInquiriesPage gotoBlock(String blockName) throws Exception {
        SelenideElement block = $(this.getBy(blockNames.find(blockName)));
        this.scrollToCenter(block);
        block.shouldBe(visible);
        System.out.printf("[ИНФОРМАЦИЯ]: произведен переход к блоку полей: [%s].%n", blockName);
        TimeUnit.SECONDS.sleep(3);

        return this;
    }

    /**
     * Устанавливает значение полей с предварительной их очисткой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     * @return ссылка на экземпляр этого класса
     */
    public RegisterOfInquiriesPage setFieldClearClickAndSendKeys(String fieldName, String value) {
        $(this.getBy(fieldNames.find(fieldName))).waitUntil(clickable, timeout, polling).sendKeys(value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);

        return this;
    }

    /**
     * Делает щелчок мышью по указанной кнопке.
     *
     * @param buttonName имя кнопки
     * @return ссылка на экземпляр этого класса
     */
    public RegisterOfInquiriesPage clickOnButton(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.scrollToCenterAndclickInElementJS(this.getBy(buttonNames.find(buttonName)));
        this.logPressedButtonName(buttonName);
        this.waitForPageLoad(5);

        return this;
    }

    // endregion
}
