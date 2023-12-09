package ru.PageObjects;

import Helpers.ConfigContainer;
import Helpers.Screenshoter;
import Helpers.SoftAssert;
import Helpers.Timer;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Абстрактная страница, от которой наследуются реальные страницы приложения.
 * Содержит ссылку на WebDriver и общие для всех страниц методы.
 * Created by Vladimir V. Klochkov on 17.03.2016.
 * Updated by Vladimir V. Klochkov on 20.04.2021.
 */
public abstract class AbstractPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    protected final Timer timer = new Timer();                          // класс для работы со временными интервалами
    protected final ConfigContainer config = ConfigContainer.getInstance(); // экземпляр ConfigContainer
    protected final Screenshoter screenshoter = new Screenshoter();     // экземпляр Screenshoter
    protected final long timeout = 180000;                              // общее время ожидания (миллисекунды)
    protected final long longtime = 360000;                             // время ожидания длительных операций (миллисекунды)
    protected final long polling = 100;                                 // интервал проверки выполнения условия (миллисекунды)
    private final long webDriverWait = 30;                              // время ожидания по умолчанию для WebDriver (секунды)
    private final long shortDrivWait = 5;                               // время ожидания по умолчанию для WebDriver (секунды)
    protected final WebDriver driver;                                   // экземпляр WebDriver
    protected final Condition clickable = and("can be clicked", visible, enabled);     // по элементу можно сделать клик
    protected final Condition readonly = and("read only", visible, disabled);          // элемент нельзя редактировать
    protected final Condition editable = and("can be edited", enabled, not(readonly)); // элемент можно редактировать

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    /*******************************************************************************************************************
     * Общие методы, используемые во всех страницах.
     ******************************************************************************************************************/
    // region Универсальные методы общего применения

    /**
     * Возвращает элемент типа By (By.xpath или By.id) автоматически распознав тип локатора по его содержимому.
     *
     * @param locator локатор элемента
     * @return элемент типа By (By.xpath или By.id)
     */
    protected By getBy(@NotNull String locator) {
        return locator.contains("/") || locator.contains("[") ? By.xpath(locator) : By.id(locator);
    }

    // endregion

    // region Работа с WebDriver

    /**
     * Переопределяет глобальное время ожидания для экземпляра WebDriver.
     *
     * @param time глобальное время ожидания в секундах
     */
    private void driverTimeout(long time) {
        getWebDriver().manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    /**
     * Переопределяет глобальное время ожидания для экземпляра WebDriver.
     */
    protected void setShortDrivWait() {
        this.driverTimeout(this.shortDrivWait);
    }

    /**
     * Переопределяет глобальное время ожидания для экземпляра WebDriver.
     */
    protected void setDefltDrivWait() {
        this.driverTimeout(this.webDriverWait);
    }

    // endregion

    // region Перемещение по странице

    /**
     * Выполняет прокрутку страницы к указанному элементу таким образом, что он оказывется по центру страницы.
     *
     * @param element указанный элемент
     */
    protected void scrollToCenter(By element) {
        this.scrollToCenter($(element));
    }

    /**
     * Выполняет прокрутку страницы к указанному элементу таким образом, что он оказывется по центру страницы.
     *
     * @param element указанный элемент
     */
    protected void scrollToCenter(SelenideElement element) {
        String scrollingScript =
                "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);" +
                        "var elementTop = arguments[0].getBoundingClientRect().top;" +
                        "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        executeJavaScript(scrollingScript, element);
        System.out.println("[ИНФОРМАЦИЯ]: произведен scrollToCenter с помощью JS.");
    }

    // endregion

    // region Установка значений

    /**
     * Устанавливает значение текстового элемента страницы, который появляется в DOM с задержкой.
     * Используется для проблемных полей в которых нельзя установить значение с первого раза.
     *
     * @param by        идентификатор или локатор элемента, которому следует установить значение
     * @param textToSet значение поля, которое требуется установить
     */
    protected static void setTextFieldWithRetry(By by, String textToSet) {
        for (int i = 0; i < 3; i++) {
            try {
                $(by).sendKeys(textToSet);
                break;
            } catch (Exception e) {
                System.err.print(e.getLocalizedMessage());
            }
        }
    }

    /**
     * Ожидает текстовое поле, очищает его, делает по нему клик и заполняет поле требуемым значением.
     * Метод эффективен для работы с обычными полями и полями со встроенным календарем которые допускают ввод текста.
     *
     * @param fieldId идентификатор текстового поля
     * @param value   требуемое значение поля
     */
    protected void waitClearClickAndSendKeysById(String fieldId, String value) throws Exception {
        this.waitClearClickAndSendKeys(By.id(fieldId), value);
    }

    /**
     * Ожидает текстовое поле, очищает его, делает по нему клик и заполняет поле требуемым значением.
     * Метод эффективен для работы с обычными полями и полями со встроенным календарем которые допускают ввод текста.
     *
     * @param by    текстовое поле
     * @param value требуемое значение поля
     */
    protected void waitClearClickAndSendKeys(By by, String value) throws Exception {
        int maxTries = Integer.parseInt(config.getConfigParameter("MaxTriesToSetFieldValue"));
        int i = 1;
        $(by).waitUntil(visible, timeout, polling);
        while (!$(by).getValue().equals(value) && i <= maxTries) {
            $(by).clear();
            $(by).click();
            $($(by)).val(value);
            $($(by)).sendKeys(Keys.ENTER);
            TimeUnit.SECONDS.sleep(1);
            i++;
        }
    }

    /**
     * Устанавливает значение элемента страницы используя его id с помощью JS.
     *
     * @param id    id элемента страницы для которого следует установить значение
     * @param value требуемое значение элемента
     */
    protected void setValueInElementJS(String id, String value) throws Exception {
        String script = String.format("document.getElementById(\"%s\").value=\"%s\";", id, value);
        ((JavascriptExecutor) driver).executeScript(script);
        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * Убирает фокус из поля ввода если введенное в него значение не воспринимается пока курсор находится в поле.
     */
    protected void removeFocusFromInputFieldJS() {
        executeJavaScript("document.activeElement.blur()");
    }

    /**
     * Устанавливает значение поля, имеющего тип [kendoNumericTextBox] с последующей проверкой значения.
     *
     * @param fieldName имя поля для сообщения об ошибке
     * @param id        локатор поля
     * @param value     значение поля
     */
    protected void setKendoNumericTextBoxJS(String fieldName, String id, String value) throws Exception {
        this.setFocusInKendoNumericTextBoxJS(id);
        this.setKendoNumericTextBox(fieldName, By.id(id), value);
    }

    /**
     * Устанавливает значение поля, имеющего тип [kendoNumericTextBox] с последующей проверкой значения.
     *
     * @param fieldName имя поля для сообщения об ошибке
     * @param by        поле
     * @param value     значение поля
     */
    protected void setKendoNumericTextBox(String fieldName, By by, String value) {
        $(by).waitUntil(clickable, timeout, polling).clear();
        $(by).val(value).click();
        Assert.assertEquals(String.format("[ОШИБКА]: неправильное значение в поле со счётчиком: [%s].", fieldName),
                value.trim(), $(by).val().trim());
        System.out.printf("[ИНФОРМАЦИЯ]: в поле со счетчиком [%s] установлено значение [%s].%n", fieldName, value);
    }

    /**
     * Делает щелчок мышью по раскрывающемуся списку значений и выбирает требуемое значение в открытом списке.
     *
     * @param fieldName     имя поля для отчета
     * @param collapsedList список значений поля в свёрнутом виде ( для того, чтобы по нему кликнуть )
     * @param desiredValue  требуемое значение для выбора в открытом списке
     * @param selectedValue реально выбранное значение поля
     */
    protected void waitForListOpensAndSelectDesiredValue(
            String fieldName, @NotNull SelenideElement collapsedList, @NotNull SelenideElement desiredValue,
            @NotNull SelenideElement selectedValue) throws Exception {

        // Выровняем требуемый список значений по центру страницы и раскроем его допустимые значения
        this.scrollToCenter(collapsedList);
        this.waitForListOpens(collapsedList, desiredValue);

        // Требуемое значение должно отображаться в списке, запоминаем его текст в качестве эталонного
        desiredValue.waitUntil(clickable, timeout, polling);
        String expectedText = desiredValue.getText();
        this.clickInElementJS(desiredValue);

        // Признаком успешного выбора значения из списка служит равенство текущего и требуемого значений
        String actualText = this.getFieldValueOrText(fieldName, selectedValue);
        Assert.assertEquals(String.format("[ОШИБКА]: значение поля [%s] было выбрано некорректно", fieldName),
                expectedText, actualText);
        System.out.printf("[ИНФОРМАЦИЯ]: произведен выбор значения [%s] в поле [%s].%n", actualText, fieldName);
    }

    /**
     * Делает щелчок мышью по раскрывающемуся списку значений в строке таблицы с указанным номером
     * и выбирает требуемое значение в открытом списке.
     *
     * @param tableName      имя таблицы для отчета
     * @param fieldName      имя поля для отчета
     * @param rowNumber      порядковый номер строки в таблице, где требуется выбрать значение из списка
     * @param collapsedLists список значений поля в свёрнутом виде ( для того, чтобы по нему кликнуть )
     * @param desiredValues  требуемое значение для выбора в открытом списке
     * @param selectedValues реально выбранное значение поля
     */
    protected void selectDesiredValueInLineByNumberFromTable(
            String tableName, String fieldName, String rowNumber, @NotNull ElementsCollection collapsedLists,
            @NotNull ElementsCollection desiredValues, @NotNull ElementsCollection selectedValues) throws Exception {

        // Сначала проанализируем размер таблицы ( не пуста ли она ? )
        int columnSize = collapsedLists.size();
        Assert.assertNotEquals(String.format("[ОШИБКА]: таблица [%s] не содержит строк", tableName),
                0, columnSize);
        System.out.printf("[ИНФОРМАЦИЯ]: таблица [%s] содержит [%d] строк(и).%n", tableName, columnSize);

        // Теперь проверим входит ли переданный в качестве параметра номер строки в диапазон строк таблицы
        System.out.printf("[ИНФОРМАЦИЯ]: в метод передан номер строки: [%s], текущий допустимый диапазон: [1 - %d].%n",
                rowNumber, columnSize);
        int line = Integer.parseInt(rowNumber) - 1;
        Assert.assertTrue(String.format("[ОШИБКА]: в метод передан номер строки вне диапазона: [1 - %d]", columnSize),
                line >= 0 && line < columnSize);

        // Таблица не пуста, переданный в качестве параметра номер строки корректен, устанавливаем значение поля
        this.waitForListOpensAndSelectDesiredValue(
                fieldName, collapsedLists.get(line), desiredValues.get(line), selectedValues.get(line));
    }

    // endregion

    // region Проверка значений

    /**
     * Проверяет, что элемент отображается на странице и разрешен.
     *
     * @param controlName имя элемента
     * @param control     элемент
     */
    protected void isControlClickable(String controlName, SelenideElement control) {
        control.waitUntil(exist, timeout, polling);
        Assert.assertTrue(String.format(
                "[ОШИБКА]: элемент [%s] вообще не отображается на странице или сделан недоступным", controlName),
                control.is(clickable));
        System.out.printf("[ИНФОРМАЦИЯ]: элемент [%s] отображается на странице и доступен.%n", controlName);
    }

    /**
     * Проверяет количество элементов в коллекции.
     *
     * @param collection   коллекция элементов
     * @param expectedSize ожидаемое количество элементов
     */
    protected void checkElementsCollectionSize(@NotNull ElementsCollection collection, int expectedSize) {
        Assert.assertEquals(String.format(
                "[ОШИБКА]: ожидаемое количество элементов: [%d], реальное количество элементов: [%d]",
                expectedSize, collection.size()), expectedSize, collection.size());
    }

    /**
     * Возвращает true, если строка символов не инициализирована или пуста.
     *
     * @param value строка символов
     * @return true, если строка символов не инициализирована или пуста
     */
    protected boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    /**
     * Проверяет содержимое строки на null и пустую строку.
     *
     * @param value true - если строка не null и не пуста
     */
    private Boolean notEmpty(String value) {
        return value != null && !value.isEmpty();
    }

    /**
     * Возвращает значение поля.
     *
     * @param fieldName имя поля
     * @param by        локатор поля
     * @return значение поля
     */
    public String getFieldValueOrText(String fieldName, By by) {
        return this.getFieldValueOrText(fieldName, $(by));
    }

    /**
     * Возвращает значение поля.
     *
     * @param fieldName имя поля
     * @param field     поле
     * @return значение поля
     */
    public String getFieldValueOrText(String fieldName, SelenideElement field) {
        String content = field.waitUntil(exist, timeout, polling).getValue();
        if (content == null || content.isEmpty()) content = field.getText();
        if (content == null || content.isEmpty()) content = field.getAttribute("aria-valuenow");
        Assert.assertFalse(String.format("[ОШИБКА]: не удалось получить значение поля [%s]", fieldName),
                content == null || content.isEmpty());
        System.out.printf("[ИНФОРМАЦИЯ]: получено значение [%s] из поля [%s].%n", content, fieldName);
        return content;
    }

    /**
     * Проверяет поле на возможность редактирования.
     *
     * @param fieldName имя поля
     * @param field     поле
     * @return истина, если поле доступно для редактирования
     */
    public boolean isFieldEditable(String fieldName, SelenideElement field) {
        String tagName = field.getTagName();
        boolean isEditable;
        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] с тегом [%s] на возможность редактирования.%n",
                fieldName, tagName);

        if (tagName.equals("input") || tagName.equals("textarea")) {
            System.out.printf("[ИНФОРМАЦИЯ]: поле [%s] является областью ввода, проверяем его атрибуты.%n", fieldName);
            isEditable = field.is(editable);
        } else {
            System.out.printf("[ИНФОРМАЦИЯ]: поле [%s] не является областью ввода, пытаемся установить на него фокус.%n",
                    fieldName);
            field.waitUntil(clickable, timeout, polling).click();
            isEditable = field.is(focused);
        }

        System.out.printf("[ИНФОРМАЦИЯ]: поле [%s] с тегом [%s] %s редактировать.%n", fieldName, tagName,
                isEditable ? "можно" : "нельзя");
        return isEditable;
    }

    /**
     * Проверяет, содержится ли в значении поля искомая подстрока.
     *
     * @param by    локатор поля
     * @param value искомая подстрока
     * @return true, если требуемое значение содержится в поле как подстрока
     */
    protected boolean verifyFieldContent(By by, String value) {
        return this.verifyFieldContent($(by), value);
    }

    /**
     * Проверяет, содержится ли в значении поля искомая подстрока.
     *
     * @param field поле
     * @param value искомая подстрока
     * @return true, если требуемое значение содержится в поле как подстрока
     */
    protected boolean verifyFieldContent(SelenideElement field, String value) {
        String content = field.waitUntil(exist, timeout, polling).getValue();
        if (content == null || content.isEmpty()) content = field.getText();
        if (content == null || content.isEmpty()) content = field.getAttribute("aria-valuenow");
        Assert.assertFalse(String.format("[ОШИБКА]: не удалось получить значение поля [%s]", field),
                content == null || content.isEmpty());
        System.out.printf("[ИНФОРМАЦИЯ]: ожидаемый фрагмент текста [%s], реальное содержимое поля [%s].%n",
                value, content);
        return content.contains(value);
    }

    /**
     * Проверяет, содержится ли в значении поля с номером телефона или факса искомая подстрока.
     *
     * @param by    локатор поля
     * @param value искомая подстрока с номером телефона или факса
     * @return true, если требуемое значение с номером телефона или факса содержится в поле как подстрока
     */
    protected boolean verifyFieldWithPhoneNumber(By by, String value) {
        String content = $(by).waitUntil(exist, timeout, polling).getValue();
        if (content == null || content.isEmpty()) content = $(by).waitUntil(exist, timeout, polling).getText();
        Assert.assertFalse(String.
                        format(">>> (verifyFieldContent) Не удалось получить значение поля [%s].", by.toString()),
                content == null || content.isEmpty());
        String formattedValue = value.
                replace(" ", "").
                replace("+", "").
                replace("-", "").
                replace("(", "").
                replace(")", "");
        String formattedContent = content.
                replace(" ", "").
                replace("+", "").
                replace("-", "").
                replace("(", "").
                replace(")", "");
        System.out.printf("[ИНФОРМАЦИЯ]: ожидаемый фрагмент текста [%s], реальное содержимое поля [%s].%n",
                formattedValue, formattedContent);
        return formattedContent.contains(formattedValue);
    }

    /**
     * Проверяет, что поле содержит не пустое значение.
     *
     * @param by локатор поля
     * @return true, если поле содержит не пустое значение
     */
    protected boolean verifyFieldIsNotEmpty(By by) {
        String content = $(by).waitUntil(exist, timeout, polling).getValue();
        if (content == null || content.isEmpty()) content = $(by).waitUntil(exist, timeout, polling).getText();
        Assert.assertNotNull(String.
                format("[ОШИБКА]: не удалось получить значение поля [%s]", by.toString()), content);
        System.out.printf("[ИНФОРМАЦИЯ]: значение поля [%s] ( не должно быть пустым ).%n", content);
        return !content.isEmpty();
    }

    /**
     * Проверяет, что поле содержит дату в виде строки соответствующую заданному шаблону даты.
     *
     * @param fieldName имя поля, содержащего строковое значение даты
     * @param field     поле, содержащее строковое значение даты
     * @param pattern   строковое значение шаблона даты
     */
    public void verifyDateFieldForPattern(String fieldName, SelenideElement field, String pattern) {
        String content = field.waitUntil(exist, timeout, polling).getValue();
        if (content == null || content.isEmpty()) content = field.waitUntil(exist, timeout, polling).getText();

        Assert.assertNotNull(String.format("[ОШИБКА]: не удалось получить значение поля [%s]", fieldName), content);

        System.out.printf("[ИНФОРМАЦИЯ]: получено значение даты [%s] из поля [%s].%n", content, fieldName);
        this.verifyDateFieldForValidValue(content, pattern);
    }

    /**
     * Проверяет, что дата в виде строки соответствует заданному шаблону даты.
     *
     * @param date    строковое значение даты
     * @param pattern строковое значение шаблона даты
     */
    public void verifyDateFieldForValidValue(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        boolean valid = false;

        try {
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+3"));
            sdf.parse(date);
            // strict mode - check 30 or 31 days, leap year
            sdf.setLenient(false);
            valid = true;
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            Assert.assertTrue(String.format(
                    "[ОШИБКА]: значение [%s] не соответствует заданному Вами шаблону даты [%s]", date, pattern), valid);
        }

        System.out.printf("[ИНФОРМАЦИЯ]: значение даты [%s] соответствует заданному Вами шаблону даты [%s].%n",
                date, pattern);
    }

    /**
     * Возвращает индекс [1..N] столбца таблицы, заголовок которого содержит искомый текст.
     *
     * @param headers коллекция заголовков столбцов в таблице
     * @param text    искомый текст заголовка столбца
     * @param exact   признак точного совпадения или просто вхождения текста в заголовок столбца
     * @return индекс [1..N] столбца таблицы, заголовок которого содержит искомый текст
     */
    public int getIndexOfColumnByTextInTableHeaders(ElementsCollection headers, String text, boolean exact) {
        int numberOfColumns = headers.size();

        System.out.printf(
                "[ИНФОРМАЦИЯ]: ищем столбец с текстом [%s] по %s строки среди [%d] заголовков столбцов таблицы.%n", text,
                exact ? "точному совпадению" : "вхождению", headers.size());

        for (int i = 0; i < numberOfColumns; i++) {
            // Очень часто искомый текст без переносов строк содержится в атрибуте [title]
            String hdrTitle = headers.get(i).getAttribute("title");

            // Если атрибут [title] отсутствует или не содержит текста - берем обычный текст элемента
            String hdrText = hdrTitle.isEmpty() ? headers.get(i).getText() : hdrTitle;

            // Убираем лишние пробелы и переносы строк из текста заголовка столбца
            if (!hdrText.trim().isEmpty()) hdrText = hdrText.trim().replace("\n", " ");
            System.out.printf("[ИНФОРМАЦИЯ]: столбец=[%d], атр. title=[%s], текст=[%s].%n", i + 1, hdrTitle, hdrText);

            // Собственно сравнение и выход из цикла если условие истинно
            if ((exact && hdrText.equals(text)) || (!exact && hdrText.contains(text))) return ++i;
        }

        throw new AssertionError(String.format("[ОШИБКА]: в таблице не найдена колонка с текстом [%s]", text));
    }

    /**
     * Возвращает столбец ( коллекцию ячеек ) таблицы по первому найденному тексту в заголовке столбца.
     *
     * @param locatorTemplate шаблон локатора столбца, куда подставляется конкретный индекс
     * @param headersLocator  локатор коллекции заголовков столбцов в таблице
     * @param text            искомый текст заголовка столбца
     * @param exact           признак точного совпадения или просто вхождения текста в заголовок столбца
     * @return столбец ( коллекцию ячеек ) таблицы по первому найденному тексту в заголовке столбца
     */
    public ElementsCollection getColumnElementsCollectionByTextInTableHeader(
            String locatorTemplate, String headersLocator, String text, boolean exact) {

        // Коллекция заголовков столбцов таблицы для поиска в них требуемого текста
        ElementsCollection headers = $$(this.getBy(headersLocator));
        System.out.printf("[ИНФОРМАЦИЯ]: шаблон локатора столбца [%s], локатор строки заголовков столбцов [%s], " +
                        "количество заголовков столбцов [%d], текст для поиска в заголовке столбца [%s] по %s.%n",
                locatorTemplate, headersLocator, headers.size(), text, exact ? "точному совпадению" : "вхождению");

        // Столбец, который мы ищем по тексту в его заголовке
        ElementsCollection column = $$(this.getBy(
                String.format(locatorTemplate, this.getIndexOfColumnByTextInTableHeaders(headers, text, exact))));

        // Пустой столбец не допускается, скорее всего ошибка в записи локаторов
        Assert.assertNotEquals("[ОШИБКА]: столбец не содержит ни одной ячейки", 0, column.size());

        return column;
    }

    /**
     * Проверяет текст ячейки [.getText().trim()] в столбце таблицы для указанного номера строки.
     *
     * @param tableName      имя таблицы
     * @param columnName     имя столбца в таблице
     * @param columnElements содержимое столбца таблицы (ячейки)
     * @param rowNumber      номер строки в столбце
     * @param cellValue      ожидаемый текст в ячейке таблицы
     */
    protected void verifyCellInTableByColumnElementsAndLineNumberForText(String tableName, String columnName,
                                                                         ElementsCollection columnElements,
                                                                         String rowNumber, String cellValue) {
        String message1 = "[ИНФОРМАЦИЯ]: проверяем ячейку таблицы [%s], столбец [%s], строка [%s], на текст [%s].";
        System.out.printf((message1) + "%n", tableName, columnName, rowNumber, cellValue);

        int columnSize = columnElements.size();
        Assert.assertTrue(String.format("[ОШИБКА]: таблица [%s] не содержит строк", tableName), columnSize > 0);

        int line = Integer.parseInt(rowNumber) - 1;
        String message2 = "[ОШИБКА]: передан некорректный номер строки: [%s]";
        Assert.assertTrue(String.format(message2, rowNumber), line >= 0 && line < columnSize);

        // В редких случаях значение содержится в @title а возвращаемый текст - пустая строка
        String actualValue = columnElements.get(line).getText().trim().isEmpty() ?
                columnElements.get(line).getAttribute("title").trim() : columnElements.get(line).getText().trim();
        if (cellValue.trim().isEmpty()) {
            String message = "[ОШИБКА]: ожидалась пустая ячейка, реальный текст ячейки: [%s]";
            Assert.assertTrue(String.format(message, actualValue), actualValue.isEmpty());
        } else {
            String message = "[ОШИБКА]: ожидаемый текст ячейки: [%s], реальный текст ячейки: [%s]";
            Assert.assertTrue(String.format(message, cellValue, actualValue), actualValue.contains(cellValue));
        }
    }

    /**
     * Проверяет коллекцию элементов на определенное значение в атрибуте Text.
     *
     * @param results        коллекция элементов
     * @param searchCriteria требуемое значение в атрибуте Text
     */
    private void checkTheSameValuesInSearchResults(ElementsCollection results, String searchCriteria) {

        // Ожидаем окончания загрузки результатов поиска ( они не должны быть пустыми )
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        results.get(0).waitUntil(clickable, longtime, polling);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        // Выводим отладочную информацию
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем результаты поиска по значению [%s].%n", searchCriteria);
        System.out.printf("[ИНФОРМАЦИЯ]: количество строк в результатах поиска   [%d].%n", results.size());

        // Проверяем, что каждый элемент коллекции содержит требуемое значение в атрибуте Text
        for (SelenideElement result : results) {
            System.out.printf("[ИНФОРМАЦИЯ]: текущее значение [%s] должно содержать строку [%s].%n",
                    result.getText(), searchCriteria);
            SoftAssert.assertTrue("[ОШИБКА]: фильтр не сработал", result.getText().contains(searchCriteria));
        }
    }

    /**
     * Проверяет коллекцию элементов на отсутствие определенного значения в атрибуте Text (например заголовки столбцов).
     *
     * @param columns    коллекция элементов
     * @param columnText значение в атрибуте Text, которое должно отсутствовать
     */
    protected void checkColumnAbsenceInColumnsCollectionByText(ElementsCollection columns, String columnText) {
        boolean found = false;
        System.out.
                printf("[ИНФОРМАЦИЯ]: проверяем отсутствие значения [%s] в заголовках столбцов таблицы.%n", columnText);
        for (SelenideElement column : columns)
            if (column.getText().contains(columnText)) {
                System.out.printf("[ИНФОРМАЦИЯ]: найден заголовок столбца таблицы с текстом [%s].%n", column.getText());
                found = true;
                break;
            }
        Assert.assertFalse(
                String.format("[ОШИБКА]: значение [%s] найдено в заголовках столбцов таблицы", columnText), found);
        System.out.printf("[ИНФОРМАЦИЯ]: значение [%s] не найдено в заголовках столбцов таблицы.%n", columnText);
    }

    /**
     * Проверяет коллекцию элементов на присутствие определенного значения в атрибуте Text (например заголовки столбцов).
     *
     * @param columns    коллекция элементов
     * @param columnText значение в атрибуте Text, которое должно присутствовать
     */
    protected void checkColumnPresenceInColumnsCollectionByText(ElementsCollection columns, String columnText) {
        boolean found = false;
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем присутствие значения [%s] в заголовках столбцов таблицы.%n",
                columnText);
        for (SelenideElement column : columns)
            if (column.getText().contains(columnText)) {
                System.out.printf("[ИНФОРМАЦИЯ]: найден заголовок столбца таблицы с текстом [%s].%n", column.getText());
                found = true;
                break;
            }
        Assert.assertTrue(
                String.format("[ОШИБКА]: значение [%s] не найдено в заголовках столбцов таблицы", columnText), found);
        System.out.printf("[ИНФОРМАЦИЯ]: значение [%s] найдено в заголовках столбцов таблицы.%n", columnText);
    }

    /**
     * Проверяет коллекцию элементов на определенное значение в атрибуте Text.
     *
     * @param resultsCollectionLocator локатор коллекции элементов
     * @param searchCriteria           требуемое значение в атрибуте Text
     */
    protected void checkTheSameValuesInSearchResults(String resultsCollectionLocator, String searchCriteria) {
        this.checkTheSameValuesInSearchResults($$(this.getBy(resultsCollectionLocator)), searchCriteria);
    }

    // endregion

    // region Нажатие на кнопки и прочие элементы, установка фокуса ввода на требуемом элементе

    /**
     * Выполняет прокрутку страницы к указанному элементу таким образом, что он оказывется по центру и
     * делает клик по элементу используя SelenideElement с помощью JS.
     *
     * @param locator локатор указанного элемента
     */
    protected void scrollToCenterAndclickInElementJS(String locator) throws Exception {
        this.scrollToCenterAndclickInElementJS($(this.getBy(locator)));
    }

    /**
     * Выполняет прокрутку страницы к указанному элементу таким образом, что он оказывется по центру и
     * делает клик по элементу используя SelenideElement с помощью JS.
     *
     * @param element указанный элемент
     */
    protected void scrollToCenterAndclickInElementJS(By element) throws Exception {
        this.scrollToCenterAndclickInElementJS($(element));
    }

    /**
     * Выполняет прокрутку страницы к указанному элементу таким образом, что он оказывется по центру и
     * делает клик по элементу используя SelenideElement с помощью JS.
     *
     * @param element указанный элемент
     */
    protected void scrollToCenterAndclickInElementJS(SelenideElement element) throws Exception {
        this.scrollToCenter(element);
        TimeUnit.SECONDS.sleep(1);
        element.waitUntil(clickable, longtime, polling);
        this.clickInElementJS(element);
    }

    /**
     * Метод делает клик по элементу страницы используя By с помощью JS.
     *
     * @param by элемент по которому следует кликнуть
     */
    protected void clickInElementJS(By by) throws Exception {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", $(by));
        TimeUnit.SECONDS.sleep(1);
        System.out.println("[ИНФОРМАЦИЯ]: произведен click с помощью JS.");
    }

    /**
     * Метод делает клик по элементу страницы используя SelenideElement с помощью JS.
     *
     * @param element элемент по которому следует кликнуть
     */
    protected void clickInElementJS(SelenideElement element) throws Exception {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("[ИНФОРМАЦИЯ]: произведен click с помощью JS.");
    }

    /**
     * Устанавливает фокус в поля типа "kendoNumericTextBox" с помощью JS.
     *
     * @param id локатор элемента
     */
    private void setFocusInKendoNumericTextBoxJS(String id) throws Exception {
        String script = "$(\"#" + id + "\").data(\"kendoNumericTextBox\").focus();";
        ((JavascriptExecutor) driver).executeScript(script);
        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * Делает щелчок мышью по раскрывающемуся списку значений и проверяет видимость первого элемента в открытом списке.
     *
     * @param collapsedList список значений поля в свёрнутом виде ( для того, чтобы по нему кликнуть )
     * @param firstElement  первый элемент в открытом списке
     */
    protected void waitForListOpens(@NotNull SelenideElement collapsedList, @NotNull SelenideElement firstElement)
            throws Exception {
        int waitTimeMinutes = Integer.parseInt(config.getConfigParameter("MaxStatusWaitTime"));
        int nTries = waitTimeMinutes * 60 / 3;
        int i = 0;

        System.out.printf("[ИНФОРМАЦИЯ]: установленное максимальное время ожидания появления списка значений %d минут.%n",
                waitTimeMinutes);

        do {
            collapsedList.waitUntil(exist, timeout, polling).shouldBe(clickable).click();
            TimeUnit.SECONDS.sleep(3);
            i++;
            System.out.printf("[ИНФОРМАЦИЯ]: %s попытка № %d открыть список значений.%n",
                    timer.getCurrentDateTime("dd.MM.yyyy HH:mm:ss"), i);
        } while (i <= nTries && !firstElement.isDisplayed());

        Assert.assertTrue("Все попытки открыть список значений не увкенчались успехом", i <= nTries);
    }

    // endregion

    // region Операции с файлами

    /**
     * Делает клик по ссылке для скачивания файла, загружает его и проверяет, что файл загружен успешно.
     *
     * @param linkToFile ссылка для скачивания файла
     */
    protected void downloadFileByLink(By linkToFile) throws Exception {
        this.downloadFileByLink($(linkToFile));
    }

    /**
     * Делает клик по ссылке для скачивания файла, загружает его и проверяет, что файл загружен успешно.
     *
     * @param linkToFile ссылка для скачивания файла
     */
    protected void downloadFileByLink(SelenideElement linkToFile) throws Exception {

        // Проверяем полученное нами имя файла
        System.out.println("[ИНФОРМАЦИЯ]: получаем имя файла из ссылки...");
        String fileName = linkToFile.waitUntil(exist, timeout, polling).getText();
        if (!this.notEmpty(fileName)) fileName = linkToFile.getValue();
        Assert.assertTrue(
                "[ОШИБКА]: полученное из ссылки имя файла для загрузки пусто", this.notEmpty(fileName));
        System.out.printf("[ИНФОРМАЦИЯ]: полученное имя файла из ссылки: [%s]...%n", fileName);

        // Делаем щелчок по ссылке на этот файл
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем ссылку на прикрепленный файл: [%s]...%n", fileName);
        System.out.println("[ИНФОРМАЦИЯ]: делаем щелчок по ссылке на файл...");
        this.clickInElementJS(linkToFile);
        TimeUnit.SECONDS.sleep(10);
        this.waitForFileDownloads(fileName);
    }

    /**
     * Ожидает загрузки файла с указанным именем во временный каталог и проверяет, что файл загружен успешно.
     *
     * @param fileName имя файла, который должен быть загружен во временный каталог
     */
    protected void waitForFileDownloads(String fileName) throws Exception {
        boolean fileDownloadedSuccessfully = false;
        long minFileLength = config.getMinDownloadedFileLength();

        // Ждем завершения загрузки файла с интервалом в 5 секунд
        for (int i = 0; i < 30; i++) {
            System.out.printf("[ИНФОРМАЦИЯ]: ожидаем завершения загрузки файла [%d] секунд, осталось ждать [%d] секунд...%n",
                    i * 5, (30 - i) * 5);
            File file = new File(config.getPathToTempFolderWithRandomName() + "\\" + fileName);
            if (file.exists() && file.length() >= minFileLength) {
                System.out.printf("[ИНФОРМАЦИЯ]: размер загруженного файла: [%d] байт...%n", file.length());
                fileDownloadedSuccessfully = true;
                break;
            }
            TimeUnit.SECONDS.sleep(5);
        }

        // Проверяем, что файл загружен успешно
        System.out.println("[ИНФОРМАЦИЯ]: проверяем, что файл загружен успешно...");
        Assert.assertTrue("[ОШИБКА]: не удалось загрузить файл на диск", fileDownloadedSuccessfully);
        System.out.println("[ИНФОРМАЦИЯ]: файл загружен успешно.");

        // Удаляем загруженный файл вместе с временным каталогом
        System.out.println("[ИНФОРМАЦИЯ]: удаляем загруженный файл вместе с временным каталогом.");
        File folderToDelete = new File(config.getPathToTempFolderWithRandomName());
        this.deleteTemporaryFolderWithFiles(folderToDelete);
    }

    /**
     * Удаляет временную папку теста для загрузки файлов вместе с содержащимися в ней файлами.
     *
     * @param file путь к папке
     */
    protected void deleteTemporaryFolderWithFiles(File file) {
        if (!file.exists()) return;
        if (file.isDirectory()) for (File f : file.listFiles()) this.deleteTemporaryFolderWithFiles(f);
        file.delete();
    }

    // endregion

    // region Логирование

    /**
     * Выводит в консоль сообщение о том, что будет нажата кнопка с указанным именем.
     *
     * @param buttonName имя кнопки, которую следует нажать
     */
    protected void logButtonNameToPress(String buttonName) {
        System.out.printf("[ИНФОРМАЦИЯ]: имя кнопки/ссылки, которую следует нажать: [%s].%n", buttonName);
    }

    /**
     * Выводит в консоль сообщение о том, что была нажата кнопка с указанным именем.
     *
     * @param buttonName имя нажатой кнопки
     */
    protected void logPressedButtonName(String buttonName) {
        System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие кнопки/ссылки: [%s].%n", buttonName);
    }

    /**
     * Выводит в консоль сообщение о том, что была нажата кнопка с указанным именем.
     *
     * @param buttonName имя нажатой кнопки
     * @param attemptNo  номер попытки
     */
    protected void logPressedButtonName(String buttonName, String attemptNo) {
        System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие кнопки: [%s], попытка: [%s].%n", buttonName, attemptNo);
    }

    /**
     * Выводит в консоль сообщение о том, что поле с указанным именем было заполнено требуемым значением.
     *
     * @param fieldName имя поля, которое было заполнено значением
     * @param value     значение, которым было заполнено поле
     */
    protected void logFilledFieldName(String fieldName, String value) {
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля: [%s] значением: [%s].%n", fieldName, value);
    }

    /**
     * Выводит в консоль сообщение о том, что поле с указанным именем было проверено на требуемое значение.
     *
     * @param fieldName имя поля, в котором было проверено значение
     * @param value     проверяемое значение поля
     */
    protected void logFieldNameToCheck(String fieldName, String value) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем поле: [%s] на значение: [%s].%n", fieldName, value);
    }

    /**
     * Выводит в консоль сообщение о том, что в столбце с указанным именем было проверено требуемое значение.
     *
     * @param columnName имя столбца, в котором было проверено значение
     * @param value      проверяемое значение поля
     */
    protected void logColumnNameToCheck(String columnName, String value) {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяем столбец: [%s] на значение: [%s].%n", columnName, value);
    }

    // endregion
}
