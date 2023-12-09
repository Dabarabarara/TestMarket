package ru.PageObjects.Supplier;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы с диалоговым окном [Выберите сертификат].
 * Created by Vladimir V. Klochkov on 29.01.2019.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class SelectCertificateDialog extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     ******************************************************************************************************************/
    public SelectCertificateDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Текст заголовка окна", "//*[@class='ui-dialog-title' and contains(., 'Выберите сертификат')]");
        controls.add("Колонка Сертификат",
                "//*[@id='jqgh_dlgSelectCertificate_CertTable_Name']/tbody/tr/td[@class='name']");
        controls.add("Выбранный сертификат", "//*[@id='jqgh_dlgSelectCertificate_CertTable_Name']//" +
                "tr[contains(@class, 'ui-state-highlight')]/td[@class='name']");
        controls.add("Кнопка ОК", "btnOK");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Выбирает требуемый сертификат в таблице сертификатов.
     */
    public SelectCertificateDialog selectCertificate() throws Exception {
        // Получаем имя сертификата для поиска
        String splitCertificateName = config.getParameter("CertificateName").split(" ")[1];
        System.out.printf("[ИНФОРМАЦИЯ]: ------- Ищем сертификат с именем: [%s].%n", splitCertificateName);

        // Ожидаем появления окна со списком сертификатов
        $(this.getBy(controls.find("Текст заголовка окна"))).waitUntil(visible, timeout, polling);

        // Получаем список доступных сертификатов из столбца [Сертификат]
        ElementsCollection listOfExistedCertificates = $$(this.getBy(controls.find("Колонка Сертификат")));

        // Проходим по списку сертификатов до сертификата с указанным именем и выбираем его
        for (SelenideElement certificate : listOfExistedCertificates) {
            System.out.printf("[ИНФОРМАЦИЯ]: проверка имени сертификата: [%s]...%n", certificate.getText());

            // Нашли требуемый сертификат, пытаемся его выбрать
            if (certificate.getText().contains(splitCertificateName)) {
                int counter = 1;
                String selectedCertName;
                // Цикл пока требуемый сертификат не станет отмеченным
                do {
                    System.out.printf("[ИНФОРМАЦИЯ]: нашли, выбираем сертификат: [%s].%n", certificate.getText());
                    this.clickInElementJS(certificate);
                    TimeUnit.SECONDS.sleep(1);
                    selectedCertName = $(this.getBy(controls.find("Выбранный сертификат"))).getText();
                    counter++;
                } while (!selectedCertName.contains(splitCertificateName) && counter <= 100);

                // Проверяем, что сертификат точно был выбран
                Assert.assertTrue("[ОШИБКА]: не удалось выбрать сертификат в таблице", counter <= 100);
                break;
            }
        }
        return this;
    }

    /**
     * Делает щелчок по кнопке [OK].
     */
    public void clickOKButton() throws Exception {
        this.logButtonNameToPress("ОК");
        $(this.getBy(controls.find("Кнопка ОК"))).waitUntil(clickable, timeout, polling).click();
        this.logPressedButtonName("ОК");
        this.waitLoadingImage();
        this.checkAdvertisingDialogWindow();
    }
}
