package ru.PageObjects.Customer.PrintFormsPreview;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.Arrays;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс описывающий страницу [Заявка на участие в ...]
 * ( .kontur.ru/customer/lk/Iframe/TradeLotApplicationPrintForm? ).
 * Created by Vladimir V. Klochkov on 02.03.2017.
 * Updated by Vladimir V. Klochkov on 04.03.2021.
 */
public class ApplicationViewPage extends CommonCustomerPage {

    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы [Заявка на участие в ...]
    private static final String PAGE_HEADER_XPATH = "//*/td[contains(.,'Заявка на участие в ')]";
    //------------------------------------------------------------------------------------------------------------------

    //region Раздел [Сведения о закупке]

    //------------------------------------------------------------------------------------------------------------------
    // Имя раздела [Сведения о закупке]
    // private static final String PURCHASE_DETAILS_XPATH = "//tr/td[text()='Сведения о закупке']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки]
    private static final String PURCHASE_NUMBER_XPATH = "//tr/td[text()='Номер закупки:']/../td[4]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки в ЕИС]
    private static final String EIS_NUMBER_XPATH = "//tr/td[text()='Номер закупки в ЕИС:']/../td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование закупки]
    private static final String PURCHASE_NAME_XPATH = "//tr/td[text()='Наименование закупки:']/../td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Способ закупки]
    private static final String PURCHASE_METHOD_XPATH = "//tr/td[text()='Способ закупки:']/../td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Организация, осуществляющая закупку]
    private static final String CUSTOMER_ORGANIZATION_NAME_XPATH =
            "//tr/td[text()='Организация, осуществляющая закупку:']/../td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип валюты]
    private static final String CURRENCY_TYPE_XPATH = "//tr/td[text()='Тип валюты:']/../td[2]";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Раздел [Сведения о лоте]

    //------------------------------------------------------------------------------------------------------------------
    // Имя раздела [Сведения о лоте]
    // private static final String LOT_DETAILS_XPATH = "//tr/td[text()='Сведения о лоте']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер лота]
    private static final String LOT_NUMBER_XPATH =
            "//tr/td[text()='Номер лота:']/../td[4]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование предмета договора:]
    private static final String CONTRACT_SUBJECT_NAME_XPATH =
            "//tr/td[text()='Наименование предмета договора:']/../td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Начальная (максимальная) цена]
    private static final String INITIAL_MAXIMUM_PRICE_XPATH =
            "//tr/td[text()='Начальная (максимальная) цена:']/../td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Вид обеспечения заявки]
    private static final String TYPE_OF_APPLICATION_SECURITY_XPATH =
            "//tr/td[text()='Вид обеспечения заявки:']/../td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Размер обеспечения заявки]
    private static final String APPLICATION_SECURITY_AMOUNT_XPATH =
            "//tr/td[text()='Размер обеспечения заявки:']/../td[2]";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Раздел [Сведения о товаре]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Страна происхождения]
    private static final String COUNTRY_OF_ORIGIN_XPATH =
            "//tr/td[text()='Страна происхождения']/../td[4]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец  [Наименование документа]
    private static final String DOCUMENT_NAME_XPATH =
            "//tr[@style='height:20px;']/td[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец  [Файл документа]
