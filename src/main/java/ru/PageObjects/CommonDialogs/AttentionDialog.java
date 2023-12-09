package ru.PageObjects.CommonDialogs;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы с диалоговым окном [Внимание!].
 * Created by Vladimir V. Klochkov on 07.05.2018.
 * Updated by Vladimir V. Klochkov on 04.03.2021.
 */
public class AttentionDialog extends CommonPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public AttentionDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Текст заголовка окна", "//span[@class='ui-dialog-title' and contains(., 'Внимание!')]");
        controls.add("Текст окна", "//div[@class='ui-dialog-content ui-widget-content' and contains(., 'Не совпадают')]");
        controls.add("Текст окна 1 строка", "dlgCouldNotApplyApplication");
        controls.add("Продолжить формирование", "btnContinue");
        controls.add("Продолжить связывание", "//button[contains(., 'Продолжить связывание')]");
        controls.add("Отмена", "//button[contains(., 'Отмена')]");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна [Внимание!].
     *
     * @return true, если диалоговое окно [Внимание!] отображается на экране
     */
    public boolean dialogDisplayed() throws Exception {
        this.waitLoadingImage(15);
        System.out.println("[ИНФОРМАЦИЯ]: проверяем наличие диалогового окна [Внимание!].");
        ElementsCollection titles = $$(this.getBy(controls.find("Текст заголовка окна"))).filter(visible);
        System.out.printf("[ИНФОРМАЦИЯ]: найдено [%d] заголовков диалогового окна [Внимание!]%n", titles.size());
        boolean result = titles.size() > 0;
        System.out.printf("[ИНФОРМАЦИЯ]: диалоговое окно [Внимание!] %s отображается.%n", result ? "" : "не");
        return result;
    }

    /**
     * Проверяет наличие требуемого текста в диалоговом окне [Внимание!].
     *
     * @param control  имя управляющего элемента в словаре
     * @param expected требуемый текст
     * @return диалоговое окно [Внимание!]
     */
    public AttentionDialog check1stLineInWindowText(String control, String expected) {
        String actual = $(this.getBy(controls.find(control))).waitUntil(visible, timeout, polling).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: ожидаемый текст в окне диалога [%s].%n", expected);
        System.out.printf("[ИНФОРМАЦИЯ]: реальный  текст в окне диалога [%s].%n", actual);
        Assert.assertTrue(String.format("[ОШИБКА]: диалоговое окно [Внимание!] не содержит текста [%s]", expected),
                actual.contains(expected));
        return this;
    }

    /**
     * Нажимает на указанную кнопку в диалоговом окне [Внимание!].
     *
     * @param buttonName имя управляющего элемента в словаре
     */
    public void clickOnButton(String buttonName) throws Exception {
        SelenideElement button = $(this.getBy(controls.find(buttonName)));
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        button.waitUntil(appears, longtime, polling).shouldBe(clickable);
        screenshoter.takeScreenshot();
        this.logButtonNameToPress(buttonName);
        this.clickInElementJS(button);
        this.logPressedButtonName(buttonName);
        button.waitUntil(disappear, longtime, polling).shouldBe(disappear);
        this.waitForPageLoad();
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }
}
