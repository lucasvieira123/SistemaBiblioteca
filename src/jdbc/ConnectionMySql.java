package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConnectionMySql extends ConnectionFactory {
	private static ConnectionMySql instance;
	private ConnectionMySql() {}
	
	public static synchronized ConnectionMySql getInstance() {
		
		if(instance==null) {
			instance = new ConnectionMySql();
		}
		
		return instance;
	}
	
	public  Connection getConnection(){
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			return DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","root");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
