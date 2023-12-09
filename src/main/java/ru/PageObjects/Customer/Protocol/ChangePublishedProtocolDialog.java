package ru.PageObjects.Customer.Protocol;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Изменить опубликованный протокол (Внимание)].
 * Created by Vladimir V. Klochkov on 08.06.2018.
 * Updated by Vladimir V. Klochkov on 04.03.2021.
 */
public class ChangePublishedProtocolDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    ChangePublishedProtocolDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Текст заголовка окна", "ChangePublishedProtocolWindow_wnd_title");
        controls.add("Причина отмены протокола", "cancellationProtocolReason");
        controls.add("Отменить протокол", "undoProtocolButton");
        controls.add("Закрыть", "changePublishedProtocolCancelButton");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна [Изменить опубликованный протокол (Внимание)].
     *
     * @return диалоговое окно [Изменить опубликованный протокол (Внимание)]
     */
    public ChangePublishedProtocolDialog ifDialogOpened() throws Exception {
        this.waitLoadingImage();
        Assert.assertTrue(
                "[ОШИБКА]: Диалоговое окно [Изменить опубликованный протокол (Внимание)] не обнаружено",
                $(By.id(controls.find("Текст заголовка окна"))).waitUntil(exist, timeout, polling).isDisplayed());
        System.out.println("[ИНФОРМАЦИЯ]: диалоговое окно [Изменить опубликованный протокол (Внимание)] обнаружено.");
        return this;
    }

    /**
     * Устанавливает значение поля с предварительной его очисткой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     * @return диалоговое окно [Изменить опубликованный протокол (Внимание)]
     */
    public ChangePublishedProtocolDialog setFieldClearClickAndSendKeys(String fieldName, String value) throws Exception {
        this.waitClearClickAndSendKeysById(controls.find(fieldName), value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n",
                fieldName, value);
        return this;
    }

    /**
     * Нажимает на указанную кнопку в диалоговом окне [Изменить опубликованный протокол (Внимание)].
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButtonByName(String buttonName) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие кнопки [%s] в диалоговом окне " +
                "[Изменить опубликованный протокол (Внимание)].%n", buttonName);
        $(By.id(controls.find(buttonName))).waitUntil(visible, timeout, polling).click();
        TimeUnit.SECONDS.sleep(15);
        this.waitLoadingImage();
    }
}
