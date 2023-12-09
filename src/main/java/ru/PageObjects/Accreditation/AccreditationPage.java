package ru.PageObjects.Accreditation;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы со страницей [Аккредитация] ( формирование черновика заявки на аккредитацию )
 * ( .kontur.ru/supplier/lk/Accreditation/Request.aspx ).
 * Created by Evgeniy Glushko on 15.04.2016.
 * Updated by Vladimir V. Klochkov on 07.04.2021.
 */
public class AccreditationPage extends CommonPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Раздел [Роли организации в закупках]

    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Аккредитация в качестве заказчика]
    private static final String CUSTOMER_ACCREDITATION_CHECK_BOX_ID = "BaseMainContent_MainContent_cbIsCustomer";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Аккредитация в качестве поставщика]
    private static final String SUPPLIER_ACCREDITATION_CHECK_BOX_ID = "BaseMainContent_MainContent_cbIsSupplier";
    //------------------------------------------------------------------------------------------------------------------
    // Поле с информацией по ускоренной аккредитации для поставщика
    private static final String SUPPLIER_BOOSTED_ACCREDITATION_INFO_ID =
            "BaseMainContent_MainContent_fsBoostedAccreditationInfo";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Информация о лице, подписавшем заявку]

    //==================================================================================================================
    // Поле [Номер телефона]
    //==================================================================================================================
    // Подполе [Код]
    private static final String CONTACT_PHONE_CITY_CODE_ID =
            "BaseMainContent_MainContent_ucEmployeeInfos_ucContactPhone_txtCityCode";
    //------------------------------------------------------------------------------------------------------------------
    // Подполе [Номер телефона]
    private static final String CONTACT_PHONE_NUMBER_ID =
            "BaseMainContent_MainContent_ucEmployeeInfos_ucContactPhone_txtNumber";
    //------------------------------------------------------------------------------------------------------------------
    // Подполе [Добавочный номер]
    private static final String CONTACT_ADDITIONAL_PHONE_NUMBER_ID =
            "BaseMainContent_MainContent_ucEmployeeInfos_ucContactPhone_txtAddNumber";
    //==================================================================================================================
    // Поле [Адрес электронной почты]
    private static final String CONTACT_EMAIL_ID = "BaseMainContent_MainContent_ucEmployeeInfos_txtContactEmail";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Имя пользователя (логин)]
    private static final String USER_LOGIN_ID = "BaseMainContent_MainContent_ucEmployeeInfos_txtLogin";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Пароль]
    private static final String PASSWORD_ID = "BaseMainContent_MainContent_ucEmployeeInfos_txtPassword";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Подтверждение пароля]
    private static final String CONFIRM_PASSWORD_ID = "BaseMainContent_MainContent_ucEmployeeInfos_txtConfirmPassword";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Кодовое слово]
    private static final String CODEWORD_ID = "BaseMainContent_MainContent_ucEmployeeInfos_txtPassphrase";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Заявитель]

    //------------------------------------------------------------------------------------------------------------------
    // Радиокнопка [Индивидуальный предприниматель]
    private static final String SELECT_ENTREPRENEUR_LEGAL_TYPE =
            "//input[@id='BaseMainContent_MainContent_ucOrganizationInfo_rblOrganizationType_1']";
    //------------------------------------------------------------------------------------------------------------------
    // Радиокнопка [Индивидуальный предприниматель]
    private static final String SELECT_INDIVIDUAL_PROFILE_TYPE =
            "//input[@id='BaseMainContent_MainContent_ucOrganizationInfo_rblOrganizationType_2']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [ИНН]
    private static final String INN_FIELD = "BaseMainContent_MainContent_ucOrganizationInfo_txtInn";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [КПП]
    private static final String KPP_FIELD = "BaseMainContent_MainContent_ucOrganizationInfo_txtKpp";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [ОГРН]
    private static final String OGRN_FIELD = "BaseMainContent_MainContent_ucOrganizationInfo_txtOgrn";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [ОГРНИП]
    private static final String OGRN_IP_FIELD = "BaseMainContent_MainContent_ucOrganizationInfo_txtOgrnIp";
    //==================================================================================================================
    // Поле [Номер телефона]
    //==================================================================================================================
    // Поле [Код]
    private static final String APPLICANT_PHONE_CITY_CODE_FIELD =
            "//input[@id='BaseMainContent_MainContent_ucOrganizationInfo_ucContactPhone_txtCityCode']";
    //-----------------------------------------------------------------------------------------------------------------
    // Поле [Номер телефона]
    private static final String APPLICANT_PHONE_NUMBER_FIELD_XPATH =
            "//input[@id='BaseMainContent_MainContent_ucOrganizationInfo_ucContactPhone_txtNumber']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Добавочный номер]
    private static final String APPLICANT_ADD_PHONE_NUMBER_FIELD_XPATH =
            "//input[@id='BaseMainContent_MainContent_ucOrganizationInfo_ucContactPhone_txtAddNumber']";
    //==================================================================================================================\
    // Поле [Основной е-mail для уведомлений]
    private static final String APPLICANT_EMAIL_FIELD_XPATH =
            "//input[@id='BaseMainContent_MainContent_ucOrganizationInfo_txtNotificationEmail']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [ФИО руководителя организации]
    private static final String DIRECTOR_NAME_FIELD_XPATH =
            "//input[@id='BaseMainContent_MainContent_ucOrganizationInfo_txtDirectorName']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Контактное лицо]
    private static final String CONTACT_PERSON_FIELD =
            "//input[@id='BaseMainContent_MainContent_ucOrganizationInfo_txtContactPerson']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Место жительства]

    //------------------------------------------------------------------------------------------------------------------
    // Список [Регион]
    private static final String REGION_LIST_ID = "BaseMainContent_MainContent_kaJudicial_ddlKladrRegion_chzn_o_34";
    //------------------------------------------------------------------------------------------------------------------
    private static final String REGION_SELECTOR_LABEL_XPATH =
            "//div[@id='BaseMainContent_MainContent_kaJudicial_ddlKladrRegion_chzn']//a";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Район]
    private static final String DISTRICT_LIST = "BaseMainContent_MainContent_kaJudicial_ddlKladrDistricts_chzn_o_17";
    private static final String DISTRICT_SELECTOR_LABEL =
            "//div[@id='BaseMainContent_MainContent_kaJudicial_ddlKladrDistricts_chzn']//a";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Город]
    private static final String CITY_LIST = "BaseMainContent_MainContent_kaJudicial_ddlKladrCity_chzn_o_2";
    private static final String CITY_SELECTOR_LABEL =
            "//div[@id='BaseMainContent_MainContent_kaJudicial_ddlKladrCity_chzn']//a";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Населённый пункт]
    private static final String LOCALITY_LIST =
            "BaseMainContent_MainContent_kaJudicial_ddlKladrPopulatedLocality_chzn_o_1";
    private static final String LOCALITY_SELECTOR_LABEL =
            "//div[@id='BaseMainContent_MainContent_kaJudicial_ddlKladrPopulatedLocality_chzn']//a";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Улица]
    private static final String STREET_FIELD = "//input[@id='BaseMainContent_MainContent_kaJudicial_txtKladrStreet']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Почтовый индекс]
    private static final String POST_INDEX_FIELD =
            "//input[@id='BaseMainContent_MainContent_kaJudicial_txtPostIndex']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дом/строение/корпус]
    private static final String BUILDING_FIELD = "//input[@id='BaseMainContent_MainContent_kaJudicial_txtHome']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Почтовый адрес]

    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Совпадает с юридическим адресом]
    private static final String EQUALS_JUDICIAL_ADDRESS_CHECKBOX_ID =
            "BaseMainContent_MainContent_kaPost_cbEqualsJudicalAddress";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о видах экономической деятельности]

    //==================================================================================================================
    // Поле [Номенклатура (ОКВЭД2)] и все, что с ним связано
    //==================================================================================================================
    // Кнопка [Выбрать] на основной форме
    private static final String OKVED2_SELECT_BUTTON_ID =
            "BaseMainContent_MainContent_ucOkved2ListInfo_OrganizationOkved2Field_btnSelect";
    //------------------------------------------------------------------------------------------------------------------
    // Окно диалога [выбор ОКВЭД2]
    private static final String OKVED2_SELECTION_DIALOG_WINDOW_ID =
            "BaseMainContent_MainContent_ucOkved2ListInfo_OrganizationOkved2Field_dialog";
    //------------------------------------------------------------------------------------------------------------------
    // Первый флажок в дереве выбора (окно диалога)
    private static final String FIRST_CHECKBOX_IN_OKVED2_DICTIONARY_XPATH =
            "//*[@id='BaseMainContent_MainContent_ucOkved2ListInfo_OrganizationOkved2Field_okdpTree']" +
                    "/li[1]/a/table/tbody/tr/td[2]/input";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Выбрать] в окне диалога
    private static final String OKVED2_SELECT_FROM_DICTIONARY_BUTTON_XPATH =
            "//div[@class='ui-dialog-buttonset']/button[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле, отображающее расшифровку выбранного элемента на основной форме
    private static final String SELECTED_OKVED2_XPATH =
            "//*[@id='BaseMainContent_MainContent_ucOkved2ListInfo_OrganizationOkved2Field_ulOkdpItemsList']/li";
    //==================================================================================================================

    // endregion

    // region Раздел [Паспортные данные заявителя]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Серия]
    private static final String PASSPORT_SERIAL_FIELD_ID =
            "BaseMainContent_MainContent_ucEmployeePassportInfo_ucEmployeePassportInfo_txtEmployeePassportSeries";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер]
    private static final String PASSPORT_NUMBER_FIELD_ID =
            "BaseMainContent_MainContent_ucEmployeePassportInfo_ucEmployeePassportInfo_txtEmployeePassportNumber";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Код подразделения]
    private static final String PASSPORT_ISSUER_DIVISION_COD_FIELD_ID =
            "BaseMainContent_MainContent_ucEmployeePassportInfo_ucEmployeePassportInfo_txtEmployeePassportIssuedDivisionCode";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Когда выдан]
    private static final String PASSPORT_ISSUE_DATE_FIELD_ID =
            "BaseMainContent_MainContent_ucEmployeePassportInfo_ucEmployeePassportInfo_txtEmployeeWhenPassportIssued";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Кем выдан]
    private static final String PASSPORT_ISSUE_DIVISION_FIELD_ID =
            "BaseMainContent_MainContent_ucEmployeePassportInfo_ucEmployeePassportInfo_txtEmployeeWhomPassportIssued";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Документы заявителя]

    // region [Документы заявителя] для заказчика / поставщика ЮЛ

    //==================================================================================================================
    // Табы [Документы заявителя] для заказчика / поставщика ЮЛ
    //==================================================================================================================
    // Вкладка [(0/0) Учредительные документы] для заказчика ЮЛ
    private static final String FOUNDATION_DOCUMENTS_TAB_XPATH = "//a[contains(., '(0/0) Учредительные документы*')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить (Копия учредительных документов)] для заказчика ЮЛ
    private static final String FOUNDATION_DOCUMENTS_UPLOAD_BUTTON_XPATH =
            "//a[@id='BaseMainContent_MainContent_dlOrganizationDocumentList_divContent_button_3']//input[@name='file']";
    //------------------------------------------------------------------------------------------------------------------
    // Вкладка [(0/0) Полномочия Руководителя] для заказчика ЮЛ
    private static final String DIRECTOR_AUTHORITY_DOCUMENTS_TAB_XPATH = "//a[contains(., '(0/0) Полномочия Руководителя*')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить (Копии документов, подтверждающих полномочия руководителя)] для заказчика ЮЛ
    private static final String DIRECTOR_AUTHORITY_DOCUMENTS_UPLOAD_BUTTON_XPATH =
            "//a[@id='BaseMainContent_MainContent_dlOrganizationDocumentList_divContent_button_4']//input[@name='file']";
    //------------------------------------------------------------------------------------------------------------------
    // Вкладка [(0/0) Выписка ЕГРЮЛ] для заказчика ЮЛ
    private static final String EGRJL_DOCUMENTS_TAB_XPATH = "//a[contains(., '(0/0) Выписка ЕГРЮЛ*')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить (Копия выписки из Единого государственного реестра юридических лиц)] для заказчика ЮЛ
    private static final String EGRJL_DOCUMENTS_UPLOAD_BUTTON_XPATH =
            "//a[@id='BaseMainContent_MainContent_dlOrganizationDocumentList_divContent_button_2']//input[@name='file']";
    //------------------------------------------------------------------------------------------------------------------
    // Вкладка [(0/0) Полномочия на получение аккредитации] для заказчика ЮЛ
    private static final String ACCREDITATION_REQUEST_AUTHORITY_DOCUMENTS_TAB_XPATH =
            "//a[contains(., '(0/0) Полномочия на получение аккредитации*')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить (Копии документов, подтверждающие полномочия лица на получение аккредитации от имени заказчика
    // закупки)] для заказчика ЮЛ
    private static final String ACCREDITATION_REQUEST_AUTHORITY_UPLOAD_BUTTON_XPATH =
            "//a[@id='BaseMainContent_MainContent_dlOrganizationDocumentList_divContent_button_30']//input[@name='file']";
    //------------------------------------------------------------------------------------------------------------------
    // Вкладка [(0/0) Доверенность] для заказчика ЮЛ
    private static final String ATTORNEY_LETTER_DOCUMENTS_TAB_XPATH = "//a[contains(., '(0/0) Доверенность*')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить (Доверенность на осуществление действий от имени заказчика закупки)] для заказчика ЮЛ
    private static final String ATTORNEY_LETTER_UPLOAD_BUTTON_XPATH =
            "//a[@id='BaseMainContent_MainContent_dlOrganizationDocumentList_divContent_button_38']//input[@name='file']";
    //==================================================================================================================

    // endregion

    //region [Документы заявителя] для поставщика ИП

    //==================================================================================================================
    // Табы [Документы заявителя] для поставщика ИП
    //==================================================================================================================
    // Вкладка [(0/0) Документ, удостоверяющий личность] для поставщика ИП
    private static final String IDENTIFY_DOCUMENTS_TAB_XPATH = "//a[contains(., '(0/0) Документ, удостоверяющий личность*')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить (Копия документа, удостоверяющего личность)] для поставщика ИП
    private static final String IDENTIFY_DOCUMENTS_UPLOAD_BUTTON_XPATH =
            "//a[@id='BaseMainContent_MainContent_dlOrganizationDocumentList_divContent_button_35']//input[@name='file']";
    //------------------------------------------------------------------------------------------------------------------
    // Вкладка [(0/0) Выписка из ЕГРИП] для поставщика ИП
    private static final String EGRIP_DOCUMENTS_TAB_XPATH = "//a[contains(., '(0/0) Выписка из ЕГРИП*')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить (Копия выписки из Единого государственного реестра индивидуальных предпринимателей)] для поставщика ИП
    private static final String EGRIP_DOCUMENTS_UPLOAD_BUTTON_XPATH =
            "//a[@id='BaseMainContent_MainContent_dlOrganizationDocumentList_divContent_button_1']//input[@name='file']";
    //------------------------------------------------------------------------------------------------------------------
    // Вкладка [(0/0) Полномочия пользователя] для поставщика ИП
    private static final String AUTHORITY_DOCUMENTS_TAB_XPATH = "//a[contains(., '(0/0) Полномочия пользователя*')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить (Доверенность на осуществление действий от имени участника закупки)] для поставщика ИП
    private static final String AUTHORITY_DOCUMENTS_UPLOAD_BUTTON_XPATH =
            "//a[@id='BaseMainContent_MainContent_dlOrganizationDocumentList_divContent_button_39']//input[@name='file']";
    //==================================================================================================================

    //endregion

    //region [Документы заявителя] для поставщика ФЛ

    //==================================================================================================================
    // Табы [Документы заявителя] для поставщика ФЛ
    //==================================================================================================================
    // Вкладка [(0/0) Документ, удостоверяющий личность] для поставщика ФЛ
    private static final String INDIVIDUAL_PROFILE_DOCUMENTS_TAB_XPATH =
            "//a[contains(., '(0/0) Документ, удостоверяющий личность*')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить (Копия документа, удостоверяющего личность)] для поставщика ФЛ
    private static final String INDIVIDUAL_PROFILE_DOCUMENTS_UPLOAD_BUTTON_XPATH =
            "//a[@id='BaseMainContent_MainContent_dlOrganizationDocumentList_divContent_button_36']//input[@name='file']";
    //==================================================================================================================

    //endregion

    // endregion

    // region Раздел [Банковские реквизиты]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [БИК]
    private static final String BIK_FIELD = "//input[@id='BaseMainContent_MainContent_ucBankingDetailsControl_txtBik']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Расчётный счёт]
    private static final String BANK_ACCOUNT_NUMBER_FIELD =
            "//input[@id='BaseMainContent_MainContent_ucBankingDetailsControl_txtSettlementAccount']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Название банка]
    private static final String BANK_NAME_FIELD =
            "//input[@id='BaseMainContent_MainContent_ucBankingDetailsControl_txtBankName']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле: [Адрес банка]
    private static final String BANK_ADRESS_FIELD =
            "//input[@id='BaseMainContent_MainContent_ucBankingDetailsControl_txtBankAddress']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Ускоренная аккредитация]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Ускоренная аккредитация]
    private static final String BOOSTED_ACCREDITATION_HEADER_XPATH = "//h2[contains(.,'Ускоренная аккредитация')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле с информацией по оплате ускоренной аккредитации
    private static final String BOOSTED_ACCREDITATION_INFO_XPATH =
            "//span[contains(.,'При заказе услуги «Ускоренная аккредитация»')]";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Заказать услуги ускоренной аккредитации]
    private static final String BOOSTED_ACCREDITATION_CHECKBOX_ID =
            "BaseMainContent_MainContent_ucBoostedAccreditation_chkBoostedAccreditation";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Заявка на аккредитацию]

    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Заявление на аккредитацию]
    private static final String AGREED_TO_CONTRACT_CHECKBOX_ID =
            "BaseMainContent_MainContent_uckonturRequestInfo_cbIsContractAgreed";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    // region Раздел [Защита от спама]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Ключ защиты]
    private static final String CAPTCHA_FIELD_ID = "BaseMainContent_MainContent_txtCaptcha";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Блок кнопок внизу формы ([Подать заявку на аккредитацию])

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Подать заявку на аккредитацию]
    private static final String SUBMIT_REQUEST_BUTTON_ID = "BaseMainContent_MainContent_mvgbAccreditation";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    // region Диалоговое окно [Аккредитация]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок диалогового окна [Аккредитация]
    private static final String ACCREDITATION_DIALOG_WINDOW_TITLE_XPATH =
            "//div[contains(@class, 'ui-dialog-titlebar')]/span[contains(., 'Аккредитация')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Ускоренная аккредитация]
    private static final String FAST_ACCREDITATION_BUTTON_XPATH =
            "//div[@class='ui-dialog-buttonset']/button[contains(., 'Ускоренная аккредитация')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Прочее

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование] на закладке [Карточка организации]
    private static final String ACCREDITATION_NAME_LABEL_ID = "BaseMainContent_MainContent_lblOrganizationName";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary checkBoxNames = new Dictionary();    // все флажки на странице
    private final Dictionary fieldNames = new Dictionary();       // все поля на странице
    private final Dictionary radioButtonNames = new Dictionary(); // все переключатели на странице

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public AccreditationPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        checkBoxNames.add("Аккредитация в качестве заказчика", CUSTOMER_ACCREDITATION_CHECK_BOX_ID);
        checkBoxNames.add("Аккредитация в качестве поставщика", SUPPLIER_ACCREDITATION_CHECK_BOX_ID);
        checkBoxNames.add("Совпадает с юридическим адресом", EQUALS_JUDICIAL_ADDRESS_CHECKBOX_ID);
        checkBoxNames.add("Заказать услугу ускоренной аккредитации", BOOSTED_ACCREDITATION_CHECKBOX_ID);
        checkBoxNames.add("Заявление на аккредитацию", AGREED_TO_CONTRACT_CHECKBOX_ID);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Поле с информацией по ускоренной аккредитации для поставщика",
                SUPPLIER_BOOSTED_ACCREDITATION_INFO_ID);
        fieldNames.add("Адрес электронной почты", CONTACT_EMAIL_ID);
        fieldNames.add("Имя пользователя (логин)", USER_LOGIN_ID);
        fieldNames.add("Пароль", PASSWORD_ID);
        fieldNames.add("Подтверждение пароля", CONFIRM_PASSWORD_ID);
        fieldNames.add("Кодовое слово", CODEWORD_ID);
        fieldNames.add("Основной е-mail для уведомлений", APPLICANT_EMAIL_FIELD_XPATH);
        fieldNames.add("Серия", PASSPORT_SERIAL_FIELD_ID);
        fieldNames.add("Номер", PASSPORT_NUMBER_FIELD_ID);
        fieldNames.add("Код подразделения", PASSPORT_ISSUER_DIVISION_COD_FIELD_ID);
        fieldNames.add("Когда выдан", PASSPORT_ISSUE_DATE_FIELD_ID);
        fieldNames.add("Кем выдан", PASSPORT_ISSUE_DIVISION_FIELD_ID);
        fieldNames.add("Заголовок раздела Ускоренная аккредитация", BOOSTED_ACCREDITATION_HEADER_XPATH);
        fieldNames.add("Поле с информацией по оплате ускоренной аккредитации", BOOSTED_ACCREDITATION_INFO_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        radioButtonNames.add("Индивидуальный предприниматель", SELECT_ENTREPRENEUR_LEGAL_TYPE);
        radioButtonNames.add("Физическое лицо", SELECT_INDIVIDUAL_PROFILE_TYPE);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    //region Раздел [Информация о лице, подписавшем заявку]

    /**
     * Заполняет поле [Номер телефона] в разделе [Информация о лице, подписавшем заявку].
     */
    public void setPhoneNumberFieldUserPersonalData() {
        $(this.getBy(CONTACT_PHONE_CITY_CODE_ID)).waitUntil(visible, timeout, polling).
                sendKeys(config.getConfigParameter("UserPhoneCityCode"));
        $(this.getBy(CONTACT_PHONE_NUMBER_ID)).waitUntil(visible, timeout, polling).
                sendKeys(config.getConfigParameter("UserPhoneNumber"));
        $(this.getBy(CONTACT_ADDITIONAL_PHONE_NUMBER_ID)).waitUntil(visible, timeout, polling).
                sendKeys(config.getConfigParameter("UserAdditionalPhoneNumber"));
    }

    // endregion

    //region  Раздел [Заявитель]

    /**
     * Сохраняет значение поля [ИНН].
     */
    public void getInnValue() {
        String innValue = $(this.getBy(INN_FIELD)).getAttribute("value");
        System.out.printf("[-] Полученный ИНН: [%s]%n", innValue);
        config.setParameter("AccreditationInnValue", innValue);
    }

    /**
     * Сохраняет значение поля [КПП].
     */
    public void getKppValue() {
        String kppValue = $(this.getBy(KPP_FIELD)).getAttribute("value");
        System.out.printf("[-] Полученный КПП: [%s]%n", kppValue);
        config.setParameter("AccreditationKppValue", kppValue);
    }

    /**
     * Сохраняет значение поля [ОГРН].
     */
    public void getOgrnValue() {
        String ogrnValue = $(this.getBy(OGRN_FIELD)).getAttribute("value");
        System.out.printf("[-] Полученный ОГРН: [%s]%n", ogrnValue);
        config.setParameter("AccreditationOgrnValue", ogrnValue);
    }

    /**
     * Сохраняет значение поля [ОГРНИП].
     */
    public void getOgrnIpValue() {
        String ogrnIpValue = $(this.getBy(OGRN_IP_FIELD)).getAttribute("value");
        System.out.printf("[-] Полученный ОГРНИП: [%s]%n", ogrnIpValue);
        config.setParameter("AccreditationOgrnIpValue", ogrnIpValue);
    }

    /**
     * Устанавливает значение поля [Номер телефона].
     */
    public void setPhoneNumberFieldApplicant() {
        $(this.getBy(APPLICANT_PHONE_CITY_CODE_FIELD)).waitUntil(visible, timeout, polling).
                sendKeys(config.getConfigParameter("UserPhoneCityCode"));
        $(this.getBy(APPLICANT_PHONE_NUMBER_FIELD_XPATH)).waitUntil(visible, timeout, polling).
                sendKeys(config.getConfigParameter("UserPhoneNumber"));
        $(this.getBy(APPLICANT_ADD_PHONE_NUMBER_FIELD_XPATH)).waitUntil(visible, timeout, polling).
                sendKeys(config.getConfigParameter("UserAdditionalPhoneNumber"));
        System.out.println("[ИНФОРМАЦИЯ]: произведено заполнения поля: [Номер телефона] в секции [Заявитель] значением "
                + config.getConfigParameter("UserPhoneCityCode")
                + " " + config.getConfigParameter("UserPhoneNumber")
                + " " + config.getConfigParameter("UserAdditionalPhoneNumber"));
    }

    /**
     * Заполняет поле [ФИО руководителя организации].
     */
    public void setOrganizationDirectorField() {
        String organizationContactPerson = config.getConfigParameter("OrganizationDirectorName");
        System.out.printf("[ИНФОРМАЦИЯ]: производит заполнение поля [Контактное лицо] значением [%s].%n",
                organizationContactPerson);
        $(this.getBy(DIRECTOR_NAME_FIELD_XPATH)).waitUntil(visible, timeout, polling)
                .sendKeys(organizationContactPerson);
    }

    /**
     * Заполняет поле [Контактное лицо].
     */
    public void setContactPersonField() {
        String organizationContactPerson = config.getConfigParameter("OrganizationContactPerson");
        System.out.printf("[ИНФОРМАЦИЯ]: производит заполнение поля [Контактное лицо] значением [%s].%n",
                organizationContactPerson);
        $(this.getBy(CONTACT_PERSON_FIELD)).waitUntil(visible, timeout, polling)
                .sendKeys(organizationContactPerson);
    }

    //endregion

    //region Раздел [Место жительства]

    /**
     * Устанавливает значение в списке [Регион].
     */
    public void setRegionList() throws Exception {
        $(this.getBy(REGION_SELECTOR_LABEL_XPATH)).waitUntil(clickable, timeout, polling).click();
        $(this.getBy(REGION_LIST_ID)).waitUntil(clickable, timeout, polling).click();
        this.waitForPageLoad();
    }

    /**
     * Устанавливает значение в списке [Район].
     */
    public void setDistrictList() throws Exception {
        $(this.getBy(DISTRICT_SELECTOR_LABEL)).waitUntil(clickable, timeout, polling).click();
        $(this.getBy(DISTRICT_LIST)).waitUntil(clickable, timeout, polling).click();
        this.waitForPageLoad();
    }

    /**
     * Устанавливает значение в списке [Город].
     */
    public void setCityList() throws Exception {
        $(this.getBy(CITY_SELECTOR_LABEL)).waitUntil(clickable, timeout, polling).click();
        $(this.getBy(CITY_LIST)).waitUntil(clickable, timeout, polling).click();
        this.waitForPageLoad();
    }

    /**
     * Устанавливает значение в списке [Населённый пункт].
     */
    public void setLocalityList() throws Exception {
        $(this.getBy(LOCALITY_SELECTOR_LABEL)).waitUntil(clickable, timeout, polling).click();
        $(this.getBy(LOCALITY_LIST)).waitUntil(clickable, timeout, polling).click();
        this.waitForPageLoad();
    }

    /**
     * Устанавливает значение в списке [Улица].
     */
    public void setStreetField() throws Exception {
        this.waitForPageLoad();
        $(this.getBy(STREET_FIELD)).sendKeys(config.getConfigParameter("OrganizationStreet"));
        this.waitForPageLoad();
        $(this.getBy(STREET_FIELD)).click();
        this.waitForPageLoad();
        $(this.getBy(STREET_FIELD)).sendKeys(Keys.ARROW_DOWN);
    }

    /**
     * Устанавливает значение в списке [Почтовый индекс].
     */
    public void setPostIndexField() throws Exception {
        this.waitForPageLoad();
        $(this.getBy(POST_INDEX_FIELD)).sendKeys(config.getConfigParameter("OrganizationPostIndex"));
        this.waitForPageLoad();
        $(this.getBy(POST_INDEX_FIELD)).sendKeys(Keys.ARROW_DOWN);
        this.waitForPageLoad();
        $(this.getBy(POST_INDEX_FIELD)).sendKeys(config.getConfigParameter("OrganizationPostIndex"));
    }

    /**
     * Устанавливает значение в списке [Дом/строение/корпус].
     */
    public void setBuildingField() {
        $(this.getBy(BUILDING_FIELD)).sendKeys(config.getConfigParameter("OrganizationBuilding"));
    }

    //endregion

    // region Раздел [Сведения о видах экономической деятельности]

    /**
     * Устанавливает значение в поле [Номенклатура (ОКВЭД2)].
     */
    public void setOrganizationOkved2Field() throws Exception {
        if ($(this.getBy(OKVED2_SELECT_BUTTON_ID)).isDisplayed()) {
            $(this.getBy(OKVED2_SELECT_BUTTON_ID)).waitUntil(visible, timeout).scrollTo();
            TimeUnit.SECONDS.sleep(3);
            $(this.getBy(OKVED2_SELECT_BUTTON_ID)).click();
            TimeUnit.SECONDS.sleep(3);
            this.waitForPageLoad();
            $(this.getBy(OKVED2_SELECTION_DIALOG_WINDOW_ID)).waitUntil(visible, timeout, polling);
            $(this.getBy(FIRST_CHECKBOX_IN_OKVED2_DICTIONARY_XPATH)).waitUntil(clickable, timeout, polling).click();
            TimeUnit.SECONDS.sleep(3);
            this.waitForPageLoad();
            $(this.getBy(OKVED2_SELECT_FROM_DICTIONARY_BUTTON_XPATH)).waitUntil(clickable, timeout, polling).click();
            TimeUnit.SECONDS.sleep(5);
            this.waitForPageLoad();
            $(this.getBy(SELECTED_OKVED2_XPATH)).waitUntil(visible, timeout, polling);
        }
    }

    //endregion

    // region Раздел [Паспортные данные заявителя]

    /**
     * Заполняет поле [Код подразделения] в форме первичной аккредитации Пользователя.
     */
    public void setPasswordIssuerDivisionCod() throws Throwable {
        this.scrollToCenter(this.getBy(PASSPORT_ISSUER_DIVISION_COD_FIELD_ID));
        this.waitClearClickAndSendKeys(
                this.getBy(PASSPORT_ISSUER_DIVISION_COD_FIELD_ID),
                config.getConfigParameter("UserPassportIssuerDivisionCode"));
    }

    /**
     * Заполняет поле [Когда выдан] в форме первичной аккредитации Пользователя.
     */
    public void setPasswordIssueDate() throws Throwable {
        this.scrollToCenter(this.getBy(PASSPORT_ISSUE_DATE_FIELD_ID));
        this.waitClearClickAndSendKeys(
                this.getBy(PASSPORT_ISSUE_DATE_FIELD_ID), config.getConfigParameter("UserPassportIssueDate"));
    }


    // endregion

    // region Раздел [Документы заявителя]

    /**
     * Загружаем файл в табу [(0/0) Учредительные документы] для заказчика ЮЛ в форме первичной аккредитации
     * Пользователя.
     */
    public void uploadFoundationDocument() throws Exception {
        $(this.getBy(FOUNDATION_DOCUMENTS_TAB_XPATH)).waitUntil(clickable, timeout, polling).click();
        $(this.getBy(FOUNDATION_DOCUMENTS_UPLOAD_BUTTON_XPATH)).sendKeys(config.getAbsolutePathToFoundationDoc());
        this.waitForPageLoad();
    }

    /**
     * Загружаем файл в табу [(0/0) Полномочия Руководителя] для заказчика ЮЛ в форме первичной аккредитации
     * Пользователя.
     */
    public void uploadDirectorAuthorityDocument() throws Exception {
        $(this.getBy(DIRECTOR_AUTHORITY_DOCUMENTS_TAB_XPATH)).waitUntil(clickable, timeout, polling).click();
        $(this.getBy(DIRECTOR_AUTHORITY_DOCUMENTS_UPLOAD_BUTTON_XPATH)).
                sendKeys(config.getAbsolutePathToDirectorAuthorityDoc());
        this.waitForPageLoad();
    }

    /**
     * Загружаем файл в табу [(0/0) Выписка ЕГРЮЛ] для заказчика ЮЛ в форме первичной аккредитации Пользователя.
     */
    public void uploadEGRJLDocument() throws Exception {
        $(this.getBy(EGRJL_DOCUMENTS_TAB_XPATH)).waitUntil(clickable, timeout, polling).click();
        $(this.getBy(EGRJL_DOCUMENTS_UPLOAD_BUTTON_XPATH)).sendKeys(config.getAbsolutePathToEGRJLdoc());
        this.waitForPageLoad();
    }

    /**
     * Загружаем файл в табу [(0/0) Полномочия на получение аккредитации для заказчика ЮЛ в форме первичной аккредитации
     * Пользователя.
     */
    public void uploadAccreditationRequestAuthorityDocument() throws Exception {
        $(this.getBy(ACCREDITATION_REQUEST_AUTHORITY_DOCUMENTS_TAB_XPATH)).waitUntil(clickable, timeout, polling).
                click();
        $(this.getBy(ACCREDITATION_REQUEST_AUTHORITY_UPLOAD_BUTTON_XPATH)).
                sendKeys(config.getAbsolutePathToAccreditationRequestAuthorityDoc());
        this.waitForPageLoad();
    }

    /**
     * Загружаем файл в табу [(0/0) Доверенность] для заказчика ЮЛ в форме первичной аккредитации Пользователя.
     */
    public void uploadAttorneyLetterDocument() throws Exception {
        $(this.getBy(ATTORNEY_LETTER_DOCUMENTS_TAB_XPATH)).waitUntil(clickable, timeout, polling).click();
        $(this.getBy(ATTORNEY_LETTER_UPLOAD_BUTTON_XPATH)).sendKeys(config.getAbsolutePathToAttorneyLetterDoc());
        this.waitForPageLoad();
    }

    /**
     * Загружаем файл в табу [(0/0) Документ, удостоверяющий личность] для поставщика ИП в форме первичной аккредитации
     * Пользователя.
     */
    public void uploadIdentityDocumentForEntrepreneur() throws Exception {
        $(this.getBy(IDENTIFY_DOCUMENTS_TAB_XPATH)).waitUntil(clickable, timeout, polling).click();
        $(this.getBy(IDENTIFY_DOCUMENTS_UPLOAD_BUTTON_XPATH)).sendKeys(config.getAbsolutePathToUserIdDoc());
        this.waitForPageLoad();
    }

    /**
     * Загружаем файл в табу [(0/0) Выписка из ЕГРИП] для поставщика ИП в форме первичной аккредитации Пользователя.
     */
    public void uploadEGRIPDocument() throws Exception {
        $(this.getBy(EGRIP_DOCUMENTS_TAB_XPATH)).waitUntil(clickable, timeout, polling).click();
        $(this.getBy(EGRIP_DOCUMENTS_UPLOAD_BUTTON_XPATH)).sendKeys(config.getAbsolutePathToUserEGRIPDoc());
        this.waitForPageLoad();
    }

    /**
     * Загружаем файл в табу [(0/0) Полномочия пользователя] для поставщика ИП в форме первичной аккредитации
     * Пользователя.
     */
    public void uploadUserAuthorityDocument() throws Exception {
        $(this.getBy(AUTHORITY_DOCUMENTS_TAB_XPATH)).waitUntil(clickable, timeout, polling).click();
        $(this.getBy(AUTHORITY_DOCUMENTS_UPLOAD_BUTTON_XPATH)).sendKeys(config.getAbsolutePathToUserAuthorityDoc());
        this.waitForPageLoad();
    }

    /**
     * Загружаем файл в табу [(0/0) Документ, удостоверяющий личность] для поставщика ФЛ в форме первичной аккредитации
     * Пользователя.
     */
    public void uploadIdentityDocumentForIndividualProfile() throws Exception {
        $(this.getBy(INDIVIDUAL_PROFILE_DOCUMENTS_TAB_XPATH)).waitUntil(clickable, timeout, polling).click();
        $(this.getBy(INDIVIDUAL_PROFILE_DOCUMENTS_UPLOAD_BUTTON_XPATH)).
                sendKeys(config.getAbsolutePathToUserAuthorityDoc());
        this.waitForPageLoad();
    }

    //endregion

    // region Раздел [Банковские реквизиты]

    /**
     * Заполняет поле [БИК] в форме первичной аккредитации Пользователя.
     */
    public void setBankBik() {
        this.scrollToCenter(this.getBy(BIK_FIELD));
        setTextFieldWithRetry(this.getBy(BIK_FIELD), config.getConfigParameter("UserBIK"));
    }

    /**
     * Заполняет поле [Расчетный счет] в форме первичной аккредитации Пользователя.
     */
    public void setBankAccountNumber() {
        $(this.getBy(BANK_ACCOUNT_NUMBER_FIELD)).sendKeys(config.getBankAccountNumber());
    }

    /**
     * Заполняет поле [Название банка] в форме первичной аккредитации Пользователя.
     */
    public void setBankName() {
        $(this.getBy(BANK_NAME_FIELD)).sendKeys(config.getConfigParameter("UserBankName"));
    }

    /**
     * Заполняет поле [Адрес банка] в форме первичной аккредитации Пользователя.
     */
    public void setBankAddress() {
        $(this.getBy(BANK_ADRESS_FIELD)).sendKeys(config.getConfigParameter("UserBankAdress"));
    }

    //endregion

    // region Раздел [Защита от спама]

    /**
     * Установит значение поля [Ключ защиты] в форме первичной аккредитации Пользователя.
     */
    public void setCaptchaField() {
        $(this.getBy(CAPTCHA_FIELD_ID)).waitUntil(visible, timeout, polling).
                sendKeys(config.getConfigParameter("Captcha"));
    }

    //endregion

    //region Блок кнопок внизу формы ([Подать заявку на аккредитацию])

    /**
     * Нажимает на кнопку [Подать заявку на аккредитацию] в форме первичной аккредитации Пользователя.
     */
    public void clickOnSubmitRequestButton() throws Exception {
        this.logPressedButtonName("Найти");
        $(this.getBy(SUBMIT_REQUEST_BUTTON_ID)).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage(10);

        if ($(this.getBy(ACCREDITATION_DIALOG_WINDOW_TITLE_XPATH)).isDisplayed()) {
            $(this.getBy(FAST_ACCREDITATION_BUTTON_XPATH)).waitUntil(clickable, timeout, polling).click();
            this.waitLoadingImage(10);
        }
    }

    //endregion

    // region Общие методы для работы с элементами этой страницы

    /**
     * Устанавливает значение поля на форме первичной аккредитации Пользователя.
     *
     * @param fieldName имя поля для заполнения
     * @param value     требуемое значение поля
     */
    public void setField(String fieldName, String value) {
        $(this.getBy(fieldNames.find(fieldName))).waitUntil(visible, timeout, polling).sendKeys(value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнения поля: [%s] значением [%s].%n",
                fieldName, value);
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

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        Assert.assertTrue(message, this.verifyFieldContent(field, value));
    }

    /**
     * Делает щелчок по радиокнопке на форме первичной аккредитации Пользователя.
     *
     * @param radioButtonName имя радиокнопки
     */
    public void clickRadioButtonInRequestAccreditationPage(String radioButtonName) throws Throwable {
        this.scrollToCenterAndclickInElementJS(radioButtonNames.find(radioButtonName));
    }

    /**
     * Получает наименование аккредитации из формы первичной аккредитации Пользователя.
     */
    public void getAccreditationName() throws Exception {
        String accreditationName =
                $(this.getBy(ACCREDITATION_NAME_LABEL_ID)).waitUntil(visible, timeout, polling).getText();
        config.setParameter("AccreditationName", accreditationName);
        System.out.printf("[ИНФОРМАЦИЯ]: наименование аккредитации: [%s].%n", accreditationName);
        TimeUnit.SECONDS.sleep(1);
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
     * Проверяет, что флажок с указанным именем выбран.
     *
     * @param checkBoxName имя флажка
     */
    public void verifyCheckBoxSelected(String checkBoxName) {
        String message = "[ОШИБКА]: флажок [%s] не установлен";
        Assert.assertTrue(String.format(message, checkBoxName), this.getCheckBoxValue(checkBoxName));
        System.out.printf("[ИНФОРМАЦИЯ]: флажок [%s] находится в отмеченном состоянии.%n", checkBoxName);
    }

    /**
     * Проверяет, что флажок с указанным именем не выбран.
     *
     * @param checkBoxName имя флажка
     */
    public void verifyCheckBoxNotSelected(String checkBoxName) {
        Assert.assertFalse(
                String.format("[ОШИБКА]: флажок [%s] установлен", checkBoxName), this.getCheckBoxValue(checkBoxName));
        System.out.printf("[ИНФОРМАЦИЯ]: флажок [%s] находится в не отмеченном состоянии.%n", checkBoxName);
    }

    //endregion
}
