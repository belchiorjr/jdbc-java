package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ExcluirPessoa {
	public static void main(String[] args) throws SQLException, IOException {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Informe o código: ");

		int codigo = entrada.nextInt();

		Connection conexao = FabricaConexao.getConexao();
		String sql = "DELETE FROM pessoas WHERE codigo = ?";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, codigo);
		
		if	(stmt.executeUpdate() > 0) {
			System.err.println("Exclusão realizada com sucesso!");
		} else {
			System.err.println("Não foi possível realizar a exclusão!");
		}
		
		stmt.close();
		conexao.close();
	}
}
