package ru.PageObjects.Customer.Notice;

import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Опубликовано для МСП закупки].
 * Created by Vladimir V. Klochkov on 31.03.2020.
 * Updated by Vladimir V. Klochkov on 31.03.2020.
 */
public class PublishedSMBAngularDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок диалогового окна [Опубликовано]
    private static final String NOTICE_PUBLISHED_DIALOG_WINDOW_TITLE_ID =
            "//kendo-dialog[contains(.,'Опубликовано')]//div[contains(text(),'Опубликовано')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Перейти к карточке закупки]
    private static final String GO_TO_PURCHASE_CARD_BUTTON_ID = "goToViewCard";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public PublishedSMBAngularDialog(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Нажимает на кнопку [Перейти к карточке закупки].
     */
    void goToPurchaseCard() throws Exception {
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        // Ждем появления диалогового окна [Опубликовано]
        $(this.getBy(NOTICE_PUBLISHED_DIALOG_WINDOW_TITLE_ID)).waitUntil(visible, longtime, polling);

        // Нажимаем на кнопку [Перейти к карточке закупки]
        this.logButtonNameToPress("Перейти к карточке закупки");
        $(this.getBy(GO_TO_PURCHASE_CARD_BUTTON_ID)).waitUntil(clickable, longtime, polling);
        this.clickInElementJS(this.getBy(GO_TO_PURCHASE_CARD_BUTTON_ID));
        this.logPressedButtonName("Перейти к карточке закупки");

        // Ждем исчезновения диалогового окна [Опубликовано]
        $(this.getBy(NOTICE_PUBLISHED_DIALOG_WINDOW_TITLE_ID)).waitUntil(disappears, longtime, polling);

        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }
}
