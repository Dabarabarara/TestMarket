package ru.PageObjects.Admin;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы со страницей [Просмотр заявки на аккредитацию]
 * ( .kontur.ru/supplier/lk/Accreditation/RequestInfoTabed.aspx?RequestGuid= )
 * ( АДМИНИСТРИРОВАНИЕ / Заявки на аккредитацию / Список заявок / Просмотр заявки на аккредитацию ).
 * Created by Evgeniy Glushko on 19.04.2016.
 * Updated by Alexander S. Vasyurenko on 05.04.2021.
 */
public class AccreditationViewPage extends CommonAdminPage {
    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public AccreditationViewPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Вкладка [Карточка организации]
    private static final String ORGANIZATION_CARD_TAB_XPATH = "//li/a[contains(text(),'Карточка организации')]";
    //------------------------------------------------------------------------------------------------------------------
    // Вкладка [Документы площадки]
    private static final String SITE_DOCUMENTS_TAB_XPATH = "//li/a[contains(text(),'Документы площадки')]";
    //------------------------------------------------------------------------------------------------------------------
    // Вкладка [Заявка]
    private static final String APPLICATION_DETAILS_TAB_XPATH = "//li/a[contains(text(),'Заявка')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус]
    private static final String STATUS_LABEL_XPATH = "//label[contains(text(),'Статус')]/following::span[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип рассмотрения]
    private static final String ACCEPTANCE_TYPE_LABEL_XPATH =
            "//label[contains(text(),'Тип рассмотрения')]/following::span[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [ИНН]
    private static final String INN_LABEL_ID = "BaseMainContent_MainContent_lblInn";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Рассмотреть]
    private static final String CONSIDER_BUTTON_XPATH = "//a[@id='BaseMainContent_MainContent_lbtConsiderationRequest']";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Аккредитовать]
    private static final String ACCREDITATION_BUTTON_ID = "BaseMainContent_MainContent_lbtConfirmChanges";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование]
    private static final String NAME_LABEL_ID = "BaseMainContent_MainContent_lblOrganizationName";
    //------------------------------------------------------------------------------------------------------------------
    // Колонка [Тип документа] на вкладке [Документы площадки]
    private static final String TYPE_DOC_COLUMN_ALL_ROWS_XPATH =
            "//td[contains(@aria-describedby, 'ucDocumentListViewApplicationRequest_jqDocuments_TypeString')]";
    //------------------------------------------------------------------------------------------------------------------
    // Колонка [Документ] на вкладке [Документы площадки]
    private static final String DOC_COLUMN_ALL_ROWS_XPATH =
            "//td[contains(@aria-describedby, 'ucDocumentListViewApplicationRequest_jqDocuments_FileName')]/a";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Принудительно утвердить]
    private static final String CONFIRM_CONDITIONALLY_ACCEPTED_REQUEST_BUTTON_ID =
            "BaseMainContent_MainContent_lbtConfirmConditionallyAcceptedRequest";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дополнительная информация]
    private static final String REJECT_MESSAGE_TEXTAREA_ID = "rejectMessage";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка для скачивания [Платежное поручение]
    private static final String CONSIDERATION_PAYMENT_XPATH =
             "//a[contains(@id,'hlAcceleratedConsiderationPayment')][contains(.,'ссылке')]";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Методы страницы
     ******************************************************************************************************************/
    /**
     * Нажимает на кнопку [Рассмотреть].
     */
    private void clickConsiderButton() throws Exception {
        //--------------------------------------------------------------------------------------------------------------
        long slowSiteTimeoutInMinutes = Long.parseLong(config.getConfigParameter("SlowSiteTimeoutInMinutes"));
        boolean slowSite = slowSiteTimeoutInMinutes > 0;                // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
        System.out.println("[ИНФОРМАЦИЯ]: произведено нажатие кнопки [Рассмотреть].");
        $(this.getBy(CONSIDER_BUTTON_XPATH)).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(10);
        this.waitLoadingImage();
        //--------------------------------------------------------------------------------------------------------------
        if (slowSite) TimeUnit.MINUTES.sleep(slowSiteTimeoutInMinutes); // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
    }

    /**
     * Нажимает на кнопку [Аккредитация].
     */
    private void clickAccreditationButton() throws Exception {
        //--------------------------------------------------------------------------------------------------------------
        long slowSiteTimeoutInMinutes = Long.parseLong(config.getConfigParameter("SlowSiteTimeoutInMinutes"));
        boolean slowSite = slowSiteTimeoutInMinutes > 0;                // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
        System.out.println("[ИНФОРМАЦИЯ]: произведено нажатие кнопки [Аккредитация].");
        $(By.id(ACCREDITATION_BUTTON_ID)).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(10);
        this.waitLoadingImage();
        //--------------------------------------------------------------------------------------------------------------
        if (slowSite) TimeUnit.MINUTES.sleep(slowSiteTimeoutInMinutes); // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
    }

    /**
     * Нажимает на кнопку [Принудительно утвердить].
     */
    private void clickConfirmConditionallyAcceptedRequestButton() throws Exception {
        //--------------------------------------------------------------------------------------------------------------
        long slowSiteTimeoutInMinutes = Long.parseLong(config.getConfigParameter("SlowSiteTimeoutInMinutes"));
        boolean slowSite = slowSiteTimeoutInMinutes > 0;                // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
        System.out.println("[ИНФОРМАЦИЯ]: произведено нажатие кнопки [Принудительно утвердить].");
        $(By.id(CONFIRM_CONDITIONALLY_ACCEPTED_REQUEST_BUTTON_ID)).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(10);
        this.waitLoadingImage();
        //--------------------------------------------------------------------------------------------------------------
        if (slowSite) TimeUnit.MINUTES.sleep(slowSiteTimeoutInMinutes); // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
    }

    /**
     * Проверяет поле [Статус аккредитации] на вкладке [Карточка организации].
     *
     * @param expectedStatus ожидаемое значение
     */
    public void checkStatusLabel(String expectedStatus) {
        String actualStatus = $(this.getBy(STATUS_LABEL_XPATH)).waitUntil(visible, longtime, polling).getText();
        System.out.println("[ИНФОРМАЦИЯ]: статус заявки [" + actualStatus + "].");
        Assert.assertEquals("[ОШИБКА]: неверный статус заявки.", expectedStatus, actualStatus);
    }

    /**
     * Проверяет поле [Тип рассмотрения] на вкладке [Карточка организации].
     *
     * @param expectedType ожидаемое значение
     */
    public void checkTypeLabel(String expectedType) {
        String actualType = $(this.getBy(ACCEPTANCE_TYPE_LABEL_XPATH)).waitUntil(visible, longtime, polling).getText();
        System.out.println("[ИНФОРМАЦИЯ]: тип рассмотрения заявки [" + actualType + "].");
        Assert.assertEquals("[ОШИБКА]: неверный тип рассмотрения заявки.", expectedType, actualType);
    }

    /**
     * Проверяет поле [ИНН ]на вкладке [Карточка организации].
     *
     * @param innExpected ожидаемое значение
     */
    public void checkInnLabel(String innExpected) {
        String innActual = $(this.getBy(INN_LABEL_ID)).waitUntil(visible, timeout, polling).getText();
        System.out.println("[ИНФОРМАЦИЯ]: ИНН в заявке на аккредитацию [" + innActual + "].");
        Assert.assertEquals("[ОШИБКА]: неверный ИНН.", innExpected, innActual);
    }

    /**
     * Переходит на вкладку [Карточка организации].
     */
    private void clickOrganizationCardTab() throws Exception {
        SelenideElement organizationCardTab = $(By.xpath(ORGANIZATION_CARD_TAB_XPATH));
        organizationCardTab.waitUntil(clickable, timeout, polling).click();
        this.waitLoadingRectangle();
        System.out.println("[ИНФОРМАЦИЯ]: выполнен переход на вкладку [Карточка организации].");
    }

    /**
     * Переходит на вкладку [Документы площадки].
     */
    private void clickSiteDocumentTab() throws Exception {
        SelenideElement siteDocumentTab = $(this.getBy(SITE_DOCUMENTS_TAB_XPATH));
        siteDocumentTab.waitUntil(clickable, timeout, polling).click();
        this.waitLoadingRectangle();
        System.out.println("[ИНФОРМАЦИЯ]: выполнен переход на вкладку [Документы площадки].");
    }

    /**
     * Переходит на вкладку [Заявка].
     */
    private void clickApplicationDetailsTab() throws Exception {
        SelenideElement siteDocumentTab = $(this.getBy(APPLICATION_DETAILS_TAB_XPATH));
        siteDocumentTab.waitUntil(clickable, timeout, polling).click();
        this.waitLoadingRectangle();
        System.out.println("[ИНФОРМАЦИЯ]: выполнен переход на вкладку [Заявка].");
    }

    /**
     * Возвращает значение поля [Наименование].
     *
     * @return начение поля [Наименование]
     */
    public String getOrganizationName() {
        return $(this.getBy(NAME_LABEL_ID)).getText();
    }

    /**
     * Проверяет значения в колонке [Тип документа].
     *
     * @param size  размер списка типов документов
     * @param value ожидаемое значение
     */
    private void checkTypeDocColumn(int size, String value) {
        boolean result = false;
        System.out.printf("[ИНФОРМАЦИЯ]: размер списка типов докум. [%d],  ожидаемое значение [%s].%n", size, value);
        ElementsCollection typeDocColumnStringAllRows = $$(By.xpath(TYPE_DOC_COLUMN_ALL_ROWS_XPATH));
        typeDocColumnStringAllRows.get(0).waitUntil(visible, timeout, polling);
        typeDocColumnStringAllRows.shouldHave(CollectionCondition.size(size));
        for (String item : typeDocColumnStringAllRows.texts()) {
            if (item.contains(value)) {
                result = true;
                break;
            }
        }
        Assert.assertTrue("[ОШИБКА]: в колонке [Тип документа] неверное значение.", result);
    }

    /**
     * Проверяет значения в колонке [Документ].
     *
     * @param size  размер списка названий документов
     * @param value ожидаемое значение
     */
    private void checkDocumentColumn(int size, String value) {
        boolean result = false;
        System.out.printf("[ИНФОРМАЦИЯ]: размер списка назв. докум. [%d],  ожидаемое значение [%s].%n", size, value);
        ElementsCollection docColumnStringAllRows = $$(By.xpath(DOC_COLUMN_ALL_ROWS_XPATH));
        docColumnStringAllRows.get(0).waitUntil(visible, timeout, polling);
        docColumnStringAllRows.shouldHave(CollectionCondition.size(size));
        for (String item : docColumnStringAllRows.texts()) {
            if (item.contains(value)) {
                result = true;
                break;
            }
        }
        Assert.assertTrue("[ОШИБКА]: в колонке [Документ] неверное значение.", result);
    }

    /**
     * Проверяет прикрепленные к заявке на Аккредитацию документы для следующих типов аккредитации:
     * - Поставщик Физическое лицо
     * - Поставщик Юридическое лицо
     * - Поставщик Индивидуальный предприниматель
     */
    public void checkAttachedDocuments2x4ForSupplier() {
        this.checkTypeDocColumn(2, "Согласие на обработку персональных данных на площадке kontur.ru");
        this.checkTypeDocColumn(2, "Заявление о присоединении поставщика");
        this.checkDocumentColumn(2, "Согласие на обработку персональных данных.rtf");
        this.checkDocumentColumn(2, "Заявление о присоединении поставщика.rtf");
    }

    /**
     * Проверяет прикрепленные к заявке на Аккредитацию документы для следующих типов аккредитации:
     * - Заказчик Юридическое лицо
     * - Заказчик Индивидуальный предприниматель
     */
    public void checkAttachedDocuments2x4forCustomer() {
        this.checkTypeDocColumn(2, "Согласие на обработку персональных данных на площадке kontur.ru");
        this.checkTypeDocColumn(2, "Заявление о присоединении заказчика");
        this.checkDocumentColumn(2, "Согласие на обработку персональных данных.rtf");
        this.checkDocumentColumn(2, "Заявление о присоединении заказчика.rtf");
    }

    /**
     * Проверяет прикрепленные к заявке на Аккредитацию документы для следующих типов аккредитации:
     * - Юридическое лицо в ролях Заказчик и Поставщик
     * - Индивидуальный предприниматель в ролях Заказчик и Поставщик
     */
    public void checkAttachedDocuments3x6() {
        this.checkTypeDocColumn(3, "Согласие на обработку персональных данных на площадке kontur.ru");
        this.checkTypeDocColumn(3, "Заявление о присоединении поставщика");
        this.checkTypeDocColumn(3, "Заявление о присоединении заказчика");
        this.checkDocumentColumn(3, "Согласие на обработку персональных данных.rtf");
        this.checkDocumentColumn(3, "Заявление о присоединении поставщика.rtf");
        this.checkDocumentColumn(3, "Заявление о присоединении заказчика.rtf");
    }

    /**
     * Проверяет прикрепленные к заявке на Аккредитацию (ускоренную) документы для следующих типов аккредитации:
     * - Поставщик Юридическое лицо (включена ускоренная аккредитация)
     */
    public void checkAttachedDocuments3x6ForSupplier() {
        this.checkTypeDocColumn(3, "Согласие на обработку персональных данных на площадке kontur.ru");
        this.checkTypeDocColumn(3, "Заявление о присоединении поставщика");
        this.checkTypeDocColumn(3, "Договор-оферта по ускоренной аккредитации");
        this.checkDocumentColumn(3, "Согласие на обработку персональных данных.rtf");
        this.checkDocumentColumn(3, "Заявление о присоединении поставщика.rtf");
        this.checkDocumentColumn(3, "Оферта.docx");
    }

    /**
     * Общая часть рассмотрения заявки Пользователя для разных типов Аккредитации.
     */
    public void performConsideration() throws Exception {
        this.clickConsiderButton();                                         // нажимаем кнопку [Рассмотреть]
        this.clickApplicationDetailsTab();                                  // переходим на вкладку [Заявка]
        this.checkStatusLabel("На рассмотрении");               // проверяем статус заявки
        this.clickSiteDocumentTab();                                        // переходим на вкладку [Документы площадки]
    }

    /**
     * Общая часть рассмотрения заявки Пользователя для Ускоренной Аккредитации.
     */
    public void supplierCheckRequestStatus() throws Exception {
        this.clickApplicationDetailsTab();                                  // переходим на вкладку [Заявка]
        this.checkStatusLabel("Подана");                        // проверяем статус заявки
        this.checkTypeLabel("Ускоренная");                       // проверяем тип рассмотрения
    }

    /**
     * Cкачивает платежные поручения по ссылкам.
     */
    public void downloadConsiderationPayment() throws Exception {
        ElementsCollection requestDocumentsDownloadLinks =
                $$(this.getBy(CONSIDERATION_PAYMENT_XPATH)).filterBy(visible);
        for (SelenideElement requestDocumentsDownloadLink : requestDocumentsDownloadLinks) {
            this.checkPossibilityToDownloadFileWithGeneratedName(requestDocumentsDownloadLink,
                    requestDocumentsDownloadLink.getText(), 200);
        }
    }

    /**
     * Финальная часть аккредитации Пользователя на площадке.
     */
    public void performAccreditation() throws Exception {
        //--------------------------------------------------------------------------------------------------------------
        long slowSiteTimeoutInMinutes = Long.parseLong(config.getConfigParameter("SlowSiteTimeoutInMinutes"));
        boolean slowSite = slowSiteTimeoutInMinutes > 0;                  // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
        this.clickOrganizationCardTab();                                  // переходим на вкладку [Карточка организации]
        this.clickAccreditationButton();                                  // нажимаем кнопку [Аккредитация]
        this.clickYesButton();                                            // нажимаем кнопку [Да]
        //--------------------------------------------------------------------------------------------------------------
        if (slowSite) TimeUnit.MINUTES.sleep(slowSiteTimeoutInMinutes);   // Для ОЧЕНЬ медленного стенда TEST
        else TimeUnit.SECONDS.sleep(45);                           // Для всех остальных стендов
        //--------------------------------------------------------------------------------------------------------------
        this.clickApplicationDetailsTab();                                // переходим на вкладку [Заявка]
    }

    /**
     * Принудительное утверждение заявки на аккредитацию Оператором
     */
    public void сonfirmConditionallyAcceptedRequest() throws Exception {
        //--------------------------------------------------------------------------------------------------------------
        long slowSiteTimeoutInMinutes = Long.parseLong(config.getConfigParameter("SlowSiteTimeoutInMinutes"));
        boolean slowSite = slowSiteTimeoutInMinutes > 0;                  // Для ОЧЕНЬ медленного стенда TEST
        //--------------------------------------------------------------------------------------------------------------
        this.clickOrganizationCardTab();                                  // переходим на вкладку [Карточка организации]
        this.clickConfirmConditionallyAcceptedRequestButton();            // нажимаем кнопку [Принудительно утвердить]
        this.waitClearClickAndSendKeysById(REJECT_MESSAGE_TEXTAREA_ID,    // заполняет поле [Дополнительная информация]
                "Дополнительная информация");
        this.clickYesButton();                                            // нажимаем кнопку [Да]
        //--------------------------------------------------------------------------------------------------------------
        if (slowSite) TimeUnit.MINUTES.sleep(slowSiteTimeoutInMinutes);   // Для ОЧЕНЬ медленного стенда TEST
        else TimeUnit.SECONDS.sleep(45);                           // Для всех остальных стендов
        //--------------------------------------------------------------------------------------------------------------
        this.clickApplicationDetailsTab();                                // переходим на вкладку [Заявка]
    }
}
