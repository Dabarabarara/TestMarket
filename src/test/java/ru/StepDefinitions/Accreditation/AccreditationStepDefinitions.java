package ru.StepDefinitions.Accreditation;

import Helpers.CertificateGenerator;
import Helpers.Dictionary;
import Helpers.UserProfiles.IUserProfile;
import Helpers.UserProfiles.UserProfileFactory;
import LogicLayer.CertificateSelectors.ICertificateSelector;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import ru.PageObjects.Accreditation.*;
import ru.PageObjects.Admin.AccreditationViewPage;
import ru.PageObjects.Authorization.SupplierOrOperatorrLogInPage;
import ru.PageObjects.FakeSMTP.FakeSMTPPage;
import ru.StepDefinitions.AbstractStepDefinitions;

import static com.codeborne.selenide.Selenide.open;

/**
 * Класс описывающий шаги работы площадки с функционалом Аккредитация.
 * Created by Evgeniy Glushko on 18.04.2016.
 * Updated by Vladimir V. Klochkov on 27.04.2021.
 */
public class AccreditationStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private IUserProfile up;                              // профиль Пользователя
    private final Dictionary orgTypes = new Dictionary(); // словарь кодов типов организаций
    private final FakeSMTPPage fakeSMTPPage = new FakeSMTPPage(driver);
    private final AccreditationPage accreditationPage = new AccreditationPage(driver);
    private final PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
    private final AccreditationViewPage accreditationViewPage = new AccreditationViewPage(driver);
    private final SupplierOrOperatorrLogInPage supplierOrOperatorrLogInPage = new SupplierOrOperatorrLogInPage(driver);

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     ******************************************************************************************************************/
    public AccreditationStepDefinitions() {
        // Формируем словарь кодов типов организаций для генератора сертификатов ---------------------------------------
        orgTypes.add("Юридическое лицо", "Jp");
        orgTypes.add("Индивидуальный предприниматель", "Ip");
        orgTypes.add("Физическое лицо", "Php");
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    @And("^'Пользователь' открывает страницу Аккредитации$")
    public void userGoesToAccreditationPage() {
        String stepName = "'Пользователь' открывает страницу Аккредитации";
        this.logStepName(stepName);

        timer.start();

        open(config.getAccreditationLoginUrl());

        timer.printStepTotalTime(stepName);
    }

    @And("^'Пользователь' открывает страницу Восстановления пароля$")
    public void userGoesToPasswordRecoveryPage() {
        String stepName = "'Пользователь' открывает страницу Восстановления пароля";
        this.logStepName(stepName);

        timer.start();

        open(config.getPasswordRecoveryUrl());

        timer.printStepTotalTime(stepName);
    }

    @When("^'Пользователь' открывает страницу перехвата e-mail сообщений$")
    public void userGoesToFakeSmtPPage() throws Exception {
        String stepName = "'Пользователь' открывает страницу перехвата e-mail сообщений";
        this.logStepName(stepName);

        timer.start();

        fakeSMTPPage.gotoFakeSMTPPage(config.getConfigParameter("FakeSMTP"));

        timer.printStepTotalTime(stepName);
    }

    // TODO Старая логика, требуется рефакторинг в дальнейшем
    @When("^'Пользователь' выбирает тип Аккредитации \"([^\"]*)\"$")
    public void userSelectsAccreditationType(String accreditationUserType) throws Throwable {
        String stepName = "'Пользователь' выбирает тип аккредитации: '" + accreditationUserType + "'";
        this.logStepName(stepName);

        timer.start();

        String pref = ">>> (userSelectAccreditationType) "; // Префикс для сообщений в консоли

        // Если диск с сертификатами для 44-го проекта смонтирован, отмонтируем его
        CertificateGenerator.getInstance().unMountDisk();

        // Получаем профиль аккредитации
        up = UserProfileFactory.getInstance().getProfile(accreditationUserType);

        // Парсим параметр на три составных части - тип аккредитуемого Пользователя, фамилия, имя
        String userType = accreditationUserType.split(" ")[0];
        String lastName = accreditationUserType.split(" ")[1];
        String firstName = accreditationUserType.split(" ")[2];

        // Получаем код типа организации по ключу [фамилия имя]
        String orgType = orgTypes.find(lastName + " " + firstName);
        System.out.println(pref + "Тип организации (orgType): [" + orgType + "].");

        // Сохраняем имя генерируемого сертификата в параметрах теста для его дальнейшего использования
        String certificateName = lastName + " " + firstName + timer.getCurrentDateTime("ddMMyyyyHHmmSS")
                + " " + userType;
        config.setParameter("CertificateName", certificateName);
        config.setParameter("CertName", certificateName);
        config.setParameter("UserLastName", lastName);
        config.setParameter("AccreditationUserType", accreditationUserType);
        System.out.println(pref + "Имя сертификата (Certificate Name): [" + certificateName + "].");

        // Генерируем сертификат с заданным именем
        CertificateGenerator.getInstance().generateCertificate(certificateName, orgType);

        // Выбираем тип аккредитации на форме [Первичная аккредитация]
        up.getLogic(ICertificateSelector.class).selectAccreditationType();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' устанавливает флажок {string} на странице 'Заявка на аккредитацию'")
    public void userSetsCheckBoxInAccreditationPage(String checkBoxName) {
        String stepName = String.format(
                "'Пользователь' устанавливает флажок '%s' на странице 'Заявка на аккредитацию'", checkBoxName);
        this.logStepName(stepName);

        timer.start();

        accreditationPage.verifyCheckBoxNotSelected(checkBoxName);
        accreditationPage.setCheckBoxValue(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' сбрасывает флажок {string} на странице аккредитации")
    public void userSetsOffCheckBoxInAccreditationPage(String checkBoxName) {
        String stepName = String.format("'Пользователь' сбрасывает флажок '%s' на странице аккредитации", checkBoxName);
        this.logStepName(stepName);

        timer.start();

        accreditationPage.verifyCheckBoxSelected(checkBoxName);
        accreditationPage.setCheckBoxValue(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' проверяет, что флажок {string} установлен на странице аккредитации")
    public void userVerifiesCheckBoxSelected(String checkBoxName) {
        String stepName = String.format("'Пользователь' проверяет, что флажок '%s'" +
                " установлен на странице аккредитации", checkBoxName);
        this.logStepName(stepName);

        timer.start();

        accreditationPage.verifyCheckBoxSelected(checkBoxName);

        timer.printStepTotalTime(stepName);
    }

    // TODO Старая логика, требуется рефакторинг в дальнейшем
    @And("^'Пользователь' открывает окно выбора сертификата$")
    public void userOpensCertSelectionWindow() throws Throwable {
        String stepName = "'Пользователь' открывает окно выбора сертификата";
        this.logStepName(stepName);

        timer.start();

        up.getLogic(ICertificateSelector.class).openCertificateWindow(driver);

        timer.printStepTotalTime(stepName);
    }

    // TODO Старая логика, требуется рефакторинг в дальнейшем
    @And("^'Пользователь' выбирает сертификат$")
    public void userSelectsCert() throws Throwable {
        String stepName = "'Пользователь' выбирает сертификат";
        this.logStepName(stepName);

        timer.start();

        up.getLogic(ICertificateSelector.class).selectCertificate(driver);

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Номер телефона лица, подписавшего заявку' на странице 'Заявка на аккредитацию'")
    public void userFillsPhoneNumberTextFieldInRequestAccreditationPage() {
        String stepName = "'Пользователь' заполняет поле 'Номер телефона лица, подписавшего заявку' " +
                "на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setPhoneNumberFieldUserPersonalData();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Адрес электронной почты' на странице 'Заявка на аккредитацию'")
    public void userFillsEmailTextFieldInRequestAccreditationPage() {
        String stepName =
                "'Пользователь' заполняет поле 'Адрес электронной почты' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        config.setParameter("ContactEmail",
                "ContactEmail" + timer.getCurrentDateTime("ddMMyyyyHHmmSS") + "@example.com");
        accreditationPage.setField("Адрес электронной почты", config.getParameter("ContactEmail"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Имя пользователя - логин' на странице 'Заявка на аккредитацию'")
    public void userFillsLoginTextFieldInRequestAccreditationPage() {
        String stepName =
                "'Пользователь' заполняет поле 'Имя пользователя - логин' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        config.setParameter("AccreditationUserLogin",
                "login" + timer.getCurrentDateTime("ddMMyyyyHHmmSS"));
        accreditationPage.setField("Имя пользователя (логин)", config.getParameter("AccreditationUserLogin"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Пароль' на странице 'Заявка на аккредитацию'")
    public void userFillsPasswordTextFieldInRequestAccreditationPage() {
        String stepName =
                "'Пользователь' заполняет поле 'Пароль' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setField("Пароль", config.getConfigParameter("UserPass"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Подтверждение пароля' на странице 'Заявка на аккредитацию'")
    public void userFillsConfirmPasswordTextFieldInRequestAccreditationPage() {
        String stepName =
                "'Пользователь' заполняет поле 'Подтверждение пароля' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setField("Подтверждение пароля", config.getConfigParameter("UserPass"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Кодовое слово' на странице 'Заявка на аккредитацию'")
    public void userFillsCodeWordTextFieldInRequestAccreditationPage() {
        String stepName =
                "'Пользователь' заполняет поле 'Кодовое слово' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setField("Кодовое слово", config.getConfigParameter("UserSecWord"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' устанавливает переключатель {string} на странице 'Заявка на аккредитацию'")
    public void userClicksRadioButtonInRequestAccreditationPage(String radioButtonName) throws Throwable {
        String stepName =
                String.format("'Пользователь' устанавливает переключатель '%s' на странице 'Заявка на аккредитацию'",
                        radioButtonName);
        this.logStepName(stepName);

        timer.start();

        accreditationPage.clickRadioButtonInRequestAccreditationPage(radioButtonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' сохраняет в параметрах теста ИНН на странице 'Заявка на аккредитацию'")
    public void userSavesInn() {
        String stepName = "'Пользователь' сохраняет в параметрах теста ИНН на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.getInnValue();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' сохраняет в параметрах теста КПП на странице 'Заявка на аккредитацию'")
    public void userSavesKpp() {
        String stepName = "'Пользователь' сохраняет в параметрах теста КПП на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.getKppValue();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' сохраняет в параметрах теста ОГРН на странице 'Заявка на аккредитацию'")
    public void userSavesOgrn() {
        String stepName = "'Пользователь' сохраняет в параметрах теста ОГРН на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.getOgrnValue();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' сохраняет в параметрах теста ОГРНИП на странице 'Заявка на аккредитацию'")
    public void userSavesOgrnIp() {
        String stepName = "'Пользователь' сохраняет в параметрах теста ОГРНИП на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.getOgrnIpValue();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поля 'Номер телефона' на странице 'Заявка на аккредитацию'")
    public void userSetsPhoneNumberFieldApplicant() {
        String stepName = "'Пользователь' заполняет поля 'Номер телефона' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setPhoneNumberFieldApplicant();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Основной е-mail для уведомлений' на странице 'Заявка на аккредитацию'")
    public void userFillsApplicantEmailTextFieldInRequestAccreditationPage() {
        String stepName =
                "'Пользователь' заполняет поле 'Основной е-mail для уведомлений' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        config.setParameter("UserEmail",
                "UserEmail" + timer.getCurrentDateTime("ddMMyyyyHHmmSS") + "@example.com");
        accreditationPage.setField("Основной е-mail для уведомлений", config.getParameter("UserEmail"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'ФИО руководителя организации' на странице 'Заявка на аккредитацию'")
    public void userSetsOrganizationDirectorFieldInRequestAccreditationPage() {
        String stepName = "'Пользователь' заполняет поле 'ФИО руководителя организации' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setOrganizationDirectorField();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Контактное лицо' на странице 'Заявка на аккредитацию'")
    public void userSetsContactPersonFieldInRequestAccreditationPage() {
        String stepName = "'Пользователь' заполняет поле 'Контактное лицо' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setContactPersonField();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Регион' на странице 'Заявка на аккредитацию'")
    public void userSetsRegionInRequestAccreditationPage() throws Throwable {
        String stepName = "'Пользователь' заполняет поле 'Регион' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setRegionList();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Район' на странице 'Заявка на аккредитацию'")
    public void userSetsDistrictInRequestAccreditationPage() throws Throwable {
        String stepName = "'Пользователь' заполняет поле 'Район' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setDistrictList();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Город' на странице 'Заявка на аккредитацию'")
    public void userSetsCityInRequestAccreditationPage() throws Throwable {
        String stepName = "'Пользователь' заполняет поле 'Город' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setCityList();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Населённый пункт' на странице 'Заявка на аккредитацию'")
    public void userSetsLocalityInRequestAccreditationPage() throws Throwable {
        String stepName = "'Пользователь' заполняет поле 'Населённый пункт' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setLocalityList();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Улица' на странице 'Заявка на аккредитацию'")
    public void userSetsStreetInRequestAccreditationPage() throws Throwable {
        String stepName = "'Пользователь' заполняет поле 'Улица' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setStreetField();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Почтовый индекс' на странице 'Заявка на аккредитацию'")
    public void userSetsPostIndexInRequestAccreditationPage() throws Throwable {
        String stepName = "'Пользователь' заполняет поле 'Почтовый индекс' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setPostIndexField();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Дом - строение - корпус' на странице 'Заявка на аккредитацию'")
    public void userSetsBuildingInRequestAccreditationPage() {
        String stepName = "'Пользователь' заполняет поле 'Дом - строение - корпус'" +
                " на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setBuildingField();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' устанавливает значение в поле 'Номенклатура ОКВЭД2' на странице 'Заявка на аккредитацию'")
    public void userFillsInformationOnTypesOfEconomicActivitiesInRequestAccreditationPage() throws Throwable {
        String stepName = "'Пользователь' устанавливает значение в поле 'Номенклатура ОКВЭД2'" +
                " на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setOrganizationOkved2Field();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Серия' на странице 'Заявка на аккредитацию'")
    public void userSetsPasswordSerialInRequestAccreditationPage() {
        String stepName = "'Пользователь' заполняет поле 'Серия' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setField("Серия", config.getConfigParameter("UserPassportSerial"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Номер' на странице 'Заявка на аккредитацию'")
    public void userSetsPasswordNumberInRequestAccreditationPage() {
        String stepName = "'Пользователь' заполняет поле 'Номер' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setField("Номер", config.getConfigParameter("UserPassportNumber"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Код подразделения' на странице 'Заявка на аккредитацию'")
    public void userSetsPasswordIssuerDivisionCodInRequestAccreditationPage() throws Throwable {
        String stepName = "'Пользователь' заполняет поле 'Код подразделения' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setPasswordIssuerDivisionCod();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Когда выдан' на странице 'Заявка на аккредитацию'")
    public void userSetsPasswordIssueDateInRequestAccreditationPage() throws Throwable {
        String stepName = "'Пользователь' заполняет поле 'Когда выдан' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setPasswordIssueDate();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Кем выдан' на странице 'Заявка на аккредитацию'")
    public void userSetsPasswordIssueDivisionInRequestAccreditationPage() {
        String stepName = "'Пользователь' заполняет поле 'Кем выдан' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setField("Кем выдан", config.getConfigParameter("UserPassportIssuer"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' загружает данные в таб '0-0 Учредительные документы' на странице 'Заявка на аккредитацию'")
    public void userUploadsFoundationDocumentInRequestAccreditationPage() throws Throwable {
        String stepName = "'Пользователь' загружает данные в таб '0-0 Учредительные документы'" +
                " на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.uploadFoundationDocument();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' загружает данные в таб '0-0 Полномочия Руководителя' на странице 'Заявка на аккредитацию'")
    public void userUploadsDirectorAuthorityInRequestAccreditationPage() throws Throwable {
        String stepName = "'Пользователь' загружает данные в таб '0-0 Полномочия Руководителя'" +
                " на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.uploadDirectorAuthorityDocument();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' загружает данные в таб '0-0 Выписка ЕГРЮЛ' на странице 'Заявка на аккредитацию'")
    public void userUploadsEGRJLInRequestAccreditationPage() throws Throwable {
        String stepName = "'Пользователь' загружает данные в таб '0-0 Выписка ЕГРЮЛ'" +
                " на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.uploadEGRJLDocument();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' загружает данные в таб '0-0 Полномочия на получение аккредитации' на странице 'Заявка на аккредитацию'")
    public void userUploadsAccreditationRequestAuthorityInRequestAccreditationPage() throws Throwable {
        String stepName = "'Пользователь' загружает данные в таб '0-0 Полномочия на получение аккредитации'" +
                " на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.uploadAccreditationRequestAuthorityDocument();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' загружает данные в таб '0-0 Доверенность' на странице 'Заявка на аккредитацию'")
    public void userUploadsAttorneyLetterInRequestAccreditationPage() throws Throwable {
        String stepName = "'Пользователь' загружает данные в таб '0-0 Доверенность' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.uploadAttorneyLetterDocument();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' загружает данные в таб '0-0 Документ, удостоверяющий личность' на странице 'Заявка на аккредитацию'")
    public void userUploadsIdentityDocumentInRequestAccreditationPage() throws Throwable {
        String stepName = "'Пользователь' загружает данные в таб '0/0 Документ, удостоверяющий личность'" +
                " на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.uploadIdentityDocumentForEntrepreneur();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' загружает данные в таб '0-0 Выписка из ЕГРИП' на странице 'Заявка на аккредитацию'")
    public void userUploadsEGRIPDocumentInRequestAccreditationPage() throws Throwable {
        String stepName = "'Пользователь' загружает данные в таб '0-0 Выписка из ЕГРИП'" +
                " на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.uploadEGRIPDocument();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' загружает данные в таб '0-0 Полномочия пользователя' на странице 'Заявка на аккредитацию'")
    public void userUploadsUserAuthorityDocumentInRequestAccreditationPage() throws Throwable {
        String stepName = "'Пользователь' загружает данные в таб '0/0 Полномочия пользователя'" +
                " на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.uploadUserAuthorityDocument();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' загружает данные в таб '0-0 Документ, удостоверяющий личность' для 'ФЛ' на странице 'Заявка на аккредитацию'")
    public void userUploadsIdentityDocumentForIndividualProfileInRequestAccreditationPage() throws Throwable {
        String stepName = "'Пользователь' загружает данные в таб '0/0 Документ, удостоверяющий личность'" +
                " дя 'ФЛ' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.uploadIdentityDocumentForIndividualProfile();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'БИК' на странице 'Заявка на аккредитацию'")
    public void userSetsBankBikInRequestAccreditationPage() {
        String stepName = "'Пользователь' заполняет поле 'БИК' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setBankBik();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Расчетный счет' на странице 'Заявка на аккредитацию'")
    public void userSetsBankAccountNumberInRequestAccreditationPage() {
        String stepName = "'Пользователь' заполняет поле 'Расчетный счет' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setBankAccountNumber();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Название банка' на странице 'Заявка на аккредитацию'")
    public void userSetsBankNameInRequestAccreditationPage() {
        String stepName = "'Пользователь' заполняет поле 'Название банка' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setBankName();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Адрес банка' на странице 'Заявка на аккредитацию'")
    public void userSetsBankAddressInRequestAccreditationPage() {
        String stepName = "'Пользователь' заполняет поле 'Адрес банка' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setBankAddress();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Ключ защиты' на странице 'Заявка на аккредитацию'")
    public void userSetsCaptchaFieldInRequestAccreditationPage() {
        String stepName = "'Пользователь' заполняет поле 'Ключ защиты' на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.setCaptchaField();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' нажимает на кнопку 'Подать заявку на аккредитацию' на странице 'Заявка на аккредитацию'")
    public void userClicksOnSubmitRequestButtonInRequestAccreditationPage() throws Exception {
        String stepName = "'Пользователь' нажимает на кнопку 'Подать заявку на аккредитацию'" +
                " на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.clickOnSubmitRequestButton();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' сохраняет в параметрах теста 'Наименование' на странице 'Карточка заявки на аккредитацию'")
    public void userGetsAccreditationName() throws Throwable {
        String stepName = "'Пользователь' сохраняет в параметрах теста 'Наименование'" +
                " на странице 'Карточка заявки на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.getAccreditationName();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' генерирует новый сертификат для организации")
    public void userGeneratesNewCertificateForOrganization() {
        String stepName = "'Пользователь' генерирует новый сертификат для организации";

        timer.start();
        String certificateName = config.getParameter("CertificateName");
        String firstName = certificateName.split(" ")[2];
        String lastName = certificateName.split(" ")[1];

        CertificateGenerator.getInstance().generateCertificate(
                config.getParameter("CertificateName"),
                "Ip",
                config.getParameter("AccreditationInnValue"),
                "random",
                config.getParameter("AccreditationOgrnIpValue"),
                firstName,
                lastName);

        //--------------------------------------------------------------------------------------------------------------
        //перезаписываем параметр CertName для работы с новым сгенерированным сертификатом
        config.setParameter("OldCertificateHash",
                config.getParameter("CertificateHash"));//сохраняем старый параметр Хэш
        //--------------------------------------------------------------------------------------------------------------

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' проверят наличие информационного текста {string} на странице 'Заявка на аккредитацию'")
    public void userChecksBoostedAccreditationTextInAccreditationPage(String value) {
        String stepName = String.format(
                "'Пользователь' проверят наличие информационного текста '%s' на странице 'Заявка на аккредитацию'",
                value);
        this.logStepName(stepName);

        timer.start();

        accreditationPage.verifyField("Поле с информацией по ускоренной аккредитации для поставщика", value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' проверяет наличие заголовка раздела 'Ускоренная аккредитация' на странице 'Заявка на аккредитацию'")
    public void userChecksBoostedAccreditationPartitionHeaderPresenceInAccreditationPage() {
        String stepName = "'Пользователь' проверяет наличие заголовка раздела 'Ускоренная аккредитация' " +
                "на странице 'Заявка на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationPage.
                verifyField("Заголовок раздела Ускоренная аккредитация", "Ускоренная аккредитация");

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' проверяет текст {string} раздела 'Ускоренная аккредитация' на странице 'Заявка на аккредитацию'")
    public void userChecksBoostedAccreditationPartitionTextPresenceInAccreditationViewPage(String value) {
        String stepName = String.format("'Пользователь' проверяет текст '%s' раздела 'Ускоренная аккредитация' " +
                "на странице 'Заявка на аккредитацию'", value);
        this.logStepName(stepName);

        timer.start();

        accreditationPage.verifyField("Поле с информацией по оплате ускоренной аккредитации", value);

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' проверят статус заявки с ускоренной аккредитацией на странице 'Просмотр заявки на аккредитацию'")
    public void userChecksBoostedAccreditationStatusInAccreditationViewPage() throws Exception {
        String stepName = "'Пользователь' проверят статус заявки с ускоренной аккредитацией";
        this.logStepName(stepName);

        timer.start();

        accreditationViewPage.supplierCheckRequestStatus();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' пытается скачать документ платежного поручения со страницы 'Просмотр заявки на аккредитацию'")
    public void userDownloadsConsiderationPaymentInAccreditationViewPage() throws Throwable {
        String stepName =
                "'Пользователь' пытается скачать документ платежного поручения со страницы 'Просмотр заявки на аккредитацию'";
        this.logStepName(stepName);

        timer.start();

        accreditationViewPage.downloadConsiderationPayment();

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Имя пользователя - логин' на странице 'Восстановление пароля'")
    public void userFillsLoginTextFieldOnPasswordRecoveryPage() {
        String stepName = "'Пользователь' заполняет поле 'Имя пользователя - логин' на странице 'Восстановление пароля'";
        this.logStepName(stepName);

        timer.start();

        passwordRecoveryPage.
                setField("Имя пользователя (логин)", config.getParameter("AccreditationUserLogin"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Адрес электронной почты' на странице 'Восстановление пароля'")
    public void userFillsEmailTextFieldOnPasswordRecoveryPage() {
        String stepName =
                "'Пользователь' заполняет поле 'Адрес электронной почты' на странице 'Восстановление пароля'";
        this.logStepName(stepName);

        timer.start();

        passwordRecoveryPage.setField("Адрес электронной почты", config.getParameter("ContactEmail"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Ключ защиты' на странице 'Восстановление пароля'")
    public void userSetsCaptchaFieldOnPasswordRecoveryPage() {
        String stepName = "'Пользователь' заполняет поле 'Ключ защиты' на странице 'Восстановление пароля'";
        this.logStepName(stepName);

        timer.start();

        passwordRecoveryPage.setField("Ключ защиты", config.getConfigParameter("Captcha"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' нажимает на кнопку {string} на странице 'Восстановление пароля'")
    public void userClicksOnButtonOnUserRolesPage(String buttonName) throws Throwable {
        String stepName = String.format(
                "'Пользователь' нажимает на кнопку '%s' на странице 'Восстановление пароля'", buttonName);
        this.logStepName(stepName);

        timer.start();

        passwordRecoveryPage.clickOnButton(buttonName);

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' проверяет сообщение 'На указаный Вами e-mail адрес выслано письмо с инструкицей...'")
    public void userChecksPublishProtocolMessage() {
        String stepName =
                "'Пользователь' проверяет сообщение 'На указаный Вами e-mail адрес выслано письмо с инструкицей...'";
        this.logStepName(stepName);

        timer.start();

        if (passwordRecoveryPage.isFieldOrTextDisplayed("Уведомление об отправки письма"))
            passwordRecoveryPage.verifyFieldContent("Уведомление об отправки письма",
                    "На указанный Вами e-mail адрес выслано письмо с инструкцией. Пожалуйста, подождите " +
                            "некоторое время, пока оно дойдет.");

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Пароль' на странице 'Восстановление пароля'")
    public void userFillsPasswordTextFieldOnPasswordRecoveryPage() {
        String stepName = "'Пользователь' заполняет поле 'Пароль' на странице 'Восстановление пароля'";
        this.logStepName(stepName);

        timer.start();

        config.setParameter("UserNewPass", "qwerty_" + timer.getCurrentDateTime("ddMMyyyy"));
        passwordRecoveryPage.setField("Пароль", config.getParameter("UserNewPass"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' заполняет поле 'Подтверждение пароля' на странице 'Восстановление пароля'")
    public void userFillsConfirmPasswordTextFieldOnPasswordRecoveryPage() {
        String stepName = "'Пользователь' заполняет поле 'Подтверждение пароля' на странице 'Восстановление пароля'";
        this.logStepName(stepName);

        timer.start();

        passwordRecoveryPage.setField("Подтверждение пароля", config.getParameter("UserNewPass"));

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' переходит по ссылке восстановления пароля на странице 'Fake SMTP'")
    public void userClicksOnPasswordRecoveryLinkOnFakeSMTPPage() throws Exception {
        String stepName = "'Пользователь' переходит по ссылке восстановления пароля на странице 'Fake SMTP'";
        this.logStepName(stepName);

        timer.start();

        fakeSMTPPage.clickPasswordRecoveryLink();

        timer.printStepTotalTime(stepName);
    }

    @And("'Поставщик' входит в личный кабинет, используя новый пароль")
    public void supplierLoginsWithNewPassword() throws Exception {
        String stepName = "'Пользователь' входит на площадку используя новый пароль";
        this.logStepName(stepName);

        timer.start();

        supplierOrOperatorrLogInPage.setLogin(config.getParameter("AccreditationUserLogin")).
                setPassword(config.getParameter("UserNewPass")).
                clickOnLoginButton();

        timer.printStepTotalTime(stepName);
    }
}

