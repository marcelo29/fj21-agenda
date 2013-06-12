package br.com.caelum.jdbc.teste;

import br.com.caelum.jdbc.dao.ContatoDao;

public class TestaContato {

	public static void main(String[] args) {

		ContatoDao cdao = new ContatoDao();
		cdao.getLista();
	}

}
