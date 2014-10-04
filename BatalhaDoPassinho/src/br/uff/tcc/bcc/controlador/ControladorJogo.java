package br.uff.tcc.bcc.controlador;

import br.uff.tcc.bcc.esii.Jogo;
import br.uff.tcc.bcc.esii.modelo.Mapa;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.visao.Botao;
import br.uff.tcc.bcc.visao.GerenciadorDeTelas;
import br.uff.tcc.bcc.visao.GerenciadorDeTelas.TipoDaTela;

public class ControladorJogo {
	
	private static ControladorJogo controlador;
	
	private Jogo jogo;
	
	private ControladorJogo(){
		jogo = new Jogo();
	}
	
	public static ControladorJogo getInstancia(){
		if (controlador == null){
			controlador = new ControladorJogo();
		}
		return controlador;
	}

	public void acao(Botao botao) {
		System.out.println(botao.getId());
	
	}
	
	public void iniciaPartida(){
		//TODO Inicia o mapa com os territórios (está na classe jogo)
		
		//Chama gerenciador de tarefas para trocar tela
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.jogo);
	}
	
	public Mapa getMapa(){
		//TODO alterar para jogo.getMapa()
		Mapa mapa = new Mapa();
		mapa.adicionaTerritorio(new Territorio("Nome1","continente1",30,45));
		mapa.adicionaTerritorio(new Territorio("Nome2","continente2",130,145));
		
		return mapa;
	}

}
