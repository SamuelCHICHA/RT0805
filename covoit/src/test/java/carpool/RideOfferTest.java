package carpool;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import java.time.LocalDateTime;
import java.util.Arrays;

public class RideOfferTest {
    private User driver;
    private User[] passengers;
    private int nbSeats;

    @BeforeEach
    public void setup(){
        // Create test objects
        driver = new User(1, "John", "Cena", "john.cena@gmail.com", "root", false);
        passengers = new User[]{new User(2, "Alice", "Wonder", "alice.wonder@gmail.com", "root", false), new User(3, "Bob", "Lemon", "bob.lemon@gmail.com", "root", false)};
        nbSeats = 4;
    }

    @Test
    public void testGetDriver(){
        // Create a ride offer object

        RideOffer rideOffer = new RideOffer(1, "StartPoint", LocalDateTime.now(), "EndPoint", LocalDateTime.now(), driver, passengers, nbSeats);

        // Test the getDriver() method
        User actualDriver = rideOffer.getDriver();

        // Assert that the returned driver is the same as the driver object used during initialization
        assertEquals(driver, actualDriver);
    }

    @Test
    public void testGetPassengers() {
        // Create a ride offer object
        RideOffer rideOffer = new RideOffer(1, "StartPoint", LocalDateTime.now(), "EndPoint", LocalDateTime.now(), driver, passengers, nbSeats);

        // Test the getPassengers() method
        List<User> actualPassengers = rideOffer.getPassengers();

        // Assert that the returned passengers list is the same as the passengers array used during initialization
        assertEquals(Arrays.asList(passengers), actualPassengers);
    }

    @Test
    public void testGetNbSeats() {
        // Create a ride offer object
        RideOffer rideOffer = new RideOffer(1, "StartPoint", LocalDateTime.now(), "EndPoint", LocalDateTime.now(), driver, passengers, nbSeats);

        // Test the getNbSeats() method
        int actualNbSeats = rideOffer.getNbSeats();

        // Assert that the returned nbSeats is the same as the nbSeats value used during initialization
        assertEquals(nbSeats, actualNbSeats);
    }

    @Test
    public void testToXML() throws Exception {
        // Arrange
        LocalDateTime testDateTime = LocalDateTime.now();
        RideOffer rideOffer = new RideOffer(1, "Start", testDateTime, "End", testDateTime, driver, passengers, nbSeats, null);
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        // Act
        Element result = rideOffer.toXML(document);

        // Assert
        assertNotNull(result);
        assertEquals("ride_offer", result.getTagName());

        Node idNode = result.getElementsByTagName("id").item(0);
        assertNotNull(idNode);
        assertEquals("1", idNode.getTextContent());

        Node driverNode = result.getElementsByTagName("driver").item(0);
        assertNotNull(driverNode);
        assertEquals("1", driverNode.getTextContent());
        
        // Add more assertions for other elements based on your class structure and fields.
    }

}
