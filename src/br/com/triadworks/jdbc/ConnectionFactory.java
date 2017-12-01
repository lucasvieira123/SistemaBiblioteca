package br.com.triadworks.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConnectionFactory {
	public Connection getConnection(){
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","root");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
