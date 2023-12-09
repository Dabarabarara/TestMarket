package ru.PageObjects.CommonDialogs;

import Helpers.Dictionary;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.AbstractPage;
import ru.PageObjects.CommonPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Информация].
 * Created by Vladimir V. Klochkov on 05.09.2016.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class InformationDialog extends CommonPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public InformationDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Текст заголовка окна", "CommonErrorWindow_wnd_title");
        controls.add("Текст заголовка окна для закупки МСП",
                "//div[@class='k-window-title k-dialog-title' and contains(., 'Информация')]");
        controls.add("Текст окна 1 строка", "//*[@id='CommonInfoText']/span[1]");
        controls.add("Текст окна 1 строка для закупки МСП", "//button[@id='navigateToTradeList']/../../div/div");
        controls.add("ОК", "//*[@id='CommonErrorWindow']//button");
        controls.add("ОК для закупки МСП", "navigateToTradeList");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна [Информация].
     *
     * @param controlName имя элемента окна с заголовком
     * @return диалоговое окно [Информация]
     */
    public InformationDialog ifDialogOpened(String controlName) {
        SelenideElement dialogWindow = $(this.getBy(controls.find(controlName)));
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        System.out.printf("[ИНФОРМАЦИЯ]: %s ожидание появления диалогового окна [Информация].%n",
                timer.getCurrentDateTime("dd.MM.yyyy HH:mm:ss"));
        //--------------------------------------------------------------------------------------------------------------
        dialogWindow.waitUntil(appears, longtime, polling);
        //--------------------------------------------------------------------------------------------------------------
        System.out.printf("[ИНФОРМАЦИЯ]: %s проверка наличия диалогового окна [Информация].%n",
                timer.getCurrentDateTime("dd.MM.yyyy HH:mm:ss"));
        //--------------------------------------------------------------------------------------------------------------
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Информация] не обнаружено", dialogWindow.isDisplayed());
        //--------------------------------------------------------------------------------------------------------------
        System.out.printf("[ИНФОРМАЦИЯ]: %s диалоговое окно [Информация] обнаружено.%n",
                timer.getCurrentDateTime("dd.MM.yyyy HH:mm:ss"));
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        return this;
    }

    /**
     * Проверяет наличие требуемого текста в диалоговом окне [Информация].
     *
     * @param controlName имя элемента окна с текстом сообщения
     * @param text        требуемый текст
     * @return диалоговое окно [Информация]
     */
    public InformationDialog check1stLineInWindowText(String controlName, String text) {
        Assert.assertTrue(String.format("[ОШИБКА]: диалоговое окно [Информация] не содержит текста [%s]", text),
                $(this.getBy(controls.find(controlName))).waitUntil(visible, timeout, polling).getText().contains(text));
        System.out.printf("[ИНФОРМАЦИЯ]: произведена проверка наличия в окне текста [%s].%n", text);
        return this;
    }

    /**
     * Нажимает на кнопку [OK] в диалоговом окне [Информация].
     *
     * @param controlName имя элемента окна с кнопкой [OK]
     */
    public void clickOnOKButton(String controlName) throws Exception {
        SelenideElement button = $(this.getBy(controls.find(controlName)));
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        button.waitUntil(appears, longtime, polling).shouldBe(visible);
        this.logButtonNameToPress("ОК");
        this.clickInElementJS(button);
        this.logPressedButtonName("ОК");
        button.waitUntil(disappear, longtime, polling).shouldBe(disappear);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }
}
