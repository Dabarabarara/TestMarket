package ru.StepDefinitions;

import Helpers.ConfigContainer;
import Helpers.Screenshoter;
import Helpers.Timer;
import com.codeborne.selenide.Condition;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Абстрактный класс, от которого наследуются все реальные классы определения шагов для тестов.
 * Created by Vladimir V. Klochkov on 19.08.2016.
 * Updated by Vladimir V. Klochkov on 05.02.2021.
 */
public abstract class AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    protected final Timer timer = new Timer();                              // класс для работы со временными интервалами
    protected final ConfigContainer config = ConfigContainer.getInstance(); // экземпляр ConfigContainer
    protected final Screenshoter screenshoter = new Screenshoter();         // экземпляр Screenshoter
    protected final WebDriver driver = Hooks.driver;                        // экземпляр WebDriver
    protected final Condition clickable = and("can be clicked", visible, enabled); // по элементу можно сделать клик

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    /**
     * Выводит в консоль строку-разделитель и строку с описанием содержания шага.
     *
     * @param stepName описание содержания шага
     */
    protected void logStepName(String stepName) {
        System.out.println(StringUtils.repeat("-", 150));
        System.out.printf("[%s]%n", stepName);
    }

    /**
     * Метод делает клик по элементу страницы используя By с помощью JS.
     *
     * @param by элемент по которому следует кликнуть
     */
    protected void clickInElementJS(By by) throws Exception {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", $(by));
        TimeUnit.SECONDS.sleep(1);
        System.out.println("[ИНФОРМАЦИЯ]: произведен click с помощью JS.");
    }
}
