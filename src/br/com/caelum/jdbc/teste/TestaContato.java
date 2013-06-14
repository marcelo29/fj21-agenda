package br.com.caelum.jdbc.teste;

import javax.swing.JOptionPane;

import org.apache.tomcat.dbcp.jocl.JOCLContentHandler;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaContato {
	public String msgCon = "";

	public static void main(String[] args) {

		ConnectionFactory con = new ConnectionFactory();
		if (con.getConnection() == null) {
			JOptionPane.showMessageDialog(null, con.getMsgErro());
		} else {

			Contato contato = new Contato();
			contato.setNome("Manutenção");
			contato.setEmail("manut@hotmail.com");
			contato.setEndereco("R. Estado do Rio 1270");

			ContatoDao cdao = new ContatoDao();
			cdao.getLista();
			JOptionPane.showMessageDialog(null, "Contato " + contato.getNome()
					+ " Adicionado com sucesso");
		}
	}

}
