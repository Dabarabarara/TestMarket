package ru.PageObjects.Customer.Protocol;

import Helpers.Dictionary;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс описывающий страницу [Протокол обсуждения функциональных характеристик] (customer/lk/Protocols/ProtocolForm?).
 * Created by Vladimir V. Klochkov on 18.12.2018.
 * Updated by Vladimir V. Klochkov on 25.02.2021.
 */
public class SMBTenderDiscussionOfFunctionalCharacteristicsProtocolPage extends CommonCustomerProtocolPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Раздел [Решение комиссии]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Решение комиссии]
    private static final String COMMISSION_DECISION_HEADER_XPATH = "//h2[contains(.,'Решение комиссии')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Принятое решение о необходимости уточнения характеристик] - текущее выбранное значение
    private static final String COMMISSION_DECISION_VALUE_XPATH =
            "//td[contains(., 'Принятое решение о необходимости уточнения характеристик:')]/following-sibling::td[1]" +
                    "//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Принятое решение о необходимости уточнения характеристик] - кнопка открытия списка допустимых значений
    private static final String COMMISSION_DECISION_OPEN_VALUES_LIST_XPATH =
            "//td[contains(., 'Принятое решение о необходимости уточнения характеристик:')]/following-sibling::td[1]" +
                    "//span[@class='k-select']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Принятое решение о необходимости уточнения характеристик] -
    // значение [Требуется уточнение функциональных характеристик] в открытом списке
    private static final String COMMISSION_DECISION_REQUIRES_CLARIFICATION_VALUE_XPATH =
            "//*[@id='SpecificationRefinementRequiredDropDownList_listbox']" +
                    "/li[contains(., 'Требуется уточнение функциональных характеристик')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Принятое решение о необходимости уточнения характеристик] -
    // значение [Не требуется уточнение функциональных характеристик] в открытом списке
    private static final String COMMISSION_DECISION_NOT_REQUIRES_CLARIFICATION_VALUE_XPATH =
            "//*[@id='SpecificationRefinementRequiredDropDownList_listbox']" +
                    "/li[contains(., 'Не требуется уточнение функциональных характеристик')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary partitionNames = new Dictionary(); // основные разделы протокола

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public SMBTenderDiscussionOfFunctionalCharacteristicsProtocolPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        partitionNames.add("Решение комиссии", COMMISSION_DECISION_HEADER_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Переходит к просмотру раздела протокола с указанным именем.
     *
     * @param partitionName имя раздела
     */
    public void goToPartition(String partitionName) throws Exception {
        $(this.getBy(partitionNames.find(partitionName))).shouldBe(clickable).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведен переход к просмотру раздела: [%s].%n", partitionName);
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * Устанавливает значение поля [Принятое решение о необходимости уточнения характеристик] в
     * [Требуется уточнение функциональных характеристик].
     */
    public void setDecisionRequiresClarificationOfFunctionalCharacteristics() throws Exception {
        SelenideElement openListButton = $(By.xpath(COMMISSION_DECISION_OPEN_VALUES_LIST_XPATH)).
                waitUntil(exist, timeout, polling).shouldBe(clickable);
        SelenideElement desiredValue = $(By.xpath(COMMISSION_DECISION_REQUIRES_CLARIFICATION_VALUE_XPATH));
        SelenideElement actualValue = $(By.xpath(COMMISSION_DECISION_VALUE_XPATH));

        this.waitForListOpens(openListButton, desiredValue);
        String expected = desiredValue.waitUntil(clickable, timeout, polling).getText();
        this.clickInElementJS(desiredValue);
        TimeUnit.SECONDS.sleep(3);

        actualValue.waitUntil(exist, timeout, polling).shouldHave(text(expected));
    }

    /**
     * Устанавливает значение поля [Принятое решение о необходимости уточнения характеристик] в
     * [Не требуется уточнение функциональных характеристик].
     */
    public void setDecisionNotRequiresClarificationOfFunctionalCharacteristics() throws Exception {
        SelenideElement openListButton = $(By.xpath(COMMISSION_DECISION_OPEN_VALUES_LIST_XPATH)).
                waitUntil(exist, timeout, polling).shouldBe(clickable);
        SelenideElement desiredValue = $(By.xpath(COMMISSION_DECISION_NOT_REQUIRES_CLARIFICATION_VALUE_XPATH));
        SelenideElement actualValue = $(By.xpath(COMMISSION_DECISION_VALUE_XPATH));

        this.waitForListOpens(openListButton, desiredValue);
        String expected = desiredValue.waitUntil(clickable, timeout, polling).getText();
        this.clickInElementJS(desiredValue);
        TimeUnit.SECONDS.sleep(3);

        actualValue.waitUntil(exist, timeout, polling).shouldHave(text(expected));
    }
}
