package com.wplab.repository;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FileDAObyDBCP extends FileDAOImpl {
private DataSource ds=null;
	
	public FileDAObyDBCP(String dbcpresourceName) {
	
        try {
        	
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            ds = (DataSource) envContext.lookup(dbcpresourceName);
        } catch (NamingException e) {
            e.printStackTrace();
        }
		
	}
	@Override
	protected void connect() {
		try {
			conn=ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
