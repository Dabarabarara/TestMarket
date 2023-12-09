package ru.PageObjects.CommonDialogs;

import Helpers.Dictionary;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonPage;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Сохранено].
 * Created by Vladimir V. Klochkov on 30.08.2016.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class SavedDialog extends CommonPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     *******************************************************************************************************************/
    //==================================================================================================================
    // Заголовок диалогового окна [Сохранено]
    private static final String COMMON_CONFIRM_WINDOW_WND_TITLE_ID = "CommonConfirmWindow_wnd_title";
    //==================================================================================================================
    // Текст диалогового окна [Сохранено]
    private static final String COMMON_CONFIRM_TEXT_ID = "CommonConfirmText";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Продолжить (формирование черновика)]
    private static final String COMMON_CONFIRM_WINDOW_OK_ID = "CommonConfirmWindowOk";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Перейти к ...]
    private static final String COMMON_CONFIRM_WINDOW_CANCEL_ID = "CommonConfirmWindowCancel";
    //==================================================================================================================
    // Заголовок диалогового окна [Проверка формы]
    private static final String COMMON_ERROR_WINDOW_WND_TITLE_ID = "CommonErrorWindow_wnd_title";
    //==================================================================================================================
    // Текст сообщения [Transaction not connected, or was disconnected] в диалоговом окне [Проверка формы]
    private static final String ERROR_MESSAGE_XPATH =
            "//span[contains(.,'Transaction not connected, or was disconnected')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [OK] в диалоговом окне [Проверка формы]
    private static final String OK_BUTTON_XPATH = "//*[@id='CommonErrorWindow']/div[4]/button";
    //==================================================================================================================

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary fieldNames = new Dictionary();  // все поля диалогового окна
    private final Dictionary buttonNames = new Dictionary(); // все кнопки диалогового окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public SavedDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Заголовок диалогового окна", COMMON_CONFIRM_WINDOW_WND_TITLE_ID);
        fieldNames.add("Текст диалогового окна", COMMON_CONFIRM_TEXT_ID);
        fieldNames.add("Заголовок диалогового окна [Проверка формы]", COMMON_ERROR_WINDOW_WND_TITLE_ID);
        fieldNames.add("Текст диалогового окна [Проверка формы]", ERROR_MESSAGE_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Продолжить (формирование черновика)", COMMON_CONFIRM_WINDOW_OK_ID);
        buttonNames.add("Перейти к ...", COMMON_CONFIRM_WINDOW_CANCEL_ID);
        buttonNames.add("OK", OK_BUTTON_XPATH);
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
     * Делает щелчок мышью по указанной кнопке.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        $(this.getBy(COMMON_CONFIRM_WINDOW_WND_TITLE_ID)).waitUntil(clickable, timeout, polling);
        this.logButtonNameToPress(buttonName);
        this.clickInElementJS(this.getBy(buttonNames.find(buttonName)));
        this.logPressedButtonName(buttonName);
        this.waitForPageLoad(5);
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        $(this.getBy(COMMON_CONFIRM_WINDOW_WND_TITLE_ID)).shouldBe(disappear);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }
}
