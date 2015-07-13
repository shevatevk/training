package hu.neuron.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String URL = "jdbc:hsqldb:file:data/db;shutdown=true";
	private static final String USER = "SA";
	private static final String PASS = "";

	public static Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(URL, USER, PASS);
		return conn;
	}
}
