package ru.PageObjects.Admin;

import Helpers.Dictionary;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonDialogs.AttentionDialog;
import ru.PageObjects.CommonDialogs.CompletedDialog;
import ru.PageObjects.CommonPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Связать закупку № XXXXX с закупкой в ЕИС].
 * Created by Vladimir V. Klochkov on 02.08.2018.
 * Updated by Kirill G. Rydzyvylo on 02.12.2020.
 */
public class LinkPurchaseWithNoToEisDialog extends CommonPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public LinkPurchaseWithNoToEisDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Текст заголовка окна", "//span[@class='ui-dialog-title' and contains(., 'Связать закупку № ')]");
        controls.add("XML извещения о закупке", "//textarea[@id='taPurchaseXml']");
        controls.add("Кнопка Связать", "//button[contains(., 'Связать')]");
        controls.add("Кнопка Отмена", "//button[contains(., 'Отмена')]");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна [Связать закупку № XXXXX с закупкой в ЕИС].
     */
    public LinkPurchaseWithNoToEisDialog ifDialogOpened() {
        System.out.println(
                "[ИНФОРМАЦИЯ]: проверяем наличие диалогового окна [Связать закупку № XXXXX с закупкой в ЕИС].");
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Связать закупку № XXXXX с закупкой в ЕИС] не отобра" +
                "жается на экране", $(this.getBy(controls.find("Текст заголовка окна"))).isDisplayed());
        return this;
    }

    /**
     * Устанавливает значение поля [XML извещения о закупке]
     * в диалоговом окне [Связать закупку № XXXXX с закупкой в ЕИС].
     *
     * @param xml требуемое значение поля
     * @return диалоговое окно [Связать закупку № XXXXX с закупкой в ЕИС]
     */
    public LinkPurchaseWithNoToEisDialog setXmlOfPurchaseField(String xml) throws Exception {
        SelenideElement field = $(this.getBy(controls.find("XML извещения о закупке")));
        field.waitUntil(clickable, timeout, polling).sendKeys(xml);
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue("[ОШИБКА]: значение поля [XML извещения о закупке] не установлено",
                field.getValue().contains("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"));
        System.out.println("[ИНФОРМАЦИЯ]: произведена установка значения поля [XML извещения о закупке].");
        return this;
    }

    /**
     * Нажимает на кнопку [Связать] в диалоговом окне [Связать закупку № XXXXX с закупкой в ЕИС].
     */
    public void clickOnLinkButton() throws Exception {
        SelenideElement button = $(this.getBy(controls.find("Кнопка Связать")));
        button.waitUntil(appears, longtime, polling).shouldBe(clickable);
        screenshoter.takeScreenshot();
        this.logButtonNameToPress("Связать");
        this.clickInElementJS(button);
        this.logPressedButtonName("Связать");
        //--------------------------------------------------------------------------------------------------------------
        // Работа в диалоговом окне [Внимание!]
        AttentionDialog attentionDialog = new AttentionDialog(driver);
        if (attentionDialog.dialogDisplayed())
            attentionDialog.
                    check1stLineInWindowText("Текст окна", "Не совпадают даты").
                    clickOnButton("Продолжить связывание");
        //--------------------------------------------------------------------------------------------------------------
        button.waitUntil(disappear, longtime, polling).shouldBe(disappear);
        //--------------------------------------------------------------------------------------------------------------
        // Работа в диалоговом окне [Операция успешно выполнена]
        CompletedDialog completedDialog = new CompletedDialog(driver);
        completedDialog.clickOnOkButton();
        //--------------------------------------------------------------------------------------------------------------
    }
}
