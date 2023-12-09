package ru.StepDefinitions;

import Helpers.CertificateGenerator;
import Helpers.ConfigContainer;
import Helpers.Screenshoter;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelectorMode;
import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static com.codeborne.selenide.Configuration.timeout;

/**
 * Код, который будет выполняться до и после каждого сценария.
 * Created by Dima Makieiev on 08.12.2015.
 * Updated by Vladimir V. Klochkov on 06.07.2021.
 */
public class Hooks {
    public static WebDriver driver;                         // Экземпляр WebDriver (главный, остальные - ссылки на него)
    private final Screenshoter screenshoter = new Screenshoter(); // экземпляр Screenshoter

    @Before
    /**
     * Код, который будет выполняться до каждого сценария.
     * Настраиваем профиль и поведение нового экземпляра WebDriver.
     * Чистим все, что можем очистить чтобы избежать общего состояния между выполнением тестов.
     */
    public void beforeTest(Scenario scenario) throws UnknownHostException {

        // region Загружаем настройки тестовой среды из файла [config.properties]

        ConfigContainer.getInstance().loadConfig();

        // endregion

        // region Создание и настройка нового экземпляра WebDriver ( для Selenium )

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", ConfigContainer.getInstance().getPathToTempFolderWithRandomName());

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.SEVERE);

        ChromeOptions options = new ChromeOptions();
        options.setBinary(new File("src/test/resources/drivers/chromium/chrome.exe"));
        options.addExtensions(new File("src/test/resources/drivers/CryptoProExt.crx"));
        options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        // options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        options.addArguments("--disable-dev-shm-usage");
        options.setExperimentalOption("debuggerAddress","localhost:1258");

        //--------------------------------------------------------------------------------------------------------------
        driver = new ChromeDriver(options);               //   S E L E N I U M
        //--------------------------------------------------------------------------------------------------------------

        Configuration.selectorMode = SelectorMode.Sizzle;
        Configuration.pollingInterval = 50;
        System.out.println(">>> (beforeTest) The static instance of Selenium WebDriver was initialized");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        // Инициализируем время ожидания по умолчанию в секундах для Selenium
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // endregion

        // region Инициализируем Selenide нашим экземпляром WebDriver

        //--------------------------------------------------------------------------------------------------------------
        WebDriverRunner.setWebDriver(driver); //   S E L E N I D E
        //--------------------------------------------------------------------------------------------------------------

        System.out.println(">>> (beforeTest) The static instance of Selenide WebDriver was initialized");

        // Инициализируем время ожидания по умолчанию в миллисекундах для Selenide
        timeout = 30000;

        // endregion

        // region Проверяем браузер и его версию

        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        String browserVersion = cap.getVersion().toLowerCase();
        System.out.printf(">>> (beforeTest) Browser is: %s version: %s%n", browserName, browserVersion);

        // endregion

        // region Выводим дополнительную информацию о компьютере, на котором выполняются тесты

        System.out.println(">>> (beforeTest) Computer name is : " + InetAddress.getLocalHost().getHostName());
        System.out.println(">>> (beforeTest) IP address is    : " + InetAddress.getLocalHost().getHostAddress());
        System.out.println(">>> (beforeTest) OS name is       : " + System.getProperty("os.name"));
        System.out.println(">>> (beforeTest) OS version is    : " + System.getProperty("os.version"));
        System.out.println(">>> (beforeTest) User logged in as: " + System.getProperty("user.name"));

        // endregion

        // region Сохраняем в ConfigContainer наш экземпляр WebDriver

        ConfigContainer.getInstance().setDriver(driver);

        // endregion

        // region Сохраняем ссылку на экземпляр класса Scenario (Сucumber JVM Scenario object)

        ConfigContainer.getInstance().setScenario(scenario);

        // endregion
    }

    @After
    /**
     * Код, который будет выполняться после каждого сценария.
     * Встраиваем скриншот в тестовый отчет, если тест завершен аварийно.
     */
    public void afterTest(Scenario scenario) throws Exception {
        // region Сохраняем ссылку на экземпляр класса Scenario (Сucumber JVM Scenario object)

        ConfigContainer.getInstance().setScenario(scenario);

        // endregion

        // region Делаем скриншот в случае аварийного завершения теста

        if (scenario.isFailed()) screenshoter.takeScreenshot();

        // endregion

        // region Если тип теста - аккредитация нового пользователя, то удаляем сгенерированный для него сертификат

        String testType = ConfigContainer.getInstance().getParameter("TestType");

        if (testType != null && testType.equals("Accreditation")) {
            String cN = ConfigContainer.getInstance().getParameter("CertificateName");
            System.out.printf("[ИНФОРМАЦИЯ]: удаляю сертификат из системы, имя сертификата: [%s].%n", cN);
            CertificateGenerator.getInstance().deleteCertificate(cN);

            String cH = ConfigContainer.getInstance().getParameter("CertificateHash");
            System.out.printf("[ИНФОРМАЦИЯ]: удаляю сертификат из системы, хэш сертификата: [%s].%n", cH);
            CertificateGenerator.getInstance().deleteCertificate(cH);
        }

        // endregion

        // region Печатаем список использованных в текущем тесте параметров

        ConfigContainer.getInstance().printParameters();

        // endregion

        // region Печатаем список ошибок консоли браузера

        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        System.out.printf(
                "%n=========   С П И С О К   О Ш И Б О К   К О Н С О Л И   Б Р А У З Е Р А      [%d]   =========%n",
                logEntries.getAll().size());
        for (LogEntry logEntry : logEntries) System.out.println(logEntry.toString());

        // endregion

        // region Закрываем браузер в любом случае (нормальное или аварийное завершение теста)

        driver.close();
        TimeUnit.SECONDS.sleep(15); // таймаут для того, чтобы браузер успел нормально закрыть текущую вкладку
        driver.quit();

        // endregion
    }
}
