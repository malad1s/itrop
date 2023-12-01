package com.my.utils;

import com.my.enity.*;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class EmailSend {

    private static  boolean accesToSend=false;
    private static Properties properties=new Properties();
    static {
        properties.setProperty("mail.transport.protocol","smtps");
        properties.setProperty("mail.smtps.ssl.trust","smtp-mail.outlook.com");
        properties.setProperty("mail.host","smtp-mail.outlook.com");
        properties.setProperty("mail.smtps.auth","true");
        properties.setProperty("mail.smtps.host","smtp-mail.outlook.com");

        properties.setProperty("mail.smtp.socketFactory.fallback", "true");
        properties.setProperty("mail.user","taskhub.sup@outlook.com");
        properties.setProperty("mail.password","taskhub123");
        properties.setProperty("mail.smtps.starttls.enable","true");
    }
    static String emailFrom="conferences.work@gmail.com";
    static String emailFromPass="!password123";

    public static void sendEmailAboutRegistrationOnSiteForUser(UserEnity user){

        if(accesToSend) {
            try {
                // InputStream input=new FileInputStream("src\\main\\resources\\mail.properties");
                Session session = Session.getDefaultInstance(properties);
                MimeMessage message = new MimeMessage(session); // email message
                message.setSubject("You complete registration on site");
                String first = "";
                if (user.getSurname() == null || user.getFirstname() == null) {
                    first = "You register on event but not register on site. So please change you info into account after log in \n";
                }
                message.setText(
                        first + "Your login: " + user.getEmail() + "\n" + "Your password: " + user.getPassword() + "\n"
                );
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
                message.setSentDate(new Date());
                Transport tr = session.getTransport();
                tr.connect("smtp.gmail.com", 465, emailFrom, emailFromPass);
                tr.sendMessage(message, message.getAllRecipients());
                tr.close();
            } catch (AddressException e) {
                throw new RuntimeException(e);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void sendEmailAboutRegistrationOnEventForUser(UserEnity user,Event event){
        if(accesToSend) {
            try {
                //InputStream input=new FileInputStream("src\\main\\resources\\mail.properties");


                Session session = Session.getDefaultInstance(properties);
                MimeMessage message = new MimeMessage(session); // email message
                message.setSubject("You complete registration on event");

                message.setText("You have registered for the event " + event.getName()
                        + " that will take place on " + event.getDate()
                        + " " + event.getTime() + " in " + event.getPlace());
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
                message.setSentDate(new Date());
                Transport tr = session.getTransport();
                tr.connect("smtp.gmail.com", 465, emailFrom, emailFromPass);
                tr.sendMessage(message, message.getAllRecipients());
                tr.close();
            } catch (AddressException e) {
                throw new RuntimeException(e);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void sendEmailAboutChangeEvent(List<UserEnity> users, Event event,List<ReportAndSpeaker> speakers){
        if(accesToSend) {
            try {
                //InputStream input=new FileInputStream("src\\main\\resources\\mail.properties");
                Session session = Session.getDefaultInstance(properties);
                MimeMessage message = new MimeMessage(session); // email message
                message.setSubject("Change info about event");
                message.setText("data about the event for which you are registered has been changed  " + event.getName()
                        + " that will take place on " + event.getDate()
                        + " " + event.getTime() + " in " + event.getPlace());
                for (UserEnity user : users) {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
                }
                for (ReportAndSpeaker speaker : speakers) {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(speaker.getSpeaker().getEmail()));
                }
                message.setSentDate(new Date());
                Transport tr = session.getTransport();
                tr.connect("smtp.gmail.com", 465, emailFrom, emailFromPass);

                tr.sendMessage(message, message.getAllRecipients());
                tr.close();
            } catch (AddressException e) {
                throw new RuntimeException(e);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public static void sendEmailAboutOfferReportsForSpeaker(UserEnity user, Event event){
        if(accesToSend) {
            try {
                //InputStream input=new FileInputStream("src\\main\\resources\\mail.properties");
                Session session = Session.getDefaultInstance(properties);
                MimeMessage message = new MimeMessage(session); // email message
                message.setSubject("New report");
                message.setText("You have been invited to speak at an even " + event.getName()
                        + " that will take place on " + event.getDate()
                        + " " + event.getTime() + " in " + event.getPlace() + " to view details view your reports ");
                if (user != null) {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
                    message.setSentDate(new Date());
                    Transport tr = session.getTransport();
                    tr.connect("smtp.gmail.com", 465, emailFrom, emailFromPass);
                    tr.sendMessage(message, message.getAllRecipients());
                    tr.close();
                }

            } catch (AddressException e) {
                throw new RuntimeException(e);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }


  public  static void send(String emailTo){
      if(accesToSend) {
          try {
              InputStream input = new FileInputStream("src\\main\\resources\\mail.properties");

              Session session = Session.getDefaultInstance(properties);
              MimeMessage message = new MimeMessage(session); // email message

              message.setSubject("Test Mail from Java Program");
              message.setText("ffghghh");
              message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
              message.setSentDate(new Date());
              Transport tr = session.getTransport();
              tr.connect("smtp-mail.outlook.com", 587, emailFrom, emailFromPass);
              tr.sendMessage(message, message.getAllRecipients());
              tr.close();
          } catch (AddressException e) {
              throw new RuntimeException(e);
          } catch (MessagingException e) {
              throw new RuntimeException(e);
          } catch (FileNotFoundException e) {
              throw new RuntimeException(e);
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
      }
  }
}
