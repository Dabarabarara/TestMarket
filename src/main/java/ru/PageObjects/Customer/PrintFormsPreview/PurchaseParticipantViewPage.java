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
 * Класс описывающий страницу [Сведения об Участнике закупки]
 * ( .kontur.ru/customer/lk/Iframe/TradeLotApplicationPrintFormPurchaserInfo? ).
 * Created by Kirill G. Rydzyvylo on 28.09.2019.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class PurchaseParticipantViewPage extends CommonCustomerPage {

    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы [Заявка на участие в ...]
//    private static final String PAGE_HEADER_XPATH = "//*/td[contains(.,'Сведения об Участнике закупки')]";
    //------------------------------------------------------------------------------------------------------------------

    //region Раздел [Сведения об аккредитации участника закупки]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер реестровой записи]
    private static final String REGISTRY_ENTRY_NUMBER_XPATH =
            "//tr/td[text()='Номер реестровой записи:']/../td[@class='s3']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата направления участнику закупки уведомления о принятии решения об аккредитации]
    private static final String ACCREDITATION_DATE_XPATH =
            "//tr/td[contains(.,'Дата направления участнику закупки уведомления')]/../td[@class='s3']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата прекращения действия аккредитации участника]
    private static final String END_DATE_ACCREDITATION_XPATH =
            "//tr/td[contains(.,'Дата направления участнику закупки уведомления')]/../td[@class='s3']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус]
    private static final String STATUS_XPATH = "//tr/td[text()='Статус:']/../td[@class='s3']";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Раздел [Основные сведения об организации]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование]
    private static final String NAME_XPATH = "//tr/td[text()='Наименование:']/../td[@class='s3']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Страна]
    private static final String COUNTRY_XPATH = "//tr/td[text()='Страна:']/../td[@class='s3']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [ИНН]
    private static final String INN_XPATH = "//tr/td[text()='ИНН:']/../td[@class='s3']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [ОГРН]
    private static final String OGRN_XPATH = "//tr/td[text()='ОГРН:']/../td[@class='s3']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [КПП]
    private static final String KPP_XPATH = "//tr/td[text()='КПП:']/../td[@class='s3']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дополнительные адреса электронной почты]
    private static final String ADDITIONAL_EMAIL_ADDRESS_XPATH =
            "//tr/td[text()='Дополнительные адреса электронной почты:']/../td[@class='s3']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Телефон]
    private static final String TELEPHONE_NUMBER_XPATH = "(//tr/td[text()='Телефон:']/../td[@class='s3'])[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Факс]
    private static final String FAX_XPATH = "(//tr/td[text()='Факс:']/../td[@class='s3'])[1]";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Раздел [Банковские реквизиты счёта участника закупки для перевода денежных средств]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Расчетный счет]
    private static final String PAYMENT_ACCOUNT_XPATH = "//tr/td[text()='Расчетный счет:']/../td[@class='s3']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Лицевой счет]
    private static final String PERSONAL_ACCOUNT_XPATH = "//tr/td[text()='Лицевой счет:']/../td[@class='s3']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Корреспондентский счет]
    private static final String CORRESPONDENT_ACCOUNT_XPATH =
            "//tr/td[text()='Корреспондентский счет:']/../td[@class='s3']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Бик]
    private static final String BIK_XPATH = "//tr/td[text()='Бик:']/../td[@class='s3']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец  [Название банка]
    private static final String BANK_NAME_XPATH = "//tr/td[text()='Название банка:']/../td[@class='s3']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец  [Адрес банка]
    private static final String BANK_ADDRESS_XPATH = "//tr/td[text()='Адрес банка:']/../td[@class='s3']";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Раздел [Сведения о лице, подписавшем заявку на аккредитацию]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Фамилия, Имя, Отчество]
    private static final String FIO_SIGNED_THE_ACCREDITATION_APPLICATION_XPATH =
            "(//tr/td[text()='Фамилия, Имя, Отчество:']/../td[@class='s3'])[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Адрес электронной почты]
    private static final String EMAIL_ADDRESS_SIGNED_THE_ACCREDITATION_APPLICATION_XPATH =
            "(//tr/td[text()='Адрес электронной почты:']/../td[@class='s3'])[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Телефон]
    private static final String TELEPHONE_NUMBER_SIGNED_THE_ACCREDITATION_APPLICATION_XPATH =
            "(//tr/td[text()='Телефон:']/../td[@class='s3'])[2]";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Таблица [Сведения о лице, подавшем заявку на участие]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Фамилия, Имя, Отчество]
    private static final String FIO_APPLIED_FOR_PARTICIPATION_XPATH =
            "(//tr/td[text()='Фамилия, Имя, Отчество:']/../td[@class='s3'])[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Адрес электронной почты]
    private static final String EMAIL_ADDRESS_APPLIED_FOR_PARTICIPATION_XPATH =
            "(//tr/td[text()='Адрес электронной почты:']/../td[@class='s3'])[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Телефон]
    private static final String TELEPHONE_NUMBER__APPLIED_FOR_PARTICIPATION_XPATH =
            "(//tr/td[text()='Телефон:']/../td[@class='s3'])[3]";
    //------------------------------------------------------------------------------------------------------------------

    //endregion


    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary fieldNames = new Dictionary();         // все поля на странице

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public PurchaseParticipantViewPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Номер реестровой записи", REGISTRY_ENTRY_NUMBER_XPATH);
        fieldNames.add("Дата направления участнику закупки уведомления о принятии решения об аккредитации",
                ACCREDITATION_DATE_XPATH);
        fieldNames.add("Дата прекращения действия аккредитации участника", END_DATE_ACCREDITATION_XPATH);
        fieldNames.add("Статус", STATUS_XPATH);
        fieldNames.add("Наименование", NAME_XPATH);
        fieldNames.add("Страна", COUNTRY_XPATH);
        fieldNames.add("ИНН", INN_XPATH);
        fieldNames.add("ОГРН", OGRN_XPATH);
        fieldNames.add("КПП", KPP_XPATH);
        fieldNames.add("Дополнительные адреса электронной почты", ADDITIONAL_EMAIL_ADDRESS_XPATH);
        fieldNames.add("Телефон организации", TELEPHONE_NUMBER_XPATH);
        fieldNames.add("Факс", FAX_XPATH);
        fieldNames.add("Расчетный счет", PAYMENT_ACCOUNT_XPATH);
        fieldNames.add("Лицевой счет", PERSONAL_ACCOUNT_XPATH);
        fieldNames.add("Корреспондентский счет", CORRESPONDENT_ACCOUNT_XPATH);
        fieldNames.add("Бик", BIK_XPATH);
        fieldNames.add("Название банка", BANK_NAME_XPATH);
        fieldNames.add("Адрес банка", BANK_ADDRESS_XPATH);
        fieldNames.add("ФИО лица, подписавшего заявку на аккредитацию", FIO_SIGNED_THE_ACCREDITATION_APPLICATION_XPATH);
        fieldNames.add("Адрес электронной почты лица, подписавшего заявку на аккредитацию",
                EMAIL_ADDRESS_SIGNED_THE_ACCREDITATION_APPLICATION_XPATH);
        fieldNames.add("Телефон лица, подписавшего заявку на аккредитацию",
                TELEPHONE_NUMBER_SIGNED_THE_ACCREDITATION_APPLICATION_XPATH);
        fieldNames.add("ФИО лица, подавшем заявку на участие", FIO_APPLIED_FOR_PARTICIPATION_XPATH);
        fieldNames.add("Адрес электронной почты лица, подавшем заявку на участие",
                EMAIL_ADDRESS_APPLIED_FOR_PARTICIPATION_XPATH);
        fieldNames.add("Телефон лица, подавшем заявку на участие", TELEPHONE_NUMBER__APPLIED_FOR_PARTICIPATION_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
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
     * Проверяет значение в поле [Номер реестровой записи]
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

        String message = String.format("[ОШИБКА]: в поле [Номер реестровой записи] " +
                "не содержится значение [%s]", expectedValue);

        By field = this.getBy(fieldNames.find("Номер реестровой записи"));

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [Номер реестровой записи] - ожидаемое значение [%s].%n",
                expectedValue);

        Assert.assertTrue(message, this.verifyFieldContent(field, expectedValue));
    }
}
