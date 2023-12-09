package ru.PageObjects.Customer.Notice;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы со страницей [Формирование черновика ... МСП]
 * ( Главная / Заказчикам / Мои закупки / Формирование черновика ... МСП )
 * ( .kontur.ru/customer/lk/App/trade/tender/create ).
 * Created by Vladimir V. Klochkov on 17.03.2020.
 * Updated by Vladimir V. Klochkov on 07.07.2021.
 */
public class CreateSMBNoticePage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Раздел [Общие сведения о закупке]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Общие сведения о закупке]
    private static final String COMMON_INFO_BLOCK_XPATH =
            "//div[@class='panel-label pull-left' and contains(.,'Общие сведения о закупке')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки]
    private static final String PURCHASE_NUMBER_XPATH =
            "//app-form-group[@label='Номер закупки']//div[@class='ng-form-group__controls']" +
                    "//*[local-name()='textarea' or local-name()='div' and not(@_ngcontent-six-c3)]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Способ закупки]
    private static final String PURCHASE_METHOD_XPATH =
            "//app-form-group[@label='Способ закупки']//kendo-dropdownlist//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Способ закупки]
    private static final String PURCHASE_METHOD_LIST_XPATH =
            "//*[@id='CommonInfo_PurchaseMethod']//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование закупки]
    private static final String ORDER_NAME_XPATH = "//app-form-group[@label='Наименование закупки']//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Описание закупки]
    private static final String DESCRIPTION_XPATH = "//app-form-group[@label='Описание закупки']//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Многолотовая закупка]
    private static final String IS_MULTI_LOTS_XPATH = "//app-form-group[@label='Многолотовая закупка']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Закупка проводится вследствие аварии, чрезвычайной ситуации]
    private static final String IS_EMERGENCY_XPATH =
            "//app-form-group[@label='Закупка проводится вследствие аварии, чрезвычайной ситуации']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Этап обсуждения функциональных характеристик]
    private static final String IS_SPECIFICATIONS_DISCUSSION_ENABLED_XPATH =
            "//app-form-group[@label='Этап обсуждения функциональных характеристик']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Этап обсуждения предложений о функциональных характеристиках]
    private static final String IS_SPECIFICATION_PROPOSALS_DISCUSSION_ENABLED_XPATH =
            "//app-form-group[@label='Этап обсуждения предложений о функциональных характеристиках']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Этап рассмотрения и оценки окончательных предложений]
    private static final String IS_EVALUATION_ENABLED_XPATH =
            "//app-form-group[@label='Этап рассмотрения и оценки окончательных предложений']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Этап проведения квалификационного отбора]
    private static final String IS_QUALIFICATION_ENABLED_XPATH =
            "//app-form-group[@label='Этап проведения квалификационного отбора']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Этап сопоставления дополнительных ценовых предложений]
    private static final String RETRADING_REVIEV_STAGE_ENABLED_XPATH =
            "//app-form-group[@label='Этап сопоставления дополнительных ценовых предложений']//input";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о порядке подачи заявок]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о порядке подачи заявок]
    private static final String APPLICATIONS_SUBMISSION_ORDER_INFO_BLOCK_XPATH =
            "//div[@class='panel-label pull-left link' and contains(.,'Сведения о порядке подачи заявок')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата начала подачи заявок]
    private static final String APPLICATION_START_DATE_XPATH =
            "//app-form-group[@label='Дата начала подачи заявок']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата и время окончания подачи заявок (МСК)]
    private static final String APPLICATION_END_DATE_XPATH =
            "//app-form-group[@labeltimezone='Дата и время окончания подачи заявок']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Место подачи заявок]
    private static final String APPLICATION_REQUEST_PLACE_XPATH =
            "//app-form-group[@label='Место подачи заявок']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Порядок подачи заявок]
    private static final String APPLICATION_REQUEST_ORDER_XPATH =
            "//app-form-group[@label='Порядок подачи заявок']//input";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о порядке рассмотрения заявок]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о порядке рассмотрения заявок]
    private static final String APPLICATIONS_EXAMINATION_INFO_BLOCK_XPATH =
            "//div[@class='panel-label pull-left link' and contains(.,'Сведения о порядке рассмотрения заявок')]";
    // Поле [Дата рассмотрения заявок]
    private static final String APPLICATIONS_EXAMINATION_DATE_TIME_XPATH = "//app-form-group[@label='Дата рассмотрения заявок']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Место рассмотрения заявок]
    private static final String APPLICATIONS_EXAMINATION_PLACE_XPATH = "//app-form-group[@label='Место рассмотрения заявок']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Порядок рассмотрения заявок]
    private static final String APPLICATIONS_EXAMINATION_ORDER_XPATH = "//app-form-group[@label='Порядок рассмотрения заявок']//input";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о порядке рассмотрения первых частей заявок]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о порядке рассмотрения первых частей заявок]
    private static final String FIRST_PART_EXAMINATION_INFO_BLOCK_XPATH =
            "//div[@class='panel-label pull-left link' and contains(.,'Сведения о порядке рассмотрения первых частей заявок')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата рассмотрения первых частей заявок]
    private static final String APPLICATION_EXAMINATION_DATE_ID = "TradeStage_ExaminationDateTime";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Место рассмотрения первых частей заявок]
    private static final String APPLICATION_EXAMINATION_PLACE_ID = "TradeStage_ExaminationPlace";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Порядок рассмотрения первых частей заявок]
    private static final String APPLICATION_EXAMINATION_ORDER_ID = "TradeStage_ExaminationOrder";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о подаче ценовых предложений]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о подаче ценовых предложений]
    private static final String PRICES_PROVISION_INFO_BLOCK_XPATH =
            "//div[@class='panel-label pull-left link' and contains(.,'Сведения о подаче ценовых предложений')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата и время начала подачи ценовых предложений]
    private static final String PRICES_PROVISION_START_DATE_TIME_XPATH = "TradeStage_SingleAuctionDate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Порядок подачи ценовых предложений]
    private static final String PRICES_PROVISION_ORDER_XPATH =
            "//app-form-group[@label='Порядок подачи ценовых предложений']//input";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о порядке сопоставления ценовых предложений]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о порядке сопоставления ценовых предложений]
    private static final String PRICES_MATCHING_INFO_BLOCK_XPATH =
            "//div[@class='panel-label pull-left link' and contains(.,'Сведения о порядке сопоставления ценовых предложений')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата проведения сопоставления ценовых предложений (аукциона)]
    private static final String PRICES_MATCHING_DATE_XPATH =
            "//app-form-group[@label='Дата проведения сопоставления ценовых предложений (аукциона)']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Порядок проведения сопоставления ценовых предложений]
    private static final String PRICES_MATCHING_ORDER_XPATH =
            "//app-form-group[@label='Порядок проведения сопоставления ценовых предложений']//input";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о порядке обсуждения функциональных характеристик]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о порядке обсуждения функциональных характеристик]
    private static final String FUNCTIONAL_FEATURES_DISCUSS_INFO_BLOCK_XPATH =
            "//div[@class='panel-label pull-left link' and contains(.,'Сведения о порядке обсуждения функциональных характеристик')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата обсуждения функциональных характеристик]
    private static final String FUNCTIONAL_FEATURES_DISCUSS_START_DATE_ID =
            "TradeStage_FunctionalFeaturesDiscussStartDate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата и время окончания обсуждения функциональных характеристик (МСК)]
    private static final String FUNCTIONAL_FEATURES_DISCUSS_END_DATE_TIME_ID =
            "TradeStage_FunctionalFeaturesDiscussEndDate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Место обсуждения функциональных характеристик]
    private static final String FUNCTIONAL_FEATURES_DISCUSS_PLACE_ID = "TradeStage_FunctionalFeaturesDiscussPlace";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Порядок обсуждения функциональных характеристик]
    private static final String FUNCTIONAL_FEATURES_DISCUSS_ORDER_ID = "TradeStage_FunctionalFeaturesDiscussOrder";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о порядке рассмотрения и оценки окончательных предложений]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о порядке рассмотрения и оценки окончательных предложений]
    private static final String EXAMINATION_INFO_BLOCK_XPATH =
            "//div[@class='panel-label pull-left link' and contains(.,'Сведения о порядке рассмотрения и оценки окончательных предложений')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата рассмотрения и оценки окончательных предложений]
    private static final String EXAMINATION_DATE_ID = "TradeStage_ExaminationDateTime";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Место рассмотрения и оценки окончательных предложений]
    private static final String EXAMINATION_PLACE_ID = "TradeStage_ExaminationPlace";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Порядок рассмотрения и оценки окончательных предложений]
    private static final String EXAMINATION_ORDER_ID = "TradeStage_ExaminationOrder";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о подаче дополнительных ценовых предложений]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о подаче дополнительных ценовых предложений]
    private static final String ADDITIONAL_PRICES_PROVISION_INFO_BLOCK_XPATH =
            "//div[@class='panel-label pull-left link' and contains(.,'Сведения о подаче дополнительных ценовых предложений')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата и время начала подачи дополнительных ценовых предложений]
    private static final String ADDITIONAL_PRICES_PROVISION_START_DATE_ID =
            "TradeStage_AdditionalPricesProvisionStartDate452Fl";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Порядок подачи дополнительных ценовых предложений]
    private static final String ADDITIONAL_PRICES_PROVISION_ORDER_ID = "TradeStage_AdditionalPricesProvisionOrder452Fl";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о порядке проведения квалификационного отбора]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о порядке проведения квалификационного отбора]
    private static final String QUALIFYING_COMPETITION_INFO_BLOCK_XPATH =
            "//div[@class='panel-label pull-left link' and contains(.,'Сведения о порядке проведения квалификационного отбора')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата проведения квалификационного отбора]
    private static final String QUALIFYING_COMPETITION_DATE_ID = "TradeStage_QualifyingCompetitionDate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Место проведения квалификационного отбора]
    private static final String QUALIFYING_COMPETITION_PLACE_ID = "TradeStage_QualifyingCompetitionPlace";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Порядок проведения квалификационного отбора]
    private static final String QUALIFYING_COMPETITION_ORDER_ID = "TradeStage_QualifyingCompetitionOrder";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о порядке рассмотрения вторых частей заявок]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о порядке рассмотрения вторых частей заявок]
    private static final String SECOND_PART_EXAMINATION_INFO_BLOCK_XPATH =
            "//div[@class='panel-label pull-left link' and contains(.,'Сведения о порядке рассмотрения вторых частей заявок')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата рассмотрения вторых частей заявок]
    private static final String SECOND_PART_EXAMINATION_DATE_ID = "TradeStage_SecondPartExaminationDateTime";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Место рассмотрения вторых частей заявок]
    private static final String SECOND_PART_EXAMINATION_PLACE_ID = "TradeStage_SecondPartExaminationPlace";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Порядок рассмотрения вторых частей заявок]
    private static final String SECOND_PART_EXAMINATION_ORDER_ID = "TradeStage_SecondPartExaminationOrder";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о порядке сопоставления дополнительных ценовых предложений]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о порядке сопоставления дополнительных ценовых предложений]
    private static final String ADDITIONAL_PRICES_MATCHING_INFO_BLOCK_XPATH =
            "//div[@class='panel-label pull-left link' and contains(.,'Сведения о порядке сопоставления дополнительных ценовых предложений')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата проведения сопоставления дополнительных ценовых предложений]
    private static final String ADDITIONAL_PRICES_MATCHING_DATE_ID = "TradeStage_AdditionalPricesMatchingDate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Порядок проведения сопоставления дополнительных ценовых предложений]
    private static final String ADDITIONAL_PRICES_MATCHING_ORDER_ID = "TradeStage_AdditionalPricesMatchingOrder";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о порядке подведения итогов]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о порядке подведения итогов]
    private static final String SUMMINGUP_INFO_BLOCK_XPATH =
            "//div[@class='panel-label pull-left link' and contains(.,'Сведения о порядке подведения итогов')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата подведения итогов]
    private static final String RESULTS_REVIEW_DATE_ID = "TradeStage_ResultsReviewDate";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Место подведения итогов]
    private static final String RESULTS_REVIEW_PLACE_ID = "TradeStage_ResultsReviewPlace";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Порядок подведения итогов]
    private static final String RESULTS_REVIEW_ORDER_ID = "TradeStage_ResultsReviewOrder";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Регламентный срок заключения договора]
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Субполе [От]
    private static final String CONTRACT_CONCLUSION_DAYS_FROM_XPATH =
            "//*[@id='TradeStage_ContractConclusionDaysFrom']//input";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Субполе [До]
    private static final String CONTRACT_CONCLUSION_DAYS_TO_XPATH =
            "//*[@id='TradeStage_ContractConclusionDaysTo']//input";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [рабочих]
    private static final String CONTRACT_CONCLUSION_DAYS0_ID = "ContractConclusionDays0";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [календарных]
    private static final String CONTRACT_CONCLUSION_DAYS1_ID = "ContractConclusionDays1";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о предоставлении документации]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о предоставлении документации]
    private static final String DOCUMENTATION_INFO_BLOCK_XPATH =
            "//div[@class='panel-label pull-left link' and contains(.,'Сведения о предоставлении документации')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок предоставления документации]
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Субполе [От]
    private static final String DOCUMENTATION_DATE_FROM_ID = "TradeStage_DocumentationDateFrom";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Субполе [До]
    private static final String DOCUMENTATION_DATE_TO_ID = "TradeStage_DocumentationDateTo";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Место предоставления документации]
    private static final String DOCUMENTATION_PLACE_ID = "TradeStage_DocumentationPlace";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Порядок предоставления документации]
    private static final String DOCUMENTATION_VIEW_PROCEDURE_ID = "TradeStage_DocumentationViewProcedure";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Установлена плата за предоставление документации]
    private static final String SHOULD_HAVE_DOCUMENTATION_FEE_ID = "TradeStage_ShouldHaveDocumentationFee";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о порядке предоставления разъяснений]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о порядке предоставления разъяснений]
    private static final String CLARIFICATION_INFO_BLOCK_XPATH =
            "//div[@class='panel-label pull-left link' and contains(.,'Сведения о порядке предоставления разъяснений')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок направления запроса о разъяснении документации (дней до окончания подачи заявок)]
    private static final String CLARIFICATION_REQUEST_LIMIT_DAYS_XPATH =
            "//*[@id='TradeStage_ClarificationRequestLimitDays']/span/input";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [рабочих]
    private static final String CLARIFICATION_REQUEST_LIMIT_DAYS0_ID = "ClarificationRequestLimitDays0";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [календарных]
    private static final String CLARIFICATION_REQUEST_LIMIT_DAYS1_ID = "ClarificationRequestLimitDays1";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок предоставления разъяснения документации]
    private static final String CLARIFICATION_RESPONCE_LIMIT_DAYS_XPATH =
            "//*[@id='TradeStage_ClarificationResponseLimitDays']/span/input";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [рабочих]
    private static final String CLARIFICATION_RESPONCE_LIMIT_DAYS0_ID = "ClarificationResponseLimitDays0";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [календарных]
    private static final String CLARIFICATION_RESPONCE_LIMIT_DAYS1_ID = "ClarificationResponseLimitDays1";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок направления запроса о разъяснении результата (после подведения итогов)]
    private static final String RESULT_CLARIFICATION_REQUEST_LIMIT_DAYS_XPATH =
            "//*[@id='TradeStage_ResultClarificationRequestLimitDays']/span/input";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [рабочих]
    private static final String RESULT_CLARIFICATION_REQUEST_LIMIT_DAYS0_ID = "ResultClarificationRequestLimitDays0";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [календарных]
    private static final String RESULT_CLARIFICATION_REQUEST_LIMIT_DAYS1_ID = "ResultClarificationRequestLimitDays1";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок предоставления разъяснения результатов]
    private static final String RESULT_CLARIFICATION_RESPONCE_LIMIT_DAYS_XPATH =
            "//*[@id='TradeStage_ResultClarificationResponseLimitDays']/span/input";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [рабочих]
    private static final String RESULT_CLARIFICATION_RESPONCE_LIMIT_DAYS0_ID = "ResultClarificationResponseLimitDays0";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [календарных]
    private static final String RESULT_CLARIFICATION_RESPONCE_LIMIT_DAYS1_ID = "ResultClarificationResponseLimitDays1";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о контактном лице]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о контактном лице]
    private static final String RESPONSIBLE_PERSON_INFO_BLOCK_XPATH =
            "//div[@class='panel-label pull-left link' and contains(.,'Сведения о контактном лице')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Ответственное должностное лицо] (список возможных значений поля свёрнут)
    private static final String RESPONSIBLE_PERSON_LIST_CLOSED_XPATH =
            "//*[@id='ContactPersonInfo_ResponderCommonUserId']//span[@role='listbox']";
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
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Заполнить контактную информацию]
    private static final String FILL_CONTACT_INFO_ID = "btnFillInfoClick";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Фамилия]
    private static final String CONTACT_PERSON_LAST_NAME_ID = "ContactPersonInfo_ContactLastName";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Имя]
    private static final String CONTACT_PERSON_FIRST_NAME_ID = "ContactPersonInfo_ContactFirstName";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Отчество]
    private static final String CONTACT_PERSON_MIDDLE_NAME_ID = "ContactPersonInfo_ContactMiddleName";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Телефон (в международном формате)]
    private static final String CONTACT_PERSON_PHONE_XPATH = "//*[@id='ContactPersonInfo_ContactPhone']/input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Адрес электронной почты]
    private static final String CONTACT_PERSON_EMAIL_ID = "ContactPersonInfo_ContactEmail";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Факс (в международном формате)]
    private static final String CONTACT_PERSON_FAX_XPATH = "//*[@id='ContactPersonInfo_ContactFax']/input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дополнительная контактная информация]
    private static final String CONTACT_PERSON_ADDITIONAL_INFO_ID = "ContactPersonInfo_ContactAddInfo";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Требования к участникам закупки]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Требования к участникам закупки]
    private static final String REQUIREMENTS_TO_PARTICIPANTS_BLOCK_XPATH =
            "//div[@class='panel-label pull-left link' and contains(.,'Требования к участникам закупки')]";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Участники закупки должны отсутствовать в реестре недобросовестных]
    private static final String IS_NOT_DISHONEST_XPATH = "//input[@id='IsNotDishonest']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Дополнительная информация]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Дополнительная информация]
    private static final String ADDITIONAL_INFORMATION_BLOCK_XPATH =
            "//div[@class='panel-label pull-left link' and contains(.,'Дополнительная информация')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дополнительная информация]
    private static final String ADDITIONAL_INFORMATION_XPATH =
            "//app-form-group[@label='Дополнительная информация']//textarea";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Информация о лоте]

    // region Общее для всех лотов

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Информация о лоте]
    private static final String LOT_INFO_BLOCK_XPATH =
            "//div[@class='panel-label pull-left' and contains(.,'Информация о лоте')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить лот]
    private static final String ADD_NEW_LOT_BUTTON_XPATH = "//button[contains(., 'Добавить лот')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Закладка [Общие сведения]

    //==================================================================================================================
    // Закладка [Общие сведения]
    private static final String LOT_COMMON_INFORMATION_TAB_XPATH = "//li//span[contains(text(),'Общие сведения')]";
    //==================================================================================================================
    // Общие сведения о лоте
    //==================================================================================================================
    // Поле [Наименование]
    private static final String SUBJECT_XPATH = "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'Subject')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Краткое описание закупки]
    private static final String PURCHASE_SHORT_INFO_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'PurchaseShortInfo')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Валюта]
    private static final String CURRENCY_CODE_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'CurrencyCode')]//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Блок радиокнопок [Торги осуществляются]
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [От цены за лот]
    private static final String TRADE_BIDDING_TYPE_0_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'TradeBiddingType_0')]";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [От цены за единицу продукции]
    private static final String TRADE_BIDDING_TYPE_1_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'TradeBiddingType_1')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Начальная (максимальная) цена]
    private static final String LOT_PRICE_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'LotPrice')]//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Формула цены]
    private static final String PRICE_FORMULA_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'PriceFormula')]";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Закупка не учитывается в соответствии с пунктом 7 постановления Правительства РФ от 11.12.2017 № 1352]
    private static final String IS_PURCHASE_IGNORED_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'IsPurchaseIgnored')]";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [НМЦ включает НДС]
    private static final String IS_LOT_PRICE_WITH_VAT_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'IsLotPriceWithVat')]";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Указать сведения о НДС]
    private static final String IS_LOT_PRICE_DETERM_WITH_VAT_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'IsLotPriceDetermWithVat')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Ставка НДС, %]
    private static final String RATE_VAT_XPATH = "//app-form-group[@label='Ставка НДС, %']//span[@role]/span[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Сумма НДС]
    private static final String SUM_VAT_XPATH = "//app-form-group[@label='Сумма НДС']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [НМЦ без НДС]
    private static final String LOT_PRICE_WITHOUT_VAT_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'LotPriceWithoutVat')]//input";
    //------------------------------------------------------------------------------------------------------------------
    // Блок радиокнопок [При выборе победителя учитывается]
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [Цена с НДС]
    private static final String PRICE_WITH_VAT_0_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'PriceWithVat_0')]";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [Цена без НДС]
    private static final String PRICE_WITH_VAT_1_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'PriceWithVat_1')]";
    //------------------------------------------------------------------------------------------------------------------
    // Радиокнопки [Включить в заявке на участие декларацию, предусмотренную п.9 ч.19.1 ст.3.4 223-ФЗ в ред. от 01.04.2021]
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [Да]
    private static final String APPLICATION_DECLARATION_ENABLE_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'TradeLotCommonInfo_1_ApplicationDeclarationEnabled_0')]";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [Нет]
    private static final String APPLICATION_DECLARATION_DISABLE_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'TradeLotCommonInfo_1_ApplicationDeclarationEnabled_1')]";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Установить приоритет товаров российского происхождения в соответствии с нормами ПП РФ № 925]
    private static final String IS_PREFERENCE_BY_925_AVAILABLE_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'IsPreferenceBy925Available')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Сведения о предоставлении преференций]
    private static final String PREFERENCES_INFO_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'PreferencesInfo')]";
    //==================================================================================================================
    // Сведения о заключении договора
    //==================================================================================================================
    // Флажок [Заключение договора возможно с любым из допущенных участников]
    private static final String KEEP_GUARANTEE_FOR_ALL_PARTICIPANTS_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'KeepGuaranteeForAllParticipants')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Количество участников, занявших места ниже первого,
    // с которыми возможно заключение договора по результатам процедуры]
    private static final String KEEP_GUARANTEE_FOR_NON_WINNING_PARTICIPANTS_COUNT_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'KeepGuaranteeForNonWinningParticipantsCount')]//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Возможно направление договора участником]
    private static final String IS_SUPPLIER_CAN_CREATE_CONTRACT_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'IsSupplierCanCreateContract')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок направления договора (в днях)]
    private static final String CREATE_CONTRACT_LIMIT_DAYS_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'CreateContractLimitDays')]//input";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [рабочих]
    private static final String CREATE_CONTRACT_LIMIT_DAYS0_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'CreateContractLimitDays_DaysType0')]";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [календарных]
    private static final String CREATE_CONTRACT_LIMIT_DAYS1_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'CreateContractLimitDays_DaysType1')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок подписания договора участником (в днях)]
    private static final String PARTICIPANT_SIGN_CONTRACT_LIMIT_DAYS_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'ParticipantSignContractLimitDays')]//input";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [рабочих]
    private static final String PARTICIPANT_SIGN_CONTRACT_LIMIT_DAYS0_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'ParticipantSignContractLimitDays_DaysType0')]";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [календарных]
    private static final String PARTICIPANT_SIGN_CONTRACT_LIMIT_DAYS1_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'ParticipantSignContractLimitDays_DaysType1')]";
    //------------------------------------------------------------------------------------------------------------------
    // Блок радиокнопок [Начало отсчета срока для подписания договора участником]
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [С даты размещения протокола на ЭП]
    private static final String IS_START_FROM_PROTOCOL_PUBLISH_DATE_0_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'IsStartFromProtocolPublishDate_0')]";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [С даты получения проекта договора поставщиком]
    private static final String IS_START_FROM_PROTOCOL_PUBLISH_DATE_1_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'IsStartFromProtocolPublishDate_1')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок заключения договора (в днях)]
    private static final String CONTRACT_COMPLETE_LIMIT_DAYS_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'ContractCompleteLimitDays')]//input";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [рабочих]
    private static final String CONTRACT_COMPLETE_LIMIT_DAYS0_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'ContractCompleteLimitDays_DaysType0')]";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Переключатель [календарных]
    private static final String CONTRACT_COMPLETE_LIMIT_DAYS1_XPATH =
            "//*[contains(@id, 'TradeLotCommonInfo') and contains(@id, 'ContractCompleteLimitDays_DaysType1')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Закладка [Товары, работы и услуги]

    //==================================================================================================================
    // Закладка [Товары, работы и услуги]
    private static final String LOT_TYPES_CODES_TAB_XPATH = "//li//span[contains(text(),'Товары, работы и услуги')]";
    //==================================================================================================================
    // Товары, работы и услуги
    //==================================================================================================================
    // Флажок [Лот является составным]
    private static final String IS_COMPOSITE_XPATH =
            "//*[contains(@id, 'TradeLotItems') and contains(@id, 'IsComposite')]";
    //==================================================================================================================
    // Добавление информации о товаре, работе или услуге
    //==================================================================================================================
    // Поле [Наименование товара, работ, услуг]
    private static final String NAME_XPATH =
            "//*[contains(@id, 'TradeLotItems') and contains(@id, 'Name')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Код ОКПД2]
    private static final String OKPD_2_XPATH = "//*[@id='cmbBox_Okpd2Lookup']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Код ОКВЭД2]
    private static final String OKVED_2_XPATH = "//*[@id='cmbBox_Okvd2Lookup']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Невозможно определить количество (объем)]
    private static final String QUANTITY_UNDEFINED_XPATH =
            "//*[contains(@id, 'TradeLotItems') and contains(@id, 'QuantityUndefined')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Единица измерения (код ОКЕИ)] (список возможных значений поля свёрнут)
    private static final String OKEI_LIST_CLOSED_XPATH =
            "//*[contains(@id, 'TradeLotItems') and contains(@id, 'Okei')]//span/span[@class='k-select']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Единица измерения (код ОКЕИ)] (список возможных значений поля открыт) -> требуемое значение в списке
    private static final String OKEI_VALUE_IN_OPENED_LIST_XPATH = "//li[contains(., 'Ампер (260)')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Единица измерения (код ОКЕИ)] (текущее значение)
    private static final String OKEI_VALUE_XPATH =
            "//*[contains(@id, 'TradeLotItems') and contains(@id, 'Okei')]//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Количество]
    private static final String QUANTITY_XPATH =
            "//*[contains(@id, 'TradeLotItems') and contains(@id, 'Quantity')]//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дополнительные сведения]
    private static final String ADDITIONAL_INFO_LOT_XPATH =
            "//*[contains(@id, 'TradeLotItems') and contains(@id, 'AdditionalInfo')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Закладка [Сведения об аукционе]

    //==================================================================================================================
    // Закладка [Сведения об аукционе]
    private static final String LOT_AUCTION_DETAILS_TAB_XPATH = "//li//span[contains(text(),'Сведения об аукционе')]";
    //==================================================================================================================
    // Сведения об аукционе
    //==================================================================================================================
    // Поле [Шаг аукциона]
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Поле [Шаг аукциона %] - [От]
    private static final String AUCTION_MIN_STEP_XPATH =
            "//app-form-group[@label='Шаг аукциона %']//input[@placeholder='От']";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Поле [Шаг аукциона %] - [До]
    private static final String AUCTION_MAX_STEP_XPATH =
            "//app-form-group[@label='Шаг аукциона %']//input[@placeholder='До']";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Автоматическое снижение шага]
    private static final String AUCTION_IS_STEP_AUTO_DECREASING_XPATH =
            "//*[contains(@id, 'TradeLotAuctionInfo') and contains(@id, 'IsStepAutoDecreasing')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Время ожидания ценового предложения (мин)]
    private static final String AUCTION_NEXT_BID_WAITING_MINUTES_XPATH =
            "//app-form-group[@label='Время ожидания ценового предложения (мин)']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Проводить вторую фазу (торги за второе место)]
    private static final String AUCTION_WITH_SECOND_PHASE_ID =
            "//app-form-group[@label='Проводить вторую фазу (торги за второе место)']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Аукцион с нижним пределом снижения цены]
    private static final String AUCTION_WITH_LIMIT_PRICE_REDUCTION_ID =
            "//app-form-group[@label='Аукцион с нижним пределом снижения цены']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Максимальная продолжительность аукциона (мин)]
    private static final String AUCTION_MAX_DURATION_IN_MINUTES_XPATH =
            "//app-form-group[@label='Максимальная продолжительность аукциона (мин)']//input";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Закладка [Сведения о заказчиках]

    //==================================================================================================================
    // Закладка [Сведения о заказчиках]
    private static final String LOT_CUSTOMERS_INFO_TAB_XPATH =
            "//li//span[contains(text(),'Сведения о заказчиках')]";
    //==================================================================================================================
    // Сведения о заказчиках, подписывающих договоры
    //==================================================================================================================
    // Флажок [Несколько заказчиков (совместная закупка)]
    private static final String IS_MULTI_CUSTOMERS_XPATH =
            "//*[contains(@id, 'TradeLotCustomers') and contains(@id, 'IsMultiCustomers')]";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Договор заключается с несколькими участниками]
    private static final String IS_MULTI_PARTICIPANTS_CONTRACT_XPATH =
            "//*[contains(@id, 'TradeLotCustomers') and contains(@id, 'IsMultiParticipantsContract')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Вид обеспечения заявки]
    private static final String APPLICATION_GUARANTEE_TYPE_XPATH =
            "//*[contains(@id, 'TradeLotCustomers') and contains(@id, 'ApplicationGuaranteeType')]//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Иные требования к обеспечению заявки]
    private static final String APPLICATION_AMOUNT_EXTRA_XPATH =
            "//*[contains(@id, 'TradeLotCustomers') and contains(@id, 'ApplicationAmountExtra')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Валюта обеспечения заявки]
    private static final String CUSTOMER_APPLICATION_AMOUNT_CURRENCY_CODE_XPATH =
            "//*[contains(@id, 'TradeLotCustomers') and contains(@id, 'ApplicationAmountCurrencyCode')]//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Вид обеспечения договора]
    private static final String DOCUMENT_GUARANTEE_TYPE_XPATH =
            "//*[contains(@id, 'TradeLotCustomers') and contains(@id, 'DocumentGuaranteeType')]//span[@class='k-input']";
    //==================================================================================================================
    // Информация о заказчике
    //==================================================================================================================
    // Поле [Заказчик]
    private static final String CUSTOMER_NAME_XPATH =
            "//*[contains(@id, 'TradeLotCustomers') and contains(@id, 'CustomerId')]//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Обеспечение заявки]
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Поле [Обеспечение заявки] - [сумма]
    private static final String APPLICATION_AMOUNT_SUM_XPATH =
            "//*[starts-with(@id, 'TradeLotCustomers') and contains(@id, 'ApplicationAmount') and not (contains(@id, 'ApplicationAmountPercent'))]//input";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Поле [Обеспечение заявки] - [%]
    private static final String APPLICATION_AMOUNT_PERCENT_XPATH =
            "//*[starts-with(@id, 'TradeLotCustomers') and contains(@id, 'ApplicationAmountPercent')]//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Обеспечение договора]
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    private static final String CONTRACT_AMOUNT_SUM_XPATH =
            "//*[starts-with(@id, 'TradeLotCustomers') and contains(@id, 'ContractAmount') and not (contains(@id, 'ContractAmountPercent'))]//input";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    private static final String CONTRACT_AMOUNT_PERCENT_XPATH =
            "//*[starts-with(@id, 'TradeLotCustomers') and contains(@id, 'ContractAmountPercent')]//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Сведения о заказчиках - Регион]
    private static final String DELIVERY_REGION_CODE_XPATH =
            "//app-form-group[@label='Регион']//kendo-multiselect//li/span[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Сведения о заказчиках - Регион - X]
    private static final String DELIVERY_REGION_X_BUTTONS_XPATH =
            "//app-form-group[@label='Регион']//span[@aria-label='delete']";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Поле [Сведения о заказчиках - Регион - поле ввода]
    private static final String DELIVERY_REGION_INPUT_FIELD_XPATH =
            "//app-form-group[@label='Регион']//kendo-searchbar/input";
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Поле [Сведения о заказчиках - Регион - требуемое значение в списке - г. Севастополь]
    private static final String DELIVERY_REGION_VALUE_TO_SELECT_XPATH = "//li[contains(., 'г. Севастополь')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Место поставки товара, выполнения работ, оказания услуг]
    private static final String DELIVERY_PLACE_XPATH =
            "//textarea[contains(@id, 'TradeLotCustomers') and contains(@id, 'DeliveryPlace')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Заполнить юридическим адресом]
    private static final String FILL_DELIVERY_PLACE_BY_JUDICAL_ADDRESS_BUTTON_XPATH =
            "//button[contains(@id, 'TradeLotCustomers') and contains(@id, 'btnFillDeliveryPlaceByJudicalAddress')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Срок поставки товара, выполнения работ, оказания услуг]
    private static final String DELIVERY_TERM_XPATH =
            "//textarea[contains(@id, 'TradeLotCustomers') and contains(@id, 'DeliveryTerm')]";
    //==================================================================================================================
    // Сведения о позиции в плане закупок
    //==================================================================================================================
    // Поле [Регистрационный номер плана]
    private static final String PLAN_REGISTRATION_NUMBER_XPATH =
            "//*[contains(@id, 'TradeLotCustomers') and contains(@id, 'PlanRegistrationNumber')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер позиции плана]
    private static final String PLAN_POSITION_NUMBER_XPATH =
            "//*[contains(@id, 'TradeLotCustomers') and contains(@id, 'PlanPositionNumber')]//input";
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
            "//app-form-panel[@label='Документы лота']//td[contains(., 'Документы не прикреплены')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Закладка [Дополнительные параметры]

    //==================================================================================================================
    // Закладка [Дополнительные параметры]
    private static final String LOT_ADDITIONAL_PARAMETERS_TAB_XPATH =
            "//li//span[contains(text(),'Дополнительные параметры')]";
    //==================================================================================================================
    // Дополнительные параметры
    //==================================================================================================================
    // Поле [Отсрочка платежа (дней)]
    private static final String DEFEREMENT_OF_PAYMENT_DAYS_XPATH =
            "//app-form-group[@label='Отсрочка платежа (дней)']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Предоплата]
    private static final String PREPAYMENT_DAYS_XPATH = "//app-form-group[@label='Предоплата']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Требования к продукции]
    private static final String PRODUCT_REQUIREMENTS_ID = "//app-form-group[@label='Требования к продукции']//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Адрес склада]
    private static final String STORAGE_ADDRESS_ID = "//app-form-group[@label='Адрес склада']//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Точная дата поставки (крайний срок)]
    private static final String DELIVERY_DATE_ID =
            "//app-form-group[@label='Точная дата поставки (крайний срок)']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Примечание]
    private static final String NOTE_ID = "//app-form-group[@label='Примечание']//textarea";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Промокод]
    private static final String PROMO_CODE_ID = "//app-form-group[@label='Промокод']//input";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // endregion

    // region Раздел [Документы]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Документы]
    private static final String DOCUMENTS_BLOCK_XPATH =
            "//div[@class='panel-label pull-left' and contains(.,'Документы')]";
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
            "//form/app-external-fields-edit/app-form-panel[@id='ExternalFields']/div[contains(.,'Дополнительные поля')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дополнительные поля] - [Порядок формирования начальной цены]
    private static final String AF_INIT_PRICE_ORDER_XPATH =
            "//*[@id='ExternalFields']//label[contains(., 'Порядок формирования начальной цены')]/../../div//textarea";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Рабочие группы]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Рабочие группы]
    private static final String WORK_GROUPS_BLOCK_XPATH =
            "//div[@class='panel-label pull-left link' and contains(.,'Рабочие группы')]";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок в таблице [Рабочие группы] справа от требуемой группы ( имя группы передается как параметр теста )
    private static final String IS_GROUP_SELECTED_XPATH =
            "//div[@id='WorkGroupsEdit']//table/tbody/tr/td[contains(.,'%s')]/../td//input";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Блок управляющих кнопок в нижней части страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Удалить черновик]
    private static final String DELETE_TRADE_DRAFT_BUTTON_ID = "deleteTradeDraft";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Сохранить и проверить]
    private static final String SAVE_AND_CHECK_BUTTON_ID = "saveAndCheck";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Опубликовать]
    private static final String CONFIRM_PUBLISHING_BUTTON_ID = "confirmPublishing";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Всплывающее окно [Календарь]

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Закрыть - окно Календарь]
    private static final String DATEPICKER_HIDE_BUTTON_XPATH = "//div[@id='ui-datepicker-div']//button[text()='Закрыть']";
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

    // region Диалоговые окна и внешние страницы

    //------------------------------------------------------------------------------------------------------------------
    // Диалоговое окно [Публикация извещения]
    private final PublishSMBAngularDialog publishSMBAngularDialog = new PublishSMBAngularDialog(driver);
    //------------------------------------------------------------------------------------------------------------------
    // Диалоговое окно [Опубликовано]
    private final PublishedSMBAngularDialog publishedSMBAngularDialog = new PublishedSMBAngularDialog(driver);
    //------------------------------------------------------------------------------------------------------------------
    // Страница [Просмотр извещения XXXX]
    private final ViewNoticePage viewNoticePage = new ViewNoticePage(driver);
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CreateSMBNoticePage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Общие сведения о закупке", COMMON_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о порядке подачи заявок", APPLICATIONS_SUBMISSION_ORDER_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о порядке рассмотрения заявок", APPLICATIONS_EXAMINATION_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о порядке рассмотрения первых частей заявок", FIRST_PART_EXAMINATION_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о подаче ценовых предложений", PRICES_PROVISION_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о порядке сопоставления ценовых предложений", PRICES_MATCHING_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о порядке обсуждения функциональных характеристик",
                FUNCTIONAL_FEATURES_DISCUSS_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о порядке рассмотрения и оценки окончательных предложений",
                EXAMINATION_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о подаче дополнительных ценовых предложений",
                ADDITIONAL_PRICES_PROVISION_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о порядке проведения квалификационного отбора",
                QUALIFYING_COMPETITION_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о порядке рассмотрения вторых частей заявок", SECOND_PART_EXAMINATION_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о порядке сопоставления дополнительных ценовых предложений",
                ADDITIONAL_PRICES_MATCHING_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о порядке подведения итогов", SUMMINGUP_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о предоставлении документации", DOCUMENTATION_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о порядке предоставления разъяснений", CLARIFICATION_INFO_BLOCK_XPATH);
        blockNames.add("Сведения о контактном лице", RESPONSIBLE_PERSON_INFO_BLOCK_XPATH);
        blockNames.add("Требования к участникам закупки", REQUIREMENTS_TO_PARTICIPANTS_BLOCK_XPATH);
        blockNames.add("Дополнительная информация", ADDITIONAL_INFORMATION_BLOCK_XPATH);
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
        radioButtonNames.add("Регламентный срок заключения договора - рабочих", CONTRACT_CONCLUSION_DAYS0_ID);
        radioButtonNames.add("Регламентный срок заключения договора - календарных", CONTRACT_CONCLUSION_DAYS1_ID);
        radioButtonNames.add("Срок направления запроса о разъяснении документации - рабочих",
                CLARIFICATION_REQUEST_LIMIT_DAYS0_ID);
        radioButtonNames.add("Срок направления запроса о разъяснении документации - календарных",
                CLARIFICATION_REQUEST_LIMIT_DAYS1_ID);
        radioButtonNames.add("Срок предоставления разъяснения документации - рабочих",
                CLARIFICATION_RESPONCE_LIMIT_DAYS0_ID);
        radioButtonNames.add("Срок предоставления разъяснения документации - календарных",
                CLARIFICATION_RESPONCE_LIMIT_DAYS1_ID);
        radioButtonNames.add("Срок направления запроса о разъяснении результата - рабочих",
                RESULT_CLARIFICATION_REQUEST_LIMIT_DAYS0_ID);
        radioButtonNames.add("Срок направления запроса о разъяснении результата - календарных",
                RESULT_CLARIFICATION_REQUEST_LIMIT_DAYS1_ID);
        radioButtonNames.add("Срок предоставления разъяснения результатов - рабочих",
                RESULT_CLARIFICATION_RESPONCE_LIMIT_DAYS0_ID);
        radioButtonNames.add("Срок предоставления разъяснения результатов - календарных",
                RESULT_CLARIFICATION_RESPONCE_LIMIT_DAYS1_ID);
        radioButtonNames.add("Торги осуществляются - От цены за лот", TRADE_BIDDING_TYPE_0_XPATH);
        radioButtonNames.add("Торги осуществляются - От цены за единицу продукции", TRADE_BIDDING_TYPE_1_XPATH);
        radioButtonNames.add("Включить в заявке на участие декларацию - Да", APPLICATION_DECLARATION_ENABLE_XPATH);
        radioButtonNames.add("Включить в заявке на участие декларацию - Нет", APPLICATION_DECLARATION_DISABLE_XPATH);
        radioButtonNames.add("Цена с НДС", PRICE_WITH_VAT_0_XPATH);
        radioButtonNames.add("Цена без НДС", PRICE_WITH_VAT_1_XPATH);
        radioButtonNames.add("Срок направления договора (в днях) - рабочих", CREATE_CONTRACT_LIMIT_DAYS0_XPATH);
        radioButtonNames.add("Срок направления договора (в днях) - календарных", CREATE_CONTRACT_LIMIT_DAYS1_XPATH);
        radioButtonNames.add("Срок подписания договора участником (в днях) - рабочих",
                PARTICIPANT_SIGN_CONTRACT_LIMIT_DAYS0_XPATH);
        radioButtonNames.add("Срок подписания договора участником (в днях) - календарных",
                PARTICIPANT_SIGN_CONTRACT_LIMIT_DAYS1_XPATH);
        radioButtonNames.add("Начало отсч. ср. для подп. дог. уч. - С даты разм. прот. на ЭП",
                IS_START_FROM_PROTOCOL_PUBLISH_DATE_0_XPATH);
        radioButtonNames.add("Начало отсч. ср. для подп. дог. уч. - С даты пол. пр. дог. пост.",
                IS_START_FROM_PROTOCOL_PUBLISH_DATE_1_XPATH);
        radioButtonNames.add("Срок заключения договора (в днях) - рабочих", CONTRACT_COMPLETE_LIMIT_DAYS0_XPATH);
        radioButtonNames.add("Срок заключения договора (в днях) - календарных", CONTRACT_COMPLETE_LIMIT_DAYS1_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        checkBoxNames.add("Многолотовая закупка", IS_MULTI_LOTS_XPATH);
        checkBoxNames.add("Закупка проводится вследствие аварии, чрезвычайной ситуации", IS_EMERGENCY_XPATH);
        checkBoxNames.add("Этап обсуждения функциональных характеристик", IS_SPECIFICATIONS_DISCUSSION_ENABLED_XPATH);
        checkBoxNames.add("Этап обсуждения предложений о функциональных характеристиках",
                IS_SPECIFICATION_PROPOSALS_DISCUSSION_ENABLED_XPATH);
        checkBoxNames.add("Этап рассмотрения и оценки окончательных предложений", IS_EVALUATION_ENABLED_XPATH);
        checkBoxNames.add("Этап проведения квалификационного отбора", IS_QUALIFICATION_ENABLED_XPATH);
        checkBoxNames.add("Этап сопоставления дополнительных ценовых предложений", RETRADING_REVIEV_STAGE_ENABLED_XPATH);
        checkBoxNames.add("Установлена плата за предоставление документации", SHOULD_HAVE_DOCUMENTATION_FEE_ID);
        checkBoxNames.add("Участники закупки должны отсутствовать в реестре недобросовестных", IS_NOT_DISHONEST_XPATH);
        checkBoxNames.add("Закупка не учитывается", IS_PURCHASE_IGNORED_XPATH);
        checkBoxNames.add("НМЦ включает НДС", IS_LOT_PRICE_WITH_VAT_XPATH);
        checkBoxNames.add("Указать сведения о НДС", IS_LOT_PRICE_DETERM_WITH_VAT_XPATH);
        checkBoxNames.add("Установить приоритет товаров российского происхождения", IS_PREFERENCE_BY_925_AVAILABLE_XPATH);
        checkBoxNames.add("Заключение договора возможно с любым из допущенных участников",
                KEEP_GUARANTEE_FOR_ALL_PARTICIPANTS_XPATH);
        checkBoxNames.add("Возможно направление договора участником", IS_SUPPLIER_CAN_CREATE_CONTRACT_XPATH);
        checkBoxNames.add("Лот является составным", IS_COMPOSITE_XPATH);
        checkBoxNames.add("Невозможно определить количество (объем)", QUANTITY_UNDEFINED_XPATH);
        checkBoxNames.add("Автоматическое снижение шага", AUCTION_IS_STEP_AUTO_DECREASING_XPATH);
        checkBoxNames.add("Проводить вторую фазу (торги за второе место)", AUCTION_WITH_SECOND_PHASE_ID);
        checkBoxNames.add("Аукцион с нижним пределом снижения цены", AUCTION_WITH_LIMIT_PRICE_REDUCTION_ID);
        checkBoxNames.add("Несколько заказчиков (совместная закупка)", IS_MULTI_CUSTOMERS_XPATH);
        checkBoxNames.add("Договор заключается с несколькими участниками", IS_MULTI_PARTICIPANTS_CONTRACT_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Номер закупки", PURCHASE_NUMBER_XPATH);
        fieldNames.add("Способ закупки:", PURCHASE_METHOD_XPATH);
        fieldNames.add("Способ закупки", PURCHASE_METHOD_LIST_XPATH);
        fieldNames.add("Наименование закупки", ORDER_NAME_XPATH);
        fieldNames.add("Описание закупки", DESCRIPTION_XPATH);
        fieldNames.add("Дата начала подачи заявок", APPLICATION_START_DATE_XPATH);
        fieldNames.add("Дата и время окончания подачи заявок", APPLICATION_END_DATE_XPATH);
        fieldNames.add("Место подачи заявок", APPLICATION_REQUEST_PLACE_XPATH);
        fieldNames.add("Порядок подачи заявок", APPLICATION_REQUEST_ORDER_XPATH);
        fieldNames.add("Дата рассмотрения заявок", APPLICATIONS_EXAMINATION_DATE_TIME_XPATH);
        fieldNames.add("Место рассмотрения заявок", APPLICATIONS_EXAMINATION_PLACE_XPATH);
        fieldNames.add("Порядок рассмотрения заявок", APPLICATIONS_EXAMINATION_ORDER_XPATH);
        fieldNames.add("Дата рассмотрения первых частей заявок", APPLICATION_EXAMINATION_DATE_ID);
        fieldNames.add("Место рассмотрения первых частей заявок", APPLICATION_EXAMINATION_PLACE_ID);
        fieldNames.add("Порядок рассмотрения первых частей заявок", APPLICATION_EXAMINATION_ORDER_ID);
        fieldNames.add("Дата и время начала подачи ценовых предложений", PRICES_PROVISION_START_DATE_TIME_XPATH);
        fieldNames.add("Порядок подачи ценовых предложений", PRICES_PROVISION_ORDER_XPATH);
        fieldNames.add("Дата проведения сопоставления ценовых предложений (аукциона)", PRICES_MATCHING_DATE_XPATH);
        fieldNames.add("Порядок проведения сопоставления ценовых предложений", PRICES_MATCHING_ORDER_XPATH);
        fieldNames.add("Дата обсуждения функциональных характеристик",
                FUNCTIONAL_FEATURES_DISCUSS_START_DATE_ID);
        fieldNames.add("Дата и время окончания обсуждения функциональных характеристик",
                FUNCTIONAL_FEATURES_DISCUSS_END_DATE_TIME_ID);
        fieldNames.add("Место обсуждения функциональных характеристик", FUNCTIONAL_FEATURES_DISCUSS_PLACE_ID);
        fieldNames.add("Порядок обсуждения функциональных характеристик", FUNCTIONAL_FEATURES_DISCUSS_ORDER_ID);
        fieldNames.add("Дата рассмотрения и оценки окончательных предложений", EXAMINATION_DATE_ID);
        fieldNames.add("Место рассмотрения и оценки окончательных предложений", EXAMINATION_PLACE_ID);
        fieldNames.add("Порядок рассмотрения и оценки окончательных предложений", EXAMINATION_ORDER_ID);
        fieldNames.add("Дата и время начала подачи дополнительных ценовых предложений",
                ADDITIONAL_PRICES_PROVISION_START_DATE_ID);
        fieldNames.add("Порядок подачи дополнительных ценовых предложений", ADDITIONAL_PRICES_PROVISION_ORDER_ID);
        fieldNames.add("Дата проведения квалификационного отбора", QUALIFYING_COMPETITION_DATE_ID);
        fieldNames.add("Место проведения квалификационного отбора", QUALIFYING_COMPETITION_PLACE_ID);
        fieldNames.add("Порядок проведения квалификационного отбора", QUALIFYING_COMPETITION_ORDER_ID);
        fieldNames.add("Дата рассмотрения вторых частей заявок", SECOND_PART_EXAMINATION_DATE_ID);
        fieldNames.add("Место рассмотрения вторых частей заявок", SECOND_PART_EXAMINATION_PLACE_ID);
        fieldNames.add("Порядок рассмотрения вторых частей заявок", SECOND_PART_EXAMINATION_ORDER_ID);
        fieldNames.add("Дата проведения сопоставления дополнительных ценовых предложений",
                ADDITIONAL_PRICES_MATCHING_DATE_ID);
        fieldNames.add("Порядок проведения сопоставления дополнительных ценовых предложений",
                ADDITIONAL_PRICES_MATCHING_ORDER_ID);
        fieldNames.add("Дата подведения итогов", RESULTS_REVIEW_DATE_ID);
        fieldNames.add("Место подведения итогов", RESULTS_REVIEW_PLACE_ID);
        fieldNames.add("Порядок подведения итогов", RESULTS_REVIEW_ORDER_ID);
        fieldNames.add("Регламентный срок заключения договора - От", CONTRACT_CONCLUSION_DAYS_FROM_XPATH);
        fieldNames.add("Регламентный срок заключения договора - До", CONTRACT_CONCLUSION_DAYS_TO_XPATH);
        fieldNames.add("Дата начала предоставления документации", DOCUMENTATION_DATE_FROM_ID);
        fieldNames.add("Дата окончания предоставления документации", DOCUMENTATION_DATE_TO_ID);
        fieldNames.add("Место предоставления документации", DOCUMENTATION_PLACE_ID);
        fieldNames.add("Порядок предоставления документации", DOCUMENTATION_VIEW_PROCEDURE_ID);
        fieldNames.add("Срок направления запроса о разъяснении документации (дней до окончания подачи заявок)",
                CLARIFICATION_REQUEST_LIMIT_DAYS_XPATH);
        fieldNames.add("Срок предоставления разъяснения документации", CLARIFICATION_RESPONCE_LIMIT_DAYS_XPATH);
        fieldNames.add("Срок направления запроса о разъяснении результата (после подведения итогов)",
                RESULT_CLARIFICATION_REQUEST_LIMIT_DAYS_XPATH);
        fieldNames.add("Срок предоставления разъяснения результатов", RESULT_CLARIFICATION_RESPONCE_LIMIT_DAYS_XPATH);
        fieldNames.add("Ответственное должностное лицо", RESPONSIBLE_PERSON_VALUE_XPATH);
        fieldNames.add("Ответственное должностное лицо Фамилия", CONTACT_PERSON_LAST_NAME_ID);
        fieldNames.add("Ответственное должностное лицо Имя", CONTACT_PERSON_FIRST_NAME_ID);
        fieldNames.add("Ответственное должностное лицо Отчество", CONTACT_PERSON_MIDDLE_NAME_ID);
        fieldNames.add("Телефон (в международном формате)", CONTACT_PERSON_PHONE_XPATH);
        fieldNames.add("Адрес электронной почты", CONTACT_PERSON_EMAIL_ID);
        fieldNames.add("Факс (в международном формате)", CONTACT_PERSON_FAX_XPATH);
        fieldNames.add("Ответственное должностное лицо Дополнительная информация", CONTACT_PERSON_ADDITIONAL_INFO_ID);
        fieldNames.add("Дополнительная информация", ADDITIONAL_INFORMATION_XPATH);
        fieldNames.add("Наименование", SUBJECT_XPATH);
        fieldNames.add("Краткое описание закупки", PURCHASE_SHORT_INFO_XPATH);
        fieldNames.add("Валюта", CURRENCY_CODE_XPATH);
        fieldNames.add("Начальная (максимальная) цена", LOT_PRICE_XPATH);
        fieldNames.add("Формула цены", PRICE_FORMULA_XPATH);
        fieldNames.add("Ставка НДС, %", RATE_VAT_XPATH);
        fieldNames.add("Сумма НДС", SUM_VAT_XPATH);
        fieldNames.add("НМЦ без НДС", LOT_PRICE_WITHOUT_VAT_XPATH);
        fieldNames.add("Сведения о предоставлении преференций", PREFERENCES_INFO_XPATH);
        fieldNames.add("Регион", DELIVERY_REGION_CODE_XPATH);
        fieldNames.add("Количество участников, занявших места ниже первого,",
                KEEP_GUARANTEE_FOR_NON_WINNING_PARTICIPANTS_COUNT_XPATH);
        fieldNames.add("Срок направления договора (в днях)", CREATE_CONTRACT_LIMIT_DAYS_XPATH);
        fieldNames.add("Срок подписания договора участником (в днях)", PARTICIPANT_SIGN_CONTRACT_LIMIT_DAYS_XPATH);
        fieldNames.add("Срок заключения договора (в днях)", CONTRACT_COMPLETE_LIMIT_DAYS_XPATH);
        fieldNames.add("Наименование товара, работ, услуг", NAME_XPATH);
        fieldNames.add("Код ОКПД2", OKPD_2_XPATH);
        fieldNames.add("Код ОКВЭД2", OKVED_2_XPATH);
        fieldNames.add("Единица измерения (код ОКЕИ)", OKEI_VALUE_XPATH);
        fieldNames.add("Количество", QUANTITY_XPATH);
        fieldNames.add("Дополнительные сведения", ADDITIONAL_INFO_LOT_XPATH);
        fieldNames.add("Шаг аукциона % - От", AUCTION_MIN_STEP_XPATH);
        fieldNames.add("Шаг аукциона % - До", AUCTION_MAX_STEP_XPATH);
        fieldNames.add("Время ожидания ценового предложения (мин)", AUCTION_NEXT_BID_WAITING_MINUTES_XPATH);
        fieldNames.add("Максимальная продолжительность аукциона (мин)", AUCTION_MAX_DURATION_IN_MINUTES_XPATH);
        fieldNames.add("Вид обеспечения заявки", APPLICATION_GUARANTEE_TYPE_XPATH);
        fieldNames.add("Иные требования к обеспечению заявки", APPLICATION_AMOUNT_EXTRA_XPATH);
        fieldNames.add("Валюта обеспечения заявки", CUSTOMER_APPLICATION_AMOUNT_CURRENCY_CODE_XPATH);
        fieldNames.add("Вид обеспечения договора", DOCUMENT_GUARANTEE_TYPE_XPATH);
        fieldNames.add("Сведения о заказчиках - Заказчик", CUSTOMER_NAME_XPATH);
        fieldNames.add("Обеспечение заявки (RUB)", APPLICATION_AMOUNT_SUM_XPATH);
        fieldNames.add("Обеспечение заявки (%)", APPLICATION_AMOUNT_PERCENT_XPATH);
        fieldNames.add("Обеспечение договора (RUB)", CONTRACT_AMOUNT_SUM_XPATH);
        fieldNames.add("Обеспечение договора (%)", CONTRACT_AMOUNT_PERCENT_XPATH);
        fieldNames.add("Сведения о заказчиках - Регион - поле ввода", DELIVERY_REGION_INPUT_FIELD_XPATH);
        fieldNames.add("Сведения о заказчиках - Регион - требуемое значение в списке - г. Севастополь",
                DELIVERY_REGION_VALUE_TO_SELECT_XPATH);
        fieldNames.add("Место поставки товара, выполнения работ, оказания услуг", DELIVERY_PLACE_XPATH);
        fieldNames.add("Срок поставки товара, выполнения работ, оказания услуг", DELIVERY_TERM_XPATH);
        fieldNames.add("Регистрационный номер плана", PLAN_REGISTRATION_NUMBER_XPATH);
        fieldNames.add("Номер позиции плана", PLAN_POSITION_NUMBER_XPATH);
        fieldNames.add("Отсрочка платежа (дней)", DEFEREMENT_OF_PAYMENT_DAYS_XPATH);
        fieldNames.add("Предоплата", PREPAYMENT_DAYS_XPATH);
        fieldNames.add("Требования к продукции", PRODUCT_REQUIREMENTS_ID);
        fieldNames.add("Адрес склада", STORAGE_ADDRESS_ID);
        fieldNames.add("Точная дата поставки (крайний срок)", DELIVERY_DATE_ID);
        fieldNames.add("Примечание", NOTE_ID);
        fieldNames.add("Промокод", PROMO_CODE_ID);
        fieldNames.add("Дополнительные поля - Порядок формирования начальной цены", AF_INIT_PRICE_ORDER_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Заполнить контактную информацию", FILL_CONTACT_INFO_ID);
        buttonNames.add("Добавить лот", ADD_NEW_LOT_BUTTON_XPATH);
        buttonNames.add("Удалить черновик", DELETE_TRADE_DRAFT_BUTTON_ID);
        buttonNames.add("'Закрыть' - в окне 'Календарь'", DATEPICKER_HIDE_BUTTON_XPATH);
        buttonNames.add("Сведения о заказчиках - Регион - X", DELIVERY_REGION_X_BUTTONS_XPATH);
        buttonNames.add("Заполнить юридическим адресом", FILL_DELIVERY_PLACE_BY_JUDICAL_ADDRESS_BUTTON_XPATH);
        buttonNames.add("Сохранить и проверить", SAVE_AND_CHECK_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
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
        this.scrollToCenter(this.getBy(RESPONSIBLE_PERSON_INFO_BLOCK_XPATH));

        for (int i = 0; i < 50; i++) {
            System.out.printf(
                    "[ИНФОРМАЦИЯ]: попытка № [%d] открыть список [Ответственное должностное лицо].%n", i + 1);
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
        this.scrollToCenter(this.getBy(RESPONSIBLE_PERSON_INFO_BLOCK_XPATH));

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

        Assert.assertEquals("Поле [Ответственное должностное лицо] не заполнено", newValue, certificateName);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [Ответственное должностное лицо] значением [%s].%n",
                newValue);
    }

    // endregion

    // region Раздел [Информация о лотах] -> закладка [Товары, работы и услуги]

    /**
     * Устанавливает значение поля [Код ОКПД2].
     *
     * @param lotOkpd2Value требуемое значение поля
     */
    public void setLotOkpd2(String lotOkpd2Value) throws Exception {
        $(this.getBy(OKPD_2_XPATH)).waitUntil(exist, timeout, polling).sendKeys(lotOkpd2Value);
        TimeUnit.SECONDS.sleep(3); // Ожидание, пока подтянется значение из справочника (контрол медленный)
        $(this.getBy(LOT_TYPES_CODES_TAB_XPATH)).waitUntil(visible, timeout, polling).click();
    }

    /**
     * Устанавливает значение поля [Код ОКВЭД2].
     *
     * @param lotOkved2Value требуемое значение поля
     */
    public void setLotOkved2(String lotOkved2Value) throws Exception {
        $(this.getBy(OKVED_2_XPATH)).waitUntil(exist, timeout, polling).sendKeys(lotOkved2Value);
        TimeUnit.SECONDS.sleep(3); // Ожидание, пока подтянется значение из справочника (контрол медленный)
        $(this.getBy(LOT_TYPES_CODES_TAB_XPATH)).waitUntil(visible, timeout, polling).click();
    }

    /**
     * Устанавливает значение поля [Единица измерения (код ОКЕИ)].
     */
    public void fillOkeiCode() throws Exception {
        SelenideElement value = $(this.getBy(OKEI_VALUE_XPATH));
        String oldValue = value.waitUntil(exist, timeout, polling).getValue();
        String newValue = oldValue;

        for (int i = 0; i < 50; i++) {
            System.out.printf("[ИНФОРМАЦИЯ]: попытка № [%d] открыть список [Единица измерения (код ОКЕИ)].%n", i + 1);
            $(this.getBy(OKEI_LIST_CLOSED_XPATH)).waitUntil(clickable, timeout, polling).click();
            $(this.getBy(OKEI_VALUE_IN_OPENED_LIST_XPATH)).waitUntil(clickable, timeout, polling);
            $(this.getBy(OKEI_VALUE_IN_OPENED_LIST_XPATH)).hover();
            TimeUnit.SECONDS.sleep(3);
            System.out.printf("[ИНФОРМАЦИЯ]: требуемая единица измерения [%s] найдена в списке.%n",
                    $(this.getBy(OKEI_VALUE_IN_OPENED_LIST_XPATH)).getText());
            this.clickInElementJS($(this.getBy(OKEI_VALUE_IN_OPENED_LIST_XPATH)));
            TimeUnit.SECONDS.sleep(3);
            newValue = value.waitUntil(exist, timeout, polling).getValue();
            if (!newValue.equals(oldValue)) break;
        }

        Assert.assertNotEquals("Поле [Единица измерения (код ОКЕИ)] не заполнено", oldValue, newValue);
        System.out.printf(
                "[ИНФОРМАЦИЯ]: произведено заполнение поля [Единица измерения (код ОКЕИ)] значением [%s].%n", newValue);
    }

    // endregion

    // region Раздел [Информация о лотах] -> закладка [Документы лота]

    /**
     * Проверяет, что таблица [Документы лота] пуста.
     */
    public void checkThatTheLotDocumentsTableIsEmpty() {
        System.out.println("[ИНФОРМАЦИЯ]: проверяем надпись [Документы не прикреплены] в таблице [Документы лота].");
        $(this.getBy(LOT_DOCUMENTS_TABLE_EMPTY_XPATH)).shouldBe(visible);
        System.out.println("[ИНФОРМАЦИЯ]: надпись [Документы не прикреплены] отображается в таблице [Документы лота].");
    }

    // endregion

    // region Раздел [Документы]

    /**
     * Прикрепляет файл в разделе [Документы].
     *
     * @param fileName имя файла
     */
    public void uploadFileIntoDocuments(String fileName) {
        System.out.printf("[ИНФОРМАЦИЯ]: прикрепляем файл [%s] в разделе [Документы].%n", fileName);
        $(this.getBy(UPLOAD_FILE_TRADE_XPATH)).waitUntil(exist, timeout, polling).sendKeys(fileName);
    }

    /**
     * Проверяет ссылку для скачивания прикрепленного файла в разделе [Документы].
     *
     * @param fileName имя файла
     */
    public void checkLinkToDownloadFileIntoDocuments(String fileName) throws Exception {
        System.out.printf(
                "[ИНФОРМАЦИЯ]: проверяем ссылку для скачивания прикрепленного файла [%s] в разделе [Документы].%n",
                fileName);
        this.getCurrentServerVersion();
        $(this.getBy(DOWNLOAD_FILE_TRADE_XPATH)).waitUntil(visible, timeout, polling).shouldHave(text(fileName));
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
        Assert.assertTrue(String.format("[ОШИБКА]:  группа [%s] в таблице [Рабочие группы] не отмечена", groupName),
                checkBox.isSelected());
        System.out.printf("[ИНФОРМАЦИЯ]: группа [%s] в таблице [Рабочие группы] отмечена.%n", groupName);
    }

    // endregion

    // region Блок кнопок внизу формы ([Сохранить черновик], [Опубликовать])

    /**
     * Публикует извещение со страницы [Формирование черновика извещения ... МСП].
     */
    public void publishNotice() throws Exception {
        // Нажимаем на кнопку "Опубликовать"
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        this.scrollToCenter(this.getBy(CONFIRM_PUBLISHING_BUTTON_ID));
        this.logButtonNameToPress("Опубликовать");
        $(this.getBy(CONFIRM_PUBLISHING_BUTTON_ID)).waitUntil(clickable, longtime, polling).click();
        this.logPressedButtonName("Опубликовать");

        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        // Нажимаем на кнопку "Продолжить" в диалоговом окне "Публикация извещения"
        publishSMBAngularDialog.continueThePublication();

        // Нажимаем на кнопку "Перейти к карточке закупки" в диалоговом окне "Опубликовано"
        publishedSMBAngularDialog.goToPurchaseCard();

        // Проверяем статус закупки "Готова к публикации" и сохраняем номер извещения
        viewNoticePage.checkPurchaseStatusAndSavePurchaseNumber("Готова к публикации");

        // Сохраняем дату и время публикации извещения
        viewNoticePage.savePublishNoticeDateAndTime();

        // Проверяем наименование закупки и сохраняем его его в параметрах текущего теста
        viewNoticePage.checkAndSavePurchaseName("View");

        // Проверяем наличие записей в диалоговом окне "История"
        viewNoticePage.checkHistoryRecordsPresence("Публикация черновика извещения");
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
        System.out.printf("[ИНФОРМАЦИЯ]: Произведено открытие блока полей: [%s].%n", blockName);
        TimeUnit.SECONDS.sleep(3);
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
     * Проверяет значение полей, содержащих номер телефона или факса.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение, которое должно содержаться в поле с номером телефона или факса
     */
    public void verifyFieldWithPhoneNumber(String fieldName, String value) {
        String message = String.format("[ОШИБКА]: Значение [%s] не содержится в поле [%s].", value, fieldName);

        By field = this.getBy(fieldNames.find(fieldName));

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        Assert.assertTrue(message, this.verifyFieldWithPhoneNumber(field, value));
    }

    /**
     * Проверяет значение поля.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение, которое должно содержаться в поле
     */
    public void verifyFieldContentOrEmptyField(String fieldName, String value) {
        String message = String.format("[ОШИБКА]: значение [%s] не содержится в поле [%s]", value, fieldName);
        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        $(this.getBy(fieldNames.find(fieldName))).waitUntil(exist, timeout, polling).shouldBe(visible);
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
        String message = String.format("[ОШИБКА]: поле: [%s] содержит пустое значение", fieldName);

        By field = this.getBy(fieldNames.find(fieldName));

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] на не пустое значение.%n", fieldName);
        Assert.assertTrue(message, this.verifyFieldIsNotEmpty(field));
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
        System.out.printf("[ИНФОРМАЦИЯ]: произведено изменение состояния флажка: [%s].%n", checkBoxName);
        boolean newValue = this.getCheckBoxValue(checkBoxName);
        System.out.printf("[ИНФОРМАЦИЯ]: флажок: [%s] находится %s отмеченном состоянии.%n",
                checkBoxName, ((newValue) ? "в" : "в не"));
        Assert.assertNotEquals(String.format(message, checkBoxName), oldValue, newValue);
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
     * Выбирает в переключателе опцию с указанным именем.
     *
     * @param radioButtonPosition имя опции
     */
    public void selectRadioButton(String radioButtonPosition) throws Exception {
        $(this.getBy(radioButtonNames.find(radioButtonPosition))).waitUntil(exist, timeout, polling).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено переключение в положение: [%s].%n", radioButtonPosition);
        TimeUnit.SECONDS.sleep(1);
        this.verifyRadioButtonSelected(radioButtonPosition);
    }

    /**
     * Проверяет, что в переключателе не выбрана опция с указанным именем.
     *
     * @param radioButtonName имя опции
     */
    public void verifyRadioButtonNotSelected(String radioButtonName) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем положение переключателя: [%s] - ", radioButtonName);
        $(this.getBy(radioButtonNames.find(radioButtonName))).waitUntil(exist, timeout, polling).shouldNotBe(selected);
        System.out.println("Ok, не выбран.");
    }

    /**
     * Проверяет, что в переключателе выбрана опция с указанным именем.
     *
     * @param radioButtonName имя опции
     */
    public void verifyRadioButtonSelected(String radioButtonName) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем положение переключателя: [%s] - ", radioButtonName);
        $(this.getBy(radioButtonNames.find(radioButtonName))).waitUntil(exist, timeout, polling).shouldBe(selected);
        System.out.println("Ok, выбран.");
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
     * Устанавливает значение поля со списком, допускающего прямой ввод значения и множественный выбор.
     *
     * @param deleteButtonsName имя набора кнопок [X], которые позволяют удалить все ранее выбранные значения в поле
     * @param fieldName         имя поля
     * @param valueName         требуемое значение в списке выбора
     */
    public void setListWithInputAndMultiSelectField(String deleteButtonsName, String fieldName, String valueName)
            throws Exception {
        // Очищаем поле от выбранных значений ( нажимаем крестик для удаления каждого значения )
        ElementsCollection deleteButtons = $$(this.getBy(buttonNames.find(deleteButtonsName)));

        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        for (SelenideElement deleteButton : deleteButtons) {
            deleteButton.waitUntil(clickable, timeout, polling).click();
            System.out.println("[ИНФОРМАЦИЯ]: удаляем элемент из поля [Регион].");
        }
        do {
            // Добавляем одно значение в поле ( клик по полю -> выбор значения из раскрывающегося списка )
            int nTries = 1;
            do {
                Assert.assertTrue("[ОШИБКА]: требуемое значение отсутствует в списке", nTries <= 10);
                $(this.getBy(fieldNames.find(fieldName))).waitUntil(clickable, timeout, polling).click();
                System.out.printf("[ИНФОРМАЦИЯ]: делаем %d щелчок мышью по полю [Регион].%n", nTries);
                nTries++;
            } while (!$(this.getBy(fieldNames.find(valueName))).isDisplayed());

            this.clickInElementJS(this.getBy(fieldNames.find(valueName)));
            System.out.println("[ИНФОРМАЦИЯ]: делаем щелчок мышью по требуемому значению в списке выбора.");

            // Проверяем добавление значения в поле, если не получилось - будем повторять все действия
            deleteButtons = $$(this.getBy(buttonNames.find(deleteButtonsName)));
            if (deleteButtons.size() == 0)
                System.err.printf("[ОШИБКА]: неудачная попытка заполнения поля [%s]%n", fieldName);
            else
                System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s].%n", fieldName);
        } while (deleteButtons.size() == 0);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Проверяет значение поля со списком, допускающего прямой ввод значения и множественный выбор.
     *
     * @param deleteButtonsName имя набора кнопок [X], которые позволяют удалить все ранее выбранные значения в поле
     */
    public void checkListWithInputAndMultiSelectField(String deleteButtonsName) {
        $$(this.getBy(buttonNames.find(deleteButtonsName))).shouldHave(sizeGreaterThanOrEqual(1));
    }

    // endregion
}
