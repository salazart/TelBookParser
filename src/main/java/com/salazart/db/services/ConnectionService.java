package com.salazart.db.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.salazart.TelBookParser;
import com.salazart.folder.services.PropertyService;

public class ConnectionService {
	private static ConnectionService instance;
	
	private ConnectionService(){
	}

	public static ConnectionService getInstance() {
        if (instance == null) {
        	synchronized(ConnectionService.class) {
        		if(instance == null) {
        			instance = new ConnectionService();
        		}
        	}
        }
        return instance;
    }
	
	public Connection getConnection() {
		Properties prop = PropertyService.getProperties();

		String driver = prop.getProperty("driver");

		try {
			TelBookParser.log4j.info(" Connecting to database...");
			return DriverManager.getConnection(driver, prop);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
			TelBookParser.log4j.error(" Don't connected to database...");
			TelBookParser.log4j.error(e.getMessage());
			return null;
		}
	}
}
