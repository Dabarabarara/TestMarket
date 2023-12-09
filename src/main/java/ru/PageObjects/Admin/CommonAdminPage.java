package ru.PageObjects.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Абстрактная страница для работы с Администрированием площадки РТС тендер.
 * Created by Evgeniy Glushko on 24.03.2016.
 * Updated by Kirill G. Rydzyvylo on 11.08.2020.
 */
public class CommonAdminPage extends CommonPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Все ссылки в меню верхнего уровня ( заглавными буквами )
    //------------------------------------------------------------------------------------------------------------------
    private static final String ADMINISTRATOR_MAIN_MENU_ELEMENT_XPATH = "//li/span[contains(text(),'%s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Все ссылки под элементами меню верхнего уровня ( пункты подменю )
    private static final String ADMINISTRATOR_SUBMENU_ELEMENT_XPATH = "//li/a[contains(text(),'%s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Подтверждение действия
    private static final String YES_BUTTON_XPATH = "//button/span[contains(text(),'Да')]";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CommonAdminPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Открывает / закрывает меню [Закупки].
     */
    public void clickPurchasesLink() throws Exception {
        String xPathLocal = String.format(ADMINISTRATOR_MAIN_MENU_ELEMENT_XPATH, "Закупки");
        $(By.xpath(xPathLocal)).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        System.out.println("[ИНФОРМАЦИЯ]: произведено открытие/закрытие меню [Закупки].");
    }

    /**
     * Открывает / закрывает меню [Администрирование].
     */
    public void clickAdministrationLink() throws Exception {
        String xPathLocal = String.format(ADMINISTRATOR_MAIN_MENU_ELEMENT_XPATH, "Администрирование");
        $(By.xpath(xPathLocal)).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        System.out.println("[ИНФОРМАЦИЯ]: произведено открытие/закрытие меню [Администрирование].");
    }

    /**
     * Открывает / закрывает меню [Справочники].
     */
    private void clickReferencesLink() throws Exception {
        String xPathLocal = String.format(ADMINISTRATOR_MAIN_MENU_ELEMENT_XPATH, "Справочники");
        $(By.xpath(xPathLocal)).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        System.out.println("[ИНФОРМАЦИЯ]: произведено открытие/закрытие меню [Справочники].");
    }

    /**
     * Осуществляет переход на страницу [Поиск закупок].
     */
    public void goToSearchPurchasesPage() throws Exception {
        String xPathLocal = String.format(ADMINISTRATOR_SUBMENU_ELEMENT_XPATH, "Поиск закупок");
        if (!$(By.xpath(xPathLocal)).isDisplayed()) this.clickPurchasesLink();
        $(By.xpath(xPathLocal)).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        this.checkPageUrl("Поиск закупок");
        System.out.println("[ИНФОРМАЦИЯ]: произведен переход на страницу [Поиск закупок].");
    }

    /**
     * Осуществляет переход на страницу [Заявки на добавление / изменение информации пользователей].
     */
    public void goToEmployeeRequestListPage() throws Exception {
        String xPathLocal = String.format(ADMINISTRATOR_SUBMENU_ELEMENT_XPATH,
                "Заявки на добавление / изменение информации пользователей");
        if (!$(By.xpath(xPathLocal)).isDisplayed()) this.clickAdministrationLink();
        $(By.xpath(xPathLocal)).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        this.checkPageUrl("Заявки на добавление / изменение информации пользователей");
        System.out.println(
                "[ИНФОРМАЦИЯ]: произведен переход на страницу [Заявки на добавление / изменение информации пользователей].");
    }

    /**
     * Осуществляет переход на страницу [Ускорение процедур].
     */
    public void goToProcedureForcePage() throws Exception {
        String xPathLocal = String.format(ADMINISTRATOR_SUBMENU_ELEMENT_XPATH, "Ускорение процедур");
        if (!$(By.xpath(xPathLocal)).isDisplayed()) this.clickAdministrationLink();
        $(By.xpath(xPathLocal)).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        this.checkPageUrl("Ускорение процедур");
        System.out.println("[ИНФОРМАЦИЯ]: произведен переход на страницу [Ускорение процедур].");
    }

    /**
     * Осуществляет переход на страницу [Системные задачи].
     */
    public void goToProcedureSystemTasksPage() throws Exception {
        String xPathLocal = String.format(ADMINISTRATOR_SUBMENU_ELEMENT_XPATH, "Системные задачи");
        if (!$(By.xpath(xPathLocal)).isDisplayed()) this.clickAdministrationLink();
        $(By.xpath(xPathLocal)).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        this.checkPageUrl("Системные задачи");
        System.out.println("[ИНФОРМАЦИЯ]: произведен переход на страницу [Системные задачи].");
    }

    /**
     * Осуществляет переход на страницу [Заявки на аккредитацию].
     */
    public void goToApplicationsForAccreditationPage() throws Exception {
        String xPathLocal = String.format(ADMINISTRATOR_SUBMENU_ELEMENT_XPATH, "Заявки на аккредитацию");
        if (!$(By.xpath(xPathLocal)).isDisplayed()) this.clickAdministrationLink();
        $(By.xpath(xPathLocal)).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        this.checkPageUrl("Список заявок");
        System.out.println("[ИНФОРМАЦИЯ]: произведен переход на страницу [Заявки на аккредитацию].");
    }

    /**
     * Осуществляет переход на страницу [Реестр участников закупок].
     */
    public void goToRegistryOfParticipantsPage() throws Exception {
        String xPathLocal = String.format(ADMINISTRATOR_SUBMENU_ELEMENT_XPATH, "Реестр участников закупок");
        if (!$(By.xpath(xPathLocal)).isDisplayed()) this.clickReferencesLink();
        $(By.xpath(xPathLocal)).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        this.checkPageUrl("Реестр участников закупок");
        System.out.println("[ИНФОРМАЦИЯ]: произведен переход на страницу [Реестр участников закупок].");
    }

    /**
     * Осуществляет переход на страницу [Реестр заказчиков].
     */
    public void goToCustomerRegisterPage() throws Exception {
        String xPathLocal = String.format(ADMINISTRATOR_SUBMENU_ELEMENT_XPATH, "Реестр заказчиков");
        if (!$(By.xpath(xPathLocal)).isDisplayed()) this.clickReferencesLink();
        $(By.xpath(xPathLocal)).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        this.checkPageUrl("Реестр заказчиков");
        System.out.println("[ИНФОРМАЦИЯ]: произведен переход на страницу [Реестр заказчиков].");
    }

    /**
     * Нажимает на кнопку [Да] (Подтверждение действия).
     */
    void clickYesButton() throws Exception {
        $(By.xpath(YES_BUTTON_XPATH)).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        this.checkPageUrl("Информация о заявки после аккредитации");
        this.logPressedButtonName("Да");
    }
}
