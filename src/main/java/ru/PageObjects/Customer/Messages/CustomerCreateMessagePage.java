package ru.PageObjects.Customer.Messages;

import Helpers.Dictionary;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы со страницей Заказчика [Создание сообщения]
 * ( Главная / Заказчикам / Уведомления / Отправка сообщения )
 * ( .kontur.ru/customer/lk/Message/CreateMessage ).
 * Created by Vladimir V. Klochkov on 31.07.2020.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class CustomerCreateMessagePage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов страницы

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     ******************************************************************************************************************/
    public CustomerCreateMessagePage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Поле Кому список адресатов", "//*[@id='multiselect_taglist']/li/span[1]");
        controls.add("Поле Кому иконки удаления адресатов",
                "//*[@id='multiselect_taglist']/li/span[@class='k-icon k-delete']");
        controls.add("Кнопка Выбрать", "//*[@id='select']");
        controls.add("Поле Тема", "//*[@id='MesTheme']");
        controls.add("Поле Текст сообщения", "//*[@id='MesText']");
        controls.add("Ссылка Прикрепить файл", "//*[@id='MessageDocumentUpload']");
        controls.add("Колонка Название файла, все ссылки",
                "//*[@id='MessageDocumentGrid']/table/tbody/tr/td/a[contains(@href, '/files/')]");
        controls.add("Кнопка Отправить", "//*[@id='SendButton']");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет, что открыта страница Заказчика [Создание сообщения].
     *
     * @return страница Заказчика [Создание сообщения]
     */
    public CustomerCreateMessagePage ifPageOpened() throws Exception {
        this.waitForPageLoad();
        this.checkPageUrl("Заказчик/Уведомления/Создание сообщения");
        SelenideElement selectButton = $(this.getBy(controls.find("Кнопка Выбрать")));
        Assert.assertTrue("[ОШИБКА]: страница [Создание сообщения] не открылась", selectButton.isDisplayed());
        selectButton.shouldBe(clickable);

        return this;
    }

    /**
     * Нажимает на указанную кнопку на странице Заказчика [Создание сообщения].
     *
     * @param buttonName имя кнопки
     * @return страница Заказчика [Создание сообщения]
     */
    public CustomerCreateMessagePage clickOnButtonByName(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        $(this.getBy(controls.find(buttonName))).waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName(buttonName);
        this.waitLoadingImage(5);

        return this;
    }

    /**
     * Проверяяет, что поле [Кому] содержит как минимум одного выбранного адресата.
     *
     * @return страница Заказчика [Создание сообщения]
     */
    public CustomerCreateMessagePage checkAtLeastOneRecipientIsSelected() {
        int recipientsSelected = $$(this.getBy(controls.find("Поле Кому иконки удаления адресатов"))).size();
        System.out.printf("[ИНФОРМАЦИЯ]: выбрано [%d] адресатов в поле [Кому].%n", recipientsSelected);
        Assert.assertNotEquals(
                "[ОШИБКА]: не выбрано ни одного адресата в поле [Кому]", 0, recipientsSelected);

        return this;
    }

    /**
     * Устанавливает значение полей с предварительной их очисткой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     * @return страница Заказчика [Создание сообщения]
     */
    public CustomerCreateMessagePage setField(String fieldName, String value) {
        SelenideElement field = $(this.getBy(controls.find(fieldName)));
        field.waitUntil(clickable, timeout, polling).sendKeys(value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
        field.waitUntil(clickable, timeout, polling).shouldHave(value(value));

        return this;
    }

    /**
     * Прикрепляет файл в разделе [Вложения].
     *
     * @param fullName полное имя файла (включая путь к нему)
     * @param fileName имя файла
     * @return страница Заказчика [Создание сообщения]
     */
    public CustomerCreateMessagePage uploadFileIntoAttachments(String fullName, String fileName) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: прикрепляем файл [%s] в разделе [Вложения].%n", fileName);
        $(this.getBy(controls.find("Ссылка Прикрепить файл"))).waitUntil(exist, timeout, polling).sendKeys(fullName);
        this.waitForPageLoad(15);
        ElementsCollection fileNames = $$(this.getBy(controls.find("Колонка Название файла, все ссылки")));
        fileNames.shouldHaveSize(1);
        fileNames.get(0).shouldHave(text(fileName));

        return this;
    }
}
