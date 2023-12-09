package Helpers.RzhdMaps;

import Helpers.ConfigContainer;
import Helpers.Timer;

import java.util.HashMap;
import java.util.UUID;

/**
 * Класс-контейнер для хранения информации о наборе параметров для файла-шаблона ZKTE.xml
 * ( обновление извещения об  открытом электронном запросе котировок ).
 * Created by Vladimir V. Klochkov on 26.06.2019.
 * Updated by Vladimir V. Klochkov on 24.06.2020.
 */
public class RzhdRFQOPNUpdate {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final ConfigContainer config = ConfigContainer.getInstance(); // экземпляр ConfigContainer
    private final Timer timer = new Timer();                              // класс для работы со временными интервалами

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    /**
     * Возвращает набор параметров для файла-шаблона ZKTE.xml.
     *
     * @return набор параметров для файла-шаблона ZKTE.xml
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
            // Дата и время создания файла XML
            //
            put("createDateTime", timer.dateToResponseFormatWithSeconds(Timer.getPublishNoticeDate(1)));
            //
            // Дата и время создания извещения о закупке ( хранится в статическом поле класса Timer )
            //
            put("ns2:createDateTime", timer.dateToResponseFormatWithSeconds(Timer.getPublishNoticeDate(1)));
            //
            // Наименование закупки
            //
            put("ns2:name", config.getConfigParameter("RzhdPurchaseNameForRequestForQuotationsOPNUpdate"));
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
            // Дата и время модификации прикрепленного документа
            //
            put("ns2:modificationDate", timer.dateToResponseFormatWithSeconds(Timer.getPublishNoticeDate(1)));
            //
            // Дата начала подачи заявок
            //
            String lr = "ns2:applSubmisionStartDate";
            put(lr, timer.dateOnlyToResponseFormat(Timer.getPublishNoticeDate(1)));
            //
            // Срок предоставления документации (МСК) [ С ]
            //
            lr = "deliveryStartDateTime";
            put(lr, timer.dateOnlyToResponseFormat(Timer.getPublishNoticeDate(1), 1));
            //
            // Срок предоставления документации (МСК) [ ПО ]
            //
            lr = "deliveryEndDateTime";
            put(lr, timer.dateOnlyToResponseFormat(Timer.getPublishNoticeDate(1), 2));
            //
            // Дата и время окончания подачи заявок (МСК)
            //
            lr = "ns2:submissionCloseDateTime";
            put(lr, timer.dateToResponseFormatWithSeconds(Timer.getPublishNoticeDate(1), 3));
            //
            // Место подачи заявок
            //
            put("ns2:applSubmisionPlace", config.getConfigParameter("RzhdApplSubmisionPlaceUpdate"));
            //
            // Дата и время вскрытия заявок
            //
            lr = "//integrCode[contains(., 'VskritieData')]/../value/dateTime";
            put(lr, timer.dateToResponseFormatWithSeconds(Timer.getPublishNoticeDate(1), 4));
            //
            // Дата и время рассмотрения заявок (МСК)
            //
            lr = "ns2:examinationDateTime";
            put(lr, timer.dateToResponseFormatWithSeconds(Timer.getPublishNoticeDate(1), 4));
            lr = "//integrCode[contains(., 'examinationDateTime')]/../value/dateTime";
            put(lr, timer.dateToResponseFormatWithSeconds(Timer.getPublishNoticeDate(1), 4));
            //
            // Дата и время подведения итогов (МСК)
            //
            lr = "ns2:summingupDateTime";
            put(lr, timer.dateToResponseFormatWithSeconds(Timer.getPublishNoticeDate(1), 5));
            //
            // Не отображается на форме черновика извещения (игнорируется)
            //
            put("ns2:publicationPlannedDate", timer.dateOnlyToResponseFormat(Timer.getPublishNoticeDate()));
            //
            // Рабочая группа организации
            //
            lr = "//integrCode[contains(., 'etzpOrganizerCode')]/../value/string";
            put(lr, config.getConfigParameter("RzhdEtzpOrganizerCode"));
            //
            // Уникальный номер закупки от РЖД
            //
            lr = "//integrCode[contains(., 'etzpNoticeNumber')]/../value/string";
            put(lr, String.format("Rzhd - Request For Quotations With Open Access %s",
                    timer.dateToResponseFormatWithSeconds(Timer.getPublishNoticeDate()).
                            replace("T", " ")));
            //
            // Порядок формирования начальной цены
            //
            lr = "//integrCode[contains(., 'etzpPricingCondition')]/../value/string";
            put(lr, config.getConfigParameter("RzhdEtzpPricingConditionUpdate"));
            //
            // Начальная (максимальная) цена ( вкладка лота [Общие сведения] )
            //
            put("initialSum", config.getConfigParameter("RzhdEtzpInitialSumUpdate"));
            //
            // Срок исполнения договора
            //
            lr = "//integrCode[contains(., 'ContractEndDate')]/../value/string";
            put(lr, config.getConfigParameter("RzhdEtzpContractEndDateUpdate"));
            //
            // Вид обеспечения заявки
            //
            lr = "//integrCode[contains(., 'ProvisionBidType')]/../value/integer";
            put(lr, config.getConfigParameter("RzhdProvisionBidType4"));
            //
            // Обеспечение заявки
            //
            lr = "//integrCode[contains(., 'Deposit')]/../value/number";
            put(lr, config.getConfigParameter("RzhdZeroDeposit"));
            //
            // Вид обеспечения договора
            //
            lr = "//integrCode[contains(., 'ProvisionContractType')]/../value/integer";
            put(lr, config.getConfigParameter("RzhdProvisionContractType4"));
        }};
    }
}