//    private static final String FILE_XPATH =
//            "//tr[@style='height:20px;']/td[@class='s15']/a";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Таблица [Ценовое предложение]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок таблицы [Ценовое предложение]
    private static final String PRICE_OFFER_TABLE_HEADER_XPATH =
            "//*/td[@class='s3' and contains(.,'Ценовое предложение')]";
    //------------------------------------------------------------------------------------------------------------------
    // Метка поля [Предложение о цене] в таблице [Ценовое предложение]
    private static final String PRICE_OFFER_LABEL_XPATH = "//*/td[@class='s4' and contains(.,'Предложение о цене')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Предложение о цене] в таблице [Ценовое предложение]
    private static final String PRICE_OFFER_FIELD_XPATH = "//tr/td[text()='Предложение о цене:']/../td[4]";
    //------------------------------------------------------------------------------------------------------------------
    // Метка поля [Тип подачи ценового предложения] в таблице [Ценовое предложение]
    private static final String PRICE_OFFER_TYPE_LABEL_XPATH =
            "//*/td[@class='s4' and contains(.,'Тип подачи ценового предложения')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип подачи ценового предложения] в таблице [Ценовое предложение]
    private static final String PRICE_OFFER_TYPE_FIELD_XPATH =
            "//tr/td[text()='Тип подачи ценового предложения:']/../td[2]";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Раздел [Сведения об участнике закупки]

    // Заголовок поля [Номер в реестре аккредитованных участников закупки]
    private static final String NUMBER_IN_REGISTER_HEADER_XPATH =
            "//*/td[contains(.,'Номер в реестре аккредитованных участников закупки')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер в реестре аккредитованных участников закупки]
    private static final String NUMBER_IN_REGISTER_FIELD_XPATH =
            "//tr/td[text()='Номер в реестре аккредитованных участников закупки:']/../td[4]";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок поля [Фирменное наименование]
    private static final String SUPPLIER_NAME_HEADER_XPATH = "//td[contains(.,'Фирменное наименование')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Фирменное наименование]
    private static final String SUPPLIER_NAME_FIELD_XPATH =
            "//tr/td[text()='Фирменное наименование:']/../td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Юридический адрес]
    private static final String LEGAL_ADDRESS_FIELD_XPATH =
            "//tr/td[text()='Юридический адрес:']/../td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Почтоваый адрес]
    private static final String MAILING_ADDRESS_FIELD_XPATH =
            "//tr/td[text()='Почтоваый адрес:']/../td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер контактоного телефона]
    private static final String PHONE_NUMBER_FIELD_XPATH =
            "//tr/td[text()='Номер контактного телефона:']/../td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок поля [ИНН]
    private static final String INN_HEADER_XPATH = "//*/td[contains(.,'ИНН')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [ИНН]
    private static final String INN_FIELD_XPATH = "//tr/td[text()='ИНН:']/../td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [ФИО сотрудника, подавшего заявку]
    private static final String FIO_FIELD_XPATH =
            "//tr/td[text()='ФИО сотрудника, подавшего заявку:']/../td[2]";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Таблица [Поправочный коэффициент]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок таблицы [Поправочный коэффициент]
    private static final String CORRECTION_FACTOR_TABLE_HEADER_XPATH =
            "//*/td[@class='s3' and contains(.,'Поправочный коэффициент')]";
    //------------------------------------------------------------------------------------------------------------------
    // Метка поля [Предложение о цене] в таблице [Поправочный коэффициент]
    private static final String CORRECTION_FACTOR_LABEL_XPATH =
            "//*/td[@class='s4' and contains(.,'Поправочный коэффициент')]";
    //------------------------------------------------------------------------------------------------------------------
    // Метка поля [Тип подачи ценового предложения] в таблице [Поправочный коэффициент]
    private static final String CORRECTION_FACTOR_TYPE_LABEL_XPATH =
            "//*/td[@class='s5' and text()='Поправочный коэффициент']";
    //------------------------------------------------------------------------------------------------------------------

    //endregion
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary fieldNames = new Dictionary(); // все поля на странице

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public ApplicationViewPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Номер закупки", PURCHASE_NUMBER_XPATH);
        fieldNames.add("Номер закупки в ЕИС", EIS_NUMBER_XPATH);
        fieldNames.add("Наименование закупки", PURCHASE_NAME_XPATH);
        fieldNames.add("Способ закупки", PURCHASE_METHOD_XPATH);
        fieldNames.add("Организация, осуществляющая закупку", CUSTOMER_ORGANIZATION_NAME_XPATH);
        fieldNames.add("Тип валюты", CURRENCY_TYPE_XPATH);
        fieldNames.add("Номер лота", LOT_NUMBER_XPATH);
        fieldNames.add("Наименование предмета договора", CONTRACT_SUBJECT_NAME_XPATH);
        fieldNames.add("Начальная - максимальная - цена", INITIAL_MAXIMUM_PRICE_XPATH);
        fieldNames.add("Вид обеспечения заявки", TYPE_OF_APPLICATION_SECURITY_XPATH);
        fieldNames.add("Размер обеспечения заявки", APPLICATION_SECURITY_AMOUNT_XPATH);
        fieldNames.add("Страна происхождения товара", COUNTRY_OF_ORIGIN_XPATH);
        fieldNames.add("Наименование документа", DOCUMENT_NAME_XPATH);
        fieldNames.add("Предложение о цене", PRICE_OFFER_FIELD_XPATH);
        fieldNames.add("Тип подачи ценового предложения", PRICE_OFFER_TYPE_FIELD_XPATH);
        fieldNames.add("Номер в реестре аккредитованных участников закупки", NUMBER_IN_REGISTER_FIELD_XPATH);
        fieldNames.add("Фирменное наименование", SUPPLIER_NAME_FIELD_XPATH);
        fieldNames.add("Юридический адрес", LEGAL_ADDRESS_FIELD_XPATH);
        fieldNames.add("Почтовый адрес", MAILING_ADDRESS_FIELD_XPATH);
        fieldNames.add("Номер контактного телефона", PHONE_NUMBER_FIELD_XPATH);
        fieldNames.add("ИНН", INN_FIELD_XPATH);
        fieldNames.add("ФИО сотрудника, подавшего заявку", FIO_FIELD_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет, что информация о Поставщиках (Наименование организации, ИНН, номер в реестре) не отображается.
     * Только для сценария [Аукцион (заявка в двух частях)].
     */
    public void checkThatSuppliersInfoIsHidden() throws Exception {
        System.out.println(
                "[ИНФОРМАЦИЯ]: проверяем страницу [Заявка на участие в аукционе] на отсутствие данных о Поставщике.");

        this.checkPageUrl("Заявка на участие в аукционе от Поставщика 1-я часть");

        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        // Проверяем, что заголовок страницы [Заявка на участие в ...] отображается и доступен для щелчка мышью
        $(By.xpath(PAGE_HEADER_XPATH)).waitUntil(clickable, timeout, polling).click();

        // Проверяем, что поля [Номер в реестре аккредитованных участников закупки], [Фирменное наименование],
        // [ИНН] отсутствуют на этой странице
        $(By.xpath(NUMBER_IN_REGISTER_HEADER_XPATH)).waitUntil(hidden, timeout, polling).shouldNotBe(exist);
        $(By.xpath(SUPPLIER_NAME_HEADER_XPATH)).waitUntil(hidden, timeout, polling).shouldNotBe(exist);
        $(By.xpath(INN_HEADER_XPATH)).waitUntil(hidden, timeout, polling).shouldNotBe(exist);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Проверяет, что информация о Поставщиках (Наименование организации, ИНН, номер в реестре) отображается.
     * Только для сценария [Аукцион (заявка в двух частях)].
     */
    public void checkThatSuppliersInfoIsShown() throws Exception {
        System.out.println(
                "[ИНФОРМАЦИЯ]: проверяем страницу [Заявка на участие в аукционе] на присутствие данных о Поставщике.");

        this.checkPageUrl("Заявка на участие в аукционе от Поставщика 2-я часть",
                "Заявка на участие в аукционе от Поставщика А2Ч - ППИ");

        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        // Проверяем, что заголовок страницы [Заявка на участие в ...] отображается и доступен для щелчка мышью
        $(By.xpath(PAGE_HEADER_XPATH)).waitUntil(clickable, timeout, polling).click();

        // Проверяем, что заголовки полей [Номер в реестре аккредитованных участников закупки], [Фирменное наименование],
        // [ИНН] присутствуют на этой странице
        $(By.xpath(NUMBER_IN_REGISTER_HEADER_XPATH)).waitUntil(exist, timeout, polling).shouldBe(visible);
        $(By.xpath(SUPPLIER_NAME_HEADER_XPATH)).waitUntil(exist, timeout, polling).shouldBe(visible);
        $(By.xpath(INN_HEADER_XPATH)).waitUntil(exist, timeout, polling).shouldBe(visible);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Проверяет отсутствие таблицы [Ценовое предложение] в печатной форме заявки на участие от Поставщика.
     */
    public void checkThatThePriceOfferTableHiddenInApplicationPrintForm() throws Exception {
        System.out.println("[ИНФОРМАЦИЯ]: проверяем отсутствие таблицы [Ценовое предложение] в печатной форме заявки " +
                "на участие от Поставщика.");

        this.checkPageUrl(Arrays.asList("Печатная форма заявки на участие от Поставщика (ПОД)",
                "Печатная форма заявки на участие от Поставщика (ПРОЗ)"));

        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        // Проверяем, что заголовок страницы [Заявка на участие в ...] отображается и доступен для щелчка мышью
        $(By.xpath(PAGE_HEADER_XPATH)).waitUntil(clickable, timeout, polling).click();

        // Проверяем, что заголовок таблицы [Ценовое предложение], метки полей [Предложение о цене],
        // [Тип подачи ценового предложения] в таблице [Ценовое предложение] отсутствуют на этой странице
        $(By.xpath(PRICE_OFFER_TABLE_HEADER_XPATH)).waitUntil(hidden, timeout, polling).shouldNotBe(exist);
        $(By.xpath(PRICE_OFFER_LABEL_XPATH)).waitUntil(hidden, timeout, polling).shouldNotBe(exist);
        $(By.xpath(PRICE_OFFER_TYPE_LABEL_XPATH)).waitUntil(hidden, timeout, polling).shouldNotBe(exist);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Проверяет отсутствие поля в печатной форме заявки.
     *
     * @param fieldName - имя поля
     */
    public void checkThatTheFieldNameHidden(String fieldName) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем отсутствие поля [%s] в печатной форме заявки на участие от " +
                "Поставщика.%n", fieldName);

        this.checkPageUrl(Arrays.asList("Печатная форма заявки на участие от Поставщика (ПОД)",
                "Печатная форма заявки на участие от Поставщика (ПРОЗ)"));
        this.setShortDrivWait();

        $(By.xpath(fieldNames.find(fieldName))).waitUntil(hidden, timeout, polling).shouldNotBe(exist);

        this.setDefltDrivWait();
    }

    /**
     * Проверяет наличие таблицы [Ценовое предложение] в печатной форме заявки на участие от Поставщика.
     */
    public void checkThatThePriceOfferTableVisibleInApplicationPrintForm() throws Exception {
        System.out.println("[ИНФОРМАЦИЯ]: проверяем наличие таблицы [Ценовое предложение] в печатной форме заявки " +
                "на участие от Поставщика.");

        this.checkPageUrl(Arrays.asList("Печатная форма заявки на участие от Поставщика (ПОД)",
                "Печатная форма заявки на участие от Поставщика (ПРОЗ)"));

        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        // Проверяем, что заголовок страницы [Заявка на участие в ...] отображается и доступен для щелчка мышью
        $(By.xpath(PAGE_HEADER_XPATH)).waitUntil(clickable, timeout, polling).click();

        // Проверяем, что заголовок таблицы [Ценовое предложение], метки полей [Предложение о цене],
        // [Тип подачи ценового предложения] в таблице [Ценовое предложение] присутствуют на этой странице
        $(By.xpath(PRICE_OFFER_TABLE_HEADER_XPATH)).waitUntil(exist, timeout, polling).shouldBe(visible);
        $(By.xpath(PRICE_OFFER_LABEL_XPATH)).waitUntil(exist, timeout, polling).shouldBe(visible);
        $(By.xpath(PRICE_OFFER_TYPE_LABEL_XPATH)).waitUntil(exist, timeout, polling).shouldBe(visible);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Проверяет наличие таблицы [Поправочный коэффициент] в печатной форме заявки на участие от Поставщика.
     */
    public void checkThatTheCorrectionFactorTableVisibleInApplicationPrintForm() throws Exception {
        System.out.println("[ИНФОРМАЦИЯ]: проверяем наличие таблицы [Поправочный коэффициент] в печатной форме заявки " +
                "на участие от Поставщика.");

        this.checkPageUrl(Arrays.asList("Печатная форма заявки на участие от Поставщика (ПОД)",
                "Печатная форма заявки на участие от Поставщика (ПРОЗ)"));

        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        // Проверяем, что заголовок страницы [Заявка на участие в ...] отображается и доступен для щелчка мышью
        $(By.xpath(PAGE_HEADER_XPATH)).waitUntil(clickable, timeout, polling).click();

        // Проверяем, что заголовок таблицы [Ценовое предложение], метки полей [Предложение о цене],
        // [Тип подачи ценового предложения] в таблице [Ценовое предложение] присутствуют на этой странице
        $(By.xpath(CORRECTION_FACTOR_TABLE_HEADER_XPATH)).waitUntil(exist, timeout, polling).shouldBe(visible);
        $(By.xpath(CORRECTION_FACTOR_LABEL_XPATH)).waitUntil(exist, timeout, polling).shouldBe(visible);
        $(By.xpath(CORRECTION_FACTOR_TYPE_LABEL_XPATH)).waitUntil(exist, timeout, polling).shouldBe(visible);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
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
     * Сравнивает значения поля с сохраненным значением в параметрах.
     *
     * @param fieldName имя поля
     */
    public void compareFieldValueAndPropertyValue(String fieldName) {
        Dictionary fieldValuesByNames = new Dictionary();
        //--------------------------------------------------------------------------------------------------------------
        fieldValuesByNames.add("Номер закупки", config.getParameter("PurchaseNumber"));
        fieldValuesByNames.add("Номер закупки в ЕИС", config.getParameter("{ns2:item:registrationNumber}"));
        fieldValuesByNames.add("Наименование закупки", config.getParameter("PurchaseName"));
        fieldValuesByNames.add("Номер в реестре аккредитованных участников закупки для ПОСТАВЩИК1ALL223 ЮРИДИЧЕСКОЕ ЛИЦО",
                config.getConfigParameter("NumberInRegisterAccreditedProcurementParticipantsSupplier1"));
        fieldValuesByNames.add("Номер в реестре аккредитованных участников закупки для ПОСТАВЩИК2ALL223 ЮРИДИЧЕСКОЕ ЛИЦО",
                config.getConfigParameter("NumberInRegisterAccreditedProcurementParticipantsSupplier2"));
        //--------------------------------------------------------------------------------------------------------------

        String expectedValue = fieldValuesByNames.find(fieldName);

        String message = String.format("[ОШИБКА]: в поле [%s] не содержится значение [%s]",
                fieldName, expectedValue);

        By field = this.getBy(fieldNames.find(fieldName));

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, expectedValue);
        Assert.assertTrue(message, this.verifyFieldContent(field, expectedValue));
    }

    /**
     * Проверяет значение в поле [Номер в реестре аккредитованных участников закупки]
     *
     * @param supplierName имя поставщика
     */
    public void checkRegisterNumberBySupplierName(String supplierName) {
        Dictionary fieldValuesBySupplierNames = new Dictionary();
        //--------------------------------------------------------------------------------------------------------------
        fieldValuesBySupplierNames.add("ПОСТАВЩИК1ALL223 ЮРИДИЧЕСКОЕ ЛИЦО",
                config.getConfigParameter("NumberInRegisterAccreditedProcurementParticipantsSupplier1"));
        fieldValuesBySupplierNames.add("ПОСТАВЩИК2ALL223 ЮРИДИЧЕСКОЕ ЛИЦО",
                config.getConfigParameter("NumberInRegisterAccreditedProcurementParticipantsSupplier2"));
        //--------------------------------------------------------------------------------------------------------------

        String expectedValue = fieldValuesBySupplierNames.find(supplierName);

        String message = String.format("[ОШИБКА]: в поле [Номер в реестре аккредитованных участников закупки] " +
                "не содержится значение [%s]", expectedValue);

        By field = this.getBy(fieldNames.find("Номер в реестре аккредитованных участников закупки"));

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [Номер в реестре аккредитованных участников закупки] " +
                "- ожидаемое значение [%s].%n", expectedValue);
        Assert.assertTrue(message, this.verifyFieldContent(field, expectedValue));
    }
}
