package ru.StepDefinitions.Supplier.Request;

import cucumber.api.java.en.And;
import ru.PageObjects.Supplier.PurchaseRequestPage;
import ru.PageObjects.Supplier.PurchaseRequestPageOld;
import ru.StepDefinitions.AbstractStepDefinitions;

/**
 * Класс, описывающий шаги работы поставщика по созданию заявки на участие в закупке.
 * Created by Vladimir V. Klochkov on 11.03.2018.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class SupplierPurchaseRequestStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final PurchaseRequestPageOld purchaseRequestPageOld = new PurchaseRequestPageOld(driver);
    private final PurchaseRequestPage purchaseRequestPage = new PurchaseRequestPage(driver);

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    @And("'Поставщик' проверяет размер обеспечения заявки на участие {string} в черновике заявки")
    public void supplierChecksApplicationGuaranteeSum(String expectedSum) {
        String stepName = String.format(
                "'Поставщик' проверяет размер обеспечения заявки на участие '%s' в черновике заявки", expectedSum);
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.checkApplicationGuaranteeSum(expectedSum);
        else
            purchaseRequestPageOld.checkApplicationGuaranteeSum(expectedSum);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет размер лицензионного вознаграждения {string} в черновике заявки")
    public void supplierChecksLicenseCommissionSum(String expectedSum) {
        String stepName = String.format(
                "'Поставщик' проверяет размер лицензионного вознаграждения '%s' в черновике заявки", expectedSum);
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.checkLicenseCommissionSum(expectedSum);
        else
            purchaseRequestPageOld.checkLicenseCommissionSum(expectedSum);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' заполняет поле заявки 'Предложение о цене' значением {string}")
    public void supplierFillsPriceOfferField(String priceOffer) {
        String stepName =
                String.format("'Поставщик' заполняет поле заявки 'Предложение о цене' значением '%s'", priceOffer);
        this.logStepName(stepName);

        timer.start();

        purchaseRequestPage.setPriceOffer(priceOffer);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет поле заявки 'Ставка НДС' на значение {string}")
    public void supplierChecksSelectedVatValue(String value) {
        String stepName = String.format("'Поставщик' проверяет поле заявки 'Ставка НДС' на значение '%s'", value);
        this.logStepName(stepName);

        timer.start();

        purchaseRequestPage.checkSelectedVatValue(value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет поле заявки 'Предложение о цене с НДС' на значение {string}")
    public void supplierChecksPriceOfferWithVatValue(String value) {
        String stepName =
                String.format("'Поставщик' проверяет поле заявки 'Предложение о цене (с НДС)' на значение '%s'", value);
        this.logStepName(stepName);

        timer.start();

        purchaseRequestPage.checkPriceOfferWithVat(value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' заполняет поле заявки 'Предложение о цене договора' значением {string} руб.")
    public void supplierFillsContractPriceOfferField(String offerSum) throws Throwable {
        String stepName = String.format(
                "'Поставщик' заполняет поле заявки 'Предложение о цене договора' значением '%s' руб.", offerSum);
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.setOffer(offerSum);
        else
            purchaseRequestPageOld.setOffer(offerSum);

        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля 'Предложение о цене договора' значением [%s] руб.%n",
                offerSum);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' переходит к разделу заявки 'Ценовое предложение'")
    public void supplierGoesToPriceOfferSectionTitle() throws Throwable {
        String stepName = "'Поставщик' переходит к разделу заявки 'Ценовое предложение'";
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.goToPriceOfferSectionTitle();
        else
            purchaseRequestPageOld.goToPriceOfferSectionTitle();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' переходит к разделу заявки 'Первая часть заявки участника'")
    public void supplierGoesToFirstPartOfApplicationSectionTitle() throws Throwable {
        String stepName = "'Поставщик' переходит к разделу заявки 'Первая часть заявки участника'";
        this.logStepName(stepName);

        timer.start();

        purchaseRequestPage.goToFirstPartOfApplicationSectionTitle();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' переходит к разделу заявки 'Вторая часть заявки участника'")
    public void supplierGoesToSecondPartOfApplicationSectionTitle() throws Throwable {
        String stepName = "'Поставщик' переходит к разделу заявки 'Вторая часть заявки участника'";
        this.logStepName(stepName);

        timer.start();

        purchaseRequestPage.goToSecondPartOfApplicationSectionTitle();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' переходит к разделу заявки 'Сведения о товаре'")
    public void supplierGoesToProductDetailsInfoSectionTitle() throws Throwable {
        String stepName = "'Поставщик' переходит к разделу заявки 'Сведения о товаре'";
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.goToProductDetailsInfoSectionTitle();
        else
            purchaseRequestPageOld.goToProductDetailsInfoSectionTitle();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' переходит к разделу заявки 'Сведения об участнике процедуры'")
    public void supplierGoesToPurchaseMembersInfoSectionTitle() throws Throwable {
        String stepName = "'Поставщик' переходит к разделу заявки 'Сведения об участнике процедуры'";
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.goToPurchaseMembersInfoSectionTitle();
        else
            purchaseRequestPageOld.goToPurchaseMembersInfoSectionTitle();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет, что поле {string} содержит значение {string} на странице 'Заявка на участие в ...'")
    public void supplierVerifiesFieldContentOnPurchaseRequestPageOld(String fieldName, String value) {
        String stepName = String.format("'Поставщик' проверяет, что поле '%s' содержит значение '%s'" +
                " на странице 'Заявка на участие в ...'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        purchaseRequestPageOld.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' заполняет поле {string} значением {string} на странице 'Заявка на участие в ...'")
    public void supplierFillsFieldByNameOnPurchaseRequestPageOld(String fieldName, String value) throws Throwable {
        String stepName = String.format("'Поставщик' заполняет поле '%s' значением '%s' " +
                "на странице 'Заявка на участие в ...'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        purchaseRequestPageOld.setFieldClearClickAndSendKeys(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' заполняет поле 'Ставка НДС' значением 'Сложный НДС' из списка")
    public void supplierSetsTaxPercentFieldToComplexVatValue() throws Throwable {
        String stepName = "'Поставщик' заполняет поле 'Ставка НДС' значением 'Сложный НДС' из списка";
        this.logStepName(stepName);

        timer.start();

        purchaseRequestPageOld.setTaxPercentFieldToComplexVatValueInEditMode();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' выполняет импорт позиций ценовых предложений из файла")
    public void supplierImportsLotItemApplicationsFromFile() throws Throwable {
        String stepName = "'Поставщик' выполняет импорт позиций ценовых предложений из файла";
        this.logStepName(stepName);

        timer.start();

        purchaseRequestPageOld.importLotItemApplicationsFromFile();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет поле 'Предложение о цене' в разделе 'Ценовое предложение' на значение {string}")
    public void supplierChecksOfferFieldInPriceOfferPartitionForValueInEditMode(String value) {
        String stepName = String.format("'Поставщик' проверяет поле 'Предложение о цене' в разделе " +
                "'Ценовое предложение' на значение '%s'", value);
        this.logStepName(stepName);

        timer.start();

        System.out.printf("[ИНФОРМАЦИЯ]: проверяем поле 'Предложение о цене' на значение [%s].%n", value);

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.checkOfferValueInEditMode(value);
        else
            purchaseRequestPageOld.checkOfferValueInEditMode(value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет поле 'Предложение о цене' в разделе 'Ценовое предложение' на текст {string}")
    public void supplierChecksOfferFieldInPriceOfferPartitionForValueInViewMode(String value) {
        String stepName = String.format("'Поставщик' проверяет поле 'Предложение о цене' в разделе " +
                "'Ценовое предложение' на текст '%s'", value);
        this.logStepName(stepName);

        timer.start();

        System.out.printf("[ИНФОРМАЦИЯ]: проверяем поле 'Предложение о цене' на текст [%s].%n", value);

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.checkOfferValueInViewMode(value);
        else
            purchaseRequestPageOld.checkOfferValueInViewMode(value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет поле 'Ставка НДС' в разделе 'Ценовое предложение' на значение {string}")
    public void supplierChecksTaxPercentFieldInPriceOfferPartitionForValueInEditMode(String value) {
        String stepName = String.format("'Поставщик' проверяет поле 'Ставка НДС' в разделе " +
                "'Ценовое предложение' на значение '%s'", value);
        this.logStepName(stepName);

        timer.start();

        System.out.printf("[ИНФОРМАЦИЯ]: проверяем поле 'Ставка НДС' на значение [%s].%n", value);
        purchaseRequestPageOld.checkTaxPercentValueInEditMode(value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет поле 'Предложение о цене с НДС' в разделе 'Ценовое предложение' на значение {string}")
    public void supplierChecksQuotationWithTaxFieldInPriceOfferPartitionForValueInEditMode(String value) {
        String stepName = String.format("'Поставщик' проверяет поле 'Предложение о цене (с НДС)' в разделе " +
                "'Ценовое предложение' на значение '%s'", value);
        this.logStepName(stepName);

        timer.start();

        System.out.printf("[ИНФОРМАЦИЯ]: проверяем поле 'Предложение о цене (с НДС)' на значение [%s].%n", value);
        purchaseRequestPageOld.checkQuotationWithTaxValueInEditMode(value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет поле 'Предложение о цене с НДС' в разделе 'Ценовое предложение' на текст {string}")
    public void supplierChecksQuotationWithTaxFieldInPriceOfferPartitionForValueInViewMode(String value) {
        String stepName = String.format("'Поставщик' проверяет поле 'Предложение о цене (с НДС)' в разделе " +
                "'Ценовое предложение' на текст '%s'", value);
        this.logStepName(stepName);

        timer.start();

        System.out.printf("[ИНФОРМАЦИЯ]: проверяем поле 'Предложение о цене (с НДС)' на текст [%s].%n", value);
        purchaseRequestPageOld.checkQuotationWithTaxValueInViewMode(value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' заполняет поле 'Предложение цены' в строке {string} таблицы раздела 'Ценовое предложение' значением {string}")
    public void supplierFillsOfferFieldIntoQuotationsTableByLineNumber(String lineNumber, String offerValue)
            throws Throwable {
        String stepName = String.format("'Поставщик' заполняет поле 'Предложение цены' в строке '%s' таблицы раздела " +
                "'Ценовое предложение' значением '%s'", lineNumber, offerValue);
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.setOfferFieldIntoQuotationsTableByLineNumber(offerValue, lineNumber);
        else
            purchaseRequestPageOld.setOfferFieldIntoQuotationsTableByLineNumber(offerValue, lineNumber);

        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля 'Предложение цены' в строке [%s] " +
                "таблицы раздела 'Ценовое предложение' значением [%s].%n", lineNumber, offerValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' заполняет поле 'НДС' в строке {string} таблицы раздела 'Ценовое предложение' значением {string}")
    public void supplierFillsVatFieldIntoQuotationsTableByLineNumber(String lineNumber, String offerValue)
            throws Throwable {
        String stepName = String.format("'Поставщик' заполняет поле 'НДС' в строке '%s' таблицы раздела " +
                "'Ценовое предложение' значением '%s'", lineNumber, offerValue);
        this.logStepName(stepName);

        timer.start();

        purchaseRequestPageOld.setVatFieldIntoQuotationsTableByLineNumber(offerValue, lineNumber);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля 'НДС' в строке [%s] " +
                "таблицы раздела 'Ценовое предложение' значением [%s].%n", lineNumber, offerValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет столбец {string} строка {string} таблицы раздела 'Ценовое предложение' на текст {string}")
    public void supplierChecksCellByColumnNameAndLineNumberInQuotationsTableForText(
            String columnName, String lineNumber, String cellValue) {
        String stepName = String.format(
                "'Поставщик' проверяет столбец [%s] строка [%s] таблицы раздела 'Ценовое предложение' на текст [%s]",
                columnName, lineNumber, cellValue);
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.
                    verifyCellByColumnNameAndLineNumberInQuotationsTableForText(columnName, lineNumber, cellValue);
        else
            purchaseRequestPageOld.
                    verifyCellByColumnNameAndLineNumberInQuotationsTableForText(columnName, lineNumber, cellValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' прикрепляет документ в разделе заявки 'Ценовое предложение'")
    public void supplierUploadsPriceOfferDetails() throws Throwable {
        String stepName = "'Поставщик' прикрепляет документ в разделе заявки 'Ценовое предложение'";
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.uploadPriceOfferDetails();
        else
            purchaseRequestPageOld.uploadPriceOfferDetails();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' устанавливает переключатель {string} в положение {string} на странице 'Заявка на участие в закупке черновик'")
    public void customerSetsRadioButtonOnPurchaseRequestPage(String radioButtonName, String radioButtonPosition) throws Throwable {
        String stepName = String.format(
                "'Заказчик' устанавливает переключатель '%s' в положение '%s' на странице " +
                        "'Заявка на участие в закупке черновик'", radioButtonName, radioButtonPosition);
        this.logStepName(stepName);

        timer.start();

        purchaseRequestPage.selectRadioButton(radioButtonPosition);

        timer.printStepTotalTime(stepName);

    }

    @And("'Поставщик' прикрепляет документ в разделе заявки 'Сведения о товаре'")
    public void supplierUploadsAgreementDetails() throws Throwable {
        String stepName = "'Поставщик' прикрепляет документ в разделе заявки 'Сведения о товаре'";
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.uploadAgreementDetails();
        else
            purchaseRequestPageOld.uploadAgreementDetails();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' прикрепляет документ в разделе заявки 'Первая часть заявки'")
    public void supplierUploadsAgreementDetailsForFirstPartApplication() throws Throwable {
        String stepName = "'Поставщик' прикрепляет документ в разделе заявки 'Первая часть заявки'";
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.uploadAgreementDetails();
        else
            purchaseRequestPageOld.uploadAgreementDetails();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' прикрепляет документ в разделе заявки 'Ценовые предложения'")
    public void supplierUploadsAlternativeAgreementDetails() {
        String stepName = "'Поставщик' прикрепляет документ в разделе заявки 'Ценовые предложения'";
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.uploadAlternativeAgreementDetails();
        else
            purchaseRequestPageOld.uploadAlternativeAgreementDetails();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' прикрепляет дополнительный документ в разделе заявки 'Ценовые предложения'")
    public void supplierUploadsAdditionalAgreementDetails() {
        String stepName = "'Поставщик' прикрепляет дополнительный документ в разделе заявки 'Ценовые предложения'";
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.uploadAdditionalAgreementDetails();
        else
            purchaseRequestPageOld.uploadAdditionalAgreementDetails();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' прикрепляет документ в разделе заявки 'Первая часть заявки участника'")
    public void supplierUploadsDocumentInFirstPartOfApplicationSection() {
        String stepName = "'Поставщик' прикрепляет документ в разделе заявки 'Первая часть заявки участника'";
        this.logStepName(stepName);

        timer.start();

        purchaseRequestPage.uploadDocumentInFirstPartOfApplicationSection();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' прикрепляет документ в разделе заявки 'Вторая часть заявки участника'")
    public void supplierUploadsDocumentInSecondPartOfApplicationSection() {
        String stepName = "'Поставщик' прикрепляет документ в разделе заявки 'Вторая часть заявки участника'";
        this.logStepName(stepName);

        timer.start();

        purchaseRequestPage.uploadDocumentInSecondPartOfApplicationSection();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' заполняет поле заявки 'Номер контактного телефона'")
    public void supplierFillsContactPhoneNumberField() throws Throwable {
        String stepName = "'Поставщик' заполняет поле заявки 'Номер контактного телефона'";
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.fillContactPhoneNumber();
        else
            purchaseRequestPageOld.fillContactPhoneNumber();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет наличие кнопки 'Внести изменения' на форме просмотра заявки")
    public void supplierChecksEditRequestButtonExist() {
        String stepName = "'Поставщик' проверяет наличие кнопки 'Внести изменения' на форме просмотра заявки";
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.checkEditRequestButtonExist();
        else
            purchaseRequestPageOld.checkEditRequestButtonExist();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' нажимает на кнопку 'Внести изменения' для редактирования заявки")
    public void supplierClicksOnsEditRequestButton() throws Throwable {
        String stepName = "'Поставщик' нажимает на кнопку 'Внести изменения' для редактирования заявки";
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.clickOnEditRequestButton();
        else
            purchaseRequestPageOld.clickOnEditRequestButton();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' переходит по ссылке № {string} 'Редактировать' в таблице 'Ценовые предложения'")
    public void supplierGoesToNthEditLinkInPriceOffersTable(String linkNumber) {
        String stepName = String.format(
                "'Поставщик' переходит по ссылке № '%s' 'Редактировать' в таблице 'Ценовые предложения'", linkNumber);
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.goToNthEditLinkInPriceOffersTable(linkNumber);
        else
            purchaseRequestPageOld.goToNthEditLinkInPriceOffersTable(linkNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' нажимает на кнопку 'Добавить' для таблицы 'Ценовые предложения'")
    public void supplierClicksOnAddNewPriceOfferToTableButton() throws Throwable {
        String stepName = "'Поставщик' нажимает на кнопку 'Добавить' для таблицы 'Ценовые предложения'";
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.clickOnAddNewPriceOfferToTableButton();
        else
            purchaseRequestPageOld.clickOnAddNewPriceOfferToTableButton();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет, что {string} ценовое предложение в {string} строке имеет значение {string}")
    public void supplierChecksThePriceOfferRankAndPrice(String rank, String row, String price) {
        String stepName = String.format(
                "'Поставщик' проверяет, что '%s' ценовое предложение в '%s' строке имеет значение '%s'",
                rank, row, price);
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.checkThePriceOfferRankAndPrice(rank, row, price);
        else
            purchaseRequestPageOld.checkThePriceOfferRankAndPrice(rank, row, price);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' заполняет поле заявки 'Страна происхождения товара' значением 'Российская федерация'")
    public void supplierFillsCountryOfOriginFieldWithTheValueRussianFederation() throws Throwable {
        String stepName =
                "'Поставщик' заполняет поле заявки 'Страна происхождения товара' значением 'Российская федерация'";
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.setCountryOfOriginFieldWithTheValueRussianFederation();
        else
            purchaseRequestPageOld.setCountryOfOriginFieldWithTheValueRussianFederation();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' нажимает на кнопку 'Подписать и отправить'")
    public void supplierClicksOnSignAndSendButton() throws Throwable {
        String stepName = "'Поставщик' нажимает на кнопку 'Подписать и отправить'";
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.clickSignAndSendButton();
        else
            purchaseRequestPageOld.clickSignAndSendButton();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' нажимает на кнопку 'Подписать и отправить' при попозиционном сравнении ценовых предложений")
    public void supplierClicksOnSignAndSendButtonOnPositionalComparisonTrades() throws Throwable {
        String stepName = "'Поставщик' нажимает на кнопку 'Подписать и отправить'";
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.clickSignAndSendButtonWhenPriceOffersForPartOfThePositions();
        else
            purchaseRequestPageOld.clickSignAndSendButtonWhenPriceOffersForPartOfThePositions();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет наличие кнопки 'Отозвать заявку'")
    public void supplierChecksWithdrawRequestButtonExist() throws Throwable {
        String stepName = "'Поставщик' проверяет наличие кнопки 'Отозвать заявку'";
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.checkWithdrawRequestButtonExist();
        else
            purchaseRequestPageOld.checkWithdrawRequestButtonExist();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' № {string} запоминает размер обеспечения и номер заявки для лота № {string} в параметрах теста")
    public void supplierSavesApplicationGuaranteeAndRequestNumber(String supplierNumber, String lotNumber) {
        String stepName = String.format(
                "'Поставщик' № '%s' запоминает размер обеспечения и номер заявки для лота № '%s' в параметрах теста",
                supplierNumber, lotNumber);
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.saveApplicationGuaranteeAndRequestNumber(supplierNumber, lotNumber);
        else
            purchaseRequestPageOld.saveApplicationGuaranteeAndRequestNumber(supplierNumber, lotNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' № {string} подтверждает заявку на участие для лота № {string}")
    public void supplierSubmitsPurchaseRequest(String supplierNumber, String lotNumber) throws Throwable {
        String stepName = String.format(
                "'Поставщик' № '%s' подтверждает заявку на участие для лота № '%s'", supplierNumber, lotNumber);
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая")) {
            purchaseRequestPage.clickSignAndSendButton();
            purchaseRequestPage.checkWithdrawRequestButtonExist();
            purchaseRequestPage.saveApplicationGuaranteeAndRequestNumber(supplierNumber, lotNumber);
        } else {
            purchaseRequestPageOld.clickSignAndSendButton();
            purchaseRequestPageOld.checkWithdrawRequestButtonExist();
            purchaseRequestPageOld.saveApplicationGuaranteeAndRequestNumber(supplierNumber, lotNumber);
        }

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' № {string} подтверждает заявку на участие для лота № {string} на часть позиций")
    public void supplierSubmitsPurchaseRequestWsPartialOffer(String supplierNumber, String lotNumber) throws Throwable {
        String stepName = String.format(
                "'Поставщик' № '%s' подтверждает заявку на участие для лота № '%s' на часть позиций",
                supplierNumber, lotNumber);
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая")) {
            purchaseRequestPage.clickSignAndSendButtonWsPartialOffer();
            purchaseRequestPage.checkWithdrawRequestButtonExist();
            purchaseRequestPage.saveApplicationGuaranteeAndRequestNumber(supplierNumber, lotNumber);
        } else {
            purchaseRequestPageOld.clickSignAndSendButtonWsPartialOffer();
            purchaseRequestPageOld.checkWithdrawRequestButtonExist();
            purchaseRequestPageOld.saveApplicationGuaranteeAndRequestNumber(supplierNumber, lotNumber);
        }

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' № {string} подтверждает заявку на участие для лота № {string} при попозиционном сравнении ценовых предложений")
    public void supplierSubmitsPurchaseRequestOnPositionalComparisonTrades(String supplierNumber, String lotNumber)
            throws Throwable {
        String stepName = String.format(
                "'Поставщик' № '%s' подтверждает заявку на участие для лота № '%s' при попозиционном сравнении " +
                        "ценовых предложений", supplierNumber, lotNumber);
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая")) {
            purchaseRequestPage.clickSignAndSendButton();
            purchaseRequestPage.waitLoadingImage();
            purchaseRequestPage.checkPageUrl("Заявка на участие в режиме просмотра");
            purchaseRequestPage.saveApplicationGuaranteeAndRequestNumber(supplierNumber, lotNumber);
        } else {
            purchaseRequestPageOld.clickSignAndSendButton();
            purchaseRequestPageOld.waitLoadingImage();
            purchaseRequestPageOld.checkPageUrl("Заявка на участие в режиме просмотра старая");
            purchaseRequestPageOld.saveApplicationGuaranteeAndRequestNumber(supplierNumber, lotNumber);
        }

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' № {string} подтверждает заявку на участие для предмета предварительного отбора № {string}")
    public void supplierSubmitsPreselectionObjectRequest(String supplierNumber, String preselectionObjectNumber) throws Throwable {
        String stepName = String.format(
                "'Поставщик' № '%s' подтверждает заявку на участие для предмета предварительного отбора № '%s'",
                supplierNumber, preselectionObjectNumber);
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая")) {
            System.out.println("[ИНФОРМАЦИЯ]: страница [Заявка на участие в ...](новая) не встречалась " +
                    "в 'Предварительном отборе'");
            throw new Throwable("[ОШИБКА] ошибка для прекращения работы теста, если встретится страница " +
                    "[Заявка на участие в ...](новая), так как она ранее не использовалась в 'Предварительном отборе'");
        } else {
            purchaseRequestPageOld.clickSignAndSendButton();
            purchaseRequestPageOld.checkWithdrawRequestButtonExist();
        }

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет статус заявки {string}")
    public void supplierChecksRequestStatus(String requestStatus) throws Throwable {
        String stepName = String.format("'Поставщик' проверяет статус заявки '%s'", requestStatus);
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.checkRequestStatus(requestStatus);
        else
            purchaseRequestPageOld.checkRequestStatus(requestStatus);

        // переключаемся в основное окно
        config.closeActiveWindowsAndSwitchToMainWindowInBrowser(driver);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет, что заявка имеет статус {string}")
    public void supplierChecksThatRequestHasStatus(String requestStatus) throws Throwable {
        String stepName = String.format("'Поставщик' проверяет, что заявка имеет статус '%s'", requestStatus);
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.checkRequestStatus(requestStatus);
        else
            purchaseRequestPageOld.checkRequestStatus(requestStatus);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' проверяет наличие надписи {string}")
    public void supplierChecksIsApplicationItemPartialOfferAllowed(String expectedValue) {
        String stepName = String.format("'Поставщик' проверяет наличие надписи '%s'", expectedValue);
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.checkIsApplicationItemPartialOfferAllowed(expectedValue);
        else
            purchaseRequestPageOld.checkIsApplicationItemPartialOfferAllowed(expectedValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' нажимает на кнопку 'Сохранить'")
    public void supplierClicksOnSaveChangesButton() throws Throwable {
        String stepName = "'Поставщик' нажимает на кнопку 'Сохранить'";
        this.logStepName(stepName);

        timer.start();

        if (config.getParameter("PurchaseRequestPageVersion").equals("новая"))
            purchaseRequestPage.clickOnSaveQuotationChangesButton();
        else
            purchaseRequestPageOld.clickOnSaveQuotationChangesButton();

        timer.printStepTotalTime(stepName);
    }
}
