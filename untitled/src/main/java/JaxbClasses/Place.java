
package JaxbClasses;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Place complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Place"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="online" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="offline" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Place", namespace = "http://it.nure.ua/matsuytskij/xml/place", propOrder = {
    "online",
    "offline"
})
public class Place {

    @XmlElement(namespace = "http://it.nure.ua/matsuytskij/xml/place")
    protected String online;
    @XmlElement(namespace = "http://it.nure.ua/matsuytskij/xml/place")
    protected String offline;

    /**
     * Gets the value of the online property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnline() {
        return online;
    }

    /**
     * Sets the value of the online property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnline(String value) {
        this.online = value;
    }

    /**
     * Gets the value of the offline property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOffline() {
        return offline;
    }

    /**
     * Sets the value of the offline property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOffline(String value) {
        this.offline = value;
    }

    @Override
    public String toString() {
        return "Place{" +
                "online='" + online + '\'' +
                ", offline='" + offline + '\'' +
                '}';
    }
}
