package db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import carpool.User;

public class DatabaseTest {
    @Test
    public void testReadWrite() throws ParserConfigurationException, TransformerException, SAXException, IOException
    {
        Document documentWrite = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Document documentRead;
        User user = new User(1, "Samuel", "CHICHA", "schicha@ikoula.com", "root", false);
        Database database = Database.getDatabase();
        user.toXML(documentWrite);
        database.write(documentWrite);
        documentRead = database.read();
        assertEquals("user", documentRead.getDocumentElement().getNodeName());
    }
}
