package ru.StepDefinitions.Supplier.CommonClarificationRequest;

import cucumber.api.java.en.And;
import org.apache.commons.lang.StringUtils;
import ru.PageObjects.CommonDialogs.CompletedDialog;
import ru.PageObjects.CommonDialogs.ConfirmDialog;
import ru.PageObjects.Supplier.CommonClarificationRequest.CommonClarificationRequestCreatePage;
import ru.PageObjects.Supplier.CommonClarificationRequest.CommonClarificationRequestViewPage;
import ru.PageObjects.Supplier.CommonClarificationRequest.MyResultRequestsPage;
import ru.StepDefinitions.AbstractStepDefinitions;

import java.util.concurrent.TimeUnit;

/**
 * Класс, описывающий шаги работы поставщика с запросами на разъяснение документации/результатов/заявок.
 * Created by Vladimir V. Klochkov on 01.10.2019.
 * Updated by Vladimir V. Klochkov on 25.09.2020.
 */
public class SupplierCommonClarificationRequestStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final CommonClarificationRequestCreatePage commonClarificationRequestCreatePage =
            new CommonClarificationRequestCreatePage(driver);
    private final CommonClarificationRequestViewPage commonClarificationRequestViewPage =
            new CommonClarificationRequestViewPage(driver);
    private final MyResultRequestsPage myResultRequestsPage = new MyResultRequestsPage(driver);
    //------------------------------------------------------------------------------------------------------------------
    private final ConfirmDialog confirmDialog = new ConfirmDialog(driver);
    private final CompletedDialog completedDialog = new CompletedDialog(driver);
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    @And("'Поставщик' переходит к просмотру раздела запроса на разъяснение {string}:")
    public void supplierGoesToBlockOnCommonClarificationRequestCreatePage(String block) throws Throwable {
        String stepName = String.format("'Поставщик' переходит к просмотру раздела запроса на разъяснение '%s':", block);
        this.logStepName(stepName);

        timer.start();

        commonClarificationRequestCreatePage.gotoBlock(block);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет что номер закупки на странице запроса на разъяснение верен")
    public void supplierChecksPurchaseNumberOnCommonClarificationRequestCreatePage() {
        String stepName = "'Поставщик' проверяет что номер закупки на странице запроса на разъяснение верен";
        this.logStepName(stepName);

        timer.start();

        commonClarificationRequestCreatePage.
                verifyField("Номер закупки", config.getParameter("PurchaseNumber"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' сохраняет наименование закупки со страницы запроса на разъяснение как параметр автоматического теста")
    public void supplierStoresValueOfPurchaseNameAsTestParameter() {
        String stepName = "'Поставщик' сохраняет наименование закупки со страницы запроса на разъяснение как параметр" +
                " автоматического теста";
        this.logStepName(stepName);

        timer.start();

        config.setParameter("PurchaseName",
                commonClarificationRequestCreatePage.getField("Наименование закупки"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет что наименование закупки на странице запроса на разъяснение верно")
    public void supplierChecksPurchaseNameOnCommonClarificationRequestCreatePage() {
        String stepName = "'Поставщик' проверяет что наименование закупки на странице запроса на разъяснение верно";
        this.logStepName(stepName);

        timer.start();

        commonClarificationRequestCreatePage.
                verifyField("Наименование закупки", config.getParameter("PurchaseName"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' заполняет поле {string} на странице запроса на разъяснение значением {string}")
    public void supplierFillsFieldByNameOnCommonClarificationRequestCreatePage(String fieldName, String value) {
        String stepName = String.format(
                "'Поставщик' заполняет поле '%s' на странице запроса на разъяснение значением '%s'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        commonClarificationRequestCreatePage.setField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' заполняет поле {string} на странице запроса на разъяснение строкой в {int} байт")
    public void supplierFillsFieldByNameWithLongStringOnCommonClarificationRequestCreatePage
            (String fieldName, int length) {
        String stepName = String.format(
                "'Поставщик' заполняет поле '%s' на на странице запроса на разъяснение строкой в '%d' байт",
                fieldName, length);
        this.logStepName(stepName);

        timer.start();

        commonClarificationRequestCreatePage.setField(fieldName, StringUtils.repeat("s", length));

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет, что поле {string} на странице запроса на разъяснение содержит строку в {int} байт")
    public void supplierChecksFieldByNameForLongStringOnCommonClarificationRequestCreatePage
            (String fieldName, int length) {
        String stepName = String.format(
                "'Поставщик' проверяет, что поле '%s' на странице запроса на разъяснение содержит строку в '%d' байт",
                fieldName, length);
        this.logStepName(stepName);

        timer.start();

        commonClarificationRequestCreatePage.verifyField(fieldName, StringUtils.repeat("s", length));

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет, что поле {string} на странице запроса на разъяснение содержит {string}")
    public void supplierChecksFieldByNameOnCommonClarificationRequestCreatePage(String fieldName, String value) {
        String stepName = String.format(
                "'Поставщик' проверяет, что поле '%s' на странице запроса на разъяснение содержит '%s'",
                fieldName, value);
        this.logStepName(stepName);

        timer.start();

        commonClarificationRequestCreatePage.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' прикрепляет документ в разделе 'Сведения о предмете запроса' на странице запроса на разъяснение")
    public void supplierUploadsDocumentInSubjectPartitionOnCommonClarificationRequestCreatePage() {
        String stepName = "'Поставщик' прикрепляет документ в разделе 'Сведения о предмете запроса' на странице " +
                "запроса на разъяснение";
        this.logStepName(stepName);

        timer.start();

        commonClarificationRequestCreatePage.uploadFileIntoDocuments(config.getAbsolutePathToFoundationDoc());
        commonClarificationRequestCreatePage.
                checkLinkToDownloadFileFromDocuments(config.getConfigParameter("FoundationDoc"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' сохраняет черновик запроса на разъяснение документации")
    public void supplierSavesCommonClarificationRequestDraftOnCommonClarificationRequestCreatePage() throws Throwable {
        String stepName = "'Поставщик' сохраняет черновик запроса на разъяснение документации";
        this.logStepName(stepName);

        timer.start();

        // Нажимаем на кнопку [Сохранить черновик]
        commonClarificationRequestCreatePage.clickOnButton("Сохранить черновик");

        // Нажимаем на кнопку [OK] в диалоговом окне [Операция успешно выполнена]
        completedDialog.clickOnOkButton();
        commonClarificationRequestCreatePage.waitForPageLoad();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' сохраняет черновик запроса на разъяснение результатов")
    public void supplierSavesCommonClarificationRequestOfResultsDraftOnCommonClarificationRequestCreatePage()
            throws Throwable {
        String stepName = "'Поставщик' сохраняет черновик запроса на разъяснение результатов";
        this.logStepName(stepName);

        timer.start();

        // Нажимаем на кнопку [Сохранить черновик]
        commonClarificationRequestCreatePage.clickOnButton("Сохранить черновик");

        // Нажимаем на кнопку [OK] в диалоговом окне [Операция успешно выполнена]
        completedDialog.clickOnOkButton();
        commonClarificationRequestCreatePage.waitForPageLoad();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' отправляет запрос на разъяснение документации")
    public void supplierSendsCommonClarificationRequestFromCommonClarificationRequestCreatePage() throws Throwable {
        String stepName = "'Поставщик' отправляет запрос на разъяснение документации";
        this.logStepName(stepName);

        timer.start();

        // Нажимаем на кнопку [Подписать и отправить]
        commonClarificationRequestCreatePage.clickOnButton("Подписать и отправить");

        // Нажимаем на кнопку [Да] в диалоговом окне [Подтверждение действия]
        confirmDialog.
                checkWindowText("Текст сообщения в окне 'Был сформирован новый запрос на разъяснение'",
                        "Был сформирован новый запрос на разъяснение.").
                clickOnButton("Кнопка Да в окне 'Был сформирован новый запрос на разъяснение'");

        // Нажимаем на кнопку [OK] в диалоговом окне [Операция успешно выполнена]
        completedDialog.clickOnOkButton();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' отправляет запрос на разъяснение результатов")
    public void supplierSendsCommonClarificationRequestOfResultsFromCommonClarificationRequestCreatePage()
            throws Throwable {
        String stepName = "'Поставщик' отправляет запрос на разъяснение результатов";
        this.logStepName(stepName);

        timer.start();

        // Нажимаем на кнопку [Подписать и отправить]
        commonClarificationRequestCreatePage.clickOnButton("Подписать и отправить");

        // Нажимаем на кнопку [OK] в диалоговом окне [Операция успешно выполнена]
        completedDialog.clickOnOkButton();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет реквизиты и статус запроса на разъяснение документации")
    public void supplierChecksRequisitesAndStatusOfCommonClarificationRequestOnCommonClarificationRequestViewPage()
            throws Throwable {
        String stepName = "'Поставщик' проверяет реквизиты и статус запроса на разъяснение документации";
        this.logStepName(stepName);

        timer.start();

        commonClarificationRequestViewPage.
                gotoBlock("Заголовок страницы").
                saveCommonClarificationRequestNumber().
                checkRequestStatus("Отправлен").
                gotoBlock("Общие сведения о закупке").
                verifyField("Номер закупки", config.getParameter("PurchaseNumber")).
                verifyField("Наименование закупки", config.getParameter("PurchaseName")).
                gotoBlock("Сведения о предмете запроса").
                verifyField("Текст запроса", "Текст запроса на раъяснение документации, адресованного " +
                        "Заказчику, который опубликовал извещение о закупке на электронной площадке.").
                checkLinkToDownloadFileIntoRequestDocuments(config.getConfigParameter("FoundationDoc")).
                gotoBlock("Разъяснение документации");

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет реквизиты и статус запроса на разъяснение результатов со строкой в {int} байт")
    public void supplierChecksRequisitesAndStatusOfCommonClarificationRequestOfResultsOnCommonClarificationRequestViewPage
            (int length) throws Throwable {
        String stepName = String.format(
                "'Поставщик' проверяет реквизиты и статус запроса на разъяснение результатов со строкой в '%d' байт",
                length);
        this.logStepName(stepName);

        timer.start();

        commonClarificationRequestViewPage.
                gotoBlock("Заголовок страницы").
                saveCommonClarificationRequestNumber().
                checkRequestStatus("Отправлен").
                gotoBlock("Общие сведения о закупке").
                verifyField("Номер закупки", config.getParameter("PurchaseNumber")).
                gotoBlock("Сведения о предмете запроса").
                verifyField("Текст запроса", StringUtils.repeat("s", length)).
                checkLinkToDownloadFileIntoRequestDocuments(config.getConfigParameter("FoundationDoc")).
                gotoBlock("Разъяснение результатов");

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет реквизиты и статус разъяснения документации аукциона")
    public void supplierChecksRequisitesAndStatusOfAuctCommonClarificationResponceOnCommonClarificationRequestViewPage()
            throws Throwable {
        String stepName = "'Поставщик' проверяет реквизиты и статус разъяснения документации аукциона";
        this.logStepName(stepName);

        timer.start();

        commonClarificationRequestViewPage.
                gotoBlock("Заголовок страницы").
                verifyField("Номер запроса", config.getParameter("CommonClarificationRequestNumber")).
                checkRequestStatus("Опубликовано разъяснение").
                gotoBlock("Общие сведения о закупке").
                verifyField("Номер закупки", config.getParameter("PurchaseNumber")).
                verifyField("Номер редакции извещения", "1").
                verifyField("Наименование закупки", config.getParameter("PurchaseName")).
                gotoBlock("Сведения о предмете запроса").
                verifyFieldIsNotEmpty("Отправитель").
                verifyField("Текст запроса", "Текст запроса на раъяснение документации, адресованного " +
                        "Заказчику, который опубликовал извещение о закупке на электронной площадке.").
                checkLinkToDownloadFileIntoRequestDocuments(config.getConfigParameter("FoundationDoc")).
                gotoBlock("Разъяснение документации").
                verifyField("Текст разъяснения", "Текст разъяснения.").
                checkLinkToDownloadFileIntoResponceDocuments(config.getConfigParameter("UserEntityDoc"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет реквизиты и статус разъяснения документации конкурса")
    public void supplierChecksRequisitesAndStatusOfTendCommonClarificationResponceOnCommonClarificationRequestViewPage()
            throws Throwable {
        String stepName = "'Поставщик' проверяет реквизиты и статус разъяснения документации конкурса";
        this.logStepName(stepName);

        timer.start();

        commonClarificationRequestViewPage.
                gotoBlock("Заголовок страницы").
                verifyField("Номер запроса", config.getParameter("CommonClarificationRequestNumber")).
                checkRequestStatus("Опубликовано разъяснение").
                gotoBlock("Общие сведения о закупке").
                verifyField("Номер закупки", config.getParameter("PurchaseNumber")).
                verifyField("Номер редакции извещения", "1").
                verifyField("Наименование закупки", config.getParameter("PurchaseName")).
                gotoBlock("Сведения о предмете запроса").
                verifyFieldIsNotEmpty("Отправитель").
                verifyField("Текст запроса", "Текст запроса на раъяснение документации, адресованного " +
                        "Заказчику, который опубликовал извещение о закупке на электронной площадке.").
                checkLinkToDownloadFileIntoRequestDocuments(config.getConfigParameter("FoundationDoc")).
                gotoBlock("Разъяснение документации").
                verifyField("Текст разъяснения", StringUtils.repeat("s", 2000)).
                checkLinkToDownloadFileIntoResponceDocuments(config.getConfigParameter("UserEntityDoc"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет реквизиты и статус разъяснения документации конкурса без запроса")
    public void supplierChecksRequisitesAndStatusOfTendCommonClarifResponceOnCommonClarificationRequestViewPage()
            throws Throwable {
        String stepName = "'Поставщик' проверяет реквизиты и статус разъяснения документации конкурса без запроса";
        this.logStepName(stepName);

        timer.start();

        commonClarificationRequestViewPage.
                gotoBlock("Заголовок страницы").
                verifyField("Номер запроса", config.getParameter("CommonClarificationRequestNumber")).
                checkRequestStatus("Опубликовано разъяснение").
                gotoBlock("Общие сведения о закупке").
                verifyField("Номер закупки", config.getParameter("PurchaseNumber")).
                verifyField("Номер редакции извещения", "1").
                verifyField("Наименование закупки", config.getParameter("PurchaseName")).
                gotoBlock("Разъяснение документации").
                verifyField("Текст разъяснения", StringUtils.repeat("s", 2000)).
                checkLinkToDownloadFileIntoResponceDocuments(config.getConfigParameter("UserEntityDoc"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет реквизиты и статус разъяснения результатов аукциона")
    public void supplierChecksRequisitesAndStatusOf()
            throws Throwable {
        String stepName = "'Поставщик' проверяет реквизиты и статус разъяснения результатов аукциона";
        this.logStepName(stepName);

        timer.start();

        commonClarificationRequestViewPage.
                gotoBlock("Заголовок страницы").
                verifyField("Номер запроса", config.getParameter("CommonClarificationRequestNumber")).
                checkRequestStatus("Опубликовано разъяснение").
                gotoBlock("Общие сведения о закупке").
                verifyField("Номер закупки", config.getParameter("PurchaseNumber")).
                verifyField("Номер редакции извещения", "1").
                verifyField("Наименование закупки", config.getParameter("PurchaseName")).
                gotoBlock("Сведения о предмете запроса").
                verifyFieldIsNotEmpty("Отправитель").
                verifyField("Текст запроса", StringUtils.repeat("s", 2000)).
                checkLinkToDownloadFileIntoRequestDocuments(config.getConfigParameter("FoundationDoc")).
                gotoBlock("Разъяснение результатов").
                verifyField("Текст разъяснения результатов", StringUtils.repeat("s", 2000)).
                checkLinkToDownloadFileIntoResponceDocuments(config.getConfigParameter("UserEntityDoc"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' находит отправленный им запрос используя номер запроса, сохраненный ранее в параметрах теста")
    public void supplierFindsRequestUsingRequestNumberPreviouslyStoredInTestParameters() throws Throwable {
        String stepName = "'Поставщик' находит отправленный им запрос используя номер запроса, сохраненный ранее " +
                "в параметрах теста";
        this.logStepName(stepName);

        timer.start();

        String commonClarificationRequestNumber = config.getParameter("CommonClarificationRequestNumber");

        myResultRequestsPage.
                gotoBlock("Заголовок страницы").
                setField("Номер запроса", commonClarificationRequestNumber).
                clickOnButton("Поиск");
        myResultRequestsPage.waitForPageLoad();
        myResultRequestsPage.
                checkRequestNumber(commonClarificationRequestNumber).
                checkRequestState("Получено разъяснение").
                isReadByCustomer("Да");

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' переходит по ссылке с номером запроса в результатах поиска")
    public void supplierOpensRequestUsingRequestNumberLink() throws Throwable {
        String stepName = "Поставщик' переходит по ссылке с номером запроса в результатах поиска";
        this.logStepName(stepName);

        timer.start();

        myResultRequestsPage.clickOnRequestNumber();

        timer.printStepTotalTime(stepName);
    }
}
