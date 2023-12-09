package Helpers;

import org.javatuples.Pair;
import org.junit.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;

/**
 * Класс для обработки файлов XML.
 * Created by Vladimir V. Klochkov on 08.02.2019.
 * Updated by Vladimir V. Klochkov on 19.08.2020.
 */
public class XMLProcessor {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final String xmlTemplateFileName;            // имя файла-шаблона XML ( полный путь к файлу )
    private final HashMap<String, String> xmlParameters; // набор параметров для подстановки в файл-шаблон XML
    private final String outFileName;                    // имя файла с результирующим XML ( полный путь к файлу )
    private Element rootElement;                         // корневой элемент дерева DOM-документа

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     ******************************************************************************************************************/
    /**
     * Инициализация данных, необходимых для генерации результирующего XML.
     *
     * @param xmlTemplateWithXmlParameters имя файла-шаблона XML и набор параметров для подстановки в данный шаблон
     * @param outFileName                  имя файла с результирующим XML
     */
    public XMLProcessor(Pair<String, HashMap<String, String>> xmlTemplateWithXmlParameters, String outFileName) {
        this.xmlTemplateFileName = new File(xmlTemplateWithXmlParameters.getValue0()).getAbsolutePath();
        Assert.assertNotNull("[ОШИБКА]: поле [xmlTemplateFileName] содержит null", xmlTemplateFileName);
        Assert.assertFalse("[ОШИБКА]: поле [xmlTemplateFileName] содержит пустую строку",
                xmlTemplateFileName.isEmpty());

        this.xmlParameters = xmlTemplateWithXmlParameters.getValue1();
        Assert.assertFalse("[ОШИБКА]: набор параметров для подстановки в шаблон пуст", xmlParameters.isEmpty());

        this.outFileName = new File(outFileName).getAbsolutePath();
        Assert.assertNotNull("[ОШИБКА]: поле [outFileName] содержит null", this.outFileName);
        Assert.assertFalse("[ОШИБКА]: поле [outFileName] содержит пустую строку", this.outFileName.isEmpty());

        this.rootElement = null;
    }

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    /**
     * Читает шаблон XML из файла на диске по указанному полному пути и преобразует его в древовидный DOM-документ.
     *
     * @return ссылку на экземпляр этого класса
     */
    public XMLProcessor readXML() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File(xmlTemplateFileName));
        Assert.assertNotNull("[ОШИБКА]: объектное представление файла XML не инициализировано", document);

        rootElement = document.getDocumentElement();
        Assert.assertNotNull("[ОШИБКА]: корневой элемент дерева DOM-документа не инициализирован", rootElement);

        return this;
    }

    /**
     * Осуществляет замену всех параметризованных значений в древовидный DOM-документе.
     *
     * @return ссылку на экземпляр этого класса
     */
    public XMLProcessor processXML() {
        xmlParameters.forEach((key, value) -> {
            try {
                setValue(key, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return this;
    }

    /**
     * Сохраняет результирующий XML в файл на диске по указанному полному пути.
     *
     * @return ссылку на экземпляр этого класса
     */
    public XMLProcessor saveXML() throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(rootElement);
        StreamResult result = new StreamResult(new File(outFileName));
        transformer.transform(source, result);

        return this;
    }

    /**
     * Возвращает результирующий XML в виде строки.
     *
     * @return результирующий XML в виде строки
     */
    public String saveXMLToString() throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        StringWriter writer = new StringWriter();
        DOMSource source = new DOMSource(rootElement);
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);

        return writer.getBuffer().toString().replaceAll("\n|\r", "");
    }

    /**
     * Устанавливает требуемое значение параметра в дереве DOM-документа по тэгу параметра или XPath к элементу.
     *
     * @param xmlParameter тэг параметра или XPath к элементу
     * @param xmlValue     новое значение параметра
     */
    private void setValue(String xmlParameter, String xmlValue) throws Exception {
        NodeList nodeList; // список найденных узлов по данному тэгу или по XPath

        // Смотрим, что к нам приехало в строке xmlParameter - просто тэг или XPath
        // Примеры:
        //   тэг   - "ns2:extendFields", "description"
        //   XPath - "//integrCode[contains(., 'etzpNoticeNumber')]/../value/string"
        if (xmlParameter.contains("/") || xmlParameter.contains("@") || xmlParameter.contains("[")) {
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            XPathExpression expression = xPath.compile(xmlParameter);
            nodeList = (NodeList) expression.evaluate(rootElement, XPathConstants.NODESET);
        } else
            nodeList = rootElement.getElementsByTagName(xmlParameter);

        for (int i = 0; i < nodeList.getLength(); i++) {
            NodeList subList = nodeList.item(i).getChildNodes();
            subList.item(0).setNodeValue(xmlValue);
        }
    }
}
