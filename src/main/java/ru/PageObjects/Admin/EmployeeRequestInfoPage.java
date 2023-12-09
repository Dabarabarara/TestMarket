package ru.PageObjects.Admin;


import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы со страницей [Заявка на добавление пользователя]
 * ( Администрирование / Заявки на добавление  изменение информации пользователейв - Сотрудник )
 * ( .kontur.ru/supplier/lk/Accreditation/EmployeeRequestInfo.aspx? ).
 * Created by Kirill G. Rydzyvylo on 27.04.2020.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */

public class EmployeeRequestInfoPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //region Раздел [Регистрация пользователя]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус]
    private static final String STATUS_ID = "BaseMainContent_MainContent_lblRequestStatus";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Раздел [Регистрация пользователя]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Фамилия]
    private static final String LAST_NAME_ID = "BaseMainContent_MainContent_lblLastName";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Имя]
    private static final String FIRST_NAME_ID = "BaseMainContent_MainContent_lblFirstName";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Основной e-mail для уведомлений заказчика]
    private static final String CUSTOMER_EMAIL_ID = "BaseMainContent_MainContent_lblCustomerEmail";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Основной e-mail для уведомлений поставщика]
    private static final String SUPPLIER_EMAIL_ID = "BaseMainContent_MainContent_lblSupplierEmail";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Имя пользователя (логин)]
    private static final String LOGIN_ID = "BaseMainContent_MainContent_lblLogin";
    //------------------------------------------------------------------------------------------------------------------
    // Строки таблицы [Документы пользователя]
    private static final String USER_DOCUMENTS_ROWS_XPATH = "//table//td/a";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    //region Управляющие кнопки в нижней части страницы
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Рассмотреть]
    private static final String CONSIDERATION_BUTTON_ID =
            "BaseMainContent_MainContent_lbtOperatorConsiderationCommon";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Назад]
    private static final String BACK_BUTTON_ID =
            "BaseMainContent_MainContent_lbtBack";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Допустить]
    private static final String ACCEPT_BUTTON_ID =
            "BaseMainContent_MainContent_lbtOperatorCommonAccept";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отклонить]
    private static final String CANCEL_BUTTON_ID =
            "BaseMainContent_MainContent_lbtOperatorCommonCancel";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary fieldNames = new Dictionary(); // все поля на странице
    private final Dictionary buttonNames = new Dictionary();// все кнопки на странице

    /*******************************************************************************************************************
     * Конструктор класса.
     * @param driver экземпляр WebDriver
     *****************************************************************************************************************
     */
    public EmployeeRequestInfoPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Статус", STATUS_ID);
        fieldNames.add("Фамилия", LAST_NAME_ID);
        fieldNames.add("Имя", FIRST_NAME_ID);
        fieldNames.add("Основной e-mail для уведомлений заказчика", CUSTOMER_EMAIL_ID);
        fieldNames.add("Основной e-mail для уведомлений поставщика", SUPPLIER_EMAIL_ID);
        fieldNames.add("Имя пользователя (логин)", LOGIN_ID);
        fieldNames.add("Документы пользователя", USER_DOCUMENTS_ROWS_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Рассмотреть",CONSIDERATION_BUTTON_ID);
        buttonNames.add("Назад",BACK_BUTTON_ID);
        buttonNames.add("Допустить",ACCEPT_BUTTON_ID);
        buttonNames.add("Отклонить",CANCEL_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------

    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/


    /**
     * Проверяет содержимое тектового поля.
     */
    public void checkField(String value, String fieldName) {

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        verifyFieldContent(this.getBy(fieldNames.find(fieldName)), value);
    }

    /**
     * Делает щелчок мышью по указанной кнопке.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие кнопки [%s].%n", buttonName);
        this.scrollToCenterAndclickInElementJS(this.getBy(buttonNames.find(buttonName)));
        this.waitForPageLoad();
    }

    /**
     * Проверяет количество строк в таблице [Документы пользователя].
     *
     * @param rows требуемое количество строк в таблице [Документы пользователя]
     */
    public void checkNumberOfRowsFromUserDocumentsTable(int rows) throws Exception {
        TimeUnit.SECONDS.sleep(5);
        int rowsActual = $$(this.getBy(USER_DOCUMENTS_ROWS_XPATH)).size();
        Assert.assertEquals(
                "[ОШИБКА]: неверное количество строк в таблице [Документы пользователя]", rows, rowsActual);
        System.out.printf(
                "[ИНФОРМАЦИЯ]: произведена проверка количества строк [%s] в таблице [Документы пользователя].%n", rows);
    }

}

