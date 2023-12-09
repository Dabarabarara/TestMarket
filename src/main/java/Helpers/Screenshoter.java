package Helpers;

import cucumber.api.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Класс для работы со скриншотами (снятие скриншота, сохранение и прочее).
 * Created by Vladimir V. Klochkov on 11.07.2017.
 * Updated by Vladimir V. Klochkov on 11.03.2021.
 */
public class Screenshoter {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final ConfigContainer config = ConfigContainer.getInstance(); // экземпляр ConfigContainer

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    /**
     * Встраивает скриншот в отчет о прохождении автоматичиеского теста с предварительной временной задержкой.
     * Если в файле конфигрурации для текущего стенда ScreenshotsInThisEnv = off скриншот НЕ встраивается в отчёт.
     * Если настройка текущего теста config.isScreenshotActive() == false скриншот НЕ встраивается в отчёт.
     *
     * @param delayInSeconds предварительная временная задержка
     */
    public void takeScreenshotWithDelay(long delayInSeconds) throws Exception {
        if (config.getConfigParameter("ScreenshotsInThisEnv").equals("off") || !config.isScreenshotActive()) return;
        if (delayInSeconds > 0) {
            System.out.printf("[ИНФОРМАЦИЯ]: ожидаем %d сек.%n", delayInSeconds);
            TimeUnit.SECONDS.sleep(delayInSeconds);
        }
        this.takeScreenshot();
    }

    /**
     * Встраивает скриншот в отчет о прохождении автоматичиеского теста без задержек и дополнительных проверок.
     */
    public void takeScreenshot() throws Exception {
        System.out.println("[ИНФОРМАЦИЯ]: сохранение скриншотов страницы приложения...");

        try {

            // Сохраняем скриншот видимой части страницы приложения и встраиваем его в отчет
            Scenario scenario = config.getScenario();
            scenario.write(String.format("[ИНФОРМАЦИЯ]: адрес текущей страницы приложения: %s", url()));
            scenario.write("[ИНФОРМАЦИЯ]: скриншот видимой части страницы приложения:");
            byte[] smallScreenshot = ((TakesScreenshot) config.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(smallScreenshot, "image/png");

            // Сохраняем скриншот всей страницы приложения и встраиваем его в отчет
            scenario.write(String.format("[ИНФОРМАЦИЯ]: адрес текущей страницы приложения: %s", url()));
            scenario.write("[ИНФОРМАЦИЯ]: скриншот всей страницы приложения:");
            BufferedImage image = new AShot().
                    shootingStrategy(ShootingStrategies.viewportPasting(1000)).
                    takeScreenshot(config.getDriver()).getImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();
            byte[] largeScreenshot = baos.toByteArray();
            baos.close();
            scenario.embed(largeScreenshot, "image/png");

        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }

        System.out.println("[ИНФОРМАЦИЯ]: скриншоты страницы приложения сохранены успешно.");
    }
}
