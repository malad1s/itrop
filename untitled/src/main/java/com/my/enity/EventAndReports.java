package com.my.enity;

import java.util.ArrayList;
import java.util.List;

public class EventAndReports {
    private Event event;
    private List<Report> reports;

    @Override
    public String toString() {
        return "EventAndReports{" +
                "event=" + event.toString() +"\n"+
                ", reports=" + reports.toString() +"\n";
    }

    public void addReport(Report report) {
        if(reports==null){
            reports=new ArrayList<>();
        }
        reports.add(report);
    }
    public EventAndReports() {
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public EventAndReports(Event event, List<Report> reports) {
        this.event = event;
        this.reports = reports;
    }
}
