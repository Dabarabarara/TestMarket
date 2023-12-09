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
 * Класс описывающий страницу [ Поиск закупок ]
 * ( supplier/auction/Trade/Search.aspx ).
 * Created by Evgeniy Glushko on 25.08.2016.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class SearchPurchasePage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [Вход]
    private static final String ENTRY_OFFER_LINK_XPATH = "//*[@id='BaseMainContent_ucHeaderControl_dvLogin']/a[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы [Поиск закупок]
    private static final String PAGE_HEADER_XPATH = "//*[@id='BaseMainContent_MainContent_upFilter']//h2";
    //==================================================================================================================
    //
    // Критерии фильтрации:
    //
    //==================================================================================================================
    // Поле [Номер закупки/лота]
    private static final String NUMBER_PURCHASE_LOT_INPUT = "BaseMainContent_MainContent_txtNumber_txtText";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование объекта закупки]
    private static final String NAME_PURCHASE_INPUT_XPATH = "//*[@id='BaseMainContent_MainContent_txtName_txtText']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Способ закупки]:
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Способ закупки] в закрытом состоянии
    private static final String PURCHASE_TYPE_XPATH =
            "//*[@id='BaseMainContent_MainContent_upFilter']/div/div[1]/div/div[1]/div/fieldset[5]/button";
    //..................................................................................................................
    private static final String purchaseTypeLocatorPrefix =
            "ui-multiselect-BaseMainContent_MainContent_lbxPurchaseMethods-option-";
    //..................................................................................................................
    private static final String purchaseTypeIsSelected = " and @aria-selected='true'";
    //..................................................................................................................
    // Флажок [Аукцион]
    private static final String AUCTION_UNSELECTED_IN_LIST_ID = purchaseTypeLocatorPrefix + "0";
    //..................................................................................................................
    private static final String AUCTION_SELECTED_IN_LIST_XPATH = "//input[@id='" + purchaseTypeLocatorPrefix + "0'" +
            purchaseTypeIsSelected + "]";
    //..................................................................................................................
    // Флажок [Конкурс]
    private static final String TENDER_UNSELECTED_IN_LIST_ID = purchaseTypeLocatorPrefix + "1";
    //..................................................................................................................
    private static final String TENDER_SELECTED_IN_LIST_XPATH = "//input[@id='" + purchaseTypeLocatorPrefix + "1'" +
            purchaseTypeIsSelected + "]";
    //------------------------------------------------------------------------------------------------------------------
    // Поля [Дата публикации извещения] (от и до):
    //------------------------------------------------------------------------------------------------------------------
    private static final String DATE_OF_PUBLICATION_NOTICE_FROM_XPATH =
            "//*[@id='BaseMainContent_MainContent_txtPublicationDate_txtDateFrom']";
    //..................................................................................................................
    private static final String DATE_OF_PUBLICATION_NOTICE_TO_XPATH =
            "//*[@id='BaseMainContent_MainContent_txtPublicationDate_txtDateTo']";
    //------------------------------------------------------------------------------------------------------------------
    // Поля [Дата окончания срока подачи заявок] (от и до):
    //------------------------------------------------------------------------------------------------------------------
    private static final String EXPIRY_DATE_FROM_XPATH =
            "//*[@id='BaseMainContent_MainContent_txtApplicationEndDate_txtDateFrom']";
    //..................................................................................................................
    private static final String EXPIRY_DATE_TO_XPATH =
            "//*[@id='BaseMainContent_MainContent_txtApplicationEndDate_txtDateTo']";
    //------------------------------------------------------------------------------------------------------------------
    // Поля [Дата начала торгов/переторжки] (от и до):
    //------------------------------------------------------------------------------------------------------------------
    private static final String START_DATE_AUCTION_FROM_XPATH =
            "//*[@id='BaseMainContent_MainContent_txtPerformingDate_txtDateFrom']";
    //..................................................................................................................
    private static final String START_DATE_AUCTION_TO_XPATH =
            "//*[@id='BaseMainContent_MainContent_txtPerformingDate_txtDateTo']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Начальная цена - от]
    private static final String START_PRICE_FROM_ID = "BaseMainContent_MainContent_txtStartPrice_txtRangeFrom";
    //------------------------------------------------------------------------------------------------------------------
    // Флажки [Тип закупки]:
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Закупка в соответствии с нормами 223-ФЗ]
    private static final String PURCHASE_TYPE_WITH_FL223_CHECKBOX_ID = "BaseMainContent_MainContent_chkPurchaseType_0";
    //..................................................................................................................
    // Флажок [Коммерческая закупка]
    private static final String PURCHASE_TYPE_COMMERCIAL_PURCHASE_CHECKBOX_ID =
            "BaseMainContent_MainContent_chkPurchaseType_1";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Закупка малого объема]
    private static final String PURCHASE_TYPE_SMALL_PURCHASE_CHECKBOX_ID =
            "BaseMainContent_MainContent_chkPurchaseType_2";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Процедура в соответствии с ПП РФ №615]
    private static final String PURCHASE_TYPE_615_PROCEDURE_CHECKBOX_ID =
            "BaseMainContent_MainContent_chkPurchaseType_3";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Поиск]
    private static final String SEARCH_BUTTON_ID = "BaseMainContent_MainContent_btnSearch";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Очистить]
    private static final String CLEAR_BUTTON_ID = "BaseMainContent_MainContent_btnCancel";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка со статусом закупки
    private static final String PURCHASES_STATE_TAB_LINK_XPATH = "//*[@id='lotStateTabs']//a[text() = '%s']";
    //==================================================================================================================
    //
    // Панель навигации и управления отображением количества результатов фильтрации на странице:
    //
    //==================================================================================================================
    // Общее количество страниц
    private static final String TOTAL_NUMBER_OF_PAGES_ID = "sp_1_BaseMainContent_MainContent_jqgTrade_toppager";
    //------------------------------------------------------------------------------------------------------------------
    // Следующие N записей
    private static final String NEXT_ROWS_XPATH
            = "//td[@id='next_t_BaseMainContent_MainContent_jqgTrade_toppager']/span";
    //------------------------------------------------------------------------------------------------------------------
    // Выбор количества строк таблицы
    private static final String SELECT_NUMBER_ROWS_XPATH =
            "//*[@id='BaseMainContent_MainContent_jqgTrade_toppager_center']//select";
    //==================================================================================================================
    //
    // Таблица с результатами поиска:
    //
    //==================================================================================================================
    // Строки в колонке [Опубликовано] (для всех строк таблицы)
    private static final String PUBLISHED_COLUMN_XPATH =
            "//tr/td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_PublicationDate']";
    //------------------------------------------------------------------------------------------------------------------
    // Строки в колонке [Номер закупки/лота] (для всех строк таблицы)
    private static final String TRADE_NUMBER_COLUMN_XPATH =
            "//td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_Number']/a";
    //------------------------------------------------------------------------------------------------------------------
    // Первая строка [Номер извещения о закупке/Номер лота] в результатах поиска закупки
    private static final String FIRST_TRADE_NUMBER_IN_COLUMN_XPATH = "//a[contains(.,'%s')][1]";
    //------------------------------------------------------------------------------------------------------------------
    // Строки в колонке [Номер в ЕИС] (для всех строк таблицы)
    private static final String EIS_NUMBER_COLUMN_XPATH =
            "//tr/td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_OosNumber']";
    //------------------------------------------------------------------------------------------------------------------
    // Строки в колонке [Наименование закупки] (для всех строк таблицы)
    private static final String PURCHASE_NAME_COLUMN_XPATH =
            "//td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_TradeName']/a";
    //------------------------------------------------------------------------------------------------------------------
    // Строки в колонке [Заявки] (для всех строк таблицы)
    private static final String HAS_APPLICATIONS_COLUMN_XPATH =
            "//td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_HasApplications']/a";
    //------------------------------------------------------------------------------------------------------------------
    // Строки в колонке [Дата окончания срока подачи заявок] (для всех строк таблицы)
    private static final String APPLICATION_END_DATE_COLUMN_XPATH =
            "//tr/td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_ApplicationEndDate']";
    //------------------------------------------------------------------------------------------------------------------
    // Строки в колонке [Дата начала торгов / переторжки] (для всех строк таблицы)
    private static final String TRADING_START_DATE_COLUMN_XPATH =
            "//tr/td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_TradingStartDate']";
    //------------------------------------------------------------------------------------------------------------------
    // Строки в колонке [Способ закупки] (для всех строк таблицы)
    private static final String PURCHASE_METHOD_COLUMN_XPATH =
            "//tr/td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_PurchaseMethodString']";
    //==================================================================================================================

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public SearchPurchasePage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     *               Методы страницы
     ******************************************************************************************************************/
    /**
     * Переходит на страницу [Поиск закупок].
     */
    public void goToSearchPurchase() throws Exception {
        open(config.getOpenPartSupplierTradeSearchUrl());
        this.waitForPageLoad();
    }

    /**
     * Проверка ссылки [Вход].
     */
    public void checkLoginLink() {
        $(By.xpath(ENTRY_OFFER_LINK_XPATH)).waitUntil(visible, timeout, polling).shouldHave(text("Вход"));
        $(By.xpath(PAGE_HEADER_XPATH)).waitUntil(visible, timeout, polling).shouldHave(text("Поиск закупок"));
    }

    /**
     * Получает значение из столбца.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  номер строки в таблице
     */
    public String getValueFromColumn(String columnName, int rowNumber) {
        String value = $$(By.xpath(PURCHASE_NAME_COLUMN_XPATH)).get(rowNumber - 1).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: получено значение [%s] из строки №: [%d], столбец: [%s].%n",
                value, rowNumber, columnName);
        return value;
    }

    /**
     * Проверяет, что результаты поиска содержат хотя бы одну строку.
     */
    public void checkThatTheSearchResultsContainsAtLeastOneRecord() {
        ElementsCollection purchaseNumbers = $$(By.xpath(TRADE_NUMBER_COLUMN_XPATH));

        // Ждем, пока отработает поиск - спинер в ЛК Поставщика не ловится от слова совсем
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        purchaseNumbers.get(0).waitUntil(clickable, longtime, polling);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        // Ну и проверяем что результаты поиска или сброса всех фильтров не пустые
        int size = purchaseNumbers.size();
        SoftAssert.assertTrue("[ОШИБКА]: нулевое количество строк в результатах поиска", size > 0);
        System.out.printf("[ИНФОРМАЦИЯ]: количество строк в результатах поиска [Поиск закупок]: [%d].%n", size);
    }

    /**
     * Проверяет, что результаты поиска содержат одинаковый номер закупки ( однолотовые и многолотовые закупки ).
     */
    public void checkTheSamePurchaseNumbersInSearchResults() {
        ElementsCollection purchaseNumbers = $$(By.xpath(TRADE_NUMBER_COLUMN_XPATH));

        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        purchaseNumbers.get(0).waitUntil(clickable, longtime, polling);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        int size = purchaseNumbers.size();
        String number = purchaseNumbers.get(0).getText().split("/")[0];
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем результаты поиска для закупки с номером [%s].%n", number);

        for (SelenideElement purchaseNumber : purchaseNumbers)
            SoftAssert.assertEquals(
                    "[ОШИБКА]: фильтр не сработал, номера закупок в результатах поиска отличаются",
                    number, purchaseNumber.getText().split("/")[0]);

        System.out.printf("[ИНФОРМАЦИЯ]: количество строк в результатах поиска [Поиск закупок]: [%d].%n", size);
    }

    /**
     * Устанавливает значение в поле [Номер закупки/лота].
     *
     * @param purchaseNumber номер закупки или номер закупки/лота
     */
    public void setPurchaseNumberLotNumber(String purchaseNumber) {
        SelenideElement numberPurchaseLot = $(By.id(NUMBER_PURCHASE_LOT_INPUT));
        numberPurchaseLot.waitUntil(visible, timeout, polling).clear();
        numberPurchaseLot.click();
        numberPurchaseLot.sendKeys(purchaseNumber);
        numberPurchaseLot.click();
        Assert.assertEquals("[ОШИБКА]: неверное значение в поле: [Номер закупки/лота]",
                purchaseNumber, numberPurchaseLot.getValue());
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [Номер закупки/лота] значением: [%s].%n",
                purchaseNumber);
    }

    /**
     * Выбор количества строк отображения в таблице.
     */
    public void selectNumberRowInTable(String numberRows) throws Exception {
        $(By.xpath(SELECT_NUMBER_ROWS_XPATH)).waitUntil(visible, timeout, polling).selectOption(numberRows);
        TimeUnit.SECONDS.sleep(15);
    }

    /**
     * Проверяет количество строк в таблице [Закупки].
     *
     * @param expectedPurchaseNumber ожидаемое количество строк в таблице
     */
    public void checkNumberOfRowsInContractPositionsTable(int expectedPurchaseNumber) {
        this.checkElementsCollectionSize(
                $$(this.getBy(PUBLISHED_COLUMN_XPATH)), expectedPurchaseNumber);
    }

    /**
     * Делает клик по ссылке на лот с указанным номером в результатах поиска извещения о закупке.
     *
     * @param noticeNumberWithLotNumber номер извещения о закупке/номер лота
     */
    public void clickOnNoticeNumberWithLotNumber(String noticeNumberWithLotNumber) throws Exception {
        SelenideElement noticeNumber =
                $(By.xpath(String.format(FIRST_TRADE_NUMBER_IN_COLUMN_XPATH, noticeNumberWithLotNumber)));
        System.out.printf(
                "[ИНФОРМАЦИЯ]: переходим по ссылке с № извещения: [%s] в первой ( верхней ) строке результатов поиска.%n",
                noticeNumber.getText());
        this.scrollToCenter(noticeNumber);
        noticeNumber.waitUntil(clickable, timeout, polling).click();
        this.waitForPageLoad();
        config.switchToNewWindowInBrowser();
    }

    /**
     * Проверка числового значения в столбце [Номер в ЕИС] в таблице.
     */
    public void checkNumberEisColumnInTable() throws Exception {
        boolean result = false;
        this.selectNumberRowInTable("100");
        $$(By.xpath(TRADE_NUMBER_COLUMN_XPATH)).get(0).waitUntil(clickable, timeout, polling);
        ElementsCollection rows = $$(By.xpath(EIS_NUMBER_COLUMN_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: количество проверяемых строк в таблице: [%d].%n", rows.size());

        for (SelenideElement row : rows)
            if (row.getText().length() == 11) {
                System.out.printf("[ИНФОРМАЦИЯ]: в столбце [Номер в ЕИС] найдено значение [%s].%n", row.getText());
                result = true;
                break;
            }
        SoftAssert.assertTrue("[ОШИБКА]: Проверяемые строки в колонке [Номер в ЕИС] пустые", result);
    }

    /**
     * Получает Номер закупки и нажимает на ссылку с номером закупки.
     */
    public void clickOnPurchaseOrLotNumberLinkInFirstRow() throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: текущая версия сервера %s.%n", this.getCurrentServerVersion());
        SelenideElement firstPurchaseNumber = $$(By.xpath(TRADE_NUMBER_COLUMN_XPATH)).get(0);
        firstPurchaseNumber.waitUntil(visible, timeout, polling);
        String purchaseNumber = firstPurchaseNumber.getText().split("/")[0];
        System.out.println("[ИНФОРМАЦИЯ]: получен Номер закупки / лота: " + purchaseNumber);
        config.setParameter("PurchaseNumberForTradeSearch", purchaseNumber);
        firstPurchaseNumber.click();
        this.waitForPageLoad();
    }

    /**
     * Работа с фильтром по дате.
     *
     * @param dateType тип поля, содержащего дату
     */
    public void checkFiltersOfDate(String dateType) throws Exception {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        List<String> dateValues = new ArrayList<>();  // Глубина поиска по дням назад, если за сегодня ничего нет
        dateValues.add(dateFormat.format(date));      // Сегодня
        dateValues.add(getDateShiftInDays(-1));  // Вчера
        dateValues.add(getDateShiftInDays(-2));  // Позавчера и т.д.
        dateValues.add(getDateShiftInDays(-3));
        dateValues.add(getDateShiftInDays(-4));
        dateValues.add(getDateShiftInDays(-5));
        dateValues.add(getDateShiftInDays(-6));
        dateValues.add(getDateShiftInDays(-7));
        dateValues.add(getDateShiftInDays(-8));
        dateValues.add(getDateShiftInDays(-9));
        dateValues.add(getDateShiftInDays(-10));
        dateValues.add(getDateShiftInDays(-11));
        dateValues.add(getDateShiftInDays(-12));

        Dictionary dateFroms = new Dictionary();
        dateFroms.add("Дата публикации извещения", DATE_OF_PUBLICATION_NOTICE_FROM_XPATH);
        dateFroms.add("Дата окончания срока подачи заявок", EXPIRY_DATE_FROM_XPATH);
        dateFroms.add("Дата начала торгов / переторжки", START_DATE_AUCTION_FROM_XPATH);
        SelenideElement dateFrom = $(By.xpath(dateFroms.find(dateType)));

        Dictionary dateTos = new Dictionary();
        dateTos.add("Дата публикации извещения", DATE_OF_PUBLICATION_NOTICE_TO_XPATH);
        dateTos.add("Дата окончания срока подачи заявок", EXPIRY_DATE_TO_XPATH);
        dateTos.add("Дата начала торгов / переторжки", START_DATE_AUCTION_TO_XPATH);
        SelenideElement dateTo = $(By.xpath(dateTos.find(dateType)));

        Dictionary columnNames = new Dictionary();
        columnNames.add("Дата публикации извещения", "Опубликовано");
        columnNames.add("Дата окончания срока подачи заявок", "Дата окончания срока подачи заявок");
        columnNames.add("Дата начала торгов / переторжки", "Дата начала торгов / переторжки");
        String columnName = columnNames.find(dateType);

        Dictionary columnsValuesInTable = new Dictionary();
        columnsValuesInTable.add("Дата публикации извещения", PUBLISHED_COLUMN_XPATH);
        columnsValuesInTable.add("Дата окончания срока подачи заявок", APPLICATION_END_DATE_COLUMN_XPATH);
        columnsValuesInTable.add("Дата начала торгов / переторжки", TRADING_START_DATE_COLUMN_XPATH);
        String columnValuesInTable = columnsValuesInTable.find(dateType);

        System.out.println("[ИНФОРМАЦИЯ]: тип даты: [" + dateType + "], имя столбца: [" + columnName + "].");

        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        for (String dateValue : dateValues) // Цикл по списку дат
        {
            dateFrom.waitUntil(visible, timeout, polling).val(dateValue);
            String currentValue = dateFrom.getValue();
            System.out.println("[ИНФОРМАЦИЯ]: начальная дата: [" + currentValue + "].");
            Assert.assertEquals("[ОШИБКА]: поле содержит не правильное значение.", dateValue, currentValue);

            dateTo.waitUntil(visible, timeout, polling).val(dateValue);
            currentValue = dateTo.getValue();
            System.out.println("[ИНФОРМАЦИЯ]: конечная дата: [" + currentValue + "].");
            Assert.assertEquals("[ОШИБКА]: поле содержит не правильное значение.", dateValue, currentValue);

            this.clickButton("Поиск");
            this.waitLoadingRectangle();
            int size = $$(By.xpath(columnValuesInTable)).size();

            if (size == 0) // Если не найдено ни одной записи, смотрим следующую дату в списке дат
            {
                this.clickButton("Очистить");
                this.waitLoadingRectangle();
            } else          // Если хотя бы одна запись найдена, проверяем таблицу
            {
                $(By.xpath(columnValuesInTable)).waitUntil(visible, timeout, polling);
                System.out.println("[ИНФОРМАЦИЯ]: получено количество записей в таблице: " + size);

                for (int i = 0; i < size; i++) // Проверяем дату в каждой записи таблицы
                {
                    String text = $$(By.xpath(columnValuesInTable)).get(i).getText();
                    Assert.assertTrue("[ОШИБКА]: проверяемые строки в колонке: [" + columnName +
                            "] содержат не правильную дату.", text.contains(dateValue));
                }
                break; // Выходим из цикла перебора дат, достаточно одной успешной проверки
            }
        }

        this.clickButton("Очистить");
        this.waitLoadingRectangle();
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Устанавливаем значение в поле [Наименование объекта закупки].
     *
     * @param purchaseName Наименование объекта закупки
     */
    public void setPurchaseName(String purchaseName) {
        SelenideElement namePurchaseInput = $(By.xpath(NAME_PURCHASE_INPUT_XPATH));
        namePurchaseInput.
                waitUntil(visible, timeout, polling).sendKeys(purchaseName);
        namePurchaseInput.click();
        Assert.assertEquals("[ОШИБКА]: неверное значение в поле: [Наименование объекта закупки]",
                purchaseName, namePurchaseInput.getValue());
        System.out.println("[ИНФОРМАЦИЯ]: произведено заполнение поля [Наименование объекта закупки], " +
                "значением: " + purchaseName);
    }

    /**
     * Метод проверяет значения в колонке [Способ закупки].
     *
     * @param mustNotBeValue значение, которого не должно быть в колонке [Способ закупки]
     */
    public void checkPurchaseMethodColumn(String mustNotBeValue) throws Exception {
        boolean result = true;
        TimeUnit.SECONDS.sleep(1);
        int totalNumberOfPage = Integer.parseInt($(By.id(TOTAL_NUMBER_OF_PAGES_ID))
                .waitUntil(visible, timeout).getText());
        do {
            for (int i = 0; i < $$(By.xpath(PURCHASE_METHOD_COLUMN_XPATH)).size(); i++) {
                if ($$(By.xpath(PURCHASE_METHOD_COLUMN_XPATH)).get(i).getText().contains(mustNotBeValue)) {
                    System.out.println(">>> столбец: [Способ закупки]. В строке № " + (i + 1) + " содержит значение: "
                            + $$(By.xpath(PURCHASE_METHOD_COLUMN_XPATH)).get(i).getText());
                    result = false;
                }
            }
            if (totalNumberOfPage != 0) {
                if ($(By.xpath(NEXT_ROWS_XPATH)).isDisplayed())
                    $(By.xpath(NEXT_ROWS_XPATH)).waitUntil(visible, timeout).click();
                TimeUnit.SECONDS.sleep(5);
                this.waitLoadingRectangle();
                $(By.xpath(PURCHASE_METHOD_COLUMN_XPATH)).waitUntil(visible, timeout).scrollTo();
                TimeUnit.SECONDS.sleep(1);
                totalNumberOfPage--;
            }
        }
        while (totalNumberOfPage != 0);
        Assert.assertTrue("[ИНФОРМАЦИЯ]: проверяемые строки в колонке [Способ закупки] содержат значение: " +
                mustNotBeValue, result);
    }

    /**
     * Метод выбирает чекбоксы в поле [Тип закупки].
     *
     * @param checkboxName имя чекбокса
     */
    public void selectPurchaseTypeCheckBox(String checkboxName) throws Exception {
        Dictionary checkboxNames = new Dictionary();
        checkboxNames.add("Закупка в соответствии с нормами 223-ФЗ", PURCHASE_TYPE_WITH_FL223_CHECKBOX_ID);
        checkboxNames.add("Коммерческая закупка", PURCHASE_TYPE_COMMERCIAL_PURCHASE_CHECKBOX_ID);
        checkboxNames.add("Закупка малого объема", PURCHASE_TYPE_SMALL_PURCHASE_CHECKBOX_ID);
        checkboxNames.add("Процедура в соответствии с ПП РФ №615", PURCHASE_TYPE_615_PROCEDURE_CHECKBOX_ID);

        System.out.printf("[ИНФОРМАЦИЯ]: устанавливаем значение флажка [%s].%n", checkboxName);
        $(By.id(checkboxNames.find(checkboxName)))
                .waitUntil(visible, timeout, polling).click();
        TimeUnit.SECONDS.sleep(1);
        $(By.id(checkboxNames.find(checkboxName))).shouldBe(selected);
    }

    /**
     * Метод возвращает получение предыдущей даты.
     *
     * @return значение даты
     */
    private String getDateShiftInDays(int daysAgo) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, daysAgo);
        Date dayResult = calendar.getTime();
        System.out.println(
                ">>> (getDateShiftInDays) рассчитанное значение даты: " + dateFormat.format(dayResult));
        return dateFormat.format(dayResult);
    }

    /**
     * Выбирает требуемый способ закупки в поле фильтра [Способ закупки].
     *
     * @param purchaseType требуемый способ закупки
     */
    public void selectPurchaseTypeInFilter(String purchaseType) throws Exception {
        SelenideElement purchaseTypesList = $(By.xpath(PURCHASE_TYPE_XPATH));
        purchaseTypesList.waitUntil(clickable, timeout, polling);
        this.scrollToCenterAndclickInElementJS(purchaseTypesList);

        Dictionary unselecteds = new Dictionary();
        unselecteds.add("Аукцион", AUCTION_UNSELECTED_IN_LIST_ID);
        unselecteds.add("Конкурс", TENDER_UNSELECTED_IN_LIST_ID);

        Dictionary selecteds = new Dictionary();
        selecteds.add("Аукцион", AUCTION_SELECTED_IN_LIST_XPATH);
        selecteds.add("Конкурс", TENDER_SELECTED_IN_LIST_XPATH);

        ElementsCollection unselectedCheckBoxes = $$(By.id(unselecteds.find(purchaseType))).filterBy(visible);
        unselectedCheckBoxes.get(0).waitUntil(clickable, timeout, polling).click();
        ElementsCollection selectedCheckBoxes = $$(By.xpath(selecteds.find(purchaseType))).filterBy(visible);
        selectedCheckBoxes.get(0).waitUntil(clickable, timeout, polling);
        purchaseTypesList.waitUntil(clickable, timeout, polling).click();
    }

    /**
     * Заполняет поле [Начальная цена] требуемым значением.
     *
     * @param startPrice требуемое значение
     */
    public void enterStartPriceValueInFilter(String startPrice) throws Exception {
        SelenideElement startPriceFrom = $(By.id(START_PRICE_FROM_ID));
        startPriceFrom.waitUntil(visible, timeout, polling);
        this.setValueInElementJS(START_PRICE_FROM_ID, startPrice);
        Assert.assertEquals("[ОШИБКА]: поле: [Начальная цена] содержит не правильное значение.",
                startPrice, startPriceFrom.getValue());
    }

    /**
     * Выбирает требуемое состояние закупки в группе закладок.
     *
     * @param purchaseState требуемое состояние закупки
     */
    public void selectPurchaseStateTabInFilter(String purchaseState) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: выбираем закладку с состоянием закупки [%s].%n", purchaseState);
        String xpath = String.format(PURCHASES_STATE_TAB_LINK_XPATH, purchaseState);
        $(By.xpath(xpath)).waitUntil(visible, timeout, polling).click();
        this.waitForPageLoad();
    }

    /**
     * Нажимает кнопку [Поиск].
     */
    public void clickSearchButton() throws Exception {
        System.out.println("[ИНФОРМАЦИЯ]: производим нажатие кнопки [Поиск].");
        $(By.id(SEARCH_BUTTON_ID)).waitUntil(visible, timeout, polling).click();
        this.waitForPageLoad();
    }

    /**
     * Сбрасывает все фильтры поиска на странице [Поиск закупок] в исходное состояние.
     */
    public void resetPurchaseSearchFilters() throws Exception {
        $(By.id(CLEAR_BUTTON_ID)).waitUntil(visible, timeout, polling).click();
        this.waitForPageLoad();
    }

    /**
     * Переходит по ссылке с указанным порядковым номером в столбце 'Заявки'.
     *
     * @param linkNumber порядковый номер ссылки
     */
    public void goToLinkInRequestsColumnByNumber(String linkNumber) throws Exception {
        ElementsCollection requests = $$(By.xpath(HAS_APPLICATIONS_COLUMN_XPATH));
        int count = requests.size();
        Assert.assertTrue("[ОШИБКА]: ссылка [Есть] отсутствует в столбце [Заявки]", count > 0);
        System.out.printf("[ИНФОРМАЦИЯ]: количество строк в таблице: '%d'.%n", count);
        int link = Integer.parseInt(linkNumber) - 1;
        Assert.assertTrue("[ОШИБКА]: переданный номер строки вне диапазона", link >= 0 && link < count);
        System.out.printf("[ИНФОРМАЦИЯ]: переходим по ссылке с текстом: '%s'.%n",
                requests.get(link).getText());
        requests.get(link).click();

        // Открылось новое диалоговое окно [Ценовые предложения]
        TimeUnit.SECONDS.sleep(3);
    }
}
