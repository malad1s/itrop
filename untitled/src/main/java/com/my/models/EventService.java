package com.my.models;

import com.my.dao.EventDaoImpl;
import com.my.enity.*;
import com.my.utils.EmailSend;

import java.util.List;

public class EventService {

    public void presenceUser(int idEvent, int idUser, int presence){
        new EventDaoImpl().updatePresence(idEvent,idUser,presence);
    }


    public int getParticipantsCount(Event event){
        Integer count =new EventDaoImpl().getParticipantsCount(event);
        if(count!=null){
            return count;
        }
        return 0;
    }
    public List<UserEnity> getParticipants(int idEvent){
        return new EventDaoImpl().getParticipants(idEvent);
    }

    public List<ReportAndSpeaker> getOfferOnFreeReports(int idEvent){
        return new EventDaoImpl().getOfferOnFreeReports(idEvent);
    }
    public void createEvent(Event event){
        if(getEvent(event.getName())==null){
            new EventDaoImpl().save(event);
        }
    }

    public void acceptOrCancelOffersOnEvent(String dataType,String action, int idReport,int idSpeaker){
        if(dataType.equals("offerOnFree")){
            if(action.equals("accept")){
                new EventDaoImpl().updateOnReportsFromFreeReports(idReport,idSpeaker);
            }else if(action.equals("cancel")){
                new EventDaoImpl().deleteFromFreeReports(idReport,idSpeaker);
            }
        }else if(dataType.equals("offerOnNew")){
            if(action.equals("accept")){
                new EventDaoImpl().saveReportFromNewReports(idReport);
            }else if(action.equals("cancel")){
                new EventDaoImpl().deleteFromNewReports(idReport);
            }
        }
    }

    public void regUser(int idEvent,int idUser){
        new EventDaoImpl().insertUserOnEvent(idEvent,idUser);
    }
    public UserEnity checkReg(int idEvent,int idUser){
        return (new EventDaoImpl().checkRegister(idEvent,idUser));
    }
    public void updateEvent(Event event){
        new EventDaoImpl().updateEvent(event);
        List<UserEnity> users=new EventService().getParticipants(event.getId());
        List<ReportAndSpeaker> speakers=new EventService().getReportsAndSpeakers(event.getId());
        EmailSend.sendEmailAboutChangeEvent(users,event,speakers);
    }
    public void updateReport(Report report){
        new EventDaoImpl().updateReport(report);
    }
    public List<Event> getAllEvents(){
        return (new EventDaoImpl().getAll());
    }
    public Report getReport(int idReport){
        return new EventDaoImpl().getReport(idReport);
    }
    public void createReport(Report report){
        new EventDaoImpl().createReport(report);
    }

    public void changePin( int idReport,int pin){
         new EventDaoImpl().updateReportPin(idReport,pin);
    }
    public Event getEvent(int id){
       return new EventDaoImpl().get(id);
    }
    public Event getEvent(String name){
        return new EventDaoImpl().get(name);
    }
    public int getReports(Event event){
        Integer result= new EventDaoImpl().getReportsForCount(event);
        if(result==null){
            return 0;
        }
        return result;
    }
    public List<ReportAndSpeaker> getReportsAndSpeakers(int idEvent){
       return new EventDaoImpl().getRepAndSpeaker(idEvent);
    }
    public void createNewReportBySpeaker(Report report){
        new EventDaoImpl().insertReportBySpeaker(report);
    }
    public void suggestSpeaker(int idReport,int idSpeaker){
        new EventDaoImpl().insertFree_reports(idReport,idSpeaker);
    }
    public List<EventAndReports> getEventAndReports(int idSpeaker){
        return new EventDaoImpl().getEventsForSpeaker(idSpeaker);
    }

    public void deleteEvent(int idEvent){
        new EventDaoImpl().delete(idEvent);
    }


}
