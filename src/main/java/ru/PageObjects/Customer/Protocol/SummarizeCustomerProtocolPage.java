package ru.PageObjects.Customer.Protocol;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.PrintFormsPreview.ApplicationViewPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Класс описывающий страницу [Протокол подведения итогов ...]
 * (.kontur.ru/customer/lk/Protocols/ProtocolForm?tradeId=).
 * Created by Evgeniy Glushko on 13.04.2016.
 * Updated by Vladimir V. Klochkov on 11.02.2021.
 */
public class SummarizeCustomerProtocolPage extends CommonCustomerProtocolPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола подведения итогов], строка с наименованиями столбцов
    private static final String TABLE_ROW_WITH_ALL_COLUMN_NAMES_XPATH =
            "//h2[contains(., 'Сведения протокола подведения итогов')]/following-sibling::div[1]//table//tr/th";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола подведения итогов], первая строка с данными, все столбцы таблицы
    private static final String TABLE_FIRST_DATA_ROW_ALL_COLUMNS_XPATH =
            "//h2[contains(., 'Сведения протокола подведения итогов')]/following-sibling::div[1]//table//tr[2]/td";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола подведения итогов], столбец [Рейтинг]
    private static final String RATING_COLUMN_XPATH = "//td[contains(@class,'red-centered-text')]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола подведения итогов], заголовок столбца [Лучшее ценовое предложение]
    private static final String TABLE_HEADER_PRICE_OFFER_COLUMN_HEADER_XPATH =
            "//h2[contains(., 'Сведения протокола подведения итогов')]/following-sibling::div[1]" +
                    "//th[contains(., 'ценовое') and contains(., 'предложение')]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола подведения итогов], заголовок столбца [Лучшее ценовое предложение]
    private static final String TABLE_HEADER_CORRECTION_FACTOR_COLUMN_HEADER_XPATH =
            "//h2[contains(., 'Сведения протокола подведения итогов')]" +
                    "/following-sibling::div[1]//th[contains(., 'Лучший поправочный коэффициент')]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола подведения итогов], столбец [Лучшее ценовое предложение]
    private static final String BEST_PRICE_OFFER_COLUMN_XPATH = "//td[@id='BestOfferTD']";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола подведения итогов], столбец [Итоговый балл] (фокус ввода не установлен)
    private static final String TOTAL_SCORE_COLUMN_UNFOCUSED_CELLS_XPATH =
            "//span[@class='k-widget k-numerictextbox mainRating']/span";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола подведения итогов], столбец [Итоговый балл] (фокус ввода установлен)
    private static final String TOTAL_SCORE_COLUMN_FOCUSED_INPUTS_XPATH =
            "//span[@class='k-widget k-numerictextbox mainRating']/span/input[contains(@id, 'MainRating')]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола подведения итогов], ссылки в столбце [Заявка]
    private static final String TABLE_APPLICATION_LINKS_XPATH =
            "//table//td/a[@class='DownloadApplicationLink' and contains(@href, 'PrintFormContentForProtocol')]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола подведения итогов], количество строк в таблице
    private static final String APPLICATION_ROWS_XPATH = "//table//tr[contains(@class,'applicationTR')]";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final ApplicationViewPage applicationViewPage = new ApplicationViewPage(driver);

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public SummarizeCustomerProtocolPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет лучшую цену в столбце [Лучшее ценовое предложение].
     *
     * @param bestPrice ожидаемая лучшая цена
     */
    public void checkBestPriceOffer(String bestPrice) {
        boolean found = false;
        System.out.printf("[ИНФОРМАЦИЯ]: ищем ценовое предложение с суммой: [%s].%n", bestPrice);
        ElementsCollection bestPrices = $$(By.xpath(BEST_PRICE_OFFER_COLUMN_XPATH));
        bestPrices.get(0).waitUntil(visible, timeout, polling);
        for (SelenideElement currentPrice : bestPrices) {
            System.out.printf("[ИНФОРМАЦИЯ]: найдено ценовое предложение с суммой [%s].%n", currentPrice.getText());
            if (currentPrice.getText().contains(bestPrice)) found = true;
        }
        Assert.assertTrue("[ОШИБКА]: указанная сумма не найдена в столбце [Лучшее ценовое предложение]", found);
    }

    /**
     * Проверяет значение в столбце [Лучшее ценовое предложение].
     *
     * @param rowNumber     ожидаемая лучшая цена
     * @param expectedPrice ожидаемая значение
     */
    public void checkBestPriceOfferInRow(int rowNumber, String expectedPrice) {
        String errorMessage = String.format("[ОШИБКА]: в строке [%d] не соответствует значению [%s]",
                rowNumber, expectedPrice);
        ElementsCollection columns = $$(this.getBy(BEST_PRICE_OFFER_COLUMN_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] строк в таблице [Сведения протокола подведения итогов].%n",
                columns.size());
        String actualPrice = columns.get(rowNumber - 1).getText().replaceAll("\n ", " ");
        Assert.assertEquals(errorMessage, expectedPrice, actualPrice);
    }

    /**
     * Проверяет наличие столбца [Лучшее ценовое предложение] в разделе [Сведения протокола подведения итогов].
     *
     * @param expectedColumns ожидаемое количество столбцов в таблице раздела [Сведения протокола подведения итогов]
     */
    public void checkThatThePriceOfferColumnVisibleInTableWhenProtocolHasFormingState(int expectedColumns) {
        ElementsCollection allColumns = $$(By.xpath(TABLE_FIRST_DATA_ROW_ALL_COLUMNS_XPATH)).filterBy(visible);
        allColumns.get(0).waitUntil(exist, timeout, polling);
        System.out.printf("[ИНФОРМАЦИЯ]: ожидаемое количество столбцов в таблице: [%d], реальное [%d].%n",
                expectedColumns, allColumns.size());
        Assert.assertEquals("[ОШИБКА]: количество столбцов в таблице не соответствует ожидаемому",
                expectedColumns, allColumns.size());

        SelenideElement columnHeader = $(By.xpath(TABLE_HEADER_PRICE_OFFER_COLUMN_HEADER_XPATH));
        System.out.println("[ИНФОРМАЦИЯ]: проверяем наличие столбца [Лучшее ценовое предложение].");
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        columnHeader.shouldBe(visible);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        System.out.println("[ИНФОРМАЦИЯ]: столбец [Лучшее ценовое предложение] в наличии - проверка прошла успешно.");
    }

    /**
     * Проверяет наличие столбца [Лучший поправочный коэффициент] в разделе [Сведения протокола подведения итогов].
     *
     * @param expectedColumns ожидаемое количество столбцов в таблице раздела [Сведения протокола подведения итогов]
     */
    public void checkThatTheCorrectionFactorColumnVisibleInTableWhenProtocolHasFormingState(int expectedColumns) {
        ElementsCollection allColumns = $$(By.xpath(TABLE_FIRST_DATA_ROW_ALL_COLUMNS_XPATH)).filterBy(visible);
        allColumns.get(0).waitUntil(exist, timeout, polling);
        System.out.printf("[ИНФОРМАЦИЯ]: ожидаемое количество столбцов в таблице: [%d], реальное [%d].%n",
                expectedColumns, allColumns.size());
        Assert.assertEquals("[ОШИБКА]: количество столбцов в таблице не соответствует ожидаемому",
                expectedColumns, allColumns.size());

        SelenideElement columnHeader = $(By.xpath(TABLE_HEADER_CORRECTION_FACTOR_COLUMN_HEADER_XPATH));
        System.out.println("[ИНФОРМАЦИЯ]: проверяем наличие столбца [Лучший поправочный коэффициент].");
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        columnHeader.shouldBe(visible);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        System.out.println("[ИНФОРМАЦИЯ]: столбец [Лучший поправочный коэффициент] в наличии - проверка прошла успешно.");
    }

    /**
     * Проверяет наличие таблицы [Ценовое предложение] в печатных формах заявок на участие от Поставщиков.
     */
    public void checkThatThePriceOfferTableVisibleInApplicationPrintForms() throws Exception {
        // Операции в окне [Протокол открытия доступа ...] (основное окно приложения)
        String mainWindow = getWebDriver().getWindowHandle();
        ElementsCollection links = $$(By.xpath(TABLE_APPLICATION_LINKS_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] ссылок на печатные формы заявок.%n", links.size());

        for (SelenideElement link : links) {
            link.waitUntil(clickable, timeout, polling);
            this.clickInElementJS(link);
            for (String winHandle : getWebDriver().getWindowHandles()) getWebDriver().switchTo().window(winHandle);

            //==========================================================================================================
            // Операции в окне [Заявка на участие в ...] (Окно было открыто по ссылке, печатная форма заявки)
            //==========================================================================================================
            System.out.println("[ИНФОРМАЦИЯ]: произошло переключение на окно с печатной формой заявки.");
            this.waitForPageLoad();
            applicationViewPage.checkThatThePriceOfferTableVisibleInApplicationPrintForm();
            getWebDriver().close();
            //==========================================================================================================

            // Возвращаемся в окно [Протокол открытия доступа ...] (основное окно приложения)
            getWebDriver().switchTo().window(mainWindow);
            System.out.println("[ИНФОРМАЦИЯ]: произошло переключение на основное окно приложения.");
            this.checkPageUrl("Один из протоколов");
        }
    }

    /**
     * Проверяет наличие таблицы [Поправочный коэффициент] в печатных формах заявок на участие от Поставщиков.
     */
    public void checkThatTheCorrectionFactorTableVisibleInApplicationPrintForms() throws Exception {
        // Операции в окне [Протокол открытия доступа ...] (основное окно приложения)
        String mainWindow = getWebDriver().getWindowHandle();
        ElementsCollection links = $$(By.xpath(TABLE_APPLICATION_LINKS_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] ссылок на печатные формы заявок.%n", links.size());

        for (SelenideElement link : links) {
            link.waitUntil(clickable, timeout, polling);
            this.clickInElementJS(link);
            for (String winHandle : getWebDriver().getWindowHandles()) getWebDriver().switchTo().window(winHandle);

            //==========================================================================================================
            // Операции в окне [Заявка на участие в ...] (Окно было открыто по ссылке, печатная форма заявки)
            //==========================================================================================================
            System.out.println("[ИНФОРМАЦИЯ]: произошло переключение на окно с печатной формой заявки.");
            this.waitForPageLoad();
            applicationViewPage.checkThatTheCorrectionFactorTableVisibleInApplicationPrintForm();
            getWebDriver().close();
            //==========================================================================================================

            // Возвращаемся в окно [Протокол открытия доступа ...] (основное окно приложения)
            getWebDriver().switchTo().window(mainWindow);
            System.out.println("[ИНФОРМАЦИЯ]: произошло переключение на основное окно приложения.");
            this.checkPageUrl("Один из протоколов");
        }
    }

    /**
     * Устанавливает значение в строке колонки [Итоговый балл] таблицы [Сведения протокола подведения итогов].
     *
     * @param rowNumber номер строки в таблице [Сведения протокола подведения итогов]
     * @param cellValue значение ячейки в колонке [Итоговый балл]
     */
    public void fillRowByNumberInTotalScoreColumnInSummarizeProtocol(String rowNumber, String cellValue)
            throws Exception {
        ElementsCollection cellsUnfocused = $$(By.xpath(TOTAL_SCORE_COLUMN_UNFOCUSED_CELLS_XPATH));
        ElementsCollection cellsFocused = $$(By.xpath(TOTAL_SCORE_COLUMN_FOCUSED_INPUTS_XPATH));

        // Проверяем, что таблица [Сведения протокола подведения итогов] содержит строки
        int totalCellsUnfocused = cellsUnfocused.size();
        int totalCellsFocused = cellsFocused.size();
        Assert.assertTrue("[ОШИБКА]: таблица [Сведения протокола подведения итогов] не содержит строк",
                totalCellsUnfocused > 0 && totalCellsUnfocused == totalCellsFocused);

        // Проверяем переданный номер строки ( попадает ли он в диапазон )
        int line = Integer.parseInt(rowNumber) - 1;
        Assert.assertTrue(String.format("[ОШИБКА]: передан некорректный номер строки: [%s]", rowNumber),
                line >= 0 && line < totalCellsUnfocused);

        // Устанавливаем фокус ввода в выбранную ячейку
        this.scrollToCenter(cellsUnfocused.get(line));
        cellsUnfocused.get(line).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(3);

        // Устанавливаем значение выбранной ячейки и убираем из нее фокус ввода
        cellsFocused.get(line).waitUntil(clickable, timeout, polling).sendKeys(cellValue);
        TimeUnit.SECONDS.sleep(3);
        cellsFocused.get(line).sendKeys(Keys.TAB);

        // Проверяем установленное значение
        Assert.assertEquals(
                String.format("[ОШИБКА]: не удалось установить значение: [%s] в колонке [Итоговый балл], строка [%s]",
                        cellValue, rowNumber), cellValue, cellsFocused.get(line).getValue());
        System.out.printf("[ИНФОРМАЦИЯ]: установлено значение: [%s] в колонке [Итоговый балл], строка [%s].%n",
                cellValue, rowNumber);
    }

    /**
     * Проверяет наименование столбца в таблице [Сведения протокола подведения итогов].
     *
     * @param positionColumnInTable - позиция столбца в таблице
     * @param expectedColumnName    - наименование столбца
     */
    public void checkTableColumnName(int positionColumnInTable, String expectedColumnName) {
        String errorMessage = String.format("[ОШИБКА]: имя столбца [%d] не соответствует ожидаемому значению [%s]",
                positionColumnInTable, expectedColumnName);
        ElementsCollection columns = $$(this.getBy(TABLE_ROW_WITH_ALL_COLUMN_NAMES_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] столбцов в таблице [Сведения протокола подведения итогов].%n",
                columns.size());
        //Столбцы таблицы пронумерованы с 1 до 13 так как НУЛЕВОЙ столбец пустой и скрытый
        String actualColumnName = columns.get(positionColumnInTable).getText().replaceAll("\n", " ");
        Assert.assertEquals(errorMessage, expectedColumnName, actualColumnName);
    }

    /**
     * Открывает страницу просмотра печатной формы заявки на участие в конкурсе.
     *
     * @param printFormNumber - номер заявки печатной формы в таблице [Сведения протокола открытия доступа]
     */
    public void openApplicationPrintForms(int printFormNumber) throws Exception {
        ElementsCollection links = $$(By.xpath(TABLE_APPLICATION_LINKS_XPATH));
        System.out.printf("[ИНФОРМАЦИЯ]: обнаружено [%d] ссылок на печатные формы заявок.%n", links.size());
        this.clickInElementJS(links.get(printFormNumber - 1));
        System.out.printf("[ИНФОРМАЦИЯ]: выбрана [%d] ссылка на печатную форму заявки.%n", printFormNumber);
        this.waitForPageLoad();
        for (String winHandle : getWebDriver().getWindowHandles()) getWebDriver().switchTo().window(winHandle);
        this.checkPageUrl("Печатная форма заявки на участие от Поставщика (ПРОЗ)");
    }

    /**
     * Проверяет количество строк в таблице [Сведения протокола подведения итогов].
     *
     * @param rowsExpected ожидаемое количество строк в таблице [Сведения протокола подведения итогов]
     */
    public void checkNumberOfRowsFromSummarizingProtocolInformationTable(int rowsExpected) throws Exception {
        TimeUnit.SECONDS.sleep(5);
        int rowsActual = $$(this.getBy(APPLICATION_ROWS_XPATH)).size();
        Assert.assertEquals(
                "[ОШИБКА]: неверное количество строк в таблице [Сведения протокола подведения итогов]",
                rowsExpected, rowsActual);
        System.out.printf("[ИНФОРМАЦИЯ]: произведена проверка количества строк [%s]" +
                " в таблице [Сведения протокола подведения итогов].%n", rowsExpected);
    }

    /**
     * Проверяет поле [Рейтинг] у указанного участника в таблице [Сведения протокола подведения итогов]
     * на пустое значение.
     *
     * @param participantNumber номер участника закупки
     */
    public void checkRatingColumn(int participantNumber) {
        String errorMessage = String.format(
                "[ОШИБКА]: в поле [Рейтинг] у [%d] участника не должно отображаться значение", participantNumber);
        ElementsCollection ratings = $$(this.getBy(RATING_COLUMN_XPATH));
        String ratingValue = ratings.get(participantNumber - 1).getText();
        Assert.assertEquals(errorMessage, "-", ratingValue);
        System.out.printf("[ИНФОРМАЦИЯ]: произведена проверка поля [Рейтинг] у [%d]" +
                " участника в таблице [Сведения протокола подведения итогов] на пустое значение.%n", participantNumber);
    }

    /**
     * Проверяет поле [Итоговый балл] у указанного участника в таблице [Сведения протокола подведения итогов]
     * на невозможность редактирования.
     *
     * @param participantNumber номер участника закупки
     */
    public void checkTotalScoreIsDisabled(int participantNumber) {
        String errorMessage =
                String.format("[ОШИБКА]: поле [Итоговый балл] у [%d] участника не должно быть редактируемым",
                        participantNumber);
        ElementsCollection totalScoreFields = $$(this.getBy(TOTAL_SCORE_COLUMN_UNFOCUSED_CELLS_XPATH));
        SelenideElement totalScoreField = totalScoreFields.get(participantNumber - 1).$(this.getBy(".//input[1]"));
        Assert.assertTrue(errorMessage, totalScoreField.is(disabled));
        System.out.printf("[ИНФОРМАЦИЯ]: произведена проверка поля [Итоговый балл] у [%d] участника в таблице " +
                "[Сведения протокола подведения итогов] на невозможность редактирования.%n", participantNumber);
    }
}
