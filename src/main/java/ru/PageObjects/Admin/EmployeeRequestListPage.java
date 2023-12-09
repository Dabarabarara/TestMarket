package ru.PageObjects.Admin;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы со страницей [Запросы сотрудников]
 * ( Администрирование / Заявки на добавление - изменение информации пользователейв )
 * ( .kontur.ru/supplier/lk/PartnerRelation/PartnerRelationRequest.aspx? ).
 * Created by Kirill G. Rydzyvylo on 24.04.2020.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */

public class EmployeeRequestListPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //region Раздел [Запросы сотрудников]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Состояние - X]
    private static final String STATE_X_BUTTONS_XPATH =
            "//*[@id='BaseMainContent_MainContent_lbxState_chzn']//a[@class='search-choice-close']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименовение организации]
    private static final String ORGANIZATION_NAME_ID = "BaseMainContent_MainContent_txtOrganizationName";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    //region  Блок управляющих кнопок

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименовение организации]
    private static final String SEARCH_BUTTON_ID = "BaseMainContent_MainContent_btnSearch";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    //region Таблица [Список запросов]

    //------------------------------------------------------------------------------------------------------------------
    // Таблица [Список запросов] - все строки
    private static final String REQUEST_LIST_TABLE_ALL_ROWS_XPATH =
            "//table[@id='BaseMainContent_MainContent_jqRequestList']//tr[@id]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица [Список запросов] - выбор сотрудника
    private static final String REQUEST_LIST_TABLE_USER_XPATH =
            "//table[@id='BaseMainContent_MainContent_jqRequestList']//td/a[contains(.,'%s')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary fieldNames = new Dictionary();  // все поля на странице
    private final Dictionary buttonNames = new Dictionary(); // все кнопки на странице

    /*******************************************************************************************************************
     * Конструктор класса.
     * @param driver экземпляр WebDriver
     *****************************************************************************************************************
     */
    public EmployeeRequestListPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Наименование организации", ORGANIZATION_NAME_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Состояние - Х", STATE_X_BUTTONS_XPATH);
        buttonNames.add("Поиск", SEARCH_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/


    /**
     * Устанавливает значение поля со списком, допускающего прямой ввод значения и множественный выбор.
     *
     * @param deleteButtonsName имя набора кнопок [X], которые позволяют удалить все ранее выбранные значения в поле
     */
    public void cleansMultiSelectField(String deleteButtonsName) throws Exception {
        // Очищаем поле от выбранных значений ( нажимаем на крестик для удаления каждого значения )
        ElementsCollection deleteButtons = $$(this.getBy(buttonNames.find(deleteButtonsName)));

        for (SelenideElement deleteButton : deleteButtons) {
            deleteButton.waitUntil(visible, timeout, polling).click();
            TimeUnit.SECONDS.sleep(1);
        }
    }

    /**
     * Нажимает на указанную кнопку
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButtonByName(String buttonName) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие кнопки [%s] на странице [Запросы сотрудников].%n",
                buttonName);
        $(this.getBy(buttonNames.find(buttonName))).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
    }

    /**
     * Устанавливает значение полей с предварительной их очисткой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     */
    public void setFieldClearClickAndSendKeys(String fieldName, String value) throws Exception {
        this.waitClearClickAndSendKeysById(fieldNames.find(fieldName), value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
    }

    /**
     * Проверяет количество строк в таблице [Список запросов сотрудников].
     *
     * @param rows требуемое количество строк в таблице [Список запросов сотрудников]
     */
    public void checkNumberOfRowsFromRequestListTable(int rows) throws Exception {
        TimeUnit.SECONDS.sleep(5);
        int rowsActual = $$(this.getBy(REQUEST_LIST_TABLE_ALL_ROWS_XPATH)).size();
        Assert.assertEquals(
                "[ОШИБКА]: неверное количество строк в таблице [Список запросов сотрудников]", rows, rowsActual);
        System.out.printf(
                "[ИНФОРМАЦИЯ]: произведена проверка количества строк [%s] в таблице [Список запросов сотрудников].%n",
                rows);
    }

    /**
     * Ожидает появления сотдрудника в таблице [Список запросов сотрудников] и переходит к заявке на
     * добавление пользователя.
     *
     * @param employeeName имя сотрудника
     */
    public void selectEmployee(String employeeName) {
        String xpath = String.format(REQUEST_LIST_TABLE_USER_XPATH, employeeName);
        System.out.printf(
                "[ИНФОРМАЦИЯ]: ожидаем появления ссылки с текстом [%s] в результатах поиска извещения о закупке.%n",
                employeeName);
        $(By.xpath(xpath)).waitUntil(clickable, longtime, polling).click();
    }
}
