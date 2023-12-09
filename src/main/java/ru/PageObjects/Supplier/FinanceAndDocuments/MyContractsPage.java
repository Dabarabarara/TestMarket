package ru.PageObjects.Supplier.FinanceAndDocuments;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Supplier.CommonSupplierPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы со страницей [Мои договоры] ( .kontur.ru/supplier/dealing/Default.aspx ).
 * Created by Evgeniy Glushko on 04.04.2016.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class MyContractsPage extends CommonSupplierPage {
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
    // [Номер договора]
    private static final String CONTRACT_NUMBER_ID = "BaseMainContent_MainContent_txtContractNumber_txtText";
    //------------------------------------------------------------------------------------------------------------------
    // [Номер закупки/лота]
    private static final String PURCHASE_OR_LOT_NUMBER_ID = "BaseMainContent_MainContent_txtTradeNumber_txtText";
    //------------------------------------------------------------------------------------------------------------------
    // Радио-кнопка [Вид документа] - [Дополнительные соглашения]
    private static final String DOCUMENT_TYPE_ADDENDUM_ID = "BaseMainContent_MainContent_rbxAdditionalAgreements";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Поиск]
    private static final String SEARCH_BUTTON_ID = "BaseMainContent_MainContent_btnSearch";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Очистить]
    private static final String CANCEL_BUTTON_ID = "BaseMainContent_MainContent_btnCancel";
    //------------------------------------------------------------------------------------------------------------------
    // Выбор количества строк таблицы
    private static final String SELECT_NUMBER_ROWS_XPATH =
            "//*[@id='BaseMainContent_MainContent_jqgContract_toppager_center']//select";
    //------------------------------------------------------------------------------------------------------------------
    // Все строки таблицы, выбор колонки происходит по параметру %s
    private static final String ANY_COLUMN_ALL_ROWS_IN_TABLE_XPATH =
            "//td[@aria-describedby='BaseMainContent_MainContent_jqgContract_%s']";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Выбирает количество строк отображения в таблице с результатами поиска.
     */
    public void selectNumberRowsInTable() throws Exception {
        $(By.xpath(SELECT_NUMBER_ROWS_XPATH)).waitUntil(visible, timeout, polling).selectOption("30");
        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * Заполняет поле с указанным именем на этой странице.
     *
     * @param fieldName имя поля для заполнения
     * @param value     требуемое значение поля
     */
    public void setFieldOnMyContractsPage(String fieldName, String value) {
        Dictionary fieldNames = new Dictionary();
        fieldNames.add("Номер закупки/лота", PURCHASE_OR_LOT_NUMBER_ID);
        fieldNames.add("Номер договора", CONTRACT_NUMBER_ID);

        $(By.id(fieldNames.find(fieldName)))
                .waitUntil(visible, timeout, polling).clear();
        $(By.id(fieldNames.find(fieldName))).click();
        $(By.id(fieldNames.find(fieldName))).sendKeys(value);
        $(By.id(fieldNames.find(fieldName))).click();
        Assert.assertEquals(String.format("[ОШИБКА]: неверное значение в поле [%s].", fieldName),
                value, $(By.id(fieldNames.find(fieldName))).getValue());
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
    }

    /**
     * Нажимает на кнопку с указанным именем на этой странице.
     *
     * @param buttonName имя кнопки
     */
    public void clickButtonOnMyContractsPage(String buttonName) throws Exception {
        Dictionary buttonNames = new Dictionary();
        buttonNames.add("Поиск", SEARCH_BUTTON_ID);
        buttonNames.add("Очистить", CANCEL_BUTTON_ID);

        $(By.id(buttonNames.find(buttonName))).waitUntil(visible, timeout).click();
        this.waitLoadingRectangle();
        this.waitLoadingImage();
        this.logPressedButtonName(buttonName);
    }

    /**
     * Устанавливает переключатель [Вид документа] в положение [Дополнительные соглашения]
     */
    public void clickOnSelectAddendumRadioButton() {
        $(this.getBy(DOCUMENT_TYPE_ADDENDUM_ID)).waitUntil(visible, timeout, polling).click();
    }

    /**
     * Переходит по ссылке [Номер договора] в таблице с результатами поиска.
     */
    public void clickOnContractNumberLinkInTable() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        String xPathLocal = String.format(ANY_COLUMN_ALL_ROWS_IN_TABLE_XPATH, "ContractNumber") + "/a";
        $(By.xpath(xPathLocal)).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * Переходит по ссылке с указанным порядковым номером [Номер договора] в таблице с результатами поиска.
     *
     * @param rowNumber номер строки в таблице
     */
    public void clickOnContractNumberLinkInTable(int rowNumber) throws Exception {
        TimeUnit.SECONDS.sleep(1);
        String xPathLocal = String.format(ANY_COLUMN_ALL_ROWS_IN_TABLE_XPATH, "ContractNumber") + "/a";
        ElementsCollection links = $$(By.xpath(xPathLocal)).filterBy(visible);
        int row = rowNumber - 1;
        Assert.assertTrue("[ОШИБКА]: номер строки находится вне допустимого диапазона",
                row >= 0 && row <= links.size());
        System.out.printf("[ИНФОРМАЦИЯ]: переходим по ссылке с номером договора в строке [%d] столбца [Номер договора].%n",
                rowNumber);
        links.get(row).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * Получает значение из столбца с указанным именем и строки с указанным номером в таблице с результатами поиска.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  номер строки в таблице
     */
    public String getValueFromColumn(String columnName, int rowNumber) {
        Dictionary columnNames = new Dictionary();
        columnNames.add("Номер договора", String.format(ANY_COLUMN_ALL_ROWS_IN_TABLE_XPATH, "ContractNumber") + "/a");
        columnNames.add("Номер закупки/лота", String.format(ANY_COLUMN_ALL_ROWS_IN_TABLE_XPATH, "TradeLotNumber") + "/a");
        columnNames.add("№", String.format(ANY_COLUMN_ALL_ROWS_IN_TABLE_XPATH, "ID"));
        columnNames.add("Наименование закупки", String.format(ANY_COLUMN_ALL_ROWS_IN_TABLE_XPATH, "TradeName"));

        String xpathLocal = columnNames.find(columnName);
        String value = $$(By.xpath(xpathLocal)).get(rowNumber - 1).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: получено значение [%s] из строки [%d] в столбце [%s].%n",
                value, rowNumber - 1, columnName);
        return value;
    }

    /**
     * Проверяет наличие строк в таблице с результатами поиска.
     */
    public void checkNumberOfRowsInTable(String rowCount) throws Exception {
        this.waitLoadingImage();
        int count = $$(By.xpath(String.format(ANY_COLUMN_ALL_ROWS_IN_TABLE_XPATH, "ContractNumber") + "/a")).size();
        switch (rowCount) {
            //----------------------------------------------------------------------------------------------------------
            case "all":
                Assert.assertNotEquals("[ОШИБКА]: нулевое количество строк в таблице", 0, count);
                break;
            //----------------------------------------------------------------------------------------------------------
            case "one":
                Assert.assertEquals("[ОШИБКА]: количество строк в таблице не равно 1", 1, count);
                break;
            //----------------------------------------------------------------------------------------------------------
            case "some":
                Assert.assertTrue("[ОШИБКА]: количество строк в таблице не находится в диапазоне от 1 до 3",
                        count > 0 && count < 4);
                break;
            //----------------------------------------------------------------------------------------------------------
            default:
                Assert.fail(String.format(
                        "[ОШИБКА]: в метод (checkNumberOfRowsInTable) передан некорректный параметр (rowCount): [%s]",
                        rowCount));
                break;
            //----------------------------------------------------------------------------------------------------------
        }
        System.out.printf("[ИНФОРМАЦИЯ]: количество строк в таблице [%d].%n", count);
    }
}
