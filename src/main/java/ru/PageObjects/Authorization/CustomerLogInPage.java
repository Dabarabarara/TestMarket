package ru.PageObjects.Authorization;

import Helpers.ConfigContainer;
import Helpers.TestSets;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Класс, реализующий функционал входа в личный кабинет Заказчика с помощью сертификата.
 * Created by Vladimir V. Klochkov on 22.05.2020.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class CustomerLogInPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Имя пользователя (логин)]
    private static final String LOGIN_ID = "auth-log";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Пароль]
    private static final String PASSWORD_ID = "auth-pass";
    //------------------------------------------------------------------------------------------------------------------
    // Таблица сертификатов
    private static final String CERTIFICATE_TABLE_ID = "certlist";
    //------------------------------------------------------------------------------------------------------------------
    // Список сертификатов в таблице
    private static final String CERTIFICATE_LIST_XPATH = "//table[@id='certlist']/tbody/tr[@data-isvalid='true']";
    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Сертификат]
    private static final String CERTIFICATE_COLUMN_XPATH = "//*[@id='certlist']//tr/td[contains(text(),'%s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Элемент в списке сертификатов, который еще не выбран
    private static final String UNSELECTED_CUSTOMER_CERTIFICATE_XPATH = "//td[1][contains(.,'%s')]";
    //------------------------------------------------------------------------------------------------------------------
    // Элемент в списке сертификатов, который выбран (по нему был произведен щелчек мышью)
    private static final String SELECTED_CUSTOMER_CERTIFICATE_XPATH =
            "//*[@id='certlist']/tbody/tr[@class='selected']/td[contains(.,'%s')][1]";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Войти]
    private static final String LOGIN_BUTTON_ID = "auth-login";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка [Добавить пользователя]
    private static final String ADD_USER_XPATH = "//div/a[contains(@href,'supplier/lk/Accreditation/EmployeeRequest')]";
    //------------------------------------------------------------------------------------------------------------------
    // Личный кабинет Пользователя, ссылка [Выход]
    private static final String EXIT_LINK_ID = "header__authorization_loguot";
    //------------------------------------------------------------------------------------------------------------------
    // Личный кабинет, режим отображения закупок в результатах поиска ( блок [Отобразить:] )
    private static final String SWITCH_TO_CARD_VIEW_XPATH = "//a[@href='/customer/lk/Auctions/Cards']/div";
    //------------------------------------------------------------------------------------------------------------------
    // Личный кабинет, фильтр [Сортировка], кнопка [V]
    private static final String OPEN_SORTING_LIST_XPATH =
            "//div[@class='filter' and contains(., 'Сортировка')]//span[@class='k-select']";
    //------------------------------------------------------------------------------------------------------------------
    // Личный кабинет, фильтр [Сортировка], список режимов сортировки, требуемое значение
    private static final String OPTION_TO_SELECT_XPATH = "//li[contains(., 'по номеру извещения')]";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final long maxtime = 900000; // время ожидания длительных операций (миллисекунды)

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public CustomerLogInPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Осуществляет переход на страницу входа в систему.
     *
     * @return ссылка на экземпляр этого класса
     */
    public CustomerLogInPage goToUserLoginPage() {
        String url = TestSets.getInstance().getCustomersLoginPageUrl(config.getParameter("TestType"));

        System.out.printf(">>> (goToUserLoginPage) Переходим на страницу входа с адресом: [%s].%n", url);
        open(url);
        $(By.id(LOGIN_ID)).waitUntil(clickable, 300000, 1000);

        // Проверка открытия нужной страницы
        Assert.assertTrue(
                ">>> (goToUserLoginPage) Страница входа на площадку проекта 223 Заказчиком не открыта !",
                url().contains(".kontur.ru/customer/lk/"));

        return this;
    }

    /**
     * Заполняет поле [Логин] требуемым значением с помощью JS.
     *
     * @param login значение поля
     * @return ссылка на экземпляр этого класса
     */
    public CustomerLogInPage setLogin(String login) throws Exception {
        SelenideElement loginField = $(By.id(LOGIN_ID));
        loginField.waitUntil(visible, timeout, polling);
        this.setValueInElementJS(LOGIN_ID, login);
        loginField.shouldHave(value(login));

        return this;
    }

    /**
     * Заполняет поле [Пароль] требуемым значением с помощью JS.
     *
     * @param password значение поля
     * @return ссылка на экземпляр этого класса
     */
    public CustomerLogInPage setPassword(String password) throws Exception {
        SelenideElement passwordField = $(By.id(PASSWORD_ID));
        passwordField.waitUntil(visible, timeout, polling);
        this.setValueInElementJS(PASSWORD_ID, password);
        passwordField.shouldHave(value(password));

        return this;
    }

    /**
     * Делает клик по требуемому сертификату.
     *
     * @param certificateName имя требуемого сертификата
     * @return ссылка на экземпляр этого класса
     */
    public CustomerLogInPage clickOnCertificateName(String certificateName) {
        String certificateNameLocator = String.format(CERTIFICATE_COLUMN_XPATH, certificateName);
        this.scrollToCenter(this.getBy(certificateNameLocator));
        $(this.getBy(certificateNameLocator)).waitUntil(visible, timeout, polling).click();

        return this;
    }

    /**
     * Делает клик по кнопке [Войти] с помощью JS.
     */
    public void clickOnLoginButton() throws Exception {
        $(By.id(LOGIN_BUTTON_ID)).waitUntil(visible, timeout, polling).scrollTo();
        this.logButtonNameToPress("Войти");
        this.clickInElementJS(By.id(LOGIN_BUTTON_ID));
        this.logPressedButtonName("Войти");
    }

    /**
     * Осуществляет вход в личный кабинет Заказчика с помощью сертификата.
     *
     * @param certName имя сертификата
     */
    public void loginAsCustomer(String certName) throws Exception {

        // Устанавливаем короткое время ожидания для WebDriver
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        // Ожидаем появления таблицы сертификатов на странице
        $(By.id(CERTIFICATE_TABLE_ID)).waitUntil(visible, maxtime, polling);

        // Ожидаем заполнения таблицы сертификатов на странице ( цикл пока список сертификатов не загрузится )
        for (int i = 1; i <= 100; i++) {
            ElementsCollection certList = $$(By.xpath(CERTIFICATE_LIST_XPATH));
            if (certList.size() == 0) {
                refresh();
                TimeUnit.SECONDS.sleep(5);
                $(By.id(CERTIFICATE_TABLE_ID)).waitUntil(visible, maxtime, polling);
            } else break;
            System.out.printf("[ИНФОРМАЦИЯ]: таблица сертификатов не заполнилась, попытка: [%d].%n", i);
        }

        //--------------------------------------------------------------------------------------------------------------
        // Формируем локаторы элементов с указанным сертификатом:
        //--------------------------------------------------------------------------------------------------------------
        // Элемент в списке, который еще не выбран
        String unselectedSertificateXpath = String.format(UNSELECTED_CUSTOMER_CERTIFICATE_XPATH, certName);
        //--------------------------------------------------------------------------------------------------------------
        // Элемент в списке, который выбран (по нему был произведен щелчок мышью)
        String selectedCertificateXpath = String.format(SELECTED_CUSTOMER_CERTIFICATE_XPATH, certName);
        //--------------------------------------------------------------------------------------------------------------

        // Делаем щелчок мышью по сертификату и проверяем, что элемент выбран ( цикл пока элемент не выбран )
        for (int i = 1; i <= 100; i++) {
            SelenideElement unselectedSertificate = $(By.xpath(unselectedSertificateXpath));
            unselectedSertificate.waitUntil(clickable, maxtime, polling);
            this.clickInElementJS(unselectedSertificate);
            TimeUnit.SECONDS.sleep(1);
            if ($(By.xpath(selectedCertificateXpath)).exists()) break;
            System.out.printf("[ОШИБКА]: сертификат [%s] не выбран, попытка: [%d].%n", unselectedSertificate.getText(), i);
        }

        // Нажимаем кнопку [Войти] и проверяем, что мы успешно вошли в систему ( цикл пока существует кнопка [Войти] )
        for (int i = 1; i <= 100; i++) {
            SelenideElement loginButton = $(By.id(LOGIN_BUTTON_ID));
            loginButton.waitUntil(clickable, maxtime, polling);
            this.clickInElementJS(loginButton);
            this.waitLoadingImage();
            System.out.printf("[ИНФОРМАЦИЯ]: вход в систему как: [%s], попытка: [%d].%n", certName, i);
            if (!$(By.id(LOGIN_BUTTON_ID)).exists()) break;
        }

        // Проверяем, что мы успешно вошли в систему
        $(By.id(EXIT_LINK_ID)).shouldBe(visible);
        this.ifPurchasesPageIsOpenedThanSetCorrectViewMode();

        // Устанавливаем время ожидания по умолчанию для WebDriver
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }

    /**
     * Делает клик по ссылке [Добавить пользователя] с помощью JS.
     */
    public void clickOnAddUserLink() throws Exception {
        SelenideElement link = $(By.xpath(ADD_USER_XPATH));
        link.waitUntil(visible, timeout, polling).scrollTo();
        this.logButtonNameToPress("Ссылка Добавить пользователя");
        this.clickInElementJS(link);
        this.logPressedButtonName("Ссылка Добавить пользователя");
    }

    /**
     * Устанавливает требуемый режим отображения и сортировки закупок для страницы [ЗАКУПКИ].
     */
    private void ifPurchasesPageIsOpenedThanSetCorrectViewMode() throws Exception {

        // Проверяем, что мы находимся на странице [ЗАКУПКИ] ( только тогда эти действия имеют смысл )
        if (url().contains("customer/lk/Auctions/")) {

            // Проверяем режим отображения закупок ( нам нужен в виде карточек по умолчанию для автотестов )
            if (url().contains("customer/lk/Auctions/Table")) {
                System.out.println("[ИНФОРМАЦИЯ]: обнаружен режим отображения закупок в виде таблицы.");
                $(By.xpath(SWITCH_TO_CARD_VIEW_XPATH)).waitUntil(clickable, timeout, polling).click();
                this.waitLoadingImage();
                System.out.println("[ИНФОРМАЦИЯ]: установлен режим отображения закупок в виде карточек.");
            } else {
                System.out.println("[ИНФОРМАЦИЯ]: обнаружен режим отображения закупок в виде карточек.");
            }

            // Устанавливаем режим сортировки по номеру извещения
            System.out.println("[ИНФОРМАЦИЯ]: устанавливаем режим сортировки по номеру извещения.");
            $$(By.xpath(OPEN_SORTING_LIST_XPATH)).get(0).waitUntil(clickable, timeout, polling).click();
            $$(By.xpath(OPTION_TO_SELECT_XPATH)).get(0).waitUntil(clickable, timeout, polling).click();
            $(By.xpath(OPEN_SORTING_LIST_XPATH)).waitUntil(clickable, maxtime, polling);

        }
    }
}
