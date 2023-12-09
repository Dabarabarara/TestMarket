package ru.PageObjects.Customer.Protocol;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы с диалоговым окном [Создание запроса на согласование].
 * Created by Vladimir V. Klochkov on 10.10.2017.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class CreateProtocolApprovalRequestDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CreateProtocolApprovalRequestDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Текст заголовка окна", "CreateApprovalRequestWindow_wnd_title");
        controls.add("Поле Дата согласования", "CreateApprovalRequestWindow_PlannedDecisionDate");
        controls.add("Поле Плановая дата публикации закупки", "CreateApprovalRequestWindow_PlannedPublishDate");
        controls.add("Радиокнопка Требуется решение каждого члена комиссии",
                "CreateApprovalRequestWindow_ApprovalTypeEveryone");
        controls.add("Радиокнопка Требуется решение от одного члена комиссии",
                "CreateApprovalRequestWindow_ApprovalTypeOneForAll");
        controls.add("Флажки в столбце Рабочая группа",
                "//*[@id='CreateApprovalRequestWorkGroupsGrid']//table/tbody/tr/td/input");
        controls.add("Кнопка Отправить", "SelectCreateApprovalRequestWindowButton");
        controls.add("Кнопка Отмена", "CancelCreateApprovalRequestWindowButton");
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
    public CreateProtocolApprovalRequestDialog ifDialogOpened() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        this.waitLoadingImage();
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Создание запроса на согласование] не обнаружено",
                $(By.id(controls.find("Текст заголовка окна"))).waitUntil(exist, timeout, polling).isDisplayed());
        return this;
    }

    /**
     * Устанавливает значение поля с предварительной его очисткой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     * @return диалоговое окно [Создание запроса на согласование]
     */
    public CreateProtocolApprovalRequestDialog setFieldClearClickAndSendKeys(String fieldName, String value)
            throws Exception {
        this.waitClearClickAndSendKeysById(controls.find(fieldName), value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
        return this;
    }

    /**
     * Выбирает в переключателе опцию с указанным именем.
     *
     * @param radioButtonName имя опции
     * @return диалоговое окно [Создание запроса на согласование]
     */
    public CreateProtocolApprovalRequestDialog selectRadioButton(String radioButtonName) throws Exception {
        $(By.id(controls.find(radioButtonName))).waitUntil(exist, timeout, polling).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено переключение в положение: [%s].%n", radioButtonName);
        TimeUnit.SECONDS.sleep(1);
        $(By.id(controls.find(radioButtonName))).waitUntil(exist, timeout, polling).shouldBe(selected);
        return this;
    }

    /**
     * Отмечает в таблице [Рабочая группа] строку с указанным порядковым номером (сверху - вниз)
     *
     * @param orderNo порядковый номер строки
     * @return диалоговое окно [Создание запроса на согласование]
     */
    public CreateProtocolApprovalRequestDialog selectWorkGroupByOrderNo(int orderNo) {
        System.out.printf(
                "[ИНФОРМАЦИЯ]: попытка выбрать строку с порядковым номером [%d] в таблице [Рабочая группа].%n", orderNo);
        ElementsCollection workGroups = $$(By.xpath(controls.find("Флажки в столбце Рабочая группа")));
        int totalLines = workGroups.size();
        int lineNo = orderNo - 1;
        Assert.assertTrue("[ОШИБКА]: номер строки должен быть числом от 0 до N", lineNo >= 0);
        Assert.assertTrue("[ОШИБКА]: не существующий порядковый номер строки", totalLines >= lineNo);
        SelenideElement workGroup = workGroups.get(lineNo);
        boolean oldValue = workGroup.waitUntil(visible, timeout, polling).isSelected();
        System.out.println("[ИНФОРМАЦИЯ]: строка находится в " + ((oldValue) ? "" : "не ") + "отмеченном состоянии.");
        workGroup.click();
        boolean newValue = workGroup.isSelected();
        System.out.println("[ИНФОРМАЦИЯ]: строка находится в " + ((newValue) ? "" : "не ") + "отмеченном состоянии.");
        Assert.assertNotEquals("[ОШИБКА]: строка не была отмечена после щелчка мышью", oldValue, newValue);
        System.out.printf(
                "[ИНФОРМАЦИЯ]: строка с порядковым номером [%d] в таблице [Рабочая группа] выбрана успешно.%n", orderNo);
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
        $(By.id(controls.find(buttonName))).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
        TimeUnit.SECONDS.sleep(15);
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        $(By.id(controls.find("Текст заголовка окна"))).shouldBe(disappear);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }
}
