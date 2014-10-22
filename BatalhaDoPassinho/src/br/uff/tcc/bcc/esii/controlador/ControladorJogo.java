package br.uff.tcc.bcc.esii.controlador;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.sun.java.swing.plaf.windows.WindowsTreeUI.CollapsedIcon;

import javafx.scene.control.Button;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Jogo;
import br.uff.tcc.bcc.esii.modelo.Jogo.TipoFase;
import br.uff.tcc.bcc.esii.modelo.Mapa;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.esii.visao.ConstanteDaCor;
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
	
	private boolean selecionouTerritorioProprio = false;
	private boolean selecionouTerritorioInimigo = false;
	private boolean selecionouTerritorioFonte = false;
	private boolean selecionouTerritorioDestino = false;
	private Territorio territorioAtacante;
	private Territorio territorioDefensor;
	private Territorio territorioFonte;
	private Territorio territorioDestino;
	private List<Territorio> jaMovidos = new LinkedList<Territorio>();
	int tropasMovendo;

	public void acaoTerritorio(Button botao) {
		//System.out.println(botao.getId());
		Jogador jogador = jogo.getJogadorDaVez();
		if(jogo.faseAtual==TipoFase.FASE_1){
//			System.out.println("OK1");			
			if(jogador.conquistouTerritorio(botao.getId())){
//				System.out.println("OK2");
				if(jogo.temTropas()){	
//					System.out.println("OK3");
					jogo.distribuirTropas(botao.getId(),1);
					jogo.decrementaTropas();
					//Atualiza a visão
					botao.setText(jogo.getTropas(botao.getId())+"");
					GerenciadorDeTelas.getInstancia().atualizaBarraInformacoes(jogo);
					System.out.println(jogo.getQuantidadeDeTropas());
				}
			}else{
				//botao.setDisable(true);
			}
		}else if(jogo.faseAtual == TipoFase.FASE_2 && !selecionouTerritorioProprio){
			if(jogador.conquistouTerritorio(botao.getId())){
				//botao.setDisable(true);
				selecionouTerritorioProprio = true;
				territorioAtacante = jogador.getTerritorioConquistado(botao.getId());
			}
		}else if(jogo.faseAtual == TipoFase.FASE_2 && !selecionouTerritorioInimigo){
			if(!jogador.conquistouTerritorio(botao.getId())){
				if(territorioAtacante.getVizinhos().contains(botao.getId()));{
					selecionouTerritorioInimigo = true;
					for(Territorio t: territorioAtacante.getVizinhos()){
						if(t.getNome().equals(botao.getId())){
							territorioDefensor = t;
							break;
						}
					}					
				}				
			}
				
		}else if(jogo.faseAtual == TipoFase.FASE_3 && !selecionouTerritorioFonte){
			if(jogador.conquistouTerritorio(botao.getId())){
				if(!jaMovidos.contains(botao.getId())){
						selecionouTerritorioFonte = true;
						territorioFonte = jogador.getTerritorioConquistado(botao.getId());
						System.out.println("escolheu fonte");
					}
				}
			
		}else if(jogo.faseAtual == TipoFase.FASE_3 && selecionouTerritorioFonte){
			if(jogador.conquistouTerritorio(botao.getId())){
				if(territorioFonte.getVizinhos().contains(botao.getId())){
					selecionouTerritorioDestino = true;
					territorioDestino = jogador.getTerritorioConquistado(botao.getId());
					System.out.println("escolheu fonte");
				}
			}
		}
	}
		
	
	public void desabilitaBotoes(Jogador jogador, boolean meusTerritorios){		
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
		
		List<Jogador> listaJogadores = ControladorTelaEscolha.getInstancia().getListaJogadores();
		Collections.shuffle(listaJogadores);
		
		for (Jogador jogador : listaJogadores) {
			jogo.adicionaJogador(jogador);
		}
		
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

	public void acaoAtaque(Button clickedBtn) {
		if(selecionouTerritorioInimigo && selecionouTerritorioProprio){
			boolean dominouTerrritorio = jogo.ataque(territorioAtacante, territorioDefensor);
			selecionouTerritorioInimigo = false;
			selecionouTerritorioProprio = false;
			if(dominouTerrritorio){
				
				if(territorioAtacante.getDono().getObjetivo().concluido(territorioAtacante.getDono(), territorioDefensor.getDono())){
					//Ganhou
				}
				//Jogador acabou de perder seu último território
				if(territorioDefensor.getDono().numeroDeConquistados()==1){
					jogo.eliminaJogador(territorioAtacante.getDono(), territorioDefensor.getDono());
				}
				//TODO Pegar da visão quantas tropas passar para o territorio dominado
				jogo.dominarTerritorio(territorioAtacante, territorioDefensor, 1);
			}
		}
		
	}
	
	public void acaoMover(Button moveBtn){
		if(selecionouTerritorioFonte && selecionouTerritorioDestino){			
			//ver se fonte tem pelo menos 2 tropas
			if(territorioFonte.getQuantidadeTropa() > 1){
				jogo.redistribuiTropa(territorioFonte, territorioDestino, 1);
				System.out.println("moveu");
			}
			jaMovidos.add(territorioDestino);
		}		
	}
	
	public void terminaMover(Button endMoves){
		selecionouTerritorioFonte = false;
		selecionouTerritorioDestino = false;
		jaMovidos.clear();		
		proximaFase();
	}
	
	

	

}
