package LogicLayer.CertificateSelectors;

import org.openqa.selenium.WebDriver;

/**
 * Created by Dima Makieiev on 08.12.2015.
 * Updated by Vladimir V. Klochkov on 11.07.2019.
 */
public interface ICertificateSelector {
    void selectAccreditationType() throws Exception;

    void openCertificateWindow(WebDriver driver) throws Exception;

    void selectCertificate(WebDriver driver) throws Exception;
}
