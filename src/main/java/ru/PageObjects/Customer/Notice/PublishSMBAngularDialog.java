package ru.PageObjects.Customer.Notice;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Публикация извещения о МСП закупке].
 * Created by Vladimir V. Klochkov on 31.03.2020.
 * Updated by Vladimir V. Klochkov on 31.03.2020.
 */
class PublishSMBAngularDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок диалогового окна [Публикация извещения]
    private static final String PUBLISH_NOTICE_DIALOG_WINDOW_TITLE_ID =
            "//kendo-dialog//div[contains(text(),'Публикация извещения')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Продолжить] в диалоговом окне [Публикация извещения]
    private static final String CONTINUE_PUBLISH_BUTTON_XPATH =
            "publication";
    //------------------------------------------------------------------------------------------------------------------
    // Текстовое поле [Обоснование внесения изменения] в диалоговом окне [Публикация извещения]
    private static final String MODIFICATION_INFO_ID = "//kendo-dialog//textarea";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    PublishSMBAngularDialog(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие поля [Обоснование внесения изменения] и заполняет его, если оно отображается, затем
     * нажимает на кнопку [Продолжить].
     */
    void continueThePublication() {
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        // Ждем появления диалогового окна [Публикация извещения]
        $(this.getBy(PUBLISH_NOTICE_DIALOG_WINDOW_TITLE_ID)).waitUntil(clickable, longtime, polling).click();

        // Проверяем наличие поля [Обоснование внесения изменения] и заполняем его, если оно отображается
        this.detectAndFillModificationInfoField();

        // Нажимаем на кнопку [Продолжить]
        this.clickOnContinueButton();

        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Нажимает на кнопку [Продолжить].
     */
    private void clickOnContinueButton() {
        SelenideElement button = $(this.getBy(CONTINUE_PUBLISH_BUTTON_XPATH));
        if (button.isDisplayed()) {
            this.logButtonNameToPress("Продолжить");
            button.click();
            this.logPressedButtonName("Продолжить");
            button.waitUntil(disappears, longtime, polling);
        }
    }

    /**
     * Проверяет наличие поля [Обоснование внесения изменения] и заполняет его, если оно отображается.
     */
    private void detectAndFillModificationInfoField() {
        SelenideElement field = $(this.getBy(MODIFICATION_INFO_ID));
        String fieldName = "Обоснование внесения изменения";
        String value = "Очень обоснованное обоснование.";

        if (field.isDisplayed()) {
            field.click();
            field.sendKeys(value);
            this.logFilledFieldName(fieldName, value);
        }
    }
}
