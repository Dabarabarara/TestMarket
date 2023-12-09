package ru.StepDefinitions.Supplier.CommonClarificationResponce;

import cucumber.api.java.en.And;
import org.apache.commons.lang.StringUtils;
import ru.PageObjects.CommonDialogs.CompletedDialog;
import ru.PageObjects.CommonDialogs.ConfirmDialog;
import ru.PageObjects.Supplier.CommonClarificationRequest.CommonClarificationRequestCreatePage;
import ru.PageObjects.Supplier.CommonClarificationRequest.CommonClarificationRequestViewPage;
import ru.PageObjects.Supplier.CommonClarificationRequest.MyResultRequestsPage;
import ru.StepDefinitions.AbstractStepDefinitions;

/**
 * Класс, описывающий шаги работы поставщика с ответами на запросы разъяснения документации/результатов/заявок.
 * Created by Vladimir V. Klochkov on 25.10.2019.
 * Updated by Vladimir V. Klochkov on 26.09.2020.
 */
public class SupplierCommonClarificationResponceStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final MyResultRequestsPage myResultRequestsPage = new MyResultRequestsPage(driver);
    private final CommonClarificationRequestCreatePage commonClarificationRequestCreatePage =
            new CommonClarificationRequestCreatePage(driver);
    private final CommonClarificationRequestViewPage commonClarificationRequestViewPage =
            new CommonClarificationRequestViewPage(driver);
    //------------------------------------------------------------------------------------------------------------------
    private final ConfirmDialog confirmDialog = new ConfirmDialog(driver);
    private final CompletedDialog completedDialog = new CompletedDialog(driver);
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    @And("'Поставщик' находит отправленный Заказчиком запрос используя номер закупки, сохраненный ранее в параметрах теста")
    public void supplierFindsRequestUsingPurchaseNumberPreviouslyStoredInTestParameters() throws Throwable {
        String stepName = "'Поставщик' находит отправленный Заказчиком запрос используя номер закупки, сохраненный " +
                "ранее в параметрах теста";
        this.logStepName(stepName);

        timer.start();

        String commonClarificationRequestNumber = config.getParameter("CommonClarificationRequestNumber");
        String purchaseNumber = config.getParameter("PurchaseNumber");
        String purchaseName = config.getParameter("PurchaseName");

        myResultRequestsPage.
                gotoBlock("Заголовок страницы").
                setField("Номер запроса", commonClarificationRequestNumber).
                setField("Номер закупки", purchaseNumber).
                setField("Наименование закупки", purchaseName).
                clickOnButton("Поиск");
        myResultRequestsPage.waitForPageLoad();
        myResultRequestsPage.
                checkRequestNumber(commonClarificationRequestNumber).
                checkRequestState("Требует разъяснения").
                isReadByCustomer("Да");

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет реквизиты и статус запроса на разъяснение заявки со строкой в {int} байт")
    public void supplierChecksRequestRequisitesAndStatus4ClarificationOfApplicationOnCommonClarificationRequestViewPage
            (int length) throws Throwable {
        String stepName = String.format(
                "'Поставщик' проверяет реквизиты и статус запроса на разъяснение заявки со строкой в '%d' байт",
                length);
        this.logStepName(stepName);

        timer.start();

        commonClarificationRequestViewPage.
                gotoBlock("Заголовок страницы").
                checkRequestStatus("Требует разъяснения").
                gotoBlock("Общие сведения о закупке").
                verifyField("Номер закупки", config.getParameter("PurchaseNumber")).
                gotoBlock("Сведения о предмете запроса").
                verifyField("Текст запроса", StringUtils.repeat("s", length)).
                checkLinkToDownloadFileIntoRequestDocuments(config.getConfigParameter("FoundationDoc")).
                gotoBlock("Документы запроса");

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' нажимает на кнопку {string} на странице 'Запрос на разъяснение заявки'")
    public void supplierClicksOnButtonByNameOnCommonClarificationRequestViewPage(String buttonName) throws Throwable {
        String stepName = String.format(
                "'Поставщик' нажимает на кнопку '%s' на странице 'Запрос на разъяснение заявки'", buttonName);
        this.logStepName(stepName);

        timer.start();

        commonClarificationRequestViewPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' прикрепляет документ в разделе 'Разъяснение заявки' на странице запроса на разъяснение")
    public void supplierUploadsDocumentInClarificationOfApplicationPartitionOnCommonClarificationRequestCreatePage()
            throws Throwable {
        String stepName = "'Поставщик' прикрепляет документ в разделе 'Разъяснение заявки' на странице " +
                "запроса на разъяснение";
        this.logStepName(stepName);

        timer.start();

        commonClarificationRequestCreatePage.uploadFileIntoResponceDocuments(config.getAbsolutePathToUserEntityDoc());
        commonClarificationRequestCreatePage.
                checkLinkToDownloadFileFromResponceDocuments(config.getConfigParameter("UserEntityDoc"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' отправляет запрос на разъяснение заявки")
    public void supplierSendsClarificationOfApplicationResponceFromCommonClarificationRequestCreatePage()
            throws Throwable {
        String stepName = "'Поставщик' отправляет запрос на разъяснение заявки";
        this.logStepName(stepName);

        timer.start();

        // Нажимаем на кнопку [Подписать и отправить]
        commonClarificationRequestCreatePage.clickOnButton("Подписать и отправить");

        // Нажимаем на кнопку [Да] в диалоговом окне [Подтверждение действия]
        confirmDialog.
                checkWindowText("Текст сообщения в окне 'Вы уверены, что хотите отправить ответ на запрос?'",
                        "Вы уверены, что хотите отправить ответ на запрос?").
                checkButtonExistsAndClickable("Кнопка Да в окне 'Вы уверены, что хотите отправить ответ на запрос?'").
                checkButtonExistsAndClickable("Кнопка Нет в окне 'Вы уверены, что хотите отправить ответ на запрос?'").
                clickOnButton("Кнопка Да в окне 'Вы уверены, что хотите отправить ответ на запрос?'");

        // Нажимаем на кнопку [OK] в диалоговом окне [Операция успешно выполнена]
        completedDialog.clickOnOkButton();

        timer.printStepTotalTime(stepName);
    }
}
