package Helpers.RzhdMaps;

import Helpers.ConfigContainer;
import Helpers.Timer;

import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

/**
 * Класс-контейнер для хранения информации о наборе параметров для файла-шаблона AEMSP.xml
 * ( обновление извещения об обычном аукционе МСП ).
 * Created by Vladimir V. Klochkov on 09.04.2019.
 * Updated by Vladimir V. Klochkov on 13.07.2020.
 */
public class RzhdSMBAuctionUpdate {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final ConfigContainer config = ConfigContainer.getInstance(); // экземпляр ConfigContainer
    private final Timer timer = new Timer();                              // класс для работы со временными интервалами

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    /**
     * Возвращает набор параметров для файла-шаблона AEMSP.xml.
     *
     * @return набор параметров для файла-шаблона AEMSP.xml
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
            put("ns2:name", config.getConfigParameter("RzhdPurchaseNameForAuctionUpdate"));
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
            // Срок предоставления документации (МСК) [ С ]
            //
            put("deliveryStartDateTime", timer.dateOnlyToResponseFormat(Timer.getPublishNoticeDate(), 0));
            //
            // Срок предоставления документации (МСК) [ ПО ]
            //
            put("deliveryEndDateTime", timer.dateOnlyToResponseFormat(Timer.getPublishNoticeDate(), 1));
            //
            // Дата модификации прикрепленного документа
            //
            put("ns2:modificationDate", timer.dateToResponseFormatWithSeconds(Timer.getPublishNoticeDate(1)));
            //
            // Дата подачи заявок (МСК)
            //
            put("ns2:applSubmisionStartDate", timer.dateOnlyToResponseFormat(Timer.getPublishNoticeDate()));
            //
            // Дата подведения итогов
            //
            put("ns2:summingupDateTime",
                    timer.dateToResponseFormatWithSeconds(Timer.getPublishNoticeDate(1), 7));
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
            // Дата рассмотрения первых частей заявок
            //
            put("//integrCode[contains(., 'applExamPeriodTime')]/../value/date",
                    timer.dateOnlyToResponseFormat(Timer.getPublishNoticeDate(), 2));
            //
            // Дата и время начала подачи ценовых предложений
            //(в черновике извещений данное поле формируется как конкатенация даты из тега pricesProvisionStartDate и
            // времени из тэга pricesProvisionStartTime)
            //
            put("//integrCode[contains(., 'pricesProvisionStartTime')]/../value/dateTime",
                    timer.dateToResponseFormatWithSeconds(Timer.getPublishNoticeDate(1)));
            //
            // Дата и время окончания срока подачи ценовых предложений
            //
            put("//integrCode[contains(., 'pricesProvisionEndDate')]/../value/dateTime",
                    timer.dateToResponseFormatWithSeconds(Timer.getPublishNoticeDate(1),
                            Calendar.MINUTE, 10));
            //
            // Дата начала срока подачи ценовых предложений
            //
            put("//integrCode[contains(., 'pricesProvisionStartDate')]/../value/date",
                    timer.dateOnlyToResponseFormat(Timer.getPublishNoticeDate(), 3));
            //
            // Дата проведения сопоставления ценовых предложений (аукциона)
            //
            put("//integrCode[contains(., 'pricesMatchingDate')]/../value/date",
                    timer.dateOnlyToResponseFormat(Timer.getPublishNoticeDate(), 4));
            //
            // Дата проведения квалификационного отбора
            //
            put("//integrCode[contains(., 'qualifyingCompetitionDate')]/../value/date",
                    timer.dateOnlyToResponseFormat(Timer.getPublishNoticeDate(), 5));
            //
            // Дата рассмотрения вторых частей заявок
            //
            put("//integrCode[contains(., 'considerationSecondPartDate')]/../value/date",
                    timer.dateOnlyToResponseFormat(Timer.getPublishNoticeDate(), 6));
            //
            // Рабочая группа организации
            //
            put("//integrCode[contains(., 'etzpOrganizerCode')]/../value/string",
                    config.getConfigParameter("RzhdEtzpOrganizerCode"));
            //
            // Уникальный номер закупки от РЖД
            //
            put("//integrCode[contains(., 'etzpNoticeNumber')]/../value/string",
                    String.format("Rzhd SMB - Auction Usual %s",
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
                    config.getConfigParameter("RzhdProvisionBidType2"));
            //
            // Обеспечение заявки
            //
            put("//integrCode[contains(., 'Deposit')]/../value/number", config.getConfigParameter("RzhdZeroDeposit"));
            //
            // Вид обеспечения договора
            //
            put("//integrCode[contains(., 'ProvisionContractType')]/../value/integer",
                    config.getConfigParameter("RzhdProvisionContractType4"));
        }};
    }
}
