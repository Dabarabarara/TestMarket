package ru.PageObjects.Customer.CommonClarificationResponce;


import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Text;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывающий страницу [Создание разъяснения положений документации о закупке]
 * (customer/lk/RegisterOfInquiries/CreateClarifyDocResponse/).
 * Created by Kirill G. Rydzyvylo on 04.12.2019.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class CreateClarifyDocResponsePage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Заголовок страницы

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы [Создание разъяснения положений документации о закупке]
    private static final String PAGE_HEADER_XPATH = "//div[@class='main']/h2";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отправить разъяснение]
    private static final String PUBLISH_EXPLANATION_RESPONSE_BUTTON_ID = "publishExplanationResponse";
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

    // region Раздел [Разъяснение положений документации закупки]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Разъяснение положений документации закупки]
    private static final String CLARIFICATION_DOCUMENTATION_PROCUREMENT_XPATH =
            "//h2[contains(., 'Разъяснение положений документации закупки')]";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Не публиковать разъяснения на площадке]
    private static final String IS_PRIVATE_ID = "IsPrivate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Текст разъяснения]
    private static final String RESPONCE_TEXT_ID = "ResponseText";
    //------------------------------------------------------------------------------------------------------------------
    // Счетчик оставшихся символов в поле [Текст разъяснения]
    private static final String RESPONCE_TEXT_REMAINING_LENGTH_XPATH = "//*[@id='ResponseText']/../div/span";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Предмет запроса]
    private static final String REQUEST_SUBJECT_INFO_ID = "RequestSubjectInfo";
    //------------------------------------------------------------------------------------------------------------------
    // Счетчик оставшихся символов в поле [Предмет запроса]
    private static final String REQUEST_SUBJECT_INFO_REMAINING_LENGTH_XPATH =
            "//*[@id='RequestSubjectInfo']/../div/span";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата поступления запроса]
    private static final String REQUEST_DATE_ID = "RequestDate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата принятия решения о предоставлении разъяснений]
    private static final String RESPONSE_DECISION_DATE_ID = "ResponseDecisionDate";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Документы разъяснения]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Документы разъяснения]
    private static final String RESPONCE_DOCUMENTS_TITLE_XPATH = "//*[@id='ResponseDocumentsGrid']/div/label";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Название документа] в таблице [Документы разъяснения] - все ссылки с именами файлов для скачивания
    private static final String RESPONCE_DOCUMENTS_DOWNLOAD_LINKS_XPATH =
            "//*[@id='ResponseDocumentsGrid']/table/tbody/tr/td/a[contains(@href, 'files/FileDownloadHandler')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дата] в таблице [Документы разъяснения] - все ячейки таблицы
    private static final String DOCUMENT_DATES_XPATH = "//*[@id='ResponseDocumentsGrid']/table/tbody/tr/td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Действие] в таблице [Документы разъяснения] -  все ссылки [Удалить]
    private static final String REMOVE_DOCUMENT_LINKS_XPATH = "//table//a[contains(@onclick, 'removeItem')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Прикрепить файл]
    private static final String MESSAGE_DOCUMENT_UPLOAD_BUTTON_ID = "MessageDocumentUpload";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary blockNames = new Dictionary();    // основные разделы страницы
    private final Dictionary checkBoxNames = new Dictionary(); // все флажки на странице
    private final Dictionary fieldNames = new Dictionary();    // все поля на странице
    private final Dictionary buttonNames = new Dictionary();   // все кнопки на странице

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CreateClarifyDocResponsePage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Заголовок страницы", PAGE_HEADER_XPATH);
        blockNames.add("Общие сведения о закупке", COMMON_PURCHASE_INFORMATION_TITLE_XPATH);
        blockNames.add("Разъяснение положений документации закупки", CLARIFICATION_DOCUMENTATION_PROCUREMENT_XPATH);
        blockNames.add("Документы разъяснения", RESPONCE_DOCUMENTS_TITLE_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        checkBoxNames.add("Не публиковать разъяснения на площадке", IS_PRIVATE_ID);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Номер закупки", PURCHASE_NUMBER_XPATH);
        fieldNames.add("Номер редакции извещения", PURCHASE_VERSION_XPATH);
        fieldNames.add("Наименование закупки", PURCHASE_NAME_XPATH);
        fieldNames.add("Текст разъяснения", RESPONCE_TEXT_ID);
        fieldNames.add("Текст разъяснения - счетчик оставшихся символов", RESPONCE_TEXT_REMAINING_LENGTH_XPATH);
        fieldNames.add("Предмет запроса", REQUEST_SUBJECT_INFO_ID);
        fieldNames.add("Предмет запроса - счетчик оставшихся символов", REQUEST_SUBJECT_INFO_REMAINING_LENGTH_XPATH);
        fieldNames.add("Дата поступления запроса", REQUEST_DATE_ID);
        fieldNames.add("Дата принятия решения о предоставлении разъяснений", RESPONSE_DECISION_DATE_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Отправить разъяснение", PUBLISH_EXPLANATION_RESPONSE_BUTTON_ID);
        buttonNames.add("Прикрепить файл", MESSAGE_DOCUMENT_UPLOAD_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Раздел [Документы разъяснения]

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
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем ссылку для скачивания прикрепленного файла [%s] " +
                "в разделе [Документы разъяснения].%n", fileName);
        responceDocumentsDownloadLinks.get(0).waitUntil(visible, timeout, polling).
                shouldBe(clickable).shouldHave(text(fileName));
    }

    /**
     * Проверяет отсутствие ссылки для скачивания первого сверху прикрепленного файла в разделе [Документы разъяснения].
     *
     * @param fileName имя файла
     */
    public void checkLinkToDownloadFileFromResponceDocumentsIsNotExists(String fileName) {
        ElementsCollection responceDocumentsDownloadLinks =
                $$(this.getBy(RESPONCE_DOCUMENTS_DOWNLOAD_LINKS_XPATH)).filterBy(visible);
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем отсутствие ссылки для скачивания прикрепленного " +
                "файла [%s] в разделе [Документы разъяснения].%n", fileName);
        responceDocumentsDownloadLinks.shouldHaveSize(0);
    }

    /**
     * Проверяет дату и время первого сверху прикрепленного файла в разделе [Документы разъяснения].
     *
     * @param fileName имя файла
     */
    public void checkFirstDateTimeOfDownloadedFileFromResponceDocuments(String fileName) {
        ElementsCollection documentDateTimeCells = $$(this.getBy(DOCUMENT_DATES_XPATH)).filterBy(visible);
        SelenideElement documentDateTimeCell = documentDateTimeCells.get(0).waitUntil(visible, timeout, polling);
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем дату и время [%s] прикрепленного файла [%s] " +
                "в разделе [Документы разъяснения].%n", documentDateTimeCell.getText(), fileName);
        Assert.assertTrue("[ОШИБКА]: дата и время прикрепленного файла содержат некорректные значения",
                documentDateTimeCell.getText().contains(" (МСК)"));
    }

    /**
     * Проверяет ссылку для удаления первого сверху прикрепленного файла в разделе [Документы разъяснения].
     *
     * @param fileName имя файла
     */
    public void checkFirstLinkToRemoveUploadedFileFromResponceDocuments(String fileName) {
        ElementsCollection removeDocumentLinks = $$(this.getBy(REMOVE_DOCUMENT_LINKS_XPATH)).filterBy(visible);
        SelenideElement removeDocumentLink = removeDocumentLinks.get(0);
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем ссылку для удаления прикрепленного файла [%s] " +
                "в разделе [Документы разъяснения].%n", fileName);
        removeDocumentLink.waitUntil(exist, timeout, polling).shouldBe(clickable).shouldHave(text("Удалить"));
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
     * Устанавливает значение полей с предварительной их очисткой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     */
    public void setFieldClearClickAndSendKeys(String fieldName, String value) throws Exception {
        this.waitClearClickAndSendKeysById(fieldNames.find(fieldName), value);
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
        TimeUnit.SECONDS.sleep(3);
    }

    // endregion
}

