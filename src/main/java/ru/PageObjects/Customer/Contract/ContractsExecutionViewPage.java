package ru.PageObjects.Customer.Contract;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы со страницей [Сведения об исполнении договора XXXX.XXXX]
 * ( .kontur.ru/customer/lk/Contracts/ExecutionView/ )
 * ( Главная / Заказчикам / Мои договоры / Сведения об исполнении договора XXXX.XXXX ).
 * Created by Vladimir V. Klochkov on 22.12.2020.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class ContractsExecutionViewPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы
    private static final String PAGE_HEADER_XPATH = "//h2[contains(.,'Сведения об исполнении договора')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о договоре]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о договоре]
    private static final String CONTRACT_INFO_BLOCK_XPATH = "//h3[contains(.,'Сведения о договоре')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер договора]
    private static final String CONTRACT_CARD_NUMBER_XPATH =
            "//label[contains(.,'Номер договора:')]/following-sibling::div[1]/a";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Предмет договора]
    private static final String CONTRACT_SUBJECT_NAME_XPATH =
            "//label[contains(.,'Предмет договора:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки]
    private static final String PURCHASE_NUMBER_XPATH =
            "//label[contains(.,'Номер закупки:')]/following-sibling::div[1]/a";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Заказчик]
    private static final String CUSTOMER_NAME_XPATH =
            "//label[contains(.,'Заказчик:')]/following-sibling::div[1]/a";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Поставщик]
    private static final String SUPPLIER_NAME_XPATH =
            "//label[contains(.,'Поставщик:')]/following-sibling::div[1]/a";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата заключения договора]
    private static final String CONTRACT_CONCLUSION_DATE_XPATH =
            "//label[contains(.,'Дата заключения договора:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения об исполнении договора]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения об исполнении договора]
    private static final String CONTRACT_EXECUTION_INFO_BLOCK_XPATH =
            "//h3[contains(.,'Сведения об исполнении договора')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус]
    private static final String CONTRACT_EXECUTION_STATUS_XPATH =
            "//label[contains(.,'Статус:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер версии сведений]
    private static final String CONTRACT_EXECUTION_VERSION_NUMBER_XPATH =
            "//label[contains(.,'Номер версии сведений:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата создания]
    private static final String CONTRACT_EXECUTION_CREATE_DATE_XPATH =
            "//label[contains(.,'Дата создания:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата публикации]
    private static final String CONTRACT_EXECUTION_PUBLICATION_DATE_XPATH =
            "//label[contains(.,'Дата публикации:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата окончания исполнения договора (отдельного этапа исполнения договора)]
    private static final String COMPLETION_STAGE_END_DATE_XPATH =
            "//label[contains(.,'Дата окончания исполнения договора (отдельного этапа исполнения договора):')]" +
                    "/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата публикации на ЭП]
    private static final String CONTRACT_EXECUTION_SITE_PUBLICATION_DATE_XPATH =
            "//label[contains(.,'Дата публикации на ЭП:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Исполнение договора завершено]
    private static final String CONTRACT_EXECUTION_IS_COMPLETED_XPATH =
            "//label[contains(.,'Исполнение договора завершено:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Прекращение обязательств сторон по договору в связи с окончанием срока действия договора]
    private static final String CONTRACT_EXECUTION_IS_TERMINATION_XPATH =
            "//label[contains(.,'Прекращение обязательств сторон по договору в связи с окончанием срока действия " +
                    "договора:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Начислены неустойки]
    private static final String CONTRACT_EXECUTION_HAS_PENALTY_XPATH =
            "//label[contains(.,'Начислены неустойки:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Информация о неустойках]
    private static final String CONTRACT_EXECUTION_PENALTY_INFO_XPATH =
            "//label[contains(.,'Информация о неустойках:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о позициях]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о позициях]
    private static final String EXECUTION_INFORMATION_ITEMS_BLOCK_XPATH =
            "//h2[contains(.,'Сведения о позициях')]";
    //------------------------------------------------------------------------------------------------------------------

    // region Общее для всех позиций

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Позиция №X]
    private static final String POSITION_X_BLOCK_XPATH = "//h2[contains(.,'Позиция №%s')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Подраздел [Общие сведения о позиции]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок подраздела [Общие сведения о позиции]
    private static final String COMMON_POSITION_INFORMATION_BLOCK_XPATH = "//h2[contains(.,'Общие сведения о позиции')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата оплаты]
    private static final String COMMON_INFO_PAYMENT_DATE_XPATH =
            "//label[contains(.,'Дата оплаты:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Валюта]
    private static final String COMMON_INFO_CURRENCY_CODE_VALUE_XPATH =
            "//label[contains(.,'Валюта:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Сумма оплаты]
    private static final String COMMON_INFO_PAYMENT_XPATH =
            "//label[contains(.,'Сумма оплаты:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Сумма оплаты, руб]
    private static final String COMMON_INFO_RUB_PAYMENT_XPATH =
            "//label[contains(.,'Сумма оплаты, руб:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Авансовый платеж]
    private static final String COMMON_INFO_PREPAID_XPATH =
            "//label[contains(.,'Авансовый платеж:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Подраздел [Сведения позиции исполнения договора по объекту закупки]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок подраздела [Сведения позиции исполнения договора по объекту закупки]
    private static final String POSITION_OBJECTS_BLOCK_XPATH =
            "//h2[contains(.,'Сведения позиции исполнения договора по объекту закупки')]";
    //------------------------------------------------------------------------------------------------------------------

    // region Таблица [Сведения позиции исполнения договора по объекту закупки]

    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Наименование объекта закупки]
    private static final String POSITION_OBJECTS_TABLE_ITEM_ID_COLUMN_XPATH =
            "//div[contains(@id,'ObjectsGrid_')]/div[@class='k-grid-content']//tr/td[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [ОКПД2]
    private static final String POSITION_OBJECTS_TABLE_OKPD2_COLUMN_XPATH =
            "//div[contains(@id,'ObjectsGrid_')]/div[@class='k-grid-content']//tr/td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дата документа]
    private static final String POSITION_OBJECTS_TABLE_DOCUMENT_DATE_COLUMN_XPATH =
            "//div[contains(@id,'ObjectsGrid_')]/div[@class='k-grid-content']//tr/td[3]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Тип документа]
    private static final String POSITION_OBJECTS_TABLE_DOCUMENT_TYPE_ID_COLUMN_XPATH =
            "//div[contains(@id,'ObjectsGrid_')]/div[@class='k-grid-content']//tr/td[4]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Наименование документа]
    private static final String POSITION_OBJECTS_TABLE_DOCUMENT_NAME_COLUMN_XPATH =
            "//div[contains(@id,'ObjectsGrid_')]/div[@class='k-grid-content']//tr/td[5]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Номер документа]
    private static final String POSITION_OBJECTS_TABLE_DOCUMENT_NUMBER_COLUMN_XPATH =
            "//div[contains(@id,'ObjectsGrid_')]/div[@class='k-grid-content']//tr/td[6]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Количество]
    private static final String POSITION_OBJECTS_TABLE_QUANTITY_COLUMN_XPATH =
            "//div[contains(@id,'ObjectsGrid_')]/div[@class='k-grid-content']//tr/td[7]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Цена за единицу]
    private static final String POSITION_OBJECTS_TABLE_UNIT_PRICE_COLUMN_XPATH =
            "//div[contains(@id,'ObjectsGrid_')]/div[@class='k-grid-content']//tr/td[8]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Единица измерения]
    private static final String POSITION_OBJECTS_TABLE_UNIT_COLUMN_XPATH =
            "//div[contains(@id,'ObjectsGrid_')]/div[@class='k-grid-content']//tr/td[9]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Тип финансового обеспечения]
    private static final String POSITION_OBJECTS_TABLE_FINANCIAL_SUPPORT_COLUMN_XPATH =
            "//div[contains(@id,'ObjectsGrid_')]/div[@class='k-grid-content']//tr/td[10]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // endregion

    // endregion

    // region Раздел [Документы]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Документы]
    private static final String DOCUMENTS_BLOCK_XPATH = "//h3[contains(.,'Документы')]";
    //------------------------------------------------------------------------------------------------------------------

    // region Таблица [Документы]

    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Название документа]
    private static final String DOCUMENTS_TABLE_DOCUMENT_NAME_COLUMN_XPATH =
            "//div[@id='DocumentsGrid']//tbody/tr/td[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Файл документа]
    private static final String DOCUMENTS_TABLE_DOCUMENT_FILE_LINK_COLUMN_XPATH =
            "//div[@id='DocumentsGrid']//tbody/tr/td[2]/a";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Сведения об ЭЦП]
    private static final String DOCUMENTS_TABLE_DOCUMENT_SIGNATURE_LINK_COLUMN_XPATH =
            "//div[@id='DocumentsGrid']//tbody/tr/td[3]/a";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // endregion

    // region Блок управляющих кнопок в нижней части страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отменить]
    private static final String CANCEL_BUTTON_XPATH = "//button[contains(.,'Отменить')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Внести изменения]
    private static final String MODIFY_BUTTON_XPATH = "//button[contains(.,'Внести изменения')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Подвал страницы

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Сервер]
    private static final String SERVER_VERSION_XPATH = "//li[contains(.,'Сервер: ') or contains(.,'SERVER: ')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary blockNames = new Dictionary();        // основные разделы сведений об исполнении договора
    private final Dictionary fieldNames = new Dictionary();        // все поля на странице
    private final Dictionary buttonNames = new Dictionary();       // все кнопки на странице
    private final Dictionary posObjectsColumns = new Dictionary(); // все колонки таблицы в разделе [Сведения позиции..]
    private final Dictionary documentsColumns = new Dictionary();  // все колонки таблицы в разделе [Документы]

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public ContractsExecutionViewPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Заголовок страницы", PAGE_HEADER_XPATH);
        blockNames.add("Сведения о договоре", CONTRACT_INFO_BLOCK_XPATH);
        blockNames.add("Сведения об исполнении договора", CONTRACT_EXECUTION_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о позициях", EXECUTION_INFORMATION_ITEMS_BLOCK_XPATH);
        blockNames.add("Позиция №X", POSITION_X_BLOCK_XPATH);
        blockNames.add("Общие сведения о позиции", COMMON_POSITION_INFORMATION_BLOCK_XPATH);
        blockNames.add("Сведения позиции исполнения договора по объекту закупки", POSITION_OBJECTS_BLOCK_XPATH);
        blockNames.add("Документы", DOCUMENTS_BLOCK_XPATH);
        blockNames.add("Подвал страницы", SERVER_VERSION_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Номер договора", CONTRACT_CARD_NUMBER_XPATH);
        fieldNames.add("Предмет договора", CONTRACT_SUBJECT_NAME_XPATH);
        fieldNames.add("Номер закупки", PURCHASE_NUMBER_XPATH);
        fieldNames.add("Заказчик", CUSTOMER_NAME_XPATH);
        fieldNames.add("Поставщик", SUPPLIER_NAME_XPATH);
        fieldNames.add("Дата заключения договора", CONTRACT_CONCLUSION_DATE_XPATH);
        fieldNames.add("Статус", CONTRACT_EXECUTION_STATUS_XPATH);
        fieldNames.add("Номер версии сведений", CONTRACT_EXECUTION_VERSION_NUMBER_XPATH);
        fieldNames.add("Дата создания", CONTRACT_EXECUTION_CREATE_DATE_XPATH);
        fieldNames.add("Дата публикации", CONTRACT_EXECUTION_PUBLICATION_DATE_XPATH);
        fieldNames.add("Дата окончания исполнения договора", COMPLETION_STAGE_END_DATE_XPATH);
        fieldNames.add("Дата публикации на ЭП", CONTRACT_EXECUTION_SITE_PUBLICATION_DATE_XPATH);
        fieldNames.add("Исполнение договора завершено", CONTRACT_EXECUTION_IS_COMPLETED_XPATH);
        fieldNames.add("Прекращение обязательств сторон по договору в связи с окончанием срока действия договора",
                CONTRACT_EXECUTION_IS_TERMINATION_XPATH);
        fieldNames.add("Начислены неустойки", CONTRACT_EXECUTION_HAS_PENALTY_XPATH);
        fieldNames.add("Информация о неустойках", CONTRACT_EXECUTION_PENALTY_INFO_XPATH);
        fieldNames.add("Дата оплаты", COMMON_INFO_PAYMENT_DATE_XPATH);
        fieldNames.add("Валюта", COMMON_INFO_CURRENCY_CODE_VALUE_XPATH);
        fieldNames.add("Сумма оплаты", COMMON_INFO_PAYMENT_XPATH);
        fieldNames.add("Сумма оплаты, руб", COMMON_INFO_RUB_PAYMENT_XPATH);
        fieldNames.add("Авансовый платеж", COMMON_INFO_PREPAID_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Номер договора", CONTRACT_CARD_NUMBER_XPATH);
        buttonNames.add("Отменить", CANCEL_BUTTON_XPATH);
        buttonNames.add("Внести изменения", MODIFY_BUTTON_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        posObjectsColumns.add("Наименование объекта закупки", POSITION_OBJECTS_TABLE_ITEM_ID_COLUMN_XPATH);
        posObjectsColumns.add("ОКПД2", POSITION_OBJECTS_TABLE_OKPD2_COLUMN_XPATH);
        posObjectsColumns.add("Дата документа", POSITION_OBJECTS_TABLE_DOCUMENT_DATE_COLUMN_XPATH);
        posObjectsColumns.add("Тип документа", POSITION_OBJECTS_TABLE_DOCUMENT_TYPE_ID_COLUMN_XPATH);
        posObjectsColumns.add("Наименование документа", POSITION_OBJECTS_TABLE_DOCUMENT_NAME_COLUMN_XPATH);
        posObjectsColumns.add("Номер документа", POSITION_OBJECTS_TABLE_DOCUMENT_NUMBER_COLUMN_XPATH);
        posObjectsColumns.add("Количество", POSITION_OBJECTS_TABLE_QUANTITY_COLUMN_XPATH);
        posObjectsColumns.add("Цена за единицу", POSITION_OBJECTS_TABLE_UNIT_PRICE_COLUMN_XPATH);
        posObjectsColumns.add("Единица измерения", POSITION_OBJECTS_TABLE_UNIT_COLUMN_XPATH);
        posObjectsColumns.add("Тип финансового обеспечения", POSITION_OBJECTS_TABLE_FINANCIAL_SUPPORT_COLUMN_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        documentsColumns.add("Название документа", DOCUMENTS_TABLE_DOCUMENT_NAME_COLUMN_XPATH);
        documentsColumns.add("Файл документа", DOCUMENTS_TABLE_DOCUMENT_FILE_LINK_COLUMN_XPATH);
        documentsColumns.add("Сведения об ЭЦП", DOCUMENTS_TABLE_DOCUMENT_SIGNATURE_LINK_COLUMN_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Раздел [Сведения о позициях]

    // region Подраздел [Общие сведения о позиции]

    // endregion

    // region Подраздел [Сведения позиции исполнения договора по объекту закупки]

    // region Таблица [Сведения позиции исполнения договора по объекту закупки]

    /**
     * Проверяет текст ячейки в таблице [Сведения позиции исполнения договора по объекту закупки] для столбца с
     * указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     */
    public void verifyCellByNameInLineByNumberFromPositionInformationTableForText(
            String columnName, String rowNumber, String cellValue) {
        this.verifyCellInTableByColumnElementsAndLineNumberForText(
                "Сведения позиции исполнения договора по объекту закупки", columnName,
                $$(this.getBy(posObjectsColumns.find(columnName))), rowNumber, cellValue);
    }

    //endregion

    // endregion

    // endregion

    // region Раздел [Документы]

    // region Таблица [Документы]

    /**
     * Проверяет текст ячейки в таблице [Документы] для столбца с указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     */
    public void verifyCellByNameInLineByNumberFromDocumentsTableForText(
            String columnName, String rowNumber, String cellValue) {
        this.verifyCellInTableByColumnElementsAndLineNumberForText("Документы", columnName,
                $$(this.getBy(documentsColumns.find(columnName))), rowNumber, cellValue);
    }

    /**
     * Переходит по ссылке в таблице [Документы] для столбца с указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     */
    public void clickOnLinkInLineByNumberFromDocumentsTable(String columnName, int rowNumber) throws Exception {
        int line = rowNumber - 1;
        ElementsCollection linesWithButtons = $$(this.getBy(documentsColumns.find(columnName)));
        System.out.printf("[ИНФОРМАЦИЯ]: в столбце [%s] обнаружено [%d] строк.%n", columnName, linesWithButtons.size());
        Assert.assertTrue(String.format("[ОШИБКА]: передан некорректный номер строки: [%d]", rowNumber),
                line >= 0 && line < linesWithButtons.size());
        this.scrollToCenter(linesWithButtons.get(line));
        linesWithButtons.get(line).waitUntil(visible, timeout, polling).shouldBe(clickable);
        this.clickInElementJS(linesWithButtons.get(line));
        this.logPressedButtonName(columnName);
    }

    // endregion

    // endregion

    // region Общие методы для работы с элементами этой страницы

    /**
     * Открывает свернутый блок полей на форме по его порядковому номеру ( например - Позиция №1 ).
     *
     * @param blockName   имя блока
     * @param blockNumber порядковый номер блока
     */
    public void openParameterizedBlockForView(String blockName, String blockNumber) throws Exception {
        SelenideElement block = $(this.getBy(String.format(blockNames.find(blockName), blockNumber)));
        this.scrollToCenter(block);
        block.shouldBe(visible).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено открытие блока полей [%s].%n", blockName);
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * Переходит к блоку полей на форме.
     *
     * @param blockName имя блока
     */
    public void gotoBlock(String blockName) throws Exception {
        SelenideElement block = $(this.getBy(blockNames.find(blockName)));
        this.scrollToCenter(block);
        block.shouldBe(visible);
        System.out.printf("[ИНФОРМАЦИЯ]: произведен переход к блоку полей [%s].%n", blockName);
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * Проверяет не пустое значение поля.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение, которое должно содержаться в поле
     */
    public void verifyField(String fieldName, String value) {
        String message = String.format("[ОШИБКА]: значение [%s] не содержится в поле [%s]", value, fieldName);
        By field = this.getBy(fieldNames.find(fieldName));

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        Assert.assertTrue(message, this.verifyFieldContent(field, value));
    }

    /**
     * Проверяет значение поля ( в том числе и пустое ).
     *
     * @param fieldName имя поля
     * @param value     требуемое значение, которое должно содержаться в поле
     */
    public void verifyFieldContentOrEmptyField(String fieldName, String value) {
        String message = String.format("[ОШИБКА]: значение [%s] не содержится в поле [%s]", value, fieldName);
        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        SelenideElement field = $(this.getBy(fieldNames.find(fieldName)));
        field.waitUntil(exist, timeout, polling);
        if (value.trim().isEmpty()) Assert.assertTrue(message, field.getText().trim().isEmpty());
        else Assert.assertTrue(message, field.getText().contains(value));
    }

    /**
     * Проверяет, что поле содержит не пустое значение.
     *
     * @param fieldName имя поля
     */
    public void verifyFieldIsNotEmpty(String fieldName) {
        String message = String.format("[ОШИБКА]: поле [%s] содержит пустое значение", fieldName);
        By field = this.getBy(fieldNames.find(fieldName));
        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] на не пустое значение.%n", fieldName);
        Assert.assertTrue(message, this.verifyFieldIsNotEmpty(field));
    }

    /**
     * Проверяет, что поле содержит дату в виде строки соответствующую заданному шаблону даты.
     *
     * @param fieldName имя поля, содержащего строковое значение даты
     * @param pattern   строковое значение шаблона даты
     */
    public void verifyDateFieldForPattern(String fieldName, String pattern) {
        this.verifyDateFieldForPattern(fieldName, $(this.getBy(fieldNames.find(fieldName))), pattern);
    }

    /**
     * Проверяет, что кнопка отображается на странице и разрешена (visible, enabled).
     *
     * @param controlName имя элемента
     */
    public void isControlClickable(String controlName) {
        this.isControlClickable(controlName, $(this.getBy(buttonNames.find(controlName))));
    }

    /**
     * Делает щелчок мышью по указанной кнопке.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.clickInElementJS(this.getBy(buttonNames.find(buttonName)));
        this.logPressedButtonName(buttonName);
        TimeUnit.SECONDS.sleep(15);
    }

    // endregion
}
