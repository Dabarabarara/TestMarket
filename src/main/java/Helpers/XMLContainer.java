package Helpers;

import Helpers.RzhdMaps.*;
import org.javatuples.Pair;
import org.junit.Assert;

import java.util.HashMap;

/**
 * Класс для хранения информации о файлах-шаблонах XML и о наборах параметров для каждого такого файла ( синглтон ).
 * Created by Vladimir V. Klochkov on 07.02.2019.
 * Updated by Vladimir V. Klochkov on 30.08.2019.
 */
public class XMLContainer {
    /*******************************************************************************************************************
     * Константы класса.
     ******************************************************************************************************************/
    // Аукцион в электронной форме, участниками которого могут быть только субъекты МСП (AEMSP.xml)
    private static final String SMB_AUC_CR_NAME = "Rzhd SMB - Auction Usual/CreateNotice";
    private static final String SMB_AUC_UP_NAME = "Rzhd SMB - Auction Usual/UpdateNotice";

    // Конкурс в электронной форме, участниками которого могут быть только субъекты МСП (KEMSP.xml)
    private static final String SMB_TEN_CR_NAME = "Rzhd SMB - Tender Usual/CreateNotice";
    private static final String SMB_TEN_UP_NAME = "Rzhd SMB - Tender Usual/UpdateNotice";

    // Запрос котировок в электронной форме, участниками которого могут быть только субъекты МСП (ZKTEMPS.xml)
    private static final String SMB_RFQ_CR_NAME = "Rzhd SMB - Request For Quotations Usual/CreateNotice";
    private static final String SMB_RFQ_UP_NAME = "Rzhd SMB - Request For Quotations Usual/UpdateNotice";

    // Запрос предложений в электронной форме, участниками которого могут быть только субъекты МСП (ZPEMSP.xml)
    private static final String SMB_RFP_CR_NAME = "Rzhd SMB - Request For Proposals Usual/CreateNotice";
    private static final String SMB_RFP_UP_NAME = "Rzhd SMB - Request For Proposals Usual/UpdateNotice";

    // Аукцион с ограниченным участием в электронной форме (AOE.xml)
    private static final String AUC_WLP_CR_NAME = "Rzhd - Auction With Limited Participation/CreateNotice";
    private static final String AUC_WLP_UP_NAME = "Rzhd - Auction With Limited Participation/UpdateNotice";

    // Открытый аукцион в электронной форме (OAE.xml)
    private static final String AUC_OPN_CR_NAME = "Rzhd - Auction With Open Access/CreateNotice";
    private static final String AUC_OPN_UP_NAME = "Rzhd - Auction With Open Access/UpdateNotice";

    // Открытый конкурс в электронной форме (OKE.xml)
    private static final String TEN_OPN_CR_NAME = "Rzhd - Tender With Open Access/CreateNotice";
    private static final String TEN_OPN_UP_NAME = "Rzhd - Tender With Open Access/UpdateNotice";

    // Запрос котировок в электронной форме (ZKTE.xml)
    private static final String RFQ_OPN_CR_NAME = "Rzhd - Request For Quotations With Open Access/CreateNotice";
    private static final String RFQ_OPN_UP_NAME = "Rzhd - Request For Quotations With Open Access/UpdateNotice";

    // Запрос котировок в электронной форме с большим файлом (ZKTE_BIG.xml)
    private static final String RFQ_OPN_BIG_CR_NAME =
            "Rzhd - Request For Quotations With Open Access And Big File/CreateNotice";
    private static final String RFQ_OPN_BIG_UP_NAME =
            "Rzhd - Request For Quotations With Open Access And Big File/UpdateNotice";

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private static volatile XMLContainer instance;                        // статический экземпляр этого класса
    private final ConfigContainer config = ConfigContainer.getInstance(); // экземпляр ConfigContainer
    private final Timer timer = new Timer();                              // класс для работы со временными интервалами

