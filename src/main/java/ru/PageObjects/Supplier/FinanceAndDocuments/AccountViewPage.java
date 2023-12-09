package ru.PageObjects.Supplier.FinanceAndDocuments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Supplier.CommonSupplierPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс описывающий страницу [Просмотр счёта] (supplier/lk/Accounting/AccountingView.aspx).
 * Created by Evgeniy Glushko on 30.03.2016.
 * Updated by Vladimir V. Klochkov on 07.11.2018.
 */
public class AccountViewPage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public AccountViewPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Транзакции]
    private static final String TRANSACTIONS_BUTTON_ID = "BaseMainContent_MainContent_hpTransactionList";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Щелчок по кнопке [Транзакции].
     */
    public void clickTransactionsButton() throws Exception {
        this.logButtonNameToPress("Транзакции");
        $(By.id(TRANSACTIONS_BUTTON_ID)).waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName("Транзакции");
        this.checkPageUrl("Реестр транзакций счета");
    }
}
