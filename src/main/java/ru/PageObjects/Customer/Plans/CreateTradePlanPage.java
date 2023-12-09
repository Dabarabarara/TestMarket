package ru.PageObjects.Customer.Plans;

import Helpers.Dictionary;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonDialogs.SavedDialog;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы со страницей [Создание плана закупки]
 * ( Главная / Заказчикам / Планы закупок / Создание плана закупки )
 * ( .kontur.ru/customer/lk/TradePlans/TradePlan/Create ).
 * Created by Vladimir V. Klochkov on 02.09.2016.
 * Updated by Vladimir V. Klochkov on 19.02.2021.
 */
public class CreateTradePlanPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы
    private static final String PAGE_HEADER_XPATH = "//h2[contains(.,'Создание плана закупки')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Общие сведения о плане закупки]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Общие сведения о плане закупки]
    private static final String PLAN_GENERAL_INFO_BLOCK_XPATH = "//h3[contains(.,'Общие сведения о плане закупки')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование]
    private static final String NAME_ID = "Name";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Заказчик]
    private static final String CUSTOMER_NAME_XPATH = "//div[@data-bind='text: Customer.Name']";
    //------------------------------------------------------------------------------------------------------------------

    // region Список [Вид]

    //------------------------------------------------------------------------------------------------------------------
    // Список [Вид] в закрытом виде - значение
    private static final String KIND_LIST_CLOSED_VALUE_XPATH =
            "//span[@aria-owns='PurchaseKind_listbox']//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Значение в раскрытом списке [Вид], которое следует выбрать
    private static final String KIND_LIST_OPENED_DESIRED_VALUE_XPATH =
            "//*[@id='PurchaseKind_listbox']/li[contains(.,'План коммерческих закупок')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Период C]
    private static final String START_DATE_ID = "StartDate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Период По]
    private static final String END_DATE_ID = "EndDate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата утверждения]
    private static final String CONFIRMED_DATE_ID = "ConfirmedDate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Отчетный год]
    private static final String REPORTING_YEAR_ID = "ReportingYear";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Продолжить]
    private static final String CONTINUE_BUTTON_XPATH =
            "//button[@class='k-button k-state-default' and contains(.,'Продолжить')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Подвал страницы

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Сервер]
    private static final String SERVER_VERSION_XPATH = "//li[contains(.,'Сервер: ') or contains(.,'SERVER: ')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary blockNames = new Dictionary();  // все разделы на странице
    private final Dictionary fieldNames = new Dictionary();  // все поля на странице
    private final Dictionary buttonNames = new Dictionary(); // все кнопки на странице

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CreateTradePlanPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Заголовок страницы", PAGE_HEADER_XPATH);
        blockNames.add("Общие сведения о плане закупки", PLAN_GENERAL_INFO_BLOCK_XPATH);
        blockNames.add("Подвал страницы", SERVER_VERSION_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Наименование", NAME_ID);
        fieldNames.add("Заказчик", CUSTOMER_NAME_XPATH);
        fieldNames.add("Вид", KIND_LIST_CLOSED_VALUE_XPATH);
        fieldNames.add("Значение в раскрытом списке Вид", KIND_LIST_OPENED_DESIRED_VALUE_XPATH);
        fieldNames.add("Период C", START_DATE_ID);
        fieldNames.add("Период По", END_DATE_ID);
        fieldNames.add("Дата утверждения", CONFIRMED_DATE_ID);
        fieldNames.add("Отчетный год", REPORTING_YEAR_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Продолжить", CONTINUE_BUTTON_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Раздел [Общие сведения о плане закупки]

    /**
     * Переходит к блоку полей на форме.
     *
     * @param blockName имя блока
     */
    public void gotoBlock(String blockName) throws Exception {
        SelenideElement block = $(this.getBy(blockNames.find(blockName)));
        this.scrollToCenter(block);
        block.shouldBe(visible);
        System.out.printf("[ИНФОРМАЦИЯ]: произведен переход к блоку полей [%s].%n", blockName);
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * Заполняет список [Вид] требуемым значением.
     */
    public void setKind() throws Exception {
        SelenideElement listClosed = $(this.getBy(fieldNames.find("Вид")));
        SelenideElement desiredValue = $(this.getBy(fieldNames.find("Значение в раскрытом списке Вид")));
        this.waitForListOpensAndSelectDesiredValue("Вид", listClosed, desiredValue, listClosed);
        config.setParameter("TradePlanKind", listClosed.getText());
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
        this.waitClearClickAndSendKeys(this.getBy(fieldNames.find(fieldName)), value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
    }

    /**
     * Устанавливает значение полей с элементами увеличения и уменьшения значения (стрелки вверх и вниз справа от поля).
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     */
    public void setKendoNumericTextBoxField(String fieldName, String value) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
        this.setKendoNumericTextBoxJS(fieldName, fieldNames.find(fieldName), value);
        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * Проверяет не пустое значение поля.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение, которое должно содержаться в поле
     */
    public void verifyField(String fieldName, String value) {
        String message = String.format("[ОШИБКА]: значение [%s] не содержится в поле [%s]", value, fieldName);
        By field = this.getBy(fieldNames.find(fieldName));

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n",
                fieldName, value);
        Assert.assertTrue(message, this.verifyFieldContent(field, value));
    }

    /**
     * Проверяет, что поле содержит не пустое значение.
     *
     * @param fieldName имя поля
     */
    public void verifyFieldIsNotEmpty(String fieldName) {
        String message = String.format("[ОШИБКА]: поле [%s] содержит пустое значение", fieldName);
        By field = this.getBy(fieldNames.find(fieldName));
        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] на не пустое значение.%n", fieldName);
        Assert.assertTrue(message, this.verifyFieldIsNotEmpty(field));
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
