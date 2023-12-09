package ru.PageObjects.Customer.Protocol;

import Helpers.Dictionary;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.Protocol.CommonCustomerProtocolPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы со страницей [Отказ от заключения договора XXXX.XXXX]
 * ( .kontur.ru/customer/lk/Contracts/RefuseView?id= )
 * ( Главная / Заказчикам / Мои договоры / Отказ от заключения договора XXXX.XXXX ).
 * Created by Vladimir V. Klochkov on 12.09.2018.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class RefuseContractProtocolPage extends CommonCustomerProtocolPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Раздел [Общие сведения о закупке]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Общие сведения о закупке]
    private static final String PURCHASE_GENERAL_INFO_HEADER_XPATH = "//h2[contains(.,'Общие сведения о закупке')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер закупки]
    private static final String PURCHASE_NUMBER_XPATH = "//td[contains(., 'Номер закупки')]/following-sibling::td/a";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование закупки]
    private static final String PURCHASE_NAME_XPATH = "//td[contains(., 'Наименование закупки')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Сведения о протоколе]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Сведения о протоколе]
    private static final String PROTOCOL_DETAILS_HEADER_XPATH = "//h2[contains(.,'Сведения о протоколе')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Статус]
    private static final String PROTOCOL_STATUS_XPATH = "//td[contains(., 'Статус')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата и время составления протокола]
    private static final String PROTOCOL_DATE_TIME_XPATH =
            "//td[contains(., 'Дата и время составления протокола')]/following-sibling::td";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Место составления (город)]
    private static final String PROTOCOL_CITY_ID = "City";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер договора]
    private static final String CONTRACT_NUMBER_XPATH = "//td[contains(., 'Номер договора')]/following-sibling::td/a";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Реквизиты документов, подтверждающие основание для отказа]
    private static final String PROTOCOL_REQUISITES_ID = "Requisites";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Основание для отказа]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Основание для отказа]
    private static final String REFUSE_REASON_HEADER_XPATH = "//h2[contains(.,'Основание для отказа')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Причина отказа - открыть список доступных значений]
    private static final String REFUSE_REASON_TYPE_OPEN_LIST_XPATH =
            "//input[@id='RejectType']/preceding-sibling::span[1]/span[@class='k-select']/span";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Причина отказа - требуемое значение в списке доступных значений]
    private static final String REFUSE_REASON_TYPE_DESIRED_VALUE_LIST_XPATH = "//*[@id='RejectType_listbox']/" +
            "li[contains(., 'В связи с признанием участника закупки уклонившимся от заключения договора')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Причина отказа - текущее выбранное значение]
    private static final String REFUSE_REASON_TYPE_SELECTED_VALUE_XPATH =
            "//input[@id='RejectType']/preceding-sibling::span[1]/span[@class='k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Пояснения]
    private static final String PROTOCOL_COMMENTS_ID = "Comment";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Документы акта об отказе]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Документы акта об отказе]
    private static final String DOCUMENTS_OF_THE_ACT_OF_REFUSAL_HEADER_XPATH =
            "//h2[contains(.,'Документы акта об отказе')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Прикрепить файл - скрытый ввод для автоимпорта файла]
    private static final String DOCUMENTS_GRID_UPLOAD_BUTTON_ID = "DocumentsGridUpload";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка на первый прикрепленный файл
    private static final String DOCUMENTS_GRID_1ST_UPLOADED_FILE_XPATH = "//*[@id='DocumentsGrid']/tbody/tr[1]/td[1]/a";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Блок кнопок управления протоколом

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Сохранить]
    private static final String SAVE_PROTOCOL_BUTTON_ID = "saveRefuse";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Опубликовать протокол]
    private static final String PUBLISH_PROTOCOL_BUTTON_ID = "SaveAndSign";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary partitionNames = new Dictionary(); // основные разделы протокола
    private final Dictionary fieldNames = new Dictionary();     // все простые текстовые поля на странице
    private final Dictionary buttons = new Dictionary();        // все кнопки на странице

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public RefuseContractProtocolPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        partitionNames.add("Общие сведения о закупке", PURCHASE_GENERAL_INFO_HEADER_XPATH);
        partitionNames.add("Сведения о протоколе", PROTOCOL_DETAILS_HEADER_XPATH);
        partitionNames.add("Основание для отказа", REFUSE_REASON_HEADER_XPATH);
        partitionNames.add("Документы акта об отказе", DOCUMENTS_OF_THE_ACT_OF_REFUSAL_HEADER_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Номер закупки", PURCHASE_NUMBER_XPATH);
        fieldNames.add("Наименование закупки", PURCHASE_NAME_XPATH);
        fieldNames.add("Статус протокола", PROTOCOL_STATUS_XPATH);
        fieldNames.add("Дата и время составления протокола", PROTOCOL_DATE_TIME_XPATH);
        fieldNames.add("Место составления (город)", PROTOCOL_CITY_ID);
        fieldNames.add("Номер договора", CONTRACT_NUMBER_XPATH);
        fieldNames.add("Реквизиты документов, подтверждающие основание для отказа", PROTOCOL_REQUISITES_ID);
        fieldNames.add("Причина отказа - текущее выбранное значение", REFUSE_REASON_TYPE_SELECTED_VALUE_XPATH);
        fieldNames.add("Пояснения", PROTOCOL_COMMENTS_ID);
        fieldNames.add("Ссылка на первый прикрепленный файл", DOCUMENTS_GRID_1ST_UPLOADED_FILE_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        buttons.add("Сохранить", SAVE_PROTOCOL_BUTTON_ID);
        buttons.add("Опубликовать протокол", PUBLISH_PROTOCOL_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Переходит к просмотру раздела протокола с указанным именем.
     *
     * @param partitionName имя раздела
     */
    public void goToPartition(String partitionName) throws Exception {
        SelenideElement partition = $(this.getBy(partitionNames.find(partitionName)));
        this.scrollToCenterAndclickInElementJS(partition);
        System.out.printf("[ИНФОРМАЦИЯ]: произведен переход к просмотру раздела: [%s].%n", partitionName);
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * Ожидает появления требуемого статуса протокола.
     *
     * @param expectedStatus ожидаемый статус протокола.
     */
    public void checkProtocolStatus(String expectedStatus) throws Exception {
        this.waitForTextInField(expectedStatus, By.xpath(PROTOCOL_STATUS_XPATH), "Статус");
    }

    /**
     * Устанавливает значение полей с предварительной их очисткой.
     *
     * @param field имя поля
     * @param value требуемое значение поля
     */
    public void setFieldValue(String field, String value) throws Exception {
        $(this.getBy(fieldNames.find(field))).waitUntil(clickable, timeout, polling).sendKeys(value);
        this.logFilledFieldName(field, value);
        TimeUnit.SECONDS.sleep(1);
        this.logFieldNameToCheck(field, value);
        $(this.getBy(fieldNames.find(field))).shouldHave(value(value));
    }

    /**
     * Устанавливает значение поля [Причина отказа].
     */
    public void selectCancelReason() throws Exception {
        SelenideElement collapsedList = $(By.xpath(REFUSE_REASON_TYPE_OPEN_LIST_XPATH));
        SelenideElement desiredValue = $(By.xpath(REFUSE_REASON_TYPE_DESIRED_VALUE_LIST_XPATH));
        this.waitForListOpens(collapsedList, desiredValue);
        String expectedText = desiredValue.getText();
        desiredValue.waitUntil(clickable, timeout, polling).click();
        SelenideElement selectedValue = $(By.xpath(REFUSE_REASON_TYPE_SELECTED_VALUE_XPATH));
        String actualText = selectedValue.getText();
        Assert.assertEquals("[ОШИБКА]: причина отказа от заключения договора была выбрана некорректно",
                expectedText, actualText);
        System.out.printf(
                "[ИНФОРМАЦИЯ]: произведен выбор причины отказа от заключения договора [%s].%n", actualText);
    }

    /**
     * Прикрепляет к протоколу документ с актом об отказе.
     */
    public void uploadAnActOfRefusalToSignTheAgreement() throws Exception {
        $(By.id(DOCUMENTS_GRID_UPLOAD_BUTTON_ID)).waitUntil(exist, timeout, polling).
                sendKeys(config.getAbsolutePathToFoundationDoc());
        TimeUnit.SECONDS.sleep(3);
        $(By.xpath(DOCUMENTS_GRID_1ST_UPLOADED_FILE_XPATH)).waitUntil(visible, timeout, polling).shouldBe(clickable);
        System.out.printf(
                "[ИНФОРМАЦИЯ]: документ с актом об отказе [%s] был успешно прикреплен к протоколу.%n",
                $(By.xpath(DOCUMENTS_GRID_1ST_UPLOADED_FILE_XPATH)).getText());
    }

    /**
     * Нажимает на указанную кнопку в протоколе отказа от заключения договора.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButtonByName(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        $(this.getBy(buttons.find(buttonName))).waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName(buttonName);
        this.waitLoadingImage();
    }
}
