package ru.PageObjects.Supplier;

import Helpers.Dictionary;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonPage;

import java.util.Hashtable;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс описывающий общие контролы для работы Поставщиком.
 * Created by Evgeniy Glushko on 24.03.2016.
 * Updated by Vladimir V. Klochkov on 13.04.2021.
 */
public class CommonSupplierPage extends CommonPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    public static final String LOADING_XPATH =
            "//div[@class='k-loading-image' or @class='k-loading-mask' or @class='spinner' or @class='loading-text']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопки [Войти по ЭЦП] / [Выберите сертификат]
    public static final String SELECT_CERTIFICATE_BUTTON_ID = "btnSelectCert";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок колонки [Сертификат] в списке сертификатов окна [Выберите сертификат]
    public static final String CERTIFICATE_COL_HEADER_XPATH =
            "//*[@id='jqgh_dlgSelectCertificate_CertTable_Name']//div[contains(.,'Сертификат')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [OK] в окне [Выберите сертификат]
    public static final String OK_BUTTON_ID = "btnOK";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Напомнить позже] в диалоговом окне [Уведомление]
    private static final String REMIND_ME_LATER_BUTTON_XPATH =
            "//div[@id='needToPayAccreditation']/following-sibling::div[1]//button[contains(., 'Напомнить позже')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [Получить спецсчет] в рекламном диалоговом окне [ПОСТАВЩИК, ВНИМАНИЕ!]
    private static final String GET_SPECIAL_ACCOUNT_XPATH =
            "//div[@id='new-requirements-504']//a[contains(.,'Получить спецсчет')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка закрытия диалогового окна [ПОСТАВЩИК, ВНИМАНИЕ!] [X]
    private static final String CLOSE_GET_SPECIAL_ACCOUNT_POPUP_XPATH =
            "//div[@id='new-requirements-504']/div/div/div/img";
    //------------------------------------------------------------------------------------------------------------------
    // Диалоговое окно [ОБРАЩАЕМ ВАШЕ ВНИМАНИЕ!]
    private static final String NEW_REQUIREMENTS_CERTIFICATE_XPATH = "//div[@id='new-requirements-certificate']/div";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка закрытия диалогового окна [ОБРАЩАЕМ ВАШЕ ВНИМАНИЕ!] [X]
    private static final String CLOSE_NEW_REQUIREMENTS_CERTIFICATE_POPUP_XPATH =
            "//div[@id='new-requirements-certificate']/div/div[1]/div/img";
    //------------------------------------------------------------------------------------------------------------------
    // Диалоговое окно [ДЛЯ ВАС УЖЕ ОДОБРЕНО]
    private static final String FINANCIAL_SERVICES_OFFERS_XPATH = "//*[@id='financial-services-offers-popup']/div/div";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка закрытия диалогового окна [ДЛЯ ВАС УЖЕ ОДОБРЕНО] [X]
    private static final String CLOSE_FINANCIAL_SERVICES_OFFERS_POPUP_XPATH =
            "//*[@id='financial-services-offers-popup']//a[contains(@onclick, 'close')]";
    //------------------------------------------------------------------------------------------------------------------
    // Диалоговое окно [Внимание! Долг XXX руб.!]
    private static final String ATTENTION_DEBT_SUM_XPATH = "//span[@class='ui-dialog-title']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка закрытия диалогового окна [Внимание! Долг XXX руб.!] [X]
    private static final String CLOSE_ATTENTION_DEBT_SUM_POPUP_XPATH =
            "//span[@class='ui-button-icon-primary ui-icon ui-icon-closethick']";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [АКТУАЛЬНО]
    private static final String LINK_TO_CURRENT_OFFERS_TAB_XPATH = "//span[contains(.,'Актуально')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [ЗАКУПКИ]
    private static final String LINK_TO_AUCTIONS_TAB_XPATH = "//span[contains(.,'Закупки')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [Поиск закупок]
    private static final String SEARCH_PURCHASE_LINK_XPATH = "//a[contains(.,'Поиск закупок')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [Запросы на разъяснение документации]
    private static final String DOCUMENTATION_CLARIFICATION_REQUESTS_LINK_XPATH =
            "//a[contains(.,'Запросы на разъяснение документации')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [Запросы на разъяснение результатов]
    private static final String RESULT_CLARIFICATION_REQUESTS_LINK_XPATH =
            "//a[contains(.,'Запросы на разъяснение результатов')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [Запросы на разъяснение заявок]
    private static final String REQUESTS_FOR_CLARIFICATION_OF_APPLICATIONS_LINK_XPATH =
            "//a[contains(.,'Запросы на разъяснение заявок')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [ФИНАНСЫ И ДОКУМЕНТЫ]
    private static final String LINK_TO_FINANCE_AND_DOCUMENTS_TAB_XPATH = "//span[contains(.,'Финансы и документы')]";
    //------------------------------------------------------------------------------------------------------------------
    //Ссылка [Счета и транзакции]
    private static final String ACCOUNTS_AND_TRANSACTIONS_LINK_XPATH = "//a[contains(.,'Счета и транзакции')]";
    //------------------------------------------------------------------------------------------------------------------
    //Ссылка [Мои договоры]
    private static final String MY_CONTRACT_LINK_XPATH = "//a[contains(.,'Мои договоры')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [МОЯ ОРГАНИЗАЦИЯ]
    private static final String MY_ORGANIZATION_TAB_XPATH = "//span[contains(.,'Моя организация')]";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [Информация об организации]
    private static final String INFO_ABOUT_ORGANIZATION_LINK_XPATH = "//a[contains(.,'Информация об организации')]";
    //------------------------------------------------------------------------------------------------------------------
    // ссылки под верхним меню
    private static final String LINK_UNDER_TOP_MENU_XPATH = "//a[contains(.,'%s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Поиск] на странице [Закупки]
    private static final String SEARCH_BUTTON_ID = "BaseMainContent_MainContent_btnSearch";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Очистить] на странице [Закупки]
    private static final String CANCEL_BUTTON_ID = "BaseMainContent_MainContent_btnCancel";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CommonSupplierPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Закрывает рекламные диалоговые окна если они отображаютя на экране.
     */
    public void checkAdvertisingDialogWindow() {
        Hashtable<String, String> dialogs = new Hashtable<String, String>(); // <Локатор элемента окна>, <Кнопка закрытия окна>
        dialogs.put(REMIND_ME_LATER_BUTTON_XPATH, REMIND_ME_LATER_BUTTON_XPATH);
        dialogs.put(GET_SPECIAL_ACCOUNT_XPATH, CLOSE_GET_SPECIAL_ACCOUNT_POPUP_XPATH);
        dialogs.put(NEW_REQUIREMENTS_CERTIFICATE_XPATH, CLOSE_NEW_REQUIREMENTS_CERTIFICATE_POPUP_XPATH);
        dialogs.put(FINANCIAL_SERVICES_OFFERS_XPATH, CLOSE_FINANCIAL_SERVICES_OFFERS_POPUP_XPATH);
        dialogs.put(ATTENTION_DEBT_SUM_XPATH, CLOSE_ATTENTION_DEBT_SUM_POPUP_XPATH);
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        final boolean[] found = {false};
        System.out.println("[ИНФОРМАЦИЯ]: производим поиск открытых диалоговых окон и закрываем их при обнаружении.");
        do {
            found[0] = false;
            dialogs.forEach((key, value) -> {
                if ($(this.getBy(key)).isDisplayed()) {
                    $(this.getBy(value)).waitUntil(clickable, timeout, polling).click();
                    System.out.printf("[ИНФОРМАЦИЯ]: найдено окно диалога с локатором [%s], закрываем его.%n", key);
                    $(this.getBy(value)).waitUntil(disappears, timeout, polling).shouldNotBe(visible);
                    found[0] = true;
                }
            });
        } while (found[0]);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Открывает страницу.
     *
     * @param pageName имя страницы
     */
    public void openPage(String pageName) throws Exception {
        switch (pageName) {
            //----------------------------------------------------------------------------------------------------------
            case "Поиск закупок":
                this.clickTopMenuItem("ЗАКУПКИ");
                this.clickLinkUnderMenu("Поиск закупок");
                break;
            //----------------------------------------------------------------------------------------------------------
            case "Запросы на разъяснение документации":
                this.clickTopMenuItem("ЗАКУПКИ");
                this.clickLinkUnderMenu("Запросы на разъяснение документации");
                break;
            //----------------------------------------------------------------------------------------------------------
            case "Запросы на разъяснение результатов":
                this.clickTopMenuItem("ЗАКУПКИ");
                this.clickLinkUnderMenu("Запросы на разъяснение результатов");
                break;
            //----------------------------------------------------------------------------------------------------------
            case "Запросы на разъяснение заявок":
                this.clickTopMenuItem("ЗАКУПКИ");
                this.clickLinkUnderMenu("Запросы на разъяснение заявок");
                break;
            //----------------------------------------------------------------------------------------------------------
            case "Мои Торги/Переторжки":
                this.clickTopMenuItem("ЗАКУПКИ");
                this.clickLinkUnderMenu("Мои Торги/Переторжки");
                break;
            //----------------------------------------------------------------------------------------------------------
            case "Счета компании":
                this.clickTopMenuItem("ФИНАНСЫ И ДОКУМЕНТЫ");
                this.clickLinkUnderMenu("Счета и транзакции");
                break;
            //----------------------------------------------------------------------------------------------------------
            case "Финансы и документы":
                this.clickTopMenuItem("ФИНАНСЫ И ДОКУМЕНТЫ");
                break;
            //----------------------------------------------------------------------------------------------------------
            case "Мои договоры":
                this.clickTopMenuItem("ФИНАНСЫ И ДОКУМЕНТЫ");
                this.clickLinkUnderMenu("Мои договоры");
                break;
            //----------------------------------------------------------------------------------------------------------
            case "Мои заявки":
                this.clickTopMenuItem("ЗАКУПКИ");
                this.clickLinkUnderMenu("Мои заявки");
                break;
            //----------------------------------------------------------------------------------------------------------
            default:
                Assert.fail(String.format(
                        "[ОШИБКА]: в метод (openPage) передан некорректный параметр (pageName): '%s'", pageName));
                break;
            //----------------------------------------------------------------------------------------------------------
        }

        this.waitForPageLoad();
    }

    /**
     * Делает щелчок по элементу главного меню.
     *
     * @param itemName имя элемента главного меню
     */
    public void clickTopMenuItem(String itemName) throws Exception {
        Dictionary itemNames = new Dictionary();
        itemNames.add("АКТУАЛЬНО", LINK_TO_CURRENT_OFFERS_TAB_XPATH);
        itemNames.add("ЗАКУПКИ", LINK_TO_AUCTIONS_TAB_XPATH);
        itemNames.add("ФИНАНСЫ И ДОКУМЕНТЫ", LINK_TO_FINANCE_AND_DOCUMENTS_TAB_XPATH);
        itemNames.add("МОЯ ОРГАНИЗАЦИЯ", MY_ORGANIZATION_TAB_XPATH);

        $(By.xpath(itemNames.find(itemName))).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено открытие/закрытие меню: [%s].%n", itemName);
    }

    /**
     * Делает щелчок по ссылке в подменю первого уровня.
     *
     * @param linkName имя ссылки в подменю первого уровня
     */
    public void clickLinkUnderMenu(String linkName) throws Exception {
        Dictionary topMenuItems = new Dictionary();
        topMenuItems.add("Мои Торги/Переторжки", "ЗАКУПКИ");
        topMenuItems.add("Запросы на разъяснение документации", "ЗАКУПКИ");
        topMenuItems.add("Запросы на разъяснение результатов", "ЗАКУПКИ");
        topMenuItems.add("Запросы на разъяснение заявок", "ЗАКУПКИ");
        topMenuItems.add("Поиск закупок", "ЗАКУПКИ");
        topMenuItems.add("Мои заявки", "ЗАКУПКИ");
        topMenuItems.add("Информация об организации", "МОЯ ОРГАНИЗАЦИЯ");
        topMenuItems.add("Счета и транзакции", "ФИНАНСЫ И ДОКУМЕНТЫ");
        topMenuItems.add("Мои договоры", "ФИНАНСЫ И ДОКУМЕНТЫ");

        Dictionary links = new Dictionary();
        links.add("Поиск закупок", SEARCH_PURCHASE_LINK_XPATH);
        links.add("Мои Торги/Переторжки", String.format(LINK_UNDER_TOP_MENU_XPATH, linkName));
        links.add("Запросы на разъяснение документации", DOCUMENTATION_CLARIFICATION_REQUESTS_LINK_XPATH);
        links.add("Запросы на разъяснение результатов", RESULT_CLARIFICATION_REQUESTS_LINK_XPATH);
        links.add("Запросы на разъяснение заявок", REQUESTS_FOR_CLARIFICATION_OF_APPLICATIONS_LINK_XPATH);
        links.add("Мои заявки", String.format(LINK_UNDER_TOP_MENU_XPATH, linkName));
        links.add("Информация об организации", INFO_ABOUT_ORGANIZATION_LINK_XPATH);
        links.add("Счета и транзакции", ACCOUNTS_AND_TRANSACTIONS_LINK_XPATH);
        links.add("Мои договоры", MY_CONTRACT_LINK_XPATH);

        Dictionary pageNames = new Dictionary();
        pageNames.add("Мои Торги/Переторжки", "Мои Торги/Переторжки");
        pageNames.add("Поиск закупок", "Поиск закупок");
        pageNames.add("Запросы на разъяснение документации", "Запросы на разъяснение документации");
        pageNames.add("Запросы на разъяснение результатов", "Запросы на разъяснение результатов");
        pageNames.add("Запросы на разъяснение заявок", "Запросы на разъяснение заявок");
        pageNames.add("Мои заявки", "Мои заявки");
        pageNames.add("Информация об организации", "Информация об организации");
        pageNames.add("Счета и транзакции", "Счета компании");
        pageNames.add("Мои договоры", "Мои договоры");

        if (!$(By.xpath(links.find(linkName))).isDisplayed()) this.clickTopMenuItem(topMenuItems.find(linkName));
        $(By.xpath(links.find(linkName))).waitUntil(clickable, timeout, polling).click();
        this.checkPageUrl(pageNames.find(linkName));
        System.out.printf("[ИНФОРМАЦИЯ]: произведен переход на страницу: [%s].%n", linkName);
    }

    /**
     * Нажимает кнопку с указанным именем.
     *
     * @param buttonName имя кнопки
     */
    public void clickButton(String buttonName) throws Exception {
        Dictionary buttonNames = new Dictionary();
        buttonNames.add("Поиск", SEARCH_BUTTON_ID);
        buttonNames.add("Очистить", CANCEL_BUTTON_ID);

        this.logButtonNameToPress(buttonName);
        SelenideElement button = $(this.getBy(buttonNames.find(buttonName))).waitUntil(exist, timeout, polling);
        this.scrollToCenterAndclickInElementJS(button);
        this.logPressedButtonName(buttonName);
        this.waitForPageLoad();
        this.waitLoadingRectangle("15");
    }
}
