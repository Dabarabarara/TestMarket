package ru.StepDefinitions.Customer.Notice;

import Helpers.DbOperations;
import Helpers.Dictionary;
import Helpers.Timer;
import cucumber.api.java.en.And;
import ru.PageObjects.CommonDialogs.InformationAngularDialog;
import ru.PageObjects.Customer.CustomerMyPurchasesPage;
import ru.PageObjects.Customer.Notice.CreateSMBNoticePage;
import ru.PageObjects.Customer.Notice.PurchaseNoticeDraftSuccessfullySavedDialog;
import ru.PageObjects.RabbitMQ.RabbitMQLoginPage;
import ru.PageObjects.RabbitMQ.RabbitMQUserCabinetPage;
import ru.StepDefinitions.AbstractStepDefinitions;

import java.util.Arrays;
import java.util.List;

/**
 * Класс описывающий шаги работы Заказчика по созданию извещения о новой закупке (МСП в соответствии с нормами 223-ФЗ).
 * Created by Vladimir V. Klochkov on 17.03.2020.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class CustomerCreateSMBNoticeStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final CustomerMyPurchasesPage customerMyPurchasesPage = new CustomerMyPurchasesPage(driver);
    private final RabbitMQUserCabinetPage rabbitMQUserCabinetPage = new RabbitMQUserCabinetPage(driver);
    private final CreateSMBNoticePage createSMBNoticePage = new CreateSMBNoticePage(driver);
    private final RabbitMQLoginPage rabbitMQLoginPage = new RabbitMQLoginPage(driver);

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    @And("'Заказчик А' МСП открывает страницу создания извещения для закупки {string}")
    public void customerOpensCreateSmallAndMediumSizedBusinessesPurchaseNoticePageForPurchaseType(String purchaseMethod)
            throws Throwable {
        String stepName = String.format(
                "'Заказчик А' МСП открывает страницу создания извещения для закупки '%s'", purchaseMethod);
        this.logStepName(stepName);

        timer.start();

        customerMyPurchasesPage.waitLoadingImage();
        customerMyPurchasesPage.openCreateSmallAndMediumSizedBusinessesPurchaseNoticePage(purchaseMethod);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП заполняет сведения о процедуре: {string}")
    public void customerFillsSMBProcedureDetails(String purchaseMethod) throws Throwable {
        String stepName = String.format("'Заказчик А' МСП заполняет сведения о процедуре: '%s'", purchaseMethod);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.waitLoadingImage();
        createSMBNoticePage.checkPageUrl(
                String.format("Формирование черновика извещения '%s' - Angular", purchaseMethod));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП заполняет раздел {string}:")
    public void customerFillsBlock(String block) throws Throwable {
        String stepName = String.format("'Заказчик А' МСП заполняет раздел '%s':", block);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.openBlock(block);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП переходит к разделу {string} в черновике извещения")
    public void customerGoesToBlockInPurchaseNoticeDraft(String block) throws Throwable {
        String stepName = String.format("'Заказчик А' МСП переходит к разделу '%s' в черновике извещения", block);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.gotoBlock(block);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП переключается на вкладку лота {string}")
    public void customerSwitchesOnLotTabByName(String tabName) throws Throwable {
        String stepName = String.format("'Заказчик А' МСП переключается на вкладку лота '%s'", tabName);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.switchToTab(tabName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП проверяет, что поле {string} содержит значение {string}")
    public void customerVerifiesFieldContent(String fieldName, String value) {
        String stepName =
                String.format("'Заказчик А' МСП проверяет, что поле '%s' содержит значение '%s'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП проверяет, что поле {string} содержит номер {string}, указанный в файле конфигурации")
    public void customerVerifiesPhoneNumber(String fieldName, String key) {
        String value = config.getConfigParameter(key);
        String stepName =
                String.format(
                        "'Заказчик А' МСП проверяет, что поле '%s' содержит номер '%s', указанный в файле конфигурации",
                        fieldName, value);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.verifyFieldWithPhoneNumber(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП проверяет, что поле {string} содержит значение {string}, указанное в файле конфигурации")
    public void customerVerifiesFieldContentUsingParameterFromConfigFile(String fieldName, String key) {
        String value = config.getConfigParameter(key);
        String stepName = String.format(
                "'Заказчик А' МСП проверяет, что поле '%s' содержит значение '%s', указанное в файле конфигурации",
                fieldName, value);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП проверяет, что поле {string} содержит дату публикации извещения")
    public void customerVerifiesFieldContentForPurchaseNoticePublishDateWithoutTime(String fieldName) {
        String stepName =
                String.format("'Заказчик А' МСП проверяет, что поле '%s' содержит дату публикации извещения", fieldName);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.
                verifyField(fieldName, timer.dateOnlyToResponseFormat4Ui(Timer.getPublishNoticeDate()));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП проверяет дату и время {string} в {string} со смещением {string} от времени публикации")
    public void customerVerifiesDateAndTimeField(String field, String shift, String amount) {
        String stepName = String.format(
                "'Заказчик А' МСП проверяет дату и время '%s' в '%s' со смещением '%s' от времени публикации",
                field, shift, amount);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.
                verifyField(field, timer.getDateTimeShift(Timer.getPublishNoticeDate(), shift, amount));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП проверяет дату {string} в {string} со смещением {string} от времени публикации")
    public void customerVerifiesDateField(String field, String shift, String amount) {
        String stepName = String.format(
                "'Заказчик А' МСП проверяет дату '%s' в '%s' со смещением '%s' от времени публикации",
                field, shift, amount);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.verifyField(field, timer.getDateShift(Timer.getPublishNoticeDate(), shift, amount));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП проверяет дату и время {string} в {string} со смещением {string} от времени публикации плюс {int} мин.")
    public void customerVerifiesDateAndTimeField(String field, String shift, String amount, int minutes) {
        String stepName = String.format("'Заказчик' МСП проверяет дату и время '%s' в '%s' со смещением '%s' от " +
                "времени публикации плюс '%d' мин.", field, shift, amount, minutes);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.verifyField(field,
                timer.getDateTimeShift(Timer.getPublishNoticeDate(minutes), shift, amount));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП проверяет, что поле {string} содержит пустое значение")
    public void customerVerifiesFieldIsEmpty(String fieldName) {
        String stepName = String.format("'Заказчик А' МСП проверяет, что поле '%s' содержит пустое значение", fieldName);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.verifyFieldContentOrEmptyField(fieldName, " ");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП проверяет, что поле {string} содержит не пустое значение")
    public void customerVerifiesFieldIsNotEmpty(String fieldName) {
        String stepName =
                String.format("'Заказчик А' МСП проверяет, что поле '%s' содержит не пустое значение", fieldName);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.verifyFieldIsNotEmpty(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП заполняет поле 'Наименование закупки' сгенерированным именем для типа закупки {string}")
    public void customerFillsPurchaseNameFieldWithAutoGeneratedValue(String purchaseType) throws Throwable {
        String stepName = String.format(
                "'Заказчик А' МСП заполняет поле 'Наименование закупки' сгенерированным именем для типа закупки  '%s'",
                purchaseType);
        this.logStepName(stepName);

        timer.start();

        Dictionary purchaseNamePrefixes = new Dictionary();
        purchaseNamePrefixes.add("Конкурс МСП", "Тестовый конкурс (МСП-закупка)");

        createSMBNoticePage.setFieldClearClickAndSendKeys("Наименование закупки",
                String.format("%s от %s.",
                purchaseNamePrefixes.find(purchaseType), timer.getCurrentDateTime("dd.MM.yyyy HH:mm:SS")));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП заполняет поле {string} значением {string}")
    public void customerFillsFieldByName(String fieldName, String value) throws Throwable {
        String stepName = String.format("'Заказчик А' МСП заполняет поле '%s' значением '%s'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.setFieldClearClickAndSendKeys(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП проверяет, что флажок {string} {string}")
    public void customerVerifiesCheckBoxState(String checkBoxName, String checkBoxState) {
        String stepName = String.format("'Заказчик А' МСП проверяет, что флажок '%s' %s", checkBoxName, checkBoxState);
        this.logStepName(stepName);

        timer.start();

        if (checkBoxState.equals("установлен")) createSMBNoticePage.verifyCheckBoxSelected(checkBoxName);
        else createSMBNoticePage.verifyCheckBoxNotSelected(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП устанавливает флажок {string}")
    public void customerSetsCheckBoxValue(String checkBoxName) {
        String stepName = String.format("'Заказчик А' МСП устанавливает флажок '%s'", checkBoxName);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.setCheckBoxValue(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП заполняет дату {string} в {string} со смещением {string} от времени публикации")
    public void customerFillsDateField(String field, String shiftType, String amountAsString) throws Throwable {
        String stepName = String.format(
                "'Заказчик А' МСП заполняет дату '%s' в  '%s' со смещением '%s' от времени публикации",
                field, shiftType, amountAsString);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.setFieldClearClickAndSendKeys(String.format("Дата %s", field),
                timer.getDateShift(Timer.getPublishNoticeDate(), shiftType, amountAsString));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП заполняет дату и время {string} в {string} со смещением {string} от времени публикации")
    public void customerFillsDateAndTimeField(String field, String shiftType, String amountAsString) throws Throwable {
        String stepName = String.format(
                "'Заказчик А' МСП заполняет дату и время '%s' в '%s' со смещением '%s' от времени публикации",
                field, shiftType, amountAsString);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.setFieldClearClickAndSendKeys(String.format("Дата и время %s", field),
                timer.getDateTimeShift(Timer.getPublishNoticeDate(), shiftType, amountAsString));
        createSMBNoticePage.clickOnButton("'Закрыть' - в окне 'Календарь'");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП проверяет, что переключатель {string} не отмечен")
    public void customerVerifiesRadioButtonNotSelected(String radioButtonName) {
        String stepName = String.format("'Заказчик А' МСП проверяет, что переключатель '%s' не отмечен", radioButtonName);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.verifyRadioButtonNotSelected(radioButtonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП проверяет, что переключатель {string} отмечен")
    public void customerVerifiesRadioButtonSelected(String radioButtonName) {
        String stepName = String.format("'Заказчик А' МСП проверяет, что переключатель '%s' отмечен", radioButtonName);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.verifyRadioButtonSelected(radioButtonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП заполняет поле 'Ответственное должностное лицо' {string}-м значением из списка")
    public void customerFillsResponciblePerson(String orderNumber) throws Throwable {
        String stepName = String.format(
                "'Заказчик А' МСП заполняет поле 'Ответственное должностное лицо' '%s'-м значением из списка",
                orderNumber);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.fillResponciblePerson(orderNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП заполняет поле 'Ответственное должностное лицо' именем сертификата {string} из файла конф.")
    public void customerFillsResponciblePersonFieldUsingCertificateNameFromConfigFile(String certificateName)
            throws Throwable {
        String stepName = String.format(
                "'Заказчик А' МСП заполняет поле 'Ответственное должностное лицо' именем сертификата '%s' из файла конф.",
                certificateName);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.
                fillResponciblePersonFieldUsingCertificateNameFromConfigFile(config.getConfigParameter(certificateName));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП нажимает кнопку {string}")
    public void customerClicksOnButtonByName(String buttonName) throws Throwable {
        String stepName = String.format("'Заказчик А' МСП нажимает кнопку '%s'", buttonName);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП заполняет поле со счётчиком {string} значением {string}")
    public void customerFillsFieldWithCounterByName(String fieldName, String value) throws Throwable {
        String stepName = String.format("'Заказчик А' МСП заполняет поле со счётчиком '%s' значением '%s",
                fieldName, value);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.setKendoNumericTextBoxField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП устанавливает переключатель {string} в положение {string}")
    public void customerSetsRadioButtonTo(String radioButtonName, String radioButtonPosition) throws Throwable {
        String stepName = String.format("'Заказчик А' МСП устанавливает переключатель '%s' в положение '%s'",
                radioButtonName, radioButtonPosition);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.selectRadioButton(radioButtonPosition);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП заполняет поле лота Код ОКПД2 значением {string}")
    public void customerFillsLotOkpd2Field(String lotOkpd2) throws Throwable {
        String stepName = String.format("'Заказчик А' МСП заполняет поле лота Код ОКПД2 значением '%s'", lotOkpd2);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.setLotOkpd2(lotOkpd2);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП заполняет поле лота Код ОКВЭД2 значением {string}")
    public void customerFillsLotOkved2Field(String lotOkved2) throws Throwable {
        String stepName = String.format("'Заказчик А' МСП заполняет поле лота Код ОКВЭД2 значением '%s'", lotOkved2);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.setLotOkved2(lotOkved2);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП заполняет поле 'Единица измерения - код ОКЕИ' значением из списка")
    public void customerFillsOkeiCode() throws Throwable {
        String stepName = "'Заказчик А' МСП заполняет поле 'Единица измерения - код ОКЕИ' значением из списка";
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.fillOkeiCode();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП переходит к полю {string}")
    public void customerClicksOnFieldByName(String fieldName) throws Throwable {
        String stepName = String.format("'Заказчик А' МСП переходит к полю '%s'", fieldName);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.clickOnFieldByName(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП заполняет поле с множественным выбором 'Регион' значением 'г. Севастополь'")
    public void customerSetsListWithInputAndMultiSelectField() throws Throwable {
        String stepName = "'Заказчик А' МСП заполняет поле с множественным выбором 'Регион' значением 'г. Севастополь'";
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.setListWithInputAndMultiSelectField(
                "Сведения о заказчиках - Регион - X",
                "Сведения о заказчиках - Регион - поле ввода",
                "Сведения о заказчиках - Регион - требуемое значение в списке - г. Севастополь");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП проверяет, что таблица 'Документы лота' пуста")
    public void customerChecksThatTheLotDocumentsTableIsEmpty() {
        String stepName = "'Заказчик А' МСП проверяет, что таблица 'Документы лота' пуста";
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.checkThatTheLotDocumentsTableIsEmpty();

        timer.printStepTotalTime(stepName);
    }


    @And("'Заказчик А' МСП проверяет, что поле с множественным выбором 'Регион' содержит не пустое значение")
    public void customerChecksListWithInputAndMultiSelectField() {
        String stepName =
                "'Заказчик А' МСП проверяет, что поле с множественным выбором 'Регион' содержит не пустое значение";
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.checkListWithInputAndMultiSelectField("Сведения о заказчиках - Регион - X");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП прикрепляет необходимые документы")
    public void customerFillsFullTenderDocs() throws Throwable {
        String stepName = "'Заказчик А' МСП прикрепляет документы полного конкурса";
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.uploadFileIntoDocuments(config.getAbsolutePathToFoundationDoc());
        //--------------------------------------------------------------------------------------------------------------
        // Был баг 19.09.2019 при котором не обновлялась таблица прикрепленных к извещению документов после загрузки
        // Это костыль для обхода данного бага ( строка ниже )
        // createSMBNoticePage.openBlock("Документы");
        //--------------------------------------------------------------------------------------------------------------
        createSMBNoticePage.checkLinkToDownloadFileIntoDocuments(config.getConfigParameter("FoundationDoc"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП проверяет ссылку для скачивания прикрепленного файла {string} в разделе 'Документы'")
    public void customerChecks1stLinkFileNameOfAttachedDocumentInDocumentsPartition(String fileName) throws Throwable {
        String stepName = String.format(
                "'Заказчик' МСП проверяет ссылку для скачивания прикрепленного файла '%s' в разделе 'Документы'",
                fileName);
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.checkLinkToDownloadFileIntoDocuments(fileName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП проверяет, что требуемая рабочая группа отмечена в таблице 'Рабочие группы'")
    public void customerChecksThatTheDesiredWorkGroupSelected() {
        String stepName = "'Заказчик' МСП проверяет, что требуемая рабочая группа отмечена в таблице 'Рабочие группы'";
        this.logStepName(stepName);

        timer.start();

        createSMBNoticePage.checkThatTheDesiredWorkGroupSelected();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП публикует извещение на электронной площадке РТС-тендер")
    public void customerPublishesPurchaseNoticeFromCreateOrEditPage() throws Throwable {
        String stepName = "'Заказчик А' МСП публикует извещение на электронной площадке РТС-тендер";
        this.logStepName(stepName);

        timer.start();

        // Публикует извещение со страницы [Формирование черновика извещения ... МСП]
        createSMBNoticePage.publishNotice();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП нажимает на кнопку 'OK' в диалоговом окне 'Информация'")
    public void customerClicksOnOkButtonInInformationDialogWindow() throws Throwable {
        String stepName = "'Заказчик' МСП нажимает на кнопку 'OK' в диалоговом окне 'Информация'";
        this.logStepName(stepName);

        timer.start();

        InformationAngularDialog informationAngularDialog = new InformationAngularDialog(driver);
        informationAngularDialog.
                ifDialogOpened("Текст заголовка окна для закупки МСП").
                clickOnOKButton("ОК для закупки МСП");

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик А' МСП нажимает кнопку {string} в диалоговом окне 'Сохранено'")
    public void customerClicksOnButtonByNameInSuccessfullySavedDialogWondow(String buttonName) throws Throwable {
        String stepName =
                String.format("'Заказчик' МСП нажимает кнопку '%s' в диалоговом окне 'Сохранено'", buttonName);
        this.logStepName(stepName);

        timer.start();
        PurchaseNoticeDraftSuccessfullySavedDialog purchaseNoticeDraftSuccessfullySavedDialog =
                new PurchaseNoticeDraftSuccessfullySavedDialog(driver);

        purchaseNoticeDraftSuccessfullySavedDialog.
                ifDialogOpened().
                clickOnButtonByName(buttonName);

        timer.printStepTotalTime(stepName);
    }
    @And("^'Заказчик' МСП записывает в базу данных номер закупки в ЕИС для опубликованной на площадке закупки Конкурс$")
    public void customerLinksPublishedTenderNoticeWithEISNoticeByEISNumberUsingSql() {
        String stepName = "'Заказчик' МСП записывает в базу данных номер закупки в ЕИС для опубликованной на площадке" +
                " закупки Конкурс";
        this.logStepName(stepName);

        timer.start();

        String purchaseNumber = config.getParameter("PurchaseNumber");
        String purchaseNumberInEis = timer.getRandomDecimalId(11);
        String sql1 = "SELECT TOP 1 [NotificationNumber], [UisPublicationDate], [UniqueId] FROM Trade WHERE [Id] = %s";
        String sql2 =
                "UPDATE [dbo].[Trade] " +
                        "SET [NotificationNumber] = NULL, " +
                        "    [UisPublicationDate] = GETUTCDATE(), " +
                        "    [UniqueId] = NEWID(), " +
                        "    [OosPurchaseMethodCode] = 4489 " +
                        "WHERE [Id] = %s";
        String sql3 =
                "SELECT TOP 1 [NotificationNumber], [UisPublicationDate], [UniqueId], [OosPurchaseMethodCode] " +
                        "FROM Trade WHERE [Id] = %s";
        String sql4 =
                "UPDATE [dbo].[Trade] " +
                        "SET [IsModificationSentToOos] = 1, " +
                        "    [NotificationNumber] = %s, " +
                        "    [OosPurchaseMethodCode] = 4489, " +
                        "    [PlacedByRTS] = 0, " +
                        "    [UisPublicationDate] = GETUTCDATE() " +
                        "WHERE [Id] = %s";
        List<String> fieldsToPrint1 = Arrays.asList("NotificationNumber", "UisPublicationDate", "UniqueId");
        List<String> fieldsToPrint2 =
                Arrays.asList("NotificationNumber", "UisPublicationDate", "UniqueId", "OosPurchaseMethodCode");

        DbOperations dbOperations = new DbOperations();
        dbOperations.
                connectToDatabase(config.getConfigParameter("SqlDatabaseName1")).
                executeSelectQuery(String.format(sql1, purchaseNumber), 1, fieldsToPrint1).
                executeUpdateQuery(String.format(sql2, purchaseNumber)).
                executeSelectQuery(String.format(sql3, purchaseNumber), 1, fieldsToPrint2).
                executeUpdateQuery(String.format(sql4, purchaseNumberInEis, purchaseNumber)).
                executeSelectQuery(String.format(sql3, purchaseNumber), 1, fieldsToPrint2).
                closeConnectionToDatabase();

        System.out.printf("[ИНФОРМАЦИЯ]: закупке с номером: [%s] на площадке присвоен № в ЕИС: [%s].%n",
                purchaseNumber, purchaseNumberInEis);
        config.setParameter("PurchaseNumberInEis", purchaseNumberInEis);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' МСП меняет статус лота для закупки Конкурс на 'Прием заявок' используя RabbitMQ$")
    public void customerChangesPublishedTenderLotStatusToAcceptRequestsUsingRabbitMQ() throws Throwable {
        String stepName = "'Заказчик' МСП меняет статус лота для закупки Конкурс на 'Прием заявок' используя RabbitMQ";
        this.logStepName(stepName);

        timer.start();

        String rabbitMQMessage =
                rabbitMQUserCabinetPage.getMessageModel("SMBTender223JsonMessageForRabbitMqModel").
                        replace("'{Entity Id}'", config.getParameter("PurchaseNumber"));

        rabbitMQLoginPage.
                gotoRabbitMQLoginPage(config.getConfigParameter("RabbitMQLoginPage")).
                thanLoginToRabbitMQ(config.getConfigParameter("RabbitMQUserName"),
                        config.getConfigParameter("RabbitMQPassword"));
        rabbitMQUserCabinetPage.
                thanCheckLoginToRabbitMQSuccessfully().
                thanSelectVirtualHostFilter().
                thanGoToQueuesTab().
                thanSetQueueFilter(config.getConfigParameter("RabbitMQQueueFilter")).
                thanCheckQueueFilteringResultsAndOpenFirstMessage().
                thanSetPayloadFildAndClickPublishMessage(rabbitMQMessage).
                thanLogoutFromRabbitMQ();

        timer.printStepTotalTime(stepName);
    }
}
