package Helpers.RzhdMaps;

import Helpers.ConfigContainer;
import Helpers.Timer;

import java.util.HashMap;
import java.util.UUID;

/**
 * Класс-контейнер для хранения информации о наборе параметров для файла-шаблона ZKTEMSP.xml
 * ( обновление извещения об обычном запросе котировок МСП ).
 * Created by Vladimir V. Klochkov on 17.04.2019.
 * Updated by Kirill G. Rydzyvylo on 04.09.2020.
 */
public class RzhdSMBRFQUpdate {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final ConfigContainer config = ConfigContainer.getInstance(); // экземпляр ConfigContainer
    private final Timer timer = new Timer();                              // класс для работы со временными интервалами

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    /**
     * Возвращает набор параметров для файла-шаблона ZKTEMSP.xml.
     *
     * @return набор параметров для файла-шаблона ZKTEMSP.xml
     */
    public HashMap<String, String> getParams() {
        return new HashMap<String, String>() {{
            //
            // GUID
            //
            config.setParameter("RzhdRandomUUID", UUID.randomUUID().toString());
            put("guid", config.getParameter("RzhdRandomUUID"));
            put("ns2:guid", config.getParameter("RzhdRandomUUID"));
            //
            // Дата создания файла XML
            //
            put("createDateTime", timer.dateToResponseFormatWithSeconds(Timer.getPublishNoticeDate(1)));
            //
            // Дата создания извещения о закупке ( хранится в статическом поле класса Timer )
            //
            put("ns2:createDateTime", timer.dateToResponseFormatWithSeconds(Timer.getPublishNoticeDate(1)));
            //
            // Наименование закупки
            //
            put("ns2:name", config.getConfigParameter("RzhdPurchaseNameForRequestForQuotationsUpdate"));
            //
            // Полное наименование организации
            //
            put("fullName", config.getConfigParameter("RzhdFullName"));
            //
            // Наименование организации
            //
            put("shortName", config.getConfigParameter("RzhdShortName"));
            //
            // ИНН организации
            //
            put("inn", config.getConfigParameter("RzhdINN"));
            //
            // КПП организации
            //
            put("kpp", config.getConfigParameter("RzhdKPP"));
            //
            // ОГРН организации
            //
            put("ogrn", config.getConfigParameter("RzhdOGRN"));
            //
            // Юридический адрес организации
            //
            put("legalAddress", config.getConfigParameter("RzhdLegalAddress"));
            //
            // Почтовый адрес организации
            //
            put("postalAddress", config.getConfigParameter("RzhdPostalAddress"));
            //
            // Ответственное должностное лицо
            //
            put("lastName", config.getConfigParameter("RzhdResponciblePersonUpdate"));
            //
            // Номер телефона организации
            //
            put("phone", config.getConfigParameter("RzhdPhoneUpdate"));
            //
            // Факс организации
            //
            put("fax", config.getConfigParameter("RzhdFaxUpdate"));
            //
            // Основной е-mail для уведомлений организации
            //
            put("email", config.getConfigParameter("RzhdEmailUpdate"));
            //
            // Дата модификации прикрепленного документа
            //
            put("ns2:modificationDate", timer.dateToResponseFormatWithSeconds(Timer.getPublishNoticeDate(1)));
            //
            // Дата подачи заявок (МСК)
            //
            put("ns2:applSubmisionStartDate", timer.dateOnlyToResponseFormat(Timer.getPublishNoticeDate()));
            //
            // Дата рассмотрения заявок
            //
            put("//integrCode[contains(., 'examinationDateTime')]/../value/date",
                    timer.dateOnlyToResponseFormat(Timer.getPublishNoticeDate(), 2));
            //
            // Дата подведения итогов
            //
            put("ns2:summingupDateTime",
                    timer.dateToResponseFormatWithSeconds(Timer.getPublishNoticeDate(1), 3));
            //
            // Дата и время окончания подачи заявок (МСК)
            //
            put("ns2:submissionCloseDateTime",
                    timer.dateToResponseFormatWithSeconds(Timer.getPublishNoticeDate(1), 1));
            //
            // Не отображается на форме черновика извещения ( игнорируется )
            //
            put("ns2:publicationPlannedDate", timer.dateOnlyToResponseFormat(Timer.getPublishNoticeDate()));
            //
            // Рабочая группа организации
            //
            put("//integrCode[contains(., 'etzpOrganizerCode')]/../value/string",
                    config.getConfigParameter("RzhdEtzpOrganizerCode"));
            //
            // Уникальный номер закупки от РЖД
            //
            put("//integrCode[contains(., 'etzpNoticeNumber')]/../value/string",
                    String.format("Rzhd SMB - Request For Quotations Usual %s",
                            timer.dateToResponseFormatWithSeconds(Timer.getPublishNoticeDate()).
                                    replace("T", " ")));
            //
            // Порядок формирования начальной цены
            //
            put("//integrCode[contains(., 'etzpPricingCondition')]/../value/string",
                    config.getConfigParameter("RzhdEtzpPricingConditionUpdate"));
            //
            // Начальная (максимальная) цена ( вкладка лота [Общие сведения] )
            //
            put("initialSum", config.getConfigParameter("RzhdEtzpInitialSumUpdate"));
            //
            // Срок исполнения договора
            //
            put("//integrCode[contains(., 'ContractEndDate')]/../value/string",
                    config.getConfigParameter("RzhdEtzpContractEndDateUpdate"));
            //
            // Вид обеспечения заявки
            //
            put("//integrCode[contains(., 'ProvisionBidType')]/../value/integer",
                    config.getConfigParameter("RzhdProvisionBidType1"));
            //
            // Иные требования к обеспечению заявки
            //
            put("//integrCode[contains(., 'ProvisionRequirements')]/../value/string",
                    config.getConfigParameter("RzhdProvisionRequirementsUpdate"));
            //
            // Обеспечение заявки
            //
            put("//integrCode[contains(., 'Deposit')]/../value/number", config.getConfigParameter("RzhdDepositUpdate"));
            //
            // Вид обеспечения договора
            //
            put("//integrCode[contains(., 'ProvisionContractType')]/../value/integer",
                    config.getConfigParameter("RzhdProvisionContractType6"));
        }};
    }
}
