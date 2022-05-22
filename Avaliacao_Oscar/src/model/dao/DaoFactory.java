package model.dao;

import db.DB;
import model.dao.impl.ProdutosDaoJDBC;

public class DaoFactory {

	public static ProdutosDao createProdutosDao() {
		// Classe auxiliar responsavel por instanciar os Dao
		
		return new ProdutosDaoJDBC(DB.getConnection());	
		
	}
	
}
