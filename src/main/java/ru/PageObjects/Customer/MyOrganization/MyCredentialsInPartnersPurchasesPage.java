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
 * Класс для работы со страницей [Мои полномочия в закупках партнеров]
 * ( Моя организация / Мои полномочия в закупках партнеров )
 * ( .kontur.ru/supplier/lk/PartnerRelation/PartnerRelationList.aspx ).
 * Created by Kirill G. Rydzyvylo on 27.11.2019.
 * Updated by Vladimir V. Klochkov on 11.03.2021.
 */
public class MyCredentialsInPartnersPurchasesPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы [Мои полномочия в закупках партнеров]
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
    // Кнопки [Делегировать полномочия / Запросить полномочия]
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
    private final Dictionary tableCells = new Dictionary();       // колонки таблицы

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public MyCredentialsInPartnersPurchasesPage(WebDriver driver) {
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
        buttonNames.add("Запросить полномочия", CREATE_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
        gridTabNames.add("Активна", IS_ACTIVE_TAB_XPATH);
        gridTabNames.add("Подтверждение", CONFIRMATION_TAB_XPATH);
        gridTabNames.add("Не активна", NOT_ACTIVE_TAB_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        tableCells.add("Наименование связи", COMMUNICATION_NAME_COLUMN_XPATH);
        tableCells.add("Наименование связи - ссылка на карточку", COMMUNICATION_NAME_LINK_TO_CARD_XPATH);
        tableCells.add("Наименование", NAME_COLUMN_XPATH);
        tableCells.add("Столбец ИНН", INN_COLUMN_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Устанавливает значение текстового поля.
     *
     * @param fieldName имя текстового поля
     * @param value     требуемое значение поля
     * @return страница [Мои полномочия в закупках партнеров]
     */
    public MyCredentialsInPartnersPurchasesPage setField(String fieldName, String value) throws Exception {
        SelenideElement field = $(this.getBy(fieldNames.find(fieldName)));
        field.waitUntil(clickable, timeout, polling).sendKeys(value);
        TimeUnit.SECONDS.sleep(3);
        Assert.assertEquals(String.format("[ОШИБКА]: не удалось установить значение [%s] в поле [%s]", value, fieldName),
                value, field.getValue());
        this.logFilledFieldName(fieldName, value);

        return this;
    }

    /**
     * Переключает на требуемую закладку.
     *
     * @param tabName имя закладки
     * @return страница [Мои полномочия в закупках партнеров]
     */
    public MyCredentialsInPartnersPurchasesPage switchToTab(String tabName) throws Exception {
        SelenideElement tab = $(this.getBy(gridTabNames.find(tabName)));
        this.scrollToCenter(tab);
        tab.shouldBe(clickable);
        this.clickInElementJS(tab);
        TimeUnit.SECONDS.sleep(1);
        System.out.printf("[ИНФОРМАЦИЯ]: Произведено нажатие на вкладку: [%s].%n", tabName);

        return this;
    }

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
     * Проверяет количество строк в результатах поиска.
     *
     * @param expectedSize ожидаемое количество строк в результатах поиска
     * @return страница [Мои полномочия в закупках партнеров]
     */
    public MyCredentialsInPartnersPurchasesPage checkRowCountInSearchResults(int expectedSize) {
        ElementsCollection rows = $$(this.getBy(tableCells.find("Наименование связи")));
        System.out.printf("[ИНФОРМАЦИЯ]: Количество строк в результатах поиска: [%d].%n", rows.size());
        Assert.assertEquals("[ОШИБКА]: не верное количество строк в результатах поиска",
                expectedSize, rows.size());

        return this;
    }

    /**
     * Проверяет наличиe ссылки на карточку партнерской связи.
     *
     * @param partnershipName имя партнерской связи (без ID)
     */
    public void checkLinkToPartnershipCardInSearchResults(String partnershipName) {
        String pathToPartnershipCard = String.format(
                tableCells.find("Наименование связи - ссылка на карточку"), partnershipName);
        SelenideElement link = $(this.getBy(pathToPartnershipCard));
        link.waitUntil(visible, timeout, polling).shouldBe(clickable);
        System.out.printf("[ИНФОРМАЦИЯ]: произведена проверка наличия ссылки на партнерскую связь [%s].%n",
                link.getText());
    }

    /**
     * Производит переход по ссылке на карточку партнерской связи.
     *
     * @param partnershipName имя партнерской связи (без ID)
     */
    public void clickOnLinkToPartnershipCard(String partnershipName) throws Exception {
        String pathToPartnershipCard = String.format(
                tableCells.find("Наименование связи - ссылка на карточку"), partnershipName);
        SelenideElement link = $(this.getBy(pathToPartnershipCard));
        link.waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        System.out.printf("[ИНФОРМАЦИЯ]: произведен переход по ссылке на партнерскую связь [%s].%n", link.getText());
    }
}
