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
 * Класс описывающий страницу [Заявка на участие в ...] (.kontur.ru/supplier/auction/Application/...).
 * [Старая редакция заявки, которая в итоге не будет использоваться в автотестах].
 * Пока используется один класс как для режима редактирования заявки так и для режима просмотра заявки.
 * В будущем надо будет разделить этот класс на два отдельных.
 * Created by Vladimir V. Klochkov on 08.04.2016.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class PurchaseRequestPageOld extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Управляющие кнопки в верхней части страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Внести изменения]
    private static final String EDIT_REQUEST_BUTTON_ID = "BaseMainContent_MainContent_btnEditTop";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отозвать заявку]
    private static final String WITHDRAW_REQUEST_BUTTON_ID = "BaseMainContent_MainContent_btnWithdrawTop";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о лоте]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Начальный поправочный коэффициент]
    private static final String INITIAL_CORRECTION_COEFFICIENT_ID =
            "BaseMainContent_MainContent_fvInitialCorrectionCoefficient_lblValue";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Вид обеспечения заявки]
    private static final String APPLICATION_GUARANTEE_TYPE_ID =
            "BaseMainContent_MainContent_fvApplicationGuaranteeType_lblValue";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Размер обеспечения заявки на участие]
    private static final String APPLICATION_GUARANTEE_SUM_ID =
            "BaseMainContent_MainContent_fvApplicationGuaranteeSum_lblValue";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Размер лицензионного вознаграждения]
    private static final String LICENSE_COMMISSION_ID =
            "BaseMainContent_MainContent_fvCommission_lblValue";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Общие сведения]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер заявки]
    private static final String REQUEST_NUMBER_ID = "BaseMainContent_MainContent_fvApplicationNumber_lblValue";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус]
    private static final String REQUEST_STATUS_ID = "BaseMainContent_MainContent_fvStatus_lblValue";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о товаре]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела формы [Сведения о товаре]
    private static final String AGREEMENT_TITLE_ID = "BaseMainContent_MainContent_hAgreementTitle";
    //------------------------------------------------------------------------------------------------------------------
    // Поле ввода в кнопке [Добавить] для поля [Название документа]
    private static final String AGREEMENT_DOCUMENT_NAME_XPATH =
            "//div[@id='BaseMainContent_MainContent_ufAgreementFile_btnUpload']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Страна происхождения товара] - область открытия списка по которой нужно будет сделать щелчок мышью
    private static final String COUNTRY_OF_ORIGIN_LIST_OPEN_VALUES_XPATH =
            "//*[@id='BaseMainContent_MainContent_lbgManufacturerType_chzn']/ul/li";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Страна происхождения товара] - список значений открыт - значение [Российская федерация]
    private static final String COUNTRY_OF_ORIGIN_LIST_DESIRED_VALUE_XPATH =
            "//*[@id='BaseMainContent_MainContent_lbgManufacturerType_chzn']/div/ul/li[contains(., 'Российская Федерация')]";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Страна происхождения товара] - текущее выбранное значение
    private static final String COUNTRY_OF_ORIGIN_LIST_SELECTED_VALUE_XPATH =
            "//*[@id='BaseMainContent_MainContent_lbgManufacturerType_chzn']/ul/li/span";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Поправочный коэффициент]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Начальный поправочный коэффициент]
    private static final String QUOTATION_ID =
            "BaseMainContent_MainContent_txtQuotation";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Ценовые/ценовое предложения]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела формы [Ценовое предложение]
    private static final String QUOTATIONS_TITLE_ID = "BaseMainContent_MainContent_hQuotationsTitle";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Импортировать] в разделе формы [Ценовое предложение] непосредственно поле для ввода полного имени файла
    private static final String IMPORT_QUOTATIONS_FROM_FILE_BUTTON_INPUT_XPATH =
            "//*[@id='BaseMainContent_MainContent_ufImportLotItemsFromExcel_btnUpload']/input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле ввода в кнопке [Добавить] для поля [Сведения о ценовом предложении] в разделе формы [Ценовое предложение]
    private static final String ADD_QUOTATION_INFO_FROM_FILE_BUTTON_INPUT_XPATH =
            "//div[@id='BaseMainContent_MainContent_ufQuotationInfoDoc_btnUpload']/input";
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
    // Ссылка [Редактировать] в разделе формы [Ценовые предложения]
    private static final String EDIT_OFFER_LINK_XPATH = "//a[contains(.,'Редактировать')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Предложение о цене (договора)] в разделе формы [Ценовое предложение] (заявка находится в режиме редактир.)
    private static final String OFFER_EDIT_ID = "BaseMainContent_MainContent_txtQuotation";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Предложение о цене (договора)] в разделе формы [Ценовое предложение] (заявка находится в режиме просмотра)
    private static final String OFFER_VIEW_ID = "BaseMainContent_MainContent_fvQuotation_lblValue";
    //------------------------------------------------------------------------------------------------------------------
    // Надпись [Заказчик предоставил возможность ввода ценовых предложений на часть позиций.]
    private static final String IS_APPLICATION_ITEM_PARTIAL_OFFER_ALLOWED_ID =
            "BaseMainContent_MainContent_sIsApplicationItemPartialOfferAllowed";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Ставка НДС] в разделе формы [Ценовое предложение] (значение уже выбрано)
    private static final String TAX_PERCENT_XPATH = "//*[@id='BaseMainContent_MainContent_ddlTaxPercent_chzn']/a/span";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Ставка НДС] в разделе формы [Ценовое предложение] (стрелка [V] открыть список значений)
    private static final String TAX_PERCENT_OPEN_VALUES_LIST_XPATH =
            "//div[@id='BaseMainContent_MainContent_ddlTaxPercent_chzn']/a/div/b";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Ставка НДС] в разделе формы [Ценовое предложение] (значение 'Сложный НДС' в списке значений)
    private static final String TAX_PERCENT_COMPLEX_VAT_VALUE_IN_LIST_XPATH =
            "//*[contains(@id, 'BaseMainContent_MainContent_ddlTaxPercent_chzn_') and contains(., 'Сложный НДС')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Предложение о цене (с НДС)] в разделе формы [Ценовое предложение] (заявка находится в режиме редактир.)
    private static final String QUOTATION_WITH_TAX_EDIT_ID = "BaseMainContent_MainContent_txtQuotationWithTax";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Предложение о цене (с НДС)] в разделе формы [Ценовое предложение] (заявка находится в режиме просмотра)
    private static final String QUOTATION_WITH_TAX_VIEW_ID = "BaseMainContent_MainContent_fvQuotationWithTax_lblValue";
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
    // Столбец [Предложение цены] в таблице [Ценовые предложения] (поле в фокусе и готово к изменению значения)
    private static final String TABLE_PRICE_OFFERS_QUOTATION_COLUMN_FOCUSED_ID = "%s_Quotation";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [НДС] в таблице [Ценовые предложения] (режим редактирования, режим просмотра)
    private static final String TABLE_PRICE_OFFERS_VAT_COLUMN_XPATH =
            "//table[@id='applicationItemGrid']//td[@aria-describedby='applicationItemGrid_TaxPercentEnum']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [НДС] в таблице [Ценовые предложения] (поле в фокусе и готово к изменению значения)
    private static final String TABLE_PRICE_OFFERS_VAT_COLUMN_FOCUSED_ID = "%s_TaxPercentEnum";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Общая сумма] в таблице [Ценовые предложения] (режим редактирования, режим просмотра)
    private static final String TABLE_PRICE_OFFERS_QUOTATION_TOTAL_COLUMN_XPATH =
            "//table[@id='applicationItemGrid']//td[@aria-describedby='applicationItemGrid_QuotationTotal']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Страна происхождения] в таблице [Ценовые предложения] (режим редактирования, режим просмотра)
    private static final String TABLE_PRICE_OFFERS_COUNTRY_CODE_COLUMN_XPATH =
            "//table[@id='applicationItemGrid']//td[@aria-describedby='applicationItemGrid_ManufacturerCountries']";
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

    // region Раздел [Сведения об участнике закупки]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела формы [Сведения об участнике закупки]
    private static final String PURCHASE_MEMBERS_INFO_ID = "BaseMainContent_MainContent_hPurchaseMembersInfo";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Заполнить] для поля [Номер контактного телефона]
    private static final String FILL_CONTACT_PHONE_NUMBER_BUTTON_XPATH =
            "//a[@class='create_btn' and contains(., 'Заполнить')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Управляющие кнопки в нижней части страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Подписать и отправить]
    private static final String SIGN_AND_SEND_BUTTON_ID = "BaseMainContent_MainContent_btnApply";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary fieldNames = new Dictionary(); // все поля на странице
    private final Dictionary quotations = new Dictionary(); // колонки таблицы в разделе [Ценовое предложение]

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public PurchaseRequestPageOld(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Начальный поправочный коэффициент", INITIAL_CORRECTION_COEFFICIENT_ID);
        fieldNames.add("Поправочный коэффициент", QUOTATION_ID);
        //--------------------------------------------------------------------------------------------------------------
        quotations.add("Наименование", TABLE_PRICE_OFFERS_NAME_COLUMN_XPATH);
        quotations.add("ОКВЕД2", TABLE_PRICE_OFFERS_OKVED2_COLUMN_XPATH);
        quotations.add("ОКПД2", TABLE_PRICE_OFFERS_OKPD2_COLUMN_XPATH);
        quotations.add("Единица измерения", TABLE_PRICE_OFFERS_UNIT_COLUMN_XPATH);
        quotations.add("Количество", TABLE_PRICE_OFFERS_AMOUNT_COLUMN_XPATH);
        quotations.add("Цена объекта", TABLE_PRICE_OFFERS_UNIT_PRICE_COLUMN_XPATH);
        quotations.add("Предложение цены", TABLE_PRICE_OFFERS_QUOTATION_COLUMN_XPATH);
        quotations.add("НДС", TABLE_PRICE_OFFERS_VAT_COLUMN_XPATH);
        quotations.add("Общая сумма", TABLE_PRICE_OFFERS_QUOTATION_TOTAL_COLUMN_XPATH);
        quotations.add("Страна происхождения", TABLE_PRICE_OFFERS_COUNTRY_CODE_COLUMN_XPATH);
        quotations.add("Текущий рейтинг", TABLE_PRICE_OFFERS_CURRENT_POSITION_COLUMN_XPATH);
        quotations.add("Лучшее ценовое предложение", TABLE_PRICE_OFFERS_BEST_BID_PRICE_COLUMN_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Производит переход к разделу [Ценовое предложение].
     */
    public void goToPriceOfferSectionTitle() throws Exception {
        $(this.getBy(QUOTATIONS_TITLE_ID)).waitUntil(visible, timeout, polling).click();
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
     * Производит переход к разделу [Сведения об участнике закупки].
     */
    public void goToPurchaseMembersInfoSectionTitle() throws Exception {
        $(this.getBy(PURCHASE_MEMBERS_INFO_ID)).waitUntil(visible, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("[ИНФОРМАЦИЯ]: произведен переход к разделу [Сведения об участнике закупки].");
    }

    /**
     * Производит импорт позиций ценовых предложений поставщика из файла.
     */
    public void importLotItemApplicationsFromFile() throws Exception {
        String fileName = config.getAbsolutePathToSupplierPriceOffer();
        $(this.getBy(IMPORT_QUOTATIONS_FROM_FILE_BUTTON_INPUT_XPATH)).waitUntil(exist, timeout, polling).
                sendKeys(fileName);
        System.out.printf(
                "[ИНФОРМАЦИЯ]: произведен импорт позиций ценовых предложений поставщика из файла [%s].%n", fileName);
        this.waitForPageLoad(25);
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
        $(this.getBy(AGREEMENT_DOCUMENT_NAME_XPATH)).waitUntil(exist, timeout, polling).sendKeys(fileName);
        this.waitForPageLoad(25);
        this.logFilledFieldName("Название документа", fileName);
    }

    /**
     * Заполняет поле [Название документа] в разделе формы [Ценовые предложения].
     */
    public void uploadAlternativeAgreementDetails() {
        String fileName = config.getAbsolutePathToFoundationDoc();
        $(this.getBy(ALTERNATIVE_AGREEMENT_DOCUMENT_NAME_XPATH)).waitUntil(exist, timeout, polling).sendKeys(fileName);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [Название документа] значением [%s].%n", fileName);
    }

    /**
     * Заполняет поле [Название документа] в разделе формы [Ценовые предложения].
     */
    public void uploadAdditionalAgreementDetails() {
        String fileName = config.getAbsolutePathToDirectorAuthorityDoc();
        $(this.getBy(ALTERNATIVE_AGREEMENT_DOCUMENT_NAME_XPATH)).waitUntil(exist, timeout, polling).sendKeys(fileName);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [Название документа] значением [%s].%n", fileName);
    }

    /**
     * Заполняет поле [Предложение о цене договора] в разделе формы [Ценовое предложение].
     *
     * @param offerValue требуемое значение поля
     */
    public void setOffer(String offerValue) throws Exception {
        this.waitClearClickAndSendKeysById(OFFER_EDIT_ID, offerValue);
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
     * Заполняет поле со списком [Ставка НДС] в разделе формы [Ценовое предложение] значением [Сложный НДС].
     */
    public void setTaxPercentFieldToComplexVatValueInEditMode() throws Exception {
        for (int i = 0; i < 50; i++) {
            System.out.printf("[ИНФОРМАЦИЯ]: попытка № [%d] открыть список [Ставка НДС].%n", i + 1);
            $(this.getBy(TAX_PERCENT_OPEN_VALUES_LIST_XPATH)).waitUntil(visible, timeout, polling).click();
            TimeUnit.SECONDS.sleep(3);
            SelenideElement valueInList = $(this.getBy(TAX_PERCENT_COMPLEX_VAT_VALUE_IN_LIST_XPATH));
            if (valueInList.isDisplayed()) {
                valueInList.waitUntil(visible, timeout, polling).click();
                TimeUnit.SECONDS.sleep(3);
                if ($(this.getBy(TAX_PERCENT_XPATH)).getText().equals("Сложный НДС")) break;
            }
        }
        System.out.println("[ИНФОРМАЦИЯ]: в поле [Ставка НДС] установлено значение [Сложный НДС].");
        this.checkTaxPercentValueInEditMode("Сложный НДС");
    }

    /**
     * Проверяет значение поля [Ставка НДС] в разделе формы [Ценовое предложение]
     * (заявка находится в режиме редактирования).
     *
     * @param expectedValue ожидаемое значение поля
     */
    public void checkTaxPercentValueInEditMode(String expectedValue) {
        String actualValue = $(this.getBy(TAX_PERCENT_XPATH)).waitUntil(visible, timeout, polling).getText();
        Assert.assertTrue(String.format(
                "[ОШИБКА]: некорректное значение в поле [Ставка НДС] ожидалось - [%s], реально - [%s]",
                expectedValue, actualValue), actualValue.contains(expectedValue));
        System.out.printf("[ИНФОРМАЦИЯ]: проверка значения в поле [Ставка НДС] " +
                "ожидаемое значение - [%s], реальное значение - [%s].%n", expectedValue, actualValue);
    }

    /**
     * Проверяет значение поля [Предложение о цене (с НДС)] в разделе формы [Ценовое предложение]
     * (заявка находится в режиме редактирования).
     *
     * @param expectedValue ожидаемое значение поля
     */
    public void checkQuotationWithTaxValueInEditMode(String expectedValue) {
        String actualValue = $(this.getBy(QUOTATION_WITH_TAX_EDIT_ID)).waitUntil(visible, timeout, polling).getValue();
        Assert.assertTrue(String.format(
                "[ОШИБКА]: некорректное значение в поле [Предложение о цене (с НДС)] ожидалось - [%s], реально - [%s]",
                expectedValue, actualValue), actualValue.contains(expectedValue));
        System.out.printf("[ИНФОРМАЦИЯ]: проверка значения в поле [Предложение о цене (с НДС)] " +
                "ожидаемое значение - [%s], реальное значение - [%s].%n", expectedValue, actualValue);
    }

    /**
     * Проверяет значение поля [Предложение о цене (с НДС)] в разделе формы [Ценовое предложение]
     * (заявка находится в режиме просмотра).
     *
     * @param expectedValue ожидаемое значение поля
     */
    public void checkQuotationWithTaxValueInViewMode(String expectedValue) {
        String actualValue = $(this.getBy(QUOTATION_WITH_TAX_VIEW_ID)).waitUntil(visible, timeout, polling).getText();
        Assert.assertTrue(String.format(
                "[ОШИБКА]: некорректное значение в поле [Предложение о цене (с НДС)] ожидалось - [%s], реально - [%s]",
                expectedValue, actualValue), actualValue.contains(expectedValue));
        System.out.printf("[ИНФОРМАЦИЯ]: проверка значения в поле [Предложение о цене (с НДС)] " +
                "ожидаемое значение - [%s], реальное значение - [%s].%n", expectedValue, actualValue);
    }

    /**
     * Заполняет поле [Предложение цены] в разделе формы [Ценовое предложение] (таблица с товарами и их ценами) для
     * строки таблицы с указанным номером.
     *
     * @param offerValue требуемое значение поля
     * @param lineNumber номер строки в таблице (от 1 до N)
     */
    public void setOfferFieldIntoQuotationsTableByLineNumber(String offerValue, String lineNumber) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: установка значения [%s] для поля в столбце [Предложение цены] " +
                "(таблица раздела 'Ценовое предложение'), в строке с номером [%s].%n", offerValue, lineNumber);
        ElementsCollection lines = $$(this.getBy(quotations.find("Предложение цены")));
        Assert.assertTrue("[ОШИБКА]: таблица раздела 'Ценовое предложение' не содержит строк",
                lines.size() > 0);
        int line = Integer.parseInt(lineNumber) - 1;
        Assert.assertTrue(String.format("[ОШИБКА]: передан некорректный номер строки: [%s]", lineNumber),
                line >= 0 && line < lines.size());
        SelenideElement offerView = lines.get(line);
        offerView.waitUntil(visible, timeout, polling).click();
        SelenideElement offerEdit = $(this.getBy(String.format(TABLE_PRICE_OFFERS_QUOTATION_COLUMN_FOCUSED_ID, lineNumber)));
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
     * Заполняет поле [НДС] в разделе формы [Ценовое предложение] (таблица с товарами и их ценами) для
     * строки таблицы с указанным номером.
     *
     * @param vatValue   требуемое значение поля
     * @param lineNumber номер строки в таблице (от 1 до N)
     */
    public void setVatFieldIntoQuotationsTableByLineNumber(String vatValue, String lineNumber) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: установка значения [%s] для поля в столбце [НДС] " +
                "(таблица раздела 'Ценовое предложение'), в строке с номером [%s].%n", vatValue, lineNumber);
        ElementsCollection lines = $$(this.getBy(quotations.find("НДС")));
        Assert.assertTrue("[ОШИБКА]: таблица раздела 'Ценовое предложение' не содержит строк",
                lines.size() > 0);
        int line = Integer.parseInt(lineNumber) - 1;
        Assert.assertTrue(String.format("[ОШИБКА]: передан некорректный номер строки: [%s]", lineNumber),
                line >= 0 && line < lines.size());
        SelenideElement vatView = lines.get(line);
        vatView.waitUntil(visible, timeout, polling).click();
        SelenideElement vatEdit = $(this.getBy(String.format(TABLE_PRICE_OFFERS_VAT_COLUMN_FOCUSED_ID, lineNumber)));
        vatEdit.waitUntil(visible, timeout, polling).shouldBe(visible);
        vatEdit.selectOption("10");
        TimeUnit.SECONDS.sleep(5);
        Assert.assertTrue(String.format(
                "[ОШИБКА]: ожидаемый текст ячейки: [%s], реальный текст ячейки: [%s]", vatValue, vatView.getText()),
                vatView.getText().contains(vatValue));
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
        this.scrollToCenterAndclickInElementJS($(this.getBy(SIGN_AND_SEND_BUTTON_ID)));
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
        this.scrollToCenterAndclickInElementJS($(this.getBy(SIGN_AND_SEND_BUTTON_ID)));
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
        this.scrollToCenterAndclickInElementJS($(this.getBy(SIGN_AND_SEND_BUTTON_ID)));
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
        if ($(this.getBy(APPLICATION_GUARANTEE_TYPE_ID)).waitUntil(visible, timeout, polling).
                getText().contains("Без обеспечения"))
            applicationGuarantee = "0,00 (Российский рубль)";
        else
            applicationGuarantee = $(this.getBy(APPLICATION_GUARANTEE_SUM_ID)).waitUntil(visible, timeout, polling).
                    getText();

        // Получаем содержимое поля [Номер заявки] в разделе формы [Общие сведения]
        String requestNumber = $(this.getBy(REQUEST_NUMBER_ID)).waitUntil(visible, timeout, polling).getText();

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
        this.waitForTextInField(requestStatus, this.getBy(REQUEST_STATUS_ID), "Статус заявки");
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
        this.checkPageUrl("Заявка на участие в режиме просмотра старая");
        $(this.getBy(WITHDRAW_REQUEST_BUTTON_ID)).waitUntil(visible, timeout, polling);
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
        SelenideElement element = $(this.getBy(APPLICATION_GUARANTEE_SUM_ID));
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
     * Устанавливает значение полей с предварительной их очисткой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     */
    public void setFieldClearClickAndSendKeys(String fieldName, String value) throws Exception {
        this.waitClearClickAndSendKeysById(fieldNames.find(fieldName), value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
    }
}
