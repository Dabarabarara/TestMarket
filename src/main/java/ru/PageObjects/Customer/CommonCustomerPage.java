package ru.PageObjects.Customer;

import Helpers.ConfigContainer;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.refresh;

/**
 * Класс описывающий общие контролы личного кабинета Заказчика.
 * Created by Evgeniy Glushko on 24.03.2016.
 * Updated by Vladimir V. Klochkov on 11.02.2021.
 */
public class CommonCustomerPage extends CommonPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Прочие элементы страницы

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Найдено извещений] (счетчик)
    private static final String FOUND_NOTICES_ID = "totalCount";
    //------------------------------------------------------------------------------------------------------------------
    // Прямоугольник [Включить CryptoPro CAdES NPAPI Browser]
    private static final String CADES_PLUGIN_OBJECT_ID = "cadesplugin_object";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица сертификатов для Заказчика
    private static final String CERTIFICATE_TABLE_ID = "certlist";
    //------------------------------------------------------------------------------------------------------------------
    // Список сертификатов в таблице для Заказчика
    private static final String CERTIFICATE_LIST_XPATH = "//table[@id='certlist']/tbody/tr[@data-isvalid='true']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Конструктор класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CommonCustomerPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Возвращает текущее значение поля [Найдено извещений].
     *
     * @return текущее значение поля [Найдено извещений]
     */
    String getSearchResultsCounter() throws Exception {
        boolean notExit = true;
        int waitTimeMin = Integer.parseInt(ConfigContainer.getInstance().getConfigParameter("MaxStatusWaitTime"));
        int nTries = waitTimeMin * 60 / 5;
        int i = 1;
        String counterText = "...";
        SelenideElement counter = $(this.getBy(FOUND_NOTICES_ID)).waitUntil(exist, timeout, polling).shouldBe(visible);

        System.out.printf("[ИНФОРМАЦИЯ]: установленное максимальное время ожидания извещения %d минут.%n",
                waitTimeMin);

        while (notExit && counterText.equals("...")) {
            counter.click();
            this.waitLoadingImage();
            counterText = counter.getText();
            notExit = i <= nTries;
            System.out.printf(">>> (getSearchResultsCounter) Проверка №%d значения в поле [Найдено извещений]: " +
                    "ожидаемое [цифра вместо '...'], реальное [%s].%n", i, counterText);
            i++;
        }
        Assert.assertTrue("Все попытки получить количество найденных извещений не увкенчались успехом", notExit);
        return counterText;
    }

    /**
     * Проверяет, что плагин [CryptoPro CAdES NPAPI Browser] не отключился или не отключен принудительно.
     */
    public void checkThatCryptoProPluginIsEnabled() {
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        Assert.assertFalse("[ОШИБКА]: плагин [CryptoPro CAdES NPAPI Browser] отключен",
                $(this.getBy(CADES_PLUGIN_OBJECT_ID)).isDisplayed());
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Проверяет, что таблица сертификатов на странице заполнилась.
     */
    public void checkThatCertificatesListIsNotEmpty() throws Exception {
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        // Ожидаем появления таблицы сертификатов на странице
        $(this.getBy(CERTIFICATE_TABLE_ID)).waitUntil(visible, 300000, 1000);

        // Ожидаем заполнения таблицы сертификатов на странице
        for (int i = 1; i <= 100; i++) // Цикл пока список сертификатов не загрузится
        {
            ElementsCollection certList = $$(this.getBy(CERTIFICATE_LIST_XPATH));
            if (certList.size() == 0) {
                refresh();
                TimeUnit.SECONDS.sleep(5);
                $(this.getBy(CERTIFICATE_TABLE_ID)).waitUntil(visible, 300000, 1000);
            } else break;
            System.out.printf("[ИНФОРМАЦИЯ]: таблица сертификатов не заполнилась, попытка: [%d].%n", i);
        }
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }
}
