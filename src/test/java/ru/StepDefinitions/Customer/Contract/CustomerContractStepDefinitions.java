package ru.StepDefinitions.Customer.Contract;

import Helpers.Timer;
import cucumber.api.java.en.And;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import ru.PageObjects.CommonDialogs.AssignResponsibilityForSigningDialog;
import ru.PageObjects.CommonDialogs.HistoryDialog;
import ru.PageObjects.CommonDialogs.WarningDialog;
import ru.PageObjects.Customer.CommonCustomerPage;
import ru.PageObjects.Customer.Contract.*;
import ru.PageObjects.Customer.Notice.ViewNoticePage;
import ru.StepDefinitions.AbstractStepDefinitions;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Класс описывающий шаги работы Заказчика с договорами.
 * Created by Vladimir V. Klochkov on 09.12.2020.
 * Updated by Alexander S. Vasyurenko on 19.04.2021.
 */
public class CustomerContractStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final ContractsTerminationEditPage contractsTerminationEditPage = new ContractsTerminationEditPage(driver);
    private final ContractsTerminationViewPage contractsTerminationViewPage = new ContractsTerminationViewPage(driver);
    private final ContractsExecutionEditPage contractsExecutionEditPage = new ContractsExecutionEditPage(driver);
    private final ContractsExecutionViewPage contractsExecutionViewPage = new ContractsExecutionViewPage(driver);
    private final ContractsAddendumPage contractsAddendumPage = new ContractsAddendumPage(driver);
    private final MyContractsPage myContractsPage = new MyContractsPage(driver);
    private final MyContractPage myContractPage = new MyContractPage(driver);
    private final ViewNoticePage viewNoticePage = new ViewNoticePage(driver);

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    @And("'Заказчик' открывает страницу 'Сведения о договоре' по ссылке на номер карточки договора")
    public void customerOpensContractInfoPageByClickToContractCardNumber() throws Throwable {
        String stepName = "'Заказчик' открывает страницу 'Сведения о договоре' по ссылке на номер карточки договора";
        this.logStepName(stepName);

        timer.start();

        myContractsPage.openContractInfoPageByClickToContractCardNumber(0);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет наличие договоров в таблице с данными$")
    public void customerChecksThatAtLeastOneContractPresentInTable() throws Throwable {
        String stepName = "'Заказчик' проверяет наличие договоров в таблице с данными";
        this.logStepName(stepName);

        timer.start();

        // Значение счетчика найденных договоров
        String contractsFoundCounterValue = myContractsPage.getContractsFoundCounterValue();

        // Список ссылок [№ карточки договора] из таблицы результатов поиска на текущей странице
        List<String> contractCardNumbers = myContractsPage.getContractCardNumbersFromCurrentPage();

        Assert.assertFalse(
                ">>> Нет найденных договоров на странице [Мои договоры] (Главная / Заказчикам / Мои договоры)",
                contractsFoundCounterValue.equals("0") ||
                        contractsFoundCounterValue.isEmpty() ||
                        contractCardNumbers.size() == 0);

        myContractsPage.checkPageFilters();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' переходит к просмотру раздела договора \"([^\"]*)\"$")
    public void customerGoesToViewContractPagePartition(String partitionName) throws Throwable {
        String stepName = String.format("'Заказчик' переходит к просмотру раздела договора '%s'", partitionName);
        this.logStepName(stepName);

        timer.start();

        myContractPage.goToPartition(partitionName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет статус договора \"([^\"]*)\"$")
    public void customerChecksContractStatus(String status) throws Throwable {
        String stepName = String.format("'Заказчик' проверяет статус договора '%s'", status);
        this.logStepName(stepName);

        timer.start();

        myContractPage.checkContractStatus(status, 30);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' сохраняет в параметре теста {string} значение поля {string} на странице 'Сведения о договоре'")
    public void customerSavesToTestParameterTheValueOfTheFieldOnMyContractPage(
            String testParameter, String fieldName) {
        String stepName = String.format(
                "'Заказчик' сохраняет в параметре теста '%s' значение поля '%s' на странице 'Сведения о договоре'",
                testParameter, fieldName);
        this.logStepName(stepName);

        timer.start();

        config.setParameter(testParameter, myContractPage.getField(fieldName));

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет содержимое поля договора 'Номер заявки' на текст \"([^\"]*)\"$")
    public void customerChecksContractApplicationNumberFieldForText(String text) {
        String stepName =
                String.format("'Заказчик' проверяет содержимое поля договора 'Номер заявки' на текст '%s'", text);
        this.logStepName(stepName);

        timer.start();

        myContractPage.checkContractApplicationNumberFieldForText(text);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' устанавливает флажок в договоре \"([^\"]*)\"$")
    public void customerSetsCheckBoxValueOnMyContractPage(String checkBoxName) {
        String stepName = String.format("'Заказчик' устанавливает флажок в договоре '%s'", checkBoxName);
        this.logStepName(stepName);

        timer.start();

        myContractPage.setCheckBoxValue(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' прикрепляет файл с проектом договора$")
    public void customerAttachesFileWithContractProject() throws Throwable {
        String stepName = "'Заказчик' прикрепляет файл с проектом договора";
        this.logStepName(stepName);

        timer.start();

        myContractPage.attachFileToContractProject();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет дату прикрепления файла с проектом договора")
    public void customerChecksDateAttachedFile() {
        String stepName = "Заказчик' проверяет дату прикрепления файла с проектом договора";
        this.logStepName(stepName);

        timer.start();

        myContractPage.checkAttachedFileDate();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' направляет договор Поставщику$")
    public void customerSendsContractToSupplier() throws Throwable {
        String stepName = "'Заказчик' направляет договор Поставщику";
        this.logStepName(stepName);

        timer.start();

        //--------------------------------------------------------------------------------------------------------------
        long slowSiteTimeoutInMinutes = Long.parseLong(config.getConfigParameter("SlowSiteTimeoutInMinutes"));
        boolean slowSite = slowSiteTimeoutInMinutes > 0;
        //--------------------------------------------------------------------------------------------------------------
        myContractPage.isControlClickable("Сохранить договор");
        myContractPage.clickOnButton("Сохранить договор");
        //--------------------------------------------------------------------------------------------------------------
        if (slowSite) TimeUnit.MINUTES.sleep(slowSiteTimeoutInMinutes); // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
        myContractPage.checkContractStatus("Договор формируется", 30);
        myContractPage.isControlClickable("Направить договор");
        myContractPage.clickOnButton("Направить договор");
        //--------------------------------------------------------------------------------------------------------------
        if (slowSite) TimeUnit.MINUTES.sleep(slowSiteTimeoutInMinutes); // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
        myContractPage.checkPageUrl("Заказчик-Направьте договор");
        myContractPage.checkContractStatus("Договор отправлен участнику на подпись", 30);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' направляет договор выигравшему конкурс Поставщику$")
    public void customerSavesAndSignContract() throws Throwable {
        String stepName = "'Заказчик' направляет договор выигравшему конкурс Поставщику";
        this.logStepName(stepName);

        timer.start();

        //--------------------------------------------------------------------------------------------------------------
        WarningDialog warningDialog = new WarningDialog(driver);
        warningDialog.clickOnContinueButton();
        //--------------------------------------------------------------------------------------------------------------
        long slowSiteTimeoutInMinutes = Long.parseLong(config.getConfigParameter("SlowSiteTimeoutInMinutes"));
        boolean slowSite = slowSiteTimeoutInMinutes > 0;
        //--------------------------------------------------------------------------------------------------------------
        TimeUnit.MINUTES.sleep(1);
        myContractPage.checkPageUrl("Заказчик-Направьте договор");
        myContractPage.checkContractStatus("Договор формируется", 30);
        myContractPage.attachFileToContractProject();
        myContractPage.checkContractStatus("Договор формируется", 30);
        myContractPage.clickOnButton("Сохранить договор");
        //--------------------------------------------------------------------------------------------------------------
        if (slowSite) TimeUnit.MINUTES.sleep(slowSiteTimeoutInMinutes); // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
        myContractPage.checkContractStatus("Договор формируется", 30);
        myContractPage.clickOnButton("Направить договор");
        //--------------------------------------------------------------------------------------------------------------
        if (slowSite) TimeUnit.MINUTES.sleep(slowSiteTimeoutInMinutes); // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
        myContractPage.checkContractStatus("Договор отправлен участнику на подпись", 30);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' подписывает договор$")
    public void customerSignsContract() throws Throwable {
        String stepName = "'Заказчик' подписывает договор";
        this.logStepName(stepName);

        timer.start();

        myContractPage.checkPageUrl("Заказчик-Подпишите договор");

        // Проверка статуса договора: [Договор подписан участником]
        myContractPage.checkContractStatus("Договор подписан участником", 30);

        // Заполнение поля [Дата начала исполнения договора]
        myContractPage.setStartPerformanceDate(
                timer.getDateShiftWithoutDelimiters(Timer.getPublishNoticeDate(), "MONTHS", "+1"));

        // Заполнение поля [Дата окончания исполнения договора]
        myContractPage.setEndPerformanceDate(
                timer.getDateShiftWithoutDelimiters(Timer.getPublishNoticeDate(), "YEARS", "+1"));

        // Кнопка [Подписать договор]
        myContractPage.clickOnButton("Подписать договор");
        myContractPage.clickOnButton("Продолжить с договором");
        myContractPage.refreshPage();

        // Ожидаемый статус договора: [Договор заключён]
        myContractPage.checkContractStatus("Договор заключён", 30);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' выполняет поиск договора по номеру закупки")
    public void customerSearchesContractByNoticeNumber() throws Throwable {
        String stepName = "'Заказчик' выполняет поиск договора по номеру закупки";
        this.logStepName(stepName);

        timer.start();

        myContractsPage.searchContractByNoticeNumber(1);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' выполняет поиск {int} договоров по номеру извещения")
    public void customerSearchesContractByNoticeNumber(Integer expectedContractsFoundCounterValue) throws Throwable {
        String stepName = String.format("'Заказчик' выполняет поиск '%d' договоров по номеру извещения",
                expectedContractsFoundCounterValue);
        this.logStepName(stepName);

        timer.start();

        myContractsPage.searchContractByNoticeNumber(expectedContractsFoundCounterValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' выполняет поиск {int} доп. соглашений по номеру извещения")
    public void customerSearchesAddendumByNoticeNumber(Integer expectedAddendumFoundCounterValue) throws Throwable {
        String stepName = String.format("'Заказчик' выполняет поиск '%d' дополнительных соглашений по номеру извещения",
                expectedAddendumFoundCounterValue);
        this.logStepName(stepName);

        timer.start();

        myContractsPage.searchAddendumByNoticeNumber(expectedAddendumFoundCounterValue);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' выполняет поиск договора по номеру закупки для лота \"([^\"]*)\"$")
    public void customerSearchesContractByNoticeNumberWithLotNumber(String lotNumber) throws Throwable {
        String stepName =
                String.format("'Заказчик' выполняет поиск договора по номеру закупки для лота '%s'", lotNumber);
        this.logStepName(stepName);

        timer.start();

        myContractsPage.searchContractByNoticeNumber(lotNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет возможность выгрузить информацию о договоре$")
    public void customerChecksPossibilityToDownloadInformationAboutContract() throws Throwable {
        String stepName = "'Заказчик' проверяет возможность выгрузить информацию о договоре";
        this.logStepName(stepName);

        timer.start();

        myContractPage.checkPossibilityToDownloadInformationAboutContract();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет возможность экспорта истории в Excel-файл$")
    public void customerChecksPossibilityToExportHistoryAsExcelFile() throws Throwable {
        String stepName = "'Заказчик' проверяет возможность экспорта истории в Excel-файл";
        this.logStepName(stepName);

        timer.start();

        myContractPage.checkPossibilityToExportHistoryAsExcelFile();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет количество строк \"([^\"]*)\" в таблице 'Позиции договора'$")
    public void customerChecksNumberOfRowsInContractPositionsTable(String rows) {
        String stepName = String.format(
                "'Заказчик' проверяет количество строк '%s' в таблице 'Позиции договора'", rows);
        this.logStepName(stepName);

        timer.start();

        myContractPage.checkNumberOfRowsInContractPositionsTable(rows);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет столбец \"([^\"]*)\" строка \"([^\"]*)\" таблица 'Позиции договора' текст \"([^\"]*)\"$")
    public void customerChecksColumnByNameInLineByNumberFromContractPositionsTableForText(
            String columnName, String rowNumber, String cellValue) {
        String stepName = String.format(
                "'Заказчик' проверяет столбец '%s' строка '%s' таблица 'Позиции договора' текст '%s'",
                columnName, rowNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        myContractPage.verifyCellByNameInLineByNumberFromContractPasitionsTableCommercialForText(
                columnName, rowNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет столбец \"([^\"]*)\" строка \"([^\"]*)\" таблица 'Позиции договора' конкурентной закупки текст \"([^\"]*)\"$")
    public void customerChecksColumnByNameInLineByNumberFromContractPositionsTableCompetitiveForText(
            String columnName, String lineNumber, String cellValue) {
        String stepName = String.format(
                "'Заказчик' проверяет столбец '%s' строка '%s' таблица 'Позиции договора' конкурентной закупки текст '%s'",
                columnName, lineNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        myContractPage.verifyCellByNameInLineByNumberFromContractPositionsTableCompetitiveForText(
                columnName, lineNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет отсутствие кнопки \"([^\"]*)\" на странице 'Сведения о договоре'$")
    public void customerChecksTheAbsenceOfButtonByButtonName(String buttonName) {
        String stepName = String.format("'Заказчик' проверяет отсутствие кнопки '%s' на странице 'Сведения о договоре'",
                buttonName);
        this.logStepName(stepName);

        timer.start();

        myContractPage.checkTheAbsenceOfButtonByButtonName(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет наличие кнопки \"([^\"]*)\" на странице 'Сведения о договоре'$")
    public void customerChecksThePresenceOfButtonByButtonName(String buttonName) {
        String stepName = String.format("'Заказчик' проверяет наличие кнопки '%s' на странице 'Сведения о договоре'",
                buttonName);
        this.logStepName(stepName);

        timer.start();

        myContractPage.checkThePresenceOfButtonByButtonName(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на кнопку \"([^\"]*)\" на странице 'Сведения о договоре'$")
    public void customerClicksOnButtonByNameOnContractInfoPage(String buttonName) throws Throwable {
        String stepName =
                String.format("'Заказчик' нажимает на кнопку '%s' на странице 'Сведения о договоре'", buttonName);
        this.logStepName(stepName);

        timer.start();

        myContractPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' назначает сотрудника {string} ответственным за подписание договора")
    public void customerAssignResponsibilityForSigningDialog(String employeeName) throws Throwable {
        String stepName =
                String.format("'Заказчик' назначает сотрудника '%s' ответственным за подписание договора", employeeName);
        this.logStepName(stepName);

        timer.start();

        AssignResponsibilityForSigningDialog assignResponsibilityForSigningDialog =
                new AssignResponsibilityForSigningDialog(driver);

        assignResponsibilityForSigningDialog.
                dialogDisplayed().
                verifyRadioButtonSelected("Требуется подписание хотя бы одного назначенного ответственного").
                verifyRadioButtonSelected("Рабочие группы").
                selectRadioButton("Сотрудники").
                setEmployeeForSigningContract(employeeName).
                clickOnButton("Сохранить");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет историю на наличие записи о назначении ответственного за подписание договора")
    public void customerFillAssignResponsibilityForSigningDialog() throws Throwable {
        String stepName =
                "'Заказчик' проверяет историю на наличие записи о назначении ответственного за подписание договора";
        this.logStepName(stepName);

        timer.start();

        HistoryDialog historyDialog =
                new HistoryDialog(driver);
        historyDialog.
                dialogDisplayed().
                checkHistoryRecords("Назначение ответственных для подписания договора").
                checkHistoryRecords("Сотрудники. Ответственные за подписание договора: ЗАКАЗЧИК4ALL223 ЮЛ3 СОТРУДНИК1").
                clickOnButton("Закрыть");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет наличие блока 'Протокол разногласий' и ссылки на прикрепленный файл в этом блоке$")
    public void customerChecksDisagreementsProtocolHeaderPresenceAndLinkToAttachedFilePresence() {
        String stepName =
                "'Заказчик' проверяет наличие блока 'Протокол разногласий' и ссылки на прикрепленный файл в этом блоке";
        this.logStepName(stepName);

        timer.start();

        myContractPage.checkDisagreementsProtocolHeaderPresenceAndLinkToAttachedFilePresence();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' заполняет диалоговое окно 'Предупреждение' текстом 'Я отказываюсь заключать договор'$")
    public void customerFillsAlertDialogWindowWithTextImNotAgreeToConcludeThisContract() throws Throwable {
        String stepName =
                "'Заказчик' заполняет диалоговое окно 'Предупреждение' текстом 'Я отказываюсь заключать договор'";
        this.logStepName(stepName);

        timer.start();

        //--------------------------------------------------------------------------------------------------------------
        // Работа в диалоговом окне [Предупреждение] (после нажатия на кнопку [Протокол отказа от заключения договора])
        RefuseContractWarningDialog refuseContractWarningDialog = new RefuseContractWarningDialog(driver);
        refuseContractWarningDialog.
                ifDialogOpened().
                setReason("Я отказываюсь заключать договор").
                clickOnButtonByName("Кнопка Продолжить");
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит к просмотру раздела {string} на странице 'Сведения об исполнении договора ред.'")
    public void customerGoesToBlockOnContractsExecutionEditPage(String blockName) throws Throwable {
        String stepName = String.format("'Заказчик' переходит к просмотру раздела '%s' " +
                "на странице 'Сведения об исполнении договора ред.'", blockName);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.gotoBlock(blockName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что {string} содержит параметр теста {string} на странице 'Сведения об исполнении договора ред.'")
    public void customerChecksFieldForTestParameterValueOnContractsExecutionEditPage(
            String fieldName, String parameterName) {
        String stepName = String.format("'Заказчик' проверяет, что '%s' содержит параметр теста '%s' " +
                "на странице 'Сведения об исполнении договора ред.'", fieldName, parameterName);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.verifyField(fieldName, config.getParameter(parameterName));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} содержит не пустое значение на странице 'Сведения об исполнении договора ред.'")
    public void customerChecksFieldForNoneEmptyValueOnContractsExecutionEditPage(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' содержит не пустое значение " +
                "на странице 'Сведения об исполнении договора ред.'", fieldName);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.verifyFieldIsNotEmpty(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} соответствует шаблону {string} на странице 'Сведения об исполнении договора ред.'")
    public void customerChecksFieldValueForDatePatternOnContractsExecutionEditPage(String fieldName, String pattern) {
        String stepName =
                String.format("'Заказчик' проверяет, что поле '%s' соответствует шаблону '%s' " +
                        "на странице 'Сведения об исполнении договора ред.'", fieldName, pattern);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.verifyDateFieldForPattern(fieldName, pattern);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} содержит значение {string} на странице 'Сведения об исполнении договора ред.'")
    public void customerChecksFieldContentOnContractsExecutionEditPage(String fieldName, String value) {
        String stepName =
                String.format("Заказчик' проверяет, что поле '%s' содержит значение '%s' " +
                        "на странице 'Сведения об исполнении договора ред.'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} содержит пустое значение на странице 'Сведения об исполнении договора ред.'")
    public void customerVerifiesFieldIsEmptyOnContractsExecutionEditPage(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' содержит пустое значение " +
                "на странице 'Сведения об исполнении договора ред.'", fieldName);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.verifyFieldContentOrEmptyField(fieldName, "");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} доступно для редактирования на странице 'Сведения об исполнении договора ред.'")
    public void customerChecksFieldForThePossibilityOfEditingOnContractsExecutionEditPage(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' доступно для редактирования " +
                "на странице 'Сведения об исполнении договора ред.'", fieldName);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.checkFieldForThePossibilityOfEditing(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} не доступно для редактирования на странице 'Сведения об исполнении договора ред.'")
    public void customerChecksFieldForTheImpossibilityOfEditingOnContractsExecutionEditPage(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' не доступно для редактирования " +
                "на странице 'Сведения об исполнении договора ред.'", fieldName);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.checkFieldForTheImpossibilityOfEditing(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что флажок {string} {string} на странице 'Сведения об исполнении договора ред.'")
    public void customerVerifiesCheckBoxStateOnContractsExecutionEditPage(String checkBoxName, String checkBoxState) {
        String stepName = String.format("'Заказчик' проверяет, что флажок '%s' '%s' " +
                "на странице 'Сведения об исполнении договора ред.'", checkBoxName, checkBoxState);
        this.logStepName(stepName);

        timer.start();

        if (checkBoxState.equals("установлен")) contractsExecutionEditPage.verifyCheckBoxSelected(checkBoxName);
        else contractsExecutionEditPage.verifyCheckBoxNotSelected(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переключается на вкладку {string} на странице 'Сведения об исполнении договора ред.'")
    public void customerSwitchesOnLotTabByNameOnContractsExecutionEditPage(String tabName) throws Throwable {
        String stepName = String.format("'Заказчик' переключается на вкладку '%s' " +
                "на странице 'Сведения об исполнении договора ред.'", tabName);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.switchToTab(tabName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет дату и нулевое время в поле {string} в {string} со смещением {string} от времени публикации на странице 'Сведения об исполнении договора ред.'")
    public void customerFillsFieldWithDateAndZeroTimeOnContractsExecutionEditPage(
            String field, String shiftType, String amountAsString) throws Throwable {
        String stepName = String.format(
                "'Заказчик' заполняет дату и нулевое время в поле '%s' в '%s' со смещением '%s' от времени публикации " +
                        "на странице 'Сведения об исполнении договора ред.'", field, shiftType, amountAsString);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.setFieldClearClickAndSendKeys(field,
                timer.getDateShift(Timer.getPublishNoticeDate(), shiftType, amountAsString) + " 00:00");
        contractsExecutionEditPage.clickOnButton("'Закрыть' - в окне 'Календарь'");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет дату в поле {string} в {string} со смещением {string} от времени публикации на странице 'Сведения об исполнении договора ред.'")
    public void customerFillsFieldWithDateOnContractsExecutionEditPage(
            String field, String shiftType, String amountAsString) throws Throwable {
        String stepName = String.format(
                "'Заказчик' заполняет дату в поле '%s' в '%s' со смещением '%s' от времени публикации " +
                        "на странице 'Сведения об исполнении договора ред.'", field, shiftType, amountAsString);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.setFieldClearClickAndSendKeys(field,
                timer.getDateShift(Timer.getPublishNoticeDate(), shiftType, amountAsString));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Валюта' значением 'Российский рубль' из списка на странице 'Сведения об исполнении договора ред.'")
    public void customerFillsCurrencyFieldWithRussianRoubleValueOnContractsExecutionEditPage() throws Throwable {
        String stepName = "'Заказчик' заполняет поле 'Валюта' значением 'Российский рубль' из списка " +
                "на странице 'Сведения об исполнении договора ред.'";
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.fillCurrencyFieldWithRussianRoubleValue();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Наименование объекта закупки' значением 'Товар 1' из списка на странице 'Сведения об исполнении договора ред.'")
    public void customerFillsItemIdFieldWithItem1ValueOnContractsExecutionEditPage() throws Throwable {
        String stepName = "'Заказчик' заполняет поле 'Наименование объекта закупки' значением 'Товар 1' из списка " +
                "на странице 'Сведения об исполнении договора ред.'";
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.fillItemIdFieldWithItem1Value();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Тип документа' значением '01 - Товарная накладная' из списка на странице 'Сведения об исполнении договора ред.'")
    public void customerFillsDocumentTypeIdFieldWith01PackingListValueOnContractsExecutionEditPage() throws Throwable {
        String stepName = "'Заказчик' заполняет поле 'Тип документа' значением '01 - Товарная накладная' из списка " +
                "на странице 'Сведения об исполнении договора ред.'";
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.fillDocumentTypeIdFieldWith01PackingListValue();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Единица измерения код ОКЕИ' значением '1000 литров' из списка на странице 'Сведения об исполнении договора ред.'")
    public void customerFillsOkeiCodeIdFieldWith1000LitersValueOnContractsExecutionEditPage() throws Throwable {
        String stepName = "'Заказчик' заполняет поле 'Единица измерения код ОКЕИ' значением '1000 литров' из списка " +
                "на странице 'Сведения об исполнении договора ред.'";
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.fillOkeiCodeIdFieldWith1000LitersValue();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле со счётчиком {string} значением {string} на странице 'Сведения об исполнении договора ред.'")
    public void customerFillsFieldWithCounterByNameOnContractsExecutionEditPage(String fieldName, String value)
            throws Throwable {
        String stepName = String.format("'Заказчик' заполняет поле со счётчиком '%s' значением '%s' " +
                "на странице 'Сведения об исполнении договора ред.'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.setKendoNumericTextBoxField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле {string} значением {string} на странице 'Сведения об исполнении договора ред.'")
    public void customerFillsFieldByNameOnContractsExecutionEditPage(String fieldName, String value) throws Throwable {
        String stepName = String.format("'Заказчик' заполняет поле '%s' значением '%s' " +
                "на странице 'Сведения об исполнении договора ред.'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.setFieldClearClickAndSendKeys(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет поле договора 'Номер договора в ЕИС'")
    public void customerChecksContractNumberInEIS() {
        String stepName = "'Заказчик' проверяет номер договора в ЕИС";
        this.logStepName(stepName);

        timer.start();

        myContractPage.verifyFieldContentOrEmptyField("Номер договора в ЕИС",
                myContractPage.getField("Номер карточки договора"));

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет что поле договора 'Сумма договора' содержит значение \"([^\"]*)\"$")
    public void customerChecksContractNumberInEIS(String contractSum) {
        String stepName = "'Заказчик' проверяет сумму договора";
        this.logStepName(stepName);

        timer.start();

        myContractPage.verifyFieldContentOrEmptyField("Сумма договора", contractSum);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' устанавливает в окне 'Изменение цены договора' новую цену \"([^\"]*)\"$")
    public void customerSetNewContractPrice(String newContractPrice) {
        String stepName = String.format(
                "'Заказчик' устанавливает в окне 'Изменение цены договора' новую цену '%s' руб.", newContractPrice);
        this.logStepName(stepName);

        timer.start();

        myContractPage.setNewContractPrice(newContractPrice);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' устанавливает переключатель 'Причина' в 'Изменении цены договора'")
    public void customerClicksReasonForChangeContractPrice() throws Exception {
        String stepName = "'Заказчик' устанавливает переключатель 'Причина' в 'Изменении цены договора'";
        this.logStepName(stepName);

        timer.start();

        myContractPage.setReasonForNewContractPrice();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает кнопку 'Сохранить' в 'Изменении цены договора'")
    public void customerClicksSaveNewContractPrice() throws Exception {
        String stepName = "'Заказчик' нажимает кнопку 'Сохранить' в 'Изменении цены договора'";
        this.logStepName(stepName);

        timer.start();

        myContractPage.clickSaveNewContractPrice();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет, что поле \"([^\"]*)\" содержит пустое значение на странице 'Сведения о договоре'$")
    public void customerVerifiesFieldIsEmpty(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' содержит пустое значение", fieldName);
        this.logStepName(stepName);

        timer.start();

        myContractPage.verifyFieldContentOrEmptyField(fieldName, " ");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет, что поле \"([^\"]*)\" не заполнено на странице 'Сведения о договоре'$")
    public void customerVerifiesDataFieldIsEmpty(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' содержит пустое значение", fieldName);
        this.logStepName(stepName);

        timer.start();

        myContractPage.verifyFieldContentOrEmptyField(fieldName, "");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} соответствует шаблону {string} на странице 'Сведения о договоре'")
    public void customerChecksFieldValueForDatePatternOnContractPage(String fieldName, String pattern) {
        String stepName =
                String.format("'Заказчик' проверяет, что поле '%s' соответствует шаблону '%s' " +
                        "на странице 'Сведения о договоре'", fieldName, pattern);
        this.logStepName(stepName);

        timer.start();

        myContractPage.verifyDateFieldForPattern(fieldName, pattern);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет, что переключатель {string} отмечен на странице 'Сведения о договоре'")
    public void customerVerifiesRadioButtonSelected(String radioButtonName) {
        String stepName = String.format("'Заказчик А' проверяет, что переключатель '%s' отмечен", radioButtonName);
        this.logStepName(stepName);

        timer.start();

        myContractPage.verifyRadioButtonSelected(radioButtonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' устанавливает переключатель {string} на странице 'Сведения о договоре'")
    public void customerToggleRadioButtonSelected(String radioButtonName) {
        String stepName = String.format("'Заказчик А' устанавливает переключатель '%s'", radioButtonName);
        this.logStepName(stepName);

        timer.start();

        myContractPage.selectDesiredOptionInRadioButton(radioButtonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет в таблице 'Позиции товара' работу кнопки 'Экспорт объектов в Excel'")
    public void customerChecksExcelExportForTableOnContractPage() throws Exception {
        String stepName = "'Заказчик' проверяет в таблице 'Позиции товара' работу кнопки 'Экспорт объектов в Excel'";
        this.logStepName(stepName);

        timer.start();

        myContractPage.checkContractPositionsTableForExportToExcel();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что отображается информационное окно 'Сведения о поставщике'")
    public void customerChecksParticipantInfoPopupIsVisible() throws Throwable {
        String stepName = "'Заказчик' проверяет, что отображается информационное окно 'Сведения о поставщике'";
        this.logStepName(stepName);

        timer.start();

        myContractPage.isParticipantInfoDialogVisible();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что флажок {string} {string} на странице 'Сведения о договоре'")
    public void customerVerifiesCheckBoxStateOnContractEditPage(String checkBoxName, String checkBoxState) {
        String stepName = String.format("'Заказчик' проверяет, что флажок '%s' '%s' " +
                "на странице 'Сведения о договоре'", checkBoxName, checkBoxState);
        this.logStepName(stepName);

        timer.start();

        if (checkBoxState.equals("установлен")) myContractPage.verifyCheckBoxSelected(checkBoxName);
        else myContractPage.verifyCheckBoxNotSelected(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает кнопку {string} на странице 'Сведения об исполнении договора ред.'")
    public void customerClicksOnButtonByNameOnContractsExecutionEditPage(String buttonName) throws Throwable {
        String stepName = String.format("'Заказчик' нажимает кнопку '%s' " +
                "на странице 'Сведения об исполнении договора ред.'", buttonName);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет столбец {string} строка {string} таблица 'Объекты позиции сведений об исполнении' текст {string} на странице 'Сведения об исполнении договора ред.'")
    public void customerChecksCellByNameInLineByNumberFromPositionObjectsTableForTextOnContractsExecutionEditPage(
            String columnName, String rowNumber, String cellValue) {
        String stepName = String.format(
                "'Заказчик' проверяет столбец '%s' строка '%s' таблица 'Объекты позиции сведений об исполнении' " +
                        "текст '%s' на странице 'Сведения об исполнении договора ред.'",
                columnName, rowNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.
                verifyCellByNameInLineByNumberFromPositionObjectsTableForText(columnName, rowNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' прикрепляет необходимые документы на странице 'Сведения об исполнении договора ред.'")
    public void customerUploadsRequiredDocsOnContractsExecutionEditPage() throws Throwable {
        String stepName =
                "'Заказчик' прикрепляет необходимые документы на странице 'Сведения об исполнении договора ред.'";
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.uploadFileIntoDocuments(config.getAbsolutePathToFoundationDoc());
        contractsExecutionEditPage.checkLinkToDownloadFileIntoDocuments(config.getConfigParameter("FoundationDoc"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что кнопка {string} отображается и доступна на странице 'Сведения об исполнении договора ред.'")
    public void customerChecksThatTheButtonIsVisibleAndClickableOnContractsExecutionEditPage(String controlName) {
        String stepName = String.format("'Заказчик' проверяет, что кнопка '%s' отображается и доступна " +
                "на странице 'Сведения об исполнении договора ред.'", controlName);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionEditPage.isControlClickable(controlName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' открывает для просмотра раздел {string} с параметром {string} на странице 'Сведения об исполнении договора просм.'")
    public void customerOpensParameterizedBlockForViewOnContractsExecutionViewPage(
            String blockName, String blockNumber) throws Throwable {
        String stepName = String.format("'Заказчик' открывает для просмотра раздел '%s' с параметром '%s' " +
                "на странице 'Сведения об исполнении договора просм.'", blockName, blockNumber);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionViewPage.openParameterizedBlockForView(blockName, blockNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит к просмотру раздела {string} на странице 'Сведения об исполнении договора просм.'")
    public void customerGoesToBlockOnContractsExecutionViewPage(String blockName) throws Throwable {
        String stepName = String.format("'Заказчик' переходит к просмотру раздела '%s' " +
                "на странице 'Сведения об исполнении договора просм.'", blockName);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionViewPage.gotoBlock(blockName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что {string} содержит параметр теста {string} на странице 'Сведения об исполнении договора просм.'")
    public void customerChecksFieldForTestParameterValueOnContractsExecutionViewPage(
            String fieldName, String parameterName) {
        String stepName = String.format("'Заказчик' проверяет, что '%s' содержит параметр теста '%s' " +
                "на странице 'Сведения об исполнении договора просм.'", fieldName, parameterName);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionViewPage.verifyField(fieldName, config.getParameter(parameterName));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} содержит не пустое значение на странице 'Сведения об исполнении договора просм.'")
    public void customerChecksFieldForNoneEmptyValueOnContractsExecutionViewPage(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' содержит не пустое значение " +
                "на странице 'Сведения об исполнении договора просм.'", fieldName);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionViewPage.verifyFieldIsNotEmpty(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} соответствует шаблону {string} на странице 'Сведения об исполнении договора просм.'")
    public void customerChecksFieldValueForDatePatternOnContractsExecutionViewPage(String fieldName, String pattern) {
        String stepName =
                String.format("'Заказчик' проверяет, что поле '%s' соответствует шаблону '%s' " +
                        "на странице 'Сведения об исполнении договора просм.'", fieldName, pattern);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionViewPage.verifyDateFieldForPattern(fieldName, pattern);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} содержит значение {string} на странице 'Сведения об исполнении договора просм.'")
    public void customerChecksFieldContentOnContractsExecutionViewPage(String fieldName, String value) {
        String stepName =
                String.format("Заказчик' проверяет, что поле '%s' содержит значение '%s' " +
                        "на странице 'Сведения об исполнении договора просм.'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionViewPage.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} содержит пустое значение на странице 'Сведения об исполнении договора просм.'")
    public void customerVerifiesFieldIsEmptyOnContractsExecutionViewPage(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' содержит пустое значение " +
                "на странице 'Сведения об исполнении договора просм.'", fieldName);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionViewPage.verifyFieldContentOrEmptyField(fieldName, "");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет столбец {string} строка {string} таблица 'Сведения позиции исполнения договора по объекту закупки' текст {string} на странице 'Сведения об исполнении договора просм.'")
    public void customerChecksCellByNameInLineByNumberFromPositionInformationTableForTextOnContractsExecutionViewPage(
            String columnName, String rowNumber, String cellValue) {
        String stepName = String.format("'Заказчик' проверяет столбец '%s' строка '%s' таблица " +
                "'Сведения позиции исполнения договора по объекту закупки' текст '%s' на странице " +
                "'Сведения об исполнении договора просм.'", columnName, rowNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionViewPage.
                verifyCellByNameInLineByNumberFromPositionInformationTableForText(columnName, rowNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет столбец {string} строка {string} таблица 'Документы' текст {string} на странице 'Сведения об исполнении договора просм.'")
    public void customerChecksCellByNameInLineByNumberFromExecutionDocumentsTableForTextOnContractsExecutionViewPage(
            String columnName, String rowNumber, String cellValue) {
        String stepName = String.format("'Заказчик' проверяет столбец '%s' строка '%s' таблица " +
                        "'Документы' текст '%s' на странице 'Сведения об исполнении договора просм.'",
                columnName, rowNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionViewPage.
                verifyCellByNameInLineByNumberFromDocumentsTableForText(columnName, rowNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит по ссылке в столбце {string} строка {int} в таблице 'Документы' на странице 'Сведения об исполнении договора просм.'")
    public void customerClicksOnLinkInLineByNumberFromDocumentsTableOnContractsExecutionViewPage(
            String columnName, int rowNumber) throws Throwable {
        String stepName = String.format("'Заказчик' переходит по ссылке в столбце '%s' строка '%d' " +
                        "в таблице 'Документы' на странице 'Сведения об исполнении договора просм.'",
                columnName, rowNumber);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionViewPage.clickOnLinkInLineByNumberFromDocumentsTable(columnName, rowNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что кнопка {string} отображается и доступна на странице 'Сведения об исполнении договора просм.'")
    public void customerChecksThatTheButtonIsVisibleAndClickableOnContractsExecutionViewPage(String controlName) {
        String stepName = String.format("'Заказчик' проверяет, что кнопка '%s' отображается и доступна " +
                "на странице 'Сведения об исполнении договора просм.'", controlName);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionViewPage.isControlClickable(controlName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит по ссылке {string} на странице 'Сведения об исполнении договора просм.'")
    public void customerClicksOnLinkOnContractsExecutionViewPage(String linkName) throws Throwable {
        String stepName = String.format("'Заказчик' переходит по ссылке '%s' " +
                "на странице 'Сведения об исполнении договора просм.'", linkName);
        this.logStepName(stepName);

        timer.start();

        contractsExecutionViewPage.isControlClickable(linkName);
        contractsExecutionViewPage.clickOnButton(linkName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет столбец {string} строка {string} таблица 'Сведения об исполнении договора' текст {string} на странице 'Сведения о договоре'")
    public void customerChecksCellByNameInLineByNumberFromContractExecutionInformationTableForTextOnMyContractPage(
            String columnName, String rowNumber, String cellValue) {
        String stepName = String.format("'Заказчик' проверяет столбец '%s' строка '%s' таблица " +
                        "'Сведения об исполнении договора' текст '%s' на странице 'Сведения о договоре'",
                columnName, rowNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        myContractPage.verifyCellByNameInLineByNumberFromExecutionsGridForText(columnName, rowNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит к просмотру раздела {string} на странице 'Сведения о расторжении договора ред.'")
    public void customerGoesToBlockOnContractsTerminationEditPage(String blockName) throws Throwable {
        String stepName = String.format("'Заказчик' переходит к просмотру раздела '%s' " +
                "на странице 'Сведения о расторжении договора ред.'", blockName);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationEditPage.gotoBlock(blockName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что {string} содержит параметр теста {string} на странице 'Сведения о расторжении договора ред.'")
    public void customerChecksFieldForTestParameterValueOnContractsTerminationEditPage(
            String fieldName, String parameterName) {
        String stepName = String.format("'Заказчик' проверяет, что '%s' содержит параметр теста '%s' " +
                "на странице 'Сведения о расторжении договора ред.'", fieldName, parameterName);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationEditPage.verifyField(fieldName, config.getParameter(parameterName));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} содержит не пустое значение на странице 'Сведения о расторжении договора ред.'")
    public void customerChecksFieldForNoneEmptyValueOnContractsTerminationEditPage(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' содержит не пустое значение " +
                "на странице 'Сведения о расторжении договора ред.'", fieldName);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationEditPage.verifyFieldIsNotEmpty(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} соответствует шаблону {string} на странице 'Сведения о расторжении договора ред.'")
    public void customerChecksFieldValueForDatePatternOnContractsTerminationEditPage(String fieldName, String pattern) {
        String stepName =
                String.format("'Заказчик' проверяет, что поле '%s' соответствует шаблону '%s' " +
                        "на странице 'Сведения о расторжении договора ред.'", fieldName, pattern);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationEditPage.verifyDateFieldForPattern(fieldName, pattern);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} содержит значение {string} на странице 'Сведения о расторжении договора ред.'")
    public void customerChecksFieldContentOnContractsTerminationEditPage(String fieldName, String value) {
        String stepName =
                String.format("Заказчик' проверяет, что поле '%s' содержит значение '%s' " +
                        "на странице 'Сведения о расторжении договора ред.'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationEditPage.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет дату и нулевое время в поле {string} в {string} со смещением {string} от времени публикации на странице 'Сведения о расторжении договора ред.'")
    public void customerFillsFieldWithDateAndZeroTimeOnContractsTerminationEditPage(
            String field, String shiftType, String amountAsString) throws Throwable {
        String stepName = String.format(
                "'Заказчик' заполняет дату и нулевое время в поле '%s' в '%s' со смещением '%s' от времени публикации " +
                        "на странице 'Сведения о расторжении договора ред.'", field, shiftType, amountAsString);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationEditPage.setFieldClearClickAndSendKeys(field,
                timer.getDateShift(Timer.getPublishNoticeDate(), shiftType, amountAsString) + " 00:00");
        contractsTerminationEditPage.clickOnButton("'Закрыть' - в окне 'Календарь'");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} доступно для редактирования на странице 'Сведения о расторжении договора ред.'")
    public void customerChecksFieldForThePossibilityOfEditingOnContractsTerminationEditPage(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' доступно для редактирования " +
                "на странице 'Сведения о расторжении договора ред.'", fieldName);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationEditPage.checkFieldForThePossibilityOfEditing(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} содержит пустое значение на странице 'Сведения о расторжении договора ред.'")
    public void customerVerifiesFieldIsEmptyOnContractsTerminationEditPage(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' содержит пустое значение " +
                "на странице 'Сведения о расторжении договора ред.'", fieldName);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationEditPage.verifyFieldContentOrEmptyField(fieldName, "");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Основание расторжения договора' значением 'Судебный акт' из списка на странице 'Сведения о расторжении договора ред.'")
    public void customerFillsContractTerminationBaseFieldWithJudicialActValueOnContractsTerminationEditPage()
            throws Throwable {
        String stepName = "'Заказчик' заполняет поле 'Основание расторжения договора' значением 'Судебный акт' " +
                "из списка на странице 'Сведения о расторжении договора ред.'";
        this.logStepName(stepName);

        timer.start();

        contractsTerminationEditPage.fillContractTerminationBaseFieldWithJudicialActValue();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле {string} строкой в {int} байт на странице 'Сведения о расторжении договора ред.'")
    public void customerFillsFieldByNameWithLongStringOnContractsTerminationEditPage(String fieldName, int length) {
        String stepName = String.format(
                "'Заказчик' заполняет поле '%s' строкой в '%d' байт на странице 'Сведения о расторжении договора ред.'",
                fieldName, length);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationEditPage.setField(fieldName, StringUtils.repeat("s", length));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что флажок {string} {string} на странице 'Сведения о расторжении договора ред.'")
    public void customerVerifiesCheckBoxStateOnContractsTerminationEditPage(String checkBoxName, String checkBoxState) {
        String stepName = String.format("'Заказчик' проверяет, что флажок '%s' '%s' " +
                "на странице 'Сведения о расторжении договора ред.'", checkBoxName, checkBoxState);
        this.logStepName(stepName);

        timer.start();

        if (checkBoxState.equals("установлен")) contractsTerminationEditPage.verifyCheckBoxSelected(checkBoxName);
        else contractsTerminationEditPage.verifyCheckBoxNotSelected(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Тип документа-основания расторжения договора' значением 'Судебный акт' из списка на странице 'Сведения о расторжении договора ред.'")
    public void customerFillsContractTerminationBasisDocumentFieldWithJudicialActValueOnContractsTerminationEditPage()
            throws Throwable {
        String stepName = "'Заказчик' заполняет поле 'Тип документа-основания расторжения договора' " +
                "значением 'Судебный акт' из списка на странице 'Сведения о расторжении договора ред.'";
        this.logStepName(stepName);

        timer.start();

        contractsTerminationEditPage.fillContractTerminationBasisDocumentFieldWithJudicialActValue();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле {string} значением {string} на странице 'Сведения о расторжении договора ред.'")
    public void customerFillsFieldByNameOnContractsTerminationEditPage(String fieldName, String value)
            throws Throwable {
        String stepName = String.format("'Заказчик' заполняет поле '%s' значением '%s' " +
                "на странице 'Сведения о расторжении договора ред.'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationEditPage.setFieldClearClickAndSendKeys(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' прикрепляет необходимые документы на странице 'Сведения о расторжении договора ред.'")
    public void customerUploadsRequiredDocsOnContractsTerminationEditPage() throws Throwable {
        String stepName =
                "'Заказчик' прикрепляет необходимые документы на странице 'Сведения о расторжении договора ред.'";
        this.logStepName(stepName);

        timer.start();

        contractsTerminationEditPage.uploadFileIntoDocuments(config.getAbsolutePathToUserEntityDoc());
        contractsTerminationEditPage.
                checkLinkToDownloadFileIntoDocuments(config.getConfigParameter("UserEntityDoc"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что кнопка {string} отображается и доступна на странице 'Сведения о расторжении договора ред.'")
    public void customerChecksThatTheButtonIsVisibleAndClickableOnContractsTerminationEditPage(String controlName) {
        String stepName = String.format("'Заказчик' проверяет, что кнопка '%s' отображается и доступна " +
                "на странице 'Сведения о расторжении договора ред.'", controlName);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationEditPage.isControlClickable(controlName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает кнопку {string} на странице 'Сведения о расторжении договора ред.'")
    public void customerClicksOnButtonByNameOnContractsTerminationEditPage(String buttonName) throws Throwable {
        String stepName = String.format("'Заказчик' нажимает кнопку '%s' " +
                "на странице 'Сведения о расторжении договора ред.'", buttonName);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationEditPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит к просмотру раздела {string} на странице 'Сведения о расторжении договора просм.'")
    public void customerGoesToBlockOnContractsTerminationViewPage(String blockName) throws Throwable {
        String stepName = String.format("'Заказчик' переходит к просмотру раздела '%s' " +
                "на странице 'Сведения о расторжении договора просм.'", blockName);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationViewPage.gotoBlock(blockName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что {string} содержит параметр теста {string} на странице 'Сведения о расторжении договора просм.'")
    public void customerChecksFieldForTestParameterValueOnContractsTerminationViewPage(
            String fieldName, String parameterName) {
        String stepName = String.format("'Заказчик' проверяет, что '%s' содержит параметр теста '%s' " +
                "на странице 'Сведения о расторжении договора просм.'", fieldName, parameterName);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationViewPage.verifyField(fieldName, config.getParameter(parameterName));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} содержит не пустое значение на странице 'Сведения о расторжении договора просм.'")
    public void customerChecksFieldForNoneEmptyValueOnContractsTerminationViewPage(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' содержит не пустое значение " +
                "на странице 'Сведения о расторжении договора просм.'", fieldName);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationViewPage.verifyFieldIsNotEmpty(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} соответствует шаблону {string} на странице 'Сведения о расторжении договора просм.'")
    public void customerChecksFieldValueForDatePatternOnContractsTerminationViewPage(String fieldName, String pattern) {
        String stepName =
                String.format("'Заказчик' проверяет, что поле '%s' соответствует шаблону '%s' " +
                        "на странице 'Сведения о расторжении договора просм.'", fieldName, pattern);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationViewPage.verifyDateFieldForPattern(fieldName, pattern);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} содержит значение {string} на странице 'Сведения о расторжении договора просм.'")
    public void customerChecksFieldContentOnContractsTerminationViewPage(String fieldName, String value) {
        String stepName =
                String.format("Заказчик' проверяет, что поле '%s' содержит значение '%s' " +
                        "на странице 'Сведения о расторжении договора просм.'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationViewPage.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} содержит пустое значение на странице 'Сведения о расторжении договора просм.'")
    public void customerVerifiesFieldIsEmptyOnContractsTerminationViewPage(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' содержит пустое значение " +
                "на странице 'Сведения о расторжении договора просм.'", fieldName);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationViewPage.verifyFieldContentOrEmptyField(fieldName, "");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} содержит строку в {int} байт на странице 'Сведения о расторжении договора просм.'")
    public void customerChecksFieldLengthOnContractsTerminationViewPage(String fieldName, int length) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' содержит строку в '%d' байт " +
                "на странице 'Сведения о расторжении договора просм.'", fieldName, length);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationViewPage.verifyField(fieldName, StringUtils.repeat("s", length));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет столбец {string} строка {string} таблица 'Документы сведений о расторжении договора' текст {string} на странице 'Сведения о расторжении договора просм.'")
    public void customerChecksCellByNameInLineByNumberFromTerminationDocsTableForTextOnContractsTerminationViewPage(
            String columnName, String rowNumber, String cellValue) {
        String stepName = String.format("'Заказчик' проверяет столбец '%s' строка '%s' таблица " +
                        "'Документы сведений о расторжении договора' текст '%s' " +
                        "на странице 'Сведения о расторжении договора просм.'",
                columnName, rowNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationViewPage.
                verifyCellByNameInLineByNumberFromDocumentsTableForText(columnName, rowNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит по ссылке в столбце {string} строка {int} в таблице 'Документы сведений о расторжении договора' на странице 'Сведения о расторжении договора просм.'")
    public void customerClicksOnLinkInLineByNumberFromDocumentsTableOnContractsTerminationViewPage(
            String columnName, int rowNumber) throws Throwable {
        String stepName = String.format("'Заказчик' переходит по ссылке в столбце '%s' строка '%d' " +
                        "в таблице 'Документы сведений о расторжении договора' " +
                        "на странице 'Сведения о расторжении договора просм.'",
                columnName, rowNumber);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationViewPage.clickOnLinkInLineByNumberFromDocumentsTable(columnName, rowNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что кнопка {string} отображается и доступна на странице 'Сведения о расторжении договора просм.'")
    public void customerChecksThatTheButtonIsVisibleAndClickableOnContractsTerminationViewPage(String controlName) {
        String stepName = String.format("'Заказчик' проверяет, что кнопка '%s' отображается и доступна " +
                "на странице 'Сведения о расторжении договора просм.'", controlName);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationViewPage.isControlClickable(controlName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит по ссылке {string} на странице 'Сведения о расторжении договора просм.'")
    public void customerClicksOnLinkOnContractsTerminationViewPage(String linkName) throws Throwable {
        String stepName = String.format("'Заказчик' переходит по ссылке '%s' " +
                "на странице 'Сведения о расторжении договора просм.'", linkName);
        this.logStepName(stepName);

        timer.start();

        contractsTerminationViewPage.isControlClickable(linkName);
        contractsTerminationViewPage.clickOnButton(linkName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет столбец {string} строка {string} таблица 'Сведения о расторжении договора' текст {string} на странице 'Сведения о договоре'")
    public void customerChecksCellByNameInLineByNumberFromTerminationInformationTableForTextOnMyContractPage(
            String columnName, String rowNumber, String cellValue) {
        String stepName = String.format("'Заказчик' проверяет столбец '%s' строка '%s' таблица " +
                        "'Сведения о расторжении договора' текст '%s' на странице 'Сведения о договоре'",
                columnName, rowNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        myContractPage.verifyCellByNameInLineByNumberFromTerminationsGridForText(columnName, rowNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' переходит по ссылке 'Создать договор на ЭП' в таблице 'Договоры закупки' столбец 'Действия' ссылка \"([^\"]*)\"$")
    public void customerGoesToCreateContractOnElectronicTenderSiteLinkFromPurchaseContractsTable(String lineNumber)
            throws Throwable {
        String stepName = String.format("'Заказчик' переходит по ссылке 'Создать договор на ЭП' " +
                "в таблице 'Договоры закупки' столбец 'Действия' ссылка '%s'", lineNumber);
        this.logStepName(stepName);

        timer.start();

        viewNoticePage.goToCreateContractOnElectronicTenderSiteLinkFromPurchaseContractsTable(lineNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' переходит к просмотру раздела дополнительного соглашения \"([^\"]*)\"$")
    public void customerGoesToViewAddendumPagePartition(String partitionName) throws Throwable {
        String stepName = String.format("'Заказчик' переходит к просмотру раздела дополнительного соглашения '%s'",
                partitionName);
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.goToPartition(partitionName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на кнопку \"([^\"]*)\" на странице 'Сведения о дополнительном соглашении'$")
    public void customerClicksOnButtonByNameOnAddendumInfoPage(String buttonName) throws Throwable {
        String stepName =
                String.format("'Заказчик' нажимает на кнопку '%s' на странице 'Сведения о договоре'", buttonName);
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет статус дополнительного соглашения \"([^\"]*)\"$")
    public void customerChecksAddendumStatus(String status) throws Throwable {
        String stepName = String.format("'Заказчик' проверяет статус дополнительного соглашения '%s'", status);
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.checkAddendumStatus(status, 30);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет, что номер дополнительного соглашения не совпадает с номером основного договора$")
    public void customerChecksThatAddendumNumberNotEqualToContractNumber() {
        String stepName = "'Заказчик' проверяет, что номер дополнительного соглашения не совпадает с номером договора";
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.checkAddendumNumberNotEqualToContractNumber();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет поле дополнительного соглашения 'Номер договора в ЕИС'")
    public void customerChecksAddendumNumberInEIS() {
        String stepName = "'Заказчик' проверяет номер договора (доп.соглашения) в ЕИС";
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.verifyFieldContentOrEmptyField("Номер договора в ЕИС",
                contractsAddendumPage.getField("Номер карточки договора"));

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет что поле доп. соглашения 'Сумма договора с учетом доп. соглашений' содержит значение \"([^\"]*)\"$")
    public void customerChecksAddendumPrice(String contractSum) {
        String stepName = "'Заказчик' проверяет сумму договора";
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.verifyFieldContentOrEmptyField("Сумма договора с учетом дополнительных соглашений",
                contractSum);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что флажок {string} {string} на странице 'Сведения о дополнительном соглашении'")
    public void customerVerifiesCheckBoxStateOnAddendumEditPage(String checkBoxName, String checkBoxState) {
        String stepName = String.format("'Заказчик' проверяет, что флажок '%s' '%s' " +
                "на странице 'Сведения о дополнительном соглашении'", checkBoxName, checkBoxState);
        this.logStepName(stepName);

        timer.start();

        if (checkBoxState.equals("установлен")) contractsAddendumPage.verifyCheckBoxSelected(checkBoxName);
        else contractsAddendumPage.verifyCheckBoxNotSelected(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет, что поле \"([^\"]*)\" содержит пустое значение на странице 'Сведения о дополнительном соглашении'$")
    public void customerVerifiesAddendumFieldIsEmpty(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' содержит пустое значение", fieldName);
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.verifyFieldContentOrEmptyField(fieldName, " ");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} соответствует шаблону {string} на странице 'Сведения о дополнительном соглашении'")
    public void customerChecksFieldValueForDatePatternOnAddendumPage(String fieldName, String pattern) {
        String stepName =
                String.format("'Заказчик' проверяет, что поле '%s' соответствует шаблону '%s' " +
                        "на странице 'Сведения о дополнительном соглашении'", fieldName, pattern);
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.verifyDateFieldForPattern(fieldName, pattern);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет, что поле \"([^\"]*)\" не заполнено на странице 'Сведения о дополнительном соглашении'$")
    public void customerVerifiesDataFieldIsEmptyOnAddendumPage(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' содержит пустое значение", fieldName);
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.verifyFieldContentOrEmptyField(fieldName, "");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' проверяет, что переключатель {string} не отмечен на странице 'Сведения о дополнительном соглашении'")
    public void customerVerifiesRadioButtonNotSelected(String radioButtonName) {
        String stepName = String.format("'Заказчик А' проверяет, что переключатель '%s' не отмечен", radioButtonName);
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.verifyRadioButtonNotSelected(radioButtonName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' прикрепляет файл с проектом доп. соглашения$")
    public void customerAttachesFileWithAddendumProject() throws Throwable {
        String stepName = "'Заказчик' прикрепляет файл с проектом дополнительного соглашения";
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.attachFileToAddendumProject();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' прикрепляет файл в разделе доп. соглашения 'Другие документы'$")
    public void customerAttachesFileWithAddendumOtherDocuments() throws Throwable {
        String stepName = "'Заказчик' прикрепляет файл в разделе доп. соглашения 'Другие документы'";
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.attachAnotherFileToAddendumProject();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' направляет доп. соглашение Поставщику$")
    public void customerSendsAddendumToSupplier() throws Throwable {
        String stepName = "'Заказчик' направляет дополнительное соглашение Поставщику";
        this.logStepName(stepName);

        timer.start();

        //--------------------------------------------------------------------------------------------------------------
        long slowSiteTimeoutInMinutes = Long.parseLong(config.getConfigParameter("SlowSiteTimeoutInMinutes"));
        boolean slowSite = slowSiteTimeoutInMinutes > 0;
        //--------------------------------------------------------------------------------------------------------------
        contractsAddendumPage.clickOnButton("Сохранить");
        //--------------------------------------------------------------------------------------------------------------
        if (slowSite) TimeUnit.MINUTES.sleep(slowSiteTimeoutInMinutes); // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
        contractsAddendumPage.checkAddendumStatus("Договор формируется", 30);
        contractsAddendumPage.clickOnButton("Направить участнику");
        //--------------------------------------------------------------------------------------------------------------
        if (slowSite) TimeUnit.MINUTES.sleep(slowSiteTimeoutInMinutes); // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
        contractsAddendumPage.checkPageUrl("Заказчик-Направьте договор");
        contractsAddendumPage.checkAddendumStatus("Договор отправлен участнику на подпись", 30);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' открывает страницу 'Сведения о доп. соглашении' по ссылке на номер карточки доп. соглашения")
    public void customerOpensAddendumInfoPageByClickToAddendumCardNumber() throws Throwable {
        String stepName = "'Заказчик' открывает страницу 'Сведения о доп. соглашении' по ссылке на номер карточки доп. соглашения";
        this.logStepName(stepName);

        timer.start();

        myContractsPage.openContractInfoPageByClickToContractCardNumber(0);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' подписывает доп. соглашение$")
    public void customerSignsAddendum() throws Throwable {
        String stepName = "'Заказчик' подписывает доп. соглашение";
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.checkPageUrl("Заказчик-Подпишите договор");

        // Проверка статуса доп. соглашения: [Договор подписан участником]
        contractsAddendumPage.checkAddendumStatus("Договор подписан участником", 30);

        // Заполнение поля [Дата начала исполнения договора]
        contractsAddendumPage.setStartPerformanceDate(
                timer.getDateShiftWithoutDelimiters(Timer.getPublishNoticeDate(), "MONTHS", "+1"));

        // Заполнение поля [Дата окончания исполнения договора]
        contractsAddendumPage.setEndPerformanceDate(
                timer.getDateShiftWithoutDelimiters(Timer.getPublishNoticeDate(), "YEARS", "+1"));

        // Кнопка [Подписать договор]
        contractsAddendumPage.clickOnButton("Подписать договор");
        contractsAddendumPage.clickOnButton("Продолжить с договором");
        contractsAddendumPage.refreshPage();

        // Ожидаемый статус договора: [Договор заключён]
        contractsAddendumPage.checkAddendumStatus("Договор заключён", 30);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверят раздел дополнительного соглашения 'Сведения о закупке и предмете договора'$")
    public void customerChecksAddendumPurchaseAndItemInfo() {
        String stepName = "'Заказчик' проверят раздел доп. соглашения 'Сведения о закупке и предмете договора (дополнительное соглашение)'";
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.checkAddendumPurchaseAndItemInfo();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле {string} значением {string} на странице 'Сведения о дополнительном соглашении'")
    public void customerFillsFieldByName(String fieldName, String value) throws Throwable {
        String stepName = String.format("'Заказчик' заполняет поле '%s' значением '%s'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.setFieldClearClickAndSendKeys(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле Код ОКПД2 значением {string} на странице 'Сведения о дополнительном соглашении'")
    public void customerFillsLotOkpd2Field(String lotOkpd2) throws Throwable {
        String stepName = String.format("'Заказчик' заполняет поле Код ОКПД2 значением '%s'" +
                "на странице 'Сведения о дополнительном соглашении'", lotOkpd2);
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.setLotOkpd2(lotOkpd2);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле Код ОКВЭД2 значением {string} на странице 'Сведения о дополнительном соглашении'")
    public void customerFillsLotOkved2Field(String lotOkved2) throws Throwable {
        String stepName = String.format("'Заказчик' заполняет поле Код ОКВЭД2 значением '%s'" +
                "на странице 'Сведения о дополнительном соглашении'", lotOkved2);
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.setLotOkved2(lotOkved2);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Единица измерения - код ОКЕИ' {string} значением из списка на странице 'Сведения о дополнительном соглашении'")
    public void customerFillsOkeiCode(String okeiNumber) throws Throwable {
        String stepName =
                String.format("'Заказчик А' заполняет поле 'Единица измерения - код ОКЕИ' '%s' значением из списка", okeiNumber);
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.fillOkeiCode(okeiNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле со счётчиком {string} значением {string} на странице 'Сведения о дополнительном соглашении'")
    public void customerFillsFieldWithCounterByName(String fieldName, String value) throws Throwable {
        String stepName = String.format("'Заказчик' заполняет поле со счётчиком '%s' значением '%s", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.setKendoNumericTextBoxField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Страна происхождения товара' значением 'Российская федерация'")
    public void customerFillsCountryOfOriginFieldWithTheValueRussianFederation() throws Throwable {
        String stepName =
                "'Заказчик' заполняет поле 'Страна происхождения товара' значением 'Российская федерация'";
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.setCountryOfOriginFieldWithTheValueRussianFederation();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет наличие блока 'Протокол разногласий' и что блок не заполнен$")
    public void customerChecksDisagreementsProtocolHeaderPresenceAndBlockIsEmpty() {
        String stepName =
                "'Заказчик' проверяет наличие блока 'Протокол разногласий' и что блок не заполнен";
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.checkDisagreementsProtocolHeaderPresenceAndBlockIsEmpty();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет наличие и заполнение блока 'Документы в обеспечение исполнения дополнительного соглашения и другие документы'$")
    public void customerChecksFinSupportAndOtherDocsHeaderPresence() {
        String stepName =
                "'Заказчик' проверяет наличие блока 'Документы в обеспечение исполнения дополнительного соглашения и другие документы'";
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.checkFinSupportAndOtherDocsHeaderPresenceAndAutocompletion();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет возможность выгрузить информацию о дополнительном соглашении$")
    public void customerChecksPossibilityToDownloadInformationAboutAddendum() throws Throwable {
        String stepName = "'Заказчик' проверяет возможность выгрузить информацию о дополнительном соглашении";
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.checkPossibilityToDownloadInformationAboutAddendum();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет что статус доп. соглашения на странице договора содержит значение \"([^\"]*)\"$")
    public void customerChecksContractAddendumStatus(String addendumStatus) {
        String stepName = "'Заказчик' проверяет статус доп. соглашения";
        this.logStepName(stepName);

        timer.start();

        myContractPage.verifyFieldContentOrEmptyField("Статус доп. соглашения", addendumStatus);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет дату и нулевое время в поле {string} в {string} со смещением {string} от времени публикации на странице 'Сведения о дополнительном соглашении'")
    public void customerFillsFieldWithDateAndZeroTimeOnAddendumPage(
            String field, String shiftType, String amountAsString) throws Throwable {
        String stepName = String.format(
                "'Заказчик' заполняет дату и нулевое время в поле '%s' в '%s' со смещением '%s' от времени публикации " +
                        "на странице 'Сведения о дополнительном соглашении'", field, shiftType, amountAsString);
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.setFieldClearClickAndSendKeys(field,
                timer.getDateShift(Timer.getPublishNoticeDate(), shiftType, amountAsString) + " 00:00");
        //contractsAddendumPage.clickOnButton("'Закрыть' - в окне 'Календарь'");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' устанавливает флажок в дополнительном соглашении \"([^\"]*)\"$")
    public void customerSetsCheckBoxValueOnAddendumPage(String checkBoxName) {
        String stepName = String.format("'Заказчик' устанавливает флажок в дополнительном соглашении '%s'", checkBoxName);
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.setCheckBoxValue(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что переключатель {string} отмечен на странице 'Сведения о дополнительном соглашении'")
    public void customerVerifiesAddendumRadioButtonSelected(String radioButtonName) {
        String stepName = String.format("'Заказчик' проверяет, что переключатель '%s' отмечен", radioButtonName);
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.verifyRadioButtonSelected(radioButtonName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет отсутствие раздела \"([^\"]*)\" на странице 'Сведения о доп. соглашении'$")
    public void customerChecksTheAbsenceOfBlockByBlockName(String blockName) {
        String stepName = String.format("'Заказчик' проверяет отсутствие раздела '%s' на странице 'Сведения о доп. соглашении'",
                blockName);
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.checkTheAbsenceOfBlockByBlockName(blockName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет количество строк \"([^\"]*)\" в таблице 'Позиции договора - доп.соглашения'$")
    public void customerChecksNumberOfRowsInAddendumPositionsTable(String rows) {
        String stepName = String.format(
                "'Заказчик' проверяет количество строк '%s' в таблице 'Позиции договора'", rows);
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.checkNumberOfRowsInAddendumPositionsTable(rows);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет столбец \"([^\"]*)\" строка \"([^\"]*)\" таблица 'Позиции договора - доп.соглашения' текст \"([^\"]*)\"$")
    public void customerChecksColumnByNameInLineByNumberFromAddendumPositionsTableForText(
            String columnName, String rowNumber, String cellValue) {
        String stepName = String.format(
                "'Заказчик' проверяет столбец '%s' строка '%s' таблица 'Позиции договора (дополнительного соглашения)' текст '%s'",
                columnName, rowNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.
                verifyCellByNameInLineByNumberFromAddendumPasitionsTableCommercialForText(columnName, rowNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет столбец \"([^\"]*)\" строка \"([^\"]*)\" таблица 'Позиции договора - доп.соглашения' конкурентной закупки текст \"([^\"]*)\"$")
    public void customerChecksColumnByNameInLineByNumberFromAddendumPositionsTableCompetitiveForText(
            String columnName, String lineNumber, String cellValue) {
        String stepName = String.format(
                "'Заказчик' проверяет столбец '%s' строка '%s' таблица 'Позиции договора (дополнительного соглашения)' текст '%s'",
                columnName, lineNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        contractsAddendumPage.
                verifyCellByNameInLineByNumberFromAddendumPositionsTableCompetitiveForText(
                        columnName, lineNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }
}
