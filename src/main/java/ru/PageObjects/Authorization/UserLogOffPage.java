package ru.PageObjects.Authorization;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonPage;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс, реализующий функционал выхода из личного кабинета Пользователя ( как Заказчика, так и Поставщика )
 * или Оператора / Администратора.
 * Created by Vladimir V. Klochkov on 21.05.2020.
 * Updated by Vladimir V. Klochkov on 25.02.2021.
 */
public class UserLogOffPage extends CommonPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Личный кабинет Пользователя, ссылка [Выход]
    private static final String EXIT_LINK_XPATH =
            "//*[@class='header__authorization_enter_text' or " +
                    "@class='header__authorization_loguot' or " +
                    "@class='header__authorization_logout']";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public UserLogOffPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Осуществляет выход из личного кабинета Пользователя.
     */
    public void userLogsOff() throws Exception {
        long waitTimeMinutes = Integer.parseInt(config.getConfigParameter("MaxStatusWaitTime"));
        long waitTimeMilliSecs = waitTimeMinutes * 60000;

        System.out.printf(
                "[ИНФОРМАЦИЯ]: установленное максимальное время ожидания выхода из личного кабинета %d минут.%n",
                waitTimeMinutes);

        //==============================================================================================================
        config.closeActiveWindowsAndSwitchToMainWindowInBrowser(driver);
        //==============================================================================================================
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        SelenideElement exitLink = $(By.xpath(EXIT_LINK_XPATH)).waitUntil(clickable, waitTimeMilliSecs, polling);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", exitLink);
        exitLink.waitUntil(disappear, waitTimeMilliSecs, polling);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }
}
