package ru.PageObjects.Supplier.MyOrganization;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonPage;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы со страницей [Настройка ролеей пользователя]
 * ( Моя организация / Список пользователей / Настроить права доступа )
 * ( .kontur.ru/supplier/lk/AccessControl/UserRoles.aspx? ).
 * Created by Kirill G. Rydzyvylo on 29.04.2020.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class UserRolesPage extends CommonPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Раздел [Пользователь]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Фамилия]
    private static final String LAST_NAME_ID = "BaseMainContent_MainContent_fvLastName_lblValue";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Имя]
    private static final String FIRST_NAME_ID = "BaseMainContent_MainContent_fvFirstName_lblValue";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Таблица [Роли пользователя]

    //------------------------------------------------------------------------------------------------------------------
    // Флажок [Опытный пользователь]
    private static final String ADVANCED_USER_XPATH = "//td[contains(.,'Опытный пользователь')]/../td/input";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // endregion

    // region Блок управляющих кнопок в нижней части страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Сохранить]
    private static final String APPLY_ID = "BaseMainContent_MainContent_btnApply";
    //------------------------------------------------------------------------------------------------------------------

    // endregion
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary fieldNames = new Dictionary();   // все поля на странице
    private final Dictionary checkBoxNames = new Dictionary();// все флажки на странице
    private final Dictionary buttonNames = new Dictionary();  // все кнопки на странице
    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     *****************************************************************************************************************
     */
    public UserRolesPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Фамилия",LAST_NAME_ID);
        fieldNames.add("Имя",FIRST_NAME_ID);
        //--------------------------------------------------------------------------------------------------------------
        checkBoxNames.add("Опытный пользователь",ADVANCED_USER_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Сохранить",APPLY_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет содержимое тектового поля.
     */
    public void checkField(String value, String fieldName) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        verifyFieldContent(this.getBy(fieldNames.find(fieldName)), value);
    }

    /**
     * Устанавливает или сбрасывает флажок с указанным именем.
     *
     * @param checkBoxName имя флажка
     */
    public void setCheckBoxValue(String checkBoxName) {
        String message = "[ОШИБКА]: состояние флажка [%s] не изменилось после щелчка мышью";

        boolean oldValue = this.getCheckBoxValue(checkBoxName);
        $(this.getBy(checkBoxNames.find(checkBoxName))).waitUntil(exist, timeout, polling).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено изменение состояния флажка: [%s].%n", checkBoxName);
        boolean newValue = this.getCheckBoxValue(checkBoxName);
        System.out.printf("[ИНФОРМАЦИЯ]: флажок: [%s] находится %s отмеченном состоянии.%n",
                checkBoxName, ((newValue) ? "в" : "в не"));
        Assert.assertNotEquals(String.format(message, checkBoxName), oldValue, newValue);
    }

    /**
     * Возвращает значение флажка с указанным именем.
     *
     * @param checkBoxName имя флажка
     * @return значение флажка
     */
    private boolean getCheckBoxValue(String checkBoxName) {
        $(this.getBy(checkBoxNames.find(checkBoxName))).waitUntil(exist, timeout, polling).shouldBe(visible);
        return $(this.getBy(checkBoxNames.find(checkBoxName))).isSelected();
    }

    /**
     * Производит нажатие на кнопку.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.clickInElementJS(this.getBy(buttonNames.find(buttonName)));
        this.logPressedButtonName(buttonName);
        this.waitForPageLoad();
    }
}
