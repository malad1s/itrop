package com.my.DBConnection;

import com.my.dao.UserDaoImpl;
import com.my.utils.Utils;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCPDataSource {
    private static final Logger logger= LoggerFactory.getLogger(DBCPDataSource.class);

    private static  BasicDataSource ds;

    public synchronized static BasicDataSource getDataSource(){
        if (ds==null){
            ds=new BasicDataSource();
            DBCPSetProperties.setProperties();
        }
        return ds;
    }
    public synchronized static  Connection getConnection()  {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }
    private DBCPDataSource(){ }
}