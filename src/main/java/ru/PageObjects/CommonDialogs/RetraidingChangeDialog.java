package ru.PageObjects.CommonDialogs;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.AbstractPage;
import ru.PageObjects.CommonPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Включение возможности переторжки].
 * Created by Vladimir V. Klochkov on 05.06.2017.
 * Updated by Vladimir V. Klochkov on 11.03.2021.
 */
public class RetraidingChangeDialog extends CommonPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public RetraidingChangeDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Текст заголовка окна", "RetradingActivateWindow_wnd_title");
        controls.add("Переключатель Очная переторжка", "RetradingType_1");
        controls.add("Переключатель Заочная переторжка", "RetradingType_0");
        controls.add("Флаг Показывать ход переторжки", "IsPublicTrading");
        controls.add("Поле Дата и время начала переторжки", "StartDate");
        controls.add("Флаг Автоматическое продление переторжки после подачи ценового предложения", "IsAutoProlongation");
        controls.add("Поле Дата и время окончания переторжки", "EndDate");
        controls.add("Поле Время продления, мин", "ProlongationMinutes");
        controls.add("Поле Максимальная продолжительность переторжки, мин", "MaxDurationInMinutes");
        controls.add("Флаг Переторжка с шагом", "WithStep");
        controls.add("Флаг Показывать ценовые предложения, поданные в заявках", "ShowApplicationQuotations");
        controls.add("Флаг Показывать наименования участников", "ShowParticipantNames");
        controls.add("Флаг Скрывать лучшее ценовое предложение", "HideOtherParticipantsBids");
        controls.add("Флаг Возможно прикрепление документов к ценовым предложениям", "WithAttachDocuments");
        controls.add("Флаг Возможно повышение значения ценового предложения", "IsRaiseBidAllowed");
        controls.add("Флаг Каскадная переторжка", "IsCascadeRetrading");
        controls.add("Кнопка Создать", "btnRetradingChangeConfirm");
        controls.add("Кнопка Отмена", "btnRetradingChangeCancel");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна [Включение возможности переторжки].
     *
     * @return диалоговое окно [Включение возможности переторжки]
     */
    public RetraidingChangeDialog ifDialogOpened() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        this.waitLoadingImage();
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Включение возможности переторжки] не обнаружено",
                $(By.id(controls.find("Текст заголовка окна"))).waitUntil(exist, timeout, polling).isDisplayed());
        return this;
    }

    /**
     * Устанавливает значение переключателя [Тип переторжки].
     *
     * @param type тип переторжки
     * @return диалоговое окно [Включение возможности переторжки]
     */
    public RetraidingChangeDialog setRetradingTypeTo(String type) {
        $(By.id(controls.find(type))).waitUntil(visible, timeout, polling).click();
        return this;
    }

    /**
     * Возвращает значение флажка с указанным именем.
     *
     * @param checkBoxName имя флажка
     * @return значение флажка
     */
    private boolean getCheckBoxValue(String checkBoxName) {
        $(By.id(controls.find(checkBoxName))).waitUntil(exist, timeout, polling).shouldBe(visible);
        return $(By.id(controls.find(checkBoxName))).isSelected();
    }

    /**
     * Устанавливает или сбрасывает флажок с указанным именем.
     *
     * @param checkBoxName имя флажка
     * @return диалоговое окно [Включение возможности переторжки]
     */
    public RetraidingChangeDialog setCheckBoxValue(String checkBoxName) {
        String message = ">>> (setCheckBoxValue) Состояние флажка [%s] не изменилось после щелчка мышью.";

        boolean oldValue = this.getCheckBoxValue(checkBoxName);
        By by = By.id(controls.find(checkBoxName));
        $(by).waitUntil(visible, timeout, polling);
        this.scrollToCenter(by);
        $(by).click();
        System.out.println("[ИНФОРМАЦИЯ]: произведено изменение состояния флажка: [" + checkBoxName + "].");
        boolean newValue = this.getCheckBoxValue(checkBoxName);
        System.out.println("[ИНФОРМАЦИЯ]: флажок: [" + checkBoxName + "] находится в " +
                ((newValue) ? "" : "не ") + "отмеченном состоянии.");
        Assert.assertNotEquals(String.format(message, checkBoxName), oldValue, newValue);
        return this;
    }

    /**
     * Проверяет, что флажок с указанным именем выбран.
     *
     * @param checkBoxName имя флажка
     * @return диалоговое окно [Включение возможности переторжки]
     */
    public RetraidingChangeDialog verifyCheckBoxSelected(String checkBoxName) {
        String message = ">>> (verifyCheckBoxSelected) Флажок [%s] не установлен.";
        Assert.assertTrue(String.format(message, checkBoxName), this.getCheckBoxValue(checkBoxName));
        System.out.println("[ИНФОРМАЦИЯ]: флажок: [" + checkBoxName + "] находится в отмеченном состоянии.");
        return this;
    }

    /**
     * Проверяет, что флажок с указанным именем не выбран.
     *
     * @param checkBoxName имя флажка
     * @return диалоговое окно [Включение возможности переторжки]
     */
    public RetraidingChangeDialog verifyCheckBoxNotSelected(String checkBoxName) {
        String message = ">>> (verifyCheckBoxNotSelected) Флажок [%s] установлен.";
        Assert.assertFalse(String.format(message, checkBoxName), this.getCheckBoxValue(checkBoxName));
        System.out.println("[ИНФОРМАЦИЯ]: флажок: [" + checkBoxName + "] находится в не отмеченном состоянии.");
        return this;
    }

    /**
     * Устанавливает значение поля с предварительной его очисткой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     * @return диалоговое окно [Включение возможности переторжки]
     */
    public RetraidingChangeDialog setFieldClearClickAndSendKeys(String fieldName, String value) throws Exception {
        this.waitClearClickAndSendKeysById(controls.find(fieldName), value);
        System.out.println("[ИНФОРМАЦИЯ]: произведено заполнение поля [" + fieldName + "] значением [" + value + "].");
        return this;
    }

    /**
     * Устанавливает значение поля, имеющего тип [kendoNumericTextBox] с последующей проверкой значения.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     * @return диалоговое окно [Включение возможности переторжки]
     */
    public RetraidingChangeDialog setNumericTextBox(String fieldName, String value) throws Exception {
        this.setKendoNumericTextBoxJS(fieldName, controls.find(fieldName), value);
        return this;
    }

    /**
     * Нажимает на кнопку [Создать] в диалоговом окне [Включение возможности переторжки].
     */
    public void retradingChangeConfirm() throws Exception {
        System.out.println("[ИНФОРМАЦИЯ]: произведено нажатие кнопки [Создать] в диалоговом окне " +
                "[Включение возможности переторжки].");
        By by = By.id(controls.find("Кнопка Создать"));
        $(by).waitUntil(visible, timeout, polling);
        this.scrollToCenter(by);
         screenshoter.takeScreenshot();
        this.clickInElementJS(by);
        this.waitLoadingImage(15);
        $(By.id(controls.find("Текст заголовка окна"))).shouldBe(disappear);
    }
}
