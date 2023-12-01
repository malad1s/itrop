package com.my.listeners;

import com.my.DBConnection.DBCPDataSource;
import com.my.DBConnection.DBCPSetProperties;
import org.apache.commons.dbcp.BasicDataSource;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;
import java.net.MalformedURLException;
import java.util.Properties;


@WebListener
public class AppContextListener implements ServletContextListener {
    private static final org.slf4j.Logger logger= LoggerFactory.getLogger(AppContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context=sce.getServletContext();
        try {
            System.setProperty("log4j.configuration", new File("C:\\Users\\admin\\Desktop\\EPAM\\project\\conferences\\untitled\\src\\main\\resources\\log4j.properties").toURL().toString());
        } catch (MalformedURLException e) {
            logger.error(e.getMessage(),e);
        }
        DBCPSetProperties.setProperties();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
