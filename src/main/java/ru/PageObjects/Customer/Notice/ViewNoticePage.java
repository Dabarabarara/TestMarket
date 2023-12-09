package ru.PageObjects.Customer.Notice;

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
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Класс для работы со страницей [Просмотр извещения XXXX]
 * ( Главная / Заказчикам / Мои закупки / Просмотр извещения XXXX ) ( .kontur.ru/customer/lk/auctions/view/ ).
 * Он же используется для страницы:
 * [Протокол ...] / [Номер закупки (номер редакции извещения)] ( .kontur.ru/customer/lk/Auctions/View/ ).
 * Created by Vladimir V. Klochkov on 06.04.2016.
 * Updated by Alexander S. Vasyurenko on 17.06.2021.
 */
public class ViewNoticePage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     *******************************************************************************************************************/
    // region Заголовок страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [История]
    private static final String HISTORY_BUTTON_XPATH = "//*[@id='HistoryButton']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Опубликовать разъяснение]
    private static final String PUBLISH_EXPLANATION_XPATH =
            "//a[contains(@href, '/customer/lk/RegisterOfInquiries/CreateClarifyDocResponse/')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отправить в ЕИС]
    private static final String PUBLISH_TO_OOS_BUTTON_XPATH = "//*[@id='PublishToOosButton']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отказ от проведения]
    private static final String TRADE_CANCEL_BUTTON_XPATH = "//*[@id='btnTradeCancel']";
    //------------------------------------------------------------------------------------------------------------------
    // Надпись [Получен проект внесения изменений в извещение о проведении закупки.
    // Перейти к публикации изменений извещения.] - адрес ссылки для перехода в режим редактирования извещения о закупке
    private static final String CUSTOMER_TRADE_EDIT_LINK_XPATH = "//a[contains(@href, '/customer/lk/Trade/Edit/')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Общие сведения о закупке]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус закупки]
    private static final String PURCHASE_STATUS_XPATH =
            "//label[@class='control-label col-md-3' and contains(., 'Статус закупки:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус процедуры]
    private static final String PROCEDURE_STATUS_XPATH =
            "//label[@class='control-label col-md-3' and contains(., 'Статус процедуры:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки]
    private static final String PURCHASE_NUMBER_XPATH =
            "//label[@class='control-label col-md-3' and contains(., 'Номер закупки:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер процедуры]
    private static final String PROCEDURE_NUMBER_XPATH =
            "//label[@class='control-label col-md-3' and contains(., 'Номер процедуры:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки в ЕИС]
    private static final String EIS_NUMBER_XPATH =
            "//label[@class='control-label col-md-3' and contains(., 'Номер закупки в ЕИС:')]/following-sibling::div[1]/a";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер процедуры в ЕИС]
    private static final String EIS_PROCEDURE_NUMBER_XPATH =
            "//label[@class='control-label col-md-3' and contains(., 'Номер процедуры в ЕИС:')]/following-sibling::div[1]/a";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование закупки]:
    //..................................................................................................................
    // Просмотр извещения в режиме редактирования
    private static final String PURCHASE_NAME_ID = "OrderName";
    //..................................................................................................................
    // Просмотр извещения в режиме только чтения
    private static final String PURCHASE_NAME_XPATH =
            "//label[@class='control-label col-md-3' and contains(., 'Наименование закупки:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата и время публикации извещения]
    private static final String PURCHASE_DATE_TIME_XPATH = "//label[@class='control-label col-md-5' and " +
            "contains(., 'Дата и время публикации извещения:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование процедуры]:
    private static final String PROCEDURE_NAME_XPATH =
            "//label[@class='control-label col-md-3' and contains(., 'Наименование процедуры:')]/following-sibling::div[1]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о лотах] -> [Лот №1]

    //------------------------------------------------------------------------------------------------------------------
    // Надпись [Сведения о лотах]
    private static final String LOTS_INFORMATION_LABEL_XPATH = "//h2[contains(., 'Сведения о лотах')]";
    //------------------------------------------------------------------------------------------------------------------
    // Надпись [Лот №1]
    private static final String LOT1_LABEL_XPATH = "//h2[contains(., 'Лот №1')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус лота №1]
    private static final String LOT1_STATE_XPATH = "//li[@class='lot-state-item current '][1]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Изменить переторжку]
    private static final String CHANGE_RETRADING_BUTTON_XPATH = "//button[contains(., 'Изменить переторжку')]";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Договоры закупки]
    private static final String LOT1_INFORMATION_ABOUT_CONTRACTS_OF_PURCHASE_HEADER_XPATH =
            "//h4[contains(., 'Договоры закупки')][1]";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения об объекте(ах) закупки]
    private static final String LOT1_INFORMATION_ABOUT_OBJECTS_OF_PURCHASE_HEADER_XPATH =
            "//h2[contains(., 'Сведения об объекте(ах) закупки')][1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Попозиционное сравнение]
    private static final String POSITIONAL_COMPARISON_XPATH =
            "//h2[contains(., 'Сведения об объекте(ах) закупки')][1]/following-sibling::div[1]/div";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Возможность подачи заявки на часть позиций]
    private static final String POSSIBILITY_OF_APPLYING_FOR_SOME_POSITIONS_XPATH =
            "//h2[contains(., 'Сведения об объекте(ах) закупки')][1]/following-sibling::div[2]/div";
    //------------------------------------------------------------------------------------------------------------------

    // region Таблица [Договоры закупки]

    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Номер места участника]
    private static final String TABLE_PURCHASE_CONTRACTS_INFO_PARTICIPANT_PLACE_NUMBER_COLUMN_XPATH =
            "//*[@id='ContractsGrid']/table/tbody/tr/td[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Номер заявки]
    private static final String TABLE_PURCHASE_CONTRACTS_APPLICATION_NUMBER_COLUMN_XPATH =
            "//*[@id='ContractsGrid']/table/tbody/tr/td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Наименование участника]
    private static final String TABLE_PURCHASE_CONTRACTS_PARTICIPANT_NAME_COLUMN_XPATH =
            "//*[@id='ContractsGrid']/table/tbody/tr/td[3]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Заказчик]
    private static final String TABLE_PURCHASE_CONTRACTS_CUSTOMER_NAME_COLUMN_XPATH =
            "//*[@id='ContractsGrid']/table/tbody/tr/td[4]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Статус договора]
    private static final String TABLE_PURCHASE_CONTRACTS_CONTRACT_STATUS_COLUMN_XPATH =
            "//*[@id='ContractsGrid']/table/tbody/tr/td[5]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Действия]
    private static final String TABLE_PURCHASE_CONTRACTS_POSSIBLE_ACTIONS_COLUMN_XPATH =
            "//*[@id='ContractsGrid']/table/tbody/tr/td[6]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Действия] все ссылки [Создать договор на ЭП]
    private static final String TABLE_PURCHASE_CONTRACTS_POSSIBLE_ACTIONS_COLUMN_CREATE_CONTRACT_LINKS_XPATH =
            "//*[@id='ContractsGrid']/table/tbody/tr/td[6]/a[contains(.,'Создать договор на ЭП')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Таблица [Сведения об объекте(ах) закупки]

    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Наименование]
    private static final String TABLE_PURCHASE_OBJECTS_INFO_NAME_COLUMN_XPATH =
            "//*[contains(@id, 'ContractObjectListItemsGrid')]/table/tbody/tr/td[1]/span";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Стоимость позиции]
    private static final String TABLE_PURCHASE_OBJECTS_INFO_POSITION_VALUE_COLUMN_XPATH =
            "//*[contains(@id, 'ContractObjectListItemsGrid')]/table/tbody/tr/td[2]/span";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [ОКПД2]
    private static final String TABLE_PURCHASE_OBJECTS_INFO_OKPD2_COLUMN_XPATH =
            "//*[contains(@id, 'ContractObjectListItemsGrid')]/table/tbody/tr/td[5]/span";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [ОКВЭД2]
    private static final String TABLE_PURCHASE_OBJECTS_INFO_OKVED2_COLUMN_XPATH =
            "//*[contains(@id, 'ContractObjectListItemsGrid')]/table/tbody/tr/td[6]/span";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Единицы измерения]
    private static final String TABLE_PURCHASE_OBJECTS_INFO_UNITS_COLUMN_XPATH =
            "//*[contains(@id, 'ContractObjectListItemsGrid')]/table/tbody/tr/td[7]/span";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Количество]
    private static final String TABLE_PURCHASE_OBJECTS_INFO_AMOUNT_COLUMN_XPATH =
            "//*[contains(@id, 'ContractObjectListItemsGrid')]/table/tbody/tr/td[8]/span";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Цена за единицу продукции]
    private static final String TABLE_PURCHASE_OBJECTS_INFO_UNIT_PRICE_COLUMN_XPATH =
            "//*[contains(@id, 'ContractObjectListItemsGrid')]/table/tbody/tr/td[9]/span";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дополнительные сведения]
    private static final String TABLE_PURCHASE_OBJECTS_INFO_ADDITIONAL_INFO_COLUMN_XPATH =
            "//*[contains(@id, 'ContractObjectListItemsGrid')]/table/tbody/tr/td[10]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дополнительные поля]
    private static final String TABLE_PURCHASE_OBJECTS_INFO_ADDITIONAL_FIELDS_COLUMN_XPATH =
            "//*[contains(@id, 'ContractObjectListItemsGrid')]/table/tbody/tr/td[11]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о приглашенных участниках]
    private static final String LOT1_INFORMATION_ABOUT_INVITED_PARTICIPANTS_HEADER_XPATH =
            "//h2[contains(., 'Сведения о приглашенных участниках')][1]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о предметах предварительного отбора] -> [Предмет предварительного отбора №1]

    //------------------------------------------------------------------------------------------------------------------
    // Надпись [Предмет предварительного отбора №1]
    private static final String PRESELECTION_SUBJECT_LABEL_XPATH =
            "//h2[contains(., 'Предмет предварительного отбора №1')]";
    //------------------------------------------------------------------------------------------------------------------
    // Надпись [Статус предмета предварительного отбора №1]
    private static final String PRESELECTION_SUBJECT_STATE_XPATH =
            "//li[@class='lot-state-item current '][1]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Завершить досрочно]
    private static final String FINISH_ETERNAL_BUTTON_XPATH =
            "(//button[contains(.,'Завершить досрочно лот')])[1]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о протоколах]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о протоколах]
    private static final String ABOUT_PROTOCOLS_HEADER_XPATH =
            "//*[@class='ra-well-title' and contains(., 'Сведения о протоколах')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Открыть форму протокола открытия доступа]
    private static final String OPEN_TENDER_OPENING_PROTOCOL_BUTTON_XPATH =
            "//button[contains(., 'Открыть форму протокола открытия доступа')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Открыть форму протокола рассмотрения заявок]
    private static final String OPEN_TENDER_CONSIDERATION_PROTOCOL_BUTTON_XPATH =
            "//button[contains(., 'Открыть форму протокола рассмотрения заявок')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Открыть форму протокола рассмотрения и оценки заявок]
    private static final String OPEN_TENDER_CONSIDERATION_AND_EVALUATION_PROTOCOL_BUTTON_XPATH =
            "//button[contains(., 'Открыть форму протокола рассмотрения и оценки заявок')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Открыть форму протокола рассмотрения и оценки первых частей заявок]
    private static final String OPEN_TENDER_CONSIDERATION_AND_EVALUATION_FIRST_PART_PROTOCOL_BUTTON_XPATH =
            "//button[contains(., 'Открыть форму протокола рассмотрения и оценки первых частей заявок')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Открыть форму протокола рассмотрения и оценки вторых частей заявок]
    private static final String OPEN_TENDER_CONSIDERATION_AND_EVALUATION_SECOND_PART_PROTOCOL_BUTTON_XPATH =
            "//button[contains(., 'Открыть форму протокола рассмотрения и оценки вторых частей заявок')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Открыть форму протокола проведения переторжки]
    private static final String OPEN_REBIDDING_PROTOCOL_BUTTON_XPATH =
            "//button[contains(., 'Открыть форму протокола проведения переторжки')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Открыть форму протокола подведения итогов]
    private static final String OPEN_SUMMARY_PROTOCOL_BUTTON_XPATH =
            "//button[contains(., 'Открыть форму протокола подведения итогов')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Открыть форму протокола подачи дополнительных ценовых предложений]
    private static final String OPEN_ADDITIONAL_OFFERS_PROTOCOL_BUTTON_XPATH =
            "//button[contains(., 'Открыть форму протокола подачи дополнительных ценовых предложений')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Открыть форму протокола проведения аукциона]
    private static final String OPEN_AUCTION_PROTOCOL_BUTTON_XPATH =
            "//button[contains(., 'Открыть форму протокола проведения аукциона')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Открыть форму протокола оценки заявок]
    private static final String OPEN_TENDER_EVALUATION_PROTOCOL_BUTTON_XPATH =
            "//button[contains(., 'Открыть форму протокола оценки заявок')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Открыть форму уведомлений об итогах рассмотрения заявок](первая)
    private static final String OPEN_TENDER_NOTIFICATION_AND_CONSIDERATION_PROTOCOL_BUTTON_XPATH =
            "//button[contains(., 'Открыть форму уведомления об итогах рассмотрения заявок')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Открыть форму уведомлений об итогах рассмотрения заявок](вторая)
    private static final String OPEN_SECOND_PROTOCOL_OF_TENDER_NOTIFICATION_AND_CONSIDERATION_BUTTON_XPATH =
            "(//button[contains(., 'Открыть форму уведомления об итогах рассмотрения заявок')])[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Открыть форму уведомлений об итогах рассмотрения заявок](третья)
    private static final String OPEN_THIRD_PROTOCOL_OF_TENDER_NOTIFICATION_AND_CONSIDERATION_BUTTON_XPATH =
            "(//button[contains(., 'Открыть форму уведомления об итогах рассмотрения заявок')])[3]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Открыть форму уведомлений об итогах рассмотрения заявок](четвертая)
    private static final String OPEN_FOURTH_PROTOCOL_OF_TENDER_NOTIFICATION_AND_CONSIDERATION_BUTTON_XPATH =
            "(//button[contains(., 'Открыть форму уведомления об итогах рассмотрения заявок')])[4]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Документы]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Документы]
    private static final String DOCUMENTS_PARTITION_HEADER_XPATH =
            "//div[@class='js-accordion-header' and contains(., 'Документы')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка на первый прикрепленный документ в разделе [Документы]
    private static final String LINK_TO_FIRST_ATTACHED_DOCUMENT_FROM_DOCUMENTS_PARTITION_XPATH =
            "//*[@id='Files_trade']/table/tbody/tr[1]/td[1]/a";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Дополнительные поля лота]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Дополнительные поля лота]
    private static final String LOT_ADDITIONAL_FIELDS_HEADER_XPATH =
            "//div[@class='well well-window']/h2[contains(.,'Дополнительные поля лота')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Новое дополнительное поле лота]
    private static final String LOT_ADDITIONAL_FIELDS_XPATH =
            "//label[@class='control-label col-md-3' and contains(., 'Новое дополнительное поле лота:')]/following-sibling::div";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Дополнительные поля]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Дополнительные поля]
    private static final String ADDITIONAL_FIELDS_HEADER_XPATH =
            "//div[@class='well well-window nomargin-bottom']/h2[contains(.,'Дополнительные поля')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Новое дополнительное поле закупки]
    private static final String PURCHASE_ADDITIONAL_FIELDS_XPATH =
            "//label[@class='control-label col-md-3' and contains(., 'Новое дополнительное поле закупки:')]/following-sibling::div";
    //------------------------------------------------------------------------------------------------------------------


    // endregion

    // region Раздел [Согласование]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Согласование]
    private static final String APPROVAL_PARTITION_HEADER_ID = "approval";
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
    private static final String COMMISSION_PARTITION_HEADER_XPATH =
            "//*[@id='approval_tradeviewapprovalinfo']/div/h3[contains(.,'Комиссия')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Организация]
    private static final String TABLE_ORGANIZATION_COLUMN_XPATH =
            "//*[@id='approval_tradeviewapprovalinfo']//table/tbody/tr/td[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Ответственный]
    private static final String TABLE_RESPONSIBLE_COLUMN_XPATH =
            "//*[@id='approval_tradeviewapprovalinfo']//table/tbody/tr/td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Статус]
    private static final String TABLE_STATUS_COLUMN_XPATH =
            "//*[@id='approval_tradeviewapprovalinfo']//table/tbody/tr/td[3]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Решение принято]
    private static final String TABLE_DECISION_IS_MADE_COLUMN_XPATH =
            "//*[@id='approval_tradeviewapprovalinfo']//table/tbody/tr/td[4]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Комментарий]
    private static final String TABLE_COMMENT_COLUMN_XPATH =
            "//*[@id='approval_tradeviewapprovalinfo']//table/tbody/tr/td[5]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Документ]
    private static final String TABLE_DOCUMENT_COLUMN_XPATH =
            "//*[@id='approval_tradeviewapprovalinfo']//table/tbody/tr/td[6]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Запросы на разъяснение документации и ответы]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Запросы на разъяснение документации и ответы]
    private static final String CLARIFICATION_REQUEST_PARTITION_HEADER_XPATH =
            "//h2[@class='ra-well-title' and contains(., 'Запросы на разъяснение документации и ответы')]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица [Запросы на разъяснение документации и ответы] - столбец [Текст запроса]
    private static final String TABLE_CLARIFICATION_REQUEST_REQUESTS_TEXTS_XPATH =
            "//*[@id='ClarificationRequestGrid']/table//tr/td[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица [Запросы на разъяснение документации и ответы] - столбец [Текст ответа]
    private static final String TABLE_CLARIFICATION_REQUEST_RESPONCES_TEXTS_XPATH =
            "//*[@id='ClarificationRequestGrid']/table//tr/td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица [Запросы на разъяснение документации и ответы] - столбец [Дата запроса]
    private static final String TABLE_CLARIFICATION_REQUEST_REQUESTS_DATES_XPATH =
            "//*[@id='ClarificationRequestGrid']/table//tr/td[3]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица [Запросы на разъяснение документации и ответы] - столбец с кнопками [Просмотреть]
    private static final String TABLE_CLARIFICATION_REQUEST_VIEW_INQUIRIES_BUTTONS_XPATH =
            "//*[@id='ClarificationRequestGrid']/table//tr/td[4]/a";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Ответить]
    private static final String CLARIFICATION_REQUEST_RESPONSE_BUTTON_XPATH =
            "//a[contains(@href, '/customer/lk/RegisterOfInquiries/ViewInquiries/')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Управляющие кнопки в нижней части страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Согласовать]
    private static final String APPROVE_BUTTON_XPATH =
            "//*[@id='approval_tradeviewapprovalinfo']//input[@value='Согласовать']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отклонить]
    private static final String REJECT_BUTTON_XPATH =
            "//*[@id='approval_tradeviewapprovalinfo']//input[@value='Отклонить']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отменить согласование]
    private static final String REJECT_APPROVAL_BUTTON_XPATH =
            "//*[@id='approval_tradeviewapprovalinfo']//input[@value='Отменить согласование']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Опубликовать]
    private static final String PUBLISH_BUTTON_XPATH =
            "//*[@id='approval_tradeviewapprovalinfo']//input[@value='Опубликовать']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary partitionNames = new Dictionary();           // основные разделы извещения о закупке
    private final Dictionary fieldNames = new Dictionary();               // все поля на странице
    private final Dictionary buttonNames = new Dictionary();              // все кнопки на странице
    private final Dictionary commColumns = new Dictionary();              // все колонки таблицы в разделе [Комиссия]
    private final Dictionary purchaseContractsInfo = new Dictionary();    // колонки таблицы [Договоры закупки]
    private final Dictionary purchaseObjectsInfo = new Dictionary();      // колонки таблицы [Сведения об объекте(ах) закупки]
    private final Dictionary clarificationRequestsInfo = new Dictionary();// колонки таблицы [Запр. на разъясн. док. и ответы]

    // region Диалоговые окна и внешние страницы

    //------------------------------------------------------------------------------------------------------------------
    // Диалоговое окно [Публикация извещения]
    private final PublishDialog publishDialog = new PublishDialog(driver);
    //------------------------------------------------------------------------------------------------------------------
    // Диалоговое окно [Опубликовано]
    private final PublishedDialog publishedDialog = new PublishedDialog(driver);
    //------------------------------------------------------------------------------------------------------------------
    // Диалоговое окно [История]
    private final HistoryDialog historyDialog = new HistoryDialog(driver);
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public ViewNoticePage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        partitionNames.add("Сведения о лотах", LOTS_INFORMATION_LABEL_XPATH);
        partitionNames.add("Лот №1", LOT1_LABEL_XPATH);
        partitionNames.add("Лот №1 - Договоры закупки", LOT1_INFORMATION_ABOUT_CONTRACTS_OF_PURCHASE_HEADER_XPATH);
        partitionNames.add("Лот №1 - Сведения об объекте(ах) закупки",
                LOT1_INFORMATION_ABOUT_OBJECTS_OF_PURCHASE_HEADER_XPATH);
        partitionNames.add("Лот №1 - Сведения о приглашенных участниках",
                LOT1_INFORMATION_ABOUT_INVITED_PARTICIPANTS_HEADER_XPATH);
        partitionNames.add("Предмет предварительного отбора №1", PRESELECTION_SUBJECT_LABEL_XPATH);
        partitionNames.add("Сведения о протоколах", ABOUT_PROTOCOLS_HEADER_XPATH);
        partitionNames.add("Документы", DOCUMENTS_PARTITION_HEADER_XPATH);
        partitionNames.add("Дополнительные поля лота",LOT_ADDITIONAL_FIELDS_HEADER_XPATH);
        partitionNames.add("Дополнительные поля",ADDITIONAL_FIELDS_HEADER_XPATH);
        partitionNames.add("Согласование", APPROVAL_PARTITION_HEADER_ID);
        partitionNames.add("Комиссия", COMMISSION_PARTITION_HEADER_XPATH);
        partitionNames.add("Запросы на разъяснение документации и ответы", CLARIFICATION_REQUEST_PARTITION_HEADER_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Статус закупки", PURCHASE_STATUS_XPATH);
        fieldNames.add("Статус процедуры", PROCEDURE_STATUS_XPATH);
        fieldNames.add("Номер закупки", PURCHASE_NUMBER_XPATH);
        fieldNames.add("Номер закупки в ЕИС", EIS_NUMBER_XPATH);
        fieldNames.add("Номер процедуры в ЕИС", EIS_PROCEDURE_NUMBER_XPATH);
        fieldNames.add("Наименование закупки редактирование", PURCHASE_NAME_ID);
        fieldNames.add("Наименование закупки", PURCHASE_NAME_XPATH);
        fieldNames.add("Дата и время публикации извещения", PURCHASE_DATE_TIME_XPATH);
        fieldNames.add("Статус лота №1", LOT1_STATE_XPATH);
        fieldNames.add("Попозиционное сравнение", POSITIONAL_COMPARISON_XPATH);
        fieldNames.add("Возможность подачи заявки на часть позиций", POSSIBILITY_OF_APPLYING_FOR_SOME_POSITIONS_XPATH);
        fieldNames.add("Статус рассмотрения", APPROVAL_STATUS_XPATH);
        fieldNames.add("Тип согласования", APPROVAL_TYPE_XPATH);
        fieldNames.add("Заявка на согласование подана", DATE_OF_APPROVAL_REQUEST_XPATH);
        fieldNames.add("Дата согласования", DATE_OF_APPROVAL_PROCEDURE_XPATH);
        fieldNames.add("Дата принятия решения", DATE_OF_APPROVAL_XPATH);
        fieldNames.add("Новое дополнительное поле закупки", PURCHASE_ADDITIONAL_FIELDS_XPATH);
        fieldNames.add("Новое дополнительное поле лота", LOT_ADDITIONAL_FIELDS_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        commColumns.add("Организация", TABLE_ORGANIZATION_COLUMN_XPATH);
        commColumns.add("Ответственный", TABLE_RESPONSIBLE_COLUMN_XPATH);
        commColumns.add("Статус", TABLE_STATUS_COLUMN_XPATH);
        commColumns.add("Решение принято", TABLE_DECISION_IS_MADE_COLUMN_XPATH);
        commColumns.add("Комментарий", TABLE_COMMENT_COLUMN_XPATH);
        commColumns.add("Документ", TABLE_DOCUMENT_COLUMN_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        purchaseContractsInfo.add("Номер места участника",
                TABLE_PURCHASE_CONTRACTS_INFO_PARTICIPANT_PLACE_NUMBER_COLUMN_XPATH);
        purchaseContractsInfo.add("Номер заявки", TABLE_PURCHASE_CONTRACTS_APPLICATION_NUMBER_COLUMN_XPATH);
        purchaseContractsInfo.add("Наименование участника", TABLE_PURCHASE_CONTRACTS_PARTICIPANT_NAME_COLUMN_XPATH);
        purchaseContractsInfo.add("Заказчик", TABLE_PURCHASE_CONTRACTS_CUSTOMER_NAME_COLUMN_XPATH);
        purchaseContractsInfo.add("Статус договора", TABLE_PURCHASE_CONTRACTS_CONTRACT_STATUS_COLUMN_XPATH);
        purchaseContractsInfo.add("Действия", TABLE_PURCHASE_CONTRACTS_POSSIBLE_ACTIONS_COLUMN_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        purchaseObjectsInfo.add("Наименование", TABLE_PURCHASE_OBJECTS_INFO_NAME_COLUMN_XPATH);
        purchaseObjectsInfo.add("Стоимость позиции", TABLE_PURCHASE_OBJECTS_INFO_POSITION_VALUE_COLUMN_XPATH);
        purchaseObjectsInfo.add("ОКПД2", TABLE_PURCHASE_OBJECTS_INFO_OKPD2_COLUMN_XPATH);
        purchaseObjectsInfo.add("ОКВЭД2", TABLE_PURCHASE_OBJECTS_INFO_OKVED2_COLUMN_XPATH);
        purchaseObjectsInfo.add("Единицы измерения", TABLE_PURCHASE_OBJECTS_INFO_UNITS_COLUMN_XPATH);
        purchaseObjectsInfo.add("Количество", TABLE_PURCHASE_OBJECTS_INFO_AMOUNT_COLUMN_XPATH);
        purchaseObjectsInfo.add("Цена за единицу продукции", TABLE_PURCHASE_OBJECTS_INFO_UNIT_PRICE_COLUMN_XPATH);
        purchaseObjectsInfo.add("Дополнительные сведения", TABLE_PURCHASE_OBJECTS_INFO_ADDITIONAL_INFO_COLUMN_XPATH);
        purchaseObjectsInfo.add("Дополнительные поля", TABLE_PURCHASE_OBJECTS_INFO_ADDITIONAL_FIELDS_COLUMN_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        clarificationRequestsInfo.add("Текст запроса", TABLE_CLARIFICATION_REQUEST_REQUESTS_TEXTS_XPATH);
        clarificationRequestsInfo.add("Текст ответа", TABLE_CLARIFICATION_REQUEST_RESPONCES_TEXTS_XPATH);
        clarificationRequestsInfo.add("Дата запроса", TABLE_CLARIFICATION_REQUEST_REQUESTS_DATES_XPATH);
        clarificationRequestsInfo.add("Просмотреть", TABLE_CLARIFICATION_REQUEST_VIEW_INQUIRIES_BUTTONS_XPATH);
        clarificationRequestsInfo.add("Ответить", CLARIFICATION_REQUEST_RESPONSE_BUTTON_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Завершить досрочно лот 1", FINISH_ETERNAL_BUTTON_XPATH);
        buttonNames.add("Открыть форму протокола открытия доступа", OPEN_TENDER_OPENING_PROTOCOL_BUTTON_XPATH);
        buttonNames.add("Открыть форму протокола рассмотрения заявок", OPEN_TENDER_CONSIDERATION_PROTOCOL_BUTTON_XPATH);
        buttonNames.add("Открыть форму протокола рассмотрения и оценки заявок",
                OPEN_TENDER_CONSIDERATION_AND_EVALUATION_PROTOCOL_BUTTON_XPATH);
        buttonNames.add("Открыть форму протокола рассмотрения и оценки первых частей заявок",
                OPEN_TENDER_CONSIDERATION_AND_EVALUATION_FIRST_PART_PROTOCOL_BUTTON_XPATH);
        buttonNames.add("Открыть форму протокола рассмотрения и оценки вторых частей заявок",
                OPEN_TENDER_CONSIDERATION_AND_EVALUATION_SECOND_PART_PROTOCOL_BUTTON_XPATH);
        buttonNames.add("Открыть форму протокола проведения переторжки", OPEN_REBIDDING_PROTOCOL_BUTTON_XPATH);
        buttonNames.add("Открыть форму протокола подачи дополнительных ценовых предложений",
                OPEN_ADDITIONAL_OFFERS_PROTOCOL_BUTTON_XPATH);
        buttonNames.add("Открыть форму протокола подведения итогов", OPEN_SUMMARY_PROTOCOL_BUTTON_XPATH);
        buttonNames.add("Открыть форму протокола проведения аукциона", OPEN_AUCTION_PROTOCOL_BUTTON_XPATH);
        buttonNames.add("Открыть форму протокола оценки заявок", OPEN_TENDER_EVALUATION_PROTOCOL_BUTTON_XPATH);
        buttonNames.add("Открыть форму уведомлений об итогах рассмотрения заявок",
                OPEN_TENDER_NOTIFICATION_AND_CONSIDERATION_PROTOCOL_BUTTON_XPATH);
        buttonNames.add("Открыть вторую форму уведомлений об итогах рассмотрения заявок",
                OPEN_SECOND_PROTOCOL_OF_TENDER_NOTIFICATION_AND_CONSIDERATION_BUTTON_XPATH);
        buttonNames.add("Открыть третью форму уведомлений об итогах рассмотрения заявок",
                OPEN_THIRD_PROTOCOL_OF_TENDER_NOTIFICATION_AND_CONSIDERATION_BUTTON_XPATH);
        buttonNames.add("Открыть четвертую форму уведомлений об итогах рассмотрения заявок",
                OPEN_FOURTH_PROTOCOL_OF_TENDER_NOTIFICATION_AND_CONSIDERATION_BUTTON_XPATH);
        buttonNames.add("История", HISTORY_BUTTON_XPATH);
        buttonNames.add("Опубликовать разъяснение", PUBLISH_EXPLANATION_XPATH);
        buttonNames.add("Отправить в ЕИС", PUBLISH_TO_OOS_BUTTON_XPATH);
        buttonNames.add("Отказ от проведения", TRADE_CANCEL_BUTTON_XPATH);
        buttonNames.add("Изменить переторжку", CHANGE_RETRADING_BUTTON_XPATH);
        buttonNames.add("Согласовать", APPROVE_BUTTON_XPATH);
        buttonNames.add("Отклонить", REJECT_BUTTON_XPATH);
        buttonNames.add("Отменить согласование", REJECT_APPROVAL_BUTTON_XPATH);
        buttonNames.add("Опубликовать", PUBLISH_BUTTON_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы
     ******************************************************************************************************************/
    // region Заголовок страницы

    /**
     * Проверяет наличие ссылки [публикации изменений извещения]
     * в надписи [Получен проект внесения изменений в извещение о проведении закупки.
     * Перейти к публикации изменений извещения.].
     */
    public void checkCustomerTradeEditLinkPresence() {
        System.out.print("[ИНФОРМАЦИЯ]: проверяем наличие надписи: [Получен проект внесения изменений в извещение...]");
        $(By.xpath(CUSTOMER_TRADE_EDIT_LINK_XPATH)).waitUntil(exist, timeout, polling).shouldBe(visible);
        System.out.println(" - OK.");
    }

    /**
     * Осуществляет переход по ссылке [публикации изменений извещения]
     * в надписи [Получен проект внесения изменений в извещение о проведении закупки.
     * Перейти к публикации изменений извещения.].
     */
    public void clickOnCustomerTradeEditLink() throws Exception {
        System.out.println(">>> (clickOnCustomerTradeEditLink) Переходим по ссылке [публикации изменений извещения].");
        SelenideElement reference = $(By.xpath(CUSTOMER_TRADE_EDIT_LINK_XPATH)).waitUntil(visible, timeout, polling);
        this.scrollToCenter(reference);
        this.clickInElementJS(reference);
        System.out.println(">>> (clickOnCustomerTradeEditLink) Переход выполнен успешно.");
    }

    /**
     * Проверяет наличие записей в диалоговом окне [История].
     *
     * @param actionName текст действия
     */
    void checkHistoryRecordsPresence(String actionName) throws Exception {
        SelenideElement historyButton = $(this.getBy(HISTORY_BUTTON_XPATH));
        this.scrollToCenter(historyButton);
        historyButton.waitUntil(clickable, timeout, polling);
        this.logButtonNameToPress("История");
        this.clickInElementJS(historyButton);
        this.logPressedButtonName("История");
        historyDialog.ifDialogOpened().checkHistoryIsNotEmpty().checkEventByActionName(actionName).closeThisWindow();
    }

    // endregion

    // region Раздел [Общие сведения о закупке]

    /**
     * Проверяет статус закупки и сохраняет номер извещения о полном конкурсе.
     */
    public void checkThatPurchaseStatusIsPublished() throws Exception {
        this.waitForTextInField("Опубликована", By.xpath(PURCHASE_STATUS_XPATH), "Статус закупки");
    }

    /**
     * Проверяет статус закупки и сохраняет номер извещения о закупке.
     *
     * @param purchaseStatus ожидаемый статус закупки
     */
    void checkPurchaseStatusAndSavePurchaseNumber(String purchaseStatus) throws Exception {
        // Проверяем значение статуса
        this.waitForTextInField(purchaseStatus, By.xpath(PURCHASE_STATUS_XPATH), "Статус закупки");

        // Получаем номер извещения и сохр. его как параметр теста [PurchaseNumber] для использования в следующих шагах
        this.savePurchaseNoticeNumberFromNoticeView();
    }

    /**
     * Проверяет статус процедуры и сохраняет номер процедуры.
     *
     * @param purchaseStatus ожидаемый статус процедуры
     */
    void checkProcedureStatusAndSavePurchaseNumber(String purchaseStatus) throws Exception {
        // Проверяем значение статуса
        this.waitForTextInField(purchaseStatus, By.xpath(PROCEDURE_STATUS_XPATH), "Статус процедуры");

        // Получаем номер извещения и сохр. его как параметр теста [PurchaseNumber] для использования в следующих шагах
        this.saveProcedureNoticeNumberFromNoticeView();
    }

    /**
     * Сохраняет номер извещения (режим просмотра извещения).
     */
    public void savePurchaseNoticeNumberFromNoticeView() {
        $(By.xpath(PURCHASE_NUMBER_XPATH)).waitUntil(visible, timeout, polling).click();
        String noticeNumber = $(By.xpath(PURCHASE_NUMBER_XPATH)).getText().trim();
        System.out.printf("[ИНФОРМАЦИЯ]: сохраняем в параметрах теста номер извещения о закупке: [%s].%n", noticeNumber);
        config.setParameter("PurchaseNumber", noticeNumber);
    }

    /**
     * Сохраняет номер процедуры (режим просмотра извещения).
     */
    public void saveProcedureNoticeNumberFromNoticeView() {
        $(By.xpath(PROCEDURE_NUMBER_XPATH)).waitUntil(visible, timeout, polling).click();
        String noticeNumber = $(By.xpath(PROCEDURE_NUMBER_XPATH)).getText().trim();
        System.out.printf("[ИНФОРМАЦИЯ]: сохраняем в параметрах теста номер процедуры: [%s].%n", noticeNumber);
        config.setParameter("PurchaseNumber", noticeNumber);
    }

    /**
     * Проверяет номер извещения о полном конкурсе.
     */
    public void checkPurchaseNumber() {
        String purchaseNoticeId = $(By.xpath(PURCHASE_NUMBER_XPATH)).waitUntil(visible, timeout, polling).getText();
        System.out.printf(">>> (checkPurchaseNumber) Номер закупки: [%s].%n", purchaseNoticeId);
        Assert.assertEquals(">>> (checkPurchaseNumber) Номер закупки отличается от ожидаемого !",
                config.getParameter("PurchaseNumber"), purchaseNoticeId);
    }

    /**
     * Проверяет наименование закупки.
     *
     * @param mode текущий режим отображения страницы [Просмотр извещения]
     */
    public void checkPurchaseName(String mode) {
        String purchaseName;
        if (mode.equals("Edit"))
            purchaseName = $(By.id(PURCHASE_NAME_ID)).waitUntil(visible, timeout, polling).getText();
        else
            purchaseName = $(By.xpath(PURCHASE_NAME_XPATH)).waitUntil(visible, timeout, polling).getText();
        Assert.assertFalse(">>> (checkPurchaseName) Пустое наименование закупки в сохраненном извещении !",
                purchaseName == null || purchaseName.trim().isEmpty());
        System.out.printf(">>> (checkPurchaseName) Наименование закупки: [%s].%n", purchaseName);
        Assert.assertEquals("Наименование закупки отличается от ожидаемого !",
                config.getParameter("PurchaseName"), purchaseName);
    }

    /**
     * Проверяет наименование закупки и сохраняет его в параметрах текущего теста.
     *
     * @param mode текущий режим отображения страницы [Просмотр извещения]
     */
    void checkAndSavePurchaseName(String mode) {
        String purchaseName;
        if (mode.equals("Edit"))
            purchaseName = $(By.id(PURCHASE_NAME_ID)).waitUntil(visible, timeout, polling).getText();
        else
            purchaseName = $(By.xpath(PURCHASE_NAME_XPATH)).waitUntil(visible, timeout, polling).getText();
        Assert.assertFalse(
                ">>> (checkAndSavePurchaseName) Пустое наименование закупки в сохраненном извещении !",
                purchaseName == null || purchaseName.trim().isEmpty());
        config.setParameter("PurchaseName", purchaseName);
        System.out.printf(">>> (checkAndSavePurchaseName) Наименование закупки: [%s].%n", purchaseName);
    }

    /**
     * Проверяет наименование процедуры и сохраняет его в параметрах текущего теста.
     */
    void checkAndSaveProcedureName() {
        String procedureName = $(By.xpath(PROCEDURE_NAME_XPATH)).waitUntil(visible, timeout, polling).getText();
        Assert.assertFalse(
                ">>> (checkAndSavePurchaseName) Пустое наименование процедуры в сохраненном извещении !",
                procedureName == null || procedureName.trim().isEmpty());
        config.setParameter("PurchaseName", procedureName);
        System.out.printf(">>> (checkAndSavePurchaseName) Наименование процедуры: [%s].%n", procedureName);
    }

    /**
     * Сохраняет дату и время публикации извещения о закупке.
     */
    void savePublishNoticeDateAndTime() {
        $(By.xpath(PURCHASE_DATE_TIME_XPATH)).waitUntil(visible, timeout, polling).click();
        String publishNoticeDateAndTime = $(By.xpath(PURCHASE_DATE_TIME_XPATH)).getText().
                trim().replace(" (МСК)", "");
        System.out.printf("[ИНФОРМАЦИЯ]: получены дата и время публикации извещения о закупке: [%s].%n",
                publishNoticeDateAndTime);
        config.setParameter("PublishNoticeDateAndTime", publishNoticeDateAndTime);
    }

    // endregion

    // region Раздел [Сведения о лотах] -> [Лот №1]

    /**
     * Ожидает требуемого статуса для лота № 1.
     *
     * @param expectedStatus требуемый статус для лота № 1
     */
    void waitLot1Status(String expectedStatus) throws Exception {
        int waitTimeMinutes = Integer.parseInt(config.getConfigParameter("MaxStatusWaitTime"));
        int nTries = waitTimeMinutes * 60 / 10;
        int i = 0;
        String currentStatus;
        SelenideElement lotLabel = $(By.xpath(LOT1_LABEL_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: установленное максимальное время ожидания смены статуса лота %d минут.%n",
                waitTimeMinutes);
        System.out.printf("[ИНФОРМАЦИЯ]: ждем, пока лот №1 перейдет в статус [%s].%n", expectedStatus);

        do {
            this.refreshPage();
            this.scrollToCenter(lotLabel);
            lotLabel.waitUntil(clickable, timeout, polling);
            this.clickInElementJS(lotLabel);
            TimeUnit.SECONDS.sleep(1);
            currentStatus = $(By.xpath(LOT1_STATE_XPATH)).waitUntil(visible, timeout, polling).getText();
            i++;
            System.out.printf(
                    "[ИНФОРМАЦИЯ]: проверка №%d значения в поле [Статус лота №1]: ожидаемое [%s], реальное [%s].%n",
                    i, expectedStatus, currentStatus);
        } while (i <= nTries && !currentStatus.equals(expectedStatus));
    }

    // region Таблица [Договоры закупки]

    /**
     * Проверяет текст ячейки в таблице 'Договоры закупки' для столбца с указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     */
    public void verifyCellByColumnNameAndLineNumberFromContractsInfoTableForText(
            String columnName, String rowNumber, String cellValue) {
        this.verifyCellInTableByColumnElementsAndLineNumberForText("Договоры закупки",
                columnName, $$(this.getBy(purchaseContractsInfo.find(columnName))), rowNumber, cellValue);
    }

    /**
     * Переходит по ссылке с указанным номером 'Создать договор на ЭП' в таблице 'Договоры закупки' столбец 'Действия'.
     *
     * @param rowNumber порядковый номер ссылки в таблице (сверху-вниз)
     */
    public void
    goToCreateContractOnElectronicTenderSiteLinkFromPurchaseContractsTable(String rowNumber) throws Exception {
        System.out.printf(
                "[ИНФОРМАЦИЯ]: переходим по ссылке [Создать договор на ЭП] в таблице [Договоры закупки] столбец " +
                        "[Действия] порядковый номер ссылки [%s].%n", rowNumber);

        ElementsCollection links =
                $$(this.getBy(TABLE_PURCHASE_CONTRACTS_POSSIBLE_ACTIONS_COLUMN_CREATE_CONTRACT_LINKS_XPATH));
        int totalLinks = links.size();
        Assert.assertTrue("[ОШИБКА]: таблица [Договоры закупки] не содержит ссылок", totalLinks > 0);

        int line = Integer.parseInt(rowNumber) - 1;
        Assert.assertTrue(String.format("[ОШИБКА]: передан некорректный порядковый номер ссылки: [%s]", rowNumber),
                line >= 0 && line < totalLinks);

        TimeUnit.SECONDS.sleep(5);
        links.get(line).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
    }

    // endregion

    // region Таблица [Сведения об объекте(ах) закупки

    /**
     * Проверяет текст ячейки в таблице 'Сведения об объекте(ах) закупки' для столбца с указанным именем и номером
     * строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     */
    public void verifyCellByColumnNameAndLineNumberFromPurchaseObjectsTableForText(
            String columnName, String rowNumber, String cellValue) {
        this.verifyCellInTableByColumnElementsAndLineNumberForText("Сведения об объекте(ах) закупки",
                columnName, $$(this.getBy(purchaseObjectsInfo.find(columnName))), rowNumber, cellValue);
    }

    // endregion

    // endregion

    // region Раздел [Сведения о предметах предварительного отбора] -> [Предмет предварительного отбора №1]

    /**
     * Ожидает требуемого статуса для предмета предварительного отбора № 1.
     *
     * @param expectedStatus требуемый статус для предмета предварительного отбора № 1
     */
    void waitPreselectionSubject1Status(String expectedStatus) throws Exception {
        int waitTimeMinutes = Integer.parseInt(config.getConfigParameter("MaxStatusWaitTime"));
        int nTries = waitTimeMinutes * 60 / 10;
        int i = 0;
        String currentStatus;
        SelenideElement lotLabel = $(By.xpath(PRESELECTION_SUBJECT_LABEL_XPATH));
        System.out.printf(
                "[ИНФОРМАЦИЯ]: установленное максимальное время ожидания смены статуса предмета предварительного отбора %d минут.%n",
                waitTimeMinutes);
        System.out.printf("[ИНФОРМАЦИЯ]: ждем, пока предмет предварительного отбора №1 перейдет в статус [%s].%n",
                expectedStatus);

        do {
            this.refreshPage();
            this.scrollToCenter(lotLabel);
            lotLabel.waitUntil(clickable, timeout, polling);
            this.clickInElementJS(lotLabel);
            TimeUnit.SECONDS.sleep(1);
            currentStatus = $(By.xpath(PRESELECTION_SUBJECT_STATE_XPATH)).waitUntil(visible, timeout, polling).getText();
            i++;
            System.out.printf(
                    "[ИНФОРМАЦИЯ]: проверка №%d значения в поле [Статус предмета предварительного отбора №1]: ожидаемое [%s], реальное [%s].%n",
                    i, expectedStatus, currentStatus);
        } while (i <= nTries && !currentStatus.equals(expectedStatus));
    }

    // region Таблица [Сведения о заказчиках, подписывающих договоры]

    // endregion

    // region Таблица [Сведения об объектах предварительного отбора]

    // endregion

    // endregion

    // region Раздел [Документы]

    /**
     * Перемещает страницу к заголовку раздела [Документы].
     */
    public void scrollToDocumentsPartition() {
        $(By.xpath(DOCUMENTS_PARTITION_HEADER_XPATH)).waitUntil(visible, timeout, polling).scrollTo();
    }

    /**
     * Проверяет, что к извещению прикреплен как минимум один документ с любым именем.
     */
    public void checkThatFirstAttachedDocumentLinkExists() {
        $(By.xpath(LINK_TO_FIRST_ATTACHED_DOCUMENT_FROM_DOCUMENTS_PARTITION_XPATH)).waitUntil(visible, timeout, polling);
        $(By.xpath(LINK_TO_FIRST_ATTACHED_DOCUMENT_FROM_DOCUMENTS_PARTITION_XPATH)).shouldBe(visible, enabled);
    }

    /**
     * Делает клик по ссылке на первый прикрепленный к извещению документ, загружает его и проверяет,
     * что файл загружен успешно.
     */
    public void downloadFirstAttachedDocumentWithVerification() throws Exception {
        this.downloadFileByLink(By.xpath(LINK_TO_FIRST_ATTACHED_DOCUMENT_FROM_DOCUMENTS_PARTITION_XPATH));
    }

    // endregion

    // region Раздел [Комиссия]

    /**
     * Проверяет текст ячейки в таблице 'Комиссия' для столбца с указанным именем и номером строки.
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

    // endregion

    // region Раздел [Запросы на разъяснение документации и ответы]

    /**
     * Проверяет текст ячейки в таблице 'Запросы на разъяснение документации и ответы' для столбца с указанным именем
     * и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     */
    public void verifyCellByColumnNameAndLineNumberFromClarificationRequestsTableForText(
            String columnName, String rowNumber, String cellValue) {
        this.verifyCellInTableByColumnElementsAndLineNumberForText(
                "Запросы на разъяснение документации и ответы", columnName,
                $$(this.getBy(clarificationRequestsInfo.find(columnName))), rowNumber, cellValue);
    }

    /**
     * Проверяет наличие кнопки в таблице 'Запросы на разъяснение документации и ответы' для столбца
     * с указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param lineNumber порядковый номер строки в таблице
     */
    public void checkButtonPresenceInLineByNumberFromClarificationRequestsTable(String columnName, int lineNumber) {
        int line = lineNumber - 1;
        ElementsCollection linesWithButtons = $$(this.getBy(clarificationRequestsInfo.find(columnName)));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] строк с кнопкой [%s].%n", linesWithButtons.size(), columnName);
        Assert.assertTrue(String.format("[ОШИБКА]: передан некорректный номер строки: [%d]", lineNumber),
                line >= 0 && line < linesWithButtons.size());
        this.scrollToCenter(linesWithButtons.get(line));
        linesWithButtons.get(line).waitUntil(visible, timeout, polling).shouldBe(clickable);
    }

    /**
     * Нажимает на кнопку в таблице 'Запросы на разъяснение документации и ответы' для столбца
     * с указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param lineNumber порядковый номер строки в таблице
     */
    public void clickOnButtonInLineByNumberFromClarificationRequestsTable(String columnName, int lineNumber)
            throws Exception {
        int line = lineNumber - 1;
        ElementsCollection linesWithButtons = $$(this.getBy(clarificationRequestsInfo.find(columnName)));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] строк с кнопкой [%s].%n", linesWithButtons.size(), columnName);
        Assert.assertTrue(String.format("[ОШИБКА]: передан некорректный номер строки: [%d]", lineNumber),
                line >= 0 && line < linesWithButtons.size());
        this.scrollToCenter(linesWithButtons.get(line));
        linesWithButtons.get(line).waitUntil(visible, timeout, polling).shouldBe(clickable);
        this.clickInElementJS(linesWithButtons.get(line));
        this.logPressedButtonName(columnName);
    }

    // endregion

    // region Управляющие кнопки в нижней части страницы

    /**
     * Публикует извещение со страницы [Просмотр извещения XXXX].
     *
     * @param lotStatus ожидаемый статус лота после публикации извещения
     */
    public void publishPurchaseNotice(String lotStatus) throws Exception {
        // Нажимаем на кнопку "Опубликовать"
        SelenideElement publishButton = $(By.xpath(PUBLISH_BUTTON_XPATH));
        publishButton.waitUntil(visible, longtime, polling).scrollTo();
        this.logButtonNameToPress("Опубликовать");
        publishButton.click();
        this.logPressedButtonName("Опубликовать");

        // Нажимаем на кнопку "Продолжить" в диалоговом окне "Публикация извещения"
        publishDialog.continueThePublication();

        // Нажимаем на кнопку "Перейти к карточке закупки" в диалоговом окне "Опубликовано"
        publishedDialog.goToPurchaseCard();

        // Проверяем статус закупки "Опубликована" и сохраняем номер извещения
        this.checkPurchaseStatusAndSavePurchaseNumber("Опубликована");

        // Проверяем наименование закупки и сохраняем его в параметрах текущего теста
        this.checkAndSavePurchaseName("View");

        // Проверяем наличие записей в диалоговом окне "История"
        this.checkHistoryRecordsPresence("Публикация процедуры");

        // Ждем пока лот №1 перейдёт в ожидаемый статус
        this.waitLot1Status(lotStatus);
    }

    // endregion

    // region Общие методы для работы с элементами этой страницы

    /**
     * Открывает свернутый раздел на форме.
     *
     * @param partitionName имя раздела
     */
    public void openPartition(String partitionName) throws Exception {
        SelenideElement partition = $(this.getBy(partitionNames.find(partitionName)));
        this.scrollToCenter(partition);
        partition.shouldBe(visible).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено открытие раздела: [%s].%n", partitionName);
        TimeUnit.SECONDS.sleep(3);
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
     * Проверяет наличие указанной кнопки.
     *
     * @param buttonName имя кнопки
     */
    public void checkButtonExistAndClickable(String buttonName) {
        SelenideElement button = $(this.getBy(buttonNames.find(buttonName)));
        this.scrollToCenter(button);
        button.waitUntil(clickable, timeout, polling).shouldBe(enabled);
        System.out.printf("[ИНФОРМАЦИЯ]: произведена проверка доступности кнопки [%s].%n", buttonName);
    }

    /**
     * Делает щелчок мышью по указанной кнопке.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.scrollToCenterAndclickInElementJS(By.xpath(buttonNames.find(buttonName)));
        this.logPressedButtonName(buttonName);
        TimeUnit.SECONDS.sleep(3);
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

    // endregion
}
