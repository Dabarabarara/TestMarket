package ru.PageObjects.Customer.CommonClarificationResponce;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывающий страницу [Запрос на разъяснение (положений документации о закупке/результатов)]
 * ( .kontur.ru/customer/lk/RegisterOfInquiries/ViewInquiries/ ).
 * Created by Vladimir V. Klochkov on 23.10.2019.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class CommonClarificationResponcePage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы [Запрос на разъяснение (положений документации о закупке/результатов)]
    private static final String PAGE_HEADER_XPATH = "//*[@id='layoutwrapper']/div/h2";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отправить разъяснение]
    private static final String PUBLISH_EXPLANATION_RESPONCE_BUTTON_ID = "publishExplanationResponse";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Общие сведения о закупке]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Общие сведения о закупке]
    private static final String COMMON_PURCHASE_INFORMATION_TITLE_XPATH =
            "//h2[contains(., 'Общие сведения о закупке')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки]
    private static final String PURCHASE_NUMBER_XPATH = "//a[contains(@href, '/customer/lk/Auctions/View/')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер редакции извещения]
    private static final String PURCHASE_VERSION_XPATH =
            "//span[contains(., 'Номер редакции извещения:')]/../following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование закупки]
    private static final String PURCHASE_NAME_XPATH =
            "//span[contains(., 'Наименование закупки:')]/../following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о запросе]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о запросе]
    private static final String REQUEST_DETAILS_TITLE_XPATH = "//h2[contains(., 'Сведения о запросе')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер запроса]
    private static final String REQUEST_NUMBER_XPATH =
            "//span[contains(., 'Номер запроса:')]/../following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Текст запроса]
    private static final String REQUEST_TEXT_XPATH =
            "//span[contains(., 'Текст запроса:')]/../following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус запроса]
    private static final String REQUEST_STATUS_XPATH =
            "//span[contains(., 'Статус запроса:')]/../following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Отправитель]
    private static final String REQUEST_SENDER_XPATH =
            "//span[contains(., 'Отправитель:')]/../following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Документы запроса]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Документы запроса]
    private static final String REQUEST_DOCUMENTS_TITLE_XPATH = "//*[@id='RequestDocumentsGrid']/div/label";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Скачать все документы]
    private static final String REQUEST_DOCUMENTS_GRID_DOWNLOAD_ALL_DOCUMENTS_XPATH =
            "//*[@id='RequestDocumentsGrid']//button[contains(., 'Скачать все документы')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Название документа] в таблице [Документы запроса] - все ссылки
    private static final String REQUEST_DOCUMENTS_DOWNLOAD_LINKS_XPATH =
            "//*[@id='RequestDocumentsGrid']/table/tbody/tr/td/a";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Подпись] в таблице [Документы запроса] - все кнопки [Показать]
    private static final String REQUEST_DOCUMENTS_SHOW_BUTTONS_XPATH =
            "//*[@id='RequestDocumentsGrid']//button[contains(., 'Показать')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Разъяснение положений документации закупки]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Разъяснение положений документации закупки]
    private static final String RESPONCE_TITLE_XPATH = "//h2[contains(., 'Разъяснение положений документации закупки')]";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Разъяснение результатов закупки]
    private static final String EXPLANATION_OF_RESULTS_TITLE_XPATH =
            "//h2[contains(., 'Разъяснение результатов закупки')]";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Не публиковать разъяснения на площадке]
    private static final String IS_PRIVATE_ID = "IsPrivate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Не публиковать разъяснения на площадке]
    private static final String IS_PRIVATE_XPATH =
            "//label[contains(., 'Не публиковать разъяснения на площадке:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Текст разъяснения]
    private static final String RESPONCE_TEXT_ID = "ResponseText";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Текст разъяснения без запроса]
    private static final String RESPONCE_TEXT_WITHOUT_REQUEST_XPATH =
            "//label[contains(., 'Текст разъяснения:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Счетчик оставшихся символов в поле [Текст разъяснения]
    private static final String REMAINING_LENGTH_XPATH = "//*[@id='ResponseText']/../div/span";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Предмет запроса]
    private static final String REQUEST_SUBJECT_XPATH =
            "//label[contains(., 'Предмет запроса:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата поступления запроса]
    private static final String REQUEST_DATE_XPATH =
            "//label[contains(., 'Дата поступления запроса:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата принятия решения о предоставлении разъяснений]
    private static final String DECISION_DATE_TO_PROVIDE_CLARIFICATION_XPATH =
            "//label[contains(., 'Дата принятия решения о предоставлении разъяснений:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Документы разъяснения]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Документы разъяснения]
    private static final String RESPONCE_DOCUMENTS_TITLE_XPATH = "//*[@id='ResponseDocumentsGrid']/div/label";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Скачать все документы]
    private static final String RESPONCE_DOCUMENTS_GRID_DOWNLOAD_ALL_DOCUMENTS_XPATH =
            "//*[@id='ResponseDocumentsGrid']//button[contains(., 'Скачать все документы')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Название документа] в таблице [Документы разъяснения] - все ссылки
    private static final String RESPONCE_DOCUMENTS_DOWNLOAD_LINKS_XPATH =
            "//*[@id='ResponseDocumentsGrid']/table/tbody/tr/td/a[contains(@href, 'files/FileDownloadHandler')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дата] в таблице [Документы разъяснения]
    private static final String RESPONCE_DOCUMENTS_DATES_XPATH = "//*[@id='ResponseDocumentsGrid']/table/tbody/tr/td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Подпись] в таблице [Документы разъяснения] - все кнопки [Показать]
    private static final String RESPONCE_DOCUMENTS_SHOW_SIGNATURE_BUTTONS_XPATH =
            "//*[@id='ResponseDocumentsGrid']/table/tbody/tr/td[3]/button";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Действие] в таблице [Документы разъяснения] - все ссылки [Удалить]
    private static final String RESPONCE_DOCUMENTS_REMOVE_DOCUMENT_LINKS_XPATH =
            "//*[@id='ResponseDocumentsGrid']/table/tbody//a[contains(.,'Удалить')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Прикрепить файл]
    private static final String MESSAGE_DOCUMENT_UPLOAD_BUTTON_ID = "MessageDocumentUpload";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary blockNames = new Dictionary();             // основные разделы страницы
    private final Dictionary checkBoxNames = new Dictionary();          // все флажки на странице
    private final Dictionary fieldNames = new Dictionary();             // все поля на странице
    private final Dictionary buttonNames = new Dictionary();            // все кнопки на странице
    private final Dictionary requestDocuments = new Dictionary();       // колонки таблицы [Документы запроса]
    private final Dictionary clarificationDocuments = new Dictionary(); // колонки таблицы [Документы разъяснения]

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CommonClarificationResponcePage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Заголовок страницы", PAGE_HEADER_XPATH);
        blockNames.add("Общие сведения о закупке", COMMON_PURCHASE_INFORMATION_TITLE_XPATH);
        blockNames.add("Сведения о запросе", REQUEST_DETAILS_TITLE_XPATH);
        blockNames.add("Документы запроса", REQUEST_DOCUMENTS_TITLE_XPATH);
        blockNames.add("Разъяснение положений документации закупки", RESPONCE_TITLE_XPATH);
        blockNames.add("Разъяснение результатов закупки", EXPLANATION_OF_RESULTS_TITLE_XPATH);
        blockNames.add("Документы разъяснения", RESPONCE_DOCUMENTS_TITLE_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        checkBoxNames.add("Не публиковать разъяснения на площадке", IS_PRIVATE_ID);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Номер закупки", PURCHASE_NUMBER_XPATH);
        fieldNames.add("Номер редакции извещения", PURCHASE_VERSION_XPATH);
        fieldNames.add("Наименование закупки", PURCHASE_NAME_XPATH);
        fieldNames.add("Номер запроса", REQUEST_NUMBER_XPATH);
        fieldNames.add("Текст запроса", REQUEST_TEXT_XPATH);
        fieldNames.add("Статус запроса", REQUEST_STATUS_XPATH);
        fieldNames.add("Отправитель", REQUEST_SENDER_XPATH);
        fieldNames.add("Не публиковать разъяснения на площадке", IS_PRIVATE_XPATH);
        fieldNames.add("Текст разъяснения", RESPONCE_TEXT_ID);
        fieldNames.add("Текст разъяснения без запроса", RESPONCE_TEXT_WITHOUT_REQUEST_XPATH);
        fieldNames.add("Текст разъяснения - содержит текст разъяснения", RESPONCE_TEXT_WITHOUT_REQUEST_XPATH);
        fieldNames.add("Текст разъяснения - счетчик оставшихся символов", REMAINING_LENGTH_XPATH);
        fieldNames.add("Предмет запроса", REQUEST_SUBJECT_XPATH);
        fieldNames.add("Дата поступления запроса", REQUEST_DATE_XPATH);
        fieldNames.add("Дата принятия решения о предоставлении разъяснений",
                DECISION_DATE_TO_PROVIDE_CLARIFICATION_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        requestDocuments.add("Название документа", REQUEST_DOCUMENTS_DOWNLOAD_LINKS_XPATH);
        requestDocuments.add("Показать подпись", REQUEST_DOCUMENTS_SHOW_BUTTONS_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        clarificationDocuments.add("Название документа", RESPONCE_DOCUMENTS_DOWNLOAD_LINKS_XPATH);
        clarificationDocuments.add("Дата", RESPONCE_DOCUMENTS_DATES_XPATH);
        clarificationDocuments.add("Показать подпись", RESPONCE_DOCUMENTS_SHOW_SIGNATURE_BUTTONS_XPATH);
        clarificationDocuments.add("Ссылки на удаление документа", RESPONCE_DOCUMENTS_REMOVE_DOCUMENT_LINKS_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Отправить разъяснение", PUBLISH_EXPLANATION_RESPONCE_BUTTON_ID);
        buttonNames.add("Скачать все документы запроса", REQUEST_DOCUMENTS_GRID_DOWNLOAD_ALL_DOCUMENTS_XPATH);
        buttonNames.add("Прикрепить файл", MESSAGE_DOCUMENT_UPLOAD_BUTTON_ID);
        buttonNames.add("Скачать все документы разъяснения", RESPONCE_DOCUMENTS_GRID_DOWNLOAD_ALL_DOCUMENTS_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Раздел [Документы запроса]

    /**
     * Проверяет ссылку для скачивания первого сверху прикрепленного файла в разделе [Документы запроса].
     *
     * @param fileName имя файла
     */
    public void checkFirstLinkToDownloadFileFromRequestDocuments(String fileName) {
        ElementsCollection requestDocumentsDownloadLinks =
                $$(this.getBy(REQUEST_DOCUMENTS_DOWNLOAD_LINKS_XPATH)).filterBy(visible);
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем ссылку для скачивания прикрепленного файла [%s] " +
                "в разделе [Документы запроса].%n", fileName);
        requestDocumentsDownloadLinks.get(0).waitUntil(visible, timeout, polling).shouldHave(text(fileName));
    }

    /**
     * Проверяет возможность скачать прикрепленные к [Запросу на разъяснение положений документации о закупке] документы
     * (из раздела [Документы запроса]).
     */
    public void checkPossibilityToDownloadRequestDocuments() throws Exception {
        this.checkPossibilityToDownloadFileWithGeneratedName(
                this.getBy(REQUEST_DOCUMENTS_GRID_DOWNLOAD_ALL_DOCUMENTS_XPATH),
                "Скачать все документы запроса", 200);
    }

    /**
     * Проверяет возможность скачать прикрепленные к [Запросу на разъяснение результатов] документы
     * (из раздела [Документы запроса]).
     */
    public void checkPossibilityToDownloadRequestOfResultsDocuments() throws Exception {
        ElementsCollection requestDocumentsDownloadLinks =
                $$(this.getBy(REQUEST_DOCUMENTS_DOWNLOAD_LINKS_XPATH)).filterBy(visible);
        for (SelenideElement requestDocumentsDownloadLink : requestDocumentsDownloadLinks) {
            this.checkPossibilityToDownloadFileWithGeneratedName(requestDocumentsDownloadLink,
                    requestDocumentsDownloadLink.getText(), 200);
        }
    }

    /**
     * Проверяет наличие кнопки [Показать] в таблице [Документы запроса] для столбца с указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param lineNumber порядковый номер строки в таблице
     */
    public void checkButtonPresenceInLineByNumberFromRequestDocumentsTable(String columnName, int lineNumber) {
        int line = lineNumber - 1;
        ElementsCollection linesWithButtons = $$(this.getBy(requestDocuments.find(columnName)));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] строк с кнопкой [%s].%n",
                linesWithButtons.size(), columnName);
        Assert.assertTrue(String.format("[ОШИБКА]: передан некорректный номер строки: [%d]", lineNumber),
                line >= 0 && line < linesWithButtons.size());
        this.scrollToCenter(linesWithButtons.get(line));
        linesWithButtons.get(line).waitUntil(visible, timeout, polling).shouldBe(clickable);
    }

    /**
     * Нажимает на кнопку [Показать] в таблице [Документы запроса] для столбца с указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param lineNumber порядковый номер строки в таблице
     */
    public void clickOnButtonInLineByNumberFromRequestDocumentsTable(String columnName, int lineNumber)
            throws Exception {
        int line = lineNumber - 1;
        ElementsCollection linesWithButtons = $$(this.getBy(requestDocuments.find(columnName)));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] строк с кнопкой [%s].%n", linesWithButtons.size(), columnName);
        Assert.assertTrue(String.format("[ОШИБКА]: передан некорректный номер строки: [%d]", lineNumber),
                line >= 0 && line < linesWithButtons.size());
        this.scrollToCenter(linesWithButtons.get(line));
        linesWithButtons.get(line).waitUntil(visible, timeout, polling).shouldBe(clickable);
        this.clickInElementJS(linesWithButtons.get(line));
        this.logPressedButtonName(columnName);
    }

    // endregion

    // region Раздел [Документы разъяснения]

    /**
     * Проверяет текст ячейки в таблице [Документы разъяснения] для столбца с указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     */
    public void verifyCellByColumnNameAndLineNumberFromResponceDocumentsTable(
            String columnName, String rowNumber, String cellValue) {
        this.verifyCellInTableByColumnElementsAndLineNumberForText("Документы разъяснения", columnName,
                $$(this.getBy(clarificationDocuments.find(columnName))), rowNumber, cellValue);
    }

    /**
     * Проверяет наличие кнопки в таблице [Документы разъяснения] для столбца с указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param lineNumber порядковый номер строки в таблице
     */
    public void checkButtonPresenceInLineByNumberFromResponceDocumentsTable(String columnName, int lineNumber) {
        int line = lineNumber - 1;
        ElementsCollection linesWithButtons = $$(this.getBy(clarificationDocuments.find(columnName)));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] строк с кнопкой [%s].%n", linesWithButtons.size(), columnName);
        Assert.assertTrue(String.format("[ОШИБКА]: передан некорректный номер строки: [%d]", lineNumber),
                line >= 0 && line < linesWithButtons.size());
        this.scrollToCenter(linesWithButtons.get(line));
        linesWithButtons.get(line).waitUntil(visible, timeout, polling).shouldBe(clickable);
    }

    /**
     * Нажимает на кнопку в таблице [Документы разъяснения] для столбца с указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param lineNumber порядковый номер строки в таблице
     */
    public void clickOnButtonInLineByNumberFromClarificationRequestsTable(String columnName, int lineNumber)
            throws Exception {
        int line = lineNumber - 1;
        ElementsCollection linesWithButtons = $$(this.getBy(clarificationDocuments.find(columnName)));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] строк с кнопкой [%s].%n", linesWithButtons.size(), columnName);
        Assert.assertTrue(String.format("[ОШИБКА]: передан некорректный номер строки: [%d]", lineNumber),
                line >= 0 && line < linesWithButtons.size());
        this.scrollToCenter(linesWithButtons.get(line));
        linesWithButtons.get(line).waitUntil(visible, timeout, polling).shouldBe(clickable);
        this.clickInElementJS(linesWithButtons.get(line));
        this.logPressedButtonName(columnName);
    }

    /**
     * Прикрепляет файл в разделе [Документы разъяснения].
     *
     * @param fileName имя файла
     */
    public void uploadFileIntoResponceDocuments(String fileName) {
        System.out.printf("[ИНФОРМАЦИЯ]: прикрепляем файл [%s] в разделе [Документы разъяснения].%n", fileName);
        $(this.getBy(MESSAGE_DOCUMENT_UPLOAD_BUTTON_ID)).waitUntil(exist, timeout, polling).sendKeys(fileName);
    }

    /**
     * Проверяет ссылку для скачивания первого сверху прикрепленного файла в разделе [Документы разъяснения].
     *
     * @param fileName имя файла
     */
    public void checkFirstLinkToDownloadFileFromResponceDocuments(String fileName) {
        ElementsCollection responceDocumentsDownloadLinks =
                $$(this.getBy(RESPONCE_DOCUMENTS_DOWNLOAD_LINKS_XPATH)).filterBy(visible);
        ElementsCollection responceDocumentsDates = $$(this.getBy(RESPONCE_DOCUMENTS_DATES_XPATH)).filterBy(visible);

        System.out.printf("[ИНФОРМАЦИЯ]: проверяем ссылку для скачивания прикрепленного файла [%s] " +
                "в разделе [Документы разъяснения].%n", fileName);
        responceDocumentsDownloadLinks.get(0).waitUntil(visible, timeout, polling).shouldHave(text(fileName));

        System.out.println("[ИНФОРМАЦИЯ]: проверяем отображение даты для прикрепленного файла " +
                "в разделе [Документы разъяснения].");
        Assert.assertFalse("[ОШИБКА]: значение в столбце [Дата] для прикрепленного файла отсутствует",
                responceDocumentsDates.get(0).waitUntil(visible, timeout, polling).getText().isEmpty());
    }

    /**
     * Проверяет ссылку для скачивания первого сверху прикрепленного файла в разделе [Документы разъяснения].
     *
     * @param fileName имя файла
     */
    public void checkFirstLinkToDownloadFileFromResponceDocumentsAndRemoveDocumentLinks(String fileName) {
        ElementsCollection responceDocumentsRemoveDocumentLinks =
                $$(this.getBy(RESPONCE_DOCUMENTS_REMOVE_DOCUMENT_LINKS_XPATH)).filterBy(visible);

        this.checkFirstLinkToDownloadFileFromResponceDocuments(fileName);

        System.out.println("[ИНФОРМАЦИЯ]: проверяем отображение ссылки [Удалить] для прикрепленного файла " +
                "в разделе [Документы разъяснения].");
        responceDocumentsRemoveDocumentLinks.get(0).waitUntil(visible, timeout, polling).shouldHave(text("Удалить"));
    }

    /**
     * Проверяет возможность скачать прикрепленные к
     * [Запросу на разъяснение (положений документации о закупке/результатов)] документы
     * (из раздела [Документы разъяснения]).
     */
    public void checkPossibilityToDownloadResponceDocuments() throws Exception {
        this.checkPossibilityToDownloadFileWithGeneratedName(
                this.getBy(RESPONCE_DOCUMENTS_GRID_DOWNLOAD_ALL_DOCUMENTS_XPATH),
                "Скачать все документы разъяснения", 250);
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
        System.out.printf("[ИНФОРМАЦИЯ]: Произведен переход к блоку полей: [%s].%n", blockName);
        TimeUnit.SECONDS.sleep(3);
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
     * Проверяет, что флажок с указанным именем выбран.
     *
     * @param checkBoxName имя флажка
     */
    public void verifyCheckBoxSelected(String checkBoxName) {
        String message = "[ОШИБКА]: флажок [%s] не установлен";
        Assert.assertTrue(String.format(message, checkBoxName), this.getCheckBoxValue(checkBoxName));
        System.out.printf("[ИНФОРМАЦИЯ]: флажок: [%s] находится в отмеченном состоянии.%n", checkBoxName);
    }

    /**
     * Проверяет, что флажок с указанным именем не выбран.
     *
     * @param checkBoxName имя флажка
     */
    public void verifyCheckBoxNotSelected(String checkBoxName) {
        Assert.assertFalse(
                String.format("[ОШИБКА]: флажок [%s] установлен", checkBoxName), this.getCheckBoxValue(checkBoxName));
        System.out.printf("[ИНФОРМАЦИЯ]: флажок: [%s] находится в не отмеченном состоянии.%n", checkBoxName);
    }

    /**
     * Устанавливает значение полей.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     */
    public void setField(String fieldName, String value) {
        $(this.getBy(fieldNames.find(fieldName))).waitUntil(clickable, timeout, polling).sendKeys(value);
        this.logFilledFieldName(fieldName, value);
    }

    /**
     * Проверяет значение поля.
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
     * Проверяет, что поле содержит не пустое значение.
     *
     * @param fieldName имя поля
     */
    public void verifyFieldIsNotEmpty(String fieldName) {
        String message = String.format("[ОШИБКА]: поле: [%s] содержит пустое значение", fieldName);

        By field = this.getBy(fieldNames.find(fieldName));

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] на не пустое значение.%n", fieldName);
        Assert.assertTrue(message, this.verifyFieldIsNotEmpty(field));
    }

    /**
     * Делает щелчок мышью по указанной кнопке.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.scrollToCenterAndclickInElementJS(this.getBy(buttonNames.find(buttonName)));
        this.logPressedButtonName(buttonName);
        TimeUnit.SECONDS.sleep(3);
    }

    // endregion
}
