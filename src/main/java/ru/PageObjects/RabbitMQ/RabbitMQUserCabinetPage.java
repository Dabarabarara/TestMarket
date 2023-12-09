package ru.PageObjects.RabbitMQ;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonPage;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Класс для работы со страницей кабинета Пользователя в RabbitMQ ( http://dev-rabbit01.devtest.local:15672/#/queues ).
 * Created by Vladimir V. Klochkov on 07.12.2018.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class RabbitMQUserCabinetPage extends CommonPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов страницы

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public RabbitMQUserCabinetPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("URL", "://dev-rabbit");
        controls.add("Logo", "//*[@id='logo']//img");
        controls.add("Фильтр сообщений Virtual host", "show-vhost");
        controls.add("Вкладка Queues", "//a[@href='#/queues']");
        controls.add("Поле Filter", "filter");
        controls.add("Столбец Name в результатах поиска",
                "//tbody//a[contains(@href, 'EntityPublishedConsumer') and not(contains(., '_error'))]");
        controls.add("Блок Publish message", "//h2[contains(., 'Publish message')]");
        controls.add("Поле Payload", "//textarea[@name='payload']");
        controls.add("Кнопка Publish message", "//input[@type='submit' and @value='Publish message']");
        controls.add("Диалоговое окно Message published", "//div[@class='form-popup-info']/span[contains(., 'Close')]");
        controls.add("Кнопка Log out", "//input[@value='Log out']");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет, что открыта страница кабинета Пользователя в RabbitMQ.
     *
     * @return страница кабинета Пользователя в RabbitMQ
     */
    public RabbitMQUserCabinetPage thanCheckLoginToRabbitMQSuccessfully() {
        System.out.println("[ИНФОРМАЦИЯ]: проверяем, что открыта страница кабинета Пользователя в RabbitMQ.");
        Assert.assertTrue("[ОШИБКА]: страница кабинета Пользователя в RabbitMQ не открыта",
                url().contains(controls.find("URL")));
        $(By.xpath(controls.find("Logo"))).waitUntil(appears, timeout, polling).shouldBe(visible);
        System.out.println("[ИНФОРМАЦИЯ]: проверка страницы кабинета Пользователя в RabbitMQ прошла успешно.");
        return this;
    }

    /**
     * Проверяет, что страница кабинета Пользователя в RabbitMQ уже открыта.
     *
     * @return true если страница кабинета Пользователя в RabbitMQ уже открыта
     */
    boolean userCabinetPageAlreadyOpened() {
        SelenideElement windowLogo = $(By.xpath(controls.find("Logo")));
        System.out.println("[ИНФОРМАЦИЯ]: проверяем, что Пользователь уже может находиться в кабинете RabbitMQ.");
        this.driverTimeout(5);
        boolean result = windowLogo.isDisplayed() && url().contains(controls.find("URL"));
        this.driverTimeout(30);
        return result;
    }

    /**
     * Устанавливает значение фильтра [Virtual host] в соответствии с текущим используемым тестовым стендом.
     *
     * @return страница кабинета Пользователя в RabbitMQ
     */
    public RabbitMQUserCabinetPage thanSelectVirtualHostFilter() throws Exception {
        String virtualHostOption = config.getConfigParameter("RabbitMQHostItem");
        SelenideElement virtualHost = $(By.id(controls.find("Фильтр сообщений Virtual host")));
        System.out.printf("[ИНФОРМАЦИЯ]: устанавливаем значение фильтра [Virtual host] в соответствии " +
                "с текущим используемым тестовым стендом: [%s].%n", virtualHostOption);
        virtualHost.waitUntil(clickable, timeout, polling).selectOption(virtualHostOption);
        this.waitForPageLoad();
        virtualHost.waitUntil(clickable, timeout, polling).shouldHave(value(virtualHostOption));
        System.out.printf("[ИНФОРМАЦИЯ]: значение фильтра [Virtual host] установлено в соответствии " +
                "с текущим используемым тестовым стендом: [%s].%n", virtualHost.getText());
        return this;
    }

    /**
     * Переходит на вкладку [Queues] в кабинете Пользователя RabbitMQ.
     *
     * @return страница кабинета Пользователя в RabbitMQ
     */
    public RabbitMQUserCabinetPage thanGoToQueuesTab() throws Exception {
        SelenideElement queuesTab = $(By.xpath(controls.find("Вкладка Queues")));
        System.out.println("[ИНФОРМАЦИЯ]: переходим на вкладку [Queues] в кабинете Пользователя RabbitMQ.");
        queuesTab.waitUntil(clickable, timeout, polling).click();
        this.waitForPageLoad();
        queuesTab.waitUntil(clickable, timeout, polling).
                shouldHave(attribute("class", "selected"));
        System.out.println(
                "[ИНФОРМАЦИЯ]: переход на вкладку [Queues] в кабинете Пользователя RabbitMQ прошел успешно.");
        return this;
    }

    /**
     * Устанавливает фильтр по очереди сообщений на вкладке [Queues] в кабинете Пользователя RabbitMQ.
     *
     * @param queueFilterValue требуемое значение фильтра по очереди сообщений на вкладке [Queues]
     * @return страница кабинета Пользователя в RabbitMQ
     */
    public RabbitMQUserCabinetPage thanSetQueueFilter(String queueFilterValue) throws Exception {
        SelenideElement filter = $(By.id(controls.find("Поле Filter")));
        System.out.printf(
                "[ИНФОРМАЦИЯ]: устанавливаем значение фильтра по очереди сообщений на вкладке [Queues] в [%s].%n",
                queueFilterValue);
        filter.waitUntil(clickable, timeout, polling).sendKeys(queueFilterValue);
        this.waitForPageLoad();
        filter.waitUntil(clickable, timeout, polling).shouldHave(value(queueFilterValue));
        System.out.printf(
                "[ИНФОРМАЦИЯ]: значение фильтра по очереди сообщений на вкладке [Queues] установлено в [%s].%n",
                filter.getValue());
        return this;
    }

    /**
     * Проверяет результаты применения фильтра к очереди сообщений на вкладке [Queues] и открывает верхнее сообщение.
     *
     * @return страница кабинета Пользователя в RabbitMQ
     */
    public RabbitMQUserCabinetPage thanCheckQueueFilteringResultsAndOpenFirstMessage() throws Exception {
        System.out.println(
                "[ИНФОРМАЦИЯ]: проверяем результаты применения фильтра к очереди сообщений на вкладке [Queues].");
        ElementsCollection messages = $$(By.xpath(controls.find("Столбец Name в результатах поиска")));
        SelenideElement payload = $(By.xpath(controls.find("Поле Payload")));
        System.out.printf("[ИНФОРМАЦИЯ]: найдено [%d] сообщений на вкладке [Queues].%n", messages.size());
        messages.shouldHave(sizeGreaterThanOrEqual(1));
        System.out.println("[ИНФОРМАЦИЯ]: открываем верхнее (первое) сообщение.");
        messages.get(0).waitUntil(clickable, timeout, polling).click();
        this.waitForPageLoad();

        // Блок Publish message может находиться в отрытом состоянии ( требуемое поле уже отображается )
        this.driverTimeout(5);

        if (!payload.isDisplayed()) {
            System.out.println("[ИНФОРМАЦИЯ]: открываем блок [Publish message].");
            $(By.xpath(controls.find("Блок Publish message"))).waitUntil(clickable, timeout, polling).click();
            this.waitForPageLoad();
        }

        this.driverTimeout(30);

        payload.shouldBe(clickable);
        System.out.println("[ИНФОРМАЦИЯ]: сообщение открылось успешно, поле [Payload] отображается.");
        return this;
    }

    /**
     * Устанавливает значение поля [Payload].
     *
     * @param value требуемое значение поля
     * @return страница кабинета Пользователя в RabbitMQ
     */
    public RabbitMQUserCabinetPage thanSetPayloadFildAndClickPublishMessage(String value) throws Exception {
        SelenideElement messagePublished = $(By.xpath(controls.find("Диалоговое окно Message published")));
        SelenideElement payload = $(By.xpath(controls.find("Поле Payload")));
        System.out.printf("[ИНФОРМАЦИЯ]: устанавливаем значение поля [Payload]:%n", value);
        System.out.println(StringUtils.repeat(":", 150));
        System.out.println(value);
        System.out.println(StringUtils.repeat(":", 150));
        payload.waitUntil(clickable, timeout, polling).sendKeys(value);
        TimeUnit.SECONDS.sleep(3);
        payload.waitUntil(clickable, timeout, polling).shouldHave(value(value));
        System.out.println("[ИНФОРМАЦИЯ]: значение в поле [Payload] установлено успешно.");
        this.logButtonNameToPress("Publish message");
        $(By.xpath(controls.find("Кнопка Publish message"))).waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName("Publish message");
        this.waitForPageLoad();
        System.out.println("[ИНФОРМАЦИЯ]: проверяем наличие диалогового окна [Message published].");
        messagePublished.shouldBe(clickable);
        System.out.println("[ИНФОРМАЦИЯ]: закрываем диалоговоое окно [Message published].");
        messagePublished.click();
        this.driverTimeout(5);
        messagePublished.should(disappear);
        this.driverTimeout(30);
        System.out.println("[ИНФОРМАЦИЯ]: диалоговоое окно [Message published] было успешно закрыто.");
        return this;
    }

    /**
     * Осуществляет выход из кабинета Пользователя в RabbitMQ.
     */
    public void thanLogoutFromRabbitMQ() throws Exception {
        System.out.println("[ИНФОРМАЦИЯ]: выходим из кабинета Пользователя в RabbitMQ.");
        $(By.xpath(controls.find("Кнопка Log out"))).waitUntil(clickable, timeout, polling).click();
        this.waitForPageLoad();
        this.driverTimeout(5);
        $(By.xpath(controls.find("Logo"))).waitUntil(disappears, timeout, polling).should(disappear);
        this.driverTimeout(30);
        System.out.println("[ИНФОРМАЦИЯ]: выход из кабинета Пользователя в RabbitMQ прошел успешно.");
    }

    /*******************************************************************************************************************
     * Вспомогательные методы.
     ******************************************************************************************************************/
    /**
     * Получает шаблон .json-сообщения для RabbitMQ как файл из ресурсов проекта в виде строки.
     *
     * @param configParameter  имя параметра конфигурационного файла с путём к файлу шаблона .json-сообщения
     * @return шаблон .json-сообщения для RabbitMQ
     */
    public String getMessageModel(String configParameter) throws Exception {
        String pathToJsonModel = config.getConfigParameter(configParameter);
        System.out.printf("[ИНФОРМАЦИЯ]: читаем шаблон сообщения из файла: [%s].%n", pathToJsonModel);
        String model = new String(Files.readAllBytes(Paths.get(pathToJsonModel)));
        Assert.assertTrue("[ОШИБКА]: Из файла было считано 0 байт", model.length() > 0);
        System.out.println("[ИНФОРМАЦИЯ]: шаблон .json-сообщения для RabbitMQ прочитан успешно.");
        return model;
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
