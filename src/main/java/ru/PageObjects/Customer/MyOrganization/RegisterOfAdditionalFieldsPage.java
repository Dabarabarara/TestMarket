package ru.PageObjects.Customer.MyOrganization;

import Helpers.Dictionary;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс описывающий работу со страницой [Реестр дополнительных полей].
 * ( .kontur.ru/customer/lk/ExternalField )
 * Created by Alexander S. Vasyurenko on 29.04.2021.
 * Updated by Alexander S. Vasyurenko on 11.05.2021.
 */
public class RegisterOfAdditionalFieldsPage extends CommonCustomerPage  {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region [Реестр дополнительных полей]

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Создать]
    private static final String CREATE_BUTTON_XPATH = "//a[contains(.,'Создать')]";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Закупка]
    private static final String PURCHASE_TAB_ID = "tab-1";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Лот]
    private static final String LOT_TAB_ID = "tab-2";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Договор]
    private static final String CONTRACT_TAB_ID = "tab-3";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Исполнение договора]
    private static final String CONTRACT_EXECUTION_TAB_ID = "tab-4";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Расторжение договора]
    private static final String CONTRACT_TERMINATION_TAB_ID = "tab-5";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Заявка]
    private static final String REQUEST_TAB_ID = "tab-6";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Позиция лота]
    private static final String LOT_POSITION_TAB_ID = "tab-7";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Свой протокол]
    private static final String CUSTOM_PROTOCOL_TAB_ID = "tab-8";
    //------------------------------------------------------------------------------------------------------------------
    // Дополнительное поле по наименованию
    private static final String ADDITIONAL_FIELD_XPATH = "//a[contains(.,'%s')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region [Создание дополнительного поля]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование]
    private static final String NAME_ID = "Name";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Код, используемый в интеграции]
    private static final String INTEGRATION_CODE_ID = "ApiCode";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Способ закупки] - область открытия списка по которой нужно будет сделать щелчок мышью
    private static final String PURCHASE_METHOD_LIST_OPEN_VALUES_XPATH =
            "//label[contains(.,'Способ закупки:')]/following-sibling::div//input";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Способ закупки] - список значений открыт - значение переданное в тесте
    private static final String PURCHASE_METHOD_LIST_DESIRED_VALUE_XPATH =
            "//*[@id='PurchaseMethodIds-list']/ul/li[contains(., '%s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Способ закупки] - текущее выбранное значение
    private static final String PURCHASE_METHOD_LIST_SELECTED_VALUE_XPATH =
            "//*[@id='PurchaseMethodIds_taglist']/li/span[contains(., '%s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип значения] (список возможных значений поля свёрнут)
    private static final String VALUE_TYPE_LIST_CLOSED_XPATH =
            "//label[contains(.,'Тип значения:')]/following-sibling::div//span[@role='listbox']//span[@class='k-select']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип значения] (список возможных значений поля открыт) -> требуемое значение в списке
    private static final String VALUE_TYPE_IN_OPENED_LIST_BY_NAME_XPATH =
            "//div[@id='ValueTypeId-list']//ul/li[contains(., '%s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип значения] (текущее значение)
    private static final String CURRENT_VALUE_TYPE_XPATH =
            "//label[contains(.,'Тип значения:')]/following-sibling::div//span[@role='listbox']//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Активно]
    private static final String IS_ACTIVE_CHECKBOX_ID = "IsActive";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Обязательное для заполнения]
    private static final String IS_REQUIRED_CHECKBOX_ID = "IsRequired";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Отображать в протоколах]
    private static final String DISPLAY_IN_PROTOCOLS_CHECKBOX_ID = "IsDisplayedForProtocols";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Отображать в договорах]
    private static final String DISPLAY_IN_CONTRACTS_CHECKBOX_ID = "IsDisplayedForDeals";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Отображать в открытой части]
    private static final String DISPLAY_IN_OPEN_PART_CHECKBOX_ID = "IsDisplayedForOpenPart";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Сохранить]
    private static final String SAVE_BUTTON_XPATH = "//button[contains(.,'Сохранить')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [ОК] в всплывающем окне [Информация]
    private static final String POPUP_OK_BUTTON_XPATH = "//*[@id='CommonErrorWindow']//button";
    //------------------------------------------------------------------------------------------------------------------

    // endregion


    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary tabNames = new Dictionary();
    private final Dictionary buttonNames = new Dictionary();
    private final Dictionary fieldNames = new Dictionary();
    private final Dictionary checkBoxNames = new Dictionary();

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public RegisterOfAdditionalFieldsPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        tabNames.add("Закупка", PURCHASE_TAB_ID);
        tabNames.add("Лот", LOT_TAB_ID);
        tabNames.add("Договор", CONTRACT_TAB_ID);
        tabNames.add("Исполнение договора", CONTRACT_EXECUTION_TAB_ID);
        tabNames.add("Расторжение договора", CONTRACT_TERMINATION_TAB_ID);
        tabNames.add("Заявка", REQUEST_TAB_ID);
        tabNames.add("Позиция лота", LOT_POSITION_TAB_ID);
        tabNames.add("Свой протокол", CUSTOM_PROTOCOL_TAB_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Создать", CREATE_BUTTON_XPATH);
        buttonNames.add("Сохранить", SAVE_BUTTON_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Наименование", NAME_ID);
        fieldNames.add("Код, используемый в интеграции", INTEGRATION_CODE_ID);
        //--------------------------------------------------------------------------------------------------------------
        checkBoxNames.add("Активно", IS_ACTIVE_CHECKBOX_ID);
        checkBoxNames.add("Обязательное для заполнения", IS_REQUIRED_CHECKBOX_ID);
        checkBoxNames.add("Отображать в протоколах", DISPLAY_IN_PROTOCOLS_CHECKBOX_ID);
        checkBoxNames.add("Отображать в договорах", DISPLAY_IN_CONTRACTS_CHECKBOX_ID);
        checkBoxNames.add("Отображать в открытой части", DISPLAY_IN_OPEN_PART_CHECKBOX_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Делает щелчок по вкладке c указанным именем.
     *
     * @param tabName имя вкладки
     */
    public void goToFieldTypeTabByName(String tabName) throws Exception {
        SelenideElement tab = $(this.getBy(tabNames.find(tabName)));
        tab.waitUntil(visible, timeout, polling);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие на закладку [%s].%n", tabName);
        this.clickInElementJS(tab);
        this.waitLoadingImage();
    }

    /**
     * Возвращает значение флажка с указанным именем.
     *
     * @param checkBoxName имя флажка
     * @return значение флажка
     */
    public boolean getCheckBoxValue(String checkBoxName) {
        $(this.getBy(checkBoxNames.find(checkBoxName))).waitUntil(exist, timeout, polling).shouldBe(visible);
        return $(this.getBy(checkBoxNames.find(checkBoxName))).isSelected();
    }

    /**
     * Проверяет, что флажок с указанным именем выбран.
     *
     * @param checkBoxName имя флажка
     * @return страница [Реестр дополнительных полей]
     */
    public RegisterOfAdditionalFieldsPage verifyCheckBoxSelected(String checkBoxName) {
        String message = ">>> (verifyCheckBoxSelected) Флажок [%s] не установлен.";
        Assert.assertTrue(String.format(message, checkBoxName), this.getCheckBoxValue(checkBoxName));
        System.out.printf("[ИНФОРМАЦИЯ]: флажок: [%s] находится в отмеченном состоянии.%n", checkBoxName);

        return this;
    }

    /**
     * Проверяет, что флажок с указанным именем не выбран.
     *
     * @param checkBoxName имя флажка
     * @return страница [Реестр дополнительных полей]
     */
    public RegisterOfAdditionalFieldsPage verifyCheckBoxNotSelected(String checkBoxName) {
        String message = ">>> (verifyCheckBoxNotSelected) Флажок [%s] установлен.";
        Assert.assertFalse(String.format(message, checkBoxName), this.getCheckBoxValue(checkBoxName));
        System.out.printf("[ИНФОРМАЦИЯ]: флажок: [%s] находится в не отмеченном состоянии.%n",
                checkBoxName);
        return this;
    }

    /**
     * Делает щелчок мышью по указанной кнопке.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.scrollToCenterAndclickInElementJS(By.xpath(buttonNames.find(buttonName)));
        this.logPressedButtonName(buttonName);
        TimeUnit.SECONDS.sleep(3);
    }

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
     * Устанавливает значение поля [Тип значения].
     *
     * @param valueType тип значения дополнительного поля
     */
    public void setValueType(String valueType) throws Exception {
        SelenideElement collapsedList = $(this.getBy(VALUE_TYPE_LIST_CLOSED_XPATH));
        SelenideElement desiredValue = $(this.getBy(String.format(VALUE_TYPE_IN_OPENED_LIST_BY_NAME_XPATH, valueType)));
        SelenideElement selectedValue = $(this.getBy(CURRENT_VALUE_TYPE_XPATH));
        this.waitForListOpensAndSelectDesiredValue("Тип значения", collapsedList, desiredValue, selectedValue);
    }

    /**
     * Установливает значение поля [Способ закупки].
     *
     * @param purchaseMethod способ закупки, в котором будет отображатся дополнительное поле
     */
    public void setPurchaseMethod(String purchaseMethod) throws Exception {
        $(this.getBy(PURCHASE_METHOD_LIST_OPEN_VALUES_XPATH)).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        $(this.getBy(String.format(PURCHASE_METHOD_LIST_DESIRED_VALUE_XPATH, purchaseMethod))).
                waitUntil(clickable, timeout, polling).click();
        $(this.getBy(String.format(PURCHASE_METHOD_LIST_SELECTED_VALUE_XPATH, purchaseMethod))).
                waitUntil(clickable, timeout, polling).shouldHave(text(purchaseMethod));
        this.logFilledFieldName("Способ закупки: ", purchaseMethod);
    }

    /**
     * Удаляет дополнительное поле по имени.
     *
     * @param fieldName имя дополнительного поля
     */
    public void deleteAdditionalFieldByName(String fieldName) throws Exception {
        $(this.getBy(String.format(ADDITIONAL_FIELD_XPATH, fieldName))).waitUntil(clickable, timeout, polling).click();
        $(this.getBy(IS_ACTIVE_CHECKBOX_ID)).waitUntil(clickable, timeout, polling).click();
        $(this.getBy(SAVE_BUTTON_XPATH)).waitUntil(clickable, timeout, polling).click();
        $(this.getBy(POPUP_OK_BUTTON_XPATH)).waitUntil(clickable, timeout, polling).click();
        this.waitForPageLoad();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено удаление дополнительного поля [%s].%n", fieldName);
    }

    /**
     * Удаляет дополнительное поле если оно отображается на странице.
     *
     * @param fieldName имя дополнительного поля
     */
    public void deleteAdditionalFieldIfDisplayed(String fieldName) throws Exception {
        SelenideElement additionalField = $(this.getBy(String.format(ADDITIONAL_FIELD_XPATH, fieldName)));

        if (additionalField.isDisplayed()) {
            this.deleteAdditionalFieldByName(fieldName);
        } else {
            System.out.printf("[ИНФОРМАЦИЯ]: дополнительное поле [%s] не отображается на странице.%n", fieldName);
        }
    }
}