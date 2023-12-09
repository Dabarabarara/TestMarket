package ru.StepDefinitions.Customer.Partnership;

import Helpers.DbOperations;
import cucumber.api.java.en.And;
import ru.PageObjects.Customer.MyOrganization.*;
import ru.StepDefinitions.AbstractStepDefinitions;

import java.util.Arrays;
import java.util.List;

/**
 * Класс описывающий шаги работы Заказчика по установлению партнерских связей.
 * Created by Vladimir V. Klochkov on 25.12.2019.
 * Updated by Vladimir V. Klochkov on 10.01.2020.
 */
public class CustomerPartnershipStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final PartnersCredentialsInMyPurchasesPage partnersCredentialsInMyPurchasesPage =
            new PartnersCredentialsInMyPurchasesPage(driver);
    private final MyCredentialsInPartnersPurchasesPage myCredentialsInPartnersPurchasesPage =
            new MyCredentialsInPartnersPurchasesPage(driver);
    private final DelegationOrganizationAuthorityPage delegationOrganizationAuthorityPage =
            new DelegationOrganizationAuthorityPage(driver);
    private final PartnershipCardPage partnershipCardPage = new PartnershipCardPage(driver);
    private final PartnerRelationRequestPage partnerRelationRequestPage = new PartnerRelationRequestPage(driver);
    private final PartnerRelationRequestInfoPage partnerRelationRequestInfoPage =
            new PartnerRelationRequestInfoPage(driver);
    private final PartnerRelationRequestListPage partnerRelationRequestListPage =
            new PartnerRelationRequestListPage(driver);

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    @And("'Заказчик' проверяет наличие ранее установленных данным автотестом партнерских связей и удаляет их из базы данных")
    public void customerChecksForPresenceThisAutotestsPreviouslyEstablishedPartnershipsAndDeletesThemBySqlQuery() {
        String stepName = "'Заказчик' проверяет наличие ранее установленных данным автотестом партнерских связей и " +
                "удаляет их из базы данных";
        this.logStepName(stepName);

        timer.start();

        // Шаблоны SQL-запросов к базе данных
        String sqlSelectPattern =
                "SELECT * FROM [btr].[PartnerRelation] WHERE " +
                        "[OrganizationDonorId] = %s AND " +
                        "[OrganizationRecipientId] = %s AND " +
                        "[Comments] LIKE 'Автотест - наименование связи от ";
        String sqlDeletePattern =
                "DELETE [btr].[PartnerRelation] WHERE " +
                        "[OrganizationDonorId] = %s AND " +
                        "[OrganizationRecipientId] = %s AND " +
                        "[Comments] LIKE 'Автотест - наименование связи от ";

        // Список названий полей, значения которых требуется вывести в консоль
        List<String> fieldsToPrint = Arrays.asList(
                "Id", "BusinessOperatorId", "OrganizationDonorId", "OrganizationRecipientId", "Comments", "Accepted");

        // Формирование SQL-запросов для удаления прямой партнерской связи
        String customer1Id = config.getConfigParameter("CertificateCustomerRecipientId");
        String customer2Id = config.getConfigParameter("CertificateCustomer1RecipientId1");

        String sqlSelect1 = String.format(sqlSelectPattern, customer1Id, customer2Id) + "%'";
        String sqlDelete1 = String.format(sqlDeletePattern, customer1Id, customer2Id) + "%'";

        // Формирование SQL-запросов для удаления обратной партнерской связи
        String sqlSelect2 = String.format(sqlSelectPattern, customer2Id, customer1Id) + "%'";
        String sqlDelete2 = String.format(sqlDeletePattern, customer2Id, customer1Id) + "%'";

        // Удаление прямой и обратной партнерских связей
        DbOperations dbOperations = new DbOperations();
        dbOperations.
                connectToDatabase(config.getConfigParameter("SqlDatabaseName3")).
                executeDeleteQueryIfThereIsAnythingToDelete(sqlSelect1, sqlDelete1, fieldsToPrint).
                executeDeleteQueryIfThereIsAnythingToDelete(sqlSelect2, sqlDelete2, fieldsToPrint).
                closeConnectionToDatabase();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} на странице 'Полномочия партнеров в моих закупках'")
    public void customerClicksOnButtonByNameOnPartnersCredentialsInMyPurchasesPage(String buttonName) throws Throwable {
        String stepName = String.format(
                "'Заказчик' нажимает на кнопку '%s' на странице 'Полномочия партнеров в моих закупках'", buttonName);
        this.logStepName(stepName);

        timer.start();

        partnersCredentialsInMyPurchasesPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} на странице 'Делегирование доступа организации'")
    public void customerClicksOnButtonByNameOnDelegationOrganizationAuthorityPage(String buttonName) throws Throwable {
        String stepName = String.format(
                "'Заказчик' нажимает на кнопку '%s' на странице 'Делегирование доступа организации'", buttonName);
        this.logStepName(stepName);

        timer.start();

        delegationOrganizationAuthorityPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' указывает требуемую организацию в диалоговом окне 'Выбрать организацию'")
    public void customerChoosesOrganizationInSelectOrganizationDialog() throws Throwable {
        String stepName = "'Заказчик' указывает требуемую организацию в диалоговом окне 'Выбрать организацию'";
        this.logStepName(stepName);

        timer.start();

        String inn = config.getConfigParameter("CertificateCustomer1Inn1");
        //--------------------------------------------------------------------------------------------------------------
        // Работа в диалоговом окне [Выбрать организацию]
        SelectOrganizationDialog selectOrganizationDialog = new SelectOrganizationDialog(driver);
        selectOrganizationDialog.
                ifDialogOpened().
                clickOnButton("Очистить").
                setField("ИНН", inn).
                clickOnButton("Поиск").
                checkRowCountInSearchResults(1).
                verifyCellByColumnNameAndLineNumberForText("ИНН", "1", inn).
                setCheckBoxValue("Отметить организацию").
                storePartnerOrganizationNameInTestParameters("PartnerOrganizationName").
                clickOnButtonToCloseDialog("Выбрать");
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет значение поля 'Организация партнёр' на странице 'Делегирование доступа организации'")
    public void customerChecksValueInPartnerOrganizationFieldOnDelegationOrganizationAuthorityPage() {
        String stepName = "'Заказчик' проверяет значение поля 'Организация партнёр' на странице " +
                "'Делегирование доступа организации'";
        this.logStepName(stepName);

        timer.start();

        delegationOrganizationAuthorityPage.
                verifyField("Организация партнёр", config.getParameter("PartnerOrganizationName"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет невозможность редактирования поля 'Организация партнёр' на странице 'Делегирование доступа организации'")
    public void customerChecksImpossibilityToEditPartnerOrganizationFieldOnDelegationOrganizationAuthorityPage() {
        String stepName = "'Заказчик' проверяет невозможность редактирования поля 'Организация партнёр' на странице " +
                "'Делегирование доступа организации'";
        this.logStepName(stepName);

        timer.start();

        delegationOrganizationAuthorityPage.verifyFieldIsNotEditable("Организация партнёр");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Наименование связи' на странице 'Делегирование доступа организации' сгенерированным именем")
    public void customerFillsCommunicationNameFieldWithAutoGeneratedValueOnDelegationOrganizationAuthorityPage()
            throws Throwable {
        String stepName =
                "'Заказчик' заполняет поле 'Наименование связи' на странице 'Делегирование доступа организации' " +
                        "сгенерированным именем";
        this.logStepName(stepName);

        timer.start();

        String CustomerDelegationPartnershipName =
                String.format("Автотест - наименование связи от %s", timer.getCurrentDateTime("ddMMyyyyHHmmSS"));
        config.setParameter("CustomerDelegationPartnershipName", CustomerDelegationPartnershipName);
        delegationOrganizationAuthorityPage.setField("Наименование связи", CustomerDelegationPartnershipName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' устанавливает флажок {string} на странице 'Делегирование доступа организации'")
    public void customerSetsCheckBoxValueOnDelegationOrganizationAuthorityPage(String checkBoxName) {
        String stepName = String.format(
                "'Заказчик' устанавливает флажок '%s' на странице 'Делегирование доступа организации'", checkBoxName);
        this.logStepName(stepName);

        timer.start();

        delegationOrganizationAuthorityPage.verifyCheckBoxNotSelected(checkBoxName);
        delegationOrganizationAuthorityPage.setCheckBoxValue(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет значение поля 'Наименование связи' на странице 'Карточка партнёрской связи'")
    public void customerChecksValueInCommunicationNameFieldOnPartnershipCardPage() {
        String stepName =
                "'Заказчик' проверяет значение поля 'Наименование связи' на странице 'Карточка партнёрской связи";
        this.logStepName(stepName);

        timer.start();

        partnershipCardPage.
                verifyField("Наименование связи", config.getParameter("CustomerDelegationPartnershipName"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет значение поля 'Наименование связи' страницы 'Карточка партнёрской связи'")
    public void customerChecksValueInOrganizationCommunicationNameFieldOnPartnershipCardPage() {
        String stepName =
                "'Заказчик' проверяет значение поля 'Наименование связи' страницы 'Карточка партнёрской связи";
        this.logStepName(stepName);

        timer.start();

        partnershipCardPage.
                verifyField("Наименование связи", config.getParameter("CustomerRequestPartnershipName"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет значение поля 'ИНН' организации донора прав на странице 'Карточка партнёрской связи'")
    public void customerChecksValueInDonorInnFieldOnPartnershipCardPage() {
        String stepName = "'Заказчик' проверяет значение поля 'ИНН' организации донора прав на странице " +
                "'Карточка партнёрской связи'";
        this.logStepName(stepName);

        timer.start();

        partnershipCardPage.
                verifyField("ИНН организации донора", config.getConfigParameter("CertificateCustomerInn"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет значение поля 'ИНН' организации донора на странице 'Карточка партнёрской связи'")
    public void customerChecksValueInDonorOrganizationInnFieldOnPartnershipCardPage() {
        String stepName = "'Заказчик' проверяет значение поля 'ИНН' организации донора на странице " +
                "'Карточка партнёрской связи'";
        this.logStepName(stepName);

        timer.start();

        partnershipCardPage.
                verifyField("ИНН организации донора", config.getConfigParameter("CertificateCustomer1Inn1"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет значение поля 'ОГРН' организации донора прав на странице 'Карточка партнёрской связи'")
    public void customerChecksValueInDonorOgrnFieldOnPartnershipCardPage() {
        String stepName = "'Заказчик' проверяет значение поля 'ОГРН' организации донора прав на странице " +
                "'Карточка партнёрской связи'";
        this.logStepName(stepName);

        timer.start();

        partnershipCardPage.verifyField(
                "ОГРН организации донора", config.getConfigParameter("CertificateCustomerOgrn"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет значение поля 'ОГРН' организации донора на странице 'Карточка партнёрской связи'")
    public void customerChecksValueInDonorOrganizationOgrnFieldOnPartnershipCardPage() {
        String stepName = "'Заказчик' проверяет значение поля 'ОГРН' организации донора на странице " +
                "'Карточка партнёрской связи'";
        this.logStepName(stepName);

        timer.start();

        partnershipCardPage.verifyField(
                "ОГРН организации донора", config.getConfigParameter("CertificateCustomer1Ogrn1"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет значение поля 'ИНН' организации реципиента прав на странице 'Карточка партнёрской связи'")
    public void customerChecksValueInRecipientInnFieldOnPartnershipCardPage() {
        String stepName = "'Заказчик' проверяет значение поля 'ИНН' организации реципиента прав на странице " +
                "'Карточка партнёрской связи'";
        this.logStepName(stepName);

        timer.start();

        partnershipCardPage.verifyField(
                "ИНН организации реципиента", config.getConfigParameter("CertificateCustomer1Inn1"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет значение поля 'ИНН' организации реципиента на странице 'Карточка партнёрской связи'")
    public void customerChecksValueInRecipientOrganizationInnFieldOnPartnershipCardPage() {
        String stepName = "'Заказчик' проверяет значение поля 'ИНН' организации реципиента на странице " +
                "'Карточка партнёрской связи'";
        this.logStepName(stepName);

        timer.start();

        partnershipCardPage.verifyField(
                "ИНН организации реципиента", config.getConfigParameter("CertificateCustomerInn"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет значение поля 'ОГРН' организации реципиента прав на странице 'Карточка партнёрской связи'")
    public void customerChecksValueInRecipientOgrnFieldOnPartnershipCardPage() {
        String stepName = "'Заказчик' проверяет значение поля 'ОГРН' организации реципиента прав на странице " +
                "'Карточка партнёрской связи'";
        this.logStepName(stepName);

        timer.start();

        partnershipCardPage.verifyField(
                "ОГРН организации реципиента", config.getConfigParameter("CertificateCustomer1Ogrn1"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет значение поля 'ОГРН' организации реципиента на странице 'Карточка партнёрской связи'")
    public void customerChecksValueInRecipientOrganizationOgrnFieldOnPartnershipCardPage() {
        String stepName = "'Заказчик' проверяет значение поля 'ОГРН' организации реципиента на странице " +
                "'Карточка партнёрской связи'";
        this.logStepName(stepName);

        timer.start();

        partnershipCardPage.verifyField(
                "ОГРН организации реципиента", config.getConfigParameter("CertificateCustomerOgrn"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что флажок {string} {string} на странице 'Карточка партнёрской связи'")
    public void customerVerifiesCheckBoxStateOnPartnershipCardPage(String checkBoxName, String checkBoxState) {
        String stepName =
                String.format("'Заказчик' проверяет, что флажок '%s' '%s' на странице 'Карточка партнёрской связи'",
                        checkBoxName, checkBoxState);
        this.logStepName(stepName);

        timer.start();

        if (checkBoxState.equals("установлен")) partnershipCardPage.verifyCheckBoxSelected(checkBoxName);
        else partnershipCardPage.verifyCheckBoxNotSelected(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет возможность редактирования поля 'Наименование связи' на странице 'Карточка партнёрской связи'")
    public void customerChecksPossibilityToEditCommunicationNameFieldOnPartnershipCardPage() throws Throwable {
        String stepName = "'Заказчик' проверяет возможность редактирования поля 'Наименование связи' на странице " +
                "'Карточка партнёрской связи'";
        this.logStepName(stepName);

        timer.start();

        partnershipCardPage.verifyFieldIsEditable("Наименование связи");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет невозможность редактирования поля 'Наименование связи' на странице 'Карточка партнёрской связи'")
    public void customerChecksImpossibilityToEditCommunicationNameFieldOnPartnershipCardPage() {
        String stepName = "'Заказчик' проверяет невозможность редактирования поля 'Наименование связи' на странице " +
                "'Карточка партнёрской связи'";
        this.logStepName(stepName);

        timer.start();

        partnershipCardPage.verifyFieldIsNotEditable("Наименование связи");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} на странице 'Карточка партнёрской связи'")
    public void customerClicksOnButtonByNameOnPartnershipCardPage(String buttonName) throws Throwable {
        String stepName = String.format("'Заказчик' нажимает на кнопку '%s' на странице 'Карточка партнёрской связи'",
                buttonName);
        this.logStepName(stepName);

        timer.start();

        partnershipCardPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} на странице 'Мои полномочия в закупках партнеров'")
    public void customerClicksOnButtonByNameOnMyCredentialsInPartnersPurchasesPage(String buttonName) throws Throwable {
        String stepName = String.format(
                "'Заказчик' нажимает на кнопку '%s' на странице 'Мои полномочия в закупках партнеров'", buttonName);
        this.logStepName(stepName);

        timer.start();

        myCredentialsInPartnersPurchasesPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' ищет партнерскую связь по её полному наименованию на странице 'Мои полномочия в закупках партнеров'")
    public void customerSearchesPartnershipByFullNameOnMyCredentialsInPartnersPurchasesPage() throws Throwable {
        String stepName = "'Заказчик' ищет партнерскую связь по её полному наименованию на странице " +
                "'Мои полномочия в закупках партнеров'";
        this.logStepName(stepName);

        timer.start();

        myCredentialsInPartnersPurchasesPage.
                switchToTab("Активна").
                setField("Наименование связи", config.getParameter("CustomerDelegationPartnershipName")).
                clickOnButton("Поиск");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет результаты поиска партнерской связи на странице 'Мои полномочия в закупках партнеров'")
    public void customerChecksPartnershipSearchResultsOnMyCredentialsInPartnersPurchasesPage() {
        String stepName = "'Заказчик' проверяет результаты поиска партнерской связи на странице " +
                "'Мои полномочия в закупках партнеров'";
        this.logStepName(stepName);

        timer.start();

        myCredentialsInPartnersPurchasesPage.
                checkRowCountInSearchResults(1).
                checkLinkToPartnershipCardInSearchResults(config.getParameter("CustomerDelegationPartnershipName"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит по ссылке с наименованием партнерской связи на странице 'Мои полномочия в закупках партнеров'")
    public void customerGoesOnLinkWithPartnershipNameOnMyCredentialsInPartnersPurchasesPage() throws Throwable {
        String stepName = "'Заказчик' переходит по ссылке с наименованием партнерской связи на странице " +
                "'Мои полномочия в закупках партнеров'";
        this.logStepName(stepName);

        timer.start();

        myCredentialsInPartnersPurchasesPage.
                clickOnLinkToPartnershipCard(config.getParameter("CustomerDelegationPartnershipName"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} на странице 'Запрос на установление связи'")
    public void customerClicksOnButtonByNameOnPartnerRelationRequestPage(String buttonName) throws Throwable {
        String stepName = String.format(
                "'Заказчик' нажимает на кнопку '%s' на странице 'Запрос на установление связи'", buttonName);
        this.logStepName(stepName);

        timer.start();

        partnerRelationRequestPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет значение поля 'Организация партнёр' на странице 'Запрос на установление связи'")
    public void customerChecksValueInPartnerOrganizationFieldOnPartnerRelationRequestPage() {
        String stepName = "'Заказчик' проверяет значение поля 'Организация партнёр' на странице " +
                "'Запрос на установление связи'";
        this.logStepName(stepName);

        timer.start();

        partnerRelationRequestPage.
                verifyField("Организация партнёр", config.getParameter("PartnerOrganizationName"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет невозможность редактирования поля 'Организация партнёр' на странице 'Запрос на установление связи'")
    public void customerChecksImpossibilityToEditPartnerOrganizationFieldOnPartnerRelationRequestPage() {
        String stepName = "'Заказчик' проверяет невозможность редактирования поля 'Организация партнёр' на странице " +
                "'Запрос на установление связи'";
        this.logStepName(stepName);

        timer.start();

        partnerRelationRequestPage.verifyFieldIsNotEditable("Организация партнёр");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Наименование связи' на странице 'Запрос на установление связи' сгенерированным именем")
    public void customerFillsCommunicationNameFieldWithAutoGeneratedValueOnPartnerRelationRequestPage()
            throws Throwable {
        String stepName = "'Заказчик' заполняет поле 'Наименование связи' на странице 'Запрос на установление связи'" +
                " сгенерированным именем";
        this.logStepName(stepName);

        timer.start();

        String CustomerRequestPartnershipName =
                String.format("Автотест - наименование связи от %s", timer.getCurrentDateTime("ddMMyyyyHHmmSS"));
        config.setParameter("CustomerRequestPartnershipName", CustomerRequestPartnershipName);
        partnerRelationRequestPage.setField("Наименование связи", CustomerRequestPartnershipName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' устанавливает флажок {string} на странице 'Запрос на установление связи'")
    public void customerSetsCheckBoxValueOnPartnerRelationRequestPage(String checkBoxName) {
        String stepName = String.format(
                "'Заказчик' устанавливает флажок '%s' на странице 'Запрос на установление связи'", checkBoxName);
        this.logStepName(stepName);

        timer.start();

        partnerRelationRequestPage.verifyCheckBoxNotSelected(checkBoxName);
        partnerRelationRequestPage.setCheckBoxValue(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что созданный запрос отображается в таблице 'Список запросов на установление связи' на странице 'Исходящие запросы на добавление связи'")
    public void customerChecksThatTheJustCreatedRequestIsShownInSearchResultsOnPartnerRelationRequestListPage() {
        String stepName = "'Заказчик' проверяет, что созданный запрос отображается в таблице 'Список запросов на " +
                "установление связи' на странице 'Исходящие запросы на добавление связи'";
        this.logStepName(stepName);

        timer.start();

        partnerRelationRequestListPage.
                checkRowCountInSearchResults(1).
                verifyCellByNameInLineByNumberFromPartnerRelationRequestListTableForText
                        ("Столбец Название организации", "1",
                                config.getParameter("PartnerOrganizationName")).
                verifyCellByNameInLineByNumberFromPartnerRelationRequestListTableForText
                        ("Столбец Коментарий", "1",
                                config.getParameter("CustomerRequestPartnershipName"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' сохраняет номер созданного запроса из таблицы 'Список запросов на установление связи' на странице 'Исходящие запросы на добавление связи'")
    public void customerSavesRequestNumberInTestParametersOnPartnerRelationRequestListPage() {
        String stepName = "'Заказчик' сохраняет номер созданного запроса из таблицы " +
                "'Список запросов на установление связи' на странице 'Исходящие запросы на добавление связи'";
        this.logStepName(stepName);

        timer.start();

        partnerRelationRequestListPage.storeRequestPartnershipNumberInTestParameters();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет значение поля 'Номер запроса' на странице 'Запрос на установление связи-принять запрос или отказать в принятии'")
    public void customerChecksValueInRequestNumberFieldOnPartnerRelationRequestInfoPage() {
        String stepName = "'Заказчик' проверяет значение поля 'Номер запроса' на странице " +
                "'Запрос на установление связи-принять запрос или отказать в принятии'";
        this.logStepName(stepName);

        timer.start();

        partnerRelationRequestInfoPage.
                verifyField("Номер запроса", config.getParameter("CustomerRequestPartnershipNumber"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет значение поля 'Статус'-подтверждение на странице 'Запрос на установление связи-принять запрос или отказать в принятии'")
    public void customerChecksValueInStatusFieldOnPartnerRelationRequestInfoPage() {
        String stepName = "'Заказчик' проверяет значение поля 'Статус'-Подтверждение на странице " +
                "'Запрос на установление связи-принять запрос или отказать в принятии'";
        this.logStepName(stepName);

        timer.start();

        partnerRelationRequestInfoPage.verifyField("Статус", "Подтверждение");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} на странице 'Запрос на установление связи-принять запрос или отказать в принятии'")
    public void customerClicksOnButtonByNameOnPartnerRelationRequestInfoPage(String buttonName) throws Throwable {
        String stepName = String.format("'Заказчик' нажимает на кнопку '%s' на странице " +
                "'Запрос на установление связи-принять запрос или отказать в принятии'", buttonName);
        this.logStepName(stepName);

        timer.start();

        partnerRelationRequestInfoPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }
}
