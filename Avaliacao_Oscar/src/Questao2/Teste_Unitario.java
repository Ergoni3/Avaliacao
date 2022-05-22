package Questao2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbIntegrityException;

public class Teste_Unitario {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Para utilizar em dados que utilizam o formato data
		
		Connection conn = null;
		Statement st = null;
		PreparedStatement st2 = null;
		ResultSet rs = null;
		
		
		// Leitura dos dados armazenados no Banco de dados
		
		try {
			conn = DB.getConnection();
			
			st = conn.createStatement();
			
			rs = st.executeQuery("select * from produtos");
			
			while (rs.next()) {
				System.out.println(rs.getInt("Id") +", " + rs.getInt("Tamanho") +", " + rs.getString("Categoria") +", " + rs.getString("Cor")
				+", " + rs.getDouble("Preço")+", " + rs.getString("Marca")+", " + rs.getDate("Data_Cadastro")+", " + rs.getInt("Qtde_Em_Estoque")
				+", " + rs.getString("Descrição"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		// Inserção de dados 
		try {
			conn = DB.getConnection();

			
			st2 = conn.prepareStatement(
					"INSERT INTO produtos "
					+ "(Tamanho, Categoria, Cor, Preço, Marca, Data_Cadastro, Qtde_Em_Estoque, Descrição) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?)", 
					Statement.RETURN_GENERATED_KEYS);

			st2.setInt(1, 39);
			st2.setString(2, "Tamanco");
			st2.setString(4, "Rosa");
			st2.setDouble(5, 50.39);
			st2.setString(5, "Nova");
			st2.setDate(3, new java.sql.Date(sdf.parse("22/04/2005").getTime()));
			st2.setInt(5, 12);
			st2.setString(5, "Um tamanco alto");

			
			int rowsAffected = st.executeUpdate(null);
			
			if (rowsAffected > 0) {
				ResultSet rs1 = st.getGeneratedKeys();
				while (rs1.next()) {
					int id = rs1.getInt(1);
					System.out.println("Done! Id: " + id);
				}
			}
			else {
				System.out.println("No rows affected!");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		
		// Atualização de dados
				try {
					conn = DB.getConnection();
			
					st2 = conn.prepareStatement(
							"UPDATE produtos "
							+ "SET Preço = Preço + ? "
							+ "WHERE "
							+ "(Id = ?)");

					st2.setDouble(1, 99.99);
					st2.setInt(2, 2);
					
					int rowsAffected = st2.executeUpdate();
					
					System.out.println("Done! Rows affected: " + rowsAffected);
				}
				catch (SQLException e) {
					e.printStackTrace();
				} 
		
				
				// Deletar dados
				try {
					conn = DB.getConnection();
			
					st2 = conn.prepareStatement(
							"DELETE FROM produtos "
							+ "WHERE "
							+ "Id = ?");

					st2.setInt(1, 1);
					
					int rowsAffected = st2.executeUpdate();
					
					System.out.println("Done! Rows affected: " + rowsAffected);
				}
				catch (SQLException e) {
					throw new DbIntegrityException(e.getMessage()); // Menssagem de erro em caso de violação de integridade
				} 
		
		
		
		
		
		
		finally {
			// Fechando os recursos externos para evitar vazamento de memória
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();	
		}
	}

	

}
