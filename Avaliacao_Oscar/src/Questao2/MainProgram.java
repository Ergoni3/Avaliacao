package Questao2;

import java.sql.Connection;

import db.DB;

public class MainProgram {

	public static void main(String[] args) {
		
		Connection conn = DB.getConnection();
		DB.closeConnection();

	}

}
