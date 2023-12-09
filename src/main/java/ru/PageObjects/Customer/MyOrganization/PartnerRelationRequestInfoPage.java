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
 * ( Главная / Заказчикам / Уведомления / Перейти к рассмотрению запроса можно по ссылке )
 * ( .kontur.ru/supplier/lk/PartnerRelation/PartnerRelationRequestInfo.aspx? ).
 * Created by Vladimir V. Klochkov on 09.01.2020.
 * Updated by Vladimir V. Klochkov on 25.02.2021.
 */
public class PartnerRelationRequestInfoPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов страницы

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public PartnerRelationRequestInfoPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Заголовок страницы", "//div[@class='main-header-title']");
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Номер запроса", "BaseMainContent_MainContent_ucPartnerRelationRequestInfo_lbRequestNumber");
        controls.add("Бизнес оператор", "BaseMainContent_MainContent_ucPartnerRelationRequestInfo_lbBusinnesOperator");
        controls.add("Организация реципиент прав",
                "BaseMainContent_MainContent_ucPartnerRelationRequestInfo_lbOrganizationRecipient");
        controls.add("Статус", "BaseMainContent_MainContent_ucPartnerRelationRequestInfo_lbAccept");
        controls.add("Дата принятия", "BaseMainContent_MainContent_ucPartnerRelationRequestInfo_lbAcceptDate");
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Принять запрос", "BaseMainContent_MainContent_lbAccept");
        controls.add("Отказать в принятии", "BaseMainContent_MainContent_lbRefuse");
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
     */
    public void verifyField(String fieldName, String value) {
        String message = String.format("[ОШИБКА]: значение [%s] не содержится в поле [%s]", value, fieldName);

        By field = this.getBy(controls.find(fieldName));

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        Assert.assertTrue(message, this.verifyFieldContent(field, value));
    }

    /**
     * Производит нажатие на кнопку.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.clickInElementJS(this.getBy(controls.find(buttonName)));
        this.logPressedButtonName(buttonName);
        this.waitForPageLoad();
    }
}
