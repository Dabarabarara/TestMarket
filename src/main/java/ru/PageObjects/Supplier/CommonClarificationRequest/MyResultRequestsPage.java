package ru.PageObjects.Supplier.CommonClarificationRequest;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Supplier.CommonSupplierPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывающий страницу [Запросы на разъяснение документации/результатов/заявок]
 * ( .kontur.ru/supplier/auction/Private/MyResultRequests.aspx?Type= ).
 * Created by Vladimir V. Klochkov on 30.10.2019.
 * Updated by Vladimir V. Klochkov on 11.03.2021.
 */
public class MyResultRequestsPage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы [Запросы на разъяснение документации/результатов/заявок]
    private static final String PAGE_HEADER_XPATH =
            "//h2[@class='auction_title' and contains(., 'Запросы на разъяснение ')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Блок фильтров для поиска запроса

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер запроса]
    private static final String REQUEST_NUMBER_ID = "BaseMainContent_MainContent_txtRequestNumber_txtText";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки]
    private static final String TRADE_NUMBER_ID = "BaseMainContent_MainContent_txtTradeNumber_txtText";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование закупки]
    private static final String TRADE_NAME_ID = "BaseMainContent_MainContent_txtTradeName_txtText";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Поиск]
    private static final String SEARCH_BUTTON_ID = "BaseMainContent_MainContent_btnSearch";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Очистить]
    private static final String CLEAR_BUTTON_ID = "BaseMainContent_MainContent_btnCancel";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Таблица с результатами поиска запроса

    //------------------------------------------------------------------------------------------------------------------
    // Строки в колонке [Номер запроса]
    private static final String REQUEST_NUMBER_COLUMN_XPATH =
            "//tr/td[@aria-describedby='BaseMainContent_MainContent_jqgRequests_RequestNumber']/a";
    //------------------------------------------------------------------------------------------------------------------
    // Строки в колонке [Статус]
    private static final String REQUEST_STATE_COLUMN_XPATH =
            "//tr/td[@aria-describedby='BaseMainContent_MainContent_jqgRequests_RequestState']";
    //------------------------------------------------------------------------------------------------------------------
    // Строки в колонке [Просмотрен заказчиком]
    private static final String IS_READ_BY_CUSTOMER_COLUMN_XPATH =
            "//tr/td[@aria-describedby='BaseMainContent_MainContent_jqgRequests_IsReadByCustomer']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary blockNames = new Dictionary();  // основные разделы страницы
    private final Dictionary fieldNames = new Dictionary();  // все поля на странице
    private final Dictionary buttonNames = new Dictionary(); // все кнопки на странице

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public MyResultRequestsPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Заголовок страницы", PAGE_HEADER_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Номер запроса", REQUEST_NUMBER_ID);
        fieldNames.add("Номер закупки", TRADE_NUMBER_ID);
        fieldNames.add("Наименование закупки", TRADE_NAME_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Поиск", SEARCH_BUTTON_ID);
        buttonNames.add("Очистить", CLEAR_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Таблица с результатами поиска запроса

    /**
     * Проверяет номер запроса в первой ( верхней ) строке результатов поиска запроса.
     *
     * @param requestNumber номер запроса
     * @return страница [Запросы на разъяснение документации/результатов/заявок]
     */
    public MyResultRequestsPage checkRequestNumber(String requestNumber) {
        ElementsCollection requestNumbers = $$(this.getBy(REQUEST_NUMBER_COLUMN_XPATH)).filterBy(visible);
        this.logColumnNameToCheck("Номер запроса", requestNumber);
        requestNumbers.get(0).waitUntil(clickable, timeout, polling).shouldHave(text(requestNumber));

        return this;
    }

    /**
     * Проверяет статус запроса в первой ( верхней ) строке результатов поиска запроса.
     *
     * @param requestState статус запроса
     * @return страница [Запросы на разъяснение документации/результатов/заявок]
     */
    public MyResultRequestsPage checkRequestState(String requestState) {
        ElementsCollection requestNumbers = $$(this.getBy(REQUEST_STATE_COLUMN_XPATH)).filterBy(visible);
        this.logColumnNameToCheck("Статус", requestState);
        requestNumbers.get(0).waitUntil(clickable, timeout, polling).shouldHave(text(requestState));

        return this;
    }

    /**
     * Проверяет состояние запроса в первой ( верхней ) строке результатов поиска запроса.
     *
     * @param isRead состояние запроса
     */
    public void isReadByCustomer(String isRead) {
        ElementsCollection requestNumbers = $$(this.getBy(IS_READ_BY_CUSTOMER_COLUMN_XPATH)).filterBy(visible);
        this.logColumnNameToCheck("Просмотрен заказчиком", isRead);
        requestNumbers.get(0).waitUntil(clickable, timeout, polling).shouldHave(text(isRead));
    }

    /**
     * Делает клик по ссылке на запрос в первой ( верхней ) строке результатов поиска запроса.
     */
    public void clickOnRequestNumber() throws Exception {
        ElementsCollection requestNumbers = $$(this.getBy(REQUEST_NUMBER_COLUMN_XPATH)).filterBy(visible);

        System.out.printf(
                "[ИНФОРМАЦИЯ]: переходим по ссылке с № запроса: [%s] в первой ( верхней ) строке результатов поиска.%n",
                requestNumbers.get(0).getText());
        this.scrollToCenter(requestNumbers.get(0));
        requestNumbers.get(0).waitUntil(clickable, timeout, polling).click();

        config.switchToNewWindowInBrowser();
        this.waitForPageLoad();
    }

    // endregion

    // region Общие методы для работы с элементами этой страницы

    /**
     * Переходит к блоку полей на форме.
     *
     * @param blockName имя блока
     * @return страница [Запросы на разъяснение документации/результатов/заявок]
     */
    public MyResultRequestsPage gotoBlock(String blockName) throws Exception {
        SelenideElement block = $(this.getBy(blockNames.find(blockName)));
        this.scrollToCenter(block);
        block.shouldBe(visible);
        System.out.printf("[ИНФОРМАЦИЯ]: Произведен переход к блоку полей: [%s].%n", blockName);
        TimeUnit.SECONDS.sleep(3);

        return this;
    }

    /**
     * Устанавливает значение поля.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     * @return страница [Запросы на разъяснение документации/результатов/заявок]
     */
    public MyResultRequestsPage setField(String fieldName, String value) throws Exception {
        $(this.getBy(fieldNames.find(fieldName))).waitUntil(clickable, timeout, polling).sendKeys(value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
        TimeUnit.SECONDS.sleep(5);
        this.verifyField(fieldName, value);

        return this;
    }

    /**
     * Проверяет значение поля.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение, которое должно содержаться в поле
     */
    public void verifyField(String fieldName, String value) {
        String message = String.format("[ОШИБКА]: значение [%s] не содержится в поле [%s]", value, fieldName);

        By field = this.getBy(fieldNames.find(fieldName));

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        Assert.assertTrue(message, this.verifyFieldContent(field, value));
    }

    /**
     * Делает щелчок мышью по указанной кнопке.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.scrollToCenterAndclickInElementJS(this.getBy(buttonNames.find(buttonName)));
        this.logPressedButtonName(buttonName);
        TimeUnit.SECONDS.sleep(3);
    }

    // endregion
}
