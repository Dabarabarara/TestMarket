package ru.PageObjects.CommonDialogs;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.AbstractPage;
import ru.PageObjects.CommonPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы с диалоговым окном [Подтверждение действия].
 * Created by Vladimir V. Klochkov on 11.04.2016.
 * Updated by Vladimir V. Klochkov on 04.03.2021.
 */
public class ConfirmDialog extends CommonPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public ConfirmDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Текст сообщения в окне", "CommonConfirmText");
        controls.add("Текст сообщения в окне 'Был сформирован новый запрос на разъяснение'",
                "//*[@id='dialogConfirm']/p");
        controls.add("Текст сообщения в окне 'Вы уверены, что хотите отправить ответ на запрос?'",
                "//*[@id='dialogConfirm']/p");
        controls.add("Кнопка Да", "//p[contains(text(),'Отправить заявку, продолжить?')]/following::button[1]");
        controls.add("Кнопка Да в окне 'Был сформирован новый запрос на разъяснение'",
                "//*[@id='dialogConfirm']/..//button[contains(., 'Да')]");
        controls.add("Кнопка Да в окне 'Вы уверены, что хотите отправить ответ на запрос?'",
                "//*[@id='dialogConfirm']/..//button[contains(., 'Да')]");
        controls.add("Кнопка Нет в окне 'Вы уверены, что хотите отправить ответ на запрос?'",
                "//*[@id='dialogConfirm']/..//button[contains(., 'Нет')]");
        controls.add("Кнопка Подтвердить", "CommonConfirmWindowOk");
        controls.add("Кнопка Отмена", "CommonConfirmWindowCancel");
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
    public ConfirmDialog checkWindowText(String controlName, String text) {
        Assert.assertTrue(String.format("[ОШИБКА]: текст в диалоговом окне не соответствует ожидаемому [%s]", text),
                $(this.getBy(controls.find(controlName))).waitUntil(visible, longtime, polling).
                        getText().contains(text));
        return this;
    }

    /**
     * Проверяет наличие и возможность сделать щелчок мышью по указанной кнопке.
     *
     * @param buttonName имя кнопки
     * @return это диалоговое окно
     */
    public ConfirmDialog checkButtonExistsAndClickable(String buttonName) {
        $(this.getBy(controls.find(buttonName))).waitUntil(clickable, timeout, polling).shouldBe(enabled);
        System.out.printf("[ИНФОРМАЦИЯ]: произведена проверка доступности кнопки [%s].%n", buttonName);

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
