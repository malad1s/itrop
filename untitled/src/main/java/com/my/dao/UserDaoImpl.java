package com.my.dao;

import com.my.DBConnection.DBCPDataSource;
import com.my.enity.Event;
import com.my.enity.Role;
import com.my.enity.UserEnity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final Logger logger= LoggerFactory.getLogger(UserDaoImpl.class);

    private final String getallEventsForUser="SELECT  events.*\n" +
            "FROM users_events\n" +
            "INNER JOIN events ON users_events.id_event = events.id_event\n" +
            "WHERE id_user=?\n";
    private final String getall=" SELECT users.*,roles.name_role\n" +
            "FROM users\n" +
            "INNER JOIN roles ON users.role = roles.id_role\n";

    private final String deleteFromUsersEvents="DELETE FROM users_events WHERE id_event=? and id_user=?";
    private final String getallbyRole=" SELECT users.*,roles.name_role\n" +
            "FROM users\n" +
            "INNER JOIN roles ON users.role = roles.id_role " +
            "WHERE roles.id_role=?";
    private final String getUserbyLog="SELECT users.*,roles.name_role\n" +
            "FROM users\n" +
            "INNER JOIN roles ON users.role = roles.id_role\n WHERE users.email=?";
    private final String getUserId="SELECT users.*,roles.name_role\n" +
            "FROM users\n" +
            "INNER JOIN roles ON users.role = roles.id_role\n WHERE users.id=?";
    private final String insertUser="INSERT INTO users(surname,firstname,password,email,role) VALUES (?,?,?,?,?)";

    private final String updateUser="UPDATE users\n" +
            "SET surname =?, firstname=?, password=?, email=?\n" +
            "WHERE id=?";
    private final String updateUserFull="UPDATE users\n" +
            "SET surname =?, firstname=?, password=?, email=?, role=?\n" +
            "WHERE id=?";
    private String deleteUser="DELETE FROM users WHERE id=?";;

    public void deleteFromUsersEvents(int idEvent,int idUser){
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(deleteFromUsersEvents);
            prst.setInt(1,idEvent);
            prst.setInt(2,idUser);
            prst.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if (con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);;
            }
        }
    }
    public UserEnity getUser(String login){
        UserEnity res= null;
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(getUserbyLog);
            prst.setString(1,login);
            ResultSet resultSet = prst.executeQuery();
            while (resultSet.next()) {
               res= new UserEnity(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(5),
                        resultSet.getString(4),
                        new Role(
                                resultSet.getInt(6),
                                resultSet.getString(7)
                        )
               );
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if (con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
                return null;
            }
        }
        return res;
    }
    public UserEnity getUser(int id){
        UserEnity res= null;
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(getUserId);
            prst.setInt(1,id);
            ResultSet resultSet = prst.executeQuery();
            while (resultSet.next()) {
                res= new UserEnity(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(5),
                        resultSet.getString(4),
                        new Role(
                                resultSet.getInt(6),
                                resultSet.getString(7)
                        )
                );
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if (con!=null){
                    con.close();
                }

            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }
        return res;
    }
    @Override
    public List<UserEnity> getAll() {
        List<UserEnity> list=new ArrayList<>();
        Connection con= null;
        Statement st=null;
        try {
            con = DBCPDataSource.getConnection();
            st=con.createStatement();
            ResultSet resultSet = st.executeQuery(getall);
            while (resultSet.next()) {
                list.add(
                        new UserEnity(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        new Role(
                                resultSet.getInt(6),
                                resultSet.getString(7)
                        )
                        )
                );
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(st!=null) {
                    st.close();}
                if (con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }
        return list;
    }
    public List<Event> getEventsForUser(int idUser){
        List<Event> res=new ArrayList<>();
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(getallEventsForUser);
            prst.setInt(1,idUser);
            ResultSet resultSet = prst.executeQuery();
            while (resultSet.next()) {
                res.add(
                        new Event(
                                resultSet.getInt(1),
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
                if(prst!=null) {
                    prst.close();}
                if (con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }
        return res;
    }
    public List<UserEnity> getAllByRole(int role) {
        List<UserEnity> list=new ArrayList<>();
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(getallbyRole);
            prst.setInt(1,role);
            ResultSet resultSet = prst.executeQuery();
            while (resultSet.next()) {
                list.add(
                        new UserEnity(resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                new Role(
                                        resultSet.getInt(6),
                                        resultSet.getString(7)
                                )
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


    public void updateUserFull(UserEnity user){
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(updateUserFull);
            prst.setString(1,user.getSurname());
            prst.setString(2,user.getFirstname());
            prst.setString(3,user.getPassword());
            prst.setString(4,user.getEmail());
            prst.setInt(5,user.getRole().getId());
            prst.setInt(6,user.getId());
            prst.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if (con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }
    public void updateUser(UserEnity user){
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(updateUser);
            prst.setString(1,user.getSurname());
            prst.setString(2,user.getFirstname());
            prst.setString(3,user.getPassword());
            prst.setString(4,user.getEmail());
            prst.setInt(5,user.getId());
            prst.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if (con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }
    @Override
    public UserEnity get(int id) {
        UserEnity res= null;
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(getUserId);
            prst.setInt(1,id);
            ResultSet resultSet = prst.executeQuery();
            while (resultSet.next()) {
                res= new UserEnity(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(5),
                        resultSet.getString(4),
                        new Role(
                                resultSet.getInt(6),
                                resultSet.getString(7)
                        )
                );
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if (con!=null){
                    con.close();
                }

            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
                return null;
            }
        }
        return res;
    }
    @Override
    public UserEnity save(UserEnity user) {
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(insertUser);
            prst.setString(1,user.getSurname());
            prst.setString(2,user.getFirstname());
            prst.setString(3,user.getPassword());
            prst.setString(4,user.getEmail());
            prst.setInt(5,user.getRole().getId());
            prst.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if(prst!=null) {
                    prst.close();}
                if (con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }
        return user;
    }
    @Override
    public void delete(int id) {
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = DBCPDataSource.getConnection();
            prst=con.prepareStatement(deleteUser);
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
}
