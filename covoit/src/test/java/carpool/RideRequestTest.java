package carpool;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.time.LocalDateTime;

import javax.xml.parsers.DocumentBuilderFactory;

import static org.junit.jupiter.api.Assertions.*;

public class RideRequestTest {

    @Test
    public void testGetPassenger() {
        User passenger = new User(1, "Name", "Forename", "email@test.com", "root", false);
        RideRequest rideRequest = new RideRequest(1, "StartPoint", LocalDateTime.now(), "EndPoint", LocalDateTime.now(), passenger, null);
        assertEquals(passenger, rideRequest.getPassenger());
    }

    @Test
    public void testToXML() throws Exception {
        // Arrange
        LocalDateTime testDateTime = LocalDateTime.now();
        User testPassenger = new User(1, "Test", "Passenger", "testpassenger@email.com", "password", true);
        RideRequest rideRequest = new RideRequest(1, "Start", testDateTime, "End", testDateTime, testPassenger, testDateTime);

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        // Act
        Element result = rideRequest.toXML(document);

        // Assert
        assertNotNull(result);
        assertEquals("ride_request", result.getTagName());

        Node idNode = result.getElementsByTagName("id").item(0);
        assertNotNull(idNode);
        assertEquals("1", idNode.getTextContent());

        Node passengerNode = result.getElementsByTagName("passenger").item(0);
        assertNotNull(passengerNode);
        assertEquals("1", passengerNode.getTextContent());

        // Add more assertions for other elements based on your class structure and fields.
    }
}
