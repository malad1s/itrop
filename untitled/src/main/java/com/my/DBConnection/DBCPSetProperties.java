package com.my.DBConnection;

import com.my.Text;
import com.my.listeners.AppContextListener;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBCPSetProperties {
    private static final org.slf4j.Logger logger= LoggerFactory.getLogger(DBCPSetProperties.class);

    public static void setProperties(){
        BasicDataSource ds=DBCPDataSource.getDataSource();
        Properties prop = new Properties();
        try {

            prop.load(Text.class.getClassLoader().getResourceAsStream("db.properties"));
            ds.setMinIdle(Integer.parseInt(prop.getProperty("DbMySql.MinIdle")));
            ds.setMaxIdle(Integer.parseInt(prop.getProperty("DbMySql.MaxIdle")));
            ds.setMaxOpenPreparedStatements(Integer.parseInt(prop.getProperty("DbMySql.MaxOpenPreparedStatements")));
            ds.setUrl(prop.getProperty("DbMySql.Url"));
            ds.setConnectionProperties(prop.getProperty("DbMySql.ConnectionProperties"));
            ds.setUsername(prop.getProperty("DbMySql.Username"));
            ds.setPassword(prop.getProperty("DbMySql.Password"));
            ds.setDriverClassName(prop.getProperty("DbMySql.DriverClassName"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    };
}
