package jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlProdute extends ProdutoConnection{
	
	
	public  ProdutoConnection getConnection(){
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			return (ProdutoConnection) DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","root");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
