package ru.PageObjects.Customer.Notice;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Запрос на согласование отправлен].
 * Created by Kirill G. Rydzyvylo on 04.03.2020.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class ApprovalRequestSentDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public ApprovalRequestSentDialog(WebDriver driver) {
        super(driver);
        controls.add("Текст заголовка окна", "//kendo-dialog-titlebar//div[contains(@class,'k-dialog-title')]");
        controls.add("Поле Текст сообщения", "//kendo-dialog//div[@class='ng-welt ng-star-inserted']");
        controls.add("Кнопка Перейти к карточке закупки", "gotoViewCard");
        controls.add("Кнопка Перейти к реестру закупок", "navigateToTradeList");
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна [Запрос на согласование отправлен].
     *
     * @return диалоговое окно [Запрос на согласование отправлен]
     */
    public ApprovalRequestSentDialog ifDialogOpened() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        this.waitLoadingImage();
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Запрос на согласование отправлен] не обнаружено",
                $(this.getBy(controls.find("Текст заголовка окна"))).waitUntil(exist, timeout, polling).isDisplayed());
        return this;
    }

    /**
     * Проверяет текст сообщения в диалоговом окне [Запрос на согласование отправлен].
     *
     * @param message ожидаемый текст сообщения
     * @return диалоговое окно [Запрос на согласование отправлен]
     */
    public ApprovalRequestSentDialog checkDialogMessage(String message) {
        String actualMessage = $(this.getBy(controls.find("Поле Текст сообщения"))).
                waitUntil(visible, timeout, polling).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: произведена проверка наличия сообщения [%s] в диалоговом окне " +
                "[Запрос на согласование отправлен].%n", actualMessage);
        Assert.assertEquals("В диалоговом окне отсутствует указанный текст сообщения", message, actualMessage);
        return this;
    }

    /**
     * Нажимает на указанную кнопку в диалоговом окне [Запрос на согласование отправлен].
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButtonByName(String buttonName) throws Exception {
        System.out.printf(
                "[ИНФОРМАЦИЯ]: произведено нажатие кнопки [%s] в диалоговом окне [Запрос на согласование отправлен].%n",
                buttonName);
        $(this.getBy(controls.find(buttonName))).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        $(this.getBy(controls.find("Текст заголовка окна"))).shouldBe(disappear);
    }
}
