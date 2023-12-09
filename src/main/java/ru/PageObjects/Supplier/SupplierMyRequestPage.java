package ru.PageObjects.Supplier;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывающий страницу: [Мои заявки] (supplier/auction/Private/Participant.aspx)
 * Created by Evgeniy Glushko on 30.08.2016.
 * Updated by Vladimir V. Klochkov on 11.03.2021.
 */
public class SupplierMyRequestPage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public SupplierMyRequestPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Локаторы и элементы страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Критерии поиска:
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер заявки]
    private static final String APPLICATION_NUMBER_ID = "BaseMainContent_MainContent_txtApplicationNumber_txtText";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки/лота]
    private static final String TRADE_NUMBER_ID = "BaseMainContent_MainContent_txtTradeNumber_txtText";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование закупки]
    private static final String TRADE_NAME_ID = "BaseMainContent_MainContent_txtTradeName_txtText";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица с результатами поиска:
    //------------------------------------------------------------------------------------------------------------------
    // Выбор количества строк таблицы
    private static final String SELECT_NUMBER_ROWS_XPATH =
            "//*[@id='BaseMainContent_MainContent_jqgApplication_toppager_center']//select";
    //------------------------------------------------------------------------------------------------------------------
    // Cсылки в столбце [Номер заявки] ( для всех строк таблицы )
    private static final String APPLICATION_NUMBER_LINKS_XPATH =
            "//td[@aria-describedby='BaseMainContent_MainContent_jqgApplication_ApplicationNumber']/a";
    //------------------------------------------------------------------------------------------------------------------
    // Cсылка в столбце [Номер заявки] ( параметр [Номер закупки/Номер лота] )
    private static final String APPLICATION_NUMBER_USING_PURCHASE_NUMBER_AND_LOT_NUMBER =
            "//td[@aria-describedby='BaseMainContent_MainContent_jqgApplication_TradeNumber']/a[contains(., '%s')]/.." +
                    "/../td[@aria-describedby='BaseMainContent_MainContent_jqgApplication_ApplicationNumber']/a";
    //------------------------------------------------------------------------------------------------------------------
    // Cсылки в столбце [Номер закупки/лота] ( для всех строк таблицы )
    private static final String NUMBER_PURCHASE_LOT_TABLE_ALL_LINK
            = "//td[@aria-describedby='BaseMainContent_MainContent_jqgApplication_TradeNumber']/a";
    //-----------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Выбор количества отображаемых строк в таблице.
     */
    public void selectNumberRowInTable() throws Exception {
        $(By.xpath(SELECT_NUMBER_ROWS_XPATH)).waitUntil(visible, timeout, polling).selectOption("100");
        this.waitLoadingRectangle();
        this.waitForAjax();
    }

    /**
     * Проверка наличия строк в результатах поиска.
     *
     * @param rowCount ожидаемое количество строк в результатах поиска
     */
    public void checkRowCountInSearchResults(String rowCount) throws Exception {
        Assert.assertTrue(String.format(
                "[ОШИБКА]: в метод (checkRowCountInSearchResults) передан некорректный параметр (rowCount): '%s'",
                rowCount), rowCount.equals("all") || rowCount.equals("three") || rowCount.equals("one"));
        this.waitLoadingImage();
        this.waitLoadingRectangle();
        $(By.xpath(APPLICATION_NUMBER_LINKS_XPATH)).waitUntil(visible, timeout, polling);
        int count = $$(By.xpath(APPLICATION_NUMBER_LINKS_XPATH)).size();
        System.out.printf("[ИНФОРМАЦИЯ]: количество строк в таблице: '%d'.%n", count);

        if (rowCount.equals("all"))
            Assert.assertNotEquals("[ОШИБКА]: нулевое количество строк в таблице", 0, count);
        else if (rowCount.equals("three"))
            Assert.assertEquals("[ОШИБКА]: количество строк в таблице не равно 3", 3, count);
        else
            Assert.assertEquals("[ОШИБКА]: количество строк в таблице не равно 1", 1, count);
    }

    /**
     * Получение значения из столбца таблицы с указанным именем.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  номер строки в таблице
     * @return значение из столбца
     */
    public String getValueFromColumn(String columnName, int rowNumber) {
        Dictionary columnNames = new Dictionary();
        columnNames.add("Номер заявки", APPLICATION_NUMBER_LINKS_XPATH);
        columnNames.add("Номер закупки/лота", NUMBER_PURCHASE_LOT_TABLE_ALL_LINK);

        String value = $$(By.xpath(columnNames.find(columnName))).get(rowNumber - 1)
                .waitUntil(visible, timeout, polling).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: получено значение: [%s], из строки: [%d], из столбца: [%s].%n",
                value, rowNumber, columnName);
        return value;
    }

    /**
     * Возвращает значение ячейки в столбце [Номер заявки] для закупки с указанным номером используя номер лота.
     *
     * @param lotNumber номер лота
     * @return значение ячейки в столбце [Номер заявки]
     */
    public String getRequestNumberUsingLotNumber(String lotNumber) {
        String purchaseNumberWithLotNumber = config.getParameter("PurchaseNumber") + "/" + lotNumber;
        String locator =
                String.format(APPLICATION_NUMBER_USING_PURCHASE_NUMBER_AND_LOT_NUMBER, purchaseNumberWithLotNumber);
        String value = $(By.xpath(locator)).waitUntil(visible, timeout, polling).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: получено значение: [%s], для лота: [%s], из столбца: [Номер заявки].%n",
                value, lotNumber);
        return value;
    }

    /**
     * Установка значения в поле [Номер заявки].
     *
     * @param applicationNumber номер заявки
     */
    public void setApplicationNumber(String applicationNumber) {
        SelenideElement numberRequestField = $(By.id(APPLICATION_NUMBER_ID));
        numberRequestField.waitUntil(visible, timeout, polling).sendKeys(applicationNumber);
        numberRequestField.click();
        Assert.assertEquals("[ОШИБКА]: неверное значение в поле: [Номер заявки]",
                applicationNumber, numberRequestField.getValue());
        this.logFilledFieldName("Номер заявки", applicationNumber);
    }

    /**
     * Установка значения в поле [Номер закупки/лота].
     *
     * @param purchaseNumberLotNumber номер закупки/лота
     */
    public void setTradeNumberLotNumber(String purchaseNumberLotNumber) {
        SelenideElement field = $(By.id(TRADE_NUMBER_ID));
        field.waitUntil(clickable, timeout, polling).click();
        field.clear();
        field.sendKeys(purchaseNumberLotNumber);
        field.click();
        Assert.assertEquals("[ОШИБКА]: неверное значение в поле: [Номер закупки/лота]",
                purchaseNumberLotNumber, field.getValue());
        this.logFilledFieldName("Номер закупки/лота", purchaseNumberLotNumber);
    }

    /**
     * Установка значения в поле [Наименование закупки].
     *
     * @param purchaseName наименование закупки
     */
    public void setTradeName(String purchaseName) {
        SelenideElement field = $(By.id(TRADE_NAME_ID));
        field.waitUntil(clickable, timeout, polling).click();
        field.clear();
        field.sendKeys(purchaseName);
        field.click();
        Assert.assertEquals("[ОШИБКА]: неверное значение в поле: [Наименование закупки]",
                purchaseName, field.getValue());
        this.logFilledFieldName("Наименование закупки", purchaseName);
    }

    /**
     * Переходит по ссылке с указанным порядковым номером в столбце 'Номер заявки'.
     *
     * @param linkNumber порядковый номер ссылки
     */
    public void goToLinkInRequestNumberColumnByNumber(String linkNumber) throws Exception {
        ElementsCollection requestNumbers = $$(By.xpath(APPLICATION_NUMBER_LINKS_XPATH));
        int count = requestNumbers.size();
        System.out.printf("[ИНФОРМАЦИЯ]: количество строк в таблице: '%d'.%n", count);
        int link = Integer.parseInt(linkNumber) - 1;
        Assert.assertTrue("[ОШИБКА]: переданный номер строки вне диапазона", link >= 0 && link < count);
        System.out.printf("[ИНФОРМАЦИЯ]: переходим по ссылке с текстом: '%s'.%n",
                requestNumbers.get(link).getText());
        requestNumbers.get(link).click();

        // Открылось новое окно просмотра заявки
        config.switchToNewWindowInBrowser();
        this.waitLoadingImage();
        this.checkPageUrl("Заявка на участие в режиме просмотра",
                "Заявка на участие в режиме просмотра старая");
    }
}
