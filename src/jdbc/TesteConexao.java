package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConexao {
	
	
	public static void main(String[] args) throws SQLException {
		
		final String url = "jdbc:mysql://localhost:3306/java?verifyServerCertificate=false&useSSL=true";
		final String usuario = "root";
		final String senha = "";
		
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		System.out.println("Conexão Efetuada com sucesso!");
		
	}
}
