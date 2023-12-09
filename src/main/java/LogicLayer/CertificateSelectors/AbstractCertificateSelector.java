package LogicLayer.CertificateSelectors;

import Helpers.ConfigContainer;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Supplier.CommonSupplierPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static ru.PageObjects.Supplier.CommonSupplierPage.CERTIFICATE_COL_HEADER_XPATH;

/**
 * Абстрактный класс для выбора сертификатов.
 * Created by Dima Makieiev on 27.01.2016.
 * Updated by Kirill G. Rydzyvylo on 16.09.2020.
 */
public abstract class AbstractCertificateSelector implements ICertificateSelector {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    protected final ConfigContainer config = ConfigContainer.getInstance(); // экземпляр ConfigContainer
    protected final long timeout = 180000;                                  // общее время ожидания (миллисекунды)
    protected final long longtime = 360000;                                 // время ожидания длительных операций (миллисекунды)
    protected final long polling = 100;                                     // интервал проверки выполнения условия (миллисекунды)
    protected final Condition clickable = and("can be clicked", visible, enabled); // по элементу можно сделать клик

    /*******************************************************************************************************************
     *               Методы класса
     ******************************************************************************************************************/
    /**
     * Производит нажатие на кнопку [Выберите сертификат].
     *
     * @param driver экземпляр WebDriver
     */
    public void openCertificateWindow(WebDriver driver) throws Exception {
        System.out.println("[ИНФОРМАЦИЯ]: нажимаем на кнопку [Выберите сертификат].");
        $(By.id(CommonSupplierPage.SELECT_CERTIFICATE_BUTTON_ID)).waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * Выбирает требуемый сертификат в окне [Выберите сертификат].
     *
     * @param driver экземпляр WebDriver
     */
    public void selectCertificate(WebDriver driver) throws Exception {
        String pref = ">>> (selectCertificate) ";

        getWebDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        int i = 1;         // Счетчик попыток
        int nTries = 100;  // Количество попыток

        String certName = config.getParameter("CertName").split(" ")[1] + " " +
                config.getParameter("CertName").split(" ")[2];

        // Формируем локаторы элементов с указанным сертификатом - элемент в списке, который еще не выбран
        String unselectedCertificateXpath = String.format("//td[@class='name' and contains(.,'%s')]", certName);
        String selectedCertificateXpath = "//*[@id='jqgh_dlgSelectCertificate_CertTable_Name']//" +
                "tr[contains(@class, 'ui-state-highlight')]/td[@class='name']";
        String certificateHashXpath = String.format("//td[@class='name' and contains(.,'%s')]/../td[1]", certName);

        // Делаем клик по сертификату и проверяем, что клик отработал, и элемент был выбран
        SelenideElement certificate = $(By.xpath(unselectedCertificateXpath));
        certificate.waitUntil(exist, timeout, polling);
        SelenideElement certificateHash = $(By.xpath(certificateHashXpath));
        certificateHash.waitUntil(exist, timeout, polling);

        while (!$(By.xpath(selectedCertificateXpath)).exists() && i <= nTries) // Цикл пока элемент не выбран ==========
        {
            System.out.println(pref + "Сертификат не отмечен, попытка: [" + i + "].");
            i++; // Шаманство ниже обусловлено тем, что если сертификат на границе видимости - выбирается соседний серт.
            // Меняем режим сортировки сертификата
            $(By.xpath(CERTIFICATE_COL_HEADER_XPATH)).waitUntil(visible, timeout, polling).click();
            TimeUnit.SECONDS.sleep(3);           // Ждем
            this.clickInElementJS(driver, certificate); // Выбираем сертификат
        } //============================================================================================================

        Assert.assertTrue(pref + "Не удалось отметить сертификат [" + certName +
                "], возможно он не существует.", $(By.xpath(selectedCertificateXpath)).exists());
        System.out.println(pref + "Сертификат с именем [" + certName + "] был успешно отмечен в списке сертификатов.");

        //==============================================================================================================
        String strCertificateHash = this.getElementTextJS(driver, certificateHash);
        config.setParameter("CertificateHash", strCertificateHash); //сохраняем hash серта
        //==============================================================================================================

        // Нажимаем кнопку [ОК] и проверяем, что окно выбора сертификата закрылось
        i = 0; // Счетчик попыток
        while ($(By.id(CommonSupplierPage.OK_BUTTON_ID)).exists() && i < nTries) // Цикл пока существует кнопка [Войти]
        {
            $(By.id(CommonSupplierPage.OK_BUTTON_ID)).
                    waitUntil(visible, timeout, polling).click();
            TimeUnit.SECONDS.sleep(5);
            if ($(By.xpath(CommonSupplierPage.LOADING_XPATH)).isDisplayed())
                $(By.xpath(CommonSupplierPage.LOADING_XPATH)).waitUntil(disappears, timeout, polling);
            i++;
            System.out.println(pref + "Подтверждаем выбор сертификата: [" + certName + "], попытка: [" + i + "].");
        } //============================================================================================================
        TimeUnit.SECONDS.sleep(5);

        getWebDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Assert.assertFalse(pref + "Окно [Выберите сертификат] для сертификата [" + certName +
                "] не было закрыто.", $(By.id(CommonSupplierPage.OK_BUTTON_ID)).is(visible));
    }

    /**
     * Выбирает требуемый сертификат по Имени и несовпадению ХЭШ в окне [Выберите сертификат].
     *
     * @param driver экземпляр WebDriver
     */
    public void selectCertificateByNameAndHash(WebDriver driver) throws Exception {
        String pref = ">>> (selectCertificate) ";

        getWebDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        int i = 1;         // Счетчик попыток
        int nTries = 100;  // Количество попыток

        String certName = config.getParameter("CertName").split(" ")[1] + " " +
                config.getParameter("CertName").split(" ")[2];
        String oldCertHash = config.getParameter("OldCertificateHash");

        // Формируем локаторы элементов с указанным сертификатом - элемент в списке, который еще не выбран
        String unselectedCertificateXpath = String.format("//tr/td[@class='name' and contains(.,'%s')]/.." +
                "/td[@class='hash' and not(contains(.,'%s'))]", certName, oldCertHash);
        String selectedCertificateXpath = "//*[@id='jqgh_dlgSelectCertificate_CertTable_Name']//" +
                "tr[contains(@class, 'ui-state-highlight')]/td[@class='name']";
        String certificateHashXpath = String.format("//tr/td[@class='name' and contains(.,'%s')]/../td[1]", certName);

        // Делаем клик по сертификату и проверяем, что клик отработал, и элемент был выбран
        SelenideElement certificate = $(By.xpath(unselectedCertificateXpath));
        certificate.waitUntil(exist, timeout, polling);
        SelenideElement certificateHash = $(By.xpath(certificateHashXpath));
        certificateHash.waitUntil(exist, timeout, polling);

        while (!$(By.xpath(selectedCertificateXpath)).exists() && i <= nTries) // Цикл пока элемент не выбран ==========
        {
            System.out.println(pref + "Сертификат не отмечен, попытка: [" + i + "].");
            i++; // Шаманство ниже обусловлено тем, что если сертификат на границе видимости - выбирается соседний серт.
            // Меняем режим сортировки сертификата
            $(By.xpath(CERTIFICATE_COL_HEADER_XPATH)).waitUntil(visible, timeout, polling).click();
            TimeUnit.SECONDS.sleep(3);           // Ждем
            this.clickInElementJS(driver, certificate); // Выбираем сертификат
        } //============================================================================================================

        Assert.assertTrue(pref + "Не удалось отметить сертификат [" + certName +
                "] у которого ХЭШ не равен " + oldCertHash + ", возможно он не существует.", $(By.xpath(selectedCertificateXpath)).exists());
        System.out.println(pref + "Сертификат с именем [" + certName + "]  у которого ХЭШ не равен " + oldCertHash + " был успешно отмечен в списке сертификатов.");

        //==============================================================================================================
        String strCertificateHash = this.getElementTextJS(driver, certificateHash);
        config.setParameter("CertificateHash", strCertificateHash); //сохраняем hash серта
        //==============================================================================================================

        // Нажимаем кнопку [ОК] и проверяем, что окно выбора сертификата закрылось
        i = 0; // Счетчик попыток
        while ($(By.id(CommonSupplierPage.OK_BUTTON_ID)).exists() && i < nTries) // Цикл пока существует кнопка [Войти]
        {
            $(By.id(CommonSupplierPage.OK_BUTTON_ID)).
                    waitUntil(visible, timeout, polling).click();
            TimeUnit.SECONDS.sleep(5);
            if ($(By.xpath(CommonSupplierPage.LOADING_XPATH)).isDisplayed())
                $(By.xpath(CommonSupplierPage.LOADING_XPATH)).waitUntil(disappears, timeout, polling);
            i++;
            System.out.println(pref + "Подтверждаем выбор сертификата: [" + certName + "], попытка: [" + i + "].");
        } //============================================================================================================
        TimeUnit.SECONDS.sleep(5);

        getWebDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Assert.assertFalse(pref + "Окно [Выберите сертификат] для сертификата [" + certName +
                "] не было закрыто.", $(By.id(CommonSupplierPage.OK_BUTTON_ID)).is(visible));
    }

    /**
     * Метод делает клик по элементу страницы используя SelenideElement с помощью JS.
     *
     * @param driver  экземпляр WebDriver
     * @param element элемент по которому следует кликнуть
     */
    protected void clickInElementJS(WebDriver driver, SelenideElement element) throws Exception {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * Метод получает текста из элемента используя SelenideElement с помощью JS.
     *
     * @param driver  экземпляр WebDriver
     * @param element элемент по которому следует кликнуть
     */
    protected String getElementTextJS(WebDriver driver, SelenideElement element) throws Exception {
        String elementText = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent", element);
        TimeUnit.SECONDS.sleep(1);
        return elementText != null ? elementText : "";
    }
}