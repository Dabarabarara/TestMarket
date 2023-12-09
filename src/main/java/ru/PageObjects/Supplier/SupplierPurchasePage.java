package ru.PageObjects.Supplier;

import Helpers.Dictionary;
import Helpers.SoftAssert;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Supplier.FinanceAndDocuments.AccountViewPage;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Класс описывающий страницы: Закупка № XXXX ( .kontur.ru/supplier/auction/Trade/View.aspx )
 * Торги ( .kontur.ru/supplier/auction/Trade/Auction.aspx ).
 * Created by Evgeniy Glushko on 28.03.2016.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class SupplierPurchasePage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Надпись [... содержит изменения ...] в верхней части страницы
    private static final String REVISION_INFO_TEXT_ID = "BaseMainContent_MainContent_sRevisionInfo";
    //------------------------------------------------------------------------------------------------------------------
    // Надпись [История изменений извещения] в разделе [История]
    private static final String REVISIONS_HISTORY_TEXT_XPATH = "//label[contains(.,'История изменений извещения')]";
    //------------------------------------------------------------------------------------------------------------------
    // Группа ссылок на ревизии извещения в разделе [История]
    private static final String REVISIONS_LINKS_XPATH =
            "//*[@id='BaseMainContent_MainContent_fvhistoryRedactions_lblValue']/a";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка в столбце [Номер закупки/лота]
    private static final String PURCHASE_NO_LOT_NO_LINKS_IN_SEARCH_RESULTS_XPATH =
            "//td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_Number']//a";
    //------------------------------------------------------------------------------------------------------------------
    private static final String PURCHASE_NUMBER_LINK_IN_COLUMN_XPATH = "//*[@title='%s']/a";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Информация о торгах] на странице Закупки
    private static final String TRADING_INFORMATION_BUTTON_XPATH =
            "//a[@id='BaseMainContent_MainContent_ucTradeLotViewList_rptLots_ucTradeLotView_0_hlAuctionInfo_0']";
    //------------------------------------------------------------------------------------------------------------------
    private static final String TRADING_INFORMATION_BUTTON_BY_INDEX_XPATH =
            "//a[@id='BaseMainContent_MainContent_ucTradeLotViewList_rptLots_ucTradeLotView_%d_hlAuctionInfo_%d']";
    //------------------------------------------------------------------------------------------------------------------
    // Метка поля [Время до начала/Время до окончания....]
    private static final String COUNTDOWN_LABEL_XPATH = "//*[@id='ltCountdownHeader']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Время до начала/Время до окончания....]
    private static final String COUNTDOWN_XPATH = "//*[@id='lblCountdown']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Информация о торгах]
    private static final String TRAIDING_INFORMATION_ID = "BaseMainContent_MainContent_lblApplicationStateInfo";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Подача ценовых предложений]
    private static final String SUBMISSION_PRICE_OFFERS_BUTTON_XPATH = "//*[@id='BaseMainContent_MainContent_hlAuction']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Время до окончания торгов] (жирный шрифт по центру страницы)
    private static final String AUCTION_PHASE_COUNTDOWN_CENTER_XPATH =
            "//span[@id='BaseMainContent_MainContent_lblAuctionPhaseCountdownCenter']/span";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Предложение цены]
    private static final String PRICE_OFFER_INPUT_ID = "BaseMainContent_MainContent_rptApplications_txtPrice_0";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Предложение ставки]
    private static final String RATE_OFFER_INPUT_ID = "BaseMainContent_MainContent_rptApplications_txtPrice_0";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Добавить] ( прикрепить один или несколько файлов к ценовому предложению )
    private static final String ADD_BUTTON_XPATH =
            "//*[@id='BaseMainContent_MainContent_rptApplications_ufBidDocuments_0_btnUpload_0']/input";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылки с именами прикрепленных к ценовому предложению файлов ( по кнопке [Добавить] )
    private static final String ATTACHED_FILE_LINKS_XPATH =
            "//*[@id='BaseMainContent_MainContent_rptApplications_ufBidDocuments_0_ulDocuments_0']" +
                    "/li/a[@class='upload-file']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Подписать]
    private static final String SIGN_BUTTON_ID = "BaseMainContent_MainContent_rptApplications_btnConfirmBidEx_0";
    //------------------------------------------------------------------------------------------------------------------
    // Текст с наименованием организации в разделе таблицы [Лучшее предложение]
    private static final String SUPPLIER_NAME_ID = "BaseMainContent_MainContent_lblBestBidOrganizationName";
    //------------------------------------------------------------------------------------------------------------------
    // Текст с суммой в разделе таблицы [Лучшее предложение]
    private static final String BEST_OFFER_PRICE_ID = "BaseMainContent_MainContent_lblBestBidPrice2";
    //==================================================================================================================
    // Таблица [Предложения участников]:
    //==================================================================================================================
    // Колонка [Наименование участника], заголовок
    private static final String SUPPLIER_NAME_COLUMN_HEADER_ID = "jqgh_jqgBestBid_OrganizationName";
    //------------------------------------------------------------------------------------------------------------------
    // Колонка [Наименование участника], значение в первой строке
    private static final String SUPPLIER_NAME_1ST_ROW_VALUE_XPATH = "//*[@id='1']/td[4]";
    //------------------------------------------------------------------------------------------------------------------
    // Колонка [Поправочный коэффициент], значение участника, который зашел на страницу торгов
    private static final String SUPPLIER_CORRECTION_FACTOR_XPATH =
            "//div[@id='gbox_jqgBestBid']//tbody//td[5]/span[@class='auction_winner_bid']";
    //==================================================================================================================
    // Секция [Общие сведения о закупке]:
    //==================================================================================================================
    // Значение поля [Тип закупки]
    private static final String PURCHASE_TYPE_VALUE_ID = "BaseMainContent_MainContent_fvPurchaseType_lblValue";
    //------------------------------------------------------------------------------------------------------------------
    // Значение поля [Закупка проводится по 223 ФЗ]
    private static final String PURCHASE_223FZ_VALUE_ID = "BaseMainContent_MainContent_fv223Fz_lblValue";
    //------------------------------------------------------------------------------------------------------------------
    // Значение поля [Статус закупки]
    private static final String FV_TRADE_STATE_VALUE_ID = "BaseMainContent_MainContent_fvTradeState_lblValue";
    //------------------------------------------------------------------------------------------------------------------
    // Значение поля [Номер закупки]
    private static final String PURCHASE_NUMBER_VALUE_XPATH =
            "//*[@id='BaseMainContent_MainContent_fvTradeNumber_lblValue']";
    //==================================================================================================================
    // Секция [Документы]:
    //==================================================================================================================
    // Заголовок секции [Документы]
    private static final String DOCUMENT_SECTION_TITLE_XPATH =
            "//*[@id='BaseMainContent_MainContent_documentPanel']//h2";
    //------------------------------------------------------------------------------------------------------------------
    // Колонка [Файл документа] (все строки)
    private static final String FILE_DOCUMENT_COLUMN_XPATH =
            "//table[@id='BaseMainContent_MainContent_jqgTradeDocs']//td[3]/a";
    //==================================================================================================================
    // Секция [Сведения об обеспечении заявки]:
    //==================================================================================================================
    // Значение поля [Размер лицензионного вознаграждения]
    //------------------------------------------------------------------------------------------------------------------
    // Цифровое (абсолютное) значение 1
    private static final String COMM_SUM_1_VALUE_ID =
            "BaseMainContent_MainContent_ucTradeLotViewList_rptLots_ucTradeLotView_0_fvCommissionSum_0_lblValue_0";
    //------------------------------------------------------------------------------------------------------------------
    // Цифровое (абсолютное) значение 1
    private static final String COMM_SUM_2_VALUE_ID =
            "BaseMainContent_MainContent_ucTradeLotViewList_rptLots_ucTradeLotView_1_fvCommissionSum_1_lblValue_1";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка на таблицу тарифов 1
    private static final String COMM_SUM_1_LINK_ID =
            "BaseMainContent_MainContent_ucTradeLotViewList_rptLots_ucTradeLotView_0_hlCommissionSum_0_hlValue_0";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка на таблицу тарифов 2
    private static final String COMM_SUM_2_LINK_ID =
            "BaseMainContent_MainContent_ucTradeLotViewList_rptLots_ucTradeLotView_1_hlCommissionSum_1_hlValue_1";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка на таблицу тарифов 3
    private static final String COMM_SUM_3_LINK_ID =
            "BaseMainContent_MainContent_ucTradeLotViewList_rptLots_ucTradeLotView_2_hlCommissionSum_2_hlValue_2";
    //==================================================================================================================

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private static String auctionPhaseCountdown = "";
    private final Dictionary fieldNames = new Dictionary(); // поля страницы

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public SupplierPurchasePage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Предложение ставки", RATE_OFFER_INPUT_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     *               Методы страницы
     ******************************************************************************************************************/
    /**
     * Проверяет отсутствие кнопки с указанным именем на форме.
     *
     * @param buttonName имя кнопки
     */
    public void checkButtonAbsence(String buttonName) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем, что кнопка с именем [%s] отсутствует.%n", buttonName);

        Dictionary buttons = new Dictionary();
        buttons.add("Подача ценовых предложений", SUBMISSION_PRICE_OFFERS_BUTTON_XPATH);
        buttons.add("Информация о торгах для лота", TRADING_INFORMATION_BUTTON_XPATH);
        buttons.add("Информация о торгах", TRADING_INFORMATION_BUTTON_XPATH);

        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        $(By.xpath(buttons.find(buttonName))).waitUntil(disappear, 3000, 1000).
                shouldNotBe(visible);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Выводит в консоль информацию о ходе торгов.
     */
    public void logCurrentTradingInfo() throws Exception {
        String headerText;
        String counterText;
        SelenideElement header = $(By.xpath(COUNTDOWN_LABEL_XPATH));
        SelenideElement counter = $(By.xpath(COUNTDOWN_XPATH));

        this.waitLoadingImage();
        if (header.isDisplayed()) headerText = header.waitUntil(visible, timeout, polling).getText();
        else headerText = "Название не отображается";
        if (counter.isDisplayed()) counterText = counter.waitUntil(visible, timeout, polling).getText();
        else counterText = "Время не отображается";
        System.out.printf("[ИНФОРМАЦИЯ]: информация о торгах - [%s] [%s].%n", headerText, counterText);
    }

    /**
     * Проверяет уменьшение таймера
     */
    public void checkCounterDecrease() throws InterruptedException {
        int waitTime = 10;
        String oldCounterValueText;
        String newCounterValueText;
        int oldCounterValue = 0;
        int newCounterValue = 0;
        SelenideElement counter = $(this.getBy(COUNTDOWN_XPATH)).waitUntil(visible, timeout, polling);

        oldCounterValueText = counter.getText();
        System.out.printf("[ИНФОРМАЦИЯ]: ожидание [%d] секунд.%n", waitTime);
        TimeUnit.SECONDS.sleep(waitTime);
        newCounterValueText = counter.getText();
        System.out.printf("[ИНФОРМАЦИЯ]: старое значение счетчика - [%s], новое значение счетчика " +
                "через '%d' секунд - [%s].%n", oldCounterValueText, waitTime, newCounterValueText);

        try {
            oldCounterValue = Integer.parseInt(oldCounterValueText.replaceAll(":", ""));
            newCounterValue = Integer.parseInt(newCounterValueText.replaceAll(":", ""));
        } catch (NumberFormatException stringToIntegerException) {
            System.err.println("[ОШИБКА]: не верный формат текста в поле [Время до начала/Время до окончания....]");
            stringToIntegerException.printStackTrace();
        }

        Assert.assertTrue(String.format("[ОШИБКА]: некорректная работа счеткича, ожидлась [%s] > [%s]",
                oldCounterValueText, newCounterValueText), oldCounterValue > newCounterValue);
    }

    /**
     * Выводит в консоль информацию о ходе торгов при попозиционном сравнении ценовых предложений.
     */
    private void logCurrentTradingInfoOnPositionalComparisonTrades() throws Exception {
        String tradingInfoText;
        SelenideElement tradingInfo = $(By.id(TRAIDING_INFORMATION_ID));

        this.waitLoadingImage();
        if (tradingInfo.isDisplayed()) tradingInfoText = tradingInfo.waitUntil(visible, timeout, polling).getText();
        else tradingInfoText = "Не отображается";
        System.out.printf("[ИНФОРМАЦИЯ]: информация о торгах - [%s].%n", tradingInfoText);
    }

    /**
     * Выводит в консоль информацию о ходе торгов при МСП-закупке в соответствии с нормами 223-ФЗ.
     */
    private void logCurrentTradingInfoOnSMBPurchasesTrades() throws Exception {
        String tradingInfoText;
        SelenideElement tradingInfo = $(By.id(TRAIDING_INFORMATION_ID));

        this.waitLoadingImage();
        if (tradingInfo.isDisplayed()) tradingInfoText = tradingInfo.waitUntil(visible, timeout, polling).getText();
        else tradingInfoText = "Не отображается";
        System.out.printf("[ИНФОРМАЦИЯ]: информация о торгах - [%s].%n", tradingInfoText);
    }

    /**
     * Проверяет количество лотов в закупке.
     *
     * @param expectedLots ожидаемое количество лотов в закупке
     */
    public void checkNumberOfLotsInPurchase(String expectedLots) {
        ElementsCollection actualLots = $$(By.xpath(PURCHASE_NO_LOT_NO_LINKS_IN_SEARCH_RESULTS_XPATH));
        int expected = Integer.parseInt(expectedLots);
        int actual = actualLots.size();
        System.out.printf("[ИНФОРМАЦИЯ]: ожидаемое количество лотов: [%d], реальное количество лотов: [%d].%n",
                expected, actual);
        Assert.assertEquals("Неверное количество лотов в закупке - ", expected, actual);
    }

    /**
     * Открывает лот с указанным номером в закупке.
     *
     * @param lotNumber номер лота в закупке
     */
    public void openLotByNumber(String lotNumber) throws Exception {
        ElementsCollection lots = $$(By.xpath(PURCHASE_NO_LOT_NO_LINKS_IN_SEARCH_RESULTS_XPATH));
        String linkText = String.format("%s/%s", config.getParameter("PurchaseNumber"), lotNumber);

        System.out.printf("[ИНФОРМАЦИЯ]: требуется открыть лот с номером: [%s].%n", lotNumber);
        SelenideElement lot = lots.size() == 1 ? lots.get(0) : lots.findBy(exactText(linkText));
        lot.waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
        System.out.printf("[ИНФОРМАЦИЯ]: успешно открыт лот с номером: [%s].%n", lotNumber);
    }

    /**
     * Делает щелчок по ссылке в столбце [Наименование закупки] (для Конкурса).
     */
    public void clickNamePurchaseTableTenderLink() throws Exception {
        String numberPurchaseLot = $(By.xpath(PURCHASE_NO_LOT_NO_LINKS_IN_SEARCH_RESULTS_XPATH)).
                waitUntil(visible, timeout, polling).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: получен [Номер закупки/лота]: [%s].%n", numberPurchaseLot);
        String xPathLocal = String.format(PURCHASE_NUMBER_LINK_IN_COLUMN_XPATH, numberPurchaseLot);
        $(By.xpath(xPathLocal)).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        System.out.println("[ИНФОРМАЦИЯ]: произведен переход по ссылке в столбце [Наименование закупки].");
    }

    /**
     * Нажимает на кнопку [Информация о торгах] в указанном лоте.
     */
    public void clickTradingInformationButton(String lotNumber) throws Exception {
        //--------------------------------------------------------------------------------------------------------------
        long slowSiteTimeoutInMinutes = Long.parseLong(config.getConfigParameter("SlowSiteTimeoutInMinutes"));
        boolean slowSite = slowSiteTimeoutInMinutes > 0;                // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
        Integer index = Integer.parseInt(lotNumber) - 1;
        String xpath = String.format(TRADING_INFORMATION_BUTTON_BY_INDEX_XPATH, index, index);
        this.logButtonNameToPress("Информация о торгах");
        $(By.xpath(xpath)).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
        this.logPressedButtonName("Информация о торгах");
        //--------------------------------------------------------------------------------------------------------------
        if (slowSite) TimeUnit.MINUTES.sleep(slowSiteTimeoutInMinutes); // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
        this.checkPageUrl("Информация о торгах");
        this.logCurrentTradingInfo();
    }

    /**
     * Нажимает на кнопку [Информация о торгах] в указанном лоте при попозиционном сравнении ценовых предложений.
     */
    public void clickTradingInformationButtonOnPositionalComparisonTrades(String lotNumber) throws Exception {
        //--------------------------------------------------------------------------------------------------------------
        long slowSiteTimeoutInMinutes = Long.parseLong(config.getConfigParameter("SlowSiteTimeoutInMinutes"));
        boolean slowSite = slowSiteTimeoutInMinutes > 0;                // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
        Integer index = Integer.parseInt(lotNumber) - 1;
        String xpath = String.format(TRADING_INFORMATION_BUTTON_BY_INDEX_XPATH, index, index);
        this.logButtonNameToPress("Информация о торгах");
        $(By.xpath(xpath)).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
        this.logPressedButtonName("Информация о торгах");
        //--------------------------------------------------------------------------------------------------------------
        if (slowSite) TimeUnit.MINUTES.sleep(slowSiteTimeoutInMinutes); // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
        this.checkPageUrl("Информация о торгах при попозиционном сравнении ценовых предложений");
        this.logCurrentTradingInfoOnPositionalComparisonTrades();
    }

    /**
     * Нажимает на кнопку [Информация о торгах] в указанном лоте при МСП-закупке в соответствии с нормами 223-ФЗ.
     */
    public void clickTradingInformationButtonOnSMBPurchasesTrades(String lotNumber) throws Exception {
        //--------------------------------------------------------------------------------------------------------------
        long slowSiteTimeoutInMinutes = Long.parseLong(config.getConfigParameter("SlowSiteTimeoutInMinutes"));
        boolean slowSite = slowSiteTimeoutInMinutes > 0;                // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
        Integer index = Integer.parseInt(lotNumber) - 1;
        String xpath = String.format(TRADING_INFORMATION_BUTTON_BY_INDEX_XPATH, index, index);
        this.logButtonNameToPress("Информация о торгах");
        $(By.xpath(xpath)).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
        this.logPressedButtonName("Информация о торгах");
        //--------------------------------------------------------------------------------------------------------------
        if (slowSite) TimeUnit.MINUTES.sleep(slowSiteTimeoutInMinutes); // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
        this.checkPageUrl("Информация о торгах при МСП-закупке в соответствии с нормами 223-ФЗ");
        this.logCurrentTradingInfoOnSMBPurchasesTrades();
    }

    /**
     * Нажимает на кнопку [Подача ценовых предложений].
     */
    public void clickSubmissionPriceOffersButton() throws Exception {
        this.waitLoadingImage();
        //--------------------------------------------------------------------------------------------------------------
        long slowSiteTimeoutInMinutes = Long.parseLong(config.getConfigParameter("SlowSiteTimeoutInMinutes"));
        boolean slowSite = slowSiteTimeoutInMinutes > 0;
        if (slowSite) TimeUnit.MINUTES.sleep(slowSiteTimeoutInMinutes); // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
        this.logButtonNameToPress("Подача ценовых предложений");
        $(By.xpath(SUBMISSION_PRICE_OFFERS_BUTTON_XPATH)).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        this.logPressedButtonName("Подача ценовых предложений");
        this.checkPageUrl("Торги");
    }

    /**
     * Нажимает на кнопку [Подача ценовых предложений] при попозиционном сравнении ценовых предложений.
     */
    public void clickSubmissionPriceOffersButtonOnPositionalComparisonTrades() throws Exception {
        this.waitLoadingImage();
        //--------------------------------------------------------------------------------------------------------------
        long slowSiteTimeoutInMinutes = Long.parseLong(config.getConfigParameter("SlowSiteTimeoutInMinutes"));
        boolean slowSite = slowSiteTimeoutInMinutes > 0;
        if (slowSite) TimeUnit.MINUTES.sleep(slowSiteTimeoutInMinutes); // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
        this.logButtonNameToPress("Подача ценовых предложений");
        $(By.xpath(SUBMISSION_PRICE_OFFERS_BUTTON_XPATH)).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        this.logPressedButtonName("Подача ценовых предложений");
    }

    /**
     * Нажимает на кнопку [Подача ценовых предложений] при МСП-закупке в соответствии с нормами 223-ФЗ.
     */
    public void clickSubmissionPriceOffersButtonOnSMBPurchasesTrades() throws Exception {
        this.waitLoadingImage();
        //--------------------------------------------------------------------------------------------------------------
        long slowSiteTimeoutInMinutes = Long.parseLong(config.getConfigParameter("SlowSiteTimeoutInMinutes"));
        boolean slowSite = slowSiteTimeoutInMinutes > 0;
        if (slowSite) TimeUnit.MINUTES.sleep(slowSiteTimeoutInMinutes); // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
        this.logButtonNameToPress("Подача ценовых предложений");
        $(By.xpath(SUBMISSION_PRICE_OFFERS_BUTTON_XPATH)).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        this.logPressedButtonName("Подача ценовых предложений");
    }

    /**
     * Ожидает начала торгов (для сценариев без ускорения процедур).
     */
    public void awaitingTheStartOfTraiding() throws Exception {
        long waitTimeMinutes = Long.parseLong(config.getConfigParameter("MaxStartOfTraidingsWaitTime"));
        long nTries = waitTimeMinutes * 60 / 30;
        long i = 1;

        // Если кнопка [Подача ценовых предложений] еще не отображается - входим в цикл ожидания её появления на форме
        System.out.printf("[ИНФОРМАЦИЯ]: установленное максимальное время ожидания торгов в автотесте %d минут.%n",
                waitTimeMinutes);

        if (!$(By.xpath(SUBMISSION_PRICE_OFFERS_BUTTON_XPATH)).isDisplayed()) {
            // Ждем появления кнопки [Подача ценовых предложений]
            do {
                TimeUnit.SECONDS.sleep(30);
                System.out.printf("[ИНФОРМАЦИЯ]: %s проверка № %d наличия кнопки [Подача ценовых предложений].%n",
                        timer.getCurrentDateTime("dd.MM.yyyy HH:mm:ss"), i);
                i++;
            } while (i <= nTries && !$(By.xpath(SUBMISSION_PRICE_OFFERS_BUTTON_XPATH)).isDisplayed());
        }

        Assert.assertTrue("[ОШИБКА]: не удалось дождаться появления кнопки [Подача ценовых предложений]",
                $(By.xpath(SUBMISSION_PRICE_OFFERS_BUTTON_XPATH)).isDisplayed());
    }

    /**
     * Ожидает начала второй фазы торгов (для сценариев без ускорения процедур).
     */
    public void awaitingTheStartOfTraidingSecondPhase() throws Exception {
        long waitTimeMinutes = Integer.parseInt(config.getConfigParameter("MaxStartOfTraidingsWaitTime"));
        long nTries = waitTimeMinutes * 60 / 10;
        long i = 1;
        final String text = "Время до окончания второй фазы:";

        // Если надпись еще не отображается - входим в цикл ожидания её появления на форме
        System.out.printf(
                "[ИНФОРМАЦИЯ]: установленное максимальное время ожидания второй фазы торгов в автотесте %d минут.%n",
                waitTimeMinutes);

        if (!$(By.xpath(COUNTDOWN_LABEL_XPATH)).shouldBe(visible).getText().contains(text)) {
            // Ждем появления надписи [Время до окончания второй фазы:]
            do {
                TimeUnit.SECONDS.sleep(10);
                System.out.printf("[ИНФОРМАЦИЯ]: %s проверка № %d наличия надписи [%s].%n",
                        timer.getCurrentDateTime("dd.MM.yyyy HH:mm:ss"), i, text);
                i++;
            } while (i <= nTries && !$(By.xpath(COUNTDOWN_LABEL_XPATH)).shouldBe(visible).getText().contains(text));
        }

        Assert.assertTrue(
                String.format("[ОШИБКА]: не удалось дождаться появления надписи [%s]", text),
                $(By.xpath(COUNTDOWN_LABEL_XPATH)).shouldBe(visible).getText().contains(text));
    }

    /**
     * Устанавливает значение в поле [Предложение цены].
     *
     * @param newPrice цена
     */
    public void setNewPriceOffer(String newPrice) throws Exception {
        this.waitClearClickAndSendKeys(By.id(PRICE_OFFER_INPUT_ID), newPrice);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [Предложение цены] значением [%s].%n", newPrice);
    }

    /**
     * Прикрепляет документ в разделе [Новое предложение].
     */
    public void uploadDocumentInNewOfferPartition() throws Exception {
        String fileName = config.getAbsolutePathToUserEntityDoc();
        $(By.xpath(ADD_BUTTON_XPATH)).waitUntil(exist, timeout, polling).sendKeys(fileName);
        System.out.printf("[ИНФОРМАЦИЯ]: прикреплен документ [%s] в разделе [Новое предложение].%n", fileName);
        TimeUnit.SECONDS.sleep(10);
        $$(By.xpath(ATTACHED_FILE_LINKS_XPATH)).shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    /**
     * Нажимает на кнопку [Подписать].
     */
    public void clickSignButton() throws Exception {
        $(By.id(SIGN_BUTTON_ID)).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
        this.logPressedButtonName("Подписать");
    }

    /**
     * Получает значение лучшего ценового предложения.
     */
    public String getBestPriceOffer() throws Exception {
        TimeUnit.SECONDS.sleep(3); // Не убирайте эту задержку - не успевает считать новое значение цены из контрола
        String bestPrice = $(By.id(BEST_OFFER_PRICE_ID)).waitUntil(exist, timeout, polling).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено получение цены лучшего ценового предложения [%s].%n", bestPrice);
        return bestPrice;
    }

    /**
     * Проверяет, что информация о Поставщиках не отображается на форме подачи ценовых предложений.
     */
    public void checkThatSuppliersInformationIsNotDisplayedOnPriceOffersForm() {
        System.out.println(
                "[ИНФОРМАЦИЯ]: проверяем, что информация о Поставщиках не отображается на форме подачи ценовых предложений.");

        // Проверяем отсутствие текста с наименованием организации в разделе таблицы [Лучшее предложение]
        $(By.id(SUPPLIER_NAME_ID)).waitUntil(visible, timeout, polling)
                .shouldHave(text("Участник "));
        System.out.println(
                "[ИНФОРМАЦИЯ]: проверяем отсутствие текста с наименованием организации в разделе таблицы [Лучшее предложение].");

        // Проверяем присутствие заголовка колонки [Наименование участника]
        $(By.id(SUPPLIER_NAME_COLUMN_HEADER_ID)).
                waitUntil(visible, timeout, polling)
                .shouldHave(text("Наименование участника"));
        System.out.println("[ИНФОРМАЦИЯ]: проверяем присутствие заголовка колонки [Наименование участника].");

        // Проверяем отсутствие текста с наименованием организации в первой строке столбца [Наименование организации]
        $(By.xpath(SUPPLIER_NAME_1ST_ROW_VALUE_XPATH)).
                waitUntil(visible, timeout, polling).shouldHave(text("Участник "));
        System.out.println(
                "[ИНФОРМАЦИЯ]: проверяем отсутствие текста с наименованием организации в первой " +
                        "строке столбца [Наименование организации].");
    }

    /**
     * Проверяет предложение Поставщика в столбце [Поправочный коэффициент] (данное предложение выделено зеленым цветом
     * в таблице [Предложение участников]).
     *
     * @param expectedCorrectionFactor ожидаемое значение поправочного коэффициента
     */
    public void checkTheSupplierCorrectionFactor(String expectedCorrectionFactor) {
        String actual = $(this.getBy(SUPPLIER_CORRECTION_FACTOR_XPATH)).waitUntil(visible, timeout, polling).getText();
        System.out.printf(
                "[ИНФОРМАЦИЯ]: проверяем значение [Поправочного коэффициента], которое ранее вводил данный Поставщик: " +
                        "ожидаемое - [%s], реальное - [%s].%n", expectedCorrectionFactor, actual);
        Assert.assertEquals("Номер закупки не совпадает !", expectedCorrectionFactor, actual);
    }

    /**
     * Возвращает значение поля [Номер закупки] (Общие сведения о закупке).
     */
    public String getPurchaseNumberValue() {
        return $(By.xpath(PURCHASE_NUMBER_VALUE_XPATH)).waitUntil(visible, timeout, polling).getText();
    }

    /**
     * Проверяет значение поля [Номер закупки] (Общие сведения о закупке).
     */
    public void checkPurchaseNumberValue() {
        String expected = config.getParameter("PurchaseNumberForTradeSearch");
        String actual = $(By.xpath(PURCHASE_NUMBER_VALUE_XPATH)).waitUntil(visible, timeout, polling).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем значение поля [Номер закупки]: ожидаемое - [%s], реальное - [%s].%n",
                expected, actual);
        Assert.assertEquals("Номер закупки не совпадает !", expected, actual);
    }

    /**
     * Проверяет колонку [Файл документа].
     */
    public void checkFileDocumentColumn() throws Exception {
        $(By.xpath(DOCUMENT_SECTION_TITLE_XPATH)).waitUntil(visible, timeout, polling).click();
        TimeUnit.SECONDS.sleep(5);

        $(By.xpath(FILE_DOCUMENT_COLUMN_XPATH)).waitUntil(visible, timeout, polling).scrollTo();
        TimeUnit.SECONDS.sleep(5);

        int count = $$(By.xpath(FILE_DOCUMENT_COLUMN_XPATH)).size();
        for (int i = 0; i < count; i++) {
            System.out.printf("[ИНФОРМАЦИЯ]: [%s].%n", $$(By.xpath(FILE_DOCUMENT_COLUMN_XPATH)).get(i).getText());
        }

        // Получаем имя файла для загрузки без пути к нему
        String fileName = $$(By.xpath(FILE_DOCUMENT_COLUMN_XPATH)).get(0).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: имя файла для загрузки: [%s].%n", fileName);

        // Делаем щелчок по ссылке на этот файл
        TimeUnit.SECONDS.sleep(5);
        $$(By.xpath(FILE_DOCUMENT_COLUMN_XPATH)).get(0).click();
        TimeUnit.SECONDS.sleep(15);

        // Проверяем, что файл загружен успешно
        int i = 10;
        File file = new File(config.getPathToTempFolderWithRandomName() + "\\" + fileName);
        while (!file.exists()) {
            TimeUnit.SECONDS.sleep(10);
            file = new File(config.getPathToTempFolderWithRandomName() + "\\" + fileName);
            i--;
            if (file.exists()) break;
            if (i == 0)
                Assert.assertTrue(String.format("[ИНФОРМАЦИЯ]: Файл: [%s] не загружен на диск.", fileName), file.exists());
        }
        // Удаляем загруженный файл вместе с временным каталогом
        File folderToDelete = new File(config.getPathToTempFolderWithRandomName());
        this.deleteTemporaryFolderWithFiles(folderToDelete);
    }

    /**
     * Проверяет значение поля [Размер лицензионного вознаграждения].
     */
    public void checkCommissionSumInPurchaseCardViewPage() throws Exception {
        // Работа в окне [Поиск закупок] ===============================================================================
        config.switchToNewWindowInBrowser();
        //==============================================================================================================

        // Работа в окне [Закупка № xxxx] =============================================================================
        this.checkPageUrl("Закупка №");

        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        if ($(By.id(PURCHASE_TYPE_VALUE_ID)).isDisplayed())
            $(By.id(PURCHASE_TYPE_VALUE_ID)).shouldHave(text("Закупка в соответствии с нормами 223-ФЗ"));
        else if ($(By.id(PURCHASE_223FZ_VALUE_ID)).isDisplayed())
            $(By.id(PURCHASE_223FZ_VALUE_ID)).shouldHave(text("Да"));
        Assert.assertNotEquals("Статус закупки не соответствует ожидаемому !",
                "Не состоялась", $(By.id(FV_TRADE_STATE_VALUE_ID)).getText());
        String message = "[ИНФОРМАЦИЯ]: обнаружена ссылка [В соответствии с действующими тарифами].";
        String txt = "В соответствии с действующими тарифами";
        if ($(By.id(COMM_SUM_1_LINK_ID)).isDisplayed()) $(By.id(COMM_SUM_1_LINK_ID)).shouldHave(text(txt));
        else if ($(By.id(COMM_SUM_2_LINK_ID)).isDisplayed()) $(By.id(COMM_SUM_2_LINK_ID)).shouldHave(text(txt));
        else if ($(By.id(COMM_SUM_3_LINK_ID)).isDisplayed()) $(By.id(COMM_SUM_3_LINK_ID)).shouldHave(text(txt));
        else {
            String expectedCommissionSum = config.getConfigParameter("OpenPart223CommissionSum");
            String actualCommissionSum = "";
            if ($(By.id(COMM_SUM_1_VALUE_ID)).isDisplayed()) {
                $(By.id(COMM_SUM_1_VALUE_ID)).waitUntil(visible, timeout, polling).click();
                TimeUnit.SECONDS.sleep(1);
                actualCommissionSum = $(By.id(COMM_SUM_1_VALUE_ID)).getText();
            } else if ($(By.id(COMM_SUM_2_VALUE_ID)).isDisplayed()) {
                $(By.id(COMM_SUM_2_VALUE_ID)).waitUntil(visible, timeout, polling).click();
                TimeUnit.SECONDS.sleep(1);
                actualCommissionSum = $(By.id(COMM_SUM_2_VALUE_ID)).getText();
            }
            message = String.format("[ИНФОРМАЦИЯ]: обнаружена сумма лицензионного вознаграждения [%s].",
                    actualCommissionSum);
            SoftAssert.assertEquals("Размер лицензионного вознаграждения не совпадает.",
                    expectedCommissionSum, actualCommissionSum);
        }
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        System.out.println(message);

        config.closeActiveWindowsAndSwitchToMainWindowInBrowser(driver);
        //==============================================================================================================

        // Работа в окне [Поиск закупок] ===============================================================================
        this.checkPageUrl("Поиск закупок");
        //==============================================================================================================
    }

    /**
     * Проверяет наличие ревизий в извещении о закупке.
     */
    public void checkRevisionsPresenceInNoticeForm() {
        // Проверяем содержимое надписи [... содержит изменения ...] в верхней части страницы
        SelenideElement revisionInfo = $(By.id(REVISION_INFO_TEXT_ID)).waitUntil(visible, timeout, polling);
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено поле с информацией о ревизиях: [%s].%n", revisionInfo.getText());
        revisionInfo.shouldHave(text("содержит изменения"));

        // Перемещаемся вниз к надписи [История изменений извещения] в разделе [История]
        System.out.println("[ИНФОРМАЦИЯ]: перемещаемся к надписи [История изменений извещения] в разделе [История].");
        this.scrollToCenter(this.getBy(REVISIONS_HISTORY_TEXT_XPATH));

        // Определяем количество ссылок на ревизии данного документа
        ElementsCollection revisions = $$(By.xpath(REVISIONS_LINKS_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено: [%d] ревизий извещения о закупке.%n", revisions.size());
        Assert.assertTrue("Документ не содержит ни одной ссылки на ревизии !", revisions.size() > 0);
    }

    /**
     * Проверяет каждую ревизию в извещении о закупке.
     */
    public void checkEachRevisionInNoticeForm() throws Exception {

        // Определяем количество ревизий в извещении о закупке
        int links = $$(By.xpath(REVISIONS_LINKS_XPATH)).size();

        // Проверяем каждую ревизию в извещении о закупке
        for (int i = 0; i < links; i++) {
            this.scrollToCenter(this.getBy(REVISIONS_HISTORY_TEXT_XPATH));
            SelenideElement revision = $$(By.xpath(REVISIONS_LINKS_XPATH)).get(i);
            System.out.printf("[ИНФОРМАЦИЯ]: проверяем ревизию документа: [%s].%n", revision.getText());
            String url = revision.getAttribute("href");
            System.out.printf("[ИНФОРМАЦИЯ]: получен url ревизии документа: [%s].%n", url);
            this.goToUrl(url, "Закупка №", 15, 15);
            screenshoter.takeScreenshotWithDelay(0);
            SelenideElement purchaseNumber = $(By.xpath(PURCHASE_NUMBER_VALUE_XPATH));
            if (purchaseNumber.isDisplayed()) this.checkPurchaseNumberValue();
            else Assert.assertTrue("Страница ревизии не открылась !", purchaseNumber.isDisplayed());
            getWebDriver().navigate().back();
            this.waitForPageLoad(15);
            this.checkPageUrl("Закупка №");
        }
    }

    /**
     * Фиксирует значение счётчика времени, оставшегося до момента подачи заявки.
     */
    public void saveTimeUntilTheEndOfTrading() {
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        auctionPhaseCountdown = $(By.xpath(AUCTION_PHASE_COUNTDOWN_CENTER_XPATH)).
                waitUntil(visible, longtime, 100).getText();
        System.out.printf(
                "[ИНФОРМАЦИЯ]: время до окончания торгов перед моментом подачи заявки - [%s].%n", auctionPhaseCountdown);
        Assert.assertTrue("[ОШИБКА]: полученное текстовое значение не соответствует формату времени",
                auctionPhaseCountdown.contains(":"));
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Проверяет автоматическое продление переторжки после подачи ценового предложения.
     *
     * @param minutes интервал автоматического продления переторжки
     */
    public void checkRetenderingAutoProlongation(String minutes) throws Exception {
        this.waitForPageLoad();  // Костыль для того, чтобы дождаться обновления счетчика времени переторжки 28.01.2020
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        String newCountdown = $(By.xpath(AUCTION_PHASE_COUNTDOWN_CENTER_XPATH)).
                waitUntil(visible, longtime, 100).getText(); // получаем новое значение счетчика
        System.out.printf("[ИНФОРМАЦИЯ]: время до окончания торгов после момента подачи заявки - [%s].%n", newCountdown);
        Assert.assertTrue("[ОШИБКА]: полученное текстовое значение не соответствует формату времени",
                newCountdown.contains(":"));
        DateFormat sdf = new SimpleDateFormat("hh:mm:ss");      // работаем только со значениями времени
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));                  // это ОБЯЗАТЕЛЬНО иначе время будет бредовое

        try {
            Date before = sdf.parse(auctionPhaseCountdown);            // переводим строку со временем в формат даты
            Date after = sdf.parse(newCountdown);                      // переводим строку со временем в формат даты
            System.out.printf("[ИНФОРМАЦИЯ]: результат конвертации - время до    подачи: [%s].%n", before);
            System.out.printf("[ИНФОРМАЦИЯ]: результат конвертации - время после подачи: [%s].%n", after);
            Assert.assertTrue("[ОШИБКА]: автоматическое продление переторжки не сработало",
                    after.getTime() > before.getTime());       // после подачи ЦП ост. время увеличилось
            // Проверяем, насколько именно увеличилось время переторжки и сравниваем с ожидаемым увеличением
            long expected = Long.parseLong(minutes);                   // переводим ожидаемую разницу в число мин.
            long actual = after.getTime() / 60000;                     // переводим реальную  разницу в число мин.
            System.out.printf("[ИНФОРМАЦИЯ]: интервал автоматического продления переторжки ожидае" +
                    "мый: [%d] мин., реальный [%d] мин., разница [%d] мин.%n", expected, actual, expected - actual);
            if ((expected - actual) > 1) SoftAssert.assertTrue(String.format(
                    "[ОШИБКА]: реальная величина интервала автоматического продления переторжки [%s] мин. " +
                            "не соответствует ожидаемому [%s] мин.", minutes, newCountdown), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Устанавливает значение полей с предварительной их очисткой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     */
    public void setFieldClearClickAndSendKeys(String fieldName, String value) throws Exception {
        this.waitClearClickAndSendKeysById(fieldNames.find(fieldName), value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
    }
}
