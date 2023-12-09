package ru.StepDefinitions;

import Helpers.CertificateGenerator;
import Helpers.Dictionary;
import Helpers.TestSets;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import ru.PageObjects.Authorization.CustomerLogInPage;
import ru.PageObjects.Authorization.SupplierOrOperatorrLogInPage;
import ru.PageObjects.Authorization.UserLogOffPage;
import ru.PageObjects.Customer.CommonCustomerPage;
import ru.PageObjects.Customer.MyOrganization.SelectOrganizationForAddingEmployeeDialog;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Общие шаги для всех тестов.
 * Created by Dima Makieiev on 17.02.2016.
 * Updated by Vladimir V. Klochkov on 06.05.2021.
 */
public class CommonStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final UserLogOffPage userLogOffPage = new UserLogOffPage(driver);
    private final CustomerLogInPage customerLogInPage = new CustomerLogInPage(driver);
    private final CommonCustomerPage commonCustomerPage = new CommonCustomerPage(driver);
    private final SupplierOrOperatorrLogInPage supplierOrOperatorrLogInPage = new SupplierOrOperatorrLogInPage(driver);

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    /**
     * Устанавливает глобальный обязательный параметр [Тип теста], инициализирует журнал длительности выполнения.
     *
     * @param testType требуемое значение параметра
     */
    @Given("^Параметру 'Тип теста' установлено значение \"([^\"]*)\"$")
    public void userSetsTestType(String testType) {
        String stepName = String.format("Параметру 'Тип теста' установлено значение '%s'", testType);
        this.logStepName(stepName);

        timer.start();

        config.setParameter("TestType", testType);
        //--------------------------------------------------------------------------------------------------------------
        Assert.assertTrue("[ОШИБКА]: не удалось создать журнал времени выполнения !",
                timer.initTimeProfileLog());
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);
    }

    /**
     * Осуществляет проверку необходимых для конкретного теста сертификатов на странице входа в ЛК Заказчика.
     */
    @And("^Все требуемые для теста сертификаты присутствуют на компьютере$")
    public void userChecksCertificates() throws Throwable {
        String stepName = "Все требуемые для теста сертификаты присутствуют на компьютере";
        this.logStepName(stepName);

        timer.start();

        String testType = config.getParameter("TestType");
        System.out.printf("[ИНФОРМАЦИЯ]: тип теста: [%s].%n", testType);

        TestSets testSets = TestSets.getInstance();
        List<String> certificates = testSets.getCertificateSetForThisTestType(testType);
        if (certificates.size() > 0) {                    // Если есть что проверять ( для теста требуются сертификаты )
            // Проверяем список по странице Заказчика - там он уже открыт
            open(testSets.getCertificatesCheckPageUrl(testType));
            commonCustomerPage.checkPageUrl("Вход в личный кабинет (Заказчик)");
            commonCustomerPage.checkThatCryptoProPluginIsEnabled();
            commonCustomerPage.checkThatCertificatesListIsNotEmpty();

            certificates.forEach(certificate -> {     // Проверяем наличие каждого из сертификатов в списке (lambda)
                System.out.printf("[ИНФОРМАЦИЯ]: проверяем наличие сертификата: [%s].%n", certificate);
                String xpath = String.format("//td[contains(text(),'%s')]", certificate);
                $(By.xpath(xpath)).waitUntil(clickable, 300000, 1000);
                try {
                    this.clickInElementJS(By.xpath(xpath));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("[ИНФОРМАЦИЯ]: сертификат успешно найден.");
            });

            config.setParameter("CurrentRtsSiteUrl", url());
            System.out.printf("[ИНФОРМАЦИЯ]: текущая версия сервера %s.%n", commonCustomerPage.getCurrentServerVersion());
        }

        timer.printStepTotalTime(stepName);
    }

    /**
     * Осуществляет вход в личный кабинет Пользователя.
     *
     * @param userType имя профиля пользователя
     */
    @When("^\"([^\"]*)\" заходит в личный кабинет$")
    public void userLogsToSystem(String userType) throws Throwable {
        String stepName = String.format("'%s' заходит в личный кабинет", userType);
        this.logStepName(stepName);

        timer.start();

        Dictionary certificateNames = new Dictionary();
        certificateNames.add("Заказчик", config.getConfigParameter("CertificateCustomerName"));
        certificateNames.add("Заказчик615", config.getConfigParameter("Certificate615Customer1Name"));
        certificateNames.add("Заказчик 1 Сотрудник 1", config.getConfigParameter("CertificateCustomer1Name1"));
        certificateNames.add("Заказчик 1 Сотрудник 2", config.getConfigParameter("CertificateCustomer1Name2"));
        certificateNames.add("Заказчик 2 Сотрудник 1", config.getConfigParameter("CertificateCustomer2Name1"));
        certificateNames.add("Заказчик 2 Сотрудник 2", config.getConfigParameter("CertificateCustomer2Name2"));
        certificateNames.add("Заказчик 3 Сотрудник 1", config.getConfigParameter("CertificateCustomer3Name1"));
        certificateNames.add("Заказчик 3 Сотрудник 2", config.getConfigParameter("CertificateCustomer3Name2"));
        certificateNames.add("Заказчик Юридическое лицо 5", config.getConfigParameter("CertificateCustomer5Name"));
        certificateNames.add("Заказчик Юридическое лицо 6", config.getConfigParameter("CertificateCustomer6Name"));
        certificateNames.add("Заказчик Юридическое лицо 7", config.getConfigParameter("CertificateCustomer7Name"));
        certificateNames.add("Заказчик Юридическое лицо 8", config.getConfigParameter("CertificateCustomer8Name"));
        certificateNames.add("Заказчик Юридическое лицо 9", config.getConfigParameter("CertificateCustomer9Name"));
        certificateNames.add("Заказчик для Конкурса", config.getConfigParameter("CertificateCustomerNameForTender"));
        certificateNames.add("Заказчик 1 для работы с ЕИС", config.getConfigParameter("CertificateEISCustomer1Name"));
        certificateNames.add("Заказчик 1 для работы с РЖД", config.getConfigParameter("CertificateRZHDCustomer1Name"));
        certificateNames.add("Заказчик для боевого сервера",
                config.getConfigParameter("OnProdCertificateCustomer1Name"));
        certificateNames.add("Поставщик Юридическое лицо 1", config.getConfigParameter("CertificateSupplier1Name"));
        certificateNames.add("Поставщик Юридическое лицо 1 для боевого сервера",
                config.getConfigParameter("OnProdCertificateSupplier1Name"));
        certificateNames.add("Поставщик Юридическое лицо 2", config.getConfigParameter("CertificateSupplier2Name"));
        certificateNames.add("Поставщик Юридическое лицо 2 для боевого сервера",
                config.getConfigParameter("OnProdCertificateSupplier2Name"));
        certificateNames.add("Поставщик Юридическое лицо 3", config.getConfigParameter("CertificateSupplier3Name"));
        certificateNames.add("Поставщик Юридическое лицо 3 для боевого сервера",
                config.getConfigParameter("OnProdCertificateSupplier3Name"));
        certificateNames.add("Поставщик Юридическое лицо 4 для боевого сервера",
                config.getConfigParameter("OnProdCertificateSupplier4Name"));
        certificateNames.add("Поставщик Юридическое лицо 5 для боевого сервера",
                config.getConfigParameter("OnProdCertificateSupplier5Name"));
        certificateNames.add("Поставщик Юридическое лицо 6 для боевого сервера",
                config.getConfigParameter("OnProdCertificateSupplier6Name"));
        certificateNames.add("Поставщик Юридическое лицо 7 для боевого сервера",
                config.getConfigParameter("OnProdCertificateSupplier7Name"));
        certificateNames.add("Поставщик Юридическое лицо 8 для боевого сервера",
                config.getConfigParameter("OnProdCertificateSupplier8Name"));
        certificateNames.add("Администратор", config.getConfigParameter("CertificateAdminName"));

        switch (userType) {
            //----------------------------------------------------------------------------------------------------------
            case "Заказчик":
            case "Заказчик615":
            case "Заказчик для Конкурса":
            case "Заказчик 1 Сотрудник 1":
            case "Заказчик 1 Сотрудник 2":
            case "Заказчик 2 Сотрудник 1":
            case "Заказчик 2 Сотрудник 2":
            case "Заказчик 3 Сотрудник 1":
            case "Заказчик 3 Сотрудник 2":
            case "Заказчик Юридическое лицо 5":
            case "Заказчик Юридическое лицо 6":
            case "Заказчик Юридическое лицо 7":
            case "Заказчик Юридическое лицо 8":
            case "Заказчик Юридическое лицо 9":
            case "Заказчик 1 для работы с ЕИС":
            case "Заказчик 2 для работы с ЕИС":
            case "Заказчик 1 для работы с РЖД":
            case "Заказчик для боевого сервера":
            case "Заказчик Сгенерированный пользователь":
                customerLogInPage.goToUserLoginPage().loginAsCustomer(certificateNames.find(userType));
                config.setParameter("CertName", certificateNames.find(userType));
                break;
            //----------------------------------------------------------------------------------------------------------
            case "Поставщик Юридическое лицо 1":
            case "Поставщик Юридическое лицо 1 для боевого сервера":
            case "Поставщик Юридическое лицо 2":
            case "Поставщик Юридическое лицо 2 для боевого сервера":
            case "Поставщик Юридическое лицо 3":
            case "Поставщик Юридическое лицо 3 для боевого сервера":
            case "Поставщик Юридическое лицо 4 для боевого сервера":
            case "Поставщик Юридическое лицо 5 для боевого сервера":
            case "Поставщик Юридическое лицо 6 для боевого сервера":
            case "Поставщик Юридическое лицо 7 для боевого сервера":
            case "Поставщик Юридическое лицо 8 для боевого сервера":
            case "Администратор":
                supplierOrOperatorrLogInPage.goToUserLoginPage().loginAsSupplierOrOperator(certificateNames.find(userType));
                config.setParameter("CertName", certificateNames.find(userType));
                break;
            //----------------------------------------------------------------------------------------------------------
            case "Поставщик Юридическое лицо по логину и паролю":
                supplierOrOperatorrLogInPage.goToOpenPartPage().
                        setLogin(config.getConfigParameter("OpenPartSupplier1Login")).
                        setPassword(config.getConfigParameter("OpenPartSupplier1Password")).
                        clickOnLoginButton();
                break;
            //----------------------------------------------------------------------------------------------------------
            default:
                Assert.fail(String.format(
                        "[ОШИБКА]: в метод (userLogToSystem) передан некорректный параметр (userType): '%s'.", userType));
                break;
            //----------------------------------------------------------------------------------------------------------
        }

        //==============================================================================================================
        config.setMainWindowHandle(driver.getWindowHandle());
        //==============================================================================================================
        System.out.printf("[ИНФОРМАЦИЯ]: произведено получение размера окна обозревателя: %s.%n",
                getWebDriver().manage().window().getSize());

        timer.printStepTotalTime(stepName);
    }

    /**
     * Осуществляет выход из личного кабинета Пользователя.
     */
    @And("'Пользователь' покидает систему")
    public void userLogsOff() throws Throwable {
        String stepName = "'Пользователь' покидает систему";
        this.logStepName(stepName);

        timer.start();

        userLogOffPage.userLogsOff();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Пользователь' ожидает \"([^\"]*)\" секунд$")
    public void userWaitsSpecifiedNumberOfSeconds(String seconds) throws Throwable {
        String stepName = String.format("'Пользователь' ожидает '%s' секунд", seconds);
        this.logStepName(stepName);

        timer.start();

        long timeout = Long.parseLong(seconds);
        System.out.printf("[ИНФОРМАЦИЯ]: ожидание: [%s] сек.%n", seconds);
        TimeUnit.SECONDS.sleep(timeout);

        timer.printStepTotalTime(stepName);
    }

    /**
     * Устанавливает глобальный необязательный параметр [Снимать скриншоты].
     *
     * @param screenshotsActivity требуемое значение параметра
     */
    @Given("^Параметру 'Снимать скриншоты' установлено значение \"([^\"]*)\"$")
    public void userSetsScreenshotsActivity(String screenshotsActivity) {
        String stepName = String.format("Параметру 'Снимать скриншоты' установлено значение '%s'", screenshotsActivity);
        this.logStepName(stepName);

        timer.start();

        config.setScreenshotActive(Boolean.parseBoolean(screenshotsActivity));

        timer.printStepTotalTime(stepName);
    }

    @And("^'Пользователь' сохраняет копию экрана приложения$")
    public void userTakesScreenshot() throws Throwable {
        String stepName = "'Пользователь' сохраняет копию экрана приложения";
        this.logStepName(stepName);

        timer.start();

        screenshoter.takeScreenshotWithDelay(0);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Пользователь' сохраняет копию экрана приложения после задержки в \"([^\"]*)\" секунд$")
    public void userTakesScreenshot(String timeoutInSeconds) throws Throwable {
        String stepName = String.format(
                "'Пользователь' сохраняет копию экрана приложения после задержки в '%s' секунд", timeoutInSeconds);
        this.logStepName(stepName);

        timer.start();

        screenshoter.takeScreenshotWithDelay(Long.parseLong(timeoutInSeconds));

        timer.printStepTotalTime(stepName);
    }

    @And("^'Пользователь' сохраняет номер извещения о закупке \"([^\"]*)\" в параметрах автоматического теста$")
    public void userStoresPurchaseNumberInCommonAutotestParameters(String purchaseNumber) {
        String stepName = String.format(
                "'Пользователь' сохраняет номер извещения о закупке '%s' в параметрах автоматического теста",
                purchaseNumber);
        this.logStepName(stepName);

        timer.start();

        config.setParameter("PurchaseNumber", purchaseNumber);

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' сохраняет значение {string} как параметр {string} автоматического теста")
    public void userStoresValueAsParameterInCommonAutotestParameters(String parameterValue, String parameterKey) {
        String stepName = String.format("'Пользователь' сохраняет значение '%s' как параметр '%s' автоматического теста",
                parameterValue, parameterKey);
        this.logStepName(stepName);

        timer.start();

        config.setParameter(parameterKey, parameterValue);

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' сохраняет значение {string} из файла конфигурации как параметр {string} автоматического теста")
    public void userStoresValueFromConfigurationFileAsParameterInCommonAutotestParameters(
            String configKey, String parameterKey) {
        String stepName = String.format(
                "'Пользователь' сохраняет значение '%s' из файла конфигурации как параметр '%s' автоматического теста",
                configKey, parameterKey);
        this.logStepName(stepName);

        timer.start();

        config.setParameter(parameterKey, config.getConfigParameter(configKey));

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' переходит по прямой ссылке {string}")
    public void userGoesToDirectUrl(String url) {
        String stepName = String.format("'Пользователь' переходит по прямой ссылке '%s'", url);
        this.logStepName(stepName);

        timer.start();

        open(url);

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' удаляет сертификат")
    public void userDeletesCertificate() {
        String stepName = "'Пользователь' удаляет сертификат";
        this.logStepName(stepName);

        timer.start();

        String cH = config.getParameter("OldCertificateHash");
        System.out.printf("[ИНФОРМАЦИЯ]: удаляется сертификат из системы, хэш сертификата: [%s].%n", cH);
        CertificateGenerator.getInstance().deleteCertificate(cH);

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' выбирает организацию в диалоговом окне 'Выберите организацию' на странице 'Заявка на добавление пользователя'")
    public void userChoosesOrganizationInSelectOrganizationForAddingEmployee() throws Throwable {
        String stepName =
                "'Пользователь' выбирает организацию в диалоговом окне 'Выберите организацию' " +
                        "на странице 'Заявка на добавление пользователя'";
        this.logStepName(stepName);

        timer.start();

        SelectOrganizationForAddingEmployeeDialog selectOrganizationForAddingEmployeeDialog =
                new SelectOrganizationForAddingEmployeeDialog(driver);
        selectOrganizationForAddingEmployeeDialog.
                ifDialogOpened().
                clickOnFieldByName("Организация").
                choseOrganization(config.getParameter("OldCertificateName")).
                clickOnButtonToCloseDialog("Да");

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' генерирует сертификат для сотрудника ИП")
    public void userGeneratesCertificateForEmployee() {
        String stepName = "'Пользователь' генерирует сертификат для сотрудника ИП";

        timer.start();


        String firstName = config.getParameter("CertificateName").split(" ")[2];
        String lastName = config.getParameter("CertificateName").split(" ")[1] + "Employee";

        CertificateGenerator.getInstance().generateCertificate(
                config.getParameter("CertificateName"),
                "Ip",
                config.getParameter("AccreditationInnValue"),
                "random",
                "random",
                firstName,
                lastName);

        //--------------------------------------------------------------------------------------------------------------
        //перезаписываем параметр CertName для работы с новым сгенерированным сертификатом
        String oldCertificateName = config.getParameter("CertificateName");
        config.setParameter("OldCertificateName", oldCertificateName);//сохраняем старый параметр CertName
        String[] organizationName = oldCertificateName.split(" ");
        config.setParameter("CertificateName", organizationName[0] + " " + organizationName[1] +
                "Employee " + organizationName[2]);
        config.setParameter("OldCertificateHash",
                config.getParameter("CertificateHash"));//сохраняем старый параметр Хэш

        config.setParameter("OldCertName", config.getParameter("CertName"));//сохраняем старый параметр CertName
        config.setParameter("CertName", organizationName[0] + " " + organizationName[1] +
                "Employee " + organizationName[2]);

        config.setParameter("EmployeeUserName", organizationName[1] + "Employee " + organizationName[2]);
        config.setParameter("EmployeeFullUserName", organizationName[0] + " " + organizationName[1] + "Employee "
                + organizationName[2]);
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' сохраняет информацию о зарегистрированном Администраторе организации")
    public void userStoresInformationAboutAdministrationWindowHandle() {
        String stepName = "'Пользователь' сохраняет информацию о зарегистрированного Администратора";
        this.logStepName(stepName);

        timer.start();

        config.setParameter("OrganizationName", config.getParameter("CertName"));
        String[] fio = config.getParameter("CertName").split(" ");
        config.setParameter("OrganizationAdministratorName", fio[1] + " " + fio[2]);

        timer.printStepTotalTime(stepName);
    }
}
