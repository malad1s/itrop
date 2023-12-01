package com.my.utils;


import com.aspose.pdf.*;
import com.my.dao.EventDaoImpl;
import com.my.enity.Event;
import com.my.enity.UserEnity;

import java.util.Collections;
import java.util.List;

public class PDFResult {

    public static void createUsersPresenceInPDF(Event event, List<UserEnity> users){
        Document doc = new Document();
        Page page = doc.getPages().add();
        long presenseTrue =users.stream().filter(user->user.getPresence()==1).count();
        long count=users.size();
        page.getParagraphs().add(new TextFragment("Event: "+event.getName()));
        page.getParagraphs().add(new TextFragment("Date: "+event.getDate()+" "+event.getTime()));
        page.getParagraphs().add(new TextFragment("Place: "+event.getPlace()));
        page.getParagraphs().add(new TextFragment("Presense: "+presenseTrue+"/"+count));
        Collections.sort(users,UserEnity.getComparatorCompareByPresense().reversed());
        Table table = new Table();
        table.setDefaultColumnWidth("150");
        table.setBorder(new BorderInfo(BorderSide.All, .5f, Color.getLightGray()));
        table.setDefaultCellBorder(new BorderInfo(BorderSide.All, .5f, Color.getLightGray()));
        for(UserEnity user:users) {
            Row row = table.getRows().add();
            row.getCells().add(user.getSurname() + " "+user.getFirstname());
            row.getCells().add(user.getEmail());
            row.getCells().add(user.getPresence()==1? "Came": "Didn't came");
        }
        doc.getPages().get_Item(1).getParagraphs().add(table);
        doc.save( "pdf/Event"+event.getId()+"Presence.pdf");
    }


}
