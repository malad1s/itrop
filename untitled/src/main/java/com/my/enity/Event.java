package com.my.enity;

import com.my.models.EventService;

import java.util.Comparator;

public class Event  {


    public static Comparator<Event> getComparatorCompareByName(){
        return (Event o1, Event o2)->o1.getName().compareTo(o2.getName());
    }

    public static Comparator<Event> getComparatorCompareByEnchrined(){
        return (Event o1, Event o2)->{
            Integer in1=o1.getPin();
            Integer in2=o2.getPin();
            return in1.compareTo(in2);
        };
    }
    public static Comparator<Event> getComparatorCompareByActive(){
        return (Event o1, Event o2)->{
            Integer in1=o1.getPin();
            Integer in2=o2.getPin();
            return in1.compareTo(in2);
        };
    }
    public static Comparator<Event> getComparatorCompareByDate(){
        return (Event o1, Event o2)->o1.getDate().compareTo(o2.getDate());
    }
    public static Comparator<Event> getComparatorCompareByParticipants(){
        return (Event o1, Event o2)->{
            Integer in1=new EventService().getParticipantsCount(o1);
            Integer in2=new EventService().getParticipantsCount(o2);
            return in1.compareTo(in2);
        };
    }
    public static Comparator<Event> getComparatorCompareByReports(){
        return (Event o1, Event o2)->{
            Integer in1=new EventService().getReports(o1);
            Integer in2=new EventService().getReports(o2);
            return in1.compareTo(in2);
        };
    }



    int id;
    String name;
    String date;
    String place;
    int pin;
    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", place='" + place + '\'' +
                ", name='" + time + '\'' +
                ", pin=" + pin +"\n";
    }

    public Event() {
    }

    public Event(String name, String date, String place, String time) {
        this.name = name;
        this.date = date;
        this.place = place;
        this.time = time;
    }

    public Event(int id, String name, String date, String place, int pin,String time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.place = place;
        this.pin = pin;
        this.time = time;
    }


    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public Event(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }




}
