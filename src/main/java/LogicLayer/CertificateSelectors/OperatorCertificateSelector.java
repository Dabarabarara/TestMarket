package LogicLayer.CertificateSelectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Supplier.CommonSupplierPage;

import java.util.Iterator;
import java.util.List;

/**
 * Класс, который осуществляет выбор сертификата для пользователя типа Оператор ( Admin ) на странице аккредитации.
 * Created by Dima Makieiev on 22.01.2016.
 * Updated by Vladimir V. Klochkov on 05.02.2021.
 */
public class OperatorCertificateSelector extends AbstractCertificateSelector {
    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    @Override
    public void selectAccreditationType() {
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
