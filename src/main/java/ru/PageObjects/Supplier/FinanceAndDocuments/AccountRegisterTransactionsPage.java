package ru.PageObjects.Supplier.FinanceAndDocuments;

import Helpers.SoftAssert;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Supplier.CommonSupplierPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывающий страницу: Реестр транзакций счёта ... [supplier/lk/Accounting/Transactions.aspx?AccountId]
 * Created by Evgeniy Glushko on 30.03.2016
 * Updated by Alexander S. Vasyurenko on 02.04.2021.
 */
public class AccountRegisterTransactionsPage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка в столбце [Номер], параметры [Тип транзакции] и [Номер заявки на участие]
    private static final String GET_TRANSACTIONS_LINKS_BY_REQUEST_NUMBER_XPATH =
            "//td[contains(., '%s')]/../td[6]/a[contains(., '%s')]" +
                    "/../../td[contains(@aria-describedby, 'BaseMainContent_MainContent_jqgTransactions_Number')]/a";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка в столбце [Номер] для проверки списания средств ускоренной аккредитации
    private static final String BOOSTED_ACCREDITATION_TRANSACTIONS_XPATH =
            "//a[contains(@href, '.kontur.ru/supplier/lk/Accounting/TransactionView.aspx?Id=')]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица [Реестр транзакций счёта]
    private static final String TRANSACTIONS_TABLE_ID = "BaseMainContent_MainContent_jqgTransactions";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка в столбце [Номер] (для всех строк таблицы)
    private static final String NUMBER_COLUMN_ALL_TABLE_LINKS_XPATH =
            "//td[@aria-describedby='BaseMainContent_MainContent_jqgTransactions_Number']/a";
    //------------------------------------------------------------------------------------------------------------------
    // Открыть выбор количества отображаемых в таблице строк [V]
    private static final String SELECT_NUMBER_OF_ROWS_XPATH =
            "//*[@id='BaseMainContent_MainContent_jqgTransactions_pager_center']/table/tbody//select";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public AccountRegisterTransactionsPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     Методы страницы.
     ******************************************************************************************************************/
    /**
     * В таблице [Реестр транзакций счёта] ищем номер заявки в колонке [Номер заявки на участие] после чего нажимаем на
     * соответствующую ссылку в колонке [Номер] найденной строки.
     *
     * @param transactionType тип транзации (например "Блокирование средств на виртуальном счете")
     * @param linkNumber      номер ссылки (0 или 1)
     * @param expectedLinks   общее количество ожидаемых ссылок (1 или 2)
     */
    public void clickLinkInNumberColumn(String transactionType, int linkNumber, int expectedLinks) throws Exception {
        String purchaseNumber = config.getParameter("PurchaseNumber");
        String requestNumber = config.getParameter("SupplierRequestNumber");

        this.waitForPageLoad();
        this.waitLoadingRectangle();
        $(By.id(TRANSACTIONS_TABLE_ID)).waitUntil(visible, timeout, polling);
        String version = this.getCurrentServerVersion();
        System.out.printf("[ИНФОРМАЦИЯ]: произведен переход к концу страницы [%s].%n", version);
        $(By.xpath(SELECT_NUMBER_OF_ROWS_XPATH)).waitUntil(visible, timeout, polling).selectOptionByValue("30");
        this.waitForPageLoad();
        this.waitLoadingRectangle();
        ElementsCollection actualLinks = $$(By.xpath(
                String.format(GET_TRANSACTIONS_LINKS_BY_REQUEST_NUMBER_XPATH, transactionType, requestNumber)));
        System.out.printf("[ИНФОРМАЦИЯ]: ищем транзакции по блокировке средств на счете для закупки с " +
                "номером: [%s], по заявке с номером: [%s].%n", purchaseNumber, requestNumber);
        System.out.printf("[ИНФОРМАЦИЯ]: ожидалось транзакций [%d], найдено транзакций [%d].%n",
                expectedLinks, actualLinks.size());
        Assert.assertTrue(String.format(
                "[ОШИБКА]: реальное количество транзакций по заявке: [%s] для закупки: [%s] меньше ожидаемого: [%d].",
                requestNumber, purchaseNumber, expectedLinks), expectedLinks <= actualLinks.size());
        Assert.assertTrue("[ОШИБКА]: неверно задан порядковый номер ссылки",
                (linkNumber + 1) <= actualLinks.size());
        actualLinks.get(linkNumber).click();
        this.waitForPageLoad();
        this.checkPageUrl("Просмотр транзакции");
    }

    /**
     * В таблице [Реестр транзакций счёта] ищет номер заявки в колонке [Номер заявки на участие] после чего нажимает на
     * соответствующую ссылку в колонке [Номер] найденной строки.
     *
     * @param linkNumber      номер ссылки (0 или 1)
     * @param expectedLinks   общее количество ожидаемых ссылок (1 или 2)
     */
    public void clickLinkInNumberColumnByApplicationNumberAcceleratedAccreditation(int linkNumber, int expectedLinks)
            throws Exception {
        this.waitForPageLoad();
        this.waitLoadingRectangle();
        $(By.id(TRANSACTIONS_TABLE_ID)).waitUntil(visible, timeout, polling);
        String version = this.getCurrentServerVersion();
        System.out.printf("[ИНФОРМАЦИЯ]: произведен переход к концу страницы [%s].%n", version);
        ElementsCollection actualLinks = $$(By.xpath(BOOSTED_ACCREDITATION_TRANSACTIONS_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: ищем транзакции по списанию средств со  счета для " +
                "оплаты услуги по ускоренной аккредитации.%n");
        System.out.printf("[ИНФОРМАЦИЯ]: ожидалось транзакций [%d], найдено транзакций [%d].%n",
                expectedLinks, actualLinks.size());
        Assert.assertTrue("[ОШИБКА]: неверно задан порядковый номер ссылки",
                (linkNumber + 1) <= actualLinks.size());
        actualLinks.get(linkNumber).click();
        this.waitForPageLoad();
        this.checkPageUrl("Просмотр транзакции");
    }

    /**
     * Проверка количества строк в таблице.
     *
     * @param rowCount ожидаемое количество строк в таблице
     */
    public void checkRowCountInTable(String rowCount) throws Exception {
        this.waitLoadingImage();
        ElementsCollection numbers = $$(By.xpath(NUMBER_COLUMN_ALL_TABLE_LINKS_XPATH));
        numbers.get(0).waitUntil(visible, timeout, polling);
        int count = numbers.size();
        System.out.printf("[ИНФОРМАЦИЯ]: количество строк в таблице: [%d].%n", count);

        if (rowCount.equals("one"))
            Assert.assertEquals("[ОШИБКА]: количество строк в таблице не равно единице", 1, count);
        else
            Assert.assertNotEquals("[ОШИБКА]: нулевое количество строк в таблице", 0, count);
    }
}