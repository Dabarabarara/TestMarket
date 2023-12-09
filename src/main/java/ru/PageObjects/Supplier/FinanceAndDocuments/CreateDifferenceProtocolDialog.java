package ru.PageObjects.Supplier.FinanceAndDocuments;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;
import ru.PageObjects.Supplier.CommonSupplierPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы с диалоговым окном [Создание протокола разногласий].
 * Created by Vladimir V. Klochkov on 10.09.2018.
 * Updated by Vladimir V. Klochkov on 04.03.2021.
 */
public class CreateDifferenceProtocolDialog extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CreateDifferenceProtocolDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Заголовок окна",
                "//span[@class='ui-dialog-title' and contains(., 'Создание протокола разногласий')]");
        controls.add("Поле Введите причину", "BaseMainContent_MainContent_txtDifferenceProtocolReason");
        controls.add("Кнопка Добавить",
                "//*[@id='BaseMainContent_MainContent_ufDifferenceProtocolFile_btnUpload']/input");
        controls.add("Прикрепленный файл",
                "//*[@id='BaseMainContent_MainContent_ufDifferenceProtocolFile_ulDocuments']/li/a[@class='upload-file']");
        controls.add("Кнопка Продолжить", "BaseMainContent_MainContent_btnContinueDifferenceProtocol");
        controls.add("Кнопка Отмена", "BaseMainContent_MainContent_btnCancelDifferenceProtocol");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна [Создание протокола разногласий].
     *
     * @return диалоговое окно [Создание протокола разногласий]
     */
    public CreateDifferenceProtocolDialog ifDialogOpened() {
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Создание протокола разногласий] не обнаружено",
                $(By.xpath(controls.find("Заголовок окна"))).waitUntil(exist, longtime, polling).isDisplayed());
        System.out.println("[ИНФОРМАЦИЯ]: диалоговое окно [Создание протокола разногласий] обнаружено успешно.");
        return this;
    }

    /**
     * Устанавливает значение поля [Введите причину] в диалоговом окне [Создание протокола разногласий].
     *
     * @param reason требуемое значение поля
     * @return диалоговое окно [Создание протокола разногласий]
     */
    public CreateDifferenceProtocolDialog setReason(String reason) {
        $(By.id(controls.find("Поле Введите причину"))).waitUntil(clickable, timeout, polling).sendKeys(reason);
        Assert.assertEquals("[ОШИБКА]: значение поля [Введите причину] установить не удалось", reason,
                $(By.id(controls.find("Поле Введите причину"))).waitUntil(clickable, timeout, polling).getValue());
        System.out.printf("[ИНФОРМАЦИЯ]: поле [Введите причину] в диалоговом окне " +
                "[Создание протокола разногласий] получило значение [%s].%n", reason);
        return this;
    }

    /**
     * Добавляет файл в диалоговом окне [Создание протокола разногласий].
     *
     * @return диалоговое окно [Создание протокола разногласий]
     */
    public CreateDifferenceProtocolDialog attachFile() throws Exception {
        ElementsCollection buttons = $$(By.xpath(controls.find("Кнопка Добавить")));
        Assert.assertNotEquals("[ОШИБКА]: не найдено ни одной кнопки [Добавить]", 0, buttons.size());
        buttons.get(0).sendKeys(config.getAbsolutePathToFoundationDoc());
        TimeUnit.SECONDS.sleep(5);
        Assert.assertEquals("[ОШИБКА]: не удалось добавить файл", 1,
                $$(By.xpath(controls.find("Прикрепленный файл"))).size());
        System.out.println(
                "[ИНФОРМАЦИЯ]: добавление файла в диалоговое окно [Создание протокола разногласий] прошло успешно.");
        return this;
    }

    /**
     * Нажимает на указанную кнопку в диалоговом окне [Создание протокола разногласий].
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButtonByName(String buttonName) throws Exception {
        System.out.printf(
                "[ИНФОРМАЦИЯ]: произведено нажатие кнопки [%s] в диалоговом окне [Создание протокола разногласий].%n",
                buttonName);
        $(By.id(controls.find(buttonName))).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
        TimeUnit.SECONDS.sleep(15);
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        $(By.xpath(controls.find("Заголовок окна"))).shouldBe(disappear);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }
}
