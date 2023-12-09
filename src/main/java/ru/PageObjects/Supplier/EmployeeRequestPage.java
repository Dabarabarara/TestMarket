package ru.PageObjects.Supplier;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс описывающий страницу [Заявка на добавление пользователя] ( .ru/supplier/lk/Accreditation/EmployeeRequest.aspx ).
 * Created by Kirill G. Rydzyvylo on 06.02.2020.
 * Updated by Vladimir V. Klochkov on 11.03.2021.
 */
public class EmployeeRequestPage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Раздел [Пользователь]

    //Заголовок раздела [Пользователь]
    //------------------------------------------------------------------------------------------------------------------
    private static final String USER_BLOCK_XPATH = "//div[@class='block_title']/h2[text()='Пользователь']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Текущий сертификат пользователя]
    //------------------------------------------------------------------------------------------------------------------
    private static final String SELECTED_CERTIFICATE_ID = "BaseMainContent_MainContent_ucSelectCertificate_lblCertInfo";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Выбрать из списка]
    //------------------------------------------------------------------------------------------------------------------
    private static final String SELECT_CERT_BUTTON_ID = "BaseMainContent_MainContent_ucSelectCertificate_btnSelectCert";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Фамилия]
    //------------------------------------------------------------------------------------------------------------------
    private static final String LUST_NAME_ID = "BaseMainContent_MainContent_txtLastName";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Имя]
    //------------------------------------------------------------------------------------------------------------------
    private static final String FIRST_NAME_ID = "BaseMainContent_MainContent_txtFirstName";
    //==================================================================================================================
    // Поле [Номер телефона]
    //==================================================================================================================
    // Подполе [Код]
    private static final String CONTACT_PHONE_CITY_CODE_ID = "BaseMainContent_MainContent_ucContactPhone_txtCityCode";
    //------------------------------------------------------------------------------------------------------------------
    // Подполе [Номер телефона]
    private static final String CONTACT_PHONE_NUMBER_ID = "BaseMainContent_MainContent_ucContactPhone_txtNumber";
    //------------------------------------------------------------------------------------------------------------------
    // Подполе [Добавочный номер]
    private static final String CONTACT_ADDITIONAL_PHONE_NUMBER_ID =
            "BaseMainContent_MainContent_ucContactPhone_txtAddNumber";
    //==================================================================================================================
    // Поле [Адрес электронной почты]
    private static final String CONTACT_EMAIL_ID = "BaseMainContent_MainContent_txtContactEmail";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Адрес электронной почты для уведомлений заказчика]
    private static final String CUSTOMER_CONTACT_EMAIL_ID = "BaseMainContent_MainContent_txtCustomerContactEmail";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Адрес электронной почты для уведомлений поставщика]
    private static final String SUPPLIER_CONTACT_EMAIL_ID = "BaseMainContent_MainContent_txtSupplierContactEmail";
    //------------------------------------------------------------------------------------------------------------------

    // Поле [Имя пользователя (логин)]
    private static final String LOGIN_ID = "BaseMainContent_MainContent_txtLogin";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Пароль]
    private static final String PASSWORD_ID = "BaseMainContent_MainContent_txtPassword";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Подтверждение пароля]
    private static final String CONFIRM_PASSWORD_ID = "BaseMainContent_MainContent_txtConfirmPassword";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Кодовое слово]
    private static final String PASSWORD_PHRASE_ID = "BaseMainContent_MainContent_txtPassphrase";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Раздел [Документы к заявке]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Документы к заявке]
    private static final String APPLICATION_DOCUMENTS_BLOCK_XPATH =
            "//fieldset[@class='no100percenttable']/label[text()='Документы к заявке']";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Тип документа]
    private static final String DOCUMENT_TYP_ID =
            "BaseMainContent_MainContent_ucDocumentUploaderCommon_ddlDocumentType_chzn";
    //------------------------------------------------------------------------------------------------------------------
    // Элемент списка [Доверенность]
    private static final String ATTORNEY_LETTER_XPATH =
            "//li[contains(@class,'active-result') and text()='Доверенность']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить]
    private static final String DOC_UPLOAD_XPATH =
            "//div[@id='BaseMainContent_MainContent_ucDocumentUploaderCommon_btnUpload']/input";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Раздел [Таблица ролей]

    //------------------------------------------------------------------------------------------------------------------
    // Флажок[Опытный пользователь]
    private static final String EXPERIENCE_USER_CHECK_BOX_XPATH =
            "//span[@class='permissions-small-text' and text()='Опытный пользователь']/../../td/input[@type='checkbox']";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Раздел [Защита от спама]

    //------------------------------------------------------------------------------------------------------------------
    // Поле[Ключ защиты]
    private static final String CAPTCHA_FIELD_ID = "BaseMainContent_MainContent_txtCaptcha";
    //-----------------------------------------------------------------------------------------------------------------

    //endregion

    // region Блок управляющих кнопок в нижней части страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отправить]
    private static final String SUBMIT_BUTTON_ID = "BaseMainContent_MainContent_lbtSubmit";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary blockNames = new Dictionary();
    private final Dictionary checkBoxNames = new Dictionary();
    private final Dictionary fieldNames = new Dictionary();
    private final Dictionary buttonNames = new Dictionary();

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     *****************************************************************************************************************
     */
    public EmployeeRequestPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Пользователь", USER_BLOCK_XPATH);
        blockNames.add("Документы к заявке", APPLICATION_DOCUMENTS_BLOCK_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        checkBoxNames.add("Опытный пользователь", EXPERIENCE_USER_CHECK_BOX_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Текущий сертификат пользователя", SELECTED_CERTIFICATE_ID);
        fieldNames.add("Фамилия", LUST_NAME_ID);
        fieldNames.add("Имя", FIRST_NAME_ID);
        fieldNames.add("Код", CONTACT_PHONE_CITY_CODE_ID);
        fieldNames.add("Номер телефона", CONTACT_PHONE_NUMBER_ID);
        fieldNames.add("Добавочный номер", CONTACT_ADDITIONAL_PHONE_NUMBER_ID);
        fieldNames.add("Адрес электронной почты", CONTACT_EMAIL_ID);
        fieldNames.add("Адрес электронной почты для уведомлений заказчика", CUSTOMER_CONTACT_EMAIL_ID);
        fieldNames.add("Адрес электронной почты для уведомлений поставщика", SUPPLIER_CONTACT_EMAIL_ID);
        fieldNames.add("Имя пользователя (логин)", LOGIN_ID);
        fieldNames.add("Пароль", PASSWORD_ID);
        fieldNames.add("Подтверждение пароля", CONFIRM_PASSWORD_ID);
        fieldNames.add("Кодовое слово", PASSWORD_PHRASE_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Выбрать из списка", SELECT_CERT_BUTTON_ID);
        buttonNames.add("Отправить", SUBMIT_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Раздел [Пользователь]

    /**
     * Заполняет поле [Номер телефона].
     */
    public void setPhoneNumberFieldUserPersonalData() {
        $(this.getBy(CONTACT_PHONE_CITY_CODE_ID)).waitUntil(visible, timeout, polling).
                sendKeys(config.getConfigParameter("UserPhoneCityCode"));
        $(this.getBy(CONTACT_PHONE_NUMBER_ID)).waitUntil(visible, timeout, polling).
                sendKeys(config.getConfigParameter("UserPhoneNumber"));
        $(this.getBy(CONTACT_ADDITIONAL_PHONE_NUMBER_ID)).waitUntil(visible, timeout, polling).
                sendKeys(config.getConfigParameter("UserAdditionalPhoneNumber"));
    }

    //endregion

    //region Раздел [Документы к заявке]

    /**
     * Прикрепляет файл Раздел [Документы к заявке].
     */
    public void atachePowerOfAttorney() throws Exception {
        this.scrollToCenter($(this.getBy(APPLICATION_DOCUMENTS_BLOCK_XPATH)));
        $(this.getBy(DOCUMENT_TYP_ID)).waitUntil(visible, timeout, polling).click();
        $(this.getBy(ATTORNEY_LETTER_XPATH)).waitUntil(visible, timeout, polling).click();
        $(this.getBy(DOC_UPLOAD_XPATH)).sendKeys(config.getAbsolutePathToFoundationDoc());
        this.waitForPageLoad();
    }

    //endregion

    // region Раздел [Защита от спама]

    /**
     * Устанавливает значение поля [Ключ защиты].
     */
    public void setCaptchaField() {
        $(this.getBy(CAPTCHA_FIELD_ID)).waitUntil(visible, timeout, polling).
                sendKeys(config.getConfigParameter("Captcha"));
    }

    //endregion

    // region Общие методы для работы с элементами этой страницы

    /**
     * Устанавливает значение поля в форме первичной аккредитации Пользователя.
     *
     * @param fieldName имя поля для заполнения
     * @param value     требуемое значение поля
     */
    public void setField(String fieldName, String value) {
        $(this.getBy(fieldNames.find(fieldName))).waitUntil(visible, timeout, polling).sendKeys(value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнения поля: [%s] значением [%s].%n", fieldName, value);
    }

    /**
     * Делает щелчок мышью по указанной кнопке.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие кнопки [%s].%n", buttonName);
        this.scrollToCenterAndclickInElementJS(this.getBy(buttonNames.find(buttonName)));
        this.waitForPageLoad();
    }

    /**
     * Устанавливает или сбрасывает флажок с указанным именем.
     *
     * @param checkBoxName имя флажка
     */
    public void setCheckBoxValue(String checkBoxName) {
        String message = "[ОШИБКА]: состояние флажка [%s] не изменилось после щелчка мышью";

        boolean oldValue = this.getCheckBoxValue(checkBoxName);
        $(this.getBy(checkBoxNames.find(checkBoxName))).waitUntil(exist, timeout, polling).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено изменение состояния флажка [%s].%n", checkBoxName);
        boolean newValue = this.getCheckBoxValue(checkBoxName);
        System.out.printf("[ИНФОРМАЦИЯ]: флажок [%s] находится %s отмеченном состоянии.%n",
                checkBoxName, ((newValue) ? "в" : "в не"));
        Assert.assertNotEquals(String.format(message, checkBoxName), oldValue, newValue);
    }

    /**
     * Возвращает значение флажка с указанным именем.
     *
     * @param checkBoxName имя флажка
     * @return значение флажка
     */
    private boolean getCheckBoxValue(String checkBoxName) {
        $(this.getBy(checkBoxNames.find(checkBoxName))).waitUntil(exist, timeout, polling).shouldBe(visible);
        return $(this.getBy(checkBoxNames.find(checkBoxName))).isSelected();
    }

    /**
     * Проверяет содержимое текcтового поля.
     *
     * @param fieldName имя поля
     * @param value     содержимое поля
     */
    public void verifyFieldContent(String fieldName, String value) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        verifyFieldContent(this.getBy(fieldNames.find(fieldName)), value);
    }

    // endregion
}
