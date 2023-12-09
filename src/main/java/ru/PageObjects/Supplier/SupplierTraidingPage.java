package ru.PageObjects.Supplier;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс описывающий страницу: Торги ( supplier/auction/app/offlineauction/ ).
 * Created by Vladimir V. Klochkov on 10.01.2019.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class SupplierTraidingPage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Раздел [Общие сведения о торге]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки]
    private static final String PURCHASE_NUMBER_XPATH = "//th[contains(., 'Номер закупки')]/following-sibling::td[1]/a";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Таблица [Текущее состояние]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Предложение цены без НДС]
    private static final String PRICE_OFFER_WITHOUT_VAT_XPATH = "//numericcomponent[@name='txtPrice']/input";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Подписать]
    private static final String CONFIRM_BIDDING_BUTTON_XPATH = "//input[@name='btnConfirmBid']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Ваше ценовое предложение]
    private static final String YOUR_PRICE_OFFER_XPATH =
            "//span[contains(., 'Ваше ценовое предложение:')]/following-sibling::span[1]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public SupplierTraidingPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Получает значение из поля [Номер закупки].
     */
    public String getPurchaseNumberFromTradingPage() {
        String purchaseNumber = $(this.getBy(PURCHASE_NUMBER_XPATH)).waitUntil(visible, timeout, polling).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: получено значения поля [Номер закупки]: [%s].%n", purchaseNumber);
        return purchaseNumber;
    }

    /**
     * Устанавливает значение в поле [Предложение цены без НДС].
     *
     * @param newPrice цена
     */
    public void setNewPriceOffer(String newPrice) throws Exception {
        this.waitClearClickAndSendKeys(this.getBy(PRICE_OFFER_WITHOUT_VAT_XPATH), newPrice);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [Предложение цены без НДС] значением [%s].%n", newPrice);
    }

    /**
     * Нажимает на кнопку [Подписать].
     */
    public void clickSignButton() throws Exception {
        this.logButtonNameToPress("Подписать");
        $(this.getBy(CONFIRM_BIDDING_BUTTON_XPATH)).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
        this.logPressedButtonName("Подписать");
    }

    /**
     * Получает значение из поля [Ваше ценовое предложение].
     */
    public String getYourPriceOffer() throws Exception {
        TimeUnit.SECONDS.sleep(3); // Не убирать эту задержку - не успеет считать новое значение цены из контрола
        String yourPrice = $(this.getBy(YOUR_PRICE_OFFER_XPATH)).waitUntil(visible, timeout, polling).getText();
        System.out.printf("[ИНФОРМАЦИЯ]: получено значения поля [Ваше ценовое предложение]: [%s].%n",
                yourPrice);
        return yourPrice;
    }
}
