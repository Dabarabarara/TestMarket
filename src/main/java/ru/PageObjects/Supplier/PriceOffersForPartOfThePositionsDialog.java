package ru.PageObjects.Supplier;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы с диалоговым окном [В заявке не указаны ценовые предложения].
 * Created by Vladimir V. Klochkov on 16.04.2020.
 * Updated by Vladimir V. Klochkov on 16.04.2020.
 */
public class PriceOffersForPartOfThePositionsDialog extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     ******************************************************************************************************************/
    public PriceOffersForPartOfThePositionsDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Кнопка Подать", "//button/span[text() = 'Подать']");
        controls.add("Кнопка Отмена", "//button/span[text() = 'Отмена']");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Делает щелчок мышью по указанной кнопке.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButtonByName(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.clickInElementJS(this.getBy(controls.find(buttonName)));
        this.logPressedButtonName(buttonName);
        this.waitLoadingImage();
    }
}
