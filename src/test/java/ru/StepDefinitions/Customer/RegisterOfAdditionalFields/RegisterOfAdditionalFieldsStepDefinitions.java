package ru.StepDefinitions.Customer.RegisterOfAdditionalFields;

import cucumber.api.java.en.And;
import ru.StepDefinitions.AbstractStepDefinitions;
import ru.PageObjects.Customer.MyOrganization.RegisterOfAdditionalFieldsPage;

/**
 * Класс описывающий шаги работы Заказчика со страницей [Реестр дополнительных полей].
 * Created by Alexander S. Vasyurenko on 30.04.2021.
 * Updated by Alexander S. Vasyurenko on 11.05.2021.
 */
public class RegisterOfAdditionalFieldsStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final RegisterOfAdditionalFieldsPage registerOfAdditionalFieldsPage = new RegisterOfAdditionalFieldsPage(driver);

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    @And("^'Заказчик' переходит на вкладку с наименование объекта дополнительного поля \"([^\"]*)\"$")
    public void customerGoesToStatusTabByTabName(String tabName) throws Throwable {
        String stepName = String.format("'Заказчик' переходит на вкладку со статусом закупки '%s'", tabName);
        this.logStepName(stepName);

        timer.start();

        registerOfAdditionalFieldsPage.goToFieldTypeTabByName(tabName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет, что флажок {string} {string} на форме создании дополнительного поля")
    public void customerVerifiesCheckBoxState(String checkBoxName, String checkBoxState) {
        String stepName = String.format("'Заказчик А' проверяет, что флажок '%s' '%s'", checkBoxName, checkBoxState);
        this.logStepName(stepName);

        timer.start();

        if (checkBoxState.equals("установлен")) registerOfAdditionalFieldsPage.verifyCheckBoxSelected(checkBoxName);
        else registerOfAdditionalFieldsPage.verifyCheckBoxNotSelected(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' нажимает на кнопку {string} на странице 'Реестр дополнительных полей'")
    public void customerClicksOnButtonByNameInRegisterOfAdditionalFieldsPage(String buttonName) throws Throwable {
        String stepName = String.format("'Заказчик' нажимает на кнопку '%s'", buttonName);
        this.logStepName(stepName);

        timer.start();

        registerOfAdditionalFieldsPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' заполняет поле {string} значением {string} на странице 'Реестр дополнительных полей'")
    public void customerFillsFieldByName(String fieldName, String value) throws Throwable {
        String stepName = String.format("'Заказчик А' заполняет поле '%s' значением '%s'", fieldName, value);
        this.logStepName(stepName);

        timer.start();

        registerOfAdditionalFieldsPage.setFieldClearClickAndSendKeys(fieldName, value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' выбирает значение {string} в списке для поля 'Тип значения' на странице 'Реестр дополнительных полей'")
    public void customerChangesValueType(String valueType) throws Throwable {
        String stepName = String.format("'Заказчик' выбирает значение '%s' в списке для поля 'Тип значения'",
                valueType);
        this.logStepName(stepName);

        timer.start();

        registerOfAdditionalFieldsPage.setValueType(valueType);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' выбирает значение {string} в списке для поля 'Способ закупки' на странице 'Реестр дополнительных полей'")
    public void customerChangesPurchaseMethod(String purchaseMethod) throws Throwable {
        String stepName = String.format("'Заказчик' выбирает значение '%s' в списке для поля 'Способ закупки'",
                purchaseMethod);
        this.logStepName(stepName);

        timer.start();

        registerOfAdditionalFieldsPage.setPurchaseMethod(purchaseMethod);

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' проверяет и удаляет дополнительное поле {string} на странице 'Реестр дополнительных полей'")
    public void customerChecksAndDeletesAdditionalFieldByName(String fieldName) throws Throwable {
        String stepName = String.format("'Заказчик' проверяет и удаляет дополнительное поле '%s' на странице " +
                "'Реестр дополнительных полей'", fieldName);
        this.logStepName(stepName);

        timer.start();

        registerOfAdditionalFieldsPage.deleteAdditionalFieldIfDisplayed(fieldName);

        timer.printStepTotalTime(stepName);
    }
}