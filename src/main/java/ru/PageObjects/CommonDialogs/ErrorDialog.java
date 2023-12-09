package ru.PageObjects.CommonDialogs;

import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы с диалоговым окном [Ошибка].
 * Created by Vladimir V. Klochkov on 08.08.2019.
 * Updated by Vladimir V. Klochkov on 15.03.2021.
 */
public class ErrorDialog extends CommonPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок диалогового окна [Ошибка]
    private static final String COMMON_ERROR_WINDOW_TITLE_XPATH =
            "//*[@id='CommonErrorWindow_wnd_title' and contains(., 'Ошибка')]";
    //------------------------------------------------------------------------------------------------------------------
    // Текст диалогового окна [Ошибка]
    private static final String COMMON_ERROR_WINDOW_TEXT_XPATH = "//*[@id='CommonErrorText']/span";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [ОК] в диалоговом окне [Ошибка]
    private static final String OK_BUTTON_XPATH = "//*[@id='CommonErrorWindow']//button[contains(., 'ОК')]";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public ErrorDialog(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна [Ошибка].
     *
     * @return диалоговое окно [Ошибка]
     */
    public ErrorDialog ifDialogOpened() throws Exception {
        this.waitLoadingImage(5);
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Ошибка] не обнаружено",
                $(this.getBy(COMMON_ERROR_WINDOW_TITLE_XPATH)).waitUntil(exist, timeout, polling).isDisplayed());
        return this;
    }

    /**
     * Проверяет отсутствие диалогового окна [Ошибка].
     *
     * @return диалоговое окно [Ошибка]
     */
    public ErrorDialog checkForErrorDialogWindow() throws Exception {
        this.waitLoadingImage(5);
        if ($(this.getBy(COMMON_ERROR_WINDOW_TITLE_XPATH)).isDisplayed())
            Assert.fail(String.format("[ОШИБКА]: обнаружено диалоговое окно с заголовком [Ошибка] и текстом [%s]",
                    $(this.getBy(COMMON_ERROR_WINDOW_TEXT_XPATH)).waitUntil(visible, timeout, polling).getText()));
        return this;
    }

    /**
     * Проверяет текст сообщения в диалоговом окне [Ошибка].
     *
     * @param text ожидаемый текст сообщения
     */
    public void checkDialogText(String text) {
        $(this.getBy(COMMON_ERROR_WINDOW_TEXT_XPATH)).waitUntil(visible, timeout, polling).shouldHave(text(text));
    }

    /**
     * Проверяет текст сообщения в диалоговом окне [Ошибка] ( в строке с указанным номером ).
     *
     * @param text ожидаемый текст сообщения
     * @param line номер строки [0..N]
     * @return диалоговое окно [Ошибка]
     */
    public ErrorDialog checkDialogText(String text, int line) {
        ElementsCollection lines = $$(this.getBy(COMMON_ERROR_WINDOW_TEXT_XPATH));
        lines.get(line).waitUntil(visible, timeout, polling).shouldHave(text(text));
        return this;
    }

    /**
     * Нажимает на кнопку [ОК] в диалоговом окне [Ошибка].
     */
    public void clickOnOkButton() throws Exception {
        this.logButtonNameToPress("ОК");
        $(this.getBy(OK_BUTTON_XPATH)).waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName("ОК");
        this.waitLoadingImage(15);
    }
}
