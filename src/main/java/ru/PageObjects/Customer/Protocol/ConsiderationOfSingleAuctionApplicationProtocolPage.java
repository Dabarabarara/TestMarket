package ru.PageObjects.Customer.Protocol;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Класс описывающий страницу [Протокол рассмотрения единственной заявки аукциона] (customer/lk/Protocols/ProtocolForm/).
 * Created by Kirill G. Rydzyvylo on 27.10.2020.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class ConsiderationOfSingleAuctionApplicationProtocolPage extends CommonCustomerProtocolPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Раздел [Сведения протокола рассмотрения и оценки заявок]

    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола рассмотрения и оценки заявок], строка с наименованиями столбцов
    private static final String TABLE_ROW_WITH_ALL_COLUMN_NAMES_XPATH =
            "//h2[contains(., 'Сведения протокола рассмотрения единственной заявки')]/..//" +
                    "table[contains(@class,'table')]/tbody/tr/th";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола открытия доступа], ссылки в столбце [Первая часть заявки]
    private static final String LINKS_IN_COLUMN_FIRST_PART_APPLICATION_XPATH =
            "//table//td/a[@class='DownloadApplicationLink' and contains(@href, 'TradeLotApplicationPrintForm?')]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола открытия доступа], ссылки в столбце [Вторая часть заявки]
    private static final String LINKS_IN_COLUMN_SECOND_PART_APPLICATION_XPATH =
            "//table//td/a[@class='DownloadApplicationLink' and contains(@href, 'TradeLotApplicationResultPrintForm?')]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола открытия доступа], ссылки в столбце [Сведения об участнике закупки]
    private static final String LINKS_IN_COLUMN_PROCUREMENT_PARTICIPANT_INFORMATION_XPATH =
            "//table//td/a[@class='DownloadApplicationLink' and contains(@href, 'TradeLotApplicationPrintFormPurchaserInfo?')]";
    //------------------------------------------------------------------------------------------------------------------

    //endregion
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary columnNames = new Dictionary(); // столбцы в таблице
    private final Dictionary pageNames = new Dictionary();   // страницы печатных форм

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     *****************************************************************************************************************
     */
    public ConsiderationOfSingleAuctionApplicationProtocolPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        columnNames.add("Первая часть заявки", LINKS_IN_COLUMN_FIRST_PART_APPLICATION_XPATH);
        columnNames.add("Вторая часть заявки", LINKS_IN_COLUMN_SECOND_PART_APPLICATION_XPATH);
        columnNames.add("Сведения об участнике закупки", LINKS_IN_COLUMN_PROCUREMENT_PARTICIPANT_INFORMATION_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        pageNames.add("Первая часть заявки","Первая часть печатной формы заявки на участие от Поставщика");
        pageNames.add("Вторая часть заявки", "Вторая часть печатной формы заявки на участие от Поставщика");
        pageNames.add("Сведения об участнике закупки", "Печатная форма сведения об участнике закупки (ПРОЗ)");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наименование столбца в таблице [Сведения протокола рассмотрения единственной заявки].
     *
     * @param positionColumnInTable - позиция столбца в таблице
     * @param expectedColumnName    - наименование столбца
     */
    public void checkTableColumnName(int positionColumnInTable, String expectedColumnName) {
        String errorMessage = String.format("[ОШИБКА]: имя столбца [%d] не соответствует ожидаемому значению [%s]",
                positionColumnInTable, expectedColumnName);
        ElementsCollection columns = $$(this.getBy(TABLE_ROW_WITH_ALL_COLUMN_NAMES_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] столбцов в таблице [Сведения протокола открытия доступа].%n",
                columns.size());
        String actualColumnName = columns.get(positionColumnInTable - 1).getText().replaceAll("\n", " ");
        Assert.assertEquals(errorMessage, actualColumnName, expectedColumnName);
    }

    /**
     * Открывает страницу просмотра печатной формы
     *
     * @param columnName - имя столбца
     * @param printFormNumber - номер заявки печатной формы в столбце
     */
    public void openApplicationPrintForm(String columnName, int printFormNumber) throws Exception {
        ElementsCollection links = $$(this.getBy(columnNames.find(columnName)));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] ссылок на печатные формы заявок.%n", links.size());
        this.clickInElementJS(links.get(printFormNumber - 1));
        System.out.printf("[ИНФОРМАЦИЯ]: выбрана [%d] ссылка на печатную форму заявки.%n", printFormNumber);
        this.waitForPageLoad();
        for (String winHandle : getWebDriver().getWindowHandles()) getWebDriver().switchTo().window(winHandle);
        this.checkPageUrl(pageNames.find(columnName));
    }
}
