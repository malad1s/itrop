import com.my.DBConnection.DBCPDataSource;
import com.my.DBConnection.DBCPSetProperties;
import com.my.dao.UserDaoImpl;
import com.my.enity.Event;
import com.my.enity.Role;
import com.my.enity.UserEnity;
import com.my.models.EventService;
import com.my.models.UserService;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

public class TestUserDAO {
    @Before
    public void testConnection(){
        BasicDataSource ds= DBCPDataSource.getDataSource();
        DBCPSetProperties.setProperties();
        assertNotNull(DBCPDataSource.getConnection());
    }

    @Test
    public void testUserDaoBasicFunc(){
        UserService service=new UserService();
        UserEnity user=new UserEnity(0,"testsurname","testfirstname","test@email","password",new Role(1));
        UserEnity userTest =service.register(user);
        assertNotNull(userTest);
        assertNotNull(service.getUser(userTest.getId()));

        assertNotNull(service.login(userTest.getEmail(),"password"));
        assertNotNull(service.updateUser(userTest.getId(),"testsurname","testfirstname1","test@email","password"));
        userTest=service.getUser(userTest.getId());
        assertNotEquals(userTest.getFirstname(),"testfirstname");

        UserEnity speaker=new UserEnity(0,"testsurnameSpeak","testfirstnameSpeak","testSpeak@email","password",new Role(3));
        UserEnity userTestSpeak =service.register(speaker);
        assertNotEquals( 0,service.getSpeakers().size());

        new EventService().createEvent(new Event(
                "testcreation",
                "2120.10.10",
                "testplace",
                "12:34"
        ));
        Event event= new EventService().getEvent("testcreation");
        new EventService().regUser(event.getId(),userTest.getId());
        assertNotEquals( 0,service.getEventsForUser(userTest.getId()).size());
        service.unsubscribingUserFromEvent(event.getId(),userTest.getId());
        assertEquals(0,service.getEventsForUser(userTest.getId()).size());
        new EventService().deleteEvent(event.getId());
        UserEnity userOnlyEmail=service.createUser("testonlyemail@email");
        assertNotNull(userOnlyEmail);


        new UserDaoImpl().delete(userOnlyEmail.getId());
        assertNull(service.getUser(userOnlyEmail.getId()));
        new UserDaoImpl().delete(userTestSpeak.getId());
        assertNull(service.login("testSpeak@email","password"));
        new UserDaoImpl().delete(userTest.getId());
        assertNull(service.login("test@email","password"));


    }
}
