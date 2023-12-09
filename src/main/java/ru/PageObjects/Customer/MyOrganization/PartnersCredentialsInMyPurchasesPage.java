package ru.PageObjects.Customer.MyOrganization;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы со страницей [Полномочия партнеров в моих закупках]
 * ( Моя организация / Полномочия партнеров в моих закупках )
 * ( .kontur.ru/supplier/lk/PartnerRelation/PartnerRelationList.aspx ).
 * Created by Kirill G. Rydzyvylo on 20.11.2019.
 * Updated by Vladimir V. Klochkov on 19.08.2020.
 */
public class PartnersCredentialsInMyPurchasesPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы [Полномочия партнеров в моих закупках]
    private static final String PAGE_HEADER_XPATH = "//*[@id='workContainer']/h2";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Критерии поиска]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование связи]
    private static final String LINK_NAME_ID = "BaseMainContent_MainContent_txtName_txtText";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Партнер]
    private static final String PARTNER_NAME_ID = "BaseMainContent_MainContent_txtOrganization_txtText";
    //------------------------------------------------------------------------------------------------------------------
    // Переключатель [Поиск по -> Наименованию]
    private static final String SEARCH_BY_ORGANIZATION_NAME_ID = "BaseMainContent_MainContent_rbByOrganizationName";
    //------------------------------------------------------------------------------------------------------------------
    // Переключатель [Поиск по -> ИНН]
    private static final String SEARCH_BY_ORGANIZATION_INN_ID = "BaseMainContent_MainContent_rbByOrganizationInn";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Поиск]
    private static final String SEARCH_BUTTON_ID = "BaseMainContent_MainContent_btnSearch";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Очистить]
    private static final String CLEAR_BUTTON_ID = "BaseMainContent_MainContent_btnClear";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Вкладки состояния полномочий]

    //------------------------------------------------------------------------------------------------------------------
    // Вкладка [Активна]
    private static final String IS_ACTIVE_TAB_XPATH = "//*[@id='gridTabsId']//a[contains(., 'Активна')]";
    //------------------------------------------------------------------------------------------------------------------
    // Вкладка [Подтверждение]
    private static final String CONFIRMATION_TAB_XPATH = "//*[@id='gridTabsId']//a[contains(., 'Подтверждение')]";
    //------------------------------------------------------------------------------------------------------------------
    // Вкладка [Не активна]
    private static final String NOT_ACTIVE_TAB_XPATH = "//*[@id='gridTabsId']//a[contains(., 'Не активна')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Таблица полномочий ( результаты поиска )

    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Наименование связи] ( все значения )
    private static final String COMMUNICATION_NAME_COLUMN_XPATH =
            "//table[@id='BaseMainContent_MainContent_jqgOrganization']//tr[contains(@class, 'jqgrow')]/td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Наименование связи] ( ссылка на карточку )
    private static final String COMMUNICATION_NAME_LINK_TO_CARD_XPATH =
            "//table[@id='BaseMainContent_MainContent_jqgOrganization']//a[contains(., '%s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Наименование] ( все значения )
    private static final String NAME_COLUMN_XPATH =
            "//table[@id='BaseMainContent_MainContent_jqgOrganization']//tr[contains(@class, 'jqgrow')]/td[3]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [ИНН] ( все значения )
    private static final String INN_COLUMN_XPATH =
            "//table[@id='BaseMainContent_MainContent_jqgOrganization']//tr[contains(@class, 'jqgrow')]/td[4]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Блок управляющих кнопок в нижней части страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Делегировать полномочия]
    private static final String CREATE_BUTTON_ID = "BaseMainContent_MainContent_btnCreate";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary blockNames = new Dictionary();       // все имена блоков
    private final Dictionary fieldNames = new Dictionary();       // все поля на странице
    private final Dictionary radioButtonNames = new Dictionary(); // все переключатели на странице
    private final Dictionary buttonNames = new Dictionary();      // все кнопки на странице
    private final Dictionary gridTabNames = new Dictionary();     // все вкладки на странице
    private final Dictionary tableColumns = new Dictionary();     // колонки таблицы

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public PartnersCredentialsInMyPurchasesPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Заголовок страницы", PAGE_HEADER_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Наименование связи", LINK_NAME_ID);
        fieldNames.add("Партнер", PARTNER_NAME_ID);
        //--------------------------------------------------------------------------------------------------------------
        radioButtonNames.add("Поиск по Наименованию", SEARCH_BY_ORGANIZATION_NAME_ID);
        radioButtonNames.add("Поиск по ИНН", SEARCH_BY_ORGANIZATION_INN_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Поиск", SEARCH_BUTTON_ID);
        buttonNames.add("Очистить", CLEAR_BUTTON_ID);
        buttonNames.add("Делегировать полномочия", CREATE_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
        gridTabNames.add("Активна", IS_ACTIVE_TAB_XPATH);
        gridTabNames.add("Подтверждение", CONFIRMATION_TAB_XPATH);
        gridTabNames.add("Не активна", NOT_ACTIVE_TAB_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        tableColumns.add("Наименование связи", COMMUNICATION_NAME_COLUMN_XPATH);
        tableColumns.add("Наименование связи - ссылка на карточку", COMMUNICATION_NAME_LINK_TO_CARD_XPATH);
        tableColumns.add("Наименование", NAME_COLUMN_XPATH);
        tableColumns.add("Столбец ИНН", INN_COLUMN_XPATH);
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
}
