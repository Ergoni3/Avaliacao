package model.dao;

import java.util.List;

import model.entities.Produtos;

public interface ProdutosDao {
	
	
	// Dao para o acesso a dados do objeto
	

	void insert(Produtos obj);
	void update(Produtos obj);
	void deleteById(Integer id);
	Produtos findById(Integer id);
	List<Produtos> findAll();
}
