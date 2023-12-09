package ru.StepDefinitions.Customer.Protocol;

import Helpers.DbOperations;
import Helpers.Timer;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import ru.PageObjects.CommonDialogs.ErrorDialog;
import ru.PageObjects.CommonDialogs.RetraidingChangeDialog;
import ru.PageObjects.Customer.Protocol.RefuseContractProtocolPage;
import ru.PageObjects.Customer.MyOrganization.OosMessageQueuePage;
import ru.PageObjects.Customer.Protocol.*;
import ru.PageObjects.Customer.CustomerMyPurchasesPage;
import ru.StepDefinitions.AbstractStepDefinitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Класс описывающий шаги работы Заказчика с протоколами.
 * Created by Vladimir V. Klochkov on 28.02.2017.
 * Updated by Alexander S. Vasyurenko on 30.06.2021.
 */
public class CustomerProtocolStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final CustomerMyPurchasesPage customerMyPurchasesPage = new CustomerMyPurchasesPage(driver);
    private final CommonCustomerProtocolPage commonCustomerProtocolPage = new CommonCustomerProtocolPage(driver);
    private final AuctionCustomerProtocolPage auctionCustomerProtocolPage = new AuctionCustomerProtocolPage(driver);
    private final TenderEvaluationRequestCustomerProtocolPage tenderEvaluationRequestCustomerProtocolPage =
            new TenderEvaluationRequestCustomerProtocolPage(driver);
    private final OpenAccessCustomerProtocolPage openAccessCustomerProtocolPage = new OpenAccessCustomerProtocolPage(driver);
    private final ConsiderationOfSingleAuctionApplicationProtocolPage considerationOfSingleAuctionApplicationProtocolPage =
            new ConsiderationOfSingleAuctionApplicationProtocolPage(driver);
    private final ConsiderationRequestCustomerProtocolPage considerationRequestCustomerProtocolPage =
            new ConsiderationRequestCustomerProtocolPage(driver);
    private final PrequalificationConsiderationProtocolPage prequalificationConsiderationProtocolPage =
            new PrequalificationConsiderationProtocolPage(driver);
    private final RetenderingCustomerProtocolPage retenderingCustomerProtocolPage = new RetenderingCustomerProtocolPage(driver);
    private final SummarizeCustomerProtocolPage summarizeCustomerProtocolPage = new SummarizeCustomerProtocolPage(driver);
    private final RefuseContractProtocolPage refuseContractProtocolPage = new RefuseContractProtocolPage(driver);
    private final SMBTenderDiscussionOfFunctionalCharacteristicsProtocolPage
            smbTenderDiscussionOfFunctionalCharacteristicsProtocolPage =
            new SMBTenderDiscussionOfFunctionalCharacteristicsProtocolPage(driver);
    private final OosMessageQueuePage oosMessageQueuePage = new OosMessageQueuePage(driver);
    //------------------------------------------------------------------------------------------------------------------
    private final RetraidingChangeDialog retraidingChangeDialog = new RetraidingChangeDialog(driver);
    private final CreateProtocolApprovalRequestDialog createProtocolApprovalRequestDialog =
            new CreateProtocolApprovalRequestDialog(driver);
    private final PublishProtocolOnOosDialog publishProtocolOnOosDialog = new PublishProtocolOnOosDialog(driver);
    private final ErrorDialog errorDialog = new ErrorDialog(driver);

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    @And("^'Заказчик' публикует протокол открытия доступа$")
    public void customerPublishesOpeningCompetitionAccessProtocol() throws Throwable {
        String stepName = "'Заказчик' публикует протокол открытия доступа";
        this.logStepName(stepName);

        timer.start();

        // Ждём загрузки страницы [Протокол открытия доступа .........]
        openAccessCustomerProtocolPage.waitForPageLoad();
        openAccessCustomerProtocolPage.checkPageUrl("Один из протоколов");

        // Нажимаем кнопку [Сформировать и прикрепить], проверяем наличие прикрепленного файла с протоколом
        openAccessCustomerProtocolPage.clickGenerateAndAttachButton();
        String actualText = openAccessCustomerProtocolPage.getAttachedProtocolLinkText("1");
        System.out.printf("[-] Ожидаемый текст ссылки: '.doc' [-] Актуальный текст ссылки: '%s'%n", actualText);
        Assert.assertTrue("Текст ссылки не совпадает", actualText.contains(".doc"));

        // Проверяем наличие дубликатов в списке шаблонов протоколов
        commonCustomerProtocolPage.checkDuplicatesInTemplatesList();

        // Нажимаем кнопку [Подписать и опубликовать]
        openAccessCustomerProtocolPage.clickOnButton("Подписать и опубликовать");

        // Нажимаем кнопку [Продолжить] в диалоговом окне [Предупреждение]
        openAccessCustomerProtocolPage.clickButtonInPopupWindow("Продолжить-Предупреждение");

        // Нажимаем на кнопку [OK] в диалоговом окне [Информация]
        openAccessCustomerProtocolPage.clickButtonInPopupWindow("ОК-Информация");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' допускает обе заявки и публикует протокол рассмотрения заявок аукциона$")
    public void customerAllowsBothAndPublishesAuctionConsiderationRequest() throws Throwable {
        String stepName = "'Заказчик' допускает обе заявки и публикует протокол рассмотрения заявок аукциона";
        this.logStepName(stepName);

        timer.start();

        // нажимаем на кнопку [Допустить всех]
        considerationRequestCustomerProtocolPage.clickAllowAllButton();

        // нажимаем на кнопку [Сформировать и прикрепить]
        considerationRequestCustomerProtocolPage.clickGenerateAndAttachButton();
        String actualText = considerationRequestCustomerProtocolPage.getAttachedProtocolLinkText();
        System.out.printf("[-] Ожидаемый текст ссылки: '.doc' [-] Актуальный текст ссылки: '%s'%n", actualText);
        Assert.assertTrue("Текст ссылки не совпадает", actualText.contains(".doc"));
        commonCustomerProtocolPage.checkDuplicatesInTemplatesList();

        // нажимаем на кнопку [Подписать и опубликовать]
        considerationRequestCustomerProtocolPage.clickOnButton("Подписать и опубликовать");

        // окно [Предупреждение] нажимаем на кнопку [Продолжить]
        considerationRequestCustomerProtocolPage.clickButtonInPopupWindow("Продолжить-Предупреждение");

        // окно [Информация] нажимаем на кнопку [ОК]
        considerationRequestCustomerProtocolPage.clickButtonInPopupWindow("ОК-Информация");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет, что информация о 3-х Поставщиках не отображается в таблице и первых частях заявок$")
    public void customerChecksThatSuppliersInfoIsHiddenBeforeTheBidding() throws Throwable {
        String stepName = "'Заказчик' проверяет, что информация о 3-х Поставщиках не отображается в таблице и " +
                "первых частях заявок";
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.checkThatSuppliersInfoIsHiddenBeforeTheBidding();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет, что информация о 2-х Поставщиках не отображается в таблице и первых частях заявок$")
    public void customerChecksThatSuppliersInfoIsHiddenAfterTheBidding() throws Throwable {
        String stepName = "'Заказчик' проверяет, что информация о 2-х Поставщиках не отображается в таблице и " +
                "первых частях заявок";
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.checkThatSuppliersInfoIsHiddenAfterTheBidding();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет, что информация о 2-х Поставщиках отображается в таблице и вторых частях заявок$")
    public void customerChecksThatSuppliersInfoIsShownAfterTheBidding() throws Throwable {
        String stepName = "'Заказчик' проверяет, что информация о 2-х Поставщиках отображается в таблице и " +
                "вторых частях заявок";
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.checkThatSuppliersInfoIsShownAfterTheBidding();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет, что информация о 2-х Поставщиках отображается в таблице и вторых частях заявок ППИ-А$")
    public void customerChecksThatSuppliersInfoIsShownAfterTheBiddingInSummaryProtocol() throws Throwable {
        String stepName = "'Заказчик' проверяет, что информация о 2-х Поставщиках отображается в таблице и вторых" +
                " частях заявок ППИ-А";
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.checkThatSuppliersInfoIsShownAfterTheBiddingInSummaryProtocol();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' отклоняет заявку второго Поставщика$")
    public void customerRejectsSecondSupplierRequest() throws Throwable {
        String stepName = "'Заказчик' отклоняет заявку второго Поставщика";
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.rejectSupplierRequestForPositionalComparison("2");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' устанавливает значение переключателя 'Заключить договор с единственным участником' в положение 'Да'$")
    public void customerSetsSingleParticipantContractSwitchToYesPosition() throws Throwable {
        String stepName = "'Заказчик' устанавливает значение переключателя " +
                "'Заключить договор с единственным участником' в положение 'Да'";
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.setSingleParticipantContractSwitchToYesPosition();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' устанавливает флажок 'Закупка признана несостоявшейся'$")
    public void customerTurnsOnMissedContestCheckBox() throws Throwable {
        String stepName = "'Заказчик' устанавливает флажок 'Закупка признана несостоявшейся'";
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.turnOnMissedContestCheckBox();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' снимает флажок 'Закупка признана несостоявшейся'$")
    public void customerTurnsOffMissedContestCheckBox() throws Throwable {
        String stepName = "'Заказчик' cнимает флажок 'Закупка признана несостоявшейся'";
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.turnOffMissedContestCheckBox();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' допускает заявки от первого и второго Поставщиков и отклоняет заявку третьего Поставщика$")
    public void customerAllowsFirstAndSecondSupplierRequestsAndRejectsThirdSupplierRequest() throws Throwable {
        String stepName = "'Заказчик' допускает заявки от первого и второго Поставщиков и " +
                "отклоняет заявку третьего Поставщика";
        this.logStepName(stepName);

        timer.start();

        // нажимаем на кнопку [Допустить всех]
        considerationRequestCustomerProtocolPage.clickAllowAllButton();

        // отклоняем  заявку третьего Поставщика
        considerationRequestCustomerProtocolPage.rejectSupplierRequestForAuctionWithRequestsInTwoParts("3");

        // нажимаем на кнопку [Сформировать и прикрепить]
        considerationRequestCustomerProtocolPage.clickGenerateAndAttachButton();
        String actualText = considerationRequestCustomerProtocolPage.getAttachedProtocolLinkText();
        System.out.printf("[-] Ожидаемый текст ссылки: '.doc' [-] Актуальный текст ссылки: '%s'%n", actualText);
        Assert.assertTrue("Текст ссылки не совпадает", actualText.contains(".doc"));
        commonCustomerProtocolPage.checkDuplicatesInTemplatesList();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' допускает заявки от первого и второго Поставщиков$")
    public void customerAllowsFirstAndSecondSupplierRequests() throws Throwable {
        String stepName = "'Заказчик' допускает заявки от первого и второго Поставщиков";
        this.logStepName(stepName);

        timer.start();

        // Нажимаем на кнопку [Допустить всех] для первого лота в закупке
        considerationRequestCustomerProtocolPage.clickAllowAllButton();

        // Нажимаем на кнопку [Сформировать и прикрепить] для первого лота в закупке
        considerationRequestCustomerProtocolPage.clickGenerateAndAttachButton();

        // Проверяем прикрепленный файл протокола рассмотрения заявок
        String actualText = considerationRequestCustomerProtocolPage.getAttachedProtocolLinkText();
        System.out.printf("[-] Ожидаемый текст ссылки: '.doc' [-] Актуальный текст ссылки: '%s'%n", actualText);
        Assert.assertTrue("Текст ссылки не совпадает", actualText.contains(".doc"));
        System.out.println("[ИНФОРМАЦИЯ]: валидация текста ссылки на прикрепленный файл протокола прошла успешно.");

        // Проверяем наличие дубликатов в раскрывающемся списке шаблонов протоколов
        commonCustomerProtocolPage.checkDuplicatesInTemplatesList();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на кнопку 'Допустить всех' для каждого из \"([^\"]*)\" лотов$")
    public void customerAllowsFirstAndSecondSupplierRequestsForAllLots(String lots) throws Throwable {
        String stepName =
                String.format("'Заказчик' нажимает на кнопку 'Допустить всех' для каждого из '%s' лотов", lots);
        this.logStepName(stepName);

        timer.start();

        // Нажимаем на кнопку [Допустить всех] для каждого из лотов закупки
        int number = Integer.parseInt(lots);
        for (int i = 1; i <= number; i++)
            considerationRequestCustomerProtocolPage.clickAllowAllButton(Integer.toString(i));

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на кнопку 'Допустить всех' в разделе 'Сведения о квалификационном отборе'$")
    public void customerClicksAllowAllButtonInQualificationInformationPartition() throws Throwable {
        String stepName =
                "'Заказчик' нажимает на кнопку 'Допустить всех' в разделе 'Сведения о квалификационном отборе'";
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.clickAllowAllButtonInQualificationInformationPartition();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на кнопку 'Допустить всех' в разделе 'Сведения протокола рассмотрения вторых частей заявок'$")
    public void customerClicksAllowAllButtonInDetailsOfTheReviewSecondPartOfApplicationsPartition() throws Throwable {
        String stepName = "'Заказчик' нажимает на кнопку 'Допустить всех' в разделе " +
                "'Сведения протокола рассмотрения вторых частей заявок'";
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.
                clickAllowAllButtonInDetailsOfTheReviewSecondPartOfApplicationsPartition();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на кнопку 'Сформировать и прикрепить' для каждого из \"([^\"]*)\" лотов$")
    public void customerClicksGenerateAndAttachButtonForAllLots(String lots) throws Throwable {
        String stepName = String.format(
                "'Заказчик' нажимает на кнопку 'Сформировать и прикрепить' для каждого из '%s' лотов", lots);
        this.logStepName(stepName);

        timer.start();

        // Нажимаем на кнопку [Сформировать и прикрепить] для каждого из лотов закупки
        int number = Integer.parseInt(lots);
        for (int i = number; i > 0; i--)
            auctionCustomerProtocolPage.clickGenerateAndAttachButton(Integer.toString(i));

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет прикрепленные файлы \"([^\"]*)\" протокола рассмотрения заявок$")
    public void customerChecksAllAttachedProtocolFiles(String files) {
        String stepName =
                String.format("'Заказчик' проверяет прикрепленные файлы '%s' протокола рассмотрения заявок", files);
        this.logStepName(stepName);

        timer.start();

        // Проверяем прикрепленные файлы протокола рассмотрения заявок
        int number = Integer.parseInt(files);
        for (int i = 1; i <= number; i++) {
            String actual = auctionCustomerProtocolPage.getAttachedProtocolLinkText(Integer.toString(i));
            System.out.printf("[-] Ожидаемый текст ссылки: 'Протокол' [-] Актуальный текст ссылки: '%s'%n", actual);
            Assert.assertTrue("Текст ссылки не совпадает", actual.contains("Протокол"));
            System.out.println("[ИНФОРМАЦИЯ]: валидация текста ссылки на прикрепленный файл протокола прошла успешно.");
        }

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' публикует протокол рассмотрения заявок$")
    public void customerPublishesConsiderationRequestsProtocol() throws Throwable {
        String stepName = "'Заказчик' публикует протокол рассмотрения заявок";
        this.logStepName(stepName);

        timer.start();

        // Нажимаем на кнопку [Подписать и опубликовать]
        considerationRequestCustomerProtocolPage.clickOnButton("Подписать и опубликовать");

        // Окно [Предупреждение] нажимаем на кнопку [Продолжить]
        considerationRequestCustomerProtocolPage.clickButtonInPopupWindow("Продолжить-Предупреждение");

        // Окно [Информация] нажимаем на кнопку [ОК]
        considerationRequestCustomerProtocolPage.clickButtonInPopupWindow("ОК-Информация");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' отправляет на согласование протокол рассмотрения заявок$")
    public void customerStartsSendConsiderationRequestsProtocolToApproval() throws Throwable {
        String stepName = "'Заказчик' отправляет на согласование протокол рассмотрения заявок";
        this.logStepName(stepName);

        timer.start();

        // Нажимаем на кнопку [Отправить на согласование]
        considerationRequestCustomerProtocolPage.clickOnButton("Отправить на согласование");

        // Заполняем все поля в диалоговом окне [Создание запроса на согласование] и нажимаем на кнопку [Отправить]
        createProtocolApprovalRequestDialog.
                ifDialogOpened().
                setFieldClearClickAndSendKeys("Поле Дата согласования",
                        timer.getDateShift(new Date(), "DAYS", "0")).
                setFieldClearClickAndSendKeys("Поле Плановая дата публикации закупки",
                        timer.getDateShift(new Date(), "DAYS", "0")).
                selectRadioButton("Радиокнопка Требуется решение каждого члена комиссии").
                selectWorkGroupByOrderNo(2).
                clickOnButtonByName("Кнопка Отправить");

        // Проверяем, что открыта страница [Протокол рассмотрения заявок аукциона]
        considerationRequestCustomerProtocolPage.checkPageUrl("Один из протоколов");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' публикует протокол оценки заявок конкурса$")
    public void customerPublishesEvaluationRequestProtocol() throws Throwable {
        String stepName = "'Заказчик' публикует протокол оценки заявок конкурса";
        this.logStepName(stepName);

        timer.start();

        tenderEvaluationRequestCustomerProtocolPage.setFinalScore(0, "20");
        tenderEvaluationRequestCustomerProtocolPage.setFinalScore(1, "10");
        tenderEvaluationRequestCustomerProtocolPage.clickGenerateAndAttachButton();
        //проверка в секции: Формирование протокола из шаблона
        String actualText = tenderEvaluationRequestCustomerProtocolPage.getAttachedProtocolLinkText();
        System.out.printf("[-] Ожидаемый текст ссылки: '.doc' [-] Актуальный текст ссылки: '%s'%n", actualText);
        Assert.assertTrue("Текст ссылки не совпадает", actualText.contains(".doc"));
        commonCustomerProtocolPage.checkDuplicatesInTemplatesList();
        tenderEvaluationRequestCustomerProtocolPage.clickOnButton("Подписать и опубликовать");
        tenderEvaluationRequestCustomerProtocolPage.clickButtonInPopupWindow("Продолжить-Предупреждение");
        tenderEvaluationRequestCustomerProtocolPage.clickButtonInPopupWindow("ОК-Информация");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет статус протокола - 'Формирование'$")
    public void customerChecksThatProtocolStatusIsForming() throws Throwable {
        String stepName = "'Заказчик' проверяет статус протокола - 'Формирование'";
        this.logStepName(stepName);

        timer.start();

        // Ждём окончания загрузки страницы
        commonCustomerProtocolPage.waitForPageLoad();
        commonCustomerProtocolPage.checkPageUrl("Один из протоколов");

        // Проверяем статус протокола
        commonCustomerProtocolPage.checkProtocolStatus("Формирование");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что в имени {int} столбца в таблице 'Сведения протокола открытия доступа' содержится строка {string}")
    public void customerChecksTableColumnsInOpenAccessCustomerProtocolPage(int positionColumnInTable, String columnName) {
        String stepName =
                String.format("'Заказчик' проверяет, что в имени '%d' столбца " +
                                "в таблице 'Сведения протокола открытия доступа' содержится строка '%s'",
                        positionColumnInTable, columnName);
        this.logStepName(stepName);

        timer.start();

        openAccessCustomerProtocolPage.checkTableColumnName(positionColumnInTable, columnName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что в имени {int} столбца в таблице 'Сведения протокола рассмотрения единственной заявки' содержится строка {string}")
    public void customerChecksTableColumnsInConsiderationOfSingleAuctionApplicationProtocolPage(int positionColumnInTable, String columnName) {
        String stepName =
                String.format("'Заказчик' проверяет, что в имени '%d' столбца " +
                                "в таблице 'Сведения протокола рассмотрения единственной заявки' содержится строка '%s'",
                        positionColumnInTable, columnName);
        this.logStepName(stepName);

        timer.start();

        considerationOfSingleAuctionApplicationProtocolPage.checkTableColumnName(positionColumnInTable, columnName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' открывает 'Заявку' для {int} участника в таблице 'Сведения протокола открытия доступа'")
    public void customerOpenApplicationPrintForms(int printFormNumber) throws Exception {
        String stepName =
                String.format("'Заказчик' открывает 'Заявку' в '%d' строке " +
                        "в таблице 'Сведения протокола открытия доступа'", printFormNumber);
        this.logStepName(stepName);

        timer.start();

        openAccessCustomerProtocolPage.openApplicationPrintForms(printFormNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' открывает {string} для {int} участника в таблице 'Сведения протокола рассмотрения единственной заявки'")
    public void customerOpenApplicationPrintFormsInConsiderationOfSingleAuctionApplicationProtocolPage(String applicationName, int printFormNumber) throws Exception {
        String stepName =
                String.format("'Заказчик' открывает '%s' в '%d' строке " +
                        "в таблице 'Сведения протокола рассмотрения единственной заявки'", applicationName, printFormNumber);
        this.logStepName(stepName);

        timer.start();

        considerationOfSingleAuctionApplicationProtocolPage.openApplicationPrintForm(applicationName, printFormNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' открывает 'Запрос на разъяснение заявки' для {int} участника в таблице 'Сведения протокола открытия доступа'")
    public void customerOpenCreateInquiryPage(int registerInquiriesNumber) throws Exception {
        String stepName =
                String.format("'Заказчик' открывает 'Заявку' в '%d' строке " +
                        "в таблице 'Сведения протокола открытия доступа'", registerInquiriesNumber);
        this.logStepName(stepName);

        timer.start();

        openAccessCustomerProtocolPage.openCreateInquiryPage(registerInquiriesNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет архив 'Заявка по закупке №' в разделе 'Сведения протокола открытия доступа'")
    public void customerCheckFileDocumentColumn() throws Exception {
        String zipArchiveName = "Заявки по закупке № " + config.getParameter("PurchaseNumber") + " (лот № 1) ";
        String stepName =
                String.format("'Заказчик' проверяет архив '%s' в разделе " +
                        "'Сведения протокола открытия доступа'", zipArchiveName);
        this.logStepName(stepName);

        timer.start();
        ArrayList<String> expectedFileNames = new ArrayList<>();
        //--------------------------------------------------------------------------------------------------------------
        expectedFileNames.add("Заявка1_ООО Автотестеры 223/Печатная_форма_заявки.html");
        expectedFileNames.
                add("Заявка1_ООО Автотестеры 223/Печатная_форма_сведений_об_участнике_закупки.html");
        expectedFileNames.add("Заявка1_ООО Автотестеры 223/Документы заявки/1.txt");
        expectedFileNames.add("Заявка2_ООО Автотестеры 223/Печатная_форма_заявки.html");
        expectedFileNames.
                add("Заявка2_ООО Автотестеры 223/Печатная_форма_сведений_об_участнике_закупки.html");
        expectedFileNames.add("Заявка2_ООО Автотестеры 223/Документы заявки/1.txt");
        expectedFileNames.add("Список участников.xlsx");
        //--------------------------------------------------------------------------------------------------------------

        commonCustomerProtocolPage.downloadZipArchiveModifyAndCheckFileNamesInIt(zipArchiveName, expectedFileNames);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет архив 'Заявка по закупке №' в разделе 'Сведения протокола открытия доступа' для 'Конкурс заявка в двух частях'")
    public void customerCheckApplicationsInOpenAccessProtocolForTenderApplicationInTwoParts() throws Exception {
        String zipArchiveName = "Заявки по закупке № " + config.getParameter("PurchaseNumber") + " (лот № 1) ";
        String stepName =
                String.format("'Заказчик' проверяет архив '%s' в разделе " +
                        "'Сведения протокола открытия доступа'", zipArchiveName);
        this.logStepName(stepName);

        timer.start();
        ArrayList<String> expectedFileNames = new ArrayList<>();
        //--------------------------------------------------------------------------------------------------------------
        expectedFileNames.add("Заявка 1/Печатная_форма_первой_части_заявки.html");
        expectedFileNames.add("Заявка 1/Документы заявки/1.txt");
        expectedFileNames.add("Заявка 2/Печатная_форма_первой_части_заявки.html");
        expectedFileNames.add("Заявка 2/Документы заявки/1.txt");
        expectedFileNames.add("Заявка 3/Печатная_форма_первой_части_заявки.html");
        expectedFileNames.add("Заявка 3/Документы заявки/1.txt");
        expectedFileNames.add("Список участников.xlsx");
        //--------------------------------------------------------------------------------------------------------------

        commonCustomerProtocolPage.downloadZipArchiveModifyAndCheckFileNamesInIt(zipArchiveName, expectedFileNames);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет отсутствие столбца 'Ценовое предложение' в разделе 'Сведения протокола открытия доступа'$")
    public void customerChecksThatThePriceOfferColumnHiddenInTableWhenOpenAccessProtHasFormingState() {
        String stepName = "'Заказчик' проверяет отсутствие столбца 'Ценовое предложение' в разделе " +
                "'Сведения протокола открытия доступа'";
        this.logStepName(stepName);

        timer.start();

        openAccessCustomerProtocolPage.
                checkThatThePriceOfferColumnHiddenInTableWhenProtocolHasFormingState(7);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет наличие столбца 'Ценовое предложение' в разделе 'Сведения протокола открытия доступа'$")
    public void customerChecksThatThePriceOfferColumnVisibleInTableWhenOpenAccessProtHasFormingState() {
        String stepName = "'Заказчик' проверяет наличие столбца 'Ценовое предложение' в разделе " +
                "'Сведения протокола открытия доступа'";
        this.logStepName(stepName);

        timer.start();

        openAccessCustomerProtocolPage.
                checkThatThePriceOfferColumnVisibleInTableWhenProtocolHasFormingState(8);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет отсутствие столбца 'Ценовое предложение' в разделе 'Сведения протокола рассмотрения и оценки заявок'$")
    public void customerChecksThatThePriceOfferColumnHiddenInTableWhenAppsConsEvalProtHasFormingState() {
        String stepName = "'Заказчик' проверяет отсутствие столбца 'Ценовое предложение' в разделе " +
                "'Сведения протокола рассмотрения и оценки заявок'";
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.
                checkThatThePriceOfferColumnHiddenInTableWhenProtocolHasFormingState(13);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет наличие столбца 'Ценовое предложение' в разделе 'Сведения протокола рассмотрения и оценки заявок'$")
    public void customerChecksThatThePriceOfferColumnVisibleInTableWhenAppsConsEvalProtHasFormingState() {
        String stepName = "'Заказчик' проверяет отсутствие столбца 'Ценовое предложение' в разделе " +
                "'Сведения протокола рассмотрения и оценки заявок'";
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.
                checkThatThePriceOfferColumnVisibleInTableWhenProtocolHasFormingState(10);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет отсутствие столбца 'Ценовое предложение' в разделе 'Сведения протокола проведения переторжки'$")
    public void customerChecksThatThePriceOfferColumnHiddenInTableWhenRetenderingProtHasFormingState() {
        String stepName = "'Заказчик' проверяет отсутствие столбца 'Ценовое предложение' в разделе " +
                "'Сведения протокола проведения переторжки'";
        this.logStepName(stepName);

        timer.start();

        retenderingCustomerProtocolPage.
                checkThatThePriceOfferColumnHiddenInTableWhenProtocolHasFormingState();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет наличие столбца 'Ценовое предложение' в разделе 'Сведения протокола проведения переторжки'$")
    public void customerChecksThatThePriceOfferColumnVisibleInTableWhenRetenderingProtHasFormingState() {
        String stepName = "'Заказчик' проверяет наличие столбца 'Ценовое предложение' в разделе " +
                "'Сведения протокола проведения переторжки'";
        this.logStepName(stepName);

        timer.start();

        retenderingCustomerProtocolPage.
                checkThatThePriceOfferColumnVisibleInTableWhenProtocolHasFormingState();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет наличие столбца 'Ценовое предложение' в разделе 'Сведения протокола подведения итогов'$")
    public void customerChecksThatThePriceOfferColumnVisibleInTableWhenSummarizingProtHasFormingState() {
        String stepName = "'Заказчик' проверяет наличие столбца 'Ценовое предложение' в разделе " +
                "'Сведения протокола подведения итогов'";
        this.logStepName(stepName);

        timer.start();

        summarizeCustomerProtocolPage.
                checkThatThePriceOfferColumnVisibleInTableWhenProtocolHasFormingState(13);

        timer.printStepTotalTime(stepName);
    }

    // Без столбца "Итоговый балл" в таблимце раздела "Сведения протокола подведения итогов" та как "Запрос котировок" не являетс бальными видом
    @And("^'Заказчик' проверяет наличие столбца 'Ценовое предложение' в разделе 'Сведения протокола подведения итогов' в 'Запросе котировок'$")
    public void customerChecksThatThePriceOfferColumnVisibleInTableWhenSummarizingProtHasFormingStateInRequestForQuotations() {
        String stepName = "'Заказчик' проверяет наличие столбца 'Ценовое предложение' в разделе " +
                "'Сведения протокола подведения итогов' в 'Запросе котировок'";
        this.logStepName(stepName);

        timer.start();

        summarizeCustomerProtocolPage.
                checkThatThePriceOfferColumnVisibleInTableWhenProtocolHasFormingState(12);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет наличие столбца 'Ценовое предложение' в разделе 'Сведения протокола рассмотрения и оценки вторых частей заявок'$")
    public void customerChecksThatThePriceOfferColumnVisibleInTableWhenSecondPatrsConsProtHasFormingState() {
        String stepName = "'Заказчик' проверяет наличие столбца 'Ценовое предложение' в разделе " +
                "'Сведения протокола рассмотрения и оценки вторых частей заявок'";
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.
                checkThatThePriceOfferColumnVisibleInTableWhenProtocolHasFormingState(12);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет наличие столбца 'Лучший поправочный коэффициент' в разделе 'Сведения протокола подведения итогов' в 'Запросе котировок'")
    public void customerChecksThatTheCorrectionFactorColumnVisibleInTableWhenSummarizingProtHasFormingState() {
        String stepName = "'Заказчик' проверяет наличие столбца 'Лучший поправочный коэффициент' в разделе " +
                "'Сведения протокола подведения итогов' в 'Запросе котировок'";
        this.logStepName(stepName);

        timer.start();

        summarizeCustomerProtocolPage.
                checkThatTheCorrectionFactorColumnVisibleInTableWhenProtocolHasFormingState(13);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет отсутствие таблицы 'Ценовое предложение' в печатных формах заявок от Поставщиков 'ПОД'$")
    public void customerChecksThatThePriceOfferTableHiddenInApplicationPrintFormsOpenAccessProt() throws Throwable {
        String stepName = "'Заказчик' проверяет отсутствие таблицы 'Ценовое предложение' в печатных формах заявок " +
                "от Поставщиков 'ПОД'";
        this.logStepName(stepName);

        timer.start();

        openAccessCustomerProtocolPage.checkThatThePriceOfferTableHiddenInApplicationPrintForms();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет наличие таблицы 'Ценовое предложение' в печатных формах заявок от Поставщиков 'ПОД'$")
    public void customerChecksThatThePriceOfferTableVisibleInApplicationPrintFormsOpenAccessProt() throws Throwable {
        String stepName = "'Заказчик' проверяет наличие таблицы 'Ценовое предложение' в печатных формах заявок " +
                "от Поставщиков 'ПОД'";
        this.logStepName(stepName);

        timer.start();

        openAccessCustomerProtocolPage.checkThatThePriceOfferTableVisibleInApplicationPrintForms();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет отсутствие таблицы 'Ценовое предложение' в печатных формах заявок от Поставщиков 'ПРОЗ'$")
    public void customerChecksThatThePriceOfferTableHiddenInApplicationPrintFormsConsEvalProt() throws Throwable {
        String stepName = "'Заказчик' проверяет отсутствие таблицы 'Ценовое предложение' в печатных формах заявок " +
                "от Поставщиков 'ПРОЗ'";
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.checkThatThePriceOfferTableHiddenInApplicationPrintForms();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет наличие таблицы 'Ценовое предложение' в печатных формах заявок от Поставщиков 'ПРОЗ'$")
    public void customerChecksThatThePriceOfferTableVisibleInApplicationPrintFormsConsEvalProt() throws Throwable {
        String stepName = "'Заказчик' проверяет наличие таблицы 'Ценовое предложение' в печатных формах заявок " +
                "от Поставщиков 'ПРОЗ'";
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.checkThatThePriceOfferTableVisibleInApplicationPrintForms();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит по ссылке 'Отправить' в {int} строке столбца 'Запрос на разъяснение заявки' на странице протокола")
    public void customerClicksOnRequestForExplanationOfApplicationLinkByRowNumber(int rowNumber) {
        String stepName = String.format("'Заказчик' переходит по ссылке 'Отправить' в '%d' строке столбца " +
                "'Запрос на разъяснение заявки' на странице протокола", rowNumber);
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.clickOnRequestForExplanationOfApplicationLinkByRowNumber(rowNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что в имени {int} столбца в таблице 'Сведения протокола рассмотрения и оценки заявок' содержится строка {string}")
    public void customerChecksTableColumnsOnConsiderationRequestCustomerProtocolPage(int positionColumnInTable, String columnName) {
        String stepName =
                String.format("'Заказчик' проверяет, что в имени '%d' столбца " +
                                "в таблице 'Сведения протокола рассмотрения и оценки заявок' содержится строка '%s'",
                        positionColumnInTable, columnName);
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.checkTableColumnName(positionColumnInTable, columnName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' открывает 'Заявку' для {int} участника в таблице 'Сведения протокола рассмотрения и оценки заявок'")
    public void customerOpenApplicationPrintFormsOnConsiderationRequestCustomerProtocolPage(int printFormNumber) throws Exception {
        String stepName =
                String.format("'Заказчик' открывает 'Заявку' в '%d' строке " +
                        "в таблице 'Сведения протокола рассмотрения и оценки заявок'", printFormNumber);
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.openApplicationPrintForms(printFormNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' открывает 'Сведения об участнике закупки' для {int} участника в таблице 'Сведения протокола рассмотрения и оценки заявок'")
    public void customerOpenPurchaserApplicationPrintFormsOnConsiderationRequestCustomerProtocolPage(int printFormNumber) throws Exception {
        String stepName =
                String.format("'Заказчик' открывает 'Заявку' в '%d' строке " +
                        "в таблице 'Сведения протокола открытия доступа'", printFormNumber);
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.openPurchaserApplicationPrintForms(printFormNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет архив 'Заявка по закупке №' в разделе 'Сведения протокола рассмотрения и оценки заявок'")
    public void customerCheckZipOnConsiderationRequestCustomerProtocolPage() throws Exception {
        String zipArchiveName = "Заявки по закупке № " + config.getParameter("PurchaseNumber") + " (лот № 1) ";
        String stepName =
                String.format("'Заказчик' проверяет архив '%s' в разделе " +
                        "'Сведения протокола рассмотрения и оценки заявок'", zipArchiveName);
        this.logStepName(stepName);

        timer.start();

        ArrayList<String> expectedFileNames = new ArrayList<>();
        //--------------------------------------------------------------------------------------------------------------
        expectedFileNames.add("Заявка1_ООО Автотестеры 223/Печатная_форма_заявки.html");
        expectedFileNames.
                add("Заявка1_ООО Автотестеры 223/Печатная_форма_сведений_об_участнике_закупки.html");
        expectedFileNames.add("Заявка1_ООО Автотестеры 223/Документы заявки/1.txt");
        expectedFileNames.add("Заявка2_ООО Автотестеры 223/Печатная_форма_заявки.html");
        expectedFileNames.
                add("Заявка2_ООО Автотестеры 223/Печатная_форма_сведений_об_участнике_закупки.html");
        expectedFileNames.add("Заявка2_ООО Автотестеры 223/Документы заявки/1.txt");
        expectedFileNames.add("Список участников.xlsx");
        //--------------------------------------------------------------------------------------------------------------

        commonCustomerProtocolPage.downloadZipArchiveModifyAndCheckFileNamesInIt(zipArchiveName, expectedFileNames);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что в столбце 'Субъетк МСП' содержится {int} флажка")
    public void customerCheckCheckboxesInSMBEntityColumnOnConsiderationRequestCustomerProtocolPage(int expectedNumber) {
        String stepName =
                String.format("'Заказчик' проверяет, что в столбце 'Субъетк МСП' содержится '%d' флажка",
                        expectedNumber);
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.checkCheckboxesNumberInSMBEntityColumn(expectedNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' открывает раздел 'Оценка заявок' на странице 'Протокол рассмотрения и оценки первых частей'")
    public void customerOpenApplicationRatingBlock() throws Throwable {
        String stepName = "'Заказчик' открывает раздел 'Оценка заявок'";
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.openApplicationRatingBlock();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что таблица участников закупки в разделе 'Оценка заявок' содержит {int} записи")
    public void customerOpenApplicationRatingBlock(int rowsNumbers) throws Throwable {
        String stepName =
                String.format("'Заказчик' проверяет, что таблица участников закупки в разделе " +
                        "'Оценка заявок' содержит '%d' запис", rowsNumbers);
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.checkNumberOfRowsFromRequestListTable(rowsNumbers);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет отсутствие таблицы 'Ценовое предложение' в печатных формах заявок от Поставщиков 'ППП'$")
    public void customerChecksThatThePriceOfferTableHiddenInApplicationPrintFormsRetenderingProt() throws Throwable {
        String stepName = "'Заказчик' проверяет отсутствие таблицы 'Ценовое предложение' в печатных формах заявок " +
                "от Поставщиков 'ППП'";
        this.logStepName(stepName);

        timer.start();

        retenderingCustomerProtocolPage.checkThatThePriceOfferTableHiddenInApplicationPrintForms();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет наличие таблицы 'Ценовое предложение' в печатных формах заявок от Поставщиков 'ППП'$")
    public void customerChecksThatThePriceOfferTableVisibleInApplicationPrintFormsRetenderingProt() throws Throwable {
        String stepName = "'Заказчик' проверяет наличие таблицы 'Ценовое предложение' в печатных формах заявок " +
                "от Поставщиков 'ППП'";
        this.logStepName(stepName);

        timer.start();

        retenderingCustomerProtocolPage.checkThatThePriceOfferTableVisibleInApplicationPrintForms();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет наличие таблицы 'Ценовое предложение' в печатных формах заявок от Поставщиков 'ППИ'$")
    public void customerChecksThatThePriceOfferTableVisibleInApplicationPrintFormsSummarizingProt() throws Throwable {
        String stepName = "'Заказчик' проверяет наличие таблицы 'Ценовое предложение' в печатных формах заявок от " +
                "Поставщиков 'ППИ'";
        this.logStepName(stepName);

        timer.start();

        summarizeCustomerProtocolPage.checkThatThePriceOfferTableVisibleInApplicationPrintForms();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что в имени {int} столбца в таблице 'Сведения протокола подведения итогов' содержится строка {string}")
    public void customerChecksTableColumnsOnSummarizeCustomerProtocolPage(int positionColumnInTable, String columnName) {
        String stepName =
                String.format("'Заказчик' проверяет, что в имени '%d' столбца " +
                                "в таблице 'Сведения протокола подведения итогов' содержится строка '%s'",
                        positionColumnInTable, columnName);
        this.logStepName(stepName);

        timer.start();

        summarizeCustomerProtocolPage.checkTableColumnName(positionColumnInTable, columnName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что в строке {int} в столбце 'Лучшее ценовое предложение' содержится значение {string}")
    public void customerCheckOnSummarizeCustomerProtocolPage(int rowNumber, String expectedPrice) {
        String stepName = "'Заказчик' проверяет, что рейтинг участников составлен по столбцу " +
                "'Лучшее ценовое предложение' в разделе 'Сведения протокола подведения итогов'";
        this.logStepName(stepName);

        timer.start();

        summarizeCustomerProtocolPage.checkBestPriceOfferInRow(rowNumber, expectedPrice);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' открывает 'Заявку' для {int} участника в таблице 'Сведения протокола подведения итогов'")
    public void customerOpenApplicationPrintFormsOnSummarizeCustomerProtocolPage(int printFormNumber) throws Exception {
        String stepName =
                String.format("'Заказчик' открывает 'Заявку' в '%d' строке " +
                        "в таблице 'Сведения протокола рассмотрения и оценки заявок'", printFormNumber);
        this.logStepName(stepName);

        timer.start();

        summarizeCustomerProtocolPage.openApplicationPrintForms(printFormNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет количество строк {int} в таблице 'Сведения протокола подведения итогов'")
    public void customerChecksNumberOfRowsFromSummarizeCustomerProtocolTable(int rows) throws Throwable {
        String stepName = String.
                format("'Заказчик' проверяет количество строк '%s' в таблице 'Сведения протокола подведения итогов'"
                        , rows);
        this.logStepName(stepName);

        timer.start();

        summarizeCustomerProtocolPage.checkNumberOfRowsFromSummarizingProtocolInformationTable(rows);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что у {int} участника нет значения в столбце 'Рейтинг' в таблице 'Сведения протокола подведения итогов'")
    public void customerCheckRatingColumnInSummarizeCustomerProtocolTable(int participantNumber) {
        String stepName = String.
                format("'Заказчик' проверяет, что у '%d' участника нет значения в столбце 'Рейтинг' " +
                        "таблице 'Сведения протокола подведения итогов'", participantNumber);
        this.logStepName(stepName);

        timer.start();

        summarizeCustomerProtocolPage.checkRatingColumn(participantNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что у {int} участника нет возможности редактировать 'Итоговый бал' в таблице 'Сведения протокола подведения итогов'")
    public void customerCheckTotalScoreIsDisabledInSummarizeCustomerProtocolTable(int participantNumber) {
        String stepName = String.
                format("'Заказчик' проверяет, что у '%d' участника нет возможности редактировать 'Итоговый бал' в " +
                        "таблице 'Сведения протокола подведения итогов'", participantNumber);
        this.logStepName(stepName);

        timer.start();

        summarizeCustomerProtocolPage.checkTotalScoreIsDisabled(participantNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет наличие таблицы 'Лучший поправочный коэффициент' в печатных формах заявок от Поставщиков 'ППИ'")
    public void customerChecksThatTheCorrectionFactorTableVisibleInApplicationPrintFormsSummarizingProt() throws Throwable {
        String stepName = "'Заказчик' проверяет наличие таблицы 'Лучший поправочный коэффициент' " +
                "в печатных формах заявок от Поставщиков 'ППИ'";
        this.logStepName(stepName);

        timer.start();

        summarizeCustomerProtocolPage.checkThatTheCorrectionFactorTableVisibleInApplicationPrintForms();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет статус протокола - 'Утвержден'$")
    public void customerChecksThatProtocolStatusIsApproved() throws Throwable {
        String stepName = "'Заказчик' проверяет статус протокола - 'Утвержден'";
        this.logStepName(stepName);

        timer.start();

        // Ждём окончания загрузки страницы
        commonCustomerProtocolPage.waitForPageLoad();
        commonCustomerProtocolPage.checkPageUrl("Один из протоколов");

        // Проверяем статус протокола
        commonCustomerProtocolPage.checkProtocolStatus("Утвержден");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет наличие суммы \"([^\"]*)\" в колонке 'Лучшее ценовое предложение'$")
    public void customerChecksSumInBestOffersColumn(String bestPrice) {
        String stepName = String.format(
                "'Заказчик' проверяет наличие суммы '%s' в колонке 'Лучшее ценовое предложение'", bestPrice);
        this.logStepName(stepName);

        timer.start();

        summarizeCustomerProtocolPage.checkBestPriceOffer(bestPrice);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на кнопку 'Сформировать и прикрепить' и проверяет наличие прикрепленного файла протокола$")
    public void customerClicksOnFormAndAttachButtonAndChecksAttachedFileWithProtocol() throws Throwable {
        String stepName = "'Заказчик' нажимает на кнопку 'Сформировать и прикрепить' и " +
                "проверяет наличие прикрепленного файла протокола";
        this.logStepName(stepName);

        timer.start();

        // Нажимаем кнопку [Сформировать и прикрепить], проверяем наличие прикрепленного файла с протоколом
        commonCustomerProtocolPage.clickGenerateAndAttachButton();
        String actualText = commonCustomerProtocolPage.getAttachedProtocolLinkText("1");
        System.out.printf("[-] Ожидаемый текст ссылки: '.doc' [-] Актуальный текст ссылки: '%s'%n", actualText);
        Assert.assertTrue("Текст ссылки не совпадает", actualText.contains(".doc"));

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' удаляет все прикрепленные ранее к протоколу файлы$")
    public void customerRemovesAllAttachmentsToProtocolInEditMode() throws Throwable {
        String stepName = "'Заказчик' удаляет все прикрепленные ранее к протоколу файлы";
        this.logStepName(stepName);

        timer.start();

        commonCustomerProtocolPage.removeAllAttachmentsToProtocolInEditMode();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на кнопку 'Изменить опубликованный протокол' с причиной отмены протокола \"([^\"]*)\"$")
    public void customerClicksOnChangePublishedProtocolButton(String reason) throws Throwable {
        String stepName = String.format(
                "'Заказчик' нажимает на кнопку 'Изменить опубликованный протокол' с причиной отмены протокола '%s'",
                reason);
        this.logStepName(stepName);

        timer.start();

        commonCustomerProtocolPage.clickChangePublishedProtocolButton(reason);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет наличие дубликатов в списке шаблонов протоколов$")
    public void customerChecksDuplicatesInTemplatesList() throws Throwable {
        String stepName = "'Заказчик' проверяет наличие дубликатов в списке шаблонов протоколов";
        this.logStepName(stepName);

        timer.start();

        // Проверяем наличие дубликатов в раскрывающемся списке шаблонов протоколов
        commonCustomerProtocolPage.checkDuplicatesInTemplatesList();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на кнопку 'Подписать и опубликовать'$")
    public void customerClicksOnSignAndPublishButton() throws Throwable {
        String stepName = "'Заказчик' нажимает на кнопку 'Подписать и опубликовать'";
        this.logStepName(stepName);

        timer.start();

        commonCustomerProtocolPage.clickOnButton("Подписать и опубликовать");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на кнопку 'Отправить в ЕИС'$")
    public void customerClicksOnPublishOnOosButton() throws Throwable {
        String stepName = "'Заказчик' нажимает на кнопку 'Отправить в ЕИС'";
        this.logStepName(stepName);

        timer.start();

        commonCustomerProtocolPage.clickOnButton("Отправить в ЕИС");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' отправляет протокол в ЕИС, тип протокола {string} URL типа протокола {string}")
    public void customerSendsProtocolToEISWithProtocolTypeChecking(String protocolType, String urlKey)
            throws Throwable {
        String stepName = String.format(
                "'Заказчик' отправляет протокол в ЕИС, тип протокола '%s' URL типа протокола '%s'",
                protocolType, config.getConfigParameter(urlKey));
        this.logStepName(stepName);

        timer.start();

        publishProtocolOnOosDialog.ifDialogOpened().
                selectProtocolType(protocolType, urlKey).
                clickOnButtonByName("Отправить в ЕИС");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет появление окна с текстом ошибки {string}")
    public void customerChecksModalDialogWindowWithSpecifiedErrorMessage(String errorMessage) throws Throwable {
        String stepName = String.format("'Заказчик' проверяет появление окна с текстом ошибки '%s'", errorMessage);
        this.logStepName(stepName);

        timer.start();

        errorDialog.
                ifDialogOpened().
                checkDialogText(errorMessage);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' закрывает диалоговое окно 'Ошибка'")
    public void customerClicksOnOkButtonInModalDialogWindowWithSpecifiedErrorMessage() throws Throwable {
        String stepName = "'Заказчик' закрывает диалоговое окно 'Ошибка'";
        this.logStepName(stepName);

        timer.start();

        errorDialog.clickOnOkButton();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет в базе данных площадки создание сообщения для ЕИС об отправке протокола")
    public void customerChecksPostMessageForEISInSiteDatabaseUsingProtocolNumberForSearch() {
        String stepName = "'Заказчик' проверяет в базе данных площадки создание сообщения для ЕИС об отправке протокола";
        this.logStepName(stepName);

        timer.start();

        String sql = "SELECT TOP 1 [PostMessage] FROM [OutgoingSaga] WHERE [ExternalObjectId] = %s";
        String protocolNumber = config.getParameter("LastProtocolNumber");
        List<String> fieldsToPrint = Arrays.asList("PostMessage");

        DbOperations dbOperations = new DbOperations();
        dbOperations.
                connectToDatabase(config.getConfigParameter("SqlDatabaseName2")).
                executeSelectQuery(String.format(sql, protocolNumber), 1, fieldsToPrint).
                closeConnectionToDatabase();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет лучшую цену \"([^\"]*)\" в колонке 'Лучшее ценовое предложение'$")
    public void customerChecksBestOfferInInFirstLot(String bestOffer) {
        String stepName =
                String.format("'Заказчик' проверяет лучшую цену '%s' в колонке 'Лучшее ценовое предложение'", bestOffer);
        this.logStepName(stepName);

        timer.start();

        auctionCustomerProtocolPage.checkBestOfferInFirstLot(bestOffer);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет лучшую цену \"([^\"]*)\" в колонке 'Лучшее ценовое предложение' для \"([^\"]*)\" лота$")
    public void customerChecksBestOfferInLotByLotNumber(String bestOffer, String lotNumber) {
        String stepName = String.format(
                "'Заказчик' проверяет лучшую цену '%s' в колонке 'Лучшее ценовое предложение' для '%s' лота",
                bestOffer, lotNumber);
        this.logStepName(stepName);

        timer.start();

        auctionCustomerProtocolPage.checkBestOfferInLotByLotNumber(bestOffer, lotNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет лучшую цену {string} для {string} лота в колонке {string}")
    public void customerChecksBestOfferInLotByLotNumber(String bestOffer, String lotNumber, String columnName) {
        String stepName = String.format(
                "'Заказчик' проверяет лучшую цену '%s' для '%s' лота в колонке '%s'",
                bestOffer, lotNumber, columnName);
        this.logStepName(stepName);

        timer.start();

        auctionCustomerProtocolPage.checkBestOfferInLotByLotNumber(bestOffer, lotNumber, columnName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' публикует протокол проведения электронного аукциона$")
    public void customerPublishesAnElectronicAuctionProtocol() throws Throwable {
        String stepName = "'Заказчик' публикует протокол проведения электронного аукциона";
        this.logStepName(stepName);

        timer.start();

        auctionCustomerProtocolPage.clickOnButton("Подписать и опубликовать");
        auctionCustomerProtocolPage.clickButtonInPopupWindow("Продолжить-Предупреждение");
        auctionCustomerProtocolPage.clickButtonInPopupWindow("ОК-Информация");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' публикует протокол проведения аукциона$")
    public void customerPublishesAnAuctionProtocol() throws Throwable {
        String stepName = "'Заказчик' публикует протокол проведения аукциона";
        this.logStepName(stepName);

        timer.start();

        auctionCustomerProtocolPage.clickGenerateAndAttachButton();
        String actualText = auctionCustomerProtocolPage.getAttachedProtocolLinkText();
        System.out.printf("[-] Ожидаемый текст ссылки: '.doc' [-] Актуальный текст ссылки: '%s'%n", actualText);
        Assert.assertTrue("Текст ссылки не совпадает", actualText.contains(".doc"));
        commonCustomerProtocolPage.checkDuplicatesInTemplatesList();
        auctionCustomerProtocolPage.clickOnButton("Подписать и опубликовать");
        auctionCustomerProtocolPage.clickButtonInPopupWindow("Продолжить-Предупреждение");
        auctionCustomerProtocolPage.clickButtonInPopupWindow("ОК-Информация");

        timer.printStepTotalTime(stepName);
    }

    @Then("Протокол перешел в статус {string}")
    public void customerChecksProtocolStatus(String status) throws Throwable {
        String stepName = String.format("Протокол перешел в статус '%s'", status);
        this.logStepName(stepName);

        timer.start();

        commonCustomerProtocolPage.checkProtocolStatus(status);

        timer.printStepTotalTime(stepName);
    }

    @Then("'Заказчик' сохраняет в параметрах теста номер протокола")
    public void customerSavesProtocolNumberInTestParemeters() {
        String stepName = "'Заказчик' сохраняет в параметрах теста номер протокола";
        this.logStepName(stepName);

        timer.start();

        config.setParameter("LastProtocolNumber", url().split("protocolId=")[1]);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет статус сведений о протоколе в журнале взаимодействия с ЕИС: {string}")
    public void customerChecksProtocolStatusInOosInteractionLog(String status) throws Throwable {
        String stepName = String.format(
                "'Заказчик' проверяет статус сведений о протоколе в журнале взаимодействия с ЕИС: '%s'", status);
        this.logStepName(stepName);

        timer.start();

        oosMessageQueuePage.
                setPurchaseNumber(config.getParameter("LastProtocolNumber")).
                setMessageType("Протокол закупки").
                clickOnSearchButton().
                checkStatusInSearchResults(status);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет возможность скачать заявки из формы 'Протокол открытия доступа конкурса'$")
    public void customerChecksPossibilityToDownloadRequestsFromOpenAccessProtocolPage() throws Throwable {
        String stepName = "'Заказчик' проверяет возможность скачать заявки из формы 'Протокол открытия доступа конкурса'";
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.checkPossibilityToDownloadRequestsFromOpenAccessProtocolPage();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет возможность скачать заявки из формы 'Протокол рассмотрения первых частей заявок аукциона'$")
    public void customerChecksPossibilityToDownloadRequestsFromFirstPartsConsiderationProtocolPage() throws Throwable {
        String stepName = "'Заказчик' проверяет возможность скачать заявки из формы " +
                "'Протокол рассмотрения первых частей заявок аукциона'";
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.checkPossibilityToDownloadRequestsFromFirstPartsConsiderationProtocolPage();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет возможность скачать заявки из формы 'Протокол рассмотрения заявок' для \"([^\"]*)\" лотов$")
    public void customerChecksPossibilityToDownloadRequestsFromConsiderationProtocolPage(String lots) throws Throwable {
        String stepName = String.format(
                "'Заказчик' проверяет возможность скачать заявки из формы 'Протокол рассмотрения заявок' для '%s' лотов",
                lots);
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.checkPossibilityToDownloadRequestsFromConsiderationProtocolPage(lots);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' переходит к просмотру раздела протокола \"([^\"]*)\"$")
    public void customerGoesToViewProtocolPagePartition(String partitionName) throws Throwable {
        String stepName = String.format("'Заказчик' переходит к просмотру раздела протокола '%s'", partitionName);
        this.logStepName(stepName);

        timer.start();

        commonCustomerProtocolPage.goToPartition(partitionName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' МСП переходит к просмотру раздела протокола \"([^\"]*)\"$")
    public void customerSMBGoesToViewProtocolPagePartition(String partitionName) throws Throwable {
        String stepName = String.format("'Заказчик' МСП переходит к просмотру раздела протокола '%s'", partitionName);
        this.logStepName(stepName);

        timer.start();

        smbTenderDiscussionOfFunctionalCharacteristicsProtocolPage.goToPartition(partitionName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' переходит к просмотру раздела \"([^\"]*)\" в протоколе отказа от заключения договора$")
    public void customerGoesToViewRefuseContractProtocolPagePartition(String partitionName) throws Throwable {
        String stepName = String.format("'Заказчик' переходит к просмотру раздела протокола '%s'", partitionName);
        this.logStepName(stepName);

        timer.start();

        refuseContractProtocolPage.goToPartition(partitionName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет статус \"([^\"]*)\" в протоколе отказа от заключения договора$")
    public void customerChecksRefuseContractProtocolStatus(String expectedStatus) throws Throwable {
        String stepName = String.format("'Заказчик' проверяет статус '%s' в протоколе отказа от заключения договора",
                expectedStatus);
        this.logStepName(stepName);

        timer.start();

        refuseContractProtocolPage.checkProtocolStatus(expectedStatus);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' заполняет поле \"([^\"]*)\" значением \"([^\"]*)\" в протоколе отказа от заключения договора$")
    public void customerFillsFieldByNameInRefuseContractProtocol(String field, String value) throws Throwable {
        String stepName = String.format(
                "'Заказчик' заполняет поле '%s' значением '%s' в протоколе отказа от заключения договора", field, value);
        this.logStepName(stepName);

        timer.start();

        refuseContractProtocolPage.setFieldValue(field, value);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' МСП заполняет поле протокола 'Принятое решение о необходимости уточнения характеристик' значением 'Требуется уточнение функциональных характеристик'$")
    public void customerSMBFillsDecisionRequiresClarificationOfFunctionalCharacteristics() throws Throwable {
        String stepName = "'Заказчик' МСП заполняет поле протокола " +
                "'Принятое решение о необходимости уточнения характеристик' значением " +
                "'Требуется уточнение функциональных характеристик'";
        this.logStepName(stepName);

        timer.start();

        smbTenderDiscussionOfFunctionalCharacteristicsProtocolPage.
                setDecisionRequiresClarificationOfFunctionalCharacteristics();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' МСП заполняет поле протокола 'Принятое решение о необходимости уточнения характеристик' значением 'Не требуется уточнение функциональных характеристик'$")
    public void customerSMBFillsDecisionNotRequiresClarificationOfFunctionalCharacteristics() throws Throwable {
        String stepName = "'Заказчик' МСП заполняет поле протокола " +
                "'Принятое решение о необходимости уточнения характеристик' значением " +
                "'Не требуется уточнение функциональных характеристик'";
        this.logStepName(stepName);

        timer.start();

        smbTenderDiscussionOfFunctionalCharacteristicsProtocolPage.
                setDecisionNotRequiresClarificationOfFunctionalCharacteristics();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' выбирает причину отказа в протоколе отказа от заключения договора$")
    public void customerSelectsTheCancelReasonInRefuseContractProtocol() throws Throwable {
        String stepName = "'Заказчик' выбирает причину отказа в протоколе отказа от заключения договора";
        this.logStepName(stepName);

        timer.start();

        refuseContractProtocolPage.selectCancelReason();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' прикрепляет к протоколу отказа от заключения договора документ с актом об отказе$")
    public void customerUploadsAnActOfRefusalToSignTheAgreement() throws Throwable {
        String stepName = "'Заказчик' прикрепляет к протоколу отказа от заключения договора документ с актом об отказе";
        this.logStepName(stepName);

        timer.start();

        refuseContractProtocolPage.uploadAnActOfRefusalToSignTheAgreement();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на кнопку \"([^\"]*)\" в протоколе отказа от заключения договора$")
    public void customerClicksOnButtonByName(String buttonName) throws Throwable {
        String stepName = String.format("'Заказчик' нажимает на кнопку '%s' в протоколе отказа от заключения договора",
                buttonName);
        this.logStepName(stepName);

        timer.start();

        refuseContractProtocolPage.clickOnButtonByName(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' переходит к просмотру раздела протокола 'Сведения протокола рассмотрения заявок'$")
    public void customerMovesToConsiderationRequestInfoHeader() throws Throwable {
        String stepName = "'Заказчик' переходит к просмотру раздела протокола 'Сведения протокола рассмотрения заявок'";
        this.logStepName(stepName);

        timer.start();

        considerationRequestCustomerProtocolPage.moveToConsiderationRequestInfoHeader();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет поле протокола \"([^\"]*)\" на текст \"([^\"]*)\"$")
    public void customerChecksProtocolPageFieldContentForText(String fieldName, String value) {
        String stepName = String.format("'Заказчик' проверяет поле протокола '%s' на текст '%s'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        commonCustomerProtocolPage.verifyFieldContent(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет столбец \"([^\"]*)\" строка \"([^\"]*)\" в таблице 'Комиссия' на текст \"([^\"]*)\"$")
    public void customerChecksProtocolPageColumnByNameInLineByNumberFromCommissionTableForText(
            String columnName, String lineNumber, String cellValue) {
        String stepName = String.format(
                "'Заказчик' проверяет столбец '%s' строка '%s' в таблице 'Комиссия' на текст '%s'",
                columnName, lineNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        commonCustomerProtocolPage.verifyCellByNameInLineByNumberFromCommissionTableForText(columnName, lineNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет столбец \"([^\"]*)\" строка \"([^\"]*)\" в таблице 'Попозиционное сравнение заявок' на текст \"([^\"]*)\"$")
    public void customerChecksProtocolPageColumnByNameInLineByNumberFromPositionalComparisonTable4ForText(
            String columnName, String lineNumber, String cellValue) {
        String stepName = String.format(
                "'Заказчик' проверяет столбец '%s' строка '%s' в таблице 'Попозиционное сравнение заявок' на текст '%s'",
                columnName, lineNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        commonCustomerProtocolPage.
                verifyCellByNameInLineByNumberFromPositionalComparisonTable4ForText(columnName, lineNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет столбец \"([^\"]*)\" строка \"([^\"]*)\" таблица 'Попозиционное сравнение заявок' текст \"([^\"]*)\"$")
    public void customerChecksProtocolPageColumnByNameInLineByNumberFromPositionalComparisonTable5ForText(
            String columnName, String lineNumber, String cellValue) {
        String stepName = String.format(
                "'Заказчик' проверяет столбец '%s' строка '%s' таблица 'Попозиционное сравнение заявок' текст '%s'",
                columnName, lineNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        commonCustomerProtocolPage.
                verifyCellByNameInLineByNumberFromPositionalComparisonTable5ForText(columnName, lineNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' указывает в протоколе подведения итогов строка {string} таблица 'Попозиционное сравнение заявок' другого победителя")
    public void customerSelectsAnotherWinnerInLineByNumberFromPositionalComparisonTable5(String lineNumber)
            throws Throwable {
        String stepName = String.format("'Заказчик' указывает в протоколе подведения итогов строка '%s' таблица " +
                "'Попозиционное сравнение заявок' другого победителя", lineNumber);
        this.logStepName(stepName);

        timer.start();

        commonCustomerProtocolPage.selectAnotherWinnerInLineByNumberFromPositionalComparisonTable5(lineNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на кнопку 'Отклонить' в нижней части страницы протокола$")
    public void customerClicksOnRejectProtocolButton() throws Throwable {
        String stepName = "'Заказчик' нажимает на кнопку 'Отклонить' в нижней части страницы протокола";
        this.logStepName(stepName);

        timer.start();

        commonCustomerProtocolPage.clickOnButton("Отклонить");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на кнопку 'Утвердить' в нижней части страницы протокола$")
    public void customerClicksOnApproveProtocolButton() throws Throwable {
        String stepName = "'Заказчик' нажимает на кнопку 'Утвердить' в нижней части страницы протокола";
        this.logStepName(stepName);

        timer.start();

        commonCustomerProtocolPage.clickOnButton("Утвердить");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на кнопку 'Открыть форму протокола' страницы с сообщением 'Внимание.'$")
    public void customerClicksOnOpenProtocolFormButton() throws Throwable {
        String stepName = "'Заказчик' нажимает на кнопку 'Открыть форму протокола' страницы с сообщением 'Внимание.'";
        this.logStepName(stepName);

        timer.start();

        commonCustomerProtocolPage.clickOnButton("Открыть форму протокола");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет сообщение 'Публикация протокола. После создания черновика последнего протокола Вам будут открыты ценовые предложения участников...'")
    public void customerChecksPublishLastProtocolDraftMessage() {
        String stepName = "'Заказчик' проверяет сообщение 'Публикация протокола. После создания черновика последнего " +
                "протокола Вам будут открыты ценовые предложения участников...'";
        this.logStepName(stepName);

        timer.start();

        if (commonCustomerProtocolPage.fieldOrTextIsDisplayed("Сообщение Публикация протокола."))
            commonCustomerProtocolPage.verifyFieldContent("Сообщение Публикация протокола.",
                    "После создания черновика последнего протокола Вам будут открыты ценовые предложения " +
                            "участников, и");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет сообщение 'Публикация протокола. После создания данного протокола будет невозможно внести изменения...'")
    public void customerChecksPublishProtocolMessage() {
        String stepName = "'Заказчик' проверяет сообщение 'Публикация протокола. После создания данного протокола " +
                "будет невозможно внести изменения...'";
        this.logStepName(stepName);

        timer.start();

        if (commonCustomerProtocolPage.fieldOrTextIsDisplayed("Сообщение Публикация протокола."))
            commonCustomerProtocolPage.verifyFieldContent("Сообщение Публикация протокола.",
                    "После создания данного протокола будет невозможно внести изменения");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет сообщение 'Публикация протокола. После создания данного протокола будут открыты сведения...'")
    public void customerChecksPublishProtocolUnitedMessage() {
        String stepName = "'Заказчик' проверяет сообщение 'Публикация протокола. После создания данного протокола " +
                "будут открыты сведения...'";
        this.logStepName(stepName);

        timer.start();

        if (commonCustomerProtocolPage.fieldOrTextIsDisplayed("Сообщение Публикация протокола."))
            commonCustomerProtocolPage.verifyFieldContent("Сообщение Публикация протокола.",
                    "После создания данного протокола будут открыты сведения об участниках и будет невозможно");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет сообщение 'Внимание. После создания данного протокола будут открыты ценовые предложения...'")
    public void customerChecksAttentionMessage() {
        String stepName = "'Заказчик' проверяет сообщение 'Внимание. После создания данного протокола будут " +
                "открыты ценовые предложения...'";
        this.logStepName(stepName);

        timer.start();

        commonCustomerProtocolPage.verifyFieldContent("Сообщение Внимание.",
                "После создания данного протокола будут открыты ценовые предложения участников и станет " +
                        "недоступна возможность проведения переторжки.");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' нажимает на кнопку 'Продолжить' страницы с сообщением 'Публикация протокола.'$")
    public void customerClicksOnContinueToOpenProtocolFormButton() throws Throwable {
        String stepName = "'Заказчик' нажимает на кнопку 'Продолжить' страницы с сообщением 'Публикация протокола.'";
        this.logStepName(stepName);

        timer.start();

        if (commonCustomerProtocolPage.fieldOrTextIsDisplayed("Сообщение Публикация протокола."))
            commonCustomerProtocolPage.clickOnButton("Продолжить");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' МСП заполняет \"([^\"]*)\" строку в колонке 'Итоговый балл' таблицы 'Сведения протокола подведения итогов' значением \"([^\"]*)\"$")
    public void customerSMBFillsRowByNumberInTotalScoreColumnInSummarizeProtocol(String rowNumber, String cellValue)
            throws Throwable {
        String stepName = String.format("'Заказчик' МСП заполняет '%s' строку в колонке 'Итоговый балл' таблицы " +
                "'Сведения протокола подведения итогов' значением '%s'", rowNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        summarizeCustomerProtocolPage.fillRowByNumberInTotalScoreColumnInSummarizeProtocol(rowNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит к разделу {string} на странице 'Уведомление об итогах рассмотрения заявок предварительного отбора'")
    public void customerGoesToPartitionOnPrequalificationConsiderationProtocolPage(String partitionName) throws Throwable {
        String stepName = String.format("'Заказчик' переходит к разделу [%s] на странице " +
                "'Уведомление об итогах рассмотрения заявок предварительного отбора'", partitionName);
        this.logStepName(stepName);

        timer.start();

        prequalificationConsiderationProtocolPage.goToPartition(partitionName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле {string} в {string} со смещением {string} от времени публикации на странице 'Уведомление об итогах рассмотрения заявок предварительного отбора'")
    public void customerFillsDateAndTimeFieldOnPrequalificationConsiderationProtocolPage(String fieldName, String shiftType, String amountAsString) throws Throwable {
        String stepName = String.format(
                "'Заказчик' заполняет поле '%s' в  '%s' со смещением '%s' от времени публикации" +
                        " на странице 'Уведомление об итогах рассмотрения заявок предварительного отбора'",
                fieldName, shiftType, amountAsString);
        this.logStepName(stepName);

        timer.start();

        prequalificationConsiderationProtocolPage.setFieldClearClickAndSendKeys(fieldName,
                timer.getDateTimeShift(Timer.getPublishNoticeDate(), shiftType, amountAsString));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' прикрепляет файл уведомления на странице 'Уведомление об итогах рассмотрения заявок предварительного отбора'")
    public void customerUploadsFileIntoPartitionOfTheNotificationAboutResultsConsiderationOfPreselection() throws Exception {
        String stepName = "'Заказчик' прикрепляет файл уведомления на странице " +
                "'Уведомление об итогах рассмотрения заявок предварительного отбора'";
        this.logStepName(stepName);

        timer.start();

        prequalificationConsiderationProtocolPage.
                uploadFileIntoPartitionOfTheNotificationAboutResultsConsiderationOfPreselection(
                        config.getAbsolutePathToFoundationDoc());
        prequalificationConsiderationProtocolPage.
                checkLinkToDownloadFileFromPartitionOfTheNotificationAboutResultsConsiderationOfPreselection(
                        config.getConfigParameter("FoundationDoc").split("\\.")[0]);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что таблица 'Сведения уведомления об итогах рассмотрения заявок' для раздела 'Предмет предварительного отбора' под номером {string} содержит {int} заявку")
    public void customerChecksNumberOfRowsFromResultsOfConsiderationApplicationTable(String preselectionObjectNumber, int numberOfRows) {
        String stepName =
                String.format("'Заказчик' проверяет, что таблица 'Сведения уведомления об итогах рассмотрения заявок' " +
                                "для раздела 'Предмет предварительного отбора' под номером [%s] содержит [%d] заявку",
                        preselectionObjectNumber, numberOfRows);
        this.logStepName(stepName);

        timer.start();

        prequalificationConsiderationProtocolPage.
                checkNumberOfRowsFromResultsOfConsiderationApplicationTable(preselectionObjectNumber, numberOfRows);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' в разделе 'Предмет предварительного отбора' под номером {string} добавляет в реестр {int} заявку в таблице 'Сведения уведомления об итогах рассмотрения заявок'")
    public void customerIncreasesRequestFromResultsOfConsiderationApplicationTable(String preselectionObjectNumber, int rowNumber) throws Exception {
        String stepName =
                String.format("'Заказчик' в разделе 'Предмет предварительного отбора' под номером '%s' добавляет " +
                                "в реестр '%d' заявку в таблице 'Сведения уведомления об итогах рассмотрения заявок'",
                        preselectionObjectNumber, rowNumber);
        this.logStepName(stepName);

        timer.start();

        prequalificationConsiderationProtocolPage.
                includeRequestFromResultsOfConsiderationApplicationTable(preselectionObjectNumber, rowNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' в разделе 'Предмет предварительного отбора' под номером {string} отклоняет {int} заявку в таблице 'Сведения уведомления об итогах рассмотрения заявок'")
    public void customerRejectsRequestFromResultsOfConsiderationApplicationTable(String preselectionObjectNumber, int rowNumber) throws Exception {
        String stepName =
                String.format("'Заказчик' в разделе 'Предмет предварительного отбора' под номером '%s' отклоняет " +
                                "'%d' заявку в таблице 'Сведения уведомления об итогах рассмотрения заявок'",
                        preselectionObjectNumber, rowNumber);
        this.logStepName(stepName);

        timer.start();

        prequalificationConsiderationProtocolPage.
                rejectRequestFromResultsOfConsiderationApplicationTable(preselectionObjectNumber, rowNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' в разделе 'Предмет предварительного отбора' под номером {string} нажимает на кнопку 'Допустить всех'")
    public void customerClicksOnAllowAllButtonOnPrequalificationConsiderationProtocolPage(String preselectionObjectNumber) throws Exception {
        String stepName = String.format(
                "'Заказчик' в разделе 'Предмет предварительного отбора' под номером '%s' нажимает на кнопку 'Допустить всех'",
                preselectionObjectNumber);
        this.logStepName(stepName);

        timer.start();

        prequalificationConsiderationProtocolPage.clickOnAllAllowButton(preselectionObjectNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на ссылку в поле {string} на странице 'Уведомление об итогах рассмотрения заявок предварительного отбора'")
    public void customerClicksOnFieldOnPrequalificationConsiderationProtocolPage(String fieldName) throws Exception {
        String stepName = String.format("'Заказчик' нажимает на ссылку в поле '%s' на странице " +
                "'Уведомление об итогах рассмотрения заявок предварительного отбора'", fieldName);
        this.logStepName(stepName);

        timer.start();

        prequalificationConsiderationProtocolPage.clickOnField(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на ссылку в поле {string} на странице протокола")
    public void customerClicksOnFieldOnProtocolPage(String fieldName) throws Exception {
        String stepName = String.format("'Заказчик' нажимает на ссылку в поле '%s' на странице протокола", fieldName);
        this.logStepName(stepName);

        timer.start();

        commonCustomerProtocolPage.clickOnField(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' переходит к просмотру раздела {string} на странице 'Уведомление об итогах рассмотрения заявок предварительного отбора'")
    public void customerGoesToViewPartitionOnPrequalificationConsiderationProtocolPage(String partitionName)
            throws Exception {
        String stepName = String.format("'Заказчик' переходит к просмотру раздела '%s' на странице " +
                "'Уведомление об итогах рассмотрения заявок предварительного отбора'", partitionName);
        this.logStepName(stepName);

        timer.start();

        prequalificationConsiderationProtocolPage.goToPartition(partitionName);

        timer.printStepTotalTime(stepName);
    }

    // region Методы класса, содержащие формирование переторжки и проверку дат переторжки

    @And("^'Заказчик' допускает обе заявки и публикует протокол рассмотрения заявок конкурса$")
    public void customerAllowsBothAndPublishesTenderConsiderationRequest() throws Throwable {
        String stepName = "'Заказчик' допускает обе заявки и публикует протокол рассмотрения заявок конкурса";
        this.logStepName(stepName);

        timer.start();

        // нажимаем на кнопку [Допустить всех]
        considerationRequestCustomerProtocolPage.clickAllowAllButton();

        //--------------------------------------------------------------------------------------------------------------
        // устанавливаем флажок [Провести переторжку]
        //--------------------------------------------------------------------------------------------------------------
        considerationRequestCustomerProtocolPage.setRetraidingEnabledCheckbox();

        // region Диалоговое окно [Включение возможности переторжки]

        retraidingChangeDialog.ifDialogOpened().
                setRetradingTypeTo("Переключатель Очная переторжка").
                setFieldClearClickAndSendKeys("Поле Дата и время начала переторжки",
                        timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MONTHS", "4")).
                verifyCheckBoxSelected("Флаг Автоматическое продление переторжки после подачи ценового предложения").
                setNumericTextBox("Поле Время продления, мин", "10").
                setNumericTextBox("Поле Максимальная продолжительность переторжки, мин", "20").
                setCheckBoxValue("Флаг Переторжка с шагом").
                verifyCheckBoxSelected("Флаг Показывать ценовые предложения, поданные в заявках").
                verifyCheckBoxSelected("Флаг Показывать наименования участников").
                verifyCheckBoxSelected("Флаг Возможно прикрепление документов к ценовым предложениям").
                retradingChangeConfirm();

        // endregion

        // нажимаем на кнопку [Сформировать и прикрепить]
        considerationRequestCustomerProtocolPage.clickGenerateAndAttachButton();
        String actualText = considerationRequestCustomerProtocolPage.getAttachedProtocolLinkText();
        System.out.printf("[-] Ожидаемый текст ссылки: '.doc' [-] Актуальный текст ссылки: '%s'%n", actualText);
        Assert.assertTrue("Текст ссылки не совпадает", actualText.contains(".doc"));
        commonCustomerProtocolPage.checkDuplicatesInTemplatesList();

        // нажимаем на кнопку [Подписать и опубликовать]
        considerationRequestCustomerProtocolPage.clickOnButton("Подписать и опубликовать");

        // окно [Предупреждение] нажимаем на кнопку [Продолжить]
        considerationRequestCustomerProtocolPage.clickButtonInPopupWindow("Продолжить-Предупреждение");

        // окно [Информация] нажимаем на кнопку [ОК]
        considerationRequestCustomerProtocolPage.clickButtonInPopupWindow("ОК-Информация");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' допускает обе заявки и публикует протокол рассмотрения заявок конкурса на бою$")
    public void customerAllowsBothAndPublishesTenderOnProductionConsiderationRequest() throws Throwable {
        String stepName = "'Заказчик' допускает обе заявки и публикует протокол рассмотрения заявок конкурса на бою";
        this.logStepName(stepName);

        timer.start();

        // нажимаем на кнопку [Допустить всех]
        considerationRequestCustomerProtocolPage.clickAllowAllButton();

        //--------------------------------------------------------------------------------------------------------------
        // устанавливаем флажок [Провести переторжку]
        //--------------------------------------------------------------------------------------------------------------
        considerationRequestCustomerProtocolPage.setRetraidingEnabledCheckbox();

        String startShift = config.getConfigParameter("OnProdBiddingStartShift");

        // region Диалоговое окно [Включение возможности переторжки]

        retraidingChangeDialog.ifDialogOpened().
                setRetradingTypeTo("Переключатель Очная переторжка").
                setFieldClearClickAndSendKeys("Поле Дата и время начала переторжки",
                        timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MINUTES", startShift)).
                verifyCheckBoxSelected("Флаг Автоматическое продление переторжки после подачи ценового предложения").
                setNumericTextBox("Поле Время продления, мин", "10").
                setNumericTextBox("Поле Максимальная продолжительность переторжки, мин", "20").
                setCheckBoxValue("Флаг Переторжка с шагом").
                verifyCheckBoxSelected("Флаг Показывать ценовые предложения, поданные в заявках").
                verifyCheckBoxSelected("Флаг Показывать наименования участников").
                verifyCheckBoxSelected("Флаг Возможно прикрепление документов к ценовым предложениям").
                retradingChangeConfirm();

        // endregion

        // нажимаем на кнопку [Сформировать и прикрепить]
        considerationRequestCustomerProtocolPage.clickGenerateAndAttachButton();
        String actualText = considerationRequestCustomerProtocolPage.getAttachedProtocolLinkText();
        System.out.printf("[-] Ожидаемый текст ссылки: '.doc' [-] Актуальный текст ссылки: '%s'%n", actualText);
        Assert.assertTrue("Текст ссылки не совпадает", actualText.contains(".doc"));
        commonCustomerProtocolPage.checkDuplicatesInTemplatesList();

        // нажимаем на кнопку [Подписать и опубликовать]
        considerationRequestCustomerProtocolPage.clickOnButton("Подписать и опубликовать");

        // окно [Предупреждение] нажимаем на кнопку [Продолжить]
        considerationRequestCustomerProtocolPage.clickButtonInPopupWindow("Продолжить-Предупреждение");

        // окно [Информация] нажимаем на кнопку [ОК]
        considerationRequestCustomerProtocolPage.clickButtonInPopupWindow("ОК-Информация");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' добавляет к протоколу рассмотрения заявок запроса цен переторжку$")
    public void customerAddsRetenderingToRequestForQuotationsApplicationsReviewProtocol() throws Throwable {
        String stepName = "'Заказчик' добавляет к протоколу рассмотрения заявок запроса цен переторжку";
        this.logStepName(stepName);

        timer.start();

        // Вычисляем даты начала и окончания переторжки
        String startDate = timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MONTHS", "4");
        String endDate = timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MONTHS", "5");

        // устанавливаем флажок [Провести переторжку]
        considerationRequestCustomerProtocolPage.setRetraidingEnabledCheckbox();

        // Занесём эти даты в параметры теста чтобы иметь возможность потом проверять их в различных протоколах
        config.setParameter("RetraidingStartDateFromApplicationsReviewProtocol", startDate);
        config.setParameter("RetraidingEndDateFromApplicationsReviewProtocol", endDate);

        // region Диалоговое окно [Включение возможности переторжки]

        retraidingChangeDialog.ifDialogOpened().
                setRetradingTypeTo("Переключатель Очная переторжка").
                setFieldClearClickAndSendKeys("Поле Дата и время начала переторжки", startDate).
                setCheckBoxValue("Флаг Автоматическое продление переторжки после подачи ценового предложения").
                setFieldClearClickAndSendKeys("Поле Дата и время окончания переторжки", endDate).
                setCheckBoxValue("Флаг Переторжка с шагом").
                verifyCheckBoxSelected("Флаг Показывать ценовые предложения, поданные в заявках").
                verifyCheckBoxSelected("Флаг Показывать наименования участников").
                verifyCheckBoxSelected("Флаг Возможно прикрепление документов к ценовым предложениям").
                retradingChangeConfirm();

        // endregion

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' добавляет к протоколу рассмотрения заявок переторжку$")
    public void customerAddsRetenderingToTenderApplicationsReviewProtocol() throws Throwable {
        String stepName = "'Заказчик' добавляет к протоколу рассмотрения заявок переторжку";
        this.logStepName(stepName);

        timer.start();

        // Вычисляем даты начала и окончания переторжки
        String startDate = timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MONTHS", "3");
        String endDate = timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MONTHS", "4");

        // Занесём эти даты в параметры теста чтобы иметь возможность потом проверять их в различных протоколах
        config.setParameter("RetraidingStartDateFromApplicationsReviewProtocol", startDate);
        config.setParameter("RetraidingEndDateFromApplicationsReviewProtocol", endDate);

        // устанавливаем флажок [Провести переторжку]
        considerationRequestCustomerProtocolPage.setRetraidingEnabledCheckbox();

        // region Диалоговое окно [Включение возможности переторжки]

        retraidingChangeDialog.ifDialogOpened().
                setRetradingTypeTo("Переключатель Очная переторжка").
                setFieldClearClickAndSendKeys("Поле Дата и время начала переторжки", startDate).
                setFieldClearClickAndSendKeys("Поле Дата и время окончания переторжки", endDate).
                verifyCheckBoxNotSelected("Флаг Показывать ценовые предложения, поданные в заявках").
                verifyCheckBoxSelected("Флаг Показывать наименования участников").
                verifyCheckBoxNotSelected("Флаг Скрывать лучшее ценовое предложение").
                retradingChangeConfirm();

        // endregion

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' добавляет к протоколу рассмотрения заявок переторжку с указанием флага 'Скрывать лучшее ценовое предложение'")
    public void customerAddsRetenderingToApplicationsReviewProtocolAndSetCheckBoxHideBestPriceOffer() throws Throwable {
        String stepName = "'Заказчик' добавляет к протоколу рассмотрения заявок переторжку с указанием флага " +
                "'Скрывать лучшее ценовое предложение'";
        this.logStepName(stepName);

        timer.start();

        // Вычисляем даты начала и окончания переторжки
        String startDate = timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MONTHS", "3");
        String endDate = timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MONTHS", "4");

        // Занесём эти даты в параметры теста чтобы иметь возможность потом проверять их в различных протоколах
        config.setParameter("RetraidingStartDateFromApplicationsReviewProtocol", startDate);
        config.setParameter("RetraidingEndDateFromApplicationsReviewProtocol", endDate);

        // устанавливаем флажок [Провести переторжку]
        considerationRequestCustomerProtocolPage.setRetraidingEnabledCheckbox();

        // region Диалоговое окно [Включение возможности переторжки]

        retraidingChangeDialog.ifDialogOpened().
                setRetradingTypeTo("Переключатель Очная переторжка").
                setFieldClearClickAndSendKeys("Поле Дата и время начала переторжки", startDate).
                setFieldClearClickAndSendKeys("Поле Дата и время окончания переторжки", endDate).
                verifyCheckBoxNotSelected("Флаг Показывать ценовые предложения, поданные в заявках").
                verifyCheckBoxSelected("Флаг Показывать наименования участников").
                setCheckBoxValue("Флаг Скрывать лучшее ценовое предложение").
                retradingChangeConfirm();

        // endregion

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' добавляет к протоколу рассмотрения и оценки заявок конкурса переторжку$")
    public void customerAddsRetenderingToCompetitiveProcurementTenderApplicationsReviewProtocol() throws Throwable {
        String stepName = "'Заказчик' добавляет к протоколу рассмотрения и оценки заявок конкурса переторжку";
        this.logStepName(stepName);

        timer.start();

        // Вычисляем дату начала переторжки
        String startDate = timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MONTHS", "3");

        // Занесём эту дату в параметры теста чтобы иметь возможность потом проверять её в различных протоколах
        config.setParameter("RetraidingStartDateFromApplicationsReviewProtocol", startDate);

        // устанавливаем флажок [Провести переторжку]
        considerationRequestCustomerProtocolPage.setRetraidingEnabledCheckbox();

        // region Диалоговое окно [Включение возможности переторжки]

        retraidingChangeDialog.ifDialogOpened().
                setRetradingTypeTo("Переключатель Очная переторжка").
                setFieldClearClickAndSendKeys("Поле Дата и время начала переторжки", startDate).
                verifyCheckBoxSelected("Флаг Автоматическое продление переторжки после подачи ценового предложения").
                setNumericTextBox("Поле Время продления, мин", "15").
                setNumericTextBox("Поле Максимальная продолжительность переторжки, мин", "45").
                setCheckBoxValue("Флаг Переторжка с шагом").
                setCheckBoxValue("Флаг Возможно прикрепление документов к ценовым предложениям").
                verifyCheckBoxNotSelected("Флаг Каскадная переторжка").
                retradingChangeConfirm();

        // endregion

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' добавляет к протоколу {string} переторжку")
    public void customerAddsRetenderingForProtocol(String protocolName) throws Throwable {
        String stepName = String.format("'Заказчик' добавляет к протоколу '%s' переторжку", protocolName);
        this.logStepName(stepName);

        timer.start();

        // Вычисляем даты начала и окончания переторжки
        String startDate = timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MONTHS", "3");
        String endDate = timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MONTHS", "4");

        // Занесём эти даты в параметры теста чтобы иметь возможность потом проверять их в различных протоколах
        config.setParameter("RetraidingStartDateFromApplicationsReviewProtocol", startDate);
        config.setParameter("RetraidingEndDateFromApplicationsReviewProtocol", endDate);


        // устанавливаем флажок [Провести переторжку]
        considerationRequestCustomerProtocolPage.setRetraidingEnabledCheckbox();

        // region Диалоговое окно [Включение возможности переторжки]

        retraidingChangeDialog.ifDialogOpened().
                setRetradingTypeTo("Переключатель Очная переторжка").
                setFieldClearClickAndSendKeys("Поле Дата и время начала переторжки", startDate).
                setCheckBoxValue("Флаг Автоматическое продление переторжки после подачи ценового предложения").
                setFieldClearClickAndSendKeys("Поле Дата и время окончания переторжки", endDate).
                setCheckBoxValue("Флаг Переторжка с шагом").
                retradingChangeConfirm();

        // endregion

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' добавляет к протоколу {string} переторжку с единственным участником")
    public void customerAddsRetenderingForProtocolWithSingleParticipant(String protocolName) throws Throwable {
        String stepName = String.format("'Заказчик' добавляет к протоколу '%s' переторжку", protocolName);
        this.logStepName(stepName);

        timer.start();

        // Вычисляем даты начала и окончания переторжки
        String startDate = timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MONTHS", "3");
        String endDate = timer.getDateTimeShift(Timer.getPublishNoticeDate(), "MONTHS", "4");

        // Занесём эти даты в параметры теста чтобы иметь возможность потом проверять их в различных протоколах
        config.setParameter("RetraidingStartDateFromApplicationsReviewProtocol", startDate);
        config.setParameter("RetraidingEndDateFromApplicationsReviewProtocol", endDate);


        // устанавливаем переключатель "Сведения протокола рассмотрения и оценки заявок" в положение "Провести переторжку"
        considerationRequestCustomerProtocolPage.setSingleParticipantContractSwitchToRetradingPosition();

        // region Диалоговое окно [Включение возможности переторжки]

        retraidingChangeDialog.ifDialogOpened().
                setRetradingTypeTo("Переключатель Очная переторжка").
                setFieldClearClickAndSendKeys("Поле Дата и время начала переторжки", startDate).
                setCheckBoxValue("Флаг Автоматическое продление переторжки после подачи ценового предложения").
                setFieldClearClickAndSendKeys("Поле Дата и время окончания переторжки", endDate).
                setCheckBoxValue("Флаг Переторжка с шагом").
                retradingChangeConfirm();

        // endregion

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет дату начала переторжки$")
    public void customerChecksRetraidingStartDateInCompetitiveProcurementTenderApplicationsReviewProtocol()
            throws Throwable {
        String stepName = "'Заказчик' проверяет дату начала переторжки";
        this.logStepName(stepName);

        timer.start();

        // Поскольку это независимый шаг, сначала проверим наличие дат в параметрах теста чтобы не словить Exception
        if (config.checkParameter("RetraidingStartDateFromApplicationsReviewProtocol")) {
            // Получаем дату начала переторжки
            String startDate = config.getParameter("RetraidingStartDateFromApplicationsReviewProtocol");

            // Проверяем значение даты в протоколе рассмотрения заявок
            considerationRequestCustomerProtocolPage.moveToRetraidingDatesHeader();
            considerationRequestCustomerProtocolPage.checkRetraidingStartDate(startDate);
        } else System.err.println("[ПРЕДУПРЕЖДЕНИЕ]: дата начала переторжки отсутствует в параметрах теста !");

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет дату начала и дату окончания переторжки$")
    public void customerChecksRetraidingStartAndRetraidingEndDatesInTenderApplicationsReviewProtocol() throws Throwable {
        String stepName = "'Заказчик' проверяет дату начала и дату окончания переторжки";
        this.logStepName(stepName);

        timer.start();

        // Поскольку это независимый шаг, сначала проверим наличие дат в параметрах теста чтобы не словить Exception
        if (config.checkParameter("RetraidingStartDateFromApplicationsReviewProtocol") &&
                config.checkParameter("RetraidingEndDateFromApplicationsReviewProtocol")) {
            // Получаем даты начала и окончания переторжки
            String startDate = config.getParameter("RetraidingStartDateFromApplicationsReviewProtocol");
            String endDate = config.getParameter("RetraidingEndDateFromApplicationsReviewProtocol");

            // Проверяем значения дат в протоколе рассмотрения заявок конкурса
            considerationRequestCustomerProtocolPage.moveToRetraidingDatesHeader();
            considerationRequestCustomerProtocolPage.checkRetraidingStartDate(startDate);
            considerationRequestCustomerProtocolPage.checkRetraidingEndDate(endDate);
        } else
            System.err.println("[ПРЕДУПРЕЖДЕНИЕ]: даты начала/окончания переторжки отсутствуют в параметрах теста !");

        timer.printStepTotalTime(stepName);
    }

    // endregion
}
