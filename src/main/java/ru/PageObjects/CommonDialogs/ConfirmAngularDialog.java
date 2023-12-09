package ru.PageObjects.CommonDialogs;

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
 * Класс для работы с диалоговым окном [Подтверждение действия].
 * Created by Vladimir V. Klochkov on 11.04.2016.
 * Updated by Vladimir V. Klochkov on 04.03.2021.
 */
public class ConfirmAngularDialog extends CommonPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public ConfirmAngularDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Текст сообщения в окне",
                "//kendo-dialog//div[@class='ng-welt ng-star-inserted']");
        controls.add("Кнопка Подтвердить", "setPublicApplicationQuotations");
        controls.add("Кнопка Продолжить", "setPublicApplicationQuotations");

        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет текст сообщения в окне.
     *
     * @param controlName условное имя элемента диалогового окна
     * @param text        текст для проверки
     * @return это диалоговое окно
     */
    public ConfirmAngularDialog checkWindowText(String controlName, String text) {
        Assert.assertTrue(String.format("[ОШИБКА]: текст в диалоговом окне не соответствует ожидаемому [%s]", text),
                $(this.getBy(controls.find(controlName))).waitUntil(visible, longtime, polling).
                        getText().contains(text));
        return this;
    }

    /**
     * Нажимает на кнопку с указанным именем.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        String windowName = "Подтверждение действия";
        TimeUnit.SECONDS.sleep(1);
        ElementsCollection buttons = $$(this.getBy(controls.find(buttonName))).filterBy(visible);
        SelenideElement button = buttons.get(0);
        System.out.printf("[ИНФОРМАЦИЯ]: ожидаем появления кнопки [%s] в диалоговом окне [%s].%n",
                buttonName, windowName);
        button.waitUntil(clickable, longtime, polling);
        System.out.printf("[ИНФОРМАЦИЯ]: нажимаем на кнопку [%s] в диалоговом окне [%s].%n",
                buttonName, windowName);
        this.clickInElementJS(button);
        this.waitLoadingImage();
        button.waitUntil(disappears, longtime, polling);
    }
}
