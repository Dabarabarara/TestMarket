package ru.PageObjects.Customer.Notice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Опубликовано].
 * Created by Vladimir V. Klochkov on 06.04.2016.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class PublishedDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок диалогового окна [Опубликовано]
    private static final String NOTICE_PUBLISHED_DIALOG_WINDOW_TITLE_ID = "CommonConfirmWindow_wnd_title";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Перейти к карточке закупки]
    private static final String GO_TO_PURCHASE_CARD_BUTTON_ID = "CommonConfirmWindowOk";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public PublishedDialog(WebDriver driver) {
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
        $(By.id(NOTICE_PUBLISHED_DIALOG_WINDOW_TITLE_ID)).waitUntil(visible, longtime, polling);

        // Нажимаем на кнопку [Перейти к карточке закупки]
        this.logButtonNameToPress("Перейти к карточке закупки");
        $(By.id(GO_TO_PURCHASE_CARD_BUTTON_ID)).waitUntil(clickable, longtime, polling);
        this.clickInElementJS(By.id(GO_TO_PURCHASE_CARD_BUTTON_ID));
        this.logPressedButtonName("Перейти к карточке закупки");

        // Ждем исчезновения диалогового окна [Опубликовано]
        $(By.id(NOTICE_PUBLISHED_DIALOG_WINDOW_TITLE_ID)).waitUntil(disappears, longtime, polling);

        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }
}
