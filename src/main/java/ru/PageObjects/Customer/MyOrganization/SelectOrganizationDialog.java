package ru.PageObjects.Customer.MyOrganization;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы с диалоговым окном [Выбрать организацию]
 * ( после нажатия на кнопку [Выбрать] на странице [Делегирование доступа организации] ).
 * Created by Kirill G. Rydzyvylo on 22.11.2019.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class SelectOrganizationDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок диалогового окна [Выбрать организацию]
    private static final String WINDOW_HEADER_XPATH = "//span[@class='ui-dialog-title']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary fieldNames = new Dictionary();    // все поля диалогового окна
    private final Dictionary buttonNames = new Dictionary();   // все кнопки диалогового окна
    private final Dictionary checkBoxNames = new Dictionary(); // все флажки диалогового окна
    private final Dictionary tableColumns = new Dictionary();  // колонки таблицы с результатами поиска

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public SelectOrganizationDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Наименование", "BaseMainContent_MainContent_txtSearchByName");
        fieldNames.add("ИНН", "BaseMainContent_MainContent_txtSearchInn_txtText");
        fieldNames.add("КПП", "BaseMainContent_MainContent_txtSearchByKpp");
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Очистить", "BaseMainContent_MainContent_lbtClear");
        buttonNames.add("Поиск", "BaseMainContent_MainContent_btnSearch");
        buttonNames.add("Выбрать", "//button/span[contains(., 'Выбрать')]/..");
        buttonNames.add("Отмена", "//button/span[contains(., 'Отмена')]/..");
        //--------------------------------------------------------------------------------------------------------------
        checkBoxNames.add("Отметить организацию",
                "//*[@id='BaseMainContent_MainContent_jqgCompany']//input[@class='js-chbCompany']");
        //--------------------------------------------------------------------------------------------------------------
        tableColumns.add("Организация",
                "//*[@id='BaseMainContent_MainContent_jqgCompany']/tbody/tr[contains(@class,'jqgrow')]/td[3]");
        tableColumns.add("Полное наименование",
                "//*[@id='BaseMainContent_MainContent_jqgCompany']/tbody/tr[contains(@class,'jqgrow')]/td[4]");
        tableColumns.add("ИНН",
                "//*[@id='BaseMainContent_MainContent_jqgCompany']/tbody/tr[contains(@class,'jqgrow')]/td[5]");
        tableColumns.add("КПП",
                "//*[@id='BaseMainContent_MainContent_jqgCompany']/tbody/tr[contains(@class,'jqgrow')]/td[6]");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна [Выбрать организацию].
     *
     * @return диалоговое окно [Выбрать организацию]
     */
    public SelectOrganizationDialog ifDialogOpened() throws Exception {
        this.waitLoadingRectangle("5");
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Выбрать организацию] не обнаружено",
                $(this.getBy(WINDOW_HEADER_XPATH)).waitUntil(exist, timeout, polling).isDisplayed());

        return this;
    }

    /**
     * Производит нажатие на кнопку.
     *
     * @param buttonName имя кнопки
     * @return диалоговое окно [Выбрать организацию]
     */
    public SelectOrganizationDialog clickOnButton(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.clickInElementJS(this.getBy(buttonNames.find(buttonName)));
        this.logPressedButtonName(buttonName);
        this.waitLoadingRectangle();

        return this;
    }

    /**
     * Устанавливает значение текстового поля.
     *
     * @param fieldName имя текстового поля
     * @param value     требуемое значение поля
     * @return диалоговое окно [Выбрать организацию]
     */
    public SelectOrganizationDialog setField(String fieldName, String value) throws Exception {
        SelenideElement field = $(this.getBy(fieldNames.find(fieldName)));
        field.waitUntil(visible, timeout, polling).sendKeys(value);
        TimeUnit.SECONDS.sleep(3);
        Assert.assertEquals(String.format("[ОШИБКА]: не удалось установить значение [%s] в поле [%s]", value, fieldName),
                value, field.getValue());
        this.logFilledFieldName(fieldName, value);

        return this;
    }

    /**
     * Проверяет количество строк в результатах поиска.
     *
     * @param expectedSize ожидаемое количество строк в результатах поиска
     * @return диалоговое окно [Выбрать организацию]
     */
    public SelectOrganizationDialog checkRowCountInSearchResults(int expectedSize) {
        ElementsCollection rows = $$(this.getBy(checkBoxNames.find("Отметить организацию")));
        System.out.printf("[ИНФОРМАЦИЯ]: количество строк в результатах поиска: [%d].%n", rows.size());
        Assert.assertEquals("[ОШИБКА]: не верное количество строк в результатах поиска",
                expectedSize, rows.size());

        return this;
    }

    /**
     * Устанавливает или сбрасывает флажок с указанным именем.
     *
     * @param checkBoxName имя флажка
     * @return диалоговое окно [Выбрать организацию]
     */
    public SelectOrganizationDialog setCheckBoxValue(String checkBoxName) {
        String message = "[ОШИБКА]: Состояние флажка [%s] не изменилось после щелчка мышью.";

        boolean oldValue = this.getCheckBoxValue(checkBoxName);
        $(this.getBy(checkBoxNames.find(checkBoxName))).waitUntil(exist, timeout, polling).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено изменение состояния флажка: [%s].%n", checkBoxName);
        boolean newValue = this.getCheckBoxValue(checkBoxName);
        System.out.printf("[ИНФОРМАЦИЯ]: флажок: [%s] находится %s отмеченном состоянии.%n",
                checkBoxName, ((newValue) ? "в" : "в не"));
        Assert.assertNotEquals(String.format(message, checkBoxName), oldValue, newValue);

        return this;
    }

    /**
     * Возвращает значение флажка с указанным именем.
     *
     * @param checkBoxName имя флажка
     * @return значение флажка
     */
    private boolean getCheckBoxValue(String checkBoxName) {
        $(this.getBy(checkBoxNames.find(checkBoxName))).waitUntil(exist, timeout, polling).shouldBe(visible);

        return $(this.getBy(checkBoxNames.find(checkBoxName))).isSelected();
    }

    /**
     * Проверяет текст ячейки в таблице столбца с указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     * @return диалоговое окно [Выбрать организацию]
     */
    public SelectOrganizationDialog verifyCellByColumnNameAndLineNumberForText(
            String columnName, String rowNumber, String cellValue) {
        this.verifyCellInTableByColumnElementsAndLineNumberForText("Выбрать организацию",
                columnName, $$(this.getBy(tableColumns.find(columnName))), rowNumber, cellValue);

        return this;
    }

    /**
     * Запоминает имя организации-партнера в параметрах теста.
     *
     * @param name имя параметра теста для сохранения информации
     * @return диалоговое окно [Выбрать организацию]
     */
    public SelectOrganizationDialog storePartnerOrganizationNameInTestParameters(String name) {
        ElementsCollection names = $$(this.getBy(tableColumns.find("Организация")));
        System.out.printf("[ИНФОРМАЦИЯ]: количество строк в результатах поиска: [%d].%n", names.size());
        Assert.assertEquals("[ОШИБКА]: не верное количество строк в результатах поиска",
                1, names.size());
        config.setParameter(name,
                names.get(0).waitUntil(visible, timeout, polling).getText());
        System.out.printf("[ИНФОРМАЦИЯ]: получено имя организации-партнера: [%s].%n", names.get(0).getText());

        return this;
    }

    /**
     * Нажимает на кнопку для закрытия диалогового окна.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButtonToCloseDialog(String buttonName) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие кнопки [%s] в диалоговом окне [Выбрать организацию].%n",
                buttonName);
        $(this.getBy(buttonNames.find(buttonName))).waitUntil(clickable, timeout, polling).click();
        this.waitForPageLoad();
        $(this.getBy(WINDOW_HEADER_XPATH)).shouldBe(disappear);
    }
}
