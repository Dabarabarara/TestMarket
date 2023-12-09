package ru.PageObjects.Customer.Notice;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Сохранено].
 * Created by Vladimir V. Klochkov on 02.04.2019.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class PurchaseNoticeDraftSuccessfullySavedDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public PurchaseNoticeDraftSuccessfullySavedDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Текст заголовка окна", "//*[contains(@class, 'k-window-title') and text()='Сохранено']");
        controls.add("Продолжить формирование черновика", "//*[@id='goToEditCard']");
        controls.add("Перейти к реестру закупок", "//*[@id='navigateToTradeList']");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна [Сохранено].
     *
     * @return диалоговое окно [Сохранено]
     */
    public PurchaseNoticeDraftSuccessfullySavedDialog ifDialogOpened() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        this.waitLoadingImage();
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Сохранено] не обнаружено",
                $(this.getBy(controls.find("Текст заголовка окна"))).waitUntil(exist, timeout, polling).
                        isDisplayed());
        return this;
    }

    /**
     * Нажимает на указанную кнопку в диалоговом окне [Сохранено].
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButtonByName(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        $(this.getBy(controls.find(buttonName))).waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName(buttonName);
        this.waitLoadingImage();
    }
}
