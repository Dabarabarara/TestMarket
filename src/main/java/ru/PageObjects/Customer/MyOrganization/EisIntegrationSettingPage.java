package ru.PageObjects.Customer.MyOrganization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы со страницей [Настройки интеграции с ЕИС] (Главная / Заказчикам / Настройки интеграции с ЕИС).
 * Created by Vladimir V. Klochkov on 01.09.2016.
 * Updated by Vladimir V. Klochkov on 14.05.2018.
 */
public class EisIntegrationSettingPage extends CommonCustomerPage
{
    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     ******************************************************************************************************************/
    public EisIntegrationSettingPage(WebDriver driver) { super(driver); }

    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Логин / пароль]
    private static final String LOGIN_PASSWORD_TAB_XPATH = "//a[@class='k-link' and contains(.,'Логин / пароль')]";
    //------------------------------------------------------------------------------------------------------------------
    // Закладка [Расширенные настройки ЕИС]
    private static final String EXTENDED_EIS_SETTINGS_TAB_XPATH =
            "//a[@class='k-link' and contains(.,'Расширенные настройки ЕИС')]";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие закладок [Логин / пароль] и [Расширенные настройки ЕИС] для открытой части
     * (вход по логину и паролю а не по сертификату).
     */
    public void openPartTabsVisibilityValidation()
    {
        $(By.xpath(LOGIN_PASSWORD_TAB_XPATH)).waitUntil(exist, timeout).shouldBe(visible);
        $(By.xpath(EXTENDED_EIS_SETTINGS_TAB_XPATH)).waitUntil(exist, timeout).shouldBe(visible);
    }
}
