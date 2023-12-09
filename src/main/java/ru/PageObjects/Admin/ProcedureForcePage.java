package ru.PageObjects.Admin;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы со страницей [Ускорение процедур] ( АДМИНИСТРИРОВАНИЕ / Сервисные страницы / Ускорение процедур ).
 * [ .kontur.ru/supplier/auction/Trade/TimeShift.aspx ]
 * Created by Glushko Evgeniy on 25.03.2016.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class ProcedureForcePage extends CommonAdminPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Основные поля страницы

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки]
    private static final String PURCHASE_NUMBER_FIELD_ID = "txtPurchaseNumber";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Загрузить данные]
    private static final String PROCEDURE_DATA_UPDATE_BUTTON_XPATH = "//*[@id='btnLoadData']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Данные о лотах]

    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [Лот X] на вкладке переключения лотов
    private static final String SELECT_LOT_XPATH = "//*[@id='lotTabs']//a[contains(.,'Лот %s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [Предмет предварительного отбора №Х] на вкладке переключения лотов
    private static final String SELECT_PRESELECTION_OBJECT_XPATH =
            "(//*[@id='lotTabs']//a[contains(.,'Предмет предварительного отбора')])[%s]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус лота]
    private static final String LOT_STATUS_VALUE_ID = "lotInfo_LotStateText";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Обновить]
    private static final String UPDATE_BUTTON_XPATH = "//*[@id='controlPurchase_UpdateLotState']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Управление закупкой]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата начала торгов]
    private static final String BIDDING_START_DATE_VALUE_ID = "lotInfo_AuctionStartDateText";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Текущая]
    private static final String SET_NOW_BUTTON_XPATH = "//button[contains(., 'Текущая')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Текущая] для поля [Дата начала рассмотрения вторых частей заявок]
    private static final String SET_NOW_SECOND_PARTS_CONSIDERATION_START_DATE_XPATH =
            "//*[@id='controlPurchase_SetNowSecondPartsConsiderationStartDate']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Текущая] для поля [Дата начала квалификационного отбора]
    private static final String SET_NOW_QUALIFICATION_START_DATE_XPATH =
            "//*[@id='controlPurchase_SetNowQualificationStartDate']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Текущая-2мин.] для поля [Дата окончания приема заявок]
    private static final String SET_NOW_APPLICATION_AND_DATE_XPATH =
            "//*[@id='controlPurchase_SetNowApplicationEndDate']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Текущая-2мин.] для поля [Дата и время публикации уведомления об итогах рассмотрения заявок]
    private static final String SET_NOW_NOTIFICATION_PROTOCOL_PUBLICATE_DATE_XPATH =
            "//*[@id='controlPurchase_SetNowNotificationProtocolPublicateDate']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Управление торгами]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус торга]
    private static final String BIDDING_STATUS_VALUE_ID = "lotInfo_LotTradingStateText";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Завершить торг]
    private static final String COMPLETE_BIDDING_BUTTON_XPATH = "//*[@id='controlBidding_CompleteBidding']";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [Управление торгами]
    private static final String BIDDING_MANAGE_LINK_ID = "BaseMainContent_MainContent_hlBiddingServiceServicePage";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Управление договорами]

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Начало регламентного срока договора - Текущая]
    private static final String START_SCHED_TERM_OF_CONTRACT_CURRENT_BUTTON_XPATH =
            "//table[@id='lotInfo_Contracts']//tr/td[5]/button[contains(text(),'Текущая')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопки [Крайний срок заключения договора - Текущая] ( если таблица имеет несколько строк )
    private static final String START_SCHED_TERM_OF_CONTRACT_CURRENT_BUTTONS_XPATH =
            "//table[@id='lotInfo_Contracts']//tr/td[6]/button[contains(text(),'Текущая')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопки [Действия - Обновить договор в Tender] ( если таблица имеет несколько строк )
    private static final String SEND_CONTRACT_TO_TENDER_BUTTONS_XPATH =
            "//table[@id='lotInfo_Contracts']//tr/td/button[contains(text(),'Обновить договор в Tender')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary buttons = new Dictionary();  // Список локаторов кнопок
    private final Dictionary timeouts = new Dictionary(); // Список значений временных задержек

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public ProcedureForcePage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        buttons.add("Дата начала рассмотрения вторых частей заявок - Текущая",
                SET_NOW_SECOND_PARTS_CONSIDERATION_START_DATE_XPATH);
        buttons.add("Дата начала квалификационного отбора - Текущая", SET_NOW_QUALIFICATION_START_DATE_XPATH);
        buttons.add("Дата окончания приема заявок - Текущая-2мин.", SET_NOW_APPLICATION_AND_DATE_XPATH);
        buttons.add("Дата и время публикации уведомления об итогах рассмотрения заявок - Текущая-2мин.",
                SET_NOW_NOTIFICATION_PROTOCOL_PUBLICATE_DATE_XPATH);
        buttons.add("Начало регламентного срока договора - Текущая", START_SCHED_TERM_OF_CONTRACT_CURRENT_BUTTON_XPATH);
        buttons.add("Крайний срок заключения договора - Текущая",
                START_SCHED_TERM_OF_CONTRACT_CURRENT_BUTTONS_XPATH);
        buttons.add("Действия - Обновить договор в Tender", SEND_CONTRACT_TO_TENDER_BUTTONS_XPATH);
        buttons.add("Загрузить данные", PROCEDURE_DATA_UPDATE_BUTTON_XPATH);
        buttons.add("Завершить торг", COMPLETE_BIDDING_BUTTON_XPATH);
        buttons.add("Обновить", UPDATE_BUTTON_XPATH);
        buttons.add("Текущая", SET_NOW_BUTTON_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        timeouts.add("Дата начала рассмотрения вторых частей заявок - Текущая", "55");
        timeouts.add("Дата начала квалификационного отбора - Текущая", "55");
        timeouts.add("Дата окончания приема заявок - Текущая-2мин.", "55");
        timeouts.add("Дата и время публикации уведомления об итогах рассмотрения заявок - Текущая-2мин.", "55");
        timeouts.add("Начало регламентного срока договора - Текущая", "55");
        timeouts.add("Крайний срок заключения договора - Текущая", "25");
        timeouts.add("Действия - Обновить договор в Tender", "25");
        timeouts.add("Загрузить данные", "25");
        timeouts.add("Завершить торг", "55");
        timeouts.add("Обновить", "55");
        timeouts.add("Текущая", "95");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     *               Методы страницы
     ******************************************************************************************************************/
    /**
     * Устанавливает значение в поле [Номер закупки].
     *
     * @param purchaseNumber номер закупки
     */
    public void setPurchaseNumber(String purchaseNumber) {
        $(this.getBy(PURCHASE_NUMBER_FIELD_ID)).shouldBe(visible).clear();
        $(this.getBy(PURCHASE_NUMBER_FIELD_ID)).sendKeys(purchaseNumber);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [Номер закупки] значением [%s].%n", purchaseNumber);
    }

    /**
     * Производит нажатие на кнопку с указанным в параметре именем.
     *
     * @param buttonName имя кнопки
     */
    public void clickButtonByName(String buttonName) throws Exception {
        ElementsCollection allButtons = $$(this.getBy(buttons.find(buttonName))).filter(visible); // Фильтр по коллекции
        this.logButtonNameToPress(buttonName);
        System.out.printf("[ИНФОРМАЦИЯ]: по данному имени найден локатор [%s].%n", buttons.find(buttonName));

        if (allButtons.size() == 0) {
            System.out.printf("[ИНФОРМАЦИЯ]: кнопка не появилась, дополнительное ожидание [%s] секунд.%n",
                    timeouts.find(buttonName));
            TimeUnit.SECONDS.sleep(Long.parseLong(timeouts.find(buttonName)));
            allButtons = $$(this.getBy(buttons.find(buttonName))).filter(visible); // Фильтр по коллекции
        }

        System.out.printf("[ИНФОРМАЦИЯ]: по данному локатору найдено [%d] кнопок.%n", allButtons.size());
        Assert.assertNotEquals("[ОШИБКА]: не найдено ни одной кнопки на странице", 0, allButtons.size());
        Assert.assertEquals("[ОШИБКА]: найдено более одной кнопки на странице", 1, allButtons.size());

        SelenideElement button = allButtons.get(0);
        String buttonText = this.isNullOrEmpty(button.getText()) ? buttonName : button.getText();
        this.scrollToCenterAndclickInElementJS(button);
        this.logPressedButtonName(buttonText);

        System.out.printf("[ИНФОРМАЦИЯ]: ожидание [%s] секунд.%n", timeouts.find(buttonName));
        TimeUnit.SECONDS.sleep(Long.parseLong(timeouts.find(buttonName)));
        $(this.getBy(PURCHASE_NUMBER_FIELD_ID)).waitUntil(clickable, longtime, polling);
    }

    /**
     * Производит нажатие на кнопку с указанным именем в требуемой строке таблицы 'Управление договорами'.
     *
     * @param buttonName имя кнопки
     * @param rowNumber  номер строки в таблице
     */
    public void clickOnButtonInContractManagementTableRowByNumber(String buttonName, int rowNumber)
            throws Exception {
        // Выводим отладочную информацию
        String locator = buttons.find(buttonName);
        String timeout = timeouts.find(buttonName);
        System.out.printf("[ИНФОРМАЦИЯ]: в метод переданы имя кнопки [%s] и номер строки [%d].%n",
                buttonName, rowNumber);
        System.out.printf(
                "[ИНФОРМАЦИЯ]: по данному имени кнопки найден локатор [%s] и задержка в секундах по умолчанию [%s].%n",
                locator, timeout);

        // Получаем коллекцию кнопок по указанному имени ( ожидаем, если кнопки не появились сразу )
        ElementsCollection allButtons = $$(this.getBy(locator)).filter(visible);
        if (allButtons.size() == 0) {
            System.out.printf("[ИНФОРМАЦИЯ]: кнопка не появилась, ожидание [%s] секунд.%n", timeout);
            TimeUnit.SECONDS.sleep(Long.parseLong(timeout));
            allButtons = $$(this.getBy(locator)).filter(visible);
        }

        // Проверяем размер полученной коллекции кнопок и переданный в метод номер строки
        System.out.printf("[ИНФОРМАЦИЯ]: по данному локатору найдено [%d] кнопок.%n", allButtons.size());
        Assert.assertNotEquals("[ОШИБКА]: не найдено ни одной кнопки на странице по данному локатору",
                0, allButtons.size());
        int lineNumber = rowNumber - 1;
        Assert.assertTrue(String.format("[ОШИБКА]: требуемый номер строки в таблице вне диапазона: [1..%d]",
                allButtons.size()), (lineNumber >= 0) && (lineNumber < allButtons.size()));

        // Получаем конкретную кнопку, которую требуется нажать и её текст для отладочной информации
        SelenideElement button = allButtons.get(lineNumber);
        String buttonText = this.isNullOrEmpty(button.getText()) ? buttonName : button.getText();

        // Располагаем кнопку по центру страницы и нажимаем на неё
        this.logButtonNameToPress(buttonName);
        this.scrollToCenterAndclickInElementJS(button);
        this.logPressedButtonName(buttonText);

        // Ожидаем завершения действия по нажатию кнопки требуемое количество секунд
        System.out.printf("[ИНФОРМАЦИЯ]: ожидание [%s] секунд.%n", timeout);
        TimeUnit.SECONDS.sleep(Long.parseLong(timeout));
        $(this.getBy(PURCHASE_NUMBER_FIELD_ID)).waitUntil(clickable, longtime, polling);
    }

    /**
     * Делает щелчок по ссылке [Управление торгами].
     */
    public void clickBiddingManageLink() throws Exception {
        $(this.getBy(BIDDING_MANAGE_LINK_ID)).waitUntil(visible, timeout, polling).scrollTo();
        TimeUnit.SECONDS.sleep(1);
        $(this.getBy(BIDDING_MANAGE_LINK_ID)).click();
        System.out.println("[ИНФОРМАЦИЯ]: произведено нажатие ссылки [Управление торгами].");
    }

    /**
     * Делает щелчок по вкладке с указанным номером лота, например [1. Лот 1].
     *
     * @param lotNumber номер лота
     */
    public void selectLotByNumber(String lotNumber) throws Exception {
        $(this.getBy(String.format(SELECT_LOT_XPATH, lotNumber))).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(1);
        System.out.printf("[ИНФОРМАЦИЯ]: произведен выбор вкладки лота [%s].%n", lotNumber);
    }

    /**
     * Делает щелчок по вкладке с указанным номером предмета предварительного отбора, например [1. Предмет предварительного отбора №1].
     *
     * @param objectNumber номер лота
     */
    public void selectPreselectionObjectByNumber(String objectNumber) throws Exception {
        $(this.getBy(String.format(SELECT_PRESELECTION_OBJECT_XPATH, objectNumber))).
                waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(1);
        System.out.printf("[ИНФОРМАЦИЯ]: произведен выбор вкладки предмета предварительного отбора [%s].%n",
                objectNumber);
    }


    /**
     * Получает значение поля [Статус лота] (Данные о лотах).
     */
    public void getLotStatus() {
        String lotStatus = $(this.getBy(LOT_STATUS_VALUE_ID)).waitUntil(visible, timeout, polling).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: Данные о лотах ---> Текущий статус лота   [%s].%n", lotStatus);
    }

    /**
     * Проверяет значение поля [Статус лота] (Данные о лотах).
     *
     * @param lotStatus ожидаемый статус лота
     */
    public void checkLotStatus(String lotStatus) {
        System.out.printf("[ИНФОРМАЦИЯ]: Данные о лотах ---> Ожидаемый статус лота [%s].%n", lotStatus);
        this.getLotStatus();
        SelenideElement lotStatusValue = $(this.getBy(LOT_STATUS_VALUE_ID));
        lotStatusValue.waitUntil(visible, timeout, polling).shouldHave(text(lotStatus));
    }

    /**
     * Проверяет значение поля [Статус торга] (Управление торгами).
     *
     * @param biddingStatus ожидаемый статус торга
     */
    public void checkBiddingStatus(String biddingStatus) {
        System.out.printf("[ИНФОРМАЦИЯ]: Данные о торгах ---> Ожидаемый статус торга [%s].%n", biddingStatus);
        $(this.getBy(BIDDING_STATUS_VALUE_ID)).waitUntil(visible, timeout, polling).shouldHave(text(biddingStatus));
    }

    /**
     * Получает значение поля [Дата начала торгов] (Управление закупкой).
     */
    public void getBiddingStartDateValue() {
        String value = "Идут торги";
        if ($(this.getBy(BIDDING_START_DATE_VALUE_ID)).isDisplayed())
            value = $(this.getBy(BIDDING_START_DATE_VALUE_ID)).waitUntil(visible, timeout, polling).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: Управление закупкой ---> Дата начала торгов [%s].%n", value);
    }
}
