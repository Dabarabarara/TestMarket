package ru.PageObjects.Customer.MyOrganization;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы со страницей [Журнал взаимодействия с ЕИС] (Главная / Заказчикам / Журнал взаимодействия с ЕИС).
 * Created by Vladimir V. Klochkov on 05.04.2018.
 * Updated by Alexander S. Vasyurenko on 25.06.2021.
 */
public class OosMessageQueuePage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип значения] (список возможных значений поля свёрнут)
    private static final String MESSAGE_TYPE_LIST_CLOSED_XPATH =
            "//td[contains(.,'Тип сведений:')]//span[@role='listbox']//span[@class='k-select']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип значения] (список возможных значений поля открыт) -> требуемое значение в списке
    private static final String MESSAGE_TYPE_IN_OPENED_LIST_BY_NAME_XPATH =
            "//div[@id='MessageType-list']//ul/li[contains(., '%s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Тип значения] (текущее значение)
    private static final String MESSAGE_VALUE_TYPE_XPATH =
            "//td[contains(.,'Тип сведений:')]//span[@role='listbox']//span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов страницы

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public OosMessageQueuePage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Поле Номер на ЭП", "Number");
        controls.add("Список Тип сведений - открыть список",
                "//input[@id='MessageType']/preceding-sibling::span[1]//span[@class='k-icon k-i-arrow-s']");
        controls.add("Список Тип сведений - требуемое значение в списке",
                "//*[@id='MessageType_listbox']/li[contains(., '%s')]");
        controls.add("Список Тип сведений - текущее выбранное значение",
                "//input[@id='MessageType']/preceding-sibling::span[1]//span[@class='k-input']");
        controls.add("Кнопка Найти", "//*[@id='FilterBox']/input[contains(@class, 'searchButton')]");
        controls.add("Столбец Дата и время создания", "//*[@id='OosMessageQueueGrid']//tbody/tr/td[1]");
        controls.add("Столбец Статус сведений", "//*[@id='OosMessageQueueGrid']//tbody/tr/td[4]");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Устанавливает значение поля [Номер на ЭП].
     *
     * @param number требуемое значение поля
     * @return страница [Журнал взаимодействия с ЕИС]
     */
    public OosMessageQueuePage setPurchaseNumber(String number) throws Exception {
        SelenideElement field = $(this.getBy(controls.find("Поле Номер на ЭП")));
        field.waitUntil(visible, timeout, polling).sendKeys(number);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля: [Номер на ЭП] значением: [%s].%n", number);
        TimeUnit.SECONDS.sleep(3);
        Assert.assertEquals("[ОШИБКА]: установка значения в поле: [Номер на ЭП]", number, field.getValue());
        return this;
    }

    /**
     * Устанавливает значение поля со списком [Тип сведений].
     *
     * @param desiredValue требуемое значение поля со списком
     * @return страница [Журнал взаимодействия с ЕИС]
     */
    public OosMessageQueuePage setInformationType(String desiredValue) throws Exception {
        String desiredValueLocator =
                String.format(controls.find("Список Тип сведений - требуемое значение в списке"), desiredValue);
        SelenideElement openListArrow = $(this.getBy(controls.find("Список Тип сведений - открыть список")));
        SelenideElement newvalue = $(this.getBy(controls.find("Список Тип сведений - текущее выбранное значение")));
        String oldValue = newvalue.waitUntil(exist, timeout, polling).getText();
        String currentValue = oldValue;
        System.out.printf("[ИНФОРМАЦИЯ]: передано значение для выбора в списке [Тип сведений]: [%s].%n",
                desiredValue);
        System.out.printf(
                "[ИНФОРМАЦИЯ]: сформирован локатор для поля со списком [Тип сведений]: [%s].%n", desiredValueLocator);
        this.scrollToCenter(openListArrow);

        for (int i = 0; i < 50; i++) {
            System.out.printf("[ИНФОРМАЦИЯ]: попытка № [%d] открыть список [Тип сведений].%n", i + 1);
            openListArrow.waitUntil(clickable, timeout, polling).click();
            TimeUnit.SECONDS.sleep(3);
            SelenideElement valueInList = $(this.getBy(desiredValueLocator));
            Assert.assertTrue("[ОШИБКА]: элемент списка с таким значением отсутствует", valueInList.exists());
            if (valueInList.isDisplayed()) {
                valueInList.waitUntil(clickable, timeout, polling).click();
                TimeUnit.SECONDS.sleep(3);
                currentValue = newvalue.waitUntil(exist, timeout, polling).getText();
                if (!currentValue.equals(oldValue)) break;
            }
        }

        Assert.assertNotEquals("Поле со списком [Тип сведений] не заполнено", oldValue, currentValue);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля со списком [Тип сведений] значением [%s].%n",
                currentValue);
        return this;
    }

    /**
     * Производит нажатие на кнопку [Найти].
     *
     * @return страница [Журнал взаимодействия с ЕИС]
     */
    public OosMessageQueuePage clickOnSearchButton() throws Exception {
        $(this.getBy(controls.find("Кнопка Найти"))).waitUntil(visible, timeout, polling).click();
        System.out.println("[ИНФОРМАЦИЯ]: произведено нажатие на кнопку: [Найти].");
        this.waitLoadingImage();
        return this;
    }

    /**
     * Сохраняет дату и время создания извещения о закупке (столбец [Дата и время сздания]) в параметре теста.
     *
     * @return страница [Журнал взаимодействия с ЕИС]
     */
    public OosMessageQueuePage storeCreationDateTimeIntoTestParameter() {
        ElementsCollection dateTimeColumn = $$(this.getBy(controls.find("Столбец Дата и время создания")));
        Assert.assertTrue("[ОШИБКА]: результаты поиска содержат неверное число записей",
                dateTimeColumn.size() > 0 && dateTimeColumn.size() < 3);
        String text = dateTimeColumn.get(0).waitUntil(visible, timeout, polling).getText();
        Assert.assertFalse("[ОШИБКА]: дата и время создания извещения отсутствуют или неверно записан локатор",
                this.isNullOrEmpty(text));
        System.out.printf("[ИНФОРМАЦИЯ]: производим сохранение даты и времени создания закупки: [%s]" +
                " в параметре теста: [NoticeCreationDateTimeFromOosMessageQueue] из результатов поиска, " +
                "найдено записей: [%d].%n", text, dateTimeColumn.size());
        config.setParameter("NoticeCreationDateTimeFromOosMessageQueue", text);
        return this;
    }

    /**
     * Проверяет статус закупки (столбец [Статус сведений]) в результатах поиска.
     *
     * @param status ожидаемый статус закупки
     */
    public void checkStatusInSearchResults(String status) {
        ElementsCollection statusColumn = $$(this.getBy(controls.find("Столбец Статус сведений")));
        Assert.assertTrue("[ОШИБКА]: результаты поиска содержат неверное число записей",
                statusColumn.size() > 0 && statusColumn.size() < 3);
        System.out.printf("[ИНФОРМАЦИЯ]: производим проверку статуса закупки: [%s] " +
                "в результатах поиска, найдено записей: [%d].%n", status, statusColumn.size());
        String text = statusColumn.get(0).waitUntil(visible, timeout, polling).getText();
        Assert.assertTrue(String.format("[ОШИБКА]: статус [%s] закупки не соответствует ожидаемому [%s]", text, status),
                text.contains(status));
    }

    /**
     * Устанавливает значение поля [Тип сведений].
     *
     * @param messageType значение поля
     */
    public OosMessageQueuePage setMessageType(String messageType) throws Exception {
        SelenideElement collapsedList = $(this.getBy(MESSAGE_TYPE_LIST_CLOSED_XPATH));
        SelenideElement desiredValue = $(this.getBy(String.format(MESSAGE_TYPE_IN_OPENED_LIST_BY_NAME_XPATH, messageType)));
        SelenideElement selectedValue = $(this.getBy(MESSAGE_VALUE_TYPE_XPATH));
        this.waitForListOpensAndSelectDesiredValue("Тип сведений", collapsedList, desiredValue, selectedValue);
        return this;
    }
}
