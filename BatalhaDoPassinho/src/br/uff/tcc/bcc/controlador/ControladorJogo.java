package br.uff.tcc.bcc.controlador;

import javafx.scene.control.Button;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Jogo;
import br.uff.tcc.bcc.esii.modelo.Jogo.TipoFase;
import br.uff.tcc.bcc.esii.modelo.Mapa;
import br.uff.tcc.bcc.esii.modelo.Territorio;
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

	public void acao(Button botao) {
		System.out.println(botao.getId());
		if(jogo.faseAtual==TipoFase.FASE_1){
			Jogador jogador = jogo.getJogadorDaVez();
			if(jogador.conquistouTerritorio(botao.getId())){
				int quantidadeDeTropas=jogo.ganhaTropa(jogador);
				while(quantidadeDeTropas>0){				
					
						jogo.distribuirTropas(botao.getId(),1);
						quantidadeDeTropas--;
					
					//Atualiza a visão
					botao.setText(jogo.getTropas(botao.getId())+"");
					System.out.println(quantidadeDeTropas);
				}
			}else{
				//ERRO:Territorio não encontrado
			}
		}
		
	}
	
	public void iniciaPartida(){
		jogo.inicializaMapa();
		
		Mapa mapa = jogo.getMapa();
		
		Jogador jogador =new Jogador("Catra","Preto");
		jogador.adicionaConquistados(mapa.getTerritorio(ConstanteDoTerritorio.BARRA_MUSIC.toString()));
		jogador.adicionaConquistados(mapa.getTerritorio(ConstanteDoTerritorio.NUTH.toString()));
		jogador.adicionaConquistados(mapa.getTerritorio(ConstanteDoTerritorio.ZAX.toString()));
		
		jogo.faseAtual = TipoFase.FASE_1;
		
		jogo.adicionaJogador(jogador);
		
		//Chama gerenciador de tarefas para trocar tela
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.jogo);
	}
	
	public Mapa getMapa(){
		return jogo.getMapa();
		
	}

}
