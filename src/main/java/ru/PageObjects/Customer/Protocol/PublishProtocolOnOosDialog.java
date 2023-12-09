package ru.PageObjects.Customer.Protocol;

import Helpers.Dictionary;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;
import ru.PageObjects.Customer.Notice.SendInformationToEisDialog;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы с диалоговым окном [Отправка сведений в ЕИС].
 * Created by Vladimir V. Klochkov on 07.08.2019.
 * Updated by Vladimir V. Klochkov on 11.03.2021.
 */
public class PublishProtocolOnOosDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public PublishProtocolOnOosDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Текст заголовка окна", "PublishOnOosWindow_wnd_title");
        controls.add("Тип протокола текст", "//*[@id='PublishOnOosWindow']//span[@class='k-input']");
        controls.add("Тип протокола открыть", "//*[@id='PublishOnOosWindow']//span[@class='k-icon k-i-arrow-s']");
        controls.add("Тип протокола значение", "//*[@id='ProtocolType_listbox']/li[contains(., '%s')]");
        controls.add("URL типа протокола", "CustomMethodUrl");
        controls.add("Отправить в ЕИС", "//*[@id='PublishOnOosWindow']//button[contains(., 'Отправить в ЕИС')]");
        controls.add("Отмена", "//*[@id='PublishOnOosWindow']//button[contains(., 'Отмена')]");
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
    public PublishProtocolOnOosDialog ifDialogOpened() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        this.waitLoadingImage();
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Отправка сведений в ЕИС] не обнаружено",
                $(this.getBy(controls.find("Текст заголовка окна"))).waitUntil(exist, timeout, polling).
                        isDisplayed());
        System.out.println("[ИНФОРМАЦИЯ]: обнаружено диалоговое окно [Отправка сведений в ЕИС].");

        return this;
    }

    /**
     * Устанавливает значение полей [Тип протокола], [URL типа протокола] в диалоговом окне [Отправка сведений в ЕИС].
     *
     * @param protocolType требуемое значение поля
     * @param urlKey       название ключа в файле конфигурации по которому можно получить URL
     * @return диалоговое окно [Отправка сведений в ЕИС]
     */
    public PublishProtocolOnOosDialog selectProtocolType(String protocolType, String urlKey) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: выбираем тип протокола [%s].%n", protocolType);

        // Открываем список типов протокола
        SelenideElement openList = $(this.getBy(controls.find("Тип протокола открыть")));
        openList.waitUntil(clickable, timeout, polling);
        this.clickInElementJS(openList);
        TimeUnit.SECONDS.sleep(3);

        // Выбираем требуемый тип протокола
        String valueLocator = String.format(controls.find("Тип протокола значение"), protocolType);
        SelenideElement value = $(this.getBy(valueLocator));
        value.waitUntil(exist, timeout, polling);
        this.clickInElementJS(value);
        TimeUnit.SECONDS.sleep(3);

        // Проверяем, что тип протокола выбран верно
        String actualValue = $(this.getBy(controls.find("Тип протокола текст"))).
                waitUntil(visible, timeout, polling).getText();
        Assert.assertTrue("[ОШИБКА]: поле [Тип протокола] содержит некорректное значение",
                actualValue.contains(protocolType));
        System.out.printf("[ИНФОРМАЦИЯ]: тип протокола [%s] выбран успешно.%n", protocolType);

        // Если поле URL отображается, заполняем его значением из файла конфигурации
        SelenideElement urlField = $(this.getBy(controls.find("URL типа протокола")));

        if (urlField.isDisplayed()) {
            String url = config.getConfigParameter(urlKey);
            urlField.waitUntil(clickable, timeout, polling).sendKeys(url);
            TimeUnit.SECONDS.sleep(3);
            Assert.assertEquals("[ОШИБКА]: поле [URL типа протокола] содержит некорректное значение",
                    url, urlField.getValue());
            System.out.printf("[ИНФОРМАЦИЯ]: URL типа протокола [%s] заполнен успешно.%n", url);
        }

        return this;
    }

    /**
     * Нажимает на указанную кнопку в диалоговом окне [Отправка сведений в ЕИС].
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButtonByName(String buttonName) {
        System.out.printf(
                "[ИНФОРМАЦИЯ]: произведено нажатие кнопки [%s] в диалоговом окне [Отправка сведений в ЕИС].%n",
                buttonName);
        $(this.getBy(controls.find(buttonName))).waitUntil(clickable, timeout, polling).click();
        System.out.printf(
                "[ИНФОРМАЦИЯ]: ожидаем исчезновения кнопки [%s] и диалогового окна [Отправка сведений в ЕИС].%n",
                buttonName);
        $(this.getBy(controls.find(buttonName))).waitUntil(disappears, longtime, polling).shouldNotBe(visible);
    }
}
