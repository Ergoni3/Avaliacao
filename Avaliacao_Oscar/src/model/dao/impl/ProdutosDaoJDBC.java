package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import db.DB;
import db.DbException;
import model.dao.ProdutosDao;
import model.entities.Produtos;

public class ProdutosDaoJDBC implements ProdutosDao {

	
	// Objeto de conexão para utilizar as funções a seguir
	private Connection conn;	
	public ProdutosDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Produtos obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO produtos "
					+ "(Tamanho, Categoria, Cor, Preço, Marca, Data_Cadastro, Quantidade_Em_Estoque, Descrição) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, obj.getTamanho());
			st.setString(2, obj.getCategoria());
			st.setString(3, obj.getCor());
			st.setDouble(4, obj.getPreço());
			st.setString(5, obj.getMarca());
			st.setDate(6, new java.sql.Date(obj.getData_Cadastro().getTime()));
			st.setInt(7, obj.getQtde_em_estoque());
			st.setString(8, obj.getDescrição());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Produtos obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE produtos "
					+ "SET Tamanho = ?, Categoria = ?, Cor = ?, Preço = ?, Marca = ? , Data_cadastro = ?, Quantidade_Em_estoque = ?, Descrição = ?"
					+ "WHERE Id = ?");
			
			st.setInt(1, obj.getTamanho());
			st.setString(2, obj.getCategoria());
			st.setString(3, obj.getCor());
			st.setDouble(4, obj.getPreço());
			st.setString(5, obj.getMarca());
			st.setDate(6, new java.sql.Date(obj.getData_Cadastro().getTime()));
			st.setInt(7, obj.getQtde_em_estoque());
			st.setString(8, obj.getDescrição());
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM produtos WHERE Id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Produtos findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
			"SELECT * FROM produtos  "
			+ "WHERE Id = ? ");
			
			st.setInt(1,id);
			rs = st.executeQuery();
			if(rs.next()) {
			Produtos obj = new Produtos();
			obj.setId(rs.getInt("Id"));
			obj.setTamanho(rs.getInt("Tamanho"));
			obj.setCategoria(rs.getString("Categoria"));
			obj.setCor(rs.getString("Cor"));
			obj.setPreço(rs.getDouble("Preço"));
			obj.setMarca(rs.getString("Marca"));
			obj.setData_Cadastro(rs.getDate("Data de cadastro"));
			obj.setQtde_em_estoque(rs.getInt("Quantidade em estoque"));
			obj.setDescrição(rs.getString("Descrição"));
			}
			return null;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}	
	}

	@Override
	public List<Produtos> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
			"SELECT * FROM produtos ");
					
			rs = st.executeQuery();
			
			List<Produtos> list = new ArrayList<>();
			
			while(rs.next()) {
			Produtos obj = new Produtos();
			list.add(obj);
			}
			return list;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}	
		
		
		
	}	
}
