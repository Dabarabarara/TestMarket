package ru.PageObjects.Customer.Messages;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы с диалоговым окном [Выбор адресата] в личном кабинете Заказчика.
 * Created by Vladimir V. Klochkov on 07.08.2020.
 * Updated by Vladimir V. Klochkov on 04.03.2021.
 */
public class CustomerRecipientSelectionDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CustomerRecipientSelectionDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Заголовок окна", "AddcreateMessageFilterWindow_wnd_title");
        controls.add("Переключатель Заказчики", "FilterCrMesTypeCustomer");
        controls.add("Переключатель Участники закупки", "FilterCrMesTypeOrganization");
        controls.add("Поле Наименование", "FilterCrMesName");
        controls.add("Поле ИНН", "FilterCrMesINN");
        controls.add("Поле КПП", "FilterCrMesKPP");
        controls.add("Кнопка Загрузить из Excel", "UploadFileXls");
        controls.add("Кнопка Скачать образец", "sampleDownload");
        controls.add("Кнопка Поиск", "senden2");
        controls.add("Столбец флажков в результатах поиска", "//*[@id='MesGrid']//td[@role='gridcell']/label");
        controls.add("Кнопка Выбрать", "SelectAddressee");
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
    public CustomerRecipientSelectionDialog ifDialogOpened() throws Exception {
        this.waitForPageLoad();
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [Выбор адресата] не обнаружено",
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
    public CustomerRecipientSelectionDialog setField(String fieldName, String value) throws Exception {
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
    public CustomerRecipientSelectionDialog clickOnButtonByName(String buttonName) throws Exception {
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
    public CustomerRecipientSelectionDialog checkNumberOfRowsInSearchResults(int expectedRows) {
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
    public CustomerRecipientSelectionDialog selectRecipientByRowNumber(int rowNumber) {
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
