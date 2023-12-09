package ru.PageObjects.Supplier.CommonClarificationRequest;

import Helpers.Dictionary;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Supplier.CommonSupplierPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывающий страницу [Запрос на разъяснение документации/результатов/заявки] в режиме редактирования
 * ( .kontur.ru/supplier/auction/CommonClarificationRequest/Create.aspx? ).
 * Created by Vladimir V. Klochkov on 30.09.2019.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class CommonClarificationRequestCreatePage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы [Запрос на разъяснение документации/результатов/заявки]
    private static final String PAGE_HEADER_XPATH = "//*[@id='workContainer']/div/h2";
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
    private static final String NOTICE_VERSION_ID = "BaseMainContent_MainContent_fvNoticeVersion_lblValue";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование закупки]
    private static final String TRADE_NAME_ID = "BaseMainContent_MainContent_fvTradeName_lblValue";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер лота]
    private static final String LOT_ORDER_NUMBER_ID = "BaseMainContent_MainContent_fvLotOrderNumber_lblValue";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование лота]
    private static final String LOT_SUBJECT_ID = "BaseMainContent_MainContent_fvLotSubject_lblValue";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения об участнике закупки]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения об участнике закупки]
    private static final String PARTICIPANT_TITLE_ID = "BaseMainContent_MainContent_hParticipantTitle";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование участника закупки]
    private static final String PARTICIPANT_NAME_ID = "BaseMainContent_MainContent_fvParticipantName_lblValue";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер заявки]
    private static final String PARTICIPANT_CODE_ID = "BaseMainContent_MainContent_fvParticipantCode_lblValue";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о предмете запроса]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о предмете запроса]
    private static final String SUBJECT_TITLE_XPATH = "//h2[contains(., 'Сведения о предмете запроса')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Текст запроса]
    private static final String REQUEST_TEXT_ID = "BaseMainContent_MainContent_txtText_txtText";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Текст запроса осталось]
    private static final String REQUEST_TEXT_REMAINING_LENGTH_ID =
            "BaseMainContent_MainContent_txtText_lblRemainingLength";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить]
    private static final String BUTTON_UPLOAD_XPATH = "//*[@id='BaseMainContent_MainContent_ufFile_btnUpload']/input";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылки для скачивания прикрепленных к запросу на разъяснение документации/результатов файлов
    private static final String DOWNLOAD_FILES_XPATH =
            "//*[@id='BaseMainContent_MainContent_ufFile_ulDocuments']/li/a[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Пиктограммы для удаления прикрепленных к запросу на разъяснение документации/результатов файлов
    private static final String REMOVE_DOWNLOADED_FILES_XPATH =
            "//*[@id='BaseMainContent_MainContent_ufFile_ulDocuments']//a[@class='delete']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Разъяснение заявки]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Разъяснение заявки]
    private static final String RESPONSE_TITLE_XPATH = "//h2[contains(.,'Разъяснение заявки')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Текст ответа]
    private static final String RESPONSE_TEXT_ID = "BaseMainContent_MainContent_txtResponseText_txtText";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Текст ответа осталось]
    private static final String RESPONSE_TEXT_REMAINING_LENGTH_ID =
            "BaseMainContent_MainContent_txtResponseText_lblRemainingLength";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить к разъяснению заявки]
    private static final String BUTTON_ADD_XPATH = "//input[@type='file']";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылки для скачивания прикрепленных к запросу на разъяснение заявки файлов
    private static final String DOWNLOAD_RESPONSE_FILES_XPATH =
            "//*[@id='BaseMainContent_MainContent_ufResponseFile_tUploadFile']//a[@class='upload-file']";
    //------------------------------------------------------------------------------------------------------------------
    // Пиктограммы для удаления прикрепленных к запросу на разъяснение заявки файлов
    private static final String REMOVE_DOWNLOADED_RESPONSE_FILES_XPATH =
            "//*[@id='BaseMainContent_MainContent_ufResponseFile_tUploadFile']//a[@class='delete']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Блок управляющих кнопок в нижней части страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Подписать и отправить]
    private static final String SAVE_BUTTON_ID = "BaseMainContent_MainContent_btnSave";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Сохранить черновик]
    private static final String SAVE_DRAFT_BUTTON_ID = "BaseMainContent_MainContent_btnSaveDraft";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [На карточку закупки]
    private static final String BACK_TO_TRADE_VIEW_BUTTON_ID = "BaseMainContent_MainContent_btnBackToTradeView";
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
    public CommonClarificationRequestCreatePage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Заголовок страницы", PAGE_HEADER_XPATH);
        blockNames.add("Общие сведения о закупке", COMMON_TITLE_ID);
        blockNames.add("Сведения об участнике закупки", PARTICIPANT_TITLE_ID);
        blockNames.add("Сведения о предмете запроса", SUBJECT_TITLE_XPATH);
        blockNames.add("Разъяснение заявки", RESPONSE_TITLE_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Номер закупки", NOTICE_NUMBER_ID);
        fieldNames.add("Номер редакции извещения", NOTICE_VERSION_ID);
        fieldNames.add("Наименование закупки", TRADE_NAME_ID);
        fieldNames.add("Номер лота", LOT_ORDER_NUMBER_ID);
        fieldNames.add("Наименование лота", LOT_SUBJECT_ID);
        fieldNames.add("Наименование участника закупки", PARTICIPANT_NAME_ID);
        fieldNames.add("Номер заявки", PARTICIPANT_CODE_ID);
        fieldNames.add("Текст запроса", REQUEST_TEXT_ID);
        fieldNames.add("Текст запроса осталось", REQUEST_TEXT_REMAINING_LENGTH_ID);
        fieldNames.add("Текст ответа", RESPONSE_TEXT_ID);
        fieldNames.add("Текст ответа осталось", RESPONSE_TEXT_REMAINING_LENGTH_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Добавить", BUTTON_UPLOAD_XPATH);
        buttonNames.add("Добавить к разъяснению заявки", BUTTON_ADD_XPATH);
        buttonNames.add("Подписать и отправить", SAVE_BUTTON_ID);
        buttonNames.add("Сохранить черновик", SAVE_DRAFT_BUTTON_ID);
        buttonNames.add("На карточку закупки", BACK_TO_TRADE_VIEW_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Раздел [Сведения о предмете запроса]

    /**
     * Прикрепляет файл в разделе [Сведения о предмете запроса].
     *
     * @param fileName имя файла
     */
    public void uploadFileIntoDocuments(String fileName) {
        System.out.printf("[ИНФОРМАЦИЯ]: прикрепляем файл [%s] в разделе [Сведения о предмете запроса].%n", fileName);
        $(this.getBy(BUTTON_UPLOAD_XPATH)).waitUntil(exist, timeout, polling).sendKeys(fileName);
    }

    /**
     * Проверяет ссылку для скачивания первого сверху из прикрепленных файлов в разделе [Сведения о предмете запроса].
     *
     * @param fileName имя файла
     */
    public void checkLinkToDownloadFileFromDocuments(String fileName) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем ссылку для скачивания прикрепленного файла [%s] " +
                "в разделе [Сведения о предмете запроса].%n", fileName);
        $$(this.getBy(DOWNLOAD_FILES_XPATH)).get(0).waitUntil(visible, timeout, polling).shouldHave(text(fileName));
        $$(this.getBy(REMOVE_DOWNLOADED_FILES_XPATH)).shouldHave(sizeGreaterThan(0));
        $$(this.getBy(REMOVE_DOWNLOADED_FILES_XPATH)).get(0).shouldBe(visible);
    }

    // endregion

    // region Раздел [Разъяснение заявки]

    /**
     * Прикрепляет файл в разделе [Разъяснение заявки].
     *
     * @param fileName имя файла
     */
    public void uploadFileIntoResponceDocuments(String fileName) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: прикрепляем файл [%s] в разделе [Разъяснение заявки].%n", fileName);
        $(this.getBy(BUTTON_ADD_XPATH)).waitUntil(exist, timeout, polling).sendKeys(fileName);

        // Таймаут нужен для завершения всех операций ( ссылка появляется раньше, чем завершается загрузка файла )
        TimeUnit.SECONDS.sleep(15);
    }

    /**
     * Проверяет ссылку для скачивания первого сверху из прикрепленных файлов в разделе [Разъяснение заявки].
     *
     * @param fileName имя файла
     */
    public void checkLinkToDownloadFileFromResponceDocuments(String fileName) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем ссылку для скачивания прикрепленного файла [%s] " +
                "в разделе [Разъяснение заявки].%n", fileName);
        $$(this.getBy(DOWNLOAD_RESPONSE_FILES_XPATH)).get(0).waitUntil(visible, timeout, polling).
                shouldHave(text(fileName));
        $$(this.getBy(REMOVE_DOWNLOADED_RESPONSE_FILES_XPATH)).shouldHave(sizeGreaterThan(0));
        $$(this.getBy(REMOVE_DOWNLOADED_RESPONSE_FILES_XPATH)).get(0).shouldBe(visible);
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
     * Устанавливает значение полей с предварительной их очисткой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     */
    public void setField(String fieldName, String value) {
        $(this.getBy(fieldNames.find(fieldName))).waitUntil(clickable, timeout, polling).sendKeys(value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
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
     * Возвращает значение поля.
     *
     * @param fieldName имя поля
     * @return значение поля
     */
    public String getField(String fieldName) {
        By by = this.getBy(fieldNames.find(fieldName));

        return this.getFieldValueOrText(fieldName, by);
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
