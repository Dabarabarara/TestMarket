package ru.PageObjects.Customer.Contract;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы с диалоговым окном [Предупреждение]
 * ( после нажатия на кнопку [Протокол отказа от заключения договора] ).
 * Created by Vladimir V. Klochkov on 12.09.2018.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class RefuseContractWarningDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public RefuseContractWarningDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Заголовок окна", "CommonConfirmWindow_wnd_title");
        controls.add("Причина отказа", "//*[@id='CommonConfirmConfirmation']/input");
        controls.add("Кнопка Продолжить", "CommonConfirmWindowOk");
        controls.add("Кнопка Отмена", "CommonConfirmWindowCancel");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна [Предупреждение].
     *
     * @return диалоговое окно [Предупреждение]
     */
    public RefuseContractWarningDialog ifDialogOpened() {
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Предупреждение] не обнаружено",
                $(By.id(controls.find("Заголовок окна"))).waitUntil(exist, longtime, polling).isDisplayed());
        System.out.println("[ИНФОРМАЦИЯ]: диалоговое окно [Предупреждение] обнаружено успешно.");
        return this;
    }

    /**
     * Устанавливает значение поля [Причина отказа] в диалоговом окне [Предупреждение].
     *
     * @param reason требуемое значение поля
     * @return диалоговое окно [Предупреждение]
     */
    public RefuseContractWarningDialog setReason(String reason) {
        $(By.xpath(controls.find("Причина отказа"))).waitUntil(clickable, timeout, polling).sendKeys(reason);
        Assert.assertEquals("[ОШИБКА]: значение поля [Причина отказа] установить не удалось", reason,
                $(By.xpath(controls.find("Причина отказа"))).waitUntil(clickable, timeout, polling).getValue());
        System.out.printf(
                "[ИНФОРМАЦИЯ]: поле [Причина отказа] в диалоговом окне [Предупреждение] получило значение [%s].%n", reason);
        return this;
    }

    /**
     * Нажимает на указанную кнопку в диалоговом окне [Предупреждение].
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButtonByName(String buttonName) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие кнопки [%s] в диалоговом окне [Предупреждение].%n",
                buttonName);
        $(By.id(controls.find(buttonName))).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
        TimeUnit.SECONDS.sleep(15);
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        $(By.id(controls.find("Заголовок окна"))).shouldBe(disappear);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }
}
