package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdatePessoa {

	public static void main(String[] args) throws IOException {
		// Pequisar registro pelo id
		Scanner entrada = new Scanner(System.in);
		System.out.println("Digite o código da pessoa: ");
		int codigo = entrada.nextInt();
		
		Connection conexao = FabricaConexao.getConexao();
		String select = "SELECT codigo, nome FROM pessoas WHERE codigo = ?";
		String update =  "UPDATE pessoas SET nome = ? WHERE codigo = ?";
		
		try {
			System.out.println("Código digitado: " + codigo);
			PreparedStatement stmt = conexao.prepareStatement(select);
			stmt.setInt(1, codigo);
			
			ResultSet r = stmt.executeQuery();
			
			if (r.next()) {
				Pessoa pessoa = new Pessoa(r.getInt(1), r.getString(2));
				System.out.println("A pessoa encontrada é : " + r.getString(2));
				entrada.nextLine();
				
				System.out.println("Digite o novo nome para a pessoa " + pessoa.getNome() + " :");
				String novoNome = entrada.nextLine();
				
				stmt.close();
				
				stmt = conexao.prepareStatement(update);
				stmt.setString(1, novoNome);
				stmt.setInt(2, codigo);
				stmt.executeUpdate();
				
				System.out.println("Alteração realizada com sucesso!");
			} else {
				System.out.println("Não foi possível encontrar a pessoa.");
			}
			
			stmt.close();
			conexao.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		entrada.close();
	}
}
