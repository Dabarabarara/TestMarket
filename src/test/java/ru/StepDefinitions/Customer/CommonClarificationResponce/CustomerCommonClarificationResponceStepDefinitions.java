package ru.StepDefinitions.Customer.CommonClarificationResponce;

import Helpers.Timer;
import cucumber.api.java.en.And;
import org.apache.commons.lang.StringUtils;
import ru.PageObjects.CommonDialogs.InformationDialog;
import ru.PageObjects.Customer.CommonClarificationResponce.CommonClarificationResponcePage;
import ru.PageObjects.Customer.CommonClarificationResponce.CreateClarifyDocResponsePage;
import ru.PageObjects.Customer.FileSignatureWindowDialog;
import ru.PageObjects.Customer.CommonClarificationResponce.RegisterOfInquiriesPage;
import ru.StepDefinitions.AbstractStepDefinitions;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Класс, описывающий шаги работы заказчика с ответами на запросы на разъяснение положений документации/результатов.
 * Created by Vladimir V. Klochkov on 24.10.2019.
 * Updated by Vladimir V. Klochkov on 23.12.2020.
 */
public class CustomerCommonClarificationResponceStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final CommonClarificationResponcePage commonClarificationResponcePage =
            new CommonClarificationResponcePage(driver);
    private final CreateClarifyDocResponsePage createClarifyDocResponsePage = new CreateClarifyDocResponsePage(driver);
    private final RegisterOfInquiriesPage registerOfInquiriesPage = new RegisterOfInquiriesPage(driver);

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    @And("'Заказчик' сохраняет в параметрах теста номер запроса на разъяснение положений документации о закупке")
    public void customerSavesInTestParametersCommonClarificationRuquestNumberOnCommonClarificationResponcePage() {
        String stepName =
                "'Заказчик' сохраняет в параметрах теста номер запроса на разъяснение положений документации о закупке";
        this.logStepName(stepName);

        timer.start();

        config.setParameter("CommonClarificationRequestNumber", url().split("/ViewInquiries/")[1]);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит к просмотру раздела страницы разъяснения положений документации {string}:")
    public void customerGoesToBlockOnCommonClarificationResponcePage(String block) throws Throwable {
        String stepName = String.format(
                "'Заказчик' переходит к просмотру раздела страницы разъяснения положений документации '%s':", block);
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.gotoBlock(block);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит к просмотру раздела страницы разъяснения результатов {string}:")
    public void customerGoesToBlockOnCommonClarificationResponceOfResultsPage(String block) throws Throwable {
        String stepName = String.format(
                "'Заказчик' переходит к просмотру раздела страницы разъяснения результатов '%s':", block);
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.gotoBlock(block);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет что номер закупки на странице разъяснения положений документации верен")
    public void customerChecksPurchaseNumberOnCommonClarificationResponcePage() {
        String stepName = "'Заказчик' проверяет что номер закупки на странице разъяснения положений документации верен";
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.
                verifyField("Номер закупки", config.getParameter("PurchaseNumber"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет что номер закупки на странице разъяснения результатов верен")
    public void customerChecksPurchaseNumberOnCommonClarificationResponceOfResultsPage() {
        String stepName = "'Заказчик' проверяет что номер закупки на странице разъяснения результатов верен";
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.
                verifyField("Номер закупки", config.getParameter("PurchaseNumber"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет что наименование закупки на странице разъяснения положений документации верно")
    public void customerChecksPurchaseNameOnCommonClarificationResponcePage() {
        String stepName =
                "'Заказчик' проверяет что наименование закупки на странице разъяснения положений документации верно";
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.
                verifyField("Наименование закупки", config.getParameter("PurchaseName"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет что наименование закупки на странице разъяснения результатов верно")
    public void customerChecksPurchaseNameOnCommonClarificationResponceOfResultsPage() {
        String stepName =
                "'Заказчик' проверяет что наименование закупки на странице разъяснения результатов верно";
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.
                verifyField("Наименование закупки", config.getParameter("PurchaseName"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет что номер запроса на странице разъяснения положений документации верен")
    public void customerChecksRequestNumberOnCommonClarificationResponcePage() {
        String stepName = "'Заказчик' проверяет что номер запроса на странице разъяснения положений документации верен";
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.
                verifyField("Номер запроса", config.getParameter("CommonClarificationRequestNumber"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет что номер запроса на странице разъяснения результатов верен")
    public void customerChecksRequestNumberOnCommonClarificationResponceOfResultsPage() {
        String stepName = "'Заказчик' проверяет что номер запроса на странице разъяснения результатов верен";
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.
                verifyField("Номер запроса", config.getParameter("CommonClarificationRequestNumber"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} страницы разъяснения положений документации содержит {string}")
    public void customerChecksFieldByNameOnCommonClarificationResponcePage(String fieldName, String value) {
        String stepName = String.format(
                "'Заказчик' проверяет, что поле '%s' страницы разъяснения положений документации содержит '%s'",
                fieldName, value);
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} страницы разъяснения результатов содержит {string}")
    public void customerChecksFieldByNameOnCommonClarificationResponceOfResultsPage(String fieldName, String value) {
        String stepName = String.format(
                "'Заказчик' проверяет, что поле '%s' страницы разъяснения результатов содержит '%s'",
                fieldName, value);
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} страницы разъяснения положений документации содержит не пустое значение")
    public void customerChecksFieldIsNotEmptyOnCommonClarificationResponcePage(String fieldName) {
        String stepName =
                String.format("'Заказчик' проверяет, что поле '%s' страницы разъяснения положений документации " +
                        "содержит не пустое значение", fieldName);
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.verifyFieldIsNotEmpty(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} страницы разъяснения результатов содержит не пустое значение")
    public void customerChecksFieldIsNotEmptyOnCommonClarificationResponceOfResultsPage(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' страницы разъяснения результатов " +
                "содержит не пустое значение", fieldName);
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.verifyFieldIsNotEmpty(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} страницы разъяснения положений документации содержит строку в {int} байт")
    public void customerChecksFieldLengthOnCommonClarificationResponcePage(String fieldName, int length) {
        String stepName =
                String.format("'Заказчик' проверяет, что поле '%s' страницы разъяснения положений документации " +
                        "содержит строку в '%d' байт", fieldName, length);
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.verifyField(fieldName, StringUtils.repeat("s", length));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} страницы разъяснения результатов содержит строку в {int} байт")
    public void customerChecksFieldLengthOnCommonClarificationResponceOfResultsPage(String fieldName, int length) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' страницы разъяснения результатов " +
                "содержит строку в '%d' байт", fieldName, length);
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.verifyField(fieldName, StringUtils.repeat("s", length));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет наличие прикрепленного к запросу документа {string} на странице разъяснения положений документации")
    public void customerChecksAttachedDocumentInRequestDocumentsPartitionOnCommonClarificationResponcePage
            (String fileName) {
        String stepName = String.format("'Заказчик' проверяет наличие прикрепленного к запросу документа %s на " +
                "странице разъяснения положений документации", fileName);
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.checkFirstLinkToDownloadFileFromRequestDocuments(fileName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет наличие прикрепленного к запросу документа {string} на странице разъяснения результатов")
    public void customerChecksAttachedDocumentInRequestDocumentsPartitionOnCommonClarificationResponceOfResultsPage
            (String fileName) {
        String stepName = String.format("'Заказчик' проверяет наличие прикрепленного к запросу документа %s на " +
                "странице разъяснения результатов", fileName);
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.checkFirstLinkToDownloadFileFromRequestDocuments(fileName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что флажок страницы разъяснения положений документации {string} {string}")
    public void customerVerifiesCheckBoxStateOnCommonClarificationResponcePage
            (String checkBoxName, String checkBoxState) {
        String stepName = String.format(
                "'Заказчик' проверяет, что флажок страницы разъяснения положений документации '%s' '%s'",
                checkBoxName, checkBoxState);
        this.logStepName(stepName);

        timer.start();

        if (checkBoxState.equals("установлен"))
            commonClarificationResponcePage.verifyCheckBoxSelected(checkBoxName);
        else
            commonClarificationResponcePage.verifyCheckBoxNotSelected(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что флажок страницы разъяснения результатов {string} {string}")
    public void customerVerifiesCheckBoxStateOnCommonClarificationResponceOfResultsPage
            (String checkBoxName, String checkBoxState) {
        String stepName = String.format("'Заказчик' проверяет, что флажок страницы разъяснения результатов '%s' '%s'",
                checkBoxName, checkBoxState);
        this.logStepName(stepName);

        timer.start();

        if (checkBoxState.equals("установлен"))
            commonClarificationResponcePage.verifyCheckBoxSelected(checkBoxName);
        else
            commonClarificationResponcePage.verifyCheckBoxNotSelected(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле {string} на странице разъяснения положений документации значением {string}")
    public void customerFillsFieldByNameOnCommonClarificationResponcePage(String fieldName, String value) {
        String stepName = String.format(
                "'Заказчик' заполняет поле '%s' на странице разъяснения положений документации значением '%s'",
                fieldName, value);
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.setField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле {string} на странице разъяснения положений документации строкой в {int} байт")
    public void customerFillsFieldByNameWithLongStringOnCommonClarificationResponcePage(String fieldName, int length) {
        String stepName = String.format(
                "'Заказчик' заполняет поле '%s' на странице разъяснения положений документации строкой в '%d' байт",
                fieldName, length);
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.setField(fieldName, StringUtils.repeat("s", length));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле {string} на странице разъяснения результатов строкой в {int} байт")
    public void customerFillsFieldByNameWithLongStringOnCommonClarificationResponceOfResultsPage
            (String fieldName, int length) {
        String stepName = String.format(
                "'Заказчик' заполняет поле '%s' на странице разъяснения результатов строкой в '%d' байт",
                fieldName, length);
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.setField(fieldName, StringUtils.repeat("s", length));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' прикрепляет документ в разделе 'Документы разъяснения' на странице разъяснения положений документации")
    public void customerUploadsDocumentInResponceDocumentsPartitionOnCommonClarificationResponcePage() {
        String stepName = "'Заказчик' прикрепляет документ в разделе 'Документы разъяснения' на странице " +
                "разъяснения положений документации";
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.
                uploadFileIntoResponceDocuments(config.getAbsolutePathToUserEntityDoc());
        commonClarificationResponcePage.
                checkFirstLinkToDownloadFileFromResponceDocumentsAndRemoveDocumentLinks(
                        config.getConfigParameter("UserEntityDoc"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' прикрепляет документ в разделе 'Документы разъяснения' на странице разъяснения результатов")
    public void customerUploadsDocumentInResponceDocumentsPartitionOnCommonClarificationResponceOfResultsPage() {
        String stepName = "'Заказчик' прикрепляет документ в разделе 'Документы разъяснения' на странице " +
                "разъяснения результатов";
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.
                uploadFileIntoResponceDocuments(config.getAbsolutePathToUserEntityDoc());
        commonClarificationResponcePage.
                checkFirstLinkToDownloadFileFromResponceDocumentsAndRemoveDocumentLinks(
                        config.getConfigParameter("UserEntityDoc"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' отправляет ответ на запрос на разъяснение документации")
    public void customerSendsCommonClarificationResponceFromCommonClarificationResponcePage() throws Throwable {
        String stepName = "'Заказчик' отправляет ответ на запрос на разъяснение документации";
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.clickOnButton("Отправить разъяснение");

        // Нажимаем на кнопку [OK] в диалоговом окне [Информация]
        InformationDialog informationDialog = new InformationDialog(driver);
        informationDialog.
                ifDialogOpened("Текст заголовка окна").
                check1stLineInWindowText("Текст окна 1 строка",
                        "Разъяснение запроса было успешно опубликовано на площадке.").
                clickOnOKButton("ОК");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' отправляет ответ на запрос на разъяснение результатов")
    public void customerSendsCommonClarificationResponceFromCommonClarificationResponceOfResultsPage() throws Throwable {
        String stepName = "'Заказчик' отправляет ответ на запрос на разъяснение результатов";
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.clickOnButton("Отправить разъяснение");

        // Нажимаем на кнопку [OK] в диалоговом окне [Информация]
        InformationDialog informationDialog = new InformationDialog(driver);
        informationDialog.
                ifDialogOpened("Текст заголовка окна").
                check1stLineInWindowText("Текст окна 1 строка",
                        "Разъяснение запроса было успешно опубликовано на площадке.").
                clickOnOKButton("ОК");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет возможность скачать документы запроса со страницы разъяснения положений документации")
    public void customerChecksPossibilityToDownloadRequestDocumentsFromCommonClarificationResponcePage()
            throws Throwable {
        String stepName = "'Заказчик' проверяет возможность скачать документы запроса со страницы разъяснения " +
                "положений документации";
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.checkPossibilityToDownloadRequestDocuments();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет возможность скачать документы запроса со страницы разъяснения результатов")
    public void customerChecksPossibilityToDownloadRequestDocumentsFromCommonClarificationResponceOfResultsPage()
            throws Throwable {
        String stepName =
                "'Заказчик' проверяет возможность скачать документы запроса со страницы разъяснения результатов";
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.checkPossibilityToDownloadRequestOfResultsDocuments();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет ссылку для скачивания прикрепленного файла в разделе 'Документы разъяснения' со страницы разъяснения положений документации")
    public void customerChecksFirstLinkToDownloadFileFromRequestDocuments() {
        String stepName = "'Заказчик' проверяет ссылку для скачивания прикрепленного файла в разделе " +
                "'Документы разъяснения' со страницы разъяснения положений документации";
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.
                checkFirstLinkToDownloadFileFromResponceDocuments(config.getConfigParameter("UserEntityDoc"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет возможность скачать документы разъяснения со страницы разъяснения положений документации")
    public void customerChecksPossibilityToDownloadResponceDocumentsFromCommonClarificationResponcePage()
            throws Throwable {
        String stepName = "'Заказчик' проверяет возможность скачать документы разъяснения со страницы разъяснения " +
                "положений документации";
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.checkPossibilityToDownloadResponceDocuments();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет возможность скачать документы разъяснения со страницы разъяснения результатов")
    public void customerChecksPossibilityToDownloadResponceDocumentsFromCommonClarificationResponceOfResultsPage()
            throws Throwable {
        String stepName = "'Заказчик' проверяет возможность скачать документы разъяснения со страницы разъяснения " +
                "результатов";
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.checkPossibilityToDownloadResponceDocuments();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' ищет запрос на разъяснение документации в реестре запросов")
    public void customerSearchesCommonClarificationRequestInRegisterOfInquiries() throws Throwable {
        String stepName = "'Заказчик' ищет запрос на разъяснение документации в реестре запросов";
        this.logStepName(stepName);

        timer.start();

        registerOfInquiriesPage.
                gotoBlock("Заголовок страницы").
                clickOnButton("Фильтр").
                setFieldClearClickAndSendKeys("Номер запроса",
                        config.getParameter("CommonClarificationRequestNumber")).
                setFieldClearClickAndSendKeys("Номер закупки", config.getParameter("PurchaseNumber")).
                clickOnButton("Показать").
                checkRowsNumberInSearchResults(1);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' ищет запрос на разъяснение результатов в реестре запросов")
    public void customerSearchesCommonClarificationRequestOfResultsInRegisterOfInquiries() throws Throwable {
        String stepName = "'Заказчик' ищет запрос на разъяснение результатов в реестре запросов";
        this.logStepName(stepName);

        timer.start();

        registerOfInquiriesPage.
                gotoBlock("Заголовок страницы").
                clickOnButton("Фильтр").
                setFieldClearClickAndSendKeys("Номер запроса",
                        config.getParameter("CommonClarificationRequestNumber")).
                setFieldClearClickAndSendKeys("Номер закупки", config.getParameter("PurchaseNumber")).
                clickOnButton("Показать").
                checkRowsNumberInSearchResults(1);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' открывает запрос на разъяснение документации в реестре запросов")
    public void customerOpensCommonClarificationRequestInRegisterOfInquiries() throws Throwable {
        String stepName = "'Заказчик' открывает запрос на разъяснение документации в реестре запросов";
        this.logStepName(stepName);

        timer.start();

        registerOfInquiriesPage.clickOnRequestNumberLinkInSearchResultsUsingPurchaseNumber();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' открывает запрос на разъяснение результатов в реестре запросов")
    public void customerOpensCommonClarificationRequestOfResultsInRegisterOfInquiries() throws Throwable {
        String stepName = "'Заказчик' открывает запрос на разъяснение результатов в реестре запросов";
        this.logStepName(stepName);

        timer.start();

        registerOfInquiriesPage.clickOnRequestNumberLinkInSearchResultsUsingPurchaseNumber();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет статус {string} запроса на разъяснение документации в реестре запросов")
    public void customerChecksRequestStatusInRegisterOfInquiries(String status) {
        String stepName = String.format(
                "'Заказчик' проверяет статус '%s' запроса на разъяснение документации в реестре запросов", status);
        this.logStepName(stepName);

        timer.start();

        registerOfInquiriesPage.
                checkCellInSearchResultsByColumnNameAndRowNumber("Столбец Статус по номеру запроса",
                        "1", status);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет статус {string} запроса на разъяснение результатов в реестре запросов")
    public void customerChecksRequestOfResultsStatusInRegisterOfInquiries(String status) {
        String stepName = String.format(
                "'Заказчик' проверяет статус '%s' запроса на разъяснение результатов в реестре запросов", status);
        this.logStepName(stepName);

        timer.start();

        registerOfInquiriesPage.
                checkCellInSearchResultsByColumnNameAndRowNumber("Столбец Статус по номеру запроса",
                        "1", status);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет просмотрен ли запрос {string} на разъяснение документации Поставщиком в реестре запросов")
    public void customerChecksThatRequestIsReadBySupplierInRegisterOfInquiries(String isRead) {
        String stepName = String.format("'Заказчик' проверяет просмотрен ли запрос '%s' на разъяснение документации " +
                "Поставщиком в реестре запросов", isRead);
        this.logStepName(stepName);

        timer.start();

        registerOfInquiriesPage.
                checkCellInSearchResultsByColumnNameAndRowNumber(
                        "Столбец Просмотрен поставщиком по номеру запроса", "1", isRead);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет просмотрен ли запрос {string} на разъяснение результатов Поставщиком в реестре запросов")
    public void customerChecksThatRequestOfResultsIsReadBySupplierInRegisterOfInquiries(String isRead) {
        String stepName = String.format("'Заказчик' проверяет просмотрен ли запрос '%s' на разъяснение результатов " +
                "Поставщиком в реестре запросов", isRead);
        this.logStepName(stepName);

        timer.start();

        registerOfInquiriesPage.
                checkCellInSearchResultsByColumnNameAndRowNumber(
                        "Столбец Просмотрен поставщиком по номеру запроса", "1", isRead);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет текст запроса {string} запроса на разъяснение результатов в реестре запросов")
    public void customerChecksRequestOfResultsRequestTextInRegisterOfInquiries(String requestText) {
        String stepName = String.format("'Заказчик' проверяет текст запроса '%s' запроса на разъяснение " +
                "результатов в реестре запросов", requestText);
        this.logStepName(stepName);

        timer.start();

        registerOfInquiriesPage.
                checkCellInSearchResultsByColumnNameAndRowNumber(
                        "Столбец Текст запроса по номеру запроса", "1", requestText);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет текст разъяснения {string} запроса на разъяснение результатов в реестре запросов")
    public void customerChecksRequestOfResultExplanationTextInRegisterOfInquiries(String explanationText) {
        String stepName = String.format("'Заказчик' проверяет текст разъяснения '%s' запроса на разъяснение " +
                "результатов в реестре запросов", explanationText);
        this.logStepName(stepName);

        timer.start();

        registerOfInquiriesPage.
                checkCellInSearchResultsByColumnNameAndRowNumber(
                        "Столбец Текст разъяснения по номеру запроса", "1", explanationText);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' просматривает раздел стр. созд. разъяснения положений док. {string}:")
    public void customerGoesToBlockOnCreateClarifyDocResponsePage(String block) throws Throwable {
        String stepName =
                String.format("'Заказчик' просматривает раздел стр. созд. разъяснения положений док. '%s':", block);
        this.logStepName(stepName);

        timer.start();

        createClarifyDocResponsePage.gotoBlock(block);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет что номер закупки на стр. созд. разъяснения положений док. верен")
    public void customerChecksPurchaseNumberOnCreateClarifyDocResponsePage() {
        String stepName = "'Заказчик' проверяет что номер закупки на стр. созд. разъяснения положений док. верен";
        this.logStepName(stepName);

        timer.start();

        createClarifyDocResponsePage.verifyField("Номер закупки", config.getParameter("PurchaseNumber"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} стр. созд. разъяснения положений док. содержит {string}")
    public void customerChecksFieldByNameOnOnCreateClarifyDocResponsePage(String fieldName, String value) {
        String stepName = String.format(
                "'Заказчик' проверяет, что поле '%s' стр. созд. разъяснения положений док. содержит '%s'",
                fieldName, value);
        this.logStepName(stepName);

        timer.start();

        createClarifyDocResponsePage.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет что наименование закупки на стр. созд. разъяснения положений док. верно")
    public void customerChecksPurchaseNameOnCreateClarifyDocResponsePage() {
        String stepName = "'Заказчик' проверяет что наименование закупки на стр. созд. разъяснения положений док. верно";
        this.logStepName(stepName);

        timer.start();

        createClarifyDocResponsePage.
                verifyField("Наименование закупки", config.getParameter("PurchaseName"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что флажок стр. созд. разъяснения положений док. {string} {string}")
    public void customerVerifiesCheckBoxStateOnCreateClarifyDocResponsePage(String checkBoxName, String checkBoxState) {
        String stepName = String.format(
                "'Заказчик' проверяет, что флажок стр. созд. разъяснения положений док. '%s' '%s'",
                checkBoxName, checkBoxState);
        this.logStepName(stepName);

        timer.start();

        if (checkBoxState.equals("установлен"))
            createClarifyDocResponsePage.verifyCheckBoxSelected(checkBoxName);
        else createClarifyDocResponsePage.verifyCheckBoxNotSelected(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле {string} на стр. созд. разъяснения положений док. строкой в {int} байт")
    public void customerFillsFieldByNameWithLongStringOnCreateClarifyDocResponsePage(String fieldName, int length)
            throws Throwable {
        String stepName = String.format(
                "'Заказчик' заполняет поле '%s' на странице разъяснения положений документации строкой в '%d' байт",
                fieldName, length);
        this.logStepName(stepName);

        timer.start();

        createClarifyDocResponsePage.setFieldClearClickAndSendKeys(fieldName, StringUtils.repeat("s", length));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле {string} в {string} со смещением {string} от времени публикации извещения")
    public void customerFillsDateAndTimeFieldOnCreateClarifyDocResponsePage
            (String field, String shiftType, String amountAsString) throws Throwable {
        String stepName = String.format(
                "'Заказчик' заполняет дату и время '%s' в  '%s' со смещением '%s' от времени публикации",
                field, shiftType, amountAsString);
        this.logStepName(stepName);

        timer.start();

        createClarifyDocResponsePage.setFieldClearClickAndSendKeys(field,
                timer.getDateTimeShift(Timer.getPublishNoticeDate(), shiftType, amountAsString));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' прикрепляет файл недопустимого формата в разделе 'Документы разъяснения' на стр. созд. разъяснения положений док.")
    public void customerUploadsFileOfDepricatedFormatInResponceDocumentsPartitionOnCreateClarifyDocResponsePage() {
        String stepName = "'Заказчик' прикрепляет файл недопустимого формата в разделе 'Документы разъяснения' на " +
                "стр. созд. разъяснения положений док.";
        this.logStepName(stepName);

        timer.start();

        createClarifyDocResponsePage.uploadFileIntoResponceDocuments(config.getAbsolutePathToSimplePingPicture());

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что файл недопустимого формата не был добавлен в раздел 'Документы разъяснения' на стр. созд. разъяснения положений док.")
    public void customerChecksThatTheLinkToDownloadFileFromResponceDocumentsIsNotExists() {
        String stepName = "'Заказчик' проверяет, что файл недопустимого формата не был добавлен в раздел " +
                "'Документы разъяснения' на стр. созд. разъяснения положений док.";
        this.logStepName(stepName);

        timer.start();

        createClarifyDocResponsePage.checkLinkToDownloadFileFromResponceDocumentsIsNotExists(
                config.getConfigParameter("SimplePingPicture"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' прикрепляет документ в разделе 'Документы разъяснения' на стр. созд. разъяснения положений док.")
    public void customerUploadsDocumentInResponceDocumentsPartitionOnCreateClarifyDocResponsePage() {
        String stepName = "'Заказчик' прикрепляет документ в разделе 'Документы разъяснения' на стр. созд. " +
                "разъяснения положений док.";
        this.logStepName(stepName);

        timer.start();

        createClarifyDocResponsePage.uploadFileIntoResponceDocuments(config.getAbsolutePathToUserEntityDoc());

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет ссылку для скачивания док. в разделе 'Документы разъяснения' на стр. созд. разъяснения положений док.")
    public void customerChecksLinkToDownloadDocumentInResponceDocumentsPartitionOnCreateClarifyDocResponsePage() {
        String stepName = "'Заказчик' проверяет ссылку для скачивания док. в разделе 'Документы разъяснения' на стр. " +
                "созд. разъяснения положений док.";
        this.logStepName(stepName);

        timer.start();

        createClarifyDocResponsePage.
                checkFirstLinkToDownloadFileFromResponceDocuments(config.getConfigParameter("UserEntityDoc"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет дату и время прикрепленного док. в разделе 'Документы разъяснения' на стр. созд. разъяснения положений док.")
    public void customerChecksDateAndTimeOfUploadedDocumentInResponceDocumentsPartitionOnCreateClarifyDocResponsePage() {
        String stepName = "'Заказчик' проверяет дату и время прикрепленного док. в разделе 'Документы разъяснения' " +
                "на стр. созд. разъяснения положений док.";
        this.logStepName(stepName);

        timer.start();

        createClarifyDocResponsePage.
                checkFirstDateTimeOfDownloadedFileFromResponceDocuments(config.getConfigParameter("UserEntityDoc"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет ссылку для удаления прикрепленного док. в разделе 'Документы разъяснения' на стр. созд. разъяснения положений док.")
    public void customerChecksFirstLinkToRemoveUploadedFileFromResponceDocuments() {
        String stepName = "'Заказчик' проверяет ссылку для удаления прикрепленного док. в разделе " +
                "'Документы разъяснения' на стр. созд. разъяснения положений док.";
        this.logStepName(stepName);

        timer.start();

        createClarifyDocResponsePage.
                checkFirstLinkToRemoveUploadedFileFromResponceDocuments(config.getConfigParameter("UserEntityDoc"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' публикует разъяснение документации без запроса")
    public void customerPublishesDocumentationClarificationWithoutRequestFromCreateClarifyDocResponsePage()
            throws Throwable {
        String stepName = "'Заказчик' публикует разъяснение документации без запроса";
        this.logStepName(stepName);

        timer.start();

        createClarifyDocResponsePage.clickOnButton("Отправить разъяснение");

        // Нажимаем на кнопку [OK] в диалоговом окне [Информация]
        InformationDialog informationDialog = new InformationDialog(driver);
        informationDialog.
                ifDialogOpened("Текст заголовка окна").
                check1stLineInWindowText("Текст окна 1 строка",
                        "Разъяснение запроса было успешно опубликовано на площадке.").
                clickOnOKButton("ОК");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет ячейку {string} строка {string} в таблице 'Документы разъяснения' на текст {string}")
    public void customerChecksCellByColumnNameAndLineNumberFromResponceDocumentsTable(
            String columnName, String rowNumber, String cellValue) {
        String stepName = String.format(
                "'Заказчик' проверяет ячейку '%s' строка '%s' в таблице 'Документы разъяснения' на текст '%s'",
                columnName, rowNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.
                verifyCellByColumnNameAndLineNumberFromResponceDocumentsTable(columnName, rowNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет наличие кнопки {string} строка {int} в таблице 'Документы запроса'")
    public void customerChecksButtonPresenceInLineByNumberFromRequestDocumentsTable(String columnName, int lineNumber) {
        String stepName = String.format(
                "'Заказчик' проверяет наличие кнопки '%s' строка '%d' в таблице 'Документы запроса'",
                columnName, lineNumber);
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.
                checkButtonPresenceInLineByNumberFromRequestDocumentsTable(columnName, lineNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет наличие кнопки {string} строка {int} в таблице 'Документы разъяснения'")
    public void customerChecksButtonPresenceInLineByNumberFromResponceDocumentsTable(String columnName, int lineNumber) {
        String stepName = String.format(
                "'Заказчик' проверяет наличие кнопки '%s' строка '%d' в таблице 'Документы разъяснения'",
                columnName, lineNumber);
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.
                checkButtonPresenceInLineByNumberFromResponceDocumentsTable(columnName, lineNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} строка {int} в таблице 'Документы запроса'")
    public void customerClicksOnButtonInLineByNumberFromRequestDocumentsTable(
            String columnName, int lineNumber) throws Throwable {
        String stepName = String.format("'Заказчик' нажимает на кнопку '%s' строка '%d' в таблице 'Документы запроса'",
                columnName, lineNumber);
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.
                clickOnButtonInLineByNumberFromRequestDocumentsTable(columnName, lineNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} строка {int} в таблице 'Документы разъяснения'")
    public void customerClicksOnButtonInLineByNumberFromResponceDocumentsTable(
            String columnName, int lineNumber) throws Throwable {
        String stepName =
                String.format("'Заказчик' нажимает на кнопку '%s' строка '%d' в таблице 'Документы разъяснения'",
                        columnName, lineNumber);
        this.logStepName(stepName);

        timer.start();

        commonClarificationResponcePage.
                clickOnButtonInLineByNumberFromClarificationRequestsTable(columnName, lineNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет реквизиты цифровой подписи отправителя в диалоговом окне 'Просмотр подписи'")
    public void customerChecksRequisitesOfSendersDigitalSignatureInShowSignatureDialogWindow() throws Throwable {
        String stepName =
                "'Заказчик' проверяет реквизиты цифровой подписи отправителя в диалоговом окне 'Просмотр подписи'";
        this.logStepName(stepName);

        timer.start();

        String certName = config.getConfigParameter("OnProdCertificateSupplier7Name");
        String lastName = certName.split(" ")[0];
        String firstName = certName.split(" ")[1] + " " + certName.split(" ")[2];

        FileSignatureWindowDialog fileSignatureWindowDialog = new FileSignatureWindowDialog(driver);
        fileSignatureWindowDialog.
                ifDialogOpened().
                verifyField("Имя", firstName).
                verifyField("Фамилия", lastName).
                verifyField("ИНН", config.getConfigParameter("OnProdSupplier7Inn")).
                clickOnButton("Отмена");

        timer.printStepTotalTime(stepName);
    }
}
