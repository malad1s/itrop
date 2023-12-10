
package JaxbClasses;

import jakarta.xml.bind.annotation.*;

import java.math.BigInteger;


/**
 * <p>Java class for User complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="User"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="firstname" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="password" type="{http://it.nure.ua/matsuytskij/xml/password}PasswordType"/&gt;
 *         &lt;element name="email" type="{http://it.nure.ua/matsuytskij/xml/emailtype}EmailType"/&gt;
 *         &lt;element name="role" type="{http://it.nure.ua/matsuytskij/xml/role}Role"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlRootElement(name = "User", namespace = "http://it.nure.ua/matsuytskij/xml/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "User", namespace = "http://it.nure.ua/matsuytskij/xml/user", propOrder = {
    "id",
    "surname",
    "firstname",
    "password",
    "email",
    "role"
})
public class User {

    @XmlElement(namespace = "http://it.nure.ua/matsuytskij/xml/user")
    protected int id;
    @XmlElement(namespace = "http://it.nure.ua/matsuytskij/xml/user", required = true)
    protected String surname;
    @XmlElement(namespace = "http://it.nure.ua/matsuytskij/xml/user", required = true)
    protected String firstname;
    @XmlElement(namespace = "http://it.nure.ua/matsuytskij/xml/user", required = true)
    protected String password;
    @XmlElement(namespace = "http://it.nure.ua/matsuytskij/xml/user", required = true)
    protected String email;
    @XmlElement(namespace = "http://it.nure.ua/matsuytskij/xml/user", required = true)
    protected Role role;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the surname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the value of the surname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurname(String value) {
        this.surname = value;
    }

    /**
     * Gets the value of the firstname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the value of the firstname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstname(String value) {
        this.firstname = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link Role }
     *     
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link Role }
     *     
     */
    public void setRole(Role value) {
        this.role = value;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
