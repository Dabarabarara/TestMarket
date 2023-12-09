package ru.PageObjects.CommonDialogs;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.AbstractPage;
import ru.PageObjects.CommonPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Операция успешно выполнена].
 * Created by Vladimir V. Klochkov on 11.04.2016.
 * Updated by Vladimir V. Klochkov on 11.02.2020.
 */
public class CompletedDialog extends CommonPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [OK] в диалоговом окне [Операция успешно выполнена]
    private static final String OK_BUTTON_XPATH =
            "//div[@id='dialogSuccess']/following-sibling::div[1]//button[contains(.,'OK')]";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CompletedDialog(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Нажимает кнопку [OK] в диалоговом окне [Операция успешно выполнена].
     */
    public void clickOnOkButton() throws Exception {
        // Нажимаем на кнопку [OK] в диалоговом окне [Операция успешно выполнена]
        TimeUnit.SECONDS.sleep(1);
        SelenideElement button = $(this.getBy(OK_BUTTON_XPATH));
        System.out.println(
                "[ИНФОРМАЦИЯ]: ожидаем появления кнопки [OK] в диалоговом окне [Операция успешно выполнена].");
        button.waitUntil(clickable, longtime, polling);
        System.out.println("[ИНФОРМАЦИЯ]: нажимаем на кнопку [OK] в диалоговом окне [Операция успешно выполнена].");
        this.clickInElementJS(button);
        this.waitLoadingImage();
        button.waitUntil(disappears, longtime, polling);
    }
}
