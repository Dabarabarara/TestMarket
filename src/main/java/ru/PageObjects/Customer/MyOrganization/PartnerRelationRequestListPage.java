package ru.PageObjects.Customer.MyOrganization;

import Helpers.Dictionary;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы со страницей [Исходящие запросы на добавление связи]
 * ( Моя организация / Мои полномочия в закупках партнеров / Кнопка [Запросить полномочия] / Кнопка [Запросить доступ] )
 * ( .kontur.ru/supplier/lk/PartnerRelation/PartnerRelationRequestList.aspx ).
 * Created by Vladimir V. Klochkov on 30.12.2019.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class PartnerRelationRequestListPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов страницы

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public PartnerRelationRequestListPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Заголовок страницы", "//h2[@class='auction_title']");
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Фильтр", "//h2[contains(., 'Фильтр')]");
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Бизнес оператор", "//*[@id='BaseMainContent_MainContent_lbxApplication_chzn']//input");
        controls.add("Поиск", "BaseMainContent_MainContent_btnSearch");
        controls.add("Очистить", "//a[contains(., 'Очистить')]");
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Список запросов на установление связи",
                "//h2[contains(., 'Список запросов на установление связи')]");
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Столбец Id",
                "//table//td[@aria-describedby='BaseMainContent_MainContent_jqgRequest_Id']/a");
        controls.add("Столбец Бизнес оператор",
                "//table//td[@aria-describedby='BaseMainContent_MainContent_jqgRequest_Application']");
        controls.add("Столбец Название организации",
                "//table//td[@aria-describedby='BaseMainContent_MainContent_jqgRequest_Name']");
        controls.add("Столбец Состояние",
                "//table//td[@aria-describedby='BaseMainContent_MainContent_jqgRequest_State']");
        controls.add("Столбец Коментарий",
                "//table//td[@aria-describedby='BaseMainContent_MainContent_jqgRequest_Comments']");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет количество строк в результатах поиска.
     *
     * @param expectedSize ожидаемое количество строк в результатах поиска
     * @return страница [Исходящие запросы на добавление связи]
     */
    public PartnerRelationRequestListPage checkRowCountInSearchResults(int expectedSize) {
        ElementsCollection rows = $$(this.getBy(controls.find("Столбец Id")));
        System.out.printf("[ИНФОРМАЦИЯ]: количество строк в результатах поиска: [%d].%n", rows.size());
        Assert.assertEquals("[ОШИБКА]: не верное количество строк в результатах поиска",
                expectedSize, rows.size());

        return this;
    }

    /**
     * Проверяет текст ячейки в таблице [Список запросов на установление связи] для столбца с указанным именем и
     * номером строки.
     *
     * @param columnName имя столбца в таблице
     * @param rowNumber  порядковый номер строки в таблице
     * @param cellValue  ожидаемый текст в ячейке таблицы
     * @return страница [Исходящие запросы на добавление связи]
     */
    public PartnerRelationRequestListPage
    verifyCellByNameInLineByNumberFromPartnerRelationRequestListTableForText(
            String columnName, String rowNumber, String cellValue) {
        this.verifyCellInTableByColumnElementsAndLineNumberForText("Список запросов на установление связи",
                columnName, $$(this.getBy(controls.find(columnName))), rowNumber, cellValue);

        return this;
    }

    /**
     * Сохраняет номер первого (верхнего) из исходящих запросов на установление связи в параметрах теста.
     */
    public void storeRequestPartnershipNumberInTestParameters() {
        ElementsCollection rows = $$(this.getBy(controls.find("Столбец Id")));
        System.out.printf("[ИНФОРМАЦИЯ]: Количество строк в результатах поиска: [%d].%n", rows.size());
        Assert.assertNotEquals(
                "[ОШИБКА]: пустое количество строк в результатах поиска", 0, rows.size());
        String number = rows.get(0).waitUntil(clickable, timeout, polling).getText();
        config.setParameter("CustomerRequestPartnershipNumber", number);
        System.out.printf(
                "[ИНФОРМАЦИЯ]: номер запроса на установление связи [%s] сохранен в параметрах теста.%n", number);
    }
}
