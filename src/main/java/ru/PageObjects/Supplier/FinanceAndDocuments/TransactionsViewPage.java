package ru.PageObjects.Supplier.FinanceAndDocuments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Supplier.CommonSupplierPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс описывающий страницу [Просмотр транзакции].
 * Created by Evgeniy Glushko on 01.04.2016.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class TransactionsViewPage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public TransactionsViewPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер заявки на участие]
    private static final String NUMBER_REQUEST_ID = "BaseMainContent_MainContent_hpTradeLotApplicationId";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип транзакции]
    private static final String TRANSACTION_TYPE_ID = "BaseMainContent_MainContent_lblTransactionType";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Сумма транзакции]
    private static final String TRANSACTION_SUM_ID = "BaseMainContent_MainContent_lblTransactionSum";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет поле [Номер заявки на участие].
     *
     * @param requestNumber ожидаемое значение поля
     */
    public void checkRequestNumber(String requestNumber) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем поле: [Номер заявки на участие] на значение: [%s].%n",
                requestNumber);
        $(By.id(NUMBER_REQUEST_ID)).waitUntil(visible, timeout, polling).shouldHave(text(requestNumber));
    }

    /**
     * Проверяет поле [Тип транзакции].
     *
     * @param transactionType ожидаемое значение поля
     */
    public void checkTransactionType(String transactionType) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем поле: [Тип транзакции] на значение: [%s].%n",
                transactionType);
        $(By.id(TRANSACTION_TYPE_ID)).waitUntil(visible, timeout, polling).shouldHave(text(transactionType));
    }

    /**
     * Проверяет поле [Сумма транзакции].
     *
     * @param transactionSum ожидаемое значение поля
     */
    public void checkTransactionSum(String transactionSum) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем поле: [Сумма транзакции] на значение: [%s].%n",
                transactionSum);
        $(By.id(TRANSACTION_SUM_ID)).waitUntil(visible, timeout, polling).shouldHave(text(transactionSum));
    }
}
