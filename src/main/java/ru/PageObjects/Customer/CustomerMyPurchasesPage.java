package ru.PageObjects.Customer;

import Helpers.Dictionary;
import Helpers.SoftAssert;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonDialogs.InformationDialog;
import ru.PageObjects.Customer.Notice.CreateNoticePage;
import ru.PageObjects.Customer.Notice.TradeCancelDialog;
import ru.PageObjects.Customer.Notice.ViewNoticePage;
import ru.PageObjects.Customer.Protocol.ConsiderationRequestCustomerProtocolPage;
import ru.PageObjects.Customer.Protocol.OpenAccessCustomerProtocolPage;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Класс описывающий страницу [ Мои закупки ]
 * ( Главная / Заказчикам / Мои закупки )
 * ( .kontur.ru/customer/lk/Auctions/Cards ).
 * Created by Evgeniy Glushko on 24.03.2016.
 * Updated by Alexander S. Vasyurenko on 22.05.2021.
 */
public class CustomerMyPurchasesPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Блок [СОЗДАТЬ ИЗВЕЩЕНИЕ]

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Создать извещение]
    private static final String CREATE_NOTICE_BUTTON_XPATH = "//button[@data-toggle='dropdown']";
    //------------------------------------------------------------------------------------------------------------------

    // region Новое многоуровневое меню

    // Общая часть локаторов нового многоуровневого меню
    private static final String MNU_CLS = "//li[@class='dropdown-submenu']";

    // region Создать извещение из плана

    //------------------------------------------------------------------------------------------------------------------
    // Пункт [Создать извещение из плана] в выпадающем меню кнопки [Создать извещение]
    private static final String CREATE_FROM_PLAN_XPATH = "//li[@class='']/a[contains(.,'Создать извещение из плана')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Создать извещения из шаблона

    //------------------------------------------------------------------------------------------------------------------
    // Пункт [Создать извещения из шаблона] в выпадающем меню кнопки [Создать извещение]
    private static final String CREATE_FROM_TEMPLATE_XPATH =
            MNU_CLS + "/a[contains(.,'Создание извещения из шаблона')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Конкурентная закупка по 223-ФЗ

    //------------------------------------------------------------------------------------------------------------------
    // Пункт [Конкурентная закупка по 223-ФЗ] в выпадающем меню кнопки [Создать извещение]
    private static final String COMPETITIVE_PURCHASE_GROUP_XPATH =
            MNU_CLS + "/a[contains(., 'Конкурентная закупка по 223-ФЗ')]";
    //------------------------------------------------------------------------------------------------------------------
    // Подпункт [Аукцион] в меню пункта [Конкурентная закупка по 223-ФЗ]
    private static final String COMPETITIVE_AUCTION_LINK_XPATH =
            MNU_CLS + "//a[contains(@href,'/customer/lk/App/trade/auction223/Competitive/create')]";
    //------------------------------------------------------------------------------------------------------------------
    // Подпункт [Аукцион (заявка в двух частях)] в меню пункта [Конкурентная закупка по 223-ФЗ]
    private static final String COMPETITIVE_AUCTION_TWO_PARTS_LINK_XPATH =
            MNU_CLS + "//a[contains(@href,'/customer/lk/App/trade/auctionTwoParts/Competitive/create')]";
    //------------------------------------------------------------------------------------------------------------------
    // Подпункт [Конкурс] в меню пункта [Конкурентная закупка по 223-ФЗ]
    private static final String COMPETITIVE_TENDER_LINK_XPATH =
            MNU_CLS + "//a[contains(@href,'/customer/lk/App/trade/tender223/Competitive/create') and " +
                    "not(contains(@href, 'TemplateId'))]";
    //------------------------------------------------------------------------------------------------------------------
    // Подпункт [Запрос предложений] в меню пункта [Конкурентная закупка по 223-ФЗ]
    private static final String COMPETITIVE_APPLICATION_REQUEST_LINK_XPATH =
            MNU_CLS + "//a[contains(@href,'/customer/lk/App/trade/applicationRequest/Competitive/create')]";
    //------------------------------------------------------------------------------------------------------------------
    // Подпункт [Запрос цен] в меню пункта [Конкурентная закупка по 223-ФЗ]
    private static final String COMPETITIVE_PRICE_REQUEST_LINK_XPATH =
            MNU_CLS + "//a[contains(@href,'/customer/lk/App/trade/priceRequest/Competitive/create')]";
    //------------------------------------------------------------------------------------------------------------------
    // Подпункт [Запрос котировок] в меню пункта [Конкурентная закупка по 223-ФЗ]
    private static final String COMPETITIVE_QUOTATIONS_REQUEST_LINK_XPATH =
            MNU_CLS + "//a[contains(@href,'/customer/lk/App/trade/quotationsRequest223/Competitive/create')]";
    //------------------------------------------------------------------------------------------------------------------
    // Подпункт [Конкурс (заявка в двух частях)] в меню пункта [Конкурентная закупка по 223-ФЗ]
    private static final String COMPETITIVE_TENDER_APPLICATION_IN_TWO_PARTS_LINK_XPATH =
            MNU_CLS + "//a[contains(@href,'/customer/lk/App/trade/tenderTwoParts/Competitive/create')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Закупка МСП по 223-ФЗ

    //------------------------------------------------------------------------------------------------------------------
    // Пункт [Закупка МСП по 223-ФЗ] в выпадающем меню кнопки [Создать извещение]
    // small and medium-sized businesses (SMBs)
    private static final String SMB_PURCHASE_GROUP_XPATH = MNU_CLS + "/a[contains(., 'Закупка МСП по 223-ФЗ')]";
    //------------------------------------------------------------------------------------------------------------------
    // Подпункт [Конкурс МСП по 223-ФЗ] в меню пункта [Закупка МСП по 223-ФЗ]
    private static final String SMB_TENDER_LINK_XPATH =
            MNU_CLS + "//a[contains(@href,'/customer/lk/App/trade/tenderSmb/Smb/create')]";
    //------------------------------------------------------------------------------------------------------------------
    // Подпункт [Аукцион МСП по 223-ФЗ] в меню пункта [Закупка МСП по 223-ФЗ]
    private static final String SMB_AUCTION_LINK_XPATH =
            MNU_CLS + "//a[contains(@href,'/customer/lk/App/trade/auction/create')]";
    //------------------------------------------------------------------------------------------------------------------
    // Подпункт [Запрос предложений МСП по 223-ФЗ] в меню пункта [Закупка МСП по 223-ФЗ]
    private static final String SMB_APPLICATION_REQUEST_LINK_XPATH =
            MNU_CLS + "//a[contains(@href,'/customer/lk/App/trade/applicationRequestSmb/Smb/create')]";
    //------------------------------------------------------------------------------------------------------------------
    // Подпункт [Запрос котировок МСП по 223-ФЗ] в меню пункта [Закупка МСП по 223-ФЗ]
    private static final String SMB_QUOTATIONS_REQUEST_LINK_XPATH =
            MNU_CLS + "//a[contains(@href,'/customer/lk/App/trade/quotationsRequest/create')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Закупка по 223-ФЗ

    //------------------------------------------------------------------------------------------------------------------
    // Пункт [Закупка по 223-ФЗ] в выпадающем меню кнопки [Создать извещение]
    private static final String FL_223_PURCHASE_GROUP_XPATH = MNU_CLS + "/a[contains(., 'Закупка по 223-ФЗ')]";
    //------------------------------------------------------------------------------------------------------------------
    // Подпункт [Аукцион] в меню пункта [Закупка по 223-ФЗ]
    private static final String FL_223_AUCTION_LINK_XPATH =
            MNU_CLS + "//a[contains(@href,'/customer/lk/App/trade/auction223/NotCompetitive/create') and " +
                    "not(contains(@href, 'TemplateId'))]";
    //------------------------------------------------------------------------------------------------------------------
    // Подпункт [Предворительный отбор (с продлением)] в меню пункта [Закупка по 223-ФЗ]
    private static final String FL_223_PRELIMINARY_SELECTION_LINK_XPATH =
            MNU_CLS + "//a[contains(@href,'/customer/lk/App/trade/eternalPrequalification/NotCompetitive/create')]";
    //------------------------------------------------------------------------------------------------------------------
    // Подпункт [Конкурс] в меню пункта [Закупка по 223-ФЗ]
    private static final String FL_223_TENDER_LINK_XPATH =
            MNU_CLS + "//a[contains(@href,'/customer/lk/App/trade/tender223/NotCompetitive/create')]";
    //------------------------------------------------------------------------------------------------------------------
    // Подпункт [Запрос предложений] в меню пункта [Закупка по 223-ФЗ] - Angular
    private static final String FL_223_APPLICATION_REQUEST_LINK_XPATH =
            MNU_CLS + "//a[contains(@href,'/customer/lk/App/trade/applicationRequest/NotCompetitive/create')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Коммерческая закупка

    //------------------------------------------------------------------------------------------------------------------
    // Пункт [Коммерческая закупка] в выпадающем меню кнопки [Создать извещение]
    private static final String COMMERCIAL_PURCHASE_GROUP_XPATH = MNU_CLS + "/a[contains(., 'Коммерческая закупка')]";
    //------------------------------------------------------------------------------------------------------------------
    // Подпункт [Аукцион] в меню пункта [Коммерческая закупка]
    private static final String COMMERCIAL_AUCTION_LINK_XPATH =
            MNU_CLS + "//a[contains(@href, '/customer/lk/App/trade/auction223/Commercial/create')]";
    //------------------------------------------------------------------------------------------------------------------
    // Подпункт [Аукцион (заявка в двух частях)] в меню пункта [Коммерческая закупка]
    private static final String COMMERCIAL_AUCTION_IN_TWO_PARTS_LINK_XPATH =
            MNU_CLS + "//a[contains(@href,'/customer/lk/App/trade/auctionTwoParts/Commercial/create')]";
    //------------------------------------------------------------------------------------------------------------------
    // Подпункт [Конкурс] в меню пункта [Коммерческая закупка]
    private static final String COMMERCIAL_TENDER_LINK_XPATH =
            MNU_CLS + "//a[contains(@href,'/customer/lk/App/trade/tender223/Commercial/create')]";
    //------------------------------------------------------------------------------------------------------------------
    // Подпункт [Запрос цен] в меню пункта [Коммерческая закупка]
    private static final String COMMERCIAL_PRICE_REQUEST_LINK_XPATH =
            MNU_CLS + "//a[contains(@href,'/customer/lk/App/trade/priceRequest/Commercial/create')]";
    //------------------------------------------------------------------------------------------------------------------
    // Подпункт [Запрос котировок] в меню пункта [Коммерческая закупка]
    private static final String COMMERCIAL_QUOTATIONS_REQUEST_LINK_XPATH =
            MNU_CLS + "//a[contains(@href,'/customer/lk/App/trade/quotationsRequest223/Commercial/create')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // endregion

    // endregion

    // region Блок закладок [ФИЛЬТРЫ ПО ВЛАДЕЛЬЦАМ ЗАКУПОК]

    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Мои Закупки]
    private static final String MY_PURCHASES_TAB_XPATH = "//*[@id='PurchaseTabs']//a[contains(.,'Мои Закупки')]";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Закупки для согласования]
    private static final String PURCHASES_FOR_APPROVE_XPATH =
            "//*[@id='PurchaseTabs']//a[contains(.,'Закупки для согласования')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Блок закладок [ФИЛЬТРЫ ПО СТАТУСАМ ЗАКУПОК]

    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Все] в результатах поиска
    private static final String ALL_STATUS_TAB_ID = "0";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Активна] в результатах поиска
    private static final String ACTIVE_STATUS_TAB_ID = "98";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Формирование] в результатах поиска
    private static final String FORMING_STATUS_TAB_ID = "100";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Публикация извещения] в результатах поиска
    private static final String PUBLISHING_STATUS_TAB_ID = "1";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Прием заявок] в результатах поиска
    private static final String RECEIVING_OF_APPLICATIONS_STATUS_TAB_ID = "2";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Определение победителей] в результатах поиска
    private static final String DETERMINATION_OF_WINNERS_STATUS_TAB_ID = "99";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Заключение договоров] в результатах поиска
    private static final String CONCLUSION_OF_CONTRACTS_STATUS_TAB_ID = "9";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Завершена] в результатах поиска
    private static final String COMPLETED_STATUS_TAB_ID = "10";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Завершена без заключения договоров] в результатах поиска
    private static final String COMPLETED_WITHOUT_CONTRACTS_STATUS_TAB_ID = "16";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Не состоялась] в результатах поиска
    private static final String DID_NOT_TAKE_PLACE_STATUS_TAB_ID = "6";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Приостановлена] в результатах поиска
    private static final String SUSPENDED_STATUS_TAB_ID = "11";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Отменена] в результатах поиска
    private static final String CANCELED_STATUS_TAB_ID = "4";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Блок переключения количества, сортировки и режимов отображения карточек закупок

    //------------------------------------------------------------------------------------------------------------------
    // Список режимов сортировки карточек в результатах поиска закупки:
    //..................................................................................................................
    // Список элементов фильтра [Сортировка] в закрытом виде
    private static final String SORTING_LIST_XPATH = "//*[@id='undefined_top']/div[1]/div/span[1]/span/span[2]";
    //..................................................................................................................
    // Универсальный локатор для элемента фильтра [Сортировка] в открытом виде
    private static final String SORTING_LIST_ELEMENT_XPATH = "//div/ul/li[contains(text(),'%s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Список отображаемого количества карточек в результатах поиска закупки:
    //..................................................................................................................
    // Список элементов фильтра [Отображаемое количество карточек] в закрытом виде
    private static final String NUMBER_OF_CARDS_LIST_XPATH = "//*[@id='undefined_top']/div[2]/span/span/span/span[2]";
    //..................................................................................................................
    // Список элементов фильтра [Отображаемое количество карточек] в закрытом виде (текущее значение)
    private static final String NUMBER_OF_CARDS_LIST_VALUE_XPATH =
            "//*[@id='undefined_top']/div[2]/span/span/span/span[1]";
    //..................................................................................................................
    // Универсальный локатор для элемента фильтра [Отображаемое количество карточек] в открытом виде
    private static final String NUMBER_OF_CARDS_LIST_ELEMENT_XPATH = "html/body/div/div/ul/li[contains(text(),'%s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка переключения отображения результатов поиска в режим [Карточки закупок]
    private static final String SWITCH_VIEW_TO_CARDS_BUTTON_XPATH = "//div[@class='filter-icon cards-look active']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Блок критериев поиска

    //------------------------------------------------------------------------------------------------------------------
    // Текстовое поле [Номер закупки]
    private static final String PURCHASE_NUMBER_ID = "Number";
    //------------------------------------------------------------------------------------------------------------------
    // Текстовое поле [Дата публикации] ( с )
    private static final String START_PRICE_FROM_XPATH = "//input[@id='StartPriceFrom']/preceding-sibling::input[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Текстовое поле [Дата публикации] ( по )
    private static final String START_PRICE_TO_XPATH = "//input[@id='StartPriceTo']/preceding-sibling::input[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Найти]
    private static final String FIND_BUTTON_ID = "SearchFilterButton";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Очистить]
    private static final String CLEAR_FILTER_BUTTON_ID = "ClearFilterButton";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Результаты поиска]

    //------------------------------------------------------------------------------------------------------------------
    // Ссылки в найденом аукционе:
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка с номером закупки
    private static final String PURCHASE_NUMBERS_LINKS_XPATH = "//a[@class='notice-number']";
    private static final String PURCHASE_NOTICE_NUMBER_LOT_NUMBER_LINK =
            "//a[contains(@class, 'notice-number') and text() = '%s']";
    //------------------------------------------------------------------------------------------------------------------
    // Статус закупки
    private static final String STATUS_AUCTION_LINK =
            "//*[@id='NotificationGrid']//table//td[2]//a[contains(., '%s')]/../../..//td[1]/" +
                    "span[@class='a term-y' or @class='a term-g' or @class='a term-r']";
    private static final String STATUS_AUCTION_A_LINK =
            "//*[@id='NotificationGrid']//table//td[2]//a[contains(., '%s')]/../../..//td[1]/a";
    private static final String PURCHASE_STATUS_XPATH =
            "//label[contains(.,'Статус закупки:')]/following-sibling::div[1]";
    private static final String PURCHASE_NOTICE_LOT_STATUS_BY_NUMBER =
            "//a[contains(@class, 'notice-number') and text() = '%s']/following-sibling::span[1]";
    private static final String LOT_STATUS_BY_NO_XPATH = "//a[contains(.,'%s')]/following-sibling::span[@class='status']";
    //------------------------------------------------------------------------------------------------------------------
    // Статус закупки
    private static final String PROCEDURE_STATUS_XPATH =
            "//label[contains(.,'Статус процедуры:')]/following-sibling::div[1]";
    private static final String PRESELECTION_OBJECT_STATUS_BY_NO_XPATH =
            "//a[contains(.,'%s')]/following-sibling::span[@class='status']";
    //------------------------------------------------------------------------------------------------------------------
    // Начальная цена (карточка закупки, большие жирные цифры)
    private static final String START_PRICE_VALUES_XPATH = "(//div[contains(@class, 'price')])";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [Заявок] (карточка закупки, ссылка с количеством поданных заявок)
    private static final String APPLICATIONS_XPATH = "//*[@id='NotificationGrid']//a[@class='inactive cursor-pointer']";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок [Сведения о протоколах]
    private static final String ABOUT_PROTOCOLS_HEADER_XPATH = "//h2[contains(., 'Сведения о протоколах')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Открыть форму протокола открытия доступа]
    private static final String OPEN_FORM_WS_OPENING_ACCESS_PROTOCOL_BUTTON_XPATH =
            "//button[contains(., 'Открыть форму протокола открытия доступа')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Открыть форму протокола рассмотрения первых частей заявок] FirstPartsConsiderationProtoco
    private static final String OPEN_FORM_WS_CONSIDERATION_PROTOCOL_BUTTON_XPATH =
            "//button[contains(., 'Открыть форму протокола рассмотрения заявок')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Открыть форму протокола рассмотрения первых частей заявок] FirstPartsConsiderationProtoco
    private static final String OPEN_FORM_WS_FIRST_PARTS_CONSIDERATION_PROTOCOL_BUTTON_XPATH =
            "//button[contains(., 'Открыть форму протокола рассмотрения первых частей заявок')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылки на все доступные для скачивания файлы на странице [Просмотр извещения xxxx]
    private static final String ALL_DOWNLOADABLE_FILES_XPATH =
            "//a[contains(@href, '/FileDownloadHandler.ashx?fileguid=') and not(contains(., '.txt'))]";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок [Документы]
    private static final String DOCUMENTS_HEADER_XPATH = "//h2[contains(., 'Документы')]";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о лотах] -> [Лот №X] (открыть детали лота)
    private static final String LOT_HEADERS_XPATH = "//h2[contains(., 'Лот №')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final OpenAccessCustomerProtocolPage openAccessCustomerProtocolPage = new OpenAccessCustomerProtocolPage(driver);
    private final ConsiderationRequestCustomerProtocolPage considerationRequestCustomerProtocolPage =
            new ConsiderationRequestCustomerProtocolPage(driver);
    private final CreateNoticePage createNoticePage = new CreateNoticePage(driver);
    private final ViewNoticePage viewNoticePage = new ViewNoticePage(driver);
    private final TradeCancelDialog tradeCancelDialog = new TradeCancelDialog(driver);
    private final InformationDialog informationDialog = new InformationDialog(driver);
    //------------------------------------------------------------------------------------------------------------------
    private final Dictionary lotStatuses = new Dictionary();
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CustomerMyPurchasesPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        lotStatuses.add("Рассмотрите вторые части заявок", STATUS_AUCTION_A_LINK);
        lotStatuses.add("Подведите итоги переторжки", STATUS_AUCTION_A_LINK);
        lotStatuses.add("Подведите итоги обсуждения", STATUS_AUCTION_A_LINK);
        lotStatuses.add("Откройте доступ к заявкам", STATUS_AUCTION_A_LINK);
        lotStatuses.add("Опубликуйте разъяснение", STATUS_AUCTION_A_LINK);
        lotStatuses.add("Подведите итоги торгов", STATUS_AUCTION_A_LINK);
        lotStatuses.add("Перенаправьте договор", STATUS_AUCTION_A_LINK);
        lotStatuses.add("Согласуйте протокол", STATUS_AUCTION_A_LINK);
        lotStatuses.add("Рассмотрите заявки", STATUS_AUCTION_A_LINK);
        lotStatuses.add("Направьте договор", STATUS_AUCTION_LINK);
        lotStatuses.add("Подпишите договор", STATUS_AUCTION_A_LINK);
        lotStatuses.add("Внесите изменения", STATUS_AUCTION_A_LINK);
        lotStatuses.add("Подведите итоги", STATUS_AUCTION_A_LINK);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет, что открыта именно страница [Мои закупки].
     *
     * @return страница [Мои закупки]
     */
    public CustomerMyPurchasesPage ifPageOpened() throws Exception {
        this.checkPageUrl("Мои закупки");

        return this;
    }

    /**
     * Переключает на закладку [Мои Закупки].
     */
    public void selectMyPurchasesTab() throws Exception {
        $(By.xpath(MY_PURCHASES_TAB_XPATH)).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
        System.out.println("[ИНФОРМАЦИЯ]: произведено переключение на закладку [Мои Закупки].");
    }

    /**
     * Переключает на закладку [Закупки для согласования].
     */
    public void selectPurchasesForApproveTab() throws Exception {
        $(By.xpath(PURCHASES_FOR_APPROVE_XPATH)).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
        System.out.println("[ИНФОРМАЦИЯ]: произведено переключение на закладку [Закупки для согласования].");
    }

    /**
     * Переключает отображение результатов поиска в режим [Карточки закупок].
     */
    public CustomerMyPurchasesPage switchViewToCards() throws Exception {
        if (url().contains("customer/lk/Auctions/Table")) {
            System.out.println("[ИНФОРМАЦИЯ]: обнаружен режим отображения закупок в виде таблицы.");
            $(By.xpath(SWITCH_VIEW_TO_CARDS_BUTTON_XPATH)).waitUntil(clickable, timeout, polling).click();
            this.waitLoadingImage();
            System.out.println("[ИНФОРМАЦИЯ]: установлен режим отображения закупок в виде карточек.");
        } else {
            System.out.println("[ИНФОРМАЦИЯ]: обнаружен режим отображения закупок в виде карточек.");
        }

        return this;
    }

    /**
     * Изменяет рехим сортировки результатов поиска.
     *
     * @param mode рехим сортировки
     */
    public CustomerMyPurchasesPage selectSortingMode(String mode) throws Exception {
        $(By.xpath(SORTING_LIST_XPATH)).waitUntil(visible, timeout, polling);
        this.openListOfSortingJS();
        $(By.xpath(String.format(SORTING_LIST_ELEMENT_XPATH, mode))).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
        System.out.printf("[ИНФОРМАЦИЯ]: произведена сортировка: [%s].%n", mode);

        return this;
    }

    /**
     * Изменяет количество отображаемых карточек закупки в результатах поиска.
     *
     * @param number количество отображаемых карточек закупки
     */
    private void selectNumberOfPurchaseCardsOnPage(String number) throws Exception {
        $(By.xpath(NUMBER_OF_CARDS_LIST_XPATH)).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(
                "[ИНФОРМАЦИЯ]: Произведено открытие списка [Количество отображаемых карточек закупки] c помощью JS.");
        String listElement = String.format(NUMBER_OF_CARDS_LIST_ELEMENT_XPATH, number);
        $(By.xpath(listElement)).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
        System.out.printf("[ИНФОРМАЦИЯ]: количество отображаемых карточек закупки: [%s].%n",
                $(By.xpath(NUMBER_OF_CARDS_LIST_VALUE_XPATH)).waitUntil(visible, timeout, polling).getText());
    }

    /**
     * Устанавливаем значение в поле [Номер закупки].
     *
     * @param purchaseNumber номер закупки
     */
    public CustomerMyPurchasesPage setPurchaseNumber(String purchaseNumber) {
        SelenideElement purchaseNumberField = $(By.id(PURCHASE_NUMBER_ID));
        purchaseNumberField.waitUntil(clickable, longtime, polling).clear();
        purchaseNumberField.val(purchaseNumber).click();
        Assert.assertEquals("[ОШИБКА]: поле: [Номер закупки] содержит не правильное значение",
                purchaseNumber, purchaseNumberField.getValue());
        System.out.printf("[ИНФОРМАЦИЯ]: в поле [Номер закупки] установлено значение [%s].%n", purchaseNumber);

        return this;
    }

    /**
     * Делает щелчок по ссылке со статусом для лота с указанным порядковым номером в закупке (цветной треугольник).
     *
     * @param lotStatus статус лота
     * @param lotNumber порядковый номер лота
     */
    public void clickOnLotStatusTriangleByNumber(String lotStatus, String lotNumber) throws Exception {
        long nTries = 15;
        long i = 1;

        System.out.printf("[ИНФОРМАЦИЯ]: ищем ссылку: [%s] для лота №: [%s].%n", lotStatus, lotNumber);
        String purchaseNumberWithLotNumber = config.getParameter("PurchaseNumber") + "/" + lotNumber;
        String xpath = String.format(lotStatuses.find(lotStatus), purchaseNumberWithLotNumber);
        ElementsCollection links;

        // Если ссылка ещё не отображается - входим в цикл ожидания её появления на форме
        do {
            System.out.printf("[ИНФОРМАЦИЯ]: %s проверка № %d наличия ссылки: [%s] для лота №: [%s].%n",
                    timer.getCurrentDateTime("dd.MM.yyyy HH:mm:ss"), i, lotStatus, lotNumber);
            this.waitForPageLoad();             // здесь ждем очередной загрузки страницы
            links = $$(By.xpath(xpath));        // получаем коллекцию ссылок на странице
            if (links.size() > 0) break;        // если не пустая - порядок, выходим из цикла ожидания
            TimeUnit.SECONDS.sleep(30);  // если пустая - выдерживаем паузу
            refresh();                          // обновляем страницу
            i++;                                // увеличиваем счетчик попыток
        } while (i <= nTries && links.size() == 0);

        System.out.printf("[ИНФОРМАЦИЯ]: количество найденных ссылок по локатору: [%s] - [%d].%n", xpath, links.size());
        Assert.assertNotEquals("[ОШИБКА]: не найдено ни одной ссылки (треугольника)", 0, links.size());
        Assert.assertEquals("[ОШИБКА]: найдено более одной ссылки (треугольников)", 1, links.size());
        System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие на ссылку: [%s] для лота №: [%s].%n", lotStatus, lotNumber);
        links.get(0).waitUntil(clickable, timeout, polling).click();
        this.waitForPageLoad(); // здесь ждем пока загрузится страница, на которую осуществлен переход по ссылке
    }

    /**
     * Переходит по ссылке с номером извещения и проверяет, что открыта страница [Просмотр извещения XXXX].
     */
    public void clickOnPurchaseNumberLinkToViewMode() throws Exception {
        $(By.xpath(PURCHASE_NUMBERS_LINKS_XPATH)).waitUntil(clickable, longtime, polling).click();
        this.checkPageUrl("Просмотр извещения");
    }

    /**
     * Переходит по ссылке с номером извещения и проверяет, что открыта страница [Формирование черновика извещения XXXX].
     */
    public void clickOnPurchaseNumberLinkToEditMode() throws Exception {
        $(By.xpath(PURCHASE_NUMBERS_LINKS_XPATH)).waitUntil(clickable, longtime, polling).click();
        this.checkPageUrl(Arrays.asList(
                "Формирование черновика извещения",
                "Формирование черновика извещения аукциона - Angular",
                "Формирование черновика извещения конкурса - Angular",
                "Формирование черновика извещения запроса котировок - Angular",
                "Формирование черновика извещения конкурса (заявка в двух частях) - Angular",
                "Формирование черновика извещения аукциона МСП от РЖД",
                "Формирование черновика извещения аукциона МСП от РЖД - Angular",
                "Формирование черновика извещения конкурса МСП от РЖД",
                "Формирование черновика извещения конкурса МСП от РЖД - Angular",
                "Формирование черновика извещения запроса котировок МСП от РЖД",
                "Формирование черновика извещения запроса котировок МСП от РЖД - Angular",
                "Формирование черновика извещения запроса предложений МСП от РЖД",
                "Формирование черновика извещения запроса предложений МСП от РЖД - Angular"
        ));
    }

    /**
     * Проверяет, что извещение о закупке с указанным номером не найдено на площадке.
     */
    public void checkThatPurchaseNoticeNotFound() {
        ElementsCollection purchaseNumbersLinks = $$(By.xpath(PURCHASE_NUMBERS_LINKS_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: найдено: [%d] извещений о закупке с номером: [%s].%n",
                purchaseNumbersLinks.size(), config.getParameter("PurchaseNumber"));
        purchaseNumbersLinks.shouldHaveSize(0);
    }

    /**
     * Делает щелчок по ссылке с количеством заявок в поле [Заявок X].
     *
     * @param lotNumber порядковый номер лота в карточке закупки
     */
    public void clickOnApplicationsLinkUsingLotNumber(String lotNumber) throws Exception {
        ElementsCollection applications = $$(By.xpath(APPLICATIONS_XPATH));
        int count = applications.size();
        Assert.assertTrue("[ОШИБКА]: ссылки [Заявок: X] отсутствуют в карточках закупки", count > 0);
        System.out.printf("[ИНФОРМАЦИЯ]: общее количество обнаруженных ссылок: '%d'.%n", count);
        int lot = Integer.parseInt(lotNumber) - 1;
        Assert.assertTrue("[ОШИБКА]: переданный номер ссылки вне диапазона", lot >= 0 && lot < count);
        System.out.printf("[ИНФОРМАЦИЯ]: переходим по ссылке для лота № '%s' с текстом: '%s'.%n",
                lotNumber, applications.get(lot).getText());
        applications.get(lot).click();

        // Открылось новое диалоговое окно [Ценовые предложения]
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * Делает щелчок по закладке c указанным статусом закупки.
     *
     * @param tabName статус закупки
     */
    public void goToStatusTabByTabName(String tabName) throws Exception {
        Dictionary tabNames = new Dictionary();
        tabNames.add("Все", ALL_STATUS_TAB_ID);
        tabNames.add("Активна", ACTIVE_STATUS_TAB_ID);
        tabNames.add("Формирование", FORMING_STATUS_TAB_ID);
        tabNames.add("Публикация извещения", PUBLISHING_STATUS_TAB_ID);
        tabNames.add("Прием заявок", RECEIVING_OF_APPLICATIONS_STATUS_TAB_ID);
        tabNames.add("Определение победителей", DETERMINATION_OF_WINNERS_STATUS_TAB_ID);
        tabNames.add("Заключение договоров", CONCLUSION_OF_CONTRACTS_STATUS_TAB_ID);
        tabNames.add("Завершена", COMPLETED_STATUS_TAB_ID);
        tabNames.add("Завершена без заключения договоров", COMPLETED_WITHOUT_CONTRACTS_STATUS_TAB_ID);
        tabNames.add("Не состоялась", DID_NOT_TAKE_PLACE_STATUS_TAB_ID);
        tabNames.add("Приостановлена", SUSPENDED_STATUS_TAB_ID);
        tabNames.add("Отменена", CANCELED_STATUS_TAB_ID);

        SelenideElement tab = $(this.getBy(tabNames.find(tabName)));
        tab.waitUntil(visible, timeout, polling);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие на закладку [%s].%n", tabName);
        this.clickInElementJS(tab);
        this.waitLoadingImage();
    }

    /**
     * Делает щелчок по кнопке [Найти].
     */
    public void clickFindButton() throws Exception {
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        this.logButtonNameToPress("Найти");
        $(By.id(FIND_BUTTON_ID)).waitUntil(clickable, longtime, polling).click();
        this.logPressedButtonName("Найти");
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        this.waitLoadingImage();
        $(By.id(FIND_BUTTON_ID)).waitUntil(clickable, timeout, polling);
    }

    /**
     * Делает щелчок по кнопке [Очистить].
     */
    public void clickClearFilterButton() throws Exception {
        this.logButtonNameToPress("Очистить");
        $(By.id(CLEAR_FILTER_BUTTON_ID)).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage(3);
        this.logPressedButtonName("Очистить");
    }

    /**
     * Проверяет возможность скачать прикрепленные к извещению документы по прямым ссылкам в извещении.
     *
     * @param quantity ожидаемое количество ссылок в извещении
     */
    public void checkPossibilityToDownloadAttachedToNoticeDocumentsUsingLinks(int quantity) throws Exception {
        long minFileLength = config.getMinDownloadedFileLength();

        // Перемещаем страницу к требуемому разделу
        $(By.xpath(DOCUMENTS_HEADER_XPATH)).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(1);

        // Получаем общее количество ссылок на прикрепленные документы
        int docs = $$(By.xpath(ALL_DOWNLOADABLE_FILES_XPATH)).size();
        int headers = $$(By.xpath(LOT_HEADERS_XPATH)).size();
        Assert.assertTrue("[ОШИБКА]: извещение не содержит ссылок на прикрепленные документы", docs > 0);
        System.out.printf("[ИНФОРМАЦИЯ]: Извещение содержит [%d] ссылок на прикрепленные документы.%n",
                docs);
        Assert.assertEquals("[ОШИБКА]: извещение содержит некорректное число ссылок", quantity, docs);

        // Проверяем возможность скачать прикрепленные к извещению документы по прямым ссылкам в извещении
        for (int i = 1; i < headers; i++) $$(By.xpath(LOT_HEADERS_XPATH)).get(i).click();
        for (int i = 0; i < docs; i++) {
            String documentName = $$(By.xpath(ALL_DOWNLOADABLE_FILES_XPATH)).get(i).getText();
            System.out.printf("[ИНФОРМАЦИЯ]: проверяем ссылку на прикрепленный документ: [%s].%n",
                    documentName);
            System.out.println("[ИНФОРМАЦИЯ]: делаем щелчок по ссылке...");
            $$(By.xpath(ALL_DOWNLOADABLE_FILES_XPATH)).get(i).click();
            TimeUnit.SECONDS.sleep(10);
            System.out.println("[ИНФОРМАЦИЯ]: проверяем, что документ загружен успешно...");
            boolean fileExists = false;

            // Делаем несколько проверок на то, что документ загружен успешно с интервалом в 10 секунд
            for (int j = 0; j < 15; j++) {
                File file = new File(config.getPathToTempFolderWithRandomName() + "\\" + documentName);
                if (file.exists() && file.length() >= minFileLength) {
                    fileExists = true;
                    break;
                }
                TimeUnit.SECONDS.sleep(10);
            }

            Assert.assertTrue("[ОШИБКА]: не удалось загрузить на диск документ", fileExists);
            System.out.println("[ИНФОРМАЦИЯ]: документ загружен успешно.");
        }

        // Удаляем загруженные документы вместе с временным каталогом
        System.out.println("[ИНФОРМАЦИЯ]: удаляем загруженные документы вместе с временным каталогом.");
        File folderToDelete = new File(config.getPathToTempFolderWithRandomName());
        this.deleteTemporaryFolderWithFiles(folderToDelete);
    }

    /**
     * Проверяет возможность скачать прикрепленные к [Протоколу открытия доступа конкурса] заявки
     * (из раздела [Сведения протокола открытия доступа]).
     */
    public void checkPossibilityToDownloadRequestsFromOpenAccessProtocolPage() throws Exception {
        // Перемещаем страницу к требуемому разделу
        SelenideElement header = $(By.xpath(ABOUT_PROTOCOLS_HEADER_XPATH));
        this.scrollToCenter(header);
        header.waitUntil(clickable, timeout, polling).click();

        // Делаем щелчок по кнопке [Открыть форму протокола открытия доступа]
        $(By.xpath(OPEN_FORM_WS_OPENING_ACCESS_PROTOCOL_BUTTON_XPATH)).waitUntil(clickable, timeout, polling).click();
        this.checkPageUrl("Один из протоколов");

        // В открывшейся форме [Протокол открытия доступа конкурса] перемещаемся к
        // разделу [Формирование протокола из шаблона]
        openAccessCustomerProtocolPage.clickOnMainPageHeader();
        openAccessCustomerProtocolPage.moveToFormingProtocolFromTemplateHeader();

        // Проверяем возможность скачать прикрепленные к протоколу заявки в
        // разделе [Сведения протокола открытия доступа] -> кнопка [Скачать заявки]
        openAccessCustomerProtocolPage.checkPossibilityToDownloadRequestsFromOpenAccessProtocolPage();

        // И возвращаемся на предыдущую страницу для корректного продолжения сценария
        this.goBackToPreviousPage();
        this.checkPageUrl("Просмотр извещения");
    }

    /**
     * Проверяет возможность скачать прикрепленные к [Протоколу рассмотрения первых частей заявок аукциона] заявки
     * (из раздела [Сведения протокола рассмотрения первых частей заявок по лоту 1]).
     */
    public void checkPossibilityToDownloadRequestsFromFirstPartsConsiderationProtocolPage() throws Exception {
        // Перемещаем страницу к требуемому разделу
        $(By.xpath(ABOUT_PROTOCOLS_HEADER_XPATH)).waitUntil(clickable, timeout, polling).click();

        // Делаем щелчок по кнопке [Открыть форму протокола рассмотрения первых частей заявок]
        $(By.xpath(OPEN_FORM_WS_FIRST_PARTS_CONSIDERATION_PROTOCOL_BUTTON_XPATH)).
                waitUntil(clickable, timeout, polling).click();
        this.checkPageUrl("Один из протоколов");

        // В открывшейся форме [Протокол рассмотрения первых частей заявок аукциона] перемещаемся к
        // разделу [Формирование протокола из шаблона]
        considerationRequestCustomerProtocolPage.clickOnMainPageHeader();
        considerationRequestCustomerProtocolPage.moveToFormingProtocolFromTemplateHeader();

        // Проверяем возможность скачать прикрепленные к протоколу заявки в
        // разделе [Сведения протокола открытия доступа] -> кнопка [Скачать заявки]
        considerationRequestCustomerProtocolPage.checkPossibilityToDownloadRequestsFromOpenAccessProtocolPage();

        // И возвращаемся на предыдущую страницу для корректного продолжения сценария
        this.goBackToPreviousPage();
        this.checkPageUrl("Просмотр извещения");
    }

    /**
     * Проверяет возможность скачать прикрепленные к [Протоколу рассмотрения заявок] заявки
     * (из раздела [Сведения протокола рассмотрения заявок]) для каждого из лотов закупки.
     *
     * @param lots количество лотов в закупке
     */
    public void checkPossibilityToDownloadRequestsFromConsiderationProtocolPage(String lots) throws Exception {
        // Получаем количество лотов
        int totalLots = Integer.parseInt(lots);

        // Цикл по каждому лоту в извещении о закупке (все лоты на форме РАЗВЁРНУТЫ) ===================================
        for (int lotNumber = 0; lotNumber < totalLots; lotNumber++) {
            $$(By.xpath(ABOUT_PROTOCOLS_HEADER_XPATH)).get(lotNumber).waitUntil(clickable, timeout, polling).click();

            // Делаем щелчок по кнопке [Открыть форму протокола рассмотрения первых частей заявок]
            $$(By.xpath(OPEN_FORM_WS_CONSIDERATION_PROTOCOL_BUTTON_XPATH)).get(lotNumber).
                    waitUntil(clickable, timeout, polling).click();
            this.checkPageUrl("Один из протоколов");

            // В открывшейся форме [Протокол рассмотрения первых частей заявок аукциона] перемещаемся к
            // разделу [Формирование протокола из шаблона]
            considerationRequestCustomerProtocolPage.clickOnMainPageHeader();
            considerationRequestCustomerProtocolPage.moveToFormingProtocolFromTemplateHeaderByIndex("1");

            // Проверяем возможность скачать прикрепленные к протоколу заявки в
            // разделе [Сведения протокола открытия доступа] -> кнопка [Скачать заявки]
            considerationRequestCustomerProtocolPage.
                    checkPossibilityToDownloadRequestsFromProtocolConsiderationRequest("1");

            // И возвращаемся на предыдущую страницу [Просмотр извещения] для корректного продолжения сценария
            this.goBackToPreviousPage();
            this.checkPageUrl("Просмотр извещения");

            // Нужно раскрыть все лоты на форме начиная со второго (первый открыт) >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            for (int lot = 1; lot < totalLots; lot++) {
                System.out.printf("[ИНФОРМАЦИЯ]: раскрываем лот № [%d].%n", lot + 1);
                $$(By.xpath(LOT_HEADERS_XPATH)).get(lot).click();
                TimeUnit.SECONDS.sleep(1);
            }
        }
        //==============================================================================================================
    }

    /**
     * Проверяет статус закупки.
     *
     * @param expectedStatus статус закупки
     */
    public void checkPurchaseStatus(String expectedStatus) {
        System.out.println("[ИНФОРМАЦИЯ]: проверка статуса закупки [" + expectedStatus + "].");
        $(By.xpath(PURCHASE_STATUS_XPATH)).waitUntil(visible, timeout, polling).
                shouldHave(Condition.text(expectedStatus));
    }

    /**
     * Проверяет статус процедуры.
     *
     * @param expectedStatus статус процедуры
     */
    public void checkProcedureStatus(String expectedStatus) {
        System.out.println("[ИНФОРМАЦИЯ]: проверка статуса процедуры [" + expectedStatus + "].");
        $(By.xpath(PROCEDURE_STATUS_XPATH)).waitUntil(visible, timeout, polling).
                shouldHave(Condition.text(expectedStatus));
    }

    /**
     * Проверяет статус лота.
     *
     * @param expectedStatus статус лота
     */
    public void checkLotStatus(String expectedStatus) throws Exception {
        this.checkLotStatus(expectedStatus, "1");
    }

    /**
     * Проверяет статус лота с указанным номером.
     *
     * @param expectedStatus статус лота
     * @param lotNumber      номер лота
     */
    public void checkLotStatus(String expectedStatus, String lotNumber) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: проверка лота с номером: [%s], ожидаемый статус: [%s].%n",
                lotNumber, expectedStatus);
        String xpath =
                String.format(LOT_STATUS_BY_NO_XPATH, config.getParameter("PurchaseNumber") + "/" + lotNumber);
        System.out.printf("[ИНФОРМАЦИЯ]: сформирован локатор для поиска лота по номеру: [%s].%n", xpath);
        this.waitForTextInFieldWithSearch(expectedStatus, By.xpath(xpath), "Статус лота",
                config.getParameter("PurchaseNumber"), By.id(PURCHASE_NUMBER_ID), By.id(FIND_BUTTON_ID));
    }

    /**
     * Проверяет статус предмета предварительного отбора с указанным номером.
     *
     * @param expectedStatus           статус лота
     * @param preselectionObjectNumber номер лота
     */
    public void checkPreselectionObjectStatus(String expectedStatus, String preselectionObjectNumber) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: проверка предмета предварительного отбора с номером: [%s], ожидаемый статус: [%s].%n",
                preselectionObjectNumber, expectedStatus);
        String xpath =
                String.format(PRESELECTION_OBJECT_STATUS_BY_NO_XPATH, config.getParameter("PurchaseNumber") + "/" + preselectionObjectNumber);
        System.out.printf("[ИНФОРМАЦИЯ]: сформирован локатор для поиска предмета предварительного отбора по номеру: [%s].%n", xpath);
        this.waitForTextInFieldWithSearch(expectedStatus, By.xpath(xpath), "Статус предмета предварительного отбора",
                config.getParameter("PurchaseNumber"), By.id(PURCHASE_NUMBER_ID), By.id(FIND_BUTTON_ID));
    }

    /**
     * Создает новый черновик извещения по указанному способу закупки из раздела [Конкурентная закупка по 223-ФЗ].
     *
     * @param purchaseMethod способ закупки
     */
    public void openCreateCompetitivePurchaseNoticePage(String purchaseMethod) throws Exception {
        Dictionary noticeTypes = new Dictionary();
        noticeTypes.add("Аукцион", COMPETITIVE_AUCTION_LINK_XPATH);
        noticeTypes.add("Аукцион (заявка в двух частях)", COMPETITIVE_AUCTION_TWO_PARTS_LINK_XPATH);
        noticeTypes.add("Конкурс", COMPETITIVE_TENDER_LINK_XPATH);
        noticeTypes.add("Запрос котировок", COMPETITIVE_QUOTATIONS_REQUEST_LINK_XPATH);
        noticeTypes.add("Конкурс (заявка в двух частях)", COMPETITIVE_TENDER_APPLICATION_IN_TWO_PARTS_LINK_XPATH);
        noticeTypes.add("Запрос предложений", COMPETITIVE_APPLICATION_REQUEST_LINK_XPATH);
        noticeTypes.add("Запрос цен", COMPETITIVE_PRICE_REQUEST_LINK_XPATH);

        $(By.xpath(CREATE_NOTICE_BUTTON_XPATH)).waitUntil(clickable, timeout, polling).click();
        $(By.xpath(COMPETITIVE_PURCHASE_GROUP_XPATH)).waitUntil(visible, timeout, polling).hover();
        $(By.xpath(noticeTypes.find(purchaseMethod))).waitUntil(clickable, timeout, polling).click();
        this.waitForPageLoad();
    }

    /**
     * Создает новый черновик извещения по указанному способу закупки из раздела [Закупка МСП по 223-ФЗ].
     *
     * @param purchaseMethod способ закупки
     */
    public void openCreateSmallAndMediumSizedBusinessesPurchaseNoticePage(String purchaseMethod) throws Exception {
        Dictionary noticeTypes = new Dictionary();
        noticeTypes.add("Конкурс МСП по 223-ФЗ", SMB_TENDER_LINK_XPATH);
        noticeTypes.add("Аукцион МСП по 223-ФЗ", SMB_AUCTION_LINK_XPATH);
        noticeTypes.add("Запрос предложений МСП по 223-ФЗ", SMB_APPLICATION_REQUEST_LINK_XPATH);
        noticeTypes.add("Запрос котировок МСП по 223-ФЗ", SMB_QUOTATIONS_REQUEST_LINK_XPATH);

        $(By.xpath(CREATE_NOTICE_BUTTON_XPATH)).waitUntil(clickable, timeout, polling).click();
        $(By.xpath(SMB_PURCHASE_GROUP_XPATH)).waitUntil(visible, timeout, polling).hover();
        $(By.xpath(noticeTypes.find(purchaseMethod))).waitUntil(clickable, timeout, polling).click();
        this.waitForPageLoad();
    }

    /**
     * Создает новый черновик извещения по указанному способу закупки из раздела [Закупка по 223-ФЗ].
     *
     * @param purchaseMethod способ закупки
     */
    public void openCreateNotCompetitivePurchaseNoticePage(String purchaseMethod) throws Exception {
        Dictionary noticeTypes = new Dictionary();
        noticeTypes.add("Аукцион", FL_223_AUCTION_LINK_XPATH);
        noticeTypes.add("Конкурс", FL_223_TENDER_LINK_XPATH);
        noticeTypes.add("Предварительный отбор (с продлением)", FL_223_PRELIMINARY_SELECTION_LINK_XPATH);
        noticeTypes.add("Запрос предложений", FL_223_APPLICATION_REQUEST_LINK_XPATH);
        $(By.xpath(CREATE_NOTICE_BUTTON_XPATH)).waitUntil(clickable, timeout, polling).click();
        $(By.xpath(FL_223_PURCHASE_GROUP_XPATH)).waitUntil(visible, timeout, polling).hover();
        $(By.xpath(noticeTypes.find(purchaseMethod))).waitUntil(clickable, timeout, polling).click();
        this.waitForPageLoad();
    }

    /**
     * Создает новый черновик извещения по указанному способу закупки из раздела [Коммерческая закупка].
     *
     * @param purchaseMethod способ закупки
     */
    public void openCreateCommercialPurchaseNoticePage(String purchaseMethod) throws Exception {
        Dictionary noticeTypes = new Dictionary();
        noticeTypes.add("Аукцион", COMMERCIAL_AUCTION_LINK_XPATH);
        noticeTypes.add("Аукцион (заявка в двух частях)", COMMERCIAL_AUCTION_IN_TWO_PARTS_LINK_XPATH);
        noticeTypes.add("Конкурс", COMMERCIAL_TENDER_LINK_XPATH);
        noticeTypes.add("Запрос цен", COMMERCIAL_PRICE_REQUEST_LINK_XPATH);
        noticeTypes.add("Запрос котировок", COMMERCIAL_QUOTATIONS_REQUEST_LINK_XPATH);

        $(By.xpath(CREATE_NOTICE_BUTTON_XPATH)).waitUntil(clickable, longtime, polling).click();
        $(By.xpath(COMMERCIAL_PURCHASE_GROUP_XPATH)).waitUntil(visible, timeout, polling).hover();
        $(By.xpath(noticeTypes.find(purchaseMethod))).waitUntil(clickable, timeout, polling).click();
        this.waitForPageLoad();
    }

    /**
     * Ожидает появления ссылки на лот с указанным номером в результатах поиска извещения о закупке.
     *
     * @param noticeNumberWithLotNumber номер извещения о закупке/номер лота
     */
    public void waitForNoticeNumberWithLotNumber(String noticeNumberWithLotNumber) {
        String xpath = String.format(PURCHASE_NOTICE_NUMBER_LOT_NUMBER_LINK, noticeNumberWithLotNumber);
        System.out.printf("[ИНФОРМАЦИЯ]: ожидаем появления ссылки с текстом [%s] в результатах поиска" +
                " извещения о закупке.%n", noticeNumberWithLotNumber);
        $(By.xpath(xpath)).waitUntil(clickable, longtime, polling);
    }

    /**
     * Проверяет статус лота с указанным номером в извещении о закупке из результатов поиска.
     *
     * @param noticeNumberWithLotNumber номер извещения о закупке/номер лота
     * @param lotStatus                 ожидаемый статус лота
     */
    public void checkLotStatusByNoticeNumber(String noticeNumberWithLotNumber, String lotStatus) {
        String xpath = String.format(PURCHASE_NOTICE_LOT_STATUS_BY_NUMBER, noticeNumberWithLotNumber);
        Assert.assertEquals("Статус лота №" + noticeNumberWithLotNumber + " не соответствует ожидаемому.",
                lotStatus, $(By.xpath(xpath)).waitUntil(visible, timeout, polling).getText());
    }

    /**
     * Открывает список элементов [Сортировка] с помощью JS.
     */
    private void openListOfSortingJS() {
        SelenideElement element = $(By.xpath(SORTING_LIST_XPATH));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        System.out.println("[ИНФОРМАЦИЯ]: произведено открытие списка [Сортировка] c помощью JS.");
    }

    /**
     * Проверяет работу фильтров для этой страницы.
     */
    public void checkPageFilters() throws Exception {

        // Запоминаем общее количество всех найденных закупок (фильтр сброшен):
        String totalUnfiltered = this.getSearchResultsCounter();

        // Если количество закупок не 0 то получаем номер закупки из файла конфигурации как критерий для поиска
        Assert.assertNotEquals("[ОШИБКА]: ни одной закупки не найдено", "0", totalUnfiltered);
        String searchCriteria = config.getConfigParameter("OnProdPurchaseNumberForCheckFilters");
        System.out.printf("[ИНФОРМАЦИЯ]: поиск по номеру закупки: [%s].%n", searchCriteria);

        // Ищем закупку и анализируем результаты поиска
        this.setPurchaseNumber(searchCriteria);
        this.clickFindButton();
        this.checkTheSameValuesInSearchResults(PURCHASE_NUMBERS_LINKS_XPATH, searchCriteria);

        // Ищем по не существующему номеру закупки ( не должно быть найдено ни одной закупки )
        System.out.println("[ИНФОРМАЦИЯ]: поиск по не существующему номеру закупки.");
        this.setPurchaseNumber("0");
        this.clickFindButton();
        Assert.assertEquals("[ОШИБКА]: в результатах поиска не должно быть закупок вообще",
                0, $$(By.xpath(PURCHASE_NUMBERS_LINKS_XPATH)).size());

        // Сбрасываем фильтр в исходное состояние (все сущности)
        //--------------------------------------------------------------------------------------------------------------
        this.clickClearFilterButton();
        //--------------------------------------------------------------------------------------------------------------

        // Ищем закупку по начальной цене ( верхняя и нижняя границы цен заполняются одним значением )
        ElementsCollection startPriceValues = $$(By.xpath(START_PRICE_VALUES_XPATH));
        String startPriceValue = "";

        for (SelenideElement currentPriceValue : startPriceValues) {
            startPriceValue = currentPriceValue.getText().replace(" руб.", "");
            if (!startPriceValue.contains("Цена не определена")) break; // Цена является числом, можно продолжать
        }

        System.out.printf("[ИНФОРМАЦИЯ]: поиск закупки по начальной цене: [%s].%n", startPriceValue);
        $(By.xpath(START_PRICE_FROM_XPATH)).waitUntil(visible, timeout, polling).sendKeys(startPriceValue);
        $(By.xpath(START_PRICE_TO_XPATH)).waitUntil(visible, timeout, polling).sendKeys(startPriceValue);
        this.clickFindButton();
        String totalFiltered = this.getSearchResultsCounter();
        SoftAssert.assertNotEquals("[ОШИБКА]: в результатах поиска по начальной цене отсутствуют закупки",
                "0", totalFiltered);
        Assert.assertNotEquals("[ОШИБКА]: фильтр по начальной цене не сработал, отображаются все закупки",
                totalUnfiltered, totalFiltered);

        // Сбрасываем фильтр в исходное состояние (все сущности)
        //--------------------------------------------------------------------------------------------------------------
        this.clickClearFilterButton();
        //--------------------------------------------------------------------------------------------------------------
    }

    /**
     * Получает список закупок на текущей странице и пытается их удалить.
     */
    public void getCollectionOfPurchaseNumbersAndTryToDeleteThemAll() throws Exception {
        this.selectNumberOfPurchaseCardsOnPage("10");
        ElementsCollection purchaseNumbers = $$(By.xpath(PURCHASE_NUMBERS_LINKS_XPATH));
        if (purchaseNumbers.isEmpty()) return;

        for (SelenideElement purchaseNumber : purchaseNumbers) {
            if (purchaseNumber.getText().contains("/") && purchaseNumber.getText().split("/")[1].equals("1")) {
                System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие на номер извещения [%s].%n",
                        purchaseNumber.getText());
                purchaseNumber.waitUntil(clickable, timeout, polling).click();
                this.waitForPageLoad();
                String url = url();
                // Удаление не опубликованного черновика извещения о закупке -------------------------------------------
                if (url.contains(".kontur.ru/customer/lk/trade/edit/")) {
                    this.goToPageBottom();
                    createNoticePage.clickOnButton("Удалить черновик");
                    this.clickButtonInPopupWindow("Продолжить-Предупреждение");
                    informationDialog.ifDialogOpened("Текст заголовка окна").clickOnOKButton("ОК");
                    this.waitForPageLoad();
                }
                // Удаление уже опубликованного извещения о закупке ----------------------------------------------------
                else if (url.contains(".kontur.ru/customer/lk/auctions/view/")) {
                    this.goToPageTop();
                    viewNoticePage.clickOnButton("Отказ от проведения");
                    tradeCancelDialog.ifDialogOpened().
                            setFieldClearClickAndSendKeys("Дата принятия решения",
                                    timer.getDateShift(new Date(), "DAYS", "0")).
                            selectCancelReason().
                            uploadFile(config.getAbsolutePathToFoundationDoc()).
                            clickOnButtonByName("Опубликовать");
                    this.clickButtonInPopupWindow("Продолжить-Предупреждение");
                    informationDialog.ifDialogOpened("Текст заголовка окна").clickOnOKButton("ОК");
                    this.checkPageUrl("Просмотр извещения");
                    this.goToPageTop();
                    this.checkPurchaseStatus("Отменена");
                    this.goBackToPreviousPage();
                }
                // Неизвестный URL страницы, лучше на ней ничего не делать - завершаем текущий сценарий ----------------
                else break;
            }
            this.checkPageUrl("Мои закупки");
        }
    }
}
