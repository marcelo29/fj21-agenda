package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private String conErro;

	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/fj21",
					"root", "caelum");
		} catch (SQLException e) {
			conErro = "Erro ao conectar";
			return null;
		}
	}

	public String getMsgErro() {
		return conErro;
	}

	public void setMsgErro(String msgErro) {
		this.conErro = msgErro;
	}
}