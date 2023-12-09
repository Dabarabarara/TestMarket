package ru.PageObjects.Customer.Protocol;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывающий страницу [Протокол оценки заявок конкурса] (customer/lk/Protocols/ProtocolForm/).
 * Created by Evgeniy Glushko on 12.04.2016.
 * Updated by Vladimir V. Klochkov on 05.02.2021.
 */
public class TenderEvaluationRequestCustomerProtocolPage extends CommonCustomerProtocolPage {
    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public TenderEvaluationRequestCustomerProtocolPage(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    //------------------------------------------------------------------------------------------------------------------
    // Поля в колонке [Итоговый балл]
    private static final String FINAL_SCORE_XPATH = "//input[@class='k-formatted-value mainRating k-input']";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок [Прикрепление протокола оценки заявок]
    private static final String ATTACH_PROTOCOL_HEADER_XPATH = "//h2[contains(.,'Прикрепление протокола')]";
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Устанавливает значение поля [Итоговый балл] в колонке [Итоговый балл].
     *
     * @param number индекс элемента
     * @param value  значение итогового балла
     */
    public void setFinalScore(int number, String value) {
        ElementsCollection finalScores = $$(By.xpath(FINAL_SCORE_XPATH));
        SelenideElement finalScore = finalScores.get(number);
        finalScore.waitUntil(clickable, timeout, polling).sendKeys(value);
        $(By.xpath(ATTACH_PROTOCOL_HEADER_XPATH)).waitUntil(clickable, timeout, polling).click();
    }
}
