
package JaxbClasses;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="UserEvent" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="event" type="{http://it.nure.ua/matsuytskij/xml/event}Event"/&gt;
 *                   &lt;element name="user" type="{http://it.nure.ua/matsuytskij/xml/user}User"/&gt;
 *                   &lt;element name="presence"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *                         &lt;enumeration value="0"/&gt;
 *                         &lt;enumeration value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "userEvent"
})
@XmlRootElement(name = "UsersEvents", namespace = "http://it.nure.ua/matsuytskij/xml/")
public class UsersEvents {

    @XmlElement(name = "UserEvent", namespace = "http://it.nure.ua/matsuytskij/xml/", required = true)
    protected List<UsersEvents.UserEvent> userEvent;

    public void setUserEvent(List<UserEvent> userEvent) {
        this.userEvent = userEvent;
    }

    /**
     * Gets the value of the userEvent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the userEvent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserEvent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UsersEvents.UserEvent }
     * 
     * 
     */
    public List<UsersEvents.UserEvent> getUserEvent() {
        if (userEvent == null) {
            userEvent = new ArrayList<UsersEvents.UserEvent>();
        }
        return this.userEvent;
    }

    @Override
    public String toString() {
        String result="UsersEvents{";
        for(UserEvent e : userEvent)
        {
            result+="event=" + e.toString()+" ";

        }
        return result;
    }

    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="event" type="{http://it.nure.ua/matsuytskij/xml/event}Event"/&gt;
     *         &lt;element name="user" type="{http://it.nure.ua/matsuytskij/xml/user}User"/&gt;
     *         &lt;element name="presence"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
     *               &lt;enumeration value="0"/&gt;
     *               &lt;enumeration value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "event",
        "user",
        "presence"
    })
    public static class UserEvent {

        @XmlElement(namespace = "http://it.nure.ua/matsuytskij/xml/", required = true)
        protected Event event;
        @XmlElement(namespace = "http://it.nure.ua/matsuytskij/xml/", required = true)
        protected User user;
        @XmlElement(namespace = "http://it.nure.ua/matsuytskij/xml/")
        protected int presence;

        /**
         * Gets the value of the event property.
         * 
         * @return
         *     possible object is
         *     {@link Event }
         *     
         */
        public Event getEvent() {
            return event;
        }

        /**
         * Sets the value of the event property.
         * 
         * @param value
         *     allowed object is
         *     {@link Event }
         *     
         */
        public void setEvent(Event value) {
            this.event = value;
        }

        /**
         * Gets the value of the user property.
         * 
         * @return
         *     possible object is
         *     {@link User }
         *     
         */
        public User getUser() {
            return user;
        }

        /**
         * Sets the value of the user property.
         * 
         * @param value
         *     allowed object is
         *     {@link User }
         *     
         */
        public void setUser(User value) {
            this.user = value;
        }

        /**
         * Gets the value of the presence property.
         * 
         */
        public int getPresence() {
            return presence;
        }

        /**
         * Sets the value of the presence property.
         * 
         */
        public void setPresence(int value) {
            this.presence = value;
        }

        @Override
        public String toString() {
            return "UserEvent{" +
                    "event=" + event +"\n"+
                    ", user=" + user +"\n"+
                    ", presence=" + presence +
                    "} \n";
        }
    }


}
