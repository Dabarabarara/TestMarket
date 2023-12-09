package ru.PageObjects.Accreditation;

import Helpers.Dictionary;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы со страницей [Информация о пользователе] ( Согласие на обработку даных )
 * ( .kontur.ru/supplier/lk/Accreditation/DataProccessingAgreement.aspx?IsCustomer=true ).
 * Created by Kirill G. Rydzyvylo on 25.03.2020.
 * Updated by Vladimir V. Klochkov on 25.02.2021.
 */

public class DataProcessingAgreementPage extends CommonPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     *******************************************************************************************************************/

    // region Раздел [Информация о пользователе]

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Выбрать из списка]
    private static final String SELECT_CERTIFICATE_BUTTON_ID =
            "BaseMainContent_MainContent_ucCurrentSelectCertificate_btnSelectCert";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Фамилия]
    private static final String LAST_NAME_ID =
            "BaseMainContent_MainContent_txtLastName";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Имя]
    private static final String FIRST_NAME_ID =
            "BaseMainContent_MainContent_txtFirstName";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Название организации]
    private static final String ORGANIZATION_NAME_ID =
            "BaseMainContent_MainContent_txtOrganizationName";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Информация о пользователе]

    // endregion

    // region Раздел [Защита от спама]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Ключ защиты]
    private static final String CAPTCHA_FIELD_ID =
            "BaseMainContent_MainContent_txtCaptcha";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Управляющие кнопки в нижней части страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отмена]
    private static final String CANCEL_BUTTON_ID =
            "BaseMainContent_MainContent_lbtCancel";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Подписать и отправить]
    private static final String AGREEMENT_BUTTON_ID =
            "BaseMainContent_MainContent_mvgbAgreement";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary fieldNames = new Dictionary();  // все поля на странице
    private final Dictionary buttonNames = new Dictionary(); // все кнопки на странице

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public DataProcessingAgreementPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Фамилия", LAST_NAME_ID);
        fieldNames.add("Имя", FIRST_NAME_ID);
        fieldNames.add("Название организации", ORGANIZATION_NAME_ID);
        fieldNames.add("Ключ защиты", CAPTCHA_FIELD_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Выбрать из списка", SELECT_CERTIFICATE_BUTTON_ID);
        buttonNames.add("Отмена", CANCEL_BUTTON_ID);
        buttonNames.add("Подписать и отправить", AGREEMENT_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы
     ******************************************************************************************************************/
    // region Раздел [Общие сведения о закупке]

    /**
     * Проверка поля на невозможность редактирвоания
     *
     * @param fieldName имя поля
     */
    public void checkDisableField(String fieldName) {
        $(this.getBy(fieldNames.find(fieldName))).shouldBe(Condition.visible, Condition.disabled);
    }

    // endregion

    // region Раздел [Защита от спама]

    /**
     * Установит значение поля [Ключ защиты] в форме первичной аккредитации Пользователя.
     */
    public void setCaptchaField() {
        $(this.getBy(CAPTCHA_FIELD_ID)).waitUntil(visible, timeout, polling).
                sendKeys(config.getConfigParameter("Captcha"));
    }

    // endregion

    // region Общие методы для работы с элементами этой страницы

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

    //endregion
}
