package ru.PageObjects.CommonDialogs;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.AbstractPage;
import ru.PageObjects.CommonPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы с диалоговым окном [Ценовые предложения] (по сути 2 разных диалоговых окна: Поставщика и Заказчика).
 * Created by Vladimir V. Klochkov on 13.08.2017.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class PriceOffersDialog extends CommonPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public PriceOffersDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        String supplierWindowHeaderXpath = "//span[@class='ui-dialog-title' and contains(.,'Ценовые предложения')]";
        String customerWindowHeaderXpath = "//*[@id='ApplicationQuotationsWindow_wnd_title']";
        controls.add("Поставщик Текст заголовка окна", supplierWindowHeaderXpath);
        controls.add("Поставщик Кнопка Закрыть окно", supplierWindowHeaderXpath + "/following-sibling::button[1]");
        controls.add("Поставщик Таблица с данными Участники", "//*[@id='quotationGrid']/fieldset/label");
        controls.add("Поставщик Таблица с данными Предложения", "//*[@id='quotationGrid']/fieldset/span");
        controls.add("Поставщик Кнопка OK", "//div[@id='quotationsDialog']/following-sibling::div[1]/div/button");
        controls.add("Заказчик Текст заголовка окна", customerWindowHeaderXpath);
        controls.add("Заказчик Кнопка Закрыть окно", customerWindowHeaderXpath + "/following-sibling::div[1]/a/span");
        controls.add("Заказчик Таблица с данными Участники", "//*[@id='QuotationsGrid']/table/tbody/tr/td[1]");
        controls.add("Заказчик Таблица с данными Предложения", "//*[@id='QuotationsGrid']/table/tbody/tr/td[2]");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна [Ценовые предложения].
     *
     * @return диалоговое окно [Ценовые предложения]
     */
    public PriceOffersDialog ifSupplierPriceOffersDialogWindowOpened() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        this.waitLoadingImage();
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Ценовые предложения] не обнаружено",
                $(By.xpath(controls.find("Поставщик Текст заголовка окна"))).
                        waitUntil(exist, timeout, polling).isDisplayed());
        return this;
    }

    /**
     * Проверяет наличие диалогового окна [Ценовые предложения].
     *
     * @return диалоговое окно [Ценовые предложения]
     */
    public PriceOffersDialog ifCustomerPriceOffersDialogWindowOpened() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        this.waitLoadingImage();
        Assert.assertTrue( "[ОШИБКА]: диалоговое окно [Ценовые предложения] не обнаружено",
                $(By.xpath(controls.find("Заказчик Текст заголовка окна"))).
                        waitUntil(exist, timeout, polling).isDisplayed());
        return this;
    }

    /**
     * Проверяет, что диалоговое окно [Ценовые предложения] содержит не пустое количество записей.
     *
     * @return диалоговое окно [Ценовые предложения]
     */
    public PriceOffersDialog checkSupplierPriceOffersDialogWindowIsNotEmpty() {
        System.out.println("[ИНФОРМАЦИЯ]: проверяем наличие записей в диалоговом окне [Ценовые предложения].");
        Assert.assertTrue("[ОШИБКА]: записи в диалоговом окне [Ценовые предложения] отсутствуют",
                $$(By.xpath(controls.find("Поставщик Таблица с данными Участники"))).size() > 0);
        return this;
    }

    /**
     * Проверяет, что диалоговое окно [Ценовые предложения] содержит не пустое количество записей.
     *
     * @return диалоговое окно [Ценовые предложения]
     */
    public PriceOffersDialog checkCustomerPriceOffersDialogWindowIsNotEmpty() {
        System.out.println("[ИНФОРМАЦИЯ]: проверяем наличие записей в диалоговом окне [Ценовые предложения].");
        Assert.assertTrue("[ОШИБКА]: записи в диалоговом окне [Ценовые предложения] отсутствуют",
                $$(By.xpath(controls.find("Заказчик Таблица с данными Участники"))).size() > 0);
        return this;
    }

    /**
     * Проверяет, что диалоговое окно [Ценовые предложения] содержит требуемое количество записей.
     *
     * @param recordsCount требуемое количество записей
     */
    public void checkSupplierPriceOffersDialogWindowRecordsCount(String recordsCount) {
        int count = Integer.parseInt(recordsCount);
        int size = $$(By.xpath(controls.find("Поставщик Таблица с данными Участники"))).size();
        System.out.printf(
                "[ИНФОРМАЦИЯ]: требуемое количество записей в диалоговом окне [Ценовые предложения] '%d'.%n", count);
        System.out.printf(
                "[ИНФОРМАЦИЯ]: реальное  количество записей в диалоговом окне [Ценовые предложения] '%d'.%n", size);
        Assert.assertEquals("[ОШИБКА]: неверное количество записей в диалоговом окне [Ценовые предложения]",
                size, count);
    }

    /**
     * Проверяет, что диалоговое окно [Ценовые предложения] содержит требуемое количество записей.
     *
     * @param recordsCount требуемое количество записей
     */
    public void checkCustomerPriceOffersDialogWindowRecordsCount(String recordsCount) {
        int count = Integer.parseInt(recordsCount);
        int size = $$(By.xpath(controls.find("Заказчик Таблица с данными Участники"))).size();
        System.out.printf(
                "[ИНФОРМАЦИЯ]: требуемое количество записей в диалоговом окне [Ценовые предложения] '%d'.%n", count);
        System.out.printf(
                "[ИНФОРМАЦИЯ]: реальное  количество записей в диалоговом окне [Ценовые предложения] '%d'.%n", size);
        Assert.assertEquals("[ОШИБКА]: неверное количество записей в диалоговом окне [Ценовые предложения]",
                size, count);
    }

    /**
     * Проверяет, что диалоговое окно [Ценовые предложения] содержит требуемую запись о ценовом предложении.
     *
     * @param supplierNameOfferNumber имя участника и порядковый номер его ценового предложения
     * @param priceOffer              ценовое предложение этого участника
     */
    public void checkPriceOfferInSupplierPriceOffersDialogWindow(
            String supplierNameOfferNumber, String priceOffer) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем, что диалоговое окно [Ценовые предложения] " +
                "содержит запись [%s] с суммой [%s].%n", supplierNameOfferNumber, priceOffer);
        ElementsCollection names = $$(By.xpath(controls.find("Поставщик Таблица с данными Участники")));
        ElementsCollection offers = $$(By.xpath(controls.find("Поставщик Таблица с данными Предложения")));
        boolean success = false;
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).getText().equals(supplierNameOfferNumber)) {
                Assert.assertEquals("[ОШИБКА]: сумма ценового предложения не совпадает",
                        priceOffer, offers.get(i).getText());
                success = true;
                break;
            }
        }
        Assert.assertTrue("[ОШИБКА]: ценовое предложение не найдено", success);
    }

    /**
     * Проверяет, что диалоговое окно [Ценовые предложения] содержит требуемую запись о ценовом предложении.
     *
     * @param supplierNameOfferNumber имя участника и порядковый номер его ценового предложения
     * @param priceOffer              ценовое предложение этого участника
     */
    public void checkPriceOfferInCustomerPriceOffersDialogWindow(
            String supplierNameOfferNumber, String priceOffer) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем, что диалоговое окно [Ценовые предложения] " +
                "содержит запись [%s] с суммой [%s].%n", supplierNameOfferNumber, priceOffer);
        ElementsCollection names = $$(By.xpath(controls.find("Заказчик Таблица с данными Участники")));
        ElementsCollection offers = $$(By.xpath(controls.find("Заказчик Таблица с данными Предложения")));
        boolean success = false;
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).getText().equals(supplierNameOfferNumber)) {
                Assert.assertEquals("[ОШИБКА]: сумма ценового предложения не совпадает",
                        priceOffer, offers.get(i).getText());
                success = true;
                break;
            }
        }
        Assert.assertTrue("[ОШИБКА]: ценовое предложение не найдено", success);
    }

    /**
     * Закрывает диалоговое окно [Ценовые предложения] с помощью кнопки [OK].
     */
    public void closeSupplierPriceOffersDialogWindow() throws Exception {
        System.out.println("[ИНФОРМАЦИЯ]: произведено нажатие кнопки [OK] в диалоговом окне [Ценовые предложения].");
        $(By.xpath(controls.find("Поставщик Кнопка OK"))).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        $(By.id(controls.find("Поставщик Текст заголовка окна"))).shouldBe(disappear);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Закрывает диалоговое окно [Ценовые предложения] с помощью кнопки [X].
     */
    public void closeCustomerPriceOffersDialogWindow() throws Exception {
        System.out.println("[ИНФОРМАЦИЯ]: произведено нажатие кнопки [X] в диалоговом окне [Ценовые предложения].");
        $(By.xpath(controls.find("Заказчик Кнопка Закрыть окно"))).waitUntil(visible, timeout, polling).click();
        this.waitLoadingImage();
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        $(By.id(controls.find("Заказчик Текст заголовка окна"))).shouldBe(disappear);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }
}
