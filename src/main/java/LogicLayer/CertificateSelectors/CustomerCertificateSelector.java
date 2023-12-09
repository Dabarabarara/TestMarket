package LogicLayer.CertificateSelectors;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс, который осуществляет выбор сертификата для пользователя типа Заказчик на странице аккредитации.
 * Created by Dima on 22.01.2016.
 * Updated by Vladimir V. Klochkov on 03.02.2020.
 */
public class CustomerCertificateSelector extends AbstractCertificateSelector {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Аккредитация в качестве заказчика
    private final SelenideElement customerAccreditationCheckBox = $(By.id("BaseMainContent_MainContent_cbIsCustomer"));
    //------------------------------------------------------------------------------------------------------------------
    // Аккредитация в качестве поставщика
    private final SelenideElement supplierAccreditationCheckBox = $(By.id("BaseMainContent_MainContent_cbIsSupplier"));
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    @Override
    public void selectAccreditationType() throws Exception {
        TimeUnit.SECONDS.sleep(25);
        customerAccreditationCheckBox.waitUntil(clickable, longtime, polling).click();
        customerAccreditationCheckBox.shouldBe(checked);
        TimeUnit.SECONDS.sleep(25);
        supplierAccreditationCheckBox.waitUntil(clickable, longtime, polling).click();
        supplierAccreditationCheckBox.shouldBe(checked);
    }

    @Override
    public void openCertificateWindow(WebDriver driver) throws Exception {
        super.openCertificateWindow(driver);
    }

    @Override
    public void selectCertificate(WebDriver driver) throws Exception {
        super.selectCertificate(driver);
    }
}
