package ru.PageObjects.RabbitMQ;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Класс для работы со страницей аутентификации в RabbitMQ ( http://dev-rabbit01.devtest.local:15672/#/queues ).
 * Created by Vladimir V. Klochkov on 06.12.2018.
 * Updated by Vladimir V. Klochkov on 10.12.2018.
 */
public class RabbitMQLoginPage extends CommonPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов страницы
    private final RabbitMQUserCabinetPage rabbitMQUserCabinetPage = new RabbitMQUserCabinetPage(driver);

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public RabbitMQLoginPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("URL", "://dev-rabbit");
        controls.add("Logo", "//*[@id='login']//img");
        controls.add("Поле Username", "//input[@name='username']");
        controls.add("Поле Password", "//input[@name='password']");
        controls.add("Кнопка Login", "//input[@value='Login']");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Переходит на страницу аутентификации в RabbitMQ и проверяет, что открыта именно эта страница.
     *
     * @param url полный адрес страницы аутентификации в RabbitMQ
     * @return страница аутентификации в RabbitMQ
     */
    public RabbitMQLoginPage gotoRabbitMQLoginPage(String url) throws Exception {
        System.out.println("[ИНФОРМАЦИЯ]: переходим на страницу аутентификации в RabbitMQ.");
        open(url);
        this.waitForPageLoad();
        Assert.assertTrue("[ОШИБКА]: страница аутентификации в RabbitMQ не открыта",
                url().contains(controls.find("URL")));
        System.out.println("[ИНФОРМАЦИЯ]: переход на страницу аутентификации в RabbitMQ прошел успешно.");
        return this;
    }

    /**
     * Авторизуется в RabbitMQ используя имя и пароль Пользователя.
     *
     * @param username имя Пользователя
     * @param password пароль Пользователя
     */
    public void thanLoginToRabbitMQ(String username, String password) throws Exception {
        if (rabbitMQUserCabinetPage.userCabinetPageAlreadyOpened()) return;     // если Пользователь уже вошел в кабинет
        System.out.println("[ИНФОРМАЦИЯ]: авторизуемся в RabbitMQ используя имя и пароль Пользователя.");
        $(By.xpath(controls.find("Поле Username"))).waitUntil(clickable, timeout, polling).sendKeys(username);
        $(By.xpath(controls.find("Поле Password"))).waitUntil(clickable, timeout, polling).sendKeys(password);
        $(By.xpath(controls.find("Кнопка Login"))).waitUntil(clickable, timeout, polling).click();
        this.waitForPageLoad();
        this.driverTimeout(5);
        $(By.xpath(controls.find("Logo"))).waitUntil(disappears, timeout, polling).should(disappear);
        this.driverTimeout(30);
        System.out.println("[ИНФОРМАЦИЯ]: авторизация в RabbitMQ используя имя и пароль Пользователя прошла успешно.");
    }

    /**
     * Переопределяет глобальное время ожидания для экземпляра WebDriver.
     *
     * @param time глобальное время ожидания в секундах
     */
    private void driverTimeout(long time) {
        getWebDriver().manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }
}
