package ru.PageObjects.Customer.Protocol;

import Helpers.Dictionary;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывающий страницу [Уведомление об итогах рассмотрения заявок предварительного отбора]
 * (customer/lk/Protocols/ProtocolForm/...&type=PrequalificationConsiderationProtocol&...).
 * Created by Kirill G. Rydzyvylo on 20.07.2020.
 * Updated by Vladimir V. Klochkov on 11.03.2021.
 */
public class PrequalificationConsiderationProtocolPage extends CommonCustomerProtocolPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // region Раздел [Страница целиком]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок страницы [Уведомление об итогах рассмотрения заявок предварительного отбора]
    private static final String NOTIFICATION_AND_CONSIDERATION_PROTOCOL_PAGE_HEADER_XPATH =
            "//h1[contains(., 'Уведомление об итогах рассмотрения заявок предварительного отбора')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Общие сведения о процедуре]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Общие сведения о процедуре]
    private static final String GENERAL_INFORMATION_ABOUT_PROCEDURE_HEADER_XPATH =
            "//h2[contains(., 'Общие сведения о процедурк')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Номер процедуры (номер редакции извещения)]
    private static final String PROCEDURE_NUMBER_FIELD_XPATH =
            "//table//td[contains(.,'Номер процедуры')]/..//a[contains(@href,'/customer/lk/Auctions/View/')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Общие сведения об уведомлении]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Общие сведения об уведомлении]
    private static final String GENERAL_INFORMATION_ABOUT_NOTIFICATION_HEADER_XPATH =
            "//h2[contains(., 'Общие сведения об уведомлении')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Дата и время следующего окончания подачи заявок]
    private static final String DATE_AND_TIME_OF_NEXT_APPLICATION_DEADLINE_FIELD_ID =
            "NextPublishDate";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Предмет предварительного отбора №Х]

    //------------------------------------------------------------------------------------------------------------------
    // Заявки в разделе [Предмет предварительного отбора №X] в таблице [Сведения уведомления об итогах рассмотрения заявок]
    private static final String ROWS_IN_RESULTS_OF_CONSIDERATION_APPLICATIONS_TABLE_XPATH =
            "//div[@data-lot-order='%s']//tr[contains(@class,'applicationTR')]";
    //==================================================================================================================
    //Столбец [Решение комиссии]
    //------------------------------------------------------------------------------------------------------------------
    // Заявки в разделе [Предмет предварительного отбора №X] поле в столбце [Решение коммисии] в строке Y
    private static final String UNDER_CONSIDERATION_FIELD =
            "//div[@data-lot-order='%s']//tr[contains(@class,'applicationTR') and @applicationnumber='%d']" +
                    "/td[4]//span[@class='k-input' and contains(.,'На рассмотрении')]";
    //------------------------------------------------------------------------------------------------------------------
    // Значение [Включить в реестр] в выпадающем списке в столбце [Решение комиссии]
    private static final String INCLUDE_IN_THE_REGISTER_XPATH =
            "//ul[contains(@id,'AllowanceDecision') and @aria-hidden='false']" +
                    "/li[contains(.,'Включить в реестр')]";
    //------------------------------------------------------------------------------------------------------------------
    // Значение [Отказать во включении в реестр] в выпадающем списке в столбце [Решение комиссии]
    private static final String REFUSE_TO_INCLUDE_IN_THE_REGISTER_XPATH =
            "//ul[contains(@id,'AllowanceDecision') and @aria-hidden='false']" +
                    "/li[contains(.,'Отказать во включении в реестр')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Выбрать основание](Выбрать) в строке Y в разделе [Предмет предварительного отбора №X]
    private static final String SELECT_THE_BASIS_FIELD_XPATH =
            "//div[@data-lot-order='%s']//tr[contains(@class,'applicationTR') and @applicationnumber='%d']" +
                    "//span[@class='notAllowanceDecision']/span[@role='listbox']";
    //------------------------------------------------------------------------------------------------------------------
    // Значение [Иное] в выпадающем списке [Выбрать основание](Выбрать)
    private static final String OTHER_FOR_THE_FIELD_BASIS_XPATH =
            "//ul[@role='listbox' and @aria-hidden='false']//span[text()='Иное']";
    //==================================================================================================================
    // Кнопка [Допустить всех] в резделе [Предмет предварительного отбора №X]
    private static final String ALL_ALLOW_BUTTON_XPATH =
            "//div[@data-lot-order='%s']//span[@id='allAllowSTemplate']";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Предмет предварительного отбора №1]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Предмет предварительного отбора №1]
    private static final String PRESELECTION_SUBJECT_NUMBER_ONE_HEADER_XPATH =
            "//h2[contains(., 'Предмет предварительного отбора №1')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Предмет предварительного отбора №2]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Предмет предварительного отбора №Х]
    private static final String PRESELECTION_SUBJECT_NUMBER_TWO_HEADER_XPATH =
            "//h2[contains(., 'Предмет предварительного отбора №2')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    // region Раздел [Прикрепление уведомления об итогах рассмотрения заявок предварительного отбора]

    //------------------------------------------------------------------------------------------------------------------
    // Заголовок раздела [Прикрепление уведомления об итогах рассмотрения заявок предварительного отбора]
    private static final String ATTACHING_NOTIFICATION_ABOUT_RESULTS_CONSIDERATION_OF_PRESELECTION_APPLICATION_XPATH =
            "//h2[contains(., 'Прикрепление уведомления об итогах рассмотрения заявок предварительного отбора')]";
    //------------------------------------------------------------------------------------------------------------------
    // Поле [Прикрепить файл протокола]
    private static final String PROTOCOL_UPLOAD_ID =
            "ProtocolUpload";
    //------------------------------------------------------------------------------------------------------------------
    // Ссылка для скачивания первого из прикрепленных к извещению файлов
    private static final String DOWNLOAD_PROTOCOL_XPATH =
            "//div[@id='filesItemTemplate']//a[contains(@class,'FileName')]";
    //------------------------------------------------------------------------------------------------------------------

    // endregion

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Dictionary partitionNames = new Dictionary(); // основные разделы протокола
    private final Dictionary fieldNames = new Dictionary();     // все поля на странице

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * @param driver экземпляр WebDriver
     *****************************************************************************************************************
     */
    public PrequalificationConsiderationProtocolPage(WebDriver driver) {
        super(driver);
        //--------------------------------------------------------------------------------------------------------------
        partitionNames.add("Протокол рассмотрения первых частей заявок аукциона",
                NOTIFICATION_AND_CONSIDERATION_PROTOCOL_PAGE_HEADER_XPATH);
        partitionNames.add("Общие сведения о процедуре", GENERAL_INFORMATION_ABOUT_PROCEDURE_HEADER_XPATH);
        partitionNames.add("Общие сведения об уведомлении", GENERAL_INFORMATION_ABOUT_NOTIFICATION_HEADER_XPATH);
        partitionNames.add("Предмет предварительного отбора №1", PRESELECTION_SUBJECT_NUMBER_ONE_HEADER_XPATH);
        partitionNames.add("Предмет предварительного отбора №2", PRESELECTION_SUBJECT_NUMBER_TWO_HEADER_XPATH);
        partitionNames.add("Прикрепление уведомления об итогах рассмотрения заявок предварительного отбора",
                ATTACHING_NOTIFICATION_ABOUT_RESULTS_CONSIDERATION_OF_PRESELECTION_APPLICATION_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.add("Дата и время следующего окончания подачи заявок",
                DATE_AND_TIME_OF_NEXT_APPLICATION_DEADLINE_FIELD_ID);
        fieldNames.add("Номер процедуры", PROCEDURE_NUMBER_FIELD_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }
    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    // region Раздел [Общие сведения о процедуре]

    // endregion

    // region Раздел [Общие сведения об уведомлении]

    // endregion

    // region Раздел [Предмет предварительного отбора №Х]

    /**
     * Проверяет количество записей в таблице [Сведения уведомления об итогах рассмотрения заявок] в разделе
     * [Предмет предварительного отбора №Х].
     *
     * @param preselectionObjectNumber номер раздела [Предмет предварительного отбора №Х]
     * @param numberOfRows             количесвто заявок в таблице [Сведения уведомления об итогах рассмотрения заявок]
     */
    public void checkNumberOfRowsFromResultsOfConsiderationApplicationTable(
            String preselectionObjectNumber, int numberOfRows) {
        String rowsPath =
                String.format(ROWS_IN_RESULTS_OF_CONSIDERATION_APPLICATIONS_TABLE_XPATH, preselectionObjectNumber);
        String informationMessage = "[ИНФОРМАЦИЯ] таблица [Сведения уведомления об итогах рассмотрения заявок] " +
                "в разделе [Предмет предварительного отбора №%s] содержит [%d] заявок.";

        int rows = $$(this.getBy(rowsPath)).size();
        System.out.printf((informationMessage) + "%n", preselectionObjectNumber, numberOfRows);
        Assert.assertEquals(
                "[ОШИБКА]: количество записей в списке приглашенных участников неверно", numberOfRows, rows);
    }

    /**
     * Включает заявку в реестр в таблице [Сведения уведомления об итогах рассмотрения заявок] в разделе
     * [Предмет предварительного отбора №Х].
     *
     * @param preselectionObjectNumber номер раздела [Предмет предварительного отбора №Х]
     * @param rowNumber                количесвто заявок в таблице [Сведения уведомления об итогах рассмотрения заявок]
     */
    public void includeRequestFromResultsOfConsiderationApplicationTable(
            String preselectionObjectNumber, int rowNumber) throws Exception {
        System.out.printf(
                "[ИНФОРМАЦИЯ]: в разделе [Предмет предварительного отбора №%s] нажимает на поле [На рассмотрении] " +
                        "в строке [%d] в столбце [Решение комиссии]%n", preselectionObjectNumber, rowNumber);

        String underConsiderationFieldXPath = String.format(UNDER_CONSIDERATION_FIELD, preselectionObjectNumber, rowNumber);
        System.out.printf("[ИНФОРМАЦИЯ]: для нажатия на поле [На рассмотрении] сформирован локатор [%s]%n",
                underConsiderationFieldXPath);

        SelenideElement underConsiderationField = $(this.getBy(underConsiderationFieldXPath));
        SelenideElement includeValue = $(this.getBy(INCLUDE_IN_THE_REGISTER_XPATH));

        System.out.println("[ИНФОРМАЦИЯ]: в выпадающем списке выбираем значенеи [Включить в реестр]");
        this.waitForListOpens(underConsiderationField, includeValue);
        includeValue.waitUntil(visible, timeout, polling).click();
    }

    /**
     * Отклоняет заявку в таблице [Сведения уведомления об итогах рассмотрения заявок] в разделе
     * [Предмет предварительного отбора №Х].
     *
     * @param preselectionObjectNumber номер раздела [Предмет предварительного отбора №Х]
     * @param rowNumber                количесвто заявок в таблице [Сведения уведомления об итогах рассмотрения заявок]
     */
    public void rejectRequestFromResultsOfConsiderationApplicationTable(String preselectionObjectNumber, int rowNumber)
            throws Exception {
        System.out.printf(
                "[ИНФОРМАЦИЯ]: в разделе [Предмет предварительного отбора №%s] нажимает на поле [На рассмотрении] " +
                        "в строке [%d] в столбце [Решение комиссии]%n", preselectionObjectNumber, rowNumber);

        String underConsiderationFieldXPath = String.format(UNDER_CONSIDERATION_FIELD, preselectionObjectNumber,
                rowNumber);
        System.out.printf("[ИНФОРМАЦИЯ]: для нажатия на поле [На рассмотрении] сформирован локатор [%s]%n",
                underConsiderationFieldXPath);

        SelenideElement underConsiderationField = $(this.getBy(underConsiderationFieldXPath));
        SelenideElement refuseToIncludeValue = $(this.getBy(REFUSE_TO_INCLUDE_IN_THE_REGISTER_XPATH));

        System.out.println("[ИНФОРМАЦИЯ]: в выпадающем списке выбираем значенеи [Отказать во включении в реестр]");
        this.waitForListOpens(underConsiderationField, refuseToIncludeValue);
        refuseToIncludeValue.waitUntil(visible, timeout, polling).click();

        String selectTheBasis = String.format(SELECT_THE_BASIS_FIELD_XPATH, preselectionObjectNumber, rowNumber);
        System.out.printf("[ИНФОРМАЦИЯ]: для нажатия на поле [Выбрать] сформирован локатор [%s]%n", selectTheBasis);
        System.out.println("[ИНФОРМАЦИЯ]: нажимаем на поле [Выбрать]");
        $(this.getBy(selectTheBasis)).waitUntil(visible, timeout, polling).click();

        System.out.println("[ИНФОРМАЦИЯ]: выбирается значение [Иное] в выпадающем списке в поле [Выбрать]");
        $(this.getBy(OTHER_FOR_THE_FIELD_BASIS_XPATH)).waitUntil(visible, timeout, polling).click();
    }

    /**
     * Делает щелчок мышью по кнопке [Допустить всех] в разделе [Предмет предварительного отбора №Х].
     *
     * @param preselectionObjectNumber номер раздела [Предмет предварительного отбора №Х]
     */
    public void clickOnAllAllowButton(String preselectionObjectNumber) throws Exception {
        this.logButtonNameToPress("Допустить всех");
        String buttonXpath = String.format(ALL_ALLOW_BUTTON_XPATH, preselectionObjectNumber);
        System.out.printf(
                "[ИНФОРМАЦИЯ]: для нажатия на кнопку [Допустить всех] в разделе [Предмет предварительного отбора №%s] " +
                        "сформирован локатор [%s]%n", preselectionObjectNumber, buttonXpath);
        this.logButtonNameToPress("Допустить всех");
        this.scrollToCenterAndclickInElementJS(By.xpath(buttonXpath));
        this.logPressedButtonName("Допустить всех");
        TimeUnit.SECONDS.sleep(3);
    }

    // endregion

    // region Раздел [Прикрепление уведомления об итогах рассмотрения заявок предварительного отбора]

    /**
     * Прикрепляет файл в разделе [Прикрепление уведомления об итогах рассмотрения заявок предварительного отбора].
     *
     * @param fileName имя файла
     */
    public void uploadFileIntoPartitionOfTheNotificationAboutResultsConsiderationOfPreselection(String fileName) {
        System.out.printf("[ИНФОРМАЦИЯ]: прикрепляем файл [%s] в " +
                "разделе [Прикрепление уведомления об итогах рассмотрения заявок предварительного отбора].%n", fileName);
        $(this.getBy(PROTOCOL_UPLOAD_ID)).waitUntil(exist, timeout, polling).sendKeys(fileName);
    }

    /**
     * Проверяет ссылку для скачивания прикрепленного файла в разделе [Документы].
     *
     * @param fileName имя файла
     */
    public void checkLinkToDownloadFileFromPartitionOfTheNotificationAboutResultsConsiderationOfPreselection(
            String fileName) throws Exception {
        System.out.printf("[ИНФОРМАЦИЯ]: проверяется ссылка для скачивания прикрепленного файла [%s] в " +
                        "разделе [Прикрепление уведомления об итогах рассмотрения заявок предварительного отбора].%n",
                fileName);
        this.getCurrentServerVersion();
        $(this.getBy(DOWNLOAD_PROTOCOL_XPATH)).waitUntil(visible, timeout, polling).shouldHave(text(fileName));
    }

    // endregion

    // region Общие методы для работы с элементами этой страницы

    /**
     * Переходит к просмотру раздела протокола с указанным именем.
     *
     * @param partitionName имя раздела
     */
    public void goToPartition(String partitionName) throws Exception {
        SelenideElement partition = $(this.getBy(partitionNames.find(partitionName)));
        this.scrollToCenter(partition);
        partition.waitUntil(clickable, timeout, polling).click();
        System.out.printf("[ИНФОРМАЦИЯ]: произведен переход к просмотру раздела: [%s].%n", partitionName);
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * Устанавливает значение полей с предварительной их очисткой.
     *
     * @param fieldName имя поля
     * @param value     требуемое значение поля
     */
    public void setFieldClearClickAndSendKeys(String fieldName, String value) throws Exception {
        this.waitClearClickAndSendKeys(this.getBy(fieldNames.find(fieldName)), value);
        System.out.printf("[ИНФОРМАЦИЯ]: произведено заполнение поля [%s] значением [%s].%n", fieldName, value);
    }

    /**
     * Производит нажатие на поле с указанныи именем.
     *
     * @param fieldName имя поля
     */
    public void clickOnField(String fieldName) throws Exception {
        this.scrollToCenterAndclickInElementJS(fieldNames.find(fieldName));
        System.out.printf("[ИНФОРМАЦИЯ]: произведено нажатие на поле [%s].%n", fieldName);
    }

    // endregion
}
