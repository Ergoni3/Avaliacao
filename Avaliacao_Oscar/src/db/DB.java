package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

	// Estabelecendo conexão com o banco de dados 
	
	
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		if(conn == null) {
			try {
			Properties props = LoadProperties();
			String url = props.getProperty("dburl");
			DriverManager.getConnection(url, props);
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	// Encerrando conexão com o banco de dados 
	
	public static void closeConnection() {
		if (conn != null){
			try {
				conn.close();
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	
	//Carregando as informações do banco de dados para conexão
	
	private static Properties LoadProperties() {
		try(FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
		
		
	}
	
	
	
}