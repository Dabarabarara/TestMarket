package ru.PageObjects.Customer.Notice;

import java.util.Hashtable;
import java.util.Set;
import java.util.UUID;

/**
 * Этот класс содержит методы для связывания конкурентной закупки - Запрос котировок с номером от ЕИС с помощью генерации
 * .xml-ответа от ЕИС.
 * Created by Kirill G. Rydzyvylo on 20.05.2020.
 * Updated by Vladimir V. Klochkov on 25.02.2021.
 */
public class CreateEisPublishedCompetitiveProcurementRequestForQuotesNotice extends CreateEisPublishedNotice {
    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    /**
     * Осуществляет заполнение полей в шаблоне .xml-ответа от ЕИС.
     *
     * @param model шаблон .xml-ответа от ЕИС
     * @return шаблон .xml-ответа от ЕИС
     */
    public String updateModel(String model) throws Exception {
        this.setPublishNoticeDate(this.getPublishNoticeDate("PublishNoticeDateAndTime"));
        System.out.printf("[ИНФОРМАЦИЯ]: дата публикации извещения [%tc].%n", publishNoticeDate);

        Hashtable<String, String> params = new Hashtable<String, String>();

        // region header

        params.put("{header:guid}", UUID.randomUUID().toString());
        params.put("{header:createDateTime}", timer.dateToResponseFormatWithSeconds(publishNoticeDate));

        // endregion

        // region ns2:body

        params.put("{ns2:item:guid}", UUID.randomUUID().toString());
        params.put("{ns2:item:urlVSRZ}", config.getParameter("PurchaseNumber"));
        params.put("{ns2:item:registrationNumber}", timer.getRandomDecimalId(11));
        params.put("{ns2:item:name}", config.getParameter("PurchaseName"));
        params.put("{ns2:item:customer:fullName}", "АО «Финтендер-Академия»");
        params.put("{ns2:item:customer:iko}", "59718036602622995816");
        params.put("{ns2:item:customer:inn}", "7710357167");
        params.put("{ns2:item:customer:kpp}", "770701001");
        params.put("{ns2:item:customer:ogrn}", "3292842395731");
        params.put("{ns2:item:customer:legalAddress}", "195112г Москва, просп Тестовый, 94");
        params.put("{ns2:item:customer:postalAddress}",
                "Российская Федерация 142100 Московская обл Подольск г Ватутинский проезд д. 64");
        params.put("{ns2:item:customer:okopfName}", "Юридические лица, являющиеся коммерческими организациями");
        params.put("{ns2:item:customer:purchaseCodeName}", "Запрос котировок конкурентный");
        params.put("{ns2:item:placer:okopfName}", "Юридические лица, являющиеся коммерческими организациями");
        params.put("{ns2:item:contact:firstName}", "ЗаказчикНП");
        params.put("{ns2:item:place}",
                "Электронная площадка РТС-тендер (https://kontur.ru/) и Официальный сайт (http://zakupki.gov.ru)");
        params.put("{ns2:item:document:guid}", UUID.randomUUID().toString());
        params.put("{ns2:item:applSubmisionStartDate}", timer.dateOnlyToResponseFormat(publishNoticeDate, 0));
        params.put("{ns2:item:electronicPlaceInfo:name}", "РТС-тендер");
        params.put("{ns2:item:placingProcedure:summingupDateTime}",
                timer.dateToResponseFormatWithSeconds(publishNoticeDate, 5));
        params.put("{ns2:item:placingProcedure:summingupPlace}", "325325, Российская Федерация, Респ. Дагестан, " +
                "Ахвахский р-н, с. Карата, Строителей, 3, ОКАТО: 82205830001");
        params.put("{ns2:item:submissionCloseDateTime}",
                timer.dateToResponseFormatWithSeconds(publishNoticeDate, 1));
        params.put("{ns2:item:lot:guid}", UUID.randomUUID().toString());
        params.put("{ns2:item:lotData:subject}", "Лот 1");
        params.put("{ns2:item:lotData:currency:name}", "Российский рубль");
        params.put("{ns2:item:lotData:initialSum}", "5.00");
        params.put("{ns2:item:lotData:deliveryPlace:state}", "Северо-Кавказский федеральный округ");
        params.put("{ns2:item:lotData:deliveryPlace:region}", "Дагестан Респ");
        params.put("{ns2:item:lotData:deliveryPlace:address}", "325325, Российская Федерация, Респ. Дагестан, " +
                "Ахвахский р-н, с. Карата, Строителей, 3, ОКАТО: 82205830001");
        params.put("{ns2:item:lot:lotItem:guid}", UUID.randomUUID().toString());
        params.put("{ns2:item:lot:lotItem:okpd2:code}", "36.00.11.000");
        params.put("{ns2:item:lot:lotItem:okpd2:name}", "Вода питьевая");
        params.put("{ns2:item:lot:lotItem:okved2:code}", "01.11.13");
        params.put("{ns2:item:lot:lotItem:okved2:name}", "Выращивание ржи");
        params.put("{ns2:item:lot:lotItem:okei:code}", "130");
        params.put("{ns2:item:lot:lotItem:okei:name}", "Тысяча литров");

        // endregion

        Set<String> keys = params.keySet();
        for (String key : keys) model = this.setFieldInModel(model, key, params.get(key), key);
        return model;
    }
}
