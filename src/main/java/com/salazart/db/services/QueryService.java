package com.salazart.db.services;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QueryService {
	protected Logger log = LogManager.getRootLogger();
	
	private Connection connection;
	
	protected Connection getConnection(){
		try {
			if(connection == null || connection.isClosed()){
				connection = ConnectionService.getInstance().getConnection();
			}
		} catch (Exception e) {
			log.error(e);
		}
		return connection;
	}
	
	protected void closeConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			log.error(e);
		}
	}
}
