package ru.PageObjects.Customer.Notice;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Создание запроса на согласование].
 * Created by Kirill G. Rydzyvylo on 04.03.2020.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class CreateApprovalRequestDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CreateApprovalRequestDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Текст заголовка окна",
                "//kendo-dialog-titlebar/div[text()='Создание запроса на согласование']");
        controls.add("Поле Дата согласования", "//app-form-group[@label='Дата согласования']//input");
        controls.add("Поле Плановая дата публикации закупки",
                "//app-form-group[@label='Плановая дата публикации закупки']//input");
        controls.add("Кнопка Отправить", "//kendo-dialog//button[@id='Send']");
        controls.add("Кнопка Отмена", "//kendo-dialog//button[@id='Cancel']");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна [Создание запроса на согласование].
     *
     * @return диалоговое окно [Создание запроса на согласование]
     */
    public CreateApprovalRequestDialog ifDialogOpened() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        this.waitLoadingImage();
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Создание запроса на согласование] не обнаружено",
                $(this.getBy(controls.find("Текст заголовка окна"))).waitUntil(exist, timeout, polling).isDisplayed());
        return this;
    }

    /**
     * Устанавливает значение поля с предварительной его очисткой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     * @return диалоговое окно [Создание запроса на согласование]
     */
    public CreateApprovalRequestDialog setFieldClearClickAndSendKeys(String fieldName, String value) throws Exception {
        this.waitClearClickAndSendKeys(this.getBy(controls.find(fieldName)), value);
        //--------------------------------------------------------------------------------------------------------------
        // Окно заполнения даты перекрывает элементы. При нажатии на [Enter] нет никакой реакции,
        // кнопок принудительного закрытия окон заполнения дат нет. Найдено решение, использовать кнопку [Esc] для
        // закрытия окна
        $($(this.getBy(controls.find(fieldName)))).sendKeys(Keys.ESCAPE);
        //--------------------------------------------------------------------------------------------------------------
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
        return this;
    }

    /**
     * Нажимает на указанную кнопку в диалоговом окне [Создание запроса на согласование].
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButtonByName(String buttonName) throws Exception {
        System.out.printf(
                "[ИНФОРМАЦИЯ]: произведено нажатие кнопки [%s] в диалоговом окне [Создание запроса на согласование].%n",
                buttonName);
        $(this.getBy(controls.find(buttonName))).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
        TimeUnit.SECONDS.sleep(15);
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        $(this.getBy(controls.find("Текст заголовка окна"))).shouldBe(disappear);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }
}
