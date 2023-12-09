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
 * Класс описывающий страницу [Разъяснение заявки]
 * ( .kontur.ru/customer/lk/RegisterOfInquiries/ViewInquiries/ ).
 * Created by Vladimir V. Klochkov on 22.09.2020.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class ViewInquiriesPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы [Разъяснение заявки]
    private static final String PAGE_HEADER_XPATH = "//*[@id='layoutwrapper']/div/h2";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Внести изменения]
    private static final String MAKE_CHANGES_BUTTON_XPATH = "//a[contains(.,'Внести изменения')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Опубликовать в ЕИС]
    private static final String SEND_TO_OOS_BUTTON_ID = "sendToOOS";
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

    // region Раздел [Сведения о запросе]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о запросе]
    private static final String REQUEST_DETAILS_TITLE_XPATH = "//h2[contains(., 'Сведения о запросе')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер запроса]
    private static final String REQUEST_NUMBER_XPATH =
            "//span[contains(., 'Номер запроса:')]/../following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер заявки]
    private static final String APPLICATION_NUMBER_XPATH =
            "//span[contains(., 'Номер заявки:')]/../following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок предоставления ответа]
    private static final String RESPONSE_TIME_FRAME_XPATH =
            "//span[contains(., 'Срок предоставления ответа:')]/../following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Текст запроса]
    private static final String REQUEST_TEXT_XPATH =
            "//span[contains(., 'Текст запроса:')]/../following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус запроса]
    private static final String REQUEST_STATUS_XPATH =
            "//span[contains(., 'Статус запроса:')]/../following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Участник закупки]
    private static final String PROCUREMENT_PARTICIPANT_XPATH =
            "//span[contains(., 'Участник закупки:')]/../following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Документы запроса]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Документы запроса]
    private static final String REQUEST_DOCUMENTS_TITLE_XPATH = "//label[contains(.,'Документы запроса')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Название документа] в таблице [Документы запроса] - все ссылки на скачивание документов
    private static final String REQUEST_DOCUMENTS_DOWNLOAD_LINKS_XPATH =
            "//*[@id='RequestDocumentsGrid']//a[contains(@href,'/files/FileDownloadHandler.ashx?')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Не отправлять в ЕИС] в таблице [Документы запроса] - все флажки
    private static final String REQUEST_DOCUMENTS_DO_NOT_SEND_TO_OOS_AND_PRIVATE_XPATH =
            "//*[@id='RequestDocumentsGrid']//input[@class='js-doNotSendToOosAndPrivate']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Подпись] в таблице [Документы запроса] - все кнопки [Показать]
    private static final String REQUEST_DOCUMENTS_SHOW_SIGNATURE_BUTTONS_XPATH =
            "//*[@id='RequestDocumentsGrid']//button[contains(.,'Показать')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Разъяснение заявки]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Не публиковать разъяснения на площадке]
    private static final String DO_NOT_PUBLISH_CLARIFICATIONS_ON_THE_SITE_XPATH =
            "//label[contains(., 'Не публиковать разъяснения на площадке:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Текст разъяснения]
    private static final String CLARIFICATION_TEXT_XPATH =
            "//label[contains(., 'Текст разъяснения:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Документы разъяснения]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Документы разъяснения]
    private static final String RESPONCE_DOCUMENTS_TITLE_XPATH = "//label[contains(.,'Документы разъяснения')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Название документа] в таблице [Документы разъяснения] - все ссылки на скачивание документов
    private static final String RESPONCE_DOCUMENTS_DOWNLOAD_LINKS_XPATH =
            "//*[@id='ResponseDocumentsGrid']//a[contains(@href,'/files/FileDownloadHandler.ashx?')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дата] в таблице [Документы разъяснения] - все строки
    private static final String RESPONCE_DOCUMENTS_DATES_XPATH = "//*[@id='ResponseDocumentsGrid']//tr/td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Подпись] в таблице [Документы разъяснения] - все кнопки [Показать]
    private static final String RESPONCE_DOCUMENTS_SHOW_SIGNATURE_BUTTONS_XPATH =
            "//*[@id='ResponseDocumentsGrid']//button[contains(.,'Показать')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary blockNames = new Dictionary();        // основные разделы страницы
    private final Dictionary fieldNames = new Dictionary();        // все поля на странице
    private final Dictionary buttonNames = new Dictionary();       // все кнопки на странице
    private final Dictionary requestDocuments = new Dictionary();  // колонки таблицы [Документы запроса]
    private final Dictionary responceDocuments = new Dictionary(); // колонки таблицы [Документы разъяснения]

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public ViewInquiriesPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Заголовок страницы", PAGE_HEADER_XPATH);
        blockNames.add("Общие сведения о закупке", COMMON_PURCHASE_INFORMATION_TITLE_XPATH);
        blockNames.add("Сведения о запросе", REQUEST_DETAILS_TITLE_XPATH);
        blockNames.add("Документы запроса", REQUEST_DOCUMENTS_TITLE_XPATH);
        blockNames.add("Документы разъяснения", RESPONCE_DOCUMENTS_TITLE_XPATH);
        blockNames.add("Текст разъяснения", CLARIFICATION_TEXT_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Номер закупки", PURCHASE_NUMBER_XPATH);
        fieldNames.add("Номер редакции извещения", PURCHASE_VERSION_XPATH);
        fieldNames.add("Наименование закупки", PURCHASE_NAME_XPATH);
        fieldNames.add("Номер лота", LOT_NUMBER_XPATH);
        fieldNames.add("Наименование лота", LOT_NAME_XPATH);
        fieldNames.add("Номер запроса", REQUEST_NUMBER_XPATH);
        fieldNames.add("Номер заявки", APPLICATION_NUMBER_XPATH);
        fieldNames.add("Срок предоставления ответа", RESPONSE_TIME_FRAME_XPATH);
        fieldNames.add("Текст запроса", REQUEST_TEXT_XPATH);
        fieldNames.add("Статус запроса", REQUEST_STATUS_XPATH);
        fieldNames.add("Участник закупки", PROCUREMENT_PARTICIPANT_XPATH);
        fieldNames.add("Не публиковать разъяснения на площадке", DO_NOT_PUBLISH_CLARIFICATIONS_ON_THE_SITE_XPATH);
        fieldNames.add("Текст разъяснения", CLARIFICATION_TEXT_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        requestDocuments.add("Название документа", REQUEST_DOCUMENTS_DOWNLOAD_LINKS_XPATH);
        requestDocuments.add("Не отправлять в ЕИС", REQUEST_DOCUMENTS_DO_NOT_SEND_TO_OOS_AND_PRIVATE_XPATH);
        requestDocuments.add("Подпись", REQUEST_DOCUMENTS_SHOW_SIGNATURE_BUTTONS_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        responceDocuments.add("Название документа", RESPONCE_DOCUMENTS_DOWNLOAD_LINKS_XPATH);
        responceDocuments.add("Дата", RESPONCE_DOCUMENTS_DATES_XPATH);
        responceDocuments.add("Подпись", RESPONCE_DOCUMENTS_SHOW_SIGNATURE_BUTTONS_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Внести изменения", MAKE_CHANGES_BUTTON_XPATH);
        buttonNames.add("Опубликовать в ЕИС", SEND_TO_OOS_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Раздел [Документы запроса]

// --Commented out by Inspection START (01.10.2020 20:34):
//    /**
//     * Проверяет текст ссылки для скачивания первого сверху прикрепленного файла в разделе [Документы запроса].
//     *
//     * @param fileName имя файла
//     */
//    public void checkFirstLinkToDownloadFileFromRequestDocuments(String fileName) {
//        ElementsCollection requestDocumentsDownloadLinks =
//                $$(this.getBy(REQUEST_DOCUMENTS_DOWNLOAD_LINKS_XPATH)).filterBy(visible);
//        System.out.println(String.format("[ИНФОРМАЦИЯ]: проверяем ссылку для скачивания прикрепленного файла [%s] " +
//                "в разделе [Документы запроса].", fileName));
//        requestDocumentsDownloadLinks.get(0).waitUntil(visible, timeout, polling).shouldHave(text(fileName));
//    }
// --Commented out by Inspection STOP (01.10.2020 20:34)

// --Commented out by Inspection START (01.10.2020 20:35):
//    /**
//     * Проверяет отображение кнопки [Показать] для первого сверху прикрепленного файла в разделе [Документы запроса].
//     */
//    public void checkFirstShowSignatureButtonFromRequestDocuments() {
//        ElementsCollection requestDocumentsRemoveDocumentLinks =
//                $$(this.getBy(REQUEST_DOCUMENTS_SHOW_SIGNATURE_BUTTONS_XPATH)).filterBy(visible);
//        System.out.println("[ИНФОРМАЦИЯ]: проверяем отображение кнопки [Показать] для первого сверху прикрепленного " +
//                "файла в разделе [Документы запроса].");
//        requestDocumentsRemoveDocumentLinks.get(0).waitUntil(visible, timeout, polling).shouldHave(text("Показать"));
//    }
// --Commented out by Inspection STOP (01.10.2020 20:35)

// --Commented out by Inspection START (01.10.2020 20:35):
//    /**
//     * Проверяет возможность скачать прикрепленные документы в разделе [Документы запроса].
//     */
//    public void checkPossibilityToDownloadRequestDocuments() throws Exception {
//        ElementsCollection requestDocumentsDownloadLinks =
//                $$(this.getBy(REQUEST_DOCUMENTS_DOWNLOAD_LINKS_XPATH)).filterBy(visible);
//        for (SelenideElement requestDocumentsDownloadLink : requestDocumentsDownloadLinks) {
//            this.checkPossibilityToDownloadFileWithGeneratedName(requestDocumentsDownloadLink,
//                    requestDocumentsDownloadLink.getText(), 200);
//        }
//    }
// --Commented out by Inspection STOP (01.10.2020 20:35)

    // endregion

    // region Раздел [Документы разъяснения]

    /**
     * Проверяет текст ссылки для скачивания первого сверху прикрепленного файла в разделе [Документы разъяснения].
     *
     * @param fileName имя файла
     */
    public void checkFirstLinkToDownloadFileFromResponceDocuments(String fileName) {
        ElementsCollection responceDocumentsDownloadLinks =
                $$(this.getBy(RESPONCE_DOCUMENTS_DOWNLOAD_LINKS_XPATH)).filterBy(visible);
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем ссылку для скачивания прикрепленного файла [%s] " +
                "в разделе [Документы разъяснения].%n", fileName);
        responceDocumentsDownloadLinks.get(0).waitUntil(visible, timeout, polling).shouldHave(text(fileName));
    }

    /**
     * Проверяет отображение кнопки [Показать] для первого сверху прикрепленного файла в разделе [Документы разъяснения].
     */
    public void checkFirstShowSignatureButtonFromResponceDocuments() {
        ElementsCollection responceDocumentsRemoveDocumentLinks =
                $$(this.getBy(RESPONCE_DOCUMENTS_SHOW_SIGNATURE_BUTTONS_XPATH)).filterBy(visible);
        System.out.println("[ИНФОРМАЦИЯ]: проверяем отображение кнопки [Показать] для первого сверху прикрепленного " +
                "файла в разделе [Документы разъяснения].");
        responceDocumentsRemoveDocumentLinks.get(0).waitUntil(visible, timeout, polling).shouldHave(text("Показать"));
    }

    /**
     * Проверяет возможность скачать прикрепленные документы в разделе [Документы разъяснения].
     */
    public void checkPossibilityToDownloadResponceDocuments() throws Exception {
        ElementsCollection responceDocumentsDownloadLinks =
                $$(this.getBy(RESPONCE_DOCUMENTS_DOWNLOAD_LINKS_XPATH)).filterBy(visible);
        for (SelenideElement responceDocumentsDownloadLink : responceDocumentsDownloadLinks) {
            this.checkPossibilityToDownloadFileWithGeneratedName(responceDocumentsDownloadLink,
                    responceDocumentsDownloadLink.getText(), 200);
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
     * Проверяет значение поля.
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
