package ru.PageObjects.Customer.MyOrganization;

import Helpers.Dictionary;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы со страницей [Запрос на установление связи]
 * ( Моя организация / Мои полномочия в закупках партнеров / Кнопка [Запросить полномочия] )
 * ( .kontur.ru/supplier/lk/PartnerRelation/PartnerRelationRequest.aspx? ).
 * Created by Kirill G. Rydzyvylo on 17.12.2019.
 * Updated by Vladimir V. Klochkov on 11.03.2021.
 */
public class PartnerRelationRequestPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов страницы

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public PartnerRelationRequestPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Заголовок страницы", "//div[@class='main-header-title']");
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Выбрать", "BaseMainContent_MainContent_btnChoseInvited");
        controls.add("Организация партнёр", "BaseMainContent_MainContent_txtInvited");
        controls.add("Наименование связи", "BaseMainContent_MainContent_txtComments");
        controls.add("Право представителя на электронной площадке", "BaseMainContent_MainContent_chkAllowLotCustomer");
        controls.add("Право просмотра отчетности по закупкам", "BaseMainContent_MainContent_chkAllowStats");
        controls.add("Право просмотра всех закупок", "BaseMainContent_MainContent_chkAllowViewTrades");
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Запросить доступ", "BaseMainContent_MainContent_lbSend");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет значение поля.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение, которое должно содержаться в поле
     * @return страница [Запрос на установление связи]
     */
    public PartnerRelationRequestPage verifyField(String fieldName, String value) {
        String message = String.format("[ОШИБКА]: значение [%s] не содержится в поле [%s]", value, fieldName);

        By field = this.getBy(controls.find(fieldName));

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        Assert.assertTrue(message, this.verifyFieldContent(field, value));

        return this;
    }

    /**
     * Проверяет, что поле не доступно для редактирования.
     *
     * @param fieldName имя поля
     */
    public void verifyFieldIsNotEditable(String fieldName) {
        SelenideElement field = $(this.getBy(controls.find(fieldName)));
        String oldValue = field.getValue();
        String newValue = "New Value " + oldValue;

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] на недоступность для редактирования.%n", fieldName);
        field.click();
        field.sendKeys(newValue);

        Assert.assertNotEquals(
                String.format("[ОШИБКА]: удалось установить новое значение в поле [%s]", fieldName),
                newValue, field.getValue());
        Assert.assertEquals("[ОШИБКА]: старое значение поля не сохранилось после попытки редактирования",
                oldValue, field.getValue());
        System.out.printf("[ИНФОРМАЦИЯ]: поле [%s] недоступно для редактирования.%n", fieldName);
    }

    /**
     * Устанавливает значение текстового поля.
     *
     * @param fieldName имя текстового поля
     * @param value     требуемое значение поля
     * @return страница [Запрос на установление связи]
     */
    public PartnerRelationRequestPage setField(String fieldName, String value) throws Exception {
        SelenideElement field = $(this.getBy(controls.find(fieldName)));
        field.waitUntil(visible, timeout, polling).sendKeys(value);
        TimeUnit.SECONDS.sleep(3);
        Assert.assertEquals(String.format("[ОШИБКА]: не удалось установить значение [%s] в поле [%s]", value, fieldName),
                value, field.getValue());
        this.logFilledFieldName(fieldName, value);

        return this;
    }

    /**
     * Производит нажатие на кнопку.
     *
     * @param buttonName имя кнопки
     * @return страница [Запрос на установление связи]
     */
    public PartnerRelationRequestPage clickOnButton(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.clickInElementJS(this.getBy(controls.find(buttonName)));
        this.logPressedButtonName(buttonName);
        this.waitForPageLoad();

        return this;
    }

    /**
     * Устанавливает или сбрасывает флажок с указанным именем.
     *
     * @param checkBoxName имя флажка
     * @return страница [Запрос на установление связи]
     */
    public PartnerRelationRequestPage setCheckBoxValue(String checkBoxName) {
        String message = "[ОШИБКА]: Состояние флажка [%s] не изменилось после щелчка мышью.";

        boolean oldValue = this.getCheckBoxValue(checkBoxName);
        $(By.id(controls.find(checkBoxName))).waitUntil(exist, timeout, polling).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено изменение состояния флажка: [%s].%n", checkBoxName);
        boolean newValue = this.getCheckBoxValue(checkBoxName);
        System.out.printf("[ИНФОРМАЦИЯ]: флажок: [%s] находится %s отмеченном состоянии.%n",
                checkBoxName, ((newValue) ? "в" : "в не"));
        Assert.assertNotEquals(String.format(message, checkBoxName), oldValue, newValue);

        return this;
    }

    /**
     * Возвращает значение флажка с указанным именем.
     *
     * @param checkBoxName имя флажка
     * @return значение флажка
     */
    private boolean getCheckBoxValue(String checkBoxName) {
        $(By.id(controls.find(checkBoxName))).waitUntil(exist, timeout, polling).shouldBe(visible);
        return $(By.id(controls.find(checkBoxName))).isSelected();
    }

    /**
     * Проверяет, что флажок с указанным именем не выбран.
     *
     * @param checkBoxName имя флажка
     * @return страница [Запрос на установление связи]
     */
    public PartnerRelationRequestPage verifyCheckBoxNotSelected(String checkBoxName) {
        Assert.assertFalse(
                String.format("[ОШИБКА]: Флажок [%s] установлен.", checkBoxName), this.getCheckBoxValue(checkBoxName));
        System.out.printf("[ИНФОРМАЦИЯ]: флажок: [%s] находится в не отмеченном состоянии.%n", checkBoxName);

        return this;
    }
}
