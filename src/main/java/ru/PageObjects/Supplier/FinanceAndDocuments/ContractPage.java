package ru.PageObjects.Supplier.FinanceAndDocuments;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Supplier.CommonSupplierPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывающий страницу [Договор № XXXX.XXXX] у Поставщика (.kontur.ru/supplier/dealing/Contract.aspx?).
 * Режим редактирования черновика договора и режим просмотра подписанного Поставщиком договора.
 * Created by Evgeniy Glushko on 04.04.2016.
 * Updated by Alexander S. Vasyurenko on 26.05.2021.
 */
public class ContractPage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Управляющие кнопки в верхней части страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Принять и подписать договор]
    private static final String ACCEPT_AND_SIGN_CONTRACT_BUTTON_ID = "BaseMainContent_MainContent_btnSignAndSend";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Создать протокол разногласий]
    private static final String CREATE_DIFFERENCE_PROTOCOL_ID = "BaseMainContent_MainContent_btnDifferenceProtocol";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Да] в окне [Подтверждения действия]
    private static final String YES_BUTTON_XPATH =
            "//div[@id='dialogConfirm']/following-sibling::div[1]//button[contains(., 'Да')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о договоре]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус]
    private static final String CONTRACT_STATUS_ID = "BaseMainContent_MainContent_fvState_lblValue";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Позиции договора]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Позиции договора]
    private static final String CONTRACT_POSITIONS_HEADER_XPATH = "//h2[contains(., 'Позиции договора')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Наименование товара, работ, услуг]
    private static final String CONTRACT_POSITIONS_TABLE_NAME_XPATH =
            "//*[@aria-describedby='BaseMainContent_MainContent_rptDealPositions_Name']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Код ОКПД2]
    private static final String CONTRACT_POSITIONS_TABLE_OKPD2_XPATH =
            "//*[@aria-describedby='BaseMainContent_MainContent_rptDealPositions_Okpd2Code']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Код ОКВЭД2]
    private static final String CONTRACT_POSITIONS_TABLE_OKVED2_XPATH =
            "//*[@aria-describedby='BaseMainContent_MainContent_rptDealPositions_Okved2Code']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Тип объекта закупки]
    private static final String CONTRACT_POSITIONS_TABLE_OBJECT_TYPE_XPATH =
            "//*[@aria-describedby='BaseMainContent_MainContent_rptDealPositions_DealPositionType']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Список допустимых стран происхождения]
    private static final String LIST_OF_ACCEPTABLE_COUNTRIES_OF_ORIGIN_XPATH =
            "//*[@aria-describedby='BaseMainContent_MainContent_rptDealPositions_DealPositionCountries']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Единица измерения]
    private static final String CONTRACT_POSITIONS_TABLE_UNIT_XPATH =
            "//*[@aria-describedby='BaseMainContent_MainContent_rptDealPositions_Okei']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Количество]
    private static final String CONTRACT_POSITIONS_TABLE_QUANTITY_XPATH =
            "//*[@aria-describedby='BaseMainContent_MainContent_rptDealPositions_Quantity']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Цена за ед.изм.]
    private static final String CONTRACT_POSITIONS_TABLE_PRICE_PER_UNIT_XPATH =
            "//*[@aria-describedby='BaseMainContent_MainContent_rptDealPositions_Price']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Стоимость]
    private static final String CONTRACT_POSITIONS_TABLE_TOTAL_COST_XPATH =
            "//*[@aria-describedby='BaseMainContent_MainContent_rptDealPositions_Sum']";
    //------------------------------------------------------------------------------------------------------------------*/
    // Столбец [Дополнительные сведения]
    private static final String CONTRACT_POSITIONS_TABLE_ADDITIONAL_INFO_XPATH =
            "//*[@aria-describedby='BaseMainContent_MainContent_rptDealPositions_AddInfo']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения об участнике закупки]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения об участнике закупки]
    private static final String PARTICIPANT_INFO_HEADER_XPATH = "//h2[contains(., 'Сведения об участнике закупки')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [ОКТМО]
    private static final String OKTMO_ID = "BaseMainContent_MainContent_ucProviderOktmo_txtCode_txtText";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [ОКПО]
    private static final String OKPO_ID = "BaseMainContent_MainContent_txtProviderOkpo_txtText";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [ОКОПФ]
    private static final String OKOPF_ID = "BaseMainContent_MainContent_ucProviderOkopf_txtCode_txtText";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата постановки на учет в налоговом органе]
    private static final String REGISTRATION_DATE_ID = "BaseMainContent_MainContent_txtProviderRegistrationDate_txtText";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Документы, подтверждающие предоставление обеспечения исполнения договора, и иные документы]

    //------------------------------------------------------------------------------------------------------------------
    // Переключатель
    // [Участник подтверждает, что обеспечение исполнения договора предоставляется путем внесения денежных средств]
    private static final String MAKING_MONEY_RB_ID = "BaseMainContent_MainContent_rbContractGuaranteePaymentType_0";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить]
    private static final String MONEY_GUARANTEE_DOC_UPLOAD_XPATH =
            "//*[@id='BaseMainContent_MainContent_ufProvisionFile_btnUpload']//input[@name='file']";
    //------------------------------------------------------------------------------------------------------------------

    // region Раздел [Проект дополнительного соглашения]

    //------------------------------------------------------------------------------------------------------------------
    // Ссылка на скачивание файла [Проект дополнительного соглашения]
    private static final String ADDENDUM_PROJECT_FILE_COLUMN_XPATH =
            "//*[@id='gbox_BaseMainContent_MainContent_jqgDocumentsInPack']//a[contains(@href,'/files/FileDownloadHandler.ashx?')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary partitionNames = new Dictionary();    // основные разделы договора
    private final Dictionary fields = new Dictionary();            // все поля формы договора
    private final Dictionary contractPositions = new Dictionary(); // все колонки таблицы в разделе [Позиции договора]

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public ContractPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        fields.add("ОКТМО", OKTMO_ID);
        fields.add("ОКПО", OKPO_ID);
        fields.add("ОКОПФ", OKOPF_ID);
        fields.add("Дата постановки на учет в налоговом органе", REGISTRATION_DATE_ID);
        //--------------------------------------------------------------------------------------------------------------
        partitionNames.add("Позиции договора", CONTRACT_POSITIONS_HEADER_XPATH);
        partitionNames.add("Сведения об участнике закупки", PARTICIPANT_INFO_HEADER_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        contractPositions.add("Наименование товара, работ, услуг", CONTRACT_POSITIONS_TABLE_NAME_XPATH);
        contractPositions.add("Код ОКПД2", CONTRACT_POSITIONS_TABLE_OKPD2_XPATH);
        contractPositions.add("Код ОКВЭД2", CONTRACT_POSITIONS_TABLE_OKVED2_XPATH);
        contractPositions.add("Тип объекта закупки", CONTRACT_POSITIONS_TABLE_OBJECT_TYPE_XPATH);
        contractPositions.add("Список допустимых стран происхождения", LIST_OF_ACCEPTABLE_COUNTRIES_OF_ORIGIN_XPATH);
        contractPositions.add("Единица измерения", CONTRACT_POSITIONS_TABLE_UNIT_XPATH);
        contractPositions.add("Количество", CONTRACT_POSITIONS_TABLE_QUANTITY_XPATH);
        contractPositions.add("Цена за ед.изм.", CONTRACT_POSITIONS_TABLE_PRICE_PER_UNIT_XPATH);
        contractPositions.add("Стоимость", CONTRACT_POSITIONS_TABLE_TOTAL_COST_XPATH);
        contractPositions.add("Дополнительные сведения", CONTRACT_POSITIONS_TABLE_ADDITIONAL_INFO_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Переходит к просмотру раздела договора с указанным именем.
     *
     * @param partitionName имя раздела
     */
    public void goToPartition(String partitionName) throws Exception {
        $(this.getBy(partitionNames.find(partitionName))).shouldBe(visible).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведен переход к просмотру раздела: [%s].%n", partitionName);
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * Проверяет поле [Статус] на текст.
     *
     * @param status ожидаемый текст
     */
    public void checkContractStatus(String status) {
        $(By.id(CONTRACT_STATUS_ID)).waitUntil(visible, timeout, polling).shouldHave(text(status));
    }

    /**
     * Устанавливает переключатель в положение
     * [Участник подтверждает, что обеспечение исполнения договора предоставляется путем внесения денежных средств].
     */
    public void clickMakingMoneyRadioButton() throws Exception {
        $(By.id(MAKING_MONEY_RB_ID)).waitUntil(visible, timeout, polling).click();
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * Проверяет, что переключатель находится в положение
     * [Участник подтверждает, что обеспечение исполнения договора предоставляется путем внесения денежных средств].
     */
    public void checkMakingMoneyRadioButton() throws Exception {
        $(By.id(MAKING_MONEY_RB_ID)).waitUntil(visible, timeout, polling).isSelected();
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * Проверяет возможность скачать файл на странице [Договор]
     */
    public void checkDownloadContractFile() throws Exception {
        ElementsCollection contractDocumentsDownloadLinks =
                $$(this.getBy(ADDENDUM_PROJECT_FILE_COLUMN_XPATH)).filterBy(visible);
        for (SelenideElement contractDocumentsDownloadLink : contractDocumentsDownloadLinks) {
            this.checkPossibilityToDownloadFileWithGeneratedName(contractDocumentsDownloadLink,
                    contractDocumentsDownloadLink.getText(), 200);
        }
    }

    /**
     * Заполняет поле указанным значением.
     *
     * @param fieldName  имя поля поля
     * @param fieldValue значение поля
     */
    public void setContractPageField(String fieldName, String fieldValue) throws Exception {
        this.waitClearClickAndSendKeysById(fields.find(fieldName), fieldValue);
    }

    /**
     * Загружает файл в раздел
     * [Документы, подтверждающие предоставление обеспечения исполнения договора, и иные документы].
     */
    public void uploadDocument() throws Exception {
        String document = config.getAbsolutePathToUserAuthorityDoc();
        $(By.id(MAKING_MONEY_RB_ID)).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(1);
        $(By.xpath(MONEY_GUARANTEE_DOC_UPLOAD_XPATH)).waitUntil(exist, timeout, polling).sendKeys(document);
        this.waitForPageLoad(45);
        this.logFilledFieldName(
                "Документы, подтверждающие предоставление обеспечения исполнения договора, и иные документы",
                document);
    }

    /**
     * Нажимает на кнопку [Принять и подписать договор].
     */
    public void clickAcceptAndSignContractButton() throws Exception {
        $(By.id(ACCEPT_AND_SIGN_CONTRACT_BUTTON_ID)).waitUntil(exist, timeout, polling);
        this.scrollToCenter(By.id(ACCEPT_AND_SIGN_CONTRACT_BUTTON_ID));
        TimeUnit.SECONDS.sleep(1);
        $(By.id(ACCEPT_AND_SIGN_CONTRACT_BUTTON_ID)).click();
        this.waitLoadingImage();
        this.logPressedButtonName("Принять и подписать договор");
    }

    /**
     * Нажимает на кнопку [Создать протокол разногласий].
     */
    public void clickCreateDifferenceProtocolButton() throws Exception {
        $(By.id(CREATE_DIFFERENCE_PROTOCOL_ID)).waitUntil(exist, timeout, polling);
        this.scrollToCenter(By.id(CREATE_DIFFERENCE_PROTOCOL_ID));
        TimeUnit.SECONDS.sleep(1);
        $(By.id(CREATE_DIFFERENCE_PROTOCOL_ID)).click();
        this.waitLoadingImage();
        this.logPressedButtonName("Создать протокол разногласий");
    }

    /**
     * Нажимает на кнопку [Да] в диалоговом окне [Подтверждения действия].
     */
    public void clickYesButton() {
        SelenideElement yesButton = $(By.xpath(YES_BUTTON_XPATH));
        this.logButtonNameToPress("Да");
        yesButton.waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName("Да");
        yesButton.waitUntil(disappears, longtime, polling);
    }

    /**
     * Проверяет количество строк в таблице [Позиции договора].
     *
     * @param rows ожидаемое количество строк в таблице
     */
    public void checkNumberOfRowsInContractPositionsTable(String rows) {
        int expectedSize = Integer.parseInt(rows);
        this.checkElementsCollectionSize(
                $$(this.getBy(contractPositions.find("Наименование товара, работ, услуг"))), expectedSize);
    }

    /**
     * Проверяет текст ячейки в таблице [Позиции договора] для столбца с указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     */
    public void verifyCellByNameInLineByNumberFromContractPasitionsTableForText(
            String columnName, String rowNumber, String cellValue) {
        this.verifyCellInTableByColumnElementsAndLineNumberForText("Позиции договора",
                columnName, $$(this.getBy(contractPositions.find(columnName))), rowNumber, cellValue);
    }
}
