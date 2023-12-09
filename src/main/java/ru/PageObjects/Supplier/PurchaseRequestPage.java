package ru.PageObjects.Supplier;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonDialogs.CompletedDialog;
import ru.PageObjects.CommonDialogs.ConfirmDialog;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывающий страницу [Заявка на участие в ...] (.kontur.ru/supplier/auction/app/application/...).
 * [Новая редакция заявки, которая в итоге будет использоваться во всех автотестах].
 * Пока используется один класс как для режима редактирования заявки так и для режима просмотра заявки.
 * В будущем надо будет разделить этот класс на два отдельных.
 * Created by Vladimir V. Klochkov on 06.03.2018.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class PurchaseRequestPage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Управляющие кнопки в верхней части страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Внести изменения]
    private static final String EDIT_REQUEST_BUTTON_ID = "BaseMainContent_MainContent_btnEditTop";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отозвать заявку]
    private static final String WITHDRAW_REQUEST_BUTTON_XPATH = "//button[@name='btnWithdraw']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о лоте]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Вид обеспечения заявки]
    private static final String APPLICATION_GUARANTEE_TYPE_XPATH =
            "//label[contains(.,'Вид обеспечения заявки')]/following-sibling::span[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Размер обеспечения заявки на участие]
    private static final String APPLICATION_GUARANTEE_SUM_XPATH =
            "//label[contains(.,'Размер обеспечения заявки на участие')]/following-sibling::span[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Размер лицензионного вознаграждения]
    private static final String LICENSE_COMMISSION_ID = "BaseMainContent_MainContent_fvCommission_lblValue";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Общие сведения]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер заявки]
    private static final String REQUEST_NUMBER_XPATH =
            "//label[contains(.,'Идентификационный номер заявки')]/following-sibling::span[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус]
    private static final String REQUEST_STATUS_XPATH = "//label[contains(.,'Статус')]/following-sibling::span[1]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о товаре]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела формы [Сведения о товаре]
    private static final String AGREEMENT_TITLE_ID = "BaseMainContent_MainContent_hAgreementTitle";
    //------------------------------------------------------------------------------------------------------------------
    // Поле ввода в кнопке [Добавить] для поля [Название документа]
    private static final String AGREEMENT_DOCUMENT_NAME_XPATH =
            "//*[@class='create_btn margin_none input-file-container']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Блок радиокнопок [Настоящим участник процедуры закупки подтверждает свое согласие ...]
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [Согласен]
    private static final String TRADE_AGREEMENT_CONFIRMED_ID =
            "BaseMainContent_MainContent_rblIsTradeAgreementConfirmed_0";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [Не согласен]
    private static final String TRADE_AGREEMENT_UNCONFIRMED_ID =
            "BaseMainContent_MainContent_rblIsTradeAgreementConfirmed_1";
    //------------------------------------------------------------------------------------------------------------------


    // endregion

    // region Раздел [Ценовые/ценовое предложения]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела формы [Ценовое предложение]
    private static final String PRICE_OFFER_HEADER_XPATH = "//h2[contains(., 'Ценовое предложение')]";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела формы [Редактирование ценового предложения]
    private static final String EDIT_OFFER_HEADER_ID = "js_newPriceTitle";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела формы [Новое ценовое предложение]
    private static final String NEW_OFFER_HEADER_ID = "js_newPriceTitle";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить] в разделе формы [Редактирование ценового предложения]
    private static final String ADD_NEW_QUOTATION_BUTTON_ID = "js-newQuotation";
    //------------------------------------------------------------------------------------------------------------------
    // Поле ввода в кнопке [Добавить] для поля [Название документа] в разделе формы [Ценовые предложения]
    private static final String ALTERNATIVE_AGREEMENT_DOCUMENT_NAME_XPATH =
            "//div[@id='BaseMainContent_MainContent_ufAlternativeAgreementFile_btnUpload']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле ввода в кнопке [Добавить] для поля [Сведения о ценовом предложении] в разделе формы [Ценовое предложение]
    private static final String ADD_QUOTATION_INFO_FROM_FILE_BUTTON_INPUT_XPATH =
            "//div[@id='BaseMainContent_MainContent_ufQuotationInfoDoc_btnUpload']/input";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [Редактировать] в разделе формы [Ценовые предложения]
    private static final String EDIT_OFFER_LINK_XPATH = "//a[contains(.,'Редактировать')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Предложение о цене] в разделе формы [Ценовое предложение] (заявка находится в режиме редактирования)
    private static final String PRICE_OFFER_EDIT_XPATH = "//*[@name='txtQuotation']/input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Предложение о цене (договора)] в разделе формы [Ценовое предложение]
    // (заявка находится в режиме редактирования)
    private static final String OFFER_EDIT_ID = "BaseMainContent_MainContent_txtQuotation";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Предложение о цене (договора)] в разделе формы [Ценовое предложение] (заявка находится в режиме просмотра)
    private static final String OFFER_VIEW_ID = "BaseMainContent_MainContent_fvQuotation_lblValue";
    //------------------------------------------------------------------------------------------------------------------
    // Надпись [Заказчик предоставил возможность ввода ценовых предложений на часть позиций.]
    private static final String IS_APPLICATION_ITEM_PARTIAL_OFFER_ALLOWED_ID =
            "BaseMainContent_MainContent_sIsApplicationItemPartialOfferAllowed";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Ставка НДС] в разделе формы [Ценовое предложение] (заявка находится в режиме редактирования)
    private static final String SELECT_VAT_XPATH = "//span[@class='vat__dropdown ng-star-inserted']//label";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Предложение о цене (с НДС)] в разделе формы [Ценовое предложение]
    // (заявка находится в режиме редактирования)
    private static final String PRICE_OFFER_WITH_VAT_XPATH =
            "//label[contains(., 'Предложение о цене (с НДС)')]/../numericcomponent/input";
    //------------------------------------------------------------------------------------------------------------------

    // region Таблица в разделе [Ценовые предложения]

    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Наименование] в таблице [Ценовые предложения] (режим редактирования, режим просмотра)
    private static final String TABLE_PRICE_OFFERS_NAME_COLUMN_XPATH =
            "//table[@id='applicationItemGrid']//td[@aria-describedby='applicationItemGrid_LotItemName']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [ОКВЕД2] в таблице [Ценовые предложения] (режим редактирования, режим просмотра)
    private static final String TABLE_PRICE_OFFERS_OKVED2_COLUMN_XPATH =
            "//table[@id='applicationItemGrid']//td[@aria-describedby='applicationItemGrid_LotItemOkved2']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [ОКПД2] в таблице [Ценовые предложения] (режим редактирования, режим просмотра)
    private static final String TABLE_PRICE_OFFERS_OKPD2_COLUMN_XPATH =
            "//table[@id='applicationItemGrid']//td[@aria-describedby='applicationItemGrid_LotItemOkpd2']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Единица измерения] в таблице [Ценовые предложения] (режим редактирования, режим просмотра)
    private static final String TABLE_PRICE_OFFERS_UNIT_COLUMN_XPATH =
            "//table[@id='applicationItemGrid']//td[@aria-describedby='applicationItemGrid_OkeiName']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Количество] в таблице [Ценовые предложения] (режим редактирования, режим просмотра)
    private static final String TABLE_PRICE_OFFERS_AMOUNT_COLUMN_XPATH =
            "//table[@id='applicationItemGrid']//td[@aria-describedby='applicationItemGrid_LotItemQuantity']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Цена объекта] в таблице [Ценовые предложения] (режим редактирования, режим просмотра)
    private static final String TABLE_PRICE_OFFERS_UNIT_PRICE_COLUMN_XPATH =
            "//table[@id='applicationItemGrid']//td[@aria-describedby='applicationItemGrid_UnitPrice']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Предложение цены] в таблице [Ценовые предложения] (режим редактирования, режим просмотра)
    private static final String TABLE_PRICE_OFFERS_QUOTATION_COLUMN_XPATH =
            "//table[@id='applicationItemGrid']//td[@aria-describedby='applicationItemGrid_Quotation']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Общая сумма] в таблице [Ценовые предложения] (режим редактирования, режим просмотра)
    private static final String TABLE_PRICE_OFFERS_QUOTATION_TOTAL_COLUMN_XPATH =
            "//table[@id='applicationItemGrid']//td[@aria-describedby='applicationItemGrid_QuotationTotal']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Страна происхождения] в таблице [Ценовые предложения] (режим редактирования, режим просмотра)
    private static final String TABLE_PRICE_OFFERS_COUNTRY_CODE_COLUMN_XPATH =
            "//table[@id='applicationItemGrid']//td[@aria-describedby='applicationItemGrid_ManufacturerCountryCode']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Текущий рейтинг] в таблице [Ценовые предложения] (заявка находится в режиме просмотра)
    private static final String TABLE_PRICE_OFFERS_CURRENT_POSITION_COLUMN_XPATH =
            "//table[@id='applicationItemGrid']//td[@aria-describedby='applicationItemGrid_CurrentPosition']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Лучшее ценовое предложение] в таблице [Ценовые предложения] (заявка находится в режиме просмотра)
    private static final String TABLE_PRICE_OFFERS_BEST_BID_PRICE_COLUMN_XPATH =
            "//table[@id='applicationItemGrid']//td[@aria-describedby='applicationItemGrid_BestBidPrice']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Предложение] в таблице [Ценовые предложения] (заявка находится в режиме просмотра)
    private static final String TABLE_PRICE_OFFERS_OFFERS_COLUMN_VIEW_XPATH =
            "//table[@id='BaseMainContent_MainContent_ucQuotations_jqgQuotations']//" +
                    "tbody/tr[@class='ui-widget-content jqgrow ui-row-ltr']/td[3]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Цена] в таблице [Ценовые предложения] (заявка находится в режиме просмотра)
    private static final String TABLE_PRICE_OFFERS_PRICES_COLUMN_VIEW_XPATH =
            "//table[@id='BaseMainContent_MainContent_ucQuotations_jqgQuotations']//" +
                    "tbody/tr[@class='ui-widget-content jqgrow ui-row-ltr']/td[5]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Сохранить] в разделе формы [Редактирование ценового предложения]
    private static final String SAVE_QUOTATION_CHANGES_BUTTON_ID = "js-saveQuotation";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Первая часть заявки участника]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела формы [Первая часть заявки участника]
    private static final String AGREEMENT_1ST_HEADER_ID = "BaseMainContent_MainContent_hAgreementTitle";
    //------------------------------------------------------------------------------------------------------------------
    // Поле ввода в кнопке [Добавить] для поля [Описание поставляемых ТРУ]
    // в разделе формы [Первая часть заявки участника]
    private static final String APPLICATION_DOCUMENTS_BUTTON_INPUT_XPATH =
            "(//fileuploader[@region='Описание поставляемых ТРУ']//" +
                    "label[@class='create_btn margin_none input-file-container']/input) | " +
                    "(//label[contains(.,'Описание поставляемых ТРУ')]/..//input[@type='file'])";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Страна происхождения товара] - область открытия списка по которой нужно будет сделать щелчок мышью
    private static final String COUNTRY_OF_ORIGIN_LIST_OPEN_VALUES_XPATH =
            "//multiselect[@name='manufacturerCountryCode']//select[@data-placeholder='Выберите страну']/../div/ul/li";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Страна происхождения товара] - список значений открыт - значение [Российская федерация]
    private static final String COUNTRY_OF_ORIGIN_LIST_DESIRED_VALUE_XPATH =
            "//multiselect[@name='manufacturerCountryCode']//select[@data-placeholder='Выберите страну']/..//li[contains(., 'Российская Федерация')]";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Страна происхождения товара] - текущее выбранное значение
    private static final String COUNTRY_OF_ORIGIN_LIST_SELECTED_VALUE_XPATH =
            "//multiselect[@name='manufacturerCountryCode']//select[@data-placeholder='Выберите страну']/../div//span";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Вторая часть заявки участника]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела формы [Вторая часть заявки участника]
    private static final String AGREEMENT_2ND_HEADER_XPATH = "//h2[contains(., 'Вторая часть заявки участника')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле ввода в кнопке [Добавить] для поля [Сведения о соответствии квалификационным требованиям]
    // в разделе формы [Вторая часть заявки участника]
    private static final String QUALIFICATION_REQUIMENTS_DOCUMENTS_BUTTON_INPUT_XPATH =
            "//fileuploader[@region='Сведения о соответствии квалификационным требованиям']//" +
                    "label[@class='create_btn margin_none input-file-container']/input";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения об участнике процедуры]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела формы [Сведения об участнике процедуры]
    private static final String PROCEDURE_MEMBERS_INFO_XPATH = "//h2[contains(., 'Сведения об участнике процедуры')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Заполнить] для поля [Номер контактного телефона]
    private static final String FILL_CONTACT_PHONE_NUMBER_BUTTON_XPATH =
            "//a[@class='create_btn' and contains(.,'Заполнить')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Управляющие кнопки в нижней части страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Подписать и отправить]
    private static final String SIGN_AND_SEND_BUTTON_XPATH = "//button[@name='btnPublish']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary quotations = new Dictionary();       // колонки таблицы раздела [Ценовое предложение]
    private final Dictionary radioButtonNames = new Dictionary(); // все переключатели на странице

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public PurchaseRequestPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        quotations.add("Наименование", TABLE_PRICE_OFFERS_NAME_COLUMN_XPATH);
        quotations.add("ОКВЕД2", TABLE_PRICE_OFFERS_OKVED2_COLUMN_XPATH);
        quotations.add("ОКПД2", TABLE_PRICE_OFFERS_OKPD2_COLUMN_XPATH);
        quotations.add("Единица измерения", TABLE_PRICE_OFFERS_UNIT_COLUMN_XPATH);
        quotations.add("Количество", TABLE_PRICE_OFFERS_AMOUNT_COLUMN_XPATH);
        quotations.add("Цена объекта", TABLE_PRICE_OFFERS_UNIT_PRICE_COLUMN_XPATH);
        quotations.add("Предложение цены", TABLE_PRICE_OFFERS_QUOTATION_COLUMN_XPATH);
        quotations.add("Общая сумма", TABLE_PRICE_OFFERS_QUOTATION_TOTAL_COLUMN_XPATH);
        quotations.add("Страна происхождения", TABLE_PRICE_OFFERS_COUNTRY_CODE_COLUMN_XPATH);
        quotations.add("Текущий рейтинг", TABLE_PRICE_OFFERS_CURRENT_POSITION_COLUMN_XPATH);
        quotations.add("Лучшее ценовое предложение", TABLE_PRICE_OFFERS_BEST_BID_PRICE_COLUMN_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        radioButtonNames.add("Согласен", TRADE_AGREEMENT_CONFIRMED_ID);
        radioButtonNames.add("Не согласен", TRADE_AGREEMENT_UNCONFIRMED_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Переходит к разделу заявки [Ценовое предложение].
     */
    public void goToPriceOfferSectionTitle() throws Exception {
        $(this.getBy(PRICE_OFFER_HEADER_XPATH)).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("[ИНФОРМАЦИЯ]: произведен переход к разделу [Ценовое предложение].");
    }

    /**
     * Производит переход к разделу [Сведения о товаре].
     */
    public void goToProductDetailsInfoSectionTitle() throws Exception {
        $(this.getBy(AGREEMENT_TITLE_ID)).waitUntil(visible, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("[ИНФОРМАЦИЯ]: произведен переход к разделу [Сведения о товаре].");
    }

    /**
     * Производит переход к разделу [Сведения об участнике процедуры].
     */
    public void goToPurchaseMembersInfoSectionTitle() throws Exception {
        $(this.getBy(PROCEDURE_MEMBERS_INFO_XPATH)).waitUntil(visible, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("[ИНФОРМАЦИЯ]: произведен переход к разделу [Сведения об участнике процедуры].");
    }

    /**
     * Переходит к разделу заявки [Первая часть заявки участника].
     */
    public void goToFirstPartOfApplicationSectionTitle() throws Exception {
        $(this.getBy(AGREEMENT_1ST_HEADER_ID)).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("[ИНФОРМАЦИЯ]: произведен переход к разделу [Первая часть заявки участника].");
    }

    /**
     * Переходит к разделу заявки [Вторая часть заявки участника].
     */
    public void goToSecondPartOfApplicationSectionTitle() throws Exception {
        $(this.getBy(AGREEMENT_2ND_HEADER_XPATH)).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("[ИНФОРМАЦИЯ]: произведен переход к разделу [Вторая часть заявки участника].");
    }

    /**
     * Заполняет поле [Предложение о цене] в разделе формы [Ценовое предложение].
     *
     * @param priceOffer требуемое значение поля
     */
    public void setPriceOffer(String priceOffer) {
        $(this.getBy(PRICE_OFFER_EDIT_XPATH)).waitUntil(clickable, timeout, polling).setValue(priceOffer);
        this.logFilledFieldName("Предложение о цене", priceOffer);
    }

    /**
     * Проверяет текущее значение поля [Ставка НДС].
     *
     * @param value требуемое значение поля
     */
    public void checkSelectedVatValue(String value) {
        this.logFieldNameToCheck("Ставка НДС", value);
        $(this.getBy(SELECT_VAT_XPATH)).waitUntil(exist, timeout, polling).shouldHave(text(value));
    }

    /**
     * Проверяет текущее значение поля [Предложение о цене (с НДС)].
     *
     * @param value требуемое значение поля
     */
    public void checkPriceOfferWithVat(String value) {
        this.logFieldNameToCheck("Предложение о цене (с НДС)", value);
        $(this.getBy(PRICE_OFFER_WITH_VAT_XPATH)).waitUntil(visible, timeout, polling).shouldHave(value(value));
    }

    /**
     * Заполняет поле [Сведения о ценовом предложении] в разделе формы [Ценовое предложение].
     */
    public void uploadPriceOfferDetails() throws Exception {
        String fileName = config.getAbsolutePathToDirectorAuthorityDoc();
        $(this.getBy(ADD_QUOTATION_INFO_FROM_FILE_BUTTON_INPUT_XPATH)).waitUntil(exist, timeout, polling).
                sendKeys(fileName);
        this.waitForPageLoad();
        this.logFilledFieldName("Сведения о ценовом предложении", fileName);
    }

    /**
     * Заполняет поле [Название документа] в разделе формы [Сведения о товаре].
     */
    public void uploadAgreementDetails() throws Exception {
        String fileName = config.getAbsolutePathToFoundationDoc();
        $$(this.getBy(AGREEMENT_DOCUMENT_NAME_XPATH)).get(0).waitUntil(exist, timeout, polling).sendKeys(fileName);
        this.waitForPageLoad(25);
        this.logFilledFieldName("Название документа", fileName);
    }

    /**
     * Заполняет поле [Название документа] в разделе формы [Ценовые предложения].
     */
    public void uploadAlternativeAgreementDetails() {
        String fileName = config.getAbsolutePathToFoundationDoc();
        $(this.getBy(ALTERNATIVE_AGREEMENT_DOCUMENT_NAME_XPATH)).waitUntil(exist, timeout, polling).sendKeys(fileName);
        this.logFilledFieldName("Название документа", fileName);
    }

    /**
     * Заполняет поле [Название документа] в разделе формы [Ценовые предложения].
     */
    public void uploadAdditionalAgreementDetails() {
        String fileName = config.getAbsolutePathToDirectorAuthorityDoc();
        $(this.getBy(ALTERNATIVE_AGREEMENT_DOCUMENT_NAME_XPATH)).waitUntil(exist, timeout, polling).sendKeys(fileName);
        this.logFilledFieldName("Название документа", fileName);
    }

    /**
     * Заполняет поле [Описание поставляемых ТРУ] в разделе формы [Первая часть заявки участника].
     */
    public void uploadDocumentInFirstPartOfApplicationSection() {
        String fileName = config.getAbsolutePathToFoundationDoc();
        $(this.getBy(APPLICATION_DOCUMENTS_BUTTON_INPUT_XPATH)).waitUntil(exist, timeout, polling).sendKeys(fileName);
        this.logFilledFieldName("Описание поставляемых ТРУ", fileName);
    }

    /**
     * Заполняет поле [Сведения о соответствии квалификационным требованиям]
     * в разделе формы [Вторая часть заявки участника].
     */
    public void uploadDocumentInSecondPartOfApplicationSection() {
        String fileName = config.getAbsolutePathToDirectorAuthorityDoc();
        $(this.getBy(QUALIFICATION_REQUIMENTS_DOCUMENTS_BUTTON_INPUT_XPATH)).waitUntil(exist, timeout, polling).
                sendKeys(fileName);
        this.logFilledFieldName("Сведения о соответствии квалификационным требованиям", fileName);
    }

    /**
     * Заполняет поле [Предложение о цене договора] в разделе формы [Ценовое предложение].
     *
     * @param offerValue требуемое значение поля
     */
    public void setOffer(String offerValue) throws Exception {
        this.waitClearClickAndSendKeysById(OFFER_EDIT_ID, offerValue);
        this.logFilledFieldName("Предложение о цене договора", offerValue);
    }

    /**
     * Проверяет значение поля [Предложение о цене] в разделе формы [Ценовое предложение]
     * (заявка находится в режиме редактирования).
     *
     * @param expectedValue ожидаемое значение поля
     */
    public void checkOfferValueInEditMode(String expectedValue) {
        String actualValue = $(this.getBy(OFFER_EDIT_ID)).waitUntil(visible, timeout, polling).getValue();
        Assert.assertTrue(String.format(
                "[ОШИБКА]: некорректное значение в поле [Предложение о цене] ожидалось - [%s], реально - [%s]",
                expectedValue, actualValue), actualValue.contains(expectedValue));
        System.out.printf("[ИНФОРМАЦИЯ]: проверка значения в поле [Предложение о цене] " +
                "ожидаемое значение - [%s], реальное значение - [%s].%n", expectedValue, actualValue);
    }

    /**
     * Проверяет значение поля [Предложение о цене] в разделе формы [Ценовое предложение]
     * (заявка находится в режиме просмотра).
     *
     * @param expectedValue ожидаемое значение поля
     */
    public void checkOfferValueInViewMode(String expectedValue) {
        String actualValue = $(this.getBy(OFFER_VIEW_ID)).waitUntil(visible, timeout, polling).getText();
        Assert.assertTrue(String.format(
                "[ОШИБКА]: некорректное значение в поле [Предложение о цене] ожидалось - [%s], реально - [%s]",
                expectedValue, actualValue), actualValue.contains(expectedValue));
        System.out.printf("[ИНФОРМАЦИЯ]: проверка значения в поле [Предложение о цене] " +
                "ожидаемое значение - [%s], реальное значение - [%s].%n", expectedValue, actualValue);
    }

    /**
     * Проверяет значение надписи в разделе формы [Ценовое предложение]
     * (заявка находится в режиме редактирования).
     *
     * @param expectedValue ожидаемое значение надписи
     */
    public void checkIsApplicationItemPartialOfferAllowed(String expectedValue) {
        String actualValue = $(this.getBy(IS_APPLICATION_ITEM_PARTIAL_OFFER_ALLOWED_ID)).
                waitUntil(exist, timeout, polling).shouldBe(visible).getText();
        Assert.assertEquals(String.format("[ОШИБКА]: не верное значение надписи в разделе формы [Ценовое предложение]" +
                " ожидалось - [%s], реально - [%s]", expectedValue, actualValue), expectedValue, actualValue);
        System.out.printf("[ИНФОРМАЦИЯ]: проверка значения надписи в разделе формы [Ценовое" +
                " предложение] ожидаемое значение - [%s], реальное значение - [%s].%n", expectedValue, actualValue);
    }

    /**
     * Заполняет поле [Предложение цены] в разделе формы [Ценовое предложение] (таблица с товарами и их ценами) для
     * строки таблицы с указанным номером.
     *
     * @param offerValue требуемое значение поля
     * @param lineNumber номер строки в таблице (от 1 до N)
     */
    public void setOfferFieldIntoQuotationsTableByLineNumber(String offerValue, String lineNumber) throws Exception {
        System.out.printf(
                "[ИНФОРМАЦИЯ]: установка значения [%s] для поля в столбце [Предложение цены] " +
                        "(таблица раздела 'Ценовое предложение'), в строке с номером [%s].%n", offerValue, lineNumber);
        ElementsCollection lines = $$(this.getBy(quotations.find("Предложение цены")));
        Assert.assertTrue("[ОШИБКА]: таблица раздела 'Ценовое предложение' не содержит строк",
                lines.size() > 0);
        int line = Integer.parseInt(lineNumber) - 1;
        Assert.assertTrue(String.format("[ОШИБКА]: передан некорректный номер строки: [%s]", lineNumber),
                line >= 0 && line < lines.size());
        SelenideElement offerView = lines.get(line);
        String idLocator = lineNumber + "_Quotation";
        SelenideElement offerEdit = $(this.getBy(idLocator));
        offerView.waitUntil(visible, timeout, polling).click();
        offerEdit.clear();
        TimeUnit.SECONDS.sleep(1);
        offerEdit.sendKeys(offerValue);
        TimeUnit.SECONDS.sleep(1);
        offerEdit.sendKeys(Keys.ENTER);
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(String.format(
                "[ОШИБКА]: ожидаемый текст ячейки: [%s], реальный текст ячейки: [%s]", offerValue, offerView.getText()),
                offerView.getText().contains(offerValue));
    }

    /**
     * Проверяет текст ячейки таблицы раздела [Ценовое предложение] (таблица с товарами и их ценами)
     * для столбца с указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     */
    public void verifyCellByColumnNameAndLineNumberInQuotationsTableForText(
            String columnName, String rowNumber, String cellValue) {
        this.verifyCellInTableByColumnElementsAndLineNumberForText("Ценовое предложение",
                columnName, $$(this.getBy(quotations.find(columnName))), rowNumber, cellValue);
    }

    /**
     * Заполняет поле [Номер контактного телефона] в разделе формы [Сведения об участнике процедуры].
     */
    public void fillContactPhoneNumber() throws Exception {
        SelenideElement contactPhoneNumber = $(this.getBy(FILL_CONTACT_PHONE_NUMBER_BUTTON_XPATH));
        contactPhoneNumber.waitUntil(clickable, timeout, polling);
        this.logButtonNameToPress("Заполнить");
        this.clickInElementJS(contactPhoneNumber);
        this.logPressedButtonName("Заполнить");
    }

    /**
     * Делает клик по кнопке [Сохранить] в разделе [Ценовые предложения].
     */
    public void clickOnSaveQuotationChangesButton() throws Exception {
        this.logButtonNameToPress("Сохранить");
        this.scrollToCenterAndclickInElementJS($(this.getBy(SAVE_QUOTATION_CHANGES_BUTTON_ID)));
        this.logPressedButtonName("Сохранить");
        this.waitForPageLoad(5);
    }


    /**
     * Делает клик по кнопке [Подписать и отправить] в нижней части формы.
     */
    public void clickSignAndSendButton() throws Exception {
        ConfirmDialog confirmDialog = new ConfirmDialog(driver);
        CompletedDialog completedDialog = new CompletedDialog(driver);

        this.logButtonNameToPress("Подписать и отправить");
        this.scrollToCenterAndclickInElementJS($(this.getBy(SIGN_AND_SEND_BUTTON_XPATH)));
        this.logPressedButtonName("Подписать и отправить");

        confirmDialog.clickOnButton("Кнопка Да");
        completedDialog.clickOnOkButton();
    }

    /**
     * Делает клик по кнопке [Подписать и отправить] в нижней части формы
     * ( в заявке не указаны ценовые предложения для части позиций ).
     */
    public void clickSignAndSendButtonWhenPriceOffersForPartOfThePositions() throws Exception {
        PriceOffersForPartOfThePositionsDialog priceOffersForPartOfThePositionsDialog =
                new PriceOffersForPartOfThePositionsDialog(driver);
        ConfirmDialog confirmDialog = new ConfirmDialog(driver);
        CompletedDialog completedDialog = new CompletedDialog(driver);

        this.logButtonNameToPress("Подписать и отправить");
        this.scrollToCenterAndclickInElementJS($(this.getBy(SIGN_AND_SEND_BUTTON_XPATH)));
        this.logPressedButtonName("Подписать и отправить");

        priceOffersForPartOfThePositionsDialog.clickOnButtonByName("Кнопка Подать");
        confirmDialog.clickOnButton("Кнопка Да");
        completedDialog.clickOnOkButton();
    }

    /**
     * Делает клик по кнопке [Подписать и отправить] в нижней части формы ( подача заявки на часть позиций ).
     */
    public void clickSignAndSendButtonWsPartialOffer() throws Exception {
        ConfirmDialog confirmDialog = new ConfirmDialog(driver);
        CompletedDialog completedDialog = new CompletedDialog(driver);
        ValidationApplicationDialog validationApplicationDialog = new ValidationApplicationDialog(driver);

        this.logButtonNameToPress("Подписать и отправить");
        this.scrollToCenterAndclickInElementJS($(this.getBy(SIGN_AND_SEND_BUTTON_XPATH)));
        this.logPressedButtonName("Подписать и отправить");

        validationApplicationDialog.
                checkWindowText("Текст сообщения в окне",
                        "В заявке не указаны ценовые предложения для части позиций.").
                clickOnButton("Подать");
        confirmDialog.clickOnButton("Кнопка Да");
        completedDialog.clickOnOkButton();
    }

    /**
     * Запоминает размер обеспечения заявки на участие и номер заявки в параметрах теста.
     *
     * @param supplierNumber номер поставщика, который подал заявку
     * @param lotNumber      номер лота, на который подана заявка
     */
    public void saveApplicationGuaranteeAndRequestNumber(String supplierNumber, String lotNumber) {
        String applicationGuarantee;

        // Получаем содержимое поля [Размер обеспечения заявки на участие] в разделе формы [Сведения о лоте]
        if ($(this.getBy(APPLICATION_GUARANTEE_TYPE_XPATH)).waitUntil(visible, timeout, polling).
                getText().contains("Без обеспечения"))
            applicationGuarantee = "0,00 (Российский рубль)";
        else
            applicationGuarantee = $(this.getBy(APPLICATION_GUARANTEE_SUM_XPATH)).waitUntil(visible, timeout, polling).
                    getText();

        // Получаем содержимое поля [Номер заявки] в разделе формы [Общие сведения]
        String requestNumber = $(this.getBy(REQUEST_NUMBER_XPATH)).waitUntil(visible, timeout, polling).getText();

        //--------------------------------------------------------------------------------------------------------------
        // Запоминаем содержимое этих полей в параметрах теста
        //--------------------------------------------------------------------------------------------------------------
        String requestNumberKey = String.format("Supplier%sLot%sRequestNumber", supplierNumber, lotNumber);
        String applicationGuaranteeKey = String.format("Supplier%sLot%sTransactionSum", supplierNumber, lotNumber);
        //--------------------------------------------------------------------------------------------------------------
        config.setParameter(requestNumberKey, requestNumber);
        config.setParameter(applicationGuaranteeKey, applicationGuarantee);
        //--------------------------------------------------------------------------------------------------------------
    }

    /**
     * Проверяет статус заявки на участие.
     *
     * @param requestStatus ожидаемый статус заявки на участие
     */
    public void checkRequestStatus(String requestStatus) throws Exception {
        this.waitForTextInField(requestStatus, this.getBy(REQUEST_STATUS_XPATH), "Статус заявки");
        System.out.printf("[ИНФОРМАЦИЯ]: произведена проверка статуса заявки [%s].%n", requestStatus);
    }

    /**
     * Проверяет наличие кнопки [Внести изменения].
     */
    public void checkEditRequestButtonExist() {
        $(this.getBy(EDIT_REQUEST_BUTTON_ID)).waitUntil(visible, timeout, polling);
        System.out.println("[ИНФОРМАЦИЯ]: произведена проверка наличия кнопки [Внести изменения].");
    }

    /**
     * Нажимает на кнопку [Внести изменения].
     */
    public void clickOnEditRequestButton() throws Exception {
        this.logButtonNameToPress("Внести изменения");
        this.scrollToCenterAndclickInElementJS($(this.getBy(EDIT_REQUEST_BUTTON_ID)));
        this.logPressedButtonName("Внести изменения");
        this.waitForPageLoad(5);
    }

    /**
     * Проверяет наличие кнопки [Отозвать заявку].
     */
    public void checkWithdrawRequestButtonExist() throws Exception {
        // Форма заявки перешла в режим просмотра и на ней должна отобразиться кнопка [Отозвать заявку]
        this.waitLoadingImage();
        this.checkPageUrl("Заявка на участие в режиме просмотра");
        $(this.getBy(WITHDRAW_REQUEST_BUTTON_XPATH)).waitUntil(visible, timeout, polling);
        System.out.println("[ИНФОРМАЦИЯ]: произведена проверка наличия кнопки [Отозвать заявку].");
    }

    /**
     * Осуществляет переход по ссылке [Редактировать] с указанным порядковым номером в таблице [Ценовые предложения].
     *
     * @param linkNumber порядковый номер ссылки
     */
    public void goToNthEditLinkInPriceOffersTable(String linkNumber) {
        ElementsCollection links = $$(this.getBy(EDIT_OFFER_LINK_XPATH));
        int count = links.size();
        System.out.printf("[ИНФОРМАЦИЯ]: количество строк в таблице: '%d'.%n", count);
        int link = Integer.parseInt(linkNumber) - 1;
        Assert.assertTrue("[ОШИБКА]: переданный номер строки вне диапазона", link >= 0 && link < count);
        System.out.printf("[ИНФОРМАЦИЯ]: переходим по ссылке с текстом: '%s'.%n", links.get(link).getText());
        $(this.getBy(OFFER_EDIT_ID)).shouldNotBe(visible);
        links.get(link).click();

        // Открылась секция [Редактирование ценового предложения]
        $(this.getBy(EDIT_OFFER_HEADER_ID)).waitUntil(visible, timeout, polling);
        $(this.getBy(OFFER_EDIT_ID)).shouldBe(visible);
    }

    /**
     * Нажимает на кнопку [Добавить] для таблицы [Ценовые предложения].
     */
    public void clickOnAddNewPriceOfferToTableButton() throws Exception {
        this.logButtonNameToPress("Добавить");
        this.scrollToCenterAndclickInElementJS($(this.getBy(ADD_NEW_QUOTATION_BUTTON_ID)));
        this.logPressedButtonName("Добавить");
        this.waitForPageLoad(5);

        // Открылась секция [Новое ценовое предложение]
        $(this.getBy(NEW_OFFER_HEADER_ID)).waitUntil(visible, timeout, polling);
        $(this.getBy(OFFER_EDIT_ID)).shouldBe(visible);
    }

    /**
     * Проверяет, что ценовое предложение в указанной строке имеет требуемый статус и сумму.
     *
     * @param rank  статус ценового предложения
     * @param row   порядковый номер строки в таблице
     * @param price сумма ценового предложения
     */
    public void checkThePriceOfferRankAndPrice(String rank, String row, String price) {
        ElementsCollection ranks = $$(this.getBy(TABLE_PRICE_OFFERS_OFFERS_COLUMN_VIEW_XPATH));
        ElementsCollection prices = $$(this.getBy(TABLE_PRICE_OFFERS_PRICES_COLUMN_VIEW_XPATH));
        int count = ranks.size();
        System.out.printf("[ИНФОРМАЦИЯ]: количество строк в таблице: '%d'.%n", count);
        int iRow = Integer.parseInt(row) - 1;
        Assert.assertTrue("[ОШИБКА]: переданный номер строки вне диапазона", iRow >= 0 && iRow < count);
        System.out.printf("[ИНФОРМАЦИЯ]: реальный статус предложения: '%s'.%n", ranks.get(iRow).getText());
        System.out.printf("[ИНФОРМАЦИЯ]: реальная  цена  предложения: '%s'.%n", prices.get(iRow).getText());
        Assert.assertTrue("[ОШИБКА]: статус предложения некорректен", ranks.get(iRow).getText().contains(rank));
        Assert.assertTrue("[ОШИБКА]: цена предложения некорректна", prices.get(iRow).getText().contains(price));
    }

    /**
     * Проверяем содержимое поля [Размер обеспечения заявки на участие] в разделе формы [Сведения о лоте].
     *
     * @param expectedSum ожидаемое значение поля
     */
    public void checkApplicationGuaranteeSum(String expectedSum) {
        // Получаем содержимое поля [Размер обеспечения заявки на участие] в разделе формы [Сведения о лоте]
        SelenideElement element = $(this.getBy(APPLICATION_GUARANTEE_SUM_XPATH));
        element.waitUntil(visible, timeout, polling).click();
        String actualSum = element.getText();
        System.out.printf("[ИНФОРМАЦИЯ]: Ожидаемый размер обеспечения заявки на участие: [%s], реальный: [%s]%n",
                expectedSum, actualSum);

        // Проверяем содержимое поля [Размер обеспечения заявки на участие] в разделе формы [Сведения о лоте]
        Assert.assertTrue("Размер обеспечения заявки на участие не верен", actualSum.contains(expectedSum));
    }

    /**
     * Проверяем содержимое поля [Размер лицензионного вознаграждения] в разделе формы [Сведения о лоте].
     *
     * @param expectedSum ожидаемое значение поля
     */
    public void checkLicenseCommissionSum(String expectedSum) {
        // Получаем содержимое поля [Размер лицензионного вознаграждения] в разделе формы [Сведения о лоте]
        SelenideElement element = $(this.getBy(LICENSE_COMMISSION_ID));
        element.waitUntil(visible, timeout, polling).click();
        String actualSum = element.getText();
        System.out.printf("[ИНФОРМАЦИЯ]: Ожидаемый размер лицензионного вознаграждения: [%s], реальный: [%s]%n",
                expectedSum, actualSum);

        // Проверяем содержимое поля [Размер лицензионного вознаграждения] в разделе формы [Сведения о лоте]
        Assert.assertTrue("Размер лицензионного вознаграждения не верен", actualSum.contains(expectedSum));
    }

    /**
     * Выбирает в переключателе опцию с указанным именем.
     *
     * @param radioButtonPosition имя опции
     */
    public void selectRadioButton(String radioButtonPosition) throws Exception {
        $(this.getBy(radioButtonNames.find(radioButtonPosition))).waitUntil(exist, timeout, polling).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено переключение в положение [%s].%n", radioButtonPosition);
        TimeUnit.SECONDS.sleep(1);
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
     * Заполняет поле [Страна происхождения товара] в разделе формы [Первая часть заявки участника] фиксированным
     * значением [Российская федерация].
     */
    public void setCountryOfOriginFieldWithTheValueRussianFederation() throws Exception {
        $(this.getBy(COUNTRY_OF_ORIGIN_LIST_OPEN_VALUES_XPATH)).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        $(this.getBy(COUNTRY_OF_ORIGIN_LIST_DESIRED_VALUE_XPATH)).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        $(this.getBy(COUNTRY_OF_ORIGIN_LIST_SELECTED_VALUE_XPATH)).waitUntil(clickable, timeout, polling).
                shouldHave(text("Российская Федерация"));
        this.logFilledFieldName("Страна происхождения товара", "Российская Федерация");
    }
}
