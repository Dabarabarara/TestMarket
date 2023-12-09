package ru.StepDefinitions.Supplier.TradeView;

import cucumber.api.java.en.And;
import ru.PageObjects.Supplier.PurchaseTradeViewPage;
import ru.PageObjects.Supplier.SupplierPurchasePage;
import ru.StepDefinitions.AbstractStepDefinitions;

/**
 * Класс, описывающий шаги работы поставщика со страницей просмотра закупки (supplier/auction/Trade/View.aspx?).
 * Created by Vladimir V. Klochkov on 27.09.2019.
 * Updated by Vladimir V. Klochkov on 25.02.2021.
 */
public class SupplierPurchaseTradeViewStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final PurchaseTradeViewPage purchaseTradeViewPage = new PurchaseTradeViewPage(driver);
    private final SupplierPurchasePage supplierPurchasePage = new SupplierPurchasePage(driver);

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    @And("'Поставщик' проверяет наличие кнопки 'Подать заявку' для закупки по 223-ФЗ")
    public void supplierChecksForAnyAddApplicationButton223FZ() {
        String stepName = "'Поставщик' проверяет наличие кнопки 'Подать заявку' для закупки по 223-ФЗ";
        this.logStepName(stepName);

        timer.start();

        purchaseTradeViewPage.checkForForAnyAddApplicationButton();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет отсутсвие кнопки 'Подать заявку' для выбранного предмета предварительного отбора")
    public void supplierCheckForTheAbsenceAddApplicationButton() {
        String stepName = "'Поставщик' проверяет отсутсвие кнопки 'Подать заявку' для выбранного предмета " +
                "предварительного отбора";
        this.logStepName(stepName);

        timer.start();

        purchaseTradeViewPage.checkForTheAbsenceAddApplicationButton();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' нажимает на кнопку 'Подать заявку' для лота № {string}")
    public void supplierClicksOnAddApplicationButtonByLotNumber(String lotNumber) throws Throwable {
        String stepName = String.format("'Поставщик' нажимает на кнопку 'Подать заявку' для лота № '%s'", lotNumber);
        this.logStepName(stepName);

        timer.start();

        purchaseTradeViewPage.clickOnAddApplicationButtonByLotNumber(lotNumber);

        //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        this.setPurchaseRequestPageSwitcher(); // в этом же методе проверяется что открылась старая или новая страница
        //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' нажимает на кнопку 'Подать заявку' для предмета предварительного отбора № {string}")
    public void supplierClickOnAddApplicationButtonByPreselectionObjectNumber(String preselectionObjectNumber) throws Throwable {
        String stepName = String.format("'Поставщик' нажимает на кнопку 'Подать заявку' для лота № '%s'",
                preselectionObjectNumber);
        this.logStepName(stepName);

        timer.start();

        purchaseTradeViewPage.clickOnAddApplicationButtonByLotNumber(preselectionObjectNumber);

        //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        this.setPurchaseRequestPageSwitcher(); // в этом же методе проверяется что открылась старая или новая страница
        //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' нажимает на кнопку 'Запросить разъяснение' на карточке закупки в заголовке формы")
    public void supplierClicksOnCreateClarificationRequestTopButton() {
        String stepName =
                "'Поставщик' нажимает на кнопку 'Запросить разъяснение' на карточке закупки в заголовке формы";
        this.logStepName(stepName);

        timer.start();

        purchaseTradeViewPage.clickOnCreateClarificationRequestTopButton();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' нажимает на кнопку 'Запросить разъяснение' для лота № {string}")
    public void supplierClicksOnCreateResultClarificationRequestButtonByLotNumber(String lotNumber) throws Throwable {
        String stepName = String.
                format("'Поставщик' нажимает на кнопку 'Запросить разъяснение' для лота № '%s'", lotNumber);
        this.logStepName(stepName);

        timer.start();

        purchaseTradeViewPage.clickOnCreateResultClarificationRequestButtonByLotNumber(lotNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' нажимает на кнопку 'Подача ценовых предложений' при попозиционном сравнении ценовых предложений$")
    public void supplierClicksSubmissionPriceOffersButtonOnPositionalComparisonTrades() throws Throwable {
        String stepName = "'Поставщик' нажимает на кнопку 'Подача ценовых предложений' при попозиционном сравнении " +
                "ценовых предложений";
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.clickSubmissionPriceOffersButtonOnPositionalComparisonTrades();
        //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        this.setPurchaseRequestPageSwitcher(); // в этом же методе проверяется что открылась старая или новая страница
        //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' переходит к просмотру раздела {string} на карточке закупки")
    public void supplierGoesToViewPartition(String blockName) throws Throwable {
        String stepName =
                String.format("'Поставщик' переходит к просмотру раздела '%s' на карточке закупки", blockName);
        this.logStepName(stepName);

        timer.start();

        purchaseTradeViewPage.gotoBlock(blockName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' переходит по ссылке в столбце {string} строка {int} таблицы 'Запросы на разъяснение документации' на карточке закупки")
    public void supplierGoesToLinkInColumnLineByNumberOfClarificationRequestsTable(String columnName, int lineNumber)
            throws Throwable {
        String stepName = String.format("'Поставщик' переходит по ссылке в столбце '%s' строка '%d' таблицы " +
                "'Запросы на разъяснение документации' на карточке закупки", columnName, lineNumber);
        this.logStepName(stepName);

        timer.start();

        purchaseTradeViewPage.clickOnLinkInLineByNumberFromClarificationRequestsTable(columnName, lineNumber);

        timer.printStepTotalTime(stepName);
    }

    /*******************************************************************************************************************
     * Вспомогательные методы класса.
     ******************************************************************************************************************/
    /**
     * Определяет какая версия страницы подачи заявок используется в данный момент и устанавливает соответствующий
     * признак как поле класса (очевидно, что на момент вызова этого метода страница уже должна быть открыта).
     */
    private void setPurchaseRequestPageSwitcher() throws Throwable {
        String pageNameFirst = "Заявка на участие в режиме редактирования";
        String pageNameSecond = "Заявка на участие в режиме редактирования старая";
        boolean requestPageNew = !purchaseTradeViewPage.checkPageUrl(pageNameFirst, pageNameSecond).contains("старая");

        config.setParameter("PurchaseRequestPageVersion", requestPageNew ? "новая" : "старая");

        System.out.printf("[ИНФОРМАЦИЯ]: используется %s страница подачи заявки.%n",
                config.getParameter("PurchaseRequestPageVersion"));
    }
}
