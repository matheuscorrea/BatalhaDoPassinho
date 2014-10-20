package br.uff.tcc.bcc.esii.controlador;

import javafx.scene.control.Button;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Jogo;
import br.uff.tcc.bcc.esii.modelo.Jogo.TipoFase;
import br.uff.tcc.bcc.esii.modelo.Mapa;
import br.uff.tcc.bcc.esii.visao.ConstanteDaCor;
import br.uff.tcc.bcc.esii.visao.ConstanteDoTerritorio;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas.TipoDaTela;

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
		//System.out.println(botao.getId());
		if(jogo.faseAtual==TipoFase.FASE_1){
			System.out.println("OK1");
			Jogador jogador = jogo.getJogadorDaVez();
			if(jogador.conquistouTerritorio(botao.getId())){
				System.out.println("OK2");
				if(jogo.temTropas()){	
					System.out.println("OK3");
					jogo.distribuirTropas(botao.getId(),1);
					jogo.decrementaTropas();
					//Atualiza a visão
					botao.setText(jogo.getTropas(botao.getId())+"");
					GerenciadorDeTelas.getInstancia().atualizaBarraInformacoes(jogo);
					System.out.println(jogo.getQuantidadeDeTropas());
				}
			}else{
				//ERRO:Territorio não encontrado
			}
		}		
	}	
	
	@Deprecated
	public void acaoIniciaTurno(){
		jogo.proximaRodada();
	}
	
	public void proximaFase(){
		jogo.proximaFase();
		GerenciadorDeTelas.getInstancia().atualizaBarraInformacoes(jogo);
	}
	
	public void iniciaPartida(){
				
		jogo.adicionaJogador(new Jogador("Catra",ConstanteDaCor.CINZA));
		jogo.adicionaJogador(new Jogador("Anitta",ConstanteDaCor.ROSA));
		jogo.adicionaJogador(new Jogador("Nego Bam",ConstanteDaCor.VERDE));
		jogo.adicionaJogador(new Jogador("Thadeu",ConstanteDaCor.VERMELHO));
		
		jogo.distribuiTerritorio();
		
		jogo.faseAtual = TipoFase.FASE_1;
		
		jogo.calculaTropa(jogo.getJogadorDaVez());
		
		//Chama gerenciador de tarefas para trocar tela
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.jogo);
		GerenciadorDeTelas.getInstancia().atualizaBarraInformacoes(jogo);
	}
	
	public Mapa getMapa(){
		return jogo.getMapa();		
	}

}
