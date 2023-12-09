package ru.PageObjects.Customer.MyOrganization;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы со страницей [Заявка на изменение информации о пользователе/сертификате]
 * (МОЯ ОРГАНИЗАЦИЯ / СПИСОК ПОЛЬЗОВАТЕЛЕЙ / имя пользователя / Редактировать).
 * ( .kontur.ru/supplier/lk/Accreditation/EmployeeRequest.aspx?EmployeeId )
 * Updated by Kirill G. Rydzyvylo on 15.09.2020.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class EmployeeChangeRequestPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы [Заявка на изменение информации о пользователе]

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Пользователь]

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сертификаты]

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Выбрать из списка]
    private static final String SELECT_CERT_ID = "BaseMainContent_MainContent_ucNewSelectCertificate_btnSelectCert";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица [Сертификаты] столбец [Данные сертификата]
    private static final String CERTIFICATE_LIST_XPATH =
            "//table[@id='BaseMainContent_MainContent_jqgCertificates']//tr[contains(@class,'ui-widget-conten')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Причина добавления сертификата]
    private static final String EDIT_REASON_ID = "BaseMainContent_MainContent_txtEditReason";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Блок управлябющих нопок внизу страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отправить]
    private static final String SUBMIT_ID = "BaseMainContent_MainContent_lbtSubmit";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary fieldNames = new Dictionary();  // все поля на странице
    private final Dictionary buttonNames = new Dictionary(); // все кнопки на странице

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public EmployeeChangeRequestPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Причина добавления сертификата", EDIT_REASON_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Выбрать из списка", SELECT_CERT_ID);
        buttonNames.add("Отправить", SUBMIT_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы [Заявка на изменение информации о пользователе]


    // endregion

    // region Раздел [Пользователь]


    // endregion

    // region Раздел [Сертификаты]

    /**
     * Проверяет количество строк в таблице [Сертификаты].
     *
     * @param rows требуемое количество строк в таблице [Сертификаты]
     */
    public void checkNumberOfRowsInCertificatesTable(String rows) throws Exception {
        TimeUnit.SECONDS.sleep(5);
        int expectedRows = Integer.parseInt(rows);
        int actualRows = $$(this.getBy(CERTIFICATE_LIST_XPATH)).size();
        Assert.assertEquals(
                "[ОШИБКА]: неверное количество строк в таблице [Сертификаты]", expectedRows, actualRows);
        System.out.printf(
                "[ИНФОРМАЦИЯ]: произведена проверка количества строк [%s] в таблице [Сертификаты].%n", rows);
    }

    // endregion

    // region Общие методы для работы с элементами этой страницы

    /**
     * Устанавливает значение полей с предварительной их очисткой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     */
    public void setFieldClearClickAndSendKeys(String fieldName, String value) throws Exception {
        this.waitClearClickAndSendKeys(this.getBy(fieldNames.find(fieldName)), value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
    }

    /**
     * Делает щелчок мышью по указанной кнопке.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.clickInElementJS(this.getBy(buttonNames.find(buttonName)));
        this.logPressedButtonName(buttonName);
        TimeUnit.SECONDS.sleep(3);
    }

    // endregion
}
