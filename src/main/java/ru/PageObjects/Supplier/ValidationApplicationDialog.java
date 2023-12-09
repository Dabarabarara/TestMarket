package ru.PageObjects.Supplier;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы с диалоговым окном [В заявке не указаны ценовые предложения].
 * Created by Vladimir V. Klochkov on 29.01.2020.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class ValidationApplicationDialog extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final String windowName = "В заявке не указаны ценовые предложения"; // имя этого диалогового окна
    private final Dictionary controls = new Dictionary();                        // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public ValidationApplicationDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Текст сообщения в окне", "//*[@id='validationApplicationDialog']/label");
        controls.add("Подать",
                "//*[@id='validationApplicationDialog']/following-sibling::div[1]//span[text() = 'Подать']");
        controls.add("Отмена",
                "//*[@id='validationApplicationDialog']/following-sibling::div[1]//span[text() = 'Отмена']");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет текст сообщения в окне.
     *
     * @param controlName  условное имя элемента диалогового окна
     * @param expectedText ожидаемый фрагмент текста для проверки
     * @return это диалоговое окно
     */
    public ValidationApplicationDialog checkWindowText(String controlName, String expectedText) {
        String actualText = $(this.getBy(controls.find(controlName))).waitUntil(visible, longtime, polling).getText();
        Assert.assertTrue(String.format(
                "[ОШИБКА]: текст [%s] в диалоговом окне [%s] не соответствует ожидаемому [%s]",
                actualText, windowName, expectedText), actualText.contains(expectedText));
        System.out.printf("[ИНФОРМАЦИЯ]: текст [%s] в диалоговом окне [%s] соответствует ожидаемому [%s]%n",
                actualText, windowName, expectedText);

        return this;
    }

    /**
     * Нажимает на кнопку с указанным именем.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        TimeUnit.SECONDS.sleep(1);
        ElementsCollection buttons = $$(this.getBy(controls.find(buttonName))).filterBy(visible);
        SelenideElement button = buttons.get(0);
        System.out.printf("[ИНФОРМАЦИЯ]: ожидаем появления кнопки [%s] в диалоговом окне [%s].%n",
                buttonName, windowName);
        button.waitUntil(clickable, longtime, polling);
        System.out.printf("[ИНФОРМАЦИЯ]: нажимаем на кнопку [%s] в диалоговом окне [%s].%n", buttonName, windowName);
        this.clickInElementJS(button);
        this.waitLoadingImage();
        button.waitUntil(disappears, longtime, polling);
    }
}
