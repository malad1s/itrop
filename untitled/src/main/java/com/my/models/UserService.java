package com.my.models;


import com.my.enity.Event;
import com.my.enity.Role;
import com.my.enity.UserEnity;
import com.my.dao.UserDaoImpl;
import com.my.utils.EmailSend;
import com.my.utils.RandomPassword;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

public class UserService {

    public UserEnity login(String login, String password){
        UserEnity user= new UserDaoImpl().getUser(login);
       // String writtenPassword=DigestUtils.md5Hex(password);
        if(user!=null&&user.getPassword().equals(password)){
            return user;
        }else{
            return null;
        }
    }
    public UserEnity getUser(int id){
        return (new UserDaoImpl().getUser(id));
    }
    public UserEnity getUser(String email){
        return (new UserDaoImpl().getUser(email));
    }

    public UserEnity createUser(String email){
        UserEnity user =new UserEnity();
        //check user with this email;
        user.setEmail(email);
        user.setRole(new Role(1));
        //create password
        //String password=RandomPassword.createPassword();
        //user.setPassword(DigestUtils.md5Hex(password));
        new UserDaoImpl().save(user);
        user=new UserDaoImpl().getUser(email);
        // SEND PASSWORD ON EMAIL
        EmailSend.sendEmailAboutRegistrationOnSiteForUser(user);
        return user;
    }

    public List<Event> getEventsForUser(int idUser){
        return new UserDaoImpl().getEventsForUser(idUser);
    }

    public void unsubscribingUserFromEvent(int idEvent, int idUser){
        new UserDaoImpl().deleteFromUsersEvents(idEvent,idUser);
    };
    public UserEnity updateUser(int idUser, String surname, String firstname, String login, String password){
       UserEnity user=new UserEnity(
               idUser,
               surname,
               firstname,
               login,
               DigestUtils.md5Hex(password),
               null
       );
       new UserDaoImpl().updateUser(user);
       return new UserService().getUser(idUser);
    }
    public List<UserEnity> getSpeakers(){
        return new UserDaoImpl().getAllByRole(3);
    }

    public UserEnity register(UserEnity newUser){
        UserEnity user= ((new UserDaoImpl()).getUser(newUser.getEmail()));
        if(user==null){
            //newUser.setPassword(DigestUtils.md5Hex(newUser.getPassword()));
            new UserDaoImpl().save(newUser);
            return new UserDaoImpl().getUser(newUser.getEmail());
        }else{
            return null;
        }

    }






}
