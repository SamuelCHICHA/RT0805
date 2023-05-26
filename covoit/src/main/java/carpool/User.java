package carpool;

import java.time.LocalDateTime;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class User extends Entity {
    private String name;
    private String lastname;
    private String email;
    private boolean isAdmin;
    private String password;

    public User(int id, String name, String lastname, String email, String password, boolean isAdmin, LocalDateTime deletionDateTime)
    {
        super(id, deletionDateTime);
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.isAdmin = isAdmin;
        this.password = password;
    }

    public User(int id, String name, String lastname, String email, String password, boolean isAdmin)
    {
        this(id, name, lastname, email, password, isAdmin, null);
    }

    public String getName()
    {
        return this.name;
    }

    public String getLastname()
    {
        return this.lastname;
    }

    public String getEmail()
    {
        return this.email;
    }

    public boolean isAdmin()
    {
        return this.isAdmin;
    }

    @Override
    public void create() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Element toXML(Document document) throws ParserConfigurationException {
        Element user = super.toXML(document);
        Element name = document.createElement("name");
        Element lastname = document.createElement("lastname");
        Element isAdmin = document.createElement("is_admin");
        Element email = document.createElement("email");
        Element password = document.createElement("password");
        document.renameNode(user, null, "user");
        name.appendChild(document.createTextNode(this.getName()));
        lastname.appendChild(document.createTextNode(this.getLastname()));
        email.appendChild(document.createTextNode(this.getEmail()));
        isAdmin.appendChild(document.createTextNode(this.isAdmin() ? "true" : "false"));
        password.appendChild(document.createTextNode(this.password));
        user.appendChild(name);
        user.appendChild(lastname);
        user.appendChild(email);
        user.appendChild(isAdmin);
        user.appendChild(password);
        return user;
    }

    @Override
    public Entity fromXML() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromXML'");
    }
}
