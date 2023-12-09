package ru.PageObjects.Customer.Plans;

import Helpers.Dictionary;
import Helpers.SoftAssert;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Класс для работы со страницей [Планы закупок]
 * ( Главная / Заказчикам / Планы закупок )
 * ( .kontur.ru/customer/lk/TradePlans ).
 * Created by Vladimir V. Klochkov on 02.09.2016.
 * Updated by Vladimir V. Klochkov on 01.03.2021.
 */
public class TradePlansPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы
    private static final String PAGE_HEADER_XPATH = "//h1[contains(.,'Планы закупок')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить план закупки]
    private static final String ADD_NEW_PURCHASE_PLAN_BUTTON_XPATH =
            "//a[@class='btn btn-default' and contains(.,'Добавить план закупки')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Блок критериев поиска

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование / номер / номер в ЕИС:]
    private static final String SEARCH_TRADE_PLAN_CRITERIA_ID = "Keyword";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Найти]
    private static final String FIND_BUTTON_XPATH = "//input[@class='btn btn1' and @value='Найти']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Результаты поиска]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок столбца [Номер] в таблице результатов поиска
    private static final String TRADE_PLANS_NUMBER_COLUMN_HEADER_XPATH = "//span[@class='k-link'and text() = 'Номер']";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка с номером плана в таблице результатов поиска
    private static final String TRADE_PLANS_OPEN_TRADE_PLAN_BY_NUMBER_LINK_XPATH = "//a[contains(.,'%s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылки с номерами планов в таблице результатов поиска
    private static final String TRADE_PLANS_NUMBERS_XPATH = "//*[@id='plansGrid']/table/tbody/tr/td[1]/a";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылки с номерами планов в ЕИС в таблице результатов поиска
    private static final String TRADE_PLANS_NUMBERS_IN_EIS_XPATH = "//*[@id='plansGrid']/table/tbody/tr/td[2]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Подвал страницы

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Сервер]
    private static final String SERVER_VERSION_XPATH = "//li[contains(.,'Сервер: ') or contains(.,'SERVER: ')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary blockNames = new Dictionary();  // все разделы на странице
    private final Dictionary fieldNames = new Dictionary();  // все поля на странице
    private final Dictionary buttonNames = new Dictionary(); // все кнопки на странице

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public TradePlansPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Заголовок страницы", PAGE_HEADER_XPATH);
        blockNames.add("Результаты поиска", TRADE_PLANS_NUMBER_COLUMN_HEADER_XPATH);
        blockNames.add("Подвал страницы", SERVER_VERSION_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Наименование / номер / номер в ЕИС", SEARCH_TRADE_PLAN_CRITERIA_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Добавить план закупки", ADD_NEW_PURCHASE_PLAN_BUTTON_XPATH);
        buttonNames.add("Найти", FIND_BUTTON_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Блок критериев поиска

    /**
     * Ищет план коммерческих закупок по его наименованию / номеру / номеру в ЕИС без проверки результатов поиска.
     *
     * @param searchCriteria наименование / номер / номер в ЕИС
     */
    private void findExistingPlanWithoutCheckSearchResults(String searchCriteria) throws Exception {
        By searchField = this.getBy(fieldNames.find("Наименование / номер / номер в ЕИС"));
        $(searchField).waitUntil(clickable, timeout, polling);
        this.waitClearClickAndSendKeys(searchField, searchCriteria);
        this.waitLoadingImage();
        this.clickOnButton("Найти");
    }

    /**
     * Проверяет работу фильтров для этой страницы.
     */
    public void checkPageFilters() throws Exception {

        // по номеру плана закупок на площадке
        System.out.printf("[ИНФОРМАЦИЯ]: поиск по номеру плана закупок на площадке [%s].%n",
                config.getParameter("TradePlanNumber"));
        this.findExistingPlanWithoutCheckSearchResults(config.getParameter("TradePlanNumber"));
        System.out.printf("По данному критерию поиска найдено [%d] записей.%n", this.getPlansCountInSearchResults());
        Assert.assertEquals("[ОШИБКА]: в результатах поиска должна быть только одна запись",
                1, this.getPlansCountInSearchResults());
        this.resetAllFilters();

        // по не существующему номеру плана закупок на площадке
        System.out.println("[ИНФОРМАЦИЯ]: поиск по не существующему номеру плана закупок на площадке [000000].");
        this.findExistingPlanWithoutCheckSearchResults("000000");
        System.out.printf("По данному критерию поиска найдено [%d] записей.%n", this.getPlansCountInSearchResults());
        Assert.assertEquals("[ОШИБКА]: в результатах поиска не должно быть записей",
                0, this.getPlansCountInSearchResults());
        this.resetAllFilters();

        // по номеру в ЕИС
        System.out.println("[ИНФОРМАЦИЯ]: поиск по номеру плана закупок в ЕИС.");
        this.setFilterByCriteria(TRADE_PLANS_NUMBERS_IN_EIS_XPATH, "Номер в ЕИС");
        System.out.printf("По данному критерию поиска найдено [%d] записей.%n", this.getPlansCountInSearchResults());
        this.resetAllFilters();

        // по наименованию плана закупок
        System.out.printf("[ИНФОРМАЦИЯ]: поиск по наименованию плана закупок на площадке [%s].%n",
                config.getParameter("TradePlanName"));
        this.findExistingPlanWithoutCheckSearchResults(config.getParameter("TradePlanName"));
        System.out.printf("По данному критерию поиска найдено [%d] записей.%n", this.getPlansCountInSearchResults());
        Assert.assertEquals("[ОШИБКА]: в результатах поиска должна быть только одна запись",
                1, this.getPlansCountInSearchResults());
        this.resetAllFilters();
    }

    /**
     * Устанавливает фильтр по заданному критерию и проверяет результат ( количество записей ).
     *
     * @param locator     локатор столбца, по значению из которого устанавливается фильтр
     * @param description описание применяемого фильтра ( наименование столбца )
     */
    private void setFilterByCriteria(String locator, String description) throws Exception {

        // Выводим отладочную информацию
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем фильтр по критерию [%s].%n", description);

        // Получаем коллекцию записей без применения фильтра ( все записи ) и проверяем её размер
        ElementsCollection rows = $$(this.getBy(locator)).filter(not(empty));
        SoftAssert.assertNotEquals("[ОШИБКА]: в реестре планов отсутствуют записи", 0, rows.size());

        // Поскольку SoftAssert и тест будет выполняться дальше...
        if (rows.size() > 0) {

            // Значение в первой строке и будет критерием поиска
            String searchCriteria = rows.get(0).getText();
            this.findExistingPlanWithoutCheckSearchResults(searchCriteria);

            // Для критерия поиска [Опубликован на ЭП] не должно быть найденных записей
            if (description.equals("Номер в ЕИС") && searchCriteria.equals("Опубликован на ЭП"))
                Assert.assertEquals(
                        "[ОШИБКА]: в результатах поиска для критерия [Опубликован на ЭП] не должно быть записей",
                        0, this.getPlansCountInSearchResults());

                // Во всех остальных случаях проверяем наличие критерия поиска (подстроки) во всех строках
            else this.checkTheSameValuesInSearchResults(locator, searchCriteria);
        }
    }

    /**
     * Сбрасывает фильтр в исходное состояние.
     */
    private void resetAllFilters() throws Exception {
        open(url());
        this.waitForPageLoad();
    }

    // endregion

    // region Раздел [Результаты поиска]

    /**
     * Возвращает количество найденных планов закупок на текущей странице.
     *
     * @return количество найденных планов закупок на текущей странице
     */
    private int getPlansCountInSearchResults() {
        return $$(this.getBy(TRADE_PLANS_NUMBERS_XPATH)).size();
    }

    /**
     * Ищет план закупок по его номеру и проверяет, что в результатах поиска есть ссылка на этот план.
     *
     * @param number номер плана закупок
     */
    public void searchPlanByNumberAndCheckLinkForItInSearchResults(String number) throws Exception {
        this.findExistingPlanWithoutCheckSearchResults(number);
        String openTradePlanLink = String.format(TRADE_PLANS_OPEN_TRADE_PLAN_BY_NUMBER_LINK_XPATH, number);
        $(this.getBy(openTradePlanLink)).waitUntil(clickable, timeout, polling);
    }

    /**
     * Открывает план коммерческих закупок по его номеру.
     *
     * @param number номер плана закупок
     */
    public void openExistingPlanByNumber(String number) throws Exception {
        String openTradePlanLink = String.format(TRADE_PLANS_OPEN_TRADE_PLAN_BY_NUMBER_LINK_XPATH, number);

        System.out.printf("[ИНФОРМАЦИЯ]: переходим по ссылке с номером плана [%s] в результатах поиска.%n", number);
        $(this.getBy(openTradePlanLink)).waitUntil(clickable, timeout, polling);
        this.clickInElementJS(this.getBy(openTradePlanLink));
        this.waitLoadingImage();
    }

    // endregion

    // region Общие методы для работы с элементами этой страницы

    /**
     * Делает щелчок мышью по указанной кнопке.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.clickInElementJS(this.getBy(buttonNames.find(buttonName)));
        this.logPressedButtonName(buttonName);
        this.waitLoadingImage(3);
    }

    // endregion
}
