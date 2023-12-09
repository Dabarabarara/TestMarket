package ru.PageObjects.CommonDialogs;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы с диалоговым окном [История].
 * Created by Kirill G. Rydzyvylo on 02.12.2020.
 * Updated by Vladimir V. Klochkov on 25.02.2021.
 */
public class HistoryDialog extends CommonPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public HistoryDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Текст заголовка окна", "ContractsHistoryWindow_wnd_title");
        controls.add("Ячейка таблицы", "//td[contains(.,'%s')]");
        controls.add("Закрыть", "//span[@id='ContractsHistoryWindow_wnd_title']/following-sibling::div[1]/a/span");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна [История].
     *
     * @return это диалоговое окно
     */
    public HistoryDialog dialogDisplayed() throws Exception {
        this.waitLoadingImage(15);
        System.out.println("[ИНФОРМАЦИЯ]: проверяем наличие диалогового окна [История].");
        ElementsCollection titles = $$(this.getBy(controls.find("Текст заголовка окна"))).filter(visible);
        System.out.printf("[ИНФОРМАЦИЯ]: найдено [%d] заголовков диалогового окна [История]%n", titles.size());
        boolean result = titles.size() > 0;
        System.out.printf("[ИНФОРМАЦИЯ]: диалоговое окно [История] %s отображается.%n", result ? "" : "не");

        return this;
    }

    /**
     * Проверяет содержимое [Истории] на текст.
     *
     * @param text ожидаемый текст в поле
     * @return это диалоговое окно
     */
    public HistoryDialog checkHistoryRecords(String text) {
        String xpathToText = String.format(controls.find("Ячейка таблицы"), text);
        $(this.getBy(xpathToText)).waitUntil(visible, longtime, polling);
        System.out.printf("[ИНФОРМАЦИЯ]: произведена проверка [Истории] на текст [%s].%n", text);

        return this;
    }

    /**
     * Нажимает на указанную кнопку в диалоговом окне [История].
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
