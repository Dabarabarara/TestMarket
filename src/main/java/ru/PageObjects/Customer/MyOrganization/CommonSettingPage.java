package ru.PageObjects.Customer.MyOrganization;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для работы со страницей [Общие настройки] (Главная / Заказчикам / Общие настройки).
 * Created by Vladimir V. Klochkov on 14.09.2017.
 * Updated by Alexander S. Vasyurenko on 04.05.2021.
 */
public class CommonSettingPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов страницы

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CommonSettingPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Название страницы", "//h2[contains(.,'Общие настройки')]");
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Кнопка Редактировать", "//a[@href='/customer/lk/OrgInfo/Edit' and contains(.,'Редактировать')]");
        controls.add("Кнопка История", "HistoryButton");
        controls.add("Кнопка Сохранить", "SaveButton");
        controls.add("Кнопка Отмена", "CancelButton");
        //--------------------------------------------------------------------------------------------------------------
        String partition = "//*[@id='panelbar']/li/span[@class='k-link k-header' and contains(.,'%s')]/span";
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Раздел Настройки процедур", String.format(partition, "Настройки процедур"));
        //-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
        controls.add("Флаг Дублировать уведомления контактным персонам по закупке просмотр",
                "propDuplicateNoticeToContactPersons");
        controls.add(
                "Флаг Уведомлять ответственного по закупке об окончании сроков исполнения действий по закупке просмотр",
                "propNotifyResponderAboutDeadLines");
        controls.add("Флаг Согласование протоколов просмотр", "propApprovalProtocol");
        controls.add("Флаг Возможно проведение переторжки с одним участником просмотр",
                "propRetradingSingleParticipantEnabled");
        controls.add("Флаг Согласование совместных закупок просмотр", "propApproveTradeWithCustomers");
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Раздел Настройки договоров и доп. соглашений",
                String.format(partition, "Настройки договоров и доп. соглашений"));
        //-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
        controls.add("Назначать ответственного для подписания договора просмотр",
                "propAssignResponderToDealSigning");
        controls.add("Сделать необязательным указание страны происхождения товара в договоре просмотр",
                "propMakeCountryOptionalInDeals");
        controls.add("Использовать дополнительные поля заказчика при создании извещения (только для закупок по 223-ФЗ) просмотр",
                "propIsOrganizerUseCustomerExtendedFields");
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Раздел Настройки для Специализированных организаций (Уполномоченных представителей)",
                String.format(partition, "Настройки для Специализированных организаций (Уполномоченных представителей)"));
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Раздел Настройки для работы по 615 ПП РФ",
                String.format(partition, "Настройки для работы по 615 ПП РФ"));
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Раздел Настройки протоколов", String.format(partition, "Настройки протоколов"));
        //-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
        controls.add("Флаг Отображать ценовые предложения в протоколе открытия доступа просмотр",
                "propShowApplicationQuotationInOpeningProtocol");
        controls.add("В любом протоколе при отправке в ЕИС указывать информацию обо всех отклоненных участниках " +
                        "лота (кроме МСП закупок, аукционов и редукционов в одной и двух частях) просмотр",
                "propShowInfoOfDeclinedParticipants");
        controls.add("Автоматическая публикация протокола проведения аукциона, редукциона, " +
                        "аукциона в двух частях и редукциона в двух частях просмотр",
                "propPublishAuctionTradingProtocolAutomatically");
        controls.add("Публиковать протокол открытия доступа для аукциона/редукциона просмотр",
                "propPublishOpeningProtocolForAuctionReduction");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет, что страница [Общие настройки] открыта.
     *
     * @return страница [Общие настройки]
     */
    public CommonSettingPage ifPageOpened() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        this.waitLoadingImage();
        Assert.assertTrue("[ОШИБКА]: страница [Общие настройки] не открыта",
                $(this.getBy(controls.find("Название страницы"))).waitUntil(exist, timeout, polling).isDisplayed());
        return this;
    }

    /**
     * Открывает раздел настроек с указанным именем.
     *
     * @param partitionName имя раздела настроек
     * @return страница [Общие настройки]
     */
    public CommonSettingPage openPartition(String partitionName) {
        $(this.getBy(controls.find(partitionName))).waitUntil(exist, timeout, polling).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведено открытие раздела настроек: [%s].%n", partitionName);
        return this;
    }

    /**
     * Возвращает значение флажка с указанным именем.
     *
     * @param checkBoxName имя флажка
     * @return значение флажка
     */
    private boolean getCheckBoxValue(String checkBoxName) {
        return $(this.getBy(controls.find(checkBoxName))).waitUntil(exist, timeout, polling).isSelected();
    }

    /**
     * Проверяет, что флажок с указанным именем выбран.
     *
     * @param checkBoxName имя флажка
     * @return страница [Общие настройки]
     */
    public CommonSettingPage verifyCheckBoxSelected(String checkBoxName) {
        String message = ">>> (verifyCheckBoxSelected) Флажок [%s] не установлен.";
        Assert.assertTrue(String.format(message, checkBoxName), this.getCheckBoxValue(checkBoxName));
        System.out.printf("[ИНФОРМАЦИЯ]: флажок: [%s] находится в отмеченном состоянии.%n", checkBoxName);

        return this;
    }

    /**
     * Проверяет, что флажок с указанным именем не выбран.
     *
     * @param checkBoxName имя флажка
     * @return страница [Общие настройки]
     */
    public CommonSettingPage verifyCheckBoxNotSelected(String checkBoxName) {
        String message = ">>> (verifyCheckBoxNotSelected) Флажок [%s] установлен.";
        Assert.assertFalse(String.format(message, checkBoxName), this.getCheckBoxValue(checkBoxName));
        System.out.printf("[ИНФОРМАЦИЯ]: флажок: [%s] находится в не отмеченном состоянии.%n",
                checkBoxName);
        return this;
    }
}
