package com.wplab.service;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener {

    public MyServletContextListener() {
    	
    }
    public void contextInitialized(ServletContextEvent sce)  { 
         System.out.println(">>> ServletContext object created");
         ServletContext context=sce.getServletContext();
         DBconnectionInfo connInfo=new DBconnectionInfo();
         connInfo.setDiverName(context.getInitParameter("jdbc.driverClassName"));
         connInfo.setUrl(context.getInitParameter("jdbc.url"));
         connInfo.setUsername(context.getInitParameter("jdbc.username"));
         connInfo.setPassword(context.getInitParameter("jdbc.password"));
         
         context.setAttribute("jdbc-info", connInfo);
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println(">>> ServletContext object destroyed");
    }
	
}
