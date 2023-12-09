package ru.PageObjects.Customer.Notice;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Выбор заказчика].
 * Created by Kirill G. Rydzyvylo on 28.02.2020.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class CustomerSelectionDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CustomerSelectionDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Текст заголовка окна", "//kendo-dialog-titlebar//div[contains(@class,'k-dialog-title')]");
        controls.add("Поле Наименование", "//kendo-dialog//app-form-group[@label='Наименование']//input");
        controls.add("Поле ИНН", "//kendo-dialog//app-form-group[@label='ИНН']//input");
        controls.add("Поле КПП", "//kendo-dialog//app-form-group[@label='КПП']//input");
        controls.add("Кнопка Поиск", "//kendo-dialog//button[text()='Поиск']");
        controls.add("Результаты поиска, 1 строка не выбрана",
                "//kendo-dialog//tbody/tr[1 and @class='ng-star-inserted']");
        controls.add("Результаты поиска, 1 строка выбрана",
                "//kendo-dialog//tbody/tr[1 and contains(@class,'k-state-selected')]/td");
        controls.add("Кнопка Выбрать", "Select");
        controls.add("Кнопка Отмена", "Cancel");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна [Выбор заказчика].
     *
     * @return диалоговое окно [Выбор заказчика]
     */
    public CustomerSelectionDialog ifDialogOpened() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        this.waitLoadingImage();
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Выбор заказчика] не обнаружено",
                $(this.getBy(controls.find("Текст заголовка окна"))).waitUntil(exist, timeout, polling).isDisplayed());
        return this;
    }

    /**
     * Устанавливает значение поля с предварительной его очисткой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     * @return диалоговое окно [Выбор заказчика]
     */
    public CustomerSelectionDialog setFieldClearClickAndSendKeys(String fieldName, String value) {
        $(this.getBy(controls.find(fieldName))).waitUntil(visible, timeout, polling).setValue(value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
        return this;
    }

    /**
     * Нажимает на указанную кнопку в диалоговом окне [Выбор заказчика].
     *
     * @param buttonName     имя кнопки
     * @param shouldBeClosed после нажатия кнопки диалоговое окно должно закрыться
     * @return диалоговое окно [Выбор заказчика]
     */
    public CustomerSelectionDialog clickOnButtonByName(String buttonName, boolean shouldBeClosed) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие кнопки [%s] в диалоговом окне [Выбор заказчика].%n",
                buttonName);
        $(this.getBy(controls.find(buttonName))).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        if (shouldBeClosed) {
            this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
            $(this.getBy(controls.find("Текст заголовка окна"))).shouldBe(disappear);
            this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        }
        return this;
    }

    /**
     * Выбирает первую строку в результатах поиска (должна быть подсвечена после выбора).
     *
     * @return диалоговое окно [Выбор заказчика]
     */
    public CustomerSelectionDialog select1stLineInSearchResults() throws Exception {
        System.out.println("[ИНФОРМАЦИЯ]: выбираем первую строку в результатах поиска (делаем щелчок мышью).");
        $(this.getBy(controls.find("Результаты поиска, 1 строка не выбрана"))).
                waitUntil(visible, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("[ИНФОРМАЦИЯ]: проверяем, что первая строка в результатах поиска выбрана.");
        $(this.getBy(controls.find("Результаты поиска, 1 строка выбрана"))).shouldBe(visible);
        System.out.println("[ИНФОРМАЦИЯ]: первая строка в результатах поиска выбрана успешно.");
        return this;
    }

    /**
     * Сохраняет в динамических параметрах теста ИНН организации и её название
     *
     * @return диалоговое окно [Выбор заказчика]
     */
    public CustomerSelectionDialog storeOrganizationNameWithInn() {
        String inn = $(this.getBy(controls.find("Поле ИНН"))).waitUntil(visible, timeout, polling).getValue();
        String name = $(this.getBy(controls.find("Результаты поиска, 1 строка выбрана"))).
                waitUntil(visible, timeout, polling).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: получены значения ИНН [%s] и названия [%s] выбранной организации.%n",
                inn, name);
        config.setParameter("INN" + inn, name);
        return this;
    }
}
