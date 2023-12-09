package ru.PageObjects.Customer;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Просмотр подписи]
 * (customer/lk/RegisterOfInquiries/ViewInquiries/).
 * Created by Kirill G. Rydzyvylo on 13.12.2019.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class FileSignatureWindowDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов диалога.
     ******************************************************************************************************************/
    //region Заголовок диалога

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Текст заголовка окна]
    private static final String DIALOG_TITLE_ID = "FileSignatureWindow_wnd_title";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Раздел [Информация о файле]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Имя файла]
    private static final String SIGNATURE_FILE_URL_ID = "SignatureFileUrl";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Адрес страницы]
    private static final String URL_XPATH = "//*[@id='SignatureFileUrl']/following-sibling::a[1]";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Раздел [Сертификат]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сертификат]
    private static final String CERTIFICATE_TITLE_XPATH =
            "//div[@id = 'FileSignatureWindow']//h4[@class='ra-well-title' and text()='Сертификат']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата выдачи сертификата]
    private static final String SIGNATURE_NOT_BEFORE_ID = "SignatureNotBefore";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок действия сертификата]
    private static final String SIGNATURE_NOT_AFTER_ID = "SignatureNotAfter";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер сертификата]
    private static final String SIGNATURE_CERT_NUMBER_ID = "SignatureCertNumber";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Раздел [Подписавший]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Подписавший]
    private static final String SIGNATORY_SECTION_XPATH =
            "//div[@id = 'FileSignatureWindow']//h4[@class='ra-well-title' and text()='Подписавший']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Имя]
    private static final String SIGNATURE_WINDOW_NAME_ID = "SignatureWindowName";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Фамилия]
    private static final String SIGNATURE_WINDOW_SURNAME_ID = "SignatureWindowSurname";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Организация]
    private static final String SIGNATURE_WINDOW_ORGANIZATION_ID = "SignatureWindowOrganization";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Роль]
    private static final String SIGNATURE_WINDOW_ROLE_ID = "SignatureWindowRole";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Email]
    private static final String SIGNATURE_WINDOW_EMAIL_ID = "SignatureWindowEmail";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Департамент]
    private static final String SIGNATURE_WINDOW_DEPARTMENT_ID = "SignatureWindowDepartament";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Местоположение]
    private static final String SIGNATURE_WINDOW_LOCATION_ID = "SignatureWindowLocation";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [ИНН]
    private static final String SIGNATURE_WINDOW_INN_ID = "SignatureWindowInn";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [ОГРН]
    private static final String SIGNATURE_WINDOW_OGRN_ID = "SignatureWindowOgrn";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [СНИЛС]
    private static final String SIGNATURE_WINDOW_SNILS_ID = "SignatureWindowSnils";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    // region Блок кнопок внизу формы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Печать]
    private static final String SIGNATURE_WINDOW_BUTTON_PRINT_XPATH =
            "//div[@id = 'FileSignatureWindow']/input[contains(@value,'Печать')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отмена]
    private static final String SIGNATURE_WINDOW_BUTTON_CANCEL_XPATH =
            "//div[@id = 'FileSignatureWindow']/input[contains(@value,'Отмена')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary blockNames = new Dictionary();  // основные разделы страницы
    private final Dictionary fieldNames = new Dictionary();  // все поля на странице
    private final Dictionary buttonNames = new Dictionary(); // все кнопки на странице

    /*******************************************************************************************************************
     * Конструктор класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public FileSignatureWindowDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Текст заголовка окна", DIALOG_TITLE_ID);
        blockNames.add("Сертификат", CERTIFICATE_TITLE_XPATH);
        blockNames.add("Подписавший", SIGNATORY_SECTION_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Текст заголовка окна", SIGNATURE_FILE_URL_ID);
        fieldNames.add("Адрес страницы", URL_XPATH);
        fieldNames.add("Дата выдачи сертификата", SIGNATURE_NOT_BEFORE_ID);
        fieldNames.add("Срок действия сертификата", SIGNATURE_NOT_AFTER_ID);
        fieldNames.add("Номер сертификата", SIGNATURE_CERT_NUMBER_ID);
        fieldNames.add("Имя", SIGNATURE_WINDOW_NAME_ID);
        fieldNames.add("Фамилия", SIGNATURE_WINDOW_SURNAME_ID);
        fieldNames.add("Организация", SIGNATURE_WINDOW_ORGANIZATION_ID);
        fieldNames.add("Роль", SIGNATURE_WINDOW_ROLE_ID);
        fieldNames.add("Email", SIGNATURE_WINDOW_EMAIL_ID);
        fieldNames.add("Департамент", SIGNATURE_WINDOW_DEPARTMENT_ID);
        fieldNames.add("Местоположение", SIGNATURE_WINDOW_LOCATION_ID);
        fieldNames.add("ИНН", SIGNATURE_WINDOW_INN_ID);
        fieldNames.add("ОГРН", SIGNATURE_WINDOW_OGRN_ID);
        fieldNames.add("СНИЛС", SIGNATURE_WINDOW_SNILS_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Печать", SIGNATURE_WINDOW_BUTTON_PRINT_XPATH);
        buttonNames.add("Отмена", SIGNATURE_WINDOW_BUTTON_CANCEL_XPATH);
        //--------------------------------------------------------------------------------------------------------------

    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Общие методы для работы с элементами этой страницы

    /**
     * Проверяет наличие диалогового окна [Просмотр подписи].
     *
     * @return диалоговое окно [Просмотр подписи]
     */
    public FileSignatureWindowDialog ifDialogOpened() throws Exception {
        this.waitLoadingImage(5);
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Просмотр подписи] не обнаружено",
                $(this.getBy(blockNames.find("Текст заголовка окна"))).waitUntil(exist, timeout, polling).
                        isDisplayed());

        return this;
    }

    /**
     * Проверяет значение поля.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение, которое должно содержаться в поле
     * @return диалоговое окно [Просмотр подписи]
     */
    public FileSignatureWindowDialog verifyField(String fieldName, String value) {
        String message = String.format("[ОШИБКА]: значение [%s] не содержится в поле [%s]", value, fieldName);

        By field = this.getBy(fieldNames.find(fieldName));

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n",
                fieldName, value);
        if ($(field).isDisplayed()) Assert.assertTrue(message, this.verifyFieldContent(field, value));
        else System.err.printf("[ОШИБКА]: поле [%s] не отображается на форме.%n", fieldName);

        return this;
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
        this.waitLoadingImage();
    }

    //endregion
}
