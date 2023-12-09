package ru.PageObjects.Customer.Contract;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonDialogs.ErrorDialog;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Класс описывающий страницу [Сведения о договоре] ( Главная / Заказчикам / Мои договоры / xxxx.xxxx )
 * ( .kontur.ru/customer/lk/Contracts/View/хххх ) или ( .kontur.ru/customer/lk/contracts/view/хххх ).
 * Created by Evgeniy Glushko on 01.04.2016.
 * Updated by Vladimir V. Klochkov on 20.04.2021.
 */
public class MyContractPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Раздел [Сведения о договоре]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о договоре]
    private static final String CONTR_GEN_INFO_HEADER_XPATH = "//h2[contains(.,'Сведения о договоре')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус договора]
    private static final String CONTR_GEN_INFO_STATUS_XPATH =
            "//td[contains(., 'Статус договора')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер карточки договора]
    private static final String CONTR_GEN_INFO_NUMBER_XPATH =
            "//td[contains(., 'Номер карточки договора')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер договора в ЕИС]
    private static final String CONTR_GEN_INFO_EIS_NUMBER_XPATH =
            "//td[contains(., 'Номер договора в ЕИС')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Сумма договора]
    private static final String CONTR_GEN_INFO_SUM_XPATH = "//td[contains(., 'Сумма договора')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [Изменить цену договора]
    private static final String CONTR_GEN_INFO_CHANGE_CONTR_PRICE_XPATH =
            "//a[contains(.,'Изменить цену договора')]";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Договором предусмотрено прекращение обязательств сторон по договору в связи с окончанием срока действия
    // договора]
    private static final String CONTR_GEN_INFO_IS_TERMINATION_ID = "IsTermination";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Договором предусмотрена возможность продления срока действия договора после его окончания]
    private static final String CONTR_GEN_INFO_IS_EXTENSION_ID = "IsExtension";
    //------------------------------------------------------------------------------------------------------------------
    // Переключатель [установить дату вручную]
    private static final String CONTR_GEN_INFO_SET_MANUAL_RADIO_BUTTON_ID = "IsExecutionDateStartFromSign2";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата начала исполнения договора]
    private static final String CONTR_GEN_INFO_DATE_START_PERFORMANCE_XPATH = "//input[@id='ExecutionDateStart']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Условия начала исполнения договора]
    private static final String CONTR_GEN_INFO_TERM_START_PERFORMANCE_XPATH = "//input[@id='ExecutionTermStart']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата окончания исполнения договора]
    private static final String CONTR_GEN_INFO_DATE_END_PERFORMANCE_XPATH = "//input[@id='ExecutionDateEnd']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Условия окончания исполнения договора]
    private static final String CONTR_GEN_INFO_TERM_END_PERFORMANCE_XPATH = "//input[@id='ExecutionTermEnd']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о заключении договора]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о заключении договора]
    private static final String CONTR_CONCL_INFO_HEADER_XPATH = "//h2[contains(.,'Сведения о заключении договора')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата публикации протокола подведения итогов]
    private static final String CONTR_CONCL_RESULTS_REVIEW_PUBLICATION_DATE_XPATH =
            "//td[contains(., 'Дата публикации протокола подведения итогов')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата создания карточки]
    private static final String CONTR_CONCL_CARD_CREATION_DATE_XPATH =
            "//td[contains(., 'Дата создания карточки')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Переключатель [Крайний срок подписания проекта договора участником закупки]
    private static final String CONTR_CONCL_SIGN_DATE_START_FROM_SEND_TO_SUPPLIER_XPATH =
            "//input[@id='IsProviderSignDateStartFromSendToSupplier1']";
    //------------------------------------------------------------------------------------------------------------------
    // Переключатель [Крайний срок подписания проекта договора участником закупки]
    private static final String CONTR_CONCL_SIGN_DATE_MANUAL_SET_XPATH =
            "//input[@id='IsProviderSignDateStartFromSendToSupplier2']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Крайний срок подписания проекта договора участником закупки]
    private static final String CONTR_CONCL_SIGNED_BY_SUPPLIER_END_DATE_XPATH =
            "//td[contains(., 'Крайний срок подписания проекта договора участником закупки')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата направления договора участнику]
    private static final String CONTR_CONCL_SENT_TO_SUPPLIER_DATE_XPATH =
            "//td[contains(., 'Дата направления договора участнику')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата подписания договора участником]
    private static final String CONTR_CONCL_SIGNED_BY_SUPPLIER_DATE_XPATH =
            "//td[contains(., 'Дата подписания договора участником')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата подписания договора заказчиком]
    private static final String CONTR_CONCL_SIGNED_BY_CUSTOMER_XPATH =
            "//td[contains(., 'Дата подписания договора заказчиком')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о закупке и предмете договора]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о закупке и предмете договора]
    private static final String CONTR_DET_INFO_HEADER_XPATH =
            "//h2[contains(.,'Сведения о закупке и предмете договора')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование предмета договора]
    private static final String CONTR_DET_SUBJECT_NAME_XPATH =
            "//td[contains(., 'Наименование предмета договора')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Таблица [Позиции договора] для коммерческих закупок

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок подраздела [Позиции договора]
    private static final String CONTR_POS_TABLE_COMM_HEADER_XPATH = "//h2[contains(.,'Позиции договора')]";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовки столбцов таблицы
    private static final String CONTR_POS_TABLE_COMM_COLS_HDS_XPATH = "//*[@id='PositionsGrid']//thead/tr/th";
    //------------------------------------------------------------------------------------------------------------------
    // Шаблон локатора любого столбца таблицы
    private static final String CONTR_POS_TABLE_COMM_ALL_COLS_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[%d]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Наименование товара, работ, услуг]
    private static final String CONTR_POS_TABLE_COMM_NAME_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Код ОКПД2]
    private static final String CONTR_POS_TABLE_COMM_OKPD2_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Код ОКВЭД2]
    private static final String CONTR_POS_TABLE_COMM_OKVED2_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[3]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Страна происхождения]
    private static final String CONTR_POS_TABLE_COMM_COUNTRY_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[4]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Единица измерения]
    private static final String CONTR_POS_TABLE_COMM_UNIT_MEASUREMENT_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[5]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Количество]
    private static final String CONTR_POS_TABLE_COMM_QUANTITY_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[6]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Цена за ед.изм.]
    private static final String CONTR_POS_TABLE_COMM_UNIT_PRICE_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[7]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Стоимость]
    private static final String CONTR_POS_TABLE_COMM_COST_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[8]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дополнительные сведения]
    private static final String CONTR_POS_TABLE_COMM_ADDITIONAL_INFORMATION_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[9]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Тип финансового обеспечения]
    private static final String CONTR_POS_TABLE_COMM_TYPE_OF_FINANCIAL_SUPPORT_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[10]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Реестровый номер контракта 44-ФЗ]
    private static final String CONTR_POS_TABLE_COMM_REGISTRY_CONTRACT_NUMBER_44_FZ_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[11]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дата окончания этапа контракта 44-ФЗ]
    private static final String CONTR_POS_TABLE_COMM_END_DATE_OF_THE_CONTRACT_PHASE_44_FZ_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[12]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Код целевой статьи расходов]
    private static final String CONTR_POS_TABLE_COMM_EXPENSE_TARGET_CODE_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[13]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Код вида расходов]
    private static final String CONTR_POS_TABLE_COMM_COST_TYPE_CODE_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[14]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Объем финансового обеспечения (руб.)]
    private static final String CONTR_POS_TABLE_COMM_AMOUNT_OF_FINANCIAL_SECURITY_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[15]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Таблица [Позиции договора] для конкурентных закупок

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок подраздела [Позиции договора]
    private static final String CONTR_POS_TABLE_COMP_HEADER_XPATH = "//h2[contains(.,'Позиции договора')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Наименование товара, работ, услуг]
    private static final String CONTR_POS_TABLE_COMP_NAME_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Код ОКПД2]
    private static final String CONTR_POS_TABLE_COMP_OKPD2_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[3]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Код ОКВЭД2]
    private static final String CONTR_POS_TABLE_COMP_OKVED2_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[4]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Тип объекта закупки]
    private static final String CONTR_POS_TABLE_COMP_OBJECT_TYPE_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[5]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Страна происхождения]
    private static final String CONTR_POS_TABLE_COMP_COUNTRY_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[6]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Единица измерения]
    private static final String CONTR_POS_TABLE_COMP_UNIT_MEASUREMENT_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[7]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Количество]
    private static final String CONTR_POS_TABLE_COMP_QUANTITY_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[8]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Цена за ед.изм.]
    private static final String CONTR_POS_TABLE_COMP_UNIT_PRICE_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[9]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Стоимость]
    private static final String CONTR_POS_TABLE_COMP_COST_XPATH = "//*[@id='PositionsGrid']//table/tbody/tr/td[10]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дополнительные сведения]
    private static final String CONTR_POS_TABLE_COMP_ADDITIONAL_INFORMATION_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[11]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Тип финансового обеспечения]
    private static final String CONTR_POS_TABLE_COMP_TYPE_OF_FINANCIAL_SUPPORT_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[12]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Реестровый номер контракта 44-ФЗ]
    private static final String CONTR_POS_TABLE_COMP_REGISTRY_CONTRACT_NUMBER_44_FZ_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[13]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дата окончания этапа контракта 44-ФЗ]
    private static final String CONTR_POS_TABLE_COMP_END_DATE_OF_THE_CONTRACT_PHASE_44_FZ_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[14]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Код целевой статьи расходов]
    private static final String CONTR_POS_TABLE_COMP_EXPENSE_TARGET_CODE_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[15]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Код вида расходов]
    private static final String CONTR_POS_TABLE_COMP_COST_TYPE_CODE_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[16]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Объем финансового обеспечения (руб.)]
    private static final String CONTR_POS_TABLE_COMP_AMOUNT_OF_FINANCIAL_SECURITY_XPATH =
            "//*[@id='PositionsGrid']//table/tbody/tr/td[17]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Экспорт объектов в Excel]
    private static final String CONTR_POS_TABLE_EXPORT_TO_EXCEL_BUTTON_XPATH =
            "//button[contains(.,'Экспорт объектов в Excel')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Импорт объектов из Excel] ID
    private static final String CONTR_POS_TABLE_IMPORT_FROM_EXCEL_ID = "UploadFile_Positions";
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
    // Диалоговое окно [Сведения о поставщике]
    private static final String DET_OF_THE_PROC_PART_APPLICATION_PARTICIPANT_INFORMATION_POPUP_ID =
            "ParticipantPopup_wnd_title";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Закрыть] в диалоговом окне [Сведения о поставщике]
    private static final String DET_OF_THE_PROC_PART_APPLICATION_PARTICIPANT_INFORMATION_POPUP_CLOSE_BUTTON_ID =
            "CloseOrganizationPopup";
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

    // region Раздел [Документы]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Документы]
    private static final String CONTR_DOCS_HEADER_XPATH = "//h2[contains(.,'Документы')]";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок подраздела [Проект договора]
    private static final String CONTR_DOCS_CONTRACT_PROJECT_HEADER_XPATH = "//h3[contains(.,'Проект договора')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [Прикрепить файл] в подразделе [Проект договора]
    private static final String CONTR_DOCS_ATTACH_FILE_TO_CONTRACT_PROJECT_XPATH =
            "//input[@id='ContractProjectUpload']";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка на прикрепленный файл в подразделе [Проект договора]
    private static final String CONTR_DOCS_CONTRACT_PROJECT_ATTACHED_FILE_XPATH =
            "//*[@id='ContractProjectGrid']/tbody/tr/td/a[contains(@href, 'https://')]";
    //------------------------------------------------------------------------------------------------------------------
    // Дата прикрепленного файла [Проект договора]
    private static final String CONTR_DOCS_CONTRACT_PROJECT_ATTACHED_FILE_DATE_XPATH =
            "//*[@id='ContractProjectGrid']/tbody/tr/td[contains(., 'Прикреплено')]";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок подраздела [Протокол разногласий]
    private static final String CONTR_DOCS_DISAGREEMENTS_PROTOCOL_HEADER_XPATH =
            "//h3[contains(.,'Протокол разногласий')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка на прикрепленный файл в подразделе [Протокол разногласий]
    private static final String CONTR_DOCS_DISAGREEMENTS_PROTOCOL_ATTACHED_FILE_XPATH =
            "//*[@id='IssueProtocolsGrid']/tbody/tr/td/a[contains(@href, 'https://')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения об исполнении договора]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения об исполнении договора]
    private static final String CONTR_EXEC_HEADER_XPATH = "//h2[contains(.,'Сведения об исполнении договора')]";
    //------------------------------------------------------------------------------------------------------------------

    // region Таблица [Сведения об исполнении договора]

    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дата создания]
    private static final String CONTR_EXEC_GRID_CONTRACT_EXECUTION_CREATE_DATE_COLUMN_XPATH =
            "//div[@id='ExecutionsGrid']//tbody/tr/td[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дата публикации]
    private static final String CONTR_EXEC_GRID_CONTRACT_EXECUTION_PUBLICATION_DATE_COLUMN_XPATH =
            "//div[@id='ExecutionsGrid']//tbody/tr/td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дата окончания исполнения договора (отдельного этапа исполнения договора)]
    private static final String CONTR_EXEC_GRID_COMPLETION_STAGE_END_DATE_COLUMN_XPATH =
            "//div[@id='ExecutionsGrid']//tbody/tr/td[3]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Статус]
    private static final String CONTR_EXEC_GRID_STATUS_COLUMN_XPATH = "//div[@id='ExecutionsGrid']//tbody/tr/td[4]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Действия]
    private static final String CONTR_EXEC_GRID_ACTIONS_COLUMN_XPATH = "//div[@id='ExecutionsGrid']//tbody/tr/td[5]/a";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // endregion

    // region Раздел [Сведения о расторжении договора]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о расторжении договора]
    private static final String CONTR_TERM_HEADER_XPATH =
            "//h2[contains(.,'Сведения о расторжении договора')]";
    //------------------------------------------------------------------------------------------------------------------

    // region Таблица [Сведения о расторжении договора]

    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дата создания]
    private static final String CONTR_TERM_GRID_CONTRACT_TERMINATION_CREATE_DATE_COLUMN_XPATH =
            "//div[@id='TerminationsGrid']//tbody/tr/td[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дата размещения сведений]
    private static final String CONTR_TERM_GRID_CONTRACT_TERMINATION_INFO_PUBLICATION_DATE_COLUMN_XPATH =
            "//div[@id='TerminationsGrid']//tbody/tr/td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Статус]
    private static final String CONTR_TERM_GRID_STATUS_COLUMN_XPATH = "//div[@id='TerminationsGrid']//tbody/tr/td[3]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Действия]
    private static final String CONTR_TERM_GRID_ACTIONS_COLUMN_XPATH =
            "//div[@id='TerminationsGrid']//tbody/tr/td[4]/a";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

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
    // Кнопка [Создать доп. соглашение]
    private static final String CREATE_CONTRACT_ADDENDUM_BUTTON_XPATH = "//a[contains(.,'Создать доп. соглашение')]";
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
    private static final String DOWNLOAD_CONTRACT_INFO_LINK_XPATH = "//a[contains(., 'Выгрузить информацию')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [История]
    private static final String HISTORY_BUTTON_ID = "HistoryButton";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Таблица [Дополнительные соглашения]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок [Дополнительные соглашения]
    private static final String CONTRACT_ADDENDUM_TABLE_HEADER_XPATH = "//h2[contains(.,'Дополнительные соглашения')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Дата создания]
    private static final String CONTRACT_ADDENDUM_TABLE_CREATION_DATE_XPATH =
            "//*[@id='AdditionalAgreementsGrid']//table/tbody/tr/td[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Номер доп. соглашения]
    private static final String CONTRACT_ADDENDUM_TABLE_ADDENDUM_NUMBER_XPATH =
            "//*[@id='AdditionalAgreementsGrid']//table/tbody/tr/td[2]/a";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Статус]
    private static final String CONTRACT_ADDENDUM_TABLE_STATUS_XPATH =
            "//*[@id='AdditionalAgreementsGrid']//table/tbody/tr/td[3]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Действие]
    private static final String CONTRACT_ADDENDUM_TABLE_ACTION_XPATH =
            "//*[@id='AdditionalAgreementsGrid']//table/tbody/tr/td[4]/a";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Диалоговое окно [История]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок диалогового окна [История]
    private static final String CONTR_HIST_WINDOW_TITLE_ID = "ContractsHistoryWindow_wnd_title";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Экспорт в Excel] в окне [История]
    private static final String CONTR_HIST_EXPORT_CONTRACTS_HISTORY_TO_EXCEL_BUTTON_ID = "HistoryToExcel";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Закрыть]/[X] в окне [История]
    private static final String CONTR_HIST_CLOSE_BUTTON_XPATH =
            "//span[@id='ContractsHistoryWindow_wnd_title']/following-sibling::div[1]/a/span";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Диалоговое окно [Изменение цены договора]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок диалогового окна [Изменение цены договора]
    private static final String CH_CONTR_PRICE_WINDOW_TITLE_ID = "ChangeContractPriceWindow_wnd_title";
    //------------------------------------------------------------------------------------------------------------------
    // Список начальный [Цена договора] в диалоговом окне [Изменение цены договора]
    private static final String CH_CONTR_PRICE_NEW_CONTRACT_PRICE_XPATH =
            "//input[contains(@class,'k-formatted-value width300 height36px k-input')]";
    //------------------------------------------------------------------------------------------------------------------
    // Список редактируемый [Цена договора] в диалоговом окне [Изменение цены договора]
    private static final String CH_CONTR_PRICE_NEW_CONTRACT_PRICE_EDITED_XPATH =
            "//input[@class='width300 height36px k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Переключатель [Причина - Изменение цены договора в результате предоставления преференций]
    // в диалоговом окне [Изменение цены договора]
    private static final String CH_CONTR_PRICE_NEW_CONTRACT_PRICE_REASON_PREFERENCES_ID = "IncreaseType6";
    //------------------------------------------------------------------------------------------------------------------
    // Переключатель [Причина - Изменение цены договора по решению заказчика или соглашению сторон]
    // в диалоговом окне [Изменение цены договора]
    private static final String CH_CONTR_PRICE_NEW_CONTRACT_PRICE_REASON_AGREEMENT_ID = "IncreaseType7";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Сохранить] в диалоговом окне [Изменение цены договора]
    private static final String CH_CONTR_PRICE_CHANGE_PRICE_SAVE_BUTTON_ID = "SaveChangePriceButton";
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
    private final Dictionary execPositions = new Dictionary();      // таблица в р. [Сведения об исполнении договора]
    private final Dictionary termPositions = new Dictionary();      // таблица в р. [Сведения о расторжении договора]

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public MyContractPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Сведения о договоре", CONTR_GEN_INFO_HEADER_XPATH);
        blockNames.add("Сведения о заключении договора", CONTR_CONCL_INFO_HEADER_XPATH);
        blockNames.add("Проект договора", CONTR_DOCS_CONTRACT_PROJECT_HEADER_XPATH);
        blockNames.add("Позиции договора комм.", CONTR_POS_TABLE_COMM_HEADER_XPATH);
        blockNames.add("Позиции договора конк.", CONTR_POS_TABLE_COMP_HEADER_XPATH);
        blockNames.add("Сведения о закупке и предмете договора", CONTR_DET_INFO_HEADER_XPATH);
        blockNames.add("Сведения о заказчике", DET_OF_THE_PROC_CUST_HEADER_XPATH);
        blockNames.add("Сведения об участнике закупки", DET_OF_THE_PROC_PART_HEADER_XPATH);
        blockNames.add("Документы", CONTR_DOCS_HEADER_XPATH);
        blockNames.add("Многосторонее подписание", MULTI_SIGN_TABLE_XPATH);
        blockNames.add("Сведения об исполнении договора", CONTR_EXEC_HEADER_XPATH);
        blockNames.add("Сведения о расторжении договора", CONTR_TERM_HEADER_XPATH);
        blockNames.add("История", CONTR_HIST_WINDOW_TITLE_ID);
        blockNames.add("Изменение цены договора", CH_CONTR_PRICE_WINDOW_TITLE_ID);
        blockNames.add("Дополнительные соглашения", CONTRACT_ADDENDUM_TABLE_HEADER_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Статус договора", CONTR_GEN_INFO_STATUS_XPATH);
        fieldNames.add("Номер карточки договора", CONTR_GEN_INFO_NUMBER_XPATH);
        fieldNames.add("Номер договора в ЕИС", CONTR_GEN_INFO_EIS_NUMBER_XPATH);
        fieldNames.add("Сумма договора", CONTR_GEN_INFO_SUM_XPATH);
        fieldNames.add("Дата начала исполнения договора", CONTR_GEN_INFO_DATE_START_PERFORMANCE_XPATH);
        fieldNames.add("Условия начала исполнения договора", CONTR_GEN_INFO_TERM_START_PERFORMANCE_XPATH);
        fieldNames.add("Дата окончания исполнения договора", CONTR_GEN_INFO_DATE_END_PERFORMANCE_XPATH);
        fieldNames.add("Условия окончания исполнения договора", CONTR_GEN_INFO_TERM_END_PERFORMANCE_XPATH);
        fieldNames.add("Крайний срок подписания проекта договора участником закупки",
                CONTR_CONCL_SIGNED_BY_SUPPLIER_END_DATE_XPATH);
        fieldNames.add("Дата направления договора участнику", CONTR_CONCL_SENT_TO_SUPPLIER_DATE_XPATH);
        fieldNames.add("Дата подписания договора участником", CONTR_CONCL_SIGNED_BY_SUPPLIER_DATE_XPATH);
        fieldNames.add("Дата подписания договора заказчиком", CONTR_CONCL_SIGNED_BY_CUSTOMER_XPATH);
        fieldNames.add("Дата публикации протокола подведения итогов",
                CONTR_CONCL_RESULTS_REVIEW_PUBLICATION_DATE_XPATH);
        fieldNames.add("Дата создания карточки", CONTR_CONCL_CARD_CREATION_DATE_XPATH);
        fieldNames.add("Наименование предмета договора", CONTR_DET_SUBJECT_NAME_XPATH);
        fieldNames.add("Номер заявки", DET_OF_THE_PROC_PART_APPLICATION_NUMBER_XPATH);
        fieldNames.add("Дата создания доп. соглашения", CONTRACT_ADDENDUM_TABLE_CREATION_DATE_XPATH);
        fieldNames.add("Статус доп. соглашения", CONTRACT_ADDENDUM_TABLE_STATUS_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        buttons.add("Изменить цену договора", CONTR_GEN_INFO_CHANGE_CONTR_PRICE_XPATH);
        buttons.add("Создать сведения об исполнении", CREATE_EXECUTION_DETAILS_BUTTON_XPATH);
        buttons.add("Создать сведения о расторжении", CREATE_TERMINATION_DETAILS_BUTTON_XPATH);
        buttons.add("Создать доп. соглашение", CREATE_CONTRACT_ADDENDUM_BUTTON_XPATH);
        buttons.add("Направить договор", SEND_CONTRACT_BUTTON_XPATH);
        buttons.add("Внести изменения", MAKE_CHANGES_BUTTON_ID);
        buttons.add("Экспорт объектов в Excel", CONTR_POS_TABLE_EXPORT_TO_EXCEL_BUTTON_XPATH);
        buttons.add("Импорт объектов из Excel", CONTR_POS_TABLE_IMPORT_FROM_EXCEL_ID);
        buttons.add("Сведения о заказчике", DET_OF_THE_PROC_CUST_XPATH);
        buttons.add("Сведения об участнике закупки", DET_OF_THE_PROC_PART_APPLICATION_PARTICIPANT_ID);
        buttons.add("Закрыть сведения о поставщике",
                DET_OF_THE_PROC_PART_APPLICATION_PARTICIPANT_INFORMATION_POPUP_CLOSE_BUTTON_ID);
        buttons.add("Заявка на участие", DET_OF_THE_PROC_PART_APPLICATION_LOT_TRADE_FORM_XPATH);
        buttons.add("Аккредитационные документы", DET_OF_THE_PROC_PART_APPLICATION_LOT_PARTICIPANT_FORM_XPATH);
        buttons.add("Наименование подписанта", MULTI_SIGN_TABLE_CUSTOMER_XPATH);
        buttons.add("Сохранить договор", SAVE_CONTRACT_BUTTON_XPATH);
        buttons.add("Назначить ответственного для подписания", ASSIGN_RESPONDER_TO_DEAL_BUTTON_ID);
        buttons.add("Переназначить ответственного для подписания", REASSIGN_RESPONDER_TO_DEAL_BUTTON_XPATH);
        buttons.add("Подписать договор", SIGN_CONTRACT_BUTTON_XPATH);
        buttons.add("Протокол отказа от заключения договора", REFUSE_CONTRACT_BUTTON_ID);
        buttons.add("Продолжить с договором", CONTINUE_BUTTON_XPATH);
        buttons.add("История", HISTORY_BUTTON_ID);
        buttons.add("Номер доп. соглашения", CONTRACT_ADDENDUM_TABLE_ADDENDUM_NUMBER_XPATH);
        buttons.add("Дейстиве доп. соглашения", CONTRACT_ADDENDUM_TABLE_ACTION_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        radioButtonNames.add("c даты получения проекта договора поставщиком",
                CONTR_CONCL_SIGN_DATE_START_FROM_SEND_TO_SUPPLIER_XPATH);
        radioButtonNames.add("установить дату вручную", CONTR_CONCL_SIGN_DATE_MANUAL_SET_XPATH);
        radioButtonNames.add("Причина - Изменение цены договора в результате предоставления преференций",
                CH_CONTR_PRICE_NEW_CONTRACT_PRICE_REASON_PREFERENCES_ID);
        radioButtonNames.add("Причина - Изменение цены договора по решению заказчика или соглашению сторон",
                CH_CONTR_PRICE_NEW_CONTRACT_PRICE_REASON_AGREEMENT_ID);
        //--------------------------------------------------------------------------------------------------------------
        checkBoxNames.add("Прекращение обязательств сторон", CONTR_GEN_INFO_IS_TERMINATION_ID);
        checkBoxNames.add("Возможность продления срока действия договора", CONTR_GEN_INFO_IS_EXTENSION_ID);
        checkBoxNames.add("Финальный подписант", MULTI_SIGN_TABLE_FINAL_SIGNATORY_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        contrPositionsComm.add("Заголовки столбцов таблицы", CONTR_POS_TABLE_COMM_COLS_HDS_XPATH);
        contrPositionsComm.add("Шаблон локатора любого столбца таблицы", CONTR_POS_TABLE_COMM_ALL_COLS_XPATH);
        contrPositionsComm.add("Наименование товара, работ, услуг", CONTR_POS_TABLE_COMM_NAME_XPATH);
        contrPositionsComm.add("Код ОКПД2", CONTR_POS_TABLE_COMM_OKPD2_XPATH);
        contrPositionsComm.add("Код ОКВЭД2", CONTR_POS_TABLE_COMM_OKVED2_XPATH);
        contrPositionsComm.add("Страна происхождения", CONTR_POS_TABLE_COMM_COUNTRY_XPATH);
        contrPositionsComm.add("Единица измерения", CONTR_POS_TABLE_COMM_UNIT_MEASUREMENT_XPATH);
        contrPositionsComm.add("Количество", CONTR_POS_TABLE_COMM_QUANTITY_XPATH);
        contrPositionsComm.add("Цена за ед.изм.", CONTR_POS_TABLE_COMM_UNIT_PRICE_XPATH);
        contrPositionsComm.add("Стоимость", CONTR_POS_TABLE_COMM_COST_XPATH);
        contrPositionsComm.add("Дополнительные сведения", CONTR_POS_TABLE_COMM_ADDITIONAL_INFORMATION_XPATH);
        contrPositionsComm.add("Тип финансового обеспечения", CONTR_POS_TABLE_COMM_TYPE_OF_FINANCIAL_SUPPORT_XPATH);
        contrPositionsComm.add("Реестровый номер контракта 44-ФЗ",
                CONTR_POS_TABLE_COMM_REGISTRY_CONTRACT_NUMBER_44_FZ_XPATH);
        contrPositionsComm.add("Дата окончания этапа контракта 44-ФЗ",
                CONTR_POS_TABLE_COMM_END_DATE_OF_THE_CONTRACT_PHASE_44_FZ_XPATH);
        contrPositionsComm.add("Код целевой статьи расходов", CONTR_POS_TABLE_COMM_EXPENSE_TARGET_CODE_XPATH);
        contrPositionsComm.add("Код вида расходов", CONTR_POS_TABLE_COMM_COST_TYPE_CODE_XPATH);
        contrPositionsComm.add("Объем финансового обеспечения (руб.)",
                CONTR_POS_TABLE_COMM_AMOUNT_OF_FINANCIAL_SECURITY_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        contrPositionsComp.add("Заголовки столбцов таблицы", CONTR_POS_TABLE_COMM_COLS_HDS_XPATH);
        contrPositionsComp.add("Шаблон локатора любого столбца таблицы", CONTR_POS_TABLE_COMM_ALL_COLS_XPATH);
        contrPositionsComp.add("Наименование товара, работ, услуг", CONTR_POS_TABLE_COMP_NAME_XPATH);
        contrPositionsComp.add("Код ОКПД2", CONTR_POS_TABLE_COMP_OKPD2_XPATH);
        contrPositionsComp.add("Код ОКВЭД2", CONTR_POS_TABLE_COMP_OKVED2_XPATH);
        contrPositionsComp.add("Тип объекта закупки", CONTR_POS_TABLE_COMP_OBJECT_TYPE_XPATH);
        contrPositionsComp.add("Страна происхождения", CONTR_POS_TABLE_COMP_COUNTRY_XPATH);
        contrPositionsComp.add("Единица измерения", CONTR_POS_TABLE_COMP_UNIT_MEASUREMENT_XPATH);
        contrPositionsComp.add("Количество", CONTR_POS_TABLE_COMP_QUANTITY_XPATH);
        contrPositionsComp.add("Цена за ед.изм.", CONTR_POS_TABLE_COMP_UNIT_PRICE_XPATH);
        contrPositionsComp.add("Стоимость", CONTR_POS_TABLE_COMP_COST_XPATH);
        contrPositionsComp.add("Дополнительные сведения", CONTR_POS_TABLE_COMP_ADDITIONAL_INFORMATION_XPATH);
        contrPositionsComp.add("Тип финансового обеспечения", CONTR_POS_TABLE_COMP_TYPE_OF_FINANCIAL_SUPPORT_XPATH);
        contrPositionsComp.add("Реестровый номер контракта 44-ФЗ",
                CONTR_POS_TABLE_COMP_REGISTRY_CONTRACT_NUMBER_44_FZ_XPATH);
        contrPositionsComp.add("Дата окончания этапа контракта 44-ФЗ",
                CONTR_POS_TABLE_COMP_END_DATE_OF_THE_CONTRACT_PHASE_44_FZ_XPATH);
        contrPositionsComp.add("Код целевой статьи расходов", CONTR_POS_TABLE_COMP_EXPENSE_TARGET_CODE_XPATH);
        contrPositionsComp.add("Код вида расходов", CONTR_POS_TABLE_COMP_COST_TYPE_CODE_XPATH);
        contrPositionsComp.add("Объем финансового обеспечения (руб.)",
                CONTR_POS_TABLE_COMP_AMOUNT_OF_FINANCIAL_SECURITY_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        execPositions.add("Дата создания", CONTR_EXEC_GRID_CONTRACT_EXECUTION_CREATE_DATE_COLUMN_XPATH);
        execPositions.add("Дата публикации", CONTR_EXEC_GRID_CONTRACT_EXECUTION_PUBLICATION_DATE_COLUMN_XPATH);
        execPositions.add("Дата окончания исполнения договора", CONTR_EXEC_GRID_COMPLETION_STAGE_END_DATE_COLUMN_XPATH);
        execPositions.add("Статус", CONTR_EXEC_GRID_STATUS_COLUMN_XPATH);
        execPositions.add("Действия", CONTR_EXEC_GRID_ACTIONS_COLUMN_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        termPositions.add("Дата создания", CONTR_TERM_GRID_CONTRACT_TERMINATION_CREATE_DATE_COLUMN_XPATH);
        termPositions.add("Дата размещения сведений",
                CONTR_TERM_GRID_CONTRACT_TERMINATION_INFO_PUBLICATION_DATE_COLUMN_XPATH);
        termPositions.add("Статус", CONTR_TERM_GRID_STATUS_COLUMN_XPATH);
        termPositions.add("Действия", CONTR_TERM_GRID_ACTIONS_COLUMN_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Раздел [Сведения о договоре]

    /**
     * Проверяет статус договора [Сведения о договоре] -> [Статус договора].
     *
     * @param expStatus        ожидаемый статус договора
     * @param numberOfAttempts количество попыток
     */
    public void checkContractStatus(String expStatus, int numberOfAttempts) throws Exception {
        long timeout = 10;
        long elapsed = 0;
        String pref = ">>> (checkContractStatus) ";
        $(this.getBy(fieldNames.find("Статус договора"))).waitUntil(visible, this.timeout);
        String actualStatus = $(this.getBy(fieldNames.find("Статус договора"))).getText();
        for (int i = 1; i <= numberOfAttempts; i++) {
            System.out.println(pref + "Проверка статуса договора №: [" + i + "].");
            if (actualStatus.equals(expStatus)) break;
            else {
                getWebDriver().navigate().refresh();
                TimeUnit.SECONDS.sleep(timeout);
                elapsed = elapsed + timeout;
                this.waitLoadingImage();
                $(this.getBy(fieldNames.find("Статус договора"))).waitUntil(visible, this.timeout);
                actualStatus = $(this.getBy(fieldNames.find("Статус договора"))).getText();
            }
        }
        System.out.println(pref + "Ожидание смены статуса договора составило: [" + elapsed + "] секунд.");
        Assert.assertEquals(pref + "Некорректный статус договора: ", expStatus, actualStatus);
        System.out.println(pref + "Произведена проверка статуса договора: [" + expStatus + "].");
    }

    /**
     * Заполняет поле [Цена договора] требуемым значением.
     *
     * @param newContractPrice требуемое значение поля
     */
    public void setNewContractPrice(String newContractPrice) {

        // Нажимаем на поле, чтобы оно перешло в состояния редактирования
        System.out.println("[ИНФОРМАЦИЯ]: произведим нажатие на поле [Цена договора].");
        $(this.getBy(CH_CONTR_PRICE_NEW_CONTRACT_PRICE_XPATH)).waitUntil(clickable, timeout, polling).click();

        // Очищаем поле и передаем новую цену
        $(this.getBy(CH_CONTR_PRICE_NEW_CONTRACT_PRICE_EDITED_XPATH)).waitUntil(clickable, timeout, polling).clear();
        $(this.getBy(CH_CONTR_PRICE_NEW_CONTRACT_PRICE_EDITED_XPATH)).sendKeys(newContractPrice);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнения поля: [Цена договора] значением [%s].%n",
                newContractPrice);
    }

    /**
     * Устанавливает переключатель [Причина] в положение
     * [Изменение цены договора по решению заказчика или соглашению сторон].
     */
    public void setReasonForNewContractPrice() throws Exception {
        $(this.getBy(CH_CONTR_PRICE_NEW_CONTRACT_PRICE_REASON_AGREEMENT_ID)).click();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("[ИНФОРМАЦИЯ]: Переключатель [Причина] установлен в положение " +
                "[Изменение цены договора по решению заказчика или соглашению сторон].");
    }

    /**
     * Нажимает на кнопку [Сохранить] в диалоговом окне [Изменение цены договора].
     */
    public void clickSaveNewContractPrice() throws Exception {
        System.out.println("[ИНФОРМАЦИЯ]: Нажимаем на кнопку 'Сохранить' в диалоговом окне [Изменение цены договора].");
        $(this.getBy(CH_CONTR_PRICE_CHANGE_PRICE_SAVE_BUTTON_ID)).click();
        waitForPageLoad(30);
    }

    /**
     * Заполняет поле [Дата начала исполнения договора].
     *
     * @param startPerformanceDate дата начала исполнения договора
     */
    public void setStartPerformanceDate(String startPerformanceDate) throws Exception {

        // TODO Убрать условный оператор когда все формы договора будут иметь этот переключатель
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        if ($(this.getBy(CONTR_GEN_INFO_SET_MANUAL_RADIO_BUTTON_ID)).isDisplayed())
            $(this.getBy(CONTR_GEN_INFO_SET_MANUAL_RADIO_BUTTON_ID)).click();
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        $(this.getBy(CONTR_GEN_INFO_DATE_START_PERFORMANCE_XPATH)).waitUntil(exist, timeout, polling).clear();
        $(this.getBy(CONTR_GEN_INFO_DATE_START_PERFORMANCE_XPATH)).sendKeys(startPerformanceDate);
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
        $(this.getBy(CONTR_GEN_INFO_DATE_END_PERFORMANCE_XPATH)).waitUntil(exist, timeout, polling).clear();
        $(this.getBy(CONTR_GEN_INFO_DATE_END_PERFORMANCE_XPATH)).sendKeys(endPerformanceDate);
        TimeUnit.SECONDS.sleep(1);
        System.out.printf(
                "[ИНФОРМАЦИЯ]: произведено заполнения поля [Дата окончания исполнения договора] значением [%s].%n",
                endPerformanceDate);
    }

    // endregion

    // region Раздел [Позиции договора]

    /**
     * Проверяет количество строк в таблице [Позиции договора].
     *
     * @param rows ожидаемое количество строк в таблице
     */
    public void checkNumberOfRowsInContractPositionsTable(String rows) {
        int expectedSize = Integer.parseInt(rows);
        ElementsCollection column = this.getColumnElementsCollectionByTextInTableHeader(
                contrPositionsComm.find("Шаблон локатора любого столбца таблицы"),
                contrPositionsComm.find("Заголовки столбцов таблицы"),
                "Наименование товара, работ, услуг", true);
        this.checkElementsCollectionSize(column, expectedSize);
    }

    /**
     * Проверяет текст ячейки в таблице [Позиции договора] коммерческой для столбца с указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     */
    public void verifyCellByNameInLineByNumberFromContractPasitionsTableCommercialForText(
            String columnName, String rowNumber, String cellValue) {
        this.verifyCellInTableByColumnElementsAndLineNumberForText("Позиции договора",
                columnName, $$(this.getBy(contrPositionsComm.find(columnName))), rowNumber, cellValue);
    }

    /**
     * Проверяет текст ячейки в таблице [Позиции договора] конкурентной для столбца с указанным именем и номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     */
    public void verifyCellByNameInLineByNumberFromContractPositionsTableCompetitiveForText(
            String columnName, String rowNumber, String cellValue) {
        ElementsCollection column = this.getColumnElementsCollectionByTextInTableHeader(
                contrPositionsComp.find("Шаблон локатора любого столбца таблицы"),
                contrPositionsComp.find("Заголовки столбцов таблицы"), columnName, true);
        this.verifyCellInTableByColumnElementsAndLineNumberForText("Позиции договора",
                columnName, column, rowNumber, cellValue);
    }

    /**
     * Проверяет возможность экспортировать таблицу [Позиции договора] для конкурентных закупок в виде Excel-файла.
     */
    public void checkContractPositionsTableForExportToExcel() throws Exception {
        this.checkPossibilityToDownloadFileWithGeneratedName(
                this.getBy(CONTR_POS_TABLE_EXPORT_TO_EXCEL_BUTTON_XPATH), "Экспорт объектов в Excel");
    }

    // endregion

    // region Раздел [Сведения об участнике закупки]

    /**
     * Проверяет содержимое поля [Номер заявки] на текст.
     *
     * @param text ожидаемый текст в поле
     */
    public void checkContractApplicationNumberFieldForText(String text) {
        $(this.getBy(DET_OF_THE_PROC_PART_APPLICATION_NUMBER_XPATH)).waitUntil(visible, timeout, polling).
                shouldHave(text(text));
        System.out.printf("[ИНФОРМАЦИЯ]: произведена проверка поля [Номер заявки] на текст '%s'.%n", text);
    }

    /**
     * Проверяет наличие диалогового окна [Сведения о поставщике].
     */
    public void isParticipantInfoDialogVisible() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        this.waitLoadingImage();
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Выбор заказчика] не обнаружено",
                $(this.getBy(DET_OF_THE_PROC_PART_APPLICATION_PARTICIPANT_INFORMATION_POPUP_ID)).
                        waitUntil(exist, timeout, polling).isDisplayed());
    }

    // endregion

    // region Раздел [Документы]

    /**
     * Прикрепляет файл в разделе [Документы] -> [Проект договора].
     */
    public void attachFileToContractProject() throws Exception {
        SelenideElement contractProjectHeader = $(this.getBy(CONTR_DOCS_CONTRACT_PROJECT_HEADER_XPATH));
        contractProjectHeader.waitUntil(exist, timeout, polling);
        this.scrollToCenterAndclickInElementJS(contractProjectHeader);
        System.out.printf("[ИНФОРМАЦИЯ]: прикрепляем файл '%s' в разделе [Документы] -> [Проект договора].%n",
                config.getConfigParameter("FoundationDoc"));
        $(this.getBy(CONTR_DOCS_ATTACH_FILE_TO_CONTRACT_PROJECT_XPATH)).waitUntil(exist, timeout, polling).
                sendKeys(config.getAbsolutePathToFoundationDoc());
        this.waitForPageLoad(5);
        $(this.getBy(CONTR_DOCS_CONTRACT_PROJECT_ATTACHED_FILE_XPATH)).waitUntil(visible, timeout, polling).
                shouldHave(text(config.getConfigParameter("FoundationDoc")));
        System.out.println("[ИНФОРМАЦИЯ]: файл прикреплен успешно, ссылка для скачивания присутствует.");
    }

    /**
     * Проверяет дату прикрепленного файла в разделе [Документы] -> [Проект договора].
     */
    public void checkAttachedFileDate() {
        String expectedFileDate = timer.dateOnlyToResponseFormat4Ui(new Date());
        String actualFileDate =
                $(this.getBy(CONTR_DOCS_CONTRACT_PROJECT_ATTACHED_FILE_DATE_XPATH)).waitUntil(visible, timeout, polling).getText();
        String message = String.format("[ОШИБКА]: ожидаемая дата [%s] не содержится в значении поля [%s]",
                expectedFileDate, actualFileDate);

        Assert.assertTrue(message, actualFileDate.contains(expectedFileDate));

        System.out.printf("[ИНФОРМАЦИЯ]: ожидаемая дата [%s] успешно найдена в значении поля [%s].%n",
                expectedFileDate, actualFileDate);
    }


    /**
     * Проверяет наличие на странице [Сведения о договоре] блока [Протокол разногласий] и
     * ссылки на прикрепленный файл в этом блоке.
     */
    public void checkDisagreementsProtocolHeaderPresenceAndLinkToAttachedFilePresence() {
        this.scrollToCenter(this.getBy(CONTR_DOCS_HEADER_XPATH));
        $(this.getBy(CONTR_DOCS_DISAGREEMENTS_PROTOCOL_HEADER_XPATH)).shouldBe(visible);
        $(this.getBy(CONTR_DOCS_DISAGREEMENTS_PROTOCOL_ATTACHED_FILE_XPATH)).shouldBe(visible);
    }

    // endregion

    // region Раздел [Сведения об исполнении договора]

    /**
     * Проверяет текст ячейки в таблице [Сведения об исполнении договора] для столбца с указанным именем и
     * номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     */
    public void verifyCellByNameInLineByNumberFromExecutionsGridForText(
            String columnName, String rowNumber, String cellValue) {
        this.verifyCellInTableByColumnElementsAndLineNumberForText("Сведения об исполнении договора",
                columnName, $$(this.getBy(execPositions.find(columnName))), rowNumber, cellValue);
    }

    // endregion

    // region Раздел [Сведения о расторжении договора]

    /**
     * Проверяет текст ячейки в таблице [Сведения о расторжении договора] для столбца с указанным именем и
     * номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     */
    public void verifyCellByNameInLineByNumberFromTerminationsGridForText(
            String columnName, String rowNumber, String cellValue) {
        this.verifyCellInTableByColumnElementsAndLineNumberForText("Сведения о расторжении договора",
                columnName, $$(this.getBy(termPositions.find(columnName))), rowNumber, cellValue);
    }

    // endregion

    // region Блок кнопок управления договором

    /**
     * Проверяет возможность выгрузить информацию о договоре.
     */
    public void checkPossibilityToDownloadInformationAboutContract() throws Exception {
        this.checkPossibilityToDownloadFileWithGeneratedName(this.getBy(DOWNLOAD_CONTRACT_INFO_LINK_XPATH),
                "ссылке [Выгрузить информацию о договоре]");
    }

    /**
     * Проверяет возможность экспорта истории в Excel-файл.
     */
    public void checkPossibilityToExportHistoryAsExcelFile() throws Exception {
        if (config.getConfigParameter("CheckContractsHistoryExport").equals("off")) return;

        // Делаем щелчок по кнопке [История]
        $(this.getBy(HISTORY_BUTTON_ID)).waitUntil(visible, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        this.waitForPageLoad();

        // Проверяем, что окно [История] открыто
        $(this.getBy(blockNames.find("История"))).waitUntil(visible, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);

        // Проверяем возможность экспорта истории в Excel-файл.
        this.checkPossibilityToDownloadFileWithGeneratedName(this.getBy(CONTR_HIST_EXPORT_CONTRACTS_HISTORY_TO_EXCEL_BUTTON_ID),
                "История");

        // Закрываем окно [История]
        TimeUnit.SECONDS.sleep(3);
        $(this.getBy(CONTR_HIST_CLOSE_BUTTON_XPATH)).waitUntil(visible, timeout, polling).click();
        this.waitForPageLoad();

        // И возвращаемся на предыдущую страницу для корректного продолжения сценария
        this.goBackToPreviousPage();
        this.checkPageUrl("Мои контракты");
    }

    // endregion

    // region Общие методы для работы с элементами этой страницы

    /**
     * Переходит к просмотру раздела договора с указанным именем.
     *
     * @param partitionName имя раздела
     */
    public void goToPartition(String partitionName) throws Exception {
        SelenideElement partitonHeader = $(this.getBy(blockNames.find(partitionName)));
        this.scrollToCenter(partitonHeader);
        partitonHeader.waitUntil(clickable, longtime, polling);
        this.clickInElementJS(partitonHeader);
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
     * Выбирает в переключателе опцию с указанным именем.
     *
     * @param radioButtonName имя опции
     */
    public void selectDesiredOptionInRadioButton(String radioButtonName) {
        System.out.printf("[ИНФОРМАЦИЯ]: выбираем положение переключателя [%s] - ", radioButtonName);
        $(this.getBy(radioButtonNames.find(radioButtonName))).waitUntil(exist, timeout, polling).click();
        System.out.println("Ok, выбрано.");
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
     * Проверяет наличие на странице [Сведения о договоре] кнопки с указанным именем.
     *
     * @param buttonName имя кнопки
     */
    public void checkThePresenceOfButtonByButtonName(String buttonName) {
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        System.out.printf("[ИНФОРМАЦИЯ]: [%s] проверка наличия кнопки [%s] на странице [Сведения о договоре].%n",
                timer.getCurrentDateTime("dd.MM.yyyy HH:mm:ss"), buttonName);
        $(this.getBy(buttons.find(buttonName))).shouldBe(clickable);
        System.out.printf("[ИНФОРМАЦИЯ]: [%s] кнопка [%s] в наличии на странице [Сведения о договоре].%n",
                timer.getCurrentDateTime("dd.MM.yyyy HH:mm:ss"), buttonName);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Проверяет отсутствие на странице [Сведения о договоре] кнопки с указанным именем.
     *
     * @param buttonName имя кнопки
     */
    public void checkTheAbsenceOfButtonByButtonName(String buttonName) {
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        System.out.printf("[ИНФОРМАЦИЯ]: [%s] проверка отсутствия кнопки [%s] на странице [Сведения о договоре].%n",
                timer.getCurrentDateTime("dd.MM.yyyy HH:mm:ss"), buttonName);
        $(this.getBy(buttons.find(buttonName))).shouldBe(hidden);
        System.out.printf("[ИНФОРМАЦИЯ]: [%s] кнопка [%s] отсутствует на странице [Сведения о договоре].%n",
                timer.getCurrentDateTime("dd.MM.yyyy HH:mm:ss"), buttonName);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Проверяет, что кнопка отображается на странице и разрешена (visible, enabled).
     *
     * @param controlName имя элемента
     */
    public void isControlClickable(String controlName) {
        this.isControlClickable(controlName, $(this.getBy(buttons.find(controlName))));
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
        new ErrorDialog(driver).checkForErrorDialogWindow();
    }

    // endregion
}
