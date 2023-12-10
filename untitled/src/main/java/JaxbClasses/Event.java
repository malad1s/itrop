
package JaxbClasses;

import java.math.BigInteger;

import jakarta.xml.bind.annotation.*;


/**
 * <p>Java class for Event complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Event"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="id_event" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="place" type="{http://it.nure.ua/matsuytskij/xml/place}Place"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="pinned"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *               &lt;enumeration value="0"/&gt;
 *               &lt;enumeration value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/all&gt;
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="organizer" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlRootElement(name = "Event", namespace = "http://it.nure.ua/matsuytskij/xml/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Event", namespace = "http://it.nure.ua/matsuytskij/xml/event", propOrder = {
        "id_event", "name", "date", "place", "description", "pinned", "time"

})
public class Event {

    @XmlElement(name = "id_event", namespace = "http://it.nure.ua/matsuytskij/xml/event", required = true)
    protected BigInteger id_event;
    @XmlElement(namespace = "http://it.nure.ua/matsuytskij/xml/event", required = true)
    protected String name;
    @XmlElement(namespace = "http://it.nure.ua/matsuytskij/xml/event", required = true)
    protected String date;
    @XmlElement(namespace = "http://it.nure.ua/matsuytskij/xml/event", required = true)
    protected Place place;
    @XmlElement(namespace = "http://it.nure.ua/matsuytskij/xml/event", required = true)
    protected String description;
    @XmlElement(namespace = "http://it.nure.ua/matsuytskij/xml/event")
    protected int pinned;
    @XmlElement(namespace = "http://it.nure.ua/matsuytskij/xml/event", required = true)
    protected String time;
    @XmlAttribute(name = "type", required = true)
    protected String type;
    @XmlAttribute(name = "organizer")
    protected String organizer;

    /**
     * Gets the value of the idEvent property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId_event() {
        return id_event;
    }

    /**
     * Sets the value of the idEvent property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId_event(BigInteger value) {
        this.id_event = value;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id_event=" + id_event +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", place=" + place.toString() +
                ", description='" + description + '\'' +
                ", pinned=" + pinned +
                ", time='" + time + '\'' +
                ", type='" + type + '\'' +
                ", organizer='" + organizer + '\'' +
                '}';
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Gets the value of the place property.
     * 
     * @return
     *     possible object is
     *     {@link Place }
     *     
     */
    public Place getPlace() {
        return place;
    }

    /**
     * Sets the value of the place property.
     * 
     * @param value
     *     allowed object is
     *     {@link Place }
     *     
     */
    public void setPlace(Place value) {
        this.place = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the pinned property.
     * 
     */
    public int getPinned() {
        return pinned;
    }

    /**
     * Sets the value of the pinned property.
     * 
     */
    public void setPinned(int value) {
        this.pinned = value;
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTime(String value) {
        this.time = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the organizer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganizer() {
        return organizer;
    }

    /**
     * Sets the value of the organizer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganizer(String value) {
        this.organizer = value;
    }

}
