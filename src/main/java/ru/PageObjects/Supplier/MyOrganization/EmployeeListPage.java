package ru.PageObjects.Supplier.MyOrganization;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Supplier.CommonSupplierPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы со страницей [Список пользователей] ( Моя организация / Список пользователей )
 * ( .ru/supplier/lk/Dictionaries/EmployeeList.aspx ).
 * Created by Kirill G. Rydzyvylo on 06.02.2020.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class EmployeeListPage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Раздел [Критерии поиска]

    // endregion

    // region Таблица [Пользователи]

    //------------------------------------------------------------------------------------------------------------------
    // Все строки в таблице [Пользователи]
    private static final String ROWS_IN_EMPLOYEES_TABLE_XPATH =
            "//table[@id='BaseMainContent_MainContent_jqgEmployees']//tr[contains(@class,'jqgrow')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [ФИО]
    private static final String FIO_XPATH = "//table[@id='BaseMainContent_MainContent_jqgEmployees']//td[@title='%s']/a";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Настроить права доступа] в таблице [Пользователи]
    private static final String PERMISSIONS_COLUMN_TABLE_XPATH =
            "//table[@id='BaseMainContent_MainContent_jqgEmployees']//a[contains(.,'%s')]" +
                    "/../..//a[contains(.,'Настроить права доступа')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Роль] в таблице [Пользователи]
    private static final String ROLE_COLUMN_TABLE_XPATH =
            "//table[@id='BaseMainContent_MainContent_jqgEmployees']//a[contains(.,'%s')]" +
                    "/../../td[4]/span";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Блок управляющих кнопок в нижней части страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить пользователя]
    private static final String CREATE_EMPLOYEE_BUTTON_ID = "BaseMainContent_MainContent_hlCreateEmployeeRequest";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary buttonNames = new Dictionary(); // все кнопки на странице

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public EmployeeListPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Добавить пользователя", CREATE_EMPLOYEE_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Производит нажатие на кнопку.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.clickInElementJS(this.getBy(buttonNames.find(buttonName)));
        this.logPressedButtonName(buttonName);
        this.waitForPageLoad();
    }

    /**
     * Проверяет количество записей в таблице [Список пользователей].
     *
     * @param numberOfRows ожидаемое количество записей в таблице [Список пользователей]
     */
    public void checkNumberOfRowsInEmployeeTable(int numberOfRows) {
        int rows = $$(this.getBy(ROWS_IN_EMPLOYEES_TABLE_XPATH)).size();
        Assert.assertEquals(
                "[ОШИБКА]: количество записей в таблице [Список пользователей] неверно", numberOfRows, rows);
    }

    /**
     * Нажимает на значение в колонке [ФИО] в таблицe [Пользователи].
     *
     * @param employeeName имя сотрудника
     */
    public void selectFIO(String employeeName) {
        String xpath = String.format(FIO_XPATH, employeeName);
        System.out.printf("[ИНФОРМАЦИЯ]: выбираем пользователя [%s] " +
                "в таблице 'Пользователи' c помощью локатора [%s].%n", employeeName, xpath);
        $(this.getBy(xpath)).waitUntil(clickable, longtime, polling).click();
    }

    /**
     * Ожидает появления сотрудника в таблице [Пользователи] и выбирает колонку [Настроить права доступа].
     *
     * @param employeeName имя сотрудника
     */
    public void selectSetPermissions(String employeeName) {
        String xpath = String.format(PERMISSIONS_COLUMN_TABLE_XPATH, employeeName);
        System.out.printf("[ИНФОРМАЦИЯ]: выбираем пользователя [%s] " +
                "в таблице 'Пользователи' и выбирает колонку 'Настроить права доступа'.%n", employeeName);
        $(this.getBy(xpath)).waitUntil(clickable, longtime, polling).click();
    }

    /**
     * Проверяет содержимое колонки [Роли] в таблице [Пользователи].
     *
     * @param userName имя пользователя
     * @param value    ожидаемое значение в колонке [Роль]
     */
    public void checkValueInColumnRole(String userName, String value) {
        String xpath = String.format(ROLE_COLUMN_TABLE_XPATH, userName);
        System.out.printf("[ИНФОРМАЦИЯ]: проверка пользователя [%s], ожидаемое значение в колонке [Роль]: [%s].%n",
                userName, value);
        verifyFieldContent(this.getBy(xpath), value);
    }
}
