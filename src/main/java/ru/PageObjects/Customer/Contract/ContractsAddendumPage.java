package ru.PageObjects.Customer.Contract;

import Helpers.Dictionary;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Класс описывающий страницу [Сведения о дополнительном соглашении] ( Главная / Заказчикам / Мои договоры / xxxx.xxxx )
 * ( .kontur.ru/customer/lk/Contracts/View/хххх ) или ( .kontur.ru/customer/lk/contracts/view/хххх ).
 * Created by Alexander S. Vasyurenko on 03.02.2021.
 * Updated by Alexander S. Vasyurenko on 20.04.2021.
 */
public class ContractsAddendumPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Раздел [Сведения о договоре (дополнительном соглашении)]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о договоре (дополнительном соглашении)]
    private static final String ADDENDUM_GEN_INFO_HEADER_XPATH =
            "//h2[contains(.,'Сведения о договоре')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Основной договор]
    private static final String ADDENDUM_GEN_INFO_MAIN_CONTRACT_XPATH =
            "//td[contains(., 'Основной договор')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка на основной договор в поле [Основной договор]
    private static final String ADDENDUM_GEN_INFO_MAIN_CONTRACT_LINK_XPATH =
            "//a[contains(@href,'/customer/lk/Contracts/View/')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус дополнительного соглашения]
    private static final String ADDENDUM_GEN_INFO_STATUS_XPATH =
            "//td[contains(., 'Статус дополнительного соглашения')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер карточки договора]
    private static final String ADDENDUM_GEN_INFO_NUMBER_XPATH =
            "//td[contains(., 'Номер карточки договора')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер договора в ЕИС]
    private static final String ADDENDUM_GEN_INFO_EIS_NUMBER_XPATH =
            "//td[contains(., 'Номер договора в ЕИС')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Сумма договора с учетом дополнительных соглашений]
    private static final String ADDENDUM_GEN_INFO_SUM_XPATH =
            "//td[contains(., 'Сумма договора с учетом дополнительных соглашений')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [Изменить цену договора]
    private static final String ADDENDUM_GEN_INFO_CHANGE_ADDENDUM_PRICE_XPATH =
            "//a[contains(.,'Изменить цену договора')]";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Доп. соглашением предусмотрено прекращение обязательств сторон по доп. соглашению в связи с окончанием
    // срока действия доп. соглашения]
    private static final String ADDENDUM_GEN_INFO_IS_TERMINATION_ID = "IsTermination";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Доп. соглашением предусмотрена возможность продления срока действия доп. соглашения после его окончания]
    private static final String ADDENDUM_GEN_INFO_IS_EXTENSION_ID = "IsExtension";
    //------------------------------------------------------------------------------------------------------------------
    // Переключатель [установить дату вручную]
    private static final String ADDENDUM_GEN_INFO_SET_MANUAL_RADIO_BUTTON_ID = "IsExecutionDateStartFromSign2";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата начала исполнения дополнительного соглашения]
    private static final String ADDENDUM_GEN_INFO_DATE_START_PERFORMANCE_XPATH = "//input[@id='ExecutionDateStart']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Условия начала исполнения дополнительного соглашения]
    private static final String ADDENDUM_GEN_INFO_TERM_START_PERFORMANCE_XPATH = "//input[@id='ExecutionTermStart']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата окончания исполнения дополнительного соглашения]
    private static final String ADDENDUM_GEN_INFO_DATE_END_PERFORMANCE_XPATH = "//input[@id='ExecutionDateEnd']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Условия окончания исполнения дополнительного соглашения]
    private static final String ADDENDUM_GEN_INFO_TERM_END_PERFORMANCE_XPATH = "//input[@id='ExecutionTermEnd']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о заключении договора (дополнительного соглашения)]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о заключении договора (дополнительного соглашения)]
    private static final String ADDENDUM_CONCL_INFO_HEADER_XPATH =
            "//h2[contains(.,'Сведения о заключении договора')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата публикации протокола подведения итогов]
    private static final String ADDENDUM_CONCL_RESULTS_REVIEW_PUBLICATION_DATE_XPATH =
            "//td[contains(., 'Дата публикации протокола подведения итогов')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата создания карточки]
    private static final String ADDENDUM_CONCL_CARD_CREATION_DATE_XPATH =
            "//td[contains(., 'Дата создания карточки')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Переключатель [Крайний срок подписания проекта дополнительного соглашения участником закупки]
    // положение [c даты получения проекта доп. соглашения поставщиком]
    private static final String ADDENDUM_CONCL_SIGN_DATE_START_FROM_SEND_TO_SUPPLIER_XPATH =
            "//input[@id='IsProviderSignDateStartFromSendToSupplier1']";
    //------------------------------------------------------------------------------------------------------------------
    // Переключатель [Крайний срок подписания проекта дополнительного соглашения участником закупки]
    // положение [установить дату вручную]
    private static final String ADDENDUM_CONCL_SIGN_DATE_MANUAL_SET_XPATH =
            "//input[@id='IsProviderSignDateStartFromSendToSupplier2']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Крайний срок подписания проекта дополнительного соглашения участником закупки]
    private static final String ADDENDUM_CONCL_SIGNED_BY_SUPPLIER_END_DATE_XPATH =
            "//td[contains(., 'Крайний срок подписания проекта дополнительного соглашения участником закупки')]" +
                    "/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата направления дополнительного соглашения участнику]
    private static final String ADDENDUM_CONCL_SENT_TO_SUPPLIER_DATE_XPATH =
            "//td[contains(., 'Дата направления дополнительного соглашения участнику')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата подписания дополнительного соглашения участником]
    private static final String ADDENDUM_CONCL_SIGNED_BY_SUPPLIER_DATE_XPATH =
            "//td[contains(., 'Дата подписания дополнительного соглашения участником')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата подписания дополнительного соглашения заказчиком]
    private static final String ADDENDUM_CONCL_SIGNED_BY_CUSTOMER_XPATH =
            "//td[contains(., 'Дата подписания дополнительного соглашения заказчиком')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о закупке и предмете договора (дополнительного соглашения)]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о закупке и предмете договора (дополнительного соглашения)]
    private static final String ADDENDUM_DET_INFO_HEADER_XPATH =
            "//h2[contains(.,'Сведения о закупке и предмете договора')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки]
    private static final String ADDENDUM_DET_INFO_PURCHASE_NUMBER_XPATH =
            "//td[contains(., 'Номер закупки')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование закупки]
    private static final String ADDENDUM_DET_INFO_PURCHASE_NAME_XPATH =
            "//td[contains(., 'Наименование закупки')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование предмета договора]
    private static final String ADDENDUM_DET_SUBJECT_NAME_XPATH =
            "//td[contains(., 'Наименование предмета договора')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Таблица [Позиции договора (дополнительного соглашения)] для коммерческих закупок

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок подраздела [Позиции договора (дополнительного соглашения)]
    private static final String ADDENDUM_POS_TABLE_COMM_HEADER_XPATH =
            "//h2[contains(.,'Позиции договора')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Наименование товара, работ, услуг]
    private static final String ADDENDUM_POS_TABLE_COMM_NAME_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Код ОКПД2]
    private static final String ADDENDUM_POS_TABLE_COMM_OKPD2_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Код ОКВЭД2]
    private static final String ADDENDUM_POS_TABLE_COMM_OKVED2_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[3]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Страна происхождения]
    private static final String ADDENDUM_POS_TABLE_COMM_COUNTRY_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[4]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Единица измерения]
    private static final String ADDENDUM_POS_TABLE_COMM_UNIT_MEASUREMENT_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[5]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Количество]
    private static final String ADDENDUM_POS_TABLE_COMM_QUANTITY_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[6]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Цена за ед.изм.]
    private static final String ADDENDUM_POS_TABLE_COMM_UNIT_PRICE_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[7]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Стоимость]
    private static final String ADDENDUM_POS_TABLE_COMM_COST_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[8]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дополнительные сведения]
    private static final String ADDENDUM_POS_TABLE_COMM_ADDITIONAL_INFORMATION_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[9]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Тип финансового обеспечения]
    private static final String ADDENDUM_POS_TABLE_COMM_TYPE_OF_FINANCIAL_SUPPORT_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[10]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Реестровый номер контракта 44-ФЗ]
    private static final String ADDENDUM_POS_TABLE_COMM_REGISTRY_CONTRACT_NUMBER_44_FZ_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[11]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дата окончания этапа контракта 44-ФЗ]
    private static final String ADDENDUM_POS_TABLE_COMM_END_DATE_OF_THE_CONTRACT_PHASE_44_FZ_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[12]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Код целевой статьи расходов]
    private static final String ADDENDUM_POS_TABLE_COMM_EXPENSE_TARGET_CODE_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[13]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Код вида расходов]
    private static final String ADDENDUM_POS_TABLE_COMM_COST_TYPE_CODE_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[14]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Объем финансового обеспечения (руб.)]
    private static final String ADDENDUM_POS_TABLE_COMM_AMOUNT_OF_FINANCIAL_SECURITY_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[15]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Таблица [Позиции договора (дополнительного соглашения)] для конкурентных закупок

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок подраздела [Позиции договора (дополнительного соглашения)]
    private static final String ADDENDUM_POS_TABLE_COMP_HEADER_XPATH =
            "//h2[contains(.,'Позиции договора')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Наименование товара, работ, услуг]
    private static final String ADDENDUM_POS_TABLE_COMP_NAME_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Код ОКПД2]
    private static final String ADDENDUM_POS_TABLE_COMP_OKPD2_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[3]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Код ОКВЭД2]
    private static final String ADDENDUM_POS_TABLE_COMP_OKVED2_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[4]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Тип объекта закупки]
    private static final String ADDENDUM_POS_TABLE_COMP_OBJECT_TYPE_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[5]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Страна происхождения]
    private static final String ADDENDUM_POS_TABLE_COMP_COUNTRY_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[6]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Единица измерения]
    private static final String ADDENDUM_POS_TABLE_COMP_UNIT_MEASUREMENT_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[7]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Количество]
    private static final String ADDENDUM_POS_TABLE_COMP_QUANTITY_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[8]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Цена за ед.изм.]
    private static final String ADDENDUM_POS_TABLE_COMP_UNIT_PRICE_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[9]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Стоимость]
    private static final String ADDENDUM_POS_TABLE_COMP_COST_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[10]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дополнительные сведения]
    private static final String ADDENDUM_POS_TABLE_COMP_ADDITIONAL_INFORMATION_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[11]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Тип финансового обеспечения]
    private static final String ADDENDUM_POS_TABLE_COMP_TYPE_OF_FINANCIAL_SUPPORT_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[12]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Реестровый номер контракта 44-ФЗ]
    private static final String ADDENDUM_POS_TABLE_COMP_REGISTRY_CONTRACT_NUMBER_44_FZ_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[13]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дата окончания этапа контракта 44-ФЗ]
    private static final String ADDENDUM_POS_TABLE_COMP_END_DATE_OF_THE_CONTRACT_PHASE_44_FZ_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[14]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Код целевой статьи расходов]
    private static final String ADDENDUM_POS_TABLE_COMP_EXPENSE_TARGET_CODE_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[15]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Код вида расходов]
    private static final String ADDENDUM_POS_TABLE_COMP_COST_TYPE_CODE_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[16]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Объем финансового обеспечения (руб.)]
    private static final String ADDENDUM_POS_TABLE_COMP_AMOUNT_OF_FINANCIAL_SECURITY_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[17]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Экспорт объектов в Excel]
    private static final String ADDENDUM_POS_TABLE_EXPORT_TO_EXCEL_BUTTON_XPATH =
            "//button[contains(.,'Экспорт объектов в Excel')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Импорт объектов из Excel] ID
    private static final String ADDENDUM_POS_TABLE_IMPORT_FROM_EXCEL_ID = "UploadFile_Positions";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить позицию]
    private static final String ADDENDUM_POS_TABLE_ADD_POSITION_BUTTON_XPATH = "//span[contains(.,'Добавить позицию')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Добавление информации о товаре, работе или услуге]

    //------------------------------------------------------------------------------------------------------------------
    // Загаловок [Добавление информации о товаре, работе или услуге]
    private static final String ADDENDUM_POS_TABLE_ADD_POSITION_HEADER_XPATH =
            "//h3[contains(.,'Добавление информации о товаре, работе или услуге')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование товара, работы, услуг]
    private static final String ADDENDUM_POS_TABLE_ADD_POSITION_NAME_ID = "CurrentPosition_Name";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Код ОКПД2]
    private static final String ADDENDUM_POS_TABLE_ADD_POSITION_OKPD_2_ID = "CurrentPosition_Okpd2";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Код ОКВЭД2]
    private static final String ADDENDUM_POS_TABLE_ADD_POSITION_OKVED2_ID = "CurrentPosition_Okved2";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Страна происхождения товара] - область открытия списка по которой нужно будет сделать щелчок мышью
    private static final String ADDENDUM_POS_TABLE_ADD_POSITION_COUNTRY_OF_ORIGIN_LIST_OPEN_VALUES_XPATH =
            "//div[@class='k-multiselect-wrap k-floatwrap']";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Страна происхождения товара] - список значений открыт - значение [Российская федерация]
    private static final String ADDENDUM_POS_TABLE_ADD_POSITION_COUNTRY_OF_ORIGIN_LIST_DESIRED_VALUE_XPATH =
            "//div[@class='k-multiselect-wrap k-floatwrap']/li[contains(., 'Российская Федерация')]";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Страна происхождения товара] - текущее выбранное значение
    private static final String ADDENDUM_POS_TABLE_ADD_POSITION_COUNTRY_OF_ORIGIN_LIST_SELECTED_VALUE_XPATH =
            "div[@class='k-multiselect-wrap k-floatwrap']/li/span";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Невозможно определить количество (объем)]
    private static final String ADDENDUM_POS_TABLE_ADD_POSITION_QUANTITY_UNDEFINED_ID =
            "CurrentPosition_QuantityUndefined";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Единица измерения (код ОКЕИ)] (текущее значение)
    private static final String ADDENDUM_POS_TABLE_ADD_POSITION_OKEI_VALUE_XPATH =
            "//input[@name='CurrentPosition.Okei_input']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Единица измерения (код ОКЕИ)] (список возможных значений поля открыт) -> требуемое значение в списке
    private static final String ADDENDUM_POS_TABLE_ADD_POSITION_OKEI_VALUE_IN_OPENED_LIST_XPATH =
            "//*[@id='CurrentPosition_Okei_listbox']/li[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Единица измерения (код ОКЕИ)] (кнопка [V] - развернуть список)
    private static final String ADDENDUM_POS_TABLE_ADD_POSITION_OKEI_LIST_CLOSED_XPATH =
            "//span[@aria-controls='CurrentPosition_Okei_listbox']";
    //------------------------------------------------------------------------------------------------------------------
    // Счетчик [Цена за единицу измерения]
    private static final String ADDENDUM_POS_TABLE_ADD_POSITION_UNIT_PRICE_XPATH =
            "//label[contains(@for,'CurrentPosition_UnitPrice')]/div/span/input";
    //------------------------------------------------------------------------------------------------------------------
    // Счетчик [Количество]
    private static final String ADDENDUM_POS_TABLE_ADD_POSITION_QUANTITY_XPATH =
            "//form-group[@label='CurrentPosition_Quantity']//input";
    //------------------------------------------------------------------------------------------------------------------
    // Конпка [Добавить]
    private static final String ADDENDUM_POS_TABLE_ADD_POSITION_ADD_BUTTON_XPATH =
            "//span[@data-bind='invisible: isInEdit'][contains(.,'Добавить')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о заказчике]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о заказчике]
    private static final String DET_OF_THE_PROC_CUST_HEADER_XPATH =
            "//h2[contains(.,'Сведения о заказчике')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Заказчик]
    private static final String DET_OF_THE_PROC_CUST_XPATH =
            "//a[@target='_blank'][contains(.,'ООО Автотестеры 223')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения об участнике закупки]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Севедения об участнике закупки]
    private static final String DET_OF_THE_PROC_PART_HEADER_XPATH =
            "//h2[contains(.,'Сведения об участнике закупки')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Участник закупки]
    private static final String DET_OF_THE_PROC_PART_APPLICATION_PARTICIPANT_ID = "ParticipantViewButton";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер заявки]
    private static final String DET_OF_THE_PROC_PART_APPLICATION_NUMBER_XPATH =
            "//td[contains(., 'Номер заявки')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [Документы участника - Заявка на участие]
    private static final String DET_OF_THE_PROC_PART_APPLICATION_LOT_TRADE_FORM_XPATH =
            "//a[@class='nowrap'][contains(.,'Заявка на участие')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [Документы участника - Аккредитационные документы]
    private static final String DET_OF_THE_PROC_PART_APPLICATION_LOT_PARTICIPANT_FORM_XPATH =
            "//a[@class='nowrap'][contains(.,'Аккредитационные документы')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Закрыть] в диалоговом окне [Сведения о поставщике]
    private static final String DET_OF_THE_PROC_PART_APPLICATION_PARTICIPANT_INFORMATION_POPUP_CLOSE_BUTTON_ID =
            "CloseOrganizationPopup";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Документы]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Документы]
    private static final String ADDENDUM_DOCS_HEADER_XPATH = "//h2[contains(.,'Документы')]";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок подраздела [Проект дополнительного соглашения]
    private static final String ADDENDUM_DOCS_CONTRACT_PROJECT_HEADER_XPATH =
            "//h3[contains(.,'Проект дополнительного соглашения')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [Прикрепить файл] в подразделе [Проект дополнительного соглашения]
    private static final String ADDENDUM_DOCS_ATTACH_FILE_TO_CONTRACT_PROJECT_XPATH =
            "//input[@id='ContractProjectUpload']";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка на прикрепленный файл в подразделе [Проект дополнительного соглашения]
    private static final String ADDENDUM_DOCS_CONTRACT_PROJECT_ATTACHED_FILE_XPATH =
            "//*[@id='ContractProjectGrid']/tbody/tr/td/a[contains(@href, 'https://')]";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок подраздела [Другие документы]
    private static final String ADDENDUM_DOCS_OTHER_DOCS_HEADER_XPATH =
            "//h3[contains(.,'Другие документы')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [Прикрепить файл] в подразделе [Другие документы]
    private static final String ADDENDUM_DOCS_ATTACH_FILE_TO_OTHER_DOCS_XPATH =
            "//input[@id='OtherUpload']";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка на прикрепленный файл в подразделе [Другие документы]
    private static final String ADDENDUM_DOCS_OTHER_DOCS_ATTACHED_FILE_XPATH =
            "//*[@id='OtherGrid']/tbody/tr/td/a[contains(@href, 'https://')]";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок подраздела [Протокол разногласий]
    private static final String ADDENDUM_DOCS_DISAGREEMENTS_PROTOCOL_HEADER_XPATH =
            "//h3[contains(.,'Протокол разногласий')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка на прикрепленный файл в подразделе [Протокол разногласий]
    private static final String ADDENDUM_DOCS_DISAGREEMENTS_PROTOCOL_ATTACHED_FILE_XPATH =
            "//*[@id='IssueProtocolsGrid']/tbody/tr/td/a[contains(@href, 'https://')]";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок подраздела [Документы в обеспечении исполнения дополнительного соглашения и другие документы]
    private static final String ADDENDUM_DOCS_FIN_SUPPORT_AND_OTHER_DOCS_HEADER_XPATH =
            "//h3[contains(.,'Документы в обеспечение исполнения дополнительного соглашения и другие документы')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка на прикрепленный файл в подразделе [Документы в обеспечении исполнения дополнительного соглашения и
    // другие документы]
    private static final String ADDENDUM_DOCS_FIN_SUPPORT_AND_OTHER_DOCS_ATTACHED_FILE_XPATH =
            "//*[@id='ProvisionProtocolsGrid']/tbody/tr/td/a[contains(@href, 'https://')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Таблица [Многосторонее подписание]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок таблицы [Многосторонее подписание]
    private static final String MULTI_SIGN_TABLE_XPATH = "//h2[contains(.,'Многостороннее подписание')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка на карточку клиента в поле [Наименование]
    private static final String MULTI_SIGN_TABLE_CUSTOMER_XPATH =
            "//a[@target='_blank'][contains(.,'ООО Автотестеры 223')]";
    //------------------------------------------------------------------------------------------------------------------
    // Чек-бокс [Финальный подписант]
    private static final String MULTI_SIGN_TABLE_FINAL_SIGNATORY_XPATH = "//input[@class='cbxFinalParticipant']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Блок кнопок управления договором

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Создать сведения об исполнении]
    private static final String CREATE_EXECUTION_DETAILS_BUTTON_XPATH =
            "//a[contains(.,'Создать сведения об исполнении')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Создать сведения о расторжении]
    private static final String CREATE_TERMINATION_DETAILS_BUTTON_XPATH =
            "//a[contains(.,'Создать сведения о расторжении')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Сохранить]
    private static final String SAVE_CONTRACT_BUTTON_XPATH = "//button[@id='saveContract']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Назначить ответственного для подписания]
    private static final String ASSIGN_RESPONDER_TO_DEAL_BUTTON_ID = "AssignResponderToDealSigningPopUpButton";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Переназначить ответственного для подписания]
    private static final String REASSIGN_RESPONDER_TO_DEAL_BUTTON_XPATH =
            "//button[contains(.,'Переназначить ответственного для подписания')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Направить участнику]
    private static final String SEND_CONTRACT_BUTTON_XPATH = "//*[@id='SendContractToProviderButton']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Внести изменения]
    private static final String MAKE_CHANGES_BUTTON_ID = "MakeChangesButton";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Подписать договор]
    private static final String SIGN_CONTRACT_BUTTON_XPATH = "//button[@id='AcceptAndSign']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Протокол отказа от заключения]
    private static final String REFUSE_CONTRACT_BUTTON_ID = "RefuseContractButton";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Продолжить] в диалоговом окне [Предупреждение]
    private static final String CONTINUE_BUTTON_XPATH = "//button[@id='CommonConfirmWindowOk']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Выгрузить информацию]
    private static final String DOWNLOAD_ADDENDUM_INFO_LINK_XPATH = "//a[contains(., 'Выгрузить информацию')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [История]
    private static final String HISTORY_BUTTON_ID = "HistoryButton";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Диалоговое окно [История]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок диалогового окна [История]
    private static final String ADDENDUM_HIST_WINDOW_TITLE_ID = "ContractsHistoryWindow_wnd_title";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Диалоговое окно [Изменение цены договора]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок диалогового окна [Изменение цены договора]
    private static final String CH_ADDENDUM_PRICE_WINDOW_TITLE_ID = "ChangeContractPriceWindow_wnd_title";
    //------------------------------------------------------------------------------------------------------------------
    // Переключатель [Причина - Изменение цены договора в результате предоставления преференций]
    // в диалоговом окне [Изменение цены договора]
    private static final String CH_ADDENDUM_PRICE_NEW_CONTRACT_PRICE_REASON_PREFERENCES_ID = "IncreaseType6";
    //------------------------------------------------------------------------------------------------------------------
    // Переключатель [Причина - Изменение цены договора по решению заказчика или соглашению сторон]
    // в диалоговом окне [Изменение цены договора]
    private static final String CH_ADDENDUM_PRICE_NEW_CONTRACT_PRICE_REASON_AGREEMENT_ID = "IncreaseType7";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary blockNames = new Dictionary();         // основные разделы страницы
    private final Dictionary fieldNames = new Dictionary();         // все поля на странице
    private final Dictionary buttons = new Dictionary();            // все кнопки на странице
    private final Dictionary radioButtonNames = new Dictionary();   // все переключатели на странице
    private final Dictionary checkBoxNames = new Dictionary();      // все флажки на странице
    private final Dictionary contrPositionsComm = new Dictionary(); // таблица в р. [Позиции договора] для комм. закупки
    private final Dictionary contrPositionsComp = new Dictionary(); // таблица в р. [Позиции договора] для конк. закупки

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public ContractsAddendumPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Сведения о договоре (дополнительном соглашении)", ADDENDUM_GEN_INFO_HEADER_XPATH);
        blockNames.add("Сведения о заключении договора (дополнительного соглашения)", ADDENDUM_CONCL_INFO_HEADER_XPATH);
        blockNames.add("Проект дополнительного соглашения", ADDENDUM_DOCS_CONTRACT_PROJECT_HEADER_XPATH);
        blockNames.add("Позиции договора комм.", ADDENDUM_POS_TABLE_COMM_HEADER_XPATH);
        blockNames.add("Позиции договора конк.", ADDENDUM_POS_TABLE_COMP_HEADER_XPATH);
        blockNames.add("Добавление информации о товаре, работе или услуги",
                ADDENDUM_POS_TABLE_ADD_POSITION_HEADER_XPATH);
        blockNames.add("Сведения о закупке и предмете договора", ADDENDUM_DET_INFO_HEADER_XPATH);
        blockNames.add("Сведения о заказчике", DET_OF_THE_PROC_CUST_HEADER_XPATH);
        blockNames.add("Сведения об участнике закупки", DET_OF_THE_PROC_PART_HEADER_XPATH);
        blockNames.add("Документы", ADDENDUM_DOCS_HEADER_XPATH);
        blockNames.add("Многосторонее подписание", MULTI_SIGN_TABLE_XPATH);
        blockNames.add("История", ADDENDUM_HIST_WINDOW_TITLE_ID);
        blockNames.add("Изменение цены договора", CH_ADDENDUM_PRICE_WINDOW_TITLE_ID);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Основной договор", ADDENDUM_GEN_INFO_MAIN_CONTRACT_XPATH);
        fieldNames.add("Статус дополнительного соглашения", ADDENDUM_GEN_INFO_STATUS_XPATH);
        fieldNames.add("Номер карточки договора", ADDENDUM_GEN_INFO_NUMBER_XPATH);
        fieldNames.add("Номер договора в ЕИС", ADDENDUM_GEN_INFO_EIS_NUMBER_XPATH);
        fieldNames.add("Сумма договора с учетом дополнительных соглашений", ADDENDUM_GEN_INFO_SUM_XPATH);
        fieldNames.add("Дата начала исполнения дополнительного соглашения",
                ADDENDUM_GEN_INFO_DATE_START_PERFORMANCE_XPATH);
        fieldNames.add("Условия начала исполнения дополнительного соглашения",
                ADDENDUM_GEN_INFO_TERM_START_PERFORMANCE_XPATH);
        fieldNames.add("Дата окончания исполнения дополнительного соглашения",
                ADDENDUM_GEN_INFO_DATE_END_PERFORMANCE_XPATH);
        fieldNames.add("Условия окончания исполнения дополнительного соглашения",
                ADDENDUM_GEN_INFO_TERM_END_PERFORMANCE_XPATH);
        fieldNames.add("Крайний срок подписания проекта дополнительного соглашения участником закупки",
                ADDENDUM_CONCL_SIGNED_BY_SUPPLIER_END_DATE_XPATH);
        fieldNames.add("Дата направления дополнительного соглашения участнику",
                ADDENDUM_CONCL_SENT_TO_SUPPLIER_DATE_XPATH);
        fieldNames.add("Дата подписания дополнительного соглашения участником",
                ADDENDUM_CONCL_SIGNED_BY_SUPPLIER_DATE_XPATH);
        fieldNames.add("Дата подписания дополнительного соглашения заказчиком",
                ADDENDUM_CONCL_SIGNED_BY_CUSTOMER_XPATH);
        fieldNames.add("Дата публикации протокола подведения итогов",
                ADDENDUM_CONCL_RESULTS_REVIEW_PUBLICATION_DATE_XPATH);
        fieldNames.add("Дата создания карточки", ADDENDUM_CONCL_CARD_CREATION_DATE_XPATH);
        fieldNames.add("Номер закупки", ADDENDUM_DET_INFO_PURCHASE_NUMBER_XPATH);
        fieldNames.add("Наименование закупки", ADDENDUM_DET_INFO_PURCHASE_NUMBER_XPATH);
        fieldNames.add("Наименование предмета договора", ADDENDUM_DET_SUBJECT_NAME_XPATH);
        fieldNames.add("Наименование товара, работ, услуг", ADDENDUM_POS_TABLE_ADD_POSITION_NAME_ID);
        fieldNames.add("Цена за единицу измерения", ADDENDUM_POS_TABLE_ADD_POSITION_UNIT_PRICE_XPATH);
        fieldNames.add("Количество", ADDENDUM_POS_TABLE_ADD_POSITION_QUANTITY_XPATH);
        fieldNames.add("Номер заявки", DET_OF_THE_PROC_PART_APPLICATION_NUMBER_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        buttons.add("Ссылка на основной договор", ADDENDUM_GEN_INFO_MAIN_CONTRACT_LINK_XPATH);
        buttons.add("Изменить цену договора", ADDENDUM_GEN_INFO_CHANGE_ADDENDUM_PRICE_XPATH);
        buttons.add("Создать сведения об исполнении", CREATE_EXECUTION_DETAILS_BUTTON_XPATH);
        buttons.add("Создать сведения о расторжении", CREATE_TERMINATION_DETAILS_BUTTON_XPATH);
        buttons.add("Направить участнику", SEND_CONTRACT_BUTTON_XPATH);
        buttons.add("Внести изменения", MAKE_CHANGES_BUTTON_ID);
        buttons.add("Экспорт объектов в Excel", ADDENDUM_POS_TABLE_EXPORT_TO_EXCEL_BUTTON_XPATH);
        buttons.add("Импорт объектов из Excel", ADDENDUM_POS_TABLE_IMPORT_FROM_EXCEL_ID);
        buttons.add("Добавить позицию", ADDENDUM_POS_TABLE_ADD_POSITION_BUTTON_XPATH);
        buttons.add("Добавить", ADDENDUM_POS_TABLE_ADD_POSITION_ADD_BUTTON_XPATH);
        buttons.add("Сведения о заказчике", DET_OF_THE_PROC_CUST_XPATH);
        buttons.add("Сведения об участнике закупки", DET_OF_THE_PROC_PART_APPLICATION_PARTICIPANT_ID);
        buttons.add("Закрыть сведения о поставщике",
                DET_OF_THE_PROC_PART_APPLICATION_PARTICIPANT_INFORMATION_POPUP_CLOSE_BUTTON_ID);
        buttons.add("Заявка на участие", DET_OF_THE_PROC_PART_APPLICATION_LOT_TRADE_FORM_XPATH);
        buttons.add("Аккредитационные документы", DET_OF_THE_PROC_PART_APPLICATION_LOT_PARTICIPANT_FORM_XPATH);
        buttons.add("Наименование подписанта", MULTI_SIGN_TABLE_CUSTOMER_XPATH);
        buttons.add("Сохранить", SAVE_CONTRACT_BUTTON_XPATH);
        buttons.add("Назначить ответственного для подписания", ASSIGN_RESPONDER_TO_DEAL_BUTTON_ID);
        buttons.add("Переназначить ответственного для подписания", REASSIGN_RESPONDER_TO_DEAL_BUTTON_XPATH);
        buttons.add("Подписать договор", SIGN_CONTRACT_BUTTON_XPATH);
        buttons.add("Протокол отказа от заключения договора", REFUSE_CONTRACT_BUTTON_ID);
        buttons.add("Продолжить с договором", CONTINUE_BUTTON_XPATH);
        buttons.add("История", HISTORY_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
        radioButtonNames.add("c даты получения проекта договора поставщиком",
                ADDENDUM_CONCL_SIGN_DATE_START_FROM_SEND_TO_SUPPLIER_XPATH);
        radioButtonNames.add("установить дату вручную", ADDENDUM_CONCL_SIGN_DATE_MANUAL_SET_XPATH);
        radioButtonNames.add("Причина - Изменение цены договора в результате предоставления преференций",
                CH_ADDENDUM_PRICE_NEW_CONTRACT_PRICE_REASON_PREFERENCES_ID);
        radioButtonNames.add("Причина - Изменение цены договора по решению заказчика или соглашению сторон",
                CH_ADDENDUM_PRICE_NEW_CONTRACT_PRICE_REASON_AGREEMENT_ID);
        //--------------------------------------------------------------------------------------------------------------
        checkBoxNames.add("Прекращение обязательств сторон", ADDENDUM_GEN_INFO_IS_TERMINATION_ID);
        checkBoxNames.add("Возможность продления срока действия доп. соглашения", ADDENDUM_GEN_INFO_IS_EXTENSION_ID);
        checkBoxNames.add("Невозможно определить количество (объем)",
                ADDENDUM_POS_TABLE_ADD_POSITION_QUANTITY_UNDEFINED_ID);
        checkBoxNames.add("Финальный подписант", MULTI_SIGN_TABLE_FINAL_SIGNATORY_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        contrPositionsComm.add("Наименование товара, работ, услуг", ADDENDUM_POS_TABLE_COMM_NAME_XPATH);
        contrPositionsComm.add("Код ОКПД2", ADDENDUM_POS_TABLE_COMM_OKPD2_XPATH);
        contrPositionsComm.add("Код ОКВЭД2", ADDENDUM_POS_TABLE_COMM_OKVED2_XPATH);
        contrPositionsComm.add("Страна происхождения", ADDENDUM_POS_TABLE_COMM_COUNTRY_XPATH);
        contrPositionsComm.add("Единица измерения", ADDENDUM_POS_TABLE_COMM_UNIT_MEASUREMENT_XPATH);
        contrPositionsComm.add("Количество", ADDENDUM_POS_TABLE_COMM_QUANTITY_XPATH);
        contrPositionsComm.add("Цена за ед.изм.", ADDENDUM_POS_TABLE_COMM_UNIT_PRICE_XPATH);
        contrPositionsComm.add("Стоимость", ADDENDUM_POS_TABLE_COMM_COST_XPATH);
        contrPositionsComm.add("Дополнительные сведения", ADDENDUM_POS_TABLE_COMM_ADDITIONAL_INFORMATION_XPATH);
        contrPositionsComm.add("Тип финансового обеспечения", ADDENDUM_POS_TABLE_COMM_TYPE_OF_FINANCIAL_SUPPORT_XPATH);
        contrPositionsComm.add("Реестровый номер контракта 44-ФЗ",
                ADDENDUM_POS_TABLE_COMM_REGISTRY_CONTRACT_NUMBER_44_FZ_XPATH);
        contrPositionsComm.add("Дата окончания этапа контракта 44-ФЗ",
                ADDENDUM_POS_TABLE_COMM_END_DATE_OF_THE_CONTRACT_PHASE_44_FZ_XPATH);
        contrPositionsComm.add("Код целевой статьи расходов", ADDENDUM_POS_TABLE_COMM_EXPENSE_TARGET_CODE_XPATH);
        contrPositionsComm.add("Код вида расходов", ADDENDUM_POS_TABLE_COMM_COST_TYPE_CODE_XPATH);
        contrPositionsComm.add("Объем финансового обеспечения (руб.)",
                ADDENDUM_POS_TABLE_COMM_AMOUNT_OF_FINANCIAL_SECURITY_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        contrPositionsComp.add("Наименование товара, работ, услуг", ADDENDUM_POS_TABLE_COMP_NAME_XPATH);
        contrPositionsComp.add("Код ОКПД2", ADDENDUM_POS_TABLE_COMP_OKPD2_XPATH);
        contrPositionsComp.add("Код ОКВЭД2", ADDENDUM_POS_TABLE_COMP_OKVED2_XPATH);
        contrPositionsComp.add("Тип объекта закупки", ADDENDUM_POS_TABLE_COMP_OBJECT_TYPE_XPATH);
        contrPositionsComp.add("Страна происхождения", ADDENDUM_POS_TABLE_COMP_COUNTRY_XPATH);
        contrPositionsComp.add("Единица измерения", ADDENDUM_POS_TABLE_COMP_UNIT_MEASUREMENT_XPATH);
        contrPositionsComp.add("Количество", ADDENDUM_POS_TABLE_COMP_QUANTITY_XPATH);
        contrPositionsComp.add("Цена за ед.изм.", ADDENDUM_POS_TABLE_COMP_UNIT_PRICE_XPATH);
        contrPositionsComp.add("Стоимость", ADDENDUM_POS_TABLE_COMP_COST_XPATH);
        contrPositionsComp.add("Дополнительные сведения", ADDENDUM_POS_TABLE_COMP_ADDITIONAL_INFORMATION_XPATH);
        contrPositionsComp.add("Тип финансового обеспечения", ADDENDUM_POS_TABLE_COMP_TYPE_OF_FINANCIAL_SUPPORT_XPATH);
        contrPositionsComp.add("Реестровый номер контракта 44-ФЗ",
                ADDENDUM_POS_TABLE_COMP_REGISTRY_CONTRACT_NUMBER_44_FZ_XPATH);
        contrPositionsComp.add("Дата окончания этапа контракта 44-ФЗ",
                ADDENDUM_POS_TABLE_COMP_END_DATE_OF_THE_CONTRACT_PHASE_44_FZ_XPATH);
        contrPositionsComp.add("Код целевой статьи расходов", ADDENDUM_POS_TABLE_COMP_EXPENSE_TARGET_CODE_XPATH);
        contrPositionsComp.add("Код вида расходов", ADDENDUM_POS_TABLE_COMP_COST_TYPE_CODE_XPATH);
        contrPositionsComp.add("Объем финансового обеспечения (руб.)",
                ADDENDUM_POS_TABLE_COMP_AMOUNT_OF_FINANCIAL_SECURITY_XPATH);
        //--------------------------------------------------------------------------------------------------------------

    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Раздел [Сведения о договоре (дополнительном соглашении)]


    /**
     * Проверяет статус дополнительного соглашения [Сведения о договоре (дополнительном соглашении)] ->
     * -> [Статус дополнительного соглашения].
     *
     * @param expStatus        ожидаемый статус дополнительного соглашения
     * @param numberOfAttempts количество попыток
     */
    public void checkAddendumStatus(String expStatus, int numberOfAttempts) throws Exception {
        long timeout = 10;
        long elapsed = 0;
        String pref = ">>> (checkAddendumStatus) ";
        $(this.getBy(fieldNames.find("Статус дополнительного соглашения"))).waitUntil(visible, this.timeout);
        String actualStatus = $(this.getBy(fieldNames.find("Статус дополнительного соглашения"))).getText();
        for (int i = 1; i <= numberOfAttempts; i++) {
            System.out.println(pref + "Проверка статуса дополнительного соглашения №: [" + i + "].");
            if (actualStatus.equals(expStatus)) break;
            else {
                getWebDriver().navigate().refresh();
                TimeUnit.SECONDS.sleep(timeout);
                elapsed = elapsed + timeout;
                this.waitLoadingImage();
                $(this.getBy(fieldNames.find("Статус дополнительного соглашения"))).waitUntil(visible, this.timeout);
                actualStatus = $(this.getBy(fieldNames.find("Статус дополнительного соглашения"))).getText();
            }
        }
        System.out.println(pref + "Ожидание смены статуса дополнительного соглашения составило: [" + elapsed +
                "] секунд.");
        Assert.assertEquals(pref + "Некорректный статус дополнительного соглашения: ", expStatus, actualStatus);
        System.out.println(pref + "Произведена проверка статуса дополнительного соглашения: [" + expStatus + "].");
    }


    /**
     * Проверяет что номер дополнительного соглашения [Сведения о договоре (дополнительном соглашении)] ->
     * -> [Номер карточки договора] не равен номеру основного договора [Сведения о договоре (дополнительном соглашении)]
     * -> [Основной договор].
     */
    public void checkAddendumNumberNotEqualToContractNumber() {
        String contract = $(this.getBy(ADDENDUM_GEN_INFO_MAIN_CONTRACT_XPATH)).getText();
        String addendum = $(this.getBy(ADDENDUM_GEN_INFO_NUMBER_XPATH)).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: номер дополнительного соглашения: [%s], номер основного договора: [%s]%n",
                addendum, contract);
        Assert.assertNotEquals("Номер дополнительного соглашения совпадает с номером основного договора!",
                addendum, contract);
    }


    /**
     * Заполняет поле [Дата начала исполнения договора].
     *
     * @param startPerformanceDate дата начала исполнения договора
     */
    public void setStartPerformanceDate(String startPerformanceDate) throws Exception {

        // TODO Убрать условный оператор когда все формы договора будут иметь этот переключатель
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        if ($(this.getBy(ADDENDUM_GEN_INFO_SET_MANUAL_RADIO_BUTTON_ID)).isDisplayed())
            $(this.getBy(ADDENDUM_GEN_INFO_SET_MANUAL_RADIO_BUTTON_ID)).click();
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        $(this.getBy(ADDENDUM_GEN_INFO_DATE_START_PERFORMANCE_XPATH)).waitUntil(exist, timeout, polling).clear();
        $(this.getBy(ADDENDUM_GEN_INFO_DATE_START_PERFORMANCE_XPATH)).sendKeys(startPerformanceDate);
        TimeUnit.SECONDS.sleep(1);
        System.out.printf(
                "[ИНФОРМАЦИЯ]: произведено заполнения поля: [Дата начала исполнения договора] значением [%s].%n",
                startPerformanceDate);
    }


    /**
     * Заполняет поле [Дата окончания исполнения договора].
     *
     * @param endPerformanceDate дата окончания исполнения договора
     */
    public void setEndPerformanceDate(String endPerformanceDate) throws Exception {
        $(this.getBy(ADDENDUM_GEN_INFO_DATE_END_PERFORMANCE_XPATH)).waitUntil(exist, timeout, polling).clear();
        $(this.getBy(ADDENDUM_GEN_INFO_DATE_END_PERFORMANCE_XPATH)).sendKeys(endPerformanceDate);
        TimeUnit.SECONDS.sleep(1);
        System.out.printf(
                "[ИНФОРМАЦИЯ]: произведено заполнения поля [Дата окончания исполнения договора] значением [%s].%n",
                endPerformanceDate);
    }

    // endregion


    // region Раздел [Сведения о закупке и предмете договора (дополнительного соглашения)]


    /**
     * Сравнивает значение полей раздела [Сведения о закупке и предмете договора (дополнительного соглашения)] со
     * значением соответствующих полей в договоре
     */
    public void checkAddendumPurchaseAndItemInfo() {
        //собираем значения из конфига
        String contractPurchaseNumber = config.getParameter("PurchaseNumber");
        String contractPurchaseName = config.getParameter("PurchaseName");
        String contractSubjectName = "Лот 1";

        //собираем значения из полей раздела [Сведения о закупке и предмете договора (дополнительного соглашения)]
        String addendumPurchaseNumber = $(this.getBy(ADDENDUM_DET_INFO_PURCHASE_NUMBER_XPATH)).getText();
        String addendumPurchaseName = $(this.getBy(ADDENDUM_DET_INFO_PURCHASE_NAME_XPATH)).getText();
        String addendumSubjectName = $(this.getBy(ADDENDUM_DET_SUBJECT_NAME_XPATH)).getText();

        String messagePurchaseNumber = String.format("[ОШИБКА]: Поле доп. соглашения содержит значение '%s'," +
                "не соответствующее значению договора '%s'", addendumPurchaseNumber, contractPurchaseNumber);
        String messagePurchaseName = String.format("[ОШИБКА]: Поле доп. соглашения содержит значение '%s'," +
                "не соответствующее значению договора '%s'", addendumPurchaseName, contractPurchaseName);
        String messageSubjectName = String.format("[ОШИБКА]: Поле доп. соглашения содержит значение '%s'," +
                "не соответствующее значению договора '%s'", addendumSubjectName, contractSubjectName);

        //проверяем значения полей раздела
        Assert.assertTrue(messagePurchaseNumber, addendumPurchaseNumber.contains(contractPurchaseNumber));
        Assert.assertTrue(messagePurchaseName, addendumPurchaseName.contains(contractPurchaseName));
        Assert.assertTrue(messageSubjectName, addendumSubjectName.contains(contractSubjectName));
    }

    // endregion

    // region Раздел [Позиции договора (дополнительного соглашения)]

    /**
     * Проверяет количество строк в таблице [Позиции договора (дополнительного соглашения)].
     *
     * @param rows ожидаемое количество строк в таблице
     */
    public void checkNumberOfRowsInAddendumPositionsTable(String rows) {
        int expectedSize = Integer.parseInt(rows);
        this.checkElementsCollectionSize(
                $$(this.getBy(contrPositionsComm.find("Наименование товара, работ, услуг"))), expectedSize);
    }

    /**
     * Проверяет текст ячейки в таблице [Позиции договора (дополнительного соглашения)] коммерческой для столбца с
     * указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     */
    public void verifyCellByNameInLineByNumberFromAddendumPasitionsTableCommercialForText(
            String columnName, String rowNumber, String cellValue) {
        this.verifyCellInTableByColumnElementsAndLineNumberForText("Позиции договора",
                columnName, $$(this.getBy(contrPositionsComm.find(columnName))), rowNumber, cellValue);
    }

    /**
     * Проверяет текст ячейки в таблице [Позиции договора (дополнительного соглашения)] конкурентной для столбца с
     * указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     */
    public void verifyCellByNameInLineByNumberFromAddendumPositionsTableCompetitiveForText(
            String columnName, String rowNumber, String cellValue) {
        this.verifyCellInTableByColumnElementsAndLineNumberForText("Позиции договора",
                columnName, $$(this.getBy(contrPositionsComp.find(columnName))), rowNumber, cellValue);
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
     * Устанавливает значение поля [Код ОКПД2].
     *
     * @param lotOkpd2Value требуемое значение поля
     */
    public void setLotOkpd2(String lotOkpd2Value) throws Exception {
        SelenideElement lotOkpd2 = $(this.getBy(ADDENDUM_POS_TABLE_ADD_POSITION_OKPD_2_ID));
        lotOkpd2.waitUntil(exist, timeout, polling);
        this.scrollToCenter(lotOkpd2);
        lotOkpd2.sendKeys(lotOkpd2Value);
        TimeUnit.SECONDS.sleep(3); // Ожидание, пока подтянется значение из справочника (контрол медленный)
        lotOkpd2.sendKeys(Keys.ENTER);    // Подтверждаем ввод значения в поле
        TimeUnit.SECONDS.sleep(1); // Задержка, чтобы ввод в поле отработал корректно
        lotOkpd2.sendKeys(Keys.TAB);      // Убираем фокус ввода из поля для сохранения установленного значения
    }


    /**
     * Устанавливает значение поля [Код ОКВЭД2].
     *
     * @param lotOkved2Value требуемое значение поля
     */
    public void setLotOkved2(String lotOkved2Value) throws Exception {
        $(this.getBy(ADDENDUM_POS_TABLE_ADD_POSITION_OKVED2_ID)).waitUntil(exist, timeout, polling).
                sendKeys(lotOkved2Value);
        TimeUnit.SECONDS.sleep(3); // Ожидание, пока подтянется значение из справочника (контрол медленный)
        SelenideElement tab = $(this.getBy(ADDENDUM_POS_TABLE_ADD_POSITION_HEADER_XPATH));
        this.scrollToCenter(tab);
        tab.waitUntil(visible, timeout, polling).click();
    }


    /**
     * Заполняет значение поля [Единица измерения (код ОКЕИ)] значением с указанным порядковым номером из списка.
     *
     * @param okeiNumber порядковый номер в списке сверху-вниз
     */
    public void fillOkeiCode(String okeiNumber) throws Exception {
        SelenideElement value = $(this.getBy(ADDENDUM_POS_TABLE_ADD_POSITION_OKEI_VALUE_XPATH));
        String oldValue = value.waitUntil(exist, timeout, polling).getValue();
        String newValue = oldValue;

        this.scrollToCenter(value);

        for (int i = 0; i < 50; i++) {
            System.out.printf("[ИНФОРМАЦИЯ]: попытка № [%d] открыть список [Единица измерения (код ОКЕИ)].%n", i + 1);
            $(this.getBy(ADDENDUM_POS_TABLE_ADD_POSITION_OKEI_LIST_CLOSED_XPATH)).waitUntil(visible, timeout, polling).
                    click();
            TimeUnit.SECONDS.sleep(3);
            String xpathToOkeiName = String.format(ADDENDUM_POS_TABLE_ADD_POSITION_OKEI_VALUE_IN_OPENED_LIST_XPATH,
                    okeiNumber);
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
     * Устанавливает значение полей с элементами увеличения и уменьшения значения (стрелки вверх и вниз справа от поля).
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     */
    public void setKendoNumericTextBoxField(String fieldName, String value) throws Exception {
        this.logFilledFieldName(fieldName, value);

        $(this.getBy(fieldNames.find(fieldName))).waitUntil(clickable, timeout, polling).sendKeys(value +
                Keys.TAB);
        TimeUnit.SECONDS.sleep(3);
    }


    /**
     * Заполняет поле [Страна происхождения товара] в разделе [Добавление информации о товаре, работе или услуге]
     * фиксированным значением [Российская федерация].
     */
    public void setCountryOfOriginFieldWithTheValueRussianFederation() throws Exception {
        this.scrollToCenter(getBy(ADDENDUM_POS_TABLE_ADD_POSITION_COUNTRY_OF_ORIGIN_LIST_OPEN_VALUES_XPATH));
        $(this.getBy(ADDENDUM_POS_TABLE_ADD_POSITION_COUNTRY_OF_ORIGIN_LIST_OPEN_VALUES_XPATH)).
                waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        $(this.getBy(ADDENDUM_POS_TABLE_ADD_POSITION_COUNTRY_OF_ORIGIN_LIST_DESIRED_VALUE_XPATH)).
                waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        $(this.getBy(ADDENDUM_POS_TABLE_ADD_POSITION_COUNTRY_OF_ORIGIN_LIST_SELECTED_VALUE_XPATH)).
                waitUntil(clickable, timeout, polling).
                shouldHave(text("Российская Федерация"));
        this.logFilledFieldName("Страна происхождения товара", "Российская Федерация");
    }

    // endregion


    // region Раздел [Документы]

    /**
     * Прикрепляет файл в разделе [Документы] -> [Проект дополнительного соглашения].
     */
    public void attachFileToAddendumProject() throws Exception {
        SelenideElement contractProjectHeader = $(this.getBy(ADDENDUM_DOCS_CONTRACT_PROJECT_HEADER_XPATH));
        contractProjectHeader.waitUntil(exist, timeout, polling);
        this.scrollToCenterAndclickInElementJS(contractProjectHeader);
        System.out.printf("[ИНФОРМАЦИЯ]: прикрепляем файл '%s' в разделе [Документы] -> " +
                        "[Проект дополнительного соглашения].%n",
                config.getConfigParameter("FoundationDoc"));
        $(this.getBy(ADDENDUM_DOCS_ATTACH_FILE_TO_CONTRACT_PROJECT_XPATH)).waitUntil(exist, timeout, polling).
                sendKeys(config.getAbsolutePathToFoundationDoc());
        this.waitForPageLoad(5);
        $(this.getBy(ADDENDUM_DOCS_CONTRACT_PROJECT_ATTACHED_FILE_XPATH)).waitUntil(visible, timeout, polling).
                shouldHave(text(config.getConfigParameter("FoundationDoc")));
        System.out.println("[ИНФОРМАЦИЯ]: файл прикреплен успешно, ссылка для скачивания присутствует.");
    }


    /**
     * Прикрепляет файл в разделе [Документы] -> [Другие документы].
     */
    public void attachAnotherFileToAddendumProject() throws Exception {
        SelenideElement contractProjectHeader = $(this.getBy(ADDENDUM_DOCS_OTHER_DOCS_HEADER_XPATH));
        contractProjectHeader.waitUntil(exist, timeout, polling);
        this.scrollToCenterAndclickInElementJS(contractProjectHeader);
        System.out.printf("[ИНФОРМАЦИЯ]: прикрепляем файл '%s' в разделе [Документы] -> [Другие документы].%n",
                config.getConfigParameter("FoundationDoc"));
        $(this.getBy(ADDENDUM_DOCS_ATTACH_FILE_TO_OTHER_DOCS_XPATH)).waitUntil(exist, timeout, polling).
                sendKeys(config.getAbsolutePathToFoundationDoc());
        this.waitForPageLoad(5);
        $(this.getBy(ADDENDUM_DOCS_OTHER_DOCS_ATTACHED_FILE_XPATH)).waitUntil(visible, timeout, polling).
                shouldHave(text(config.getConfigParameter("FoundationDoc")));
        System.out.println("[ИНФОРМАЦИЯ]: файл прикреплен успешно, ссылка для скачивания присутствует.");
    }


    /**
     * Проверяет наличие на странице [Сведения о доп. соглашении] блока [Протокол разногласий] и
     * отсутствие ссылки на прикрепленный файл в этом блоке.
     */
    public void checkDisagreementsProtocolHeaderPresenceAndBlockIsEmpty() {
        this.scrollToCenter(this.getBy(ADDENDUM_DOCS_HEADER_XPATH));
        $(this.getBy(ADDENDUM_DOCS_DISAGREEMENTS_PROTOCOL_HEADER_XPATH)).shouldBe(visible);
        $(this.getBy(ADDENDUM_DOCS_DISAGREEMENTS_PROTOCOL_ATTACHED_FILE_XPATH)).shouldNotBe(visible);
    }


    /**
     * Проверяет наличие на странице [Сведения о доп. соглашении] блока
     * [Документы в обеспечение исполнения дополнительного соглашения и другие документы] и
     * его автозаполнение.
     */
    public void checkFinSupportAndOtherDocsHeaderPresenceAndAutocompletion() {
        this.scrollToCenter(this.getBy(ADDENDUM_DOCS_HEADER_XPATH));
        $(this.getBy(ADDENDUM_DOCS_FIN_SUPPORT_AND_OTHER_DOCS_HEADER_XPATH)).shouldBe(visible);
        $(this.getBy(ADDENDUM_DOCS_FIN_SUPPORT_AND_OTHER_DOCS_ATTACHED_FILE_XPATH)).shouldNotBe(visible);
    }

    // endregion


    // region Блок кнопок управления договором

    /**
     * Проверяет возможность выгрузить информацию о договоре.
     */
    public void checkPossibilityToDownloadInformationAboutAddendum() throws Exception {
        this.checkPossibilityToDownloadFileWithGeneratedName(this.getBy(DOWNLOAD_ADDENDUM_INFO_LINK_XPATH),
                "ссылке [Выгрузить информацию о дополнительном соглашениие]");
    }

    // endregion


    // region Общие методы для работы с элементами этой страницы

    /**
     * Переходит к просмотру раздела доп. соглашения с указанным именем.
     *
     * @param partitionName имя раздела
     */
    public void goToPartition(String partitionName) throws Exception {
        SelenideElement partitionHeader = $(this.getBy(blockNames.find(partitionName)));
        this.scrollToCenter(partitionHeader);
        partitionHeader.waitUntil(clickable, longtime, polling);
        this.clickInElementJS(partitionHeader);
        System.out.printf("[ИНФОРМАЦИЯ]: произведен переход к просмотру раздела: [%s].%n", partitionName);
        TimeUnit.SECONDS.sleep(3);
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
     * Проверяет значение поля ( в том числе и пустое ).
     *
     * @param fieldName имя поля
     * @param value     требуемое значение, которое должно содержаться в поле
     */
    public void verifyFieldContentOrEmptyField(String fieldName, String value) {
        String message = String.format("[ОШИБКА]: значение [%s] не содержится в поле [%s]", value, fieldName);
        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        SelenideElement field = $(this.getBy(fieldNames.find(fieldName)));
        field.waitUntil(exist, timeout, polling);
        if (value.trim().isEmpty()) Assert.assertTrue(message, field.getText().trim().isEmpty());
        else Assert.assertTrue(message, field.getText().contains(value));
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
     * Устанавливает или сбрасывает флажок с указанным именем.
     *
     * @param checkBoxName имя флажка
     */
    public void setCheckBoxValue(String checkBoxName) {
        boolean oldValue = this.getCheckBoxValue(checkBoxName);
        $(this.getBy(checkBoxNames.find(checkBoxName))).waitUntil(exist, timeout, polling).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено изменение состояния флажка: [%s].%n", checkBoxName);
        boolean newValue = this.getCheckBoxValue(checkBoxName);
        System.out.println("[ИНФОРМАЦИЯ]: флажок: [" + checkBoxName + "] находится в " +
                ((newValue) ? "" : "не ") + "отмеченном состоянии.");
        Assert.assertNotEquals(String.format(
                "[ОШИБКА]: состояние флажка [%s] не изменилось после щелчка мышью", checkBoxName), oldValue, newValue);
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
     * Проверяет, что в переключателе не выбрана опция с указанным именем.
     *
     * @param radioButtonName имя опции
     */
    public void verifyRadioButtonNotSelected(String radioButtonName) {
        System.out.print("[ИНФОРМАЦИЯ]: проверяем положение переключателя [" + radioButtonName + "] - ");
        $(this.getBy(radioButtonNames.find(radioButtonName))).waitUntil(exist, timeout, polling).shouldNotBe(selected);
        System.out.println("Ok, не выбран.");
    }

    /**
     * Проверяет отсутствие на странице [Сведения о доп. соглашении] раздела с указанным именем.
     *
     * @param blockName имя раздела
     */
    public void checkTheAbsenceOfBlockByBlockName(String blockName) {
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        System.out.printf("[ИНФОРМАЦИЯ]: [%s] проверка отсутствия раздела [%s] на странице [Сведения о доп. соглашении].%n",
                timer.getCurrentDateTime("dd.MM.yyyy HH:mm:ss"), blockName);
        $(this.getBy(blockNames.find(blockName))).shouldBe(hidden);
        System.out.printf("[ИНФОРМАЦИЯ]: [%s] раздел [%s] отсутствует на странице [Сведения о доп. соглашении].%n",
                timer.getCurrentDateTime("dd.MM.yyyy HH:mm:ss"), blockName);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Нажимает на кнопку с указанным именем.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        SelenideElement button = $(this.getBy(buttons.find(buttonName)));
        this.logButtonNameToPress(buttonName);
        button.waitUntil(exist, timeout, polling);
        this.scrollToCenterAndclickInElementJS(button);
        this.waitLoadingImage(3);
        this.logPressedButtonName(buttonName);
    }

    // endregion
}
