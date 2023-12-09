package ru.PageObjects.Supplier;

import Helpers.SoftAssert;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывающий страницу [Предложения для Вас: XXX] (kontur.ru/supplier/auction/app/recommendedTrades).
 * Created by Vladimir V. Klochkov on 13.04.2018.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class RecommendedTradesPage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Блок с критериями поиска

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Категория закупок] (свернуто)
    private static final SelenideElement purchaseCategoryField =
            $(By.xpath("//input[@value='Выберите категорию.']/../.."));
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Категория закупок] (список возможных значений)
    private static final ElementsCollection listOfValues =
            $$(By.xpath("//input[@value='Выберите категорию.']/../../following-sibling::div[1]/ul/li"));
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Категория закупок] (крестики на выбранных значениях [X])
    private static final String SELECTED_CATEGORIES_REMOVE_CROSSES_XPATH =
            "//input[@value='Выберите категорию.']/../preceding-sibling::li[1]/a";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Найти]
    private static final SelenideElement searchButton = $(By.xpath("//input[@type='button' and @value='Найти']"));
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Очистить]
    private static final SelenideElement clearButton = $(By.xpath("//input[@type='button' and @value='Очистить']"));
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Таблица с результатами поиска

    //------------------------------------------------------------------------------------------------------------------
    // Все ссылки с номерами закупок
    private static final String LINKS_TO_PURCHASES_XPATH = "//a[contains(@href, '/Trade/View.aspx?Id=')]";
    //------------------------------------------------------------------------------------------------------------------
    // Все кнопки [Подать заявку]
    private static final String LINKS_TO_APPLICATIONS_XPATH =
            "//a[contains(@href, '/Application/Create') and contains(., 'Подать заявку')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public RecommendedTradesPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Добавляет категорию с указанным названием в поле [Категория закупок].
     *
     * @param category название категории
     * @return страница [Предложения для Вас: XXX]
     */
    public RecommendedTradesPage setSingleCategory(String category) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: добавляем категорию [%s] в поле [Категория закупок].%n", category);
        purchaseCategoryField.waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(5);
        Assert.assertTrue("[ОШИБКА]: список категорий закупок пуст", listOfValues.size() > 0);
        listOfValues.get(0).waitUntil(visible, timeout, polling);
        listOfValues.findBy(exactText(category)).click();
        TimeUnit.SECONDS.sleep(3);
        $$(By.xpath(SELECTED_CATEGORIES_REMOVE_CROSSES_XPATH)).shouldHaveSize(1);
        return this;
    }

    /**
     * Нажимает на кнопку [Найти].
     *
     * @return страница [Предложения для Вас: XXX]
     */
    public RecommendedTradesPage clickOnSearchButton() throws Exception {
        this.logButtonNameToPress("Найти");
        searchButton.waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName("Найти");
        this.waitLoadingImage(10);
        return this;
    }

    /**
     * Нажимает на кнопку [Очистить].
     *
     * @return страница [Предложения для Вас: XXX]
     */
    public RecommendedTradesPage clickOnClearButton() throws Exception {
        this.logButtonNameToPress("Очистить");
        clearButton.waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName("Очистить");
        this.waitLoadingImage(10);
        return this;
    }

    /**
     * Проверяет, что результаты поиска не пусты.
     */
    public void checkResultsIsNotEmpty() {
        int links = $$(By.xpath(LINKS_TO_PURCHASES_XPATH)).size();
        System.out.printf("[ИНФОРМАЦИЯ]: результаты поиска содержат [%d] закупок.%n", links);
        Assert.assertTrue("[ОШИБКА]: результаты поиска закупок по заданным критериям пусты", links > 0);
    }

    /**
     * Переходит по ссылке с номером извещения о закупке с указанным номером в результатах поиска.
     *
     * @param linkNumber порядковый номер ссылки (сверху-вниз)
     */
    public void goToLinkWithSpecifiedOrderNumber223FZ(int linkNumber) throws Exception {
        ElementsCollection linksToPurchases = $$(By.xpath(LINKS_TO_PURCHASES_XPATH));
        int links = linksToPurchases.size();
        int index = linkNumber - 1;
        System.out.printf("[ИНФОРМАЦИЯ]: переходим по [%d] ссылке с номером извещения о закупке.%n", linkNumber);
        Assert.assertTrue("[ОШИБКА]: указанный порядковый номер ссылки неверен", index < links);
        linksToPurchases.get(index).waitUntil(clickable, timeout, polling).click();
        this.waitForPageLoad();
        for (String winHandle : driver.getWindowHandles()) driver.switchTo().window(winHandle);
    }

    /**
     * Проверяет наличие кнопки [Подать заявку] в указанной строке результатов поиска закупки по 223-ФЗ.
     *
     * @param buttonNumber порядковый номер строки в результатах поиска для 223-ФЗ (сверху-вниз)
     */
    public void checkAddApplicationButtonWithSpecifiedOrderNumber223FZ(int buttonNumber) {
        ElementsCollection linksToApplications = $$(By.xpath(LINKS_TO_APPLICATIONS_XPATH));
        int links = linksToApplications.size();
        int index = buttonNumber - 1;
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем наличие кнопки [Подать заявку] в [%d] строке " +
                "результатов поиска закупки по 223-ФЗ.%n", buttonNumber);
        Assert.assertTrue("[ОШИБКА]: указанный порядковый номер кнопки неверен", index < links);
        SelenideElement linkToApplications = linksToApplications.get(index);
        SoftAssert.assertTrue(String.format(
                "[ОШИБКА]: кнопка [Подать заявку] не отображается в [%d] строке результатов поиска закупки по 223-ФЗ",
                buttonNumber), linkToApplications.isDisplayed());
    }
}
