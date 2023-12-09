package ru.PageObjects.Customer.Protocol;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс описывающий страницу [Протокол проведения аукциона] (customer/lk/Protocols/ProtocolForm/).
 * Created by Evgeniy Glushko on 30.03.2016.
 * Updated by Vladimir V. Klochkov on 04.03.2021.
 */
public class AuctionCustomerProtocolPage extends CommonCustomerProtocolPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Поле с наивысшим рейтингом в колонке [Лучшее ценовое предложение] для лота с указанным порядковым номером
    private static final String LOT_BEST_OFFER_XPATH = "//div[@data-lot-order='%d']//tr[2]//span[@id='BestOfferSpan']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле с наивысшим рейтингом в колонке [Лучшее ценовое предложение - без НДС] для лота с указанным порядковым номером
    private static final String LOT_BEST_OFFER_WITHOUT_VAT_XPATH =
            "//div[@data-lot-order='%d']//tr[3]//span[@id='BestOfferSpan']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле с наивысшим рейтингом в колонке [Лучшее ценовое предложение - с НДС] для лота с указанным порядковым номером
    private static final String LOT_BEST_OFFER_WITH_VAT_XPATH =
            "//div[@data-lot-order='%d']//tr[3]//span[@id='BestOfferWithVatSpan']";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public AuctionCustomerProtocolPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Лучшее ценовое предложение - без НДС", LOT_BEST_OFFER_WITHOUT_VAT_XPATH);
        controls.add("Лучшее ценовое предложение - с НДС", LOT_BEST_OFFER_WITH_VAT_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет лучшую цену в колонке [Лучшее ценовое предложение] для первого по порядку лота в закупке.
     *
     * @param expectedOffer лучшая цена
     */
    public void checkBestOfferInFirstLot(String expectedOffer) {
        this.checkBestOfferInLotByLotNumber(expectedOffer, "1");
    }

    /**
     * Проверяет лучшую цену в колонке [Лучшее ценовое предложение] для лота с указанным номером в закупке.
     *
     * @param expectedOffer лучшая цена
     * @param lotNumber     номер лота
     */
    public void checkBestOfferInLotByLotNumber(String expectedOffer, String lotNumber) {
        int lot = Integer.parseInt(lotNumber);
        String xpath = String.format(LOT_BEST_OFFER_XPATH, lot);
        System.out.printf("[ИНФОРМАЦИЯ]: сформирован локатор для поиска лота по номеру: [%s].%n", xpath);
        String actualOffer = $(By.xpath(xpath)).waitUntil(visible, timeout, polling).getText();
        System.out.printf(
                "[ИНФОРМАЦИЯ]: проверка лота № [%s] ожидаемое лучшее ценовое предложение [%s руб.], реальное [%s].%n",
                lotNumber, expectedOffer, actualOffer);
        Assert.assertTrue("[ОШИБКА]: сумма лучшего ценового предложения не соответствует ожидаемой",
                actualOffer.contains(expectedOffer));
    }

    /**
     * Проверяет лучшую цену в колонке [Лучшее ценовое предложение] для лота с указанным номером в закупке.
     *
     * @param expectedOffer лучшая цена
     * @param columnName    наименование столбца
     * @param lotNumber     номер лота
     */
    public void checkBestOfferInLotByLotNumber(String expectedOffer, String lotNumber, String columnName) {
        int lot = Integer.parseInt(lotNumber);
        String xpath = String.format(controls.find(columnName), lot);
        System.out.printf("[ИНФОРМАЦИЯ]: сформирован локатор для поиска лота по номеру: [%s].%n", xpath);
        String actualOffer = $(By.xpath(xpath)).waitUntil(visible, timeout, polling).getText();
        System.out.printf(
                "[ИНФОРМАЦИЯ]: проверка лота № [%s] ожидаемое лучшее ценовое предложение [%s руб.], реальное [%s].%n",
                lotNumber, expectedOffer, actualOffer);
        Assert.assertTrue("[ОШИБКА]: сумма лучшего ценового предложения не соответствует ожидаемой",
                actualOffer.contains(expectedOffer));
    }
}
