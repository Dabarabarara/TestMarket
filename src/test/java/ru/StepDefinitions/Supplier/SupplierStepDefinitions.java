package ru.StepDefinitions.Supplier;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import ru.PageObjects.Authorization.SupplierOrOperatorrLogInPage;
import ru.PageObjects.CommonDialogs.CompletedDialog;
import ru.PageObjects.CommonDialogs.ConfirmDialog;
import ru.PageObjects.CommonDialogs.PriceOffersDialog;
import ru.PageObjects.Supplier.*;
import ru.PageObjects.Supplier.FinanceAndDocuments.*;
import ru.PageObjects.Supplier.Messages.SupplierCreateMessagePage;
import ru.PageObjects.Supplier.Messages.SupplierMessagesPage;
import ru.PageObjects.Supplier.Messages.SupplierRecipientSelectionDialog;
import ru.PageObjects.Supplier.MyOrganization.EditInformationPage;
import ru.PageObjects.Supplier.MyOrganization.OrganizationInfoPage;
import ru.StepDefinitions.AbstractStepDefinitions;

/**
 * Класс, описывающий общие шаги работы поставщика.
 * Created by Evgeniy Glushko on 28.03.2016.
 * Updated by Alexander S. Vasyurenko on 05.04.2021.
 */
public class SupplierStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final SupplierOrOperatorrLogInPage supplierOrOperatorrLogInPage = new SupplierOrOperatorrLogInPage(driver);
    private final SupplierCreateMessagePage supplierCreateMessagePage = new SupplierCreateMessagePage(driver);
    private final SupplierMyRequestPage supplierMyRequestPage = new SupplierMyRequestPage(driver);
    private final RecommendedTradesPage recommendedTradesPage = new RecommendedTradesPage(driver);
    private final OrganizationInfoPage organizationInfoPage = new OrganizationInfoPage(driver);
    private final TransactionsViewPage transactionsViewPage = new TransactionsViewPage(driver);
    private final SupplierPurchasePage supplierPurchasePage = new SupplierPurchasePage(driver);
    private final SupplierTraidingPage supplierTraidingPage = new SupplierTraidingPage(driver);
    private final SupplierMessagesPage supplierMessagesPage = new SupplierMessagesPage(driver);
    private final AccountsCompanyPage accountsCompanyPage = new AccountsCompanyPage(driver);
    private final EditInformationPage editInformationPage = new EditInformationPage(driver);
    private final CommonSupplierPage commonSupplierPage = new CommonSupplierPage(driver);
    private final SearchPurchasePage searchPurchasePage = new SearchPurchasePage(driver);
    private final SupplierRecipientSelectionDialog supplierRecipientSelectionDialog =
            new SupplierRecipientSelectionDialog(driver);
    private final AccountRegisterTransactionsPage accountRegisterTransactionsPage =
            new AccountRegisterTransactionsPage(driver);
    private final AccountViewPage accountViewPage = new AccountViewPage(driver);
    private final MyContractsPage myContractsPage = new MyContractsPage(driver);
    private final MyTendersPage myTendersPage = new MyTendersPage(driver);
    private final ContractPage contractPage = new ContractPage(driver);

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    @And("^'Поставщик' проверяет, что открыта страница \"([^\"]*)\"$")
    public void supplierChecksThatThePageIsOpened(String pageName) throws Throwable {
        String stepName = String.format("'Поставщик' проверяет, что открыта страница '%s'", pageName);
        this.logStepName(stepName);

        timer.start();

        commonSupplierPage.checkPageUrl(pageName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' переходит на страницу \"([^\"]*)\"$")
    public void supplierGoesToPage(String pageName) throws Throwable {
        String stepName = String.format("'Поставщик' переходит на страницу '%s'", pageName);
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.openPage(pageName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' нажимает на кнопку 'Транзакции' и переходит на страницу 'Реестр транзакций счёта'$")
    public void supplierClicksOnTransactionsButtonAndGoesToRegistryOfAccountTransactionsPage() throws Throwable {
        String stepName =
                "'Поставщик' нажимает на кнопку 'Транзакции' и переходит на страницу 'Реестр транзакций счёта'";
        this.logStepName(stepName);

        timer.start();

        accountViewPage.clickTransactionsButton();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' переходит на страницу 'Предложения для Вас'$")
    public void supplierGoesToCurrentOffersPage() throws Throwable {
        String stepName = "'Поставщик' переходит на страницу 'Предложения для Вас'";
        this.logStepName(stepName);

        timer.start();

        commonSupplierPage.clickTopMenuItem("АКТУАЛЬНО");
        commonSupplierPage.waitForPageLoad();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' переходит к списку закупок$")
    public void supplierGoesToPurchasesList() throws Throwable {
        String stepName = "'Поставщик' переходит к списку закупок";
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.waitLoadingImage();
        supplierPurchasePage.clickTopMenuItem("ЗАКУПКИ");
        supplierPurchasePage.clickLinkUnderMenu("Поиск закупок");
        supplierPurchasePage.waitForPageLoad();
        supplierPurchasePage.checkPageUrl("Поиск закупок");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' переходит на страницу 'Мои заявки'$")
    public void supplierGoesToMyRequestsPage() throws Throwable {
        String stepName = "'Поставщик' переходит на страницу 'Мои заявки'";
        this.logStepName(stepName);

        timer.start();

        commonSupplierPage.clickTopMenuItem("ЗАКУПКИ");
        commonSupplierPage.clickLinkUnderMenu("Мои заявки");

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' переходит на страницу 'Мои Торги Переторжки'")
    public void supplierGoesToMyTendersPage() throws Throwable {
        String stepName = "'Поставщик' переходит на страницу 'Мои Торги Переторжки'";
        this.logStepName(stepName);

        timer.start();

        commonSupplierPage.clickTopMenuItem("ЗАКУПКИ");
        commonSupplierPage.clickLinkUnderMenu("Мои Торги/Переторжки");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' переходит на страницу 'Информация об организации'$")
    public void supplierSelectsMyOrganizationOrganizationInfo() throws Throwable {
        String stepName = "'Поставщик' переходит на страницу 'Информация об организации'";
        this.logStepName(stepName);

        timer.start();

        commonSupplierPage.clickTopMenuItem("МОЯ ОРГАНИЗАЦИЯ");
        commonSupplierPage.clickLinkUnderMenu("Информация об организации");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' находит закупки с категорией \"([^\"]*)\"$")
    public void supplierFindsPurchasesByCategory(String category) throws Throwable {
        String stepName = String.format("'Поставщик' находит закупки с категорией '%s'", category);
        this.logStepName(stepName);

        timer.start();

        recommendedTradesPage.
                clickOnClearButton().
                setSingleCategory(category).
                clickOnSearchButton().
                checkResultsIsNotEmpty();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' переходит по ссылке с номером извещения о закупке в {string} строке результатов поиска закупки по 223-ФЗ")
    public void supplierGoesToLinkWithSpecifiedOrderNumber223FZ(String orderNumber) throws Throwable {
        String stepName = String.format("'Поставщик' переходит по ссылке с номером извещения о закупке в '%s' строке " +
                "результатов поиска для 223-ФЗ", orderNumber);
        this.logStepName(stepName);

        timer.start();

        recommendedTradesPage.goToLinkWithSpecifiedOrderNumber223FZ(Integer.parseInt(orderNumber));

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет наличие кнопки 'Подать заявку' в {string} строке результатов поиска закупки по 223-ФЗ")
    public void supplierChecksAddApplicationButtonWithSpecifiedOrderNumber223FZ(String orderNumber) {
        String stepName = String.format(
                "'Поставщик' проверяет наличие кнопки 'Подать заявку' в '%s' строке результатов поиска закупки по 223-ФЗ",
                orderNumber);
        this.logStepName(stepName);

        timer.start();

        recommendedTradesPage.checkAddApplicationButtonWithSpecifiedOrderNumber223FZ(Integer.parseInt(orderNumber));

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' находит созданную закупку$")
    public void supplierFindsPurchase() throws Throwable {
        String stepName = "'Поставщик' находит созданную закупку";
        this.logStepName(stepName);

        timer.start();

        // Для отладки -------------------------------------------------------------------------------------------------
        // config.setParameter("PurchaseNumber", "19027");
        // Для отладки -------------------------------------------------------------------------------------------------

        searchPurchasePage.setPurchaseNumberLotNumber(config.getParameter("PurchaseNumber"));
        searchPurchasePage.clickButton("Поиск");
        searchPurchasePage.waitLoadingImage();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' находит и открывает ранее созданную закупку$")
    public void supplierFindsAuction() throws Throwable {
        String stepName = "'Поставщик' находит и открывает ранее созданную закупку";
        this.logStepName(stepName);

        timer.start();

        // Для отладки -------------------------------------------------------------------------------------------------
        // config.setParameter("PurchaseNumber", "19027");
        // Для отладки -------------------------------------------------------------------------------------------------

        supplierPurchasePage.clickTopMenuItem("ЗАКУПКИ");
        supplierPurchasePage.clickLinkUnderMenu("Поиск закупок");
        supplierPurchasePage.checkPageUrl("Поиск закупок");
        searchPurchasePage.setPurchaseNumberLotNumber(config.getParameter("PurchaseNumber"));
        searchPurchasePage.clickButton("Поиск");
        supplierPurchasePage.waitLoadingImage();
        supplierPurchasePage.clickNamePurchaseTableTenderLink();
        config.switchToNewWindowInBrowser();

        // Проверка открытия страницы [.kontur.ru/supplier/auction/Trade/View]
        supplierPurchasePage.checkPageUrl("Закупка №");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' проверяет количество лотов \"([^\"]*)\" в закупке$")
    public void supplierChecksNumberOfLotsInPurchase(String expectedLots) {
        String stepName = String.format("'Поставщик' проверяет количество лотов '%s' в закупке", expectedLots);
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.checkNumberOfLotsInPurchase(expectedLots);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' открывает лот с номером \"([^\"]*)\" в закупке$")
    public void supplierOpensLotByNumber(String lotNumber) throws Throwable {
        String stepName = String.format("'Поставщик' открывает лот с номером '%s' в закупке", lotNumber);
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.openLotByNumber(lotNumber);
        config.switchToNewWindowInBrowser();

        // Проверка открытия страницы [.kontur.ru/supplier/auction/Trade/View]
        supplierPurchasePage.checkPageUrl("Закупка №");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' ожидает начала торгов$")
    public void supplierAwaitingsTheStartOfTrading() throws Throwable {
        String stepName = "'Поставщик' ожидает начала торгов";
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.awaitingTheStartOfTraiding();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' ожидает начала второй фазы торгов$")
    public void supplierAwaitingsTheStartOfTradingSecondPhase() throws Throwable {
        String stepName = "'Поставщик' ожидает начала второй фазы торгов";
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.awaitingTheStartOfTraidingSecondPhase();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' нажимает на кнопку 'Информация о торгах' для лота с номером \"([^\"]*)\" в закупке$")
    public void supplierClicksTradingInformationButton(String lotNumber) throws Throwable {
        String stepName = String.format(
                "'Поставщик' нажимает на кнопку 'Информация о торгах' для лота с номером [%s] в закупке", lotNumber);
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.clickTradingInformationButton(lotNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' нажимает на кнопку 'Информация о торгах' для лота \"([^\"]*)\" при попозиционном сравнении ценовых предложений$")
    public void supplierClicksTradingInformationButtonOnPositionalComparisonTrades(String lotNumber) throws Throwable {
        String stepName = String.format(
                "'Поставщик' нажимает на кнопку 'Информация о торгах' для лота [%s] при попозиционном сравнении " +
                        "ценовых предложений", lotNumber);
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.clickTradingInformationButtonOnPositionalComparisonTrades(lotNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' МСП нажимает на кнопку 'Информация о торгах' для лота \"([^\"]*)\"$")
    public void supplierClicksTradingInformationButtonOnSMBPurchasesTrades(String lotNumber) throws Throwable {
        String stepName =
                String.format("'Поставщик' МСП нажимает на кнопку 'Информация о торгах' для лота [%s]", lotNumber);
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.clickTradingInformationButtonOnSMBPurchasesTrades(lotNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' нажимает на кнопку 'Подача ценовых предложений'$")
    public void supplierClicksSubmissionPriceOffersButton() throws Throwable {
        String stepName = "'Поставщик' нажимает на кнопку 'Подача ценовых предложений'";
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.clickSubmissionPriceOffersButton();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' МСП нажимает на кнопку 'Подача ценовых предложений'$")
    public void supplierClicksSubmissionPriceOffersButtonOnSMBPurchasesTrades() throws Throwable {
        String stepName = "'Поставщик' МСП нажимает на кнопку 'Подача ценовых предложений'";
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.clickSubmissionPriceOffersButtonOnSMBPurchasesTrades();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' МСП проверяет, что номер закупки верен$")
    public void supplierChecksThatThePurchaseNumberIsCorrect() {
        String stepName = "'Поставщик' МСП проверяет, что номер закупки верен";
        this.logStepName(stepName);

        timer.start();

        String expected = config.getParameter("PurchaseNumber");
        String actual = supplierTraidingPage.getPurchaseNumberFromTradingPage();
        System.out.printf("[ИНФОРМАЦИЯ]: ожидаемый номер закупки МСП: [%s], актуальнный номер закупки МСП: [%s]%n",
                expected, actual);
        Assert.assertEquals("Номер закупки МСП на странице [Торги] не совпадает", expected, actual);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' вводит цену \"([^\"]*)\" в поле Предложение цены$")
    public void supplierSetsPriceOffer(String price) throws Throwable {
        String stepName = String.format("'Поставщик' вводит цену '%s' в поле 'Предложение цены'", price);
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.setNewPriceOffer(price);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' заполняет поле {string} значением {string} на странице 'Торги'")
    public void supplierSetFieldClearClickAndSendKeysOnBiddingPage(String fieldName, String value) throws Throwable {
        String stepName = String.format("'Поставщик' заполняет поле '%s' значением '%s' на странице 'Торги'",
                fieldName, value);
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.setFieldClearClickAndSendKeys(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет свое предложение 'Поправочного коэффициента' {string} в таблице 'Предложение участников'")
    public void supplierChecksHesOfferCorrectionFactor(String value) {
        String stepName = String.format("'Поставщик' проверяет свое предложение 'Поправочного коэффициента' '%s'" +
                " в таблице 'Предложение участников'", value);
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.checkTheSupplierCorrectionFactor(value);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' МСП вводит цену \"([^\"]*)\" в поле 'Предложение цены без НДС'$")
    public void supplierSetsPriceOfferOnSMBPurchasesTrades(String price) throws Throwable {
        String stepName = String.format("'Поставщик' МСП вводит цену '%s' в поле 'Предложение цены без НДС'", price);
        this.logStepName(stepName);

        timer.start();

        supplierTraidingPage.setNewPriceOffer(price);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' подает ценовое предложение$")
    public void supplierClicksSignButton() throws Throwable {
        String stepName = "'Поставщик' подает ценовое предложение";
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.clickSignButton();
        supplierPurchasePage.clickButtonInPopupWindow("ОК-Операция успешно выполнена");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' МСП подает ценовое предложение$")
    public void supplierClicksSignButtonOnSMBPurchasesTrades() throws Throwable {
        String stepName = "'Поставщик' МСП подает ценовое предложение";
        this.logStepName(stepName);

        timer.start();

        // Нажимаем на кнопку [Подписать]
        supplierTraidingPage.clickSignButton();

        // Нажимаем на кнопку [Да] в диалоговом окне [Подтверждение действия]
        ConfirmDialog confirmDialog = new ConfirmDialog(driver);
        confirmDialog.clickOnButton("Кнопка Да");

        // Нажимаем на кнопку [OK] в диалоговом окне [Операция успешно выполнена]
        CompletedDialog completedDialog = new CompletedDialog(driver);
        completedDialog.clickOnOkButton();

        timer.printStepTotalTime(stepName);
    }

    @Then("^Ценовое предложение отобразилось в списке лучших ценовых предложений \"([^\"]*)\"$")
    public void supplierGetsTheBestPriceOffers(String expected) throws Throwable {
        String stepName =
                String.format("Ценовое предложение отобразилось в списке лучших ценовых предложений '%s'", expected);
        this.logStepName(stepName);

        timer.start();

        String actual = supplierPurchasePage.getBestPriceOffer();
        System.out.printf("[ИНФОРМАЦИЯ]: ожидаемая сумма лучшего ЦП: [%s], актуальная сумма лучшего ЦП: [%s]%n",
                expected, actual);
        Assert.assertTrue("Сумма лучшего ценового предложения не совпадает", actual.contains(expected));

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' МСП проверяет поле 'Ваше ценовое предложение' на значение \"([^\"]*)\"$")
    public void supplierChecksYourPriceOfferFieldValueOnSMBPurchasesTrades(String expected) throws Throwable {
        String stepName =
                String.format("'Поставщик' МСП проверяет поле 'Ваше ценовое предложение' на значение '%s'", expected);
        this.logStepName(stepName);

        timer.start();

        String actual = supplierTraidingPage.getYourPriceOffer();
        System.out.printf(
                "[ИНФОРМАЦИЯ]: ожидаемая сумма Вашего ЦП: [%s], актуальная сумма Вашего ЦП: [%s]%n", expected, actual);
        Assert.assertTrue("Сумма Вашего ценового предложения без НДС не совпадает", actual.contains(expected));

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' проверяет отсутствие кнопки \"([^\"]*)\"$")
    public void supplierChecksButtonAbsence(String buttonName) throws Throwable {
        String stepName = String.format("'Поставщик' проверяет отсутствие кнопки '%s'", buttonName);
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.checkButtonAbsence(buttonName);
        supplierPurchasePage.logCurrentTradingInfo();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет уменьшение счетчика на странице 'Информация о торгах'")
    public void supplierCheckCounterDecrease() throws Throwable {
        String stepName = "'Поставщик' проверяет уменьшение счетчика на странице 'Информация о торгах'";
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.checkCounterDecrease();

        timer.printStepTotalTime(stepName);
    }


    @And("^'Поставщик' прикрепляет документ в разделе 'Новое предложение'$")
    public void supplierUploadsDocumentInNewOfferPartition() throws Throwable {
        String stepName = "'Поставщик' прикрепляет документ в разделе 'Новое предложение'";
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.uploadDocumentInNewOfferPartition();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' фиксирует время до окончания торгов$")
    public void supplierSavesAuctionPhaseCountdownCounter() {
        String stepName = "'Поставщик' фиксирует время до окончания торгов";
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.saveTimeUntilTheEndOfTrading();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' проверяет автоматическое продление переторжки после подачи ценового предложения до \"([^\"]*)\" мин.$")
    public void supplierChecksAuctionPhaseCountdownCounter(String minutes) throws Throwable {
        String stepName = String.format(
                "'Поставщик' проверяет автоматическое продление переторжки после подачи ценового предложения до '%s' мин.",
                minutes);
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.checkRetenderingAutoProlongation(minutes);

        timer.printStepTotalTime(stepName);
    }

    @And("^Информация о Поставщиках не отображается на форме подачи ценовых предложений$")
    public void suppliersInformationIsNotDisplayedOnPriceOffersForm() {
        String stepName = "Информация о Поставщиках не отображается на форме подачи ценовых предложений";
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.checkThatSuppliersInformationIsNotDisplayedOnPriceOffersForm();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' завершает ход торгов$")
    public void supplier1FinishesPriceOffers() throws Throwable {
        String stepName = "'Поставщик' завершает ход торгов";
        this.logStepName(stepName);

        timer.start();

        config.switchToMainWindowInBrowser();
        supplierPurchasePage.clickTopMenuItem("ЗАКУПКИ");

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет количество строк {int} в таблице 'Закупки' на странице 'Поиск закупок'")
    public void supplierCheckNumberOfRowsInPurchaseTableSearchPurchasePage(int purchaseNumber) {
        String stepName = String.format("'Поставщик' проверяет количество строк '%d' в таблице 'Закупки'", purchaseNumber);
        this.logStepName(stepName);

        timer.start();

        searchPurchasePage.checkNumberOfRowsInContractPositionsTable(purchaseNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' переходит по ссылке с номером закупки в результатах поиска$")
    public void supplierOpensPurchaseUsingOnlyPurchaseNumber() throws Throwable {
        String stepName = "Поставщик' переходит по ссылке с номером закупки в результатах поиска";
        this.logStepName(stepName);

        timer.start();

        // В этом методе мы переключаемся на новую вкладку в браузере ( открылась при щелчке по ссылке )
        searchPurchasePage.clickOnNoticeNumberWithLotNumber(config.getParameter("PurchaseNumber"));

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' переходит по ссылке с номером закупки в результатах поиска для лота № \"([^\"]*)\"$")
    public void supplierOpensPurchaseUsingPurchaseNumberAndLotNumber(String lotNumber) throws Throwable {
        String stepName = String.format(
                "Поставщик' переходит по ссылке с номером закупки в результатах поиска для лота № '%s'", lotNumber);
        this.logStepName(stepName);

        timer.start();

        String noticeNumberWithLotNumber = config.getParameter("PurchaseNumber") + "/" + lotNumber;
        searchPurchasePage.clickOnNoticeNumberWithLotNumber(noticeNumberWithLotNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' ожидает окончания загрузки страницы с деталями закупки 'Закупка № XXXX'$")
    public void supplierWaitsForLoadingPageWithPurchaseCommonDetails() throws Throwable {
        String stepName = "'Поставщик' ожидает окончания загрузки страницы с деталями закупки 'Закупка № XXXX'";
        this.logStepName(stepName);

        timer.start();

        // Эти действия выполняются уже в новой вкладке браузера
        supplierPurchasePage.waitLoadingImage();
        supplierPurchasePage.checkPageUrl("Закупка №", "504 Закупка №");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' ожидает окончания загрузки страницы с деталями процедуры 'Процедуры № XXXX'$")
    public void supplierWaitsForLoadingPageWithProcedureCommonDetails() throws Throwable {
        String stepName = "'Поставщик' ожидает окончания загрузки страницы с деталями закупки 'Процедуры № XXXX'";
        this.logStepName(stepName);

        timer.start();

        // Эти действия выполняются уже в новой вкладке браузера
        supplierPurchasePage.waitLoadingImage();
        supplierPurchasePage.checkPageUrl("Процедура №", "504 Процедура №");

        timer.printStepTotalTime(stepName);
    }

    @Then("^'Поставщик' № \"([^\"]*)\" проверяет \"([^\"]*)\" для лота № \"([^\"]*)\"$")
    public void supplierChecksBlockedOnAccountsAmounts(
            String supplierNumber, String transactionType, String lotNumber) throws Throwable {
        String stepName = String.format("'Поставщик' № '%s' проверяет '%s' для лота № '%s'",
                supplierNumber, transactionType, lotNumber);
        this.logStepName(stepName);

        timer.start();

        if (config.getConfigParameter("ChecksBlockedOnAccountsAmounts").equals("off")) {
            System.out.println(
                    "[ИНФОРМАЦИЯ]: проверка блокирования средств на виртуальном счете для Поставщиков отключена.");
            return;
        }
        //--------------------------------------------------------------------------------------------------------------
        // Получаем номер заявки и сумму транзакции из параметров теста
        //--------------------------------------------------------------------------------------------------------------
        String requestNumberKey = String.format("Supplier%sLot%sRequestNumber", supplierNumber, lotNumber);
        String applicationGuaranteeKey = String.format("Supplier%sLot%sTransactionSum", supplierNumber, lotNumber);
        String requestNumber = config.getParameter(requestNumberKey);
        String transactionSum = config.getParameter(applicationGuaranteeKey);
        //--------------------------------------------------------------------------------------------------------------
        System.out.printf(
                "[ИНФОРМАЦИЯ]: ищем номер заявки: [%s] для закупки с номером [%s], с суммой транзакции: [%s].%n",
                requestNumber, config.getParameter("PurchaseNumber"), transactionSum);
        config.setParameter("SupplierRequestNumber", requestNumber);

        accountRegisterTransactionsPage.clickLinkInNumberColumn(transactionType, 0, 1);
        transactionsViewPage.checkRequestNumber(requestNumber);
        transactionsViewPage.checkTransactionType(transactionType);
        transactionsViewPage.checkTransactionSum(transactionSum);

        timer.printStepTotalTime(stepName);
    }

    @Then("^'Поставщик' проверяет заблокированную сумму \"([^\"]*)\"$")
    public void supplierChecksBlockedSums(String transactionSum) throws Throwable {
        String stepName = String.format("'Поставщик' проверяет заблокированную сумму '%s'", transactionSum);
        this.logStepName(stepName);

        timer.start();

        String transactionType = "Блокирование средств на виртуальном счете";
        accountRegisterTransactionsPage.clickLinkInNumberColumn(transactionType, 0, 1);
        transactionsViewPage.checkRequestNumber(config.getParameter("SupplierRequestNumber"));
        transactionsViewPage.checkTransactionType(transactionType);
        transactionsViewPage.checkTransactionSum(transactionSum);

        timer.printStepTotalTime(stepName);
    }

    @Then("^'Поставщик' проверяет списание суммы за услугу ускоренной аккредитации \"([^\"]*)\"$")
    public void supplierChecksAcceleratedAccreditationSum(String transactionSum) throws Throwable {
        String stepName = String.format("'Поставщик' проверяет списание суммы за услугу ускоренной аккредитации '%s'",
                transactionSum);
        this.logStepName(stepName);

        timer.start();

        String transactionType = "Списание средств в счет оплаты ускоренного рассмотрения запросов на доступ";
        accountRegisterTransactionsPage.
                clickLinkInNumberColumnByApplicationNumberAcceleratedAccreditation(0, 1);
        transactionsViewPage.checkTransactionType(transactionType);
        transactionsViewPage.checkTransactionSum(transactionSum);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' производит поиск договора$")
    public void supplierSearchesContract() throws Throwable {
        String stepName = "'Поставщик' производит поиск договора";
        this.logStepName(stepName);

        timer.start();

        // Заполняем поле [Номер закупки/лота]
        myContractsPage.setFieldOnMyContractsPage("Номер закупки/лота",
                config.getParameter("PurchaseNumber"));

        // Нажимаем кнопку [Поиск]
        myContractsPage.clickButtonOnMyContractsPage("Поиск");

        // Делаем клик по ссылке с номером договора в результатах поиска
        myContractsPage.clickOnContractNumberLinkInTable();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' производит поиск дополнительного соглашения$")
    public void supplierSearchesAddendum() throws Throwable {
        String stepName = "'Поставщик' производит поиск дополнительного соглашения";
        this.logStepName(stepName);

        timer.start();

        // Заполняем поле [Номер закупки/лота]
        myContractsPage.setFieldOnMyContractsPage("Номер закупки/лота",
                config.getParameter("PurchaseNumber"));

        // Устанавливаем фильтр [Вид документа] в положение [Дополнительные соглашения]
        myContractsPage.clickOnSelectAddendumRadioButton();

        // Нажимаем кнопку [Поиск]
        myContractsPage.clickButtonOnMyContractsPage("Поиск");

        // Делаем клик по ссылке с номером договора в результатах поиска
        myContractsPage.clickOnContractNumberLinkInTable();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' находит и открывает договор по ссылке в строке с номером {int} результатов поиска")
    public void supplierSearchesContract(int rowNumber) throws Throwable {
        String stepName = String.format(
                "'Поставщик' находит и открывает договор по ссылке в строке с номером '%d' результатов поиска",
                rowNumber);
        this.logStepName(stepName);

        timer.start();

        // Заполняем поле [Номер закупки/лота]
        myContractsPage.setFieldOnMyContractsPage("Номер закупки/лота",
                config.getParameter("PurchaseNumber"));

        // Нажимаем кнопку [Поиск]
        myContractsPage.clickButtonOnMyContractsPage("Поиск");

        // Делаем клик по ссылке с номером договора в указанноной строке результатов поиска
        myContractsPage.clickOnContractNumberLinkInTable(rowNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' принимает и подписывает договор")
    public void supplierAcceptsAndSignsContract() throws Throwable {
        String stepName = "'Поставщик' принимает и подписывает договор";
        this.logStepName(stepName);

        timer.start();

        // Переключаемся на 2-ю вкладку браузера в которой открылся черновик договора
        config.switchToNewWindowInBrowser();
        contractPage.checkPageUrl("Поставщик-Подпишите договор");

        // Проверяем статус договора: [Договор отправлен участнику на подпись]
        contractPage.checkContractStatus("Договор отправлен участнику на подпись");

        // Устанавливаем переключатель в положение:
        // [Участник подтверждает, что обеспечение исполнения договора предоставляется путем внесения денежных средств]
        contractPage.clickMakingMoneyRadioButton();

        // Заполняем обязательные поля
        contractPage.setContractPageField("ОКТМО", "07712000");
        contractPage.setContractPageField("ОКПО", "54896122");
        contractPage.setContractPageField(
                "ОКОПФ", "10000"); // Не меняйте это значение иначе на бою тест упадет !
        contractPage.setContractPageField("Дата постановки на учет в налоговом органе", "01052005");

        // Прикрепляем документ
        contractPage.uploadDocument();

        // Подписываем договор
        contractPage.clickAcceptAndSignContractButton();
        contractPage.clickYesButton();

        // Проверяем статус договора: [Договор подписан участником] (режим просмотра подписанного договора)
        contractPage.checkContractStatus("Договор подписан участником");

        // Остаемся на странице с подписанным договором для осуществления дальнейших проверок (2-я вкладка браузера)

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' принимает и подписывает дополнительное соглашение")
    public void supplierAcceptsAndSignsAddendum() throws Throwable {
        String stepName = "'Поставщик' принимает и подписывает дополнительное соглашение";
        this.logStepName(stepName);

        timer.start();

        // Переключаемся на 2-ю вкладку браузера в которой открылся черновик дополнительного соглашения
        config.switchToNewWindowInBrowser();
        contractPage.checkPageUrl("Поставщик-Подпишите договор");

        // Проверяем статус дополнительного соглашения: [Договор отправлен участнику на подпись]
        contractPage.checkContractStatus("Договор отправлен участнику на подпись");

        // Проверяем, что переключатель находится в положение:
        // [Участник подтверждает, что обеспечение исполнения договора предоставляется путем внесения денежных средств]
        contractPage.checkMakingMoneyRadioButton();

        // Заполняем обязательные поля
        contractPage.setContractPageField("ОКТМО", "07712000");
        contractPage.setContractPageField("ОКПО", "54896122");
        contractPage.setContractPageField(
                "ОКОПФ", "10000"); // Не меняйте это значение иначе на бою тест упадет !
        contractPage.setContractPageField("Дата постановки на учет в налоговом органе", "01052005");

        // Прикрепляем документ
        contractPage.uploadDocument();

        // Подписываем дополнительное соглашение
        contractPage.clickAcceptAndSignContractButton();
        contractPage.clickYesButton();

        // Проверяем статус доп. соглашения: [Договор подписан участником] (режим просмотра подписанного доп. соглашение)
        contractPage.checkContractStatus("Договор подписан участником");

        // Остаемся на странице с подписанным доп. соглашением для осуществления дальнейших проверок (2-я вкладка браузера)

        timer.printStepTotalTime(stepName);
    }

    @Then("^'Поставщик' проверяет возможность скачать файл со страницы 'Договор'$")
    public void supplierChecksDownloadContractFile() throws Throwable {
        String stepName = "'Поставщик' проверяет возможность скачать файл со страницы 'Договор'";
        this.logStepName(stepName);

        timer.start();

        contractPage.checkDownloadContractFile();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' создает протокол разногласий - отклоняет договор$")
    public void supplierCreatesProtocolOfDisagreementsRejectsTheContract() throws Throwable {
        String stepName = "'Поставщик' создает протокол разногласий - отклоняет договор";
        this.logStepName(stepName);

        timer.start();

        // Переключаемся на 2-ю вкладку браузера в которой открылся черновик договора
        config.switchToNewWindowInBrowser();
        contractPage.checkPageUrl("Поставщик-Подпишите договор");

        // Проверяем статус договора: [Договор отправлен участнику на подпись]
        contractPage.checkContractStatus("Договор отправлен участнику на подпись");

        // Нажимаем на кнопку [Создать протокол разногласий]
        contractPage.clickCreateDifferenceProtocolButton();

        //--------------------------------------------------------------------------------------------------------------
        // Работа в диалоговом окне [Создание протокола разногласий]
        CreateDifferenceProtocolDialog createDifferenceProtocolDialog = new CreateDifferenceProtocolDialog(driver);
        createDifferenceProtocolDialog.
                ifDialogOpened().
                setReason("223 Автотесты.").
                attachFile().
                clickOnButtonByName("Кнопка Продолжить");
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' проверяет статус договора \"([^\"]*)\"$")
    public void supplierChecksContractStatus(String status) {
        String stepName = String.format("'Поставщик' проверяет статус договора '%s'", status);
        this.logStepName(stepName);

        timer.start();

        contractPage.checkContractStatus(status);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' переходит к просмотру раздела договора \"([^\"]*)\"$")
    public void supplierGoesToViewContractPagePartition(String partitionName) throws Throwable {
        String stepName = String.format("'Поставщик' переходит к просмотру раздела договора '%s'", partitionName);
        this.logStepName(stepName);

        timer.start();

        contractPage.goToPartition(partitionName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' проверяет количество строк \"([^\"]*)\" в таблице 'Позиции договора'$")
    public void supplierChecksNumberOfRowsInContractPositionsTable(String rows) {
        String stepName = String.format(
                "'Поставщик' проверяет количество строк '%s' в таблице 'Позиции договора'", rows);
        this.logStepName(stepName);

        timer.start();

        contractPage.checkNumberOfRowsInContractPositionsTable(rows);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' проверяет столбец \"([^\"]*)\" строка \"([^\"]*)\" таблица 'Позиции договора' текст \"([^\"]*)\"$")
    public void supplierChecksColumnByNameInLineByNumberFromContractPositionsTableForText(
            String columnName, String lineNumber, String cellValue) {
        String stepName = String.format(
                "'Поставщик' проверяет столбец '%s' строка '%s' таблица 'Позиции договора' текст '%s'",
                columnName, lineNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        contractPage.
                verifyCellByNameInLineByNumberFromContractPasitionsTableForText(columnName, lineNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @When("^'Поставщик' заходит в личный кабинет$")
    public void supplierGoesToPrivateCabinetPage() {
        String stepName = "'Поставщик' заходит в личный кабинет";
        this.logStepName(stepName);

        timer.start();

        supplierOrOperatorrLogInPage.goToUserLoginPage();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' \"([^\"]*)\" выбирает сертификат$")
    public void supplierSelectsCertificate(String certificateName) throws Throwable {
        String stepName = String.format("'Поставщик' '%s' выбирает сертификат", certificateName);
        this.logStepName(stepName);

        SelectCertificateDialog selectCertificateDialog = new SelectCertificateDialog(driver);

        timer.start();

        supplierOrOperatorrLogInPage.clickSelectCertificateButton();
        System.out.printf("[ИНФОРМАЦИЯ]: имя сертификата: [%s].%n", config.getParameter("AccreditationName"));
        selectCertificateDialog.selectCertificate().clickOKButton();

        timer.printStepTotalTime(stepName);
    }

    @Then("^'Поставщик' проверяет данные аккредитации$")
    public void supplierChecksAccreditation() {
        String stepName = "'Поставщик' проверяет данные аккредитации";
        this.logStepName(stepName);

        timer.start();

        organizationInfoPage.checkOrganizationNameField();
        organizationInfoPage.checkOrganizationAccreditationState();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' переключается на вкладку {string} страницы 'Информация об организации'")
    public void supplierClicksOnTabByTabNameInOrganizationInfoPage(String tabName) throws Throwable {
        String stepName = String.format(
                "'Поставщик' переключается на вкладку '%s' страницы 'Информация об организации'", tabName);
        this.logStepName(stepName);

        timer.start();

        organizationInfoPage.clickOnTab(tabName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет наличие кнопки {string} на странице 'Информация об организации'")
    public void supplierChecksForButtonByButtonNameInOrganizationInfoPage(String buttonName) {
        String stepName = String.format(
                "'Поставщик' проверяет наличие кнопки '%s' на странице 'Информация об организации'", buttonName);
        this.logStepName(stepName);

        timer.start();

        organizationInfoPage.checkButtonExist(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' нажимает на кнопку {string} на странице 'Информация об организации'")
    public void supplierClicksOnButtonByButtonNameInOrganizationInfoPage(String buttonName) throws Throwable {
        String stepName = String.format(
                "'Поставщик' нажимает на кнопку '%s' на странице 'Информация об организации'", buttonName);
        this.logStepName(stepName);

        timer.start();

        organizationInfoPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет, что список ссылок для скачивания документов организации на странице 'Информация об организации' не пустой")
    public void supplierChecksThatTheDocumentDownloadLinksColumnIsNotEmpty() {
        String stepName = "'Поставщик' проверяет, что список ссылок для скачивания документов организации на странице" +
                " 'Информация об организации' не пустой";
        this.logStepName(stepName);

        timer.start();

        organizationInfoPage.checkThatTheDocumentDownloadLinksColumnIsNotEmpty();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' пытается скачать документ со страницы 'Информация об организации' по первой сверху ссылке")
    public void supplierDownloadsFirstAttachedDocumentWithVerification() throws Throwable {
        String stepName =
                "'Поставщик' пытается скачать документ со страницы 'Информация об организации' по первой сверху ссылке";
        this.logStepName(stepName);

        timer.start();

        organizationInfoPage.downloadFirstAttachedDocumentWithVerification();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' пытается скачать документ 'Оферта.docx' со страницы 'Информация об организации'")
    public void supplierDownloadsOfferAttachedDocumentWithVerification() throws Throwable {
        String stepName =
                "'Поставщик' пытается скачать документ 'Оферта.docx' со страницы 'Информация об организации'";
        this.logStepName(stepName);

        timer.start();

        organizationInfoPage.downloadOfferAttachedDocumentWithVerification();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет, что список действий на странице 'Информация об организации' не пустой")
    public void supplierChecksThatTheActionsTableIsNotEmpty() {
        String stepName = "'Поставщик' проверяет, что список действий на странице 'Информация об организации' не пустой";
        this.logStepName(stepName);

        timer.start();

        organizationInfoPage.checkThatTheActionsTableIsNotEmpty();

        timer.printStepTotalTime(stepName);
    }

    @When("^'Поставщик' переходит на страницу: 'Поиск закупок'$")
    public void supplierGoesToSearchPurchasesPage() throws Throwable {
        String stepName = "'Поставщик' переходит на страницу: 'Поиск закупок'";
        this.logStepName(stepName);

        timer.start();

        searchPurchasePage.goToSearchPurchase();
        searchPurchasePage.checkPageUrl("Поиск закупок");
        searchPurchasePage.checkLoginLink();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' выбирает способ закупки: \"([^\"]*)\" в фильтре поиска$")
    public void supplierSelectsPurchaseTypeInFilter(String purchaseType) throws Throwable {
        String stepName = String.format("'Поставщик' выбирает способ закупки: [%s] в фильтре поиска", purchaseType);
        this.logStepName(stepName);

        timer.start();

        searchPurchasePage.selectPurchaseTypeInFilter(purchaseType);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' вводит значение: \"([^\"]*)\" в поле 'Начальная цена'$")
    public void supplierEntersStartPriceValueInFilter(String startPrice) throws Throwable {
        String stepName = String.format("'Поставщик' вводит значение: '%s' в поле 'Начальная цена'", startPrice);
        this.logStepName(stepName);

        timer.start();

        searchPurchasePage.enterStartPriceValueInFilter(startPrice);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' выбирает тип закупки: \"([^\"]*)\"$")
    public void supplierSelectsPurchaseTypeCheckBoxInFilter(String purchaseType) throws Throwable {
        String stepName = String.format("'Поставщик' выбирает тип закупки: '%s'", purchaseType);
        this.logStepName(stepName);

        timer.start();

        searchPurchasePage.selectPurchaseTypeCheckBox(purchaseType);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' выбирает состояние закупки: \"([^\"]*)\"$")
    public void supplierSelectsPurchaseStateTabInFilter(String purchaseState) throws Throwable {
        String stepName = String.format("'Поставщик' выбирает состояние закупки: '%s'", purchaseState);
        this.logStepName(stepName);

        timer.start();

        searchPurchasePage.selectPurchaseStateTabInFilter(purchaseState);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' нажимает кнопку 'Поиск'$")
    public void supplierClicksSearchButton() throws Throwable {
        String stepName = "'Поставщик' нажимает кнопку 'Поиск'";
        this.logStepName(stepName);

        timer.start();

        searchPurchasePage.clickSearchButton();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' проверяет размер лицензионного вознаграждения в карточке закупки$")
    public void supplierChecksCommissionSumInPurchaseCardViewPage() throws Throwable {
        String stepName = "'Поставщик' проверяет размер лицензионного вознаграждения в карточке закупки";
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.checkCommissionSumInPurchaseCardViewPage();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' сбрасывает все фильтры поиска на странице 'Поиск закупок' в исходное состояние$")
    public void supplierResetsPurchaseSearchFilters() throws Throwable {
        String stepName = "'Поставщик' сбрасывает все фильтры поиска на странице 'Поиск закупок' в исходное состояние";
        this.logStepName(stepName);

        timer.start();

        searchPurchasePage.resetPurchaseSearchFilters();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' проверяет таблицу с закупками$")
    public void supplierChecksTableWithPurchase() throws Throwable {
        String stepName = "'Поставщик' проверяет таблицу с закупками";
        this.logStepName(stepName);

        timer.start();

        searchPurchasePage.selectNumberRowInTable("100");
        searchPurchasePage.checkThatTheSearchResultsContainsAtLeastOneRecord();
        searchPurchasePage.checkNumberEisColumnInTable();
        searchPurchasePage.checkFiltersOfDate("Дата публикации извещения");
        searchPurchasePage.checkFiltersOfDate("Дата окончания срока подачи заявок");
        searchPurchasePage.checkFiltersOfDate("Дата начала торгов / переторжки");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' в первой строке нажимает на ссылку в колонке 'Номер закупки/лота'$")
    public void SupplierClicksOneRowNumberPurchaseLot() throws Throwable {
        String stepName = "'Поставщик' в первой строке нажимает на ссылку в колонке 'Номер закупки/лота'";
        this.logStepName(stepName);

        timer.start();

        searchPurchasePage.clickOnPurchaseOrLotNumberLinkInFirstRow();

        timer.printStepTotalTime(stepName);
    }

    @Then("^'Поставщик' проверяет возможность скачать файл со страницы 'Закупка'$")
    public void SupplierChecksDownloadFile() throws Throwable {
        String stepName = "'Поставщик' проверяет возможность скачать файл со страницы 'Закупка'";
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.checkFileDocumentColumn();

        timer.printStepTotalTime(stepName);
    }

    @Then("^'Поставщик' осуществляет проверки на странице 'Поиск закупок'$")
    public void SupplierChecksSearchPurchasesPage() throws Throwable {
        String stepName = "'Поставщик' осуществляет проверки на странице 'Поиск закупок'";
        this.logStepName(stepName);

        timer.start();

        // Устанавливаем режим отображения закупок 100 на одной странице и проверяем, что закупки есть вообще
        searchPurchasePage.selectNumberRowInTable("100");
        searchPurchasePage.checkThatTheSearchResultsContainsAtLeastOneRecord();

        // Получаем номер закупки из файла конфигурации как критерий для поиска
        String purchaseNumber = config.getConfigParameter("OnProdPurchaseNumberForCheckFilters");
        searchPurchasePage.setPurchaseNumberLotNumber(purchaseNumber);

        // Осуществляем поиск закупки по её номеру
        searchPurchasePage.clickButton("Поиск");
        searchPurchasePage.checkTheSamePurchaseNumbersInSearchResults();

        // Получаем название закупки из первой сверху закупки в результатах поиска как критерий для поиска
        String purchaseName = searchPurchasePage.
                getValueFromColumn("Наименование объекта закупки", 1);
        String purchaseNameLength60 = purchaseName.length() > 60 ? purchaseName.substring(0, 60) : purchaseName;

        // Сбрасываем все фильтры поиска
        searchPurchasePage.clickButton("Очистить");

        // Осуществляем поиск закупки по её наименованию
        System.out.printf(
                "[ИНФОРМАЦИЯ]: проверяем результаты поиска для закупки с номером [%s] по её наименованию [%s].%n",
                purchaseNumber, purchaseNameLength60);
        searchPurchasePage.setPurchaseName(purchaseNameLength60);
        searchPurchasePage.clickButton("Поиск");
        //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // searchPurchasePage.detectAndCloseServerErrorDialogWindow();
        //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        searchPurchasePage.checkThatTheSearchResultsContainsAtLeastOneRecord();

        // Сбрасываем все фильтры поиска
        searchPurchasePage.clickButton("Очистить");

        timer.printStepTotalTime(stepName);
    }

    @Then("^'Поставщик' осуществляет проверки на странице 'Мои заявки'$")
    public void SupplierChecksMyApplicationsPage() throws Throwable {
        String stepName = "'Поставщик' осуществляет проверки на странице 'Мои заявки'";
        this.logStepName(stepName);

        timer.start();

        supplierMyRequestPage.selectNumberRowInTable();
        supplierMyRequestPage.checkRowCountInSearchResults("all");
        supplierMyRequestPage.
                setTradeNumberLotNumber(config.getConfigParameter("OnProdPurchaseNumberForCheckFilters"));
        supplierMyRequestPage.clickButton("Поиск");
        supplierMyRequestPage.checkRowCountInSearchResults("one");
        supplierMyRequestPage.clickButton("Очистить");
        supplierMyRequestPage.setApplicationNumber(supplierMyRequestPage.
                getValueFromColumn("Номер заявки", 1));
        supplierMyRequestPage.clickButton("Поиск");
        supplierMyRequestPage.checkRowCountInSearchResults("one");
        supplierMyRequestPage.clickButton("Очистить");

        timer.printStepTotalTime(stepName);
    }

    @Then("^'Поставщик' осуществляет проверки на странице 'Счета компании'$")
    public void SupplierChecksCompanyAccountsPage() throws Throwable {
        String stepName = "'Поставщик' осуществляет проверки на странице 'Счета компании'";
        this.logStepName(stepName);

        timer.start();

        accountsCompanyPage.checkRowNumberInTable("all");
        accountsCompanyPage.
                clickOnAccountNumberLink(config.getConfigParameter("OpenPartSupplier1AccountNumber"));
        accountsCompanyPage.checkPageUrl("Просмотр счёта");

        timer.printStepTotalTime(stepName);
    }

    @Then("^'Поставщик' осуществляет проверки на странице 'Реестр транзакций счёта'$")
    public void SupplierChecksAccountTransactionsRegistryPage() throws Throwable {
        String stepName = "'Поставщик' осуществляет проверки на странице 'Реестр транзакций счёта'";
        this.logStepName(stepName);

        timer.start();

        accountRegisterTransactionsPage.checkRowCountInTable("all");

        timer.printStepTotalTime(stepName);
    }

    @Then("^'Поставщик' осуществляет проверки на странице 'Мои договоры'$")
    public void SupplierChecksMyContractsPage() throws Throwable {
        String stepName = "'Поставщик' осуществляет проверки на странице 'Мои договоры'";
        this.logStepName(stepName);

        timer.start();

        myContractsPage.selectNumberRowsInTable();
        myContractsPage.checkNumberOfRowsInTable("all");
        myContractsPage.setFieldOnMyContractsPage("Номер закупки/лота",
                config.getConfigParameter("OnProdPurchaseNumberForCheckFilters"));
        myContractsPage.clickButton("Поиск");
        myContractsPage.checkNumberOfRowsInTable("one");
        myContractsPage.clickButton("Очистить");
        myContractsPage.setFieldOnMyContractsPage("Номер договора",
                myContractsPage.getValueFromColumn("Номер договора", 1));
        myContractsPage.clickButton("Поиск");
        myContractsPage.checkNumberOfRowsInTable("one");
        myContractsPage.clickButton("Очистить");

        timer.printStepTotalTime(stepName);
    }

    @Then("^'Поставщик' осуществляет проверку 'Тип закупки' на странице 'Поиск закупок'$")
    public void supplierTypesPurchase() throws Throwable {
        String stepName = "'Поставщик' осуществляет проверку 'Тип закупки' на странице 'Поиск закупок'";
        this.logStepName(stepName);

        timer.start();

        searchPurchasePage.selectNumberRowInTable("100");
        searchPurchasePage.selectPurchaseTypeCheckBox("Коммерческая закупка");
        searchPurchasePage.clickButton("Поиск");
        searchPurchasePage.checkPurchaseMethodColumn("Предварительный отбор");
        searchPurchasePage.clickButton("Очистить");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' осуществляет поиск поданной заявки по номеру закупки$")
    public void supplierSearchesPurchaseRequestByPurchaseNumber() throws Throwable {
        String stepName = "'Поставщик' осуществляет поиск поданной заявки по номеру закупки";
        this.logStepName(stepName);

        timer.start();

        // Для отладки -------------------------------------------------------------------------------------------------
        // config.setParameter("PurchaseNumber", "1528");
        // Для отладки -------------------------------------------------------------------------------------------------
        supplierMyRequestPage.clickButton("Поиск"); // Для "прогрева" (костыль). Иногда поиск не срабатывает.
        supplierMyRequestPage.setTradeNumberLotNumber(config.getParameter("PurchaseNumber"));
        supplierMyRequestPage.setTradeName(config.getParameter("PurchaseName"));
        supplierMyRequestPage.clickButton("Поиск");
        supplierMyRequestPage.checkRowCountInSearchResults("one");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' осуществляет поиск трех поданных заявок по номеру закупки$")
    public void supplierSearchesForThreePurchaseRequestsByPurchaseNumber() throws Throwable {
        String stepName = "'Поставщик' осуществляет поиск трех поданных заявок по номеру закупки";
        this.logStepName(stepName);

        timer.start();

        // Для отладки -------------------------------------------------------------------------------------------------
        // config.setParameter("PurchaseNumber", "1528");
        // Для отладки -------------------------------------------------------------------------------------------------
        supplierMyRequestPage.clickButton("Поиск"); // Для "прогрева" (костыль). Иногда поиск не срабатывает.
        supplierMyRequestPage.setTradeNumberLotNumber(config.getParameter("PurchaseNumber"));
        supplierMyRequestPage.clickButton("Поиск");
        supplierMyRequestPage.checkRowCountInSearchResults("three");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' сохраняет номер поданной заявки в параметрах теста$")
    public void supplierSavesPurchaseRequestNumberInTestParameters() {
        String stepName = "'Поставщик' сохраняет номер поданной заявки в параметрах теста";
        this.logStepName(stepName);

        timer.start();

        String purchaseRequestNumber = supplierMyRequestPage.getValueFromColumn("Номер заявки", 1);
        config.setParameter("SupplierRequestNumber", purchaseRequestNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' сохраняет номер поданной заявки для лота \"([^\"]*)\" в параметрах теста$")
    public void supplierSavesPurchaseRequestNumberForLotByNumberInTestParameters(String lotNumber) {
        String stepName =
                String.format("'Поставщик' сохраняет номер поданной заявки для лота '%s' в параметрах теста", lotNumber);
        this.logStepName(stepName);

        timer.start();

        String purchaseRequestNumber = supplierMyRequestPage.getRequestNumberUsingLotNumber(lotNumber);
        config.setParameter("SupplierRequestNumber", purchaseRequestNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' открывает извещение о закупке используя прямую ссылку из файла конфигурации$")
    public void supplierOpensNoticeUsingDirectLinkFromConfigFile() throws Throwable {
        String stepName = "Поставщик' открывает извещение о закупке используя прямую ссылку из файла конфигурации";
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.goToUrl(config.getConfigParameter("OnProdDirectLinkToNoticeWithRevisionsHistory"),
                "Закупка №", 15, 15);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' проверяет наличие ревизий в извещении о закупке$")
    public void supplierChecksRevisionsPresenceInNoticeForm() {
        String stepName = "'Поставщик' проверяет наличие ревизий в извещении о закупке";
        this.logStepName(stepName);

        timer.start();

        config.setParameter("PurchaseNumberForTradeSearch", supplierPurchasePage.getPurchaseNumberValue());
        System.out.printf("[ИНФОРМАЦИЯ]: получен номер закупки: [%s].%n",
                config.getParameter("PurchaseNumberForTradeSearch"));
        supplierPurchasePage.checkPurchaseNumberValue();
        supplierPurchasePage.checkRevisionsPresenceInNoticeForm();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' проверяет каждую ревизию в извещении о закупке$")
    public void supplierChecksEachRevisionInNoticeForm() throws Throwable {
        String stepName = "'Поставщик' проверяет каждую ревизию в извещении о закупке";
        this.logStepName(stepName);

        timer.start();

        supplierPurchasePage.checkEachRevisionInNoticeForm();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет уведомление {string}")
    public void supplierChecksMessagePresence(String subjectWithoutPurchaseNumber) throws Throwable {
        String subject = String.format(subjectWithoutPurchaseNumber, config.getParameter("PurchaseNumber"));
        String stepName = String.format("'Поставщик' проверяет уведомление '%s'", subject);
        this.logStepName(stepName);

        timer.start();

        supplierMessagesPage.
                goToMessages("30").
                setDateFrom(timer.getYesterdayDateTime("dd.MM.yyyy")).
                setDateTo(timer.getCurrentDateTime("dd.MM.yyyy")).
                clickOnSearchButton().
                checkSubject(subject).
                clickOnClearButton().
                checkFindFieldIsEmpty();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' сохраняет в параметрах теста количество непрочитанных входящих уведомлений")
    public void supplierSavesInTestParametersTheNumberOfUnreadMessages() throws Throwable {
        String stepName = "'Поставщик' сохраняет в параметрах теста количество непрочитанных входящих уведомлений";
        this.logStepName(stepName);

        timer.start();

        supplierMessagesPage.goToMessages("30");
        config.setParameter("UnreadMessagesCounter", supplierMessagesPage.getUnreadMessagesCounter());

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' выполняет ручную отправку уведомления из личного кабинета для организации с ИНН {string}")
    public void supplierPerformsManualSendAnOutgoingMessageForOrganizationWithSpecificInn(String inn) throws Throwable {
        String stepName = String.format(
                "'Поставщик' выполняет ручную отправку уведомления из личного кабинета для организации с ИНН '%s'", inn);
        this.logStepName(stepName);

        timer.start();

        String subject = String.format("Тема сообщения от %s.", timer.getCurrentDateTime("dd.MM.yyyy HH:mm:SS"));

        supplierMessagesPage.
                goToMessages("30").
                clickOnWriteAMessageButton();
        supplierCreateMessagePage.
                ifPageOpened().
                clickOnButtonByName("Кнопка Выбрать");
        supplierRecipientSelectionDialog.
                ifDialogOpened().
                setField("Поле ИНН", inn).
                clickOnButtonByName("Кнопка Найти").
                checkNumberOfRowsInSearchResults(1).
                selectRecipientByRowNumber(1).
                clickOnButtonByName("Кнопка Выбрать").
                waitForDialogCloses();
        supplierCreateMessagePage.
                ifPageOpened().
                verifyFieldIsNotEmpty("Поле Кому").
                verifyFieldIsNotEmpty("Поле От").
                setField("Поле Тема", subject).
                setField("Поле Текст сообщения",
                        StringUtils.repeat("Текст сообщения на нескольких строках. ", 50)).
                uploadFileIntoDocuments(config.getAbsolutePathToFoundationDoc(),
                        config.getConfigParameter("FoundationDoc")).
                clickOnButtonByName("Кнопка Отправить");
        supplierMessagesPage.checkPageUrl("МОЯ ОРГАНИЗАЦИЯ/Уведомления");
        config.setParameter("SupplierOutgoingMessageSubject", subject);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет, что количество непрочитанных входящих уведомлений увеличилось")
    public void supplierChecksThatTheNumberOfUnreadMessagesIncremented() throws Throwable {
        String stepName = "'Поставщик' проверяет, что количество непрочитанных входящих уведомлений увеличилось";
        this.logStepName(stepName);

        timer.start();

        supplierMessagesPage.goToMessages("30");

        long unreadMessagesCounterOld = Long.parseLong(config.getParameter("UnreadMessagesCounter"));
        long unreadMessagesCounterNew = Long.parseLong(supplierMessagesPage.getUnreadMessagesCounter());
        System.out.printf(
                "[ИНФОРМАЦИЯ]: проверка количества непрочитанных входящих уведомлений - было: [%d], стало: [%d].%n",
                unreadMessagesCounterOld, unreadMessagesCounterNew);

        Assert.assertTrue("[ОШИБКА]: количество непрочитанных входящих уведомлений не увеличилось",
                unreadMessagesCounterNew > unreadMessagesCounterOld);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет наличие входящего уведомления по уникальному значению темы, сохраненной в параметрах теста")
    public void supplierChecksIncomingMessagePresenceUsingMessageSubjectStoredInTestParameters() throws Throwable {
        String stepName = "'Поставщик' проверяет наличие входящего уведомления по уникальному значению темы, " +
                "сохраненной в параметрах теста";
        this.logStepName(stepName);

        timer.start();

        String subject = config.getParameter("SupplierOutgoingMessageSubject");

        supplierMessagesPage.
                goToMessages("30").
                setDateFrom(timer.getYesterdayDateTime("dd.MM.yyyy")).
                setDateTo(timer.getCurrentDateTime("dd.MM.yyyy")).
                setSearchForText(subject).
                clickOnSearchButton().
                checkSubject(subject).
                clickOnMessageInSearchResultsBySubject(subject).
                checkPageUrl("МОЯ ОРГАНИЗАЦИЯ/Уведомления/Входящее сообщение №");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' переходит на страницу 'Реестр транзакций счёта'$")
    public void supplierGoesToRegistryOfTransactionsPage() throws Throwable {
        String stepName = "'Поставщик' переходит на страницу 'Реестр транзакций счёта'";
        this.logStepName(stepName);

        timer.start();

        commonSupplierPage.clickTopMenuItem("ФИНАНСЫ И ДОКУМЕНТЫ");
        commonSupplierPage.clickLinkUnderMenu("Счета и транзакции");
        accountsCompanyPage.clickOnAccountNumberLink("-223");
        accountViewPage.clickTransactionsButton();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' переходит на страницу 'Мои договоры'$")
    public void supplierGoesToMyContractsPage() throws Throwable {
        String stepName = "'Поставщик' переходит на страницу 'Мои договоры'";
        this.logStepName(stepName);

        timer.start();

        commonSupplierPage.clickTopMenuItem("ФИНАНСЫ И ДОКУМЕНТЫ");
        commonSupplierPage.clickLinkUnderMenu("Мои договоры");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' переходит по ссылке № \"([^\"]*)\" в столбце 'Номер заявки'$")
    public void supplierGoesToLinkInRequestNumberColumn(String linkNumber) throws Throwable {
        String stepName = String.format("'Поставщик' переходит по ссылке № '%s' в столбце 'Номер заявки'", linkNumber);
        this.logStepName(stepName);

        timer.start();

        supplierMyRequestPage.goToLinkInRequestNumberColumnByNumber(linkNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' переходит по ссылке 'Есть' № \"([^\"]*)\" в столбце 'Заявки'$")
    public void supplierGoesToLinkInRequestsColumn(String linkNumber) throws Throwable {
        String stepName = String.format("'Поставщик' переходит по ссылке 'Есть' № '%s' в столбце 'Заявки'", linkNumber);
        this.logStepName(stepName);

        timer.start();

        searchPurchasePage.goToLinkInRequestsColumnByNumber(linkNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' проверяет наличие \"([^\"]*)\" ценовых предложений в диалоговом окне 'Ценовые предложения'$")
    public void supplierChecksNumberOfRequestsInDialogWindow(String requests) throws Throwable {
        String stepName = String.format(
                "'Поставщик' проверяет наличие '%s' ценовых предложений в диалоговом окне 'Ценовые предложения'",
                requests);
        this.logStepName(stepName);

        timer.start();

        PriceOffersDialog priceOffersDialog = new PriceOffersDialog(driver);
        priceOffersDialog.ifSupplierPriceOffersDialogWindowOpened().
                checkSupplierPriceOffersDialogWindowIsNotEmpty().
                checkSupplierPriceOffersDialogWindowRecordsCount(requests);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' проверяет наличие строки \"([^\"]*)\" с ценовым предложением \"([^\"]*)\"$")
    public void supplierChecksOfferPresenceInDialogWindow(String supplierNameOfferNumber, String priceOffer) {
        String stepName = String.format("'Поставщик' проверяет наличие строки '%s' с ценовым предложением '%s'",
                supplierNameOfferNumber, priceOffer);
        this.logStepName(stepName);

        timer.start();

        PriceOffersDialog priceOffersDialog = new PriceOffersDialog(driver);
        priceOffersDialog.checkPriceOfferInSupplierPriceOffersDialogWindow(supplierNameOfferNumber, priceOffer);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Поставщик' закрывает диалоговое окно 'Ценовые предложения'$")
    public void supplierClosesPriceOffersInfoDialogWindow() throws Throwable {
        String stepName = "'Поставщик' закрывает диалоговое окно 'Ценовые предложения'";
        this.logStepName(stepName);

        timer.start();

        PriceOffersDialog priceOffersDialog = new PriceOffersDialog(driver);
        priceOffersDialog.closeSupplierPriceOffersDialogWindow();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' переходит к разделу {string} на странице 'Редактирование информации'")
    public void supplierGoesToBlockByBlockNameOnEditInformationPage(String blockName) throws Throwable {
        String stepName = String.format(
                "'Поставщик' переходит к разделу '%s' на странице 'Редактирование информации'", blockName);
        this.logStepName(stepName);

        timer.start();

        editInformationPage.gotoBlock(blockName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет доступность кнопки {string} на странице 'Редактирование информации'")
    public void supplierChecksVisibilityAndActivityOfButtonByButtonNameOnEditInformationPage(String buttonName) {
        String stepName = String.format(
                "'Поставщик' проверяет доступность кнопки '%s' на странице 'Редактирование информации'",
                buttonName);
        this.logStepName(stepName);

        timer.start();

        editInformationPage.checkButtonExistAndClickable(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет значения счетчиков на заголовке вкладки {string} страницы 'Редактирование информации'")
    public void supplierChecksNonZeroApplicantDocumentsCounterValuesOnEditInformationPage(String tabName) throws Throwable {
        String stepName = String.format(
                "Поставщик' проверяет значения счетчиков на заголовке вкладки '%s' страницы 'Редактирование информации'"
                , tabName);
        this.logStepName(stepName);

        timer.start();

        editInformationPage.switchToTab(tabName);
        editInformationPage.checkCountersInTabTitle(tabName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' заполняет поле {string} на странице 'Мои Торги Переторжки'")
    public void supplierSetsPurchaseNumberFieldOnMyTendersPage(String fieldName) throws Throwable {
        String stepName = String.format("'Поставщик' заполняет поле '%s' на странице 'Мои Торги Переторжки'", fieldName);
        this.logStepName(stepName);

        timer.start();

        myTendersPage.setFieldClearClickAndSendKeys(fieldName, config.getParameter("PurchaseNumber"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' устанавливает переключатель {string} в положение {string} на странице 'Мои Торги Переторжки'")
    public void supplierSetsRadioButtonToOnMyTendersPage(String radioButtonName, String radioButtonPosition)
            throws Throwable {
        String stepName = String.format(
                "'Поставщик' устанавливает переключатель '%s' в положение '%s' на странице 'Мои Торги Переторжки'",
                radioButtonName, radioButtonPosition);
        this.logStepName(stepName);

        timer.start();

        myTendersPage.selectRadioButton(radioButtonPosition);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' нажимает на кнопку {string} на странице 'Мои Торги Переторжки'")
    public void supplierClicksOnButtonByNameOnMyTendersPage(String buttonName) throws Throwable {
        String stepName = String.format("'Поставщик' нажимает на кнопку '%s' на странице 'Мои Торги Переторжки'",
                buttonName);
        this.logStepName(stepName);

        timer.start();

        myTendersPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет количество лотов {int} в закупке на странице 'Мои Торги Переторжки'")
    public void supplierChecksNumberOfLotsInPurchaseOnMyTendersPage(int expectedLots) {
        String stepName = String.format(
                "'Поставщик' проверяет количество лотов '%d' в закупке на странице 'Мои Торги Переторжки'",
                expectedLots);
        this.logStepName(stepName);

        timer.start();

        myTendersPage.checkNumberOfLotsInPurchase(expectedLots);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' открывает извещение по которому была назначена переторжка на странице 'Мои Торги Переторжки'")
    public void supplierClicksOnTradeNumberLinkOnMyTendersPage() throws Throwable {
        String stepName = "'Поставщик' открывает извещение по которому была назначена переторжка на странице " +
                "'Мои Торги Переторжки'";
        this.logStepName(stepName);

        timer.start();

        myTendersPage.clickOnTradeNumberLinkInRowByNumber(1);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' закрывает все модальные диалоговые окна, которые открыты в текущий момент времени")
    public void supplierClosesAllModalDialogWindowsWhichAreOpenedAtTheMoment() {
        String stepName =
                "'Поставщик' закрывает все модальные диалоговые окна, которые открыты в текущий момент времени";
        this.logStepName(stepName);

        timer.start();

        commonSupplierPage.checkAdvertisingDialogWindow();

        timer.printStepTotalTime(stepName);
    }
}
