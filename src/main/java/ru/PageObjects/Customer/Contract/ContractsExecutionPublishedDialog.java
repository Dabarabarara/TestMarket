package ru.PageObjects.Customer.Contract;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Опубликовано].
 * Created by Vladimir V. Klochkov on 21.12.2020.
 * Updated by Vladimir V. Klochkov on 25.02.2021.
 */
public class ContractsExecutionPublishedDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок диалогового окна [Опубликовано]
    private static final String COMMON_CONFIRM_WINDOW_TITLE_ID = "CommonConfirmWindow_wnd_title";
    //------------------------------------------------------------------------------------------------------------------
    // Текст диалогового окна [Опубликовано]
    private static final String COMMON_CONFIRM_TEXT_ID = "CommonConfirmText";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Перейти к карточке сведений]
    private static final String COMMON_CONFIRM_WINDOW_OK_ID = "CommonConfirmWindowOk";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Перейти к реестру договоров]
    private static final String COMMON_CONFIRM_WINDOW_CANCEL_ID = "CommonConfirmWindowCancel";
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
    public ContractsExecutionPublishedDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Заголовок диалогового окна", COMMON_CONFIRM_WINDOW_TITLE_ID);
        fieldNames.add("Текст диалогового окна", COMMON_CONFIRM_TEXT_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Перейти к карточке сведений", COMMON_CONFIRM_WINDOW_OK_ID);
        buttonNames.add("Перейти к реестру договоров", COMMON_CONFIRM_WINDOW_CANCEL_ID);
        buttonNames.add("Перейти к карточке договора", COMMON_CONFIRM_WINDOW_CANCEL_ID);
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
     * Нажимает на кнопку [Перейти к карточке сведений] в диалоговом окне [Опубликовано].
     */
    public void goToExecutionViewCard() throws Exception {
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        // Ждем появления диалогового окна [Опубликовано]
        $(By.id(COMMON_CONFIRM_WINDOW_TITLE_ID)).waitUntil(visible, longtime, polling);

        // Нажимаем на кнопку [Перейти к карточке сведений]
        this.logButtonNameToPress("Перейти к карточке сведений");
        $(By.id(COMMON_CONFIRM_WINDOW_OK_ID)).waitUntil(clickable, longtime, polling);
        this.clickInElementJS(By.id(COMMON_CONFIRM_WINDOW_OK_ID));
        this.logPressedButtonName("Перейти к карточке сведений");

        // Ждем исчезновения диалогового окна [Опубликовано]
        $(By.id(COMMON_CONFIRM_WINDOW_TITLE_ID)).waitUntil(disappears, longtime, polling);

        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }
}
