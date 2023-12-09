package ru.PageObjects.Admin;

import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы со страницей [Реестр заказчиков, специализированных ...]
 * (Справочники / Реестр заказчиков).
 * ( .kontur.ru/supplier/lk/Dictionaries/OrganizationList.aspx?IsCustomer=True )
 * Updated by Kirill G. Rydzyvylo on 11.08.2020.
 * Updated by Vladimir V. Klochkov on 25.02.2021.
 */
public class CustomerRegisterPage extends CommonAdminPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Раздел поиска участника:
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование организации / ФИО ИП]
    private static final String ORGANIZATION_NAME_PERSON_LASTNAME_ID = "BaseMainContent_MainContent_txtOgrName";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Поиск]
    private static final String FIND_BUTTON_ID = "BaseMainContent_MainContent_btnSearch";
    //------------------------------------------------------------------------------------------------------------------
    // Раздел результатов поиска:
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Полное наименование]
    private static final String FULL_NAME_COLUMN_XPATH = "//td[@aria-describedby='jqgOrganization_FullName']";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка с указанным текстом в столбце [Полное наименование]
    private static final String FULL_NAME_PARAMETRIZED_LINK_XPATH = "//a[contains(.,'%s')]";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     ******************************************************************************************************************/
    public CustomerRegisterPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Выполняет поиск участника закупок по полю [Наименование организации / ФИО ИП].
     *
     * @param organizationName значение поля [Наименование организации / ФИО ИП] для поиска
     */
    public void performParticipantSearchByOrganizationName(String organizationName) throws Exception {
        SelenideElement organizationNamePersonLastName = $(By.id(ORGANIZATION_NAME_PERSON_LASTNAME_ID));
        organizationNamePersonLastName.waitUntil(visible, timeout, polling).clear();
        organizationNamePersonLastName.sendKeys(organizationName);
        organizationNamePersonLastName.click();
        Assert.assertEquals("[ОШИБКА]: поле [Наименование организации / ФИО ИП] содержит неверное значение",
                organizationName, organizationNamePersonLastName.val());

        $(By.id(FIND_BUTTON_ID)).waitUntil(visible, timeout, polling).click();
        this.waitForPageLoad();

        $$(By.xpath(FULL_NAME_COLUMN_XPATH)).get(0)
                .waitUntil(visible, timeout, polling);
        int size = $$(By.xpath(FULL_NAME_COLUMN_XPATH)).size();
        Assert.assertEquals("[ОШИБКА]: в результатах поиска должен быть только один участник", 1, size);
    }

    /**
     * Делает щелчок по ссылке с указанным текстом в столбце [Полное наименование].
     *
     * @param text текст ссылки по которой следует сделать щелчок
     */
    public void clickOnParticipantFullNameLinkByText(String text) throws Exception {
        String link = String.format(FULL_NAME_PARAMETRIZED_LINK_XPATH, text);
        System.out.printf("[ИНФОРМАЦИЯ]: делаем нажатие по ссылке [%s].%n", link);
        $(By.xpath(link)).waitUntil(visible, timeout, polling).click();
        this.waitForPageLoad();
        this.checkPageUrl("Информация об организации");
    }
}