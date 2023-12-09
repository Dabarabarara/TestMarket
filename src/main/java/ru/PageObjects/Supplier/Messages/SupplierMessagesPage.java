package ru.PageObjects.Supplier.Messages;

import Helpers.Dictionary;
import Helpers.SoftAssert;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Supplier.CommonSupplierPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Класс для работы со страницей Поставщика [Уведомления]
 * ( МОЯ ОРГАНИЗАЦИЯ / Уведомления )
 * ( .kontur.ru/supplier/lk/Private/MessagesList.aspx ).
 * Created by Vladimir V. Klochkov on 03.08.2017.
 * Updated by Vladimir V. Klochkov on 11.03.2021.
 */
public class SupplierMessagesPage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов страницы

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     ******************************************************************************************************************/
    public SupplierMessagesPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Меню МОЯ ОРГАНИЗАЦИЯ", "//span[contains(.,'Моя организация')]");
        controls.add("Пункт подменю Уведомления", "//a[contains(.,'Уведомления')]");
        controls.add("Кнопка Написать", "BaseMainContent_MainContent_lnkNewMessage");
        controls.add("Поле фильтра Дата с",
                "//*[@id='BaseMainContent_MainContent_MessagesContent_commonMessageFilter_txtSendingDateFrom']");
        controls.add("Поле фильтра Дата до",
                "//*[@id='BaseMainContent_MainContent_MessagesContent_commonMessageFilter_txtSendingDateTo']");
        controls.add("Флажок фильтра Непрочитанные",
                "//*[@id='BaseMainContent_MainContent_MessagesContent_commonMessageFilter_chbxIsOnlyNotRead']");
        controls.add("Поле фильтра Искать",
                "//*[@id='BaseMainContent_MainContent_MessagesContent_commonMessageFilter_txtSearchString']");
        controls.add("Кнопка Поиск",
                "//*[@id='BaseMainContent_MainContent_MessagesContent_commonMessageFilter_lbtnSearch']");
        controls.add("Кнопка Очистить",
                "//*[@id='BaseMainContent_MainContent_MessagesContent_commonMessageFilter_lbtnClear']");
        controls.add("Входящие сообщения счетчик непрочитанных сообщений",
                "BaseMainContent_MainContent_lblIncomingUnreadCount");
        controls.add("Результаты поиска колонка Тема все строки",
                "//*[@id='BaseMainContent_MainContent_MessagesContent_jqgMessages']//tr/td[5]/a");
        controls.add("Количество результатов на странице поиска",
                "//*[@id='BaseMainContent_MainContent_MessagesContent_jqgMessages_pager_center']/table//select");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Осуществляет переход на страницу Поставщика [Уведомления] используя главное меню личного кабинета.
     *
     * @param option требуемое количество результатов на странице поиска
     * @return страница Поставщика [Уведомления]
     */
    public SupplierMessagesPage goToMessages(String option) throws Exception {
        SelenideElement messagesLink = $(By.xpath(controls.find("Пункт подменю Уведомления")));
        if (!messagesLink.isDisplayed()) {
            $(By.xpath(controls.find("Меню МОЯ ОРГАНИЗАЦИЯ"))).waitUntil(clickable, timeout, polling).click();
            System.out.println("[ИНФОРМАЦИЯ]: произведено нажатие ссылки [МОЯ ОРГАНИЗАЦИЯ].");
            TimeUnit.SECONDS.sleep(3);
        }
        messagesLink.waitUntil(clickable, timeout, polling).click();
        System.out.println("[ИНФОРМАЦИЯ]: произведено нажатие ссылки [Уведомления].");
        this.waitForPageLoad();
        Assert.assertTrue("[ОШИБКА]: текущий URL страницы не соотвествует странице [Уведомления]",
                url().contains(".kontur.ru/supplier/lk/Private/MessagesList.aspx"));
        Assert.assertTrue("[ОШИБКА]: страница [Уведомления] не открылась",
                $(By.xpath(controls.find("Кнопка Поиск"))).waitUntil(visible, timeout, polling).isDisplayed());
        SelenideElement resultsOnPage = $(By.xpath(controls.find("Количество результатов на странице поиска")));
        resultsOnPage.selectOption(option);
        this.waitForPageLoad();
        resultsOnPage.waitUntil(clickable, timeout, polling).shouldHave(text(option));

        return this;
    }

    /**
     * Делает щелчок мышью по кнопке [Написать].
     */
    public void clickOnWriteAMessageButton() {
        SelenideElement writeAMessageButton = $(this.getBy(controls.find("Кнопка Написать")));
        this.logButtonNameToPress("Написать");
        writeAMessageButton.waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName("Написать");
    }

    /**
     * Устанавливает значение поля [Дата - с].
     *
     * @param dateFromValue требуемое значение поля [Дата - с]
     * @return страница Поставщика [Уведомления]
     */
    public SupplierMessagesPage setDateFrom(String dateFromValue) throws Exception {
        this.waitClearClickAndSendKeys(By.xpath(controls.find("Поле фильтра Дата с")), dateFromValue);
        this.logFilledFieldName("Поле фильтра Дата с", dateFromValue);

        return this;
    }

    /**
     * Устанавливает значение поля [Дата - до].
     *
     * @param dateToValue требуемое значение поля [Дата - до]
     * @return страница Поставщика [Уведомления]
     */
    public SupplierMessagesPage setDateTo(String dateToValue) throws Exception {
        this.waitClearClickAndSendKeys(By.xpath(controls.find("Поле фильтра Дата до")), dateToValue);
        this.logFilledFieldName("Поле фильтра Дата до", dateToValue);

        return this;
    }

    /**
     * Устанавливает значение поля [Искать].
     *
     * @param searchForText требуемое значение поля [Искать]
     * @return страница Поставщика [Уведомления]
     */
    public SupplierMessagesPage setSearchForText(String searchForText) throws Exception {
        this.waitClearClickAndSendKeys(By.xpath(controls.find("Поле фильтра Искать")), searchForText);
        this.logFilledFieldName("Поле фильтра Искать", searchForText);

        return this;
    }

    /**
     * Делает щелчок мышью по кнопке [Поиск] и проверяет количество уведомлений в результатах поиска.
     *
     * @return страница Поставщика [Уведомления]
     */
    public SupplierMessagesPage clickOnSearchButton() throws Exception {
        int i = 1;
        int size;

        // Цикл ожидания пока не будет найдено хотя бы одно уведомление
        do {
            this.logButtonNameToPress("Поиск");
            $(By.xpath(controls.find("Кнопка Поиск"))).waitUntil(clickable, timeout, polling).click();
            this.logPressedButtonName("Поиск");
            this.waitLoadingRectangle();
            ElementsCollection results = $$(By.xpath(controls.find("Результаты поиска колонка Тема все строки")));
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
     * Делает щелчок мышью по кнопке [Очистить].
     *
     * @return страница Поставщика [Уведомления]
     */
    public SupplierMessagesPage clickOnClearButton() throws Exception {
        this.logButtonNameToPress("Очистить");
        $(By.xpath(controls.find("Кнопка Очистить"))).waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName("Очистить");
        this.waitLoadingImage();

        return this;
    }

    /**
     * Проверка того, что поле [Искать] не содержит информации.
     */
    public void checkFindFieldIsEmpty() {
        SelenideElement subjectField = $(By.xpath(controls.find("Поле фильтра Искать")));
        System.out.println("[ИНФОРМАЦИЯ]: произведена проверка того, что поле [Искать] пусто.");
        Assert.assertEquals("[ОШИБКА]: поле [Искать] не пустое", 0, subjectField.val().length());
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
     * Проверяет значение всех строк колонки [Тема] в результатах поиска уведомлений.
     *
     * @param subject искомое значение в колонке [Тема]
     * @return страница Поставщика [Уведомления]
     */
    public SupplierMessagesPage checkSubject(String subject) {
        String cleared = subject.replace("  ", " ");
        System.out.printf("[ИНФОРМАЦИЯ]: искомое значение в колонке [Тема] - [%s].%n", cleared);
        ElementsCollection results = $$(By.xpath(controls.find("Результаты поиска колонка Тема все строки")));
        int found = 0;

        for (SelenideElement result : results) {
            String actual = result.waitUntil(visible, timeout, polling).getText();
            System.out.printf("[ИНФОРМАЦИЯ]: текущее значение в колонке [Тема] - [%s].%n", actual);
            if (actual.contains(cleared)) {
                System.out.printf("[ИНФОРМАЦИЯ]: текущее значение в колонке [Тема] - [%s] соответствует искомому.%n",
                        subject);
                found++;
            }
        }

        System.out.printf("[ИНФОРМАЦИЯ]: найдено - [%d] совпадений в колонке [Тема].%n", found);
        SoftAssert.assertNotEquals(
                "[ОШИБКА]: искомое значение не найдено в колонке [Тема] на этой странице", 0, found);
        SoftAssert.assertFalse(
                "[ОШИБКА]: искомое значение задублировано в колонке [Тема] несколько раз", found != 1);

        return this;
    }

    /**
     * Открывает уведомление по заданному значению из колонки [Тема] в результатах поиска уведомлений.
     *
     * @param subject subject искомое значение в колонке [Тема]
     * @return страница Поставщика [Уведомления]
     */
    public SupplierMessagesPage clickOnMessageInSearchResultsBySubject(String subject) throws Exception {
        ElementsCollection results = $$(By.xpath(controls.find("Результаты поиска колонка Тема все строки")));
        System.out.printf("[ИНФОРМАЦИЯ]: искомое значение в колонке [Тема] - [%s].%n", subject);

        results.shouldHaveSize(1);
        System.out.printf("[ИНФОРМАЦИЯ]: открываем уведомление по ссылке в колонке [Тема] - [%s].%n", subject);
        results.get(0).waitUntil(clickable, timeout, polling).click();
        this.waitForPageLoad(15);

        return this;
    }
}
