package db;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Database {
    private static final String path = "a.test";
    private static Database database;
    private File file;

    private Database(){
        this.file = new File(Database.path);
    }

    public static Database getDatabase(){
        if(Database.database == null)
            Database.database = new Database();
        return Database.database;
    }

    public void write(Document document) throws TransformerException
    {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(this.file);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(source, result);
    }

    public Document read() throws ParserConfigurationException, SAXException, IOException
    {
        // Parse the XML as a DOM Document.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(this.file);
        return document;
    }

    public static NodeList queryAll(Document document, String query) throws XPathExpressionException
    {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile(query);
        return (NodeList)expr.evaluate(document, XPathConstants.NODESET);
    }

    public static Node queryOne(Document document, String query) throws XPathExpressionException
    {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile(query);
        return (Node)expr.evaluate(document, XPathConstants.NODE);
    }
}
