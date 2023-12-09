package ru.PageObjects.Customer.Messages;

import Helpers.Dictionary;
import Helpers.SoftAssert;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Класс для работы со страницей Заказчика [Просмотр сообщения XXXXX]
 * ( Главная / Заказчикам / Уведомления / XXXXX )
 * ( .kontur.ru/customer/lk/Message/ViewMessage/ ).
 * Created by Vladimir V. Klochkov on 09.01.2020.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class CustomerViewMessagePage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary controls = new Dictionary(); // локаторы всех управляющих элементов страницы

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     ******************************************************************************************************************/
    public CustomerViewMessagePage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        controls.add("Заголовок страницы", "//h2[contains(.,'Просмотр сообщения ')]");
        controls.add("Поле Тема", "//label[contains(.,'Тема:')]/following-sibling::div[1]");
        controls.add("Текст сообщения", "//h2[contains(.,'Текст сообщения')]");
        controls.add("Ссылка Перейти к рассмотрению запроса можно по ссылке",
                "//a[contains(@href,'supplier/lk/PartnerRelation/PartnerRelationRequestInfo.aspx?Id=')]");
        controls.add("Ссылка Подробности можно посмотреть в кабинете участника или по ссылке",
                "//a[contains(., '.kontur.ru/customer/lk/registerofinquiries/viewinquiries')]");
        controls.add("Ссылка Подробности можно посмотреть в кабинете участника или по ссылке (разъяснение документации)",
                "//a[contains(., '.kontur.ru/customer/lk/RegisterOfInquiries/ViewInquiries')]");
        controls.add("Ссылка Вы назначены ответственным для подписания договора по закупке",
                "//a[contains(@href,'.kontur.ru/customer/lk/contracts/view')]");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Переходит по ссылке [Перейти к рассмотрению запроса можно по ссылке].
     */
    public void clickOnPartnerRelationRequestInfoLink() throws Exception {
        SelenideElement link =
                $(this.getBy(controls.find("Ссылка Перейти к рассмотрению запроса можно по ссылке")));
        System.out.printf("[ИНФОРМАЦИЯ]: переходим к рассмотрению запроса по ссылке [%s].%n",
                link.getText());
        this.scrollToCenterAndclickInElementJS(link);
    }

    /**
     * Переходит по ссылке [Подробности можно посмотреть в кабинете участника или по ссылке].
     */
    public void clickOnRegisterOfInquiriesInfoLink() throws Exception {
        SelenideElement link = $(this.getBy(
                controls.find("Ссылка Подробности можно посмотреть в кабинете участника или по ссылке")));
        System.out.printf("[ИНФОРМАЦИЯ]: переходим к просмотру разъяснения заявки по ссылке [%s].%n",
                link.getText());
        this.scrollToCenterAndclickInElementJS(link);
    }

    /**
     * Переходит по ссылке [Подробности можно посмотреть в кабинете участника или по ссылке] в запросе на раъяснение документации.
     */
    public void clickOnRegisterOfInquiriesDocumentationInfoLink() throws Exception {
        SelenideElement link = $(this.getBy(
                controls.find("Ссылка Подробности можно посмотреть в кабинете участника или по ссылке (разъяснение документации)")));
        System.out.printf("[ИНФОРМАЦИЯ]: переходим к просмотру разъяснения заявки по ссылке [%s].%n",
                link.getText());
        this.scrollToCenterAndclickInElementJS(link);
    }

    /**
     * Переходит по ссылке тексте сообщения.
     */
    public void clickOnRequestInfoLink(String requestLink) throws Exception {
        SelenideElement link = $(this.getBy(controls.find(requestLink)));
        System.out.printf("[ИНФОРМАЦИЯ]: переходим к рассмотрению запроса по ссылке [%s].%n",
                link.getText());
        this.scrollToCenterAndclickInElementJS(link);
    }
}
