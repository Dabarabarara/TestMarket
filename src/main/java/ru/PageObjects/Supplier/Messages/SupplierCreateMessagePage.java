package ru.PageObjects.Supplier.Messages;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.Messages.CustomerCreateMessagePage;
import ru.PageObjects.Supplier.CommonSupplierPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы со страницей Поставщика [Отправить]
 * ( МОЯ ОРГАНИЗАЦИЯ / Уведомления / [Написать] )
 * ( .kontur.ru/supplier/lk/Private/WriteMessage.aspx?Type=Incoming ).
 * Created by Vladimir V. Klochkov on 19.08.2020.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class SupplierCreateMessagePage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов страницы

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     ******************************************************************************************************************/
    public SupplierCreateMessagePage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Кнопка Написать", "BaseMainContent_MainContent_lnkNewMessage");
        controls.add("Кнопка Настройка", "BaseMainContent_MainContent_lnkSettings");
        controls.add("Кнопка Отправить", "BaseMainContent_MainContent_MessagesContent_LBtnSend");
        controls.add("Кнопка Отменить", "BaseMainContent_MainContent_MessagesContent_lbtnCancel");
        controls.add("Входящие сообщения счетчик непрочитанных сообщений",
                "BaseMainContent_MainContent_lblIncomingUnreadCount");
        controls.add("Поле Кому", "BaseMainContent_MainContent_MessagesContent_txtInvited");
        controls.add("Кнопка Выбрать", "BaseMainContent_MainContent_MessagesContent_btnChoseInvited");
        controls.add("Поле От", "BaseMainContent_MainContent_MessagesContent_txtFrom");
        controls.add("Поле Тема", "BaseMainContent_MainContent_MessagesContent_txtMessageTheme");
        controls.add("Поле Текст сообщения", "BaseMainContent_MainContent_MessagesContent_txtMessageBody");
        controls.add("Кнопка Добавить", "//*[@id='BaseMainContent_MainContent_MessagesContent_ufFiles_btnUpload']/input");
        controls.add("Название файла, все ссылки",
                "//*[@id='BaseMainContent_MainContent_MessagesContent_ufFiles_ulDocuments']//a[@class='upload-file']");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет, что открыта страница Поставщика [Отправить].
     *
     * @return страница Поставщика [Отправить]
     */
    public SupplierCreateMessagePage ifPageOpened() throws Exception {
        this.waitForPageLoad();
        this.checkPageUrl("МОЯ ОРГАНИЗАЦИЯ/Уведомления/[Написать]");
        SelenideElement selectButton = $(this.getBy(controls.find("Кнопка Выбрать")));
        Assert.assertTrue("[ОШИБКА]: страница [Отправить] не открылась", selectButton.isDisplayed());
        selectButton.shouldBe(clickable);

        return this;
    }

    /**
     * Нажимает на указанную кнопку на странице Поставщика [Отправить].
     *
     * @param buttonName имя кнопки
     * @return страница Поставщика [Отправить]
     */
    public SupplierCreateMessagePage clickOnButtonByName(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        $(this.getBy(controls.find(buttonName))).waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName(buttonName);
        this.waitLoadingImage(5);

        return this;
    }

    /**
     * Проверяет, что поле содержит не пустое значение.
     *
     * @param fieldName имя поля
     * @return страница Поставщика [Отправить]
     */
    public SupplierCreateMessagePage verifyFieldIsNotEmpty(String fieldName) {
        String message = String.format("[ОШИБКА]: поле: [%s] содержит пустое значение", fieldName);

        By field = this.getBy(controls.find(fieldName));

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] на не пустое значение.%n", fieldName);
        Assert.assertTrue(message, this.verifyFieldIsNotEmpty(field));

        return this;
    }

    /**
     * Устанавливает значение полей с предварительной их очисткой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     * @return страница Поставщика [Отправить]
     */
    public SupplierCreateMessagePage setField(String fieldName, String value) {
        SelenideElement field = $(this.getBy(controls.find(fieldName)));
        field.waitUntil(clickable, timeout, polling).sendKeys(value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
        field.waitUntil(clickable, timeout, polling).shouldHave(value(value));

        return this;
    }

    /**
     * Прикрепляет файл в разделе [Документы].
     *
     * @param fullName полное имя файла (включая путь к нему)
     * @param fileName имя файла
     * @return страница Поставщика [Отправить]
     */
    public SupplierCreateMessagePage uploadFileIntoDocuments(String fullName, String fileName) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: прикрепляем файл [%s] в разделе [Документы].%n", fileName);
        $(this.getBy(controls.find("Кнопка Добавить"))).waitUntil(exist, timeout, polling).sendKeys(fullName);
        this.waitForPageLoad(15);
        ElementsCollection fileNames = $$(this.getBy(controls.find("Название файла, все ссылки")));
        fileNames.shouldHaveSize(1);
        fileNames.get(0).shouldHave(text(fileName));

        return this;
    }
}
