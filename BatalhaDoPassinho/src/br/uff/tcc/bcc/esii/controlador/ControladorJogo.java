package br.uff.tcc.bcc.esii.controlador;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.Button;
import br.uff.tcc.bcc.esii.modelo.Carta;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Jogo;
import br.uff.tcc.bcc.esii.modelo.Jogo.TipoFase;
import br.uff.tcc.bcc.esii.modelo.Mapa;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
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
	//Atributos criados para evento território
	//TODO Verificar com cuidado se há necessidade de tantos atributos do mesmo tipo (reutilizar boolean's por exemplo) 
	private boolean selecionouTerritorioProprio = false;
	private boolean selecionouTerritorioInimigo = false;
	private boolean selecionouTerritorioFonte = false;
	private boolean selecionouTerritorioDestino = false;
	private boolean dominouTerrritorio = false;
	private Territorio territorioAtacante;
	private Territorio territorioDefensor;
	private Button btAtacante;
	private Button btDefensor;
	private Button btFonte;
	private Button btDestino;
	public  Territorio territorioFonte;
	public Territorio territorioDestino;
	private List<String> jaMovidos = new LinkedList<String>();
	int tropasMovendo;
	
	//Atributos criados para evento território
	
	public void acaoTerritorio(Button botao) {
	
		Jogador jogador = jogo.getJogadorDaVez();
		
		switch (jogo.faseAtual) {
		case FASE_1:
			if(jogador.possuiTerritorio(botao.getId())){
				if(jogo.temTropas()){	
					jogo.distribuirTropas(botao.getId(),1);
					jogo.decrementaTropas();
					//Atualiza a visão
					botao.setText(jogo.getTropas(botao.getId())+"");
					GerenciadorDeTelas.getInstancia().atualizaBarraInformacoes(jogo);
				}
			}
			break;
		case FASE_2:
			if(!selecionouTerritorioProprio){
				if(jogador.possuiTerritorio(botao.getId()) && jogador.getConquistados().get(botao.getId()).getQuantidadeTropa() > 1){
					botao.setDisable(true);
					selecionouTerritorioProprio = true;
					territorioAtacante = jogador.getTerritorioConquistado(botao.getId());
					btAtacante = botao;
				}
			} else if(!selecionouTerritorioInimigo){
				if(!jogador.possuiTerritorio(botao.getId())){
					if(territorioAtacante.getVizinhos().containsKey(botao.getId())){
						botao.setDisable(true);
						btDefensor = botao;
						selecionouTerritorioInimigo = true;
						territorioDefensor = territorioAtacante.getVizinhos().get(botao.getId());				
					}				
				}
			}
			break;
		case FASE_3:
			if(selecionouTerritorioDestino){
				selecionouTerritorioFonte=false;
				selecionouTerritorioDestino=false;				
				if(jogador.possuiTerritorio(botao.getId())){
					if(!jaMovidos.contains(botao.getId())){
							selecionouTerritorioFonte = true;
							territorioFonte = jogador.getTerritorioConquistado(botao.getId());
							btFonte = botao;
							GerenciadorDeTelas.getInstancia().atualizaBarraInformacoes(jogo);
					}
				}
			} else if(!selecionouTerritorioFonte){
				if(jogador.possuiTerritorio(botao.getId())){
					if(!jaMovidos.contains(botao.getId())){
							selecionouTerritorioFonte = true;
							territorioFonte = jogador.getTerritorioConquistado(botao.getId());
							btFonte = botao;
							GerenciadorDeTelas.getInstancia().atualizaBarraInformacoes(jogo);
					}
				}
			} else if(selecionouTerritorioFonte){
				if(jogador.possuiTerritorio(botao.getId())){
					if(territorioFonte.getVizinhos().containsKey(botao.getId())){
						selecionouTerritorioDestino = true;
						territorioDestino = jogador.getTerritorioConquistado(botao.getId());
						btDestino = botao;
						GerenciadorDeTelas.getInstancia().atualizaBarraInformacoes(jogo);
					}
				}
			}
			break;
		}
	}
	
	public void proximaFase(){
		
		switch (jogo.faseAtual) {
		case FASE_1:
			break;
		case FASE_2:
			if(selecionouTerritorioInimigo){
				selecionouTerritorioInimigo = false;
				btDefensor.setDisable(false);
			}
			if(selecionouTerritorioProprio){
				selecionouTerritorioProprio = false;
				btAtacante.setDisable(false);
			}
			break;
		case FASE_3:
			selecionouTerritorioFonte=false;
			selecionouTerritorioDestino=false;
			jaMovidos.clear();
			if(dominouTerrritorio){
				jogo.getJogadorDaVez().ganhaCarta();
				if(jogo.getJogadorDaVez().getMao().size()>5){
					do{
						jogo.getJogadorDaVez().descartar();
					}while(jogo.getJogadorDaVez().getMao().size() >5);
				}
			}
			break;
		}		
		jogo.proximaFase();		
		GerenciadorDeTelas.getInstancia().atualizaBarraInformacoes(jogo);
	}
	
	public void iniciaPartida(){
		
		List<Jogador> listaJogadores = ControladorTelaEscolha.getInstancia().getListaJogadores();
		Collections.shuffle(listaJogadores);
		
		for (Jogador jogador : listaJogadores) {
			jogo.adicionaJogador(jogador);
			
		}
		jogo.distribuiObjetivos(listaJogadores);		
		jogo.distribuiTerritorio();		
		jogo.faseAtual = TipoFase.FASE_1;		
		jogo.calculaTropa(jogo.getJogadorDaVez());		
		//Chama gerenciador de tarefas para trocar tela
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.JOGO);
		GerenciadorDeTelas.getInstancia().atualizaBarraInformacoes(jogo);
	}
	
	public Mapa getMapa(){
		return jogo.getMapa();		
	}

	public void acaoAtaque(Button clickedBtn) {
		if(selecionouTerritorioInimigo && selecionouTerritorioProprio){
			dominouTerrritorio = jogo.ataque(territorioAtacante, territorioDefensor);
			btAtacante.setText(""+territorioAtacante.getQuantidadeTropa());
			btDefensor.setText(""+territorioDefensor.getQuantidadeTropa());
			selecionouTerritorioInimigo = false;
			selecionouTerritorioProprio = false;
			btAtacante.setDisable(false);
			btDefensor.setDisable(false);
			if(dominouTerrritorio){
				
				if(territorioAtacante.getDono().getObjetivo().concluido(territorioAtacante.getDono(), territorioDefensor.getDono())){
					//TODO Implementar lógica do método ganharJogo() e como o controlador deve se comportar nesse caso
					jogo.ganharJogo(territorioAtacante.getDono());
				}
				//Jogador acabou de perder seu último território
				if(territorioDefensor.getDono().numeroDeConquistados()==1){
					jogo.eliminaJogador(territorioAtacante.getDono(), territorioDefensor.getDono());
				}
		
				//TODO Rever com cuidado
				//TODO Pegar da visão quantas tropas passar para o territorio dominado 
				jogo.dominarTerritorio(territorioAtacante, territorioDefensor, 1);
				btAtacante.setText(territorioAtacante.getQuantidadeTropa()+"");
				btDefensor.setText(territorioDefensor.getQuantidadeTropa()+"");
				btDefensor.setGraphic(FabricaDeBotoes.criaImageView(territorioDefensor));				
			}
		}		
	}
	
	public void acaoMover(Button moveBtn){
		if(selecionouTerritorioFonte && selecionouTerritorioDestino){			
			//ver se fonte tem pelo menos 2 tropas
			if(territorioFonte.getQuantidadeTropa() > 1){
				jogo.redistribuiTropa(territorioFonte, territorioDestino, 1);
				btFonte.setText(territorioFonte.getQuantidadeTropa()+"");
				btDestino.setText(territorioDestino.getQuantidadeTropa()+"");
			}
			jaMovidos.add(territorioDestino.getNome());
		}		
	}	
	
	public void acaoTelaTroca(Button moveBtn){		
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.TROCA);
	}

	public void voltaAoJogo() {
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.JOGO);
		GerenciadorDeTelas.getInstancia().atualizaBarraInformacoes(jogo);
	}

	public int[] getNumeroCartasJogador() {
		Jogador jogador = ControladorJogo.getInstancia().jogo.getJogadorDaVez();
		int tiro = 0;
		int porrada = 0;
		int bomba = 0;
		int valesca = 0;
		for(Carta carta : jogador.getMao()){
			switch(carta.getTipo()){
			case TIRO:
				tiro++;
				break;
			case PORRADA:
				porrada++;
				break;
			case BOMBA:
				bomba++;
				break;
			case VALESCA:
				valesca++;
				break;
			}
		}
		
		int [] resp = new int [4];
		resp[0] = tiro;
		resp[1] = porrada;
		resp[2] = bomba;
		resp[3] = valesca;
		
		return resp;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}