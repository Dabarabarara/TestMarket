package ru.StepDefinitions.Customer.CommonClarificationRequest;

import Helpers.Timer;
import cucumber.api.java.en.And;
import org.apache.commons.lang.StringUtils;
import ru.PageObjects.Customer.CommonClarificationRequest.CreateInquiryEditInquiryPage;
import ru.PageObjects.Customer.CommonClarificationRequest.ViewInquiriesPage;
import ru.StepDefinitions.AbstractStepDefinitions;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Класс, описывающий шаги работы заказчика с запросами на разъяснение заявок поставщиков.
 * Created by Vladimir V. Klochkov on 18.09.2020.
 * Updated by Vladimir V. Klochkov on 29.09.2020.
 */
public class CustomerCommonClarificationRequestStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final CreateInquiryEditInquiryPage createInquiryEditInquiryPage = new CreateInquiryEditInquiryPage(driver);
    private final ViewInquiriesPage viewInquiriesPage = new ViewInquiriesPage(driver);

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    @And("'Заказчик' сохраняет в параметрах теста номер запроса на разъяснение заявки на странице 'Разъяснение заявки'")
    public void customerSavesInTestParametersRequestForClarificationOfApplicationNumberOnViewInquiriesPage() {
        String stepName = "Заказчик' сохраняет в параметрах теста номер запроса на разъяснение заявки на " +
                "странице 'Разъяснение заявки'";
        this.logStepName(stepName);

        timer.start();

        config.setParameter("CommonClarificationRequestNumber", url().split("/ViewInquiries/")[1]);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит к просмотру раздела страницы 'Запрос на разъяснение заявки' {string}:")
    public void customerGoesToBlockOnCreateInquiryPage(String block) throws Throwable {
        String stepName = String.format(
                "'Заказчик' переходит к просмотру раздела страницы 'Запрос на разъяснение заявки' '%s':", block);
        this.logStepName(stepName);

        timer.start();

        createInquiryEditInquiryPage.gotoBlock(block);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит к просмотру раздела страницы 'Разъяснение заявки' {string}:")
    public void customerGoesToBlockOnViewInquiriesPage(String block) throws Throwable {
        String stepName =
                String.format("'Заказчик' переходит к просмотру раздела страницы 'Разъяснение заявки' '%s':", block);
        this.logStepName(stepName);

        timer.start();

        viewInquiriesPage.gotoBlock(block);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} на странице 'Разъяснение заявки'")
    public void customerClicksOnButtonByNameOnViewInquiriesPage(String buttonName) throws Throwable {
        String stepName =
                String.format("'Заказчик' нажимает на кнопку '%s' на странице 'Разъяснение заявки'", buttonName);
        this.logStepName(stepName);

        timer.start();

        viewInquiriesPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} на странице 'Разъяснение заявки' содержит строку в {int} байт")
    public void customerChecksFieldLengthOnViewInquiriesPage(String fieldName, int length) {
        String stepName = String.format(
                "'Заказчик' проверяет, что поле '%s' на странице 'Разъяснение заявки' содержит строку в '%d' байт",
                fieldName, length);
        this.logStepName(stepName);

        timer.start();

        viewInquiriesPage.verifyField(fieldName, StringUtils.repeat("s", length));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет текст ссылки для скачивания первого сверху прикрепленного файла в разделе 'Документы разъяснения' на странице 'Разъяснение заявки'")
    public void customerChecksFirstLinkToDownloadFileFromResponceDocumentsOnViewInquiriesPage() {
        String stepName = "'Заказчик' проверяет текст ссылки для скачивания первого сверху прикрепленного файла в " +
                "разделе 'Документы разъяснения' на странице 'Разъяснение заявки'";
        this.logStepName(stepName);

        timer.start();

        viewInquiriesPage.checkFirstLinkToDownloadFileFromResponceDocuments(
                config.getConfigParameter("UserEntityDoc"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет отображение кнопки 'Показать' для первого сверху прикрепленного файла в разделе 'Документы разъяснения' на странице 'Разъяснение заявки'")
    public void customerChecksFirstShowSignatureButtonFromResponceDocumentsOnViewInquiriesPage() {
        String stepName = "'Заказчик' проверяет отображение кнопки 'Показать' для первого сверху прикрепленного " +
                "файла в разделе 'Документы разъяснения' на странице 'Разъяснение заявки'";
        this.logStepName(stepName);

        timer.start();

        viewInquiriesPage.checkFirstShowSignatureButtonFromResponceDocuments();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет возможность скачать прикрепленные документы в разделе 'Документы разъяснения' на странице 'Разъяснение заявки'")
    public void customerChecksPossibilityToDownloadResponceDocumentsOnViewInquiriesPage() throws Throwable {
        String stepName = "'Заказчик' проверяет возможность скачать прикрепленные документы в разделе " +
                "'Документы разъяснения' на странице 'Разъяснение заявки'";
        this.logStepName(stepName);

        timer.start();

        viewInquiriesPage.checkPossibilityToDownloadResponceDocuments();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет что номер закупки на странице 'Запрос на разъяснение заявки' верен")
    public void customerChecksPurchaseNumberOnCreateInquiryPage() {
        String stepName = "'Заказчик' проверяет что номер закупки на странице 'Запрос на разъяснение заявки' верен";
        this.logStepName(stepName);

        timer.start();

        createInquiryEditInquiryPage.verifyField("Номер закупки", config.getParameter("PurchaseNumber"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} страницы 'Запрос на разъяснение заявки' содержит {string}")
    public void customerChecksFieldByNameOnCreateInquiryPage(String fieldName, String value) {
        String stepName = String.format(
                "'Заказчик' проверяет, что поле '%s' страницы 'Запрос на разъяснение заявки' содержит '%s'",
                fieldName, value);
        this.logStepName(stepName);

        timer.start();

        createInquiryEditInquiryPage.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет что наименование закупки на странице 'Запрос на разъяснение заявки' верно")
    public void customerChecksPurchaseNameOnCreateInquiryPage() {
        String stepName =
                "'Заказчик' проверяет что наименование закупки на странице 'Запрос на разъяснение заявки' верно";
        this.logStepName(stepName);

        timer.start();

        createInquiryEditInquiryPage.verifyField("Наименование закупки", config.getParameter("PurchaseName"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} страницы 'Запрос на разъяснение заявки' содержит не пустое значение")
    public void customerChecksFieldIsNotEmptyOnCreateInquiryPage(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' страницы 'Запрос на разъяснение заявки' " +
                "содержит не пустое значение", fieldName);
        this.logStepName(stepName);

        timer.start();

        createInquiryEditInquiryPage.verifyFieldIsNotEmpty(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле 'Срок предоставления ответа' на странице 'Запрос на разъяснение заявки'")
    public void customerFillsResponseTimeFrameFieldOnCreateInquiryPage() throws Throwable {
        String stepName =
                "'Заказчик' заполняет поле 'Срок предоставления ответа' на странице 'Запрос на разъяснение заявки'";
        this.logStepName(stepName);

        timer.start();

        createInquiryEditInquiryPage.setFieldClearClickAndSendKeys("Срок предоставления ответа (МСК)",
                timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MONTHS", "+1"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле {string} на странице 'Запрос на разъяснение заявки' строкой в {int} байт")
    public void customerFillsFieldByNameWithLongStringOnCreateInquiryPage(String fieldName, int length) {
        String stepName = String.format(
                "'Заказчик' заполняет поле '%s' на странице 'Запрос на разъяснение заявки' строкой в '%d' байт",
                fieldName, length);
        this.logStepName(stepName);

        timer.start();

        createInquiryEditInquiryPage.setField(fieldName, StringUtils.repeat("s", length));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' прикрепляет документ в разделе 'Документы запроса' на странице 'Запрос на разъяснение заявки'")
    public void customerUploadsFileIntoRequestDocumentsPartitionOnCreateInquiryPage() throws Throwable {
        String stepName = "'Заказчик' прикрепляет документ в разделе 'Документы запроса' на странице " +
                "'Запрос на разъяснение заявки'";
        this.logStepName(stepName);

        timer.start();

        String fileName = config.getConfigParameter("FoundationDoc");

        createInquiryEditInquiryPage.uploadFileIntoRequestDocuments(config.getAbsolutePathToFoundationDoc());
        createInquiryEditInquiryPage.checkFirstLinkToDownloadFileFromRequestDocuments(fileName);
        createInquiryEditInquiryPage.checkFirstRemoveDocumentLinkFromRequestDocuments();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет возможность скачать документы запроса со страницы 'Запрос на разъяснение заявки'")
    public void customerChecksPossibilityToDownloadRequestDocumentsOnCreateInquiryPage()
            throws Throwable {
        String stepName =
                "'Заказчик' проверяет возможность скачать документы запроса со страницы 'Запрос на разъяснение заявки'";
        this.logStepName(stepName);

        timer.start();

        createInquiryEditInquiryPage.checkPossibilityToDownloadRequestDocuments();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' отправляет запрос на разъяснение заявки со страницы 'Запрос на разъяснение заявки'")
    public void customerSendsRequestForClarificationOfApplicationOnCreateInquiryPage() throws Throwable {
        String stepName =
                "'Заказчик' отправляет запрос на разъяснение заявки со страницы 'Запрос на разъяснение заявки'";
        this.logStepName(stepName);

        timer.start();

        createInquiryEditInquiryPage.clickOnButton("Отправить запрос");

        timer.printStepTotalTime(stepName);
    }
}
