package br.uff.tcc.bcc.controlador;

import javafx.scene.control.Button;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Jogo;
import br.uff.tcc.bcc.esii.modelo.Jogo.TipoFase;
import br.uff.tcc.bcc.esii.modelo.Mapa;
import br.uff.tcc.bcc.visao.ConstanteDaCor;
import br.uff.tcc.bcc.visao.ConstanteDoTerritorio;
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

	public void acaoTerritorio(Button botao) {
		System.out.println(botao.getId());
		if(jogo.faseAtual==TipoFase.FASE_1){
			Jogador jogador = jogo.getJogadorDaVez();
			if(jogador.conquistouTerritorio(botao.getId())){
				if(jogo.temTropas()){				
					jogo.distribuirTropas(botao.getId(),1);
					jogo.decrementaTropas();
					//Atualiza a visão
					botao.setText(jogo.getTropas(botao.getId())+"");
					System.out.println(jogo.getQuantidadeDeTropas());
				}
			}else{
				//ERRO:Territorio não encontrado
			}
		}		
	}	
	
	public void acaoIniciaTurno(){
		jogo.proximaRodada();
	}
	
	public void iniciaPartida(){
				
		Mapa mapa = jogo.getMapa();
		
		jogo.adicionaJogador(new Jogador("Catra",ConstanteDaCor.CINZA));
		jogo.adicionaJogador(new Jogador("Anitta",ConstanteDaCor.ROSA));
		jogo.adicionaJogador(new Jogador("Nego Bam",ConstanteDaCor.VERDE));
		jogo.adicionaJogador(new Jogador("Thadeu",ConstanteDaCor.VERMELHO));
		
		jogo.distribuiTerritorio();
		
		jogo.faseAtual = TipoFase.FASE_1;
		
		//Chama gerenciador de tarefas para trocar tela
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.jogo);
	}
	
	public Mapa getMapa(){
		return jogo.getMapa();		
	}

}
