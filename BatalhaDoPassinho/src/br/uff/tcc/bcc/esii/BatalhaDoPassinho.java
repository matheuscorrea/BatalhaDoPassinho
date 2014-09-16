package br.uff.tcc.bcc.esii;

import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Territorio;

public class BatalhaDoPassinho {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Bem-vindo a Batalha do Passinho");
		Jogo jogo = new Jogo();
		Jogador  jogador = new Jogador("MC Catra", "Preto");
		jogador.adicionadConquistados(new Territorio("1","Centro"));
		jogador.adicionadConquistados(new Territorio("2","Centro"));
		jogador.adicionadConquistados(new Territorio("3","Centro"));
		jogador.adicionadConquistados(new Territorio("4","Centro"));
		
		jogador.adicionadConquistados(new Territorio("5","Centro"));
		jogador.adicionadConquistados(new Territorio("6","Niteroi"));
		jogador.adicionadConquistados(new Territorio("7","Niteroi"));
		jogador.adicionadConquistados(new Territorio("8","Niteroi"));
		jogador.adicionadConquistados(new Territorio("9","Niteroi"));
		jogador.adicionadConquistados(new Territorio("10","Niteroi"));
		jogador.adicionadConquistados(new Territorio("11","Niteroi"));
		jogador.adicionadConquistados(new Territorio("12","Niteroi"));
		jogador.adicionadConquistados(new Territorio("13","Niteroi"));
		
		
		System.out.println(jogador.numeroDeConquistados());
		System.out.println(jogo.ganhaTropa(jogador));	
	}
	
}
