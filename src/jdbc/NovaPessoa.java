package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class NovaPessoa {
	public static void main(String[] args) throws IOException {
		
		Scanner entrada = new Scanner(System.in);
		System.out.println("Informe o nome: ");
		String nome = entrada.nextLine();
		entrada.close();

		// Modo de insert protegido contra injections
		String sql = "INSERT INTO pessoas(nome) VALUES(?)";
		
		try {
			Connection conexao = FabricaConexao.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.execute();
			conexao.close();
			System.out.println("\n" + nome + ", foi incluido com sucesso na base de dados!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
