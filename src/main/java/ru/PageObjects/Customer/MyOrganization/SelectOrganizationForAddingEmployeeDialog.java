package ru.PageObjects.Customer.MyOrganization;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Выберите организацию]
 * ( после нажатия на кнопку [Выбрать из списка] на странице [заявка на добавление пользователя] ).
 * Created by Kirill G. Rydzyvylo on 23.04.2020.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */

public class SelectOrganizationForAddingEmployeeDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/

    // region Заголовок страницы

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок диалогового окна [Выберите организацию]
    private static final String WINDOW_HEADER_XPATH =
            "//div[@aria-describedby='selectOrganizationDialog']//span[@class='ui-dialog-title']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Поле [Организация]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Организация]
    private static final String ORGANIZATION_ID = "BaseMainContent_MainContent_ddlOrganizations_chzn";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Организация - Выбрать организацию]
    private static final String CHOOSE_ORGANIZATION_XPATH =
            "//div[@id='selectOrganizationDialog']//li[contains(.,'%s')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Блок кнопок внизу диалогового окна

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Организация - Список организаций]
    private static final String YES_BUTTON_XPATH =
            "//div[@aria-describedby='selectOrganizationDialog']//button/span[contains(.,'Да')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary fieldNames = new Dictionary();    // все поля диалогового окна
    private final Dictionary buttonNames = new Dictionary();   // все кнопки диалогового окна

    /*******************************************************************************************************************
     * Конструктор класса.
     * @param driver экземпляр WebDriver
     *****************************************************************************************************************
     */
    public SelectOrganizationForAddingEmployeeDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Организация", ORGANIZATION_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Да", YES_BUTTON_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна [Выбрать организацию].
     *
     * @return диалоговое окно [Выбрать организацию]
     */
    public SelectOrganizationForAddingEmployeeDialog ifDialogOpened() throws Exception {
        this.waitLoadingRectangle("5");
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Выбрать организацию] не обнаружено",
                $(this.getBy(WINDOW_HEADER_XPATH)).waitUntil(exist, timeout, polling).isDisplayed());
        return this;
    }

    /**
     * Устанавливает фокус ввода (курсор) в поле с указанным именем.
     *
     * @param fieldName имя поля
     */
    public SelectOrganizationForAddingEmployeeDialog clickOnFieldByName(String fieldName) throws Exception {
        $(this.getBy(fieldNames.find(fieldName))).waitUntil(visible, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        System.out.printf("[ИНФОРМАЦИЯ]: произведен переход в поле [%s].%n", fieldName);
        return this;
    }

    /**
     * Устанавливает фокус ввода (курсор) в поле с указанным именем.
     *
     * @param organizationName имя поля
     */
    public SelectOrganizationForAddingEmployeeDialog choseOrganization(String organizationName) throws Exception {
        String organization = String.format(CHOOSE_ORGANIZATION_XPATH, organizationName);
        $(this.getBy(organization)).waitUntil(visible, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        System.out.printf("[ИНФОРМАЦИЯ]: произведен выбор организации [%s].%n", organizationName);
        return this;
    }

    /**
     * Нажимает на кнопку для закрытия диалогового окна.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButtonToCloseDialog(String buttonName) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие кнопки [%s] в диалоговом окне [Выбрать организацию].%n",
                buttonName);
        $(this.getBy(buttonNames.find(buttonName))).waitUntil(clickable, timeout, polling).click();
        this.waitForPageLoad();
        $(this.getBy(WINDOW_HEADER_XPATH)).shouldBe(disappear);
    }
}
