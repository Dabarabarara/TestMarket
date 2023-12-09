package ru.StepDefinitions.Customer.Notice;

import Helpers.XMLContainer;
import Helpers.XMLProcessor;
import Helpers.XMLSenderForRzhd;
import cucumber.api.java.en.And;
import org.javatuples.Pair;
import org.junit.Assert;
import ru.StepDefinitions.AbstractStepDefinitions;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Класс описывающий шаги работы Заказчика по созданию извещения о новой закупке (РЖД в соответствии с нормами 223-ФЗ).
 * Created by Vladimir V. Klochkov on 12.02.2019.
 * Updated by Vladimir V. Klochkov on 04.03.2021.
 */
public class CustomerCreateRZHDNoticeStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final XMLContainer container = XMLContainer.getInstance(); // экземпляр XMLContainer

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    // region Аукцион МСП - формирование .xml-файлов

    @And("'Заказчик' РЖД формирует .xml-файл, который имитирует входящее извещение об обычном аукционе МСП")
    public void customerCreatesAnXMLFileWithRZHDAuctionSMBUsual() throws Throwable {
        String stepName =
                "'Заказчик' РЖД формирует .xml-файл, который имитирует входящее извещение об обычном аукционе МСП";
        this.logStepName(stepName);

        timer.start();

        // Готовим входные данные для получения корректного .xml-файла с актуальными датами
        Pair<String, HashMap<String, String>> xmlTemplateWithXmlParameters =
                container.getXMLWithParams(String.format("%s/CreateNotice", config.getParameter("TestType")));
        String outFileNamePrefix = "RzhdSMB-AuctionUsualCreateNotice";
        String outFileName = config.getConfigParameter("PurchaseXMLFromRzhdTargetPath") +
                String.format("%s-%s.xml", outFileNamePrefix, timer.getCurrentDateTime("ddMMyyyyHHmmSS"));

        // Создаем корректный .xml-файл с актуальными датами в указанном каталоге
        XMLProcessor processor = new XMLProcessor(xmlTemplateWithXmlParameters, outFileName);
        String result = processor.readXML().processXML().saveXML().saveXMLToString();

        // Сохраняем содержимое этого файла и его полное имя в параметрах автоматического теста
        config.setParameter("CreatePurchaseNoticeXMLFromRzhd", result);
        config.setParameter("CreatePurchaseNoticeXMLFromRzhdOutFileName", new File(outFileName).getAbsolutePath());

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' РЖД формирует .xml-файл, который имитирует обновление входящего извещения об обычном аукционе МСП")
    public void customerUpdatesAnXMLFileWithRZHDAuctionSMBUsual() throws Throwable {
        String stepName = "'Заказчик' РЖД формирует .xml-файл, который имитирует обновление входящего извещения об " +
                "обычном аукционе МСП";
        this.logStepName(stepName);

        timer.start();

        // Готовим входные данные для получения корректного .xml-файла с актуальными датами
        Pair<String, HashMap<String, String>> xmlTemplateWithXmlParameters =
                container.getXMLWithParams(String.format("%s/UpdateNotice", config.getParameter("TestType")));
        String outFileNamePrefix = "RzhdSMB-AuctionUsualUpdateNotice";
        String outFileName = config.getConfigParameter("PurchaseXMLFromRzhdTargetPath") +
                String.format("%s-%s.xml", outFileNamePrefix, timer.getCurrentDateTime("ddMMyyyyHHmmSS"));

        // Создаем корректный .xml-файл с актуальными датами в указанном каталоге
        XMLProcessor processor = new XMLProcessor(xmlTemplateWithXmlParameters, outFileName);
        String result = processor.readXML().processXML().saveXML().saveXMLToString();

        // Сохраняем содержимое этого файла и его полное имя в параметрах автоматического теста
        config.setParameter("UpdatePurchaseNoticeXMLFromRzhd", result);
        config.setParameter("UpdatePurchaseNoticeXMLFromRzhdOutFileName", new File(outFileName).getAbsolutePath());

        timer.printStepTotalTime(stepName);
    }

    // endregion

    // region Конкурс МСП - формирование .xml-файлов

    @And("'Заказчик' РЖД формирует .xml-файл, который имитирует входящее извещение об обычном конкурсе МСП")
    public void customerCreatesAnXMLFileWithRZHDTenderSMBUsual() throws Throwable {
        String stepName =
                "'Заказчик' РЖД формирует .xml-файл, который имитирует входящее извещение об обычном конкурсе МСП";
        this.logStepName(stepName);

        timer.start();

        // Готовим входные данные для получения корректного .xml-файла с актуальными датами
        Pair<String, HashMap<String, String>> xmlTemplateWithXmlParameters =
                container.getXMLWithParams(String.format("%s/CreateNotice", config.getParameter("TestType")));
        String outFileNamePrefix = "RzhdSMB-TenderUsualCreateNotice";
        String outFileName = config.getConfigParameter("PurchaseXMLFromRzhdTargetPath") +
                String.format("%s-%s.xml", outFileNamePrefix, timer.getCurrentDateTime("ddMMyyyyHHmmSS"));

        // Создаем корректный .xml-файл с актуальными датами в указанном каталоге
        XMLProcessor processor = new XMLProcessor(xmlTemplateWithXmlParameters, outFileName);
        String result = processor.readXML().processXML().saveXML().saveXMLToString();

        // Сохраняем содержимое этого файла и его полное имя в параметрах автоматического теста
        config.setParameter("CreatePurchaseNoticeXMLFromRzhd", result);
        config.setParameter("CreatePurchaseNoticeXMLFromRzhdOutFileName", new File(outFileName).getAbsolutePath());

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' РЖД формирует .xml-файл, который имитирует обновление входящего извещения об обычном конкурсе МСП")
    public void customerUpdatesAnXMLFileWithRZHDTenderSMBUsual() throws Throwable {
        String stepName = "'Заказчик' РЖД формирует .xml-файл, который имитирует обновление входящего извещения об " +
                "обычном конкурсе МСП";
        this.logStepName(stepName);

        timer.start();

        // Готовим входные данные для получения корректного .xml-файла с актуальными датами
        Pair<String, HashMap<String, String>> xmlTemplateWithXmlParameters =
                container.getXMLWithParams(String.format("%s/UpdateNotice", config.getParameter("TestType")));
        String outFileNamePrefix = "RzhdSMB-TenderUsualUpdateNotice";
        String outFileName = config.getConfigParameter("PurchaseXMLFromRzhdTargetPath") +
                String.format("%s-%s.xml", outFileNamePrefix, timer.getCurrentDateTime("ddMMyyyyHHmmSS"));

        // Создаем корректный .xml-файл с актуальными датами в указанном каталоге
        XMLProcessor processor = new XMLProcessor(xmlTemplateWithXmlParameters, outFileName);
        String result = processor.readXML().processXML().saveXML().saveXMLToString();

        // Сохраняем содержимое этого файла и его полное имя в параметрах автоматического теста
        config.setParameter("UpdatePurchaseNoticeXMLFromRzhd", result);
        config.setParameter("UpdatePurchaseNoticeXMLFromRzhdOutFileName", new File(outFileName).getAbsolutePath());

        timer.printStepTotalTime(stepName);
    }

    // endregion

    // region Запрос котировок МСП - формирование .xml-файлов

    @And("'Заказчик' РЖД формирует .xml-файл, который имитирует входящее извещение об обычном запросе котировок МСП")
    public void customerCreatesAnXMLFileWithRZHDRequestForQuotationsSMBUsual() throws Throwable {
        String stepName = "'Заказчик' РЖД формирует .xml-файл, который имитирует входящее извещение об обычном " +
                "запросе котировок МСП";
        this.logStepName(stepName);

        timer.start();

        // Готовим входные данные для получения корректного .xml-файла с актуальными датами
        Pair<String, HashMap<String, String>> xmlTemplateWithXmlParameters =
                container.getXMLWithParams(String.format("%s/CreateNotice", config.getParameter("TestType")));
        String outFileNamePrefix = "RzhdSMB-RequestForQuotationsUsualCreateNotice";
        String outFileName = config.getConfigParameter("PurchaseXMLFromRzhdTargetPath") +
                String.format("%s-%s.xml", outFileNamePrefix, timer.getCurrentDateTime("ddMMyyyyHHmmSS"));

        // Создаем корректный .xml-файл с актуальными датами в указанном каталоге
        XMLProcessor processor = new XMLProcessor(xmlTemplateWithXmlParameters, outFileName);
        String result = processor.readXML().processXML().saveXML().saveXMLToString();

        // Сохраняем содержимое этого файла и его полное имя в параметрах автоматического теста
        config.setParameter("CreatePurchaseNoticeXMLFromRzhd", result);
        config.setParameter("CreatePurchaseNoticeXMLFromRzhdOutFileName", new File(outFileName).getAbsolutePath());

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' РЖД формирует .xml-файл, который имитирует обновление входящего извещения об обычном запросе котировок МСП")
    public void customerUpdatesAnXMLFileWithRZHDRequestForQuotationsSMBUsual() throws Throwable {
        String stepName = "'Заказчик' РЖД формирует .xml-файл, который имитирует обновление входящего извещения об " +
                "обычном запросе котировок МСП";
        this.logStepName(stepName);

        timer.start();

        // Готовим входные данные для получения корректного .xml-файла с актуальными датами
        Pair<String, HashMap<String, String>> xmlTemplateWithXmlParameters =
                container.getXMLWithParams(String.format("%s/UpdateNotice", config.getParameter("TestType")));
        String outFileNamePrefix = "RzhdSMB-RequestForQuotationsUsualUpdateNotice";
        String outFileName = config.getConfigParameter("PurchaseXMLFromRzhdTargetPath") +
                String.format("%s-%s.xml", outFileNamePrefix, timer.getCurrentDateTime("ddMMyyyyHHmmSS"));

        // Создаем корректный .xml-файл с актуальными датами в указанном каталоге
        XMLProcessor processor = new XMLProcessor(xmlTemplateWithXmlParameters, outFileName);
        String result = processor.readXML().processXML().saveXML().saveXMLToString();

        // Сохраняем содержимое этого файла и его полное имя в параметрах автоматического теста
        config.setParameter("UpdatePurchaseNoticeXMLFromRzhd", result);
        config.setParameter("UpdatePurchaseNoticeXMLFromRzhdOutFileName", new File(outFileName).getAbsolutePath());

        timer.printStepTotalTime(stepName);
    }

    // endregion

    // region Запрос предложений МСП - формирование .xml-файлов

    @And("'Заказчик' РЖД формирует .xml-файл, который имитирует входящее извещение об обычном запросе предложений МСП")
    public void customerCreatesAnXMLFileWithRZHDRequestForProposalsSMBUsual() throws Throwable {
        String stepName = "'Заказчик' РЖД формирует .xml-файл, который имитирует входящее извещение об обычном " +
                "запросе предложений МСП";
        this.logStepName(stepName);

        timer.start();

        // Готовим входные данные для получения корректного .xml-файла с актуальными датами
        Pair<String, HashMap<String, String>> xmlTemplateWithXmlParameters =
                container.getXMLWithParams(String.format("%s/CreateNotice", config.getParameter("TestType")));
        String outFileNamePrefix = "RzhdSMB-RequestForProposalsUsualCreateNotice";
        String outFileName = config.getConfigParameter("PurchaseXMLFromRzhdTargetPath") +
                String.format("%s-%s.xml", outFileNamePrefix, timer.getCurrentDateTime("ddMMyyyyHHmmSS"));

        // Создаем корректный .xml-файл с актуальными датами в указанном каталоге
        XMLProcessor processor = new XMLProcessor(xmlTemplateWithXmlParameters, outFileName);
        String result = processor.readXML().processXML().saveXML().saveXMLToString();

        // Сохраняем содержимое этого файла и его полное имя в параметрах автоматического теста
        config.setParameter("CreatePurchaseNoticeXMLFromRzhd", result);
        config.setParameter("CreatePurchaseNoticeXMLFromRzhdOutFileName", new File(outFileName).getAbsolutePath());

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' РЖД формирует .xml-файл, который имитирует обновление входящего извещения об обычном запросе предложений МСП")
    public void customerUpdatesAnXMLFileWithRZHDRequestForProposalsSMBUsual() throws Throwable {
        String stepName = "'Заказчик' РЖД формирует .xml-файл, который имитирует обновление входящего извещения об " +
                "обычном запросе предложений МСП";
        this.logStepName(stepName);

        timer.start();

        // Готовим входные данные для получения корректного .xml-файла с актуальными датами
        Pair<String, HashMap<String, String>> xmlTemplateWithXmlParameters =
                container.getXMLWithParams(String.format("%s/UpdateNotice", config.getParameter("TestType")));
        String outFileNamePrefix = "RzhdSMB-RequestForProposalsUsualUpdateNotice";
        String outFileName = config.getConfigParameter("PurchaseXMLFromRzhdTargetPath") +
                String.format("%s-%s.xml", outFileNamePrefix, timer.getCurrentDateTime("ddMMyyyyHHmmSS"));

        // Создаем корректный .xml-файл с актуальными датами в указанном каталоге
        XMLProcessor processor = new XMLProcessor(xmlTemplateWithXmlParameters, outFileName);
        String result = processor.readXML().processXML().saveXML().saveXMLToString();

        // Сохраняем содержимое этого файла и его полное имя в параметрах автоматического теста
        config.setParameter("UpdatePurchaseNoticeXMLFromRzhd", result);
        config.setParameter("UpdatePurchaseNoticeXMLFromRzhdOutFileName", new File(outFileName).getAbsolutePath());

        timer.printStepTotalTime(stepName);
    }

    // endregion

    // region Аукцион с ограниченным участием - формирование .xml-файлов

    @And("'Заказчик' РЖД формирует .xml-файл, который имитирует входящее извещение о электронном аукционе с ограниченным участием")
    public void customerCreatesAnXMLFileWithRZHDAuctionWithLimitedParticipation() throws Throwable {
        String stepName = "'Заказчик' РЖД формирует .xml-файл, который имитирует входящее извещение о электронном " +
                "аукционе с ограниченным участием";
        this.logStepName(stepName);

        timer.start();

        // Готовим входные данные для получения корректного .xml-файла с актуальными датами
        Pair<String, HashMap<String, String>> xmlTemplateWithXmlParameters =
                container.getXMLWithParams(String.format("%s/CreateNotice", config.getParameter("TestType")));
        String outFileNamePrefix = "Rzhd-AuctionWithLimitedParticipationCreateNotice";
        String outFileName = config.getConfigParameter("PurchaseXMLFromRzhdTargetPath") +
                String.format("%s-%s.xml", outFileNamePrefix, timer.getCurrentDateTime("ddMMyyyyHHmmSS"));

        // Создаем корректный .xml-файл с актуальными датами в указанном каталоге
        XMLProcessor processor = new XMLProcessor(xmlTemplateWithXmlParameters, outFileName);
        String result = processor.readXML().processXML().saveXML().saveXMLToString();

        // Сохраняем содержимое этого файла и его полное имя в параметрах автоматического теста
        config.setParameter("CreatePurchaseNoticeXMLFromRzhd", result);
        config.setParameter("CreatePurchaseNoticeXMLFromRzhdOutFileName", new File(outFileName).getAbsolutePath());

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' РЖД формирует .xml-файл, который имитирует обновление входящего извещения о электронном аукционе с ограниченным участием")
    public void customerUpdatesAnXMLFileWithRZHDAuctionWithLimitedParticipation() throws Throwable {
        String stepName = "'Заказчик' РЖД формирует .xml-файл, который имитирует обновление входящего извещения о " +
                "электронном аукционе с ограниченным участием";
        this.logStepName(stepName);

        timer.start();

        // Готовим входные данные для получения корректного .xml-файла с актуальными датами
        Pair<String, HashMap<String, String>> xmlTemplateWithXmlParameters =
                container.getXMLWithParams(String.format("%s/UpdateNotice", config.getParameter("TestType")));
        String outFileNamePrefix = "Rzhd-AuctionWithLimitedParticipationUpdateNotice";
        String outFileName = config.getConfigParameter("PurchaseXMLFromRzhdTargetPath") +
                String.format("%s-%s.xml", outFileNamePrefix, timer.getCurrentDateTime("ddMMyyyyHHmmSS"));

        // Создаем корректный .xml-файл с актуальными датами в указанном каталоге
        XMLProcessor processor = new XMLProcessor(xmlTemplateWithXmlParameters, outFileName);
        String result = processor.readXML().processXML().saveXML().saveXMLToString();

        // Сохраняем содержимое этого файла и его полное имя в параметрах автоматического теста
        config.setParameter("UpdatePurchaseNoticeXMLFromRzhd", result);
        config.setParameter("UpdatePurchaseNoticeXMLFromRzhdOutFileName", new File(outFileName).getAbsolutePath());

        timer.printStepTotalTime(stepName);
    }

    // endregion

    // region Открытый аукцион - формирование .xml-файлов

    @And("'Заказчик' РЖД формирует .xml-файл, который имитирует входящее извещение об открытом электронном аукционе")
    public void customerCreatesAnXMLFileWithRZHDOpenAuction() throws Throwable {
        String stepName = "'Заказчик' РЖД формирует .xml-файл, который имитирует входящее извещение об открытом " +
                "электронном аукционе";
        this.logStepName(stepName);

        timer.start();

        // Готовим входные данные для получения корректного .xml-файла с актуальными датами
        Pair<String, HashMap<String, String>> xmlTemplateWithXmlParameters =
                container.getXMLWithParams(String.format("%s/CreateNotice", config.getParameter("TestType")));
        String outFileNamePrefix = "Rzhd-OpenAuctionCreateNotice";
        String outFileName = config.getConfigParameter("PurchaseXMLFromRzhdTargetPath") +
                String.format("%s-%s.xml", outFileNamePrefix, timer.getCurrentDateTime("ddMMyyyyHHmmSS"));

        // Создаем корректный .xml-файл с актуальными датами в указанном каталоге
        XMLProcessor processor = new XMLProcessor(xmlTemplateWithXmlParameters, outFileName);
        String result = processor.readXML().processXML().saveXML().saveXMLToString();

        // Сохраняем содержимое этого файла и его полное имя в параметрах автоматического теста
        config.setParameter("CreatePurchaseNoticeXMLFromRzhd", result);
        config.setParameter("CreatePurchaseNoticeXMLFromRzhdOutFileName", new File(outFileName).getAbsolutePath());

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' РЖД формирует .xml-файл, который имитирует обновление входящего извещения об открытом электронном аукционе")
    public void customerUpdatesAnXMLFileWithRZHDOpenAuction() throws Throwable {
        String stepName = "'Заказчик' РЖД формирует .xml-файл, который имитирует обновление входящего извещения об " +
                "открытом электронном аукционе";
        this.logStepName(stepName);

        timer.start();

        // Готовим входные данные для получения корректного .xml-файла с актуальными датами
        Pair<String, HashMap<String, String>> xmlTemplateWithXmlParameters =
                container.getXMLWithParams(String.format("%s/UpdateNotice", config.getParameter("TestType")));
        String outFileNamePrefix = "Rzhd-OpenAuctionUpdateNotice";
        String outFileName = config.getConfigParameter("PurchaseXMLFromRzhdTargetPath") +
                String.format("%s-%s.xml", outFileNamePrefix, timer.getCurrentDateTime("ddMMyyyyHHmmSS"));

        // Создаем корректный .xml-файл с актуальными датами в указанном каталоге
        XMLProcessor processor = new XMLProcessor(xmlTemplateWithXmlParameters, outFileName);
        String result = processor.readXML().processXML().saveXML().saveXMLToString();

        // Сохраняем содержимое этого файла и его полное имя в параметрах автоматического теста
        config.setParameter("UpdatePurchaseNoticeXMLFromRzhd", result);
        config.setParameter("UpdatePurchaseNoticeXMLFromRzhdOutFileName", new File(outFileName).getAbsolutePath());

        timer.printStepTotalTime(stepName);
    }

    // endregion

    // region Открытый конкурс - формирование .xml-файлов

    @And("'Заказчик' РЖД формирует .xml-файл, который имитирует входящее извещение об открытом электронном конкурсе")
    public void customerCreatesAnXMLFileWithRZHDOpenTender() throws Throwable {
        String stepName = "'Заказчик' РЖД формирует .xml-файл, который имитирует входящее извещение об открытом " +
                "электронном конкурсе";
        this.logStepName(stepName);

        timer.start();

        // Готовим входные данные для получения корректного .xml-файла с актуальными датами
        Pair<String, HashMap<String, String>> xmlTemplateWithXmlParameters =
                container.getXMLWithParams(String.format("%s/CreateNotice", config.getParameter("TestType")));
        String outFileNamePrefix = "Rzhd-OpenTenderCreateNotice";
        String outFileName = config.getConfigParameter("PurchaseXMLFromRzhdTargetPath") +
                String.format("%s-%s.xml", outFileNamePrefix, timer.getCurrentDateTime("ddMMyyyyHHmmSS"));

        // Создаем корректный .xml-файл с актуальными датами в указанном каталоге
        XMLProcessor processor = new XMLProcessor(xmlTemplateWithXmlParameters, outFileName);
        String result = processor.readXML().processXML().saveXML().saveXMLToString();

        // Сохраняем содержимое этого файла и его полное имя в параметрах автоматического теста
        config.setParameter("CreatePurchaseNoticeXMLFromRzhd", result);
        config.setParameter("CreatePurchaseNoticeXMLFromRzhdOutFileName", new File(outFileName).getAbsolutePath());

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' РЖД формирует .xml-файл, который имитирует обновление входящего извещения об открытом электронном конкурсе")
    public void customerUpdatesAnXMLFileWithRZHDOpenTender() throws Throwable {
        String stepName = "'Заказчик' РЖД формирует .xml-файл, который имитирует обновление входящего извещения об " +
                "открытом электронном конкурсе";
        this.logStepName(stepName);

        timer.start();

        // Готовим входные данные для получения корректного .xml-файла с актуальными датами
        Pair<String, HashMap<String, String>> xmlTemplateWithXmlParameters =
                container.getXMLWithParams(String.format("%s/UpdateNotice", config.getParameter("TestType")));
        String outFileNamePrefix = "Rzhd-OpenTenderUpdateNotice";
        String outFileName = config.getConfigParameter("PurchaseXMLFromRzhdTargetPath") +
                String.format("%s-%s.xml", outFileNamePrefix, timer.getCurrentDateTime("ddMMyyyyHHmmSS"));

        // Создаем корректный .xml-файл с актуальными датами в указанном каталоге
        XMLProcessor processor = new XMLProcessor(xmlTemplateWithXmlParameters, outFileName);
        String result = processor.readXML().processXML().saveXML().saveXMLToString();

        // Сохраняем содержимое этого файла и его полное имя в параметрах автоматического теста
        config.setParameter("UpdatePurchaseNoticeXMLFromRzhd", result);
        config.setParameter("UpdatePurchaseNoticeXMLFromRzhdOutFileName", new File(outFileName).getAbsolutePath());

        timer.printStepTotalTime(stepName);
    }

    // endregion

    // region Открытый запрос котировок - формирование .xml-файлов

    @And("'Заказчик' РЖД формирует .xml-файл, который имитирует входящее извещение об открытом электронном запросе котировок")
    public void customerCreatesAnXMLFileWithRZHDOpenRequestForQuotations() throws Throwable {
        String stepName = "'Заказчик' РЖД формирует .xml-файл, который имитирует входящее извещение об открытом " +
                "электронном запросе котировок";
        this.logStepName(stepName);

        timer.start();

        // Готовим входные данные для получения корректного .xml-файла с актуальными датами
        Pair<String, HashMap<String, String>> xmlTemplateWithXmlParameters =
                container.getXMLWithParams(String.format("%s/CreateNotice", config.getParameter("TestType")));
        String outFileNamePrefix = "Rzhd-OpenRequestForQuotationsCreateNotice";
        String outFileName = config.getConfigParameter("PurchaseXMLFromRzhdTargetPath") +
                String.format("%s-%s.xml", outFileNamePrefix, timer.getCurrentDateTime("ddMMyyyyHHmmSS"));

        // Создаем корректный .xml-файл с актуальными датами в указанном каталоге
        XMLProcessor processor = new XMLProcessor(xmlTemplateWithXmlParameters, outFileName);
        String result = processor.readXML().processXML().saveXML().saveXMLToString();

        // Сохраняем содержимое этого файла и его полное имя в параметрах автоматического теста
        config.setParameter("CreatePurchaseNoticeXMLFromRzhd", result);
        config.setParameter("CreatePurchaseNoticeXMLFromRzhdOutFileName", new File(outFileName).getAbsolutePath());

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' РЖД формирует .xml-файл, который имитирует входящее извещение об открытом электронном запросе котировок с большим файлом")
    public void customerCreatesAnXMLFileWithRZHDOpenRequestForQuotationsAndBigFile() throws Throwable {
        String stepName = "'Заказчик' РЖД формирует .xml-файл, который имитирует входящее извещение об открытом " +
                "электронном запросе котировок с большим файлом";
        this.logStepName(stepName);

        timer.start();

        // Готовим входные данные для получения корректного .xml-файла с актуальными датами
        Pair<String, HashMap<String, String>> xmlTemplateWithXmlParameters =
                container.getXMLWithParams(String.format("%s/CreateNotice", config.getParameter("TestType")));
        String outFileNamePrefix = "Rzhd-OpenRequestForQuotationsCreateNoticeAndBigFile";
        String outFileName = config.getConfigParameter("PurchaseXMLFromRzhdTargetPath") +
                String.format("%s-%s.xml", outFileNamePrefix, timer.getCurrentDateTime("ddMMyyyyHHmmSS"));

        // Создаем корректный .xml-файл с актуальными датами в указанном каталоге
        XMLProcessor processor = new XMLProcessor(xmlTemplateWithXmlParameters, outFileName);
        processor.readXML().processXML().saveXML();

        // Сохраняем полное имя этого файла в параметрах автоматического теста
        config.setParameter("CreatePurchaseNoticeXMLFromRzhdOutFileName", new File(outFileName).getAbsolutePath());

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' РЖД формирует .xml-файл, который имитирует обновление входящего извещения об открытом электронном запросе котировок")
    public void customerUpdatesAnXMLFileWithRZHDOpenRequestForQuotations() throws Throwable {
        String stepName = "'Заказчик' РЖД формирует .xml-файл, который имитирует обновление входящего извещения об " +
                "открытом электронном запросе котировок";
        this.logStepName(stepName);

        timer.start();

        // Готовим входные данные для получения корректного .xml-файла с актуальными датами
        Pair<String, HashMap<String, String>> xmlTemplateWithXmlParameters =
                container.getXMLWithParams(String.format("%s/UpdateNotice", config.getParameter("TestType")));
        String outFileNamePrefix = "Rzhd-OpenRequestForQuotationsUpdateNotice";
        String outFileName = config.getConfigParameter("PurchaseXMLFromRzhdTargetPath") +
                String.format("%s-%s.xml", outFileNamePrefix, timer.getCurrentDateTime("ddMMyyyyHHmmSS"));

        // Создаем корректный .xml-файл с актуальными датами в указанном каталоге
        XMLProcessor processor = new XMLProcessor(xmlTemplateWithXmlParameters, outFileName);
        String result = processor.readXML().processXML().saveXML().saveXMLToString();

        // Сохраняем содержимое этого файла и его полное имя в параметрах автоматического теста
        config.setParameter("UpdatePurchaseNoticeXMLFromRzhd", result);
        config.setParameter("UpdatePurchaseNoticeXMLFromRzhdOutFileName", new File(outFileName).getAbsolutePath());

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' РЖД формирует .xml-файл, который имитирует обновление входящего извещения об открытом электронном запросе котировок с большим файлом")
    public void customerUpdatesAnXMLFileWithRZHDOpenRequestForQuotationsAndBigFile() throws Throwable {
        String stepName = "'Заказчик' РЖД формирует .xml-файл, который имитирует обновление входящего извещения об " +
                "открытом электронном запросе котировок с большим файлом";
        this.logStepName(stepName);

        timer.start();

        // Готовим входные данные для получения корректного .xml-файла с актуальными датами
        Pair<String, HashMap<String, String>> xmlTemplateWithXmlParameters =
                container.getXMLWithParams(String.format("%s/UpdateNotice", config.getParameter("TestType")));
        String outFileNamePrefix = "Rzhd-OpenRequestForQuotationsUpdateNoticeAndBigFile";
        String outFileName = config.getConfigParameter("PurchaseXMLFromRzhdTargetPath") +
                String.format("%s-%s.xml", outFileNamePrefix, timer.getCurrentDateTime("ddMMyyyyHHmmSS"));

        // Создаем корректный .xml-файл с актуальными датами в указанном каталоге
        XMLProcessor processor = new XMLProcessor(xmlTemplateWithXmlParameters, outFileName);
        processor.readXML().processXML().saveXML();

        // Сохраняем полное имя этого файла в параметрах автоматического теста
        config.setParameter("UpdatePurchaseNoticeXMLFromRzhdOutFileName", new File(outFileName).getAbsolutePath());

        timer.printStepTotalTime(stepName);
    }

    // endregion

    // region Отправка .xml-файлов от РЖД на площадку и ожидание ответа площадки

    @And("'Заказчик' РЖД отправляет .xml-файл от РЖД на площадку и ожидает ответ площадки")
    public void customerPostsAnXMLCreateFileToStandAndWaitsStandsResponse() throws Throwable {
        String stepName = "'Заказчик' РЖД отправляет .xml-файл от РЖД на площадку и ожидает ответ площадки";
        this.logStepName(stepName);

        timer.start();

        // Отправляем .xml-файл от РЖД на площадку и ожидаем ответ площадки
        XMLSenderForRzhd sender = new XMLSenderForRzhd(
                config.getConfigParameter("RzhdPostUrl"),
                config.getConfigParameter("RzhdGetUrl"),
                config.getConfigParameter("RzhdLogin"),
                config.getConfigParameter("RzhdPassword"),
                config.getParameter("CreatePurchaseNoticeXMLFromRzhdOutFileName")
        );
        sender.postRequest().getResponce();

        timer.printStepTotalTime(stepName);
    }

    @And("'Заказчик' РЖД отправляет обновленный .xml-файл от РЖД на площадку и ожидает ответ площадки")
    public void customerPostsAnXMLUpdateFileToStandAndWaitsStandsResponse() throws Throwable {
        String stepName = "'Заказчик' РЖД отправляет обновленный .xml-файл от РЖД на площадку и ожидает ответ площадки";
        this.logStepName(stepName);

        timer.start();

        // Отправляем .xml-файл от РЖД на площадку и ожидаем ответ площадки
        XMLSenderForRzhd sender = new XMLSenderForRzhd(
                config.getConfigParameter("RzhdPostUrl"),
                config.getConfigParameter("RzhdGetUrl"),
                config.getConfigParameter("RzhdLogin"),
                config.getConfigParameter("RzhdPassword"),
                config.getParameter("UpdatePurchaseNoticeXMLFromRzhdOutFileName")
        );
        sender.postRequest().getResponce();

        timer.printStepTotalTime(stepName);
    }

    // endregion

    // region Сохранение и проверка уникальных идентификаторов извещения о закупке из URL

    @And("^'Заказчик' сохраняет в параметрах теста уникальный идентификатор извещения о закупке из URL$")
    public void customerSavesInTestParametersUniquePurchaseIdFromUrl() {
        String stepName = "'Заказчик' сохраняет в параметрах теста уникальный идентификатор извещения о закупке из URL";
        this.logStepName(stepName);

        timer.start();

        config.setParameter("PurchaseId", url().split("/edit/")[1]);
        System.out.printf("[ИНФОРМАЦИЯ]: уникальный идентификатор извещения о закупке из URL: [%s].%n",
                config.getParameter("PurchaseId"));

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет, что уникальный идентификатор извещения о закупке из URL не изменился$")
    public void customerChecksThatTheUniquePurchaseIdFromUrlIsNotChanged() {
        String stepName = "'Заказчик' проверяет, что уникальный идентификатор извещения о закупке из URL не изменился";
        this.logStepName(stepName);

        timer.start();

        System.out.printf(
                "[ИНФОРМАЦИЯ]: уникальный идентификатор извещения о закупке из URL: [%s], из параметров теста: [%s].%n",
                url().split("/edit/")[1], config.getParameter("PurchaseId"));
        Assert.assertEquals("[ОШИБКА]: уникальный идентификатор извещения о закупке из URL изменился",
                config.getParameter("PurchaseId"), url().split("/edit/")[1]);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Заказчик' проверяет, что уникальный идентификатор извещения о закупке из URL изменился$")
    public void customerChecksThatTheUniquePurchaseIdFromUrlIsChanged() {
        String stepName = "'Заказчик' проверяет, что уникальный идентификатор извещения о закупке из URL изменился";
        this.logStepName(stepName);

        timer.start();

        System.out.printf(
                "[ИНФОРМАЦИЯ]: уникальный идентификатор извещения о закупке из URL: [%s], из параметров теста: [%s].%n",
                url().split("/edit/")[1], config.getParameter("PurchaseId"));
        Assert.assertNotEquals("[ОШИБКА]: уникальный идентификатор извещения о закупке из URL не изменился",
                config.getParameter("PurchaseId"), url().split("/edit/")[1]);

        timer.printStepTotalTime(stepName);
    }

    // endregion

    // region Удаление исходных больших .xml-файлов от РЖД после успешного завершения теста

    @And("'Заказчик' РЖД удаляет исходные большие .xml-файлы от РЖД после успешного завершения теста")
    public void customerDeletesAllSourceBigXMLFilesFromRZHDAfterTestCaseCompletesSuccessfully() {
        String stepName = "'Заказчик' РЖД удаляет исходные большие .xml-файлы от РЖД после успешного завершения теста";
        this.logStepName(stepName);

        timer.start();

        List<File> bigFilesToDelete = new ArrayList<>(Arrays.asList(
                new File(config.getParameter("CreatePurchaseNoticeXMLFromRzhdOutFileName")),
                new File(config.getParameter("UpdatePurchaseNoticeXMLFromRzhdOutFileName"))
        ));

        for (File file : bigFilesToDelete) {
            String bigFileToDeleteName = file.getAbsolutePath();
            System.out.printf("[ИНФОРМАЦИЯ]: удаляем исходный .xml-файл от РЖД: [%s].%n", bigFileToDeleteName);
            Assert.assertTrue(String.format("[ОШИБКА]: не удалось удалить исходный .xml-файл от РЖД: [%s]",
                    bigFileToDeleteName), file.delete());
        }

        timer.printStepTotalTime(stepName);
    }

    // endregion
}
