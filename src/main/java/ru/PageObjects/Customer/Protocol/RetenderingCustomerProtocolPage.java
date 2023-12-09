package ru.PageObjects.Customer.Protocol;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.PrintFormsPreview.ApplicationViewPage;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Класс описывающий страницу [Протокол проведения переторжки ...]
 * (.kontur.ru/customer/lk/Protocols/ProtocolForm?tradeId=).
 * Created by Vladimir V. Klochkov on 30.08.2018.
 * Updated by Alexander S. Vasyurenko on 18.06.2021.
 */
public class RetenderingCustomerProtocolPage extends CommonCustomerProtocolPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола проведения переторжки], заголовок столбца [Ценовое предложение]
    private static final String TABLE_HEADER_PRICE_OFFER_COLUMN_HEADER_XPATH =
            "//h2[contains(., 'Сведения протокола проведения переторжки')]/following-sibling::div[1]/table/tbody/tr" +
                    "/th[contains(., 'ценовое')]";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица раздела [Сведения протокола проведения переторжки], ссылки в столбце [Заявка]
    private static final String TABLE_APPLICATION_LINKS_XPATH =
            "//table//td/a[@class='DownloadApplicationLink' and contains(@href, 'PrintFormContentForProtocol')]";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final ApplicationViewPage applicationViewPage = new ApplicationViewPage(driver);

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public RetenderingCustomerProtocolPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет отсутствие столбца [Ценовое предложение] в разделе [Сведения протокола проведения переторжки].
     */
    public void checkThatThePriceOfferColumnHiddenInTableWhenProtocolHasFormingState() {
        SelenideElement columnHeader = $(By.xpath(TABLE_HEADER_PRICE_OFFER_COLUMN_HEADER_XPATH));
        System.out.println("[ИНФОРМАЦИЯ]: проверяем отсутствие столбца [Ценовое предложение].");
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        columnHeader.shouldBe(hidden);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        System.out.println("[ИНФОРМАЦИЯ]: столбец [Ценовое предложение] скрыт - проверка прошла успешно.");
    }

    /**
     * Проверяет наличие столбца [Ценовое предложение] в разделе [Сведения протокола проведения переторжки].
     */
    public void checkThatThePriceOfferColumnVisibleInTableWhenProtocolHasFormingState() {
        SelenideElement columnHeader = $(By.xpath(TABLE_HEADER_PRICE_OFFER_COLUMN_HEADER_XPATH));
        System.out.println("[ИНФОРМАЦИЯ]: проверяем наличие столбца [Ценовое предложение].");
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        columnHeader.shouldBe(visible);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        System.out.println("[ИНФОРМАЦИЯ]: столбец [Ценовое предложение] не скрыт - проверка прошла успешно.");
    }

    /**
     * Проверяет отсутствие таблицы [Ценовое предложение] в печатных формах заявок на участие от Поставщиков.
     */
    public void checkThatThePriceOfferTableHiddenInApplicationPrintForms() throws Exception {
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
            applicationViewPage.checkThatThePriceOfferTableHiddenInApplicationPrintForm();
            getWebDriver().close();
            //==========================================================================================================

            // Возвращаемся в окно [Протокол открытия доступа ...] (основное окно приложения)
            getWebDriver().switchTo().window(mainWindow);
            System.out.println("[ИНФОРМАЦИЯ]: произошло переключение на основное окно приложения.");
            this.checkPageUrl("Один из протоколов");
        }
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
}
