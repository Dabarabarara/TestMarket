package ru.PageObjects.CommonDialogs;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Сообщение].
 * Created by Vladimir V. Klochkov on 25.12.2019.
 * Updated by Vladimir V. Klochkov on 25.12.2019.
 */
public class MessageDialog extends CommonPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [OK] в диалоговом окне [Сообщение]
    private static final String OK_BUTTON_XPATH =
            "//div[@id='dialogError']/following-sibling::div[1]//button/span[contains(.,'Ok')]";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public MessageDialog(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Нажимает кнопку [OK] в диалоговом окне [Операция успешно выполнена].
     */
    public void clickOnOkButton() throws Exception {
        // Нажимаем на кнопку [OK] в диалоговом окне [Сообщение]
        TimeUnit.SECONDS.sleep(1);
        SelenideElement okButton = $(this.getBy(OK_BUTTON_XPATH));
        okButton.waitUntil(clickable, longtime, polling);
        System.out.println("[ИНФОРМАЦИЯ]: произведено нажатие кнопки [OK] в диалоговом окне [Сообщение].");
        this.clickInElementJS(okButton);
        okButton.waitUntil(disappears, longtime, polling);
        this.waitLoadingImage();
    }
}
