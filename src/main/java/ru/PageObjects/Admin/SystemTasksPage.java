package ru.PageObjects.Admin;

import Helpers.ConfigContainer;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Класс для работы со страницей [Системные задачи] ( АДМИНИСТРИРОВАНИЕ / Сервисные страницы / Системные задачи ).
 * [ .kontur.ru/supplier/auction/Scheduler/Scheduler.aspx ]
 * Created by Dima Makieiev on 09.02.2016.
 * Updated by Vladimir V. Klochkov on 25.02.2021.
 */
public class SystemTasksPage extends CommonAdminPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    private static final String ADMIN_TAB_XPATH = "//span[contains(.,'Администрирование')]";
    //------------------------------------------------------------------------------------------------------------------
    private static final String SYSTEM_TASKS_TABLE_XPATH = "//td[@class='ng-binding']";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public SystemTasksPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    /**
     * Оператор открывает меню Администрирование.
     */
    public void openAdminTab() throws Exception {
        $(By.xpath(ADMIN_TAB_XPATH)).waitUntil(visible, timeout, polling).click();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("[ИНФОРМАЦИЯ]: произведено открытие/закрытие меню [Администрирование].");
    }

    /**
     * Оператор запускает на выполнение системную задачу.
     *
     * @param description название системной задачи в колонке [Описание]
     */
    public void selectRowAndClickInTable(String description) throws Exception {
        ElementsCollection tasks = $$(By.xpath(SYSTEM_TASKS_TABLE_XPATH));
        for (SelenideElement task : tasks) {
            String taskName = task.getText();
            if (taskName.contains(description)) {
                task.click();
                System.out.printf("[ИНФОРМАЦИЯ]: произведен выбор системной задачи: [%s], ждем 65 секунд.%n", taskName);
                TimeUnit.SECONDS.sleep(65); // Задача отрабатывает в пределах одной минуты
                break;
            }
        }
    }

    /**
     * Оператор проверяет состояние системной задачи после выполнения в колонке [Состояние].
     *
     * @param description название системной задачи в колонке [Описание]
     */
    public void checkStatusOk(String description) {
        final String stateOk = "- OK -";
        int size = $$(By.xpath(SYSTEM_TASKS_TABLE_XPATH)).size();

        for (int i = 0; i < size; i++) {
            String taskName = $$(By.xpath(SYSTEM_TASKS_TABLE_XPATH)).get(i).getText();
            if (taskName.contains(description))  // Если мы обнаружили подходящий текст в колонке [Описание],
            {                                    // то следующая по порядку ячейка содержит состояние системной задачи
                String state = $$(By.xpath(SYSTEM_TASKS_TABLE_XPATH)).get(i + 1).getText();
                System.out.println("[ИНФОРМАЦИЯ]: обнаружена системная задача     [" + taskName + "].");
                System.out.println("[ИНФОРМАЦИЯ]: состояние задачи содержит текст [" + state + "].");
                Assert.assertTrue(
                        "Состояние системной задачи не соответствует ожидаемому !", state.contains(stateOk));
                System.out.println("[ИНФОРМАЦИЯ]: произведена проверка состояния: [" + stateOk +
                        "] системной задачи: [" + taskName + "].");
                break;
            }
        }
    }
}
