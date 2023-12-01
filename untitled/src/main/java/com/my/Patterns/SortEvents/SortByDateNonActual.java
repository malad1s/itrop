package com.my.Patterns.SortEvents;

import com.my.enity.Event;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SortByDateNonActual implements SortEvents {
    @Override
    public void sort(List<Event> list) {
        list.removeIf(s -> {
            String dataEvent=s.getDate();
            String dayEvent=dataEvent.substring(0, 4);
            String mounthEvent=dataEvent.substring(5, 7);
            String yearEvent=dataEvent.substring(8, 10);
            GregorianCalendar calendar = new GregorianCalendar(
                    Integer.parseInt(dayEvent),
                    Integer.parseInt(mounthEvent)-1 ,
                    Integer.parseInt(yearEvent));
            Date date1=new java.util.Date();
            Date date2=calendar.getTime();
            return date2.after(date1);
        });
    }
}
