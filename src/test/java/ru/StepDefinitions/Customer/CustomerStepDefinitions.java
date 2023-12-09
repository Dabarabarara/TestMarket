package ru.StepDefinitions.Customer;

import Helpers.Dictionary;
import LogicLayer.CertificateSelectors.SupplierCertificateSelector;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import ru.PageObjects.Accreditation.DataProcessingAgreementPage;
import ru.PageObjects.Admin.EmployeeViewPage;
import ru.PageObjects.Authorization.CustomerLogInPage;
import ru.PageObjects.CommonDialogs.*;
import ru.PageObjects.Customer.*;
import ru.PageObjects.Customer.Contract.ContractsExecutionPublishedDialog;
import ru.PageObjects.Customer.Messages.CustomerCreateMessagePage;
import ru.PageObjects.Customer.Messages.CustomerMessagesPage;
import ru.PageObjects.Customer.Messages.CustomerRecipientSelectionDialog;
import ru.PageObjects.Customer.Messages.CustomerViewMessagePage;
import ru.PageObjects.Customer.MyOrganization.CommonSettingPage;
import ru.PageObjects.Customer.MyOrganization.EisIntegrationSettingPage;
import ru.PageObjects.Customer.MyOrganization.EmployeeChangeRequestPage;
import ru.PageObjects.Customer.MyOrganization.OosMessageQueuePage;
import ru.PageObjects.Customer.Notice.PublishedDialog;
import ru.PageObjects.Customer.Notice.TradeCancelDialog;
import ru.PageObjects.Customer.Notice.ViewNoticePage;
import ru.PageObjects.Customer.Reports.RegistryOfReportsPage;
import ru.PageObjects.Supplier.EmployeeRequestPage;
import ru.PageObjects.Supplier.MyOrganization.EmployeeListPage;
import ru.PageObjects.Supplier.MyOrganization.OrganizationInfoPage;
import ru.PageObjects.Supplier.MyOrganization.RegisterOfQualifiedContractorsPage;
import ru.PageObjects.Supplier.MyOrganization.UserRolesPage;
import ru.StepDefinitions.AbstractStepDefinitions;

