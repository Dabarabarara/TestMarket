package ru.PageObjects;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Общая часть логики, которая может быть применена для любой страницы площадки.
 * Created by Dima Makieiev on 13.01.2016.
 * Updated by Alexander S. Vasyurenko on 29.04.2021.
 */
public class CommonPage extends AbstractPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Надпись [Номер телефона] в заголовке страницы (по центру)
    private static final String HEADER_PHONE_NO_XPATH = "//div[@class='header__phone']";
    //------------------------------------------------------------------------------------------------------------------
    // Сплошное крутящееся колесико (выполняется операция)
    private static final String LOADING_IMAGE_SOLID_XPATH =
            "//div[@class='loading' or @class='k-loading-image' or @class='k-loading-mask' or @class='up-progress']";
    //------------------------------------------------------------------------------------------------------------------
    // Сегментированное крутящееся колесико (выполняется операция) не визуальный элемент
    private static final String LOADING_IMAGE_SGMNTD_XPATH = "//div[@class='spinjs']";
    //------------------------------------------------------------------------------------------------------------------
    // Сегментированное крутящееся колесико (выполняется операция) визуальный элемент
    private static final String LOADING_IMAGE_SEGMNT_XPATH = "//div[@class='spinjs']/div/div/div";
    //------------------------------------------------------------------------------------------------------------------
    // Прямоугольник с надписью [Загрузка ...] или [Ждите. Идет подписание ...]
    private static final String LOADING_RECTANGLE_XPATH =
            "//*[@id='dsSignAndSend_progressbar'" +
                    " or @id='load_BaseMainContent_MainContent_jqgTrade'" +
                    " or @id='load_BaseMainContent_MainContent_jqgContract'" +
                    " or @id='load_BaseMainContent_MainContent_ucAccountingList_jqgAccounting'" +
                    " or @id='load_BaseMainContent_MainContent_jqgApplication'" +
                    " or @id='load_BaseMainContent_MainContent_MessagesContent_jqgMessages']";
    //------------------------------------------------------------------------------------------------------------------
    // Диалоговое окно [Предупреждение] - кнопка [Продолжить]
    private static final String CONTINUE_BUTTON_XPATH = "//*[@id='CommonConfirmWindowOk' or @id='deleteDraft']";
    //------------------------------------------------------------------------------------------------------------------
    // Диалоговое окно [Предупреждение] - кнопка [Да]
    private static final String DELETE_DRAFT_BUTTON_ID = "deleteDraft";
    //------------------------------------------------------------------------------------------------------------------
    // Диалоговое окно [Операция успешно выполнена] - кнопка [ОК]
    private static final String OK_BUTTON_XPATH = "//div[@id='dialogSuccess']/following::button[contains(.,'OK')]";
    //------------------------------------------------------------------------------------------------------------------
    // Диалоговое окно [Информация] - кнопка [ОК]
    private static final String OK_INFO_BUTTON_XPATH = "//*[@id='CommonErrorWindow']//button";
    //------------------------------------------------------------------------------------------------------------------
    // Диалоговое окно [Опубликовано] - [План закупки был отправлен на ЭП] - кнопка [Перейти к реестру планов закупок]
    private static final String GO_TO_REGISTRY_OF_TRADE_PLANS_BUTTON_XPATH = "//*[@id='CommonConfirmWindowCancel']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Сервер] в подвале страницы (справа)
    private static final String SERVER_VERSION_XPATH = "//font[@color='gray' and contains(.,'SERVER:')]";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary urls = new Dictionary(); // фрагменты адресов страниц приложения для проверок

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CommonPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        urls.add("Поиск закупок", "/Trade/Search.aspx");
        urls.add("Закупка №", "Trade/View.aspx");
        urls.add("Процедура №", "Trade/View.aspx");
        urls.add("504 Закупка №", "/View/");
        urls.add("504 Процедура №", "/View/");
        urls.add("Просмотр извещения", ".kontur.ru/customer/lk/auctions/view/");
        urls.add("Просмотр извещения (/Auctions/View/)", ".kontur.ru/customer/lk/Auctions/View/");
        urls.add("Формирование черновика извещения конкурса", ".kontur.ru/customer/lk/trade/edit/");
        urls.add("Формирование черновика извещения конкурса - Angular", ".kontur.ru/customer/lk/App/trade/tender223/edit/");
        urls.add("Создание черновика извещения конкурса", ".kontur.ru/customer/lk/trade/create/Commercial/Tender");
        urls.add("Создание черновика извещения конкурса - Angular", ".kontur.ru/customer/lk/App/trade/tender223/Commercial/create");
        urls.add("Создание черновика извещения конкурса - конкурентная закупка",
                ".kontur.ru/customer/lk/trade/create/Competitive/Tender");
        urls.add("Создание черновика извещения конкурса - неконкурентная закупка",
                ".kontur.ru/customer/lk/trade/create/NotCompetitive/Tender");
        urls.add("Создание черновика извещения 'Конкурентная закупка - Конкурс' - Angular",
                ".kontur.ru/customer/lk/App/trade/tender223/Competitive/create");
        urls.add("Создание черновика извещения запрос котировок - конкурентная закупка",
                ".kontur.ru/customer/lk/trade/create/Competitive/QuotationsRequest");
        urls.add("Создание черновика извещения конкурса - МСП", ".kontur.ru/customer/lk/App/trade/tender/create");
        urls.add("Формирование черновика извещения 'Конкурс МСП по 223-ФЗ' - Angular",
                ".kontur.ru/customer/lk/App/trade/tenderSmb/Smb/create");
        urls.add("Внесение изменений в извещение конкурса - МСП", ".kontur.ru/customer/lk/App/trade/tender/edit");
        urls.add("Формирование черновика извещения аукциона", ".kontur.ru/customer/lk/trade/create");
        urls.add("Формирование черновика извещения аукциона - Angular",
                ".kontur.ru/customer/lk/App/trade/auction223/edit");
        urls.add("Формирование черновика извещения 'Закупка по 223-ФЗ - Аукцион' - Angular",
                ".ru/customer/lk/App/trade/auction223/NotCompetitive/create");
        urls.add("Формирование черновика извещения 'Закупка по 223-ФЗ - Предварительный отбор (с продлением)' - Angular",
                ".kontur.ru/customer/lk/App/trade/eternalPrequalification/NotCompetitive/create");
        urls.add("Формирование черновика извещения 'Закупка по 223-ФЗ - Запрос предложений' - Angular",
                ".kontur.ru/customer/lk/App/trade/applicationRequest/NotCompetitive/create");
        urls.add("Формирование черновика извещения 'Закупка по 223-ФЗ - Конкурс' - Angular",
                ".ru/customer/lk/App/trade/tender223/NotCompetitive/create");
        urls.add("Формирование черновика извещения 'Конкурентная закупка по 223ФЗ - Аукцион' - Angular",
                ".kontur.ru/customer/lk/App/trade/auction223/Competitive/create");
        urls.add("Формирование черновика извещения 'Конкурентная закупка по 223ФЗ - Аукцион (заявка в двух частях)' - Angular",
                ".kontur.ru/customer/lk/App/trade/auctionTwoParts/Competitive/create");
        urls.add("Формирование черновика извещения 'Конкурентная закупка по 223ФЗ - Конкурс' - Angular",
                ".kontur.ru/customer/lk/App/trade/tender223/Competitive/create");
        urls.add("Формирование черновика извещения 'Конкурентная закупка по 223ФЗ - Запрос котировок' - Angular",
                ".ru/customer/lk/App/trade/quotationsRequest223/Competitive/create");
        urls.add("Формирование черновика извещения 'Конкурентная закупка по 223ФЗ - Конкурс (заявка в двух частях)' - Angular",
                ".kontur.ru/customer/lk/App/trade/tenderTwoParts/Competitive/create");
        urls.add("Формирование черновика извещения 'Конкурентная закупка по 223ФЗ - Запрос предложений' - Angular",
                ".kontur.ru/customer/lk/App/trade/applicationRequest/Competitive/create");
        urls.add("Формирование черновика извещения 'Конкурентная закупка по 223ФЗ - Запрос цен' - Angular",
                ".kontur.ru/customer/lk/App/trade/priceRequest/Competitive/create");
        urls.add("Формирование черновика извещения 'Коммерческая закупка - Запрос котировок' - Angular",
                ".kontur.ru/customer/lk/App/trade/quotationsRequest223/Commercial/create");
        urls.add("Формирование черновика извещения 'Коммерческая закупка - Запрос цен' - Angular",
                ".kontur.ru/customer/lk/App/trade/priceRequest/Commercial/create");
        urls.add("Формирование черновика извещения 'Коммерческая закупка - Конкурс' - Angular",
                ".kontur.ru/customer/lk/App/trade/tender223/Commercial/create");
        urls.add("Формирование черновика извещения 'Коммерческая закупка - Аукцион' - Angular",
                ".kontur.ru/customer/lk/App/trade/auction223/Commercial/create");
        urls.add("Формирование черновика извещения 'Комерческая закупка - Аукцион(заявка в двух частях)' - Angular",
                "/customer/lk/App/trade/auctionTwoParts/Commercial/create");
        urls.add("Формирование черновика извещения аукциона (заявка в двух частях)",
                ".kontur.ru/customer/lk/trade/create/Commercial/AuctionTwoParts");
        urls.add("Формирование черновика извещения конкурса (заявка в двух частях) - Angular",
                ".kontur.ru/customer/lk/App/trade/tenderTwoParts/edit");
        urls.add("Формирование черновика извещения запроса цен",
                ".kontur.ru/customer/lk/trade/create/Commercial/PriceRequest");
        urls.add("Формирование черновика извещения запроса котировок",
                ".kontur.ru/customer/lk/trade/create/Commercial/QuotationsRequest");
        urls.add("Формирование черновика извещения запроса котировок - Angular",
                ".kontur.ru/customer/lk/App/trade/quotationsRequest223/edit");
        urls.add("Формирование черновика извещения", ".kontur.ru/customer/lk/trade/edit/");
        urls.add("Формирование черновика извещения - Angular", ".kontur.ru/customer/lk/App/trade/tender223/edit/");
        urls.add("Формирование черновика извещения аукциона МСП от РЖД",
                ".kontur.ru/customer/lk/App/trade/auction/edit/");
        urls.add("Формирование черновика извещения аукциона МСП от РЖД - Angular",
                ".kontur.ru/customer/lk/app/trade/auctionsmb/edit/");
        urls.add("Формирование черновика извещения конкурса МСП от РЖД",
                ".kontur.ru/customer/lk/App/trade/tender/edit/");
        urls.add("Формирование черновика извещения конкурса МСП от РЖД - Angular",
                ".kontur.ru/customer/lk/App/trade/tenderSmb/edit/");
        urls.add("Формирование черновика извещения запроса котировок МСП от РЖД",
                ".kontur.ru/customer/lk/App/trade/quotationsRequest/edit/");
        urls.add("Формирование черновика извещения запроса котировок МСП от РЖД - Angular",
                ".kontur.ru/customer/lk/app/trade/quotationsrequestsmb/edit/");
        urls.add("Формирование черновика извещения запроса предложений МСП от РЖД",
                ".kontur.ru/customer/lk/App/trade/proposalsRequest/edit/");
        urls.add("Формирование черновика извещения запроса предложений МСП от РЖД - Angular",
                ".kontur.ru/customer/lk/App/trade/applicationRequestSmb/edit/");
        urls.add("Список заявок", "Accreditation/RequestList.aspx");
        urls.add("Системные задачи", "Scheduler/Scheduler.aspx");
        urls.add("Заявки на добавление / изменение информации пользователей", "Accreditation/EmployeeRequestList.aspx");
        urls.add("Ускорение процедур", "Trade/TimeShift.aspx");
        urls.add("Один из протоколов", ".kontur.ru/customer/lk/Protocols/ProtocolForm");
        urls.add("Мои закупки", "customer/lk/Auctions/");
        urls.add("Счета компании", "Accounting/AccountingList.aspx");
        urls.add("Просмотр транзакции", "Accounting/TransactionView.aspx");
        urls.add("Просмотр счёта", "Accounting/AccountingView.aspx");
        urls.add("Информация о торгах", "Trade/AuctionInfo.aspx");
        urls.add("Информация о пользователе - Заказчик",
                "/lk/Accreditation/DataProccessingAgreement.aspx?IsCustomer=true");
        urls.add("Информация о торгах при попозиционном сравнении ценовых предложений",
                "Trade/OfflineRetradingInfo.aspx");
        urls.add("Информация о торгах при МСП-закупке в соответствии с нормами 223-ФЗ",
                "Trade/OfflineRetradingInfo.aspx");
        urls.add("Торги", "Trade/Auction.aspx");
        urls.add("Торги при МСП-закупке в соответствии с нормами 223-ФЗ",
                ".kontur.ru/supplier/auction/app/offlineauction/");
        urls.add("Реестр транзакций счета", "Accounting/Transactions.aspx");
        urls.add("Авторизация Поставщиком", "supplier");
        urls.add("Главная страница-Поставщик", "supplier/lk");
        urls.add("Мои заявки", "Private/Participant.aspx");
        urls.add("Мои Торги/Переторжки", ".kontur.ru/supplier/auction/Private/MyTenders.aspx");
        urls.add("Мои договоры", "dealing/Default.aspx");
        urls.add("Вход в личный кабинет (Заказчик)", ".kontur.ru/customer/lk/");
        urls.add("Внесение изменений в извещение конкурса", ".kontur.ru/customer/lk/Trade/Edit/");
        urls.add("Мои контракты", ".kontur.ru/customer/lk/Contracts/");
        urls.add("Настройки интеграции с ЕИС", ".kontur.ru/customer/lk/IntegrationSettings");
        urls.add("Планы закупок", ".kontur.ru/customer/lk/TradePlans");
        urls.add("Реестр отчетов", ".kontur.ru/customer/report-registry");
        urls.add("Создание плана закупки", ".kontur.ru/customer/lk/TradePlans/TradePlan/Create");
        urls.add("Редактирование плана закупки", ".kontur.ru/customer/lk/TradePlans/TradePlan/Edit/");
        urls.add("Просмотр плана закупок", ".kontur.ru/customer/lk/TradePlans/TradePlan/View/");
        urls.add("Реестр участников закупок",
                ".kontur.ru/supplier/lk/Dictionaries/OrganizationList.aspx");
        urls.add("Реестр заказчиков", ".kontur.ru/supplier/lk/Dictionaries/OrganizationList.aspx?IsCustomer=True");
        urls.add("Информация о пользователе - Администратор", ".kontur.ru/supplier/lk/Dictionaries/EmployeeView");
        urls.add("Предложения для Вас", ".kontur.ru/supplier/auction/app/recommendedTrades");
        urls.add("Сведения о договоре", "ontracts/view/");
        urls.add("ЛК Поставщика", ".kontur.ru/supplier/lk/");
        //..............................................................................................................
        urls.add("Заявка на участие в режиме редактирования", ".kontur.ru/supplier/auction/app/application/create");
        urls.add("Заявка на участие в режиме редактирования старая",
                ".kontur.ru/supplier/auction/Application/Create.aspx");
        urls.add("Заявка на участие в режиме редактирования 44-ФЗ", "Application/Create?");
        //..............................................................................................................
        urls.add("Заявка на участие в режиме просмотра", ".kontur.ru/supplier/auction/app/application/view/");
        urls.add("Заявка на участие в режиме просмотра старая",
                ".kontur.ru/supplier/auction/Application/View.aspx");
        //..............................................................................................................
        urls.add("Первая часть печатной формы заявки на участие от Поставщика",
                ".kontur.ru/customer/lk/Iframe/TradeLotApplicationPrintForm?applicationId=");
        urls.add("Вторая часть печатной формы заявки на участие от Поставщика",
                ".kontur.ru/customer/lk/Iframe/TradeLotApplicationResultPrintForm?applicationId");
        urls.add("Печатная форма заявки на участие от Поставщика (ПОД)",
                ".kontur.ru/customer/lk/Iframe/TradeLotApplicationPrintForm?applicationId=");
        urls.add("Печатная форма заявки на участие от Поставщика (ПРОЗ)",
                ".kontur.ru/customer/lk/Iframe/GetTradeLotApplicationPrintFormContentForProtocol?");
        urls.add("Печатная форма сведения об участнике закупки (ПРОЗ)",
                ".kontur.ru/customer/lk/Iframe/TradeLotApplicationPrintFormPurchaserInfo?");
        urls.add("Заявка на участие в аукционе от Поставщика 1-я часть",
                "/customer/lk/Iframe/TradeLotApplicationPrintForm");
        urls.add("Заявка на участие в аукционе от Поставщика 2-я часть",
                "/customer/lk/Iframe/TradeLotApplicationResultPrintForm");
        urls.add("Заявка на участие в аукционе от Поставщика А2Ч - ППИ",
                "/customer/lk/Iframe/GetTradeLotApplicationPrintFormContentForProtocol");
        urls.add("Информация об организации", ".kontur.ru/supplier/lk/Accreditation/OrganizationViewTabed.aspx");
        urls.add("Заявка на добавление пользователя", ".ru/supplier/lk/Accreditation/EmployeeRequest.aspx");
        urls.add("Список пользователей", ".kontur.ru/supplier/lk/Dictionaries/EmployeeList.aspx");
        urls.add("Реестр квалифицированных подрядных организаций", ".kontur.ru/customer/lk/QualifiedSupplier");
        urls.add("Реестр дополнительных полей", ".kontur.ru/customer/lk/ExternalField");
        urls.add("Настройка ролей пользователя", ".kontur.ru/supplier/lk/AccessControl/UserRoles.aspx");
        urls.add("Информация о заявки после аккредитации",
                "/supplier/lk/Accreditation/RequestInfoTabed.aspx?RequestGuid");
        urls.add("Заказчик-Направьте договор", ".kontur.ru/customer/lk/Contracts/View/");
        urls.add("Карточка заказчика", "/supplier/lk/Customers/CustomerCard.aspx?Id=");
        urls.add("Заявка на участие", "/customer/lk/Iframe/TradeLotApplicationLastPrintForm?applicationId=");
        urls.add("Сведения об участнике", "/customer/lk/Iframe/TradeLotParticipantPrintForm?docID=");
        urls.add("Заказчик-Перенаправьте договор", ".kontur.ru/customer/lk/Contracts/View/");
        urls.add("Поставщик-Подпишите договор", ".kontur.ru/supplier/dealing/Contract.aspx?Id=");
        urls.add("Заказчик-Подпишите договор", ".kontur.ru/customer/lk/Contracts/View/");
        urls.add("Отказ от заключения договора XXXX.XXXX", ".kontur.ru/customer/lk/Contracts/RefuseView?id=");
        urls.add("Сведения об исполнении договора XXXX.XXXX (редактирование)",
                ".kontur.ru/customer/lk/Contracts/ExecutionEdit?dealId=");
        urls.add("Сведения об исполнении договора XXXX.XXXX (просмотр)",
                ".kontur.ru/customer/lk/Contracts/ExecutionView/");
        urls.add("Сведения о расторжении договора XXXX.XXXX (редактирование)",
                ".kontur.ru/customer/lk/Contracts/TerminationEdit?dealId=");
        urls.add("Сведения о расторжении договора XXXX.XXXX (просмотр)",
                ".kontur.ru/customer/lk/Contracts/TerminationView/");
        urls.add("МОЯ ОРГАНИЗАЦИЯ/СПИСОК ПОЛЬЗОВАТЕЛЕЙ", ".kontur.ru/supplier/lk/Dictionaries/EmployeeList.aspx");
        urls.add("МОЯ ОРГАНИЗАЦИЯ/ПОЛНОМОЧИЯ ПАРТНЕРОВ В МОИХ ЗАКУПКАХ",
                ".kontur.ru/supplier/lk/PartnerRelation/PartnerRelationList.aspx?");
        urls.add("МОЯ ОРГАНИЗАЦИЯ/МОИ ПОЛНОМОЧИЯ В ЗАКУПКАХ ПАРТНЕРОВ",
                ".kontur.ru/supplier/lk/PartnerRelation/PartnerRelationList.aspx?");
        urls.add("МОЯ ОРГАНИЗАЦИЯ/ЖУРНАЛ ВЗАИМОДЕЙСТВИЯ С ЕИС", ".kontur.ru/customer/lk/OosMessageQueue/Table");
        urls.add("МОЯ ОРГАНИЗАЦИЯ/ОБЩИЕ НАСТРОЙКИ", ".kontur.ru/customer/lk/OrgInfo");
        urls.add("ЗАКУПКИ/Заказчикам", ".kontur.ru/customer/lk/Auctions/Cards");
        urls.add("Редактирование информации",
                ".kontur.ru/supplier/lk/Accreditation/Request.aspx?RequestKind=Modification");
        urls.add("ЗАПРОСЫ/ЗАПРОСЫ НА РАЗЪЯСНЕНИЕ ДОКУМЕНТАЦИИ",
                ".kontur.ru/customer/lk/RegisterOfInquiries/Index/Documentation");
        urls.add("Запрос на разъяснение положений документации о закупке",
                ".kontur.ru/customer/lk/RegisterOfInquiries/ViewInquiries");
        urls.add("Создание разъяснения положений документации о закупке",
                ".kontur.ru/customer/lk/RegisterOfInquiries/CreateClarifyDocResponse/");
        urls.add("ЗАПРОСЫ/ЗАПРОСЫ НА РАЗЪЯСНЕНИЕ РЕЗУЛЬТАТОВ",
                ".kontur.ru/customer/lk/RegisterOfInquiries/Index/Result");
        urls.add("Запрос на разъяснение результатов - Разъяснение результатов закупки",
                ".kontur.ru/customer/lk/RegisterOfInquiries/ViewInquiries/");
        urls.add("ЗАПРОСЫ/ЗАПРОСЫ НА РАЗЪЯСНЕНИЕ ЗАЯВОК",
                ".kontur.ru/customer/lk/RegisterOfInquiries/Index/Application");
        urls.add("Запрос на разъяснение заявки - создание",
                ".kontur.ru/customer/lk/RegisterOfInquiries/CreateInquiry?applicationId=");
        urls.add("Запрос на разъяснение заявки - редактирование",
                ".kontur.ru/customer/lk/RegisterOfInquiries/EditInquiry?inquiryId=");
        urls.add("Разъяснение заявки", ".kontur.ru/customer/lk/RegisterOfInquiries/ViewInquiries/");
        urls.add("Запросы на разъяснение документации",
                ".kontur.ru/supplier/auction/Private/MyResultRequests.aspx?Type=1");
        urls.add("Запросы на разъяснение результатов",
                ".kontur.ru/supplier/auction/Private/MyResultRequests.aspx?Type=2");
        urls.add("Запросы на разъяснение заявок",
                ".kontur.ru/supplier/auction/Private/MyResultRequests.aspx?Type=3");
        urls.add("Запрос на разъяснение документации в режиме редактирования",
                ".kontur.ru/supplier/auction/CommonClarificationRequest/Create.aspx?TradeId=");
        urls.add("Запрос на разъяснение заявки в режиме редактирования",
                ".kontur.ru/supplier/auction/CommonClarificationRequest/Create.aspx?Id=");
        urls.add("Запрос на разъяснение документации в режиме просмотра",
                ".kontur.ru/supplier/auction/CommonClarificationRequest/View.aspx?Id=");
        urls.add("Запрос на разъяснение результатов в режиме просмотра",
                ".kontur.ru/supplier/auction/CommonClarificationRequest/View.aspx?Id=");
        urls.add("Запрос на разъяснение заявки в режиме просмотра",
                ".kontur.ru/supplier/auction/CommonClarificationRequest/View.aspx?Id=");
        urls.add("Делегирование доступа организации",
                ".kontur.ru/supplier/lk/PartnerRelation/PartnerRelationRequest.aspx?");
        urls.add("Запрос на установление связи",
                ".kontur.ru/supplier/lk/PartnerRelation/PartnerRelationRequest.aspx?");
        urls.add("Запрос на установление связи-принять запрос или отказать в принятии",
                ".kontur.ru/supplier/lk/PartnerRelation/PartnerRelationRequestInfo.aspx?");
        urls.add("Карточка партнёрской связи", ".kontur.ru/supplier/lk/PartnerRelation/PartnerRelationCard.aspx?");
        urls.add("Исходящие запросы на добавление связи",
                ".kontur.ru/supplier/lk/PartnerRelation/PartnerRelationRequestList.aspx");
        urls.add("Просмотр сообщения", ".kontur.ru/customer/lk/Message/ViewMessage/");
        urls.add("УВЕДОМЛЕНИЯ/ВХОДЯЩИЕ", ".kontur.ru/customer/lk/Message");
        urls.add("УВЕДОМЛЕНИЯ/ОТПРАВЛЕННЫЕ", ".kontur.ru/customer/lk/Message/outMessage");
        urls.add("Заказчик/Уведомления/Создание сообщения", ".kontur.ru/customer/lk/Message/CreateMessage");
        urls.add("МОЯ ОРГАНИЗАЦИЯ/Уведомления", ".kontur.ru/supplier/lk/Private/MessagesList.aspx");
        urls.add("МОЯ ОРГАНИЗАЦИЯ/Уведомления/[Написать]",
                ".kontur.ru/supplier/lk/Private/WriteMessage.aspx?Type=Incoming");
        urls.add("МОЯ ОРГАНИЗАЦИЯ/Уведомления/Входящее сообщение №",
                ".kontur.ru/supplier/lk/Private/MessageView.aspx?Type=Incoming");
        urls.add("Запрос на разъяснение результатов в режиме редактирования",
                ".kontur.ru/supplier/auction/CommonClarificationRequest/Create.aspx?");
        urls.add("Страница перехвата e-mail сообщений Fake SMTP", ".kontur.ru/fakesmtp/Messages?pageNumber=1");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Осуществляет переход на страницу по прямому адресу с проверкой загрузки страницы.
     *
     * @param url              адрес страницы на которую требуется перейти
     * @param pageName         имя страницы на которую требуется перейти
     * @param nTries           количество попыток перехода на страницу по прямому адресу
     * @param timeoutInSeconds временная задержка в секундах перед основными действиями для медленных стендов
     */
    public void goToUrl(String url, String pageName, int nTries, long timeoutInSeconds) throws Exception {
        for (int i = 1; i <= nTries; i++) {
            System.out.printf("[ИНФОРМАЦИЯ]: попытка №%d перехода по прямому адресу [%s].%n", i, url);
            open(url);
            this.waitForPageLoad(timeoutInSeconds);
            String noticeId = String.format(".kontur.ru/supplier/auction/Trade/View.aspx?Id=%s",
                    url.split("Id=")[1].split("#")[0]);
            System.out.printf("[ИНФОРМАЦИЯ]: подстрока адреса для проверки: [%s].%n", noticeId);
            if (url().contains(noticeId)) break;
            refresh();
            this.waitForPageLoad();
        }
        this.checkPageUrl(pageName);
    }

    /**
     * Проверяет, что открыта корректная страница с указанным именем.
     *
     * @param pageName имя страницы для проверки
     * @return результат проверки
     */
    private boolean urlCorrect(String pageName) {
        System.out.printf("[--- Текущая страница имеет адрес: '%s']%n", url());
        System.out.printf("[--- Проверяем URL страницы: '%s' по маске '%s']%n", pageName, urls.find(pageName));
        return url().toLowerCase().contains(urls.find(pageName).toLowerCase());
    }

    /**
     * Проверяет, что открыта корректная страница с указанным именем.
     *
     * @param pageName имя страницы для проверки
     */
    public void checkPageUrl(String pageName) throws Exception {
        this.waitForPageLoad();
        Assert.assertTrue(String.format("[ОШИБКА]: страница: '%s' не открыта", pageName),
                this.urlCorrect(pageName));
    }

    /**
     * Проверяет, что открыта одна из двух страниц с указанными именами.
     *
     * @param pageNameFirst  имя первой страницы для проверки
     * @param pageNameSecond имя второй страницы для проверки
     * @return имя открытой страницы ( первой или второй )
     */
    public String checkPageUrl(String pageNameFirst, String pageNameSecond) throws Exception {
        this.waitForPageLoad();
        System.out.printf("[--- Текущая страница имеет адрес: '%s']%n", url());
        System.out.printf("[--- Проверяем URL страниц: '%s' или '%s' по маскам '%s' или '%s' соответственно]%n",
                pageNameFirst, pageNameSecond, urls.find(pageNameFirst), urls.find(pageNameSecond));

        if (url().toLowerCase().contains(urls.find(pageNameFirst).toLowerCase())) return pageNameFirst;
        else if (url().toLowerCase().contains(urls.find(pageNameSecond).toLowerCase())) return pageNameSecond;
        else Assert.fail(String.format("[ИНФОРМАЦИЯ]: ни одна из страниц: '%s' или '%s' не открыта",
                    pageNameFirst, pageNameSecond));

        return "false";
    }

    /**
     * Проверяет, что открыта одна из страниц с указанными именами.
     *
     * @param pageNames список имен страниц для проверки
     */
    public void checkPageUrl(List<String> pageNames) throws Exception {
        this.waitForPageLoad();

        String url = url().toLowerCase();
        System.out.printf("[--- Текущая страница имеет адрес: '%s']%n", url);

        for (String pageName : pageNames) {
            String mask = urls.find(pageName).toLowerCase();
            System.out.printf("[--- Проверяем URL страницы: '%s' по маске '%s']%n", pageName, mask);
            if (url.contains(mask)) return;
        }

        Assert.fail("[ОШИБКА]: ни одна из указанных страниц не открыта");
    }

    /**
     * Возвращается на предыдущую страницу.
     */
    public void goBackToPreviousPage() throws Exception {
        getWebDriver().navigate().back();
        this.waitForPageLoad();
        int size = getWebDriver().getWindowHandles().size();
        String url = url();
        String title = getWebDriver().getTitle();
        System.out.printf("[ИНФОРМАЦИЯ]: возврат на предыдущую страницу [%s] c URL [%s].%n", title, url);
        System.out.printf("[ИНФОРМАЦИЯ]: общее число открытых окон в браузере [%d].%n", size);
    }

    /**
     * Обновляет страницу в браузере.
     */
    public void refreshPage() throws Exception {
        refresh();
        this.waitForPageLoad();
        TimeUnit.SECONDS.sleep(10);   // Не удаляйте эту строку и не меняйте значение времени ожидания !
    }

    /**
     * Перемещает область видимости на начало страницы.
     */
    public void goToPageTop() throws Exception {
        $(By.xpath(HEADER_PHONE_NO_XPATH)).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * Перемещает область видимости в конец страницы.
     */
    protected void goToPageBottom() throws Exception {
        this.getCurrentServerVersion();
    }

    /**
     * Перемещает область видимости в конец страницы.
     * Возвращает текущую версию сервера для тестируемого приложения в виде строки [Сервер: 102.5].
     *
     * @return текущая версия сервера для тестируемого приложения
     */
    public String getCurrentServerVersion() throws Exception {
        SelenideElement ver = $(By.xpath(SERVER_VERSION_XPATH)).waitUntil(exist, timeout, polling);
        this.scrollToCenter(ver);
        TimeUnit.SECONDS.sleep(3);
        ver.shouldBe(visible);
        return ver.getText();
    }

    /**
     * Ожидает полной загрузки страницы.
     */
    public void waitForPageLoad() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        new WebDriverWait(driver, 90).until((ExpectedCondition<Boolean>) wd ->
                (Boolean) ((JavascriptExecutor) wd).executeScript("return document.readyState == 'complete'"));
        this.waitLoadingImage();        // Не убирайте эту строку - Javascript не ловит колесико при загрузке страницы !
        this.waitLoadingRectangle();    // Не убирайте эту строку - Javascript не ловит прямоуг. при загрузке страницы !
        this.waitForAjax();             // Ждем завершения Ajax на странице (именно в таком порядке: колесо -> Ajax)
    }

    /**
     * Ожидает полной загрузки страницы.
     *
     * @param timeoutInSeconds временная задержка в секундах перед основными действиями для медленных стендов
     */
    public void waitForPageLoad(long timeoutInSeconds) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: ожидание: [%d] сек.%n", timeoutInSeconds);
        TimeUnit.SECONDS.sleep(timeoutInSeconds);
        this.waitForPageLoad();
    }

    /**
     * Ожидает полной загрузки страницы (тупо временным мнтервалом). Для страниц где спиннер не исчезает.
     *
     * @param timeoutInSeconds временная задержка в секундах перед основными действиями для медленных стендов
     */
    public void waitForPageLoad(String timeoutInSeconds) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: ожидание: [%s] сек.%n", timeoutInSeconds);
        TimeUnit.SECONDS.sleep(Long.parseLong(timeoutInSeconds));
    }

    /**
     * Ожидает завершения Ajax на странице.
     */
    protected void waitForAjax() throws Exception {
        Boolean ajaxIsComplete;
        int i = 0;         // Счетчик попыток
        int nTries = 100;  // Количество попыток
        do {
            i++;
            Thread.sleep(100);
            ajaxIsComplete = (Boolean) ((JavascriptExecutor) driver).
                    executeScript("return window.jQuery != undefined && window.jQuery.active == 0");
        } while (!ajaxIsComplete && i < nTries);  // Цикл пока не закончатся все активные jQuery
    }

    /**
     * Ожидает исчезновения крутящегося колесика (выполняется операция).
     */
    public void waitLoadingImage() throws Exception {
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        if (url().contains("/customer/lk/")) {
            SelenideElement loadingSolid = $(By.xpath(LOADING_IMAGE_SOLID_XPATH));
            if (loadingSolid.isDisplayed()) {
                System.out.println("[ИНФОРМАЦИЯ]: обнаружен сплошной спиннер, ожидаем его исчезновения.");
                loadingSolid.waitUntil(hidden, 900000, 5);
            }
        } else {
            SelenideElement loadingSegmented = $(By.xpath(LOADING_IMAGE_SGMNTD_XPATH));
            ElementsCollection loadingSegments = $$(By.xpath(LOADING_IMAGE_SEGMNT_XPATH));
            if (loadingSegmented.exists() && loadingSegments.get(0).isDisplayed()) {
                System.out.println("[ИНФОРМАЦИЯ]: обнаружен сегментированный спиннер, ожидаем его исчезновения.");
                loadingSegments.get(0).waitUntil(hidden, 900000, 5);
            } else {
                TimeUnit.SECONDS.sleep(1);
                String script = "return document.readyState == 'complete'";
                new WebDriverWait(driver, 90).until((ExpectedCondition<Boolean>) wd ->
                        (Boolean) ((JavascriptExecutor) wd).executeScript(script));
                this.waitForAjax();
            }
        }
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Ожидает исчезновения крутящегося колесика (выполняется операция).
     *
     * @param timeoutInSeconds временная задержка в секундах перед основными действиями для медленных стендов
     */
    public void waitLoadingImage(long timeoutInSeconds) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: дополнительное ожидание [%d] сек.%n", timeoutInSeconds);
        TimeUnit.SECONDS.sleep(timeoutInSeconds);
        this.waitLoadingImage();
    }

    /**
     * Ожидает исчезновения прямоугольника с надписью [Загрузка ...] или [Ждите. Идет подписание ...].
     */
    public void waitLoadingRectangle() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        if ($(By.xpath(LOADING_RECTANGLE_XPATH)).isDisplayed()) {
            System.out.printf("[ИНФОРМАЦИЯ]: ожидаем исчезновения прямоугольника с надписью [%s].%n",
                    $(By.xpath(LOADING_RECTANGLE_XPATH)).getText());
            $(By.xpath(LOADING_RECTANGLE_XPATH)).waitUntil(disappear, 900000, polling);
        }
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Ожидает прямоугольник с надписью [Загрузка ...]. Для страниц где прямоугольник не исчезает а перекрывается.
     *
     * @param timeoutInSeconds временная задержка в секундах перед основными действиями для медленных стендов
     */
    public void waitLoadingRectangle(String timeoutInSeconds) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: ожидание: [%s] сек.%n", timeoutInSeconds);
        TimeUnit.SECONDS.sleep(Long.parseLong(timeoutInSeconds));
    }

    /**
     * Делает клик по кнопке [Продолжить] в окне [Предупреждение].
     */
    public void clickButtonInPopupWindow(String buttonName) throws Exception {
        Dictionary buttons = new Dictionary();
        buttons.add("Продолжить-Предупреждение", CONTINUE_BUTTON_XPATH);
        buttons.add("Да-Предупреждение", DELETE_DRAFT_BUTTON_ID);
        buttons.add("ОК-Операция успешно выполнена", OK_BUTTON_XPATH);
        buttons.add("ОК-Информация", OK_INFO_BUTTON_XPATH);
        buttons.add("Перейти к реестру планов закупок", GO_TO_REGISTRY_OF_TRADE_PLANS_BUTTON_XPATH);
        SelenideElement button = $(this.getBy(buttons.find(buttonName)));
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        button.waitUntil(clickable, longtime, polling).shouldBe(clickable);
        this.logButtonNameToPress(buttonName);
        this.clickInElementJS(button);
        this.logPressedButtonName(buttonName);
        button.waitUntil(disappear, longtime, polling).shouldBe(disappear);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Возвращает список имен файлов в указанном каталоге.
     *
     * @param folderName каталог, откуда следует получить список имен файлов
     * @return список имен файлов в указанном каталоге
     */
    private List<String> getFolderFileNames(String folderName) {
        List<String> listOfFileNames = new ArrayList<>();
        File folder = new File(folderName);
        Assert.assertTrue("[ОШИБКА]: указанное в параметре имя не является каталогом", folder.isDirectory());
        File[] listOfFiles = folder.listFiles();
        Assert.assertNotNull("[ОШИБКА]: список файлов в каталоге = null", listOfFiles);

        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) listOfFileNames.add(listOfFile.getName());
        }
        return listOfFileNames;
    }

    /**
     * Проверяет возможность загрузить файл со случайно сгенерированным именем.
     *
     * @param downloadButton кнопка/ссылка для загрузки файла
     * @param buttonName     название кнопки/ссылки для вывода в консоль отладочной информации
     */
    protected void
    checkPossibilityToDownloadFileWithGeneratedName(By downloadButton, String buttonName) throws Exception {
        this.checkPossibilityToDownloadFileWithGeneratedName($(downloadButton), buttonName);
    }

    /**
     * Проверяет возможность загрузить файл со случайно сгенерированным именем.
     *
     * @param downloadButton              кнопка/ссылка для загрузки файла
     * @param buttonName                  название кнопки/ссылки для вывода в консоль отладочной информации
     * @param minimalDownloadedFileLength минимально допустимую длина загруженного на диск файла
     */
    protected void
    checkPossibilityToDownloadFileWithGeneratedName(
            By downloadButton, String buttonName, long minimalDownloadedFileLength) throws Exception {
        this.checkPossibilityToDownloadFileWithGeneratedName($(downloadButton), buttonName, minimalDownloadedFileLength);
    }

    /**
     * Проверяет возможность загрузить файл со случайно сгенерированным именем.
     *
     * @param downloadButton кнопка/ссылка для загрузки файла
     * @param buttonName     название кнопки/ссылки для вывода в консоль отладочной информации
     */
    protected void
    checkPossibilityToDownloadFileWithGeneratedName(SelenideElement downloadButton, String buttonName) throws Exception {
        this.checkPossibilityToDownloadFileWithGeneratedName(
                downloadButton, buttonName, config.getMinDownloadedFileLength());
    }

    /**
     * Проверяет возможность загрузить файл со случайно сгенерированным именем.
     *
     * @param downloadButton              кнопка/ссылка для загрузки файла
     * @param buttonName                  название кнопки/ссылки для вывода в консоль отладочной информации
     * @param minimalDownloadedFileLength минимально допустимую длина загруженного на диск файла
     */
    protected void
    checkPossibilityToDownloadFileWithGeneratedName(
            SelenideElement downloadButton, String buttonName, long minimalDownloadedFileLength) throws Exception {

        // Делаем щелчок по кнопке/ссылке для загрузки файла
        this.logButtonNameToPress(buttonName);
        this.scrollToCenterAndclickInElementJS(downloadButton);
        this.logPressedButtonName(buttonName);
        this.waitLoadingImage(120);

        // Проверяем содержимое временной папки для скачивания файлов
        System.out.println("[ИНФОРМАЦИЯ]: проверяем содержимое временной папки для скачивания файлов.");
        String tempFolder = config.getPathToTempFolderWithRandomName();
        List<String> listOfFileNames = this.getFolderFileNames(tempFolder);
        Assert.assertNotEquals("[ОШИБКА]: не удалось дождаться загрузки файла во временную папку.",
                0, listOfFileNames.size());
        Assert.assertEquals("[ОШИБКА]: во временном каталоге должен быть только один загруженный файл",
                1, listOfFileNames.size());
        File file = new File(tempFolder + "\\" + listOfFileNames.get(0));

        // Проверяем, что файл загружен успешно
        System.out.printf(
                "[ИНФОРМАЦИЯ]: проверяем минимальный размер (не менее [%d] байт) загруженного файла [%s] - [%d] байт.%n",
                minimalDownloadedFileLength, listOfFileNames.get(0), file.length());
        Assert.assertTrue("[ОШИБКА]: не удалось загрузить файл на диск",
                file.length() >= minimalDownloadedFileLength);
        System.out.println("[ИНФОРМАЦИЯ]: файл загружен успешно.");

        // Удаляем загруженный файл вместе с временным каталогом
        System.out.println("[ИНФОРМАЦИЯ]: удаляем загруженный файл вместе с временным каталогом.");
        File folderToDelete = new File(config.getPathToTempFolderWithRandomName());
        this.deleteTemporaryFolderWithFiles(folderToDelete);
    }

    /**
     * Скачивает zip архив и возвращает имена файлов, содержащихся в архиве
     *
     * @param downloadButton - элемент загрузки файла
     * @param zipName        - название архива
     * @return список имен файлов
     */
    public List<String> downloadZipArchiveAndReturnListOfFileNames(By downloadButton,
                                                                   String zipName) throws Exception {
        List<String> fileNames = new ArrayList<>(); // список имен файлов в архиве

        $(downloadButton).click();
        TimeUnit.SECONDS.sleep(30);

        File file = new File(config.getPathToTempFolderWithRandomName() + "\\" + zipName + ".zip");
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file), Charset.forName("IBM866"));
        ZipEntry zipEntry = zipInputStream.getNextEntry();

        while (zipEntry != null) {
            String fileName = zipEntry.getName();
            fileNames.add(fileName);
            zipEntry = zipInputStream.getNextEntry();
        }

        zipInputStream.closeEntry();
        zipInputStream.close();

        File folderToDelete = new File(config.getPathToTempFolderWithRandomName());
        this.deleteTemporaryFolderWithFiles(folderToDelete);

        return fileNames;
    }

    /**
     * Устанавливает фильтр по одному полю ( критерию поиска ).
     *
     * @param value    значение поля для фильтрации
     * @param criteria поле по которому будет произведена фильтрация
     * @param find     кнопка активации фильтра которую следует нажать
     */
    protected void setFilterByField(String value, By criteria, By find) throws Exception {
        $(criteria).waitUntil(exist, timeout, polling).shouldBe(visible).clear();
        $(criteria).sendKeys(value);
        $(find).waitUntil(exist, timeout, polling).shouldBe(clickable).click();
        this.waitForPageLoad();
    }

    /**
     * Ожидает появления требуемого текста в поле.
     *
     * @param expectedValue требуемый текст или значение поля
     * @param by            поле, в котором проверяется текст или значение
     * @param fieldName     имя поля для вывода в консоль
     */
    protected void waitForTextInField(String expectedValue, By by, String fieldName) throws Exception {
        int waitTimeMinutes = Integer.parseInt(config.getConfigParameter("MaxStatusWaitTime"));
        int nTries = waitTimeMinutes * 60 / 30;
        int i = 0;
        String actualValue;

        System.out.printf(
                "[ИНФОРМАЦИЯ]: установленное макс. время ожидания появления требуемого значения в поле %d минут.%n",
                waitTimeMinutes);

        do {
            actualValue = $(by).waitUntil(exist, timeout, polling).getValue();
            if (actualValue == null || actualValue.equals("")) actualValue = $(by).getText();
            if (!actualValue.equals(expectedValue)) {
                this.refreshPage();
                TimeUnit.SECONDS.sleep(10);
            }
            i++;
            System.out.printf(
                    "[ИНФОРМАЦИЯ]: [%s] Проверка № [%d] значения в поле [%s]: ожидаемое [%s], реальное [%s].%n",
                    timer.getCurrentDateTime("dd.MM.yyyy HH:mm:ss"), i, fieldName, expectedValue, actualValue);
        } while (i <= nTries && !actualValue.equals(expectedValue));

        Assert.assertEquals(
                String.format("[ОШИБКА]: не удалось дождаться появления требуемого значения в поле [%s]", fieldName),
                expectedValue, actualValue);
    }

    /**
     * Ожидает появления требуемого текста в поле (результаты поиска по одному критерию).
     *
     * @param expectedValue требуемый текст или значение поля
     * @param field         поле, в котором проверяется текст или значение
     * @param fieldName     имя поля для вывода в консоль
     * @param criteria      строка, являющаяся критерием поиска
     * @param search        поле, в которое вводится критерий поиска
     * @param button        кнопка, активирующая поиск
     */
    protected void waitForTextInFieldWithSearch(
            String expectedValue, By field, String fieldName, String criteria, By search, By button) throws Exception {
        int waitTimeMinutes = Integer.parseInt(config.getConfigParameter("MaxStatusWaitTime"));
        int nTries = waitTimeMinutes * 60 / 15;
        int i = 0;
        String infoMessage = "[ИНФОРМАЦИЯ]: %s проверка № %d значения в поле [%s]: ожидаемое [%s], реальное [%s].";
        String actualValue;

        System.out.printf(
                "[ИНФОРМАЦИЯ]: установленное макс. время ожидания появления требуемого значения в поле %d минут.%n",
                waitTimeMinutes);

        do {
            actualValue = $(field).waitUntil(visible, timeout, polling).getValue();
            if (actualValue == null || actualValue.equals("")) actualValue = $(field).getText();
            if (!actualValue.equals(expectedValue)) {
                this.refreshPage();
                TimeUnit.SECONDS.sleep(10);
                this.waitClearClickAndSendKeys(search, criteria);
                this.waitLoadingImage();
                $(button).waitUntil(visible, timeout, polling).click();
                this.waitLoadingImage();
            }
            i++;
            System.out.printf((infoMessage) + "%n",
                    timer.getCurrentDateTime("dd.MM.yyyy HH:mm:ss"), i, fieldName, expectedValue, actualValue);
        } while (i <= nTries && !actualValue.equals(expectedValue));

        Assert.assertEquals(
                String.format("[ОШИБКА]: не удалось дождаться появления требуемого значения в поле [%s]", fieldName),
                expectedValue, actualValue);
    }
}
