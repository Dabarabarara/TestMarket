package ru.PageObjects.Supplier.CommonClarificationRequest;

import Helpers.Dictionary;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Supplier.CommonSupplierPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс описывающий страницу [Разъяснение документации/результатов/заявки] в режиме просмотра
 * ( .kontur.ru/supplier/auction/CommonClarificationRequest/View.aspx?Id= ).
 * Created by Vladimir V. Klochkov on 03.10.2019.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class CommonClarificationRequestViewPage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы [Разъяснение документации/результатов/заявки]
    private static final String PAGE_HEADER_XPATH = "//*[@id='workContainer']/div/h2";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер запроса]
    private static final String REQUEST_NUMBER_ID = "BaseMainContent_MainContent_fvRequestNumber_lblValue";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус запроса]
    private static final String REQUEST_STATUS_ID = "BaseMainContent_MainContent_fvRequestStatus_lblValue";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Общие сведения о закупке]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Общие сведения о закупке]
    private static final String COMMON_TITLE_ID = "BaseMainContent_MainContent_hCommonTitle";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки]
    private static final String NOTICE_NUMBER_ID = "BaseMainContent_MainContent_hlNoticeNumber_hlValue";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер редакции извещения]
    private static final String NOTICE_VERSION_XPATH =
            "//*[contains(@id, 'BaseMainContent_MainContent_fvNoticeVersion')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование закупки]
    private static final String TRADE_NAME_ID = "BaseMainContent_MainContent_fvTradeName_lblValue";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о предмете запроса]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о предмете запроса]
    private static final String SUBJECT_TITLE_XPATH = "//h2[contains(., 'Сведения о предмете запроса')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Отправитель]
    private static final String ORG_NAME_ID = "BaseMainContent_MainContent_fvOrgName_lblValue";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Текст запроса]
    private static final String REQUEST_TEXT_ID = "BaseMainContent_MainContent_fvRequestText_lblValue";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Документы]
    private static final String REQUEST_SUBJECT_ATTACHED_DOCUMENTS_HEADER_XPATH =
            "//fieldset[@id='BaseMainContent_MainContent_ufRequestFile_fsUpload']//label[contains(.,'Документы')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка для скачивания первого из прикрепленных к запросу на Разъяснение документации/результатов/заявки файлов
    private static final String DOWNLOAD_REQUEST_FILE_XPATH =
            "//*[@id='BaseMainContent_MainContent_ufRequestFile_ulDocuments']/li/a[1]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Разъяснение документации ...]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Разъяснение документации ...]
    private static final String DOCUMENTATION_CLARIFICATION_XPATH =
            "//div[@class='block_title']/h2[contains(., 'Разъяснение документации')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Текст разъяснения]
    private static final String RESPONCE_TEXT_ID = "BaseMainContent_MainContent_fvResponseText_lblValue";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка для скачивания первого из прикрепленных к разъяснению документации файлов
    private static final String DOWNLOAD_RESPONCE_FILE_XPATH =
            "//*[@id='BaseMainContent_MainContent_ufResponseFile_ulDocuments']/li/a[1]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Разъяснение результатов ...]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Разъяснение результатов ...]
    private static final String RESULTS_CLARIFICATION_XPATH =
            "//div[@class='block_title']/h2[contains(., 'Разъяснение результатов')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Текст разъяснения результатов]
    private static final String RESULTS_RESPONCE_TEXT_ID = "BaseMainContent_MainContent_fvResponseText_lblValue";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Управляющие кнопки в нижней части страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Ответить на запрос]
    private static final String RESPONSE_BUTTON_ID = "BaseMainContent_MainContent_btnResponse";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary blockNames = new Dictionary();  // основные разделы страницы
    private final Dictionary fieldNames = new Dictionary();  // все поля на странице
    private final Dictionary buttonNames = new Dictionary(); // все кнопки на странице

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CommonClarificationRequestViewPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Заголовок страницы", PAGE_HEADER_XPATH);
        blockNames.add("Общие сведения о закупке", COMMON_TITLE_ID);
        blockNames.add("Сведения о предмете запроса", SUBJECT_TITLE_XPATH);
        blockNames.add("Разъяснение документации", DOCUMENTATION_CLARIFICATION_XPATH);
        blockNames.add("Разъяснение результатов", RESULTS_CLARIFICATION_XPATH);
        blockNames.add("Документы запроса", REQUEST_SUBJECT_ATTACHED_DOCUMENTS_HEADER_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Номер запроса", REQUEST_NUMBER_ID);
        fieldNames.add("Статус запроса", REQUEST_STATUS_ID);
        fieldNames.add("Номер закупки", NOTICE_NUMBER_ID);
        fieldNames.add("Номер редакции извещения", NOTICE_VERSION_XPATH);
        fieldNames.add("Наименование закупки", TRADE_NAME_ID);
        fieldNames.add("Отправитель", ORG_NAME_ID);
        fieldNames.add("Текст запроса", REQUEST_TEXT_ID);
        fieldNames.add("Текст разъяснения", RESPONCE_TEXT_ID);
        fieldNames.add("Текст разъяснения результатов", RESULTS_RESPONCE_TEXT_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Ответить на запрос", RESPONSE_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы

    /**
     * Сохраняет в параметрах теста номер запроса на Разъяснение документации/результатов/заявки.
     *
     * @return страница [Разъяснение документации/результатов/заявки]
     */
    public CommonClarificationRequestViewPage saveCommonClarificationRequestNumber() {
        config.setParameter("CommonClarificationRequestNumber", $(this.getBy(REQUEST_NUMBER_ID)).getText());

        return this;
    }

    /**
     * Проверяет статус запроса на Разъяснение документации/результатов/заявки.
     *
     * @param requestStatus ожидаемый статус запроса на Разъяснение документации/результатов/заявки
     * @return страница [Разъяснение документации/результатов/заявки]
     */
    public CommonClarificationRequestViewPage checkRequestStatus(String requestStatus) throws Exception {
        this.waitForTextInField(requestStatus, this.getBy(REQUEST_STATUS_ID), "Статус запроса");

        return this;
    }

    // endregion

    // region Раздел [Сведения о предмете запроса]

    /**
     * Проверяет ссылку для скачивания прикрепленного файла в разделе [Сведения о предмете запроса].
     *
     * @param fileName имя файла
     * @return страница [Разъяснение документации/результатов/заявки]
     */
    public CommonClarificationRequestViewPage checkLinkToDownloadFileIntoRequestDocuments(String fileName) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем ссылку для скачивания прикрепленного файла [%s] " +
                "в разделе [Сведения о предмете запроса].%n", fileName);
        $(this.getBy(DOWNLOAD_REQUEST_FILE_XPATH)).waitUntil(visible, timeout, polling).shouldHave(text(fileName));

        return this;
    }

    // endregion

    // region [Разъяснение документации аукциона]

    /**
     * Проверяет ссылку для скачивания прикрепленного файла в разделе [Разъяснение документации/результатов аукциона].
     *
     * @param fileName имя файла
     */
    public void checkLinkToDownloadFileIntoResponceDocuments(String fileName) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем ссылку для скачивания прикрепленного файла [%s] " +
                "в разделе [Разъяснение документации/результатов аукциона].%n", fileName);
        $(this.getBy(DOWNLOAD_RESPONCE_FILE_XPATH)).waitUntil(visible, timeout, polling).shouldHave(text(fileName));
    }

    // endregion

    // region Общие методы для работы с элементами этой страницы

    /**
     * Переходит к блоку полей на форме.
     *
     * @param blockName имя блока
     * @return страница [Разъяснение документации/результатов/заявки]
     */
    public CommonClarificationRequestViewPage gotoBlock(String blockName) throws Exception {
        SelenideElement block = $(this.getBy(blockNames.find(blockName)));
        this.scrollToCenter(block);
        block.shouldBe(visible);
        System.out.printf("[ИНФОРМАЦИЯ]: Произведен переход к блоку полей: [%s].%n", blockName);
        TimeUnit.SECONDS.sleep(3);

        return this;
    }

    /**
     * Проверяет значение поля.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение, которое должно содержаться в поле
     * @return страница [Разъяснение документации/результатов/заявки]
     */
    public CommonClarificationRequestViewPage verifyField(String fieldName, String value) {
        String message = String.format("[ОШИБКА]: значение [%s] не содержится в поле [%s]", value, fieldName);

        By field = this.getBy(fieldNames.find(fieldName));

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        Assert.assertTrue(message, this.verifyFieldContent(field, value));

        return this;
    }

    /**
     * Проверяет, что поле содержит не пустое значение.
     *
     * @param fieldName имя поля
     */
    public CommonClarificationRequestViewPage verifyFieldIsNotEmpty(String fieldName) {
        String message = String.format("[ОШИБКА]: поле: [%s] содержит пустое значение", fieldName);

        By field = this.getBy(fieldNames.find(fieldName));

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] на не пустое значение.%n", fieldName);
        Assert.assertTrue(message, this.verifyFieldIsNotEmpty(field));

        return this;
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

        this.waitForPageLoad();
    }

    // endregion
}
