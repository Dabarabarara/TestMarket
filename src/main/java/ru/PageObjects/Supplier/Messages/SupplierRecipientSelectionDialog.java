package ru.PageObjects.Supplier.Messages;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Supplier.CommonSupplierPage;

import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы с диалоговым окном [Выбор получателей] в личном кабинете Поставщика.
 * Created by Vladimir V. Klochkov on 20.08.2020.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class SupplierRecipientSelectionDialog extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public SupplierRecipientSelectionDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Заголовок окна", "//span[@class='ui-dialog-title' and text() = 'Выбрать получателей']");
        controls.add("Вкладка Оператор", "//*[@id='invationDialog']//a[contains(., 'Оператор')]");
        controls.add("Вкладка Участник закупки", "//*[@id='invationDialog']//a[contains(., 'Участник закупки')]");
        controls.add("Вкладка Заказчик", "//*[@id='invationDialog']//a[contains(., 'Заказчик')]");
        controls.add("Вкладка Пользователи своей организации",
                "//*[@id='invationDialog']//a[contains(., 'Пользователи своей организации')]");
        controls.add("Поле Наименование", "BaseMainContent_MainContent_MessagesContent_txtSearchByName");
        controls.add("Поле ИНН", "BaseMainContent_MainContent_MessagesContent_txtSearchByInn");
        controls.add("Поле КПП", "BaseMainContent_MainContent_MessagesContent_txtSearchByKpp");
        controls.add("Кнопка Найти", "BaseMainContent_MainContent_MessagesContent_lbtnSearch");
        controls.add("Кнопка Очистить", "BaseMainContent_MainContent_MessagesContent_lbtClear");
        controls.add("Столбец флажков в результатах поиска",
                "//*[@id='gview_BaseMainContent_MainContent_MessagesContent_jqgCompany']//input");
        controls.add("Кнопка Выбрать", "//button[contains(., 'Выбрать')]");
        controls.add("Кнопка Отмена", "//button[contains(., 'Отмена')]");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна.
     *
     * @return это диалоговое окно
     */
    public SupplierRecipientSelectionDialog ifDialogOpened() throws Exception {
        this.waitForPageLoad();
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Выбор получателей] не обнаружено",
                $(this.getBy(controls.find("Заголовок окна"))).waitUntil(exist, timeout, polling).isDisplayed());

        return this;
    }

    /**
     * Устанавливает значение поля с предварительной его очисткой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     * @return это диалоговое окно
     */
    public SupplierRecipientSelectionDialog setField(String fieldName, String value) throws Exception {
        this.waitClearClickAndSendKeys(this.getBy(controls.find(fieldName)), value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);

        return this;
    }

    /**
     * Нажимает на указанную кнопку в диалоговом окне.
     *
     * @param buttonName имя кнопки
     * @return это диалоговое окно
     */
    public SupplierRecipientSelectionDialog clickOnButtonByName(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        $(this.getBy(controls.find(buttonName))).waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName(buttonName);
        this.waitLoadingImage(5);

        return this;
    }

    /**
     * Проверяет количество строк в результатах поиска.
     *
     * @param expectedRows ожидаемое количество строк в результатах поиска
     * @return это диалоговое окно
     */
    public SupplierRecipientSelectionDialog checkNumberOfRowsInSearchResults(int expectedRows) {
        int actualRows = $$(this.getBy(controls.find("Столбец флажков в результатах поиска"))).size();
        System.out.printf("[ИНФОРМАЦИЯ]: ожидаемое количество строк в результатах поиска: [%d], реальное: [%d].%n",
                expectedRows, actualRows);
        Assert.assertEquals(
                "[ОШИБКА]: реальное количество строк в результатах поиска не соответствует ожидаемому",
                actualRows, expectedRows);

        return this;
    }

    /**
     * Выбирает получателя в строке с указанным порядковым номером ( сверху-вниз ).
     *
     * @param rowNumber порядковый номер строки
     * @return это диалоговое окно
     */
    public SupplierRecipientSelectionDialog selectRecipientByRowNumber(int rowNumber) {
        ElementsCollection rows = $$(this.getBy(controls.find("Столбец флажков в результатах поиска")));
        int actualRows = rows.size();
        int index = rowNumber - 1;
        System.out.printf(
                "[ИНФОРМАЦИЯ]: количество строк в результатах поиска: [%d], номер строки для выбора адресата: [%d].%n",
                actualRows, rowNumber);
        Assert.assertTrue("[ОШИБКА]: передан некорректный порядковый номер строки",
                (index >= 0) && (rowNumber <= actualRows));
        rows.get(index).waitUntil(clickable, timeout, polling).click();

        return this;
    }

    /**
     * Ожидает закрытия этого диалогового окна.
     */
    public void waitForDialogCloses() {
        $(this.getBy(controls.find("Заголовок окна"))).waitUntil(disappears, timeout, polling);
    }
}
