package com.my.dao;

import com.my.DBConnection.DBCPDataSource;
import com.my.enity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDaoImpl implements EventDao{
    private final Logger logger= LoggerFactory.getLogger(EventDaoImpl.class);

    private final String getallEvents=" SELECT * FROM events ";
    private final String getParticipantsOnEventCount ="SELECT  COUNT(*) AS Per\n" +
            "FROM users_events\n" +
            "where id_event=?\n" +
            "GROUP BY id_event";
    private final String getParticipantsOnEvent ="SELECT users.*,users_events.presence\n" +
            "FROM users_events\n" +
            "INNER JOIN users ON users_events.id_user = users.id\n" +
            "WHERE id_event=?";
    private final String getEventById =" SELECT * FROM events WHERE id_event=?";
    private final String getEventByName=" SELECT * FROM events WHERE name=?";
    private final String getIdUserFromUsersOnEvents=" SELECT users_events.id_user FROM users_events WHERE id_event=? and id_user=?";

    private final String getRepAndSpeaker="SELECT reports.idreports ,reports.id_event,reports.id_speaker,reports.topic,reports.pinned,users.id,users.surname,users.firstname,users.email\n" +
            "FROM reports\n" +
            "Left JOIN users ON reports.id_speaker = users.id\n" +
            "WHERE id_event=?";
    private final String getFreeRepAndSpeaker="SELECT reports.idreports, reports.id_event,reports.topic,u.id,u.surname,u.firstname,u.email\n" +
            "FROM reports\n" +
            "INNER join free_reports fr on reports.idreports = fr.id_report\n" +
            "INNER join users u on fr.idspeaker = u.id\n" +
            "WHERE id_event=?";

    private final String getNewReportAndSpeaker="SELECT newrepors_speak.*,u.id,u.surname,u.firstname,u.email\n" +
            "FROM newrepors_speak\n" +
            "INNER join users u on newrepors_speak.speaker_id = u.id\n" +
            "WHERE event_id=?";
    private final String insertUserOnEvent="INSERT INTO users_events(id_event,id_user) VALUES (?,?)";
    private final String createEvent="INSERT INTO events(name, date, place, time) VALUES (?,?,?,?)";
    private final String createReport="INSERT INTO reports(id_event, id_speaker, topic) VALUES (?,?,?)";

    private final String updateReportOnPin="UPDATE reports\n" +
            "SET pinned =?\n" +
            "WHERE idreports = ?;";
    private final String updateEvent="UPDATE events\n" +
            "SET name =?,date=?, place=?, time=?\n" +
            "WHERE id_event= ?;";
    private final String getReport="SELECT reports.*\n" +
            "FROM reports\n" +
            "WHERE idreports=?";

    private final String getReportsForC="SELECT  COUNT(*) AS Per\n" +
            "FROM reports\n" +
            "where id_event=?\n" +
            "GROUP BY id_event\n" +
            "\n";
    private final String updateReport="UPDATE reports\n" +
            "SET id_speaker =?, topic=?\n" +
            "WHERE idreports = ?;";
    private final String updatePresenceOnEvent="UPDATE users_events\n" +
            "SET presence =?\n" +
            "WHERE id_event = ? and id_user=?";

    private final String getEventsForSpeakers="SELECT reports.*, events.*\n" +
            "FROM reports\n" +
            "Left JOIN events ON reports.id_event = events.id_event\n" +
            "WHERE id_speaker=?\n" +
            "ORDER BY reports.id_event DESC";


    private final String insertNewReportBySpeaker="INSERT INTO newrepors_speak(speaker_id, event_id, topic) VALUES (?,?,?)";
    private final String insertInFree_reports="INSERT INTO free_reports(id_report,idspeaker) VALUES (?,?)";
    private final String getFromFree_reports="SELECT id_report FROM free_reports WHERE id_report=? and idspeaker=? ";
    ;
    private final String deleteEvent="DELETE FROM events WHERE id_event=?";
    private final String deleteFromFreeReports="DELETE FROM free_reports WHERE id_report=? and idspeaker=?";
    private final String deleteFromNewReports="DELETE FROM newrepors_speak WHERE idspeak_repors=? ";
    private final String getReportFromNewReports="SELECT newrepors_speak.*\n" +
            "FROM newrepors_speak\n" +
            "WHERE idspeak_repors=?";

    private final String updateReportSpeaker="UPDATE reports\n" +
            "SET id_speaker =?\n" +
            "WHERE idreports = ?";
    private String deleteReport="DELETE FROM free_reports WHERE idreports=?";

    public void closeStatement(){

    }

    public void updateOnReportsFromFreeReports(int idReport,int idSpeaker){
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            con.setAutoCommit(false);
            prst=con.prepareStatement(updateReportSpeaker);
            prst.setInt(2,idReport);
            prst.setInt(1,idSpeaker);
            prst.executeUpdate();
            prst=con.prepareStatement(deleteFromFreeReports);
            prst.setInt(1,idReport);
            prst.setInt(2,idSpeaker);
            prst.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }

            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }


    }

    public Report saveReportFromNewReports(int idReport){
        Report res= null;
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            con.setAutoCommit(false);
            prst=con.prepareStatement(getReportFromNewReports);
            prst.setInt(1,idReport);
            ResultSet resultSet = prst.executeQuery();
            while (resultSet.next()) {
                res= new Report(0,
                        resultSet.getInt(3),
                        resultSet.getInt(2),
                        resultSet.getString(4),
                        0
                );
            }
            prst=con.prepareStatement(createReport);
            prst.setInt(1,res.getId_event());
            prst.setInt(2,res.getId_speaker());
            prst.setString(3,res.getTopic());
            prst.executeUpdate();
            prst=con.prepareStatement(deleteFromNewReports);
            prst.setInt(1,idReport);
            prst.executeUpdate();

            con.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
                return new Report();
            }
        }
        return res;
    }
    public void deleteFromNewReports(int idReport){
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(deleteFromNewReports);
            prst.setInt(1,idReport);
            prst.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }
    public void deleteFromFreeReports(int idReport,int idSpeaker){
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(deleteFromFreeReports);
            prst.setInt(1,idReport);
            prst.setInt(2,idSpeaker);
            prst.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }

    }

    public List<ReportAndSpeaker> getOfferOnNewReports(int idEvent){
        List<ReportAndSpeaker> res = new ArrayList<>();
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(getNewReportAndSpeaker);
            prst.setInt(1,idEvent);
            ResultSet resultSet = prst.executeQuery();
            while (resultSet.next()) {
                res.add(new ReportAndSpeaker(
                        new Report(
                                resultSet.getInt(1),
                                resultSet.getInt(3),
                                resultSet.getInt(2),
                                resultSet.getString(4),
                                0
                        ),
                        new UserEnity(
                                resultSet.getInt(5),
                                resultSet.getString(6),
                                resultSet.getString(7),
                                resultSet.getString(8),
                                "",
                                null
                        )
                ));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);

            }
        }
        return res;
    }
    public List<ReportAndSpeaker> getOfferOnFreeReports(int idEvent){
        List<ReportAndSpeaker> res = new ArrayList<>();
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(getFreeRepAndSpeaker);
            prst.setInt(1,idEvent);
            ResultSet resultSet = prst.executeQuery();
            while (resultSet.next()) {
                res.add(new ReportAndSpeaker(
                        new Report(
                                resultSet.getInt(1),
                                resultSet.getInt(2),0,
                                resultSet.getString(3),0
                        ),
                        new UserEnity(
                                resultSet.getInt(4),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getString(7),
                                "",
                                null
                        )
                ));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }

            } catch (SQLException e) {
                logger.error(e.getMessage(),e);

            }
        }
        return res;
    }
    public  void updatePresence(int idEvent, int idUser, int presence){
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(updatePresenceOnEvent);
            prst.setInt(1,presence);
            prst.setInt(2,idEvent);
            prst.setInt(3,idUser);
            prst.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }

    public List<EventAndReports> getEventsForSpeaker(int idSpeaker){
        List<EventAndReports> list=new ArrayList<>();
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(getEventsForSpeakers);
            prst.setInt(1,idSpeaker);
            ResultSet resultSet = prst.executeQuery();
            EventAndReports result=new EventAndReports();
            while (resultSet.next()) {
                if(result.getEvent()==null){
                    result.addReport(
                            new Report(
                                    resultSet.getInt(1),
                                    resultSet.getInt(2),
                                    resultSet.getInt(3),
                                    resultSet.getString(4),
                                    resultSet.getInt(5)
                            ));
                    result.setEvent(
                            new Event(
                                    resultSet.getInt(6),
                                    resultSet.getString(7),
                                    resultSet.getString(8),
                                    resultSet.getString(9),
                                    resultSet.getInt(10),
                                    resultSet.getString(11)
                            ));

                }else if(result.getEvent().getId()==resultSet.getInt(2)){
                    result.addReport(
                            new Report(
                                    resultSet.getInt(1),
                                    resultSet.getInt(2),
                                    resultSet.getInt(3),
                                    resultSet.getString(4),
                                    resultSet.getInt(5)
                            ));
                }else {
                    list.add(result);
                    result=new EventAndReports();
                    result.addReport(
                            new Report(
                                    resultSet.getInt(1),
                                    resultSet.getInt(2),
                                    resultSet.getInt(3),
                                    resultSet.getString(4),
                                    resultSet.getInt(5)
                            ));
                    result.setEvent(
                            new Event(
                                    resultSet.getInt(6),
                                    resultSet.getString(7),
                                    resultSet.getString(8),
                                    resultSet.getString(9),
                                    resultSet.getInt(10),
                                    resultSet.getString(11)
                            ));
                }
            }
            list.add(result);
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }

            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }
        return list;
    }

    public void insertReportBySpeaker(Report report){
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(insertNewReportBySpeaker);
            prst.setInt(1,report.getId_speaker());
            prst.setInt(2,report.getId_event());
            prst.setString(3,report.getTopic());
            prst.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }

    }

    public void insertFree_reports(int idReport,int idSpeaker){
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(getFromFree_reports);
            prst.setInt(1,idReport);
            prst.setInt(2,idSpeaker);
            ResultSet resultSet= prst.executeQuery();
            System.out.println(resultSet);
            if(!resultSet.next()){
                prst=con.prepareStatement(insertInFree_reports);
                prst.setInt(1,idReport);
                prst.setInt(2,idSpeaker);
                prst.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                };
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }

    }
    public Report getReport(int idReport){
        Report res= null;
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(getReport);
            prst.setInt(1,idReport);
            ResultSet resultSet = prst.executeQuery();
            while (resultSet.next()) {
                res= new Report(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                );
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }
        return res;
    }
    public void updateEvent(Event event){
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(updateEvent);
            prst.setString(1,event.getName());
            prst.setString(2,event.getDate());
            prst.setString(3,event.getPlace());
            prst.setString(4,event.getTime());
            prst.setInt(5,event.getId());
            prst.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }
    public void insertUserOnEvent(int idEvent, int idUser){
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(insertUserOnEvent);
            prst.setInt(1,idEvent);
            prst.setInt(2,idUser);
            prst.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }

    }

    public void updateReport(Report report){
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(updateReport);
            System.out.println(report.getId_speaker());
            if(report.getId_speaker()==0){
                prst.setObject(1,null);
            }else{
                prst.setInt(1,report.getId_speaker());
            }
            prst.setString(2,report.getTopic());
            prst.setInt(3,report.getId());
            prst.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }
    public void updateReportPin(int idReport,int pin){
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(updateReportOnPin);
            prst.setInt(1,pin);
            prst.setInt(2,idReport);
            prst.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }

    public Integer getReportsForCount(Event event){
        Integer res= null;
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(getReportsForC);
            prst.setInt(1,event.getId());
            ResultSet resultSet = prst.executeQuery();
            while (resultSet.next()) {
                res= resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }

            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
                return 0;
            }
        }
        return res;
    }
    public List<UserEnity> getParticipants(int idEvent){
        List<UserEnity> list=new ArrayList<>();
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(getParticipantsOnEvent);
            prst.setInt(1,idEvent);
            ResultSet resultSet = prst.executeQuery();
            while (resultSet.next()) {
                list.add(
                        new UserEnity(resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(5),
                                resultSet.getString(4),
                                new Role(
                                        resultSet.getInt(6),
                                        null
                                ),
                                resultSet.getInt(7)
                        )
                );
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }
        return list;
    }
    public Integer getParticipantsCount(Event event){
        Integer res= null;
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(getParticipantsOnEventCount);
            prst.setInt(1,event.getId());
            ResultSet resultSet = prst.executeQuery();
            while (resultSet.next()) {
                res=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }

            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
                return 0;
            }
        }
        return res;
    }
    public UserEnity checkRegister(int idEvent,int idUser){
        UserEnity result=null;
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(getIdUserFromUsersOnEvents);
            prst.setInt(1,idEvent);
            prst.setInt(2,idUser);
            ResultSet resultSet = prst.executeQuery();
            if(resultSet==null){
                return null;}
            while (resultSet.next()) {
                result= new UserEnity(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
                return null;
            }
        }
        return result;
    }
    public List<ReportAndSpeaker> getRepAndSpeaker(int idEvent){
       List<ReportAndSpeaker> res = new ArrayList<>();
       Connection con= null;
       PreparedStatement prst=null;
       try {
           con = DBCPDataSource.getConnection();
           prst=con.prepareStatement(getRepAndSpeaker);
           prst.setInt(1,idEvent);
           ResultSet resultSet = prst.executeQuery();
           while (resultSet.next()) {
               res.add(new ReportAndSpeaker(
                       new Report(
                               resultSet.getInt(1),
                               resultSet.getInt(2),
                               resultSet.getInt(3),
                               resultSet.getString(4),
                               resultSet.getInt(5)
                               ),
                       new UserEnity(
                               resultSet.getInt(6),
                               resultSet.getString(7),
                               resultSet.getString(8),
                               resultSet.getString(9),
                               "",
                               null
                       )
               ));
           }
       } catch (SQLException e) {
           logger.error(e.getMessage(),e);
       } finally {
           try {
               if(prst!=null) {
                   prst.close();}
               if(con!=null){
                   con.close();
               }
           } catch (SQLException e) {
               logger.error(e.getMessage(),e);
           }
       }
       return res;
   }
    @Override
    public List<Event> getAll() {
        List<Event> list=new ArrayList<>();
        Connection con= null;
        Statement st=null;
        try {
            con = DBCPDataSource.getConnection();
            st=con.createStatement();
            ResultSet resultSet = st.executeQuery(getallEvents);
            while (resultSet.next()) {
                list.add( new Event(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getString(6)
                        )
                );
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(st!=null) {
                    st.close();
                }
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }
        return list;
    }

    @Override
    public Event save(Event event) {
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(createEvent);
            prst.setString(1,event.getName());
            prst.setString(2,event.getDate());
            prst.setString(3,event.getPlace());
            prst.setString(4,event.getTime());
            prst.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }
        return null;
    }

    public void createReport(Report report){
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(createReport);
            prst.setInt(1,report.getId_event());
            if(report.getId_speaker()!=0){
                prst.setInt(2,report.getId_speaker());
            }else{
                prst.setObject(2,null);
            }
            prst.setString(3,report.getTopic());
            prst.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }

    @Override
    public void delete(int id) {
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(deleteEvent);
            prst.setInt(1,id);
            prst.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }

    }


    public void deleteReport(int id) {
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(deleteReport);
            prst.setInt(1,id);
            prst.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }

    }

    @Override
    public Event get(int id) {
        Event res= null;
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(getEventById);
            prst.setInt(1,id);
            ResultSet resultSet = prst.executeQuery();
            while (resultSet.next()) {
                res= new Event(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getString(6)
                );
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
                return null;
            }
        }
        return res;
    }

    public Event get(String name) {
        Event res= null;
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(getEventByName);
            prst.setString(1,name);
            ResultSet resultSet = prst.executeQuery();
            while (resultSet.next()) {
                res= new Event(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getString(6)
                );
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if(con!=null){
                    con.close();
                }

            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
                return new Event();
            }
        }
        return res;
    }
}
