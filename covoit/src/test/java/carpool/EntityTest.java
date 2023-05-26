package carpool;

import java.time.LocalDateTime;

import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {
    public class TestEntity extends Entity {
        public TestEntity(int id, LocalDateTime deletionDateTime) {
            super(id, deletionDateTime);
        }

        @Override
        public void create() {
            // stub method
        }

        @Override
        public Entity fromXML() {
            // stub method
            return null;
        }
    }

    @Test
    public void testGetId() {
        TestEntity e = new TestEntity(1, null);
        assertEquals(1, e.getId());
    }

    @Test
    public void testGetDeletionDateTime() {
        TestEntity e = new TestEntity(1, null);
        assertNull(e.getDeletionDateTime());
    }

    @Test
    public void testToXML() throws Exception {
        // Arrange
        LocalDateTime testDateTime = LocalDateTime.now();
        Entity entity = new TestEntity(1, testDateTime);

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        // Act
        Element result = entity.toXML(document);

        // Assert
        assertNotNull(result);
        assertEquals("root", result.getTagName());

        Node idNode = result.getElementsByTagName("id").item(0);
        assertNotNull(idNode);
        assertEquals("1", idNode.getTextContent());

        Node deletionDateNode = result.getElementsByTagName("deletion_date").item(0);
        assertNotNull(deletionDateNode);
        assertEquals(testDateTime.toString(), deletionDateNode.getTextContent());
    }
}