package com.my.enity;

import java.util.Comparator;

public class ReportAndSpeaker {
    Report report;
    UserEnity speaker;
    public int getPin() {
        return report.getPin();
    }
    public static Comparator<ReportAndSpeaker> getComparatorCompareByEnchrined(){
        return (ReportAndSpeaker o1, ReportAndSpeaker o2)->{
            Integer in1=o1.getPin();
            Integer in2=o2.getPin();
            return in1.compareTo(in2);
        };
    }
    @Override
    public String toString() {
        return "ReportAndSpeaker{" +
                "report=" + report +
                ", speaker=" + speaker +
                "} \n";
    }

    public ReportAndSpeaker(Report report, UserEnity speaker) {
        this.report = report;
        this.speaker = speaker;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public UserEnity getSpeaker() {
        return speaker;
    }

    public void setSpeaker(UserEnity speaker) {
        this.speaker = speaker;
    }
}
