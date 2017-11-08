package com.jjdev.ls.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JConnectionFactory {

	private static JConnectionFactory instance;

	public static synchronized JConnectionFactory getInstance() {
		if (instance == null) {
			instance = new JConnectionFactory();
		}
		return instance;
	}

	public Connection getConnection() {

		Connection conn = null;

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection("jdbc:mysql://localhost/logistics_service", "root", "master");
		} catch (SQLException e) {
			System.out.println("ERROR: " + e);
		}

		return conn;
	}
}
