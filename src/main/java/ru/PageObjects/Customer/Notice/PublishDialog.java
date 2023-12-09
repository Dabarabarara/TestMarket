package ru.PageObjects.Customer.Notice;

import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Публикация извещения].
 * Created by Vladimir V. Klochkov on 06.04.2016.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
class PublishDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Опубликовать] на родительской форме [Формирование черновика извещения конкурса]
    private static final String PUBLISH_BUTTON_XPATH = "//button[contains(.,'Опубликовать')]";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок диалогового окна [Публикация извещения]
    private static final String PUBLISH_NOTICE_DIALOG_WINDOW_TITLE_ID = "publishButtons_wnd_title";
    //------------------------------------------------------------------------------------------------------------------
    // Текстовая область [Обоснование внесения изменения] в диалоговом окне [Публикация извещения]
    private static final String MODIFICATION_INFO_ID = "ModificationInfo";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Продолжить] в диалоговом окне [Публикация извещения]
    private static final String CONTINUE_PUBLISH_BUTTON_XPATH =
            "//button[@data-bind='click: startPublish' or @data-bind='click: publishTrade']";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок диалогового окна [Проверка формы]
    private static final String CHECKING_FORM_DIALOG_WINDOW_TITLE_ID = "CommonErrorWindow_wnd_title";
    //------------------------------------------------------------------------------------------------------------------
    // Текст сообщения [Transaction not connected, or was disconnected] в диалоговом окне [Проверка формы]
    private static final String ERROR_MESSAGE_XPATH =
            "//span[contains(.,'Transaction not connected, or was disconnected')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [OK] в диалоговом окне [Проверка формы]
    private static final String OK_BUTTON_XPATH = "//*[@id='CommonErrorWindow']/div[4]/button";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Константы класса.
     ******************************************************************************************************************/
    private static final String transactionError = "[Transaction not connected, or was disconnected].";

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    PublishDialog(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Нажимает кнопку [Продолжить].
     */
    void continueThePublication() {
        SelenideElement dialogTitle = $(By.id(PUBLISH_NOTICE_DIALOG_WINDOW_TITLE_ID));
        SelenideElement publishButton = $(By.xpath(PUBLISH_BUTTON_XPATH));

        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        // Ждем появления диалогового окна [Публикация извещения]
        dialogTitle.waitUntil(clickable, longtime, polling).click();

        // Нажимаем на кнопку [Продолжить]
        this.detectAndFillModificationInfoField();
        this.clickOnContinueButton();
        //==============================================================================================================
        // Этот код является костылем сделанным с целью обойти неоднократно повторяющееся сообщение об ошибке
        int i = 0;
        while ($(By.id(CHECKING_FORM_DIALOG_WINDOW_TITLE_ID)).isDisplayed() && i < 10) {
            // Проверяем наличие окна с сообщением об ошибке и закрываем его, если оно отображается ( кнопкой [OK] )
            this.detectAndCloseTransactionErrorMessageWindow();

            // Нажимаем на кнопку [Опубликовать] на родительской форме [Формирование черновика извещения]
            publishButton.waitUntil(visible, longtime, polling).scrollTo();
            this.logButtonNameToPress("Опубликовать");
            publishButton.click();
            this.logPressedButtonName("Опубликовать");

            // Ждем появления диалогового окна [Публикация извещения]
            dialogTitle.waitUntil(visible, longtime, polling).click();

            // Нажимаем на кнопку [Продолжить]
            this.detectAndFillModificationInfoField();
            this.clickOnContinueButton();
            i++;
        }
        Assert.assertTrue(">>> Не удалось обойти сообщение об ошибке: " + transactionError, i < 10);
        //==============================================================================================================
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Нажимает кнопку [Продолжить].
     */
    private void clickOnContinueButton() {
        SelenideElement button = $(By.xpath(CONTINUE_PUBLISH_BUTTON_XPATH));
        if (button.isDisplayed()) {
            this.logButtonNameToPress("Продолжить");
            button.click();
            this.logPressedButtonName("Продолжить");
            button.waitUntil(disappears, longtime, polling);
        }
    }

    /**
     * Проверяет наличие окна с сообщением об ошибке и закрывает его, если оно отображается ( кнопкой [OK] ).
     */
    private void detectAndCloseTransactionErrorMessageWindow() {
        SelenideElement button = $(By.xpath(OK_BUTTON_XPATH));
        if ($(By.id(CHECKING_FORM_DIALOG_WINDOW_TITLE_ID)).isDisplayed()) {
            $(By.xpath(ERROR_MESSAGE_XPATH)).shouldBe(visible);
            System.err.println(StringUtils.repeat("#", 150));
            System.err.println("[ОШИБКА]: Обнаружено сообщение об ошибке: " + transactionError);
            System.err.println(StringUtils.repeat("#", 150));
            this.logButtonNameToPress("OK");
            button.waitUntil(visible, longtime, polling).click();
            this.logPressedButtonName("OK");
            button.waitUntil(disappears, longtime, polling);
        }
    }

    /**
     * Проверяет наличие поля [Обоснование внесения изменения] и заполняет его, если оно отображается.
     */
    private void detectAndFillModificationInfoField() {
        SelenideElement field = $(By.id(MODIFICATION_INFO_ID));
        String fieldName = "Обоснование внесения изменения";
        String value = "Очень обоснованное обоснование.";

        if (field.isDisplayed()) {
            field.click();
            field.sendKeys(value);
            this.logFilledFieldName(fieldName, value);
        }
    }
}
