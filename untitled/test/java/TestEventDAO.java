import com.my.DBConnection.DBCPDataSource;
import com.my.DBConnection.DBCPSetProperties;
import com.my.dao.EventDaoImpl;
import com.my.dao.UserDaoImpl;
import com.my.enity.*;
import com.my.models.EventService;
import com.my.models.UserService;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

public class TestEventDAO {

    @Before
    public void testConnection(){
        BasicDataSource ds= DBCPDataSource.getDataSource();
        DBCPSetProperties.setProperties();
        assertNotNull(DBCPDataSource.getConnection());
    }

    @Test
    public void testEventDaoBasicFunc(){
        EventService service=new EventService();

        service.createEvent(new Event(
                "testcreation",
                "2120.10.10",
                "testplace",
                "12:34"
        ));
        Event event=service.getEvent("testcreation");
        assertNotNull(event);
        event=service.getEvent(event.getId());
        assertNotNull(event);
        assertNotNull(service.getAllEvents());

        String name=event.getName();
        event.setName("testcreation1");
        new EventDaoImpl().updateEvent(event);

        event=service.getEvent(event.getId());
        assertNotEquals(name,event.getName());
        //create report
        service.createReport(new Report(event.getId(),0,"topic",0));
        assertNotEquals(0,service.getReports(event));
        List<ReportAndSpeaker> reportAndSpeakerList=service.getReportsAndSpeakers(event.getId());
        assertNotEquals(0,reportAndSpeakerList.size());
        Report report=reportAndSpeakerList.get(0).getReport();
        report.setTopic("non");
        service.updateReport(report);
        service.changePin(report.getId(),1);
        Report repfor2 =new EventDaoImpl().getReport(report.getId());
        assertNotEquals("topic",repfor2.getTopic());
        assertNotEquals(0,repfor2.getPin());


        //создать юзуреа
        UserEnity user=new UserEnity(0,"testsurname","testfirstname","test@email","password",new Role(1));
        UserEnity userTest =new UserService().register(user);
        //podpisatm
        service.regUser(event.getId(),userTest.getId());
        //check podpiska
        assertNotNull(service.checkReg(event.getId(),userTest.getId()));
        //delete user
        assertNotEquals(0,service.getParticipants(event.getId()));



        new UserDaoImpl().delete(userTest.getId());
        assertNull(new UserService().getUser(userTest.getId()));
        service.deleteEvent(event.getId());
        new EventDaoImpl().deleteReport(report.getId());


    }
}
