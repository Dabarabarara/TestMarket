package ru.PageObjects.Admin;

import Helpers.Dictionary;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы со страницей [Карточка организации]
 * (Справочники / Реестр участников закупок / Щелчок по ссылке в столбце [Полное наименование]).
 * Created by Vladimir V. Klochkov on 20.09.2016.
 * Updated by Kirill G. Rydzyvylo on 12.08.2020.
 */
public class OrganizationViewPage extends CommonAdminPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Карточка организации]:
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Создать счет]
    private static final String CREATE_ACCOUNT_BUTTON_ID = "BaseMainContent_MainContent_lbCreateAccount";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Сотрудники организации - перейти к списку]
    private static final String EMPLOYEES_LIST_ID = "BaseMainContent_MainContent_hlEmployeeList";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public OrganizationViewPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Сотрудники организации - перейти к списку", EMPLOYEES_LIST_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Делает щелчок по кнопке [Создать счет].
     */
    public void checkButtonCreateAccountPresenceAndClicksOnItIfPresent() throws Exception {
        SelenideElement button = $(this.getBy(CREATE_ACCOUNT_BUTTON_ID));
        if (button.exists()) {
            this.logButtonNameToPress("Создать счет");
            button.waitUntil(clickable, timeout, polling);
            this.clickInElementJS(button);
            this.logPressedButtonName("Создать счет");
            this.waitForPageLoad(15);
            button.waitUntil(disappears, longtime, polling).shouldNotBe(visible);
        } else System.out.println("[ИНФОРМАЦИЯ]: кнопка [Создать счет] не обнаружена.");
    }

    /**
     * Выполняет нажатие на ссылку [Сотрудники организации - перейти к списку]
     */
    public void clickOnOrganizationEmployees() throws Exception {
        $(this.getBy(EMPLOYEES_LIST_ID)).waitUntil(visible, timeout, polling).click();
        //--------------------------------------------------------------------------------------------------------------
        config.switchToNewWindowInBrowser();
        //--------------------------------------------------------------------------------------------------------------
        System.out.println("[ИНФОРМАЦИЯ]: произведено нажатие на элемент [Сотрудники организации - перейти к списку].");
    }
}
