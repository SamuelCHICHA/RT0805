package carpool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class RideTest {
    public class TestRide extends Ride
    {
        public TestRide(int id, String startPoint, LocalDateTime startDateTime, String endPoint, LocalDateTime endDateTime, LocalDateTime deletionDateTime)
        {
            super(id, startPoint, startDateTime, endPoint, endDateTime, deletionDateTime);
        }

        @Override
        public void create() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'create'");
        }

        @Override
        public Entity fromXML() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'fromXML'");
        }

        @Test
        public void testToXML() throws Exception {
            // Arrange
            LocalDateTime testDateTime = LocalDateTime.now();
            Ride ride = new TestRide(1, "StartPoint", testDateTime, "EndPoint", testDateTime, testDateTime);
    
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    
            // Act
            Element result = ride.toXML(document);
    
            // Assert
            assertNotNull(result);
            assertEquals("ride", result.getTagName());
    
            Node idNode = result.getElementsByTagName("id").item(0);
            assertNotNull(idNode);
            assertEquals("1", idNode.getTextContent());
    
            Node startPointNode = result.getElementsByTagName("start_point").item(0);
            assertNotNull(startPointNode);
            assertEquals("StartPoint", startPointNode.getTextContent());
    
            Node endPointNode = result.getElementsByTagName("end_point").item(0);
            assertNotNull(endPointNode);
            assertEquals("EndPoint", endPointNode.getTextContent());
    
            // Assert the start and end times, need to adjust the format according to your implementation
            Node startTimeNode = result.getElementsByTagName("start_time").item(0);
            assertNotNull(startTimeNode);
            assertEquals(testDateTime.toString(), startTimeNode.getTextContent());
    
            Node endTimeNode = result.getElementsByTagName("end_time").item(0);
            assertNotNull(endTimeNode);
            assertEquals(testDateTime.toString(), endTimeNode.getTextContent());
    
            // Add more assertions for other elements based on your class structure and fields.
        }
    }
}
