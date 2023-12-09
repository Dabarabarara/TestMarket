package ru.PageObjects.Customer.CommonClarificationRequest;

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
 * Класс описывающий страницу [Запрос на разъяснение заявки] - режим создания / режим редактирования
 * ( .kontur.ru/customer/lk/RegisterOfInquiries/CreateInquiry?applicationId= )
 * ( .kontur.ru/customer/lk/RegisterOfInquiries/EditInquiry?inquiryId= ).
 * Created by Vladimir V. Klochkov on 17.09.2020.
 * Updated by Vladimir V. Klochkov on 11.03.2021.
 */
public class CreateInquiryEditInquiryPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы [Запрос на разъяснение заявки]
    private static final String PAGE_HEADER_XPATH = "//*[@id='layoutwrapper']/div/h2";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отправить запрос]
    private static final String SEND_REQUEST_BUTTON_ID = "publishResponse";
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
    // Поле [Номер лота]
    private static final String LOT_NUMBER_XPATH =
            "//span[contains(., 'Номер лота:')]/../following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование лота]
    private static final String LOT_NAME_XPATH =
            "//span[contains(., 'Наименование лота:')]/../following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Запрос на разъяснение заявки на участие в закупке]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Запрос на разъяснение заявки на участие в закупке]
    private static final String REQUEST_FOR_EXPLANATION_OF_APPLICATION_TITLE_XPATH =
            "//h2[contains(., 'Запрос на разъяснение заявки на участие в закупке')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Участник закупки]
    private static final String PROCUREMENT_PARTICIPANT_XPATH =
            "//span[contains(., 'Участник закупки:')]/../following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер заявки]
    private static final String APPLICATION_NUMBER_XPATH =
            "//span[contains(., 'Номер заявки:')]/../following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок предоставления ответа (МСК)]
    private static final String EXPIRE_RESPONSE_DATE_ID = "ExpireResponseDate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Текст запроса]
    private static final String REQUEST_TEXT_XPATH = "//textarea[@name='RequestText']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [осталось] для поля [Текст запроса]
    private static final String REQUEST_REMAINING_LENGTH_XPATH = "//span[contains(@id,'remainingLength')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Документы запроса]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Документы запроса]
    private static final String REQUEST_DOCUMENTS_TITLE_XPATH = "//label[contains(.,'Документы запроса')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Название документа] в таблице [Документы запроса] - все ссылки на скачивание документов
    private static final String REQUEST_DOCUMENTS_DOWNLOAD_LINKS_XPATH =
            "//a[contains(@href,'/files/FileDownloadHandler.ashx?')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Действие] в таблице [Документы запроса] - все ссылки [Удалить]
    private static final String REQUEST_DOCUMENTS_DELETE_LINKS_XPATH = "//a[contains(., 'Удалить')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Прикрепить файл]
    private static final String MESSAGE_DOCUMENT_UPLOAD_BUTTON_INPUT_XPATH = "//input[@id='MessageDocumentUpload']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary blockNames = new Dictionary();       // основные разделы страницы
    private final Dictionary fieldNames = new Dictionary();       // все поля на странице
    private final Dictionary buttonNames = new Dictionary();      // все кнопки на странице
    private final Dictionary requestDocuments = new Dictionary(); // колонки таблицы [Документы запроса]

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CreateInquiryEditInquiryPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Заголовок страницы", PAGE_HEADER_XPATH);
        blockNames.add("Общие сведения о закупке", COMMON_PURCHASE_INFORMATION_TITLE_XPATH);
        blockNames.add("Запрос на разъяснение заявки на участие в закупке",
                REQUEST_FOR_EXPLANATION_OF_APPLICATION_TITLE_XPATH);
        blockNames.add("Документы запроса", REQUEST_DOCUMENTS_TITLE_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Номер закупки", PURCHASE_NUMBER_XPATH);
        fieldNames.add("Номер редакции извещения", PURCHASE_VERSION_XPATH);
        fieldNames.add("Наименование закупки", PURCHASE_NAME_XPATH);
        fieldNames.add("Номер лота", LOT_NUMBER_XPATH);
        fieldNames.add("Наименование лота", LOT_NAME_XPATH);
        fieldNames.add("Участник закупки", PROCUREMENT_PARTICIPANT_XPATH);
        fieldNames.add("Номер заявки", APPLICATION_NUMBER_XPATH);
        fieldNames.add("Срок предоставления ответа (МСК)", EXPIRE_RESPONSE_DATE_ID);
        fieldNames.add("Текст запроса", REQUEST_TEXT_XPATH);
        fieldNames.add("осталось", REQUEST_REMAINING_LENGTH_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        requestDocuments.add("Название документа", REQUEST_DOCUMENTS_DOWNLOAD_LINKS_XPATH);
        requestDocuments.add("Действие", REQUEST_DOCUMENTS_DELETE_LINKS_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Отправить запрос", SEND_REQUEST_BUTTON_ID);
        buttonNames.add("Прикрепить файл", MESSAGE_DOCUMENT_UPLOAD_BUTTON_INPUT_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Раздел [Документы запроса]

    /**
     * Прикрепляет файл в разделе [Документы запроса].
     *
     * @param fileName имя файла
     */
    public void uploadFileIntoRequestDocuments(String fileName) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: прикрепляем файл [%s] в разделе [Документы запроса].%n", fileName);
        $(this.getBy(MESSAGE_DOCUMENT_UPLOAD_BUTTON_INPUT_XPATH)).waitUntil(exist, timeout, polling).sendKeys(fileName);

        // Таймаут нужен для завершения всех операций ( ссылка появляется раньше, чем завершается загрузка файла )
        TimeUnit.SECONDS.sleep(15);
    }

    /**
     * Проверяет текст ссылки для скачивания первого сверху прикрепленного файла в разделе [Документы запроса].
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
     * Проверяет отображение ссылки [Удалить] для первого сверху прикрепленного файла в разделе [Документы запроса].
     */
    public void checkFirstRemoveDocumentLinkFromRequestDocuments() {
        ElementsCollection requestDocumentsRemoveDocumentLinks =
                $$(this.getBy(REQUEST_DOCUMENTS_DELETE_LINKS_XPATH)).filterBy(visible);
        System.out.println("[ИНФОРМАЦИЯ]: проверяем отображение ссылки [Удалить] для прикрепленного файла " +
                "в разделе [Документы запроса].");
        requestDocumentsRemoveDocumentLinks.get(0).waitUntil(visible, timeout, polling).shouldHave(text("Удалить"));
    }

    /**
     * Проверяет возможность скачать прикрепленные документы в разделе [Документы запроса].
     */
    public void checkPossibilityToDownloadRequestDocuments() throws Exception {
        ElementsCollection requestDocumentsDownloadLinks =
                $$(this.getBy(REQUEST_DOCUMENTS_DOWNLOAD_LINKS_XPATH)).filterBy(visible);
        for (SelenideElement requestDocumentsDownloadLink : requestDocumentsDownloadLinks) {
            this.checkPossibilityToDownloadFileWithGeneratedName(requestDocumentsDownloadLink,
                    requestDocumentsDownloadLink.getText(), 200);
        }
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
        this.waitForPageLoad(5);
    }

    // endregion
}
