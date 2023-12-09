package ru.PageObjects.Supplier.MyOrganization;

import Helpers.Dictionary;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.PageObjects.Supplier.CommonSupplierPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывающий работу со страницой [Редактирование информации].
 * Created by Vladimir V. Klochkov on 19.08.2019.
 * Updated by Vladimir V. Klochkov on 11.03.2021.
 */
public class EditInformationPage extends CommonSupplierPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Раздел [Документы заявителя]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Документы заявителя]
    private static final String APPLICANT_DOCUMENTS_BLOCK_XPATH = "//h2[contains(.,'Документы заявителя')]";
    //------------------------------------------------------------------------------------------------------------------
    // Заголовок вкладки [Все активные]
    private static final String ALL_ACTIVE_TAB_XPATH = "//a[@class='ui-tabs-anchor' and contains(.,'Все активные')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Блок управляющих кнопок в нижней части страницы

    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Сохранить черновик]
    private static final String SAVE_DRAFT_BUTTON_ID = "BaseMainContent_MainContent_mvgbSaveDraft";
    //------------------------------------------------------------------------------------------------------------------
    // Кнопка [Подать заявку на редактирование]
    private static final String CHANGE_ACCREDITATION_BUTTON_ID = "BaseMainContent_MainContent_mvgbAccreditation";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary blockNames = new Dictionary();  // все разделы на странице
    private final Dictionary tabNames = new Dictionary();    // все вкладки на странице
    private final Dictionary buttonNames = new Dictionary(); // все кнопки на странице

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     ******************************************************************************************************************/
    public EditInformationPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        blockNames.add("Документы заявителя", APPLICANT_DOCUMENTS_BLOCK_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        tabNames.add("Все активные", ALL_ACTIVE_TAB_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.add("Сохранить черновик", SAVE_DRAFT_BUTTON_ID);
        buttonNames.add("Подать заявку на редактирование", CHANGE_ACCREDITATION_BUTTON_ID);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Переходит к блоку полей на форме.
     *
     * @param blockName имя блока
     */
    public void gotoBlock(String blockName) throws Exception {
        SelenideElement block = $(this.getBy(blockNames.find(blockName)));
        this.scrollToCenter(block);
        TimeUnit.SECONDS.sleep(3);
        System.out.printf("[ИНФОРМАЦИЯ]: Произведен переход к блоку полей: [%s].%n", blockName);
    }

    /**
     * Переключает на требуемую вкладку.
     *
     * @param tabName имя вкладки
     */
    public void switchToTab(String tabName) throws Exception {
        SelenideElement tab = $(this.getBy(tabNames.find(tabName)));
        this.scrollToCenter(tab);
        tab.waitUntil(clickable, timeout, polling).click();
        TimeUnit.SECONDS.sleep(1);
        System.out.printf("[ИНФОРМАЦИЯ]: Произведено нажатие на вкладку: [%s].%n", tabName);
    }

    /**
     * Проверяет значение счетчиков в заголовке требуемой вкладки.
     *
     * @param tabName имя вкладки
     */
    public void checkCountersInTabTitle(String tabName) {
        SelenideElement tab = $(this.getBy(tabNames.find(tabName)));
        this.scrollToCenter(tab);
        String tabTitleText = tab.waitUntil(visible, timeout, polling).getText();
        String counter1 = tabTitleText.split("\\(")[1].split("/")[0];
        String counter2 = tabTitleText.split("/")[1].split("\\)")[0];
        System.out.printf("[ИНФОРМАЦИЯ]: Текст заголовка вкладки: [%s].%n", tabTitleText);
        System.out.printf("[ИНФОРМАЦИЯ]: Счетчик 1: [%s], Счетчик 2: [%s].%n", counter1, counter2);
        int cnt1 = Integer.parseInt(counter1);
        int cnt2 = Integer.parseInt(counter1);
        Assert.assertNotEquals(String.format("[ОШИБКА]: счетчик 1 в заголовке вкладки [%s] равен 0", tabTitleText),
                0, cnt1);
        Assert.assertNotEquals(String.format("[ОШИБКА]: счетчик 2 в заголовке вкладки [%s] равен 0", tabTitleText),
                0, cnt2);
    }

    /**
     * Проверяет наличие указанной кнопки.
     */
    public void checkButtonExistAndClickable(String buttonName) {
        SelenideElement button = $(this.getBy(buttonNames.find(buttonName)));
        this.scrollToCenter(button);
        button.waitUntil(clickable, timeout, polling).shouldBe(enabled);
        System.out.printf("[ИНФОРМАЦИЯ]: произведена проверка доступности кнопки [%s].%n", buttonName);
    }
}
