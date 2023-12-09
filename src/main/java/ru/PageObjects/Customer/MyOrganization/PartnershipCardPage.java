package ru.PageObjects.Customer.MyOrganization;

import Helpers.Dictionary;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Класс для работы со страницей [Карточка партнерской связи]
 * ( Моя организация / Полномочия партнеров в моих закупках / Ссылка на партнерскую связь в таблице партнеров )
 * ( .kontur.ru/supplier/lk/PartnerRelation/PartnerRelationCard.aspx ).
 * Created by Kirill G. Rydzyvylo on 26.11.2019.
 * Updated by Vladimir V. Klochkov on 11.03.2021.
 */
public class PartnershipCardPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary blockNames = new Dictionary();    // основные разделы страницы
    private final Dictionary fieldNames = new Dictionary();    // все поля на странице
    private final Dictionary checkBoxNames = new Dictionary(); // все флажки на странице
    private final Dictionary buttonNames = new Dictionary();   // все кнопки на странице

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public PartnershipCardPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Заголовок страницы", "//div[@class='main-header-title']");
        blockNames.add("Организация донор прав",
                "//div[@class='infoblock-title' and contains(., 'Организация донор прав')]");
        blockNames.add("Организация реципиент прав",
                "//div[@class='infoblock-title' and contains(., 'Организация реципиент прав')]");
        blockNames.add("Редактирование связи",
                "//div[@class='infoblock-title' and contains(., 'Редактирование связи')]");
        blockNames.add("Деактивация связи", "//div[@class='infoblock-title' and contains(., 'Деактивация связи')]");
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Номер запроса", "BaseMainContent_MainContent_ucPartnerRelationRequestInfo_lbRequestNumber");
        fieldNames.add("Бизнес оператор",
                "BaseMainContent_MainContent_ucPartnerRelationRequestInfo_lbBusinnesOperator");
        fieldNames.add("Статус", "BaseMainContent_MainContent_ucPartnerRelationRequestInfo_lbAccept");
        fieldNames.add("Дата установления связи",
                "BaseMainContent_MainContent_ucPartnerRelationRequestInfo_lbAcceptDate");
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Название организации донора", "BaseMainContent_MainContent_hplNameDonor");
        fieldNames.add("ИНН организации донора", "BaseMainContent_MainContent_lblInnDonor");
        fieldNames.add("КПП организации донора", "BaseMainContent_MainContent_lblKppDonor");
        fieldNames.add("ОГРН организации донора", "BaseMainContent_MainContent_lblOgrnDonor");
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Название организации реципиента", "BaseMainContent_MainContent_hplNameRec");
        fieldNames.add("ИНН организации реципиента", "BaseMainContent_MainContent_lblInnRec");
        fieldNames.add("КПП организации реципиента", "BaseMainContent_MainContent_lblKppRec");
        fieldNames.add("ОГРН организации реципиента", "BaseMainContent_MainContent_lblOgrnRec");
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Наименование связи", "BaseMainContent_MainContent_txtComments");
        //--------------------------------------------------------------------------------------------------------------
        checkBoxNames.add("Право предоставитекля на электррнной площадке",
                "BaseMainContent_MainContent_chkAllowLotCustomer");
        checkBoxNames.add("Право просмотра отчетности по закупкам", "BaseMainContent_MainContent_chkAllowStats");
        checkBoxNames.add("Право просмотра всех закупок", "BaseMainContent_MainContent_chkAllowViewTrades");
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Сохранить", "BaseMainContent_MainContent_lbSave");
        buttonNames.add("Деактивировать", "BaseMainContent_MainContent_lbDeactivate");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет значение поля.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение, которое должно содержаться в поле
     * @return страница [Карточка партнерской связи]
     */
    public PartnershipCardPage verifyField(String fieldName, String value) {
        String message = String.format("[ОШИБКА]: Значение [%s] не содержится в поле [%s].", value, fieldName);

        By field = this.getBy(fieldNames.find(fieldName));

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        Assert.assertTrue(message, this.verifyFieldContent(field, value));

        return this;
    }

    /**
     * Проверяет, что поле доступно для редактирования.
     *
     * @param fieldName имя поля
     */
    public void verifyFieldIsEditable(String fieldName) throws Exception {
        SelenideElement field = $(this.getBy(fieldNames.find(fieldName)));
        String oldValue = field.getValue();
        String newValue = "New Value " + oldValue;

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] на доступность для редактирования.%n", fieldName);
        this.waitClearClickAndSendKeys(this.getBy(fieldNames.find(fieldName)), newValue);

        Assert.assertEquals(String.format("[ОШИБКА]: не удалось установить новое значение в поле [%s]", fieldName),
                newValue, field.getValue());
        Assert.assertNotEquals("[ОШИБКА]: старое значение поля сохранилось после попытки редактирования",
                oldValue, field.getValue());

        this.waitClearClickAndSendKeys(this.getBy(fieldNames.find(fieldName)), oldValue);
        Assert.assertEquals(String.format("[ОШИБКА]: не удалось восстановить старое значение в поле [%s]", fieldName),
                oldValue, field.getValue());
        System.out.printf("[ИНФОРМАЦИЯ]: поле [%s] доступно для редактирования.%n", fieldName);
    }

    /**
     * Проверяет, что поле не доступно для редактирования.
     *
     * @param fieldName имя поля
     */
    public void verifyFieldIsNotEditable(String fieldName) {
        SelenideElement field = $(this.getBy(fieldNames.find(fieldName)));

        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] на недоступность для редактирования.%n", fieldName);
        field.waitUntil(visible, timeout, polling).shouldNotBe(enabled);
        System.out.printf("[ИНФОРМАЦИЯ]: поле [%s] недоступно для редактирования.%n", fieldName);
    }

    /**
     * Возвращает значение флажка с указанным именем.
     *
     * @param checkBoxName имя флажка
     * @return значение флажка
     */
    private boolean getCheckBoxValue(String checkBoxName) {
        $(By.id(checkBoxNames.find(checkBoxName))).waitUntil(exist, timeout, polling).shouldBe(visible);

        return $(By.id(checkBoxNames.find(checkBoxName))).isSelected();
    }

    /**
     * Проверяет, что флажок с указанным именем выбран.
     *
     * @param checkBoxName имя флажка
     * @return страница [Карточка партнерской связи]
     */
    public PartnershipCardPage verifyCheckBoxSelected(String checkBoxName) {
        String message = "[ОШИБКА]: Флажок [%s] не установлен.";
        Assert.assertTrue(String.format(message, checkBoxName), this.getCheckBoxValue(checkBoxName));
        System.out.printf("[ИНФОРМАЦИЯ]: флажок: [%s] находится в отмеченном состоянии.%n", checkBoxName);

        return this;
    }

    /**
     * Проверяет, что флажок с указанным именем не выбран.
     *
     * @param checkBoxName имя флажка
     * @return страница [Карточка партнерской связи]
     */
    public PartnershipCardPage verifyCheckBoxNotSelected(String checkBoxName) {
        Assert.assertFalse(
                String.format("[ОШИБКА]: Флажок [%s] установлен.", checkBoxName), this.getCheckBoxValue(checkBoxName));
        System.out.printf("[ИНФОРМАЦИЯ]: флажок: [%s] находится в не отмеченном состоянии.%n", checkBoxName);

        return this;
    }

    /**
     * Производит нажатие на кнопку.
     *
     * @param buttonName имя кнопки
     * @return страница [Карточка партнерской связи]
     */
    public PartnershipCardPage clickOnButton(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.scrollToCenterAndclickInElementJS(this.getBy(buttonNames.find(buttonName)));
        this.logPressedButtonName(buttonName);
        this.waitForPageLoad(3);

        return this;
    }
}
