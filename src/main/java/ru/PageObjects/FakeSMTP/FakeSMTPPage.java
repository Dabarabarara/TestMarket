package ru.PageObjects.FakeSMTP;

import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.CommonPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Класс для работы со страницей FakeSMTP - перехват e-mail сообщений
 * ( .kontur.ru/fakesmtp/Messages ).
 * Created by Alexander S. Vasyurenko on 13.04.2021.
 * Updated by Alexander S. Vasyurenko on 11.05.2021.
 */

public class FakeSMTPPage extends CommonPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка на страницу восстановление пароля
    private static final String PASSWORD_RECOVERY_LINK_XPATH =
            "//li[contains(.,'%s')]/a[contains(@href,'.kontur.ru/supplier/sso/RecoverPassword.aspx?RestoreCode=')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [To:] адресата письма
    private static final String PASSWORD_RECOVERY_EMAIL_TO_FIELD_XPATH = "//div[contains(text(),'%s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Раздел [Messages]
    private static final String MESSAGES_TAB_XPATH = "//a[contains(.,'Messages')]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [All] выбора отображения всех сообщений на странице
    private static final String SHOW_ALL_BUTTON_XPATH = "//a[contains(.,'All')]";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public FakeSMTPPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Переходит на страницу перехвата e-mail сообщений Fake SMTP и проверяет, что открыта именно эта страница.
     *
     * @param url полный адрес страницы перехвата e-mail сообщений Fake SMTP
     */
    public void gotoFakeSMTPPage(String url) throws Exception {
        System.out.println("[ИНФОРМАЦИЯ]: переходим на страницу перехвата e-mail сообщений Fake SMTP.");
        open(url);
        this.checkPageUrl("Страница перехвата e-mail сообщений Fake SMTP");
    }

    /**
     * Ожидает письма для пользователя.
     */
    private void waitForEmail() throws Exception {
        final int WAIT_TIME_MIN = 10;
        int nTries = (WAIT_TIME_MIN * 60) / 120;
        int i = 1;
        SelenideElement contactEmailLink = $(this.getBy(String.format(PASSWORD_RECOVERY_EMAIL_TO_FIELD_XPATH,
                config.getParameter("ContactEmail"))));
        SelenideElement showAllButton = $(By.xpath(SHOW_ALL_BUTTON_XPATH));

        System.out.printf("[ИНФОРМАЦИЯ]: установленное максимальное время ожидания письма %d минут.%n", WAIT_TIME_MIN);

        while (i <= nTries && !contactEmailLink.isDisplayed()) {
            System.out.printf("[ИНФОРМАЦИЯ]: [%s] проверка № %d на наличие письма на странице заглушки Fake SMTP.%n",
                    timer.getCurrentDateTime("dd.MM.yyyy HH:mm:ss"), i);
            this.refreshPage();
            this.waitForPageLoad();
            if (showAllButton.isDisplayed()) showAllButton.click();
            i++;
        }

        Assert.assertTrue(String.format("На странице заглушки Fake SMTP за период времени в %d минут не отобразилось " +
                "письмо с ссылкой для восстановления пароля", WAIT_TIME_MIN), contactEmailLink.isDisplayed());
        System.out.println("[ИНФОРМАЦИЯ]: письмо на странице заглушки Fake SMTP найдено успешно.");
    }

    /**
     * Производит нажатие на ссылку восстновления пароля в сообщении на странице Fake SMTP
     */
    public void clickPasswordRecoveryLink() throws Exception {
        // Формирует локатор ссылки по логину пользователя
        String xPathLocal =
                String.format(PASSWORD_RECOVERY_LINK_XPATH, config.getParameter("AccreditationUserLogin"));

        // Открывает вкладку [Messages]
        $(By.xpath(MESSAGES_TAB_XPATH)).waitUntil(clickable, timeout, polling).click();

        // Ждет появления e-mail сообщения
        this.waitForEmail();

        // Нажимает на ссылку в e-mail сообщении
        $(By.xpath(xPathLocal)).waitUntil(clickable, timeout, polling).click();
        System.out.println("[ИНФОРМАЦИЯ]: произведено нажатие на ссылку для восстановление пароля.");
    }
}
