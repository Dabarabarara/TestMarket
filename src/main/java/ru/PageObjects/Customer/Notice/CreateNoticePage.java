package ru.PageObjects.Customer.Notice;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonDialogs.SavedAngularDialog;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Класс для работы со страницей [Формирование черновика извещения ...]
 * (Главная / Заказчикам / Мои закупки / Формирование черновика извещения ...).
 * Created by Kirill G. Rydzyvylo on 10.02.2020.
 * Updated by Vladimir V. Klochkov on 07.07.2021.
 */
public class CreateNoticePage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Раздел [Общие сведения о закупке]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Общие сведения о закупке]
    private static final String COMMON_INFO_BLOCK_XPATH =
            "//app-form-panel[@label='Общие сведения о закупке']//div[contains(@class,'panel-label')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки]
    private static final String PURCHASE_NUMBER_XPATH =
            "//app-form-group[@label='Номер закупки']//div[@class='ng-form-group__controls']" +
                    "//*[local-name()='textarea' or local-name()='div' and not(@_ngcontent-six-c3)]";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Способ закупки]
    private static final String PURCHASE_METHOD_XPATH =
            "//app-form-group[@label='Способ закупки']//kendo-dropdownlist//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Способ определения поставщика (подрядчика, исполнителя)]
    private static final String DETERMINING_THE_SUPPLIER_METHOD_XPATH =
            "//app-form-group[@label='Способ определения поставщика (подрядчика, исполнителя)']" +
                    "//kendo-dropdownlist//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип закупки]
    private static final String PURCHASE_TYPE_XPATH =
            "//app-form-group[@label='Тип закупки']//div[@class='ng-form-group__controls']/div";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование закупки]
    private static final String ORDER_NAME_XPATH = "//app-form-group[@label='Наименование закупки']//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование закупки]
    private static final String PROCEDURE_NAME_XPATH = "//app-form-group[@label='Наименование процедуры']//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование закупки] для получения информации, что оно не пустое
    private static final String ORDER_NAME_GET_TEXT_ID =
            "CommonInfo_OrderName";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Описание закупки]
    private static final String DESCRIPTION_XPATH = "//app-form-group[@label='Описание закупки']//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Многолотовая закупка]
    private static final String IS_MULTI_LOTS_PURCHASE_XPATH = "//app-form-group[@label='Многолотовая закупка']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Независимая публикация протоколов по лотам]
    private static final String IS_INDEPENDENT_PROTOCOL_PUBLICATION_XPATH =
            "//app-form-group[@label='Независимая публикация протоколов по лотам']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Закупка с возможностью приема заявок без ЭЦП]
    private static final String IS_DIGITAL_SIGN_NOT_REQUIRED_XPATH =
            "//app-form-group[@label='Возможность участия в процедуре без ЭЦП']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Возможно уточнение заявок]
    private static final String IS_IMPLICIT_RETRADING_XPATH =
            "//app-form-group[@label='Возможно уточнение заявок']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Публикация протокола открытия доступа]
    private static final String IS_OPENING_PROTOCOL_ENABLED_XPATH =
            "//app-form-group[@label='Публикация протокола открытия доступа']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Возможно продление сроков закупки]
    private static final String IS_AUTO_PROLONGATION_DATES_XPATH =
            "//app-form-group[@label='Возможно продление сроков закупки']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Отдельные протоколы рассмотрения и оценки заявок]
    private static final String IS_SEPARATED_CONS_PROTOCOL_ENABLED_XPATH =
            "//app-form-group[@label='Отдельные протоколы рассмотрения и оценки заявок']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Публикация итогового протокола]
    private static final String IS_RESULT_PROTOCOL_ENABLED_XPATH =
            "//app-form-group[@label='Публикация итогового протокола']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Отображать цены поступивших предложений в открытом доступе]
    private static final String PUBLIC_APPLICATION_QUOTATIONS_XPATH =
            "//app-form-group[@label='Отображать цены поступивших предложений в открытом доступе']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Закупка с проведением предварительного этапа]
    private static final String IS_MULTI_STAGE_XPATH =
            "//app-form-group[@label='Закупка с проведением предварительного этапа']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Возможна подача альтернативного предложения в заявке]
    private static final String IS_ALTERNATIVE_PROPOSAL_AVAILABLE_XPATH =
            "//app-form-group[@label='Возможна подача альтернативного предложения в заявке']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Получить доступ к заявкам до окончания срока их приема]
    private static final String ALLOW_FILLING_APPLICATION_ACCESS_XPATH =
            "//app-form-group[@label='Получить доступ к заявкам до окончания срока их приема']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Закупка проводится вследствие аварии, чрезвычайной ситуации]
    private static final String IS_EMERGENCY_XPATH =
            "//app-form-group[@label='Закупка проводится вследствие аварии, чрезвычайной ситуации']//input";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о порядке подачи заявок]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о порядке подачи заявок]
    private static final String APPLICATION_DATES_BLOCK_XPATH =
            "//app-form-panel[@label='Сведения о порядке подачи заявок']//div[contains(@class,'panel-label')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата и время начала подачи заявок (МСК)]
    private static final String APPLICATION_START_DATE_XPATH =
            "//app-form-group[@labeltimezone='Дата и время начала подачи заявок']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата и время окончания подачи заявок (МСК)]
    private static final String APPLICATION_END_DATE_XPATH =
            "//app-form-group[@labeltimezone='Дата и время окончания подачи заявок']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата и время последнего окончания подачи заявок (МСК)]
    private static final String APPLICATION_LAST_END_DATE_XPATH =
            "//app-form-group[@labeltimezone='Дата и время последнего окончания подачи заявок']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата и время открытия доступа (МСК)]
    private static final String CONSIDERATION_START_DATE_XPATH =
            "//app-form-group[@labeltimezone='Дата и время открытия доступа']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Место подачи заявок]
    private static final String APPLICATION_REQUEST_PLACE_XPATH = "//app-form-group[@label='Место подачи заявок']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Порядок подачи заявок]
    private static final String APPLICATION_REQUEST_ORDER_XPATH =
            "//app-form-group[@label='Порядок подачи заявок']//input";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о порядке работы комиссии]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о порядке работы комиссии]
    private static final String COMMISSION_INFO_BLOCK_XPATH =
            "//app-form-panel[@label='Сведения о порядке работы комиссии']//div[contains(@class,'panel-label')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата и время рассмотрения заявок (МСК)]
    private static final String CONSIDERATION_DATE_ID =
            "//*[starts-with(@id,'TradeStage') and contains(@id,'ConsiderationDate')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Место рассмотрения заявок]
    private static final String CONSIDERATION_PLACE_XPATH =
            "//*[starts-with(@id,'TradeStage') and contains(@id,'ConsiderationPlace')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Порядок рассмотрения заявок]
    private static final String CONSIDERATION_ORDER_XPATH =
            "//app-form-group[@label='Порядок рассмотрения заявок']//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата и время проведения аукциона (МСК)]
    private static final String SINGLE_AUCTION_DATE_XPATH =
            "//app-form-group[@labeltimezone='Дата и время проведения аукциона']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата и время подведения итогов (МСК)]
    private static final String RESULTS_REVIEW_DATE_ID = "//*[starts-with(@id,'TradeStage') and contains(@id,'ResultsReviewDate')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Место подведения итогов]
    private static final String RESULTS_REVIEW_PLACE_XPATH =
            "//app-form-group[@label='Место подведения итогов']//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Порядок подведения итогов]
    private static final String RESULTS_REVIEW_ORDER_XPATH =
            "//app-form-group[@label='Порядок подведения итогов']//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Регламентный срок заключения договора]
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Субполе [От]
    private static final String CONTRACT_CONCLUSION_DAYS_FROM_XPATH = "//kendo-numerictextbox[@placeholder='От']//input";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Субполе [До]
    private static final String CONTRACT_CONCLUSION_DAYS_TO_XPATH = "//kendo-numerictextbox[@placeholder='До']//input";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [рабочих]
    private static final String CONTRACT_CONCLUSION_DAYS_WORKING_DAYS1_ID = "ContractConclusionDays0";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [календарных]
    private static final String CONTRACT_CONCLUSION_DAYS_WORKING_DAYS2_ID = "ContractConclusionDays1";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о предоставлении документации]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о предоставлении документации]
    private static final String DOCUMENTATION_BLOCK_XPATH =
            "//app-form-panel[@label='Сведения о предоставлении документации']//div[contains(@class,'panel-label')]";
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок предоставления документации (МСК)]
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Субполе [От]
    private static final String DOCUMENTATION_DATE_FROM_XPATH = "//app-date-picker[@placeholder='С']//input";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Субполе [До]
    private static final String DOCUMENTATION_DATE_TO_XPATH = "//app-date-picker[@placeholder='По']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Место предоставления документации]
    private static final String DOCUMENTATION_PLACE_XPATH =
            "//app-form-group[@label='Место предоставления документации']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Порядок предоставления документации]
    private static final String DOCUMENTATION_VIEW_PROCEDURE_XPATH =
            "//app-form-group[@label='Порядок предоставления документации']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Установлена плата за предоставление документации]
    private static final String SHOULD_HAVE_DOCUMENTATION_FREE_XPATH =
            "//app-form-group[@label='Установлена плата за предоставление документации']//input";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о порядке предоставления разъяснений]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о порядке предоставления разъяснений]
    private static final String CLARIFICATION_INFO_BLOCK_XPATH =
            "//app-form-panel[@label='Сведения о порядке предоставления разъяснений']//div[contains(@class,'panel-label')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок направления запроса о разъяснении документации (дней до окончания подачи заявок)]
    private static final String CLARIFICATION_REQUEST_LIMIT_DAYS_XPATH =
            "//app-form-group[@label='Срок направления запроса о разъяснении документации (дней до окончания подачи заявок)']//input";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [рабочих]
    private static final String CLARIFICATION_REQUEST_LIMIT_WORKING_DAYS_FROM_ID =
            "ClarificationRequestLimitDays0";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [календарных]
    private static final String CLARIFICATION_REQUEST_LIMIT_WORKING_DAYS_TO_ID =
            "ClarificationRequestLimitDays1";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок предоставления разъяснения документации]
    private static final String CLARIFICATION_RESPONCE_LIMIT_DAYS_XPATH =
            "//app-form-group[@label='Срок предоставления разъяснения документации']//input";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [рабочих]
    private static final String CLARIFICATION_RESPONCE_LIMIT_WORKING_DAYS_FROM_ID =
            "ClarificationResponseLimitDays0";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [календарных]
    private static final String CLARIFICATION_RESPONCE_LIMIT_WORKING_DAYS_TO_ID =
            "ClarificationResponseLimitDays1";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок направления запроса о разъяснении результата (после подведения итогов)]
    private static final String RESULT_CLARIFICATION_REQUEST_LIMIT_DAYS_XPATH =
            "//app-form-group[@label='Срок направления запроса о разъяснении результата (после подведения итогов)']//input";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [рабочих]
    private static final String RESULT_CLARIFICATION_REQUEST_LIMIT_WORKING_DAYS_FROM_ID =
            "ResultClarificationRequestLimitDays0";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [календарных]
    private static final String RESULT_CLARIFICATION_REQUEST_LIMIT_WORKING_DAYS_TO_ID =
            "ResultClarificationRequestLimitDays1";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок предоставления разъяснения результатов]
    private static final String RESULT_CLARIFICATION_RESPONCE_LIMIT_DAYS_XPATH =
            "//app-form-group[@label='Срок предоставления разъяснения результатов']//input";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [рабочих]
    private static final String RESULT_CLARIFICATION_RESPONCE_LIMIT_WORKING_DAYS_FROM_ID =
            "ResultClarificationResponseLimitDays0";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [календарных]
    private static final String RESULT_CLARIFICATION_RESPONCE_LIMIT_WORKING_DAYS_TO_ID =
            "ResultClarificationResponseLimitDays1";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Разъяснения не производятся]
    private static final String CLARIFICATION_ARE_NOT_PERFORMED_XPATH =
            "//app-form-group[@label='Разъяснения не производятся']//input";
    //------------------------------------------------------------------------------------------------------------------


    // endregion

    // region Раздел [Сведения о контактном лице]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о контактном лице]
    private static final String CONTACT_PERSON_INFO_BLOCK_XPATH =
            "//app-form-panel[@label='Сведения о контактном лице']//div[contains(@class,'panel-label')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Ответственное должностное лицо] (список возможных значений поля свёрнут)
    private static final String RESPONSIBLE_PERSON_LIST_CLOSED_XPATH =
            "//*[@id='ContactPersonInfo_ResponderCommonUserId']//span[@role='listbox']/span[@class='k-select']";
    //            "//app-form-group[@label='Ответственное должностное лицо']//span[@role='listbox']"; перестало работать
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Ответственное должностное лицо] (список возможных значений поля открыт) -> требуемое значение в списке
    private static final String RESPONSIBLE_PERSON_VALUE_IN_OPENED_LIST_XPATH =
            "//kendo-list[@class='ng-star-inserted']//ul/li[%s]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Ответственное должностное лицо] (список возможных значений поля открыт) -> требуемое значение в списке
    private static final String RESPONSIBLE_PERSON_VALUE_IN_OPENED_LIST_BY_NAME_XPATH =
            "//kendo-list[@class='ng-star-inserted']//ul/li[contains(., '%s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Ответственное должностное лицо] (текущее значение)
    private static final String RESPONSIBLE_PERSON_VALUE_XPATH =
            "//*[@id='ContactPersonInfo_ResponderCommonUserId']//span[@role='listbox']/span[@class='k-input']";
    //    "//app-form-group[@label='Ответственное должностное лицо']//span[@role='listbox']/span"; перестало работать
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Заполнить контактную информацию]
    private static final String FILL_CONTACT_INFO_XPATH = "//button[contains(.,'Заполнить контактную информацию')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Фамилия]
    private static final String CONTACT_PERSON_LAST_NAME_XPATH = "//app-form-group[@label='Фамилия']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Имя]
    private static final String CONTACT_PERSON_FIRST_NAME_XPATH = "//app-form-group[@label='Имя']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Отчество]
    private static final String CONTACT_PERSON_MIDDLE_NAME_XPATH = "//app-form-group[@label='Отчество']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Телефон (в международном формате)]
    private static final String CONTACT_PERSON_PHONE_XPATH =
            "//app-form-group[@label='Телефон (в международном формате)']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Адрес электронной почты]
    private static final String CONTACT_PERSON_EMAIL_XPATH =
            "//app-form-group[@label='Адрес электронной почты']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Факс (в международном формате)]
    private static final String CONTACT_PERSON_FAX_XPATH =
            "//app-form-group[@label='Факс (в международном формате)']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дополнительная контактная информация]
    private static final String CONTACT_PERSON_ADDITIONAL_INFO_XPATH =
            "//app-form-group[@label='Дополнительная контактная информация']//textarea";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения об условиях участия]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок [Сведения об условиях участия]
    private static final String INFO_ABOUT_THE_CONDITIONS_BLOCK_XPATH =
            "//app-form-panel[@label='Сведения об условиях участия']//div[@class='panel-label pull-left link']";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Закупка с ограниченным участием]
    private static final String PRIVATE_PURCHASE_XPATH =
            "//app-form-group[@label='Закупка с ограниченным участием']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Отображать перечень приглашённых участников в опубликованном извещении]
    private static final String SHOW_PARTICIPANTS_IN_NOTICE_XPATH =
            "//app-form-group[@label='Отображать перечень приглашённых участников в опубликованном извещении']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Строки в таблице с выбранными участниками
    private static final String ROW_IN_LIST_OF_INVITED_PATICIPANTS = "//kendo-grid-list//tr";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [ИНН] в таблице с выбранными участниками
    private static final String INN_COLUMN_IN_LIST_OF_INVITED_PATICIPANTS =
            "//app-form-group[@label='Перечень квалифицированных поставщиков']/..//kendo-grid-list//td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить участника]
    private static final String ADD_PARTICIPANT_BUTTON_XPATH = "//button[contains(text(),'Добавить участника')]";
    //------------------------------------------------------------------------------------------------------------------

    //==================================================================================================================
    // Окно [Выбор организации]
    //==================================================================================================================
    // Заголовок окна
    private static final String SELECT_ORGANIZATION_WINDOW_TITLE_XPATH = "//div[contains(@class,'k-dialog-title')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [ИНН]
    private static final String INN_FIELD_XPATH = "//app-form-group[@label='ИНН']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Поиск]
    private static final String INVITED_ORGANIZATION_SEARCH_BUTTON_XPATH = "//app-button/button[text()='Поиск']";
    //------------------------------------------------------------------------------------------------------------------
    // Строки в таблице с результатами поиска
    private static final String NUMBER_OF_ROWS_IN_SEARCH_RESULTS_XPATH =
            "//app-organization-select-dialog//tbody/tr";
    //------------------------------------------------------------------------------------------------------------------
    // Первая строка в таблице с результатами поиска
    private static final String FIRST_ROW_IN_SEARCH_RESULTS_XPATH =
            "//app-organization-select-dialog//tbody/tr[1]//input";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Выбрать]
    private static final String INVITED_ORGANIZATION_SELECT_BUTTON_XPATH = "//*[@id='Select']";
    //==================================================================================================================

    // endregion

    // region Раздел [Требования к участникам закупки]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Требования к участникам закупки]
    private static final String REQUIREMENTS_TO_PARTICIPANTS_BLOCK_XPATH =
            "//app-form-panel[@label='Требования к участникам закупки']//div[contains(@class,'panel-label')]";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Участники закупки должны отсутствовать в реестре недобросовестных]
    private static final String NOT_DISHONEST_XPATH =
            "//app-form-group[contains(@label,'Участники закупки должны отсутствовать')]//input";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Информация о лотах]

    // region Общее для всех лотов

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Информация о лоте]
    private static final String LOT_INFO_BLOCK_XPATH =
            "//div[contains(text(),'Информация о лоте')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить лот]
    private static final String ADD_NEW_LOT_BUTTON_XPATH =
            "//app-form-panel[@id='Lots']//button[contains(.,'Добавить лот')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Закладка [Общие сведения]

    //==================================================================================================================
    // Закладка [Общие сведения]
    private static final String LOT_COMMON_INFORMATION_TAB_XPATH = "//li//span[contains(text(),'Общие сведения')]";
    //==================================================================================================================
    // Поле [Этап]
    private static final String LOT_STATUS_XPATH = "//app-form-group[@label='Этап']//div[@class='ng-form-group__controls']/div";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование]
    private static final String LOT_NAME_XPATH = "//app-form-group[@label='Наименование']//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Валюта - конкурентная закупка] для конкуретной закупки
    private static final String CURRENCY_CODE_XPATH = "//app-form-group[@label='Валюта']//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Невозможно определить цену]
    private static final String PRICE_UNDEFINED_XPATH = "//app-form-group[@label='Невозможно определить цену']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Блок радиокнопок [Торги осуществляются]
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    private static final String TRADE_BIDDING_TYPE_1_ID =
            "TradeLotCommonInfo_1_TradeBiddingType_0";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Радиокнопка [От цены за единицу продукции]
    private static final String TRADE_BIDDING_TYPE_2_ID =
            "TradeLotCommonInfo_1_TradeBiddingType_1";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Радиокнопка [От процентной ставки]
    private static final String TRADE_BIDDING_TYPE_3_ID =
            "TradeLotCommonInfo_1_TradeBiddingType_2";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Начальная (максимальная) цена]
    private static final String LOT_PRICE_XPATH =
            "//kendo-numerictextbox[starts-with(@id,'TradeLotCommonInfo') and contains(@id,'LotPrice')]//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [НМЦ включает НДС]
    private static final String IS_LOT_PRICE_WITH_VAT_XPATH = "//app-form-group[@label='НМЦ включает НДС']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Указать сведения о НДС]
    private static final String IS_LOT_PRICE_DETERM_WITH_VAT_XPATH =
            "//app-form-group[@label='Указать сведения об НДС']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Ставка НДС, %]
    private static final String VAT_RATE_VALUE_IN_PERCENT_XPATH =
            "//app-form-group[@label='Ставка НДС, %']//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Сумма НДС]
    private static final String SUM_VAT_XPATH = "//app-form-group[@label='Сумма НДС']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [НМЦ без НДС]
    private static final String LOT_PRICE_WITHOUT_VAT_XPATH =
            "//kendo-numerictextbox[starts-with(@id,'TradeLotCommonInfo') and contains(@id,'LotPriceWithoutVat')]//input";
    //------------------------------------------------------------------------------------------------------------------
    // Радиокнопка [Цена с НДС]
    private static final String PRICE_WITH_VAT_2_ID =
            "//input[starts-with(@id,'TradeLotCommonInfo') and contains(@id,'PriceWithVat_0')]";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Радиокнопка [Цена без НДС]
    private static final String PRICE_WITH_VAT_1_ID =
            "//input[starts-with(@id,'TradeLotCommonInfo') and contains(@id,'PriceWithVat_1')]";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Тип подачи ценового предложения]
    private static final String APPLICATION_OFFER_XPATH =
            "//app-form-group[@label='Тип подачи ценового предложения']//span[@class='k-input']";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Радиокнопка [Тип подачи ценового предложения] (список возможных значений поля открыт) -> требуемое значение в списке
    private static final String APPLICATION_OFFER_VALUES_IN_OPEN_LIST_XPATH =
            "//div[@class='k-list-scroller']//li[contains(.,'%s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Невозможно определить поправочный коэффициент]
    private static final String UNABLE_SET_CORRECTION_COEFFICIENT_XPATH =
            "//input[starts-with(@id,'TradeLotCommonInfo') and contains(@id,'UnableSetCorrectionCoefficient')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Начальный поправочный коэффициент]
    private static final String INITIAL_CORRECTION_COEFFICIENT_XPATH =
            "//kendo-numerictextbox[starts-with(@id,'TradeLotCommonInfo') and contains(@id,'InitialCorrectionCoefficient')]//input";
    //------------------------------------------------------------------------------------------------------------------
    // Блок радиокнопок [Особенности участия субъектов малого и среднего предпринимательства]
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Радиокнопка [Не установлены]
    private static final String PREFERENCE_TYPE_NONE_XPATH =
            "//app-form-group[@label='Особенности участия субъектов малого и среднего предпринимательства']//" +
                    "input[@id='_0' or @id='TradeLotCommonInfo_PreferenceType_0']";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Радиокнопка [Привлечение субподрядчиков, являющихся субъектами МСП]
    private static final String PREFERENCE_TYPE_SUBCONTRACTORS_MSP_XPATH =
            "//app-form-group[@label='Особенности участия субъектов малого и среднего предпринимательства']" +
                    "//input[@id='_1' or @id='TradeLotCommonInfo_PreferenceType_1']";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Установить приоритет товаров российского происхождения в соответствии с нормами ПП РФ № 925]
    private static final String IS_PREFERENCE_BY_925_AVAILABLE_XPATH =
            "//input[starts-with(@id,'TradeLotCommonInfo') and contains(@id,'IsPreferenceBy925Available')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Сведения о предоставлении преференций]
    private static final String PREFERENCES_INFO_XPATH =
            "//app-form-group[@label='Сведения о предоставлении преференций']//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Заключение договора возможно с любым из допущенных участников]
    private static final String KEEP_GUARANTEE_FOR_ALL_PARTICIPANTS_XPATH =
            "//app-form-group[@label='Заключение договора возможно с любым из допущенных участников']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Количество участников, занявших места ниже первого,
    // с которыми возможно заключение договора по результатам процедуры]
    private static final String SUPPLIERS_AMOUNT_INPUT_XPATH =
            "//app-form-group[contains(@label,'Количество участников, занявших места ниже первого,')]//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Возможно направление договора участником]
    private static final String IS_SUPPLIER_CAN_CREATE_CONTRACT_XPATH =
            "//app-form-group[@label='Возможно направление договора участником']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок направления договора (в днях)]
    private static final String CREATE_CONTRACT_LIMIT_INPUT_XPATH =
            "//app-form-group[contains(@label,'Срок направления договора (в днях)')]//input[@role='spinbutton']";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [рабочих]
    private static final String CREATE_CONTRACT_LIMIT_WORKING_DAYS_FROM_ID =
            "TradeLotCommonInfo_1_CreateContractLimitDays_DaysType0";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [календарных]
    private static final String CREATE_CONTRACT_LIMIT_WORKING_DAYS_TO_ID =
            "TradeLotCommonInfo_1_CreateContractLimitDays_DaysType1";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок подписания договора участником (в днях)]
    private static final String PARTICIPANT_SIGN_CONTRACT_LIMIT_INPUT_XPATH =
            "//app-form-group[contains(.,'Срок подписания договора участ')]//input[@role='spinbutton']";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [рабочих]
    private static final String PARTICIPANT_SIGN_CONTRACT_LIMIT_WORKING_DAYS_FROM_ID =
            "TradeLotCommonInfo_1_ParticipantSignContractLimitDays_DaysType0";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [календарных]
    private static final String PARTICIPANT_SIGN_CONTRACT_LIMIT_WORKING_DAYS_TO_ID =
            "TradeLotCommonInfo_1_ParticipantSignContractLimitDays_DaysType1";
    //------------------------------------------------------------------------------------------------------------------
    // Блок радиокнопок [Начало отсчета срока для подписания договора участником]
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Радиокнопка [C даты размещения протокола на ЭП]
    private static final String IS_START_FROM_PROTOCOL_PUBLISH_DATE1_XPATH =
            "//app-form-group[@label='Начало отсчета срока для подписания договора участником']//input[@id='_0' or @id='TradeLotCommonInfo_1_IsStartFromProtocolPublishDate_0']";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Радиокнопка [C даты получения проекта договора поставщиком]
    private static final String IS_START_FROM_PROTOCOL_PUBLISH_DATE2_XPATH =
            "//app-form-group[@label='Начало отсчета срока для подписания договора участником']//input[@id='_1' or @id='TradeLotCommonInfo_1_IsStartFromProtocolPublishDate_1']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок заключения договора (в днях)]
    private static final String CONTRACT_COMPLETE_LIMIT_XPATH =
            "//app-form-group[contains(@label,'Срок заключения договора (в днях)')]//input[@role='spinbutton']";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [рабочих]
    private static final String CONTRACT_COMPLETE_LIMIT_WORKING_DAYS_ID =
            "TradeLotCommonInfo_1_ContractCompleteLimitDays_DaysType0";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [календарных]
    private static final String CONTRACT_COMPLETE_LIMIT_CALENDARIAN_DAYS_ID =
            "TradeLotCommonInfo_1_ContractCompleteLimitDays_DaysType1";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Закладка [Товары, работы и услуги]

    //==================================================================================================================
    // Закладка [Товары, работы и услуги]
    private static final String LOT_TYPES_CODES_TAB_XPATH =
            "//li//span[contains(text(),'Товары, работы и услуги')]";
    //==================================================================================================================
    // Флажок [Лот является составным]
    private static final String CURRENT_LOT_IS_COMPOSITE_XPATH =
            "//app-form-group[@label='Лот является составным']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Предмет предварительного отбора является составным]
    private static final String PRELIMINARY_SELECTION_LOT_IS_COMPOSITE_XPATH =
            "//input[starts-with(@id,'TradeLotItems') and contains(@id,'IsComposite')]";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Попозиционное сравнение]
    private static final String CURRENT_LOT_IS_ITEM_APPLICATION_COMPARE_XPATH =
            "//app-form-group[@label='Попозиционное сравнение']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Блок радиокнопок [Количество возможных победителей]
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Радиокнопка [Один победитель]
    private static final String MULTI_PARTICIPANTS_CONTRACT_1_XPATH =
            "//input[@id='TradeLotItems_1_IsMultiParticipantsContract_0']";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Радиокнопка [Несколько победителей]
    private static final String MULTI_PARTICIPANTS_CONTRACT_2_XPATH =
            "//input[@id='TradeLotItems_1_IsMultiParticipantsContract_1']";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Возможность подачи заявки на часть позиций]
    private static final String CURRENT_LOT_IS_APPLICATION_ITEM_PARTIAL_OFFER_ALLOWED_XPATH =
            "//app-form-group[@label='Возможность подачи заявки на часть позиций']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование товара, работ, услуг]
    private static final String LOT_GOODS_AND_SERVICES_NAME_XPATH =
            "//app-form-group[@label='Наименование товара, работ, услуг']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Код ОКПД2]
    private static final String LOT_OKPD_2_INPUT_XPATH = "//app-form-group[@label='Код ОКПД2']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле расшифрованное значение [Код ОКПД2]
    private static final String LOT_OKPD_2_DESCRIPTION_XPATH = "//app-okpd2-lookup//div[contains(@class,'ng-welt')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Код ОКВЭД2]
    private static final String LOT_OKVED_2_INPUT_XPATH = "//app-form-group[@label='Код ОКВЭД2']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле расшифрованное значение [Код ОКВЭД2]
    private static final String LOT_OKVED_2_DESCRIPTION_XPATH = "//app-okved2-lookup//div[contains(@class,'ng-welt')]";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Невозможно определить количество (объем)]
    private static final String QUANTITY_UNDEFINED_XPATH =
            "//app-form-group[@label='Невозможно определить количество (объем)']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Единица измерения (код ОКЕИ)] (список возможных значений поля свёрнут)
    private static final String OKEI_LIST_CLOSED_XPATH =
            "//app-form-group[@label='Единица измерения (код ОКЕИ)']//span[@class='k-select']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Единица измерения (код ОКЕИ)] (список возможных значений поля открыт) -> требуемое значение в списке
    private static final String OKEI_VALUE_IN_OPENED_LIST_XPATH =
            "//kendo-popup[@class='k-animation-container ng-star-inserted k-animation-container-shown']//ul/li[%s]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Единица измерения (код ОКЕИ)] (список возможных значений поля открыт) -> требуемое значение в списке
    private static final String OKEI_TEXT_IN_OPENED_LIST_XPATH =
            "//kendo-popup[@class='k-animation-container ng-star-inserted k-animation-container-shown']//ul/li[contains(.,'%s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Единица измерения (код ОКЕИ)] (текущее значение)
    private static final String OKEI_VALUE_XPATH =
            "//app-form-group[@label='Единица измерения (код ОКЕИ)']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Цена за единицу измерения]
    private static final String UNIT_PRICE_XPATH =
            "//kendo-numerictextbox[contains(@id, 'TradeLotItems_') and contains(@id, '_UnitPrice')]//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Количество]
    private static final String UNITS_QUANTITY_XPATH = "//app-form-group[@label='Количество']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дополнительные сведения]
    private static final String PRODUCT_ADDITIONAL_INFO_XPATH =
            "//app-form-group[@label='Дополнительные сведения']//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить]
    private static final String ADD_PRODUCT_TO_TABLE_ID =
            "TradeLotItems_1_btnAddLotItem";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица [Товары] - все столбцы
    private static final String PRODUCTS_TABLE_ALL_COLUMNS_XPATH =
            "//app-trade-lot-items-list//thead//th//span[@title]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица [Товары] - все строки
    private static final String PRODUCTS_TABLE_ALL_ROWS_XPATH =
            "//app-trade-lot-items-list//tbody//tr[@role='row']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Закладка [Сведения об аукционе]

    //==================================================================================================================
    // Закладка [Сведения об аукционе]
    private static final String LOT_AUCTION_DETAILS_TAB_XPATH =
            "//li//span[contains(text(),'Сведения об аукционе')]";
    //==================================================================================================================
    // Флажок [Аукцион с шагом]
    private static final String AUCTION_WITH_STEP_XPATH = "//app-form-group[@label='Аукцион с шагом']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Блок радиокнопок [Тип шага аукциона]
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Радиокнопка [Фиксированная сумма]
    private static final String AUCTION_STEP_IN_FIXED_SUM_XPATH =
            "//app-form-group[@label='Тип шага аукциона']//input[@id='_0' or @id='TradeLotAuctionInfo_1_StepInFixedSum_0']";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Радиокнопка [Процент от НМЦ]
    private static final String AUCTION_PERCENT_OF_SMP_XPATH =
            "//app-form-group[@label='Тип шага аукциона']//input[@id='_1' or @id='TradeLotAuctionInfo_1_StepInFixedSum_1']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Шаг аукциона] - [От]
    private static final String AUCTION_MIN_STEP_XPATH = "//app-numeric-textbox[@placeholder='От']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Шаг аукциона] - [До]
    private static final String AUCTION_MAX_STEP_XPATH = "//app-numeric-textbox[@placeholder='До']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Автоматическое снижение шага]
    private static final String AUCTION_IS_STEP_AUTO_DECREASING_XPATH =
            "//app-form-group[@label='Автоматическое снижение шага']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Аукцион с продлением]
    private static final String AUCTION_IS_AUTO_PROLONGATION_XPATH =
            "//app-form-group[@label='Аукцион с продлением']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата и время окончания аукциона (МСК)]
    private static final String AUCTION_END_DATE_XPATH =
            "//app-form-group[@labeltimezone='Дата и время окончания аукциона']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Время ожидания ценового предложения (мин)]
    private static final String AUCTION_NEXT_BID_WAITING_MINUTES_XPATH =
            "//app-form-group[@label='Время ожидания ценового предложения (мин)']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Улучшение своего предложения без снижения текущего минимального]
    private static final String AUCTION_IMPROVE_BID_ONLY_XPATH =
            "//input[starts-with(@id,'TradeLotAuctionInfo') and contains(@id,'ImproveBidOnly')]";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Проводить вторую фазу (торги за второе место)]
    private static final String AUCTION_WITH_SECOND_PHASE_XPATH =
            "//app-form-group[@label='Проводить вторую фазу (торги за второе место)']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Длительность второй фазы (мин)]
    private static final String AUCTION_SECOND_PHASE_DURATION_MINUTES_XPATH =
            "//app-form-group[@label='Длительность второй фазы (мин)']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Аукцион с нижним пределом снижения цены]
    private static final String AUCTION_WITH_LIMIT_PRICE_REDUCTION_XPATH =
            "//app-form-group[@label='Аукцион с нижним пределом снижения цены']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Показывать наименования участников]
    private static final String SHOW_PARTICIPANT_NANES_XPATH =
            "//app-form-group[@label='Показывать наименования участников']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Завершение аукциона при снижении до, % от НМЦ]
    private static final String AUCTION_LIMIT_PRICE_REDUCTION_PERCENT_XPATH =
            "//app-form-group[@label='Завершение аукциона при снижении до, % от НМЦ']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Максимальная продолжительность аукциона (мин)]
    private static final String AUCTION_MAX_DURATION_IN_MINUTES_XPATH =
            "//app-form-group[@label='Максимальная продолжительность аукциона (мин)']//input";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Закладка [Сведения о переторжке]

    // endregion

    // region Закладка [Сведения о заказчиках]

    //==================================================================================================================
    // Закладка [Сведения о заказчиках]
    private static final String LOT_CUSTOMERS_INFO_TAB_XPATH =
            "//li//span[contains(text(),'Сведения о заказчиках')]";
    //==================================================================================================================
    // Флажок [Несколько заказчиков (совместная закупка)]
    private static final String IS_MULTI_CUSTOMERS_XPATH =
            "//app-form-group[@label='Несколько заказчиков (совместная закупка)']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Договор заключается с несколькими участниками]
    private static final String IS_MULTI_PARTICIPANTS_CONTRACT_XPATH =
            "//app-form-group[@label='Договор заключается с несколькими участниками']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Вид обеспечения заявки]
    private static final String APPLICATION_GUARANTEE_TYPE_XPATH =
            "//app-form-group[@label='Вид обеспечения заявки']//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Вид обеспечения договора]
    private static final String DOCUMENT_GUARANTEE_TYPE_XPATH =
            "//app-form-group[@label='Вид обеспечения договора']//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Заказчик]
    private static final String CUSTOMER_NAME_XPATH = "//app-form-group[@label='Заказчик']//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Заказчик]
    private static final String FIRST_CUSTOMER_NAME_FROM_POPUP_XPATH =
            "//kendo-popup//kendo-list[@class='ng-star-inserted']//div[@class='k-list-scroller']/ul/li[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Начальная (максимальная цена) договора]
    private static final String CURRENT_CUSTOMER_MAX_PRICE_XPATH =
            "//app-form-group[@label='Начальная (максимальная) цена договора']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Количество]
    private static final String CURRENT_CUSTOMER_QUANTITY_XPATH = "//app-form-group[@label='Количество']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Найти]
    private static final String FIND_CUSTOMER_TO_ADD_XPATH = "//app-form-group[@label='Заказчик']//button";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить]
    private static final String ADD_CUSTOMER_TO_TABLE_ID = "TradeLotCustomers_1_btnAddCustomer";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица [Заказчики] - все строки
    private static final String CUSTOMERS_TABLE_ALL_ROWS_XPATH = "//app-trade-lot-customers-list//tbody/tr";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Обеспечение заявки]
    private static final String APPLICATION_AMOUNT_PERCENT_XPATH =
            "//kendo-numerictextbox[starts-with(@id,'TradeLotCustomers') and contains(@id,'ApplicationAmountPercent')]//input";
    private static final String APPLICATION_AMOUNT_SUM_XPATH =
            "(//kendo-numerictextbox[starts-with(@id,'TradeLotCustomers') and contains(@id,'ApplicationAmount')]//input)[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Обеспечение договора]
    private static final String CONTRACT_AMOUNT_PERCENT_XPATH =
            "//kendo-numerictextbox[starts-with(@id,'TradeLotCustomers') and contains(@id,'ContractAmountPercent')]//input";
    private static final String CONTRACT_AMOUNT_SUM_XPATH =
            "(//kendo-numerictextbox[starts-with(@id,'TradeLotCustomers') and contains(@id,'ContractAmount')]//input)[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Сведения о заказчиках - Регион - значение первого выбранного региона]
    private static final String DELIVERY_REGION_VALUE_XPATH =
            "//app-form-group[@label='Регион']//span[@class='ng-star-inserted' and 1]";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Поле [Сведения о заказчиках - Регион - X]
    private static final String DELIVERY_REGION_X_BUTTONS_XPATH =
            "//app-form-group[@label='Регион']//span[@aria-label='delete']/span";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Поле [Сведения о заказчиках - Регион - поле ввода]
    private static final String DELIVERY_REGION_INPUT_FIELD_XPATH =
            "//app-form-group[@label='Регион']//kendo-searchbar/input";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Поле [Сведения о заказчиках - Регион - список вариантов]
    private static final String DELIVERY_REGION_MULTISELECT_LIST_XPATH =
            "//kendo-list[@class='ng-star-inserted']//li[contains(.,'%s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Место поставки товара, выполнения работ, оказания услуг]
    private static final String DELIVERY_PLACE_XPATH =
            "//app-form-group[@label='Место поставки товара, выполнения работ, оказания услуг']//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Заполнить юридическим адресом]
    private static final String FILL_WITH_LEGAL_ADDRESS_BUTTON_XPATH =
            "//app-form-group[@label='Место поставки товара, выполнения работ, оказания услуг']//button";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок поставки товара, выполнения работ, оказания услуг]
    private static final String DELIVERY_TERM_XPATH =
            "//app-form-group[@label='Срок поставки товара, выполнения работ, оказания услуг']//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Регистрационный номер плана]
    private static final String PLAN_REGISTRATION_NUMBER_XPATH =
            "//app-form-group[@label='Регистрационный номер плана']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер позиции плана]
    private static final String PLAN_POSITION_NUMBER_XPATH = "//app-form-group[@label='Номер позиции плана']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Вид обеспечения заявки] (список возможных значений поля свёрнут)
    private static final String APPLICATION_GUARANTEE_LIST_CLOSED_XPATH =
            "//*[@id='TradeLotCustomers_1_ApplicationGuaranteeType']//span[@role='listbox']/span[@class='k-select']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Вид обеспечения заявки] (список возможных значений поля открыт) -> требуемое значение в списке
    // private static final String APPLICATION_GUARANTEE_VALUE_IN_OPENED_LIST_XPATH =
    //         "//kendo-list[@class='ng-star-inserted']//ul/li[%s]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Вид обеспечения заявки] (список возможных значений поля открыт) -> требуемое значение в списке
    private static final String APPLICATION_GUARANTEE_VALUE_IN_OPENED_LIST_BY_NAME_XPATH =
            "//kendo-list[@class='ng-star-inserted']//ul/li[contains(., '%s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Вид обеспечения заявки] (текущее значение)
    private static final String APPLICATION_GUARANTEE_VALUE_XPATH =
            "//*[@id='TradeLotCustomers_1_ApplicationGuaranteeType']//span[@role='listbox']/span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Вид обеспечения договора] (список возможных значений поля свёрнут)
    private static final String DOCUMENT_GUARANTEE_LIST_CLOSED_XPATH =
            "//*[@id='TradeLotCustomers_1_DocumentGuaranteeType']//span[@role='listbox']/span[@class='k-select']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Вид обеспечения договора] (список возможных значений поля открыт) -> требуемое значение в списке
    // private static final String DOCUMENT_GUARANTEE_VALUE_IN_OPENED_LIST_XPATH =
    //         "//kendo-list[@class='ng-star-inserted']//ul/li[%s]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Вид обеспечения договора] (список возможных значений поля открыт) -> требуемое значение в списке
    private static final String DOCUMENT_GUARANTEE_VALUE_IN_OPENED_LIST_BY_NAME_XPATH =
            "//kendo-list[@class='ng-star-inserted']//ul/li[contains(., '%s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Вид обеспечения договора] (текущее значение)
    private static final String DOCUMENT_GUARANTEE_VALUE_XPATH =
            "//*[@id='TradeLotCustomers_1_DocumentGuaranteeType']//span[@role='listbox']/span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // endregion

    // region Закладка [Документы лота]

    //==================================================================================================================
    // Закладка [Документы лота]
    private static final String LOT_DOCUMENTS_TAB_XPATH =
            "//li//span[contains(text(),'Документы лота')]";
    //==================================================================================================================
    // Документы лота
    //==================================================================================================================
    // Таблица [Документы лота] - документы не прикреплены
    private static final String LOT_DOCUMENTS_TABLE_EMPTY_XPATH =
            "//div[@role='grid']/table//td[contains(.,'Документы не прикреплены')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Закладка [Дополнительные параметры]

    //==================================================================================================================
    // Закладка [Дополнительные параметры]
    private static final String LOT_ADDITIONAL_PARAMETERS_TAB_XPATH =
            "//li//span[contains(text(),'Дополнительные параметры')]";
    //==================================================================================================================
    // Дополнительные параметры лота
    //==================================================================================================================
    // Поле [Отсрочка платежа (дней)]
    private static final String DEFEREMENT_OF_PAYMENT_DAYS_XPATH =
            "//app-form-group[@label='Отсрочка платежа (дней)']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Предоплата]
    private static final String PREPAYMENT_DAYS_XPATH = "//app-form-group[@label='Предоплата']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Требования к продукции]
    private static final String PRODUCT_REQUIREMENTS_XPATH = "//app-form-group[@label='Требования к продукции']//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Адрес склада]
    private static final String STORAGE_ADDRESS_XPATH = "//app-form-group[@label='Адрес склада']//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Точная дата поставки (крайний срок)]
    private static final String DELIVERY_DATE_XPATH =
            "//app-form-group[@label='Точная дата поставки (крайний срок)']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Примечание]
    private static final String NOTE_XPATH = "//app-form-group[@label='Примечание']//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Промокод]
    private static final String PROMO_CODE_XPATH = "//app-form-group[@label='Промокод']//input";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // endregion

    // region Раздел [Документы]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Документы]
    private static final String DOCUMENTS_BLOCK_XPATH =
            "//app-form-panel[@label='Документы']//div[contains(@class,'panel-label')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Прикрепить файл]
    private static final String UPLOAD_FILE_TRADE_XPATH = "//app-uploader[@buttontext='Прикрепить файлы']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка для скачивания первого из прикрепленных к извещению файлов
    private static final String DOWNLOAD_FILE_TRADE_XPATH =
            "//tr[1]//a[contains(@href, '.kontur.ru/files/FileDownloadHandler.ashx?fileguid=')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Дополнительные поля]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Дополнительные поля]
    private static final String ADDITIONAL_FIELDS_BLOCK_XPATH =
            "//app-external-fields-edit[@name='TradeExternalFields']//div[contains(@class,'panel-label')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Порядок формирования начальной цены]
    private static final String AF_INIT_PRICE_ORDER_XPATH =
            "//label[contains(.,'Порядок формирования начальной')]/../..//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Новое дополнительное поле закупки - при создании, выбран тип поля 'Строка']
    private static final String NEW_AF_PURCHASE_XPATH =
            "//label[contains(.,'Новое дополнительное поле закупки')]/../..//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Новое дополнительное поле лота - при создании, выбран тип поля 'Время и дата']
    private static final String NEW_AF_LOT_XPATH =
            "//label[contains(.,'Новое дополнительное поле лота')]/following::input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Новое дополнительное поле позиций лота - при создании, выбран тип поля 'Валюта']
    // (кнопка [V] - развернуть список)
            private static final String NEW_AF_LOT_POSITION_LIST_CLOSED_XPATH =
            "//label[contains(.,'%s')]/following::div//span[@class='k-icon k-i-arrow-s']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Новое дополнительное поле позиций лота - при создании, выбран тип поля 'Валюта'] (список возможных
    // значений поля открыт) -> требуемое значение в списке
    private static final String NEW_AF_LOT_POSITION_VALUE_IN_OPENED_LIST_XPATH =
            "//li[contains(.,'Российский рубль (RUB)')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Новое дополнительное поле позиций лота - при создании, выбран тип поля 'Валюта'] (текущее значение)
    private static final String NEW_AF_LOT_POSITION_VALUE_XPATH =
            "//label[contains(.,'%s')]/following::div//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Рабочие группы]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Рабочие группы]
    private static final String WORK_GROUPS_BLOCK_XPATH =
            "//app-form-panel[@label='Рабочие группы']//div[contains(@class,'panel-label')]";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок в таблице [Рабочие группы] справа от требуемой группы ( имя группы передается как параметр теста )
    private static final String IS_GROUP_SELECTED_XPATH =
            "//tbody/tr/td[contains(.,'%s')]/../td//input[@type='checkbox']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Согласование]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус рассмотрения]
    private static final String APPROVAL_STATUS_XPATH =
            "//app-form-group[@label='Статус рассмотрения']//div[@class='ng-form-group__controls']/label";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип согласования]
    private static final String APPROVAL_TYPE_XPATH =
            "//app-form-group[@label='Тип согласования']//div[@class='ng-form-group__controls']/label";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Заявка на согласование подана]
    private static final String DATE_OF_APPROVAL_REQUEST_XPATH =
            "//app-form-group[@label='Заявка на согласование подана (МСК)']//div[@class='ng-form-group__controls']/label";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата согласования]
    private static final String DATE_OF_APPROVAL_PROCEDURE_XPATH =
            "//app-form-group[@label='Дата согласования (МСК)']//div[@class='ng-form-group__controls']/label";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Плановая дата публикации закупки]
    private static final String PLANNED_PUBLICATION_DATE_0F_PURCHASE_XPATH =
            "//app-form-group[@label='Плановая дата публикации закупки (МСК)']//div[@class='ng-form-group__controls']/label";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата принятия решения]
    private static final String DATE_OF_APPROVAL_XPATH =
            "//app-form-group[@label='Дата принятия решения (МСК)']//div[@class='ng-form-group__controls']/label";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Комиссия]

    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Организация]
    private static final String TABLE_ORGANIZATION_COLUMN_XPATH =
            "//div[contains(@class,'panel-label') and contains(.,'Комиссия')]/../../..//table/tbody/tr/td[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Ответственный]
    private static final String TABLE_RESPONSIBLE_COLUMN_XPATH =
            "//div[contains(@class,'panel-label') and contains(.,'Комиссия')]/../../..//table/tbody/tr/td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Статус]
    private static final String TABLE_STATUS_COLUMN_XPATH =
            "//div[contains(@class,'panel-label') and contains(.,'Комиссия')]/../../..//table/tbody/tr/td[3]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Решение принято]
    private static final String TABLE_DECISION_IS_MADE_COLUMN_XPATH =
            "//div[contains(@class,'panel-label') and contains(.,'Комиссия')]/../../..//table/tbody/tr/td[4]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Комментарий]
    private static final String TABLE_COMMENT_COLUMN_XPATH =
            "//div[contains(@class,'panel-label') and contains(.,'Комиссия')]/../../..//table/tbody/tr/td[5]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Документ]
    private static final String TABLE_DOCUMENT_COLUMN_XPATH =
            "//div[contains(@class,'panel-label') and contains(.,'Комиссия')]/../../..//table/tbody/tr/td[6]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Блок управляющих кнопок в нижней части страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Опубликовать]
    private static final String PUBLISH_BUTTON_XPATH = "//button[contains(.,'Опубликовать')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Удалить черновик]
    private static final String DELETE_DRAFT_BUTTON_XPATH = "//button[contains(.,'Удалить черновик')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Сохранить черновик]
    private static final String SAVE_DRAFT_BUTTON_XPATH = "//button[contains(.,'Сохранить черновик')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Сохранить и проверить]
    private static final String SAVE_AND_CHECK_BUTTON_XPATH = "//button[contains(.,'Сохранить и проверить')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Отправить на согласование]
    private static final String SEND_TO_APPROVAL_BUTTON_XPATH = "//button[contains(.,'Отправить на согласование')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Всплывающее окно [Календарь]

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Закрыть - окно Календарь]
    private static final String DATEPICKER_HIDE_BUTTON_XPATH = "//div[@id='ui-datepicker-div']//button[text()='Закрыть']";
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
    private final Dictionary blockNames = new Dictionary();       // основные разделы извещения о закупке
    private final Dictionary tabNames = new Dictionary();         // основные разделы лота
    private final Dictionary radioButtonNames = new Dictionary(); // все переключатели на странице
    private final Dictionary checkBoxNames = new Dictionary();    // все флажки на странице
    private final Dictionary fieldNames = new Dictionary();       // все поля на странице
    private final Dictionary buttonNames = new Dictionary();      // все кнопки на странице
    private final Dictionary commColumns = new Dictionary();      // все колонки таблицы в разделе [Комиссия]
    //------------------------------------------------------------------------------------------------------------------
    // Диалоговое окно [Публикация извещения]
    private final PublishAngularDialog publishAngularDialog = new PublishAngularDialog(driver);
    //------------------------------------------------------------------------------------------------------------------
    // Диалоговое окно [Опубликовано]
    private final PublishedAngularDialog publishedAngularDialog = new PublishedAngularDialog(driver);
    //------------------------------------------------------------------------------------------------------------------
    // Диалоговое окно [Сохранено]
    private final SavedAngularDialog savedAngularDialog = new SavedAngularDialog(driver);
    //------------------------------------------------------------------------------------------------------------------
    // Страница [Просмотр извещения XXXX]
    private final ViewNoticePage viewNoticePage = new ViewNoticePage(driver);
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CreateNoticePage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Общие сведения о закупке", COMMON_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о порядке подачи заявок", APPLICATION_DATES_BLOCK_XPATH);
        blockNames.add("Сведения о порядке работы комиссии", COMMISSION_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о предоставлении документации", DOCUMENTATION_BLOCK_XPATH);
        blockNames.add("Сведения о порядке предоставления разъяснений", CLARIFICATION_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о контактном лице", CONTACT_PERSON_INFO_BLOCK_XPATH);
        blockNames.add("Сведения об условиях участия", INFO_ABOUT_THE_CONDITIONS_BLOCK_XPATH);
        blockNames.add("Требования к участникам закупки", REQUIREMENTS_TO_PARTICIPANTS_BLOCK_XPATH);
        blockNames.add("Информация о лоте", LOT_INFO_BLOCK_XPATH);
        blockNames.add("Документы", DOCUMENTS_BLOCK_XPATH);
        blockNames.add("Дополнительные поля", ADDITIONAL_FIELDS_BLOCK_XPATH);
        blockNames.add("Рабочие группы", WORK_GROUPS_BLOCK_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        tabNames.add("Общие сведения", LOT_COMMON_INFORMATION_TAB_XPATH);
        tabNames.add("Товары, работы и услуги", LOT_TYPES_CODES_TAB_XPATH);
        tabNames.add("Сведения об аукционе", LOT_AUCTION_DETAILS_TAB_XPATH);
        tabNames.add("Сведения о заказчиках", LOT_CUSTOMERS_INFO_TAB_XPATH);
        tabNames.add("Документы лота", LOT_DOCUMENTS_TAB_XPATH);
        tabNames.add("Дополнительные параметры", LOT_ADDITIONAL_PARAMETERS_TAB_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        radioButtonNames.add("Фиксированная сумма", AUCTION_STEP_IN_FIXED_SUM_XPATH);
        radioButtonNames.add("Процент от НМЦ", AUCTION_PERCENT_OF_SMP_XPATH);
        radioButtonNames.add("Регламентный срок заключения договора - рабочих",
                CONTRACT_CONCLUSION_DAYS_WORKING_DAYS1_ID);
        radioButtonNames.add("Регламентный срок заключения договора - календарных",
                CONTRACT_CONCLUSION_DAYS_WORKING_DAYS2_ID);
        radioButtonNames.add("Срок направления запроса о разъяснении документации - рабочих",
                CLARIFICATION_REQUEST_LIMIT_WORKING_DAYS_FROM_ID);
        radioButtonNames.add("Срок направления запроса о разъяснении документации - календарных",
                CLARIFICATION_REQUEST_LIMIT_WORKING_DAYS_TO_ID);
        radioButtonNames.add("Срок предоставления разъяснения документации - рабочих",
                CLARIFICATION_RESPONCE_LIMIT_WORKING_DAYS_FROM_ID);
        radioButtonNames.add("Срок предоставления разъяснения документации - календарных",
                CLARIFICATION_RESPONCE_LIMIT_WORKING_DAYS_TO_ID);
        radioButtonNames.add("Срок направления запроса о разъяснении результата - рабочих",
                RESULT_CLARIFICATION_REQUEST_LIMIT_WORKING_DAYS_FROM_ID);
        radioButtonNames.add("Срок направления запроса о разъяснении результата - календарных",
                RESULT_CLARIFICATION_REQUEST_LIMIT_WORKING_DAYS_TO_ID);
        radioButtonNames.add("Срок предоставления разъяснения результатов - рабочих",
                RESULT_CLARIFICATION_RESPONCE_LIMIT_WORKING_DAYS_FROM_ID);
        radioButtonNames.add("Срок предоставления разъяснения результатов - календарных",
                RESULT_CLARIFICATION_RESPONCE_LIMIT_WORKING_DAYS_TO_ID);
        radioButtonNames.add("От цены за лот", TRADE_BIDDING_TYPE_1_ID);
        radioButtonNames.add("От цены за единицу продукции", TRADE_BIDDING_TYPE_2_ID);
        radioButtonNames.add("От процентной ставки", TRADE_BIDDING_TYPE_3_ID);
        radioButtonNames.add("Один победитель", MULTI_PARTICIPANTS_CONTRACT_1_XPATH);
        radioButtonNames.add("Несколько победителей", MULTI_PARTICIPANTS_CONTRACT_2_XPATH);
        radioButtonNames.add("Цена с НДС", PRICE_WITH_VAT_2_ID);
        radioButtonNames.add("Цена без НДС", PRICE_WITH_VAT_1_ID);
        radioButtonNames.add("Особенности участия субъектов малого и среднего предпринимательства - Не установлены",
                PREFERENCE_TYPE_NONE_XPATH);
        radioButtonNames.add("Особенности участия субъектов малого и среднего предпринимательства - " +
                "Привлечение субподрядчиков, являющихся субъектами МСП", PREFERENCE_TYPE_SUBCONTRACTORS_MSP_XPATH);
        radioButtonNames.add("Срок направления договора (в днях) - рабочих",
                CREATE_CONTRACT_LIMIT_WORKING_DAYS_FROM_ID);
        radioButtonNames.add("Срок направления договора (в днях) - календарных",
                CREATE_CONTRACT_LIMIT_WORKING_DAYS_TO_ID);
        radioButtonNames.add("Срок подписания договора участником (в днях) - рабочих",
                PARTICIPANT_SIGN_CONTRACT_LIMIT_WORKING_DAYS_FROM_ID);
        radioButtonNames.add("Срок подписания договора участником (в днях) - календарных",
                PARTICIPANT_SIGN_CONTRACT_LIMIT_WORKING_DAYS_TO_ID);
        radioButtonNames.add("С даты размещения протокола на ЭП", IS_START_FROM_PROTOCOL_PUBLISH_DATE1_XPATH);
        radioButtonNames.add("С даты получения проекта договора поставщиком",
                IS_START_FROM_PROTOCOL_PUBLISH_DATE2_XPATH);
        radioButtonNames.add("Срок заключения договора (в днях) - рабочих",
                CONTRACT_COMPLETE_LIMIT_WORKING_DAYS_ID);
        radioButtonNames.add("Срок заключения договора (в днях) - календарных",
                CONTRACT_COMPLETE_LIMIT_CALENDARIAN_DAYS_ID);
        //--------------------------------------------------------------------------------------------------------------
        checkBoxNames.add("Многолотовая закупка", IS_MULTI_LOTS_PURCHASE_XPATH);
        checkBoxNames.add("Независимая публикация протоколов по лотам", IS_INDEPENDENT_PROTOCOL_PUBLICATION_XPATH);
        checkBoxNames.add("Возможность участия в процедуре без ЭЦП", IS_DIGITAL_SIGN_NOT_REQUIRED_XPATH);
        checkBoxNames.add("Возможно уточнение заявок", IS_IMPLICIT_RETRADING_XPATH);
        checkBoxNames.add("Публикация протокола открытия доступа", IS_OPENING_PROTOCOL_ENABLED_XPATH);
        checkBoxNames.add("Возможно продление сроков закупки", IS_AUTO_PROLONGATION_DATES_XPATH);
        checkBoxNames.add("Отдельные протоколы рассмотрения и оценки заявок", IS_SEPARATED_CONS_PROTOCOL_ENABLED_XPATH);
        checkBoxNames.add("Публикация итогового протокола", IS_RESULT_PROTOCOL_ENABLED_XPATH);
        checkBoxNames.add("Отображать цены поступивших предложений в открытом доступе",
                PUBLIC_APPLICATION_QUOTATIONS_XPATH);
        checkBoxNames.add("Закупка с проведением предварительного этапа", IS_MULTI_STAGE_XPATH);
        checkBoxNames.add("Возможна подача альтернативного предложения в заявке", IS_ALTERNATIVE_PROPOSAL_AVAILABLE_XPATH);
        checkBoxNames.add("Получить доступ к заявкам до окончания срока их приема", ALLOW_FILLING_APPLICATION_ACCESS_XPATH);
        checkBoxNames.add("Закупка проводится вследствие аварии, чрезвычайной ситуации", IS_EMERGENCY_XPATH);
        checkBoxNames.add("Закупка с ограниченным участием", PRIVATE_PURCHASE_XPATH);
        checkBoxNames.add("Установлена плата за предоставление документации", SHOULD_HAVE_DOCUMENTATION_FREE_XPATH);
        checkBoxNames.add("Разъяснения не производятся", CLARIFICATION_ARE_NOT_PERFORMED_XPATH);
        checkBoxNames.add("Невозможно определить цену", PRICE_UNDEFINED_XPATH);
        checkBoxNames.add("НМЦ включает НДС", IS_LOT_PRICE_WITH_VAT_XPATH);
        checkBoxNames.add("Указать сведения о НДС", IS_LOT_PRICE_DETERM_WITH_VAT_XPATH);
        checkBoxNames.add("Невозможно определить поправочный коэффициент", UNABLE_SET_CORRECTION_COEFFICIENT_XPATH);
        checkBoxNames.add("Установить приоритет товаров российского происхождения", IS_PREFERENCE_BY_925_AVAILABLE_XPATH);
        checkBoxNames.add("Возможно направление договора участником", IS_SUPPLIER_CAN_CREATE_CONTRACT_XPATH);
        checkBoxNames.add("Лот является составным", CURRENT_LOT_IS_COMPOSITE_XPATH);
        checkBoxNames.add("Предмет предварительного отбора является составным",
                PRELIMINARY_SELECTION_LOT_IS_COMPOSITE_XPATH);
        checkBoxNames.add("Попозиционное сравнение", CURRENT_LOT_IS_ITEM_APPLICATION_COMPARE_XPATH);
        checkBoxNames.add("Возможность подачи заявки на часть позиций",
                CURRENT_LOT_IS_APPLICATION_ITEM_PARTIAL_OFFER_ALLOWED_XPATH);
        checkBoxNames.add("Невозможно определить количество (объем)", QUANTITY_UNDEFINED_XPATH);
        checkBoxNames.add("Отображать перечень приглашённых участников в опубликованном извещении",
                SHOW_PARTICIPANTS_IN_NOTICE_XPATH);
        checkBoxNames.add("Участники закупки должны отсутствовать в реестре недобросовестных", NOT_DISHONEST_XPATH);
        checkBoxNames.add("Аукцион с шагом", AUCTION_WITH_STEP_XPATH);
        checkBoxNames.add("Автоматическое снижение шага", AUCTION_IS_STEP_AUTO_DECREASING_XPATH);
        checkBoxNames.add("Аукцион с продлением", AUCTION_IS_AUTO_PROLONGATION_XPATH);
        checkBoxNames.add("Улучшение своего предложения без снижения текущего минимального", AUCTION_IMPROVE_BID_ONLY_XPATH);
        checkBoxNames.add("Проводить вторую фазу (торги за второе место)", AUCTION_WITH_SECOND_PHASE_XPATH);
        checkBoxNames.add("Аукцион с нижним пределом снижения цены", AUCTION_WITH_LIMIT_PRICE_REDUCTION_XPATH);
        checkBoxNames.add("Аукцион - Показывать наименования участников", SHOW_PARTICIPANT_NANES_XPATH);
        checkBoxNames.add("Заключение договора возможно с любым из допущенных участников",
                KEEP_GUARANTEE_FOR_ALL_PARTICIPANTS_XPATH);
        checkBoxNames.add("Несколько заказчиков (совместная закупка)", IS_MULTI_CUSTOMERS_XPATH);
        checkBoxNames.add("Договор заключается с несколькими участниками", IS_MULTI_PARTICIPANTS_CONTRACT_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Номер закупки", PURCHASE_NUMBER_XPATH);
        fieldNames.add("Способ закупки", PURCHASE_METHOD_XPATH);
        fieldNames.add("Способ определения поставщика (подрядчика, исполнителя)", DETERMINING_THE_SUPPLIER_METHOD_XPATH);
        fieldNames.add("Тип закупки", PURCHASE_TYPE_XPATH);
        fieldNames.add("Наименование закупки", ORDER_NAME_XPATH);
        fieldNames.add("Наименование процедуры", PROCEDURE_NAME_XPATH);
        fieldNames.add("Описание закупки", DESCRIPTION_XPATH);
        fieldNames.add("Дата и время начала подачи заявок (МСК)", APPLICATION_START_DATE_XPATH);
        fieldNames.add("Дата и время окончания подачи заявок (МСК)", APPLICATION_END_DATE_XPATH);
        fieldNames.add("Дата и время последнего окончания подачи заявок (МСК)", APPLICATION_LAST_END_DATE_XPATH);
        fieldNames.add("Дата и время открытия доступа (МСК)", CONSIDERATION_START_DATE_XPATH);
        fieldNames.add("Место подачи заявок", APPLICATION_REQUEST_PLACE_XPATH);
        fieldNames.add("Порядок подачи заявок", APPLICATION_REQUEST_ORDER_XPATH);
        fieldNames.add("Дата и время рассмотрения заявок (МСК)", CONSIDERATION_DATE_ID);
        fieldNames.add("Дата и время следующего окончания подачи заявок (МСК)", CONSIDERATION_DATE_ID);
        fieldNames.add("Место рассмотрения заявок", CONSIDERATION_PLACE_XPATH);
        fieldNames.add("Порядок рассмотрения заявок", CONSIDERATION_ORDER_XPATH);
        fieldNames.add("Дата и время проведения аукциона (МСК)", SINGLE_AUCTION_DATE_XPATH);
        fieldNames.add("Дата и время подведения итогов (МСК)", RESULTS_REVIEW_DATE_ID);
        fieldNames.add("Дата и время окончания аукциона (МСК)", AUCTION_END_DATE_XPATH);
        fieldNames.add("Место подведения итогов", RESULTS_REVIEW_PLACE_XPATH);
        fieldNames.add("Порядок подведения итогов", RESULTS_REVIEW_ORDER_XPATH);
        fieldNames.add("Регламентный срок заключения договора - От", CONTRACT_CONCLUSION_DAYS_FROM_XPATH);
        fieldNames.add("Регламентный срок заключения договора - До", CONTRACT_CONCLUSION_DAYS_TO_XPATH);
        fieldNames.add("Срок предоставления документации (МСК) От", DOCUMENTATION_DATE_FROM_XPATH);
        fieldNames.add("Срок предоставления документации (МСК) До", DOCUMENTATION_DATE_TO_XPATH);
        fieldNames.add("Место предоставления документации", DOCUMENTATION_PLACE_XPATH);
        fieldNames.add("Порядок предоставления документации", DOCUMENTATION_VIEW_PROCEDURE_XPATH);
        fieldNames.add("Срок направления запроса о разъяснении документации (дней до окончания подачи заявок)",
                CLARIFICATION_REQUEST_LIMIT_DAYS_XPATH);
        fieldNames.add("Срок предоставления разъяснения документации", CLARIFICATION_RESPONCE_LIMIT_DAYS_XPATH);
        fieldNames.add("Срок направления запроса о разъяснении результата (после подведения итогов)",
                RESULT_CLARIFICATION_REQUEST_LIMIT_DAYS_XPATH);
        fieldNames.add("Срок предоставления разъяснения результатов", RESULT_CLARIFICATION_RESPONCE_LIMIT_DAYS_XPATH);
        fieldNames.add("Ответственное должностное лицо", RESPONSIBLE_PERSON_VALUE_XPATH);
        fieldNames.add("Фамилия", CONTACT_PERSON_LAST_NAME_XPATH);
        fieldNames.add("ФИО", CONTACT_PERSON_LAST_NAME_XPATH);
        fieldNames.add("Имя", CONTACT_PERSON_FIRST_NAME_XPATH);
        fieldNames.add("Отчество", CONTACT_PERSON_MIDDLE_NAME_XPATH);
        fieldNames.add("Телефон (в международном формате)", CONTACT_PERSON_PHONE_XPATH);
        fieldNames.add("Адрес электронной почты", CONTACT_PERSON_EMAIL_XPATH);
        fieldNames.add("Факс (в международном формате)", CONTACT_PERSON_FAX_XPATH);
        fieldNames.add("Дополнительная контактная информация", CONTACT_PERSON_ADDITIONAL_INFO_XPATH);
        fieldNames.add("Этап", LOT_STATUS_XPATH);
        fieldNames.add("Наименование", LOT_NAME_XPATH);
        fieldNames.add("Валюта", CURRENCY_CODE_XPATH);
        fieldNames.add("Валюта - выбранное значение", CURRENCY_CODE_XPATH);
        fieldNames.add("Код ОКПД2", LOT_OKPD_2_INPUT_XPATH);
        fieldNames.add("Код ОКВЭД2", LOT_OKVED_2_INPUT_XPATH);
        fieldNames.add("Начальная (максимальная) цена", LOT_PRICE_XPATH);
        fieldNames.add("Ставка НДС, %", VAT_RATE_VALUE_IN_PERCENT_XPATH);
        fieldNames.add("Тип подачи ценового предложения", APPLICATION_OFFER_XPATH);
        fieldNames.add("Сумма НДС", SUM_VAT_XPATH);
        fieldNames.add("НМЦ без НДС", LOT_PRICE_WITHOUT_VAT_XPATH);
        fieldNames.add("Начальный поправочный коэффициент", INITIAL_CORRECTION_COEFFICIENT_XPATH);
        fieldNames.add("Сведения о предоставлении преференций", PREFERENCES_INFO_XPATH);
        fieldNames.add("Количество участников, занявших места ниже первого,", SUPPLIERS_AMOUNT_INPUT_XPATH);
        fieldNames.add("Срок направления договора (в днях)", CREATE_CONTRACT_LIMIT_INPUT_XPATH);
        fieldNames.add("Срок подписания договора участником (в днях)", PARTICIPANT_SIGN_CONTRACT_LIMIT_INPUT_XPATH);
        fieldNames.add("Срок заключения договора (в днях)", CONTRACT_COMPLETE_LIMIT_XPATH);
        fieldNames.add("Наименование товара, работ, услуг", LOT_GOODS_AND_SERVICES_NAME_XPATH);
        fieldNames.add("Единица измерения (код ОКЕИ)", OKEI_VALUE_XPATH);
        fieldNames.add("Цена за единицу измерения", UNIT_PRICE_XPATH);
        fieldNames.add("Количество", UNITS_QUANTITY_XPATH);
        fieldNames.add("Обеспечение договора", CONTRACT_AMOUNT_SUM_XPATH);
        fieldNames.add("Дополнительные сведения", PRODUCT_ADDITIONAL_INFO_XPATH);
        fieldNames.add("Шаг аукциона - От", AUCTION_MIN_STEP_XPATH);
        fieldNames.add("Шаг аукциона - До", AUCTION_MAX_STEP_XPATH);
        fieldNames.add("Время ожидания ценового предложения (мин)", AUCTION_NEXT_BID_WAITING_MINUTES_XPATH);
        fieldNames.add("Максимальная продолжительность аукциона (мин)", AUCTION_MAX_DURATION_IN_MINUTES_XPATH);
        fieldNames.add("Длительность второй фазы (мин)", AUCTION_SECOND_PHASE_DURATION_MINUTES_XPATH);
        fieldNames.add("Завершение аукциона при снижении до, % от НМЦ", AUCTION_LIMIT_PRICE_REDUCTION_PERCENT_XPATH);
        fieldNames.add("Обеспечение заявки (%)", APPLICATION_AMOUNT_PERCENT_XPATH);
        fieldNames.add("Обеспечение заявки (RUB)", APPLICATION_AMOUNT_SUM_XPATH);
        fieldNames.add("Обеспечение договора (%)", CONTRACT_AMOUNT_PERCENT_XPATH);
        fieldNames.add("Обеспечение договора (RUB)", CONTRACT_AMOUNT_SUM_XPATH);
        fieldNames.add("Наименование лота", LOT_NAME_XPATH);
        fieldNames.add("Вид обеспечения заявки", APPLICATION_GUARANTEE_TYPE_XPATH);
        fieldNames.add("Вид обеспечения договора", DOCUMENT_GUARANTEE_TYPE_XPATH);
        fieldNames.add("Сведения о заказчиках - Заказчик", CUSTOMER_NAME_XPATH);
        fieldNames.add("Начальная (максимальная цена) договора", CURRENT_CUSTOMER_MAX_PRICE_XPATH);
        fieldNames.add("Сведения о заказчиках - Количество", CURRENT_CUSTOMER_QUANTITY_XPATH);
        fieldNames.add("Сведения о заказчиках - Регион", DELIVERY_REGION_VALUE_XPATH);
        fieldNames.add("Сведения о заказчиках - Регион - поле множественного выбора", DELIVERY_REGION_INPUT_FIELD_XPATH);
        fieldNames.add("Сведения о заказчиках - Регион - поле ввода", DELIVERY_REGION_INPUT_FIELD_XPATH);
        fieldNames.add("Место поставки товара, выполнения работ, оказания услуг", DELIVERY_PLACE_XPATH);
        fieldNames.add("Срок поставки товара, выполнения работ, оказания услуг", DELIVERY_TERM_XPATH);
        fieldNames.add("Регистрационный номер плана", PLAN_REGISTRATION_NUMBER_XPATH);
        fieldNames.add("Номер позиции плана", PLAN_POSITION_NUMBER_XPATH);
        fieldNames.add("Отсрочка платежа (дней)", DEFEREMENT_OF_PAYMENT_DAYS_XPATH);
        fieldNames.add("Предоплата", PREPAYMENT_DAYS_XPATH);
        fieldNames.add("Требования к продукции", PRODUCT_REQUIREMENTS_XPATH);
        fieldNames.add("Адрес склада", STORAGE_ADDRESS_XPATH);
        fieldNames.add("Точная дата поставки (крайний срок)", DELIVERY_DATE_XPATH);
        fieldNames.add("Примечание", NOTE_XPATH);
        fieldNames.add("Промокод", PROMO_CODE_XPATH);
        fieldNames.add("Дополнительные поля - Порядок формирования начальной цены", AF_INIT_PRICE_ORDER_XPATH);
        fieldNames.add("Статус рассмотрения", APPROVAL_STATUS_XPATH);
        fieldNames.add("Тип согласования", APPROVAL_TYPE_XPATH);
        fieldNames.add("Заявка на согласование подана", DATE_OF_APPROVAL_REQUEST_XPATH);
        fieldNames.add("Дата согласования", DATE_OF_APPROVAL_PROCEDURE_XPATH);
        fieldNames.add("Плановая дата публикации закупки", PLANNED_PUBLICATION_DATE_0F_PURCHASE_XPATH);
        fieldNames.add("Дата принятия решения", DATE_OF_APPROVAL_XPATH);
        fieldNames.add("Новое дополнительное поле закупки", NEW_AF_PURCHASE_XPATH);
        fieldNames.add("Новое дополнительное поле лота", NEW_AF_LOT_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        commColumns.add("Организация", TABLE_ORGANIZATION_COLUMN_XPATH);
        commColumns.add("Ответственный", TABLE_RESPONSIBLE_COLUMN_XPATH);
        commColumns.add("Статус", TABLE_STATUS_COLUMN_XPATH);
        commColumns.add("Решение принято", TABLE_DECISION_IS_MADE_COLUMN_XPATH);
        commColumns.add("Комментарий", TABLE_COMMENT_COLUMN_XPATH);
        commColumns.add("Документ", TABLE_DOCUMENT_COLUMN_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Заполнить контактную информацию", FILL_CONTACT_INFO_XPATH);
        buttonNames.add("Найти заказчика", FIND_CUSTOMER_TO_ADD_XPATH);
        buttonNames.add("Добавить заказчика", ADD_CUSTOMER_TO_TABLE_ID);
        buttonNames.add("Добавить иформацию о товаре", ADD_PRODUCT_TO_TABLE_ID);
        buttonNames.add("Заполнить юридическим адресом", FILL_WITH_LEGAL_ADDRESS_BUTTON_XPATH);
        buttonNames.add("Удалить черновик", DELETE_DRAFT_BUTTON_XPATH);
        buttonNames.add("Сохранить черновик", SAVE_DRAFT_BUTTON_XPATH);
        buttonNames.add("Сохранить и проверить", SAVE_AND_CHECK_BUTTON_XPATH);
        buttonNames.add("Отправить на согласование", SEND_TO_APPROVAL_BUTTON_XPATH);
        buttonNames.add("Сведения о заказчиках - Регион - X", DELIVERY_REGION_X_BUTTONS_XPATH);
        buttonNames.add("Опубликовать", PUBLISH_BUTTON_XPATH);
        buttonNames.add("Добавить лот", ADD_NEW_LOT_BUTTON_XPATH);
        buttonNames.add("'Закрыть' - в окне 'Календарь'", DATEPICKER_HIDE_BUTTON_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Раздел [Проверка страницы]

    /**
     * Проверяет, что открыта именно страница [Формирование черновика извещения конкурса (режим создания/формирования)].
     */
    public void verifyCreateOrEditTenderPageOpened(boolean create) throws Exception {
        String pageName = create ? "Создание" : "Формирование";
        this.checkPageUrl(pageName + " черновика извещения конкурса - Angular");
    }

    // endregion

    // region Раздел [Общие сведения о закупке]

    /**
     * Устанавливает значение поля [Наименование].
     *
     * @param value требуемое значение поля
     */
    public void fillsFieldNameWithDate(String value, String number) throws Exception {
        String fieldValue = value + " " + timer.getPublishNoticeDateInFormatyyyyMMddhhmmss() + " №" + number;
        this.waitClearClickAndSendKeys(this.getBy(fieldNames.find("Наименование")), fieldValue);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [Наименованеи] значением [%s].%n", fieldValue);
    }

    // endregion

    // region Раздел [Сведения о порядке подачи заявок]

    // endregion

    // region Раздел [Сведения о порядке работы комиссии]

    // endregion

    // region Раздел [Сведения о предоставлении документации]

    // endregion

    // region Раздел [Сведения о порядке предоставления разъяснений]

    // endregion

    // region Раздел [Сведения о контактном лице]

    /**
     * Устанавливает значение поля [Ответственное должностное лицо].
     *
     * @param orderNumber порядковый номер из списка значений сверху-вниз, начиная со второго ( первый это прочерк )
     */
    public void fillResponciblePerson(String orderNumber) throws Exception {
        SelenideElement value = $(this.getBy(RESPONSIBLE_PERSON_VALUE_XPATH));
        String oldValue = value.waitUntil(exist, timeout, polling).getText();
        String newValue = oldValue;
        System.out.printf(
                "[ИНФОРМАЦИЯ]: передан № значения в списке [Ответственное должностное лицо] [%s].%n", orderNumber);
        this.scrollToCenter(this.getBy(CONTACT_PERSON_INFO_BLOCK_XPATH));

        for (int i = 0; i < 50; i++) {
            System.out.printf("[ИНФОРМАЦИЯ]: попытка № [%d] открыть список [Ответственное должностное лицо].%n", i + 1);
            $(this.getBy(RESPONSIBLE_PERSON_LIST_CLOSED_XPATH)).waitUntil(clickable, timeout, polling).click();
            TimeUnit.SECONDS.sleep(3);
            SelenideElement valueInList =
                    $(this.getBy(String.format(RESPONSIBLE_PERSON_VALUE_IN_OPENED_LIST_XPATH, orderNumber)));
            Assert.assertTrue("[ОШИБКА]: элемент списка с таким порядковым № отсутствует", valueInList.exists());
            if (valueInList.isDisplayed()) {
                valueInList.waitUntil(clickable, timeout, polling).click();
                TimeUnit.SECONDS.sleep(3);
                newValue = value.waitUntil(exist, timeout, polling).getText();
                if (!newValue.equals(oldValue)) break;
            }
        }

        Assert.assertNotEquals("Поле [Ответственное должностное лицо] не заполнено", oldValue, newValue);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [Ответственное должностное лицо] значением [%s].%n",
                newValue);
    }

    /**
     * Устанавливает значение поля [Ответственное должностное лицо].
     *
     * @param certificateName имя сертификата из файла конфигурации
     */
    public void fillResponciblePersonFieldUsingCertificateNameFromConfigFile(String certificateName) throws Exception {
        SelenideElement value = $(this.getBy(RESPONSIBLE_PERSON_VALUE_XPATH));
        String oldValue = value.waitUntil(exist, timeout, polling).getText();
        String newValue = oldValue;
        System.out.printf("[ИНФОРМАЦИЯ]: передано имя сертификата из файла конфигурации [%s].%n", certificateName);
        this.scrollToCenter(this.getBy(CONTACT_PERSON_INFO_BLOCK_XPATH));

        for (int i = 0; i < 50; i++) {
            System.out.printf("[ИНФОРМАЦИЯ]: попытка № [%d] открыть список [Ответственное должностное лицо].%n", i + 1);
            $(this.getBy(RESPONSIBLE_PERSON_LIST_CLOSED_XPATH)).waitUntil(clickable, timeout, polling).click();
            TimeUnit.SECONDS.sleep(3);
            SelenideElement valueInList =
                    $(this.getBy(String.format(RESPONSIBLE_PERSON_VALUE_IN_OPENED_LIST_BY_NAME_XPATH, certificateName)));
            Assert.assertTrue("[ОШИБКА]: элемент списка с именем сертификата отсутствует", valueInList.exists());
            if (valueInList.isDisplayed()) {
                valueInList.waitUntil(clickable, timeout, polling).click();
                TimeUnit.SECONDS.sleep(3);
                newValue = value.waitUntil(exist, timeout, polling).getText();
                if (!newValue.equals(oldValue)) break;
            }
        }

        Assert.assertNotEquals("Поле [Ответственное должностное лицо] не заполнено", oldValue, newValue);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [Ответственное должностное лицо] значением [%s].%n",
                newValue);
    }

    // endregion

    // region Раздел [Сведения об условиях участия]

    /**
     * Добавляет организации в таблицу [Список приглашённых участников] в зависимости от типа автоматического теста.
     */
    public void addInvitedOrganizationsToList() throws Exception {

        // Участники не должны пересекаться для параллельно идущих тестов на Production ( SSO ) ------------------------
        Map<String, List<String>> innLists = new HashMap<>();
        //--------------------------------------------------------------------------------------------------------------
        innLists.put("Full Tender On Production",
                Arrays.asList(
                        config.getConfigParameter("OnProdSupplier2Inn"),
                        config.getConfigParameter("OnProdSupplier3Inn")));
        //--------------------------------------------------------------------------------------------------------------
        innLists.put("Full Auction In Two Parts On Production",
                Arrays.asList(
                        config.getConfigParameter("OnProdSupplier1Inn"),
                        config.getConfigParameter("OnProdSupplier4Inn"),
                        config.getConfigParameter("OnProdSupplier5Inn")));
        //--------------------------------------------------------------------------------------------------------------

        List<String> innList = innLists.get(config.getParameter("TestType"));
        int expected = 0;

        for (String inn : innList) {
            expected++;
            this.addInvitedOrganizationToList(inn, expected);
        }
    }

    /**
     * Добавляет организацию в таблицу [Список приглашённых участников].
     *
     * @param organizationInn ИНН организации, по которому осуществляется поиск
     * @param expected        ожидаемое количество записей в таблице [Список приглашённых участников] после добавления
     */
    private void addInvitedOrganizationToList(String organizationInn, int expected) throws Exception {
        // Открываем окно [Выбор организации] --------------------------------------------------------------------------
        $(this.getBy(ADD_PARTICIPANT_BUTTON_XPATH)).waitUntil(visible, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        //--------------------------------------------------------------------------------------------------------------

        //==== Окно [Выбор организации] открыто ========================================================================
        $(this.getBy(SELECT_ORGANIZATION_WINDOW_TITLE_XPATH)).waitUntil(visible, timeout, polling).click();
        //--------------------------------------------------------------------------------------------------------------
        $(this.getBy(INN_FIELD_XPATH)).sendKeys(organizationInn);
        $(this.getBy(INVITED_ORGANIZATION_SEARCH_BUTTON_XPATH)).click();
        this.waitLoadingImage();
        $(this.getBy(INVITED_ORGANIZATION_SEARCH_BUTTON_XPATH)).waitUntil(visible, timeout, polling).shouldBe(enabled);
        int rows = $$(this.getBy(NUMBER_OF_ROWS_IN_SEARCH_RESULTS_XPATH)).size();
        Assert.assertEquals("[ОШИБКА]: в результатах поиска должна быть одна строка", 1, rows);
        $(this.getBy(FIRST_ROW_IN_SEARCH_RESULTS_XPATH)).waitUntil(visible, timeout, polling).click();
        TimeUnit.SECONDS.sleep(2);
        //--------------------------------------------------------------------------------------------------------------
        $(this.getBy(INVITED_ORGANIZATION_SELECT_BUTTON_XPATH)).waitUntil(visible, timeout, polling).shouldBe(enabled).
                click();
        this.waitLoadingImage();
        $(this.getBy(SELECT_ORGANIZATION_WINDOW_TITLE_XPATH)).waitUntil(disappear, timeout, polling);
        //==== Окно [Выбор организации] закрыто ========================================================================

        // Проверяем список приглашенных участников --------------------------------------------------------------------
        rows = $$(this.getBy(ROW_IN_LIST_OF_INVITED_PATICIPANTS)).size();
        Assert.assertEquals(
                "[ОШИБКА]: количество записей в списке приглашенных участников неверно", expected, rows);
        //--------------------------------------------------------------------------------------------------------------
    }

    /**
     * Проверяет количество записей в таблице [Список приглашённых участников].
     *
     * @param numberOfRows ожидаемое количество записей в таблице [Список приглашённых участников]
     */
    public void checkNumberOfRowsFromInvitedOrganizationsTable(int numberOfRows) {
        int rows = $$(this.getBy(ROW_IN_LIST_OF_INVITED_PATICIPANTS)).size();
        Assert.assertEquals(
                "[ОШИБКА]: количество записей в списке приглашенных участников неверно", numberOfRows, rows);
    }

    /**
     * Проверяет соответствие ожидаемых ИНН реальному содержимому столбца [ИНН]
     * в таблице [Список приглашённых участников].
     *
     * @param expectedInns ожидаемый список ИНН в таблице [Список приглашённых участников]
     */
    public void checkInnsFromInvitedOrganizationsTable(List<String> expectedInns) {
        ElementsCollection actualInns = $$(this.getBy(INN_COLUMN_IN_LIST_OF_INVITED_PATICIPANTS));
        Assert.assertEquals(
                "[ОШИБКА]: количество ИНН в переданном списке не соотвествует количеству ИНН в таблице",
                expectedInns.size(), actualInns.size());

        int found = 0;

        for (String expectedInn : expectedInns) {
            System.out.printf("[ИНФОРМАЦИЯ]: производим поиск ИНН со значением [%s]...%n", expectedInn);
            for (SelenideElement actualInn : actualInns) {
                String actualInnText = actualInn.getText();
                System.out.printf("[ИНФОРМАЦИЯ]: обнаружен ИНН со значением [%s]...%n", actualInnText);
                if (actualInnText.equals(expectedInn)) {
                    System.out.println("[ИНФОРМАЦИЯ]: ИНН найден успешно.");
                    found++;
                    break;
                }
            }
        }

        System.out.printf("[ИНФОРМАЦИЯ]: успешно найдено [%d] ИНН из [%d].%n", found, expectedInns.size());
        Assert.assertEquals(
                "[ОШИБКА]: не все ИНН из переданного списка найдены в таблице", found, expectedInns.size());
    }

    // endregion

    // region Раздел [Информация о лоте] -> закладка [Общие сведения]

    /**
     * Устанавливает значение поля [Тип подачи ценового предложения].
     *
     * @param fieldValue имя сертификата из файла конфигурации
     */
    public void fillApplicationOfferField(String fieldValue) throws Exception {
        SelenideElement value = $(this.getBy(APPLICATION_OFFER_XPATH));
        String oldValue = value.waitUntil(exist, timeout, polling).getText();
        String newValue = oldValue;
        System.out.printf("[ИНФОРМАЦИЯ]: значение для заполнения поля [%s].%n", fieldValue);
        this.scrollToCenter(this.getBy(APPLICATION_OFFER_XPATH));

        for (int i = 0; i < 50; i++) {
            System.out.printf("[ИНФОРМАЦИЯ]: попытка № [%d] открыть список [Тип пожачи ценового предложения].%n", i + 1);
            $(this.getBy(APPLICATION_OFFER_XPATH)).waitUntil(clickable, timeout, polling).click();
            TimeUnit.SECONDS.sleep(3);
            SelenideElement valueInList =
                    $(this.getBy(String.format(APPLICATION_OFFER_VALUES_IN_OPEN_LIST_XPATH, fieldValue)));
            Assert.assertTrue("[ОШИБКА]: элемент списка с данным значение отстутствует", valueInList.exists());
            if (valueInList.isDisplayed()) {
                valueInList.waitUntil(clickable, timeout, polling).click();
                TimeUnit.SECONDS.sleep(3);
                newValue = value.waitUntil(exist, timeout, polling).getText();
                if (!newValue.equals(oldValue)) break;
            }
        }

        Assert.assertNotEquals("Поле [Тип пожачи ценового предложения] не заполнено", oldValue, newValue);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [Тип пожачи ценового предложения] значением [%s].%n",
                newValue);
    }

    // endregion

    // region Раздел [Информация о лоте] -> закладка [Товары, работы и услуги]

    /**
     * Устанавливает значение поля [Код ОКПД2].
     *
     * @param lotOkpd2Value требуемое значение поля
     */
    public void setLotOkpd2(String lotOkpd2Value) throws Exception {
        $(this.getBy(LOT_OKPD_2_INPUT_XPATH)).waitUntil(exist, timeout, polling).sendKeys(lotOkpd2Value);
        TimeUnit.SECONDS.sleep(3); // Ожидание, пока подтянется значение из справочника (контрол медленный)
        SelenideElement tab = $(this.getBy(LOT_TYPES_CODES_TAB_XPATH));
        this.scrollToCenter(tab);
        tab.waitUntil(visible, timeout, polling).click();
    }

    /**
     * Проверяет расшифровку значения кода в поле [Код ОКПД2] для текущего лота.
     *
     * @param value ожидаемое значение поля
     */
    public void checkLotOkpd2(String value) {
        $(this.getBy(LOT_OKPD_2_DESCRIPTION_XPATH)).waitUntil(exist, timeout, polling).shouldHave(text(value));
    }

    /**
     * Устанавливает значение поля [Код ОКВЭД2].
     *
     * @param lotOkved2Value требуемое значение поля
     */
    public void setLotOkved2(String lotOkved2Value) throws Exception {
        $(this.getBy(LOT_OKVED_2_INPUT_XPATH)).waitUntil(exist, timeout, polling).sendKeys(lotOkved2Value);
        TimeUnit.SECONDS.sleep(3); // Ожидание, пока подтянется значение из справочника (контрол медленный)
        SelenideElement tab = $(this.getBy(LOT_TYPES_CODES_TAB_XPATH));
        this.scrollToCenter(tab);
        tab.waitUntil(visible, timeout, polling).click();
    }

    /**
     * Проверяет расшифровку значения кода в поле [Код ОКВЭД2] для текущего лота.
     *
     * @param value ожидаемое значение поля
     */
    public void checkLotkved2(String value) {
        $(this.getBy(LOT_OKVED_2_DESCRIPTION_XPATH)).waitUntil(exist, timeout, polling).shouldHave(text(value));
    }

    /**
     * Заполняет значение поля [Единица измерения (код ОКЕИ)] значением с указанным порядковым номером из списка.
     *
     * @param okeiNumber порядковый номер в списке сверху-вниз
     */
    public void fillOkeiCode(String okeiNumber) throws Exception {
        SelenideElement value = $(this.getBy(OKEI_VALUE_XPATH));
        String oldValue = value.waitUntil(exist, timeout, polling).getValue();
        String newValue = oldValue;

        for (int i = 0; i < 50; i++) {
            System.out.printf("[ИНФОРМАЦИЯ]: попытка № [%d] открыть список [Единица измерения (код ОКЕИ)].%n", i + 1);
            $(this.getBy(OKEI_LIST_CLOSED_XPATH)).waitUntil(visible, timeout, polling).click();
            TimeUnit.SECONDS.sleep(3);
            String xpathToOkeiName = String.format(OKEI_VALUE_IN_OPENED_LIST_XPATH, okeiNumber);
            SelenideElement valueInList = $(this.getBy(xpathToOkeiName));
            if (valueInList.isDisplayed()) {
                valueInList.waitUntil(visible, timeout, polling).click();
                TimeUnit.SECONDS.sleep(3);
                newValue = value.waitUntil(exist, timeout, polling).getValue();
                if (!newValue.equals(oldValue)) break;
            }
        }

        Assert.assertNotEquals("[ОШИБКА]: поле [Единица измерения (код ОКЕИ)] не заполнено", oldValue, newValue);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [Единица измерения (код ОКЕИ)] значением [%s].%n",
                newValue);
    }

    /**
     * Заполняет значение поля [Единица измерения (код ОКЕИ)] требуемым текстовым значением из списка.
     *
     * @param textValue требуемое текстовое значение из списка
     */
    public void fillOkeiCodeUsingTextValueInOkeiList(String textValue) throws Exception {
        SelenideElement value = $(this.getBy(OKEI_VALUE_XPATH));
        String oldValue = value.waitUntil(exist, timeout, polling).getValue();
        String newValue = oldValue;

        for (int i = 0; i < 50; i++) {
            System.out.printf("[ИНФОРМАЦИЯ]: попытка № [%d] открыть список [Единица измерения (код ОКЕИ)].%n", i + 1);
            $(this.getBy(OKEI_LIST_CLOSED_XPATH)).waitUntil(visible, timeout, polling).click();
            TimeUnit.SECONDS.sleep(3);
            String xpathToValueInList = String.format(OKEI_TEXT_IN_OPENED_LIST_XPATH, textValue);
            SelenideElement valueInList = $(this.getBy(xpathToValueInList));
            if (valueInList.isDisplayed()) {
                valueInList.waitUntil(visible, timeout, polling).click();
                TimeUnit.SECONDS.sleep(3);
                newValue = value.waitUntil(exist, timeout, polling).getValue();
                if (!newValue.equals(oldValue)) break;
            }
        }

        Assert.assertNotEquals("[ОШИБКА]: поле [Единица измерения (код ОКЕИ)] не заполнено", oldValue, newValue);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [Единица измерения (код ОКЕИ)] значением [%s].%n",
                newValue);
    }

    /**
     * Проверяет количество столбцов в таблице [Товары].
     *
     * @param columns требуемое количество столбцов в таблице [Товары]
     */
    public void checkNumberOfColumnsFromProductsTable(String columns) throws Exception {
        TimeUnit.SECONDS.sleep(5);
        int columnsExpected = Integer.parseInt(columns);
        int columnsActual = $$(this.getBy(PRODUCTS_TABLE_ALL_COLUMNS_XPATH)).size();
        Assert.assertEquals(
                "[ОШИБКА]: неверное количество столбцов в таблице [Товары]", columnsExpected, columnsActual);
        System.out.printf("[ИНФОРМАЦИЯ]: произведена проверка количества столбцов [%s] в таблице [Товары].%n", columns);
    }

    /**
     * Проверяет количество строк в таблице [Товары].
     *
     * @param rows требуемое количество строк в таблице [Товары]
     */
    public void checkNumberOfRowsFromProductsTable(String rows) throws Exception {
        TimeUnit.SECONDS.sleep(5);
        int rowsExpected = Integer.parseInt(rows);
        int rowsActual = $$(this.getBy(PRODUCTS_TABLE_ALL_ROWS_XPATH)).size();
        Assert.assertEquals(
                "[ОШИБКА]: неверное количество строк в таблице [Товары]", rowsExpected, rowsActual);
        System.out.printf("[ИНФОРМАЦИЯ]: произведена проверка количества строк [%s] в таблице [Товары].%n", rows);
    }

    // endregion

    // region Раздел [Информация о лоте] -> закладка [Сведения о переторжке]

    // endregion

    // region Раздел [Информация о лоте] -> закладка [Сведения о заказчиках]

    /**
     * Устанавливает значение поля [Заказчик] выбирая первый по порядку элемент в списке значений ниже "-----".
     */
    public void changeLotCustomerNameToSecondInList() throws Throwable {
        $(this.getBy(CUSTOMER_NAME_XPATH)).waitUntil(exist, timeout, polling).shouldBe(visible);
        this.clickInElementJS(this.getBy(CUSTOMER_NAME_XPATH));
        TimeUnit.SECONDS.sleep(3);
        if (!$(this.getBy(FIRST_CUSTOMER_NAME_FROM_POPUP_XPATH)).waitUntil(exist, timeout, polling).isDisplayed()) {
            this.clickInElementJS(this.getBy(CUSTOMER_NAME_XPATH));
            TimeUnit.SECONDS.sleep(3);
        }
        $(this.getBy(FIRST_CUSTOMER_NAME_FROM_POPUP_XPATH)).waitUntil(visible, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);

    }

    /**
     * Устанавливает значение поля [Вид обеспечения заявки] выбирая полученое от теста значение.
     */
    public void changeLotApplicationGuaranteeType(String guaranteeType) throws Exception {
        SelenideElement value = $(this.getBy(APPLICATION_GUARANTEE_VALUE_XPATH));
        String oldValue = value.waitUntil(exist, timeout, polling).getText();
        String newValue = oldValue;
        System.out.printf("[ИНФОРМАЦИЯ]: устанавливаем тип обеспечения заявки [%s].%n", guaranteeType);
        this.scrollToCenter(this.getBy(LOT_INFO_BLOCK_XPATH));

        for (int i = 0; i < 50; i++) {
            System.out.printf("[ИНФОРМАЦИЯ]: попытка № [%d] открыть список [Вид обеспечения заявки].%n", i + 1);
            $(this.getBy(APPLICATION_GUARANTEE_LIST_CLOSED_XPATH)).waitUntil(clickable, timeout, polling).click();
            TimeUnit.SECONDS.sleep(3);
            SelenideElement valueInList =
                    $(this.getBy(String.format(APPLICATION_GUARANTEE_VALUE_IN_OPENED_LIST_BY_NAME_XPATH, guaranteeType)));
            Assert.assertTrue("[ОШИБКА]: элемент списка с типом обеспечсения отсутствует", valueInList.exists());
            if (valueInList.isDisplayed()) {
                valueInList.waitUntil(clickable, timeout, polling).click();
                TimeUnit.SECONDS.sleep(3);
                newValue = value.waitUntil(exist, timeout, polling).getText();
                if (!newValue.equals(oldValue)) break;
            }
        }

        Assert.assertNotEquals("Поле [Вид обеспеченния заявки] не заполнено", oldValue, newValue);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [Вид обеспечения заявки] значением [%s].%n", newValue);
    }

    /**
     * Устанавливает значение поля [Вид обеспечения договора] выбирая полученое от теста значение.
     */
    public void changeLotDocumentGuaranteeType(String guaranteeType) throws Exception {
        SelenideElement value = $(this.getBy(DOCUMENT_GUARANTEE_VALUE_XPATH));
        String oldValue = value.waitUntil(exist, timeout, polling).getText();
        String newValue = oldValue;
        System.out.printf("[ИНФОРМАЦИЯ]: устанавливаем тип обеспечения договора [%s].%n", guaranteeType);
        this.scrollToCenter(this.getBy(LOT_INFO_BLOCK_XPATH));

        for (int i = 0; i < 50; i++) {
            System.out.printf("[ИНФОРМАЦИЯ]: попытка № [%d] открыть список [Вид обеспечения договора].%n", i + 1);
            $(this.getBy(DOCUMENT_GUARANTEE_LIST_CLOSED_XPATH)).waitUntil(clickable, timeout, polling).click();
            TimeUnit.SECONDS.sleep(3);
            SelenideElement valueInList =
                    $(this.getBy(String.format(DOCUMENT_GUARANTEE_VALUE_IN_OPENED_LIST_BY_NAME_XPATH, guaranteeType)));
            Assert.assertTrue("[ОШИБКА]: элемент списка с типом обеспечсения отсутствует", valueInList.exists());
            if (valueInList.isDisplayed()) {
                valueInList.waitUntil(clickable, timeout, polling).click();
                TimeUnit.SECONDS.sleep(3);
                newValue = value.waitUntil(exist, timeout, polling).getText();
                if (!newValue.equals(oldValue)) break;
            }
        }

        Assert.assertNotEquals("Поле [Вид обеспеченния договора] не заполнено", oldValue, newValue);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [Вид обеспечения договора] значением [%s].%n", newValue);
    }

    // endregion

    // region Раздел [Информация о лотах] -> закладка [Документы лота]

    /**
     * Проверяет, что таблица [Документы лота] пуста.
     */
    public void checkThatTheLotDocumentsTableIsEmpty() {
        System.out.println("[ИНФОРМАЦИЯ]: ожидаем надпись [Документы не прикреплены] в таблице [Документы лота].");
        $(this.getBy(LOT_DOCUMENTS_TABLE_EMPTY_XPATH)).shouldBe(visible);
        System.out.println("[ИНФОРМАЦИЯ]: надпись [Документы не прикреплены] отображается в таблице [Документы лота].");
    }

    /**
     * Проверяет количество строк в таблице [Заказчики].
     *
     * @param rows требуемое количество строк в таблице [Заказчики]
     */
    public void checkNumberOfRowsFromCustomersTable(String rows) throws Exception {
        TimeUnit.SECONDS.sleep(5);
        int rowsExpected = Integer.parseInt(rows);
        int rowsActual = $$(this.getBy(CUSTOMERS_TABLE_ALL_ROWS_XPATH)).size();
        Assert.assertEquals(
                "[ОШИБКА]: неверное количество строк в таблице [Заказчики]", rowsExpected, rowsActual);
        System.out.printf("[ИНФОРМАЦИЯ]: произведена проверка количества строк [%s] в таблице [Заказчики].%n", rows);
    }

    // endregion

    // region Раздел [Документы]

    /**
     * Перемещает страницу к заголовку раздела [Документы].
     */
    public void scrollToDocumentsPartition() {
        $(By.xpath(DOWNLOAD_FILE_TRADE_XPATH)).waitUntil(visible, timeout, polling).scrollTo();
    }

    /**
     * Прикрепляет файл в разделе [Документы].
     *
     * @param fileName имя файла
     */
    public void uploadFileIntoDocuments(String fileName) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: прикрепляем файл [%s] в разделе [Документы].%n", fileName);
        $(this.getBy(UPLOAD_FILE_TRADE_XPATH)).waitUntil(exist, timeout, polling).sendKeys(fileName);
        TimeUnit.SECONDS.sleep(15);
    }

    /**
     * Проверяет ссылку для скачивания прикрепленного файла в разделе [Документы].
     *
     * @param fileName имя файла
     */
    public void checkLinkToDownloadFileIntoDocuments(String fileName) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем ссылку для скачивания прикрепленного файла [%s] в разделе [Документы].%n",
                fileName);
        this.getCurrentServerVersion();
        $(this.getBy(DOWNLOAD_FILE_TRADE_XPATH)).waitUntil(visible, timeout, polling).shouldHave(text(fileName));
    }

    /**
     * Делает клик по ссылке на первый прикрепленный к извещению документ, загружает его и проверяет,
     * что файл загружен успешно.
     */
    public void downloadFirstAttachedDocumentWithVerification() throws Exception {
        this.downloadFileByLink(By.xpath(DOWNLOAD_FILE_TRADE_XPATH));
    }

    // endregion

    // region Раздел [Дополнительные поля]

    /**
     * Выбирает фиксированное значение [Российский рубль (RUB)] для дополнительного поля типа 'Валюта'.
     *
     *  @param fieldName имя дополнительного поля
     */
    public void fillCurrencyAdditionalFieldWithRussianRoubleValue(String fieldName) throws Exception {
        SelenideElement collapsedList = $(this.getBy(String.format(NEW_AF_LOT_POSITION_LIST_CLOSED_XPATH, fieldName)));
        SelenideElement desiredValue = $(this.getBy(NEW_AF_LOT_POSITION_VALUE_IN_OPENED_LIST_XPATH));
        SelenideElement selectedValue = $(this.getBy(String.format(NEW_AF_LOT_POSITION_VALUE_XPATH, fieldName)));
        this.waitForListOpensAndSelectDesiredValue(fieldName, collapsedList, desiredValue, selectedValue);
    }

    // endregion

    // region Раздел [Рабочие группы]

    /**
     * Проверяет, что требуемая рабочая группа отмечена в таблице [Рабочие группы].
     */
    public void checkThatTheDesiredWorkGroupSelected() {
        String groupName = config.getConfigParameter("RzhdWorkGroupNameOnUi");
        String locator = String.format(IS_GROUP_SELECTED_XPATH, groupName);
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем группу [%s] в таблице [Рабочие группы].%n", groupName);
        System.out.printf("[ИНФОРМАЦИЯ]: сформирован локатор [%s] для таблицы [Рабочие группы].%n", locator);
        SelenideElement checkBox = $(this.getBy(locator));
        checkBox.waitUntil(visible, timeout, polling);
        Assert.assertTrue(String.format("[ОШИБКА]: группа [%s] в таблице [Рабочие группы] не отмечена", groupName),
                checkBox.isSelected());
        System.out.printf("[ИНФОРМАЦИЯ]: группа [%s] в таблице [Рабочие группы] отмечена.%n", groupName);
    }

    // endregion

    // region Раздел [Комиссия]

    /**
     * Проверяет текст ячейки в таблице [Комиссия] для столбца с указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param lineNumber порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     */
    public void verifyCellByNameInLineByNumberFromCommissionTableForText(
            String columnName, String lineNumber, String cellValue) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверка текста ячейки в таблице [Комиссия] в столбце [%s], " +
                "в строке с номером [%s] - ожидаемый текст [%s].%n", columnName, lineNumber, cellValue);
        ElementsCollection lines = $$(this.getBy(commColumns.find(columnName)));
        Assert.assertTrue("[ОШИБКА]: таблица [Комиссия] не содержит строк", lines.size() > 0);
        int line = Integer.parseInt(lineNumber) - 1;
        Assert.assertTrue(String.format("[ОШИБКА]: передан некорректный номер строки [%s]", lineNumber),
                line >= 0 && line < lines.size());
        String actualValue = lines.get(line).getText().trim();
        if (cellValue.trim().isEmpty())
            Assert.assertTrue(
                    String.format("[ОШИБКА]: ожидалась пустая ячейка, реальный текст ячейки [%s]", actualValue),
                    actualValue.isEmpty());
        else
            Assert.assertTrue(String.format(
                    "[ОШИБКА]: ожидаемый текст ячейки [%s], реальный текст ячейки [%s]", cellValue, actualValue),
                    actualValue.contains(cellValue));
    }

    //endregion

    // region Блок кнопок внизу формы ([Сохранить черновик], [Опубликовать])

    /**
     * Публикует извещение со страницы [Формирование черновика извещения ...].
     */
    public void publishNotice() throws Exception {
        // Нажимаем на кнопку "Опубликовать"
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        this.scrollToCenter(this.getBy(PUBLISH_BUTTON_XPATH));
        this.logButtonNameToPress("Опубликовать");
        $(this.getBy(PUBLISH_BUTTON_XPATH)).waitUntil(clickable, longtime, polling).click();
        this.logPressedButtonName("Опубликовать");

        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        // Нажимаем на кнопку "Продолжить" в диалоговом окне "Публикация извещения"
        publishAngularDialog.continueThePublication();

        // Нажимаем на кнопку "Перейти к карточке закупки" в диалоговом окне "Опубликовано"
        publishedAngularDialog.goToPurchaseCard();

        // Проверяем статус закупки "Опубликована" и сохраняем номер извещения
        viewNoticePage.checkPurchaseStatusAndSavePurchaseNumber("Опубликована");

        // Сохраняем дату и время публикации извещения
        viewNoticePage.savePublishNoticeDateAndTime();

        // Проверяем наименование закупки и сохраняем ее в параметрах текущего теста
        viewNoticePage.checkAndSavePurchaseName("View");

        // Проверяем наличие записей в диалоговом окне "История"
        viewNoticePage.checkHistoryRecordsPresence("Публикация процедуры");

        // Ждем пока лот №1 перейдёт в статус "Прием заявок"
        viewNoticePage.waitLot1Status("Прием заявок");
    }

    /**
     * Публикует извещение со страницы [Формирование черновика извещения предварительного отбора ...].
     */
    public void publishNoticePreliminarySelection() throws Exception {
        // Нажимаем на кнопку "Опубликовать"
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        this.scrollToCenter(this.getBy(PUBLISH_BUTTON_XPATH));
        this.logButtonNameToPress("Опубликовать");
        $(this.getBy(PUBLISH_BUTTON_XPATH)).waitUntil(clickable, longtime, polling).click();
        this.logPressedButtonName("Опубликовать");

        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        // Нажимаем на кнопку "Продолжить" в диалоговом окне "Публикация извещения"
        publishAngularDialog.continueThePublication();

        // Нажимаем на кнопку "Перейти к карточке закупки" в диалоговом окне "Опубликовано"
        publishedAngularDialog.goToPurchaseCard();

        // Проверяем статус процедуры  "Опубликована" и сохраняем номер извещения
        viewNoticePage.checkProcedureStatusAndSavePurchaseNumber("Опубликована");

        // Сохраняем дату и время публикации извещения
        viewNoticePage.savePublishNoticeDateAndTime();

        // Проверяем наименование закупки и сохраняем ее в параметрах текущего теста
        viewNoticePage.checkAndSaveProcedureName();

        // Проверяем наличие записей в диалоговом окне "История"
        viewNoticePage.checkHistoryRecordsPresence("Публикация процедуры");

        // Ждем пока предмет предварительного отбора №1 перейдёт в статус "Прием заявок"
        viewNoticePage.waitPreselectionSubject1Status("Прием заявок");
    }

    /**
     * Сохраняет черновик извещения о полном конкурсе.
     */
    public void saveTenderDraft() throws Exception {
        // Щелчок по полю "Сервер: " для скроллинга
        $(this.getBy(SERVER_VERSION_XPATH)).waitUntil(visible, timeout, polling).click();

        // Щелчок по кнопке "Сохранить черновик"
        this.logButtonNameToPress("Сохранить черновик");
        this.clickInElementJS(this.getBy(SAVE_DRAFT_BUTTON_XPATH));
        this.logPressedButtonName("Сохранить черновик");
        TimeUnit.SECONDS.sleep(1);
        this.waitForPageLoad("35");

        // Кликаем по кнопке "Продолжить формирование черновика" в диалоговом окне "Сохранено"
        savedAngularDialog.clickOnContinueButton();
        this.waitForPageLoad();

        this.savePurchaseNoticeIdFromTenderDraft();

        this.checkAndSavePurchaseName();
    }

    /**
     * Сохраняет номер закупки из URL.
     */
    private void savePurchaseNoticeIdFromTenderDraft() {
        String currentUrl = url();
        String tenderId = currentUrl.split("/")[9];
        config.setParameter("PurchaseNumber", tenderId);
    }

    /**
     * Проверяте, что поле [Номер закупки] не пустое и запоминает его.
     */
    private void checkAndSavePurchaseName() {
        String purchaseName;
        purchaseName = $(this.getBy(ORDER_NAME_GET_TEXT_ID)).
                waitUntil(visible, timeout, polling).getValue();
        Assert.assertFalse(
                ">>> (checkAndSavePurchaseName) Пустое наименование закупки в сохраненном извещении !",
                purchaseName == null || purchaseName.trim().isEmpty());
        config.setParameter("PurchaseName", purchaseName);
        System.out.printf(">>> (checkAndSavePurchaseName) Наименование закупки: [%s].%n", purchaseName);
    }

    // endregion

    // region Общие методы для работы с элементами этой страницы

    /**
     * Проверяет поле на невозможность редактирования.
     *
     * @param fieldName имя поля
     */
    public void checkFieldForTheImpossibilityOfEditing(String fieldName) {
        SelenideElement field = $(this.getBy(fieldNames.find(fieldName)));
        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] на невозможность редактирования.%n", fieldName);
        Assert.assertTrue(String.format("[ОШИБКА]: поле [%s] возможно редактировать", fieldName), field.is(readonly));
    }

    /**
     * Устанавливает фокус ввода (курсор) в поле с указанным именем.
     *
     * @param fieldName имя поля
     */
    public void clickOnFieldByName(String fieldName) throws Exception {
        $(this.getBy(fieldNames.find(fieldName))).waitUntil(visible, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        System.out.printf("[ИНФОРМАЦИЯ]: произведен переход в поле [%s].%n", fieldName);
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
     * Устанавливает или сбрасывает флажок с указанным именем.
     *
     * @param checkBoxName имя флажка
     */
    public void setCheckBoxValue(String checkBoxName) {
        String message = "[ОШИБКА]: состояние флажка [%s] не изменилось после щелчка мышью";

        boolean oldValue = this.getCheckBoxValue(checkBoxName);
        $(this.getBy(checkBoxNames.find(checkBoxName))).waitUntil(exist, timeout, polling).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено изменение состояния флажка [%s].%n", checkBoxName);
        boolean newValue = this.getCheckBoxValue(checkBoxName);
        System.out.printf("[ИНФОРМАЦИЯ]: флажок [%s] находится %s отмеченном состоянии.%n",
                checkBoxName, ((newValue) ? "в" : "в не"));
        Assert.assertNotEquals(String.format(message, checkBoxName), oldValue, newValue);
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
     * Проверяет, что флажок с указанным именем отображается на странице.
     *
     * @param checkBoxName имя флажка
     */
    public void verifyCheckBoxIsDisplayed(String checkBoxName) {
        SelenideElement checkBox = $(this.getBy(checkBoxNames.find(checkBoxName)));
        checkBox.waitUntil(exist, timeout, polling);
        Assert.assertTrue(String.format("[ОШИБКА]: флажок [%s] не отображается на странице", checkBoxName),
                checkBox.isDisplayed());
        System.out.printf("[ИНФОРМАЦИЯ]: флажок [%s] отображается на странице.%n", checkBoxName);
    }

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
     * Открывает свернутый блок полей на форме.
     *
     * @param blockName имя блока
     */
    public void openBlock(String blockName) throws Exception {
        SelenideElement block = $(this.getBy(blockNames.find(blockName)));
        this.scrollToCenter(block);
        block.shouldBe(visible).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено открытие блока полей [%s].%n", blockName);
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * Устанавливает значение полей с элементами увеличения и уменьшения значения (стрелки вверх и вниз справа от поля).
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     */
    public void setKendoNumericTextBoxField(String fieldName, String value) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
        this.setKendoNumericTextBox(fieldName, this.getBy(fieldNames.find(fieldName)), value);
        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * Переключает на требуемую закладку.
     *
     * @param tabName имя закладки
     */
    public void switchToTab(String tabName) throws Exception {
        SelenideElement tab = $(this.getBy(tabNames.find(tabName)));
        this.scrollToCenter(tab);
        tab.shouldBe(clickable);
        this.clickInElementJS(tab);
        TimeUnit.SECONDS.sleep(1);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие на закладку [%s].%n", tabName);
    }

    /**
     * Выбирает в переключателе опцию с указанным именем.
     *
     * @param radioButtonPosition имя опции
     */
    public void selectRadioButton(String radioButtonPosition) throws Exception {
        $(this.getBy(radioButtonNames.find(radioButtonPosition))).waitUntil(exist, timeout, polling).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено переключение в положение [%s].%n", radioButtonPosition);
        TimeUnit.SECONDS.sleep(1);
        this.verifyRadioButtonSelected(radioButtonPosition);
    }

    /**
     * Проверяет, что в переключателе не выбрана опция с указанным именем.
     *
     * @param radioButtonName имя опции
     */
    public void verifyRadioButtonNotSelected(String radioButtonName) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем положение переключателя [%s] - ", radioButtonName);
        $(this.getBy(radioButtonNames.find(radioButtonName))).waitUntil(exist, timeout, polling).shouldNotBe(selected);
        System.out.println("Ok, не выбран.");
    }

    /**
     * Проверяет, что в переключателе выбрана опция с указанным именем.
     *
     * @param radioButtonName имя опции
     */
    public void verifyRadioButtonSelected(String radioButtonName) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем положение переключателя [%s] - ", radioButtonName);
        $(this.getBy(radioButtonNames.find(radioButtonName))).waitUntil(exist, timeout, polling).shouldBe(selected);
        System.out.println("Ok, выбран.");
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

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
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
        $(this.getBy(fieldNames.find(fieldName))).waitUntil(exist, timeout, polling);
        if (value.trim().isEmpty()) {
            String actualValue = $(this.getBy(fieldNames.find(fieldName))).getText().trim();
            if (actualValue.isEmpty()) actualValue = $(this.getBy(fieldNames.find(fieldName))).getValue().trim();
            Assert.assertTrue(message, actualValue.isEmpty());
        } else
            Assert.assertTrue(message, $(this.getBy(fieldNames.find(fieldName))).getText().contains(value));
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
     * Проверяет отсутствие поля на странице.
     *
     * @param fieldName имя поля
     */
    public void checkFieldIsNotDisplayed(String fieldName) {
        String message = String.format("[ОШИБКА]: поле [%s] отображается на странице", fieldName);

        Assert.assertFalse(message, $(this.getBy(fieldNames.find(fieldName))).isDisplayed());

        System.out.printf("[ИНФОРМАЦИЯ]: поле [%s] не отображается на странице.%n", fieldName);

    }

    /**
     * Проверяет значение полей, содержащих номер телефона или факса.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение, которое должно содержаться в поле с номером телефона или факса
     */
    public void verifyFieldWithPhoneNumber(String fieldName, String value) {
        String message = String.format("[ОШИБКА]: значение [%s] не содержится в поле [%s]", value, fieldName);

        By field = this.getBy(fieldNames.find(fieldName));

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        Assert.assertTrue(message, this.verifyFieldWithPhoneNumber(field, value));
    }

    /**
     * Делает щелчок мышью по указанной кнопке предварительно дождавшись, пока она станет активной.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        SelenideElement button = $(this.getBy(buttonNames.find(buttonName))).
                waitUntil(clickable, timeout, polling).shouldBe(enabled);
        this.clickInElementJS($(button));
        this.logPressedButtonName(buttonName);
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * Устанавливает значение поля со списком, допускающего прямой ввод значения и множественный выбор.
     * Аналог метода setListWithInputAndMultiSelectField только выбор прямо из списка.
     *
     * @param deleteButtonsName имя набора кнопок [X], которые позволяют удалить все ранее выбранные значения в поле
     * @param fieldName         имя поля
     * @param value             требуемое значение поля
     */
    public void setListWithInputAndMultiSelectFieldDirectValueFromList(String deleteButtonsName, String fieldName,
                                                                       String value) throws Exception {
        // Очищаем поле от выбранных значений (нажимаем крестик для удаления каждого значения)
        ElementsCollection deleteButtons = $$(this.getBy(buttonNames.find(deleteButtonsName))).filterBy(clickable);
        System.out.printf("[ИНФОРМАЦИЯ]: найдено %d предыдущих значений поля с множественным выбором.%n",
                deleteButtons.size());

        for (SelenideElement deleteButton : deleteButtons) {
            System.out.print("[ИНФОРМАЦИЯ]: удаляем предыдущее значение поля с множественным выбором -> ");
            deleteButton.waitUntil(clickable, timeout, polling).click();
            TimeUnit.SECONDS.sleep(1);
            deleteButton.waitUntil(disappears, timeout, polling);
            System.out.println("удалено успешно.");
        }

        do {
            //Открывает мультиселект и выбирает значение из выпадающего списка
            SelenideElement input = $(this.getBy(fieldNames.find(fieldName)));
            input.waitUntil(clickable, timeout, polling).click();
            TimeUnit.SECONDS.sleep(3);
            $(this.getBy(String.format(DELIVERY_REGION_MULTISELECT_LIST_XPATH, value))).click();

            if (deleteButtons.size() == 0)
                System.err.printf("[ОШИБКА]: неудачная попытка заполнения поля [%s] значением [%s]%n",
                        fieldName, value);
            else
                System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n",
                        fieldName, value);
        } while (deleteButtons.size() == 0);
    }

    // endregion
}
