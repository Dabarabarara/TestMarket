package ru.PageObjects.Admin;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы со страницей [Список заявок]
 * ( .kontur.ru/supplier/lk/Accreditation/RequestList.aspx )
 * ( АДМИНИСТРИРОВАНИЕ / Заявки на аккредитацию / Список заявок ).
 * Created by Evgeniy Glushko on 18.04.2016.
 * Updated by Vladimir V. Klochkov on 05.02.2021.
 */
public class AccreditationListRequestsPage extends CommonAdminPage {
    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public AccreditationListRequestsPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //----------------------------------------------------------------------------------------------------------------
    // Поле [Наименование организации/ФИО]
    private final SelenideElement organizationNameOrFIO = $(By.id("BaseMainContent_MainContent_txtName"));
    //----------------------------------------------------------------------------------------------------------------
    // Поле [ИНН]
    private final SelenideElement inn = $(By.id("BaseMainContent_MainContent_txtInn"));
    //----------------------------------------------------------------------------------------------------------------
    // Кнопка [Поиск]
    private final SelenideElement searchButton = $(By.id("BaseMainContent_MainContent_btnSearch"));
    //----------------------------------------------------------------------------------------------------------------
    // Столбец [Номер] в результатах поиска ( все значения )
    private final ElementsCollection numberColumn =
            $$(By.xpath("//td[@aria-describedby='BaseMainContent_MainContent_jqRequestList_Id']/a"));
    //----------------------------------------------------------------------------------------------------------------
    // Локатор для получения значения из столбца [Номер] в результатах поиска
    private final String NUMBER_COLUMN_XPATH = "//a[contains(text(),'%s')]";
    //----------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Методы страницы
     ******************************************************************************************************************/
    /**
     * Заполняет поле [ИНН].
     */
    public void setINNField() throws Exception {
        inn.waitUntil(clickable, timeout, polling).clear();
        inn.click();
        inn.sendKeys(config.getParameter("AccreditationInnValue"));
        TimeUnit.SECONDS.sleep(1);
        inn.sendKeys(Keys.ENTER);
        TimeUnit.SECONDS.sleep(1);
        organizationNameOrFIO.waitUntil(clickable, timeout, polling).click();

        Assert.assertEquals("Неверное значение в поле [ИНН]",
                config.getParameter("AccreditationInnValue"), inn.getAttribute("value"));
    }

    /**
     * Нажимает на кнопку [Поиск].
     */
    public void clickSearchButton() throws Exception {
        searchButton.waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
    }

    /**
     * Переходит по ссылке в колонке [Номер].
     */
    public void clickOnLinkInNumberColumn() throws Exception {
        numberColumn.get(0).waitUntil(visible, timeout, polling);
        numberColumn.shouldHave(size(1));

        String xpath = String.format(NUMBER_COLUMN_XPATH, this.getLinkFromNumberColumn());
        $(By.xpath(xpath)).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
    }

    /**
     * Получает значение из колонки [Номер].
     */
    private String getLinkFromNumberColumn() throws Exception {
        SelenideElement link = numberColumn.get(0).waitUntil(visible, timeout, polling);
        link.scrollTo();
        TimeUnit.SECONDS.sleep(1);
        String number = link.getText();
        System.out.printf("[ИНФОРМАЦИЯ]: номер заявки на аккредитацию [%s].%n", number);

        return number;
    }
}
