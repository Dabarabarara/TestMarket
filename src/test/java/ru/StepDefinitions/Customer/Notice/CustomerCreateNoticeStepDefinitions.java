package ru.StepDefinitions.Customer.Notice;

import Helpers.Dictionary;
import Helpers.Timer;
import cucumber.api.java.en.And;
import ru.PageObjects.CommonDialogs.ConfirmAngularDialog;
import ru.PageObjects.CommonDialogs.InformationAngularDialog;
import ru.PageObjects.Customer.CommonCustomerPage;
import ru.PageObjects.Customer.CustomerMyPurchasesPage;
import ru.PageObjects.Customer.Notice.*;
import ru.PageObjects.Customer.Plans.ViewTradePlanPage;
import ru.StepDefinitions.AbstractStepDefinitions;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Класс описывающий шаги работы Заказчика по созданию извещения о новой закупке.
 * Created by Vladimir V. Klochkov on 10.02.2020.
 * Updated by Alexander S. Vasyurenko on 20.05.2021.
 */
public class CustomerCreateNoticeStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final CreateApprovalRequestDialog createApprovalRequestDialog = new CreateApprovalRequestDialog(driver);
    private final SendInformationToEisDialog sendInformationToEisDialog = new SendInformationToEisDialog(driver);
    private final ApprovalRequestSentDialog approvalRequestSentDialog = new ApprovalRequestSentDialog(driver);
    private final InformationAngularDialog informationAngularDialog = new InformationAngularDialog(driver);
    private final PurchaseNoticeDraftSuccessfullySavedDialog purchaseNoticeDraftSuccessfullySavedDialog =
            new PurchaseNoticeDraftSuccessfullySavedDialog(driver);
    private final CustomerSelectionDialog customerSelectionDialog = new CustomerSelectionDialog(driver);
    private final CustomerMyPurchasesPage customerMyPurchasesPage = new CustomerMyPurchasesPage(driver);
    private final CreateNoticePage createNoticePage = new CreateNoticePage(driver);
    private final ConfirmAngularDialog confirmAngularDialog = new ConfirmAngularDialog(driver);
    private final CommonCustomerPage commonCustomerPage = new CommonCustomerPage(driver);
    private final ViewTradePlanPage viewTradePlanPage = new ViewTradePlanPage(driver);
    private final ViewNoticePage viewNoticePage = new ViewNoticePage(driver);

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    // region Раздел [Редактирование извещения о закупке]
    @And("'Заказчик А' открывает страницу создания извещения для коммерческой закупки {string}")
    public void customerOpensCreateCommercialPurchaseNoticePageForPurchaseType(String purchaseMethod) throws Throwable {
        String stepName = String.format("'Заказчик А' открывает страницу создания извещения для коммерческой закупки '%s'",
                purchaseMethod);
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.waitLoadingImage();
        customerMyPurchasesPage.openCreateCommercialPurchaseNoticePage(purchaseMethod);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' открывает страницу создания извещения для конкурентной закупки {string}")
    public void customerOpensCreateCompetitivePurchaseNoticePageForPurchaseType(String purchaseMethod) throws Throwable {
        String stepName = String.format("'Заказчик А' открывает страницу создания извещения для конкурентной закупки '%s'",
                purchaseMethod);
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.waitLoadingImage();
        customerMyPurchasesPage.openCreateCompetitivePurchaseNoticePage(purchaseMethod);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' открывает страницу создания извещения для неконкурентной закупки {string}")
    public void customerOpensCreateNotCompetitivePurchaseNoticePageForPurchaseType(String purchaseMethod) throws Throwable {
        String stepName = String.format("'Заказчик А' открывает страницу создания извещения для неконкурентной закупки '%s'",
                purchaseMethod);
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.waitLoadingImage();
        customerMyPurchasesPage.openCreateNotCompetitivePurchaseNoticePage(purchaseMethod);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет сведения о процедуре: {string}")
    public void customerFillsProcedureDetails(String purchaseMethod) throws Throwable {
        String stepName = String.format("'Заказчик А' заполняет сведения о процедуре: '%s'", purchaseMethod);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.waitLoadingImage();
        createNoticePage.
                checkPageUrl(String.format("Формирование черновика извещения '%s' - Angular", purchaseMethod));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет сведения о полном электронном конкурсе:")
    public void customerFillsFullTenderDetails() throws Throwable {
        String stepName = "'Заказчик А' заполняет сведения о полном электронном конкурсе:";
        this.logStepName(stepName);

        timer.start();

        createNoticePage.waitLoadingImage();
        createNoticePage.verifyCreateOrEditTenderPageOpened(true);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет сведения о полном электронном конкурсе - конкурентная закупка:")
    public void customerFillsFullCompetitiveTenderDetails() throws Throwable {
        String stepName = "'Заказчик А' заполняет сведения о полном электронном конкурсе - конкурентная закупка:";
        this.logStepName(stepName);

        timer.start();

        createNoticePage.waitLoadingImage();
        createNoticePage.
                checkPageUrl("Создание черновика извещения 'Конкурентная закупка - Конкурс' - Angular");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет общие сведения о закупке для полного электронного аукциона с заявкой в двух частях")
    public void customerFillsFullAuctionInTwoPartsCommonPurchaseDetails() throws Throwable {
        String stepName = "'Заказчик А' заполняет общие сведения о закупке для полного электронного аукциона с " +
                "заявкой в двух частях";
        this.logStepName(stepName);

        timer.start();

        createNoticePage.setFieldClearClickAndSendKeys("Наименование закупки", String.format("%s от %s.",
                config.getConfigParameter("OnProdAuctionInTwoPartsNamePrefix"),
                timer.getCurrentDateTime("dd.MM.yyyy HH:mm:SS")));

        createNoticePage.setCheckBoxValue("Публикация итогового протокола");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет общие сведения о закупке для полного конкурса")
    public void customerFillsFullTenderCommonPurchaseDetails() throws Throwable {
        String stepName = "'Заказчик А' заполняет общие сведения о закупке для полного конкурса";
        this.logStepName(stepName);

        timer.start();

        Dictionary prefixes = new Dictionary();
        prefixes.add("Full Tender", config.getConfigParameter("TenderNamePrefix"));
        prefixes.add("Smoke Customer Personal Cabinet", config.getConfigParameter("OnProdTenderNamePrefix"));
        prefixes.add("Full Tender On Production", config.getConfigParameter("OnProdTenderNamePrefix"));

        createNoticePage.setFieldClearClickAndSendKeys("Наименование закупки", String.format("%s от %s.",
                prefixes.find(config.getParameter("TestType")),
                timer.getCurrentDateTime("dd.MM.yyyy HH:mm:SS")));
        createNoticePage.setCheckBoxValue("Публикация протокола открытия доступа");
        createNoticePage.setCheckBoxValue("Отдельные протоколы рассмотрения и оценки заявок");
        createNoticePage.setCheckBoxValue("Публикация итогового протокола");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' переходит к разделу {string}:")
    public void customerGoesToBlock(String block) throws Throwable {
        String stepName = String.format("'Заказчик А' переходит к разделу '%s':", block);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.gotoBlock(block);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' открывает раздел {string}:")
    public void customerGoesToBlockAndOpenIt(String block) throws Throwable {
        String stepName = String.format("'Заказчик А' открывает раздел '%s':", block);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.openBlock(block);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' переходит к полю {string}")
    public void customerClicksOnFieldByName(String fieldName) throws Throwable {
        String stepName = String.format("'Заказчик А' переходит к полю '%s'", fieldName);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.clickOnFieldByName(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет, что поле {string} содержит значение {string}")
    public void customerVerifiesFieldContent(String fieldName, String value) {
        String stepName =
                String.format("'Заказчик А' проверяет, что поле '%s' содержит значение '%s'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет, что поле {string} содержит пустое значение")
    public void customerVerifiesFieldIsEmpty(String fieldName) {
        String stepName = String.format("'Заказчик А' проверяет, что поле '%s' содержит пустое значение", fieldName);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.verifyFieldContentOrEmptyField(fieldName, "");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет, что поле {string} содержит не пустое значение")
    public void customerVerifiesFieldContent(String fieldName) {
        String stepName = String.format("'Заказчик А' проверяет, что поле '%s' содержит не пустое значение", fieldName);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.verifyFieldIsNotEmpty(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет, что поле {string} не доступно для редактирования")
    public void customerChecksFieldForTheImpossibilityOfEditing(String fieldName) {
        String stepName =
                String.format("'Заказчик А' проверяет, что поле '%s' не доступно для редактирования", fieldName);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.checkFieldForTheImpossibilityOfEditing(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет, что поле {string} содержит номер {string}, указанный в файле конфигурации")
    public void customerVerifiesPhoneNumber(String fieldName, String key) {
        String value = config.getConfigParameter(key);
        String stepName =
                String.format(
                        "'Заказчик А' проверяет, что поле '%s' содержит номер '%s', указанный в файле конфигурации",
                        fieldName, value);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.verifyFieldWithPhoneNumber(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет, что поле {string} содержит значение {string}, указанное в файле конфигурации")
    public void customerVerifiesFieldContentUsingParameterFromConfigFile(String fieldName, String key) {
        String value = config.getConfigParameter(key);
        String stepName = String.format(
                "'Заказчик А' проверяет, что поле '%s' содержит значение '%s', указанное в файле конфигурации",
                fieldName, value);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет, что поля 'Фамилия', 'Имя', 'Отчество' содержат значения {string}, указанное в файле конфигурации")
    public void customerVerifiesFieldsFioUsingParameterFromConfigFile(String key) {
        String value = config.getConfigParameter(key);
        String stepName = String.format(
                "'Заказчик А' проверяет, что поля 'Фамилия', 'Имя', 'Отчество' содержат значения  '%s', указанное в файле конфигурации",
                value);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.verifyField("Фамилия", value.split(" ")[0]);
        createNoticePage.verifyField("Имя", value.split(" ")[1]);
        createNoticePage.verifyField("Отчество", value.split(" ")[2]);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет поле 'Ответственное должностное лицо' {string}-м значением из списка")
    public void customerFillsResponciblePerson(String orderNumber) throws Throwable {
        String stepName = String.format(
                "'Заказчик А' заполняет поле 'Ответственное должностное лицо' '%s'-м значением из списка", orderNumber);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.fillResponciblePerson(orderNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет поле 'Ответственное должностное лицо' именем сертификата {string} из файла конф.")
    public void customerFillsResponciblePersonFieldUsingCertificateNameFromConfigFile(String certificateName)
            throws Throwable {
        String stepName = String.format(
                "'Заказчик А' заполняет поле 'Ответственное должностное лицо' именем сертификата '%s' из файла конф.",
                certificateName);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.
                fillResponciblePersonFieldUsingCertificateNameFromConfigFile(config.getConfigParameter(certificateName));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' устанавливает переключатель {string} в положение {string}")
    public void customerSetsRadioButtonTo(String radioButtonName, String radioButtonPosition) throws Throwable {
        String stepName = String.format(
                "'Заказчик' устанавливает переключатель '%s' в положение '%s'", radioButtonName, radioButtonPosition);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.selectRadioButton(radioButtonPosition);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' устанавливает флажок {string}")
    public void customerSetCheckBoxValue(String checkBoxName) {
        String stepName = String.format("'Заказчик А' устанавливает флажок %s", checkBoxName);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.verifyCheckBoxNotSelected(checkBoxName);
        createNoticePage.setCheckBoxValue(checkBoxName);
        createNoticePage.verifyCheckBoxSelected(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' сбрасывает флажок {string}")
    public void customerSetsOffCheckBoxValue(String checkBoxName) {
        String stepName = String.format("'Заказчик А' сбрасывает флажок '%s'", checkBoxName);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.verifyCheckBoxSelected(checkBoxName);
        createNoticePage.setCheckBoxValue(checkBoxName);
        createNoticePage.verifyCheckBoxNotSelected(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет, что флажок {string} {string}")
    public void customerVerifiesCheckBoxState(String checkBoxName, String checkBoxState) {
        String stepName = String.format("'Заказчик А' проверяет, что флажок '%s' '%s'", checkBoxName, checkBoxState);
        this.logStepName(stepName);

        timer.start();

        if (checkBoxState.equals("установлен")) createNoticePage.verifyCheckBoxSelected(checkBoxName);
        else createNoticePage.verifyCheckBoxNotSelected(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет, что флажок {string} отображается на странице")
    public void customerVerifiesThatCheckBoxByNameIsDisplayed(String checkBoxName) {
        String stepName = String.format("'Заказчик А' проверяет, что флажок '%s' отображается на странице", checkBoxName);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.verifyCheckBoxIsDisplayed(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет сведения о порядке подачи заявок")
    public void customerFillsReqestAcceptionDetails() throws Throwable {
        String stepName = "'Заказчик А' заполняет сведения о порядке подачи заявок";
        this.logStepName(stepName);

        timer.start();

        createNoticePage.setFieldClearClickAndSendKeys("Дата и время начала подачи заявок (МСК)",
                timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MINUTES",
                        config.getConfigParameter("OnProdApplicationStartDateShift")));
        createNoticePage.clickOnButton("'Закрыть' - в окне 'Календарь'");
        createNoticePage.setFieldClearClickAndSendKeys("Дата и время окончания подачи заявок (МСК)",
                timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MINUTES",
                        config.getConfigParameter("OnProdApplicationEndDateShift")));
        createNoticePage.clickOnButton("'Закрыть' - в окне 'Календарь'");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет сведения о порядке подачи заявок для конкурса с ускорениями процедур")
    public void customerFillsFullTenderWithProceduresAccelerationReqestAcceptionDetails() throws Throwable {
        String stepName = "'Заказчик' заполняет сведения о порядке подачи заявок для конкурса с ускорениями процедур";
        this.logStepName(stepName);

        timer.start();

        createNoticePage.setFieldClearClickAndSendKeys("Дата и время начала подачи заявок (МСК)",
                timer.getDateTimeShift(Timer.getPublishNoticeDate(), "HOURS", "-1"));
        createNoticePage.clickOnButton("'Закрыть' - в окне 'Календарь'");
        createNoticePage.setFieldClearClickAndSendKeys("Дата и время окончания подачи заявок (МСК)",
                timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MONTHS", "1"));
        createNoticePage.clickOnButton("'Закрыть' - в окне 'Календарь'");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет дату и время {string} в {string} со смещением {string} от времени публикации")
    public void customerFillsDateAndTimeField(String field, String shiftType, String amountAsString) throws Throwable {
        String stepName = String.format(
                "'Заказчик А' заполняет дату и время '%s' в '%s' со смещением '%s' от времени публикации",
                field, shiftType, amountAsString);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.setFieldClearClickAndSendKeys(String.format("Дата и время %s (МСК)", field),
                timer.getDateTimeShift(Timer.getPublishNoticeDate(), shiftType, amountAsString));
        createNoticePage.clickOnButton("'Закрыть' - в окне 'Календарь'");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет сведения о порядке работы комиссии для конкурса с ускорением процедур")
    public void customerFillsFullTenderWithProceduresAccelerationComissionDetails() throws Throwable {
        String stepName = "'Заказчик' заполняет сведения о порядке работы комиссии для конкурса с ускорением процедур";
        this.logStepName(stepName);

        timer.start();

        createNoticePage.setFieldClearClickAndSendKeys("Дата и время рассмотрения заявок (МСК)",
                timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MONTHS", "3"));
        createNoticePage.clickOnButton("'Закрыть' - в окне 'Календарь'");
        createNoticePage.setFieldClearClickAndSendKeys("Дата и время подведения итогов (МСК)",
                timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MONTHS", "6"));
        createNoticePage.clickOnButton("'Закрыть' - в окне 'Календарь'");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет сведения о порядке работы комиссии для конкурса без ускорения процедур")
    public void customerFillsFullTenderWithoutProceduresAccelerationComissionDetails() throws Throwable {
        String stepName = "'Заказчик А' заполняет сведения о порядке работы комиссии для конкурса без ускорения процедур";
        this.logStepName(stepName);

        timer.start();

        createNoticePage.setFieldClearClickAndSendKeys("Дата и время рассмотрения заявок (МСК)",
                timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MINUTES",
                        config.getConfigParameter("OnProdConsiderationEndDateShift")));
        createNoticePage.clickOnButton("'Закрыть' - в окне 'Календарь'");
        createNoticePage.setFieldClearClickAndSendKeys("Дата и время подведения итогов (МСК)",
                timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MINUTES",
                        config.getConfigParameter("OnProdResultsReviewShift")));
        createNoticePage.clickOnButton("'Закрыть' - в окне 'Календарь'");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет сведения о порядке работы комиссии для аукциона c заявкой в двух частях")
    public void customerFillsFullAuctionInTwoPartsComissionDetails() throws Throwable {
        String stepName =
                "'Заказчик А' заполняет сведения о порядке работы комиссии для аукциона c заявкой в двух частях";
        this.logStepName(stepName);

        timer.start();

        createNoticePage.setFieldClearClickAndSendKeys("Дата и время рассмотрения заявок (МСК)",
                timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MINUTES",
                        config.getConfigParameter("OnProdConsiderationEndDateShift")));
        createNoticePage.clickOnButton("'Закрыть' - в окне 'Календарь'");
        createNoticePage.setFieldClearClickAndSendKeys("Дата и время проведения аукциона (МСК)",
                timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MINUTES",
                        config.getConfigParameter("OnProdBiddingStartShift")));
        createNoticePage.clickOnButton("'Закрыть' - в окне 'Календарь'");
        createNoticePage.setFieldClearClickAndSendKeys("Дата и время подведения итогов (МСК)",
                timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MINUTES",
                        config.getConfigParameter("OnProdResultsReviewShift")));
        createNoticePage.clickOnButton("'Закрыть' - в окне 'Календарь'");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет сведения о порядке предоставления разъяснений")
    public void customerFillsInInformationOnTheProcedureForProvidingClarifications() throws Throwable {
        String stepName = "'Заказчик А' заполняет сведения о порядке предоставления разъяснений";
        this.logStepName(stepName);

        timer.start();

        createNoticePage.setKendoNumericTextBoxField(
                "Срок направления запроса о разъяснении документации (дней до окончания подачи заявок)",
                "3650");
        createNoticePage.setKendoNumericTextBoxField("Срок предоставления разъяснения документации",
                "3650");
        createNoticePage.setKendoNumericTextBoxField(
                "Срок направления запроса о разъяснении результата (после подведения итогов)",
                "3650");
        createNoticePage.setKendoNumericTextBoxField("Срок предоставления разъяснения результатов",
                "3650");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет сведения о контактном лице")
    public void customerFillsFullTenderContactPersonDetails() throws Throwable {
        String stepName = "'Заказчик А' заполняет сведения о контактном лице";
        this.logStepName(stepName);

        timer.start();

        String[] fio = config.getConfigParameter("СontactPersonFio").split(" ");

        createNoticePage.setFieldClearClickAndSendKeys("Фамилия", fio[0]);
        createNoticePage.setFieldClearClickAndSendKeys("Имя", fio[1]);
        createNoticePage.setFieldClearClickAndSendKeys("Отчество", fio[2]);

        createNoticePage.setFieldClearClickAndSendKeys("Телефон (в международном формате)",
                config.getConfigParameter("ContactPersonPhone"));
        createNoticePage.setFieldClearClickAndSendKeys("Адрес электронной почты",
                config.getConfigParameter("FakeEmailAddress"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет сведения об условиях участия для 'Поставщиков'")
    public void customerFillsInformationAboutTheConditions() throws Throwable {
        String stepName = "'Заказчик А' заполняет сведения об условиях участия для 'Поставщиков'";
        this.logStepName(stepName);

        timer.start();

        createNoticePage.openBlock("Сведения об условиях участия");
        createNoticePage.setCheckBoxValue("Закупка с ограниченным участием");
        createNoticePage.
                setCheckBoxValue("Отображать перечень приглашённых участников в опубликованном извещении");
        createNoticePage.addInvitedOrganizationsToList();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет дату и нулевое время {string} в {string} со смещением {string} от времени публикации")
    public void customerVerifiesDateAndZeroTimeField(String field, String shift, String amount) {
        String stepName = String.format(
                "'Заказчик А' проверяет дату и нулевое время '%s' в '%s' со смещением '%s' от времени публикации",
                field, shift, amount);
        this.logStepName(stepName);

        timer.start();

        // Получаем значение даты и времени со смещением и обнуляем время
        String value = timer.getDateTimeShift(Timer.getPublishNoticeDate(), shift, amount);
        value = value.substring(0, 10) + " 00:00";

        createNoticePage.verifyField(field, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет дату и время {string} в {string} со смещением {string} от времени публикации")
    public void customerVerifiesDateAndTimeField(String field, String shift, String amount) {
        String stepName = String.format(
                "'Заказчик А' проверяет дату и время '%s' в '%s' со смещением '%s' от времени публикации",
                field, shift, amount);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.verifyField(field, timer.getDateTimeShift(Timer.getPublishNoticeDate(), shift, amount));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет дату {string} в {string} со смещением {string} от времени публикации")
    public void customerVerifiesDateField(String field, String shift, String amount) {
        String stepName = String.format(
                "'Заказчик А' проверяет дату '%s' в '%s' со смещением '%s' от времени публикации",
                field, shift, amount);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.verifyField(field, timer.getDateShift(Timer.getPublishNoticeDate(), shift, amount));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет дату и время {string} в {string} со смещением {string} от времени публикации плюс {int} мин.")
    public void customerVerifiesDateAndTimeField(String field, String shift, String amount, int minutes) {
        String stepName = String.format("'Заказчик А' проверяет дату и время '%s' в '%s' со смещением '%s' от " +
                "времени публикации плюс '%d' мин.", field, shift, amount, minutes);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.verifyField(field,
                timer.getDateTimeShift(Timer.getPublishNoticeDate(minutes), shift, amount));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет, что переключатель {string} не отмечен")
    public void customerVerifiesRadioButtonNotSelected(String radioButtonName) {
        String stepName = String.format("'Заказчик А' проверяет, что переключатель '%s' не отмечен", radioButtonName);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.verifyRadioButtonNotSelected(radioButtonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет, что переключатель {string} отмечен")
    public void customerVerifiesRadioButtonSelected(String radioButtonName) {
        String stepName = String.format("'Заказчик А' проверяет, что переключатель '%s' отмечен", radioButtonName);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.verifyRadioButtonSelected(radioButtonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет поле 'Наименование закупки' сгенерированным именем для типа закупки {string}")
    public void customerFillsPurchaseNameFieldWithAutoGeneratedValue(String purchaseType) throws Throwable {
        String stepName = String.format(
                "'Заказчик А' заполняет поле 'Наименование закупки' сгенерированным именем для типа закупки  '%s'",
                purchaseType);
        this.logStepName(stepName);

        timer.start();

        Dictionary purchaseNamePrefixes = new Dictionary();
        purchaseNamePrefixes.add("Аукцион", "Тестовый аукцион");
        purchaseNamePrefixes.add("Коммерческий аукцион - 1 заявка", "Тестовый коммерческий аукцион с одной заявкой");
        purchaseNamePrefixes.add("Коммерческий аукцион - 1 заявка, назначение ответственного за заключение договора",
                "Тестовый коммерческий аукцион с одной заявкой и назначением ответственного за заключение договора");
        purchaseNamePrefixes.add("Аукцион (Конкурентный)", "Тестовый конкурентный аукцион");
        purchaseNamePrefixes.add("Аукцион (Конкурентный) - с включенной автопубликацией ППА и включенным ППИ",
                "Тестовый конкурентный Аукцион - с включенной автопубликацией ППА и включенным ППИ");
        purchaseNamePrefixes.add("Аукцион (Конкурентный заявка в двух частях)",
                "Тестовый конкурентный аукцион ( заявка в двух частях) - 1 заявка");
        purchaseNamePrefixes.add("Конкурс", "Тестовый конкурс");
        purchaseNamePrefixes.add("Конкурс - Конкурентн.", "Тестовый конкурс (Конкурентная закупка)");
        purchaseNamePrefixes.add("Конкурс (заявка в двух частях) - Конкурентн.",
                "Тестовый конкурс заявка в двух частях (Конкурентная закупка) с ПОД и ППИ");
        purchaseNamePrefixes.add("Конкурс - Конкурентн. с одним уч.",
                "Тестовый конкурс с одним участником (Конкурентная закупка)");
        purchaseNamePrefixes.add("Конкурс - Конкурентн. указывать информацию обо всех отклоненных участниках лота",
                "Тестовый конкурс с одним участником (Конкурентная закупка) " +
                        "указывать информацию обо всех отклоненных участниках лота");
        purchaseNamePrefixes.add("Конкурс - Конкурентн. " +
                        "не указывать информацию обо всех отклоненных участниках лота",
                "Тестовый конкурс с одним участником (Конкурентная закупка) " +
                        "не указывать информацию обо всех отклоненных участниках лота");
        purchaseNamePrefixes.add("Конкурс - ППС", "Тестовый конкурс с попозиционным сравнением");
        purchaseNamePrefixes.add("Конкурс - отпр. в ЕИС. ЦП - Попр. коэф. Переторжка",
                "Тестовый конкурс с отправкой в ЕИС. ЦП:Поправочный коэффициент.С переторжкой (закупка по 223-ФЗ)");
        purchaseNamePrefixes.add("Запрос котировок - ППС", "Тестовый запрос котировок с попозиционным сравнением");
        purchaseNamePrefixes.add("Запрос котировок - ППИ и переторжкой",
                "Тестовый запрос котировок с публикацией протокола подведения итогов и переторжкой");
        purchaseNamePrefixes.add("Аукцион без ускорений", "Тестовый аукцион без ускорений");
        purchaseNamePrefixes.add("Конкурс без ускорений", "Тестовый конкурс без ускорений");
        purchaseNamePrefixes.add("Тестовый аукцион (заявка в двух частях) без ускорений",
                "Тестовый аукцион (заявка в двух частях) без ускорений");
        purchaseNamePrefixes.add("Запрос цен", "Тестовый запрос цен");
        purchaseNamePrefixes.add("Аукцион - согл. Зак.", "Тестовый аукцион - согласование с Заказчиками");
        purchaseNamePrefixes.add("Аукцион - отпр. в ЕИС", "Тестовый аукцион - отправка закупки в ЕИС");
        purchaseNamePrefixes.add("Аукцион 223-ФЗ - отпр. в ЕИС", "Тестовый аукцион 223-ФЗ - отправка закупки в ЕИС");
        purchaseNamePrefixes.add("Запрос предложений - ЗП с одним уч.", "Тестовый запрос предложений - ЗП с одним уч. и переторжкой");
        purchaseNamePrefixes.add("Запрос предложений", "Тестовый запрос предложений");

        createNoticePage.setFieldClearClickAndSendKeys("Наименование закупки", String.format("%s от %s.",
                purchaseNamePrefixes.find(purchaseType), timer.getCurrentDateTime("dd.MM.yyyy HH:mm:SS")));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет поле 'Наименование процедуры' сгенерированным именем для типа закупки {string}")
    public void customerFillsProcedureNameFieldWithAutoGeneratedValue(String purchaseType) throws Throwable {
        String stepName = String.format(
                "'Заказчик А' заполняет поле 'Наименование закупки' сгенерированным именем для типа закупки  '%s'",
                purchaseType);
        this.logStepName(stepName);

        timer.start();

        Dictionary purchaseNamePrefixes = new Dictionary();
        purchaseNamePrefixes.add("Пред. отбор (с продл.) - Неконкур. - Многолот.",
                "Тестовый неконкурентный предварительный отбор с продлением - многолот");

        createNoticePage.setFieldClearClickAndSendKeys("Наименование процедуры",
                String.format("%s от %s.",
                        purchaseNamePrefixes.find(purchaseType), timer.getCurrentDateTime("dd.MM.yyyy HH:mm:SS")));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет поле {string} значением {string}")
    public void customerFillsFieldByName(String fieldName, String value) throws Throwable {
        String stepName = String.format("'Заказчик А' заполняет поле '%s' значением '%s'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.setFieldClearClickAndSendKeys(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет поле 'Наименование' значением {string} с сегодняшней датой и номером {string}")
    public void customerFillsFieldByNameAndNumber(String value, String number) throws Throwable {
        String stepName =
                String.format("'Заказчик А' заполняет поле 'Наименование' значением '%s' от сегодняшей даты с номером '%s'",
                        value, number);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.fillsFieldNameWithDate(value, number);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет поле со счётчиком {string} значением {string}")
    public void customerFillsFieldWithCounterByName(String fieldName, String value) throws Throwable {
        String stepName = String.format("'Заказчик А' заполняет поле со счётчиком '%s' значением '%s", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.setKendoNumericTextBoxField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет, что поле {string} не отображается на странице черновика извещения")
    public void customerCheckFieldIsNotDisplayed(String fieldName) {
        String stepName =
                String.format("'Заказчик А' проверяет, что поле '%s' не отображается на странице черновика извещения",
                        fieldName);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.checkFieldIsNotDisplayed(fieldName);

        timer.printStepTotalTime(stepName);
    }


    @And("'Заказчик А' заполняет поле 'Тип подачи ценового предложения' значением {string} из списка")
    public void customerFillApplicationOfferField(String fieldValue) throws Throwable {
        String stepName =
                String.format("'Заказчик А' заполняет поле 'Тип подачи ценового предложения' значением '%s' из списка",
                        fieldValue);

        this.logStepName(stepName);

        timer.start();

        createNoticePage.fillApplicationOfferField(fieldValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет поле 'Регион' значением {string} во вкладке 'Сведения о заказчиках'")
    public void customerFillsListWithInputAndMultiSelectField(String value) throws Throwable {
        String stepName = String.format("'Заказчик' заполняет поле 'Регион' значением '%s'.", value);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.setListWithInputAndMultiSelectFieldDirectValueFromList(
                "Сведения о заказчиках - Регион - X",
                "Сведения о заказчиках - Регион - поле множественного выбора", value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' переключается на вкладку лота {string}")
    public void customerSwitchesOnLotTabByName(String tabName) throws Throwable {
        String stepName = String.format("'Заказчик А' переключается на вкладку лота '%s'", tabName);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.switchToTab(tabName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' добавляет к закупке новый лот")
    public void customerAddsANewLot() throws Throwable {
        String stepName = "'Заказчик' добавляет к закупке новый лот";
        this.logStepName(stepName);

        timer.start();

        createNoticePage.clickOnButton("Добавить лот");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет поле лота Код ОКПД2 значением {string}")
    public void customerFillsLotOkpd2Field(String lotOkpd2) throws Throwable {
        String stepName = String.format("'Заказчик А' заполняет поле лота Код ОКПД2 значением '%s'", lotOkpd2);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.setLotOkpd2(lotOkpd2);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет поле лота Код ОКВЭД2 значением {string}")
    public void customerFillsLotOkved2Field(String lotOkved2) throws Throwable {
        String stepName = String.format("'Заказчик А' заполняет поле лота Код ОКВЭД2 значением '%s'", lotOkved2);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.setLotOkved2(lotOkved2);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет поле 'Единица измерения - код ОКЕИ' {string} значением из списка")
    public void customerFillsOkeiCode(String okeiNumber) throws Throwable {
        String stepName =
                String.format("'Заказчик А' заполняет поле 'Единица измерения - код ОКЕИ' '%s' значением из списка", okeiNumber);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.fillOkeiCode(okeiNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет поле 'Единица измерения - код ОКЕИ' значением {string} из списка")
    public void customerFillsOkeiCodeUsingTextValueInOkeiList(String textValue) throws Throwable {
        String stepName = String.format(
                "'Заказчик А' заполняет поле 'Единица измерения - код ОКЕИ' значением '%s' из списка", textValue);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.fillOkeiCodeUsingTextValueInOkeiList(textValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет поле 'Единица измерения - код ОКЕИ' значением параметра {string} из файла конфигурации")
    public void customerFillsOkeiCodeUsingValueFromConfigFileParameter(String parameterKey) throws Throwable {
        String stepName = String.format("'Заказчик А' заполняет поле 'Единица измерения - код ОКЕИ' значением " +
                "параметра '%s' из файла конфигурации", parameterKey);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.fillOkeiCodeUsingTextValueInOkeiList(config.getConfigParameter(parameterKey));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' выбирает второе по счёту значение в списке для поля 'Заказчик'")
    public void customerChangesLotCustomerNameToSecondInList() throws Throwable {
        String stepName = "'Заказчик' выбирает второе по счёту значение в списке для поля 'Заказчик'";
        this.logStepName(stepName);

        timer.start();

        createNoticePage.changeLotCustomerNameToSecondInList();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' выбирает значение {string} в списке для поля 'Вид обеспечения заявки'")
    public void customerChangesApplicationGuaranteeType(String applicationGuarantee) throws Throwable {
        String stepName = String.format("'Заказчик' выбирает значение '%s' в списке для поля 'Вид обеспечения заявки'",
                applicationGuarantee);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.changeLotApplicationGuaranteeType(applicationGuarantee);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' выбирает значение {string} в списке для поля 'Вид обеспечения договора'")
    public void customerChangesDocumentGuaranteeType(String documentGuarantee) throws Throwable {
        String stepName = String.format("'Заказчик' выбирает значение '%s' в списке для поля 'Вид обеспечения договора'",
                documentGuarantee);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.changeLotDocumentGuaranteeType(documentGuarantee);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет сведения о лоте № {string} для полного электронного аукциона с заявкой в двух частях")
    public void customerFillsFullAuctionInTwoPartsLotDetails(String lotNumber) throws Throwable {
        String stepName = String.format("'Заказчик А' заполняет сведения о лоте №: '%s' для полного электронного " +
                "аукциона с заявкой в двух частях", lotNumber);
        this.logStepName(stepName);

        timer.start();

        //==============================================================================================================
        // Вкладка [Общие сведения]
        //==============================================================================================================
        String lotName = config.getConfigParameter("LotNamePrefix") + lotNumber;
        createNoticePage.setFieldClearClickAndSendKeys("Наименование", lotName);
        createNoticePage.setKendoNumericTextBoxField("Начальная (максимальная) цена",
                config.getConfigParameter("LotPrice"));
        createNoticePage.
                setKendoNumericTextBoxField("Количество участников, занявших места ниже первого,",
                        config.getConfigParameter("SuppliersAmount"));
        createNoticePage.setKendoNumericTextBoxField("Срок направления договора (в днях)", "0");
        createNoticePage.
                setKendoNumericTextBoxField("Срок подписания договора участником (в днях)", "0");
        createNoticePage.setKendoNumericTextBoxField("Срок заключения договора (в днях)", "0");
        //==============================================================================================================

        //==============================================================================================================
        // Вкладка [Товары, работы и услуги]
        //==============================================================================================================
        createNoticePage.switchToTab("Товары, работы и услуги");
        createNoticePage.setFieldClearClickAndSendKeys("Наименование товара, работ, услуг",
                config.getConfigParameter("LotGoodsAndServicesName") + lotNumber);
        createNoticePage.setLotOkpd2(config.getConfigParameter("LotOkpd2"));
        createNoticePage.setLotOkved2(config.getConfigParameter("LotOkved2"));
        createNoticePage.setFieldClearClickAndSendKeys("Единица измерения (код ОКЕИ)",
                "Ампер (260)");
        createNoticePage.setKendoNumericTextBoxField("Количество", "1");
        //==============================================================================================================

        //==============================================================================================================
        // Вкладка [Сведения об аукционе]
        //==============================================================================================================
        createNoticePage.switchToTab("Сведения об аукционе");
        createNoticePage.verifyCheckBoxSelected("Аукцион с шагом");
        createNoticePage.selectRadioButton("Фиксированная сумма");
        createNoticePage.setKendoNumericTextBoxField("Шаг аукциона - От",
                config.getConfigParameter("AuctionFixedSumStepFrom"));
        createNoticePage.setKendoNumericTextBoxField("Шаг аукциона - До",
                config.getConfigParameter("AuctionFixedSumStepTo"));
        createNoticePage.verifyCheckBoxNotSelected("Автоматическое снижение шага");
        createNoticePage.verifyCheckBoxSelected("Аукцион с продлением");
        createNoticePage.verifyField("Время ожидания ценового предложения (мин)",
                config.getConfigParameter("AuctionWaitQuotationTimeoutInMinutes"));
        createNoticePage.setCheckBoxValue("Улучшение своего предложения без снижения текущего минимального");
        createNoticePage.setCheckBoxValue("Проводить вторую фазу (торги за второе место)");
        createNoticePage.setKendoNumericTextBoxField("Длительность второй фазы (мин)",
                config.getConfigParameter("AuctionSecondPhaseDurationInMinutes"));
        createNoticePage.setCheckBoxValue("Аукцион с нижним пределом снижения цены");
        createNoticePage.setKendoNumericTextBoxField("Завершение аукциона при снижении до, % от НМЦ",
                config.getConfigParameter("AuctionCompletionWithDecreaseToPercentFromSMP"));

        //==============================================================================================================
        // Вкладка [Сведения о заказчиках]
        //==============================================================================================================
        createNoticePage.switchToTab("Сведения о заказчиках");
        createNoticePage.changeLotCustomerNameToSecondInList();
        createNoticePage.setKendoNumericTextBoxField("Обеспечение заявки (%)",
                config.getConfigParameter("ApplicationAmountPercent"));
        createNoticePage.setKendoNumericTextBoxField("Обеспечение договора (%)",
                config.getConfigParameter("ContractAmountPercent"));
        //==============================================================================================================

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет сведения о лоте № {string} для полного конкурса")
    public void customerFillsFullTenderLotDetails(String lotNumber) throws Throwable {
        String stepName = String.format("'Заказчик А' заполняет сведения о лоте №: '%s' для полного конкурса", lotNumber);
        this.logStepName(stepName);

        timer.start();

        //==============================================================================================================
        // Вкладка [Общие сведения]
        //==============================================================================================================
        String lotName = String.format("%s %s", config.getConfigParameter("LotNamePrefix"), lotNumber);
        createNoticePage.setFieldClearClickAndSendKeys("Наименование", lotName);
        createNoticePage.setKendoNumericTextBoxField("Начальная (максимальная) цена",
                config.getConfigParameter("LotPrice"));
        createNoticePage.
                setKendoNumericTextBoxField("Количество участников, занявших места ниже первого,",
                        config.getConfigParameter("SuppliersAmount"));
        createNoticePage.setKendoNumericTextBoxField("Срок направления договора (в днях)", "0");
        createNoticePage.
                setKendoNumericTextBoxField("Срок подписания договора участником (в днях)", "0");
        createNoticePage.setKendoNumericTextBoxField("Срок заключения договора (в днях)", "0");
        //==============================================================================================================

        //==============================================================================================================
        // Вкладка [Товары, работы и услуги]
        //==============================================================================================================
        createNoticePage.getCurrentServerVersion();
        createNoticePage.switchToTab("Товары, работы и услуги");
        createNoticePage.setFieldClearClickAndSendKeys("Наименование товара, работ, услуг",
                config.getConfigParameter("LotGoodsAndServicesName") + lotNumber);
        createNoticePage.setLotOkpd2(config.getConfigParameter("LotOkpd2"));
        createNoticePage.setLotOkved2(config.getConfigParameter("LotOkved2"));
        createNoticePage.setFieldClearClickAndSendKeys("Единица измерения (код ОКЕИ)",
                "Ампер (260)");
        createNoticePage.setKendoNumericTextBoxField("Количество", "1");
        //==============================================================================================================

        //==============================================================================================================
        // Вкладка [Сведения о заказчиках]
        //==============================================================================================================
        createNoticePage.switchToTab("Сведения о заказчиках");
        createNoticePage.changeLotCustomerNameToSecondInList();
        createNoticePage.setKendoNumericTextBoxField("Обеспечение заявки (%)",
                config.getConfigParameter("ApplicationAmountPercent"));
        createNoticePage.setKendoNumericTextBoxField("Обеспечение договора (%)",
                config.getConfigParameter("ContractAmountPercent"));
        //==============================================================================================================

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' прикрепляет необходимые документы")
    public void customerUploadsRequiredDocs() throws Throwable {
        String stepName = "'Заказчик А' прикрепляет необходимые документы";
        this.logStepName(stepName);

        timer.start();

        createNoticePage.uploadFileIntoDocuments(config.getAbsolutePathToFoundationDoc());
        createNoticePage.checkLinkToDownloadFileIntoDocuments(config.getConfigParameter("FoundationDoc"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет ссылку для скачивания прикрепленного файла {string} в разделе 'Документы'")
    public void customerChecks1stLinkFileNameOfAttachedDocumentInDocumentsPartition(String fileName) throws Throwable {
        String stepName = String.format(
                "'Заказчик А' проверяет ссылку для скачивания прикрепленного файла '%s' в разделе 'Документы'",
                fileName);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.checkLinkToDownloadFileIntoDocuments(fileName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' публикует извещение")
    public void customerPublishesPurchaseNoticeFromCreateOrEditPage() throws Throwable {
        String stepName = "'Заказчик А' публикует извещение";
        this.logStepName(stepName);

        timer.start();

        // Публикует извещение со страницы [Формирование черновика извещения ...]
        createNoticePage.publishNotice();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' публикует извещение предварительного отбора")
    public void customerPublishesPurchaseNoticePreliminarySelectionFromCreateOrEditPage() throws Throwable {
        String stepName = "'Заказчик А' публикует извещение предварительного отбора";
        this.logStepName(stepName);

        timer.start();

        // Публикует извещение со страницы [Формирование черновика извещения предварительного отбора ...]
        createNoticePage.publishNoticePreliminarySelection();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' сохраняет черновик полного конкурса")
    public void customerSavesNotificationAboutFullTenderDraft() throws Throwable {
        String stepName = "'Заказчик А' сохраняет черновик полного конкурса";
        this.logStepName(stepName);

        timer.start();

        createNoticePage.saveTenderDraft();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' сохраняет черновик извещения")
    public void customerSavesNotificationDraft() throws Throwable {
        String stepName = "'Заказчик А' сохраняет черновик извещения";
        this.logStepName(stepName);

        timer.start();

        createNoticePage.saveTenderDraft();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' редактирует страницу {string}:")
    public void customerEditsFullTenderDetails(String page) throws Throwable {
        String stepName = String.format("'Заказчик А'редактирует страницу '%s'", page);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.waitLoadingImage();
        createNoticePage.checkPageUrl(page);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' редактирует общие сведения о закупке")
    public void customerEditsFullTenderCommonPurchaseDetails() {
        String stepName = "'Заказчик А' редактирует общие сведения о закупке";
        this.logStepName(stepName);

        timer.start();

        // Установка содержимого обязательных полей - не требуется

        // Проверка содержимого обязательных полей
        createNoticePage.verifyField("Номер закупки", config.getParameter("PurchaseNumber"));
        createNoticePage.verifyField("Способ закупки", "Конкурс (заявка в двух частях)");
        createNoticePage.verifyField("Тип закупки",
                "Закупка в соответствии с нормами 223-ФЗ (Конкурентная)");
        createNoticePage.verifyField("Наименование закупки", config.getParameter("PurchaseName"));
        createNoticePage.verifyFieldContentOrEmptyField("Описание закупки", "");

        createNoticePage.verifyCheckBoxNotSelected("Многолотовая закупка");
        createNoticePage.verifyCheckBoxNotSelected("Публикация протокола открытия доступа");
        createNoticePage.verifyCheckBoxSelected("Публикация итогового протокола");
        createNoticePage.verifyCheckBoxNotSelected(
                "Закупка проводится вследствие аварии, чрезвычайной ситуации");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' редактирует сведения о порядке подачи заявок")
    public void customerEditsFullTenderReqestAcceptionDetails() {
        String stepName = "'Заказчик А' редактирует сведения о порядке подачи заявок";
        this.logStepName(stepName);

        timer.start();

        // Установка содержимого обязательных полей - не требуется

        // Проверка содержимого обязательных полей
        createNoticePage.verifyField("Дата и время начала подачи заявок (МСК)",
                config.getParameter("ApplicationStartDate"));
        createNoticePage.verifyField("Дата и время окончания подачи заявок (МСК)",
                config.getParameter("ApplicationEndDate"));
        createNoticePage.verifyField("Место подачи заявок", "Электронная площадка РТС-тендер");
        createNoticePage.verifyField("Порядок подачи заявок", "В электронной форме");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик А' редактирует сведения о порядке работы комиссии")
    public void customerEditsFullTenderComissionDetails() throws Throwable {
        String stepName = "'Заказчик А' редактирует сведения о порядке работы комиссии";
        this.logStepName(stepName);

        timer.start();

        // Установка содержимого обязательных полей
        createNoticePage.setFieldClearClickAndSendKeys("Порядок рассмотрения заявок", "АТ Порядок РЗ");
        createNoticePage.setFieldClearClickAndSendKeys("Порядок подведения итогов", "АТ Порядок ПИ");
        createNoticePage.setFieldClearClickAndSendKeys("Дата и время подведения итогов (МСК)",
                config.getParameter("ResultsReviewDate"));

        // Проверка содержимого обязательных полей
        createNoticePage.verifyField("Дата и время рассмотрения заявок (МСК)",
                config.getParameter("ConsiderationEndDate"));
        createNoticePage.verifyField("Место рассмотрения заявок", "ОКАТО: 18230501000");
        createNoticePage.verifyField("Дата и время подведения итогов (МСК)",
                config.getParameter("ResultsReviewDate"));
        createNoticePage.verifyField("Место подведения итогов", "ОКАТО: 18230501000");
        createNoticePage.verifyField("Регламентный срок заключения договора - От", "10");
        createNoticePage.verifyField("Регламентный срок заключения договора - До", "20");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик А' редактирует сведения о предоставлении документации")
    public void customerEditsFullTenderForTheAvailabilityOfDocumentation() {
        String stepName = "'Заказчик А' редактирует сведения о предоставлении документации";
        this.logStepName(stepName);

        timer.start();

        // Установка содержимого обязательных полей - не требуется

        // Проверка содержимого обязательных полей
        createNoticePage.verifyField("Срок предоставления документации (МСК) От",
                config.getParameter("DocsDeliveryStartDate").split(" ")[0]);
        createNoticePage.verifyField("Срок предоставления документации (МСК) До",
                config.getParameter("DocsDeliveryEndDate").split(" ")[0]);
        createNoticePage.verifyField("Место предоставления документации",
                "Электронная площадка РТС-тендер");
        createNoticePage.verifyField("Порядок предоставления документации",
                config.getConfigParameter("DocsDeliveryInfoProcedure"));
        createNoticePage.verifyCheckBoxNotSelected("Установлена плата за предоставление документации");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' редактирует сведения о контактном лице")
    public void customerEditsFullTenderContactPersonDetails() {
        String stepName = "'Заказчик А' редактирует сведения о контактном лице";
        this.logStepName(stepName);

        timer.start();

        // Установка содержимого обязательных полей - не требуется

        // Проверка содержимого обязательных полей
        createNoticePage.verifyField("ФИО",
                config.getConfigParameter("СontactPersonFio").split(" ")[0]);
        createNoticePage.verifyField("Телефон (в международном формате)", "+7 (978) 478-2222");
        createNoticePage.verifyField("Адрес электронной почты",
                config.getConfigParameter("FakeEmailAddress"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' редактирует сведения о лоте № {string}")
    public void customerEditsFullTenderLotDetails(String lotNumber) throws Throwable {
        String stepName = String.format("'Заказчик А' редактирует сведения о лоте № '%s'", lotNumber);
        this.logStepName(stepName);

        timer.start();

        //--------------------------------------------------------------------------------------------------------------
        // Лот - Общие сведения
        //--------------------------------------------------------------------------------------------------------------
        // Установка содержимого обязательных полей
        createNoticePage.selectRadioButton(
                "Особенности участия субъектов малого и среднего предпринимательства - Не установлены");
        createNoticePage.setKendoNumericTextBoxField("Количество участников, занявших места ниже первого,",
                config.getConfigParameter("SuppliersAmount"));

        // Проверка содержимого обязательных полей
        createNoticePage.verifyField("Наименование лота", config.getParameter("LotName"));
        createNoticePage.verifyField("Валюта", "RUB");
        createNoticePage.verifyField("Начальная (максимальная) цена",
                config.getConfigParameter("LotPrice"));
        createNoticePage.verifyField("Количество участников, занявших места ниже первого,",
                config.getConfigParameter("SuppliersAmount"));
        //--------------------------------------------------------------------------------------------------------------
        // Лот - Товары, работы и услуги
        //--------------------------------------------------------------------------------------------------------------
        createNoticePage.switchToTab("Товары, работы и услуги");
        // Установка содержимого обязательных полей - не требуется

        // Проверка содержимого обязательных полей
        createNoticePage.verifyField("Наименование товара, работ, услуг",
                config.getParameter("LotGoodsAndServicesName"));
        createNoticePage.verifyField("Код ОКПД2", config.getConfigParameter("LotOkpd2"));
        createNoticePage.verifyField("Код ОКВЭД2", config.getConfigParameter("LotOkved2"));
        //--------------------------------------------------------------------------------------------------------------
        // Лот - Сведения о заказчиках
        //--------------------------------------------------------------------------------------------------------------
        createNoticePage.switchToTab("Сведения о заказчиках");
        // Установка содержимого обязательных полей - не требуется

        // Проверка содержимого обязательных полей
        createNoticePage.verifyField("Обеспечение договора", "0,03");
        createNoticePage.setListWithInputAndMultiSelectFieldDirectValueFromList("Сведения о заказчиках - Регион - X",
                "Сведения о заказчиках - Регион - поле ввода", "Вологодск");
        createNoticePage.verifyField("Место поставки товара, выполнения работ, оказания услуг",
                config.getConfigParameter("LotDeliveryPlace"));
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет, что к извещению прикреплен документ")
    public void customerChecksThatTheDocumentAttachedToNotification() throws Throwable {
        String stepName = "'Заказчик А' редактирует сведения о контактном лице";
        this.logStepName(stepName);

        timer.start();

        createNoticePage.checkLinkToDownloadFileIntoDocuments(config.getParameter("TenderDraftFileToUpload"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет наличие изменений в извещении о полном конкурсе")
    public void customerChecksForChangesInNotificationAboutFullTender() throws Throwable {
        String stepName = "'Заказчик' проверяет наличие изменений в извещении о полном конкурсе";
        this.logStepName(stepName);

        timer.start();

        viewNoticePage.checkThatPurchaseStatusIsPublished();
        viewNoticePage.checkPurchaseNumber();
        viewNoticePage.checkPurchaseName("View");
        viewNoticePage.checkCustomerTradeEditLinkPresence();
        viewNoticePage.clickOnCustomerTradeEditLink();
        createNoticePage.waitLoadingImage();
        createNoticePage.
                checkPageUrl("Формирование черновика извещения конкурса (заявка в двух частях) - Angular");
        createNoticePage.switchToTab("Товары, работы и услуги");
        createNoticePage.verifyField("Наименование товара, работ, услуг",
                config.getParameter("LotGoodsAndServicesName"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' находит секцию 'Документы' и проверяет наличие ссылки для скачивания файла на странице 'Просмотр извещения XXXX'")
    public void customerFindsDocumentsPartitionAndChecksLinkToDownloadFile() throws Throwable {
        String stepName = "'Заказчик А' находит секцию 'Документы' и проверяет наличие ссылки " +
                "для скачивания файла на странице 'Просмотр извещения XXXX'";
        this.logStepName(stepName);

        timer.start();

        createNoticePage.scrollToDocumentsPartition();
        createNoticePage.checkLinkToDownloadFileIntoDocuments(config.getConfigParameter("FoundationDoc"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' нажимает на ссылку и скачивает файл на странице 'Просмотр извещения XXXX'$")
    public void customerClicksFileDownloadLinkAndChecksThatFileLoadedSuccessfully() throws Throwable {
        String stepName = "'Заказчик А' нажимает на ссылку и скачивает файл на странице 'Просмотр извещения XXXX'";
        this.logStepName(stepName);

        timer.start();

        createNoticePage.downloadFirstAttachedDocumentWithVerification();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' нажимает кнопку {string}")
    public void customerClicksOnButtonByName(String buttonName) throws Throwable {
        String stepName = String.format("'Заказчик А' нажимает кнопку '%s'", buttonName);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' создает извещение на основе плана коммерческих закупок")
    public void customerCreatesNewNoticeBasedOnPlanOfCommercialPurchases() throws Throwable {
        String stepName = "'Заказчик А' создает извещение на основе плана коммерческих закупок";
        this.logStepName(stepName);

        timer.start();

        //--------------------------------------------------------------------------------------------------------------
        viewTradePlanPage.selectFirstPlanPosition();
        viewTradePlanPage.clickOnCreateNoticeButton();
        //--------------------------------------------------------------------------------------------------------------
        createNoticePage.verifyCreateOrEditTenderPageOpened(true);
        createNoticePage.verifyField("Способ закупки", "Конкурс");
        createNoticePage.verifyField("Тип закупки", "Коммерческая закупка");
        createNoticePage.verifyField("Наименование закупки", "АвтоТест_1");
        createNoticePage.verifyCheckBoxNotSelected("Многолотовая закупка");
        //--------------------------------------------------------------------------------------------------------------
        createNoticePage.switchToTab("Общие сведения");
        createNoticePage.verifyField("Наименование лота", "АвтоТест_1");
        createNoticePage.verifyField("Начальная (максимальная) цена", "5 000,00");
        createNoticePage.switchToTab("Товары, работы и услуги");
        createNoticePage.verifyField("Наименование товара, работ, услуг", "АвтоТест_1");
        createNoticePage.checkLotOkpd2("Документация проектная для строительства");
        createNoticePage.checkLotkved2("Разработка строительных проектов");
        createNoticePage.switchToTab("Сведения о заказчиках");
        createNoticePage.changeLotCustomerNameToSecondInList();
        createNoticePage.saveTenderDraft();
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' нажимает кнопку {string} в диалоговом окне с сообщением {string}")
    public void customerClicksOnButtonInDialogWindowWithMessage(String buttonName, String messageText) throws Throwable {
        String stepName = String.format(
                "'Заказчик' нажимает кнопку '%s' в диалоговом окне с сообщением '%s'", buttonName, messageText);
        this.logStepName(stepName);

        timer.start();

        confirmAngularDialog.checkWindowText("Текст сообщения в окне", messageText).clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' добавляет организацию с ИНН {string} в разделе 'Сведения о заказчиках, подписывающих договоры'")
    public void customerAddsOrganizationByInn(String inn) throws Throwable {
        String stepName = String.format(
                "'Заказчик А' добавляет организацию с ИНН '%s' в разделе 'Сведения о заказчиках, подписывающих договоры'",
                inn);
        this.logStepName(stepName);

        timer.start();

        customerSelectionDialog.ifDialogOpened().
                setFieldClearClickAndSendKeys("Поле ИНН", inn).
                clickOnButtonByName("Кнопка Поиск", false).
                select1stLineInSearchResults().
                storeOrganizationNameWithInn().
                clickOnButtonByName("Кнопка Выбрать", true);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет, что поле 'Заказчик' содержит требуемую организацию с ИНН {string}")
    public void customerChecksCustomerFieldContentByOrganizationInn(String inn) {
        String stepName = String.format(
                "'Заказчик А' проверяет, что поле 'Заказчик' содержит требуемую организацию с ИНН '%s'", inn);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.verifyField(
                "Сведения о заказчиках - Заказчик", config.getParameter("INN" + inn));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' нажимает кнопку {string} в диалоговом окне 'Сохранено'")
    public void customerClicksOnButtonByNameInSuccessfullySavedDialogWondow(String buttonName) throws Throwable {
        String stepName =
                String.format("'Заказчик А' нажимает кнопку '%s' в диалоговом окне 'Сохранено'", buttonName);
        this.logStepName(stepName);

        timer.start();

        purchaseNoticeDraftSuccessfullySavedDialog.
                ifDialogOpened().
                clickOnButtonByName(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' нажимает на кнопку 'Продолжить' в диалоговом окне 'Предупреждение'")
    public void customerClicksOnContinueButtonInWarningDialogWindow() throws Throwable {
        String stepName = "'Заказчик А' нажимает на кнопку 'Продолжить' в диалоговом окне 'Предупреждение'";
        this.logStepName(stepName);

        timer.start();

        commonCustomerPage.clickButtonInPopupWindow("Продолжить-Предупреждение");


        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' нажимает на кнопку 'OK' в диалоговом окне 'Информация'")
    public void customerClicksOnOkButtonInInformationDialogWindow() throws Throwable {
        String stepName = "'Заказчик А' нажимает на кнопку 'OK' в диалоговом окне 'Информация'";
        this.logStepName(stepName);

        timer.start();

        informationAngularDialog.
                ifDialogOpened("Текст заголовка окна").
                clickOnOKButton("ОК");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет, что таблица 'Список приглашённых участников' содержит {int} строки")
    public void customerChecksNumberOfRowsFromInvitedOrganizationsTable(int numberOfRows) {
        String stepName = String.format(
                "'Заказчик А' проверяет, что таблица 'Список приглашённых участников' содержит '%d' строки", numberOfRows);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.checkNumberOfRowsFromInvitedOrganizationsTable(numberOfRows);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет, что таблица 'Список приглашённых участников' содержит ИНН двух аккредитованных Поставщиков")
    public void customerChecks() {
        String stepName = "'Заказчик А' проверяет, что таблица 'Список приглашённых участников' содержит ИНН двух " +
                "аккредитованных Поставщиков";
        this.logStepName(stepName);

        timer.start();

        List<String> expectedInns = Arrays.asList(
                config.getConfigParameter("OnProdSupplier1Inn"),
                config.getConfigParameter("OnProdSupplier2Inn")
        );
        createNoticePage.checkInnsFromInvitedOrganizationsTable(expectedInns);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет столбец {string} строка {string} таблица 'Комиссия' текст {string}")
    public void customerChecksCellByNameInLineByNumberFromCommissionTableForTextInNoticeDraft(
            String columnName, String lineNumber, String cellValue) {
        String stepName = String.format("'Заказчик А' проверяет столбец '%s' строка '%s' таблица 'Комиссия' текст '%s'",
                columnName, lineNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.verifyCellByNameInLineByNumberFromCommissionTableForText(columnName, lineNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' создаёт запрос на согласование и нажимает на кнопку 'Отправить'")
    public void customerCreatesApprovalRequestAndClicksOnSendButton() throws Throwable {
        String stepName = "'Заказчик' создаёт запрос на согласование и нажимает на кнопку 'Отправить'";
        this.logStepName(stepName);

        timer.start();

        createApprovalRequestDialog.ifDialogOpened().

                setFieldClearClickAndSendKeys("Поле Дата согласования",
                        timer.getDateShift(new Date(), "DAYS", "0")).
                setFieldClearClickAndSendKeys("Поле Плановая дата публикации закупки",
                        timer.getDateShift(new Date(), "DAYS", "0")).
                clickOnButtonByName("Кнопка Отправить");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет, что требуемая рабочая группа отмечена в таблице 'Рабочие группы'")
    public void customerChecksThatTheDesiredWorkGroupSelected() {
        String stepName = "'Заказчик А' проверяет, что требуемая рабочая группа отмечена в таблице 'Рабочие группы'";
        this.logStepName(stepName);

        timer.start();

        createNoticePage.checkThatTheDesiredWorkGroupSelected();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет количество строк {string} в таблице 'Заказчики'")
    public void customerChecksNumberOfRowsFromCustomersTable(String rows) throws Throwable {
        String stepName = String.format("'Заказчик А' проверяет количество строк '%s' в таблице 'Заказчики'", rows);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.checkNumberOfRowsFromCustomersTable(rows);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет количество столбцов {string} в таблице 'Товары'")
    public void customerChecksNumberOfColumnsFromProductsTable(String columns) throws Throwable {
        String stepName = String.format("'Заказчик А' проверяет количество столбцов '%s' в таблице 'Товары'", columns);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.checkNumberOfColumnsFromProductsTable(columns);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет количество строк {string} в таблице 'Товары'")
    public void customerChecksNumberOfRowsFromProductsTable(String rows) throws Throwable {
        String stepName = String.format("'Заказчик' проверяет количество строк '%s' в таблице 'Товары'", rows);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.checkNumberOfRowsFromProductsTable(rows);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет, что таблица 'Документы лота' пуста")
    public void customerChecksThatTheLotDocumentsTableIsEmpty() {
        String stepName = "'Заказчик А' проверяет, что таблица 'Документы лота' пуста";
        this.logStepName(stepName);

        timer.start();

        createNoticePage.checkThatTheLotDocumentsTableIsEmpty();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет дополнительное поле типа 'дата и время' {string} в {string} со смещением {string} от времени публикации")
    public void customerFillsAdditionalFieldDateAndTimeField(String field, String shiftType, String amountAsString) throws Throwable {
        String stepName = String.format(
                "'Заказчик А' заполняет дополнительное поле типа 'дата и время' '%s' в '%s' со смещением '%s' от времени публикации",
                field, shiftType, amountAsString);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.setFieldClearClickAndSendKeys(field, timer.getDateTimeShift(Timer.getPublishNoticeDate(),
                shiftType, amountAsString));
        createNoticePage.clickOnButton("'Закрыть' - в окне 'Календарь'");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' заполняет дополнительное поле типа 'валюта' {string} значением 'Российский рубль' из списка")
    public void customerFillsCurrencyAdditionalFieldWithRussianRoubleValue(String field) throws Throwable {
        String stepName = String.format("'Заказчик А' заполняет дополнительное поле типа 'валюта' '%s' фиксированым" +
                " значением 'Российский рубль' из списка", field);
        this.logStepName(stepName);

        timer.start();

        createNoticePage.fillCurrencyAdditionalFieldWithRussianRoubleValue(field);

        timer.printStepTotalTime(stepName);
    }

    // endregion

    // region Раздел [Просмотр извещения о закупке]

    @And("'Заказчик А' нажимает на кнопку 'Перейти к карточке закупки' в диалоговом окне 'Запрос на согласование отправлен'")
    public void customerClicksOnGoToPurchaseCardButtonInApprovalRequestSentDialogWindow() throws Throwable {
        String stepName = "'Заказчик' нажимает на кнопку 'Перейти к карточке закупки' в " +
                "диалоговом окне 'Запрос на согласование отправлен'";
        this.logStepName(stepName);

        timer.start();

        approvalRequestSentDialog.ifDialogOpened().
                checkDialogMessage("Проект извещения был сохранен и отправлен на согласование.").
                clickOnButtonByName("Кнопка Перейти к карточке закупки");
        viewNoticePage.checkPageUrl("Просмотр извещения");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' публикует извещение о закупке и ожидает перехода лота в статус \"([^\"]*)\"$")
    public void customerPublishesPurchaseNoticeFromViewPage(String lotStatus) throws Throwable {
        String stepName = String.format(
                "'Заказчик' публикует извещение о закупке и ожидает перехода лота в статус [%s]", lotStatus);
        this.logStepName(stepName);

        timer.start();

        // Публикует извещение со страницы [Просмотр извещения XXXX]
        viewNoticePage.publishPurchaseNotice(lotStatus);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' сохраняет в параметрах теста номер извещения о закупке в режиме просмотра извещения$")
    public void customerSavesPurchaseNumberFromViewNoticePage() {
        String stepName =
                "'Заказчик' сохраняет в параметрах теста номер извещения о закупке в режиме просмотра извещения";
        this.logStepName(stepName);

        timer.start();

        viewNoticePage.savePurchaseNoticeNumberFromNoticeView();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' переходит к просмотру раздела \"([^\"]*)\"$")
    public void customerGoesToViewPartition(String partitionName) throws Throwable {
        String stepName = String.format("'Заказчик' переходит к просмотру раздела '%s'", partitionName);
        this.logStepName(stepName);

        timer.start();

        viewNoticePage.openPartition(partitionName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет содержимое поля \"([^\"]*)\" на текст \"([^\"]*)\"$")
    public void customerChecksFieldContentForText(String fieldName, String value) {
        String stepName = String.format("'Заказчик' проверяет содержимое поля '%s' на текст '%s'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        viewNoticePage.verifyFieldContent(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} на странице 'Просмотр извещения' содержит не пустое значение")
    public void customerVerifiesFieldContentOnViewNoticePage(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' " +
                "на странице 'Просмотр извещения' содержит не пустое значение", fieldName);
        this.logStepName(stepName);

        timer.start();

        viewNoticePage.verifyFieldIsNotEmpty(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на кнопку \"([^\"]*)\"$")
    public void customerClicksOnButtonByNameInViewNoticePage(String buttonName) throws Throwable {
        String stepName = String.format("'Заказчик' нажимает на кнопку '%s'", buttonName);
        this.logStepName(stepName);

        timer.start();

        viewNoticePage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' отправляет извещение о электронном аукционе в ЕИС, способ закупки {string} URL {string}")
    public void customerSendsPurchaseNoticeToEISWithPurchaseTypeChecking(String purchaseMethod, String urlKey)
            throws Throwable {
        String stepName = String.format(
                "'Заказчик' отправляет извещение о электронном аукционе в ЕИС, способ закупки '%s' URL '%s'",
                purchaseMethod, config.getConfigParameter(urlKey));
        this.logStepName(stepName);

        timer.start();

        sendInformationToEisDialog.ifDialogOpened().
                selectPurchaseMethod(purchaseMethod, urlKey).
                clickOnButtonByName("Отправить в ЕИС");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет ячейку \"([^\"]*)\" строка \"([^\"]*)\" в таблице 'Комиссия' на текст \"([^\"]*)\"$")
    public void customerChecksCellByNameInLineByNumberFromCommissionTableForText(
            String columnName, String lineNumber, String cellValue) {
        String stepName = String.format(
                "'Заказчик' проверяет ячейку '%s' строка '%s' в таблице 'Комиссия' на текст '%s'",
                columnName, lineNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        viewNoticePage.verifyCellByNameInLineByNumberFromCommissionTableForText(columnName, lineNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет ячейку {string} строка {string} в таблице 'Запросы на разъяснение документации и ответы' на текст {string}")
    public void customerChecksCellByNameInLineByNumberFromClarificationRequestsTableForText(
            String columnName, String lineNumber, String cellValue) {
        String stepName = String.format("'Заказчик' проверяет ячейку '%s' строка '%s' в таблице " +
                "'Запросы на разъяснение документации и ответы' на текст '%s'", columnName, lineNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        viewNoticePage.verifyCellByColumnNameAndLineNumberFromClarificationRequestsTableForText(
                columnName, lineNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет наличие кнопки {string} строка {int} в таблице 'Запросы на разъяснение документации и ответы'")
    public void customerChecksButtonPresenceInLineByNumberFromClarificationRequestsTable(
            String columnName, int lineNumber) {
        String stepName = String.format("'Заказчик' проверяет наличие кнопки '%s' строка '%d' в таблице " +
                "'Запросы на разъяснение документации и ответы'", columnName, lineNumber);
        this.logStepName(stepName);

        timer.start();

        viewNoticePage.checkButtonPresenceInLineByNumberFromClarificationRequestsTable(columnName, lineNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} строка {int} в таблице 'Запросы на разъяснение документации и ответы'")
    public void customerClicksOnButtonInLineByNumberFromClarificationRequestsTable(String columnName, int lineNumber)
            throws Throwable {
        String stepName = String.format("'Заказчик' нажимает на кнопку '%s' строка '%d' в таблице " +
                "'Запросы на разъяснение документации и ответы'", columnName, lineNumber);
        this.logStepName(stepName);

        timer.start();

        viewNoticePage.clickOnButtonInLineByNumberFromClarificationRequestsTable(columnName, lineNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет ячейку \"([^\"]*)\" строка \"([^\"]*)\" в таблице 'Договоры закупки' на текст \"([^\"]*)\"$")
    public void customerChecksCellByNameInLineByNumberFromPurchaseContractsTableForText(
            String columnName, String lineNumber, String cellValue) {
        String stepName = String.format(
                "'Заказчик' проверяет ячейку '%s' строка '%s' в таблице 'Договоры закупки' на текст '%s'",
                columnName, lineNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        viewNoticePage.verifyCellByColumnNameAndLineNumberFromContractsInfoTableForText(columnName, lineNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет ячейку \"([^\"]*)\" строка \"([^\"]*)\" в таблице 'Объекты закупки' на текст \"([^\"]*)\"$")
    public void customerChecksCellByNameInLineByNumberFromPurchaseObjectsTableForText(
            String columnName, String lineNumber, String cellValue) {
        String stepName = String.format(
                "'Заказчик' проверяет ячейку '%s' строка '%s' в таблице 'Объекты закупки' на текст '%s'",
                columnName, lineNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        viewNoticePage.verifyCellByColumnNameAndLineNumberFromPurchaseObjectsTableForText(columnName, lineNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} соответствует шаблону {string}")
    public void customerChecksFieldValueForDatePattern(String fieldName, String pattern) {
        String stepName =
                String.format("'Заказчик' проверяет, что поле '%s' соответствует шаблону '%s'", fieldName, pattern);
        this.logStepName(stepName);

        timer.start();

        viewNoticePage.verifyDateFieldForPattern(fieldName, pattern);

        timer.printStepTotalTime(stepName);
    }

    // endregion
}
