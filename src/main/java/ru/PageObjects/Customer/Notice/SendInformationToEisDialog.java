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
 * Класс для работы с диалоговым окном [Отправка сведений в ЕИС].
 * Created by Vladimir V. Klochkov on 03.04.2018.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class SendInformationToEisDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public SendInformationToEisDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Текст заголовка окна", "PublishToOosDialog_wnd_title");
        controls.add("Способ закупки текст", "//input[@id='PurchaseTypeList']/preceding-sibling::span[1]/span[1]");
        controls.add("Способ закупки открыть", "//input[@id='PurchaseTypeList']/preceding-sibling::span[1]/span[2]");
        controls.add("Способ закупки значение", "//*[@id='PurchaseTypeList_listbox']/li[contains(., '%s')]");
        controls.add("URL способа закупки", "CustomMethodUrl");
        controls.add("Отправить в ЕИС", "confirmPublishToOosOk");
        controls.add("Отмена", "confirmPublishToOosCancel");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна [Отправка сведений в ЕИС].
     *
     * @return диалоговое окно [Отправка сведений в ЕИС]
     */
    public SendInformationToEisDialog ifDialogOpened() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        this.waitLoadingImage();
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Отправка сведений в ЕИС] не обнаружено",
                $(By.id(controls.find("Текст заголовка окна"))).waitUntil(exist, timeout, polling).isDisplayed());
        System.out.println("[ИНФОРМАЦИЯ]: обнаружено диалоговое окно [Отправка сведений в ЕИС].");

        return this;
    }

    /**
     * Устанавливает значение полей [Способ закупки], [URL способа закупки] в диалоговом окне [Отправка сведений в ЕИС].
     *
     * @param purchaseMethod требуемое значение поля
     * @param urlKey         название ключа в файле конфигурации по которому можно получить URL
     * @return диалоговое окно [Отправка сведений в ЕИС]
     */
    public SendInformationToEisDialog selectPurchaseMethod(String purchaseMethod, String urlKey) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: выбираем способ закупки [%s].%n", purchaseMethod);

        // Открываем список способов закупки
        SelenideElement openList = $(this.getBy(controls.find("Способ закупки открыть")));
        openList.waitUntil(clickable, timeout, polling);
        this.clickInElementJS(openList);
        TimeUnit.SECONDS.sleep(3);

        // Выбираем требуемый способ закупки
        String valueLocator = String.format(controls.find("Способ закупки значение"), purchaseMethod);
        SelenideElement value = $(this.getBy(valueLocator));
        value.waitUntil(exist, timeout, polling);
        this.clickInElementJS(value);
        TimeUnit.SECONDS.sleep(3);

        // Проверяем, что способ закупки выбран верно
        String actualValue = $(this.getBy(controls.find("Способ закупки текст"))).
                waitUntil(visible, timeout, polling).getText();
        Assert.assertTrue("[ОШИБКА]: поле [Способ закупки] содержит некорректное значение",
                actualValue.contains(purchaseMethod));
        System.out.printf("[ИНФОРМАЦИЯ]: способ закупки [%s] выбран успешно.%n", purchaseMethod);

        // Если поле URL отображается, заполняем его значением из файла конфигурации
        SelenideElement urlField = $(this.getBy(controls.find("URL способа закупки")));

        if (urlField.isDisplayed()) {
            String url = config.getConfigParameter(urlKey);
            urlField.waitUntil(clickable, timeout, polling).sendKeys(url);
            TimeUnit.SECONDS.sleep(3);
            Assert.assertEquals("[ОШИБКА]: поле [URL способа закупки] содержит некорректное значение",
                    url, urlField.getValue());
            System.out.printf("[ИНФОРМАЦИЯ]: URL способа закупки [%s] заполнен успешно.%n", url);
        }

        return this;
    }

    /**
     * Нажимает на указанную кнопку в диалоговом окне [Отправка сведений в ЕИС].
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButtonByName(String buttonName) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие кнопки [%s] в диалоговом окне [Отправка сведений в ЕИС].%n",
                buttonName);
        $(By.id(controls.find(buttonName))).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(15);
        this.waitLoadingImage();
    }
}
