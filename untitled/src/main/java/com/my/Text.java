package com.my;

import com.my.DBConnection.DBCPSetProperties;
import com.my.dao.EventDao;
import com.my.dao.EventDaoImpl;
import com.my.dao.UserDaoImpl;
import com.my.enity.Event;
import com.my.enity.Report;
import com.my.enity.Role;
import com.my.enity.UserEnity;
import com.my.models.EventService;
import com.my.models.UserService;
import com.my.utils.EmailSend;
import com.my.utils.RandomPassword;

import java.util.List;
import java.util.Random;



public class Text {

    public static void fillEvents(){
        for(int i=1;i<4000;i++){
            new EventDaoImpl().save(
                    new Event(
                            "event"+i,
                            "2022-11-23",
                            "1",
                            "14:00"));
        }
    }
    public static void fillReports(){
        int eventstart=8027;
        int eventfin=11080;
        int speakerstart=9000;
        int speakerfin=10000;
        for(int i=1;i<4000;i++){
            new EventDaoImpl().createReport(
                    new Report(
                            new Random().nextInt(eventfin-eventstart)+eventstart,
                            new Random().nextInt(speakerfin- speakerstart)+speakerstart,
                            "topicBySpeaker"+i,0
                    )
            );
        }
    }

    public static void fillnewSpeakerRepors(){
        int eventstart=8027;
        int eventfin=11080;
        int speakerstart=9000;
        int speakerfin=10000;
        for(int i=1;i<4000;i++){
            new EventDaoImpl().insertReportBySpeaker(
                    new Report(
                            new Random().nextInt(eventfin-eventstart)+eventstart,
                            new Random().nextInt(speakerfin-speakerstart )+speakerstart,
                            "topicBySpeaker"+i,0

                    )
            );
        }

    }
    public static void fillUsers(){
        for(int i=1;i<4000;i++){
            new UserDaoImpl().save(new UserEnity(
                    0,
                    "surname"+i,
                    "firstname"+i,
                    "user_email"+i+"@gmail.com",
                    RandomPassword.createPassword(),
                    new Role(new Random().nextInt(2)+1)));
        }
    }
    public static void fillUsers_Events(){
        int eventstart=8027;
        int eventfin=11080;
        int userstart=8044;
        int userfin=9000;
        for(int i=1;i<4000;i++){
            new EventDaoImpl().insertUserOnEvent(
                    new Random().nextInt(eventfin-eventstart)+eventstart,
                    new Random().nextInt(userfin-userstart)+userstart
            );
        }
    }

    public static void main(String[] args) {
        EventDaoImpl dao= new EventDaoImpl();
        UserService dao2= new UserService();

        System.out.println( dao2.login("1","1").toString());


       // EmailSend.send("random1pochta@gmail.com");
        /*
        DBCPSetProperties.setProperties();
        //new_reports_speak( get all speak) users(1038 1136) (reports ) events(4200 7000)
        fillUsers();
        fillEvents();
        fillUsers_Events();
        fillReports();
        fillnewSpeakerRepors();
        */

    }
}
