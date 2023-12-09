package LogicLayer.CertificateSelectors;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс, который осуществляет выбор сертификата для пользователя типа Поставщик на странице аккредитации.
 * Created by Dima Makieiev on 22.01.2016.
 * Updated by Vladimir V. Klochkov on 05.02.2021.
 */
public class SupplierCertificateSelector extends AbstractCertificateSelector {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
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