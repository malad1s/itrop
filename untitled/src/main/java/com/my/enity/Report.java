package com.my.enity;

import java.util.Comparator;

public class Report {
    int id;
    int id_event;
    int id_speaker;
    String topic;
    int pin;

    public Report() {
    }

    public Report(int id, int id_speaker, String topic) {
        this.id = id;
        this.id_speaker = id_speaker;
        this.topic = topic;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public static Comparator<Report> getComparatorCompareByEnchrined(){
        return (Report o1, Report o2)->{
            Integer in1=o1.getPin();
            Integer in2=o2.getPin();
            return in1.compareTo(in2);
        };
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", id_event=" + id_event +
                ", id_speaker=" + id_speaker +
                ", topic='" + topic + '\'' +
                '}';
    }

    public Report(int id, int id_event, int id_speaker, String topic,int pin) {
        this.id = id;
        this.id_event = id_event;
        this.id_speaker = id_speaker;
        this.topic = topic;
        this.pin = pin;
    }

    public Report(int id_event, int id_speaker, String topic, int pin) {
        this.id_event = id_event;
        this.id_speaker = id_speaker;
        this.topic = topic;
        this.pin = pin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getId_speaker() {
        return id_speaker;
    }

    public void setId_speaker(int id_speaker) {
        this.id_speaker = id_speaker;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
