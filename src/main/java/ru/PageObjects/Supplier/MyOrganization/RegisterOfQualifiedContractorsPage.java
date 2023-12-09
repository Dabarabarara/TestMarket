package ru.PageObjects.Supplier.MyOrganization;

import Helpers.Dictionary;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Supplier.CommonSupplierPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывающий работу со страницой [Реестр квалифицированных подрядных организаций].
 * ( .kontur.ru/customer/lk/QualifiedSupplier )
 * Created by Kirill G. Rydzyvylo on 30.07.2020.
 * Updated by Vladimir V. Klochkov on 09.03.2021.
 */
public class RegisterOfQualifiedContractorsPage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Раздел [Реестр перечней]

    //------------------------------------------------------------------------------------------------------------------
    // Поле [Наименование перечня]
    private final String NAME_ID = "Name";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Найти]
    private final String SEARCH_BUTTON_XPATH = "//input[@value='Найти']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Таблица [Список квалифицированных подрядных организаций]

    //------------------------------------------------------------------------------------------------------------------
    // Столбец [Номер процедуры отбора]
    private static final String SELECTION_PROCEDURE_NUMBER_XPATH =
            "//tbody//td/a[contains(@href,'/customer/lk/auctions/view/%s#%s')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел элементов пагинации таблицы

// --Commented out by Inspection START (01.10.2020 20:28):
//    //------------------------------------------------------------------------------------------------------------------
//    // Элемент перехода на предыдущую страницу таблицы [<]
//    private static final String PREVIOUS_TABLE_PAGE_XPATH =
//            "//div[@id='QualifiedSupplierListGrid']//a[@title='Перейти на предыдущую страницу']";
// --Commented out by Inspection STOP (01.10.2020 20:28)
// --Commented out by Inspection START (01.10.2020 20:27):
//    //------------------------------------------------------------------------------------------------------------------
//    // Элемент перехода на последнюю страницу таблицы [>>]
//    private static final String LAST_TABLE_PAGE_XPATH =
//            "//div[@id='QualifiedSupplierListGrid']//a[@title='К последней странице']";
// --Commented out by Inspection STOP (01.10.2020 20:27)
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary fieldNames = new Dictionary();  // основные разделы извещения о закупке
    private final Dictionary buttonNames = new Dictionary(); // основные разделы лота

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public RegisterOfQualifiedContractorsPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Наименование перечня", NAME_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Найти", SEARCH_BUTTON_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Устанавливает значение поля [Наименование перечня].
     *
     * @param value требуемое значение поля
     */
    public void fillsFieldNameOfTheListWithDate(String value) throws Exception {
        String fieldValue = value + " " + timer.getPublishNoticeDateInFormatyyyyMMddhhmmss() + " №";
        this.waitClearClickAndSendKeys(this.getBy(fieldNames.find("Наименование перечня")), fieldValue);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [Наименование перечня] значением [%s].%n",
                fieldValue);
    }

    /**
     * Делает щелчок мышью по указанной кнопке.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) throws Exception {
        this.logButtonNameToPress(buttonName);
        this.clickInElementJS(this.getBy(buttonNames.find(buttonName)));
        this.logPressedButtonName(buttonName);
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * Проверяет наличие записи о квалифицированной подрядной организации.
     *
     * @param preselectionObjectNumber имя кнопки
     */
    public void checkTheNumberOFQualifiedContractorRecord(String preselectionObjectNumber) {
        String selectionProcedureNumberXpath = String.format(
                SELECTION_PROCEDURE_NUMBER_XPATH, config.getParameter("PurchaseNumber"), preselectionObjectNumber);

        System.out.printf(
                "[ИНФОРМАЦИЯ] сформирован локатор [%s] для проверки наличия записи о квалифицированной подрядной " +
                        "организации.%n", selectionProcedureNumberXpath);

        ElementsCollection elements = $$(getBy(selectionProcedureNumberXpath));
        System.out.printf("[ИНФОРМАЦИЯ]: найдена [%d] запись о квалифицированной подрядной организации.%n",
                elements.size());
        Assert.assertNotEquals("[ОШИБКА]: не найдено ни одной записи о квалифицированной подрядной организации",
                0, elements.size());
    }
}
