package ru.PageObjects.Customer.Notice;

import Helpers.Dictionary;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Отказ от проведения закупки].
 * Created by Vladimir V. Klochkov on 19.10.2017.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class TradeCancelDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public TradeCancelDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Текст заголовка окна", "TradeCancelWindow_wnd_title");
        controls.add("Дата принятия решения", "TradeCancelDecisionDate");
        controls.add("Причина отказа от проведения закупки открыть",
                "//input[@id='CancelReasonCode']/preceding-sibling::span[1]//span[@class='k-icon k-i-arrow-s']");
        controls.add("Причина отказа от проведения закупки первое значение",
                "//ul[@id='CancelReasonCode_listbox']/li[2]");
        controls.add("Причина отказа от проведения закупки выбранное значение",
                "//input[@id='CancelReasonCode']/preceding-sibling::span[1]/span[@class='k-input']");
        controls.add("Прикрепить файл", "TradeCancelFilesUpload");
        controls.add("Удалить файл", "//*[@id='FilesGrid']/table/tbody//a[contains(.,'Удалить')]");
        controls.add("Опубликовать", "btnCancelTradeConfirm");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна [Отказ от проведения закупки].
     *
     * @return диалоговое окно [Отказ от проведения закупки]
     */
    public TradeCancelDialog ifDialogOpened() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        this.waitLoadingImage();
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Отказ от проведения закупки] не обнаружено",
                $(By.id(controls.find("Текст заголовка окна"))).waitUntil(exist, timeout, polling).isDisplayed());
        return this;
    }

    /**
     * Устанавливает значение поля с предварительной его очисткой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     * @return диалоговое окно [Отказ от проведения закупки]
     */
    public TradeCancelDialog setFieldClearClickAndSendKeys(String fieldName, String value) throws Exception {
        this.waitClearClickAndSendKeysById(controls.find(fieldName), value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
        return this;
    }

    /**
     * Устанавливает значение поля со списком [Причина отказа от проведения закупки].
     *
     * @return диалоговое окно [Отказ от проведения закупки]
     */
    public TradeCancelDialog selectCancelReason() throws Exception {
        SelenideElement collapsedList = $(By.xpath(controls.find("Причина отказа от проведения закупки открыть")));
        SelenideElement firstElement =
                $(By.xpath(controls.find("Причина отказа от проведения закупки первое значение")));
        this.waitForListOpens(collapsedList, firstElement);
        String expectedText = firstElement.getText();
        firstElement.waitUntil(clickable, timeout, polling).click();
        SelenideElement justSelected =
                $(By.xpath(controls.find("Причина отказа от проведения закупки выбранное значение")));
        String actualText = justSelected.getText();
        Assert.assertEquals("[ОШИБКА]: причина отказа от проведения закупки была выбрана некорректно",
                expectedText, actualText);
        System.out.printf("[ИНФОРМАЦИЯ]: произведен выбор причины отказа от проведения закупки [%s] " +
                "в диалоговом окне [Отказ от проведения закупки].%n", actualText);
        return this;
    }

    /**
     * Прикрепляет документ с причиной отказа от проведения закупки в разделе [Документы].
     *
     * @param fileName полное имя файла с документом
     * @return диалоговое окно [Отказ от проведения закупки]
     */
    public TradeCancelDialog uploadFile(String fileName) throws Exception {
        $(By.id(controls.find("Прикрепить файл"))).waitUntil(exist, timeout, polling).sendKeys(fileName);
        TimeUnit.SECONDS.sleep(3);
        $(By.xpath(controls.find("Удалить файл"))).waitUntil(exist, timeout, polling).shouldBe(visible);
        System.out.printf("[ИНФОРМАЦИЯ]: прикреплён документ с причиной отказа от проведения закупки [%s] " +
                "в диалоговом окне [Отказ от проведения закупки].%n", fileName);
        return this;
    }

    /**
     * Нажимает на указанную кнопку в диалоговом окне [Отказ от проведения закупки].
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButtonByName(String buttonName) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие кнопки [%s] в диалоговом окне [Отказ от проведения закупки].%n",
                buttonName);
        $(By.id(controls.find(buttonName))).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
        TimeUnit.SECONDS.sleep(15);
    }
}
