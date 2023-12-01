package com.my.dao;

import com.my.enity.UserEnity;

import java.util.List;

public interface UserDao extends Dao<UserEnity> {

    public List<UserEnity> getAll() ;

    public UserEnity save(UserEnity user) ;


}
