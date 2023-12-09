package ru.PageObjects.Supplier.MyOrganization;

import Helpers.Dictionary;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Supplier.CommonSupplierPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывающий работу со страницой [Информация об организации].
 * ( .kontur.ru/supplier/lk/Accreditation/OrganizationViewTabed.aspx )
 * Created by Evgeniy Glushko on 29.04.2016.
 * Updated by Alexander S. Vasyurenko on 05.04.2021.
 */
public class OrganizationInfoPage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Вкладка [Карточка организации]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок вкладки [Карточка организации]
    private static final String ORGANIZATION_CARD_TAB_XPATH =
            "//a[@class='ui-tabs-anchor' and contains(.,'Карточка организации')]";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Состояние аккредитации] строка [Аккредитация на ЭТП]
    private static final String ACCREDITATION_STATE_XPATH = "//tr[@class='ordinal']/td[2]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Создать заявку на внесение изменений]
    private static final String CREATE_REQUEST_BUTTON_ID = "BaseMainContent_MainContent_hlCreateRequest";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование]
    private static final String NAME_ID = "BaseMainContent_MainContent_lblOrganizationName";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Вкладка [Документы]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок вкладки [Документы]
    private static final String DOCUMENTS_TAB_XPATH = "//a[@class='ui-tabs-anchor' and contains(.,'Документы')]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица со списком документов, все ссылки для скачивания в столбце [Документ]
    private static final String DOCUMENTS_TABLE_ALL_DOWNLOAD_DOCS_LINKS_XPATH =
            "//*[@id='BaseMainContent_MainContent_ucDocumentList_jqDocuments']//td/" +
                    "a[contains(@href, '/files/FileDownloadHandler.')]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица со списком документов, ссылки для скачивания файла 'Оферта.docx' для ускоренной аккредитации
    private static final String DOCUMENTS_TABLE_DOWNLOAD_OFFER_DOC_LINK_XPATH = "//a[contains(.,'Оферта.docx')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Вкладка [Действия]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок вкладки [Действия]
    private static final String ACTIONS_TAB_XPATH = "//a[@class='ui-tabs-anchor' and contains(.,'Действия')]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица со списком действий все строки таблицы
    private static final String ACTIONS_TABLE_ALL_ROWS_XPATH =
            "//*[@id='BaseMainContent_MainContent_jqActions']/tbody/tr[contains(@class, 'jqgrow')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Вкладка [Сведения о видах экономической деятельности]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок вкладки [Сведения о видах экономической деятельности]
    private static final String OKVED2_INFO_TAB_XPATH =
            "//a[@class='ui-tabs-anchor' and contains(.,'Сведения о видах экономической деятельности')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Номенклатура (ОКВЭД2) Выбрать]
    private static final String SELECT_OKVED2_BUTTON_ID = "BaseMainContent_MainContent_Okved2Field_btnSelect";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary tabNames = new Dictionary();    // все вкладки на странице
    private final Dictionary buttonNames = new Dictionary(); // все кнопки на странице

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public OrganizationInfoPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        tabNames.add("Карточка организации", ORGANIZATION_CARD_TAB_XPATH);
        tabNames.add("Документы", DOCUMENTS_TAB_XPATH);
        tabNames.add("Действия", ACTIONS_TAB_XPATH);
        tabNames.add("Сведения о видах экономической деятельности", OKVED2_INFO_TAB_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Создать заявку на внесение изменений", CREATE_REQUEST_BUTTON_ID);
        buttonNames.add("Номенклатура (ОКВЭД2) Выбрать", SELECT_OKVED2_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Переключает на требуемую вкладку.
     *
     * @param tabName имя вкладки
     */
    public void clickOnTab(String tabName) throws Exception {
        SelenideElement tab = $(this.getBy(tabNames.find(tabName)));
        this.scrollToCenter(tab);
        tab.waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(1);
        System.out.printf("[ИНФОРМАЦИЯ]: Произведено нажатие на вкладку: [%s].%n", tabName);
    }

    /**
     * Проверяет наличие указанной кнопки.
     */
    public void checkButtonExist(String buttonName) {
        $(this.getBy(buttonNames.find(buttonName))).waitUntil(visible, timeout, polling);
        System.out.printf("[ИНФОРМАЦИЯ]: произведена проверка наличия кнопки [%s].%n", buttonName);
    }

    /**
     * Делает щелчок мышью по указанной кнопке.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.clickInElementJS(this.getBy(buttonNames.find(buttonName)));
        this.logPressedButtonName(buttonName);
        this.waitForPageLoad(5);
    }

    /**
     * Проверяет поле [Наименование].
     */
    public void checkOrganizationNameField() {
        String expected = config.getParameter("AccreditationName");
        String actual = $(this.getBy(NAME_ID)).waitUntil(visible, timeout, polling).getText();
        Assert.assertEquals("[ОШИБКА]: наименование не совпало", expected, actual);
    }

    /**
     * Проверяет содержимое ячейки таблицы в колонке [Состояние аккредитации], строка [Аккредитация на ЭТП].
     */
    public void checkOrganizationAccreditationState() {
        String expected = "Активна";
        String actual = $(this.getBy(ACCREDITATION_STATE_XPATH)).waitUntil(visible, timeout, polling).getText();
        Assert.assertEquals("[ОШИБКА]: состояние не совпало", expected, actual);
    }

    /**
     * Проверяет, что список действий не пустой.
     */
    public void checkThatTheActionsTableIsNotEmpty() {
        $$(this.getBy(ACTIONS_TABLE_ALL_ROWS_XPATH)).filterBy(visible).shouldHave(sizeGreaterThan(0));
    }

    /**
     * Проверяет, что список ссылок для скачивания документов организации не пустой.
     */
    public void checkThatTheDocumentDownloadLinksColumnIsNotEmpty() {
        $$(this.getBy(DOCUMENTS_TABLE_ALL_DOWNLOAD_DOCS_LINKS_XPATH)).filterBy(clickable).shouldHave(sizeGreaterThan(0));
    }

    /**
     * Пытается скачать документ по первой сверху ссылке.
     */
    public void downloadFirstAttachedDocumentWithVerification() throws Exception {
        this.downloadFileByLink(
                $$(this.getBy(DOCUMENTS_TABLE_ALL_DOWNLOAD_DOCS_LINKS_XPATH)).filterBy(clickable).get(0)
        );
    }

    /**
     * Пытается скачать документ 'Оферта.docx' по ссылке.
     */
    public void downloadOfferAttachedDocumentWithVerification() throws Exception {
        this.downloadFileByLink(
                $(this.getBy(DOCUMENTS_TABLE_DOWNLOAD_OFFER_DOC_LINK_XPATH))
        );
    }
}
