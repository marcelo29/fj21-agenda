package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Contato;

public class ContatoDao {
	private Connection connection;

	public ContatoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Contato contato) {
		String sql = "insert into contatos" + "(nome,endereco,email)"
				+ "values(?,?,?)";
		try {
			java.sql.PreparedStatement comando = connection
					.prepareStatement(sql);

			comando.setString(1, contato.getNome());
			comando.setString(2, contato.getEndereco());
			comando.setString(3, contato.getEmail());

			comando.execute();
			comando.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro no insert");
		}

	}

	public void altera(Contato contato) {
		String sql = "update contatos set nome=?, email=?, endereco=?"
				+ "where id=?";
		try {
			java.sql.PreparedStatement comando = connection
					.prepareStatement(sql);

			comando.setString(1, contato.getNome());
			comando.setString(2, contato.getEndereco());
			comando.setString(3, contato.getEmail());
			comando.setLong(5, contato.getId());
			comando.execute();
			comando.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro no update");
		}
	}

	public void remove(Contato contato) {
		try {
			java.sql.PreparedStatement comando = connection
					.prepareStatement("delete from contatos where id=?");
			comando.setLong(1, contato.getId());
			comando.execute();
			comando.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro no delete");
		}

	}

	public List<Contato> getLista() {
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from contatos");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				Contato contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				// adicionando o objeto � lista
				contatos.add(contato);
			}
			for (Contato contato : contatos) {
				System.out.println("Nome: " + contato.getNome());
				System.out.println("Email: " + contato.getEmail());
				System.out.println("Endere�o: " + contato.getEndereco() + "\n");
			}
			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar");
		}
	}
}