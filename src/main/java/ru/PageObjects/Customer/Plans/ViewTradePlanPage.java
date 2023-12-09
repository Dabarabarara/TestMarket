package ru.PageObjects.Customer.Plans;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы со страницей [Просмотр плана закупок]
 * ( Главная / Заказчикам / Планы закупок / Просмотр плана закупки )
 * ( .kontur.ru/customer/lk/TradePlans/TradePlan/View/ ).
 * Created by Vladimir V. Klochkov on 06.09.2016.
 * Updated by Alexander S. Vasyurenko on 20.05.2021.
 */
public class ViewTradePlanPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Раздел [Позиции плана]

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Создать извещение] ( сверху над таблицей )
    private static final String CREATE_NOTICE_BUTTON_ID = "btnCreateTradeTop";
    //------------------------------------------------------------------------------------------------------------------
    // Все строки в таблице [Позиции плана]
    private static final String PLAN_POSITIONS_ALL_ROWS_XPATH = "//div[@id='lots']//tbody/tr[@class='plan-lot']";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок выбора [ ] рядом с первым номером плана в таблице [Позиции плана]
    private static final String PLAN_POSITIONS_SELECT_1ST_POS_CHECKBOX_XPATH =
            "(//input[@class='js-selected-lot' and @name='selectedLots'])[1]";
    //------------------------------------------------------------------------------------------------------------------
    // Флажок выбора [ ] рядом с четвертым номером плана в таблице [Позиции плана]
    private static final String PLAN_POSITIONS_SELECT_4TH_POS_CHECKBOX_XPATH =
            "(//input[@class='js-selected-lot' and @name='selectedLots'])[4]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary buttonNames = new Dictionary();   // все кнопки на странице
    private final Dictionary planPositions = new Dictionary(); // таблица [Позиции плана]

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public ViewTradePlanPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Создать извещение верхняя кнопка", CREATE_NOTICE_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
        planPositions.add("Позиции плана все строки", PLAN_POSITIONS_ALL_ROWS_XPATH);
        planPositions.add("Флажок выбора рядом с первым номером плана", PLAN_POSITIONS_SELECT_1ST_POS_CHECKBOX_XPATH);
        planPositions.add("Флажок выбора рядом с четвертым номером плана", PLAN_POSITIONS_SELECT_4TH_POS_CHECKBOX_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Раздел [Позиции плана]

    /**
     * Проверяет, что таблица [Позиции плана] содержит ожидаемое количество строк.
     *
     * @param rows ожидаемое количество строк в таблице [Позиции плана]
     */
    public void checkNumberOfRowsInPlanPositionTable(int rows) {
        ElementsCollection tableRows = $$(this.getBy(planPositions.find("Позиции плана все строки")));

        System.out.printf(
                "[ИНФОРМАЦИЯ]: проверяем количество строк в таблице [Позиции плана], ожидается: [%d], реально: [%d].%n",
                rows, tableRows.size());
        Assert.assertEquals("[ОШИБКА]: количество строк в таблице [Позиции плана] отличается от ожидаемого",
                rows, tableRows.size());
    }

    /**
     * Выбирает  первую позицию в таблице [Позиции плана].
     */
    public void selectFirstPlanPosition() throws Exception {
        SelenideElement checkBox = $(this.getBy(planPositions.find("Флажок выбора рядом с первым номером плана")));
        System.out.println("[ИНФОРМАЦИЯ]: выбираем первую позицию в таблице [Позиции плана].");
        checkBox.waitUntil(clickable, timeout, polling).scrollTo();
        this.clickInElementJS(checkBox);
        TimeUnit.SECONDS.sleep(3);
        checkBox.shouldBe(selected);
        System.out.println("[ИНФОРМАЦИЯ]: первая позиция в таблице [Позиции плана] выбрана успешно.");
    }

    /**
     * Выбирает  четвертую позицию в таблице [Позиции плана].
     */
    public void selectFourthPlanPosition() throws Exception {
        SelenideElement checkBox = $(this.getBy(planPositions.find("Флажок выбора рядом с четвертым номером плана")));
        System.out.println("[ИНФОРМАЦИЯ]: выбираем четвертую позицию в таблице [Позиции плана].");
        checkBox.waitUntil(clickable, timeout, polling).scrollTo();
        this.clickInElementJS(checkBox);
        TimeUnit.SECONDS.sleep(3);
        checkBox.shouldBe(selected);
        System.out.println("[ИНФОРМАЦИЯ]: четвертая позиция в таблице [Позиции плана] выбрана успешно.");
    }

    /**
     * Делает клик по кнопке [Создать извещение].
     */
    public void clickOnCreateNoticeButton() throws Exception {
        SelenideElement createNoticeButton = $(this.getBy(buttonNames.find("Создать извещение верхняя кнопка")));
        do {
            this.logButtonNameToPress("Создать извещение");
            createNoticeButton.waitUntil(clickable, timeout, polling);
            this.clickInElementJS(By.id(CREATE_NOTICE_BUTTON_ID));
            this.logPressedButtonName("Создать извещение");
            this.waitForPageLoad(15);
        } while (createNoticeButton.isDisplayed());
    }

    // endregion

    // region Общие методы для работы с элементами этой страницы

    /**
     * Проверяет, что кнопка отображается на странице и разрешена (visible, enabled).
     *
     * @param controlName имя элемента
     */
    public void isControlClickable(String controlName) {
        this.isControlClickable(controlName, $(this.getBy(buttonNames.find(controlName))));
    }

    // endregion
}
