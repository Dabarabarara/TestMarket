package ru.PageObjects.Customer.Contract;

import Helpers.Dictionary;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы со страницей [Сведения об исполнении договора XXXX.XXXX]
 * ( .kontur.ru/customer/lk/Contracts/ExecutionEdit?dealId= )
 * ( Главная / Заказчикам / Мои договоры / Сведения об исполнении договора XXXX.XXXX ).
 * Created by Vladimir V. Klochkov on 10.12.2020.
 * Updated by Vladimir V. Klochkov on 16.03.2021.
 */
public class ContractsExecutionEditPage extends CommonCustomerPage {
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
    private static final String CONTRACT_EXECUTION_CREATE_DATE_ID = "CreateDate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата публикации]
    private static final String CONTRACT_EXECUTION_PUBLICATION_DATE_ID = "PublicationDate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата окончания исполнения договора (отдельного этапа исполнения договора)]
    private static final String COMPLETION_STAGE_END_DATE_ID = "CompletionStageEndDate";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Исполнение договора завершено]
    private static final String CONTRACT_EXECUTION_IS_COMPLETED_ID = "IsCompleted";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Прекращение обязательств сторон по договору в связи с окончанием срока действия договора]
    private static final String CONTRACT_EXECUTION_IS_TERMINATION_ID = "IsTermination";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Начислены неустойки]
    private static final String CONTRACT_EXECUTION_HAS_PENALTY_ID = "HasPenalty";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Информация о неустойках]
    private static final String CONTRACT_EXECUTION_PENALTY_INFO_XPATH = "//textarea[@name='PenaltyInfo']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Позиции сведений об исполнении]

    // region Общее для всех позиций

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Позиции сведений об исполнении]
    private static final String EXECUTION_INFORMATION_ITEMS_BLOCK_XPATH =
            "//h3[contains(.,'Позиции сведений об исполнении')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить позицию]
    private static final String ADD_POSITION_BUTTON_XPATH =
            "//span[contains(@class,'k-link k-link_add-position')]";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Позиция X]
    private static final String POSITION_X_TAB_XPATH = "//span[contains(@title,'Позиция %s')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Закладка [Общая информация]

    //==================================================================================================================
    // Закладка [Общая информация]
    private static final String COMMON_POSITION_INFORMATION_TAB_XPATH =
            "//a[@class='k-link'][contains(.,'Общая информация')]";
    //==================================================================================================================
    // Поле [Дата оплаты]
    private static final String COMMON_INFO_PAYMENT_DATE_ID = "CurrentPosition_CommonInfo_PaymentDate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Валюта] (кнопка [V] - развернуть список)
    private static final String COMMON_INFO_CURRENCY_CODE_LIST_CLOSED_XPATH =
            "//span[@aria-controls='CurrentPosition_CommonInfo_CurrencyCode_listbox']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Валюта] (список возможных значений поля открыт) -> требуемое значение в списке
    private static final String COMMON_INFO_CURRENCY_CODE_VALUE_IN_OPENED_LIST_XPATH =
            "//ul[@id='CurrentPosition_CommonInfo_CurrencyCode_listbox']/li[contains(.,'Российский рубль (RUB)')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Валюта] (текущее значение)
    private static final String COMMON_INFO_CURRENCY_CODE_VALUE_XPATH =
            "//input[@name='CurrentPosition.CommonInfo.CurrencyCode_input']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Фактически оплачено]
    private static final String COMMON_INFO_PAYMENT_XPATH =
            "//label[@for='CurrentPosition_CommonInfo_Payment']/following-sibling::div[1]//input[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Фактически оплачено, руб.]
    private static final String COMMON_INFO_RUB_PAYMENT_XPATH =
            "//label[@for='CurrentPosition_CommonInfo_RubPayment']/following-sibling::div[1]//input[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Авансовый платеж]
    private static final String COMMON_INFO_PREPAID_XPATH = "//input[@name='CurrentPosition.CommonInfo.Prepaid']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Закладка [Объекты позиции]

    //==================================================================================================================
    // Закладка [Объекты позиции]
    private static final String POSITION_OBJECTS_TAB_XPATH = "//a[@class='k-link'][contains(.,'Объекты позиции')]";
    //==================================================================================================================

    // region Таблица [Объекты позиции сведений об исполнении]

    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Наименование объекта закупки]
    private static final String POSITION_OBJECTS_TABLE_ITEM_ID_COLUMN_XPATH =
            "//div[contains(@class,'executionObjectsGrid')]/div[@class='k-grid-content']//tr/td[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [ОКПД2]
    private static final String POSITION_OBJECTS_TABLE_OKPD2_COLUMN_XPATH =
            "//div[contains(@class,'executionObjectsGrid')]/div[@class='k-grid-content']//tr/td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дата документа]
    private static final String POSITION_OBJECTS_TABLE_DOCUMENT_DATE_COLUMN_XPATH =
            "//div[contains(@class,'executionObjectsGrid')]/div[@class='k-grid-content']//tr/td[3]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Тип документа]
    private static final String POSITION_OBJECTS_TABLE_DOCUMENT_TYPE_ID_COLUMN_XPATH =
            "//div[contains(@class,'executionObjectsGrid')]/div[@class='k-grid-content']//tr/td[4]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Наименование документа]
    private static final String POSITION_OBJECTS_TABLE_DOCUMENT_NAME_COLUMN_XPATH =
            "//div[contains(@class,'executionObjectsGrid')]/div[@class='k-grid-content']//tr/td[5]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер (объекта позиции)]
    private static final String POSITION_OBJECTS_NUMBER_XPATH =
            "//label[contains(.,'Номер:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование объекта закупки] (кнопка [V] - развернуть список)
    private static final String POSITION_OBJECTS_ITEM_ID_LIST_CLOSED_XPATH =
            "//span[@aria-owns='CurrentObject_ItemId_listbox']//span[@class='k-icon k-i-arrow-s']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование объекта закупки] (список возможных значений поля открыт) -> требуемое значение в списке
    private static final String POSITION_OBJECTS_ITEM_ID_VALUE_IN_OPENED_LIST_XPATH =
            "//ul[@id='CurrentObject_ItemId_listbox']/li[contains(.,'Товар 1')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование объекта закупки] (текущее значение)
    private static final String POSITION_OBJECTS_ITEM_ID_VALUE_XPATH =
            "//span[@aria-owns='CurrentObject_ItemId_listbox']//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [ОКПД2 (объекта позиции)]
    private static final String POSITION_OBJECTS_OKPD2_ID = "CurrentObject_ItemOkpd2";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата (объекта позиции)]
    private static final String POSITION_OBJECTS_DOCUMENT_DATE_ID = "CurrentObject_DocumentDate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип документа] (кнопка [V] - развернуть список)
    private static final String POSITION_OBJECTS_DOCUMENT_TYPE_ID_LIST_CLOSED_XPATH =
            "//span[@aria-owns='CurrentObject_DocumentTypeId_listbox']//span[@class='k-icon k-i-arrow-s']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип документа] (список возможных значений поля открыт) -> требуемое значение в списке
    private static final String POSITION_OBJECTS_DOCUMENT_TYPE_ID_VALUE_IN_OPENED_LIST_XPATH =
            "//ul[@id='CurrentObject_DocumentTypeId_listbox']/li[contains(.,'01 - Товарная накладная')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип документа] (текущее значение)
    private static final String POSITION_OBJECTS_DOCUMENT_TYPE_ID_VALUE_XPATH =
            "//span[@aria-owns='CurrentObject_DocumentTypeId_listbox']//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование документа (объекта позиции)]
    private static final String POSITION_OBJECTS_DOCUMENT_NAME_XPATH = "//input[@name='CurrentObject.DocumentName']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер документа (объекта позиции)]
    private static final String POSITION_OBJECTS_DOCUMENT_NUMBER_XPATH = "//input[@name='CurrentObject.DocumentNumber']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Количество (объекта позиции)]
    private static final String POSITION_OBJECTS_QUANTITY_ID = "CurrentObject_Quantity";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Цена за единицу (объекта позиции)]
    private static final String POSITION_OBJECTS_UNIT_PRICE_XPATH =
            "//input[@id='CurrentObject_UnitPrice']/preceding-sibling::input[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Единица измерения (код ОКЕИ)] (кнопка [V] - развернуть список)
    private static final String POSITION_OBJECTS_OKEI_LIST_CLOSED_XPATH =
            "//span[@aria-controls='CurrentObject_Okei_listbox']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Единица измерения (код ОКЕИ)] (список возможных значений поля открыт) -> требуемое значение в списке
    private static final String POSITION_OBJECTS_OKEI_VALUE_IN_OPENED_LIST_XPATH =
            "//ul[@id='CurrentObject_Okei_listbox']/li[contains(.,'Ампер (260)')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Единица измерения (код ОКЕИ)] (текущее значение)
    private static final String POSITION_OBJECTS_OKEI_VALUE_XPATH = "//input[@name='CurrentObject.Okei_input']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип финансового обеспечения (объекта позиции)]
    private static final String POSITION_OBJECTS_TYPE_PROVISION_XPATH =
            "//span[@aria-owns='CurrentObject_TypeProvision_listbox']//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить (объект позиции)]
    private static final String POSITION_OBJECTS_ADD_BUTTON_XPATH = "//button[contains(.,'Добавить')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // endregion

    // region Раздел [Документы]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Документы]
    private static final String DOCUMENTS_BLOCK_XPATH = "//h3[contains(.,'Документы')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Прикрепить файл]
    private static final String UPLOAD_FILE_EXECUTION_BUTTON_ID = "UploadFile_Execution";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка для скачивания первого из прикрепленных к сведениям об исполнении договора файлов
    private static final String DOWNLOAD_FILE_EXECUTION_XPATH =
            "//tr[1]//a[contains(@href, '.kontur.ru/files/FileDownloadHandler.ashx?fileguid=')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Блок управляющих кнопок в нижней части страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Сохранить]
    private static final String SAVE_DRAFT_BUTTON_XPATH = "//button[contains(.,'Сохранить')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Опубликовать]
    private static final String PUBLISH_BUTTON_XPATH = "//button[contains(.,'Опубликовать')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Удалить]
    private static final String REMOVE_BUTTON_XPATH = "//button[contains(.,'Удалить')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Всплывающее окно [Календарь]

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Закрыть - окно Календарь]
    private static final String DATEPICKER_HIDE_BUTTON_XPATH = "//div[@id='ui-datepicker-div']//button[text()='Закрыть']";
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
    private final Dictionary tabNames = new Dictionary();          // основные разделы позиций
    private final Dictionary checkBoxNames = new Dictionary();     // все флажки на странице
    private final Dictionary fieldNames = new Dictionary();        // все поля на странице
    private final Dictionary buttonNames = new Dictionary();       // все кнопки на странице
    private final Dictionary posObjectsColumns = new Dictionary(); // все колонки таблицы в разделе [Объекты позиции...]

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public ContractsExecutionEditPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Заголовок страницы", PAGE_HEADER_XPATH);
        blockNames.add("Сведения о договоре", CONTRACT_INFO_BLOCK_XPATH);
        blockNames.add("Сведения об исполнении договора", CONTRACT_EXECUTION_INFO_BLOCK_XPATH);
        blockNames.add("Позиции сведений об исполнении", EXECUTION_INFORMATION_ITEMS_BLOCK_XPATH);
        blockNames.add("Документы", DOCUMENTS_BLOCK_XPATH);
        blockNames.add("Подвал страницы", SERVER_VERSION_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        tabNames.add("Позиция X", POSITION_X_TAB_XPATH);
        tabNames.add("Общая информация", COMMON_POSITION_INFORMATION_TAB_XPATH);
        tabNames.add("Объекты позиции", POSITION_OBJECTS_TAB_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        checkBoxNames.add("Исполнение договора завершено", CONTRACT_EXECUTION_IS_COMPLETED_ID);
        checkBoxNames.add("Прекращение обязательств сторон по договору в связи с окончанием срока действия договора",
                CONTRACT_EXECUTION_IS_TERMINATION_ID);
        checkBoxNames.add("Начислены неустойки", CONTRACT_EXECUTION_HAS_PENALTY_ID);
        checkBoxNames.add("Авансовый платеж", COMMON_INFO_PREPAID_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Номер договора", CONTRACT_CARD_NUMBER_XPATH);
        fieldNames.add("Предмет договора", CONTRACT_SUBJECT_NAME_XPATH);
        fieldNames.add("Номер закупки", PURCHASE_NUMBER_XPATH);
        fieldNames.add("Заказчик", CUSTOMER_NAME_XPATH);
        fieldNames.add("Поставщик", SUPPLIER_NAME_XPATH);
        fieldNames.add("Дата заключения договора", CONTRACT_CONCLUSION_DATE_XPATH);
        fieldNames.add("Статус", CONTRACT_EXECUTION_STATUS_XPATH);
        fieldNames.add("Номер версии сведений", CONTRACT_EXECUTION_VERSION_NUMBER_XPATH);
        fieldNames.add("Дата создания", CONTRACT_EXECUTION_CREATE_DATE_ID);
        fieldNames.add("Дата публикации", CONTRACT_EXECUTION_PUBLICATION_DATE_ID);
        fieldNames.add("Дата окончания исполнения договора", COMPLETION_STAGE_END_DATE_ID);
        fieldNames.add("Информация о неустойках", CONTRACT_EXECUTION_PENALTY_INFO_XPATH);
        fieldNames.add("Дата оплаты", COMMON_INFO_PAYMENT_DATE_ID);
        fieldNames.add("Валюта развернуть список", COMMON_INFO_CURRENCY_CODE_LIST_CLOSED_XPATH);
        fieldNames.add("Валюта требуемое значение в списке", COMMON_INFO_CURRENCY_CODE_VALUE_IN_OPENED_LIST_XPATH);
        fieldNames.add("Валюта текущее значение", COMMON_INFO_CURRENCY_CODE_VALUE_XPATH);
        fieldNames.add("Фактически оплачено", COMMON_INFO_PAYMENT_XPATH);
        fieldNames.add("Фактически оплачено, руб.", COMMON_INFO_RUB_PAYMENT_XPATH);
        fieldNames.add("Номер объекта позиции", POSITION_OBJECTS_NUMBER_XPATH);
        fieldNames.add("Наименование объекта закупки развернуть список", POSITION_OBJECTS_ITEM_ID_LIST_CLOSED_XPATH);
        fieldNames.add("Наименование объекта закупки требуемое значение в списке",
                POSITION_OBJECTS_ITEM_ID_VALUE_IN_OPENED_LIST_XPATH);
        fieldNames.add("Наименование объекта закупки текущее значение", POSITION_OBJECTS_ITEM_ID_VALUE_XPATH);
        fieldNames.add("ОКПД2 объекта позиции", POSITION_OBJECTS_OKPD2_ID);
        fieldNames.add("Дата объекта позиции", POSITION_OBJECTS_DOCUMENT_DATE_ID);
        fieldNames.add("Тип документа развернуть список", POSITION_OBJECTS_DOCUMENT_TYPE_ID_LIST_CLOSED_XPATH);
        fieldNames.add("Тип документа требуемое значение в списке",
                POSITION_OBJECTS_DOCUMENT_TYPE_ID_VALUE_IN_OPENED_LIST_XPATH);
        fieldNames.add("Тип документа текущее значение", POSITION_OBJECTS_DOCUMENT_TYPE_ID_VALUE_XPATH);
        fieldNames.add("Наименование документа объекта позиции", POSITION_OBJECTS_DOCUMENT_NAME_XPATH);
        fieldNames.add("Номер документа объекта позиции", POSITION_OBJECTS_DOCUMENT_NUMBER_XPATH);
        fieldNames.add("Количество объекта позиции", POSITION_OBJECTS_QUANTITY_ID);
        fieldNames.add("Цена за единицу объекта позиции", POSITION_OBJECTS_UNIT_PRICE_XPATH);
        fieldNames.add("Единица измерения код ОКЕИ развернуть список", POSITION_OBJECTS_OKEI_LIST_CLOSED_XPATH);
        fieldNames.add("Единица измерения код ОКЕИ требуемое значение в списке",
                POSITION_OBJECTS_OKEI_VALUE_IN_OPENED_LIST_XPATH);
        fieldNames.add("Единица измерения код ОКЕИ текущее значение", POSITION_OBJECTS_OKEI_VALUE_XPATH);
        fieldNames.add("Тип финансового обеспечения объекта позиции", POSITION_OBJECTS_TYPE_PROVISION_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Добавить позицию", ADD_POSITION_BUTTON_XPATH);
        buttonNames.add("Добавить объект позиции", POSITION_OBJECTS_ADD_BUTTON_XPATH);
        buttonNames.add("Прикрепить файл", UPLOAD_FILE_EXECUTION_BUTTON_ID);
        buttonNames.add("Сохранить", SAVE_DRAFT_BUTTON_XPATH);
        buttonNames.add("Опубликовать", PUBLISH_BUTTON_XPATH);
        buttonNames.add("Удалить", REMOVE_BUTTON_XPATH);
        buttonNames.add("'Закрыть' - в окне 'Календарь'", DATEPICKER_HIDE_BUTTON_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        posObjectsColumns.add("Наименование объекта закупки", POSITION_OBJECTS_TABLE_ITEM_ID_COLUMN_XPATH);
        posObjectsColumns.add("ОКПД2", POSITION_OBJECTS_TABLE_OKPD2_COLUMN_XPATH);
        posObjectsColumns.add("Дата документа", POSITION_OBJECTS_TABLE_DOCUMENT_DATE_COLUMN_XPATH);
        posObjectsColumns.add("Тип документа", POSITION_OBJECTS_TABLE_DOCUMENT_TYPE_ID_COLUMN_XPATH);
        posObjectsColumns.add("Наименование документа", POSITION_OBJECTS_TABLE_DOCUMENT_NAME_COLUMN_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Раздел [Позиции сведений об исполнении]

    // region Закладка [Общая информация]

    /**
     * Заполняет поле [Валюта] фиксированным значением [Российский рубль (RUB)] из списка выбора.
     */
    public void fillCurrencyFieldWithRussianRoubleValue() throws Exception {
        SelenideElement collapsedList = $(this.getBy(COMMON_INFO_CURRENCY_CODE_LIST_CLOSED_XPATH));
        SelenideElement desiredValue = $(this.getBy(COMMON_INFO_CURRENCY_CODE_VALUE_IN_OPENED_LIST_XPATH));
        SelenideElement selectedValue = $(this.getBy(COMMON_INFO_CURRENCY_CODE_VALUE_XPATH));
        this.waitForListOpensAndSelectDesiredValue("Валюта", collapsedList, desiredValue, selectedValue);
    }

    // endregion

    // region Закладка [Объекты позиции]

    // region Таблица [Объекты позиции сведений об исполнении]

    /**
     * Проверяет текст ячейки в таблице [Объекты позиции сведений об исполнении] для столбца с указанным именем и
     * номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     */
    public void verifyCellByNameInLineByNumberFromPositionObjectsTableForText(
            String columnName, String rowNumber, String cellValue) {
        this.verifyCellInTableByColumnElementsAndLineNumberForText("Объекты позиции сведений об исполнении",
                columnName, $$(this.getBy(posObjectsColumns.find(columnName))), rowNumber, cellValue);
    }

    //endregion

    /**
     * Заполняет поле [Наименование объекта закупки] фиксированным значением [Товар 1] из списка выбора.
     */
    public void fillItemIdFieldWithItem1Value() throws Exception {
        SelenideElement collapsedList = $(this.getBy(POSITION_OBJECTS_ITEM_ID_LIST_CLOSED_XPATH));
        SelenideElement desiredValue = $(this.getBy(POSITION_OBJECTS_ITEM_ID_VALUE_IN_OPENED_LIST_XPATH));
        SelenideElement selectedValue = $(this.getBy(POSITION_OBJECTS_ITEM_ID_VALUE_XPATH));
        this.waitForListOpensAndSelectDesiredValue("Наименование объекта закупки",
                collapsedList, desiredValue, selectedValue);
    }

    /**
     * Заполняет поле [Тип документа] фиксированным значением [01 - Товарная накладная] из списка выбора.
     */
    public void fillDocumentTypeIdFieldWith01PackingListValue() throws Exception {
        SelenideElement collapsedList = $(this.getBy(POSITION_OBJECTS_DOCUMENT_TYPE_ID_LIST_CLOSED_XPATH));
        SelenideElement desiredValue = $(this.getBy(POSITION_OBJECTS_DOCUMENT_TYPE_ID_VALUE_IN_OPENED_LIST_XPATH));
        SelenideElement selectedValue = $(this.getBy(POSITION_OBJECTS_DOCUMENT_TYPE_ID_VALUE_XPATH));
        this.waitForListOpensAndSelectDesiredValue("Тип документа", collapsedList, desiredValue, selectedValue);
    }

    /**
     * Заполняет поле [Единица измерения (код ОКЕИ)] фиксированным значением [Ампер (260)]
     * из списка выбора.
     */
    public void fillOkeiCodeIdFieldWith1000LitersValue() throws Exception {
        SelenideElement collapsedList = $(this.getBy(POSITION_OBJECTS_OKEI_LIST_CLOSED_XPATH));
        SelenideElement desiredValue = $(this.getBy(POSITION_OBJECTS_OKEI_VALUE_IN_OPENED_LIST_XPATH));
        SelenideElement selectedValue = $(this.getBy(POSITION_OBJECTS_OKEI_VALUE_XPATH));
        this.waitForListOpensAndSelectDesiredValue("Тип документа", collapsedList, desiredValue, selectedValue);
    }

    // endregion

    // endregion

    // region Раздел [Документы]

    /**
     * Прикрепляет файл в разделе [Документы].
     *
     * @param fileName имя файла
     */
    public void uploadFileIntoDocuments(String fileName) {
        System.out.printf("[ИНФОРМАЦИЯ]: прикрепляем файл [%s] в разделе [Документы].%n", fileName);
        $(this.getBy(UPLOAD_FILE_EXECUTION_BUTTON_ID)).waitUntil(exist, timeout, polling).sendKeys(fileName);
    }

    /**
     * Проверяет ссылку для скачивания первого прикрепленного файла в разделе [Документы].
     *
     * @param fileName имя файла
     */
    public void checkLinkToDownloadFileIntoDocuments(String fileName) throws Exception {
        System.out.printf(
                "[ИНФОРМАЦИЯ]: проверяем ссылку для скачивания первого прикрепленного файла [%s] в разделе [Документы].%n",
                fileName);
        this.getCurrentServerVersion();
        $(this.getBy(DOWNLOAD_FILE_EXECUTION_XPATH)).waitUntil(visible, timeout, polling).shouldHave(text(fileName));
    }

    // endregion

    // region Общие методы для работы с элементами этой страницы

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
     * Переключает на требуемую закладку.
     *
     * @param tabName имя закладки
     */
    public void switchToTab(String tabName) throws Exception {
        SelenideElement tab = $(this.getBy(tabNames.find(tabName)));
        this.scrollToCenter(tab);
        tab.shouldBe(clickable);
        this.clickInElementJS(tab);
        TimeUnit.SECONDS.sleep(1);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие на закладку [%s].%n", tabName);
    }

    /**
     * Устанавливает значение полей с предварительной их очисткой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     */
    public void setFieldClearClickAndSendKeys(String fieldName, String value) throws Exception {
        this.waitClearClickAndSendKeys(this.getBy(fieldNames.find(fieldName)), value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
    }

    /**
     * Устанавливает значение полей с элементами увеличения и уменьшения значения (стрелки вверх и вниз справа от поля).
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     */
    public void setKendoNumericTextBoxField(String fieldName, String value) throws Exception {
        this.logFilledFieldName(fieldName, value);
        $(this.getBy(fieldNames.find(fieldName))).waitUntil(clickable, timeout, polling).sendKeys(value +
                Keys.TAB);
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

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n",
                fieldName, value);
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
        if (value.trim().isEmpty()) {
            String actualValue = field.getText().trim();
            if (actualValue.isEmpty()) actualValue = field.getValue().trim();
            Assert.assertTrue(message, actualValue.isEmpty());
        } else
            Assert.assertTrue(message, field.getText().contains(value));
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
     * Проверяет поле на возможность редактирования.
     *
     * @param fieldName имя поля
     */
    public void checkFieldForThePossibilityOfEditing(String fieldName) {
        SelenideElement field = $(this.getBy(fieldNames.find(fieldName)));
        Assert.assertTrue(String.format("[ОШИБКА]: поле [%s] невозможно редактировать", fieldName),
                this.isFieldEditable(fieldName, field));
    }

    /**
     * Проверяет поле на невозможность редактирования.
     *
     * @param fieldName имя поля
     */
    public void checkFieldForTheImpossibilityOfEditing(String fieldName) {
        SelenideElement field = $(this.getBy(fieldNames.find(fieldName)));
        Assert.assertFalse(String.format("[ОШИБКА]: поле [%s] возможно редактировать", fieldName),
                this.isFieldEditable(fieldName, field));
    }

    /**
     * Проверяет, что флажок с указанным именем выбран.
     *
     * @param checkBoxName имя флажка
     */
    public void verifyCheckBoxSelected(String checkBoxName) {
        String message = "[ОШИБКА]: флажок [%s] не установлен";
        Assert.assertTrue(String.format(message, checkBoxName), this.getCheckBoxValue(checkBoxName));
        System.out.printf("[ИНФОРМАЦИЯ]: флажок [%s] находится в отмеченном состоянии.%n", checkBoxName);
    }

    /**
     * Проверяет, что флажок с указанным именем не выбран.
     *
     * @param checkBoxName имя флажка
     */
    public void verifyCheckBoxNotSelected(String checkBoxName) {
        Assert.assertFalse(
                String.format("[ОШИБКА]: флажок [%s] установлен", checkBoxName), this.getCheckBoxValue(checkBoxName));
        System.out.printf("[ИНФОРМАЦИЯ]: флажок [%s] находится в не отмеченном состоянии.%n", checkBoxName);
    }

    /**
     * Возвращает значение флажка с указанным именем.
     *
     * @param checkBoxName имя флажка
     * @return значение флажка
     */
    public boolean getCheckBoxValue(String checkBoxName) {
        $(this.getBy(checkBoxNames.find(checkBoxName))).waitUntil(exist, timeout, polling).shouldBe(visible);
        return $(this.getBy(checkBoxNames.find(checkBoxName))).isSelected();
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
