package br.com.caelum.jdbc.teste;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import br.com.caelum.jdbc.ConnectionFactory;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		Connection connection = new ConnectionFactory().getConnection();
		JOptionPane.showMessageDialog(null, "Conexao Aberta");
		connection.close();
	}

}
