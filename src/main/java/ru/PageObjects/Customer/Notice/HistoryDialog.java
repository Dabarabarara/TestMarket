package ru.PageObjects.Customer.Notice;

import Helpers.Dictionary;
import Helpers.SoftAssert;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс для работы с диалоговым окном [История].
 * Created by Vladimir V. Klochkov on 22.02.2017.
 * Updated by Vladimir V. Klochkov on 15.03.2021.
 */
public class HistoryDialog extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов окна

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    HistoryDialog(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Текст заголовка окна", "HistoryWindow_wnd_title");
        controls.add("Кнопка Закрыть окно", "//span[@id='HistoryWindow_wnd_title']/following-sibling::div[1]/a");
        controls.add("Таблица с данными Все строки таблицы", "//*[@id='ContractHistoryGrid']/div/table/tbody/tr");
        controls.add("Ячейка с описанием требуемого действия в колонке Действие",
                "//*[@id='ContractHistoryGrid']//td[@role='gridcell' and contains(.,'%s')]");
        controls.add("Кнопка Экспорт в Excel", "HistoryToExcel");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет наличие диалогового окна [История].
     *
     * @return диалоговое окно [История]
     */
    public HistoryDialog ifDialogOpened() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        this.waitLoadingImage();
        Assert.assertTrue("[ОШИБКА]: диалоговое окно [История] не обнаружено",
                $(By.id(controls.find("Текст заголовка окна"))).waitUntil(exist, timeout, polling).isDisplayed());
        return this;
    }

    /**
     * Проверяет, что диалоговое окно [История] содержит не пустое количество записей.
     *
     * @return диалоговое окно [История]
     */
    HistoryDialog checkHistoryIsNotEmpty() {
        System.out.println("[ИНФОРМАЦИЯ]: проверяем количество записей в диалоговом окне [История].");
        SoftAssert.assertTrue("[ОШИБКА]: записи в диалоговом окне [История] отсутствуют",
                $$(By.xpath(controls.find("Таблица с данными Все строки таблицы"))).size() > 0);
        return this;
    }

    /**
     * Проверяет, что диалоговое окно [История] содержит запись о требуемом действии.
     *
     * @param actionName текст действия
     * @return диалоговое окно [История]
     */
    HistoryDialog checkEventByActionName(String actionName) {
        System.out.printf(
                "[ИНФОРМАЦИЯ]: проверяем, что диалоговое окно [История] содержит запись о требуемом действии [%s].%n",
                actionName);
        String xpath = String.format(controls.find("Ячейка с описанием требуемого действия в колонке Действие"),
                actionName);
        SoftAssert.assertTrue(
                String.format("[ОШИБКА]: запись о действии [%s] в диалоговом окне [История] отсутствует", actionName),
                $(By.xpath(xpath)).isDisplayed());
        return this;
    }

    /**
     * Закрывает диалоговое окно [История].
     */
    void closeThisWindow() throws Exception {
        System.out.println("[ИНФОРМАЦИЯ]: произведено нажатие кнопки [Закрыть окно] в диалоговом окне [История].");
        $(By.xpath(controls.find("Кнопка Закрыть окно"))).waitUntil(clickable, timeout, polling).click();
        this.waitLoadingImage();
        this.setShortDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        $(By.id(controls.find("Текст заголовка окна"))).shouldBe(disappear);
        this.setDefltDrivWait(); //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    }
}
