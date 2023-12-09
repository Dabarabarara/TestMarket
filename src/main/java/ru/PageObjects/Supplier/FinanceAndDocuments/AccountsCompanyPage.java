package ru.PageObjects.Supplier.FinanceAndDocuments;

import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.PageObjects.Supplier.CommonSupplierPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывающий страницу [Счета компании]
 * ( .kontur.ru/supplier/lk/Accounting/AccountingList.aspx ).
 * Created by Evgeniy Glushko on 30.03.2016
 * Updated by Vladimir V. Klochkov on 18.08.2020.
 */
public class AccountsCompanyPage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //-----------------------------------------------------------------------------------------------------------------
    // Ссылка на конкретный счёт в колонке [Номер счёта]
    private static final String ACCOUNT_NUMBER_VALUE_LINK = "//td/a[contains(., '%s')]";
    //-----------------------------------------------------------------------------------------------------------------
    // Все строки в колонке [Номер счета]
    private static final String ACCOUNT_NUMBER_ALL_LINK =
            "//td[@aria-describedby='BaseMainContent_MainContent_ucAccountingList_jqgAccounting_Number']";
    //-----------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public AccountsCompanyPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Нажимает на ссылку в колонке [Номер счета] для счета, который содержит строку [accountNumber].
     *
     * @param accountNumber номер счета
     */
    public void clickOnAccountNumberLink(String accountNumber) throws Exception {
        SelenideElement account = $(this.getBy(String.format(ACCOUNT_NUMBER_VALUE_LINK, accountNumber)));
        account.waitUntil(clickable, timeout, polling);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие на ссылку с текстом [%s] в колонке [Номер счета].%n",
                account.getText());
        account.click();
        this.waitForPageLoad();
        this.checkPageUrl("Просмотр счёта");
    }

    /**
     * Проверяет наличие требуемого количества строк в таблице.
     *
     * @param rowCount переключатель [all/one].
     */
    public void checkRowNumberInTable(String rowCount) throws Exception {
        this.waitForPageLoad();
        int totalRows = $$(this.getBy(ACCOUNT_NUMBER_ALL_LINK)).size();

        switch (rowCount) {
            //----------------------------------------------------------------------------------------------------------
            case "all":
                Assert.assertTrue("[ОШИБКА]: нулевое количество строк в таблице", totalRows > 0);
                break;
            //----------------------------------------------------------------------------------------------------------
            case "one":
                Assert.assertEquals("[ОШИБКА]: количество строк в таблице не равно 1", 1, totalRows);
                break;
            //----------------------------------------------------------------------------------------------------------
            default:
                Assert.fail(String.format(
                        "[ОШИБКА]: в метод (checkNumberRowInTable) передан некорректный параметр (rowCount): '%s'",
                        rowCount));
                break;
            //----------------------------------------------------------------------------------------------------------
        }

        System.out.printf("[ИНФОРМАЦИЯ]: реальное количество строк в таблице [%d].%n", totalRows);
    }
}
