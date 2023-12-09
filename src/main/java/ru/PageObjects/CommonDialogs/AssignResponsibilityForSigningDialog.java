package ru.PageObjects.CommonDialogs;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы с диалоговым окном [Назначить ответственного для подписания].
 * Created by Kirill G. Rydzyvylo on 01.12.2020.
 * Updated by Vladimir V. Klochkov on 04.03.2021.
 */
public class AssignResponsibilityForSigningDialog extends CommonPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public AssignResponsibilityForSigningDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Текст заголовка окна", "AssignResponderToDealSigningWindow_wnd_title");
        controls.add("Требуется подписание хотя бы одного назначенного ответственного",
                "//input[@value='SigningAtLeastOneDesignatedResponsible']");
        controls.add("Рабочие группы", "DealSigningResponsibleSelectionType_WorkGroups");
        controls.add("Сотрудники", "DealSigningResponsibleSelectionType_Employees");
        controls.add("Поле Сотрудники", "//div[@id='EmployeesList']//div[contains(@class,'k-header')]");
        controls.add("Выбрыть Сотрудника", "//ul[@id='EmployeeIdsForSelectionModelList_listbox']/li[contains(.,'%s')]");
        controls.add("Сохранить", "SaveTradeWorkGroup");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна [Назначить ответственного для подписания].
     *
     * @return это диалоговое окно
     */
    public AssignResponsibilityForSigningDialog dialogDisplayed() throws Exception {
        this.waitLoadingImage(15);
        System.out.println("[ИНФОРМАЦИЯ]: проверяем наличие диалогового окна [Назначить ответственного для подписания].");
        ElementsCollection titles = $$(this.getBy(controls.find("Текст заголовка окна"))).filter(visible);
        System.out.printf("[ИНФОРМАЦИЯ]: найдено [%d] заголовков диалогового окна [Назначить ответственного " +
                "для подписания]%n", titles.size());
        boolean result = titles.size() > 0;
        System.out.printf("[ИНФОРМАЦИЯ]: диалоговое окно [Назначить ответственного для подписания] %s отображается.%n",
                result ? "" : "не");

        return this;
    }

    /**
     * Проверяет, что в переключателе выбрана опция с указанным именем.
     *
     * @param radioButtonName имя опции
     * @return это диалоговое окно
     */
    public AssignResponsibilityForSigningDialog verifyRadioButtonSelected(String radioButtonName) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем положение переключателя [%s] - ", radioButtonName);
        $(this.getBy(controls.find(radioButtonName))).waitUntil(exist, timeout, polling).shouldBe(selected);
        System.out.printf("[%s], выбран.%n", radioButtonName);

        return this;
    }

    /**
     * Выбирает в переключателе опцию с указанным именем.
     *
     * @param radioButtonPosition имя опции
     * @return это диалоговое окно
     */
    public AssignResponsibilityForSigningDialog selectRadioButton(String radioButtonPosition) {
        $(this.getBy(controls.find(radioButtonPosition))).waitUntil(exist, timeout, polling).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено переключение в положение [%s].%n", radioButtonPosition);
        this.verifyRadioButtonSelected(radioButtonPosition);

        return this;
    }

    /**
     * Заполняет поле [Сотрудникиа] в разделе формы [Тип назначаемого ответственного] фиксированным.
     *
     * @param employeeName имя сотрудника подписывающего договор
     */
    public AssignResponsibilityForSigningDialog setEmployeeForSigningContract(String employeeName) throws Exception {
        $(this.getBy(controls.find("Поле Сотрудники"))).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);
        String employee = String.format(controls.find("Выбрыть Сотрудника"), employeeName);
        $(this.getBy(employee)).waitUntil(clickable, timeout, polling).click();
        this.logFilledFieldName("Поле Сотрудник", "employeeName");

        return this;
    }

    /**
     * Нажимает на указанную кнопку в диалоговом окне [Назначить ответственного для подписания].
     *
     * @param buttonName имя управляющего элемента в словаре
     */
    public void clickOnButton(String buttonName) throws Exception {
        SelenideElement button = $(this.getBy(controls.find(buttonName)));
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        button.waitUntil(appears, longtime, polling).shouldBe(clickable);
        screenshoter.takeScreenshot();
        this.logButtonNameToPress(buttonName);
        this.clickInElementJS(button);
        this.logPressedButtonName(buttonName);
        button.waitUntil(disappear, longtime, polling).shouldBe(disappear);
        this.waitForPageLoad();
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }
}
