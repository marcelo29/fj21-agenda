package br.com.caelum.jdbc.teste;

import br.com.caelum.jdbc.dao.FuncionarioDao;

public class TestaFuncionario {
	//consegue
	public static void main(String[] args) {
		FuncionarioDao fdao = new FuncionarioDao();
		fdao.getLista();
	}

}
