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
 * Класс для работы со страницей [Сведения о расторжении договора XXXX.XXXX]
 * ( .kontur.ru/customer/lk/Contracts/TerminationView/ )
 * ( Главная / Заказчикам / Мои договоры / Сведения о расторжении договора XXXX.XXXX ).
 * Created by Vladimir V. Klochkov on 21.01.2021.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class ContractsTerminationViewPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы
    private static final String PAGE_HEADER_XPATH = "//h2[contains(.,'Сведения о расторжении договора')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о договоре]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о договоре]
    private static final String CONTRACT_INFO_BLOCK_XPATH = "//h3[contains(.,'Сведения о договоре')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер договора]
    private static final String CONTRACT_INFO_CARD_NUMBER_XPATH =
            "//label[contains(.,'Номер договора:')]/following-sibling::div[1]/a";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Предмет договора]
    private static final String CONTRACT_INFO_SUBJECT_NAME_XPATH =
            "//label[contains(.,'Предмет договора:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки]
    private static final String CONTRACT_INFO_PURCHASE_NUMBER_XPATH =
            "//label[contains(.,'Номер закупки:')]/following-sibling::div[1]/a";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Заказчик]
    private static final String CONTRACT_INFO_CUSTOMER_NAME_XPATH =
            "//label[contains(.,'Заказчик:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Поставщик]
    private static final String CONTRACT_INFO_SUPPLIER_NAME_XPATH =
            "//label[contains(.,'Поставщик:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата заключения договора]
    private static final String CONTRACT_INFO_CONCLUSION_DATE_XPATH =
            "//label[contains(.,'Дата заключения договора:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о расторжении договора]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о расторжении договора]
    private static final String CONTRACT_TERMINATION_INFO_BLOCK_XPATH =
            "//h3[contains(.,'Сведения о расторжении договора')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус]
    private static final String CONTRACT_TERMINATION_STATUS_XPATH =
            "//label[contains(.,'Статус:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер версии сведений]
    private static final String CONTRACT_TERMINATION_VERSION_NUMBER_XPATH =
            "//label[contains(.,'Номер версии сведений:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата расторжения договора]
    private static final String CONTRACT_TERMINATION_CANCELLATION_DATE_XPATH =
            "//label[contains(.,'Дата расторжения договора:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата создания]
    private static final String CONTRACT_TERMINATION_CREATE_DATE_XPATH =
            "//label[contains(.,'Дата создания:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата размещения сведений]
    private static final String CONTRACT_TERMINATION_INFO_POSTED_DATE_XPATH =
            "//label[contains(.,'Дата размещения сведений:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата публикации на ЭП]
    private static final String CONTRACT_TERMINATION_PUBLICATION_DATE_XPATH =
            "//label[contains(.,'Дата публикации на ЭП:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Основание расторжения договора]
    private static final String CONTRACT_TERMINATION_BASE_XPATH =
            "//label[contains(.,'Основание расторжения договора')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Причина расторжения договора]
    private static final String CONTRACT_TERMINATION_REASON_TEXT_XPATH =
            "//label[contains(.,'Причина расторжения договора')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Исполнение договора завершено]
    private static final String CONTRACT_TERMINATION_IS_COMPLETED_XPATH =
            "//label[contains(.,'Исполнение договора завершено')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Начислены неустойки]
    private static final String CONTRACT_TERMINATION_HAS_PENALTY_XPATH =
            "//label[contains(.,'Начислены неустойки')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Информация о неустойках]
    private static final String CONTRACT_TERMINATION_PENALTY_INFO_XPATH =
            "//label[contains(.,'Информация о неустойках')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о документе-основании расторжения договора]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о документе-основании расторжения договора]
    private static final String TERM_BASIS_DOC_INFO_BLOCK_XPATH =
            "//h3[contains(.,'Сведения о документе-основании расторжения договора')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип документа-основания расторжения договора]
    private static final String TERM_BASIS_DOC_TYPE_VALUE_XPATH =
            "//label[contains(.,'Тип документа-основания расторжения договора:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Документ-основание расторжения договора]
    private static final String TERM_BASIS_DOC_DOCUMENT_XPATH =
            "//label[contains(.,'Документ-основание расторжения договора:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер документа-основания]
    private static final String TERM_BASIS_DOC_DOCUMENT_NUMBER_XPATH =
            "//label[contains(.,'Номер документа-основания:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата документа-основания]
    private static final String TERM_BASIS_DOC_DOCUMENT_DATE_XPATH =
            "//label[contains(.,'Дата документа-основания:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Документы сведений о расторжении договора]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Документы сведений о расторжении договора]
    private static final String TERM_ADDITIONAL_DOCUMENTS_BLOCK_XPATH =
            "//h3[contains(.,'Документы сведений о расторжении договора')]";
    //------------------------------------------------------------------------------------------------------------------

    // region Таблица [Документы сведений о расторжении договора]

    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Название документа]
    private static final String DOCUMENTS_TABLE_DOCUMENT_NAME_COLUMN_XPATH =
            "//div[@id='DocumentsGrid']//tbody/tr/td[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Файл документа]
    private static final String DOCUMENTS_TABLE_DOCUMENT_FILE_LINK_COLUMN_XPATH =
            "//div[@id='DocumentsGrid']//tbody/tr/td[2]/a";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дата]
    private static final String DOCUMENTS_TABLE_DOCUMENT_DATE_COLUMN_XPATH =
            "//div[@id='DocumentsGrid']//tbody/tr/td[3]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Сведения об ЭЦП]
    private static final String DOCUMENTS_TABLE_DOCUMENT_SIGNATURE_LINK_COLUMN_XPATH =
            "//div[@id='DocumentsGrid']//tbody/tr/td[4]/a";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // endregion

    // region Блок управляющих кнопок в нижней части страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отменить]
    private static final String TERMINATION_CANCEL_BUTTON_XPATH = "//button[contains(.,'Отменить')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Внести изменения]
    private static final String TERMINATION_MODIFY_BUTTON_XPATH = "//button[contains(.,'Внести изменения')]";
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
    private final Dictionary blockNames = new Dictionary();       // основные разделы сведений о расторжении договора
    private final Dictionary fieldNames = new Dictionary();       // все поля на странице
    private final Dictionary buttonNames = new Dictionary();      // все кнопки на странице
    private final Dictionary documentsColumns =
            new Dictionary();               // все колонки таблицы в разделе [Документы сведений о расторжении договора]

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public ContractsTerminationViewPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Заголовок страницы", PAGE_HEADER_XPATH);
        blockNames.add("Сведения о договоре", CONTRACT_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о расторжении договора", CONTRACT_TERMINATION_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о документе-основании расторжения договора", TERM_BASIS_DOC_INFO_BLOCK_XPATH);
        blockNames.add("Документы сведений о расторжении договора", TERM_ADDITIONAL_DOCUMENTS_BLOCK_XPATH);
        blockNames.add("Подвал страницы", SERVER_VERSION_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Номер договора", CONTRACT_INFO_CARD_NUMBER_XPATH);
        fieldNames.add("Предмет договора", CONTRACT_INFO_SUBJECT_NAME_XPATH);
        fieldNames.add("Номер закупки", CONTRACT_INFO_PURCHASE_NUMBER_XPATH);
        fieldNames.add("Заказчик", CONTRACT_INFO_CUSTOMER_NAME_XPATH);
        fieldNames.add("Поставщик", CONTRACT_INFO_SUPPLIER_NAME_XPATH);
        fieldNames.add("Дата заключения договора", CONTRACT_INFO_CONCLUSION_DATE_XPATH);
        fieldNames.add("Статус", CONTRACT_TERMINATION_STATUS_XPATH);
        fieldNames.add("Номер версии сведений", CONTRACT_TERMINATION_VERSION_NUMBER_XPATH);
        fieldNames.add("Дата расторжения договора", CONTRACT_TERMINATION_CANCELLATION_DATE_XPATH);
        fieldNames.add("Дата создания", CONTRACT_TERMINATION_CREATE_DATE_XPATH);
        fieldNames.add("Дата размещения сведений", CONTRACT_TERMINATION_INFO_POSTED_DATE_XPATH);
        fieldNames.add("Дата публикации на ЭП", CONTRACT_TERMINATION_PUBLICATION_DATE_XPATH);
        fieldNames.add("Основание расторжения договора", CONTRACT_TERMINATION_BASE_XPATH);
        fieldNames.add("Причина расторжения договора", CONTRACT_TERMINATION_REASON_TEXT_XPATH);
        fieldNames.add("Исполнение договора завершено", CONTRACT_TERMINATION_IS_COMPLETED_XPATH);
        fieldNames.add("Начислены неустойки", CONTRACT_TERMINATION_HAS_PENALTY_XPATH);
        fieldNames.add("Информация о неустойках", CONTRACT_TERMINATION_PENALTY_INFO_XPATH);
        fieldNames.add("Тип документа-основания расторжения договора",
                TERM_BASIS_DOC_TYPE_VALUE_XPATH);
        fieldNames.add("Документ-основание расторжения договора", TERM_BASIS_DOC_DOCUMENT_XPATH);
        fieldNames.add("Номер документа-основания", TERM_BASIS_DOC_DOCUMENT_NUMBER_XPATH);
        fieldNames.add("Дата документа-основания", TERM_BASIS_DOC_DOCUMENT_DATE_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Номер договора", CONTRACT_INFO_CARD_NUMBER_XPATH);
        buttonNames.add("Отменить", TERMINATION_CANCEL_BUTTON_XPATH);
        buttonNames.add("Внести изменения", TERMINATION_MODIFY_BUTTON_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        documentsColumns.add("Название документа", DOCUMENTS_TABLE_DOCUMENT_NAME_COLUMN_XPATH);
        documentsColumns.add("Файл документа", DOCUMENTS_TABLE_DOCUMENT_FILE_LINK_COLUMN_XPATH);
        documentsColumns.add("Дата", DOCUMENTS_TABLE_DOCUMENT_DATE_COLUMN_XPATH);
        documentsColumns.add("Сведения об ЭЦП", DOCUMENTS_TABLE_DOCUMENT_SIGNATURE_LINK_COLUMN_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Раздел [Документы сведений о расторжении договора]

    // region Таблица [Документы сведений о расторжении договора]

    /**
     * Проверяет текст ячейки в таблице [Документы сведений о расторжении договора]
     * для столбца с указанным именем и номером строки.
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
     * Переходит по ссылке в таблице [Документы сведений о расторжении договора]
     * для столбца с указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     */
    public void clickOnLinkInLineByNumberFromDocumentsTable(String columnName, int rowNumber) throws Exception {
        int line = rowNumber - 1;
        ElementsCollection linesWithButtons = $$(this.getBy(documentsColumns.find(columnName)));
        System.out.printf("[ИНФОРМАЦИЯ]: в столбце [%s] обнаружено [%d] строк.%n",
                columnName, linesWithButtons.size());
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
