package ru.PageObjects.CommonDialogs;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Предупреждение].
 * Created by Vladimir V. Klochkov on 14.04.2016.
 * Updated by Vladimir V. Klochkov on 25.02.2021.
 */
public class WarningDialog extends CommonPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок диалогового окна [Предупреждение]
    private static final String COMMON_CONFIRM_WINDOW_WND_TITLE_ID = "CommonConfirmWindow_wnd_title";
    //------------------------------------------------------------------------------------------------------------------
    // Текст диалогового окна [Предупреждение]
    private static final String COMMON_CONFIRM_TEXT_ID = "CommonConfirmText";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Продолжить] в диалоговом окне [Предупреждение]
    private static final String COMMON_CONFIRM_WINDOW_OK_ID = "CommonConfirmWindowOk";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отмена] в диалоговом окне [Предупреждение]
    private static final String COMMON_CONFIRM_WINDOW_CANCEL_ID = "CommonConfirmWindowCancel";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Да] в диалоговом окне [Предупреждение]
    private static final String COMMON_CONFIRM_WINDOW_YES_ID = "CommonConfirmWindowOk";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary fieldNames = new Dictionary();  // все поля диалогового окна
    private final Dictionary buttonNames = new Dictionary(); // все кнопки диалогового окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public WarningDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Заголовок диалогового окна", COMMON_CONFIRM_WINDOW_WND_TITLE_ID);
        fieldNames.add("Текст диалогового окна", COMMON_CONFIRM_TEXT_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Продолжить", COMMON_CONFIRM_WINDOW_OK_ID);
        buttonNames.add("Отмена", COMMON_CONFIRM_WINDOW_CANCEL_ID);
        buttonNames.add("Да", COMMON_CONFIRM_WINDOW_YES_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
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
     * Проверяет, что кнопка отображается на странице и разрешена (visible, enabled).
     *
     * @param controlName имя элемента
     */
    public void isControlClickable(String controlName) {
        this.isControlClickable(controlName, $(this.getBy(buttonNames.find(controlName))));
    }

    /**
     * Нажимает кнопку [Продолжить] в диалоговом окне [Предупреждение].
     */
    public void clickOnContinueButton() throws Exception {

        // Ждем появления диалогового окна [Предупреждение]
        $(By.id(COMMON_CONFIRM_WINDOW_WND_TITLE_ID)).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(1);

        // Нажимаем на кнопку [Продолжить] в диалоговом окне [Предупреждение]
        this.logButtonNameToPress("Продолжить");
        $(By.id(COMMON_CONFIRM_WINDOW_OK_ID)).waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName("Продолжить");
        TimeUnit.SECONDS.sleep(35);
    }

    /**
     * Нажимает кнопку [Да] в диалоговом окне [Предупреждение].
     */
    public void clickOnOkButton() throws Exception {

        // Ждем появления диалогового окна [Предупреждение]
        $(By.id(COMMON_CONFIRM_WINDOW_WND_TITLE_ID)).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(1);

        // Нажимаем на кнопку [Да] в диалоговом окне [Предупреждение]
        this.logButtonNameToPress("Да");
        $(By.id(COMMON_CONFIRM_WINDOW_YES_ID)).waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName("Да");
        TimeUnit.SECONDS.sleep(3);
    }
}
