package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	// Estabelecendo conex?o com o banco de dados 
	
	
	
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
	
	// Encerrando conex?o com o banco de dados 
	
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
	
	
	//Carregando as informa??es do banco de dados para conex?o
	
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
	
	// Fechamento do statement no DB para organizar o fechamento no programa principal
	
		public static void closeStatement(Statement st) {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
			}
		}
		
		// Fechamento do resultSet no DB para organizar o fechamento no programa principal
		
		public static void closeResultSet(ResultSet rs) {
			if (rs != null) {
				try {
				rs.close();
				} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
			}
		}
	
	
	
	
}