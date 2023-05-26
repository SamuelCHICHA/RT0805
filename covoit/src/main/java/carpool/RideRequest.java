package carpool;

import java.time.LocalDateTime;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class RideRequest extends Ride{
    private User passenger;

    public RideRequest(int id, String startPoint, LocalDateTime startDateTime, String endPoint, LocalDateTime endDateTime, User passenger, LocalDateTime deletionDateTime)
    {
        super(id, startPoint, startDateTime, endPoint, endDateTime, deletionDateTime);
        this.passenger = passenger;
    }

    public User getPassenger()
    {
        return this.passenger;
    }

    @Override
    public void create() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Element toXML(Document document) throws ParserConfigurationException {
        Element rideRequestElt = super.toXML(document);
        Element passengerElt = document.createElement("passenger");
        document.renameNode(rideRequestElt, null, "ride_request");
        passengerElt.appendChild(document.createTextNode(String.valueOf(this.getPassenger().getId())));
        rideRequestElt.appendChild(passengerElt);
        return rideRequestElt;
    }

    @Override
    public Entity fromXML() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromXML'");
    }
}