    // Таблица связей имён тестов с файлами-шаблонами XML и наборами параметров ----------------------------------------
    private final HashMap<String, Pair<String, HashMap<String, String>>> catalog =
            new HashMap<String, Pair<String, HashMap<String, String>>>();
    //------------------------------------------------------------------------------------------------------------------

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * Делаем конструктор класса приватным, чтобы не было возможности создать экземпляр класса извне.
     ******************************************************************************************************************/
    private XMLContainer() {
        final String SMB_AUC_XML_FILE = config.getConfigParameter("RzhdAEMSPModel");
        final String SMB_TEN_XML_FILE = config.getConfigParameter("RzhdKEMSPModel");
        final String SMB_RFQ_XML_FILE = config.getConfigParameter("RzhdZKTEMSPModel");
        final String SMB_RFP_XML_FILE = config.getConfigParameter("RzhdZPEMSPModel");
        final String AUC_WLP_XML_FILE = config.getConfigParameter("RzhdAOEModel");
        final String AUC_OPN_XML_FILE = config.getConfigParameter("RzhdOAEModel");
        final String TEN_OPN_XML_FILE = config.getConfigParameter("RzhdOKEModel");
        final String RFQ_OPN_XML_FILE = config.getConfigParameter("RzhdZKTEModel");
        final String RFQ_OPN_BIG_XML_FILE = config.getConfigParameter("RzhdZKTEBigModel");

        // region Формируем уникальный номер закупки от РЖД и сохраняем его в параметрах теста

        config.setParameter("PurchaseNumber", String.format("%s %s", config.getParameter("TestType"),
                timer.dateToResponseFormatWithSeconds(Timer.getPublishNoticeDate()).replace("T", " ")));

        // endregion

        // region Формируем таблицу связей имён тестов с файлами-шаблонами XML и наборами параметров

        catalog.put(SMB_AUC_CR_NAME, new Pair<>(SMB_AUC_XML_FILE, new RzhdSMBAuctionCreate().getParams()));
        catalog.put(SMB_AUC_UP_NAME, new Pair<>(SMB_AUC_XML_FILE, new RzhdSMBAuctionUpdate().getParams()));
        catalog.put(SMB_TEN_CR_NAME, new Pair<>(SMB_TEN_XML_FILE, new RzhdSMBTenderCreate().getParams()));
        catalog.put(SMB_TEN_UP_NAME, new Pair<>(SMB_TEN_XML_FILE, new RzhdSMBTenderUpdate().getParams()));
        catalog.put(SMB_RFQ_CR_NAME, new Pair<>(SMB_RFQ_XML_FILE, new RzhdSMBRFQCreate().getParams()));
        catalog.put(SMB_RFQ_UP_NAME, new Pair<>(SMB_RFQ_XML_FILE, new RzhdSMBRFQUpdate().getParams()));
        catalog.put(SMB_RFP_CR_NAME, new Pair<>(SMB_RFP_XML_FILE, new RzhdSMBRFPCreate().getParams()));
        catalog.put(SMB_RFP_UP_NAME, new Pair<>(SMB_RFP_XML_FILE, new RzhdSMBRFPUpdate().getParams()));
        catalog.put(AUC_WLP_CR_NAME, new Pair<>(AUC_WLP_XML_FILE, new RzhdAuctionWLPCreate().getParams()));
        catalog.put(AUC_WLP_UP_NAME, new Pair<>(AUC_WLP_XML_FILE, new RzhdAuctionWLPUpdate().getParams()));
        catalog.put(AUC_OPN_CR_NAME, new Pair<>(AUC_OPN_XML_FILE, new RzhdAuctionOPNCreate().getParams()));
        catalog.put(AUC_OPN_UP_NAME, new Pair<>(AUC_OPN_XML_FILE, new RzhdAuctionOPNUpdate().getParams()));
        catalog.put(TEN_OPN_CR_NAME, new Pair<>(TEN_OPN_XML_FILE, new RzhdTenderOPNCreate().getParams()));
        catalog.put(TEN_OPN_UP_NAME, new Pair<>(TEN_OPN_XML_FILE, new RzhdTenderOPNUpdate().getParams()));
        catalog.put(RFQ_OPN_CR_NAME, new Pair<>(RFQ_OPN_XML_FILE, new RzhdRFQOPNCreate().getParams()));
        catalog.put(RFQ_OPN_UP_NAME, new Pair<>(RFQ_OPN_XML_FILE, new RzhdRFQOPNUpdate().getParams()));
        catalog.put(RFQ_OPN_BIG_CR_NAME, new Pair<>(RFQ_OPN_BIG_XML_FILE, new RzhdRFQOPNBigCreate().getParams()));
        catalog.put(RFQ_OPN_BIG_UP_NAME, new Pair<>(RFQ_OPN_BIG_XML_FILE, new RzhdRFQOPNBigUpdate().getParams()));

        // endregion
    }

    /*******************************************************************************************************************
     * Метод доступа к экземпляру этого класса.
     ******************************************************************************************************************/
    /**
     * Возвращает экземпляр этого класса ( синглтон ).
     *
     * @return экземпляр этого класса
     */
    public static XMLContainer getInstance() {
        if (instance == null) {
            synchronized (XMLContainer.class) {
                if (instance == null) {
                    instance = new XMLContainer();
                }
            }
        }
        return instance;
    }

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    /**
     * Возвращает имя файла-шаблона XML и набор параметров для подстановки в данный шаблон.
     *
     * @param testNameWsPf имя теста вместе с постфиксом, например [Rzhd SMB - Auction Usual/CreateNotice]
     * @return имя файла-шаблона XML и набор параметров для подстановки в данный шаблон
     */
    public Pair<String, HashMap<String, String>> getXMLWithParams(String testNameWsPf) {
        Assert.assertNotNull("[ОШИБКА]: параметр [testNameWsPf] содержит null", testNameWsPf);
        Assert.assertFalse("[ОШИБКА]: параметр [testNameWsPf] содержит пустую строку", testNameWsPf.isEmpty());
        Assert.assertTrue("[ОШИБКА]: ключ [testNameWsPf] не найден", catalog.containsKey(testNameWsPf));
        return catalog.get(testNameWsPf);
    }
}
