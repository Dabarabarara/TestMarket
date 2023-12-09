package ru.StepDefinitions.Customer.PrintFormsPreview;

import cucumber.api.java.en.And;

import ru.PageObjects.Customer.PrintFormsPreview.ApplicationViewPage;
import ru.PageObjects.Customer.PrintFormsPreview.PurchaseParticipantViewPage;
import ru.PageObjects.Customer.PrintFormsPreview.TradeLotApplicationResultPrintFormPage;
import ru.StepDefinitions.AbstractStepDefinitions;

/**
 * Класс описывающий шаги работы Заказчика с печатными формами в электронном виде ( предпросмотр ).
 * Created by Kirill G. Rydzyvylo on 23.09.2019.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class CustomerPrintFormsPreviewStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    final PurchaseParticipantViewPage purchaseParticipantViewPage = new PurchaseParticipantViewPage(driver);
    final TradeLotApplicationResultPrintFormPage tradeLotApplicationResultPrintFormPage =
            new TradeLotApplicationResultPrintFormPage(driver);
    final ApplicationViewPage applicationViewPage = new ApplicationViewPage(driver);

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    @And("'Заказчик' проверяет значение поля {string} на странице печатной формы 'Заявка на участие в ...'")
    public void customerComparesFieldValueAndPropertyValue(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет значение поля '%s' на странице печатной формы " +
                "'Заявка на участие в ...'", fieldName);
        this.logStepName(stepName);

        timer.start();

        applicationViewPage.compareFieldValueAndPropertyValue(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} содержит значение {string} на странице печатной формы 'Заявка на участие в ...'")
    public void customerVerifiesFieldContentOnApplicationViewPage(String fieldName, String value) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' содержит значение '%s' " +
                "на странице печатной формы 'Заявка на участие в ...'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        applicationViewPage.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} отсутствует на странице печатной формы 'Заявка на участие в ...'")
    public void customerСhecksThatTheFieldNameHiddenInApplicationViewPage(String fieldName) throws Exception {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' содержит значение " +
                "на странице печатной формы 'Заявка на участие в ...'", fieldName);
        this.logStepName(stepName);

        timer.start();

        applicationViewPage.checkThatTheFieldNameHidden(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле 'Номер в реестре аккредитованных участников закупки' содержит значение, которое соответствует поставщику {string} на странице печатной формы 'Заявка на участие в ...'")
    public void customerChecksRegisterNumberBySupplierNameOnApplicationViewPage(String supplierName) {
        String stepName =
                String.format("''Заказчик' проверяет, что поле 'Номер в реестре аккредитованных участников закупки' " +
                        "содержит значение которое соответствует поставщику '%s' " +
                        "на странице печатной формы 'Заявка на участие ...'", supplierName);
        this.logStepName(stepName);

        timer.start();

        applicationViewPage.checkRegisterNumberBySupplierName(supplierName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} содержит значение {string} на странице печатной формы 'Сведения об Участнике закупки'")
    public void customerVerifiesFieldContentOnPurchaseParticipantViewPage(String fieldName, String value) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' содержит значение '%s' " +
                "на странице печатной формы 'Сведения об Участнике закупки'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        purchaseParticipantViewPage.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле 'Номер в реестре аккредитованных участников закупки' содержит значение, которое соответствует поставщику {string} на странице печатной формы 'Сведения об Участнике закупки'")
    public void customerChecksRegisterNumberBySupplierNameOnPurchaseParticipantViewPage(String supplierName) {
        String stepName =
                String.format("''Заказчик' проверяет, что поле 'Номер в реестре аккредитованных участников закупки' " +
                        "содержит значение, которое соответствует поставщику '%s' " +
                        "на странице печатной формы 'Сведения об Участнике закупки'", supplierName);
        this.logStepName(stepName);

        timer.start();

        purchaseParticipantViewPage.checkRegisterNumberBySupplierName(supplierName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет значение поля {string} на странице печатной формы 'Вторая часть заявки'")
    public void customerComparesFieldValueAndPropertyValueOnSecondPartApplication(String fieldName) {
        String stepName = String.format("'Заказчик' проверяет значение поля '%s' на странице печатной формы " +
                "'Вторая часть заявки'", fieldName);
        this.logStepName(stepName);

        timer.start();

        tradeLotApplicationResultPrintFormPage.compareFieldValueAndPropertyValue(fieldName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле {string} содержит значение {string} на странице печатной формы 'Вторая часть заявки'")
    public void customerVerifiesFieldContentOnSecondPartApplication(String fieldName, String value) {
        String stepName = String.format("'Заказчик' проверяет, что поле '%s' содержит значение '%s' " +
                "на странице печатной формы 'Вторая часть заявки'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        tradeLotApplicationResultPrintFormPage.verifyField(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что поле 'Номер в реестре аккредитованных участников закупки' содержит значение, которое соответствует поставщику {string} на странице печатной формы 'Вторая часть заявки'")
    public void customerChecksRegisterNumberBySupplierNameOnSecondPartApplication(String supplierName) {
        String stepName =
                String.format("''Заказчик' проверяет, что поле 'Номер в реестре аккредитованных участников закупки' " +
                        "содержит значение, которое соответствует поставщику '%s' " +
                        "на странице печатной формы 'Вторая часть заявки'", supplierName);
        this.logStepName(stepName);

        timer.start();

        tradeLotApplicationResultPrintFormPage.checkRegisterNumberBySupplierName(supplierName);

        timer.printStepTotalTime(stepName);
    }
}
