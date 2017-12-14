package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector extends ProductConnector{
	
	private String url;
	private String user;
	private String password;
	
	public MySQLConnector(String newUrl, String newUser, String newPassword) {
		this.url = newUrl;
		this.user = newUser;
		this.password = newPassword;
		
	}
	
	public  Connection getConnection(){
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			return DriverManager.getConnection(this.url, this.user, this.password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
