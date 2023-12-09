package ru.StepDefinitions.Customer.Plan;

import Helpers.Timer;
import cucumber.api.java.en.And;
import org.junit.Assert;
import ru.PageObjects.Customer.Notice.PublishedDialog;
import ru.PageObjects.Customer.Plans.CreateTradePlanPage;
import ru.PageObjects.Customer.Plans.EditTradePlanPage;
import ru.PageObjects.Customer.Plans.TradePlansPage;
import ru.PageObjects.Customer.Plans.ViewTradePlanPage;
import ru.StepDefinitions.AbstractStepDefinitions;

import java.util.Date;
import java.util.List;

import static org.apache.commons.lang.WordUtils.capitalizeFully;

/**
 * Класс описывающий шаги работы Заказчика с планами закупок.
 * Created by Vladimir V. Klochkov on 12.02.2021.
 * Updated by Vladimir V. Klochkov on 31.03.2021.
 */
public class CustomerPlanStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final PublishedDialog publishedDialog = new PublishedDialog(driver);
    private final CreateTradePlanPage createTradePlanPage = new CreateTradePlanPage(driver);
    private final EditTradePlanPage editTradePlanPage = new EditTradePlanPage(driver);
    private final ViewTradePlanPage viewTradePlanPage = new ViewTradePlanPage(driver);
    private final TradePlansPage tradePlansPage = new TradePlansPage(driver);

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    @And("^'Заказчик' проверяет фильтры на странице 'Планы закупок'$")
    public void customerChecksPageFiltersOnTradePlansPage() throws Throwable {
        String stepName = "'Заказчик' проверяет фильтры на странице 'Планы закупок'";
        this.logStepName(stepName);

        timer.start();

        tradePlansPage.checkPageFilters();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} на странице 'Планы закупок'")
    public void customerClicksOnButtonOnTradePlansPage(String buttonName) throws Throwable {
        String stepName = String.format("'Заказчик' нажимает на кнопку '%s' на странице 'Планы закупок'", buttonName);
        this.logStepName(stepName);

        timer.start();

        tradePlansPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' создает черновик нового плана коммерческих закупок$")
    public void customerCreatesNewPlanOfCommercialPurchases() throws Throwable {
        String stepName = "'Заказчик' создает черновик нового плана коммерческих закупок";
        this.logStepName(stepName);

        timer.start();

        String tradePlanName =
                String.format("Тестовый план закупки %s.", timer.getCurrentDateTime("ddMMyyyyHHmmSS"));
        Date publishNoticeDate = new Date();    // Время публикации извещения должно быть максимально близко к реальному
        String currentDate = timer.getDateShift(publishNoticeDate, "MONTHS", "0");
        String futureDate = timer.getDateShift(publishNoticeDate, "MONTHS", "1");
        //--------------------------------------------------------------------------------------------------------------
        config.setParameter("TradePlanName", tradePlanName);
        config.setParameter("TradePlanCurrentDate", currentDate);
        config.setParameter("TradePlanFutureDate", futureDate);
        //--------------------------------------------------------------------------------------------------------------
        createTradePlanPage.setFieldClearClickAndSendKeys("Наименование", tradePlanName);
        createTradePlanPage.setKind();
        createTradePlanPage.setFieldClearClickAndSendKeys("Период C", currentDate);
        createTradePlanPage.setFieldClearClickAndSendKeys("Период По", futureDate);
        createTradePlanPage.setFieldClearClickAndSendKeys("Дата утверждения", currentDate);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет созданный черновик нового плана коммерческих закупок")
    public void customerChecksJustCreatedPlanOfCommercialPurchasesDraft() {
        String stepName = "'Заказчик' проверяет созданный черновик нового плана коммерческих закупок";
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.storeTradePlanNumber();      // это нужно для дальнейшего поиска данного плана закупок в тесте
        editTradePlanPage.verifyField("Наименование", config.getParameter("TradePlanName"));
        editTradePlanPage.verifyField("Вид", config.getParameter("TradePlanKind"));
        editTradePlanPage.verifyField("Период C", config.getParameter("TradePlanCurrentDate"));
        editTradePlanPage.verifyField("Период По", config.getParameter("TradePlanFutureDate"));
        editTradePlanPage.verifyField("Дата утверждения", config.getParameter("TradePlanCurrentDate"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' импортирует позиции из Excel-файла в созданный черновик плана коммерческих закупок")
    public void customerImportsPlanPositionsIntoJustCreatedPlanOfCommercialPurchasesDraft() throws Throwable {
        String stepName =
                "'Заказчик' импортирует позиции из Excel-файла в созданный черновик плана коммерческих закупок";
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.loadTradePlanPositions(config.getAbsolutePathToTradePlanPositions());

        List<String> positionNumbers = editTradePlanPage.getPositionNumbersFromTradePlanTable();

        String errMess = String.format(
                "[ОШИБКА]: таблица [%s] на странице [%s] пустая после импорта позиций из Excel-файла",
                "Позиции плана", "Редактирование плана закупки");

        Assert.assertNotEquals(errMess, 0, positionNumbers.size());

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' публикует план коммерческих закупок")
    public void customerPublishesJustCreatedPlanOfCommercialPurchasesDraft() throws Throwable {
        String stepName = "'Заказчик' публикует план коммерческих закупок";
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.clickOnButton("Опубликовать");

        timer.printStepTotalTime(stepName);

    }

    @And("'Заказчик' нажимает на кнопку 'Перейти к реестру планов закупок' в окне диалога 'Опубликовано'")
    public void customerClicksOnGoToPurchasesRegistryButtonInPublishedDialogWindow() throws Throwable {
        String stepName =
                "'Заказчик' нажимает на кнопку 'Перейти к реестру планов закупок' в окне диалога 'Опубликовано'";
        this.logStepName(stepName);

        timer.start();

        publishedDialog.clickButtonInPopupWindow("Перейти к реестру планов закупок");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' ищет созданный план коммерческих закупок$")
    public void customerSearchesPlanOfCommercialPurchases() throws Throwable {
        String stepName = "'Заказчик' ищет созданный план коммерческих закупок";
        this.logStepName(stepName);

        timer.start();

        tradePlansPage.searchPlanByNumberAndCheckLinkForItInSearchResults(config.getParameter("TradePlanNumber"));

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' открывает созданный план коммерческих закупок$")
    public void customerOpensPlanOfCommercialPurchases() throws Throwable {
        String stepName = "'Заказчик' открывает созданный план коммерческих закупок";
        this.logStepName(stepName);

        timer.start();

        tradePlansPage.openExistingPlanByNumber(config.getParameter("TradePlanNumber"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит к просмотру раздела {string} на странице 'Создание плана закупки'")
    public void customerGoesToBlockOnCreateTradePlanPage(String blockName) throws Throwable {
        String stepName = String.format("'Заказчик' переходит к просмотру раздела '%s' " +
                "на странице 'Создание плана закупки'", blockName);
        this.logStepName(stepName);

        timer.start();

        createTradePlanPage.gotoBlock(blockName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Наименование' сгенерированным именем на странице 'Создание плана закупки'")
    public void customerFillsNameFieldWithGeneratedNameOnCreateTradePlanPage() throws Throwable {
        String stepName =
                "'Заказчик' заполняет поле 'Наименование' сгенерированным именем на странице 'Создание плана закупки'";
        this.logStepName(stepName);

        timer.start();

        String tradePlanName =
                String.format("Тестовый план закупки %s.", timer.getCurrentDateTime("ddMMyyyyHHmmSS"));
        config.setParameter("TradePlanName", tradePlanName);
        createTradePlanPage.setFieldClearClickAndSendKeys("Наименование", tradePlanName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} содержит не пустое значение на странице 'Создание плана закупки'")
    public void customerVerifiesThatTheFieldIsNotEmptyOnCreateTradePlanPage(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' содержит не пустое значение " +
                "на странице 'Создание плана закупки'", fieldName);
        this.logStepName(stepName);

        timer.start();

        createTradePlanPage.verifyFieldIsNotEmpty(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} содержит значение {string} на странице 'Создание плана закупки'")
    public void customerChecksFieldContentOnCreateTradePlanPage(String fieldName, String value) {
        String stepName = String.format("Заказчик' проверяет, что поле '%s' содержит значение '%s' " +
                "на странице 'Создание плана закупки'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        createTradePlanPage.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет дату {string} в {string} со смещением {string} от текущей на странице 'Создание плана закупки'")
    public void customerFillsDateFieldOnCreateTradePlanPage(String field, String shiftType, String amountAsString)
            throws Throwable {
        String stepName = String.format(
                "'Заказчик' заполняет дату '%s' в '%s' со смещением '%s' от текущей на странице 'Создание плана закупки'",
                field, shiftType, amountAsString);
        this.logStepName(stepName);

        timer.start();

        createTradePlanPage.setFieldClearClickAndSendKeys(
                field, timer.getDateShift(Timer.getPublishNoticeDate(), shiftType, amountAsString));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет год {string} в {string} со смещением {string} от текущего на странице 'Создание плана закупки'")
    public void customerFillsYearFieldOnCreateTradePlanPage(String field, String shiftType, String amountAsString)
            throws Throwable {
        String stepName = String.format(
                "'Заказчик' заполняет год '%s' в '%s' со смещением '%s' от текущего на странице 'Создание плана закупки'",
                field, shiftType, amountAsString);
        this.logStepName(stepName);

        timer.start();

        createTradePlanPage.setKendoNumericTextBoxField(
                field, timer.getYearShift(Timer.getPublishNoticeDate(), shiftType, amountAsString));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} на странице 'Создание плана закупки'")
    public void customerClicksOnContinueButtonOnCreateTradePlanPage(String buttonName) throws Throwable {
        String stepName =
                String.format("'Заказчик' нажимает на кнопку '%s' на странице 'Создание плана закупки'", buttonName);
        this.logStepName(stepName);

        timer.start();

        createTradePlanPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит к просмотру раздела {string} на странице 'Редактирование плана закупки'")
    public void customerGoesToBlockOnEditTradePlanPage(String blockName) throws Throwable {
        String stepName = String.format("'Заказчик' переходит к просмотру раздела '%s' " +
                "на странице 'Редактирование плана закупки'", blockName);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.gotoBlock(blockName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что {string} содержит параметр теста {string} на странице 'Редактирование плана закупки'")
    public void customerChecksFieldForTestParameterValueOnEditTradePlanPage(String fieldName, String parameterName) {
        String stepName = String.format("'Заказчик' проверяет, что '%s' содержит параметр теста '%s' " +
                "на странице 'Редактирование плана закупки'", fieldName, parameterName);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.verifyField(fieldName, config.getParameter(parameterName));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} содержит не пустое значение на странице 'Редактирование плана закупки'")
    public void customerVerifiesThatTheFieldIsNotEmptyOnEditTradePlanPage(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' содержит не пустое значение " +
                "на странице 'Редактирование плана закупки'", fieldName);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.verifyFieldIsNotEmpty(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} содержит значение {string} на странице 'Редактирование плана закупки'")
    public void customerChecksFieldContentOnEditTradePlanPage(String fieldName, String value) {
        String stepName = String.format("Заказчик' проверяет, что поле '%s' содержит значение '%s' " +
                "на странице 'Редактирование плана закупки'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет дату в поле {string} в {string} со смещением {string} от текущей на странице 'Редактирование плана закупки'")
    public void customerChecksDateFieldOnEditTradePlanPage(String field, String shiftType, String amountAsString) {
        String stepName = String.format("'Заказчик' проверяет дату в поле '%s' в '%s' со смещением '%s' от текущей " +
                "на странице 'Редактирование плана закупки'", field, shiftType, amountAsString);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.verifyField(field, timer.getDateShift(Timer.getPublishNoticeDate(), shiftType, amountAsString));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет год в поле {string} в {string} со смещением {string} от текущего на странице 'Редактирование плана закупки'")
    public void customerChecksYearFieldOnEditTradePlanPage(String field, String shiftType, String amountAsString) {
        String stepName = String.format("'Заказчик' проверяет год в поле '%s' в '%s' со смещением '%s' от текущего " +
                "на странице 'Редактирование плана закупки'", field, shiftType, amountAsString);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.verifyField(field, timer.getYearShift(Timer.getPublishNoticeDate(), shiftType, amountAsString));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что переключатель {string} не отмечен на странице 'Редактирование плана закупки'")
    public void customerVerifiesRadioButtonNotSelectedOnEditTradePlanPage(String radioButtonName) {
        String stepName = String.format(
                "'Заказчик' проверяет, что переключатель '%s' не отмечен на странице 'Редактирование плана закупки'",
                radioButtonName);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.verifyRadioButtonNotSelected(radioButtonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что переключатель {string} отмечен на странице 'Редактирование плана закупки'")
    public void customerVerifiesRadioButtonSelectedOnEditTradePlanPage(String radioButtonName) {
        String stepName = String.format(
                "'Заказчик' проверяет, что переключатель '%s' отмечен на странице 'Редактирование плана закупки'",
                radioButtonName);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.verifyRadioButtonSelected(radioButtonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' устанавливает переключатель {string} в положение {string} на странице 'Редактирование плана закупки'")
    public void customerSetsRadioButtonToDesiredPositionOnEditTradePlanPage(
            String radioButtonName, String radioButtonPosition) throws Throwable {
        String stepName = String.format("'Заказчик' устанавливает переключатель '%s' в положение '%s' " +
                "на странице 'Редактирование плана закупки'", radioButtonName, radioButtonPosition);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.selectRadioButton(radioButtonPosition);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что кнопка {string} отображается и доступна на странице 'Редактирование плана закупки'")
    public void customerChecksThatTheButtonIsVisibleAndClickableOnEditTradePlanPage(String controlName) {
        String stepName = String.format("'Заказчик' проверяет, что кнопка '%s' отображается и доступна " +
                "на странице 'Редактирование плана закупки'", controlName);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.isControlClickable(controlName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' импортирует позиции плана ТРУ из Excel-файла в созданный черновик на странице 'Редактирование плана закупки'")
    public void customerImportsTruPlanPositionsIntoJustCreatedDraftOfPlanOnEditTradePlanPage() throws Throwable {
        String stepName =
                "'Заказчик' импортирует позиции плана ТРУ из Excel-файла в созданный черновик " +
                        "на странице 'Редактирование плана закупки'";
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.loadTradePlanPositions(config.getAbsolutePathToProcurPlanOfGoodsWorksAndServicesPositions());

        List<String> positionNumbers = editTradePlanPage.getPositionNumbersFromTradePlanTable();

        String errMess = String.format(
                "[ОШИБКА]: таблица [%s] на странице [%s] пустая после импорта позиций из Excel-файла",
                "Позиции плана", "Редактирование плана закупки");

        Assert.assertNotEquals(errMess, 0, positionNumbers.size());

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет количество импортированных позиций плана закупки - {int} на странице 'Редактирование плана закупки'")
    public void customerVerifiesCountOfImportedFromExcelFilePlanPositionsOnEditTradePlanPage(int expectedPositions) {
        String stepName = String.format(
                "'Заказчик' проверяет количество импортированных позиций плана закупки - '%d' " +
                        "на странице 'Редактирование плана закупки'", expectedPositions);
        this.logStepName(stepName);

        timer.start();

        int actualPositions = editTradePlanPage.getPositionNumbersFromTradePlanTable().size();
        String errorMessage = String.format("[ОШИБКА]: таблица [Позиции плана] на странице [Редактирование плана " +
                        "закупки] после импорта из Excel-файла ожидалось [%d] позиций, а в реальности их [%d]",
                expectedPositions, actualPositions);
        System.out.printf(
                "[ИНФОРМАЦИЯ]: проверка количества позиций - ожидаемое значение [%d], реальное значение [%d].%n",
                expectedPositions, actualPositions);

        Assert.assertEquals(errorMessage, expectedPositions, actualPositions);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что в столбце {string} таблицы 'Позиции плана' нет пустых ячеек на странице 'Редактирование плана закупки'")
    public void customerChecksPlanPositionsColumnByNameForEmptyCellsAbsenceOnEditTradePlanPage(String columnName) {
        String stepName = String.format(
                "'Заказчик' проверяет, что в столбце '%s' таблицы 'Позиции плана' нет пустых ячеек " +
                        "на странице 'Редактирование плана закупки'", columnName);
        this.logStepName(stepName);

        timer.start();

        int numberOfEmptyCells = editTradePlanPage.getNumberOfEmptyCellsInColumnByName(columnName);
        String errorMessage = String.format("[ОШИБКА]: в столбце [%s] таблицы [Позиции плана] на странице " +
                "[Редактирование плана закупки] обнаружено [%d] пустых ячеек", columnName, numberOfEmptyCells);

        Assert.assertEquals(errorMessage, 0, numberOfEmptyCells);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' прикрепляет документ в разделе 'Документы' на странице 'Редактирование плана закупки'")
    public void customerUploadsRequiredDocumentInDocumentsPartitionOnEditTradePlanPage() {
        String stepName = "'Заказчик' прикрепляет документ в разделе 'Документы' " +
                "на странице 'Редактирование плана закупки'";
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.uploadFileIntoDocuments(config.getAbsolutePathToFoundationDoc());

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет ссылку для скачивания прикрепленного файла {string} в разделе 'Документы' на странице 'Редактирование плана закупки'")
    public void customerChecksLinkToAttachedFileByNameInDocumentsPartitionOnEditTradePlanPage(String fileName) {
        String stepName = String.format("'Заказчик' проверяет ссылку для скачивания прикрепленного файла '%s' " +
                "в разделе 'Документы' на странице 'Редактирование плана закупки'", fileName);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.checkLinkToDownloadFileFromDocuments(fileName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} доступно для редактирования на странице 'Редактирование плана закупки'")
    public void customerChecksFieldForThePossibilityOfEditingOnEditTradePlanPage(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' доступно для редактирования " +
                "на странице 'Редактирование плана закупки'", fieldName);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.checkFieldForThePossibilityOfEditing(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле {string} таблицы 'Позиции плана' значением {string} на странице 'Редактирование плана закупки'")
    public void customerFillsFieldByNameOfTradePlanTableOnEditTradePlanPage(String fieldName, String value) {
        String stepName = String.format("'Заказчик' заполняет поле '%s' таблицы 'Позиции плана' значением '%s' " +
                "на странице 'Редактирование плана закупки'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.setFieldInNewLineOfTradePlanTable(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле со счётчиком {string} таблицы 'Позиции плана' значением {string} на странице 'Редактирование плана закупки'")
    public void customerFillsFieldWsCounterByNameOfTradePlanTableOnEditTradePlanPage(String fieldName, String value)
            throws Throwable {
        String stepName = String.format("'Заказчик' заполняет поле со счётчиком '%s' таблицы 'Позиции плана' " +
                "значением '%s' на странице 'Редактирование плана закупки'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.setKendoNumericTextBoxFieldInNewLineOfTradePlanTable(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} таблицы 'Позиции плана' содержит значение {string} на странице 'Редактирование плана закупки'")
    public void customerChecksFieldContentOfTradePlanTableOnEditTradePlanPage(String fieldName, String value) {
        String stepName = String.format("Заказчик' проверяет, что поле '%s' таблицы 'Позиции плана' " +
                "содержит значение '%s' на странице 'Редактирование плана закупки'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.verifyFieldOfTradePlanTable(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} таблицы 'Позиции плана' содержит не пустое значение на странице 'Редактирование плана закупки'")
    public void customerVerifiesThatTheFieldIsNotEmptyOfTradePlanTableOnEditTradePlanPage(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' таблицы 'Позиции плана' содержит " +
                "не пустое значение на странице 'Редактирование плана закупки'", fieldName);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.verifyFieldIsNotEmptyOfTradePlanTable(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что флажок {string} таблицы 'Позиции плана' {string} на странице 'Редактирование плана закупки'")
    public void customerVerifiesCheckBoxStateOfTradePlanTableOnEditTradePlanPage(
            String checkBoxName, String checkBoxState) {
        String stepName = String.format("'Заказчик' проверяет, что флажок '%s' таблицы 'Позиции плана' '%s' " +
                "на странице 'Редактирование плана закупки'", checkBoxName, checkBoxState);
        this.logStepName(stepName);

        timer.start();

        if (checkBoxState.equals("установлен")) editTradePlanPage.verifyCheckBoxSelectedOfTradePlanTable(checkBoxName);
        else editTradePlanPage.verifyCheckBoxNotSelectedOfTradePlanTable(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что переключатель {string} таблицы 'Позиции плана' отмечен на странице 'Редактирование плана закупки'")
    public void customerVerifiesRadioButtonSelectedOfTradePlanTableOnEditTradePlanPage(String radioButtonName) {
        String stepName = String.format("'Заказчик' проверяет, что переключатель '%s' таблицы 'Позиции плана' отмечен" +
                " на странице 'Редактирование плана закупки'", radioButtonName);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.verifyRadioButtonSelectedOfTradePlanTable(radioButtonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что переключатель {string} таблицы 'Позиции плана' не отмечен на странице 'Редактирование плана закупки'")
    public void customerVerifiesRadioButtonNotSelectedOfTradePlanTableOnEditTradePlanPage(String radioButtonName) {
        String stepName = String.format("'Заказчик' проверяет, что переключатель '%s' таблицы 'Позиции плана' не " +
                "отмечен на странице 'Редактирование плана закупки'", radioButtonName);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.verifyRadioButtonNotSelectedOfTradePlanTable(radioButtonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' устанавливает переключатель {string} таблицы 'Позиции плана' в положение {string} на странице 'Редактирование плана закупки'")
    public void customerSetsRadioButtonToDesiredPositionOfTradePlanTableOnEditTradePlanPage(
            String radioButtonName, String radioButtonPosition) throws Throwable {
        String stepName = String.format("'Заказчик' устанавливает переключатель '%s' таблицы 'Позиции плана' в " +
                "положение '%s' на странице 'Редактирование плана закупки'", radioButtonName, radioButtonPosition);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.selectRadioButtonOfTradePlanTable(radioButtonPosition);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет дату {string} таблицы 'Позиции плана' в {string} со смещением {string} от текущей на странице 'Редактирование плана закупки'")
    public void customerFillsDateFieldOfTradePlanTableOnEditTradePlanPage(
            String field, String shiftType, String amountAsString) throws Throwable {
        String stepName = String.format("'Заказчик' заполняет дату '%s' таблицы 'Позиции плана' в '%s' со смещением " +
                "'%s' от текущей на странице 'Редактирование плана закупки'", field, shiftType, amountAsString);
        this.logStepName(stepName);

        timer.start();

        // Дата в очень "экзотическом" формате - месяц без падежа и с большой буквы [19 Март 2021]
        String dateValue = timer.replaceMonthNameToCorrectValue(timer.getDateTimeShiftWithDateFormat(
                Timer.getPublishNoticeDate(), shiftType, amountAsString, "dd MMMMM yyy"));
        System.out.printf("[ИНФОРМАЦИЯ]: сформировано значение даты [%s].%n", dateValue);


        editTradePlanPage.setFieldClearClickAndSendKeysOfTradePlanTable(field, dateValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет месяц и год {string} таблицы 'Позиции плана' в {string} со смещением {string} от текущего на странице 'Редактирование плана закупки'")
    public void customerFillsMonthAndYearFieldOfTradePlanTableOnEditTradePlanPage(
            String field, String shiftType, String amountAsString) throws Throwable {
        String stepName = String.format("'Заказчик' заполняет месяц и год '%s' таблицы 'Позиции плана' в '%s' со смещ" +
                "ением '%s' от текущего на странице 'Редактирование плана закупки'", field, shiftType, amountAsString);
        this.logStepName(stepName);

        timer.start();

        // Дата в очень "экзотическом" формате - месяц без падежа и с большой буквы [Апрель 2021]
        String monthAndYearValue = timer.replaceMonthNameToCorrectValue(timer.getDateTimeShiftWithDateFormat(
                Timer.getPublishNoticeDate(), shiftType, amountAsString, "MMMMM yyy"));
        System.out.printf("[ИНФОРМАЦИЯ]: сформировано значение месяца и года [%s].%n", monthAndYearValue);


        editTradePlanPage.setFieldClearClickAndSendKeysOfTradePlanTable(field, monthAndYearValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} таблицы 'Позиции плана' на странице 'Редактирование плана закупки'")
    public void customerClicksOnButtonByNameOfTradePlanTableOnEditTradePlanPage(String buttonName) throws Throwable {
        String stepName = String.format("'Заказчик' нажимает на кнопку '%s' таблицы 'Позиции плана' на странице " +
                "'Редактирование плана закупки'", buttonName);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.clickOnButtonOfTradePlanTable(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} таблицы 'Товары работы услуги' содержит не пустое значение на странице 'Редактирование плана закупки'")
    public void customerVerifiesThatTheFieldIsNotEmptyOfGoodsWorksServicesTableOnEditTradePlanPage(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' таблицы 'Товары работы услуги' содержит " +
                "не пустое значение на странице 'Редактирование плана закупки'", fieldName);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.verifyFieldIsNotEmptyOfGoodsWorksServicesTable(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' выбирает значение 'Товар' в списке для поля 'Тип объекта закупки' таблицы 'Товары работы услуги' на странице 'Редактирование плана закупки'")
    public void customerChoosesGoodValueInListForPurchaseObjectTypeFieldOfGoodsWorksServicesTableOnEditTradePlanPage()
            throws Throwable {
        String stepName = "'Заказчик' выбирает значение 'Товар' в списке для поля 'Тип объекта закупки' " +
                "таблицы 'Товары работы услуги' на странице 'Редактирование плана закупки'";
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.setObjectPurchaseItemTypeOfGoodsWorksServicesTable();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' выбирает значение '67000000000: г. Севастополь' в списке для поля 'Регион поставки' таблицы 'Товары работы услуги' на странице 'Редактирование плана закупки'")
    public void customerChoosesRegionValueInListForDeliveryRegionFieldOfGoodsWorksServicesTableOnEditTradePlanPage()
            throws Throwable {
        String stepName = "'Заказчик' выбирает значение '67000000000: г. Севастополь' в списке для поля 'Регион поставки' " +
                "таблицы 'Товары работы услуги' на странице 'Редактирование плана закупки'";
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.setDeliveryRegionOfGoodsWorksServicesTable();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} таблицы 'Товары работы услуги' содержит значение {string} на странице 'Редактирование плана закупки'")
    public void customerChecksFieldContentOfGoodsWorksServicesTableOnEditTradePlanPage(String fieldName, String value) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' таблицы 'Товары работы услуги' содержит " +
                "значение '%s' на странице 'Редактирование плана закупки'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.verifyFieldOfGoodsWorksServicesTable(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле {string} таблицы 'Товары работы услуги' значением {string} на странице 'Редактирование плана закупки'")
    public void customerFillsFieldByNameOfGoodsWorksServicesTableOnEditTradePlanPage(String fieldName, String value) {
        String stepName = String.format("'Заказчик' заполняет поле '%s' таблицы 'Товары работы услуги' значением " +
                "'%s' на странице 'Редактирование плана закупки'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.setFieldOfGoodsWorksServicesTable(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что флажок {string} таблицы 'Товары работы услуги' {string} на странице 'Редактирование плана закупки'")
    public void customerVerifiesCheckBoxStateOfGoodsWorksServicesTableOnEditTradePlanPage(
            String checkBoxName, String checkBoxState) {
        String stepName = String.format("'Заказчик' проверяет, что флажок '%s' таблицы 'Товары работы услуги' '%s' " +
                "на странице 'Редактирование плана закупки'", checkBoxName, checkBoxState);
        this.logStepName(stepName);

        timer.start();

        if (checkBoxState.equals("установлен"))
            editTradePlanPage.verifyCheckBoxSelectedOfGoodsWorksServicesTable(checkBoxName);
        else editTradePlanPage.verifyCheckBoxNotSelectedOfGoodsWorksServicesTable(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле {string} значением {string} из файла конфигурации на странице 'Редактирование плана закупки'")
    public void customerFillsFieldWithValueFromConfigFileOnEditTradePlanPage(String fieldName, String key) {
        String stepName = String.format("'Заказчик' заполняет поле '%s' значением '%s' из файла конфигурации " +
                "на странице 'Редактирование плана закупки'", fieldName, config.getConfigParameter(key));
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.setField(fieldName, config.getConfigParameter(key));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} на странице 'Редактирование плана закупки'")
    public void customerClicksOnButtonByNameOnEditTradePlanPage(String buttonName) throws Throwable {
        String stepName = String.format("'Заказчик' нажимает на кнопку '%s' на странице 'Редактирование плана закупки'",
                buttonName);
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет возможность экспортирования в Excel 2007+ плана закупки на странице 'Редактирование плана закупки'")
    public void customerChecksPossibilityToExportTradePlanInExcel2007FormatOnEditTradePlanPage()throws Throwable {
        String stepName = "'Заказчик' проверяет возможность экспортирования в Excel 2007+ плана закупки " +
                "на странице 'Редактирование плана закупки'";
        this.logStepName(stepName);

        timer.start();

        editTradePlanPage.checkPossibilityToExportTradePlanInExcel2007Format();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что таблица 'Позиции плана' содержит {int} строк на странице 'Просмотр плана закупок'")
    public void customerChecksThatThePlanPositionsTableContainsDesiredNumberOfRowsOnViewTradePlanPage(int rows) {
        String stepName = String.format("'Заказчик' проверяет, что таблица 'Позиции плана' содержит '%d' строк " +
                "на странице 'Просмотр плана закупок'", rows);
        this.logStepName(stepName);

        timer.start();

        viewTradePlanPage.checkNumberOfRowsInPlanPositionTable(rows);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' выбирает первую позицию в таблице 'Позиции плана' на странице 'Просмотр плана закупок'")
    public void customerSelectsFirstPositionInPlanPositionsTableOnViewTradePlanPage() throws Throwable {
        String stepName =
                "'Заказчик' выбирает первую позицию в таблице 'Позиции плана' на странице 'Просмотр плана закупок'";
        this.logStepName(stepName);

        timer.start();

        viewTradePlanPage.selectFirstPlanPosition();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что кнопка {string} отображается и доступна на странице 'Просмотр плана закупок'")
    public void customerChecksThatTheButtonIsVisibleAndClickableOnViewTradePlanPage(String controlName) {
        String stepName = String.format("'Заказчик' проверяет, что кнопка '%s' отображается и доступна " +
                "на странице 'Просмотр плана закупок'", controlName);
        this.logStepName(stepName);

        timer.start();

        viewTradePlanPage.isControlClickable(controlName);

        timer.printStepTotalTime(stepName);
    }
}
