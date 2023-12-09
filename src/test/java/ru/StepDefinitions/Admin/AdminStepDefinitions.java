package ru.StepDefinitions.Admin;

import Helpers.DbOperations;
import LogicLayer.CertificateSelectors.OperatorCertificateSelector;
import cucumber.api.java.en.And;
import org.junit.Assert;
import ru.PageObjects.Admin.*;
import ru.PageObjects.Customer.Notice.*;
import ru.PageObjects.Supplier.PurchaseTradeViewPage;
import ru.PageObjects.Supplier.SearchPurchasePage;
import ru.PageObjects.Supplier.SupplierPurchasePage;
import ru.StepDefinitions.AbstractStepDefinitions;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Класс описывающий шаги работы с площадкой под Администратором (Оператором).
 * Created by Evgeniy Glushko on 25.03.2016.
 * Updated by Alexander S. Vasyurenko on 01.04.2021.
 */
public class AdminStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final AccreditationListRequestsPage accreditationListRequestsPage = new AccreditationListRequestsPage(driver);
    private final RegistryOfParticipantsPage registryOfParticipantsPage = new RegistryOfParticipantsPage(driver);
    private final OrganizationRequestPage organizationRequestPage = new OrganizationRequestPage(driver);
    private final EmployeeRequestListPage employeeRequestListPage = new EmployeeRequestListPage(driver);
    private final EmployeeRequestInfoPage employeeRequestInfoPage = new EmployeeRequestInfoPage(driver);
    private final AccreditationViewPage accreditationViewPage = new AccreditationViewPage(driver);
    private final PurchaseTradeViewPage purchaseTradeViewPage = new PurchaseTradeViewPage(driver);
    private final CustomerRegisterPage customerRegisterPage = new CustomerRegisterPage(driver);
    private final OrganizationViewPage organizationViewPage = new OrganizationViewPage(driver);
    private final SupplierPurchasePage supplierPurchasePage = new SupplierPurchasePage(driver);
    private final EmployeeRequestPage employeeRequestPage = new EmployeeRequestPage(driver);
    private final ProcedureForcePage procedureForcePage = new ProcedureForcePage(driver);
    private final SearchPurchasePage searchPurchasePage = new SearchPurchasePage(driver);
    private final EmployeeListPage employeeListPage = new EmployeeListPage(driver);
    private final EmployeeViewPage employeeViewPage = new EmployeeViewPage(driver);
    private final CommonAdminPage commonAdminPage = new CommonAdminPage(driver);
    private final SystemTasksPage systemTasksPage = new SystemTasksPage(driver);

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    @And("^'Администратор' переходит на страницу \"([^\"]*)\"$")
    public void adminGoesToPage(String pageName) throws Throwable {
        String stepName = String.format("'Администратор' переходит на страницу '%s'", pageName);
        this.logStepName(stepName);

        timer.start();

        switch (pageName) {
            //----------------------------------------------------------------------------------------------------------
            case "Поиск закупок":
                commonAdminPage.clickPurchasesLink();
                commonAdminPage.goToSearchPurchasesPage();
                break;
            //----------------------------------------------------------------------------------------------------------
            case "Заявки на аккредитацию":
                commonAdminPage.clickAdministrationLink();
                commonAdminPage.goToApplicationsForAccreditationPage();
                break;
            //----------------------------------------------------------------------------------------------------------
            case "Ускорение процедур":
                commonAdminPage.clickAdministrationLink();
                commonAdminPage.goToProcedureForcePage();
                break;
            //----------------------------------------------------------------------------------------------------------
            case "Системные задачи":
                commonAdminPage.clickAdministrationLink();
                commonAdminPage.goToProcedureSystemTasksPage();
                break;
            //----------------------------------------------------------------------------------------------------------
            case "Заявки на добавление / изменение информации пользователей":
                commonAdminPage.clickAdministrationLink();
                commonAdminPage.goToEmployeeRequestListPage();
                break;
            //----------------------------------------------------------------------------------------------------------
            case "Управление торгами":
                procedureForcePage.setPurchaseNumber(config.getParameter("PurchaseNumber"));
                procedureForcePage.clickButtonByName("Загрузить данные");
                procedureForcePage.clickBiddingManageLink();
                break;
            //----------------------------------------------------------------------------------------------------------
            default:
                Assert.fail(">>> В метод (administratorGoToPage) передан некорректный параметр (pageName): '" +
                        pageName + "'.");
                break;
            //----------------------------------------------------------------------------------------------------------
        }

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет, что открыта страница {string}")
    public void adminChecksPage(String pageName) throws Throwable {
        String stepName = String.format("'Администратор' проверяет, что открывта страница '%s'", pageName);
        this.logStepName(stepName);

        timer.start();

        commonAdminPage.checkPageUrl(pageName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' выбирает сгенерированного пользователя на странице 'Список пользователей'")
    public void adminChosesEmployeeOnEmployeeListPAge() throws Throwable {
        String stepName = "'Администратор' выбирает сгенерированного пользователя на странице 'Список пользователей'";
        this.logStepName(stepName);

        timer.start();

        String fio = config.getParameter("OrganizationAdministratorName").split(" ")[1] + "  "
                + config.getParameter("OrganizationAdministratorName").split(" ")[0];
        employeeListPage.clickOnFIOLinkByText(fio);

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' нажимает на кнопку {string} на странице 'Список пользователей'")
    public void adminClicksOnButtonInEmployeeListPage(String buttonName) throws Throwable {
        String stepName = "'Администратор' нажимает на кнопку '%s' на странице 'Список пользователей'";
        this.logStepName(stepName);

        timer.start();

        employeeListPage.clickOnButtonByName(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет, что таблица на странице 'Список пользователей' содержит {int} строки")
    public void adminChecksRowsNumberInEmployeeTableInEmployeeListPage(int numberOfRows) {
        String stepName = String.format(
                "'Администратор' проверяет, что таблица на странице 'Список пользователей' содержит '%d' строки", numberOfRows);
        this.logStepName(stepName);

        timer.start();

        employeeListPage.checkNumberOfRowsInEmployeeTable(numberOfRows);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' выполняет поиск по номеру закупки на странице 'Поиск закупок'$")
    public void adminPerformsSearchByPurchaseNumberOnSearchPurchasesPage() throws Throwable {
        String stepName = "'Администратор' выполняет поиск по номеру закупки на странице 'Поиск закупок'";
        this.logStepName(stepName);

        timer.start();

        searchPurchasePage.setPurchaseNumberLotNumber(config.getParameter("PurchaseNumber"));
        searchPurchasePage.clickButton("Поиск");
        searchPurchasePage.waitLoadingImage();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' переходит по ссылке с номером закупки в результатах поиска$")
    public void adminClicksOnNoticeNumberWithLotNumberInSearchResults() throws Throwable {
        String stepName = "'Администратор' переходит по ссылке с номером закупки в результатах поиска";
        this.logStepName(stepName);

        timer.start();

        // В этом методе мы переключаемся на новую вкладку в браузере ( открылась при щелчке по ссылке )
        searchPurchasePage.clickOnNoticeNumberWithLotNumber(config.getParameter("PurchaseNumber"));

        // Эти действия выполняются уже в новой вкладке браузера
        supplierPurchasePage.waitLoadingImage();
        supplierPurchasePage.checkPageUrl("Закупка №", "504 Закупка №");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' выполняет поиск по номеру закупки$")
    public void adminSearchesObject() throws Throwable {
        String stepName = "'Администратор' выполняет поиск по номеру закупки";
        this.logStepName(stepName);

        timer.start();

        // Для отладки -------------------------------------------------------------------------------------------------
        // config.setParameter("PurchaseNumber", "13784");
        // Для отладки -------------------------------------------------------------------------------------------------

        procedureForcePage.setPurchaseNumber(config.getParameter("PurchaseNumber"));
        procedureForcePage.clickButtonByName("Загрузить данные");
        procedureForcePage.getLotStatus();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' выполняет ускорение процедур$")
    public void adminPerformsToSpeedUpProcedure() throws Throwable {
        String stepName = "'Администратор' выполняет ускорение процедур";
        this.logStepName(stepName);

        timer.start();

        procedureForcePage.clickButtonByName("Текущая");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' нажимает на кнопку 'Дата начала рассмотрения вторых частей заявок - Текущая'$")
    public void adminClicksOnSetNowSecondPartsConsiderationStartDateButton() throws Throwable {
        String stepName = "'Администратор' нажимает на кнопку 'Дата начала рассмотрения вторых частей заявок - Текущая'";
        this.logStepName(stepName);

        timer.start();

        procedureForcePage.clickButtonByName("Дата начала рассмотрения вторых частей заявок - Текущая");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' нажимает на кнопку 'Дата начала квалификационного отбора - Текущая'$")
    public void adminClicksOnSetNowQualificationStartDateButton() throws Throwable {
        String stepName = "'Администратор' нажимает на кнопку 'Дата начала квалификационного отбора - Текущая'";
        this.logStepName(stepName);

        timer.start();

        procedureForcePage.clickButtonByName("Дата начала квалификационного отбора - Текущая");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' нажимает на кнопку 'Начало регламентного срока договора - Текущая'$")
    public void adminClicksOnStartScheduledTermOfTheContractCurrentButton() throws Throwable {
        String stepName = "'Администратор' нажимает на кнопку 'Начало регламентного срока договора - Текущая'";
        this.logStepName(stepName);

        timer.start();

        procedureForcePage.clickButtonByName("Начало регламентного срока договора - Текущая");

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' нажимает на кнопку {string} на странице 'Ускорение процедур'")
    public void adminClicksOnButtonOnProcedureForcePage(String buttonName) throws Throwable {
        String stepName =
                String.format("'Администратор' нажимает на кнопку '%s' на странице 'Ускорение процедур'", buttonName);
        this.logStepName(stepName);

        timer.start();

        procedureForcePage.clickButtonByName(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' нажимает кнопку {string} в строке {int} таблицы 'Управление договорами'")
    public void adminClicksOnButtonInContractManagementTableRowByNumber(String buttonName, int rowNumber)
            throws Throwable {
        String stepName = String.format(
                "'Администратор' нажимает кнопку '%s' в строке '%d' таблицы 'Управление договорами'",
                buttonName, rowNumber);
        this.logStepName(stepName);

        timer.start();

        procedureForcePage.clickOnButtonInContractManagementTableRowByNumber(buttonName, rowNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' завершает торги$")
    public void adminCompletsBidding() throws Throwable {
        String stepName = "'Администратор' завершает торги";
        this.logStepName(stepName);

        timer.start();

        procedureForcePage.clickButtonByName("Завершить торг");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' проверяет статус лота \"([^\"]*)\" для лота с номером \"([^\"]*)\"$")
    public void adminChecksLotStatusByLotNumber(String lotStatus, String lotNumber) throws Throwable {
        String stepName = String.format("'Администратор' проверяет статус лота '%s' для лота с номером '%s'",
                lotStatus, lotNumber);
        this.logStepName(stepName);

        timer.start();

        procedureForcePage.selectLotByNumber(lotNumber);
        procedureForcePage.clickButtonByName("Обновить");
        procedureForcePage.checkLotStatus(lotStatus);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' проверяет статус лота \"([^\"]*)\" для лота с номером \"([^\"]*)\" без нажатия кнопки 'Обновить'$")
    public void adminChecksLotStatusByLotNumberWithoutRefresh(String lotStatus, String lotNumber) throws Throwable {
        String stepName = String.format(
                "'Администратор' проверяет статус лота '%s' для лота с номером '%s' без нажатия кнопки 'Обновить'",
                lotStatus, lotNumber);
        this.logStepName(stepName);

        timer.start();

        procedureForcePage.selectLotByNumber(lotNumber);
        procedureForcePage.checkLotStatus(lotStatus);

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет статус предмета предварительного отбора {string} для предмета с номером {string} без нажатия кнопки 'Обновить'")
    public void adminChecksPreselectionObjectStatusByObjectNumberWithoutRefresh(String objectStatus, String objectNumber) throws Throwable {
        String stepName = String.format(
                "'Администратор' проверяет статус лота '%s' для лота с номером '%s' без нажатия кнопки 'Обновить'",
                objectStatus, objectNumber);
        this.logStepName(stepName);

        timer.start();

        procedureForcePage.selectPreselectionObjectByNumber(objectNumber);
        procedureForcePage.checkLotStatus(objectStatus);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' получает значение поля 'Дата начала торгов'$")
    public void adminLoadsDataForToSpeedUpProcedure() {
        String stepName = "'Администратор' получает значение поля 'Дата начала торгов'";
        this.logStepName(stepName);

        timer.start();

        procedureForcePage.getBiddingStartDateValue();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' проверяет статус торга \"([^\"]*)\"$")
    public void adminChecksAuctionStatus(String biddingStatus) {
        String stepName = String.format("'Администратор' проверяет статус торга '%s'", biddingStatus);
        this.logStepName(stepName);

        timer.start();

        procedureForcePage.checkBiddingStatus(biddingStatus);

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' устанавливает значение поля 'ИНН' на странице 'Список заявок'")
    public void adminSetsInnFieldInRequestListPage() throws Throwable {
        String stepName = "'Администратор' устанавливает значение поля 'ИНН' на странице 'Список заявок'";
        this.logStepName(stepName);

        timer.start();

        accreditationListRequestsPage.setINNField();

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' нажимает на кнопку 'Поиск' на странице 'Список заявок'")
    public void adminClicksSearchButtonInRequestListPage() throws Throwable {
        String stepName = "'Администратор' нажимает на кнопку 'Поиск' на странице 'Список заявок'";
        this.logStepName(stepName);

        timer.start();

        accreditationListRequestsPage.clickSearchButton();

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' нажимает на ссылку в колонке 'Номер' на странице 'Список заявок'")
    public void adminClicksLinkInNumberColumnInRequestListPage() throws Throwable {
        String stepName = "'Администратор' нажимает на ссылку в колонке 'Номер' на странице 'Список заявок'";
        this.logStepName(stepName);

        timer.start();

        accreditationListRequestsPage.clickOnLinkInNumberColumn(); // Нажимаем на ссылку в колонке [Номер]
        //--------------------------------------------------------------------------------------------------------------
        config.switchToNewWindowInBrowser();
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);

    }

    @And("'Администратор' проверяет наименование организации")
    public void adminChecksAccreditationName() {
        String stepName = "'Администратор' проверяет наименвоание организации";
        this.logStepName(stepName);

        timer.start();

        String accreditationName = accreditationViewPage.getOrganizationName();
        String expectedName = config.getParameter("AccreditationName");
        Assert.assertEquals("Наименование организации не совпало", expectedName, accreditationName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет ИНН организации")
    public void adminChecksInn() {
        String stepName = "'Администратор' проверяет ИНН организации";
        this.logStepName(stepName);

        timer.start();

        accreditationViewPage.checkInnLabel(config.getParameter("AccreditationInnValue")); // Проверяем поле ИНН

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' выполняет рассмотрение заявки")
    public void adminPerformsConsideration() throws Throwable {
        String stepName = "'Администратор' выполняет рассмотрение заявки";
        this.logStepName(stepName);

        timer.start();

        accreditationViewPage.performConsideration();

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет прикрепленные документы у пользователя с ролями 'Заказчик' и 'Поставщик'")
    public void adminChecksAttachedDocumentsForBothRoles() {
        String stepName =
                "'Администратор' проверяет прикрепленные документы у пользователя с ролями 'Заказчик' и 'Поставщик'";
        this.logStepName(stepName);

        timer.start();

        accreditationViewPage.checkAttachedDocuments3x6();

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет прикрепленные документы у 'Заказчик'")
    public void adminChecksAttachedDocumentsForCustomer() {
        String stepName = "'Администратор' проверяет прикрепленные документы у 'Заказчик'";
        this.logStepName(stepName);

        timer.start();

        accreditationViewPage.checkAttachedDocuments2x4forCustomer();

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет прикрепленные документы у 'Поставщик'")
    public void adminChecksAttachedDocumentsForSupplier() {
        String stepName = "Администратор' проверяет прикрепленные документы у 'Поставщик'";
        this.logStepName(stepName);

        timer.start();

        accreditationViewPage.checkAttachedDocuments2x4ForSupplier();

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет прикрепленные документы у 'Поставщик' с ускоренной аккредитацией")
    public void adminChecksAttachedDocumentsForAcceleratedSupplier() {
        String stepName = "Администратор' проверяет прикрепленные документы у 'Поставщик' с ускоренной аккредитацией";
        this.logStepName(stepName);

        timer.start();

        accreditationViewPage.checkAttachedDocuments3x6ForSupplier();

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' аккредитует поданную заявку")
    public void adminAccreditsApplication() throws Throwable {
        String stepName = "'Администратор' аккредитует поданную заявку";
        this.logStepName(stepName);

        timer.start();

        accreditationViewPage.performAccreditation();

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' принудительно утверждает поданную заявку")
    public void adminConfirmApplication() throws Throwable {
        String stepName = "'Администратор' принудительно утверждает поданную заявку";
        this.logStepName(stepName);

        timer.start();

        accreditationViewPage.сonfirmConditionallyAcceptedRequest();

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет статус аккредитации {string}")
    public void adminAccreditsApplication(String status) {
        String stepName = String.format("'Администратор' проверяет статус аккредитации '%s'", status);
        this.logStepName(stepName);

        timer.start();

        accreditationViewPage.checkStatusLabel(status);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' выполняет поиск карточки участника закупок по наименованию организации$")
    public void adminPerformsParticipantSearchByOrganizationName() throws Throwable {
        String stepName = "'Администратор' выполняет поиск карточки участника закупок по наименованию организации";
        this.logStepName(stepName);

        timer.start();

        registryOfParticipantsPage.performParticipantSearchByOrganizationName(
                config.getParameter("AccreditationName"));

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' открывает карточку участника закупок используя ссылку в результатах поиска")
    public void adminOpensParticipantCardByLinkInSearchResults() throws Throwable {
        String stepName = "'Администратор' открывает карточку участника закупок используя ссылку в результатах поиска";
        this.logStepName(stepName);

        timer.start();

        registryOfParticipantsPage.clickOnParticipantFullNameLinkByText(config.getParameter("AccreditationName"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' выполняет поиск карточки участника в 'Реестре заказчиков' по наименованию организации")
    public void adminPerformsParticipantSearchByOrganizationNameOnCustomerRegisterPage() throws Throwable {
        String stepName =
                "'Администратор' выполняет поиск карточки участника в 'Реестре заказчиков' по наименованию организации";
        this.logStepName(stepName);

        timer.start();

        customerRegisterPage.performParticipantSearchByOrganizationName(
                config.getParameter("AccreditationName"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' открывает карточку участника закупок в 'Реестре заказчиков' используя ссылку в результатах поиска")
    public void adminOpensParticipantCardByLinkInSearchResultsOnCustomerRegisterPage() throws Throwable {
        String stepName =
                "'Администратор' открывает карточку участника закупок в 'Реестре заказчиков' используя ссылку в результатах поиска";
        this.logStepName(stepName);

        timer.start();

        customerRegisterPage.clickOnParticipantFullNameLinkByText(config.getParameter("AccreditationName"));

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' проверяет наличие кнопки 'Создать счет' и нажимает ее если она присутствует$")
    public void adminChecksButtonCreateAccountPresenceAndClicksOnItIfPresent() throws Throwable {
        String stepName = "'Администратор' проверяет наличие кнопки 'Создать счет' и нажимает ее если она присутствует";
        this.logStepName(stepName);

        timer.start();

        organizationViewPage.checkButtonCreateAccountPresenceAndClicksOnItIfPresent();

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' нажимает на 'Сотрудники организации - перейти к списку' на странице просмотра организации во вкладке 'Карточка организации'")
    public void adminClicksOnControlOnOrganizationViewPage() throws Throwable {
        String stepName = "'Администратор' нажимает на 'Сотрудники организации перейти к списку' " +
                "на странице просмотра организации во вкладке 'Карточка организации'";

        this.logStepName(stepName);

        timer.start();

        organizationViewPage.clickOnOrganizationEmployees();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' ждет \"([^\"]*)\" мин для обновления статусов лотов$")
    public void adminWaitsMinutesForUpdateLotStatus(String time) throws Throwable {
        String stepName = String.format("'Администратор' ждет %s мин. для обновления статусов лотов", time);
        this.logStepName(stepName);

        timer.start();

        TimeUnit.MINUTES.sleep(Integer.parseInt(time));

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' выбирает \"([^\"]*)\"$")
    public void adminSelects(String action) throws Throwable {
        String stepName = String.format("'Администратор' выбирает '%s'", action);
        this.logStepName(stepName);

        timer.start();

        systemTasksPage.selectRowAndClickInTable(action);
        systemTasksPage.checkStatusOk(action);
        systemTasksPage.openAdminTab();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' выбирает 'Справочники-Реестр участников закупок'$")
    public void adminSelectsDirectoriesRegisterParticipantsOfPurchases() throws Throwable {
        String stepName = "'Администратор' выбирает 'Справочники-Реестр участников закупок'";
        this.logStepName(stepName);

        timer.start();

        commonAdminPage.goToRegistryOfParticipantsPage();

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' выбирает 'Реестр заказчиков' в разделе меню 'Справочники'")
    public void adminClicksReferencesLink() throws Throwable {
        String stepName = "'Администратор' выбирает 'Реестр заказчиков' в разделе меню 'Справочники'";
        this.logStepName(stepName);

        timer.start();

        commonAdminPage.goToCustomerRegisterPage();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' проверяет наличие кнопки 'Связать с закупкой в ЕИС' на карточке закупки$")
    public void adminChecksThatLinkToEisPurchaseButtonExist() {
        String stepName = "'Администратор' проверяет наличие кнопки 'Связать с закупкой в ЕИС' на карточке закупки";
        this.logStepName(stepName);

        timer.start();

        purchaseTradeViewPage.checkThatLinkToEisPurchaseButtonExist();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' связывает опубликованную на площадке закупку 'Конкурентная закупка - Конкурс' с закупкой в ЕИС$")
    public void adminLinksPublishedCompetitiveProcurementTenderPurchaseWithEisPurchaseUsingXml() throws Throwable {
        String stepName = "'Администратор' связывает опубликованную на площадке закупку " +
                "'Конкурентная закупка - Конкурс' с закупкой в ЕИС";
        this.logStepName(stepName);

        timer.start();

        //--------------------------------------------------------------------------------------------------------------
        // Генерация .xml-ответа от ЕИС
        CreateEisPublishedCompetitiveProcurementTenderNotice xmlGenerator =
                new CreateEisPublishedCompetitiveProcurementTenderNotice();
        String xml = xmlGenerator.getModel("CompetitiveProcurementTender223XMLFromEISModel");
        xml = xmlGenerator.updateModel(xml);
        Assert.assertTrue("[ОШИБКА]: не удалось записать на диск файл с .xml-ответом от ЕИС",
                xmlGenerator.putModel(xml, "CompetitiveProcurementTenderNotice"));
        //--------------------------------------------------------------------------------------------------------------
        purchaseTradeViewPage.clickOnLinkToEisPurchaseButton();
        //--------------------------------------------------------------------------------------------------------------
        // Работа в диалоговом окне [Связать закупку № XXXXX с закупкой в ЕИС]
        LinkPurchaseWithNoToEisDialog linkPurchaseWithNoToEisDialog = new LinkPurchaseWithNoToEisDialog(driver);
        linkPurchaseWithNoToEisDialog.ifDialogOpened().setXmlOfPurchaseField(xml).clickOnLinkButton();
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' связывает опубликованную на площадке закупку 'Конкурентная закупка - Конкурс заявка в двух частях' с закупкой в ЕИС$")
    public void adminLinksPublishedCompetitiveProcurementTenderApplicationInTwoPartsPurchaseWithEisPurchaseUsingXml() throws Throwable {
        String stepName = "'Администратор' связывает опубликованную на площадке закупку " +
                "'Конкурентная закупка - Конкурс (заявка в двух частях)' с закупкой в ЕИС";
        this.logStepName(stepName);

        timer.start();

        //--------------------------------------------------------------------------------------------------------------
        // Генерация .xml-ответа от ЕИС
        CreateEisPublishedCompetitiveProcurementTenderApplicationInTwoPartsNotice xmlGenerator =
                new CreateEisPublishedCompetitiveProcurementTenderApplicationInTwoPartsNotice();
        String xml = xmlGenerator.
                getModel("CompetitiveProcurementTenderApplicationInTwoPart223XMLFromEISModel");
        xml = xmlGenerator.updateModel(xml);
        Assert.assertTrue("[ОШИБКА]: не удалось записать на диск файл с .xml-ответом от ЕИС",
                xmlGenerator.putModel(xml, "CompetitiveProcurementTenderApplicationInTwoPart223XMLFromEISModel"));
        //--------------------------------------------------------------------------------------------------------------
        purchaseTradeViewPage.clickOnLinkToEisPurchaseButton();
        //--------------------------------------------------------------------------------------------------------------
        // Работа в диалоговом окне [Связать закупку № XXXXX с закупкой в ЕИС]
        LinkPurchaseWithNoToEisDialog linkPurchaseWithNoToEisDialog = new LinkPurchaseWithNoToEisDialog(driver);
        linkPurchaseWithNoToEisDialog.ifDialogOpened().setXmlOfPurchaseField(xml).clickOnLinkButton();
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' связывает опубликованную на площадке закупку 'Конкурентная закупка - Аукцион' с закупкой в ЕИС$")
    public void adminLinksPublishedCompetitiveAuctionWithEisPurchaseUsingXml() throws Throwable {
        String stepName = "'Администратор' связывает опубликованную на площадке закупку " +
                "'Конкурентная закупка - Аукцион' с закупкой в ЕИС";
        this.logStepName(stepName);

        timer.start();

        //--------------------------------------------------------------------------------------------------------------
        // Генерация .xml-ответа от ЕИС
        CreateEisPublishedCompetitiveProcurementAuctionNotice xmlGenerator =
                new CreateEisPublishedCompetitiveProcurementAuctionNotice();
        String xml = xmlGenerator.getModel("CompetitiveProcurementAuction23XMLFromEISModel");
        xml = xmlGenerator.updateModel(xml);
        Assert.assertTrue("[ОШИБКА]: не удалось записать на диск файл с .xml-ответом от ЕИС",
                xmlGenerator.putModel(xml, "CompetitiveProcurementAuction223XMLFromEIS"));
        //--------------------------------------------------------------------------------------------------------------
        purchaseTradeViewPage.clickOnLinkToEisPurchaseButton();
        //--------------------------------------------------------------------------------------------------------------
        // Работа в диалоговом окне [Связать закупку № XXXXX с закупкой в ЕИС]
        LinkPurchaseWithNoToEisDialog linkPurchaseWithNoToEisDialog = new LinkPurchaseWithNoToEisDialog(driver);
        linkPurchaseWithNoToEisDialog.ifDialogOpened().setXmlOfPurchaseField(xml).clickOnLinkButton();
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' связывает опубликованную на площадке закупку 'Конкурентная закупка - Аукцион с несколькими лотами' с закупкой в ЕИС$")
    public void adminLinksPublishedCompetitiveAuctionWithSeveralLotsWithEisPurchaseUsingXml() throws Throwable {
        String stepName = "'Администратор' связывает опубликованную на площадке закупку " +
                "'Конкурентная закупка - Аукцион с несколькими лотами' с закупкой в ЕИС";
        this.logStepName(stepName);

        timer.start();

        //--------------------------------------------------------------------------------------------------------------
        // Генерация .xml-ответа от ЕИС
        CreateEisPublishedCompetitiveProcurementAuctionWithSeveralLotsNotice xmlGenerator =
                new CreateEisPublishedCompetitiveProcurementAuctionWithSeveralLotsNotice();
        String xml = xmlGenerator.getModel("CompetitiveProcurementAuctionWithSeveralLots223XMLFromEISModel");
        xml = xmlGenerator.updateModel(xml);
        Assert.assertTrue("[ОШИБКА]: не удалось записать на диск файл с .xml-ответом от ЕИС",
                xmlGenerator.putModel(xml, "CompetitiveProcurementAuctionWithSeveralLots223XMLFromEIS"));
        //--------------------------------------------------------------------------------------------------------------
        purchaseTradeViewPage.clickOnLinkToEisPurchaseButton();
        //--------------------------------------------------------------------------------------------------------------
        // Работа в диалоговом окне [Связать закупку № XXXXX с закупкой в ЕИС]
        LinkPurchaseWithNoToEisDialog linkPurchaseWithNoToEisDialog = new LinkPurchaseWithNoToEisDialog(driver);
        linkPurchaseWithNoToEisDialog.ifDialogOpened().setXmlOfPurchaseField(xml).clickOnLinkButton();
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' связывает опубликованную на площадке закупку 'Конкурентная закупка - Аукцион заявка в двух частях' с закупкой в ЕИС$")
    public void adminLinksPublishedCompetitiveAuctionApplicationInTwoPartWithEisPurchaseUsingXml() throws Throwable {
        String stepName = "'Администратор' связывает опубликованную на площадке закупку " +
                "'Конкурентная закупка - Аукцион с несколькими лотами' с закупкой в ЕИС";
        this.logStepName(stepName);

        timer.start();

        //--------------------------------------------------------------------------------------------------------------
        // Генерация .xml-ответа от ЕИС
        CreateEisPublishedCompetitiveProcurementAuctionApplicationInTwoPartsNotice xmlGenerator =
                new CreateEisPublishedCompetitiveProcurementAuctionApplicationInTwoPartsNotice();
        String xml = xmlGenerator.getModel("CompetitiveProcurementAuctionApplicationInTwoPart223PurchaseNoticeModel");
        xml = xmlGenerator.updateModel(xml);
        Assert.assertTrue("[ОШИБКА]: не удалось записать на диск файл с .xml-ответом от ЕИС",
                xmlGenerator.putModel(xml, "CompetitiveProcurementAuctionApplicationInTwoPart223XMLFromEIS"));
        //--------------------------------------------------------------------------------------------------------------
        purchaseTradeViewPage.clickOnLinkToEisPurchaseButton();
        //--------------------------------------------------------------------------------------------------------------
        // Работа в диалоговом окне [Связать закупку № XXXXX с закупкой в ЕИС]
        LinkPurchaseWithNoToEisDialog linkPurchaseWithNoToEisDialog = new LinkPurchaseWithNoToEisDialog(driver);
        linkPurchaseWithNoToEisDialog.ifDialogOpened().setXmlOfPurchaseField(xml).clickOnLinkButton();
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' связывает опубликованную на площадке закупку 'Конкурентная закупка - Запрос котировок' с закупкой в ЕИС$")
    public void adminLinksPublishedCompetitiveProcurementRequestForQuotesPurchaseWithEisPurchaseUsingXml() throws Throwable {
        String stepName = "'Администратор' связывает опубликованную на площадке закупку " +
                "'Конкурентная закупка - Запрос котировок' с закупкой в ЕИС";
        this.logStepName(stepName);

        timer.start();

        //--------------------------------------------------------------------------------------------------------------
        // Генерация .xml-ответа от ЕИС
        CreateEisPublishedCompetitiveProcurementRequestForQuotesNotice xmlGenerator =
                new CreateEisPublishedCompetitiveProcurementRequestForQuotesNotice();
        String xml = xmlGenerator.getModel("CompetitiveProcurementRequestForQuotes223XMLFromEISModel");
        xml = xmlGenerator.updateModel(xml);
        Assert.assertTrue("[ОШИБКА]: не удалось записать на диск файл с .xml-ответом от ЕИС",
                xmlGenerator.putModel(xml, "CompetitiveProcurementRequestForQuotes"));
        //--------------------------------------------------------------------------------------------------------------
        purchaseTradeViewPage.clickOnLinkToEisPurchaseButton();
        //--------------------------------------------------------------------------------------------------------------
        // Работа в диалоговом окне [Связать закупку № XXXXX с закупкой в ЕИС]
        LinkPurchaseWithNoToEisDialog linkPurchaseWithNoToEisDialog = new LinkPurchaseWithNoToEisDialog(driver);
        linkPurchaseWithNoToEisDialog.ifDialogOpened().setXmlOfPurchaseField(xml).clickOnLinkButton();
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' связывает опубликованную на площадке закупку 'Неконкурентная закупка - Аукцион' с закупкой в ЕИС$")
    public void adminLinksPublishedUncompetitiveProcurementRequestForAuctionWithEisPurchaseUsingXml() throws Throwable {
        String stepName = "'Администратор' связывает опубликованную на площадке закупку " +
                "'Неконкурентная закупка - Аукцион' с закупкой в ЕИС";
        this.logStepName(stepName);

        timer.start();

        //--------------------------------------------------------------------------------------------------------------
        // Генерация .xml-ответа от ЕИС
        CreateEisPublishedUncompetitiveProcurementAuctionNotice xmlGenerator =
                new CreateEisPublishedUncompetitiveProcurementAuctionNotice();
        String xml = xmlGenerator.getModel("UncompetitiveProcurementAuction223XMLFromEISModel");
        xml = xmlGenerator.updateModel(xml);
        Assert.assertTrue("[ОШИБКА]: не удалось записать на диск файл с .xml-ответом от ЕИС",
                xmlGenerator.putModel(xml, "UncompetitiveProcurementAuction"));
        //--------------------------------------------------------------------------------------------------------------
        purchaseTradeViewPage.clickOnLinkToEisPurchaseButton();
        //--------------------------------------------------------------------------------------------------------------
        // Работа в диалоговом окне [Связать закупку № XXXXX с закупкой в ЕИС]
        LinkPurchaseWithNoToEisDialog linkPurchaseWithNoToEisDialog = new LinkPurchaseWithNoToEisDialog(driver);
        linkPurchaseWithNoToEisDialog.ifDialogOpened().setXmlOfPurchaseField(xml).clickOnLinkButton();
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' связывает опубликованную на площадке закупку 'Неконкурентная закупка - Конкурс' с закупкой в ЕИС$")
    public void adminLinksPublishedUncompetitiveProcurementRequestForTenderWithEisPurchaseUsingXml() throws Throwable {
        String stepName = "'Администратор' связывает опубликованную на площадке закупку " +
                "'Неконкурентная закупка - Конкурс' с закупкой в ЕИС";
        this.logStepName(stepName);

        timer.start();

        //--------------------------------------------------------------------------------------------------------------
        // Генерация .xml-ответа от ЕИС
        CreateEisPublishedUncompetitiveProcurementTenderNotice xmlGenerator =
                new CreateEisPublishedUncompetitiveProcurementTenderNotice();
        String xml = xmlGenerator.getModel("UncompetitiveProcurementTender223XMLFromEISModel");
        xml = xmlGenerator.updateModel(xml);
        Assert.assertTrue("[ОШИБКА]: не удалось записать на диск файл с .xml-ответом от ЕИС",
                xmlGenerator.putModel(xml, "UncompetitiveProcurementTender"));
        //--------------------------------------------------------------------------------------------------------------
        purchaseTradeViewPage.clickOnLinkToEisPurchaseButton();
        //--------------------------------------------------------------------------------------------------------------
        // Работа в диалоговом окне [Связать закупку № XXXXX с закупкой в ЕИС]
        LinkPurchaseWithNoToEisDialog linkPurchaseWithNoToEisDialog = new LinkPurchaseWithNoToEisDialog(driver);
        linkPurchaseWithNoToEisDialog.ifDialogOpened().setXmlOfPurchaseField(xml).clickOnLinkButton();
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' связывает опубликованную на площадке закупку 'Неконкурентная закупка - Предварительный отбор с продлением и несколькими лотами' с закупкой в ЕИС$")
    public void adminLinksPublishedUncompetitivePreselectionWithExtensionAndSeveralLotsWithEisPurchaseUsingXml() throws Throwable {
        String stepName = "'Администратор' связывает опубликованную на площадке закупку " +
                "'Неконкурентная закупка - Предварительный отбор ( с продлением ) и несколькими лотами' с закупкой в ЕИС";
        this.logStepName(stepName);

        timer.start();

        //--------------------------------------------------------------------------------------------------------------
        // Генерация .xml-ответа от ЕИС
        CreateEisPublishedUncompetitivePreselectionWithExtensionAndSeveralLotsNotice xmlGenerator =
                new CreateEisPublishedUncompetitivePreselectionWithExtensionAndSeveralLotsNotice();
        String xml = xmlGenerator.
                getModel("UncompetitiveProcurementPreselectionWithExtensionAndSeveralLots223XMLFromEISModel");
        xml = xmlGenerator.updateModel(xml);
        Assert.assertTrue("[ОШИБКА]: не удалось записать на диск файл с .xml-ответом от ЕИС",
                xmlGenerator.putModel(xml, "UncompetitiveProcurementPreselectionWithExtensionAndSeveralLots"));
        //--------------------------------------------------------------------------------------------------------------
        purchaseTradeViewPage.clickOnLinkToEisPurchaseButton();
        //--------------------------------------------------------------------------------------------------------------
        // Работа в диалоговом окне [Связать закупку № XXXXX с закупкой в ЕИС]
        LinkPurchaseWithNoToEisDialog linkPurchaseWithNoToEisDialog = new LinkPurchaseWithNoToEisDialog(driver);
        linkPurchaseWithNoToEisDialog.ifDialogOpened().setXmlOfPurchaseField(xml).clickOnLinkButton();
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);
    }

    @And("^'Администратор' связывает опубликованную на площадке закупку 'Неконкурентная закупка - Запрос предложений' с закупкой в ЕИС$")
    public void adminLinksPublishedUncompetitiveProcurementRequestForApplicationRequestWithEisPurchaseUsingXml() throws Throwable {
        String stepName = "'Администратор' связывает опубликованную на площадке закупку " +
                "'Неконкурентная закупка - Запрос предложений' с закупкой в ЕИС";
        this.logStepName(stepName);

        timer.start();

        //--------------------------------------------------------------------------------------------------------------
        // Генерация .xml-ответа от ЕИС
        CreateEisPublishedUncompetitiveProcurementApplicationRequestNotice xmlGenerator =
                new CreateEisPublishedUncompetitiveProcurementApplicationRequestNotice();
        String xml = xmlGenerator.getModel("UncompetitiveProcurementApplicationRequest223XMLFromEISModel");
        xml = xmlGenerator.updateModel(xml);
        Assert.assertTrue("[ОШИБКА]: не удалось записать на диск файл с .xml-ответом от ЕИС",
                xmlGenerator.putModel(xml, "UncompetitiveApplicationRequestTender"));
        //--------------------------------------------------------------------------------------------------------------
        purchaseTradeViewPage.clickOnLinkToEisPurchaseButton();
        //--------------------------------------------------------------------------------------------------------------
        // Работа в диалоговом окне [Связать закупку № XXXXX с закупкой в ЕИС]
        LinkPurchaseWithNoToEisDialog linkPurchaseWithNoToEisDialog = new LinkPurchaseWithNoToEisDialog(driver);
        linkPurchaseWithNoToEisDialog.ifDialogOpened().setXmlOfPurchaseField(xml).clickOnLinkButton();
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' очищает поле 'Состояние' на странице 'Запросы сотрудников'")
    public void adminCleansStateFieldOnEmployeeRequestListPage() throws Throwable {
        String stepName = "'Администратор' очищает поле 'Состояние' на странице 'Запросы сотрудников'";
        this.logStepName(stepName);

        timer.start();

        employeeRequestListPage.cleansMultiSelectField("Состояние - Х");

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' заполняет поле 'Наименовение организации' на странице 'Запросы сотрудников'")
    public void adminSetsOrganizationNameOnEmployeeRequestListPage() throws Throwable {
        String stepName = "'Администратор' заполняет поле 'Наименовение организации' на странице 'Запросы сотрудников'";
        this.logStepName(stepName);

        timer.start();

        employeeRequestListPage.setFieldClearClickAndSendKeys("Наименование организации",
                config.getParameter("OldCertificateName"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' нажимает на кнопку {string} на странице 'Запросы сотрудников'")
    public void adminClicksOnButtonOnEmployeeRequestListPage(String buttonName) throws Throwable {
        String stepName = String.
                format("'Администратор' нажимает на кнопку '%s' на странице 'Запросы сотрудников'", buttonName);
        this.logStepName(stepName);

        timer.start();

        employeeRequestListPage.clickOnButtonByName(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет количество строк {int} в таблице 'Список запросов'")
    public void adminChecksNumberOfRowsFromRequestListTableOnRequestListPage(int rows) throws Throwable {
        String stepName = String.
                format("'Администратор' проверяет количество строк '%s' в таблице 'Список запросов'", rows);
        this.logStepName(stepName);

        timer.start();

        employeeRequestListPage.checkNumberOfRowsFromRequestListTable(rows);

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' выбирает сгенерированного работника для организации")
    public void adminSelectsGenerateEmployeeOnRequestListPage() throws Throwable {
        String stepName = "'Администратор' выбирает сгенерированного пользователя для организации";
        this.logStepName(stepName);

        timer.start();

        employeeRequestListPage.selectEmployee(config.getParameter("EmployeeUserName")); //Выбираем сотрудника в столбце сотрудники

        //--------------------------------------------------------------------------------------------------------------
        config.switchToNewWindowInBrowser();
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет поле 'Статус' {string} на странице 'Заявка на добавление пользователя'")
    public void adminChecksFieldStateOnEmployeeRequestInfoPage(String status) {
        String stepName = String.
                format("'Администратор' проверяет поле 'Статус' '%s'на странице 'Заявка на добавление пользователя'",
                        status);
        this.logStepName(stepName);

        timer.start();

        employeeRequestInfoPage.checkField(status, "Статус");

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет поле 'Фамилия' на странице 'Заявка на добавление пользователя'")
    public void adminChecksFieldLastNameOnEmployeeRequestInfoPage() {
        String stepName = "'Администратор' проверяет поле 'Фамилия' на странице 'Заявка на добавление пользователя'";
        this.logStepName(stepName);

        timer.start();

        String lastName = config.getParameter("EmployeeUserName").split(" ")[0];
        employeeRequestInfoPage.checkField(lastName, "Фамилия");

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет поле 'Имя' на странице 'Заявка на добавление пользователя'")
    public void adminChecksFieldFirstNameOnEmployeeRequestInfoPage() {
        String stepName = "'Администратор' проверяет поле 'Имя' на странице 'Заявка на добавление пользователя'";
        this.logStepName(stepName);

        timer.start();

        String firstName = config.getParameter("EmployeeUserName").split(" ")[1];
        employeeRequestInfoPage.checkField(firstName, "Имя");

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет поле 'Основной e-mail для уведомлений заказчика' на странице 'Заявка на добавление пользователя'")
    public void adminChecksFieldCustomerEmailOnEmployeeRequestInfoPage() {
        String stepName =
                "'Администратор' проверяет поле 'Основной e-mail для уведомлений заказчика' на странице 'Заявка на добавление пользователя'";
        this.logStepName(stepName);

        timer.start();

        String firstName = config.getParameter("SupplierEmail");
        employeeRequestInfoPage.checkField(firstName, "Основной e-mail для уведомлений заказчика");

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет поле 'Основной e-mail для уведомлений поставщика' на странице 'Заявка на добавление пользователя'")
    public void adminChecksFieldSupplierEmailOnEmployeeRequestInfoPage() {
        String stepName =
                "'Администратор' проверяет поле 'Основной e-mail для уведомлений поставщика' на странице 'Заявка на добавление пользователя'";
        this.logStepName(stepName);

        timer.start();

        String firstName = config.getParameter("SupplierEmail");
        employeeRequestInfoPage.checkField(firstName, "Основной e-mail для уведомлений поставщика");

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет поле 'Имя пользователя - логин' на странице 'Заявка на добавление пользователя'")
    public void adminChecksFieldLoginOnEmployeeRequestInfoPage() {
        String stepName =
                "'Администратор' проверяет поле 'Имя пользователя (логин)' на странице 'Заявка на добавление пользователя'";
        this.logStepName(stepName);

        timer.start();

        String firstName = config.getParameter("EmployeeRequestUserLogin");
        employeeRequestInfoPage.checkField(firstName, "Имя пользователя (логин)");

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' нажимает на кнопку {string} на странице 'Заявка на добавление пользователя'")
    public void adminClicksOnButtonOnEmployeeRequestInfoPage(String buttonName) throws Exception {
        String stepName = String.format("'Администратор' нажимает на кнопку '%s' на странице 'Заявка на добавление пользователя'", buttonName);
        this.logStepName(stepName);

        timer.start();

        employeeRequestInfoPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет количество строк {int} в таблице 'Документы пользователя' на странице 'Заявка на добавление пользователя'")
    public void adminChecksNumberOfRowsFromUserDocumentsTableOnRequestListPage(int rows) throws Throwable {
        String stepName = String.
                format("'Администратор' проверяет количество строк '%s' в таблице 'Список запросов'", rows);
        this.logStepName(stepName);

        timer.start();

        employeeRequestInfoPage.checkNumberOfRowsFromUserDocumentsTable(rows);

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' нажимает на кнопку {string} странице 'Информация о пользователе'")
    public void adminClicksOnButtonOnEmployeeViewPage(String buttonName) throws Throwable {
        String stepName = String.
                format("'Администратор' нажимает на кнопку '%s' странице 'Информация о пользователе'", buttonName);
        this.logStepName(stepName);

        timer.start();

        employeeViewPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' нажимает на кнопку {string} на странице 'Заявка на изменение информации о пользователе'")
    public void adminClicksOnButtonOnEmployeeRequestPage(String buttonName) throws Throwable {
        String stepName = String.format("'Администратор' нажимает на кнопку '%s' на странице " +
                "'Заявка на изменение информации о пользователе'", buttonName);
        this.logStepName(stepName);

        timer.start();

        employeeRequestPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет количество строк {string} в таблице 'Сертификаты' на странице 'Заявка на изменение информации о пользователе'")
    public void adminChecksRowsInCertificatesTableOnEmployeeRequestPage(String rows) throws Throwable {
        String stepName = String.format("'Заказчик' проверяет количество строк '%s' в таблице 'Сертификаты' " +
                "на странице 'Заявка на изменение информации о пользователе'", rows);
        this.logStepName(stepName);

        timer.start();

        employeeRequestPage.checkNumberOfRowsInCertificatesTable(rows);

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' заполняет поле {string} значением {string} на странице 'Заявка на изменение информации о пользователе'")
    public void adminFillsFieldByName(String fieldName, String value) throws Throwable {
        String stepName = String.format("'Администратор' заполняет поле '%s' значением '%s' " +
                "на странице 'Заявка на изменение информации о пользователе'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        employeeRequestPage.setFieldClearClickAndSendKeys(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' выбирает новый сертификат для редактирования информации о пользователе")
    public void adminChoosesNewCertificateForUserEdition() throws Throwable {
        String stepName = "'Администратор' выбирает новый сертификат для редактирования информации о пользователе";
        this.logStepName(stepName);

        timer.start();

        //Выполняется сохранение CertName и потом его пересохранение,
        // так как выбор сертификата в окне "Выберите сертификат" происходит по параметру "CertName"
        String organizationName = config.getParameter("CertificateName");
        String certName = config.getParameter("CertName");
        config.setParameter("CertName", organizationName);

        OperatorCertificateSelector operatorCertificateSelector = new OperatorCertificateSelector();
        operatorCertificateSelector.selectCertificate(driver);

        config.setParameter("CertName", certName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет поле 'Текущий сертификат пользователя' на странице 'Заявка на добавление пользователя в организацию'")
    public void adminChecksSelectedCertificateInOrganizationRequestPage() {
        String stepName =
                "'Администратор' проверяет поле 'Текущий сертификат пользователя' на странице " +
                        "'Заявка на добавление пользователя в организацию'";
        timer.start();

        organizationRequestPage.verifyFieldContent("Текущий сертификат пользователя",
                config.getParameter("OldCertName"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет поле 'Фамилия' на странице 'Заявка на добавление пользователя в организацию'")
    public void adminChecksLastNameInOrganizationRequestPage() {
        String stepName =
                "'Администратор' проверяет поле 'Фамилия' на странице 'Заявка на добавление пользователя в организацию'";

        timer.start();

        organizationRequestPage.verifyFieldContent("Фамилия",
                config.getParameter("CertName").split(" ")[1]);

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' проверяет поле 'Имя' на странице 'Заявка на добавление пользователя в организацию'")
    public void adminChecksFirstNameInOrganizationRequestPage() {
        String stepName =
                "'Администратор' проверяет поле 'Имя' на странице 'Заявка на добавление пользователя в организацию'";

        timer.start();

        organizationRequestPage.verifyFieldContent("Имя",
                config.getParameter("CertName").split(" ")[2]);

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' заполняет поле 'Номер телефона' на странице 'Заявка на добавление пользователя в организацию'")
    public void adminSetsPhoneNumberFieldInUserPersonalDataOnOrganizationRequestPage() {
        String stepName = "'Администратор' заполняет поле 'Номер телефона' на странице " +
                "'Заявка на добавление пользователя в организацию'";

        timer.start();

        organizationRequestPage.setPhoneNumberFieldUserPersonalData();

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' заполняет поле 'Адрес эл. почты для ув. заказчика' на странице 'Заявка на добавление пользователя в организацию'")
    public void adminSetsCustomerContactEmailOnOrganizationRequestPage() {
        String stepName =
                "'Администратор' заполняет поле 'Адрес эл. почты для ув. заказчика' на странице " +
                        "'Заявка на добавление пользователя в организацию''";

        timer.start();

        config.setParameter("CustomerEmail",
                "CustomerEmail" + timer.getCurrentDateTime("ddMMyyyyHHmmSS") + "@example.com");
        organizationRequestPage.
                setField("Адрес электронной почты для уведомлений заказчика",
                        config.getParameter("CustomerEmail"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' заполняет поле 'Адрес эл. почты для ув. поставщика' на странице 'Заявка на добавление пользователя в организацию'")
    public void adminSetsSupplierContactEmailOnOrganizationRequestPage() {
        String stepName =
                "'Администратор' заполняет поле 'Адрес эл. почты для ув. поставщика' на странице " +
                        "'Заявка на добавление пользователя в организацию''";

        timer.start();

        config.setParameter("SupplierEmail",
                "SupplierEmail" + timer.getCurrentDateTime("ddMMyyyyHHmmSS") + "@example.com");
        organizationRequestPage.
                setField("Адрес электронной почты для уведомлений поставщика",
                        config.getParameter("SupplierEmail"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' заполняет поле 'Имя пользователя - логин' на странице 'Заявка на добавление пользователя в организацию'")
    public void adminFillsLoginTextFieldOnOrganizationRequestPage() {
        String stepName =
                "'Администратор' заполняет поле 'Имя пользователя - логин' на странице " +
                        "'Заявка на добавление пользователя в организацию'";
        this.logStepName(stepName);

        timer.start();

        config.setParameter("EmployeeRequestUserLogin",
                "login" + timer.getCurrentDateTime("ddMMyyyyHHmmSS"));
        organizationRequestPage.setField("Имя пользователя (логин)", config.getParameter("EmployeeRequestUserLogin"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' заполняет поле 'Пароль' на странице 'Заявка на добавление пользователя в организацию'")
    public void adminFillsPasswordTextFieldOnOrganizationRequestPage() {
        String stepName =
                "'Администратор' заполняет поле 'Пароль' на странице 'Заявка на добавление пользователя в организацию'";
        this.logStepName(stepName);

        timer.start();

        organizationRequestPage.setField("Пароль", config.getConfigParameter("UserPass"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' заполняет поле 'Подтверждение пароля' на странице 'Заявка на добавление пользователя в организацию'")
    public void adminFillsConfirmPasswordTextFieldOnOrganizationRequestPage() {
        String stepName =
                "'Администратор' заполняет поле 'Подтверждение пароля' на странице " +
                        "'Заявка на добавление пользователя в организацию'";
        this.logStepName(stepName);

        timer.start();

        organizationRequestPage.setField("Подтверждение пароля", config.getConfigParameter("UserPass"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' заполняет поле 'Кодовое слово' на странице 'Заявка на добавление пользователя в организацию'")
    public void adminFillsCodeWordTextFieldOnOrganizationRequestPage() {
        String stepName =
                "'Администратор' заполняет поле 'Кодовое слово' на странице " +
                        "'Заявка на добавление пользователя в организацию'";
        this.logStepName(stepName);

        timer.start();

        organizationRequestPage.setField("Кодовое слово", config.getConfigParameter("UserSecWord"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' прикрепляет 'Доверенность' к заявке на странице 'Заявка на добавление пользователя в организацию'")
    public void adminAttachesPowerOfAttorneyOnOrganizationRequestPage() throws Throwable {
        String stepName =
                "'Администратор' прикрепляет 'Доверенность' к заявке на странице " +
                        "'Заявка на добавление пользователя в организацию'";
        this.logStepName(stepName);

        timer.start();

        organizationRequestPage.atachePowerOfAttorney();

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' устанавливает флажок 'Опытный пользователь' на странице 'Заявка на добавление пользователя в организацию'")
    public void adminSetsCheckBoxValueOnOrganizationRequestPage() {
        String stepName =
                "'Администратор' устанавливает флажок 'Опытный пользователь' на странице " +
                        "'Заявка на добавление пользователя в организацию'";
        this.logStepName(stepName);

        timer.start();

        organizationRequestPage.setCheckBoxValue("Опытный пользователь");

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' нажимает на кнопку {string} на странице 'Заявка на добавление пользователя в организацию'")
    public void adminClicksOnButtonOnOrganizationRequestPage(String buttonName) throws Throwable {
        String stepName = String.format("'Заказчик' нажимает на кнопку '%s'" +
                " на странице 'Заявка на добавление пользователя в организацию'", buttonName);
        this.logStepName(stepName);

        timer.start();

        organizationRequestPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Администратор' меняет дату регистрации пользователя в {string} со смещением {string} от текущего времени")
    public void adminChangeRegistrationTime(String shiftType, String amountAsString) {
        String stepName =
                String.format("'Администратор' меняет дату регистрации пользователя в '%s' со смещением '%s' " +
                        "от текущего времени", shiftType, amountAsString);
        this.logStepName(stepName);

        timer.start();

        String registrationDateMinus2Weeks =
                timer.getDateTimeShiftWithDateFormat(null, shiftType, amountAsString, "yyyy-MM-dd HH:mm:ss");
        String organizationInn = config.getParameter("AccreditationInnValue");

        String sql1 =
                "SELECT RA.[CommonRequestId], CR.[INN], RA.[CreateDate] " +
                        "FROM [uac].[RequestActions] AS RA " +
                        "INNER JOIN [uac].[CommonRequests] AS CR " +
                        "ON RA.[CommonRequestId] = CR.[ID] " +
                        "WHERE CR.[INN] = '%s' AND RA.[RequestActionKindId]=3";
        String sql2 =
                "UPDATE RA " +
                        "SET RA.[CreateDate] = '%s' " +
                        "FROM [uac].[RequestActions] AS RA " +
                        "INNER JOIN [uac].[CommonRequests] AS CR " +
                        "ON RA.[CommonRequestId] = CR.[ID] " +
                        "WHERE CR.[INN] = '%s' AND RA.[RequestActionKindId]=3";

        List<String> fieldsToPrint1 = Arrays.asList("CommonRequestId", "INN", "CreateDate");

        DbOperations dbOperations = new DbOperations();
        dbOperations.
                connectToDatabase(config.getConfigParameter("SqlDatabaseName3")).
                executeSelectQuery(String.format(sql1, organizationInn), 1, fieldsToPrint1).
                executeUpdateQuery(String.format(sql2, registrationDateMinus2Weeks, organizationInn)).
                closeConnectionToDatabase();

        System.out.printf("[ИНФОРМАЦИЯ]: дата регистрации пользователя с ИНН: [%s]  изменено на: [%s].%n",
                organizationInn, registrationDateMinus2Weeks);

        timer.printStepTotalTime(stepName);
    }
}
