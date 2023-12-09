package ru.PageObjects.Customer.Protocol;

import Helpers.Dictionary;
import Helpers.SoftAssert;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonClarificationResponce.RegisterOfInquiriesPage;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Общий класс для работы со страницами протоколов [Протокол ....] (customer/lk/Protocols/ProtocolForm/).
 * Created by Evgeniy Glushko on 30.03.2016.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class CommonCustomerProtocolPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Страница с информационным сообщением которая появляется перед созданием черновика протокола

    //------------------------------------------------------------------------------------------------------------------
    // Сообщение [Внимание. После создания данного протокола будут открыты ценовые предложения...]
    private static final String OPEN_PROTOCOL_FORM_ATTENTION_MESSAGE_XPATH =
            "//section[@id='profile']/div[contains(@style,'center;min-height: 100px')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Открыть форму протокола]
    private static final String OPEN_PROTOCOL_FORM_BUTTON_XPATH = "//a[contains(., 'Открыть форму протокола')]";
    //------------------------------------------------------------------------------------------------------------------
    // Сообщение [Публикация протокола. После создания данного протокола будет невозможно внести изменения...]
    private static final String OPEN_PROTOCOL_FORM_PUBLISH_PROTOCOL_MESSAGE_XPATH =
            "//section[@id='profile']/div[contains(@style,'center;min-height: 100px')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Продолжить]
    private static final String CONTINUE_TO_OPEN_PROTOCOL_FORM_BUTTON_XPATH = "//a[contains(., 'Продолжить')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Общие сведения о лоте]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Общие сведения о лоте]
    private static final String COMMON_LOT_INFORMATION_PARTITION_HEADER_XPATH =
            "//h2[contains(.,'Общие сведения о лоте')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки]
    private static final String PURCHASE_NUMBER_XPATH = "//a[contains(@href, '/customer/lk/Auctions/View/')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Общие сведения о протоколе]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Общие сведения о протоколе]
    private static final String COMMON_PROTOCOL_INFORMATION_PARTITION_HEADER_XPATH =
            "//h2[contains(.,'Общие сведения о протоколе')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус]
    private static final String PROTOCOL_STATUS_XPATH = "//tr/td/span[@class='txt_style1']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения протокола рассмотрения заявок]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения протокола рассмотрения заявок]
    private static final String REVIEW_APPLICATION_PROTOCOL_DETAILS_PARTITION_HEADER_XPATH =
            "//h2[contains(.,'Сведения протокола рассмотрения заявок')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения протокола рассмотрения и оценки заявок]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения протокола рассмотрения и оценки заявок]
    private static final String REVIEW_APPLICATION_AND_EVALUATION_PROTOCOL_DETAILS_PARTITION_HEADER_XPATH =
            "//h2[contains(.,'Сведения протокола рассмотрения и оценки заявок')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения протокола проведения аукциона]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения протокола проведения аукциона]
    private static final String AUCTION_PROTOCOL_DETAILS_PARTITION_HEADER_XPATH =
            "//h2[contains(.,'Сведения протокола проведения аукциона')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    //region Таблица [Сведения протокола ...]

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Скачать заявки]
    private static final String DOWNLOAD_REQUESTS_BUTTON_ID = "downloadAppsTemplate";
    //------------------------------------------------------------------------------------------------------------------

    //endregion

    // region Раздел [Формирование протокола из шаблона]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Формирование протокола из шаблона]
    private static final String FORMING_PROTOCOL_FROM_TEMPLATE_PARTITION_HEADER_XPATH =
            "//h2[contains(.,'Формирование протокола из шаблона')]";
    //------------------------------------------------------------------------------------------------------------------
    // Список шаблонов протоколов (в свернутом состоянии) для первого лота в закупке
    private static final String LOT1_PROTOCOL_TEMPLATES_LIST_COLLAPSED_XPATH =
            "//input[@id='LotInfos_0__TemplateId']/preceding-sibling::span[1]/span";
    //------------------------------------------------------------------------------------------------------------------
    // Список шаблонов протоколов (в раскрытом состоянии) для первого лота в закупке
    private static final String LOT1_PROTOCOL_TEMPLATES_LIST_EXPANDED_XPATH =
            "//*[@id='LotInfos_0__TemplateId_listbox']/li";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Сформировать и прикрепить]
    private static final String GENERATE_AND_ATTACH_BUTTON_XPATH =
            "//h2[contains(.,'Формирование протокола из шаблона')]/following-sibling::div[1]" +
                    "//span[contains(.,'Сформировать и прикрепить')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Прикрепление протокола рассмотрения заявок аукциона]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Прикрепление протокола рассмотрения заявок аукциона]
    private static final String ATTACH_REVIEW_PROTOCOL_AU_PARTITION_HEADER_XPATH =
            "//h2[contains(.,'Прикрепление протокола рассмотрения заявок аукциона')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка с именем прикрепленного файла протокола для скачивания
    private static final String PROTOCOL_CONSIDERATION_REQUEST_LINK_XPATH =
            "//a[@class='link3 FileName' and contains(@href, '.kontur.ru/files/FileDownloadHandler')]";
    //------------------------------------------------------------------------------------------------------------------
    // Группа ссылок с именами прикрепленных файлов протоколов для скачивания
    private static final String PROTOCOL_CONSIDERATION_REQUESTS_LINKS_XPATH =
            "//*[@id='filesItemTemplate' and @style='display: block;']/a[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Группа ссылок [Удалить] для  прикрепленных к протоколу файлов
    private static final String REMOVE_ATTACHMENT_LINKED_TO_PROTOCOL_LINKS_XPATH =
            "//a[contains(@onclick, 'removeItem') and  contains(., 'Удалить')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Прикрепление протокола рассмотрения заявок запроса котировок]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Прикрепление протокола рассмотрения заявок запроса котировок]
    private static final String ATTACH_REVIEW_PROTOCOL_ZK_PARTITION_HEADER_XPATH =
            "//h2[contains(.,'Прикрепление протокола рассмотрения заявок')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Согласование]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Согласование]
    private static final String APPROVAL_PARTITION_HEADER_XPATH = "//*[@id='approval']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус рассмотрения]
    private static final String APPROVAL_STATUS_XPATH =
            "//label[@class='control-label col-md-3' and contains(., 'Статус рассмотрения:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип согласования]
    private static final String APPROVAL_TYPE_XPATH =
            "//label[@class='control-label col-md-3' and contains(., 'Тип согласования:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Заявка на согласование подана]
    private static final String DATE_OF_APPROVAL_REQUEST_XPATH =
            "//label[@class='control-label col-md-3' and contains(., 'Заявка на согласование подана:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата согласования]
    private static final String DATE_OF_APPROVAL_PROCEDURE_XPATH =
            "//label[@class='control-label col-md-3' and contains(., 'Дата согласования:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата принятия решения]
    private static final String DATE_OF_APPROVAL_XPATH =
            "//label[@class='control-label col-md-3' and contains(., 'Дата принятия решения:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Комиссия]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Комиссия]
    private static final String COMMISSION_PARTITION_HEADER_XPATH = "//h3[contains(.,'Комиссия')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Ответственный]
    private static final String TABLE_RESPONSIBLE_COLUMN_XPATH = "//*[@id='protocol_approvalinfo']//table/tbody/tr/td[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Статус]
    private static final String TABLE_STATUS_COLUMN_XPATH = "//*[@id='protocol_approvalinfo']//table/tbody/tr/td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Решение принято]
    private static final String TABLE_DECISION_IS_MADE_COLUMN_XPATH = "//*[@id='protocol_approvalinfo']//table/tbody/tr/td[3]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Комментарий]
    private static final String TABLE_COMMENT_COLUMN_XPATH = "//*[@id='protocol_approvalinfo']//table/tbody/tr/td[4]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Попозиционное сравнение заявок]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Попозиционное сравнение заявок]
    private static final String POSITIONAL_COMPARISON_HEADER_XPATH =
            "//h2[contains(.,'Попозиционное сравнение заявок')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Наименование]
    private static final String COMPARISON_TABLE_NAME_COLUMN_XPATH = "//div[@id='ItemsGrid']/table/tbody/tr/td[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Стоимость позиции]
    private static final String COMPARISON_TABLE_POSITION_VALUE_COLUMN_XPATH =
            "//div[@id='ItemsGrid']/table/tbody/tr/td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Победитель] - текущее выбранное значение ( все строки )
    private static final String COMPARISON_TABLE_WITH_WINNER_WINNER_COLUMN_SELECTED_VALUE_XPATH =
            "//div[@id='ItemsGrid']/table/tbody/tr/td[3]/span/span/span[1]";
    //-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    // Столбец [Победитель] - список значений находится в свернутом состоянии ( все строки )
    private static final String COMPARISON_TABLE_WITH_WINNER_WINNER_COLUMN_COLLAPSED_XPATH =
            "//table[contains(@class, 'LotItemApplicationCompareGrid')]/tbody//span[@class='k-select']";
    //-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    // Столбец [Победитель] - требуемое значение в списке доступных значений ( все строки )
    private static final String COMPARISON_TABLE_WITH_WINNER_WINNER_COLUMN_DESIRED_VALUE_IN_LIST_XPATH =
            "//ul[contains(@id, 'TradeLotItemWinner_') and contains(@id, '_listbox')]/li[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Поставщик 1]
    private static final String COMPARISON_TABLE_SUPPLIER1_VALUE_COLUMN_XPATH =
            "//div[@id='ItemsGrid']/table/tbody/tr/td[3]";
    //-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    private static final String COMPARISON_TABLE_WITH_WINNER_SUPPLIER1_VALUE_COLUMN_XPATH =
            "//div[@id='ItemsGrid']/table/tbody/tr/td[4]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Поставщик 2]
    private static final String COMPARISON_TABLE_SUPPLIER2_VALUE_COLUMN_XPATH =
            "//div[@id='ItemsGrid']/table/tbody/tr/td[4]";
    //-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    private static final String COMPARISON_TABLE_WITH_WINNER_SUPPLIER2_VALUE_COLUMN_XPATH =
            "//div[@id='ItemsGrid']/table/tbody/tr/td[5]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Блок кнопок управления протоколом

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Подписать и опубликовать]
    private static final String SIGN_AND_PUBLISH_BUTTON_ID = "publishAndSignProtocol";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отправить в ЕИС]
    private static final String PUBLISH_ON_OOS_BUTTON_ID = "publishOnOos";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Сохранить]
    private static final String SAVE_PROTOCOL_BUTTON_ID = "saveProtocol";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отправить на согласование]
    private static final String START_SEND_TO_APPROVAL_BUTTON_ID = "startSendToApproval";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отменить согласование]
    private static final String CANCEL_APPROVAL_REQUEST_BUTTON_ID = "cancelApprovalRequest";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Утвердить]
    private static final String APPROVE_PROTOCOL_BUTTON_ID = "approveProtocol";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отклонить]
    private static final String REJECT_PROTOCOL_BUTTON_ID = "rejectProtocol";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Изменить опубликованный протокол]
    private static final String CHANGE_PUBLISHED_PROTOCOL_BUTTON_ID = "changePublishedProtocolButton";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary partitionNames = new Dictionary();     // основные разделы протокола
    private final Dictionary fieldNames = new Dictionary();         // все поля на странице
    private final Dictionary buttonNames = new Dictionary();        // все кнопки на странице
    private final Dictionary commColumns = new Dictionary();        // все колонки таблицы в разделе [Комиссия]
    private final Dictionary positionalColumns4 = new Dictionary(); // все колонки таблицы [Попозиционное сравнение заявок]
    private final Dictionary positionalColumns5 = new Dictionary(); // все колонки таблицы [Попозиционное сравнение заявок]

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CommonCustomerProtocolPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        partitionNames.add("Общие сведения о лоте", COMMON_LOT_INFORMATION_PARTITION_HEADER_XPATH);
        partitionNames.add("Общие сведения о протоколе", COMMON_PROTOCOL_INFORMATION_PARTITION_HEADER_XPATH);
        partitionNames.add("Сведения протокола рассмотрения заявок",
                REVIEW_APPLICATION_PROTOCOL_DETAILS_PARTITION_HEADER_XPATH);
        partitionNames.add("Сведения протокола рассмотрения и оценки заявок",
                REVIEW_APPLICATION_AND_EVALUATION_PROTOCOL_DETAILS_PARTITION_HEADER_XPATH);
        partitionNames.add("Сведения протокола проведения аукциона", AUCTION_PROTOCOL_DETAILS_PARTITION_HEADER_XPATH);
        partitionNames.add("Формирование протокола из шаблона", FORMING_PROTOCOL_FROM_TEMPLATE_PARTITION_HEADER_XPATH);
        partitionNames.add("Прикрепление протокола рассмотрения заявок аукциона",
                ATTACH_REVIEW_PROTOCOL_AU_PARTITION_HEADER_XPATH);
        partitionNames.add("Прикрепление протокола рассмотрения заявок",
                ATTACH_REVIEW_PROTOCOL_ZK_PARTITION_HEADER_XPATH);
        partitionNames.add("Согласование", APPROVAL_PARTITION_HEADER_XPATH);
        partitionNames.add("Комиссия", COMMISSION_PARTITION_HEADER_XPATH);
        partitionNames.add("Попозиционное сравнение заявок", POSITIONAL_COMPARISON_HEADER_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Сообщение Внимание.", OPEN_PROTOCOL_FORM_ATTENTION_MESSAGE_XPATH);
        fieldNames.add("Сообщение Публикация протокола.", OPEN_PROTOCOL_FORM_PUBLISH_PROTOCOL_MESSAGE_XPATH);
        fieldNames.add("Номер закупки", PURCHASE_NUMBER_XPATH);
        fieldNames.add("Статус", PROTOCOL_STATUS_XPATH);
        fieldNames.add("Статус рассмотрения", APPROVAL_STATUS_XPATH);
        fieldNames.add("Тип согласования", APPROVAL_TYPE_XPATH);
        fieldNames.add("Заявка на согласование подана", DATE_OF_APPROVAL_REQUEST_XPATH);
        fieldNames.add("Дата согласования", DATE_OF_APPROVAL_PROCEDURE_XPATH);
        fieldNames.add("Дата принятия решения", DATE_OF_APPROVAL_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Открыть форму протокола", OPEN_PROTOCOL_FORM_BUTTON_XPATH);
        buttonNames.add("Продолжить", CONTINUE_TO_OPEN_PROTOCOL_FORM_BUTTON_XPATH);
        buttonNames.add("Подписать и опубликовать", SIGN_AND_PUBLISH_BUTTON_ID);
        buttonNames.add("Отправить в ЕИС", PUBLISH_ON_OOS_BUTTON_ID);
        buttonNames.add("Сохранить", SAVE_PROTOCOL_BUTTON_ID);
        buttonNames.add("Отправить на согласование", START_SEND_TO_APPROVAL_BUTTON_ID);
        buttonNames.add("Отменить согласование", CANCEL_APPROVAL_REQUEST_BUTTON_ID);
        buttonNames.add("Утвердить", APPROVE_PROTOCOL_BUTTON_ID);
        buttonNames.add("Отклонить", REJECT_PROTOCOL_BUTTON_ID);
        buttonNames.add("Изменить опубликованный протокол", CHANGE_PUBLISHED_PROTOCOL_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
        commColumns.add("Ответственный", TABLE_RESPONSIBLE_COLUMN_XPATH);
        commColumns.add("Статус", TABLE_STATUS_COLUMN_XPATH);
        commColumns.add("Решение принято", TABLE_DECISION_IS_MADE_COLUMN_XPATH);
        commColumns.add("Комментарий", TABLE_COMMENT_COLUMN_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        positionalColumns4.add("Наименование", COMPARISON_TABLE_NAME_COLUMN_XPATH);
        positionalColumns4.add("Стоимость позиции", COMPARISON_TABLE_POSITION_VALUE_COLUMN_XPATH);
        positionalColumns4.add("Поставщик 1", COMPARISON_TABLE_SUPPLIER1_VALUE_COLUMN_XPATH);
        positionalColumns4.add("Поставщик 2", COMPARISON_TABLE_SUPPLIER2_VALUE_COLUMN_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        positionalColumns5.add("Наименование", COMPARISON_TABLE_NAME_COLUMN_XPATH);
        positionalColumns5.add("Стоимость позиции", COMPARISON_TABLE_POSITION_VALUE_COLUMN_XPATH);
        positionalColumns5.add("Победитель", COMPARISON_TABLE_WITH_WINNER_WINNER_COLUMN_SELECTED_VALUE_XPATH);
        positionalColumns5.add("Поставщик 1", COMPARISON_TABLE_WITH_WINNER_SUPPLIER1_VALUE_COLUMN_XPATH);
        positionalColumns5.add("Поставщик 2", COMPARISON_TABLE_WITH_WINNER_SUPPLIER2_VALUE_COLUMN_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Переходит к просмотру раздела протокола с указанным именем.
     *
     * @param partitionName имя раздела
     */
    public void goToPartition(String partitionName) throws Exception {
        SelenideElement partition = $(this.getBy(partitionNames.find(partitionName)));
        this.scrollToCenter(partition);
        partition.waitUntil(clickable, timeout, polling).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведен переход к просмотру раздела: [%s].%n", partitionName);
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * Ожидает появления требуемого статуса протокола.
     *
     * @param expectedStatus ожидаемый статус протокола.
     */
    public void checkProtocolStatus(String expectedStatus) throws Exception {
        this.waitForTextInField(expectedStatus, By.xpath(PROTOCOL_STATUS_XPATH), "Статус");
    }

    /**
     * Получает текст первой по порядку ссылки с именем прикрепленного файла протокола для скачивания.
     */
    public String getAttachedProtocolLinkText() {
        return this.getAttachedProtocolLinkText("1");
    }

    /**
     * Получает текст ссылки с именем прикрепленного файла протокола для скачивания по её порядковому номеру.
     *
     * @param linkNumber порядковый номер ссылки
     */
    public String getAttachedProtocolLinkText(String linkNumber) {
        System.out.println(
                "[ИНФОРМАЦИЯ]: получаем текст ссылки на прикрепленный файл с порядковым номером [" + linkNumber + "].");
        int index = Integer.parseInt(linkNumber) - 1;
        ElementsCollection attachedProtocols = $$(By.xpath(PROTOCOL_CONSIDERATION_REQUEST_LINK_XPATH));
        String text = attachedProtocols.get(index).waitUntil(visible, timeout, polling).getText();
        System.out.println("[ИНФОРМАЦИЯ]: текст ссылки [" + text + "].");
        return text;
    }

    /**
     * Удаляет все прикрепленные ранее к протоколу файлы.
     */
    public void removeAllAttachmentsToProtocolInEditMode() throws Exception {
        ElementsCollection attachedFilesRemoveLinks = $$(By.xpath(REMOVE_ATTACHMENT_LINKED_TO_PROTOCOL_LINKS_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] прикрепленных к протоколу файлов.%n",
                attachedFilesRemoveLinks.size());
        while (attachedFilesRemoveLinks.size() > 0) {
            attachedFilesRemoveLinks.get(0).waitUntil(clickable, timeout, polling).click();
            TimeUnit.SECONDS.sleep(3);
            System.out.println("[ИНФОРМАЦИЯ]: удаление прикрепленного к протоколу файла.");
            attachedFilesRemoveLinks = $$(By.xpath(REMOVE_ATTACHMENT_LINKED_TO_PROTOCOL_LINKS_XPATH));
        }
    }

    /**
     * Нажимает на кнопку [Сформировать и прикрепить] для первого лота.
     */
    public void clickGenerateAndAttachButton() throws Exception {
        this.clickGenerateAndAttachButton("1");
    }

    /**
     * Нажимает на кнопку [Сформировать и прикрепить] для лота с указанным порядковым номером.
     *
     * @param lotNumber порядковый номер лота
     */
    public void clickGenerateAndAttachButton(String lotNumber) throws Exception {
        System.out.println(
                "[ИНФОРМАЦИЯ]: нажимаем кнопку [Сформировать и прикрепить] для лота с номером [" + lotNumber + "].");
        int index = Integer.parseInt(lotNumber) - 1;
        ElementsCollection generateAndAttachButtons = $$(By.xpath(GENERATE_AND_ATTACH_BUTTON_XPATH));
        //--------------------------------------------------------------------------------------------------------------
        int beforeClick = $$(By.xpath(PROTOCOL_CONSIDERATION_REQUESTS_LINKS_XPATH)).size();
        int afterClick;
        int nTries = 100;
        int i = 1;
        do {
            SelenideElement generateAndAttachButton = generateAndAttachButtons.get(index);
            this.scrollToCenter(generateAndAttachButton);
            generateAndAttachButton.waitUntil(clickable, timeout, polling);
            this.logButtonNameToPress("Сформировать и прикрепить");
            this.clickInElementJS(generateAndAttachButton);
            this.waitLoadingImage(3);
            this.logPressedButtonName("Сформировать и прикрепить", Integer.toString(i));
            afterClick = $$(By.xpath(PROTOCOL_CONSIDERATION_REQUESTS_LINKS_XPATH)).size();
            i++;
        } while (afterClick == beforeClick && i <= nTries);
        //--------------------------------------------------------------------------------------------------------------
    }

    /**
     * Нажимает на кнопку [Изменить опубликованный протокол].
     *
     * @param reason причина отмены протокола
     */
    public void clickChangePublishedProtocolButton(String reason) throws Exception {
        this.clickOnButton("Изменить опубликованный протокол");
        //--------------------------------------------------------------------------------------------------------------
        ChangePublishedProtocolDialog changePublishedProtocolDialog = new ChangePublishedProtocolDialog(driver);
        changePublishedProtocolDialog.
                ifDialogOpened().
                setFieldClearClickAndSendKeys("Причина отмены протокола", reason).
                clickOnButtonByName("Отменить протокол");
        //--------------------------------------------------------------------------------------------------------------
    }

    /**
     * Проверяет наличие дубликатов в раскрывающемся списке шаблонов протоколов.
     */
    public void checkDuplicatesInTemplatesList() throws Exception {
        ElementsCollection collapsedLists = $$(By.xpath(LOT1_PROTOCOL_TEMPLATES_LIST_COLLAPSED_XPATH));
        SelenideElement collapsedList = collapsedLists.get(0).waitUntil(exist, timeout, polling).shouldBe(visible);
        ElementsCollection templates = $$(By.xpath(LOT1_PROTOCOL_TEMPLATES_LIST_EXPANDED_XPATH));

        System.out.printf("[ИНФОРМАЦИЯ]: Текущий размер списка шаблонов протоколов: [%d].%n", templates.size());
        Assert.assertTrue("[ОШИБКА]: Список шаблонов протоколов пуст", templates.size() > 0);
        this.waitForListOpens(collapsedList, templates.first());
        System.out.println("[ИНФОРМАЦИЯ]: Проверяем дубликаты в списке шаблонов протоколов:");

        boolean isSecond = false;
        String previousTemplateName = "";

        for (SelenideElement template : templates) {
            String currentTemplateName = template.getText();
            System.out.printf("[ИНФОРМАЦИЯ]: Текущий шаблон протокола: [%s].%n", currentTemplateName);
            if (isSecond)
                SoftAssert.assertNotEquals("[ИНФОРМАЦИЯ]: Обнаружен дубликат шаблона протокола !",
                        previousTemplateName, currentTemplateName);
            previousTemplateName = currentTemplateName;
            isSecond = true;
        }
    }

    /**
     * Проверяет отображение поля или текстовой надписи на странице.
     *
     * @param fieldName имя поля
     */
    public boolean fieldOrTextIsDisplayed(String fieldName) {
        SelenideElement field = $(this.getBy(fieldNames.find(fieldName)));
        System.out.printf("[ИНФОРМАЦИЯ]: проверка отображения  поля или текстовой надписи [%s] на странице.%n", fieldName);

        return field.isDisplayed();
    }

    /**
     * Проверяет значение поля.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение, которое должно содержаться в поле
     */
    public void verifyFieldContent(String fieldName, String value) {
        String message = String.format("[ОШИБКА]: значение [%s] не содержится в поле [%s].", value, fieldName);

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        $(this.getBy(fieldNames.find(fieldName))).waitUntil(exist, timeout, polling).shouldBe(visible);
        if (value.trim().isEmpty())
            Assert.assertTrue(message, $(this.getBy(fieldNames.find(fieldName))).getText().trim().isEmpty());
        else
            Assert.assertTrue(message, $(this.getBy(fieldNames.find(fieldName))).getText().contains(value));
    }

    /**
     * Проверяет текст ячейки в таблице [Комиссия] для столбца с указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     */
    public void verifyCellByNameInLineByNumberFromCommissionTableForText(
            String columnName, String rowNumber, String cellValue) {
        this.verifyCellInTableByColumnElementsAndLineNumberForText("Комиссия",
                columnName, $$(this.getBy(commColumns.find(columnName))), rowNumber, cellValue);
    }

    /**
     * Проверяет текст ячейки в таблице [Попозиционное сравнение заявок] для столбца с указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     */
    public void verifyCellByNameInLineByNumberFromPositionalComparisonTable4ForText(
            String columnName, String rowNumber, String cellValue) {
        this.verifyCellInTableByColumnElementsAndLineNumberForText("Попозиционное сравнение заявок",
                columnName, $$(this.getBy(positionalColumns4.find(columnName))), rowNumber, cellValue);
    }

    /**
     * Проверяет текст ячейки в таблице [Попозиционное сравнение заявок]
     * для столбца с указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     */
    public void verifyCellByNameInLineByNumberFromPositionalComparisonTable5ForText(
            String columnName, String rowNumber, String cellValue) {
        this.verifyCellInTableByColumnElementsAndLineNumberForText("Попозиционное сравнение заявок",
                columnName, $$(this.getBy(positionalColumns5.find(columnName))), rowNumber, cellValue);
    }

    /**
     * Устанавливает значение поля [Победитель] в таблице [Попозиционное сравнение заявок]
     * для столбца с указанным именем и номером строки.
     *
     * @param rowNumber порядковый номер строки в таблице
     */
    public void selectAnotherWinnerInLineByNumberFromPositionalComparisonTable5(String rowNumber) throws Exception {
        ElementsCollection collapsedLists = $$(By.xpath(COMPARISON_TABLE_WITH_WINNER_WINNER_COLUMN_COLLAPSED_XPATH));
        ElementsCollection desiredValues =
                $$(By.xpath(COMPARISON_TABLE_WITH_WINNER_WINNER_COLUMN_DESIRED_VALUE_IN_LIST_XPATH));
        ElementsCollection selectedValues =
                $$(By.xpath(COMPARISON_TABLE_WITH_WINNER_WINNER_COLUMN_SELECTED_VALUE_XPATH));

        this.selectDesiredValueInLineByNumberFromTable("Попозиционное сравнение заявок", "Победитель",
                rowNumber, collapsedLists, desiredValues, selectedValues);
    }

    /**
     * Делает щелчок мышью по кнопке с указанным именем.
     *
     * @param buttonName имя кнопки
     * @return страница [Реестр запросов на разъяснение документации]
     */
    public void clickOnButton(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.scrollToCenterAndclickInElementJS(this.getBy(buttonNames.find(buttonName)));
        this.logPressedButtonName(buttonName);
        this.waitForPageLoad(15);
    }

    /**
     * Загружает Zip архив, удаляет дату из имен файлов и проверяет находящиеся имена.
     *
     * @param zipName           имя архива
     * @param expectedFileNames список ожидаемых имен файлов
     */
    public void downloadZipArchiveModifyAndCheckFileNamesInIt(String zipName, ArrayList<String> expectedFileNames)
            throws Exception {
        List<String> fileNames =
                this.downloadZipArchiveAndReturnListOfFileNames(this.getBy(DOWNLOAD_REQUESTS_BUTTON_ID), zipName);

        checkPresenceFileNames(removeDateFromFileNames(fileNames), expectedFileNames);
    }

    /**
     * Удаляет дату из имен файлов
     *
     * @param fileNames список имен файлов
     * @return возвращает список скорректированных имен файлов
     */
    private List<String> removeDateFromFileNames(List<String> fileNames) {
        List<String> truncatedFileNames = new ArrayList<>();

        for (int i = 0; i < fileNames.size(); i++) {
            //Удаление неотслеживаемой части названия файла
            // Формат "Заявка1_ООО Автотестеры 223_от_25.09.2020_10-17/Печатная_форма_заявки.html"
            // Удаляемая часть "Заявка1_ООО Автотестеры 223 [_от_25.09.2020_10-17] /Печатная_форма_заявки.html"
            String fileName = fileNames.get(i);
            if (fileName.contains("_от")) {
                String leftPartOfFileName = fileName.substring(0, fileName.indexOf("_от"));
                String rightPartOfFileName = fileName.substring(fileName.indexOf("/"));
                truncatedFileNames.add(i, leftPartOfFileName + rightPartOfFileName);
            } else {
                truncatedFileNames.add(fileName);
            }
        }

        return truncatedFileNames;
    }

    /**
     * Проверяет наличие ожидаемых имен файлов
     *
     * @param actualFileNames   - список фактических имен файлов
     * @param expectedFileNames - список ожидаемых имен файлов
     */
    private void checkPresenceFileNames(List<String> actualFileNames, List<String> expectedFileNames) {
        Set<String> fileNames = new HashSet<>();

        //Проверяем файлы на дублирование
        for (String actualFileName : actualFileNames) {
            Assert.assertTrue(String.format("[ОШИБКА]: файл [%s] повторно содержится в архиве", actualFileName),
                    fileNames.add(actualFileName));
        }

        //Сравниваем список имен ожидаемых файлов со списком имен, который получили из архива
        for (String expectedFileName : expectedFileNames) {
            System.out.printf("[ИНФОРМАЦИЯ]: проверка наличия файла [%s].%n", expectedFileName);
            Assert.assertTrue(String.format("[ОШИБКА]: файл [%s] не содержится в архиве", expectedFileName),
                    fileNames.remove(expectedFileName));
        }

        checkPresenceOfUnexpectedFileNames(fileNames);
    }

    /**
     * Проверяет присутсвие неожидаемых файлов.
     *
     * @param actualFileNames - множество фактических имен файлов
     */
    private void checkPresenceOfUnexpectedFileNames(Set<String> actualFileNames) {
        //Проверяем, есть ли лишние файлы в архиве
        if (actualFileNames.size() > 0) {
            for (String unexpectedFile : actualFileNames) {
                System.err.printf("[ОШИБКА]: присутствие файла [%s] в архиве не ожидалось%n", unexpectedFile);
            }
            Assert.fail("[ОШИБКА]: в архиве присутствуют файлы, которые не ожидалось увидеть");
        }
    }

    /**
     * Производит нажатие на поле с указанныи именем.
     *
     * @param fieldName имя поля
     */
    public void clickOnField(String fieldName) throws Exception {
        this.scrollToCenterAndclickInElementJS(fieldNames.find(fieldName));
        System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие на поле [%s].%n", fieldName);
    }
}
