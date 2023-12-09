package ru.PageObjects.Customer;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Предупреждение - Отклонить закупку/протокол?].
 * Created by Vladimir V. Klochkov on 29.09.2017.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class RejectApprovalRequestDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Константы класса.
     ******************************************************************************************************************/
    private static final String WINDOW_NAME = "Предупреждение - Отклонить закупку/протокол?";

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public RejectApprovalRequestDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Текст заголовка окна", "CommonConfirmWindow_wnd_title");
        controls.add("Текст окна", "CommonConfirmText");
        controls.add("Поле Комментарий", "CommonConfirmComment");
        controls.add("Прикрепить файл", "CommonConfirmUpload");
        controls.add("Продолжить", "CommonConfirmWindowOk");
        controls.add("Отмена", "CommonConfirmWindowCancel");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна.
     *
     * @return это диалоговое окно
     */
    public RejectApprovalRequestDialog ifDialogOpened() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        this.waitLoadingImage();
        Assert.assertTrue(String.format("[ОШИБКА]: диалоговое окно [%s] не обнаружено", WINDOW_NAME),
                $(By.id(controls.find("Текст заголовка окна"))).waitUntil(exist, timeout, polling).isDisplayed());
        Assert.assertTrue(String.format("[ОШИБКА]: текст диалогового окна [%s] не соответствует ожидаемому", WINDOW_NAME),
                $(By.id(controls.find("Текст окна"))).
                        waitUntil(exist, timeout, polling).getText().contains("Отклонить "));
        return this;
    }

    /**
     * Устанавливает значение поля с предварительной его очисткой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     * @return это диалоговое окно
     */
    public RejectApprovalRequestDialog setFieldClearClickAndSendKeys(String fieldName, String value) throws Exception {
        this.waitClearClickAndSendKeysById(controls.find(fieldName), value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
        return this;
    }

    /**
     * Нажимает на указанную кнопку в диалоговом окне.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButtonByName(String buttonName) throws Exception {
        System.out.printf(
                "[ИНФОРМАЦИЯ]: произведено нажатие кнопки [%s] в диалоговом окне [%s].%n", buttonName, WINDOW_NAME);
        $(By.id(controls.find(buttonName))).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        TimeUnit.SECONDS.sleep(15);
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        $(By.id(controls.find("Текст заголовка окна"))).shouldBe(disappear);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }
}
