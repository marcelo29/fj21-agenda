package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Funcionario;

public class FuncionarioDao {

	private Connection connection;
	String msg;

	public FuncionarioDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Funcionario funcionario) {
		String sql = "insert into funcionarios" + "(nome,usuario,senha)"
				+ "values(?,?,?)";
		try {
			java.sql.PreparedStatement comando = connection
					.prepareStatement(sql);

			comando.setString(1, funcionario.getNome());
			comando.setString(2, funcionario.getUsuario());
			comando.setString(3, funcionario.getSenha());

			comando.execute();
			comando.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro no insert");
		}
	}

	public void altera(Funcionario funcionario) {
		String sql = "update funcionarios set nome=?, usuario=? ,senha=?"
				+ "where id=?";
		try {
			java.sql.PreparedStatement comando = connection
					.prepareStatement(sql);

			comando.setString(1, funcionario.getNome());
			comando.setString(2, funcionario.getUsuario());
			comando.setString(3, funcionario.getSenha());

			comando.execute();
			comando.close();

		} catch (SQLException e) {
			throw new RuntimeException("Erro no update");
		}
	}

	public void remove(Funcionario funcionario) {
		try {
			java.sql.PreparedStatement comando = connection
					.prepareStatement("delete from funcionarios"
							+ " where id=?");
			comando.setLong(1, funcionario.getId());
			comando.execute();
			comando.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro no delete");
		}

	}

	public List<Funcionario> getLista() {
		try {
			List<Funcionario> funcionarios = new ArrayList<Funcionario>();
			PreparedStatement stmt = connection
					.prepareStatement("select * from funcionarios");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rs.getLong("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setUsuario(rs.getString("usuario"));
				funcionario.setSenha(rs.getString("senha"));
				
				funcionarios.add(funcionario);
			}
			for (Funcionario funcionario : funcionarios) {
				System.out.println("Nome: " + funcionario.getNome());
				System.out.println("Usuario: " + funcionario.getUsuario());
				System.out.println("Senha: " + funcionario.getSenha()+"\n");
			}
			rs.close();
			stmt.close();
			return funcionarios;			
		} catch (SQLException e) {
			throw new RuntimeException("Erro na lista");
		}

	}
}
