package ru.PageObjects.Customer.Reports;

import Helpers.Dictionary;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Customer.CommonCustomerPage;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Класс для работы со страницей [Реестр отчётов] ( пункт меню [ОТЧЕТЫ] ) ( .kontur.ru/customer/report-registry ).
 * Created by Vladimir V. Klochkov on 27.03.2019.
 * Updated by Vladimir V. Klochkov on 11.03.2021.
 */
public class RegistryOfReportsPage extends CommonCustomerPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Заголовок и URL страницы

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы
    private static final String REPORT_REGISTRY_HEADER_XPATH = "//h2[contains(., 'Реестр отчётов')]";
    //------------------------------------------------------------------------------------------------------------------
    // URL страницы
    private static final String REPORT_REGISTRY_PAGE_URL = "https://223fl.kontur.ru/customer/report-registry";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Фильтры поиска

    // region Список [Тип отчета]

    //------------------------------------------------------------------------------------------------------------------
    // Список [Тип отчета]
    private static final String REPORT_TYPE_VALUE_INPUT_FIELD_ID =
            "ReportTypeSelectionDropdown";
    //------------------------------------------------------------------------------------------------------------------
    // Список [Тип отчета] ( текущее значение )
    private static final String REPORT_TYPE_VALUE_XPATH = "//*[@id='ReportTypeSelectionDropdown']/../../div/label";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Поле [Диапазон дат ]

    //------------------------------------------------------------------------------------------------------------------
    // Субполе [От]
    private static final String PERIOD_DATE_FROM_ID = "ReportDatesFrom";
    //------------------------------------------------------------------------------------------------------------------
    // Субполе [До]
    private static final String PERIOD_DATE_TO_ID = "ReportDatesTo";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private boolean isOnProduction = false;                 // признак того, что автотест выполняется на боевом сервере
    private final Dictionary fieldNames = new Dictionary(); // все поля на странице

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     ******************************************************************************************************************/
    public RegistryOfReportsPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Тип отчёта", REPORT_TYPE_VALUE_XPATH);
        fieldNames.add("Диапазон дат - от", PERIOD_DATE_FROM_ID);
        fieldNames.add("Диапазон дат -до", PERIOD_DATE_TO_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет, что открыта именно страница [Реестр отчётов].
     *
     * @return страница [Реестр отчётов]
     */
    public RegistryOfReportsPage checkPageIsOpened() throws Exception {
        this.waitLoadingImage(15);
        $(this.getBy(REPORT_REGISTRY_HEADER_XPATH)).waitUntil(exist, timeout, polling).shouldBe(clickable);
        if (url().contains(REPORT_REGISTRY_PAGE_URL)) isOnProduction = true;
        System.out.printf("[ИНФОРМАЦИЯ]: автоматический тест выполняется на %s.%n",
                isOnProduction ? "боевом сервере" : "тестовом стенде");

        return this;
    }

    /**
     * Проверяет значение [true] атрибута [disable] в поле [Тип отчета].
     */
    public RegistryOfReportsPage checkDisableAttributeIsSetToTrueInReportTypeField() {
        String disabledAttributeValue = $(this.getBy(REPORT_TYPE_VALUE_INPUT_FIELD_ID)).
                waitUntil(exist, timeout, polling).getAttribute("disabled");

        System.out.println("[ИНФОРМАЦИЯ]: проверка значения [true] атрибута [disable] в поле [Тип отчета].");
        Assert.assertNotNull("[ОШИБКА]: атрибут [disable] поля [Тип отчета] не найден", disabledAttributeValue);

        System.out.printf("[ИНФОРМАЦИЯ]: атрибут [disable] в поле [Тип отчета] содержит значение [%s].%n",
                disabledAttributeValue);
        Assert.assertTrue("[ОШИБКА]: атрибут поля [Тип отчета] [disable] не содержит ожидаемое значение [true]",
                Boolean.parseBoolean(disabledAttributeValue));

        return this;
    }

    /**
     * Проверяет значение поля.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение, которое должно содержаться в поле
     */
    public void verifyFieldContentOrEmptyField(String fieldName, String value) {
        String message = String.format("[ОШИБКА]: значение [%s] не содержится в поле [%s]", value, fieldName);
        System.out.printf("[ИНФОРМАЦИЯ]: проверка поля [%s] - ожидаемое значение [%s].%n", fieldName, value);
        $(this.getBy(fieldNames.find(fieldName))).waitUntil(exist, timeout, polling);
        if (value.trim().isEmpty()) {
            String actualValue = $(this.getBy(fieldNames.find(fieldName))).getText().trim();
            if (actualValue.isEmpty()) actualValue = $(this.getBy(fieldNames.find(fieldName))).getValue().trim();
            Assert.assertTrue(message, actualValue.isEmpty());
        } else
            Assert.assertTrue(message, $(this.getBy(fieldNames.find(fieldName))).getText().contains(value));
    }
}
