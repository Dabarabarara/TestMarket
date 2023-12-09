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
 * Класс для работы со страницей [Сведения о расторжении договора XXXX.XXXX]
 * ( .kontur.ru/customer/lk/Contracts/TerminationEdit?dealId= )
 * ( Главная / Заказчикам / Мои договоры / Сведения о расторжении договора XXXX.XXXX ).
 * Created by Vladimir V. Klochkov on 28.12.2020.
 * Updated by Vladimir V. Klochkov on 11.02.2021.
 */
public class ContractsTerminationEditPage extends CommonCustomerPage {
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
            "//input[@data-bind='datevalue: CancellationDate']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата создания]
    private static final String CONTRACT_TERMINATION_CREATE_DATE_ID = "CreateDate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата размещения сведений]
    private static final String CONTRACT_TERMINATION_PUBLICATION_DATE_ID = "PublicationDate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Основание расторжения договора] (кнопка [V] - развернуть список)
    private static final String CONTRACT_TERMINATION_BASE_LIST_CLOSED_XPATH =
            "//label[contains(.,'Основание расторжения договора')]/following-sibling::div[1]" +
                    "//span[@class='k-icon k-i-arrow-s']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Основание расторжения договора] (список возможных значений поля открыт) -> требуемое значение в списке
    private static final String CONTRACT_TERMINATION_BASE_VALUE_IN_OPENED_LIST_XPATH =
            "//li[contains(.,'Судебный акт')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Основание расторжения договора] (текущее значение)
    private static final String CONTRACT_TERMINATION_BASE_VALUE_XPATH =
            "//label[contains(.,'Основание расторжения договора')]/following-sibling::div[1]//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Причина расторжения договора]
    private static final String CONTRACT_TERMINATION_REASON_TEXT_XPATH = "//textarea[@name='ReasonText']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Причина расторжения договора - счетчик оставшихся символов]
    private static final String CONTRACT_TERMINATION_REASON_TEXT_REMAINING_LENGTH_XPATH =
            "//textarea[@name='ReasonText']/following-sibling::div[1]/span";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Исполнение договора завершено]
    private static final String CONTRACT_TERMINATION_IS_COMPLETED_ID = "IsCompleted";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Начислены неустойки]
    private static final String CONTRACT_TERMINATION_HAS_PENALTY_ID = "HasPenalty";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Информация о неустойках]
    private static final String CONTRACT_TERMINATION_PENALTY_INFO_ID = "PenaltyInfo";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о документе-основании расторжения договора]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о документе-основании расторжения договора]
    private static final String TERM_BASIS_DOC_INFO_BLOCK_XPATH =
            "//h3[contains(.,'Сведения о документе-основании расторжения договора')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип документа-основания расторжения договора] (кнопка [V] - развернуть список)
    private static final String TERM_BASIS_DOC_TYPE_LIST_CLOSED_XPATH =
            "//span[@aria-owns='DocumentType_listbox']//span[@class='k-icon k-i-arrow-s']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип документа-основания расторжения договора] (список возможных значений поля открыт) ->
    // требуемое значение в списке
    private static final String TERM_BASIS_DOC_TYPE_VALUE_IN_OPENED_LIST_XPATH =
            "//ul[@id='DocumentType_listbox']//li[contains(.,'Судебный акт')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип документа-основания расторжения договора] (текущее значение)
    private static final String TERM_BASIS_DOC_TYPE_VALUE_XPATH =
            "//span[@aria-owns='DocumentType_listbox']//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Документ-основание расторжения договора]
    private static final String TERM_BASIS_DOC_DOCUMENT_ID = "Document";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер документа-основания]
    private static final String TERM_BASIS_DOC_DOCUMENT_NUMBER_ID = "DocumentNumber";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата документа-основания]
    private static final String TERM_BASIS_DOC_DOCUMENT_DATE_ID = "DocumentDate";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Документы сведений о расторжении договора]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Документы]
    private static final String DOCUMENTS_BLOCK_XPATH = "//h3[contains(.,'Документы сведений о расторжении договора')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Прикрепить файл]
    private static final String UPLOAD_FILE_TERMINATION_BUTTON_ID = "UploadFile_Termination";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка для скачивания первого из прикрепленных к сведениям о расторжении договора файлов
    private static final String DOWNLOAD_FILE_TERMINATION_XPATH =
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
    private static final String DATEPICKER_HIDE_BUTTON_XPATH =
            "//div[@id='ui-datepicker-div']//button[text()='Закрыть']";
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
    private final Dictionary blockNames = new Dictionary();    // основные разделы сведений о расторжении договора
    private final Dictionary checkBoxNames = new Dictionary(); // все флажки на странице
    private final Dictionary fieldNames = new Dictionary();    // все поля на странице
    private final Dictionary buttonNames = new Dictionary();   // все кнопки на странице

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public ContractsTerminationEditPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Заголовок страницы", PAGE_HEADER_XPATH);
        blockNames.add("Сведения о договоре", CONTRACT_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о расторжении договора", CONTRACT_TERMINATION_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о документе-основании расторжения договора", TERM_BASIS_DOC_INFO_BLOCK_XPATH);
        blockNames.add("Документы сведений о расторжении договора", DOCUMENTS_BLOCK_XPATH);
        blockNames.add("Подвал страницы", SERVER_VERSION_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        checkBoxNames.add("Исполнение договора завершено", CONTRACT_TERMINATION_IS_COMPLETED_ID);
        checkBoxNames.add("Начислены неустойки", CONTRACT_TERMINATION_HAS_PENALTY_ID);
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
        fieldNames.add("Дата создания", CONTRACT_TERMINATION_CREATE_DATE_ID);
        fieldNames.add("Дата размещения сведений", CONTRACT_TERMINATION_PUBLICATION_DATE_ID);
        fieldNames.add("Основание расторжения договора развернуть список", CONTRACT_TERMINATION_BASE_LIST_CLOSED_XPATH);
        fieldNames.add("Основание расторжения договора требуемое значение в списке",
                CONTRACT_TERMINATION_BASE_VALUE_IN_OPENED_LIST_XPATH);
        fieldNames.add("Основание расторжения договора текущее значение", CONTRACT_TERMINATION_BASE_VALUE_XPATH);
        fieldNames.add("Причина расторжения договора", CONTRACT_TERMINATION_REASON_TEXT_XPATH);
        fieldNames.add("Причина расторжения договора - счетчик оставшихся символов",
                CONTRACT_TERMINATION_REASON_TEXT_REMAINING_LENGTH_XPATH);
        fieldNames.add("Информация о неустойках", CONTRACT_TERMINATION_PENALTY_INFO_ID);
        fieldNames.add("Тип документа-основания расторжения договора развернуть список",
                TERM_BASIS_DOC_TYPE_LIST_CLOSED_XPATH);
        fieldNames.add("Тип документа-основания расторжения договора требуемое значение в списке",
                TERM_BASIS_DOC_TYPE_VALUE_IN_OPENED_LIST_XPATH);
        fieldNames.add("Тип документа-основания расторжения договора текущее значение",
                TERM_BASIS_DOC_TYPE_VALUE_XPATH);
        fieldNames.add("Документ-основание расторжения договора", TERM_BASIS_DOC_DOCUMENT_ID);
        fieldNames.add("Номер документа-основания", TERM_BASIS_DOC_DOCUMENT_NUMBER_ID);
        fieldNames.add("Дата документа-основания", TERM_BASIS_DOC_DOCUMENT_DATE_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Прикрепить файл", UPLOAD_FILE_TERMINATION_BUTTON_ID);
        buttonNames.add("Сохранить", SAVE_DRAFT_BUTTON_XPATH);
        buttonNames.add("Опубликовать", PUBLISH_BUTTON_XPATH);
        buttonNames.add("Удалить", REMOVE_BUTTON_XPATH);
        buttonNames.add("'Закрыть' - в окне 'Календарь'", DATEPICKER_HIDE_BUTTON_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Раздел [Сведения о расторжении договора]

    /**
     * Заполняет поле [Основание расторжения договора] фиксированным значением [Судебный акт] из списка выбора.
     */
    public void fillContractTerminationBaseFieldWithJudicialActValue() throws Exception {
        SelenideElement collapsedList = $(this.getBy(CONTRACT_TERMINATION_BASE_LIST_CLOSED_XPATH));
        SelenideElement desiredValue = $(this.getBy(CONTRACT_TERMINATION_BASE_VALUE_IN_OPENED_LIST_XPATH));
        SelenideElement selectedValue = $(this.getBy(CONTRACT_TERMINATION_BASE_VALUE_XPATH));
        this.waitForListOpensAndSelectDesiredValue(
                "Основание расторжения договора", collapsedList, desiredValue, selectedValue);
    }

    // endregion

    // region Раздел [Сведения о документе-основании расторжения договора]

    /**
     * Заполняет поле [Тип документа-основания расторжения договора] фиксированным значением [Судебный акт]
     * из списка выбора.
     */
    public void fillContractTerminationBasisDocumentFieldWithJudicialActValue() throws Exception {
        SelenideElement collapsedList = $(this.getBy(TERM_BASIS_DOC_TYPE_LIST_CLOSED_XPATH));
        SelenideElement desiredValue = $(this.getBy(TERM_BASIS_DOC_TYPE_VALUE_IN_OPENED_LIST_XPATH));
        SelenideElement selectedValue = $(this.getBy(TERM_BASIS_DOC_TYPE_VALUE_XPATH));
        this.waitForListOpensAndSelectDesiredValue(
                "Тип документа-основания расторжения договора", collapsedList, desiredValue, selectedValue);
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
        $(this.getBy(UPLOAD_FILE_TERMINATION_BUTTON_ID)).waitUntil(exist, timeout, polling).sendKeys(fileName);
    }

    /**
     * Проверяет ссылку для скачивания первого прикрепленного файла в разделе [Документы].
     *
     * @param fileName имя файла
     */
    public void checkLinkToDownloadFileIntoDocuments(String fileName) throws Exception {
        System.out.printf(
                "[ИНФОРМАЦИЯ]: проверяем ссылку для скачивания первого прикрепленного файла [%s] " +
                        "в разделе [Документы].%n", fileName);
        this.getCurrentServerVersion();
        $(this.getBy(DOWNLOAD_FILE_TERMINATION_XPATH)).waitUntil(visible, timeout, polling).shouldHave(text(fileName));
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