import java.util.Date;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Класс описывающий общие шаги работы Заказчика.
 * Created by Evgeniy Glushko on 24.03.2016.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class CustomerStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final SavedDialog savedDialog = new SavedDialog(driver);
    private final WarningDialog warningDialog = new WarningDialog(driver);
    private final MessageDialog messageDialog = new MessageDialog(driver);
    private final CompletedDialog completedDialog = new CompletedDialog(driver);
    private final CustomerRecipientSelectionDialog customerRecipientSelectionDialog =
            new CustomerRecipientSelectionDialog(driver);
    private final TradeCancelDialog tradeCancelDialog = new TradeCancelDialog(driver);
    private final InformationDialog informationDialog = new InformationDialog(driver);
    private final ContractsExecutionPublishedDialog contractsExecutionPublishedDialog =
            new ContractsExecutionPublishedDialog(driver);
    private final RejectApprovalRequestDialog rejectApprovalRequestDialog = new RejectApprovalRequestDialog(driver);
    private final DataProcessingAgreementPage dataProcessingAgreementPage = new DataProcessingAgreementPage(driver);
    private final EisIntegrationSettingPage eisIntegrationSettingPage = new EisIntegrationSettingPage(driver);
    private final CustomerCreateMessagePage customerCreateMessagePage = new CustomerCreateMessagePage(driver);
    private final EmployeeChangeRequestPage employeeChangeRequestPage = new EmployeeChangeRequestPage(driver);
    private final CustomerViewMessagePage customerViewMessagePage = new CustomerViewMessagePage(driver);
    private final CustomerMyPurchasesPage customerMyPurchasesPage = new CustomerMyPurchasesPage(driver);
    private final RegistryOfReportsPage registryOfReportsPage = new RegistryOfReportsPage(driver);
    private final CustomerMessagesPage customerMessagesPage = new CustomerMessagesPage(driver);
    private final CustomerMainMenuPage customerMainMenuPage = new CustomerMainMenuPage(driver);
    private final OrganizationInfoPage organizationInfoPage = new OrganizationInfoPage(driver);
    private final EmployeeRequestPage employeeRequestPage = new EmployeeRequestPage(driver);
    private final OosMessageQueuePage oosMessageQueuePage = new OosMessageQueuePage(driver);
    private final RegisterOfQualifiedContractorsPage registerOfQualifiedContractorsPage =
            new RegisterOfQualifiedContractorsPage(driver);
    private final CommonCustomerPage commonCustomerPage = new CommonCustomerPage(driver);
    private final CustomerLogInPage customerLogInPage = new CustomerLogInPage(driver);
    private final CommonSettingPage commonSettingPage = new CommonSettingPage(driver);
    private final EmployeeListPage employeeListPage = new EmployeeListPage(driver);
    private final EmployeeViewPage employeeViewPage = new EmployeeViewPage(driver);
    private final ViewNoticePage viewNoticePage = new ViewNoticePage(driver);
    private final UserRolesPage userRolesPage = new UserRolesPage(driver);

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    @When("^'Заказчик' открывает страницу 'Вход в личный кабинет'$")
    public void customerOpensLoginToPersonalCabinetPage() {
        String stepName = "'Заказчик' открывает страницу 'Вход в личный кабинет'";
        this.logStepName(stepName);

        timer.start();

        customerLogInPage.goToUserLoginPage();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' авторизуется в системе и попадает на страницу 'Мои закупки'$")
    public void customerLogsInAndGetsMyPurchasesPage() throws Throwable {
        String stepName = "'Заказчик' авторизуется в системе и попадает на страницу 'Мои закупки'";
        this.logStepName(stepName);

        timer.start();

        customerLogInPage.
                setLogin(config.getConfigParameter("OpenPartCustomer1Login")).
                setPassword(config.getConfigParameter("OpenPartCustomer1Password")).
                clickOnLoginButton();
        customerMyPurchasesPage.ifPageOpened().switchViewToCards();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' производит авторизацию по логину и паролю после аккредитации")
    public void customerLogsIn() throws Throwable {
        String stepName = "'Заказчик' производит авторизацию по логину и паролю после аккредитации";
        this.logStepName(stepName);

        timer.start();

        customerLogInPage.
                setLogin(config.getParameter("AccreditationUserLogin")).
                setPassword(config.getConfigParameter("UserPass")).
                clickOnLoginButton();

        timer.printStepTotalTime(stepName);
    }

    @When("'Заказчик' {string} заходит в личный кабинет Заказчика автоматически сгенерированным пользователем")
    public void userLogsToSystemUsingGeneratedCertificate(String userType) throws Throwable {
        String stepName = String.format(
                "'Заказчик' '%s' заходит в личный кабинет Заказчика автоматически сгенерированным пользователем",
                userType);
        this.logStepName(stepName);

        timer.start();

        Dictionary userTypes = new Dictionary();
        userTypes.add("Сгенерированный пользователь Администратор", "OrganizationAdministratorName");
        userTypes.add("Сгенерированный работник", "EmployeeUserName");

        String userName = config.getParameter(userTypes.find(userType));
        config.setParameter("CertName", userName);

        customerLogInPage.goToUserLoginPage().loginAsCustomer(userName);

        //==============================================================================================================
        config.setMainWindowHandle(driver.getWindowHandle());
        //==============================================================================================================

        System.out.printf("[ИНФОРМАЦИЯ]: произведено получение размера окна обозревателя: %s.%n",
                getWebDriver().manage().window().getSize());

        timer.printStepTotalTime(stepName);
    }

    @When("'Заказчик' заходит в личный кабинет пользователем {string}")
    public void customerOpensLoginToPersonalCabinetPage(String customerName) throws Throwable {
        String stepName = String.format("'Заказчик' заходит в личный кабинет пользователем '%s'", customerName);
        this.logStepName(stepName);

        timer.start();

        Dictionary certificateNames = new Dictionary();
        certificateNames.add("Заказчик Сгенерированный работник", config.getParameter("EmployeeUserName"));

        config.setParameter("CertName", config.getParameter("EmployeeFullUserName"));

        customerLogInPage.clickOnCertificateName(certificateNames.find(customerName)).clickOnLoginButton();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' переходит на страницу 'Мои закупки'$")
    public void customerGoesToMyPurchasesPage() throws Throwable {
        String stepName = "'Заказчик' переходит на страницу 'Мои закупки'";
        this.logStepName(stepName);

        timer.start();

        customerMainMenuPage.clickLinkUnderMenu("ЗАКУПКИ/Заказчикам", "Exit","Мои закупки");
        customerMyPurchasesPage.switchViewToCards().selectSortingMode("по номеру извещения");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' переходит на страницу 'Мои закупки' и ищет опубликованное извещение$")
    public void customerSearchesForPublishedNotice() throws Throwable {
        String stepName = "'Заказчик' переходит на страницу 'Мои закупки' и ищет опубликованное извещение";
        this.logStepName(stepName);

        timer.start();

        // Для отладки -------------------------------------------------------------------------------------------------
        // config.setParameter("PurchaseNumber", "13915");
        // Для отладки -------------------------------------------------------------------------------------------------

        customerMainMenuPage.clickLinkUnderMenu("ЗАКУПКИ/Заказчикам", "Exit","Мои закупки");
        customerMyPurchasesPage.
                switchViewToCards().
                selectSortingMode("по номеру извещения").
                setPurchaseNumber(config.getParameter("PurchaseNumber")).
                clickFindButton();
        customerMyPurchasesPage.waitForNoticeNumberWithLotNumber(config.getParameter("PurchaseNumber") + "/1");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет поле 'Тип отчета' на странице 'Реестр отчетов'")
    public void customerChecksReportTypeFieldInReportRegistryPage() throws Throwable {
        String stepName = "'Заказчик' проверяет поле 'Тип отчета' на странице 'Реестр отчетов'";
        this.logStepName(stepName);

        timer.start();

        registryOfReportsPage.
                checkPageIsOpened().
                checkDisableAttributeIsSetToTrueInReportTypeField().
                verifyFieldContentOrEmptyField("Тип отчёта", "Общий отчет (данные с РТС-Тендер)");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет данные аккредитации")
    public void supplierChecksAccreditation() {
        String stepName = "'Заказчик' проверяет данные аккредитации";
        this.logStepName(stepName);

        timer.start();

        organizationInfoPage.checkOrganizationNameField();
        organizationInfoPage.checkOrganizationAccreditationState();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' переходит на страницу \"([^\"]*)\"$")
    public void customerGoesToPageByName(String linkName) throws Throwable {
        String stepName = String.format("'Заказчик' переходит на страницу '%s'", linkName);
        this.logStepName(stepName);

        timer.start();

        customerMainMenuPage.clickLinkUnderMenu(linkName, linkName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит на страницу {string} с именем URL для проверки {string}")
    public void customerGoesToPageByNameWithURLNameToCheck(String linkName, String urlName) throws Throwable {
        String stepName = String.format(
                "'Заказчик' переходит на страницу '%s' с именем URL для проверки '%s'", linkName, urlName);
        this.logStepName(stepName);

        timer.start();

        customerMainMenuPage.clickLinkUnderMenu(linkName, urlName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' переходит по ссылке \"([^\"]*)\" для лота \"([^\"]*)\"$")
    public void customerGoesToLinkForLotWithNumber(String lotStatus, String number) throws Throwable {
        String stepName = String.format("'Заказчик' переходит по ссылке '%s' для лота '%s'", lotStatus, number);
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.clickOnLotStatusTriangleByNumber(lotStatus, number);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет фильтры на странице 'Мои закупки'$")
    public void customerChecksPageFiltersInPurchasePage() throws Throwable {
        String stepName = "'Заказчик' проверяет фильтры на странице 'Мои закупки'";
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.checkPageFilters();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' сбрасывает все установленные ранее фильтры на странице 'Мои закупки'$")
    public void customerClearsAllPageFiltersInPurchasePage() throws Throwable {
        String stepName = "'Заказчик' сбрасывает все установленные ранее фильтры на странице 'Мои закупки'";
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.clickClearFilterButton();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' переходит на вкладку со статусом закупки \"([^\"]*)\"$")
    public void customerGoesToStatusTabByTabName(String tabName) throws Throwable {
        String stepName = String.format("'Заказчик' переходит на вкладку со статусом закупки '%s'", tabName);
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.goToStatusTabByTabName(tabName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' получает список закупок на текущей странице и пытается их удалить$")
    public void customerGetsCollectionOfPurchaseNumbersAndTryToDeleteThemAll() throws Throwable {
        String stepName = "'Заказчик' получает список закупок на текущей странице и пытается их удалить";
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.getCollectionOfPurchaseNumbersAndTryToDeleteThemAll();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' упорядочивает закупки по номеру извещения$")
    public void customerSortsPurchasesByNoticeNumber() throws Throwable {
        String stepName = "'Заказчик' упорядочивает закупки по номеру извещения";
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.selectSortingMode("по номеру извещения");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' переключает режим отображения закупок на 'карточки закупок'$")
    public void customerSwitchesViewToCards() throws Throwable {
        String stepName = "'Заказчик' переключает режим отображения закупок на 'карточки закупок'";
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.switchViewToCards();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' выбирает закупки в статусе 'Формирование'$")
    public void customerFiltersPurchasesInFormingStatus() throws Throwable {
        String stepName = "'Заказчик' выбирает закупки в статусе 'Формирование'";
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.goToStatusTabByTabName("Формирование");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' ищет закупку по номеру извещения$")
    public void customerSearchesPurchaseByNoticeNumber() throws Throwable {
        String stepName = "'Заказчик' ищет закупку по номеру извещения";
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.setPurchaseNumber(config.getParameter("PurchaseNumber"));
        customerMyPurchasesPage.clickFindButton();
        customerMyPurchasesPage.waitForNoticeNumberWithLotNumber(config.getParameter("PurchaseNumber") + "/1");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' открывает закупку по номеру извещения$")
    public void customerOpensPurchaseByNoticeNumber() throws Throwable {
        String stepName = "'Заказчик' открывает закупку по номеру извещения";
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.clickOnPurchaseNumberLinkToViewMode();
        customerMyPurchasesPage.checkPageUrl("Формирование черновика извещения конкурса");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' находит секцию 'Документы' и проверяет наличие ссылки для скачивания файла на странице 'Просмотр извещения XXXX'$")
    public void customerFindsDocumentsPartitionAndChecksLinkToDownloadFile() {
        String stepName = "'Заказчик' находит секцию 'Документы' и проверяет наличие ссылки " +
                "для скачивания файла на странице 'Просмотр извещения XXXX'";
        this.logStepName(stepName);

        timer.start();

        viewNoticePage.scrollToDocumentsPartition();
        viewNoticePage.checkThatFirstAttachedDocumentLinkExists();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на ссылку и скачивает файл на странице 'Просмотр извещения XXXX'$")
    public void customerClicksFileDownloadLinkAndChecksThatFileLoadedSuccessfully() throws Throwable {
        String stepName = "'Заказчик' нажимает на ссылку и скачивает файл на странице 'Просмотр извещения XXXX'";
        this.logStepName(stepName);

        timer.start();

        viewNoticePage.downloadFirstAttachedDocumentWithVerification();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет доступность кнопки {string} на странице 'Просмотр извещения XXXX'")
    public void customerChecksThatTheButtonByNameExistAndClickable(String buttonName) {
        String stepName = String.format(
                "'Заказчик' проверяет доступность кнопки '%s' на странице 'Просмотр извещения XXXX'",
                buttonName);
        this.logStepName(stepName);

        timer.start();

        viewNoticePage.checkButtonExistAndClickable(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} для досрочного заершения лота на странице 'Просмотр извещения XXXX'")
    public void customerClicksOnButtonByNameForEarlyFinishOfTheLot(String buttonName) throws Exception {
        String stepName = String.format(
                "'Заказчик' проверяет доступность кнопки '%s' на странице 'Просмотр извещения XXXX'",
                buttonName);
        this.logStepName(stepName);

        timer.start();

        viewNoticePage.clickOnButton(buttonName);

        // Клик по кнопке [Да] в диалоговом окне [Предупреждение]
        WarningDialog warningDialog = new WarningDialog(driver);
        warningDialog.clickOnOkButton();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет наличие вкладки 'Расширенные настройки ЕИС'$")
    public void customerChecksExtendedEisSettingsTabAbsence() {
        String stepName = "'Заказчик' проверяет наличие вкладки 'Расширенные настройки ЕИС'";
        this.logStepName(stepName);

        timer.start();

        eisIntegrationSettingPage.openPartTabsVisibilityValidation();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'$")
    public void customerFindsPurchaseOnMyPurchasesPage() throws Throwable {
        String stepName = "'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'";
        this.logStepName(stepName);

        timer.start();

        // Для отладки -------------------------------------------------------------------------------------------------
        // config.setParameter("PurchaseNumber", "44468");
        // Для отладки -------------------------------------------------------------------------------------------------

        customerMyPurchasesPage.setPurchaseNumber(config.getParameter("PurchaseNumber"));
        customerMyPurchasesPage.clickFindButton();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' выполняет поиск закупки № \"([^\"]*)\"$")
    public void customerFindsPurchaseOnMyPurchasesPageByNumber(String purchaseNumber) throws Throwable {
        String stepName = String.format("'Заказчик' выполняет поиск закупки № [%s]", purchaseNumber);
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.setPurchaseNumber(purchaseNumber);
        customerMyPurchasesPage.clickFindButton();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' переходит по ссылке 'Заявок' для лота № \"([^\"]*)\" в карточке закупки$")
    public void customerClicksOnApplicationsLinkUsingLotNumber(String lotNumber) throws Throwable {
        String stepName = String.format(
                "'Заказчик' переходит по ссылке 'Заявок' для лота № '%s' в карточке закупки", lotNumber);
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.clickOnApplicationsLinkUsingLotNumber(lotNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска$")
    public void customerGoesToViewNotice() throws Throwable {
        String stepName = "'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска";
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.clickOnPurchaseNumberLinkToViewMode();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' открывает черновик извещения по ссылке с номером извещения в результатах поиска$")
    public void customerGoesToEditNoticeDraft() throws Throwable {
        String stepName = "'Заказчик' открывает черновик извещения по ссылке с номером извещения в результатах поиска";
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.waitLoadingImage();
        customerMyPurchasesPage.clickOnPurchaseNumberLinkToEditMode();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет, что извещение о закупке с указанным номером не найдено на площадке$")
    public void customerChecksThatPurchaseNoticeNotFound() {
        String stepName = "'Заказчик' проверяет, что извещение о закупке с указанным номером не найдено на площадке";
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.checkThatPurchaseNoticeNotFound();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет возможность скачать прикрепленные к извещению документы \"([^\"]*)\" по ссылкам$")
    public void customerChecksPossibilityToDownloadAttachedToNoticeDocumentsUsingLinks(String quantity) throws Throwable {
        String stepName = String.format(
                "'Заказчик' проверяет возможность скачать прикрепленные к извещению документы '%s' по ссылкам",
                quantity);
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.checkPossibilityToDownloadAttachedToNoticeDocumentsUsingLinks(Integer.parseInt(quantity));

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет статус закупки \"([^\"]*)\" и статус лота \"([^\"]*)\"$")
    public void customerChecksPurchaseStatusAndLotStatus(String purchaseStatus, String lotStatus) throws Throwable {
        String stepName = String.format("'Заказчик' проверяет статус закупки: '%s' и статус лота: '%s'",
                purchaseStatus, lotStatus);
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.checkPurchaseStatus(purchaseStatus);
        customerMyPurchasesPage.goBackToPreviousPage();
        customerMyPurchasesPage.checkLotStatus(lotStatus);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет статус закупки \"([^\"]*)\" и статус лота \"([^\"]*)\" для \"([^\"]*)\" лотов$")
    public void customerChecksPurchaseStatusAndLotStatuses(String pStatus, String lStatus, String lots) throws Throwable {
        String stepName = String.format("'Заказчик' проверяет статус закупки: '%s' и статус лота: '%s' для '%s' лотов",
                pStatus, lStatus, lots);
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.checkPurchaseStatus(pStatus);
        customerMyPurchasesPage.goBackToPreviousPage();

        // Проверяем статус для каждого из лотов закупки
        int number = Integer.parseInt(lots);
        for (int i = 1; i <= number; i++)
            customerMyPurchasesPage.checkLotStatus(lStatus, Integer.toString(i));

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет статус процедуры \"([^\"]*)\" и статус предмета предварительного отбора \"([^\"]*)\" для \"([^\"]*)\" предметов$")
    public void customerChecksProcedureStatusAndPreselectionObjectStatuses(String pStatus, String oStatus, String preselectionObject) throws Throwable {
        String stepName = String.format("'Заказчик' проверяет статус процедуры: '%s' " +
                        "и статус предмета предварительного отбора: '%s' для '%s' предметов",
                pStatus, oStatus, preselectionObject);
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.checkProcedureStatus(pStatus);
        customerMyPurchasesPage.goBackToPreviousPage();

        // Проверяем статус для каждого из предметов предварительного отбора
        int number = Integer.parseInt(preselectionObject);
        for (int i = 1; i <= number; i++)
            customerMyPurchasesPage.checkPreselectionObjectStatus(oStatus, Integer.toString(i));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет статус предмета предварительного отбора {string} для {string} предмета на странице 'Мои закупки'")
    public void customerChecksPreselectionObjectStatuses(String oStatus, String preselectionObjectNumber) throws Throwable {
        String stepName = String.format("'Заказчик' проверяет статус предмета предварительного отбора '%s' для '%s' " +
                "предмета на странице 'Мои закупки'", oStatus, preselectionObjectNumber);
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.checkPreselectionObjectStatus(oStatus, preselectionObjectNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет статус \"([^\"]*)\" лота № \"([^\"]*)\" в опубликованном извещении$")
    public void customerChecksLotsStatusInPublishedNotice(String lotStatus, String lotNumber) {
        String stepName = String.format("'Заказчик' проверяет статус: '%s' лота № '%s' в опубликованном извещении",
                lotStatus, lotNumber);
        this.logStepName(stepName);

        timer.start();

        String noticeNumberWithLotNumber = config.getParameter("PurchaseNumber") + "/" + lotNumber;
        customerMyPurchasesPage.checkLotStatusByNoticeNumber(noticeNumberWithLotNumber, lotStatus);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' сохраняет в параметрах теста количество непрочитанных входящих уведомлений")
    public void customerSavesInTestParametersTheNumberOfUnreadMessages() throws Throwable {
        String stepName = "'Заказчик' сохраняет в параметрах теста количество непрочитанных входящих уведомлений";
        this.logStepName(stepName);

        timer.start();

        customerMessagesPage.goToIncomingMessages();
        config.setParameter("UnreadMessagesCounter", customerMessagesPage.getUnreadMessagesCounter());

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' выполняет ручную отправку уведомления из личного кабинета для организации с ИНН {string}")
    public void customerPerformsManualSendAnOutgoingMessageForOrganizationWithSpecificInn(String inn) throws Throwable {
        String stepName = String.format(
                "'Заказчик' выполняет ручную отправку уведомления из личного кабинета для организации с ИНН '%s'", inn);
        this.logStepName(stepName);

        timer.start();

        customerMessagesPage.
                goToOutgoingMessages().
                clickOnNewMessageButton();
        customerCreateMessagePage.
                ifPageOpened().
                clickOnButtonByName("Кнопка Выбрать");
        customerRecipientSelectionDialog.
                ifDialogOpened().
                setField("Поле ИНН", inn).
                clickOnButtonByName("Кнопка Поиск").
                checkNumberOfRowsInSearchResults(1).
                selectRecipientByRowNumber(1).
                clickOnButtonByName("Кнопка Выбрать").
                waitForDialogCloses();
        customerCreateMessagePage.
                ifPageOpened().
                checkAtLeastOneRecipientIsSelected().
                setField("Поле Тема", "Тема сообщения.").
                setField("Поле Текст сообщения",
                        StringUtils.repeat("Текст сообщения на нескольких строках. ", 50)).
                uploadFileIntoAttachments(config.getAbsolutePathToFoundationDoc(),
                        config.getConfigParameter("FoundationDoc")).
                clickOnButtonByName("Кнопка Отправить").
                checkPageUrl("Просмотр сообщения");
        config.setParameter("CustomerOutgoingMessageNumber", url().split("/ViewMessage/")[1]);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что количество непрочитанных входящих уведомлений увеличилось")
    public void customerChecksThatTheNumberOfUnreadMessagesIncremented() throws Throwable {
        String stepName = "'Заказчик' проверяет, что количество непрочитанных входящих уведомлений увеличилось";
        this.logStepName(stepName);

        timer.start();

        customerMessagesPage.goToIncomingMessages();

        long unreadMessagesCounterOld = Long.parseLong(config.getParameter("UnreadMessagesCounter"));
        long unreadMessagesCounterNew = Long.parseLong(customerMessagesPage.getUnreadMessagesCounter());
        System.out.printf(
                "[ИНФОРМАЦИЯ]: проверка количества непрочитанных входящих уведомлений - было: [%d], стало: [%d].%n",
                unreadMessagesCounterOld, unreadMessagesCounterNew);

        Assert.assertTrue("[ОШИБКА]: количество непрочитанных входящих уведомлений не увеличилось",
                unreadMessagesCounterNew > unreadMessagesCounterOld);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет наличие входящего уведомления по его номеру, сохраненному в параметрах теста")
    public void customerChecksIncomingMessagePresenceUsingMessageNumberStoredInTestParameters() throws Throwable {
        String stepName =
                "'Заказчик' проверяет наличие входящего уведомления по его номеру, сохраненному в параметрах теста";
        this.logStepName(stepName);

        timer.start();

        customerMessagesPage.
                goToIncomingMessages().
                clickOnFilterButton().
                setSubjectField("Тема сообщения.").
                setDispatchDateFrom(timer.getYesterdayDateTime("dd.MM.yy")).
                setDispatchDateTo(timer.getCurrentDateTime("dd.MM.yy")).
                clickOnShowButton().
                openIncomingMessageByMessageNumber(config.getParameter("CustomerOutgoingMessageNumber")).
                goBackToPreviousPage();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет входящее уведомление \"([^\"]*)\"$")
    public void customerChecksIncomingMessagePresence(String subj) throws Throwable {
        String purchaseNumber = config.getParameter("PurchaseNumber");
        String subject = subj.contains("%s") ? String.format(subj, purchaseNumber) : subj + purchaseNumber;
        String stepName = String.format("'Заказчик' проверяет входящее уведомление '%s'", subject);
        this.logStepName(stepName);

        timer.start();

        customerMessagesPage.
                goToIncomingMessages().
                clickOnFilterButton().
                setSubjectField(subject).
                setDispatchDateFrom(timer.getYesterdayDateTime("dd.MM.yy")).
                setDispatchDateTo(timer.getCurrentDateTime("dd.MM.yy")).
                clickOnShowButton(1).
                checkFirstSubject(subject).
                clickOnClearFilterButton().
                checkSubjectFieldIsEmpty();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет последнее из входящих уведомлений \"([^\"]*)\"$")
    public void customerChecksLastIncomingMessagePresence(String subject) throws Throwable {
        String stepName = String.format("'Заказчик' проверяет последнее из входящих уведомлений '%s'", subject);
        this.logStepName(stepName);

        timer.start();

        customerMessagesPage.
                goToIncomingMessages().
                clickOnFilterButton().
                setSubjectField(subject).
                setDispatchDateFrom(timer.getYesterdayDateTime("dd.MM.yy")).
                setDispatchDateTo(timer.getCurrentDateTime("dd.MM.yy")).
                clickOnShowButton().
                checkFirstSubject(subject);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет последнее из входящих уведомлений {string} по номеру закупки")
    public void customerChecksLastIncomingMessagePresenceByTradeNumber(String subj) throws Throwable {
        String purchaseNumber = config.getParameter("PurchaseNumber");
        String subject = subj.contains("%s") ? String.format(subj, purchaseNumber) : subj + purchaseNumber;
        String stepName =
                String.format("'Заказчик' проверяет последнее из входящих уведомлений '%s' по номеру закупки", subject);
        this.logStepName(stepName);

        timer.start();

        customerMessagesPage.
                goToIncomingMessages().
                clickOnFilterButton().
                setSubjectField(subject).
                setDispatchDateFrom(timer.getYesterdayDateTime("dd.MM.yy")).
                setDispatchDateTo(timer.getCurrentDateTime("dd.MM.yy")).
                clickOnShowButton().
                checkFirstSubject(subject);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет последнее из входящих уведомлений {string} с номером закупки, сохраненным в параметрах теста")
    public void customerChecksLastIncomingMessagePresenceBySubjectAndPurchaseNumber(String subj) throws Throwable {
        String purchaseNumber = config.getParameter("PurchaseNumber");
        String subject = subj.contains("%s") ? String.format(subj, purchaseNumber) : subj + purchaseNumber;
        String stepName = String.format("'Заказчик' проверяет последнее из входящих уведомлений '%s' с номером " +
                "закупки, сохраненным в параметрах теста", subject);
        this.logStepName(stepName);

        timer.start();

        customerMessagesPage.
                goToIncomingMessages().
                clickOnFilterButton().
                setSubjectField(subject).
                setDispatchDateFrom(timer.getYesterdayDateTime("dd.MM.yy")).
                setDispatchDateTo(timer.getCurrentDateTime("dd.MM.yy")).
                clickOnShowButton().
                checkFirstSubject(subject.replace("  ", " ")).
                clickOnClearFilterButton().
                checkSubjectFieldIsEmpty();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет входящие уведомления \"([^\"]*)\" в количестве \"([^\"]*)\" сообщений$")
    public void customerChecksIncomingMessagePresence(String subj, String expectedMessages) throws Throwable {
        String purchaseNumber = config.getParameter("PurchaseNumber");
        String subject = subj.contains("%s") ? String.format(subj, purchaseNumber) : subj + purchaseNumber;
        String stepName = String.format(
                "'Заказчик' проверяет входящие уведомления '%s' в количестве '%s' сообщений", subject, expectedMessages);
        this.logStepName(stepName);

        timer.start();

        int expected = Integer.parseInt(expectedMessages);
        customerMessagesPage.
                goToIncomingMessages().
                clickOnFilterButton().
                setSubjectField(subject).
                clickOnShowButton(expected).
                checkFirstSubject(subject).
                clickOnClearFilterButton().
                checkSubjectFieldIsEmpty();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит по первой верхней ссылке в колонке 'Входящий номер' результатов поиска уведомлений")
    public void customerGoesToFirstIncomingNumberLinkIntoIncomingMessagesSearchResults() throws Throwable {
        String stepName = "'Заказчик' переходит по первой верхней ссылке в колонке 'Входящий номер' результатов " +
                "поиска уведомлений";
        this.logStepName(stepName);

        timer.start();

        customerMessagesPage.clickOnFirstIncomingNumberLink();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит к рассмотрению запроса по ссылке на странице 'Просмотр сообщения'")
    public void customerClicksOnPartnerRelationRequestInfoLink() throws Throwable {
        String stepName = "'Заказчик' переходит к рассмотрению запроса по ссылке на странице 'Просмотр сообщения'";
        this.logStepName(stepName);

        timer.start();

        customerViewMessagePage.clickOnPartnerRelationRequestInfoLink();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит по ссылке {string} на странице 'Просмотр сообщения'")
    public void customerClicksRequestInfoLink(String linkName) throws Throwable {
        String stepName = String.format(
                "'Заказчик' переходит по ссылке '%s' на странице 'Просмотр сообщения'", linkName);
        this.logStepName(stepName);

        timer.start();

        customerViewMessagePage.clickOnRequestInfoLink(linkName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит к просмотру разъяснения заявки по ссылке на странице 'Просмотр сообщения'")
    public void customerClicksOnRegisterOfInquiriesInfoLink() throws Throwable {
        String stepName =
                "'Заказчик' переходит к просмотру разъяснения заявки по ссылке на странице 'Просмотр сообщения'";
        this.logStepName(stepName);

        timer.start();

        customerViewMessagePage.clickOnRegisterOfInquiriesInfoLink();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит к просмотру разъяснения заявки по ссылке на странице 'Просмотр сообщения' запроса на разъяснение")
    public void customerClicksOnRegisterOfInquiriesForDocumentationInfoLink() throws Throwable {
        String stepName =
                "'Заказчик' переходит к просмотру разъяснения заявки по ссылке на странице 'Просмотр сообщения'";
        this.logStepName(stepName);

        timer.start();

        customerViewMessagePage.clickOnRegisterOfInquiriesDocumentationInfoLink();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет наличие \"([^\"]*)\" ценовых предложений в диалоговом окне 'Ценовые предложения'$")
    public void customerChecksNumberOfRequestsInDialogWindow(String requests) throws Throwable {
        String stepName = String.format(
                "'Заказчик' проверяет наличие '%s' ценовых предложений в диалоговом окне 'Ценовые предложения'",
                requests);
        this.logStepName(stepName);

        timer.start();

        PriceOffersDialog priceOffersDialog = new PriceOffersDialog(driver);
        priceOffersDialog.ifCustomerPriceOffersDialogWindowOpened().
                checkCustomerPriceOffersDialogWindowIsNotEmpty().
                checkCustomerPriceOffersDialogWindowRecordsCount(requests);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет наличие строки \"([^\"]*)\" с ценовым предложением \"([^\"]*)\"$")
    public void customerChecksOfferPresenceInDialogWindow(String supplierNameOfferNumber, String priceOffer) {
        String stepName = String.format("'Заказчик' проверяет наличие строки '%s' с ценовым предложением '%s'",
                supplierNameOfferNumber, priceOffer);
        this.logStepName(stepName);

        timer.start();

        PriceOffersDialog priceOffersDialog = new PriceOffersDialog(driver);
        priceOffersDialog.checkPriceOfferInCustomerPriceOffersDialogWindow(supplierNameOfferNumber, priceOffer);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' закрывает диалоговое окно 'Ценовые предложения'$")
    public void customerClosesPriceOffersInfoDialogWindow() throws Throwable {
        String stepName = "'Заказчик' закрывает диалоговое окно 'Ценовые предложения'";
        this.logStepName(stepName);

        timer.start();

        PriceOffersDialog priceOffersDialog = new PriceOffersDialog(driver);
        priceOffersDialog.closeCustomerPriceOffersDialogWindow();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет статус сведений о закупке в журнале взаимодействия с ЕИС: \"([^\"]*)\"$")
    public void customerChecksPurchaseNoticeStatusInOosInteractionLog(String status) throws Throwable {
        String stepName = String.format(
                "'Заказчик' проверяет статус сведений о закупке в журнале взаимодействия с ЕИС: '%s'", status);
        this.logStepName(stepName);

        timer.start();

        oosMessageQueuePage.
                setPurchaseNumber(config.getParameter("PurchaseNumber")).
                setInformationType("Извещение о закупке").
                clickOnSearchButton().
                storeCreationDateTimeIntoTestParameter().
                checkStatusInSearchResults(status);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' открывает \"([^\"]*)\" на странице 'Общие настройки'$")
    public void customerOpensPartitionOnCommonSettingsPage(String partitionName) throws Throwable {
        String stepName = String.format("'Заказчик' открывает '%s' на странице 'Общие настройки'", partitionName);
        this.logStepName(stepName);

        timer.start();

        commonSettingPage.ifPageOpened().openPartition(partitionName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет что флаг \"([^\"]*)\" на странице 'Общие настройки' \"([^\"]*)\"$")
    public void customerChecksCheckboxState(String checkboxName, String checkboxState) {
        String stepName = String.format("'Заказчик' проверяет что флаг '%s' на странице 'Общие настройки' '%s'",
                checkboxName, checkboxState);
        this.logStepName(stepName);

        timer.start();

        String fullCheckboxName = checkboxName + " просмотр";
        if (checkboxState.equals("включен")) commonSettingPage.verifyCheckBoxSelected(fullCheckboxName);
        else commonSettingPage.verifyCheckBoxNotSelected(fullCheckboxName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' переключается на вкладку 'Закупки для согласования'$")
    public void customerSelectsPurchasesForApproveTab() throws Throwable {
        String stepName = "'Заказчик' переключается на вкладку 'Закупки для согласования'";
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.selectPurchasesForApproveTab();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' переключается на вкладку 'Мои Закупки'$")
    public void customerSelectsMyPurchasesTab() throws Throwable {
        String stepName = "'Заказчик' переключается на вкладку 'Мои Закупки'";
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.selectMyPurchasesTab();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на кнопку 'Продолжить' в диалоговом окне 'Предупреждение'$")
    public void customerClicksOnContinueButtonInWarningDialogWindowOnCommonCustomerPage() throws Throwable {
        String stepName = "'Заказчик' нажимает на кнопку 'Продолжить' в диалоговом окне 'Предупреждение'";
        this.logStepName(stepName);

        timer.start();

        // Нажимаем на кнопку [Продолжить] в диалоговом окне [Предупреждение] ( старый код, универсальный метод )
        commonCustomerPage.clickButtonInPopupWindow("Продолжить-Предупреждение");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку 'Продолжить' в окне диалога 'Предупреждение'")
    public void customerClicksOnContinueButtonInWarningDialogWindow() throws Throwable {
        String stepName = "'Заказчик' нажимает на кнопку 'Продолжить' в окне диалога 'Предупреждение'";
        this.logStepName(stepName);

        timer.start();

        // Нажимаем на кнопку [Продолжить] в окне диалога [Предупреждение] ( новый код, специализированный метод )
        warningDialog.clickOnContinueButton();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на кнопку 'Да' в диалоговом окне 'Предупреждение'$")
    public void customerClicksOnYesButtonInWarningDialogWindow() throws Throwable {
        String stepName = "'Заказчик' нажимает на кнопку 'Да' в диалоговом окне 'Предупреждение'";
        this.logStepName(stepName);

        timer.start();

        // Нажимаем на кнопку [Да] в диалоговом окне [Предупреждение]
        commonCustomerPage.clickButtonInPopupWindow("Да-Предупреждение");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет, что открыта страница \"([^\"]*)\"$")
    public void customerChecksThatThePageIsOpened(String pageName) throws Throwable {
        String stepName = String.format("'Заказчик' проверяет, что открыта страница '%s'", pageName);
        this.logStepName(stepName);

        timer.start();

        commonCustomerPage.checkPageUrl(pageName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' вводит комментарий \"([^\"]*)\" в диалоговом окне 'Предупреждение' и нажимает на кнопку \"([^\"]*)\"$")
    public void customerEntersCommentInWarningDialogAndClicksOnButtonByName(
            String comment, String buttonName) throws Throwable {
        String stepName = String.format(
                "'Заказчик' вводит комментарий '%s' в диалоговом окне 'Предупреждение' и нажимает на кнопку '%s'",
                comment, buttonName);
        this.logStepName(stepName);

        timer.start();

        rejectApprovalRequestDialog.
                ifDialogOpened().
                setFieldClearClickAndSendKeys("Поле Комментарий", comment).
                clickOnButtonByName(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' выбирает причину отказа, затем прикрепляет документ и нажимает на кнопку 'Опубликовать'$")
    public void customerEntersCancelReasonThanAttachTheDocumentAndClicksOnPublishButton() throws Throwable {
        String stepName =
                "'Заказчик' выбирает причину отказа, затем прикрепляет документ и нажимает на кнопку 'Опубликовать'";
        this.logStepName(stepName);

        timer.start();

        tradeCancelDialog.
                ifDialogOpened().
                setFieldClearClickAndSendKeys("Дата принятия решения",
                        timer.getDateShift(new Date(), "DAYS", "0")).
                selectCancelReason().
                uploadFile(config.getAbsolutePathToFoundationDoc()).
                clickOnButtonByName("Опубликовать");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Информация'$")
    public void customerClicksOnOkButtonInInformationDialogWindow() throws Throwable {
        String stepName = "'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Информация'";
        this.logStepName(stepName);

        timer.start();

        informationDialog.
                ifDialogOpened("Текст заголовка окна").
                clickOnOKButton("ОК");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Информация' с текстом \"([^\"]*)\"$")
    public void customerClicksOnOkButtonInInformationDialogWindowWithSpecifiedText(String text) throws Throwable {
        String stepName = String.format(
                "'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Информация' с текстом '%s'", text);
        this.logStepName(stepName);

        timer.start();

        informationDialog.
                ifDialogOpened("Текст заголовка окна").
                check1stLineInWindowText("Текст окна 1 строка", text).
                clickOnOKButton("ОК");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Операция успешно выполнена'")
    public void customerClicksOnOkButtonInOperationCompletedSuccessfullyDialogWindow() throws Throwable {
        String stepName = "'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Операция успешно выполнена'";
        this.logStepName(stepName);

        timer.start();

        completedDialog.clickOnOkButton();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Сообщение'")
    public void customerClicksOnOkButtonInMessageDialogWindow() throws Throwable {
        String stepName = "'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Сообщение'";
        this.logStepName(stepName);

        timer.start();

        messageDialog.clickOnOkButton();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет текст {string} в строке {int} сообщения и нажимает кнопку 'ОК' окна диалога 'Ошибка'")
    public void customerChecksTextInLineByNumberAndPressOkButtonOfErrorDialogWindow(String text, int line)
            throws Throwable {
        String stepName = String.format("'Заказчик' проверяет текст '%s' в строке '%d' сообщения и нажимает кнопку " +
                "'ОК' окна диалога 'Ошибка'", text, line);
        this.logStepName(stepName);

        timer.start();

        int lineNo = line - 1;
        Assert.assertTrue(
                String.format("[ОШИБКА]: передан не верный номер строки '%d' для диалогового окна [Ошибка]", line),
                lineNo >= 0);
        //--------------------------------------------------------------------------------------------------------------
        // Работа в диалоговом окне [Ошибка]
        ErrorDialog errorDialog = new ErrorDialog(driver);
        errorDialog.
                ifDialogOpened().
                checkDialogText(text, lineNo).
                clickOnOkButton();
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} на странице 'Список пользователей'")
    public void customerClicksOnCreateEmployeeOnEmployeeListPage(String buttonName) throws Throwable {
        String stepName = String.format("'Заказчик' нажимает на кнопку '%s' на странице 'Список пользователей'", buttonName);
        this.logStepName(stepName);

        timer.start();

        employeeListPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит по ссылке на страницу 'Заявка на добавление пользователя'")
    public void customerGoesToEmployeeRequestPage() throws Throwable {
        String stepName =
                "'Заказчик' переходит по ссылке на страницу 'Заявка на добавление пользователя'";

        timer.start();

        customerLogInPage.clickOnAddUserLink();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} на странице 'Заявка на добавление пользователя'")
    public void customerClicksOnButtonOnEmployeeRequestPage(String buttonName) throws Throwable {
        String stepName = String.format("'Заказчик' нажимает на кнопку '%s'" +
                " на странице 'Заявка на добавление пользователя'", buttonName);
        this.logStepName(stepName);

        timer.start();

        employeeRequestPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' выбирает сертификат нового пользователя")
    public void customerSelectsCertificateNewEmployee() throws Throwable {
        String stepName = "'Заказчик' выбирает сертификат нового пользователя";

        timer.start();

        SupplierCertificateSelector supplierCertificateSelector = new SupplierCertificateSelector();
        supplierCertificateSelector.selectCertificate(driver);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' выбирает сгенерированный сертификат для редактирования информации о пользователе")
    public void customerSelectsNewCertificateForUserEdition() throws Throwable {
        String stepName = "'Заказчик' выбирает новый сертификат для редактирования информации о пользователе";
        this.logStepName(stepName);

        timer.start();

        // Выполняется сохранение значения параметра [CertName] и потом его восстановление, так как выбор сертификата
        // в окне [Выберите сертификат] происходит по параметру [CertName]
        String organizationName = config.getParameter("CertificateName");
        String certName = config.getParameter("CertName");
        config.setParameter("CertName", organizationName);

        SupplierCertificateSelector supplierCertificateSelector = new SupplierCertificateSelector();
        supplierCertificateSelector.selectCertificate(driver);

        config.setParameter("CertName", certName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' выбирает сгенерированный сертификат по совпадению имени и не совпадению Хеш со старым сертификатом")
    public void customerSelectsNewCertificateForUserByCertificateNameAndHashEdition() throws Throwable {
        String stepName = "'Заказчик' выбирает сгенерированный сертификат по совпадению имени и не совпадению Хеш со старым сертификатом";
        this.logStepName(stepName);

        timer.start();

        // Выполняется сохранение значения параметра [CertName] и потом его восстановление, так как выбор сертификата
        // в окне [Выберите сертификат] происходит по параметру [CertName]
        String organizationName = config.getParameter("CertificateName");
        String certName = config.getParameter("CertName");
        config.setParameter("CertName", organizationName);

        SupplierCertificateSelector supplierCertificateSelector = new SupplierCertificateSelector();
        supplierCertificateSelector.selectCertificateByNameAndHash(driver);

        config.setParameter("CertName", certName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет поле 'Текущий сертификат пользователя' на странице 'Заявка на добавление пользователя'")
    public void customerChecksSelectedCertificateInEmployeeRequestPage() {
        String stepName =
                "'Заказчик' проверяет поле 'Текущий сертификат пользователя' на странице 'Заявка на добавление пользователя'";

        timer.start();

        employeeRequestPage.verifyFieldContent("Текущий сертификат пользователя",
                config.getParameter("OldCertName"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет поле 'Фамилия' на странице 'Заявка на добавление пользователя'")
    public void customerChecksLastNameInEmployeeRequestPage() {
        String stepName =
                "'Заказчик' проверяет поле 'Фамилия' на странице 'Заявка на добавление пользователя'";

        timer.start();

        employeeRequestPage.verifyFieldContent("Фамилия",
                config.getParameter("CertName").split(" ")[1]);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет поле 'Имя' на странице 'Заявка на добавление пользователя'")
    public void customerChecksFirstNameInEmployeeRequestPage() {
        String stepName =
                "'Заказчик' проверяет поле 'Имя' на странице 'Заявка на добавление пользователя'";

        timer.start();

        employeeRequestPage.verifyFieldContent("Имя", config.getParameter("CertName").split(" ")[2]);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Номер телефона' на странице 'Заявка на добавление пользователя'")
    public void customerSetsPhoneNumberFieldUserPersonalDataInEmployeeRequestPage() {
        String stepName = "'Заказчик' заполняет поле 'Номер телефона' на странице 'Заявка на добавление пользователя'";

        timer.start();

        employeeRequestPage.setPhoneNumberFieldUserPersonalData();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Адрес эл. почты для ув. заказчика' на странице 'Заявка на добавление пользователя'")
    public void customerSetsCustomerContactEmailInEmployeeRequestPage() {
        String stepName =
                "'Заказчик' заполняет поле 'Адрес эл. почты для ув. заказчика' на странице 'Заявка на добавление пользователя'";

        timer.start();

        config.setParameter("CustomerEmail",
                "CustomerEmail" + timer.getCurrentDateTime("ddMMyyyyHHmmSS") + "@example.com");
        employeeRequestPage.
                setField("Адрес электронной почты для уведомлений заказчика",
                        config.getParameter("CustomerEmail"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Адрес эл. почты для ув. поставщика' на странице 'Заявка на добавление пользователя'")
    public void customerSetsSupplierContactEmailInEmployeeRequestPage() {
        String stepName =
                "'Заказчик' заполняет поле 'Адрес эл. почты для ув. поставщика' на странице 'Заявка на добавление пользователя'";

        timer.start();

        config.setParameter("SupplierEmail",
                "SupplierEmail" + timer.getCurrentDateTime("ddMMyyyyHHmmSS") + "@example.com");
        employeeRequestPage.
                setField("Адрес электронной почты для уведомлений поставщика",
                        config.getParameter("SupplierEmail"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Адрес электронной почты' на странице 'Заявка на добавление пользователя'")
    public void customerSetsContactEmailInEmployeeRequestPage() {
        String stepName =
                "'Заказчик' заполняет поле 'Адрес электронной почты' на странице 'Заявка на добавление пользователя'";

        timer.start();

        config.setParameter("SupplierEmail",
                "SupplierEmail" + timer.getCurrentDateTime("ddMMyyyyHHmmSS") + "@example.com");
        employeeRequestPage.
                setField("Адрес электронной почты",
                        config.getParameter("SupplierEmail"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Имя пользователя - логин' на странице 'Заявка на добавление пользователя'")
    public void customerFillsLoginTextFieldInEmployeeRequestPage() {
        String stepName =
                "'Пользователь' заполняет поле 'Имя пользователя - логин' на странице 'Заявка на добавление пользователя'";
        this.logStepName(stepName);

        timer.start();

        config.setParameter("EmployeeRequestUserLogin",
                "login" + timer.getCurrentDateTime("ddMMyyyyHHmmSS"));
        employeeRequestPage.setField("Имя пользователя (логин)", config.getParameter("EmployeeRequestUserLogin"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Пароль' на странице 'Заявка на добавление пользователя'")
    public void customerFillsPasswordTextFieldInEmployeeRequestPage() {
        String stepName =
                "'Пользователь' заполняет поле 'Пароль' на странице 'Заявка на добавление пользователя'";
        this.logStepName(stepName);

        timer.start();

        employeeRequestPage.setField("Пароль", config.getConfigParameter("UserPass"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Подтверждение пароля' на странице 'Заявка на добавление пользователя'")
    public void customerFillsConfirmPasswordTextFieldInEmployeeRequestPage() {
        String stepName =
                "'Пользователь' заполняет поле 'Подтверждение пароля' на странице 'Заявка на добавление пользователя'";
        this.logStepName(stepName);

        timer.start();

        employeeRequestPage.setField("Подтверждение пароля", config.getConfigParameter("UserPass"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Кодовое слово' на странице 'Заявка на добавление пользователя'")
    public void customerFillsCodeWordTextFieldInEmployeeRequestPage() {
        String stepName =
                "'Пользователь' заполняет поле 'Кодовое слово' на странице 'Заявка на добавление пользователя'";
        this.logStepName(stepName);

        timer.start();

        employeeRequestPage.setField("Кодовое слово", config.getConfigParameter("UserSecWord"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' прикрепляет 'Доверенность' к заявке на странице 'Заявка на добавление пользователя'")
    public void customerAttachesPowerOfAttorneyInEmployeeRequestPage() throws Throwable {
        String stepName =
                "'Заказчик' прикрепляет 'Доверенность' к заявке на странице 'Заявка на добавление пользователя'";
        this.logStepName(stepName);

        timer.start();

        employeeRequestPage.atachePowerOfAttorney();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' устанавливает флажок 'Опытный пользователь' на странице 'Заявка на добавление пользователя'")
    public void customerSetsCheckBoxValueInEmployeeRequestPage() {
        String stepName =
                "'Заказчик' устанавливает флажок 'Опытный пользователь' на странице 'Заявка на добавление пользователя'";
        this.logStepName(stepName);

        timer.start();

        employeeRequestPage.setCheckBoxValue("Опытный пользователь");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Ключ защиты' на странице 'Заявка на добавление пользователя'")
    public void customerSetsCaptchaFieldInRequestAccreditationPage() {
        String stepName =
                "'Заказчик' заполняет поле 'Ключ защиты' на странице 'Заявка на добавление пользователя'";
        this.logStepName(stepName);

        timer.start();

        employeeRequestPage.setCaptchaField();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что таблица на странице 'Список пользователей' содержит {int} строки")
    public void customerChecksRowsInEmployeeTableInEmployeeListPage(int numberOfRows) {
        String stepName = String.format(
                "'Заказчик' проверяет, что таблица на странице 'Список пользователей' содержит '%d' строки", numberOfRows);
        this.logStepName(stepName);

        timer.start();

        employeeListPage.checkNumberOfRowsInEmployeeTable(numberOfRows);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на 'Настроить права доступа' для сгенерированного работника в таблице 'Пользователи'")
    public void customerSelectsSetPermissionsOnEmployeeListPage() {
        String stepName = "'Заказчик' нажимает на 'Настроить права доступа' " +
                "для сгенерированного работника в таблице 'Пользователи' ";

        this.logStepName(stepName);

        timer.start();

        //В таблице имя и фамилия написаны наоборот, двойной пробел между фамиией и именем
        String[] employeeName = config.getParameter("EmployeeUserName").split(" ");
        employeeListPage.selectSetPermissions(employeeName[1] + "  " + employeeName[0]);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' выбирает сгенерированного пользователя на странице 'Список пользователей'")
    public void customerSelectsEmployeeOnEmployeeListPAge() {
        String stepName = "'Заказчик' выбирает сгенерированного пользователя на странице 'Список пользователей'";
        this.logStepName(stepName);

        timer.start();

        String fio = config.getParameter("OrganizationAdministratorName").split(" ")[1] + "  "
                + config.getParameter("OrganizationAdministratorName").split(" ")[0];
        employeeListPage.selectFIO(fio);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет колонку 'Роль' у сгенерированного работника на значение {string} на странице 'Список пользователей'")
    public void customerChecksColumnRoleOnEmployeeListPage(String columnValue) {
        String stepName = String.format("'Заказчик' проверяет колонку 'Роль' у сгенерированного работника " +
                "на значение '%s' на странице 'Заявка на добавление пользователя'", columnValue);

        this.logStepName(stepName);

        timer.start();

        //В таблице имя и фамилия написаны наоборот, двойной пробел между фамиией и именем
        String[] employeeName = config.getParameter("EmployeeUserName").split(" ");
        employeeListPage.checkValueInColumnRole(employeeName[1] + "  " + employeeName[0], columnValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} на странице согласия на обработку персональных данных")
    public void customerClicksOnButtonInDataProcessingAgreementPage(String buttonName) throws Throwable {
        String stepName = String.format(
                "'Заказчик' нажимает на кнопку '%s' на странице согласия на обработку персональных данных", buttonName);
        this.logStepName(stepName);

        timer.start();

        dataProcessingAgreementPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' подписывает и отправляет согласие на обработку персональных данных")
    public void customerClicksOnButtonSubscribeAndSendInDataProcessingAgreementPage() throws Throwable {
        String stepName = "'Заказчик' подписывает и отправляет согласие на обработку персональных данных";
        this.logStepName(stepName);

        timer.start();

        dataProcessingAgreementPage.clickOnButton("Подписать и отправить");
        CompletedDialog completedDialog = new CompletedDialog(driver);
        completedDialog.clickOnOkButton();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет невозможность редактирования поля {string} на странице согласия на обработку персональных данных")
    public void customerChecksFieldInDataProcessingAgreementPage(String fieldName) {
        String stepName = String.format(
                "'Заказчик' проверяет невозможность редактирования поля '%s' " +
                        "на странице согласия на обработку персональных данных", fieldName);
        this.logStepName(stepName);

        timer.start();

        dataProcessingAgreementPage.checkDisableField(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Ключ защиты' на странице согласия на обработку персональных данных")
    public void customerSetsCaptchaFieldIDataProcessingAgreementPage() {
        String stepName = "'Заказчик' заполняет поле 'Ключ защиты' на странице согласия на обработку персональных данных";
        this.logStepName(stepName);

        timer.start();

        dataProcessingAgreementPage.setCaptchaField();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет поле 'Фамилия' на странице 'Настройка ролей пользователя'")
    public void customerChecksFieldLastNameOnUserRolesPage() {
        String stepName = "'Заказчик' проверяет поле 'Фамилия' на странице 'Настройка ролей пользователя'";
        this.logStepName(stepName);

        timer.start();

        String lastName = config.getParameter("EmployeeUserName").split(" ")[0];
        userRolesPage.checkField(lastName, "Фамилия");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет поле 'Имя' на странице 'Настройка ролей пользователя'")
    public void customerChecksFieldFirstNameOnUserRolesPage() {
        String stepName = "'Заказчик' проверяет поле 'Имя' на странице 'Настройка ролей пользователя'";
        this.logStepName(stepName);

        timer.start();

        String firstName = config.getParameter("EmployeeUserName").split(" ")[1];
        userRolesPage.checkField(firstName, "Имя");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' устанавливает флажок {string} на странице 'Настройка ролей пользователя'")
    public void customerSetsCheckBoxValueOnUserRolesPage(String checkBoxName) {
        String stepName = String.format("'Заказчик' устанавливает флажок '%s' на странице " +
                "'Настройка ролей пользователя'", checkBoxName);
        this.logStepName(stepName);

        timer.start();

        userRolesPage.setCheckBoxValue(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} на странице 'Настройка ролей пользователя'")
    public void customerClicksOnButtonOnUserRolesPage(String buttonName) throws Throwable {
        String stepName = String.format("'Заказчик' нажимает на кнопку '%s' на странице " +
                "'Настройка ролей пользователя'", buttonName);
        this.logStepName(stepName);

        timer.start();

        userRolesPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Наименование перечня' значением {string} с текущей датой на странице 'Реестр квалифицированных подрядных организаций'")
    public void customerFillsFieldNameOfTheListWithDateOnRegisterOfQualifiedContractorsPage(String fieldValue) throws Exception {
        String stepName = String.format("'Заказчик' заполняет поле 'Наименование перечня' значением '%s' с текущей датой на странице " +
                "'Реестр квалифицированных подрядных организаций'", fieldValue);
        this.logStepName(stepName);

        timer.start();

        registerOfQualifiedContractorsPage.fillsFieldNameOfTheListWithDate(fieldValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} на странице 'Реестр квалифицированных подрядных организаций'")
    public void customerClicksOnButtonOnRegisterOfQualifiedContractorsPage(String buttonName) throws Exception {
        String stepName = String.format("'Заказчик' нажимает на кнопку '%s' на странице " +
                "'Реестр квалифицированных подрядных организаций'", buttonName);
        this.logStepName(stepName);

        timer.start();

        registerOfQualifiedContractorsPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет наличие записи о квалифицированной подрядной организации для предмета предварительного отбора под номером {string}")
    public void customerChecksTheNumberOFQualifiedContractorRecordOnRegisterOfQualifiedContractorsPage
            (String preselectionObjectNumber) {
        String stepName = String.format("'Заказчик' проверяет наличие записи о квалифицированной подрядной организации " +
                "для предмета предварительного отбора под номером '%s'", preselectionObjectNumber);
        this.logStepName(stepName);

        timer.start();

        registerOfQualifiedContractorsPage.checkTheNumberOFQualifiedContractorRecord(preselectionObjectNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} странице 'Информация о пользователе'")
    public void customerClicksOnButtonOnEmployeeViewPage(String buttonName) throws Throwable {
        String stepName = String.format(
                "'Заказчик' нажимает на кнопку '%s' странице 'Информация о пользователе'", buttonName);
        this.logStepName(stepName);

        timer.start();

        employeeViewPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @When("'Заказчик' нажимает на кнопку {string} на странице 'Заявка на изменение информации о пользователе или сертификате'")
    public void customerClicksOnButtonOnEmployeeChangeRequestPage(String buttonName) throws Throwable {
        String stepName = String.format("'Заказчик' нажимает на кнопку '%s' на странице " +
                "'Заявка на изменение информации о пользователе/сертификате'", buttonName);
        this.logStepName(stepName);

        timer.start();

        employeeChangeRequestPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет количество строк {string} в таблице 'Сертификаты' на странице 'Заявка на изменение информации о пользователе или сертификате'")
    public void customerChecksRowsInCertificatesTableOnEmployeeChangeRequestPage(String rows) throws Throwable {
        String stepName = String.format("'Заказчик' проверяет количество строк '%s' в таблице 'Сертификаты' " +
                "на странице 'Заявка на изменение информации о пользователе/сертификате'", rows);
        this.logStepName(stepName);

        timer.start();

        employeeChangeRequestPage.checkNumberOfRowsInCertificatesTable(rows);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле {string} значением {string} на странице 'Заявка на изменение информации о пользователе или сертификате'")
    public void customerFillsFieldByNameOnEmployeeChangeRequestPage(String fieldName, String value) throws Throwable {
        String stepName = String.format("'Заказчик' заполняет поле '%s' значением '%s' " +
                "на странице 'Заявка на изменение информации о пользователе/сертификате'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        employeeChangeRequestPage.setFieldClearClickAndSendKeys(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет в элементе {string} в окне диалога 'Сохранено' текст {string}")
    public void customerChecksTextInSavedDialogWindow(String fieldName, String value) {
        String stepName = String.format("'Заказчик' проверяет в элементе '%s' в окне диалога 'Сохранено' текст '%s'",
                fieldName, value);
        this.logStepName(stepName);

        timer.start();

        savedDialog.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что кнопка {string} отображается и доступна в окне диалога 'Сохранено'")
    public void customerChecksThatTheButtonByNameIsClickableInSavedDialogWindow(String controlName) {
        String stepName = String.format(
                "'Заказчик' проверяет, что кнопка '%s' отображается и доступна в окне диалога 'Сохранено'",
                controlName);
        this.logStepName(stepName);

        timer.start();

        savedDialog.isControlClickable(controlName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} в окне диалога 'Сохранено'")
    public void customerClicksOnButtonInSavedDialogWindow(String buttonName) throws Throwable {
        String stepName = String.format("'Заказчик' нажимает на кнопку '%s' в окне диалога 'Сохранено'", buttonName);
        this.logStepName(stepName);

        timer.start();

        savedDialog.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет в элементе {string} в окне диалога 'Предупреждение' текст {string}")
    public void customerChecksTextInWarningDialogWindow(String fieldName, String value) {
        String stepName = String.format(
                "'Заказчик' проверяет в элементе '%s' в окне диалога 'Предупреждение' текст '%s'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        warningDialog.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что кнопка {string} отображается и доступна в окне диалога 'Предупреждение'")
    public void customerChecksThatTheButtonByNameIsClickableInWarningDialogWindow(String controlName) {
        String stepName = String.format(
                "'Заказчик' проверяет, что кнопка '%s' отображается и доступна в окне диалога 'Предупреждение'",
                controlName);
        this.logStepName(stepName);

        timer.start();

        warningDialog.isControlClickable(controlName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет в элементе {string} в окне диалога 'Опубликовано' текст {string}")
    public void customerChecksTextInPublishedDialogWindow(String fieldName, String value) {
        String stepName = String.format(
                "'Заказчик' проверяет в элементе '%s' в окне диалога 'Опубликовано' текст '%s'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionPublishedDialog.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что кнопка {string} отображается и доступна в окне диалога 'Опубликовано'")
    public void customerChecksThatTheButtonByNameIsClickableInPublishedDialogWindow(String controlName) {
        String stepName = String.format(
                "'Заказчик' проверяет, что кнопка '%s' отображается и доступна в окне диалога 'Опубликовано'",
                controlName);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionPublishedDialog.isControlClickable(controlName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку 'Перейти к карточке сведений' в окне диалога 'Опубликовано'")
    public void customerClicksOnButtonInPublishedDialogWindow() throws Throwable {
        String stepName = "'Заказчик' нажимает на кнопку 'Перейти к карточке сведений' в окне диалога 'Опубликовано'";
        this.logStepName(stepName);

        timer.start();

        contractsExecutionPublishedDialog.goToExecutionViewCard();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет реквизиты цифровой подписи в диалоговом окне 'Просмотр подписи'")
    public void customerChecksRequisitesOfDigitalSignatureInShowSignatureDialogWindow() throws Throwable {
        String stepName = "'Заказчик' проверяет реквизиты цифровой подписи в диалоговом окне 'Просмотр подписи'";
        this.logStepName(stepName);

        timer.start();

        String certName = config.getParameter("CertName");
        String lastName = certName.split(" ")[0];
        String firstName = certName.split(" ")[1] + " " + certName.split(" ")[2];

        FileSignatureWindowDialog fileSignatureWindowDialog = new FileSignatureWindowDialog(driver);
        fileSignatureWindowDialog.
                ifDialogOpened().
                verifyField("Имя", firstName).
                verifyField("Фамилия", lastName).
                verifyField("ИНН", config.getConfigParameter("CertificateCustomerInn")).
                verifyField("ОГРН", config.getConfigParameter("CertificateCustomerOgrn")).
                clickOnButton("Отмена");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' нажимает на кнопку 'OK' в диалоговом окне 'Информация' с текстом {string}")
    public void customerClicksOnTheOkButtonInInformationDialogWindowWithSpecifiedText(String text) throws Throwable {
        String stepName = String.format(
                "'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Информация' с текстом '%s'", text);
        this.logStepName(stepName);

        timer.start();
        InformationAngularDialog informationAngularDialog = new InformationAngularDialog(driver);

        informationAngularDialog.
                ifDialogOpened("Текст заголовка окна").
                check1stLineInWindowText("Текст окна 1 строка", text).
                clickOnOKButton("ОК");

        timer.printStepTotalTime(stepName);
    }
}
