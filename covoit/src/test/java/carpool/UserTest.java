package carpool;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import javax.xml.parsers.DocumentBuilderFactory;


public class UserTest {

    @Test
    public void testGetName() {
        User user = new User(1, "Name", "Lastname", "email@test.com", "root", false);
        assertEquals("Name", user.getName());
    }

    @Test
    public void testGetForname() {
        User user = new User(1, "Name", "Lastname", "email@test.com", "root", false);
        assertEquals("Lastname", user.getLastname());
    }

    @Test
    public void testGetEmail() {
        User user = new User(1, "Name", "Lastname", "email@test.com", "root", false);
        assertEquals("email@test.com", user.getEmail());
    }

    @Test
    public void testIsAdmin() {
        User user = new User(1, "Name", "Lastname", "email@test.com", "root", true);
        assertTrue(user.isAdmin());
    }

    @Test
    public void testToXML() throws Exception {
        // Arrange
        LocalDateTime testDateTime = LocalDateTime.now();
        User user = new User(1, "Test", "User", "testuser@email.com", "password", true, testDateTime);

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        // Act
        Element result = user.toXML(document);

        // Assert
        assertNotNull(result);
        assertEquals("user", result.getTagName());

        Node idNode = result.getElementsByTagName("id").item(0);
        assertNotNull(idNode);
        assertEquals("1", idNode.getTextContent());

        Node nameNode = result.getElementsByTagName("name").item(0);
        assertNotNull(nameNode);
        assertEquals("Test", nameNode.getTextContent());

        Node lastnameNode = result.getElementsByTagName("lastname").item(0);
        assertNotNull(lastnameNode);
        assertEquals("User", lastnameNode.getTextContent());

        Node emailNode = result.getElementsByTagName("email").item(0);
        assertNotNull(emailNode);
        assertEquals("testuser@email.com", emailNode.getTextContent());

        Node isAdminNode = result.getElementsByTagName("is_admin").item(0);
        assertNotNull(isAdminNode);
        assertEquals("true", isAdminNode.getTextContent());

        // Add more assertions for other elements based on your class structure and fields.
    }
}
