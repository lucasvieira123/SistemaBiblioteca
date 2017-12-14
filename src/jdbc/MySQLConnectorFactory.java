package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class MySQLConnectorFactory extends ConnectorFactory {
	private static MySQLConnectorFactory instance;
	protected MySQLConnectorFactory() {}
	
	public static synchronized MySQLConnectorFactory getInstance() {
		
		if(instance==null) {
			instance = new MySQLConnectorFactory();
		}
		
		return instance;
	}

	@Override
	public ProductConnector getConnector(String url, String user, String password) {
		ProductConnector connector = new MySQLConnector(url, user, password);
		return connector;
		
	}
}
