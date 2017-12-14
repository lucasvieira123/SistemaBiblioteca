package jdbc;

import java.sql.Connection;

public abstract class ConnectorFactory {
	
	protected String dbName;
	
	public abstract ProductConnector getConnector(String url, String user, String password);
	
	public static ConnectorFactory getInstance(String myDB) {
		ConnectorFactory connector = null; 
		
		/* 
		 * Podem ser inseridos novos condicionais
		 * para instanciar conectores de diferentes bancos. 
		 */
		if (myDB.equals("mysql") || myDB.equals("mariadb")) {
			connector = new MySQLConnectorFactory();
		}
		
		return connector;
	}
	
}
