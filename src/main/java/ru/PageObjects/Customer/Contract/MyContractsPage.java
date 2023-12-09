package ru.PageObjects.Customer.Contract;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Класс для работы со страницей [Мои договоры] ( Главная / Заказчикам / Мои договоры ).
 * ( .kontur.ru/customer/lk/Contracts/Table )
 * Created by Vladimir V. Klochkov on 01.09.2016.
 * Updated by Alexander S. Vasyrenko on 18.03.2021.
 */
public class MyContractsPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public MyContractsPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Найдено договоров]
    private static final String CONTRACTS_FOUND_ID = "totalCount";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки/лота]
    private static final String TRADE_NUMBER_ID = "TradeNumber";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер карточки договора]
    private static final String CONTRACT_NUMBER_ID = "ContractNumber";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование закупки]
    private static final String AUCTION_NAME_ID = "AuctionName";
    //------------------------------------------------------------------------------------------------------------------
    // Вид документа [Договор]
    private static final String SHOW_ONLY_CONTRACTS_XPATH = "//label[@for='ShowOnlyDeals'][contains(.,'Договор')]";
    //------------------------------------------------------------------------------------------------------------------
    // Вид документа [Дополнительное соглашение]
    private static final String SHOW_ONLY_ADDENDUMS_XPATH =
            "//label[@for='ShowOnlyAdditionalAgreements'][contains(.,'Дополнительное соглашение')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Найти]
    private static final String FIND_CONTRACTS_BUTTON_XPATH = "//input[@class='btn btn1 searchButton']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец значений [Номер закупки/лота]
    private static final String PURCHASE_CARD_NUMBERS_XPATH = "//*[@id='ContractsGrid']/table/tbody/tr/td[3]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец ссылок [№ карточки договора] в таблице результатов поиска
    private static final String CONTRACT_CARD_NUMBERS_XPATH = "//*[@id='ContractsGrid']/table/tbody/tr/td[4]/a";
    //------------------------------------------------------------------------------------------------------------------
    // Значение первого элемента в столбце [Наименование закупки]
    private static final String FIRST_PURCHASE_NAME_XPATH = "//*[@id='ContractsGrid']/table/tbody/tr[1]/td[7]";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Возвращает значение поля [Найдено договоров].
     *
     * @return значение поля [Найдено договоров]
     */
    public String getContractsFoundCounterValue() {
        String counter = $(By.id(CONTRACTS_FOUND_ID)).waitUntil(visible, timeout, polling).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: общее количество найденных договоров: [%s].%n", counter);
        return counter;
    }

    /**
     * Возвращает список ссылок [№ карточки договора] из таблицы результатов поиска для текущей страницы.
     *
     * @return список ссылок [№ карточки договора] из таблицы результатов поиска
     */
    public List<String> getContractCardNumbersFromCurrentPage() {
        $(By.xpath(CONTRACT_CARD_NUMBERS_XPATH), 0).waitUntil(visible, timeout, polling);
        int size = $$(By.xpath(CONTRACT_CARD_NUMBERS_XPATH)).size();
        List<String> results = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            String cardNumber = $$(By.xpath(CONTRACT_CARD_NUMBERS_XPATH)).get(i).getText();
            results.add(cardNumber);
            System.out.printf("[ИНФОРМАЦИЯ]: найдена карточка договора №: [%s].%n", cardNumber);
        }
        return results;
    }

    /**
     * Выполняет поиск договора по номеру извещения и порядковому номеру лота.
     *
     * @param lotNumber порядковый номер лота
     */
    public void searchContractByNoticeNumber(String lotNumber) throws Exception {
        String noticeNumber = config.getParameter("PurchaseNumber") + "/" + lotNumber;
        System.out.printf("[ИНФОРМАЦИЯ]: номер закупки для поиска: [%s].%n", noticeNumber);
        this.setFilterByField(noticeNumber, By.id(TRADE_NUMBER_ID), By.xpath(FIND_CONTRACTS_BUTTON_XPATH));
        Assert.assertEquals("[ОШИБКА]: в результатах поиска должен быть только один договор",
                "1", this.getContractsFoundCounterValue());
    }

    /**
     * Выполняет поиск нескольких договоров по номеру извещения.
     *
     * @param expectedContractsFoundCounterValue ожидаемое количество договоров в результатах поиска
     */
    public void searchContractByNoticeNumber(Integer expectedContractsFoundCounterValue) throws Exception {
        String noticeNumber = config.getParameter("PurchaseNumber");
        SelenideElement contractFilter = $(By.xpath(SHOW_ONLY_CONTRACTS_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: номер закупки для поиска: [%s], ожидаемое количество договоров [%d].%n",
                noticeNumber, expectedContractsFoundCounterValue);
        contractFilter.click();
        this.setFilterByField(noticeNumber, By.id(TRADE_NUMBER_ID), By.xpath(FIND_CONTRACTS_BUTTON_XPATH));
        Assert.assertEquals("[ОШИБКА]: результаты поиска должны содержать только '%d' договор(ов)",
                expectedContractsFoundCounterValue.toString(), this.getContractsFoundCounterValue());
    }

    /**
     * Выполняет поиск нескольких доп. соглашений по номеру извещения.
     *
     * @param expectedAddendumFoundCounterValue ожидаемое количество доп. соглашений в результатах поиска
     */
    public void searchAddendumByNoticeNumber(Integer expectedAddendumFoundCounterValue) throws Exception {
        String noticeNumber = config.getParameter("PurchaseNumber");
        SelenideElement addendumFilter = $(By.xpath(SHOW_ONLY_ADDENDUMS_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: номер закупки для поиска: [%s], ожидаемое количество дополнительных соглашений [%d].%n",
                noticeNumber, expectedAddendumFoundCounterValue);
        addendumFilter.click();
        this.setFilterByField(noticeNumber, By.id(TRADE_NUMBER_ID), By.xpath(FIND_CONTRACTS_BUTTON_XPATH));
        Assert.assertEquals("[ОШИБКА]: результаты поиска должны содержать только '%d' дополнительных соглашений",
                expectedAddendumFoundCounterValue.toString(), this.getContractsFoundCounterValue());
    }

    /**
     * Открывает страницу 'Сведения о договоре' по ссылке на номер карточки договора.
     *
     * @param linkOrderNo Порядковый номер ссылки на номер карточки договора (0 - N)
     */
    public void openContractInfoPageByClickToContractCardNumber(int linkOrderNo) throws Exception {
        SelenideElement contract = $(By.xpath(CONTRACT_CARD_NUMBERS_XPATH), linkOrderNo);
        String text = contract.waitUntil(visible, timeout, polling).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: переход по ссылке на номер карточки договора [%s].%n", text);
        contract.click();
        this.checkPageUrl("Сведения о договоре");
    }

    /**
     * Проверяет работу фильтров для этой страницы.
     */
    public void checkPageFilters() throws Exception {
        // Запоминаем общее количество всех найденных договоров (фильтр сброшен)
        int unfiltered = Integer.parseInt(this.getContractsFoundCounterValue().replace(" ", ""));

        // Если количество договоров не 0 то получаем номер закупки для поиска из файла конфигурации
        Assert.assertNotEquals("[ОШИБКА]: ни одного договора не найдено", 0, unfiltered);
        String searchCriteria = config.getConfigParameter("OnProdPurchaseNumberForCheckFilters");

        // Ищем по существующему номеру закупки
        System.out.printf("[ИНФОРМАЦИЯ]: номер закупки для поиска: [%s].%n", searchCriteria);
        this.setFilterByField(searchCriteria, By.id(TRADE_NUMBER_ID), By.xpath(FIND_CONTRACTS_BUTTON_XPATH));
        int filtered = Integer.parseInt(this.getContractsFoundCounterValue().replace(" ", ""));
        Assert.assertTrue("[ОШИБКА]: фильтр по номеру закупки не сработал",
                (filtered > 0) && (filtered < unfiltered));
        ElementsCollection purchaseNumbers = $$(By.xpath(PURCHASE_CARD_NUMBERS_XPATH));
        for (SelenideElement purchaseNumber : purchaseNumbers)
            Assert.assertTrue("[ОШИБКА]: фильтр по номеру закупки не сработал",
                    purchaseNumber.waitUntil(visible, timeout, polling).getText().startsWith(searchCriteria));

        // Ищем по не существующему номеру закупки
        System.out.println("[ИНФОРМАЦИЯ]: поиск по не существующему номеру закупки.");
        this.setFilterByField("0", By.id(TRADE_NUMBER_ID), By.xpath(FIND_CONTRACTS_BUTTON_XPATH));
        filtered = Integer.parseInt(this.getContractsFoundCounterValue().replace(" ", ""));
        Assert.assertEquals("[ОШИБКА]: в результатах поиска не должно быть договоров", 0, filtered);

        // Сбрасываем фильтр в исходное состояние (все договоры)
        //--------------------------------------------------------------------------------------------------------------
        open(url());
        this.waitForPageLoad();
        //--------------------------------------------------------------------------------------------------------------

        // Ищем по номеру карточки договора
        searchCriteria = $$(By.xpath(CONTRACT_CARD_NUMBERS_XPATH)).get(0).waitUntil(visible, timeout, polling).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: номер карточки договора для поиска: [%s].%n", searchCriteria);
        this.setFilterByField(searchCriteria, By.id(CONTRACT_NUMBER_ID), By.xpath(FIND_CONTRACTS_BUTTON_XPATH));
        filtered = Integer.parseInt(this.getContractsFoundCounterValue().replace(" ", ""));
        Assert.assertEquals("[ОШИБКА]: в результатах поиска должен быть только один договор", 1, filtered);

        // Сбрасываем фильтр в исходное состояние (все договоры)
        //--------------------------------------------------------------------------------------------------------------
        open(url());
        this.waitForPageLoad();
        //--------------------------------------------------------------------------------------------------------------

        // Ищем по наименованию закупки
        searchCriteria = $(By.xpath(FIRST_PURCHASE_NAME_XPATH)).waitUntil(visible, timeout, polling).getText();
        if (searchCriteria.length() > 17) searchCriteria = searchCriteria.substring(0, 16);
        System.out.printf("[ИНФОРМАЦИЯ]: наименование закупки для для поиска: [%s].%n", searchCriteria);
        this.setFilterByField(searchCriteria, By.id(AUCTION_NAME_ID), By.xpath(FIND_CONTRACTS_BUTTON_XPATH));
        filtered = Integer.parseInt(this.getContractsFoundCounterValue().replace(" ", ""));
        Assert.assertTrue("[ОШИБКА]: фильтр по наименованию закупки не сработал",
                (filtered > 0) && (filtered < unfiltered));

        // Сбрасываем фильтр в исходное состояние (все договоры)
        //--------------------------------------------------------------------------------------------------------------
        open(url());
        this.waitForPageLoad();
        //--------------------------------------------------------------------------------------------------------------
    }
}
