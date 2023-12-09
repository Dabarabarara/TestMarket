package Helpers;

import org.junit.Assert;

import java.util.*;

/**
 * Класс для работы с наборами сертификатов для каждого типа автоматического теста ( синглтон ).
 * Created by Vladimir V. Klochkov on 25.05.2018.
 * Updated by Alexander S. Vasyurenko on 07.05.2021.
 */
public class TestSets {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private static volatile TestSets instance;                // статический экземпляр этого класса
    private final Map<String, String> sert = new HashMap<>(); // URL для проверки соответствующего набора сертификатов
    private final Map<String, String> cust = new HashMap<>(); // URL страницы входа в личный кабинет Заказчика
    private final Map<String, String> supl = new HashMap<>(); // URL страницы входа в личный кабинет Поставщика
    private final Map<String, List<String>> sets = new HashMap<>(); // наборы сертификатов для каждого типа автотеста
    private final ConfigContainer config = ConfigContainer.getInstance();  // экземпляр ConfigContainer

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * Делаем конструктор класса приватным, чтобы не было возможности создать экземпляр класса извне.
     ******************************************************************************************************************/
    private TestSets() {
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Smoke Trade Search", config.getOpenPartCustomerLoginUrl());
        sets.put("Smoke Trade Search", new ArrayList<>());
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Smoke Supplier Personal Cabinet", config.getOpenPartCustomerLoginUrl());
        supl.put("Smoke Supplier Personal Cabinet", config.getOpenPartSupplerLoginUrl());
        sets.put("Smoke Supplier Personal Cabinet", new ArrayList<>());
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Smoke Customer Personal Cabinet", config.getOpenPartCustomerLoginUrl());
        cust.put("Smoke Customer Personal Cabinet", config.getOpenPartCustomerLoginUrl());
        sets.put("Smoke Customer Personal Cabinet", new ArrayList<>());
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Autotest Certificates Generation And Accreditation", config.getCustomerLoginUrl());
        sets.put("Autotest Certificates Generation And Accreditation", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Autotest Accreditation Of Existing Certificates", config.getCustomerLoginUrl());
        sets.put("Autotest Accreditation Of Existing Certificates", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Checking for current offers", config.getOnProdCustomerLoginUrl());
        supl.put("Checking for current offers", config.getOnProdSupplerLoginUrl());
        sets.put("Checking for current offers", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("OnProdCertificateSupplier7Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Rzhd SMB - Auction Usual", config.getOnProdCustomerLoginUrl());
        cust.put("Rzhd SMB - Auction Usual", config.getOnProdCustomerLoginUrl());
        sets.put("Rzhd SMB - Auction Usual", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateRZHDCustomer1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Rzhd SMB - Tender Usual", config.getOnProdCustomerLoginUrl());
        cust.put("Rzhd SMB - Tender Usual", config.getOnProdCustomerLoginUrl());
        sets.put("Rzhd SMB - Tender Usual", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateRZHDCustomer1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Rzhd SMB - Request For Quotations Usual", config.getOnProdCustomerLoginUrl());
        cust.put("Rzhd SMB - Request For Quotations Usual", config.getOnProdCustomerLoginUrl());
        sets.put("Rzhd SMB - Request For Quotations Usual", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateRZHDCustomer1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Rzhd SMB - Request For Proposals Usual", config.getOnProdCustomerLoginUrl());
        cust.put("Rzhd SMB - Request For Proposals Usual", config.getOnProdCustomerLoginUrl());
        sets.put("Rzhd SMB - Request For Proposals Usual", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateRZHDCustomer1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Rzhd - Auction With Limited Participation", config.getOnProdCustomerLoginUrl());
        cust.put("Rzhd - Auction With Limited Participation", config.getOnProdCustomerLoginUrl());
        sets.put("Rzhd - Auction With Limited Participation", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateRZHDCustomer1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Rzhd - Auction With Open Access", config.getOnProdCustomerLoginUrl());
        cust.put("Rzhd - Auction With Open Access", config.getOnProdCustomerLoginUrl());
        sets.put("Rzhd - Auction With Open Access", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateRZHDCustomer1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Rzhd - Tender With Open Access", config.getOnProdCustomerLoginUrl());
        cust.put("Rzhd - Tender With Open Access", config.getOnProdCustomerLoginUrl());
        sets.put("Rzhd - Tender With Open Access", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateRZHDCustomer1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Rzhd - Request For Quotations With Open Access", config.getOnProdCustomerLoginUrl());
        cust.put("Rzhd - Request For Quotations With Open Access", config.getOnProdCustomerLoginUrl());
        sets.put("Rzhd - Request For Quotations With Open Access", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateRZHDCustomer1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Rzhd - Request For Quotations With Open Access And Big File", config.getOnProdCustomerLoginUrl());
        cust.put("Rzhd - Request For Quotations With Open Access And Big File", config.getOnProdCustomerLoginUrl());
        sets.put("Rzhd - Request For Quotations With Open Access And Big File", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateRZHDCustomer1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Sending purchase to EIS", config.getCustomerLoginUrl());
        cust.put("Sending purchase to EIS", config.getCustomerLoginUrl());
        sets.put("Sending purchase to EIS", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateEISCustomer1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Plans Download From The EIS", config.getCustomerLoginUrl());
        cust.put("Plans Download From The EIS", config.getCustomerLoginUrl());
        sets.put("Plans Download From The EIS", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateEISCustomer1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Plans Importing A Tru Plan From Excel", config.getCustomerLoginUrl());
        cust.put("Plans Importing A Tru Plan From Excel", config.getCustomerLoginUrl());
        sets.put("Plans Importing A Tru Plan From Excel", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateCustomerName"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Plans Exporting A Tru Plan To Excel", config.getCustomerLoginUrl());
        cust.put("Plans Exporting A Tru Plan To Excel", config.getCustomerLoginUrl());
        sets.put("Plans Exporting A Tru Plan To Excel", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateEISCustomer1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Accreditation", config.getCustomerLoginUrl());
        cust.put("Accreditation", config.getCustomerLoginUrl());
        supl.put("Accreditation", config.getSupplierLoginUrl());
        sets.put("Accreditation", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Sending purchase to EIS and Than Protocol To EIS", config.getCustomerLoginUrl());
        cust.put("Sending purchase to EIS and Than Protocol To EIS", config.getCustomerLoginUrl());
        supl.put("Sending purchase to EIS and Than Protocol To EIS", config.getSupplierLoginUrl());
        sets.put("Sending purchase to EIS and Than Protocol To EIS", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateEISCustomer1Name"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Clear Purchase Notices", config.getCustomerLoginUrl());
        cust.put("Clear Purchase Notices", config.getCustomerLoginUrl());
        sets.put("Clear Purchase Notices", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateCustomerName"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Establishing a two-way partnership", config.getCustomerLoginUrl());
        cust.put("Establishing a two-way partnership", config.getCustomerLoginUrl());
        sets.put("Establishing a two-way partnership", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateCustomer1Name1"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Check The Supplier Menu Items", config.getOnProdCustomerLoginUrl());
        supl.put("Check The Supplier Menu Items", config.getOnProdSupplerLoginUrl());
        sets.put("Check The Supplier Menu Items", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("OnProdCertificateSupplier8Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Check The Customer Menu Items", config.getOnProdCustomerLoginUrl());
        cust.put("Check The Customer Menu Items", config.getOnProdCustomerLoginUrl());
        sets.put("Check The Customer Menu Items", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("OnProdCertificateCustomer1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Checking Revisions History In Published Notice", config.getOnProdCustomerLoginUrl());
        supl.put("Checking Revisions History In Published Notice", config.getOnProdSupplerLoginUrl());
        sets.put("Checking Revisions History In Published Notice", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("OnProdCertificateSupplier6Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Customers Negotiations And Inner Protocols Negotiations", config.getCustomerLoginUrl());
        cust.put("Customers Negotiations And Inner Protocols Negotiations", config.getCustomerLoginUrl());
        supl.put("Customers Negotiations And Inner Protocols Negotiations", config.getSupplierLoginUrl());
        sets.put("Customers Negotiations And Inner Protocols Negotiations", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"),
                config.getConfigParameter("CertificateCustomer1Name1"),
                config.getConfigParameter("CertificateCustomer1Name2"),
                config.getConfigParameter("CertificateCustomer2Name1"),
                config.getConfigParameter("CertificateCustomer2Name2"),
                config.getConfigParameter("CertificateCustomer3Name1"),
                config.getConfigParameter("CertificateCustomer3Name2"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Full Auction In Two Parts On Production", config.getOnProdCustomerLoginUrl());
        cust.put("Full Auction In Two Parts On Production", config.getOnProdCustomerLoginUrl());
        supl.put("Full Auction In Two Parts On Production", config.getOnProdSupplerLoginUrl());
        sets.put("Full Auction In Two Parts On Production", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("OnProdCertificateCustomer1Name"),
                config.getConfigParameter("OnProdCertificateSupplier1Name"),
                config.getConfigParameter("OnProdCertificateSupplier4Name"),
                config.getConfigParameter("OnProdCertificateSupplier5Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Full Tender On Production", config.getOnProdCustomerLoginUrl());
        cust.put("Full Tender On Production", config.getOnProdCustomerLoginUrl());
        supl.put("Full Tender On Production", config.getOnProdSupplerLoginUrl());
        sets.put("Full Tender On Production", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("OnProdCertificateCustomer1Name"),
                config.getConfigParameter("OnProdCertificateSupplier2Name"),
                config.getConfigParameter("OnProdCertificateSupplier3Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Full Tender", config.getCustomerLoginUrl());
        cust.put("Full Tender", config.getCustomerLoginUrl());
        supl.put("Full Tender", config.getSupplierLoginUrl());
        sets.put("Full Tender", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Full Tender With One Participant", config.getCustomerLoginUrl());
        cust.put("Full Tender With One Participant", config.getCustomerLoginUrl());
        supl.put("Full Tender With One Participant", config.getSupplierLoginUrl());
        sets.put("Full Tender With One Participant", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Application Request With One Participant And Retendering", config.getCustomerLoginUrl());
        cust.put("Application Request With One Participant And Retendering", config.getCustomerLoginUrl());
        supl.put("Application Request With One Participant And Retendering", config.getSupplierLoginUrl());
        sets.put("Application Request With One Participant And Retendering", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Application Request With Documentation Clarification Request", config.getCustomerLoginUrl());
        cust.put("Application Request With Documentation Clarification Request", config.getCustomerLoginUrl());
        supl.put("Application Request With Documentation Clarification Request", config.getSupplierLoginUrl());
        sets.put("Application Request With Documentation Clarification Request", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Request For Prices With Documentation Clarification Request", config.getCustomerLoginUrl());
        cust.put("Request For Prices With Documentation Clarification Request", config.getCustomerLoginUrl());
        supl.put("Request For Prices With Documentation Clarification Request", config.getSupplierLoginUrl());
        sets.put("Request For Prices With Documentation Clarification Request", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Full Tender 223FZ With Sending To The EIS, Correction Coefficient And Retendering",
                config.getCustomerLoginUrl());
        cust.put("Full Tender 223FZ With Sending To The EIS, Correction Coefficient And Retendering",
                config.getCustomerLoginUrl());
        supl.put("Full Tender 223FZ With Sending To The EIS, Correction Coefficient And Retendering",
                config.getSupplierLoginUrl());
        sets.put("Full Tender 223FZ With Sending To The EIS, Correction Coefficient And Retendering",
                new ArrayList<>(Arrays.asList(
                        config.getConfigParameter("CertificateAdminName"),
                        config.getConfigParameter("CertificateCustomerName"),
                        config.getConfigParameter("CertificateSupplier1Name"),
                        config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Full Auction", config.getCustomerLoginUrl());
        cust.put("Full Auction", config.getCustomerLoginUrl());
        supl.put("Full Auction", config.getSupplierLoginUrl());
        sets.put("Full Auction", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Full Auction With One Application", config.getCustomerLoginUrl());
        cust.put("Full Auction With One Application", config.getCustomerLoginUrl());
        supl.put("Full Auction With One Application", config.getSupplierLoginUrl());
        sets.put("Full Auction With One Application", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Conclusion Of Information On The Execution Of The Contract", config.getCustomerLoginUrl());
        cust.put("Conclusion Of Information On The Execution Of The Contract", config.getCustomerLoginUrl());
        supl.put("Conclusion Of Information On The Execution Of The Contract", config.getSupplierLoginUrl());
        sets.put("Conclusion Of Information On The Execution Of The Contract", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Conclusion Of Information On Termination Of The Contract", config.getCustomerLoginUrl());
        cust.put("Conclusion Of Information On Termination Of The Contract", config.getCustomerLoginUrl());
        supl.put("Conclusion Of Information On Termination Of The Contract", config.getSupplierLoginUrl());
        sets.put("Conclusion Of Information On Termination Of The Contract", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Full Auction 223FZ With Sending To The EIS", config.getCustomerLoginUrl());
        cust.put("Full Auction 223FZ With Sending To The EIS", config.getCustomerLoginUrl());
        supl.put("Full Auction 223FZ With Sending To The EIS", config.getSupplierLoginUrl());
        sets.put("Full Auction 223FZ With Sending To The EIS", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Commercial Auction With Documentation Clarification Request", config.getCustomerLoginUrl());
        cust.put("Commercial Auction With Documentation Clarification Request", config.getCustomerLoginUrl());
        supl.put("Commercial Auction With Documentation Clarification Request", config.getSupplierLoginUrl());
        sets.put("Commercial Auction With Documentation Clarification Request", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Commercial Tender With Documentation Clarification Request", config.getCustomerLoginUrl());
        cust.put("Commercial Tender With Documentation Clarification Request", config.getCustomerLoginUrl());
        supl.put("Commercial Tender With Documentation Clarification Request", config.getSupplierLoginUrl());
        sets.put("Commercial Tender With Documentation Clarification Request", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Commercial Tender And Documentation Clarification Without Request", config.getCustomerLoginUrl());
        cust.put("Commercial Tender And Documentation Clarification Without Request", config.getCustomerLoginUrl());
        supl.put("Commercial Tender And Documentation Clarification Without Request", config.getSupplierLoginUrl());
        sets.put("Commercial Tender And Documentation Clarification Without Request", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Full Auction With Protocol Of Disagreements", config.getCustomerLoginUrl());
        cust.put("Full Auction With Protocol Of Disagreements", config.getCustomerLoginUrl());
        supl.put("Full Auction With Protocol Of Disagreements", config.getSupplierLoginUrl());
        sets.put("Full Auction With Protocol Of Disagreements", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Commercial Auction With Documentation Clarification Request And Duplicate Notifications",
                config.getCustomerLoginUrl());
        cust.put("Commercial Auction With Documentation Clarification Request And Duplicate Notifications",
                config.getCustomerLoginUrl());
        supl.put("Commercial Auction With Documentation Clarification Request And Duplicate Notifications",
                config.getSupplierLoginUrl());
        sets.put("Commercial Auction With Documentation Clarification Request And Duplicate Notifications",
                new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateCustomer2Name2"),
                config.getConfigParameter("CertificateSupplier1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Commercial Auction With One Application and Assigned Responsible Employee For Signing Contract",
                config.getCustomerLoginUrl());
        cust.put("Commercial Auction With One Application and Assigned Responsible Employee For Signing Contract",
                config.getCustomerLoginUrl());
        supl.put("Commercial Auction With One Application and Assigned Responsible Employee For Signing Contract",
                config.getSupplierLoginUrl());
        sets.put("Commercial Auction With One Application and Assigned Responsible Employee For Signing Contract",
                new ArrayList<>(Arrays.asList(
                        config.getConfigParameter("CertificateAdminName"),
                        config.getConfigParameter("CertificateCustomer3Name1"),
                        config.getConfigParameter("CertificateCustomer3Name2"),
                        config.getConfigParameter("CertificateSupplier1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Commercial Tender With Positional Comparison Retendering And Several Winners",
                config.getCustomerLoginUrl());
        cust.put("Commercial Tender With Positional Comparison Retendering And Several Winners",
                config.getCustomerLoginUrl());
        supl.put("Commercial Tender With Positional Comparison Retendering And Several Winners",
                config.getSupplierLoginUrl());
        sets.put("Commercial Tender With Positional Comparison Retendering And Several Winners",
                new ArrayList<>(Arrays.asList(
                        config.getConfigParameter("CertificateAdminName"),
                        config.getConfigParameter("CertificateCustomerName"),
                        config.getConfigParameter("CertificateSupplier1Name"),
                        config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Commercial Price Request With Positional Comparison Retendering And Several Winners",
                config.getCustomerLoginUrl());
        cust.put("Commercial Price Request With Positional Comparison Retendering And Several Winners",
                config.getCustomerLoginUrl());
        supl.put("Commercial Price Request With Positional Comparison Retendering And Several Winners",
                config.getSupplierLoginUrl());
        sets.put("Commercial Price Request With Positional Comparison Retendering And Several Winners",
                new ArrayList<>(Arrays.asList(
                        config.getConfigParameter("CertificateAdminName"),
                        config.getConfigParameter("CertificateCustomerName"),
                        config.getConfigParameter("CertificateSupplier1Name"),
                        config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Positional Comparison", config.getCustomerLoginUrl());
        cust.put("Positional Comparison", config.getCustomerLoginUrl());
        supl.put("Positional Comparison", config.getSupplierLoginUrl());
        sets.put("Positional Comparison", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Request For Quotations", config.getCustomerLoginUrl());
        cust.put("Request For Quotations", config.getCustomerLoginUrl());
        supl.put("Request For Quotations", config.getSupplierLoginUrl());
        sets.put("Request For Quotations", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Full Auction With Several Lots", config.getCustomerLoginUrl());
        cust.put("Full Auction With Several Lots", config.getCustomerLoginUrl());
        supl.put("Full Auction With Several Lots", config.getSupplierLoginUrl());
        sets.put("Full Auction With Several Lots", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Full Auction With Several Lots IMP with VAT, Winner Without VAT", config.getCustomerLoginUrl());
        cust.put("Full Auction With Several Lots IMP with VAT, Winner Without VAT", config.getCustomerLoginUrl());
        supl.put("Full Auction With Several Lots IMP with VAT, Winner Without VAT", config.getSupplierLoginUrl());
        sets.put("Full Auction With Several Lots IMP with VAT, Winner Without VAT", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Request For Quotations With Positional Comparison", config.getCustomerLoginUrl());
        cust.put("Request For Quotations With Positional Comparison", config.getCustomerLoginUrl());
        supl.put("Request For Quotations With Positional Comparison", config.getSupplierLoginUrl());
        sets.put("Request For Quotations With Positional Comparison", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Request For Quotations With Summary Protocol And Retendering", config.getCustomerLoginUrl());
        cust.put("Request For Quotations With Summary Protocol And Retendering", config.getCustomerLoginUrl());
        supl.put("Request For Quotations With Summary Protocol And Retendering", config.getSupplierLoginUrl());
        sets.put("Request For Quotations With Summary Protocol And Retendering", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Full Auction 615", config.getCustomerLoginUrl());
        cust.put("Full Auction 615", config.getCustomerLoginUrl());
        supl.put("Full Auction 615", config.getSupplierLoginUrl());
        sets.put("Full Auction 615", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("Certificate615Customer1Name"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Verification Of Work RKPO 615", config.getCustomerLoginUrl());
        cust.put("Verification Of Work RKPO 615", config.getCustomerLoginUrl());
        supl.put("Verification Of Work RKPO 615", config.getSupplierLoginUrl());
        sets.put("Verification Of Work RKPO 615", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("Certificate615Customer1Name"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"),
                config.getConfigParameter("CertificateSupplier3Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Verification Of Work Pre-selection 615", config.getCustomerLoginUrl());
        cust.put("Verification Of Work Pre-selection 615", config.getCustomerLoginUrl());
        supl.put("Verification Of Work Pre-selection 615", config.getSupplierLoginUrl());
        sets.put("Verification Of Work Pre-selection 615", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("Certificate615Customer1Name"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"),
                config.getConfigParameter("CertificateSupplier3Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Competitive Procurement - Tender", config.getCustomerLoginUrl());
        cust.put("Competitive Procurement - Tender", config.getCustomerLoginUrl());
        supl.put("Competitive Procurement - Tender", config.getSupplierLoginUrl());
        sets.put("Competitive Procurement - Tender", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Competitive Tender With Full Contract Check", config.getCustomerLoginUrl());
        cust.put("Competitive Tender With Full Contract Check", config.getCustomerLoginUrl());
        supl.put("Competitive Tender With Full Contract Check", config.getSupplierLoginUrl());
        sets.put("Competitive Tender With Full Contract Check", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Competitive Tender With Full Contract Addendum Check", config.getCustomerLoginUrl());
        cust.put("Competitive Tender With Full Contract Addendum Check", config.getCustomerLoginUrl());
        supl.put("Competitive Tender With Full Contract Addendum Check", config.getSupplierLoginUrl());
        sets.put("Competitive Tender With Full Contract Addendum Check", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Competitive Tender With Access Opening Protocol And Retendering", config.getCustomerLoginUrl());
        cust.put("Competitive Tender With Access Opening Protocol And Retendering", config.getCustomerLoginUrl());
        supl.put("Competitive Tender With Access Opening Protocol And Retendering", config.getSupplierLoginUrl());
        sets.put("Competitive Tender With Access Opening Protocol And Retendering", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Competitive Tender Application In Two Parts With Access Opening And Summary Protocols",
                config.getCustomerLoginUrl());
        cust.put("Competitive Tender Application In Two Parts With Access Opening And Summary Protocols",
                config.getCustomerLoginUrl());
        supl.put("Competitive Tender Application In Two Parts With Access Opening And Summary Protocols",
                config.getSupplierLoginUrl());
        sets.put("Competitive Tender Application In Two Parts With Access Opening And Summary Protocols",
                new ArrayList<>(Arrays.asList(
                        config.getConfigParameter("CertificateAdminName"),
                        config.getConfigParameter("CertificateCustomerName"),
                        config.getConfigParameter("CertificateSupplier1Name"),
                        config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Competitive Tender Checkbox Specify Information About All Rejected Participants Switch Off",
                config.getCustomerLoginUrl());
        cust.put("Competitive Tender Checkbox Specify Information About All Rejected Participants Switch Off",
                config.getCustomerLoginUrl());
        supl.put("Competitive Tender Checkbox Specify Information About All Rejected Participants Switch Off",
                config.getSupplierLoginUrl());
        sets.put("Competitive Tender Checkbox Specify Information About All Rejected Participants Switch Off",
                new ArrayList<>(Arrays.asList(
                        config.getConfigParameter("CertificateAdminName"),
                        config.getConfigParameter("CertificateCustomerName"),
                        config.getConfigParameter("CertificateSupplier1Name"),
                        config.getConfigParameter("CertificateSupplier2Name"),
                        config.getConfigParameter("CertificateSupplier3Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Competitive Tender Switch On Checkbox Specify Information About All Rejected Participants",
                config.getCustomerLoginUrl());
        cust.put("Competitive Tender Switch On Checkbox Specify Information About All Rejected Participants",
                config.getCustomerLoginUrl());
        supl.put("Competitive Tender Switch On Checkbox Specify Information About All Rejected Participants",
                config.getSupplierLoginUrl());
        sets.put("Competitive Tender Switch On Checkbox Specify Information About All Rejected Participants",
                new ArrayList<>(Arrays.asList(
                        config.getConfigParameter("CertificateAdminName"),
                        config.getConfigParameter("CertificateCustomer8Name"),
                        config.getConfigParameter("CertificateSupplier1Name"),
                        config.getConfigParameter("CertificateSupplier2Name"),
                        config.getConfigParameter("CertificateSupplier3Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Competitive Auction Application In Two Parts One Application",
                config.getCustomerLoginUrl());
        cust.put("Competitive Auction Application In Two Parts One Application",
                config.getCustomerLoginUrl());
        supl.put("Competitive Auction Application In Two Parts One Application",
                config.getSupplierLoginUrl());
        sets.put("Competitive Auction Application In Two Parts One Application",
                new ArrayList<>(Arrays.asList(
                        config.getConfigParameter("CertificateAdminName"),
                        config.getConfigParameter("CertificateCustomerName"),
                        config.getConfigParameter("CertificateSupplier1Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Competitive Auction Application With Auto-Publish Auction Protocol And Summary Protocol",
                config.getCustomerLoginUrl());
        cust.put("Competitive Auction Application With Auto-Publish Auction Protocol And Summary Protocol",
                config.getCustomerLoginUrl());
        supl.put("Competitive Auction Application With Auto-Publish Auction Protocol And Summary Protocol",
                config.getSupplierLoginUrl());
        sets.put("Competitive Auction Application With Auto-Publish Auction Protocol And Summary Protocol",
                new ArrayList<>(Arrays.asList(
                        config.getConfigParameter("CertificateAdminName"),
                        config.getConfigParameter("CertificateCustomer9Name"),
                        config.getConfigParameter("CertificateSupplier1Name"),
                        config.getConfigParameter("CertificateSupplier2Name"),
                        config.getConfigParameter("CertificateSupplier3Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Customer Additional Fields Usage In Purchase Creation", config.getCustomerLoginUrl());
        cust.put("Customer Additional Fields Usage In Purchase Creation", config.getCustomerLoginUrl());
        sets.put("Customer Additional Fields Usage In Purchase Creation", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateCustomer5Name"),
                config.getConfigParameter("CertificateCustomer6Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Request For Clarification Of The Application With Sending To The EIS", config.getCustomerLoginUrl());
        cust.put("Request For Clarification Of The Application With Sending To The EIS", config.getCustomerLoginUrl());
        supl.put("Request For Clarification Of The Application With Sending To The EIS", config.getSupplierLoginUrl());
        sets.put("Request For Clarification Of The Application With Sending To The EIS", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Small And Medium-sized Business - Tender", config.getCustomerLoginUrl());
        cust.put("Small And Medium-sized Business - Tender", config.getCustomerLoginUrl());
        supl.put("Small And Medium-sized Business - Tender", config.getSupplierLoginUrl());
        sets.put("Small And Medium-sized Business - Tender", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Joint Auction Commercial Organizer Is Not Among Customers", config.getCustomerLoginUrl());
        cust.put("Joint Auction Commercial Organizer Is Not Among Customers", config.getCustomerLoginUrl());
        supl.put("Joint Auction Commercial Organizer Is Not Among Customers", config.getSupplierLoginUrl());
        sets.put("Joint Auction Commercial Organizer Is Not Among Customers", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"),
                config.getConfigParameter("CertificateCustomer5Name"),
                config.getConfigParameter("CertificateCustomer6Name"),
                config.getConfigParameter("CertificateCustomer7Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Joint Auction Commercial Organizer Is Among Customers", config.getCustomerLoginUrl());
        cust.put("Joint Auction Commercial Organizer Is Among Customers", config.getCustomerLoginUrl());
        supl.put("Joint Auction Commercial Organizer Is Among Customers", config.getSupplierLoginUrl());
        sets.put("Joint Auction Commercial Organizer Is Among Customers", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"),
                config.getConfigParameter("CertificateCustomer5Name"),
                config.getConfigParameter("CertificateCustomer6Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Uncompetitive Preliminary Selection With Extension And Several Lots", config.getCustomerLoginUrl());
        cust.put("Uncompetitive Preliminary Selection With Extension And Several Lots", config.getCustomerLoginUrl());
        supl.put("Uncompetitive Preliminary Selection With Extension And Several Lots", config.getSupplierLoginUrl());
        sets.put("Uncompetitive Preliminary Selection With Extension And Several Lots", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateCustomerName"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Sending Manual Notification From The Customer's Personal Cabinet", config.getCustomerLoginUrl());
        cust.put("Sending Manual Notification From The Customer's Personal Cabinet", config.getCustomerLoginUrl());
        sets.put("Sending Manual Notification From The Customer's Personal Cabinet", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateCustomer5Name"),
                config.getConfigParameter("CertificateCustomer6Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Sending Manual Notification From The Supplier's Personal Cabinet", config.getCustomerLoginUrl());
        supl.put("Sending Manual Notification From The Supplier's Personal Cabinet", config.getSupplierLoginUrl());
        sets.put("Sending Manual Notification From The Supplier's Personal Cabinet", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Request For Explanation Of Results", config.getOnProdCustomerLoginUrl());
        cust.put("Request For Explanation Of Results", config.getOnProdCustomerLoginUrl());
        supl.put("Request For Explanation Of Results", config.getOnProdSupplerLoginUrl());
        sets.put("Request For Explanation Of Results", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("OnProdCertificateCustomer1Name"),
                config.getConfigParameter("OnProdCertificateSupplier7Name"))));
        //--------------------------------------------------------------------------------------------------------------
        sert.put("Joint contest SMB - without sending to EIS", config.getCustomerLoginUrl());
        cust.put("Joint contest SMB - without sending to EIS", config.getCustomerLoginUrl());
        supl.put("Joint contest SMB - without sending to EIS", config.getSupplierLoginUrl());
        sets.put("Joint contest SMB - without sending to EIS", new ArrayList<>(Arrays.asList(
                config.getConfigParameter("CertificateAdminName"),
                config.getConfigParameter("CertificateSupplier1Name"),
                config.getConfigParameter("CertificateSupplier2Name"),
                config.getConfigParameter("CertificateCustomer5Name"),
                config.getConfigParameter("CertificateCustomer6Name"))));
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Метод доступа к экземпляру этого класса.
     ******************************************************************************************************************/
    /**
     * Возвращает экземпляр этого класса ( синглтон ).
     *
     * @return экземпляр этого класса
     */
    public static TestSets getInstance() {
        if (instance == null) {
            synchronized (TestSets.class) {
                if (instance == null) {
                    instance = new TestSets();
                }
            }
        }
        return instance;
    }

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    /**
     * Возвращает URL для указанного типа автоматического теста ( где можно проверить набор сертификатов ).
     *
     * @param testType тип автоматического теста
     * @return URL для указанного типа автоматического теста ( где можно проверить набор сертификатов )
     */
    public String getCertificatesCheckPageUrl(String testType) {

        // Проверка набора URL
        Assert.assertFalse("[ОШИБКА]: набор URL отсутствует ( не инициализирован )", sert.isEmpty());

        // Проверка переданного параметра
        Assert.assertNotNull("[ОШИБКА]: переданный параметр [testType] - null", testType);
        Assert.assertFalse("[ОШИБКА]: переданный параметр [testType] - пустая строка", testType.isEmpty());

        // Поиск в наборе URL требуемого URL
        String result = sert.get(testType);

        // Проверка результата поиска
        Assert.assertNotNull(String.format("[ОШИБКА]: [%s] - требуемый ключ не найден в наборе URL", testType), result);

        return result;
    }

    /**
     * Возвращает URL страницы входа в личный кабинет Заказчика.
     *
     * @param testType тип автоматического теста
     * @return URL страницы входа в личный кабинет Заказчика
     */
    public String getCustomersLoginPageUrl(String testType) {

        // Проверка набора URL
        Assert.assertFalse("[ОШИБКА]: набор URL отсутствует ( не инициализирован )", cust.isEmpty());

        // Проверка переданного параметра
        Assert.assertNotNull("[ОШИБКА]: переданный параметр [testType] - null", testType);
        Assert.assertFalse("[ОШИБКА]: переданный параметр [testType] - пустая строка", testType.isEmpty());

        // Поиск в наборе URL требуемого URL
        String result = cust.get(testType);

        // Проверка результата поиска
        Assert.assertNotNull(String.format("[ОШИБКА]: [%s] - требуемый ключ не найден в наборе URL", testType), result);

        return result;
    }

    /**
     * Возвращает URL страницы входа в личный кабинет Поставщика или Оператора.
     *
     * @param testType тип автоматического теста
     * @return URL страницы входа в личный кабинет Поставщика или Оператора
     */
    public String getSuppliersLoginPageUrl(String testType) {

        // Проверка набора URL
        Assert.assertFalse("[ОШИБКА]: набор URL отсутствует ( не инициализирован )", supl.isEmpty());

        // Проверка переданного параметра
        Assert.assertNotNull("[ОШИБКА]: переданный параметр [testType] - null", testType);
        Assert.assertFalse("[ОШИБКА]: переданный параметр [testType] - пустая строка", testType.isEmpty());

        // Поиск в наборе URL требуемого URL
        String result = supl.get(testType);

        // Проверка результата поиска
        Assert.assertNotNull(String.format("[ОШИБКА]: [%s] - требуемый ключ не найден в наборе URL", testType), result);

        return result;
    }

    /**
     * Возвращает набор сертификатов для указанного типа автоматического теста.
     *
     * @param testType тип автоматического теста
     * @return набор сертификатов
     */
    public List<String> getCertificateSetForThisTestType(String testType) {

        // Проверка наборов сертификатов
        Assert.assertFalse("[ОШИБКА]: наборы сертификатов отсутствуют ( не инициализированы )", sets.isEmpty());

        // Проверка переданного параметра
        Assert.assertNotNull("[ОШИБКА]: переданный параметр [testType] - null", testType);
        Assert.assertFalse("[ОШИБКА]: переданный параметр [testType] - пустая строка", testType.isEmpty());

        // Поиск в наборах сертификатов требуемого набора
        List<String> result = sets.get(testType);

        // Проверка результата поиска
        Assert.assertNotNull(String.format("[ОШИБКА]: [%s] - требуемый ключ не найден в списке наборов сертификатов",
                testType), result);
        if (result.size() == 0) System.out.println("[ИНФОРМАЦИЯ]: этот тип теста не требует наличия сертификатов.");

        return result;
    }
}
