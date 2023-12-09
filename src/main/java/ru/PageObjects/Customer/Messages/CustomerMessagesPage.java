package ru.PageObjects.Customer.Messages;

import Helpers.Dictionary;
import Helpers.SoftAssert;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы со страницами Заказчика [Входящие сообщения] и [Отправленные сообщения]
 * ( Главная / Заказчикам / Уведомления )
 * ( .kontur.ru/customer/lk/Message и .kontur.ru/customer/lk/Message/outMessage ).
 * Created by Vladimir V. Klochkov on 31.07.2017.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class CustomerMessagesPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов страницы

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     ******************************************************************************************************************/
    public CustomerMessagesPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Меню УВЕДОМЛЕНИЯ", "//a[@class='dropdown-toggle' and contains(.,'УВЕДОМЛЕНИЯ')]");
        controls.add("Пункт подменю ВХОДЯЩИЕ", "//a[@href='/customer/lk/Message']");
        controls.add("Пункт подменю ОТПРАВЛЕННЫЕ", "//a[@href='/customer/lk/Message/outMessage']");
        controls.add("Входящие сообщения счетчик непрочитанных сообщений",
                "//span[@class='label label-warning message-count']");
        controls.add("Кнопка Новое сообщение", "//*[@id='createMessage']");
        controls.add("Кнопка Фильтр", "//*[@id='hiddenFilterButton']");
        controls.add("Поле фильтра Тема", "//*[@id='Filter_Theme']");
        controls.add("Поле фильтра Дата отправки с", "//*[@id='DateFrom']");
        controls.add("Поле фильтра Дата отправки по", "//*[@id='DateTo']");
        controls.add("Кнопка Показать", "//button[contains(.,'Показать')]");
        controls.add("Кнопка Очистить фильтр", "//button[contains(.,'Очистить фильтр')]");
        controls.add("Результаты поиска колонка Входящий номер все строки", "//*[@id='MesGrid']/table//tr/td[1]/a");
        controls.add("Результаты поиска колонка Входящий номер по номеру",
                "//*[@id='MesGrid']/table//tr/td[1]/a[@href='/customer/lk/Message/ViewMessage/%s']");
        controls.add("Результаты поиска колонка Тема все строки", "//*[@id='MesGrid']/table//tr/td[3]");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Осуществляет переход на страницу Заказчика [Входящие сообщения] используя главное меню личного кабинета.
     *
     * @return страница Заказчика [Входящие сообщения]
     */
    public CustomerMessagesPage goToIncomingMessages() throws Exception {
        SelenideElement incomingMessagesLink = $(this.getBy(controls.find("Пункт подменю ВХОДЯЩИЕ")));

        if (!incomingMessagesLink.isDisplayed()) {
            $(this.getBy(controls.find("Меню УВЕДОМЛЕНИЯ"))).waitUntil(clickable, timeout, polling).click();
            System.out.println("[ИНФОРМАЦИЯ]: произведено нажатие ссылки [УВЕДОМЛЕНИЯ].");
            TimeUnit.SECONDS.sleep(3);
        }

        incomingMessagesLink.waitUntil(clickable, timeout, polling).click();
        System.out.println("[ИНФОРМАЦИЯ]: произведено нажатие ссылки [ВХОДЯЩИЕ].");
        this.waitForPageLoad();
        this.checkPageUrl("УВЕДОМЛЕНИЯ/ВХОДЯЩИЕ");
        Assert.assertTrue("[ОШИБКА]: страница [Входящие сообщения] не открылась",
                $(this.getBy(controls.find("Кнопка Фильтр"))).isDisplayed());

        return this;
    }

    /**
     * Осуществляет переход на страницу Заказчика [Отправленные сообщения] используя главное меню личного кабинета.
     *
     * @return страница Заказчика [Отправленные сообщения]
     */
    public CustomerMessagesPage goToOutgoingMessages() throws Exception {
        SelenideElement outgoingMessagesLink = $(this.getBy(controls.find("Пункт подменю ОТПРАВЛЕННЫЕ")));

        if (!outgoingMessagesLink.isDisplayed()) {
            $(this.getBy(controls.find("Меню УВЕДОМЛЕНИЯ"))).waitUntil(clickable, timeout, polling).click();
            System.out.println("[ИНФОРМАЦИЯ]: произведено нажатие ссылки [УВЕДОМЛЕНИЯ].");
            TimeUnit.SECONDS.sleep(3);
        }

        outgoingMessagesLink.waitUntil(clickable, timeout, polling).click();
        System.out.println("[ИНФОРМАЦИЯ]: произведено нажатие ссылки [ОТПРАВЛЕННЫЕ].");
        this.waitForPageLoad();
        this.checkPageUrl("УВЕДОМЛЕНИЯ/ОТПРАВЛЕННЫЕ");
        Assert.assertTrue("[ОШИБКА]: страница [Отправленные сообщения] не открылась",
                $(this.getBy(controls.find("Кнопка Фильтр"))).isDisplayed());

        return this;
    }

    /**
     * Делает щелчок мышью по кнопке [Новое сообщение].
     */
    public void clickOnNewMessageButton() {
        SelenideElement createMessageButton = $(this.getBy(controls.find("Кнопка Новое сообщение")));
        this.logButtonNameToPress("Новое сообщение");
        createMessageButton.waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName("Новое сообщение");
    }

    /**
     * Возвращает количество непрочитанных входящих уведомлений или 0 если таковых нет ( в виде строки ).
     *
     * @return количество непрочитанных входящих уведомлений
     */
    public String getUnreadMessagesCounter() {
        SelenideElement unreadMessagesCounter =
                $(this.getBy(controls.find("Входящие сообщения счетчик непрочитанных сообщений")));

        if (unreadMessagesCounter.isDisplayed() && !unreadMessagesCounter.getText().isEmpty())
            return unreadMessagesCounter.getText();
        else
            return "0";
    }

    /**
     * Делает щелчок мышью по кнопке [Фильтр] и проверяет видимость поля [Тема] в критериях поиска.
     *
     * @return страница Заказчика [Входящие сообщения]
     */
    public CustomerMessagesPage clickOnFilterButton() throws Exception {
        SelenideElement buttonFilter = $(this.getBy(controls.find("Кнопка Фильтр")));
        this.logButtonNameToPress("Фильтр");
        buttonFilter.waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName("Фильтр");
        TimeUnit.SECONDS.sleep(3);
        SelenideElement fieldSubject = $(this.getBy(controls.find("Поле фильтра Тема")));
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        if (!fieldSubject.isDisplayed()) buttonFilter.click();
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        Assert.assertTrue("[ОШИБКА]: поле [Тема] не отображается на форме", fieldSubject.isDisplayed());

        return this;
    }

    /**
     * Устанавливает значение поля [Тема].
     *
     * @param subject требуемое значение поля [Тема]
     * @return страница Заказчика [Входящие сообщения]
     */
    public CustomerMessagesPage setSubjectField(String subject) {
        SelenideElement subjectField = $(this.getBy(controls.find("Поле фильтра Тема")));
        subjectField.waitUntil(visible, timeout, polling).val(subject);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [Тема] значением [%s].%n", subject);
        subjectField.click();
        Assert.assertEquals("[ОШИБКА]: Неправильное значение в поле [Тема]", subject, subjectField.val());

        return this;
    }

    /**
     * Устанавливает значение поля [Дата отправки - с].
     *
     * @param dateFromValue требуемое значение поля [Дата отправки - с]
     * @return страница Заказчика [Входящие сообщения]
     */
    public CustomerMessagesPage setDispatchDateFrom(String dateFromValue) throws Exception {
        this.waitClearClickAndSendKeys(this.getBy(controls.find("Поле фильтра Дата отправки с")), dateFromValue);
        this.logFilledFieldName("Поле фильтра Дата отправки с", dateFromValue);

        return this;
    }

    /**
     * Устанавливает значение поля [Дата отправки - по].
     *
     * @param dateToValue требуемое значение поля [Дата отправки - по]
     * @return страница Заказчика [Входящие сообщения]
     */
    public CustomerMessagesPage setDispatchDateTo(String dateToValue) throws Exception {
        this.waitClearClickAndSendKeys(this.getBy(controls.find("Поле фильтра Дата отправки по")), dateToValue);
        this.logFilledFieldName("Поле фильтра Дата отправки по", dateToValue);

        return this;
    }

    /**
     * Делает щелчок мышью по кнопке [Показать] и проверяет количество уведомлений в результатах поиска.
     *
     * @return страница Заказчика [Входящие сообщения]
     */
    public CustomerMessagesPage clickOnShowButton() throws Exception {
        int i = 1;
        int size;

        // Цикл ожидания пока не будет найдено хотя бы одно уведомление
        do {
            this.logButtonNameToPress("Показать");
            $(this.getBy(controls.find("Кнопка Показать"))).waitUntil(clickable, timeout, polling).click();
            this.logPressedButtonName("Показать");
            this.waitLoadingImage();
            ElementsCollection results = $$(this.getBy(controls.find("Результаты поиска колонка Тема все строки")));
            size = results.size();
            System.out.printf("[ИНФОРМАЦИЯ]: попытка № [%d].%n", i);
            System.out.printf("[ИНФОРМАЦИЯ]: результаты поиска содержат [%d] уведомлений.%n", size);
            if (size == 0) TimeUnit.SECONDS.sleep(10);
            i++;
        } while (size == 0 && i <= 50);

        // Проверяем, по какому условию мы вышли из цикла ожидания ( ничего не нашли или нашли что-то )
        SoftAssert.assertNotEquals("[ОШИБКА]: результаты поиска пустые", 0, size);

        return this;
    }

    /**
     * Делает щелчок мышью по кнопке [Показать] и проверяет количество уведомлений в результатах поиска.
     *
     * @param expected ожидаемое количество уведомлений в результатах поиска
     * @return страница Заказчика [Входящие сообщения]
     */
    public CustomerMessagesPage clickOnShowButton(int expected) throws Exception {
        int i = 1;
        int size;

        // Цикл ожидания пока не будет найдено хотя бы одно уведомление
        do {
            this.logButtonNameToPress("Показать");
            $(this.getBy(controls.find("Кнопка Показать"))).waitUntil(clickable, timeout, polling).click();
            this.logPressedButtonName("Показать");
            this.waitLoadingImage();
            ElementsCollection results = $$(this.getBy(controls.find("Результаты поиска колонка Тема все строки")));
            size = results.size();
            System.out.printf("[ИНФОРМАЦИЯ]: попытка № [%d].%n", i);
            System.out.printf("[ИНФОРМАЦИЯ]: результаты поиска содержат [%d] уведомлений.%n", size);
            if (size == 0) TimeUnit.SECONDS.sleep(10);
            i++;
        } while (size == 0 && i <= 50);

        // Проверяем, по какому условию мы вышли из цикла ожидания ( ничего не нашли или нашли что-то )
        SoftAssert.assertNotEquals("[ОШИБКА]: результаты поиска пустые", 0, size);

        // Этот код сделан для обхода бага с дублированием найденных уведомлений чтобы не валить каждый раз весь тест
        if (size != expected) {
            screenshoter.takeScreenshot();
            SoftAssert.assertEquals(
                    "[ОШИБКА]: результаты поиска содержат более одного уведомления", expected, size);
        }

        return this;
    }

    /**
     * Открывает входящее уведомление в результатах поиска по его номеру.
     *
     * @param messageNumber номер входящего уведомления
     * @return страница Заказчика [Входящие сообщения]
     */
    public CustomerMessagesPage openIncomingMessageByMessageNumber(String messageNumber) throws Exception {
        String incomingMessageLocator = String.format(
                controls.find("Результаты поиска колонка Входящий номер по номеру"), messageNumber);
        System.out.printf(
                "[ИНФОРМАЦИЯ]: сформирован локатор для поиска уведомления в колонке [Входящий номер] - [%s].%n",
                incomingMessageLocator);
        SelenideElement incomingMessage = $(this.getBy(incomingMessageLocator));
        System.out.printf(
                "[ИНФОРМАЦИЯ]: ищем и открываем входящее уведомление с номером [%s] в таблице с результатами поиска.%n",
                messageNumber);
        incomingMessage.waitUntil(exist, timeout, polling).shouldBe(clickable);
        incomingMessage.click();
        this.checkPageUrl("Просмотр сообщения");
        System.out.println("[ИНФОРМАЦИЯ]: уведомление успешно найдено и открыто по ссылке с его номером.");

        return this;
    }

    /**
     * Проверяет значение первого из полей в колонке [Тема] результатов поиска уведомлений.
     *
     * @param subject требуемое значение поля в колонке [Тема]
     * @return страница Заказчика [Входящие сообщения]
     */
    public CustomerMessagesPage checkFirstSubject(String subject) {
        System.out.printf("[ИНФОРМАЦИЯ]: требуемое значение поля в колонке [Тема] - [%s].%n", subject);
        ElementsCollection results = $$(this.getBy(controls.find("Результаты поиска колонка Тема все строки")));
        String actual = results.get(0).waitUntil(visible, timeout, polling).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: реальное  значение поля в колонке [Тема] - [%s].%n", actual);
        SoftAssert.assertTrue("[ОШИБКА]: неправильное значение результатов поиска в колонке [Тема]",
                actual.contains(subject));

        return this;
    }

    /**
     * Переходит по первой (верхней) ссылке в колонке [Входящий номер] результатов поиска уведомлений.
     */
    public void clickOnFirstIncomingNumberLink() throws Exception {
        SelenideElement link = $$(this.getBy(controls.find("Результаты поиска колонка Входящий номер все строки"))).
                get(0);
        System.out.printf("[ИНФОРМАЦИЯ]: переходим по первой (верхней) ссылке [%s] в колонке " +
                "[Входящий номер] результатов поиска уведомлений.%n", link.getText());
        this.scrollToCenterAndclickInElementJS(link);
    }

    /**
     * Делает щелчок мышью по кнопке [Очистить фильтр].
     *
     * @return страница Заказчика [Входящие сообщения]
     */
    public CustomerMessagesPage clickOnClearFilterButton() throws Exception {
        this.logButtonNameToPress("Очистить фильтр");
        $(this.getBy(controls.find("Кнопка Очистить фильтр"))).waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName("Очистить фильтр");
        this.waitLoadingImage();

        return this;
    }

    /**
     * Проверка того, что поле [Тема] не содержит информации.
     */
    public void checkSubjectFieldIsEmpty() {
        SelenideElement subjectField = $(this.getBy(controls.find("Поле фильтра Тема")));
        System.out.println("[ИНФОРМАЦИЯ]: произведена проверка того, что поле [Тема] пусто.");
        Assert.assertEquals("[ОШИБКА]: поле [Тема] не пустое", 0, subjectField.val().length());
    }
}
