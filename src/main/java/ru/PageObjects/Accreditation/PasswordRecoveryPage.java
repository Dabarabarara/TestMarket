package ru.PageObjects.Accreditation;

import Helpers.Dictionary;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonPage;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы со страницей [ Восстановление пароля ]
 * ( .kontur.ru/supplier/sso/RecoverPasswordRequest.aspx ).
 * Created by Alexander S. Vasyurenko on 12.04.2021.
 * Updated by Vladimir V. Klochkov on 26.04.2021.
 */

public class PasswordRecoveryPage extends CommonPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Имя пользователя (логин)]
    private static final String USER_LOGIN_ID = "MainContent_txtUserName";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [E-mail]
    private static final String CONTACT_EMAIL_ID = "MainContent_txtEmail";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Ключ защиты]
    private static final String CAPTCHA_FIELD_ID = "MainContent_txtCaptcha";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Восстановить]
    private static final String RESTORE_BUTTON_ID = "MainContent_btnRestorePassword";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Информационное сообщение]
    private static final String INFO_MESSAGE_ID = "pInfoMessage";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Пароль]
    private static final String USER_PASSWORD_ID = "MainContent_txtUserPassword";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Подтверждение пароля]
    private static final String PASSWORD_CONFIRMATION_ID = "MainContent_txtUserConfirmPassword";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Изменить]
    private static final String CHANGE_BUTTON_ID = "MainContent_btnChangePassword";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Вернуться в личный кабинет]
    private static final String RETURN_BUTTON_ID = "hlReturn";
    //------------------------------------------------------------------------------------------------------------------
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary buttonNames = new Dictionary(); // все кнопки на странице
    private final Dictionary fieldNames = new Dictionary();  // все поля на странице

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Адрес электронной почты", CONTACT_EMAIL_ID);
        fieldNames.add("Имя пользователя (логин)", USER_LOGIN_ID);
        fieldNames.add("Ключ защиты", CAPTCHA_FIELD_ID);
        fieldNames.add("Уведомление об отправки письма", INFO_MESSAGE_ID);
        fieldNames.add("Пароль", USER_PASSWORD_ID);
        fieldNames.add("Подтверждение пароля", PASSWORD_CONFIRMATION_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Восстановить", RESTORE_BUTTON_ID);
        buttonNames.add("Изменить", CHANGE_BUTTON_ID);
        buttonNames.add("Вернуться в личный кабинет", RETURN_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Устанавливает значение поля на форме Восстановления пароля Пользователя.
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
     * Производит нажатие на кнопку.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.clickInElementJS(this.getBy(buttonNames.find(buttonName)));
        this.logPressedButtonName(buttonName);
        this.waitForPageLoad();
    }

    /**
     * Проверяет отображение поля или текстовой надписи на странице.
     *
     * @param fieldName имя поля
     */
    public boolean isFieldOrTextDisplayed(String fieldName) {
        SelenideElement field = $(this.getBy(fieldNames.find(fieldName)));
        System.out.printf("[ИНФОРМАЦИЯ]: проверка отображения  поля или текстовой надписи [%s] на странице.%n",
                fieldName);

        return field.isDisplayed();
    }

    /**
     * Проверяет значение поля.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение, которое должно содержаться в поле
     */
    public void verifyFieldContent(String fieldName, String value) {
        String message = String.format("[ОШИБКА]: значение [%s] не содержится в поле [%s].", value, fieldName);

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        SelenideElement field = $(this.getBy(fieldNames.find(fieldName)));
        field.waitUntil(exist, timeout, polling).shouldBe(visible);
        if (value.trim().isEmpty())
            Assert.assertTrue(message, field.getText().trim().isEmpty());
        else
            Assert.assertTrue(message, field.getText().contains(value));
    }
}
