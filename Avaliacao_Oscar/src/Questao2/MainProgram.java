package Questao2;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.ProdutosDao;
import model.entities.Produtos;


public class MainProgram {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ProdutosDao produtosDao = DaoFactory.createProdutosDao();
		
		
		System.out.println("=== TEST 1: Produtos findById =====");
		Produtos produtos = produtosDao.findById(2);
		System.out.println(produtos);
		
		System.out.println("=== TEST 2: Produtos findAll =====");
		List<Produtos> list = produtosDao.findAll();
		for (Produtos obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("=== TEST 3: Produtos insert =====");
		Produtos newprodutos = new Produtos(null, 37, "Chinelo", "Roxo", 85.60, "Havaianas",new Date(0), 40, "Um chinelo roxo");
		produtosDao.insert(newprodutos);
		System.out.println("Inserted! New id = " + newprodutos.getId());

		System.out.println("=== TEST 4: Produtos update =====");
		produtos = produtosDao.findById(1);
		produtos.setMarca("Havaianas");
		produtosDao.update(produtos);
		System.out.println("Update completed");

		System.out.println("=== TEST 5: Produtos delete =====");
		System.out.println("Enter id for delete test: ");
		int id = sc.nextInt();
		produtosDao.deleteById(id);
		System.out.println("Delete completed");
		
		sc.close();
		
		
	}
}
