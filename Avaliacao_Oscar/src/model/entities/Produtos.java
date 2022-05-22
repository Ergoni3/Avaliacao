package model.entities;

import java.util.Objects;
import java.io.Serializable;
import java.sql.Date;

public class Produtos implements Serializable {
	// Serializable implementado para possivel formação de arquivos
	private static final long serialVersionUID = 1L;
	
	
	// Atributos
	private Integer Id;
	private int Tamanho;
	private String Categoria;
	private String Cor;
	private double Preço;
	private String Marca;
	private Date Data_Cadastro;
	private int Qtde_em_estoque;
	private String Descrição;
	
	
	// Construtores
	public Produtos() {
		
	}

	public Produtos(Integer id, int tamanho, String categoria, String cor, double preço, String marca,
			Date data_Cadastro, int qtde_em_estoque, String descrição) {
		
		Id = id;
		Tamanho = tamanho;
		Categoria = categoria;
		Cor = cor;
		Preço = preço;
		Marca = marca;
		Data_Cadastro = data_Cadastro;
		Qtde_em_estoque = qtde_em_estoque;
		Descrição = descrição;
	}
	
	// Getters e Setters
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public int getTamanho() {
		return Tamanho;
	}

	public void setTamanho(int tamanho) {
		Tamanho = tamanho;
	}

	public String getCategoria() {
		return Categoria;
	}

	public void setCategoria(String categoria) {
		Categoria = categoria;
	}

	public String getCor() {
		return Cor;
	}

	public void setCor(String cor) {
		Cor = cor;
	}

	public double getPreço() {
		return Preço;
	}

	public void setPreço(double preço) {
		Preço = preço;
	}

	public String getMarca() {
		return Marca;
	}

	public void setMarca(String marca) {
		Marca = marca;
	}

	public Date getData_Cadastro() {
		return Data_Cadastro;
	}

	public void setData_Cadastro(Date data_Cadastro) {
		Data_Cadastro = data_Cadastro;
	}

	public int getQtde_em_estoque() {
		return Qtde_em_estoque;
	}

	public void setQtde_em_estoque(int qtde_em_estoque) {
		Qtde_em_estoque = qtde_em_estoque;
	}

	public String getDescrição() {
		return Descrição;
	}

	public void setDescrição(String descrição) {
		Descrição = descrição;
	}

	
	//Hashcode e equals
	
	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produtos other = (Produtos) obj;
		return Objects.equals(Id, other.Id);
	}

	
	//To String
	
	@Override
	public String toString() {
		return "Produtos [Id=" + Id + ", Tamanho=" + Tamanho + ", Categoria=" + Categoria + ", Cor=" + Cor + ", Preço="
				+ Preço + ", Marca=" + Marca + ", Data_Cadastro=" + Data_Cadastro + ", Qtde_em_estoque="
				+ Qtde_em_estoque + ", Descrição=" + Descrição + "]";
	}	
	
}
