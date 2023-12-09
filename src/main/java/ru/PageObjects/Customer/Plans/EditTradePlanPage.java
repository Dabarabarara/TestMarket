package ru.PageObjects.Customer.Plans;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonDialogs.InformationDialog;
import ru.PageObjects.CommonDialogs.WarningDialog;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Класс для работы со страницей [Редактирование плана закупки]
 * ( Главная / Заказчикам / Планы закупок / Редактирование плана закупки )
 * ( .kontur.ru/customer/lk/TradePlans/TradePlan/Edit/ ).
 * Created by Vladimir V. Klochkov on 02.09.2016.
 * Updated by Vladimir V. Klochkov on 31.03.2021.
 */
public class EditTradePlanPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы
    private static final String PAGE_HEADER_XPATH = "//h2[contains(.,'Редактирование плана закупки')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Общие сведения о плане закупки]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Общие сведения о плане закупки]
    private static final String PLAN_GENERAL_INFO_BLOCK_XPATH = "//h3[contains(.,'Общие сведения о плане закупки')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование]
    private static final String NAME_ID = "Name";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Заказчик]
    private static final String CUSTOMER_NAME_XPATH = "//div[@data-bind='text: Customer.Name']";
    //------------------------------------------------------------------------------------------------------------------

    // region Список [Вид]

    //------------------------------------------------------------------------------------------------------------------
    // Список [Вид] в закрытом виде со значением, которое было установлено тестом
    private static final String KIND_LIST_CLOSED_VALUE_XPATH =
            "//span[@aria-owns='PurchaseKind_listbox']//span[@class='k-input']";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Список [Вид] значение в раскрытом списке, которое следует выбрать
    private static final String KIND_LIST_OPENED_DESIRED_VALUE_XPATH =
            "//*[@id='PurchaseKind_listbox']/li[contains(.,'План коммерческих закупок')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Период C]
    private static final String START_DATE_ID = "StartDate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Период По]
    private static final String END_DATE_ID = "EndDate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата утверждения]
    private static final String CONFIRMED_DATE_ID = "ConfirmedDate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Отчетный год]
    private static final String REPORTING_YEAR_ID = "ReportingYear";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Импорт позиций]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Импорт позиций]
    private static final String IMPORT_OF_POSITIONS_BLOCK_XPATH = "//h3[contains(.,'Импорт позиций')]";
    //------------------------------------------------------------------------------------------------------------------
    // Блок переключателей [Загрузка ...]
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Блок переключателей [Загрузка ...] переключатель [Загрузка из Excel-файла]
    private static final String LOAD_FROM_EXCEL_FILE_ID = "import_file";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Блок переключателей [Загрузка ...] переключатель [Загрузка с ЕИС]
    private static final String LOAD_FROM_OOS_ID = "import_oos";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Введите URL плана закупок на ЕИС]
    private static final String OOS_URL_XPATH = "//input[@name='oosUrl']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Загрузить файл input]
    private static final String LOAD_FILE_BUTTON_ID = "UploadFile_Positions";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Загрузить файл]
    private static final String LOAD_FILE_BUTTON_XPATH =
            "//input[@id='UploadFile_Positions']/following-sibling::span[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Импортировать]
    private static final String IMPORT_FROM_OOS_BUTTON_XPATH = "//button[@data-bind='click: importFromOos']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Позиции плана]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Позиции плана]
    private static final String PLAN_POSITIONS_BLOCK_XPATH = "//h3[contains(.,'Позиции плана')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить позицию]
    private static final String ADD_POSITION_BUTTON_XPATH = "//button[contains(.,'Добавить позицию')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Сохранить]
    private static final String SAVE_NEW_POSITION_BUTTON_XPATH = "//button[@data-bind='click: modify']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отмена]
    private static final String CANCEL_NEW_POSITION_BUTTON_XPATH =
            "//button[@class='k-button k-state-default js-plan-lot-editCancel']";
    //------------------------------------------------------------------------------------------------------------------

    // region Таблица [Позиции плана]

    //------------------------------------------------------------------------------------------------------------------
    // Колонка [Номер] ( существующие / добавленные позиции плана )
    private static final String TRADE_PLAN_POSITIONS_TABLE_POSITION_NUMBERS_XPATH =
            "//*[@id='lots']/table/tbody/tr/td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Колонка [Предмет договора] ( существующие / добавленные позиции плана )
    private static final String TRADE_PLAN_POSITIONS_TABLE_CONTRACT_SUBJECTS_XPATH =
            "//*[@id='lots']/table/tbody/tr/td[3]";
    //------------------------------------------------------------------------------------------------------------------
    // Колонка [Начальная цена договора] ( существующие / добавленные позиции плана )
    private static final String TRADE_PLAN_POSITIONS_TABLE_CONTRACT_PRICES_XPATH =
            "//*[@id='lots']/table/tbody/tr/td[5]";
    //------------------------------------------------------------------------------------------------------------------
    // Колонка [Способ закупки] ( существующие / добавленные позиции плана )
    private static final String TRADE_PLAN_POSITIONS_TABLE_PURCHASE_METHODS_XPATH =
            "//*[@id='lots']/table/tbody/tr/td[9]";
    //==================================================================================================================
    // Ячейка [+] в новой позиции
    private static final String EXPAND_GOODS_WORKS_SERVICES_BLOCK_XPATH =
            "//a[@data-bind='invisible: LotItemsVisibled']";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [Номер] в новой позиции
    private static final String TRADE_PLAN_POSITIONS_TABLE_PLAN_LOT_ID_XPATH = "//input[@class='js-plan-lot-id']";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [Предмет договора] в новой позиции
    private static final String TRADE_PLAN_POSITIONS_TABLE_SUBJECT_XPATH = "//textarea[@data-bind='value: Subject']";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [Мин. требования к товарам/услугам] в новой позиции
    private static final String TRADE_PLAN_POSITIONS_TABLE_MINIMAL_PURCHASE_REQUEIRMENT_XPATH =
            "//textarea[@data-bind='value: MinimalPurchaseRequeirment']";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [Начальная цена договора] в новой позиции собственно значение
    private static final String TRADE_PLAN_POSITIONS_TABLE_STARTING_PRICE_DIGIT_XPATH =
            "//input[contains(@data-bind,'value: StartingPriceDigit')]";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Ячейка [Начальная цена договора] в новой позиции выпадающий список доступных валют
    private static final String TRADE_PLAN_POSITIONS_TABLE_STARTING_PRICE_CURRENCY_CODE_XPATH =
            "//input[contains(@data-bind,'value: StartingPriceCurrencyCode')]/preceding-sibling::span/" +
                    "span[@class='k-input']";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Ячейка [Начальная цена договора] в новой позиции флажок [Невозможно определить цену]
    private static final String TRADE_PLAN_POSITIONS_TABLE_USE_STARTING_PRICE_MESSAGE_XPATH =
            "//input[contains(@data-bind,'checked: UseStartingPriceMessage')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [Порядок формирования цены договора] в новой позиции
    private static final String TRADE_PLAN_POSITIONS_TABLE_ORDER_PRICING_XPATH =
            "//textarea[contains(@data-bind,'value: OrderPricing')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [Планируемый период размещения] в новой позиции собственно значение
    private static final String TRADE_PLAN_POSITIONS_TABLE_PLAN_DEPOSIT_PERIOD_EDIT_XPATH =
            "//input[contains(@data-bind,'value: PlanDepositPeriodEdit')]";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Ячейка [Планируемый период размещения] в новой позиции переключатель [Месяц]
    private static final String TRADE_PLAN_POSITIONS_TABLE_PERIOD_MONTH_XPATH = "//input[@value='month']";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Ячейка [Планируемый период размещения] в новой позиции переключатель [Дата]
    private static final String TRADE_PLAN_POSITIONS_TABLE_PERIOD_DATE_XPATH = "//input[@value='date']";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Ячейка [Планируемый период размещения] в новой позиции флажок
    // [Закупка запланирована на третий или последующие годы реализации плана закупки]
    private static final String TRADE_PLAN_POSITIONS_TABLE_PERIOD_PLANNED_AFTER_SECOND_YEAR_XPATH =
            "//input[@class='js-planned-after-second-year']";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [Срок исполнения договора] в новой позиции
    private static final String TRADE_PLAN_POSITIONS_TABLE_EXECUTION_AGREEMENT_DATE_EDIT_XPATH =
            "//input[contains(@data-bind,'value: ExecutionAgreementDateEdit')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [Способ закупки] в новой позиции
    private static final String TRADE_PLAN_POSITIONS_TABLE_METHOD_PURCHASE_CODE_XPATH =
            "//input[contains(@data-bind,'value: MethodPurchaseCode')]/preceding-sibling::span/span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [Закупка в эл. форме] в новой позиции
    private static final String TRADE_PLAN_POSITIONS_TABLE_IS_IN_DIGITAL_FORM_XPATH =
            "//input[contains(@data-bind,'value: IsInDigitalForm')]/preceding-sibling::span/span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [МСП] в новой позиции выпадающий список [Да/Нет]
    private static final String TRADE_PLAN_POSITIONS_TABLE_IS_SMP_XPATH =
            "//input[contains(@data-bind,'value: IsSmp')]/preceding-sibling::span/span[@class='k-input']";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Ячейка [МСП] в новой позиции выпадающий список категорий
    private static final String TRADE_PLAN_POSITIONS_TABLE_PURCHASE_CATEGORY_XPATH =
            "//input[contains(@data-bind,'value: PurchaseCategory')]/preceding-sibling::span/span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [Причина аннулирования] в новой позиции
    private static final String TRADE_PLAN_POSITIONS_TABLE_CANCELLATION_REASON_XPATH =
            "//input[contains(@data-bind,'value: CancellationReason')]/preceding-sibling::span/span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [Закупка товаров (работ, услуг), относящихся к инновационной продукции] в новой позиции
    private static final String TRADE_PLAN_POSITIONS_TABLE_IS_INNOVATIVE_XPATH =
            "//input[contains(@data-bind,'value: IsInnovative')]/preceding-sibling::span/span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [Заказчик] в новой позиции
    private static final String TRADE_PLAN_POSITIONS_TABLE_CUSTOMER_ID_XPATH =
            "//input[contains(@data-bind,'value: CustomerId')]/preceding-sibling::span/span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Товары (работы, услуги)]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Товары (работы, услуги)] для новой позиции
    private static final String GOODS_WORKS_SERVICES_BLOCK_XPATH = "//h3[contains(.,'Товары (работы, услуги)')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [Наименование] для новой позиции
    private static final String GOODS_WORKS_SERVICES_TABLE_NAME_XPATH = "//input[@data-bind='value: Name']";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [Кол-во] для новой позиции собственно значение
    private static final String GOODS_WORKS_SERVICES_TABLE_QUANTITY_XPATH =
            "//input[@data-bind='value: Quantity, disabled: PlannedSecondYearOrAfter']";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Ячейка [Кол-во] для новой позиции флажок [Невозможно определить кол-во]
    private static final String GOODS_WORKS_SERVICES_TABLE_IMPOSSIBLE_DETERMINE_QUANTITY_XPATH =
            "//input[@class='impossible-determine-quantity']";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [Ед. измерения] для новой позиции
    private static final String GOODS_WORKS_SERVICES_TABLE_OKEI_CODE_XPATH =
            "//input[contains(@class,'k-input js-plan-lot-item-okei')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [Тип объекта закупки] список в закрытом виде со значением, которое было установлено тестом для новой
    // позиции
    private static final String GOODS_WORKS_SERVICES_TABLE_OBJECT_PURCHASE_LIST_CLOSED_VALUE_XPATH =
            "//span[contains(@class,'js-plan-lot-item-type-object-purchase')]//span[@class='k-input']";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Значение в раскрытом списке ячейки [Тип объекта закупки], которое следует выбрать для новой позиции
    private static final String GOODS_WORKS_SERVICES_TABLE_OBJECT_PURCHASE_LIST_OPENED_DESIRED_VALUE_XPATH =
            "//ul[@class='k-list k-reset']/li[contains(.,'Товар')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [ОКВЭД2] для новой позиции
    private static final String GOODS_WORKS_SERVICES_TABLE_OKVED2_CODE_XPATH = "//input[@data-bind='value: Okved2Code']";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [ОКПД2] для новой позиции
    private static final String GOODS_WORKS_SERVICES_TABLE_OKPD2_CODE_XPATH = "//input[@data-bind='value: Okpd2Code']";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [Регион поставки] список в закрытом виде со значением, которое было установлено тестом для новой позиции
    private static final String GOODS_WORKS_SERVICES_TABLE_REGION_LIST_CLOSED_VALUE_XPATH =
            "//span[contains(@class,'js-plan-lot-item-region')]//span[@class='k-input']";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Значение в раскрытом списке ячейки [Регион поставки], которое следует выбрать для новой позиции
    private static final String GOODS_WORKS_SERVICES_TABLE_REGION_LIST_OPENED_DESIRED_VALUE_XPATH =
            "//ul[@class='k-list k-reset']/li[contains(.,'67000000000: г. Севастополь')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ячейка [Дополнительные сведения] для новой позиции
    private static final String GOODS_WORKS_SERVICES_TABLE_ADDITIONAL_INFO_XPATH =
            "//input[@data-bind='value: AdditionalInfo']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // endregion

    // region Раздел [Информеры]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Информеры]
    private static final String INFORMERS_BLOCK_XPATH = "//h3[contains(.,'Информеры')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Документы]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Документы]
    private static final String DOCUMENTS_BLOCK_XPATH = "//h3[contains(.,'Документы')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Название] в таблице раздела [Документы]
    private static final String DOCUMENTS_FILE_NAME_COLUMN_XPATH =
            "//tr//a[contains(@href, '.kontur.ru/files/FileDownloadHandler.ashx?fileguid=')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Действия] в таблице раздела [Документы]
    private static final String DOCUMENTS_ACTIONS_COLUMN_XPATH =
            "//tr//a[contains(@data-bind,'click: remove') and contains(.,'Удалить')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Прикрепить файл] в разделе [Документы]
    private static final String DOCUMENTS_UPLOAD_FILE_BUTTON_ID = "UploadFile_Plan";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Блок управляющих кнопок в нижней части страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Сохранить черновик]
    private static final String SAVE_AS_DRAFT_BUTTON_XPATH =
            "//button[@class='k-button k-state-default' and contains(.,'Сохранить черновик')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Опубликовать]
    private static final String PUBLISH_BUTTON_XPATH =
            "//button[@class='k-button k-state-default' and contains(.,'Опубликовать')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отправить в ЕИС]
    private static final String SEND_TO_OOS_BUTTON_XPATH =
            "//button[@class='k-button k-state-default' and contains(.,'Отправить в ЕИС')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Экспортировать в Excel 2007+]
    private static final String EXPORT_EXCEL_BUTTON_XPATH =
            "//button[@class='k-button k-state-default' and contains(.,'Экспортировать в Excel 2007+')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Сформировать печатную форму]
    private static final String GENERATE_PRINT_FORM_BUTTON_XPATH =
            "//a[@class='k-button k-state-default' and contains(.,'Сформировать печатную форму')]";
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
    private final Dictionary blockNames = new Dictionary();       // все разделы на странице
    private final Dictionary fieldNames = new Dictionary();       // все поля на странице
    private final Dictionary buttonNames = new Dictionary();      // все кнопки на странице
    private final Dictionary radioButtonNames = new Dictionary(); // все переключатели на странице
    private final Dictionary positionsColumns = new Dictionary(); // все колонки таблицы в разделе [Позиции плана]
    private final Dictionary positionsCells = new Dictionary();   // все ячейки новой позиции для добавления в таблицу в разделе [Позиции плана]
    private final Dictionary goodsCells = new Dictionary();       // все ячейки новой позиции для добавления в таблицу в разделе [Товары (работы, услуги)]
    private final Dictionary documentsColumns = new Dictionary(); // все колонки таблицы в разделе [Документы]

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public EditTradePlanPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Заголовок страницы", PAGE_HEADER_XPATH);
        blockNames.add("Общие сведения о плане закупки", PLAN_GENERAL_INFO_BLOCK_XPATH);
        blockNames.add("Импорт позиций", IMPORT_OF_POSITIONS_BLOCK_XPATH);
        blockNames.add("Позиции плана", PLAN_POSITIONS_BLOCK_XPATH);
        blockNames.add("Товары (работы, услуги)", GOODS_WORKS_SERVICES_BLOCK_XPATH);
        blockNames.add("Информеры", INFORMERS_BLOCK_XPATH);
        blockNames.add("Документы", DOCUMENTS_BLOCK_XPATH);
        blockNames.add("Подвал страницы", SERVER_VERSION_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Наименование", NAME_ID);
        fieldNames.add("Заказчик", CUSTOMER_NAME_XPATH);
        fieldNames.add("Вид", KIND_LIST_CLOSED_VALUE_XPATH);
        fieldNames.add("Значение в раскрытом списке Вид", KIND_LIST_OPENED_DESIRED_VALUE_XPATH);
        fieldNames.add("Период C", START_DATE_ID);
        fieldNames.add("Период По", END_DATE_ID);
        fieldNames.add("Дата утверждения", CONFIRMED_DATE_ID);
        fieldNames.add("Отчетный год", REPORTING_YEAR_ID);
        fieldNames.add("Введите URL плана закупок на ЕИС", OOS_URL_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Загрузить файл input", LOAD_FILE_BUTTON_ID);
        buttonNames.add("Загрузить файл", LOAD_FILE_BUTTON_XPATH);
        buttonNames.add("Импортировать", IMPORT_FROM_OOS_BUTTON_XPATH);
        buttonNames.add("Добавить позицию", ADD_POSITION_BUTTON_XPATH);
        buttonNames.add("Сохранить новую позицию", SAVE_NEW_POSITION_BUTTON_XPATH);
        buttonNames.add("Отмена сохранения новой позиции", CANCEL_NEW_POSITION_BUTTON_XPATH);
        buttonNames.add("Сохранить", ADD_POSITION_BUTTON_XPATH);
        buttonNames.add("Прикрепить файл", DOCUMENTS_UPLOAD_FILE_BUTTON_ID);
        buttonNames.add("Сохранить черновик", SAVE_AS_DRAFT_BUTTON_XPATH);
        buttonNames.add("Опубликовать", PUBLISH_BUTTON_XPATH);
        buttonNames.add("Отправить в ЕИС", SEND_TO_OOS_BUTTON_XPATH);
        buttonNames.add("Экспортировать в Excel 2007+", EXPORT_EXCEL_BUTTON_XPATH);
        buttonNames.add("Сформировать печатную форму", GENERATE_PRINT_FORM_BUTTON_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        radioButtonNames.add("Загрузка из Excel-файла", LOAD_FROM_EXCEL_FILE_ID);
        radioButtonNames.add("Загрузка с ЕИС", LOAD_FROM_OOS_ID);
        //--------------------------------------------------------------------------------------------------------------
        // Колонки таблицы [Позиции плана] с уже существующими ( добавленными ) позициями плана закупки
        positionsColumns.add("Номер", TRADE_PLAN_POSITIONS_TABLE_POSITION_NUMBERS_XPATH);
        positionsColumns.add("Предмет договора", TRADE_PLAN_POSITIONS_TABLE_CONTRACT_SUBJECTS_XPATH);
        positionsColumns.add("Начальная цена договора", TRADE_PLAN_POSITIONS_TABLE_CONTRACT_PRICES_XPATH);
        positionsColumns.add("Способ закупки", TRADE_PLAN_POSITIONS_TABLE_PURCHASE_METHODS_XPATH);
        //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Ячейки таблицы [Позиции плана] для заполнения в новой позиции плана закупки при её добавлении
        positionsCells.add("Показать блок товары (работы, услуги)", EXPAND_GOODS_WORKS_SERVICES_BLOCK_XPATH);
        positionsCells.add("Номер", TRADE_PLAN_POSITIONS_TABLE_PLAN_LOT_ID_XPATH);
        positionsCells.add("Предмет договора", TRADE_PLAN_POSITIONS_TABLE_SUBJECT_XPATH);
        positionsCells.add("Мин. требования к товарам/услугам",
                TRADE_PLAN_POSITIONS_TABLE_MINIMAL_PURCHASE_REQUEIRMENT_XPATH);
        positionsCells.add("Начальная цена договора",
                TRADE_PLAN_POSITIONS_TABLE_STARTING_PRICE_DIGIT_XPATH);
        positionsCells.add("Начальная цена договора список доступных валют",
                TRADE_PLAN_POSITIONS_TABLE_STARTING_PRICE_CURRENCY_CODE_XPATH);
        positionsCells.add("Начальная цена договора невозможно определить цену",
                TRADE_PLAN_POSITIONS_TABLE_USE_STARTING_PRICE_MESSAGE_XPATH);
        positionsCells.add("Порядок формирования цены договора",
                TRADE_PLAN_POSITIONS_TABLE_ORDER_PRICING_XPATH);
        positionsCells.add("Планируемый период размещения",
                TRADE_PLAN_POSITIONS_TABLE_PLAN_DEPOSIT_PERIOD_EDIT_XPATH);
        positionsCells.add("Планируемый период размещения месяц",
                TRADE_PLAN_POSITIONS_TABLE_PERIOD_MONTH_XPATH);
        positionsCells.add("Планируемый период размещения дата",
                TRADE_PLAN_POSITIONS_TABLE_PERIOD_DATE_XPATH);
        positionsCells.add("Планируемый период размещения закупка запланирована на третий",
                TRADE_PLAN_POSITIONS_TABLE_PERIOD_PLANNED_AFTER_SECOND_YEAR_XPATH);
        positionsCells.add("Срок исполнения договора",
                TRADE_PLAN_POSITIONS_TABLE_EXECUTION_AGREEMENT_DATE_EDIT_XPATH);
        positionsCells.add("Способ закупки", TRADE_PLAN_POSITIONS_TABLE_METHOD_PURCHASE_CODE_XPATH);
        positionsCells.add("Закупка в эл. форме", TRADE_PLAN_POSITIONS_TABLE_IS_IN_DIGITAL_FORM_XPATH);
        positionsCells.add("МСП Да/Нет", TRADE_PLAN_POSITIONS_TABLE_IS_SMP_XPATH);
        positionsCells.add("МСП категория", TRADE_PLAN_POSITIONS_TABLE_PURCHASE_CATEGORY_XPATH);
        positionsCells.add("Причина аннулирования", TRADE_PLAN_POSITIONS_TABLE_CANCELLATION_REASON_XPATH);
        positionsCells.add("Закупка товаров (работ, услуг), относящихся к инновационной продукции",
                TRADE_PLAN_POSITIONS_TABLE_IS_INNOVATIVE_XPATH);
        positionsCells.add("Заказчик", TRADE_PLAN_POSITIONS_TABLE_CUSTOMER_ID_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        // Ячейки таблицы [Товары (работы, услуги)] для заполнения в новой позиции плана закупки при её добавлении
        goodsCells.add("Наименование", GOODS_WORKS_SERVICES_TABLE_NAME_XPATH);
        goodsCells.add("Кол-во", GOODS_WORKS_SERVICES_TABLE_QUANTITY_XPATH);
        goodsCells.add("Невозможно определить кол-во", GOODS_WORKS_SERVICES_TABLE_IMPOSSIBLE_DETERMINE_QUANTITY_XPATH);
        goodsCells.add("Ед. измерения", GOODS_WORKS_SERVICES_TABLE_OKEI_CODE_XPATH);
        goodsCells.add("Тип объекта закупки", GOODS_WORKS_SERVICES_TABLE_OBJECT_PURCHASE_LIST_CLOSED_VALUE_XPATH);
        goodsCells.add("Значение в раскрытом списке Тип объекта закупки",
                GOODS_WORKS_SERVICES_TABLE_OBJECT_PURCHASE_LIST_OPENED_DESIRED_VALUE_XPATH);
        goodsCells.add("ОКВЭД2", GOODS_WORKS_SERVICES_TABLE_OKVED2_CODE_XPATH);
        goodsCells.add("ОКПД2", GOODS_WORKS_SERVICES_TABLE_OKPD2_CODE_XPATH);
        goodsCells.add("Регион поставки", GOODS_WORKS_SERVICES_TABLE_REGION_LIST_CLOSED_VALUE_XPATH);
        goodsCells.add("Значение в раскрытом списке Регион поставки",
                GOODS_WORKS_SERVICES_TABLE_REGION_LIST_OPENED_DESIRED_VALUE_XPATH);
        goodsCells.add("Дополнительные сведения", GOODS_WORKS_SERVICES_TABLE_ADDITIONAL_INFO_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        documentsColumns.add("Название", DOCUMENTS_FILE_NAME_COLUMN_XPATH);
        documentsColumns.add("Действия", DOCUMENTS_ACTIONS_COLUMN_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы

    /**
     * Запоминает номер плана закупки в глобальных параметрах для его дальнейшего использования.
     */
    public void storeTradePlanNumber() {
        String tradePlanNumber = url().split("/")[8];
        config.setParameter("TradePlanNumber", tradePlanNumber);
        System.out.printf("[ИНФОРМАЦИЯ]: номер плана закупки: [%s].%n", tradePlanNumber);
    }

    // endregion

    // region Раздел [Импорт позиций]

    /**
     * Загружает позиции плана закупки из Excel-файла.
     *
     * @param fileName полное имя загружаемого Excel-файла (включая путь)
     */
    public void loadTradePlanPositions(String fileName) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: загружаем позиции плана закупок из файла: [%s].%n", fileName);
        $(this.getBy(buttonNames.find("Загрузить файл input"))).
                waitUntil(exist, timeout, polling).sendKeys(fileName);
        TimeUnit.SECONDS.sleep(3);

        // Клик по кнопке [Продолжить] в диалоговом окне [Предупреждение]
        WarningDialog warningDialog = new WarningDialog(driver);
        warningDialog.clickOnContinueButton();
        TimeUnit.SECONDS.sleep(10);

        // Клик по кнопке [OK] в диалоговом окне [Информация]
        InformationDialog informationDialog = new InformationDialog(driver);
        informationDialog.clickOnOKButton("ОК");
        this.waitLoadingImage();
    }

    // endregion

    // region Раздел [Позиции плана]

    /**
     * Возвращает список номеров позиций из таблицы [Позиции плана] для текущей страницы.
     *
     * @return список номеров позиций из таблицы [Позиции плана]
     */
    public List<String> getPositionNumbersFromTradePlanTable() {
        List<String> results = new ArrayList<>();
        ElementsCollection numbers = $$(this.getBy(positionsColumns.find("Номер")));
        numbers.get(0).waitUntil(visible, timeout, polling);
        int size = numbers.size();
        System.out.printf("[ИНФОРМАЦИЯ]: всего найдено позиций в плане закупок: [%d].%n", size);

        for (SelenideElement number : numbers) {
            String positionNumber = number.getText();
            results.add(positionNumber);
            System.out.printf("[ИНФОРМАЦИЯ]: найден номер позиции в плане закупок: [%s].%n", positionNumber);
        }

        return results;
    }

    /**
     * Возвращает количество пустых ячеек в столбце с указанным именем из таблицы [Позиции плана] для текущей страницы.
     *
     * @param columnName имя столбца
     * @return количество пустых ячеек в столбце с указанным именем из таблицы [Позиции плана] для текущей страницы
     */
    public int getNumberOfEmptyCellsInColumnByName(String columnName) {
        ElementsCollection cells = $$(this.getBy(positionsColumns.find(columnName)));
        cells.get(0).waitUntil(visible, timeout, polling);
        int result = 0;

        for (SelenideElement cell : cells) if (cell.getText().isEmpty()) result++;

        System.out.printf("[ИНФОРМАЦИЯ]: найдено [%d] пустых ячеек в столбце [%s] таблицы [Позиции плана].%n",
                result, columnName);

        return result;
    }

    /**
     * Устанавливает значение полей с проверкой в новой строке таблицы [Позиции плана] для текущей страницы.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     */
    public void setFieldInNewLineOfTradePlanTable(String fieldName, String value) {
        By field = this.getBy(positionsCells.find(fieldName));

        $(field).waitUntil(clickable, timeout, polling).sendKeys(value);
        this.logFilledFieldName(fieldName, value);

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        Assert.assertTrue(String.format("[ОШИБКА]: значение [%s] не содержится в поле [%s]", value, fieldName),
                this.verifyFieldContent(field, value));

        // Убираем фокус из поля ввода, иначе введенное в нем значение не воспринимается
        this.removeFocusFromInputFieldJS();
    }

    /**
     * Устанавливает значение полей с элементами увеличения и уменьшения значения (стрелки вверх и вниз справа от поля)
     * в новой строке таблицы [Позиции плана] для текущей страницы.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     */
    public void setKendoNumericTextBoxFieldInNewLineOfTradePlanTable(String fieldName, String value) throws Exception {
        String focusedLocator = positionsCells.find(fieldName);
        String notFocusedLocator = focusedLocator + "/preceding-sibling::input";
        SelenideElement kendoNumericTextBoxNotFocused = $(this.getBy(notFocusedLocator));
        SelenideElement kendoNumericTextBoxFocused = $(this.getBy(focusedLocator));

        kendoNumericTextBoxNotFocused.waitUntil(clickable, timeout, polling).click();
        kendoNumericTextBoxFocused.sendKeys(value);
        this.removeFocusFromInputFieldJS();

        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * Проверяет не пустое значение поля в новой строке таблицы [Позиции плана] для текущей страницы.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение, которое должно содержаться в поле
     */
    public void verifyFieldOfTradePlanTable(String fieldName, String value) {
        String message = String.format("[ОШИБКА]: значение [%s] не содержится в поле [%s]", value, fieldName);
        By field = this.getBy(positionsCells.find(fieldName));

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n",
                fieldName, value);
        Assert.assertTrue(message, this.verifyFieldContent(field, value));
    }

    /**
     * Проверяет, что поле содержит не пустое значение в новой строке таблицы [Позиции плана] для текущей страницы.
     *
     * @param fieldName имя поля
     */
    public void verifyFieldIsNotEmptyOfTradePlanTable(String fieldName) {
        String message = String.format("[ОШИБКА]: поле [%s] содержит пустое значение", fieldName);
        By field = this.getBy(positionsCells.find(fieldName));
        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] на не пустое значение.%n", fieldName);
        Assert.assertTrue(message, this.verifyFieldIsNotEmpty(field));
    }

    /**
     * Проверяет, что флажок с указанным именем выбран в новой строке таблицы [Позиции плана] для текущей страницы.
     *
     * @param checkBoxName имя флажка
     */
    public void verifyCheckBoxSelectedOfTradePlanTable(String checkBoxName) {
        String message = "[ОШИБКА]: флажок [%s] не установлен";
        Assert.assertTrue(String.format(message, checkBoxName), this.getCheckBoxValueOfTradePlanTable(checkBoxName));
        System.out.printf("[ИНФОРМАЦИЯ]: флажок [%s] находится в отмеченном состоянии.%n", checkBoxName);
    }

    /**
     * Проверяет, что флажок с указанным именем не выбран в новой строке таблицы [Позиции плана] для текущей страницы.
     *
     * @param checkBoxName имя флажка
     */
    public void verifyCheckBoxNotSelectedOfTradePlanTable(String checkBoxName) {
        Assert.assertFalse(
                String.format("[ОШИБКА]: флажок [%s] установлен", checkBoxName),
                this.getCheckBoxValueOfTradePlanTable(checkBoxName));
        System.out.printf("[ИНФОРМАЦИЯ]: флажок [%s] находится в не отмеченном состоянии.%n", checkBoxName);
    }

    /**
     * Возвращает значение флажка с указанным именем в новой строке таблицы [Позиции плана] для текущей страницы.
     *
     * @param checkBoxName имя флажка
     * @return значение флажка
     */
    private boolean getCheckBoxValueOfTradePlanTable(String checkBoxName) {
        $(this.getBy(positionsCells.find(checkBoxName))).waitUntil(exist, timeout, polling).shouldBe(visible);
        return $(this.getBy(positionsCells.find(checkBoxName))).isSelected();
    }

    /**
     * Проверяет, что в переключателе не выбрана опция с указанным именем в новой строке таблицы [Позиции плана]
     * для текущей страницы.
     *
     * @param radioButtonName имя опции
     */
    public void verifyRadioButtonNotSelectedOfTradePlanTable(String radioButtonName) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем положение переключателя [%s] - ", radioButtonName);
        $(this.getBy(positionsCells.find(radioButtonName))).waitUntil(exist, timeout, polling).shouldNotBe(selected);
        System.out.println("Ok, не выбран.");
    }

    /**
     * Проверяет, что в переключателе выбрана опция с указанным именем в новой строке таблицы [Позиции плана]
     * для текущей страницы.
     *
     * @param radioButtonName имя опции
     */
    public void verifyRadioButtonSelectedOfTradePlanTable(String radioButtonName) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем положение переключателя [%s] - ", radioButtonName);
        $(this.getBy(positionsCells.find(radioButtonName))).waitUntil(exist, timeout, polling).shouldBe(selected);
        System.out.println("Ok, выбран.");
    }

    /**
     * Выбирает в переключателе опцию с указанным именем в новой строке таблицы [Позиции плана] для текущей страницы.
     *
     * @param radioButtonPosition имя опции
     */
    public void selectRadioButtonOfTradePlanTable(String radioButtonPosition) throws Exception {
        $(this.getBy(positionsCells.find(radioButtonPosition))).waitUntil(exist, timeout, polling).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено переключение в положение [%s].%n", radioButtonPosition);
        TimeUnit.SECONDS.sleep(1);
        this.verifyRadioButtonSelectedOfTradePlanTable(radioButtonPosition);
    }

    /**
     * Устанавливает значение полей с предварительной их очисткой в новой строке таблицы [Позиции плана]
     * для текущей страницы.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     */
    public void setFieldClearClickAndSendKeysOfTradePlanTable(String fieldName, String value) throws Exception {
        this.waitClearClickAndSendKeys(this.getBy(positionsCells.find(fieldName)), value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
    }

    /**
     * Делает щелчок мышью по указанной кнопке в новой строке таблицы [Позиции плана] для текущей страницы.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButtonOfTradePlanTable(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.clickInElementJS(this.getBy(positionsCells.find(buttonName)));
        this.logPressedButtonName(buttonName);
        TimeUnit.SECONDS.sleep(10);
    }

    // endregion

    // region Раздел [Товары (работы, услуги)]

    /**
     * Проверяет, что поле содержит не пустое значение в новой строке таблицы [Товары (работы, услуги)]
     * для текущей страницы.
     *
     * @param fieldName имя поля
     */
    public void verifyFieldIsNotEmptyOfGoodsWorksServicesTable(String fieldName) {
        String message = String.format("[ОШИБКА]: поле [%s] содержит пустое значение", fieldName);
        By field = this.getBy(goodsCells.find(fieldName));
        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] на не пустое значение.%n", fieldName);
        Assert.assertTrue(message, this.verifyFieldIsNotEmpty(field));
    }

    /**
     * Проверяет не пустое значение поля в новой строке таблицы [Товары (работы, услуги)] для текущей страницы.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение, которое должно содержаться в поле
     */
    public void verifyFieldOfGoodsWorksServicesTable(String fieldName, String value) {
        String message = String.format("[ОШИБКА]: значение [%s] не содержится в поле [%s]", value, fieldName);
        By field = this.getBy(goodsCells.find(fieldName));

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n",
                fieldName, value);
        Assert.assertTrue(message, this.verifyFieldContent(field, value));
    }

    /**
     * Устанавливает значение полей с проверкой в новой строке таблицы [Товары (работы, услуги)] для текущей страницы.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     */
    public void setFieldOfGoodsWorksServicesTable(String fieldName, String value) {
        SelenideElement field = $(this.getBy(goodsCells.find(fieldName))).waitUntil(clickable, timeout, polling);

        field.clear();
        field.sendKeys(value);
        this.logFilledFieldName(fieldName, value);

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        Assert.assertTrue(String.format("[ОШИБКА]: значение [%s] не содержится в поле [%s]", value, fieldName),
                this.verifyFieldContent(field, value));

        // Убираем фокус из поля ввода, иначе введенное в нем значение не воспринимается
        this.removeFocusFromInputFieldJS();
    }

    /**
     * Проверяет, что флажок с указанным именем выбран в новой строке таблицы [Товары (работы, услуги)]
     * для текущей страницы.
     *
     * @param checkBoxName имя флажка
     */
    public void verifyCheckBoxSelectedOfGoodsWorksServicesTable(String checkBoxName) {
        String message = "[ОШИБКА]: флажок [%s] не установлен";
        Assert.assertTrue(
                String.format(message, checkBoxName), this.getCheckBoxValueOfGoodsWorksServicesTable(checkBoxName));
        System.out.printf("[ИНФОРМАЦИЯ]: флажок [%s] находится в отмеченном состоянии.%n", checkBoxName);
    }

    /**
     * Проверяет, что флажок с указанным именем не выбран в новой строке таблицы [Товары (работы, услуги)]
     * для текущей страницы.
     *
     * @param checkBoxName имя флажка
     */
    public void verifyCheckBoxNotSelectedOfGoodsWorksServicesTable(String checkBoxName) {
        Assert.assertFalse(
                String.format("[ОШИБКА]: флажок [%s] установлен", checkBoxName),
                this.getCheckBoxValueOfGoodsWorksServicesTable(checkBoxName));
        System.out.printf("[ИНФОРМАЦИЯ]: флажок [%s] находится в не отмеченном состоянии.%n", checkBoxName);
    }

    /**
     * Возвращает значение флажка с указанным именем в новой строке таблицы [Товары (работы, услуги)]
     * для текущей страницы.
     *
     * @param checkBoxName имя флажка
     * @return значение флажка
     */
    private boolean getCheckBoxValueOfGoodsWorksServicesTable(String checkBoxName) {
        $(this.getBy(goodsCells.find(checkBoxName))).waitUntil(exist, timeout, polling).shouldBe(visible);
        return $(this.getBy(goodsCells.find(checkBoxName))).isSelected();
    }

    /**
     * Заполняет список [Тип объекта закупки] значением [Товар] в новой строке таблицы [Товары (работы, услуги)]
     * для текущей страницы.
     */
    public void setObjectPurchaseItemTypeOfGoodsWorksServicesTable() throws Exception {
        SelenideElement listClosed = $(this.getBy(goodsCells.find("Тип объекта закупки")));
        SelenideElement desiredValue =
                $(this.getBy(goodsCells.find("Значение в раскрытом списке Тип объекта закупки")));
        this.waitForListOpensAndSelectDesiredValue("Тип объекта закупки", listClosed, desiredValue, listClosed);
    }

    /**
     * Заполняет список [Регион поставки] значением [67000000000: г. Севастополь] в новой строке таблицы
     * [Товары (работы, услуги)] для текущей страницы.
     */
    public void setDeliveryRegionOfGoodsWorksServicesTable() throws Exception {
        SelenideElement listClosed = $(this.getBy(goodsCells.find("Регион поставки")));
        SelenideElement desiredValue =
                $(this.getBy(goodsCells.find("Значение в раскрытом списке Регион поставки")));
        this.waitForListOpensAndSelectDesiredValue("Регион поставки", listClosed, desiredValue, listClosed);
    }

    // endregion

    // region Раздел [Документы]

    /**
     * Прикрепляет файл в разделе [Документы].
     *
     * @param fileName имя файла
     */
    public void uploadFileIntoDocuments(String fileName) {
        System.out.printf("[ИНФОРМАЦИЯ]: прикрепляем файл [%s] в разделе [Документы].%n", fileName);
        $(this.getBy(buttonNames.find("Прикрепить файл"))).waitUntil(exist, timeout, polling).sendKeys(fileName);
    }

    /**
     * Проверяет первую сверху ссылку для скачивания прикрепленного файла в разделе [Документы].
     *
     * @param fileName имя файла
     */
    public void checkLinkToDownloadFileFromDocuments(String fileName) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем первую сверху ссылку для скачивания прикрепленного файла [%s] " +
                "в разделе [Документы].%n", fileName);
        ElementsCollection names = $$(this.getBy(documentsColumns.find("Название")));
        names.get(0).waitUntil(visible, timeout, polling).shouldHave(text(fileName));
    }

    // endregion

    // region Блок управляющих кнопок в нижней части страницы

    /**
     * Проверяет  возможность экспортирования в Excel 2007+ плана закупки с текущей страницы.
     */
    public void checkPossibilityToExportTradePlanInExcel2007Format() throws Exception {
        this.checkPossibilityToDownloadFileWithGeneratedName(
                this.getBy(buttonNames.find("Экспортировать в Excel 2007+")),
                "Экспортировать в Excel 2007+", 250);
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
     * Устанавливает значение полей с проверкой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     */
    public void setField(String fieldName, String value) {
        By field = this.getBy(fieldNames.find(fieldName));

        $(field).waitUntil(clickable, timeout, polling).sendKeys(value);
        this.logFilledFieldName(fieldName, value);

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        Assert.assertTrue(String.format("[ОШИБКА]: значение [%s] не содержится в поле [%s]", value, fieldName),
                this.verifyFieldContent(field, value));

        // Убираем фокус из поля ввода, иначе введенное в нем значение не воспринимается
        this.removeFocusFromInputFieldJS();
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
     * Проверяет, что в переключателе не выбрана опция с указанным именем.
     *
     * @param radioButtonName имя опции
     */
    public void verifyRadioButtonNotSelected(String radioButtonName) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем положение переключателя [%s] - ", radioButtonName);
        $(this.getBy(radioButtonNames.find(radioButtonName))).waitUntil(exist, timeout, polling).shouldNotBe(selected);
        System.out.println("Ok, не выбран.");
    }

    /**
     * Проверяет, что в переключателе выбрана опция с указанным именем.
     *
     * @param radioButtonName имя опции
     */
    public void verifyRadioButtonSelected(String radioButtonName) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем положение переключателя [%s] - ", radioButtonName);
        $(this.getBy(radioButtonNames.find(radioButtonName))).waitUntil(exist, timeout, polling).shouldBe(selected);
        System.out.println("Ok, выбран.");
    }

    /**
     * Выбирает в переключателе опцию с указанным именем.
     *
     * @param radioButtonPosition имя опции
     */
    public void selectRadioButton(String radioButtonPosition) throws Exception {
        $(this.getBy(radioButtonNames.find(radioButtonPosition))).waitUntil(exist, timeout, polling).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено переключение в положение [%s].%n", radioButtonPosition);
        TimeUnit.SECONDS.sleep(1);
        this.verifyRadioButtonSelected(radioButtonPosition);
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
        TimeUnit.SECONDS.sleep(10);
    }

    // endregion
}
