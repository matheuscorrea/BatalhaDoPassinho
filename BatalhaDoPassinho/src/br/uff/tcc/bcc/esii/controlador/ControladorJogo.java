package br.uff.tcc.bcc.esii.controlador;

import java.io.File;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import javax.swing.JOptionPane;

import br.uff.tcc.bcc.esii.modelo.Carta;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Jogo;
import br.uff.tcc.bcc.esii.modelo.Jogo.TipoFase;
import br.uff.tcc.bcc.esii.modelo.Mapa;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.esii.modelo.ia.JogadorIA;
import br.uff.tcc.bcc.esii.save.Save;
import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas.TipoDaTela;

public class ControladorJogo {

	private static ControladorJogo controlador;

	private Jogo jogo;

	private ControladorJogo() {
		jogo = new Jogo();
	}

	public static ControladorJogo getInstancia() {
		if (controlador == null) {
			controlador = new ControladorJogo();
		}
		return controlador;
	}
	//Atributos criados para evento territ�rio
	//TODO Verificar com cuidado se h� necessidade de tantos atributos do mesmo tipo (reutilizar boolean's por exemplo) 
	private boolean selecionouTerritorioProprio = false;
	private boolean selecionouTerritorioInimigo = false;
	private boolean selecionouTerritorioFonte = false;
	private boolean selecionouTerritorioDestino = false;
	public boolean dominouTerrritorio = false;
	public Territorio territorioAtacante;
	public Territorio territorioDefensor;
	private Button btAtacante;
	private Button btDefensor;
	private Button btFonte;
	private Button btDestino;
	public Territorio territorioFonte;
	public Territorio territorioDestino;
	private List<String> jaMovidos = new LinkedList<String>();
	private int [] controleDeCartas;
	private int cartasTiroSelecionadas = 0;
	private int cartasPorradaSelecionadas = 0;
	private int cartasBombaSelecionadas = 0;
	private int cartasValescaSelecionadas = 0;
	private int totalCartasSelecionadas = cartasTiroSelecionadas + cartasPorradaSelecionadas + cartasBombaSelecionadas + cartasValescaSelecionadas;

	// Atributos criados para evento territ�rio

	/**M�todo respons�vel pelas a��es relacionadas ao clique de um bot�o de territ�rio
	 * @param botao Bot�o do territ�rio clicado
	 */
	public void acaoTerritorio(Button botao) {

		Jogador jogador = jogo.getJogadorDaVez();

		switch (jogo.faseAtual) {
		case FASE_1:
			if (distribuiTropa(jogador, botao.getId())) {
				// Atualiza a vis�o
				botao.setText(jogo.getTropas(botao.getId()) + "");
				//TODO testando
				GerenciadorDeTelas.getInstancia()
				.atualizaBarraInformacoes(jogo);
				
			}
			break;
		case FASE_2:
			if (!selecionouTerritorioProprio) {
				if (jogador.possuiTerritorio(botao.getId())
						&& jogador.getTerritorioConquistado(botao.getId())
								.getQuantidadeTropa() > 1) {
					selecionouTerritorioProprio = true;
					territorioAtacante = jogador.getTerritorioConquistado(botao
							.getId());
					
					btAtacante = botao;
					GerenciadorDeTelas.getInstancia().atualizaImageBotao(btAtacante.getId(), 
							FabricaDeBotoes.criaImagemDoBotaoTerritorioComHighLight(territorioAtacante));
					
				}
			//J� selecionou 1 
			} else if (!selecionouTerritorioInimigo) {
				//clicou no territorio inimigo
				if (!jogador.possuiTerritorio(botao.getId())) {
					if (territorioAtacante.getVizinhos().containsKey(
							botao.getId())) {
						btDefensor = botao;
						selecionouTerritorioInimigo = true;
						territorioDefensor = territorioAtacante.getVizinhos()
								.get(botao.getId());
						GerenciadorDeTelas.getInstancia().atualizaImageBotao(btDefensor.getId(), 
								FabricaDeBotoes.criaImagemDoBotaoTerritorioComHighLight(territorioDefensor));
						
					}else{
						//btAtacante.setDisable(false);
						selecionouTerritorioProprio = false;
						GerenciadorDeTelas.getInstancia().atualizaImageBotao(btAtacante.getId(), 
								FabricaDeBotoes.criaImagemDoBotaoTerritorio(territorioAtacante));
						
					}
				//clicou no proprio territorio
				}else{
					//btAtacante.setDisable(false);
					GerenciadorDeTelas.getInstancia().atualizaImageBotao(btAtacante.getId(), 
							FabricaDeBotoes.criaImagemDoBotaoTerritorio(territorioAtacante));
					//se poder atacar
					if(jogador.getTerritorioConquistado(botao.getId())
								.getQuantidadeTropa() > 1){
						//botao.setDisable(true);
						territorioAtacante = jogador.getTerritorioConquistado(botao
								.getId());
						btAtacante = botao;
						GerenciadorDeTelas.getInstancia().atualizaImageBotao(btAtacante.getId(), 
								FabricaDeBotoes.criaImagemDoBotaoTerritorioComHighLight(territorioAtacante));
						
					}else{
						selecionouTerritorioProprio=false;
					}
				}
			//Ja selecionou os dois
			}else{
				//Se poder atacar
				if (jogador.possuiTerritorio(botao.getId())
						&& jogador.getTerritorioConquistado(botao.getId())
								.getQuantidadeTropa() > 1) {
					//btAtacante.setDisable(false);
					//btDefensor.setDisable(false);
					GerenciadorDeTelas.getInstancia().atualizaImageBotao(btAtacante.getId(), 
							FabricaDeBotoes.criaImagemDoBotaoTerritorio(territorioAtacante));
					GerenciadorDeTelas.getInstancia().atualizaImageBotao(btDefensor.getId(), 
							FabricaDeBotoes.criaImagemDoBotaoTerritorio(territorioDefensor));
					
					
					selecionouTerritorioInimigo = false;
					
					//botao.setDisable(true);
					selecionouTerritorioProprio = true;
					territorioAtacante = jogador.getTerritorioConquistado(botao
							.getId());
					btAtacante = botao;
					GerenciadorDeTelas.getInstancia().atualizaImageBotao(btAtacante.getId(), 
							FabricaDeBotoes.criaImagemDoBotaoTerritorioComHighLight(territorioAtacante));
					
					
				}else{
//					btAtacante.setDisable(false);
//					btDefensor.setDisable(false);
					selecionouTerritorioProprio = false;
					selecionouTerritorioInimigo = false;
					GerenciadorDeTelas.getInstancia().atualizaImageBotao(btAtacante.getId(), 
							FabricaDeBotoes.criaImagemDoBotaoTerritorio(territorioAtacante));
					GerenciadorDeTelas.getInstancia().atualizaImageBotao(btDefensor.getId(), 
							FabricaDeBotoes.criaImagemDoBotaoTerritorio(territorioDefensor));
				
				}
			}
			break;
		case FASE_3:
			if (selecionouTerritorioDestino) {
				selecionouTerritorioFonte = false;
				selecionouTerritorioDestino = false;
				
				GerenciadorDeTelas.getInstancia().atualizaImageBotao(btDestino.getId(), 
						FabricaDeBotoes.criaImagemDoBotaoTerritorio(territorioDestino));
				GerenciadorDeTelas.getInstancia().atualizaImageBotao(btFonte.getId(), 
						FabricaDeBotoes.criaImagemDoBotaoTerritorio(territorioFonte));
				
				//btDestino.setDisable(false);
				//btFonte.setDisable(false);
				if (jogador.possuiTerritorio(botao.getId())) {
					if ((!jaMovidos.contains(botao.getId()))&&(jogador
							.getTerritorioConquistado(botao.getId()).getQuantidadeTropa()>1)) {
						selecionouTerritorioFonte = true;
						territorioFonte = jogador
								.getTerritorioConquistado(botao.getId());
						btFonte = botao;
						//btFonte.setDisable(true);
						GerenciadorDeTelas.getInstancia().atualizaImageBotao(btFonte.getId(), 
								FabricaDeBotoes.criaImagemDoBotaoTerritorioComHighLight(territorioFonte));
						
						GerenciadorDeTelas.getInstancia()
								.atualizaBarraInformacoes(jogo);
					}
				}
			} else if (!selecionouTerritorioFonte) {
				if (jogador.possuiTerritorio(botao.getId())) {
					if ((!jaMovidos.contains(botao.getId()))&&(jogador
							.getTerritorioConquistado(botao.getId()).getQuantidadeTropa()>1)) {
						
						selecionouTerritorioFonte = true;
						territorioFonte = jogador
								.getTerritorioConquistado(botao.getId());
						btFonte = botao;
						//btFonte.setDisable(true);
						GerenciadorDeTelas.getInstancia().atualizaImageBotao(btFonte.getId(), 
								FabricaDeBotoes.criaImagemDoBotaoTerritorioComHighLight(territorioFonte));
						
						GerenciadorDeTelas.getInstancia()
								.atualizaBarraInformacoes(jogo);
					}
				}
			} else if (selecionouTerritorioFonte) {
				if (jogador.possuiTerritorio(botao.getId())) {
					if (territorioFonte.getVizinhos().containsKey(botao.getId())) {
						selecionouTerritorioDestino = true;
						territorioDestino = jogador
								.getTerritorioConquistado(botao.getId());
						btDestino = botao;
						//btDestino.setDisable(true);
						GerenciadorDeTelas.getInstancia().atualizaImageBotao(btDestino.getId(), 
								FabricaDeBotoes.criaImagemDoBotaoTerritorioComHighLight(territorioDestino));
						
						GerenciadorDeTelas.getInstancia()
								.atualizaBarraInformacoes(jogo);
					}else{
						
						//btFonte.setDisable(false);
						GerenciadorDeTelas.getInstancia().atualizaImageBotao(btFonte.getId(), 
								FabricaDeBotoes.criaImagemDoBotaoTerritorio(territorioFonte));
						
						if ((!jaMovidos.contains(botao.getId()))&&
								(jogador.getTerritorioConquistado(botao.getId()).getQuantidadeTropa()>1)) {
							territorioFonte = jogador
									.getTerritorioConquistado(botao.getId());
							btFonte = botao;
							//btFonte.setDisable(true);
							GerenciadorDeTelas.getInstancia().atualizaImageBotao(btFonte.getId(), 
									FabricaDeBotoes.criaImagemDoBotaoTerritorioComHighLight(territorioFonte));
							
							GerenciadorDeTelas.getInstancia()
									.atualizaBarraInformacoes(jogo);
						}					
					}
				}else{
					//btFonte.setDisable(false);
					GerenciadorDeTelas.getInstancia().atualizaImageBotao(btFonte.getId(), 
							FabricaDeBotoes.criaImagemDoBotaoTerritorio(territorioFonte));
					
					selecionouTerritorioFonte = false;
					GerenciadorDeTelas.getInstancia()
								.atualizaBarraInformacoes(jogo);
				}
			}
			break;
		}
	}
	
	/**M�todo respons�vel pela distribui��o de tropas (Fase 1) 
	 * @param jogador Jogador que est� distribuindo as tropas
	 * @param nomeTerritorio Territ�rio para o qual o jogador est� tentando distribuir suas tropas
	 * @return True se o jogador possuir o terri�rio para o qual quer distribuir suas tropas, false caso contr�rio.
	 */
	public boolean distribuiTropa(Jogador jogador, String nomeTerritorio) {
		if (jogador.possuiTerritorio(nomeTerritorio)) {
			if (jogo.temTropas()) {
				jogo.distribuirTropas(nomeTerritorio, 1);
				jogo.decrementaTropas();
				return true;
			}
		}
		return false;
	}

	/** M�todo respons�vel por verificar se um dado jogador j� acabou de distribuir suas tropas na Fase 1
	 * @return True se o jogador da vez tem tropas a distribuir, false caso contr�rio
	 */
	public boolean FaseUmTemTropasParaDistribuir() {
		return jogo.temTropas();
	}

//	public boolean DominouTerritorio(Territorio territorioAtacante,
//			Territorio territorioDefensor) {
//		if (jogo.ataque(territorioAtacante, territorioDefensor)) {
//
//			if (territorioAtacante
//					.getDono()
//					.getObjetivo()
//					.concluido(territorioAtacante.getDono(),
//							territorioDefensor.getDono())) {
//				
//				jogo.ganharJogo(territorioAtacante.getDono());
//				GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.FIM_JOGO);
//				
//			}
//			// Jogador acabou de perder seu �ltimo territ�rio
//			if (territorioDefensor.getDono().numeroDeConquistados() == 1) {
//				jogo.eliminaJogador(territorioAtacante.getDono(),
//						territorioDefensor.getDono());
//			}
//
//			// TODO Rever com cuidado
//			// TODO Pegar da vis�o quantas tropas passar para o territorio
//			// dominado
//			jogo.dominarTerritorio(territorioAtacante, territorioDefensor, 1);
//			return true;
//		}
//		return false;
//	}

	/**M�todo respons�vel por mover tropas de um territ�rio para outro territ�rio dominados por um mesmo jogador
	 * @param territorioFonte Territ�rio do qual as tropas se originam
	 * @param territorioDestino Territ�rio para onde se destinam as tropas
	 */
	public void mover(Territorio territorioFonte, Territorio territorioDestino) {
		if (territorioFonte.getQuantidadeTropa() > 1) {
			jogo.redistribuiTropa(territorioFonte, territorioDestino, 1);
			btFonte.setText(territorioFonte.getQuantidadeTropa() + "");
			btDestino.setText(territorioDestino.getQuantidadeTropa() + "");
		}
		jaMovidos.add(territorioDestino.getNome());
	}

	/**M�todo respons�vel por passar as fases do jogo
	 * 
	 */
	public void proximaFase() {

		switch (jogo.faseAtual) {
		case FASE_1:
			
			break;
		case FASE_2:

			if (selecionouTerritorioInimigo) {
				selecionouTerritorioInimigo = false;
				//btDefensor.setDisable(false);
				GerenciadorDeTelas.getInstancia().atualizaImageBotao(btDefensor.getId(), 
						FabricaDeBotoes.criaImagemDoBotaoTerritorio(territorioDefensor));
				
			}
			if (selecionouTerritorioProprio) {
				selecionouTerritorioProprio = false;
				//btAtacante.setDisable(false);
				GerenciadorDeTelas.getInstancia().atualizaImageBotao(btAtacante.getId(), 
						FabricaDeBotoes.criaImagemDoBotaoTerritorio(territorioAtacante));
				
			}
			break;
		case FASE_3:

			if (selecionouTerritorioFonte) {
				selecionouTerritorioFonte = false;
				//btFonte.setDisable(false);
				GerenciadorDeTelas.getInstancia().atualizaImageBotao(btFonte.getId(), 
						FabricaDeBotoes.criaImagemDoBotaoTerritorio(territorioFonte));
				
			}
			if (selecionouTerritorioDestino) {
				selecionouTerritorioDestino = false;
				//btDestino.setDisable(false);
				GerenciadorDeTelas.getInstancia().atualizaImageBotao(btDestino.getId(), 
						FabricaDeBotoes.criaImagemDoBotaoTerritorio(territorioDestino));
				
			}
			
			jaMovidos.clear();
			
			if (dominouTerrritorio) {
				jogo.getJogadorDaVez().ganhaCarta();
				if (jogo.getJogadorDaVez().getMao().size() > 5) {
					do {
						jogo.getJogadorDaVez().descartar();
					} while (jogo.getJogadorDaVez().getMao().size() > 5);
				}
			}
			dominouTerrritorio = false;
			break;
		}
		jogo.proximaFase();
		if (jogo.getJogadorDaVez() instanceof JogadorIA) {
			JogadorIA jogadorIA = (JogadorIA) jogo.getJogadorDaVez();
			switch (jogo.faseAtual) {
			case FASE_1:				
//				jogadorIA.fase1();
				break;
			case FASE_2:
				jogadorIA.fase2();
				break;
			case FASE_3:
				jogadorIA.fase3();
				break;
			}
		}
		if(!acabouJogo()){
			GerenciadorDeTelas.getInstancia().atualizaBarraInformacoes(jogo);
		}
	}

	/**M�todo respons�vel pelo in�cio da Partida
	 * 
	 */
	public void iniciaPartida() {
		List<Jogador> listaJogadores = ControladorTelaEscolha.getInstancia()
				.getListaJogadores();
		Collections.shuffle(listaJogadores);

		for (Jogador jogador : listaJogadores) {
			jogo.adicionaJogador(jogador);
		}
		jogo.distribuiObjetivos(listaJogadores);
		jogo.distribuiTerritorio();
		jogo.faseAtual = TipoFase.FASE_1;
		jogo.calculaTropa(jogo.getJogadorDaVez());
				
		// Chama gerenciador de tarefas para trocar tela
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.JOGO);
		GerenciadorDeTelas.getInstancia().atualizaBarraInformacoes(jogo);
		if (jogo.getJogadorDaVez() instanceof JogadorIA) {
			((JogadorIA) jogo.getJogadorDaVez()).fase1();
		}
	}

	public Mapa getMapa() {
		return jogo.getMapa();
	}

	public List<Jogador> getJogadores() {
		return jogo.getJogadores();
	}
	
	/**M�todo respons�vel pelas a��es relacionadas ao clique do bot�o Ataque
	 * 
	 */
	public void acaoAtaque() {
		try{
			if (selecionouTerritorioInimigo && selecionouTerritorioProprio) {
	//			dominouTerrritorio = jogo.ataque(territorioAtacante,
	//					territorioDefensor);
				btAtacante.setText("" + territorioAtacante.getQuantidadeTropa());
				btDefensor.setText("" + territorioDefensor.getQuantidadeTropa());
				selecionouTerritorioInimigo = false;
				selecionouTerritorioProprio = false;
				btAtacante.setDisable(false);
				btDefensor.setDisable(false);
				GerenciadorDeTelas.getInstancia().atualizaImageBotao(btAtacante.getId(), 
						FabricaDeBotoes.criaImagemDoBotaoTerritorio(territorioAtacante));
			
				GerenciadorDeTelas.getInstancia().atualizaImageBotao(btDefensor.getId(), 
						FabricaDeBotoes.criaImagemDoBotaoTerritorio(territorioDefensor));
			
				GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.ATAQUE);
	//			if (dominouTerrritorio) {
	//
	//				if (territorioAtacante
	//						.getDono()
	//						.getObjetivo()
	//						.concluido(territorioAtacante.getDono(),
	//								territorioDefensor.getDono())) {
	//					if(!(jogo.getJogadorDaVez() instanceof JogadorIA)){
	//							fimDeJogo();
	//					}
	//				}
	//				// Jogador acabou de perder seu �ltimo territ�rio
	//				if (territorioDefensor.getDono().numeroDeConquistados() == 1) {
	//					jogo.eliminaJogador(territorioAtacante.getDono(),
	//							territorioDefensor.getDono());
	//				}
	//
	//				// TODO Rever com cuidado
	//				// TODO Pegar da vis�o quantas tropas passar para o territorio
	//				// dominado
	//				jogo.dominarTerritorio(territorioAtacante, territorioDefensor,
	//						1);
	//				btAtacante
	//						.setText(territorioAtacante.getQuantidadeTropa() + "");
	//				btDefensor
	//						.setText(territorioDefensor.getQuantidadeTropa() + "");
	//				
	//				GerenciadorDeTelas.getInstancia()
	//					.atualizaImageBotao(territorioDefensor.getNome(), FabricaDeBotoes
	//							.criaImageView(territorioDefensor));
	//			}
			}
			}catch(Exception e){
				new Save().save();
			}
			
	}

	/**M�todo respons�vel pela exibi��o da tela de fim de jogo
	 * 
	 */
	public void fimDeJogo() {
		jogo.ganharJogo(territorioAtacante.getDono());
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.FIM_JOGO);
	}

	/**M�todo respons�vel pelas a��es relacionadas ao clique do bot�o Mover
	 * 
	 */
	public void acaoMover(){
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

	/**M�todo respons�vel por tornar um jogador que tenha conquistado um dado territ�rio o dono do mesmo 
	 * 
	 */
	public void dominarTerritorio(int tropasAPassar){ 
		jogo.dominarTerritorio(territorioAtacante, territorioDefensor,tropasAPassar);
		btAtacante.setText(territorioAtacante.getQuantidadeTropa() + "");
		btDefensor.setText(territorioDefensor.getQuantidadeTropa() + "");

	}
	
	public Jogador getJogadorDaVez()
	{
		return jogo.getJogadorDaVez();
	}
	
	
	/**M�todo respons�vel pela elimina��o de um dado jogador
	 * 
	 */
	public void eliminaJogador(){
		jogo.eliminaJogador(territorioAtacante.getDono(),territorioDefensor.getDono());
		while(territorioAtacante.getDono().getMao().size()>=5){
			territorioAtacante.getDono().descartar();
		}
	}
	
	/**M�todo respons�vel pelas a��es relacionadas ao clique do bot�o Trocar na tela de troca
	 * @param Btn ?????
	 */
	public void acaoTelaTroca(Button Btn){		
		controleDeCartas = getNumeroCartasJogador();
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.TROCA);

	}

	/**M�todo respons�vel pelas a��es relacionadas ao clique do bot�o Objetivo
	 * 
	 */
	public void acaoTelaObjetivo() {
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.OBJETIVO);
	}

	/**M�todo respons�vel por voltar ao jogo a partir da tela de troca
	 * 
	 */
	public void voltaAoJogo() {
		cartasTiroSelecionadas = 0;
		cartasPorradaSelecionadas = 0;
		cartasBombaSelecionadas = 0;
		cartasValescaSelecionadas = 0;
		controleDeCartas = new int [4];
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.JOGO);
		GerenciadorDeTelas.getInstancia().atualizaBarraInformacoes(jogo);
	}

	public int[] getNumeroCartasJogador() {
		Jogador jogador = ControladorJogo.getInstancia().jogo.getJogadorDaVez();
		int tiro = 0;
		int porrada = 0;
		int bomba = 0;
		int valesca = 0;
		for (Carta carta : jogador.getMao()) {
			switch (carta.getTipo()) {
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

		int[] resp = new int[4];
		resp[0] = tiro;
		resp[1] = porrada;
		resp[2] = bomba;
		resp[3] = valesca;

		return resp;
	}

	/**M�todo respons�vel pela sele��o da carta tiro na tela de troca
	 * @param moveBtn ????
	 */
	public void selecionouCartaTiro() {
		 
		if(controleDeCartas[0] > 0 && totalCartasSelecionadas <= 3){
			cartasTiroSelecionadas++;
			System.out.println("selecionou tiro");
			GerenciadorDeTelas.getInstancia().atualizaBarraTroca();
			controleDeCartas[0]--;
		}
	}

	/**M�todo respons�vel pela sele��o da carta porrada na tela de troca
	 * @param moveBtn ????
	 */
	public void selecionouCartaPorrada(Button moveBtn) {
		if(controleDeCartas[1] > 0 && totalCartasSelecionadas <= 3){ 
			cartasPorradaSelecionadas++;
			System.out.println("selecionou porrada");
			GerenciadorDeTelas.getInstancia().atualizaBarraTroca();
			controleDeCartas[1]--;
		}
	}

	/**M�todo respons�vel pela sele��o da carta bomba na tela de troca
	 * @param moveBtn
	 */
	public void selecionouCartaBomba(Button moveBtn) {
		if(controleDeCartas[2] > 0 && totalCartasSelecionadas <= 3){ 
			cartasBombaSelecionadas++;
			System.out.println("selecionou bomba");
			GerenciadorDeTelas.getInstancia().atualizaBarraTroca();
			controleDeCartas[2]--;
		}
	}

	/**M�todo respons�vel pela sele��o da carta Valesca na tela de troca
	 * @param moveBtn
	 */
	public void selecionouCartaValesca(Button moveBtn) {
		if(controleDeCartas[3] > 0 && totalCartasSelecionadas <= 3){
			cartasValescaSelecionadas++;
			System.out.println("selecionou valesca");
			GerenciadorDeTelas.getInstancia().atualizaBarraTroca();
			controleDeCartas[3]--;
		}
	}

	public int[] getCartasSelecionadas() {
		System.out.println("mandou as cartas selecionadas");
		int[] resp = new int[4];
		resp[0] = cartasTiroSelecionadas;
		resp[1] = cartasPorradaSelecionadas;
		resp[2] = cartasBombaSelecionadas;
		resp[3] = cartasValescaSelecionadas;

		return resp;
	}

	/**M�todo respons�vel pelas a��es relacionadas ao clique do bot�o Realiza Troca
	 * @param moveBtn
	 */
	public void acaoRealizaTroca(Button moveBtn) {
		int [] selecionadas = getCartasSelecionadas();
		int [] aux = selecionadas;
		
		boolean valido = checaValidade(selecionadas);
		
		if(valido){
			for(int i = aux[0]; i>0;i--){
				jogo.getJogadorDaVez().removeCartaTiro();
			}
			for(int i = aux[1]; i>0;i--){
				jogo.getJogadorDaVez().removeCartaPorrada();
			}
			for(int i = aux[2]; i>0;i--){
				jogo.getJogadorDaVez().removeCartaBomba();
			}
			for(int i = aux[0]; i>0;i--){
				jogo.getJogadorDaVez().removeCartaValesca();
			}
			
			jogo.adicionaTropasTroca();
			cartasTiroSelecionadas = 0;
			cartasPorradaSelecionadas = 0;
			cartasBombaSelecionadas  = 0;
			cartasValescaSelecionadas = 0;
			GerenciadorDeTelas.getInstancia().atualizaBarraTroca();		
			
		}else{	
			
		cartasTiroSelecionadas = 0;
		cartasPorradaSelecionadas = 0;
		cartasBombaSelecionadas = 0;
		cartasValescaSelecionadas = 0;
		GerenciadorDeTelas.getInstancia().atualizaBarraTroca();

		}		
	}
	
	/**M�todo respons�vel por verificar se o conjunto de cartas selecionadas configura uma troca v�lida
	 * @param selecionadas Vetor das cartas selecionadas
	 * @return True se a troca for v�lida, false caso contr�rio
	 */
	private boolean checaValidade(int[] selecionadas) {
		if(selecionadas[3]==0){
			
			if(selecionadas[0]==3 || selecionadas[1]==3 || selecionadas[2]==3){
				return true;
			}else if(selecionadas[0]==1 && selecionadas[1]==1 && selecionadas[2]==1){
				return true;
			}else return false;
		
		}else if(selecionadas[3]==1){
			
			if(selecionadas[0]+selecionadas[1]+selecionadas[2] == 2){
				return true;
			}else return false;
			
		}else if(selecionadas[3]==2){
			
			if(selecionadas[0]+selecionadas[1]+selecionadas[2] == 1){
				return true;
			}else return false;
			
		}else return true;
		
		
	}

	/**Retorna o objetivo do jogador da vez
	 * @return A string com o nome do objetivo do jogador da vez
	 */
	public String objetivoDoJogadorAtual(){
		Jogador jogador = jogo.getJogadorDaVez();
		return jogador.getObjetivo().getNomeObjetivo();
	}

	/**M�todo respons�vel pelas a��es relacionadas ao clique do bot�o Sair
	 * @param moveBtn
	 */
	public void acaoSair(Button moveBtn) {
		GerenciadorDeTelas.getInstancia().sair();

	}

	public List<Button> getListaDeBotoesTerritorios() {
		return GerenciadorDeTelas.getInstancia().getListaDeBotoesTerritorios();
	}
	
	/**M�todo que verifica se j� foram movidas tropas para um dado territ�rio
	 * @param nomeTerritorio Nome do territ�rio 
	 * @return true caso j� tenha sido movida alguma tropas para o territ�rio de nome nomeTerrit�rio, false caso contr�rio
	 */
	public boolean jaMoveuTerritorio(String nomeTerritorio){
		return jaMovidos.contains(nomeTerritorio);
	}
	
	/**M�todo respons�vel por resetar os bot�es durante a fase 3
	 * 
	 */
	public void limpaBotoesFase3(){
		if (selecionouTerritorioFonte) {
			selecionouTerritorioFonte = false;
			//btFonte.setDisable(false);
			GerenciadorDeTelas.getInstancia().atualizaImageBotao(btFonte.getId(), 
					FabricaDeBotoes.criaImagemDoBotaoTerritorio(territorioFonte));
			
			
		}
		if (selecionouTerritorioDestino) {
			selecionouTerritorioDestino = false;
			//btDestino.setDisable(false);
			GerenciadorDeTelas.getInstancia().atualizaImageBotao(btDestino.getId(), 
					FabricaDeBotoes.criaImagemDoBotaoTerritorio(territorioDestino));
			
		}
	}

	public void AdicionaJogador(Jogador jogador){
		jogo.adicionaJogador(jogador);
	}
	
	public void acaoSalvar() {
		Save save = new Save();
		save.save();
	}
	
	public void acaoCarregar() {
		Save save = new Save();
		save.carregaJogo();
		atualizaTela();
	}

	/**M�todo respons�vel por resetar os atributos de controlador jogo
	 * 
	 */
	public void recomecaAtributos() {
		this.jogo = new Jogo();
		selecionouTerritorioProprio = false;
		selecionouTerritorioInimigo = false;
		selecionouTerritorioFonte = false;
		selecionouTerritorioDestino = false;
		dominouTerrritorio = false;
		jaMovidos = new LinkedList<String>();
		controleDeCartas = new int [4];
		cartasTiroSelecionadas = 0;
		cartasPorradaSelecionadas = 0;
		cartasBombaSelecionadas = 0;
		cartasValescaSelecionadas = 0;
		totalCartasSelecionadas = cartasTiroSelecionadas + cartasPorradaSelecionadas + cartasBombaSelecionadas + cartasValescaSelecionadas;

	}
	
	public Jogador jogadorVencedor(){
		return jogo.getJogadorVencedor();
	}
	
	public boolean acabouJogo(){
		return jogo.jogoAcabou();
	}

	public void ganharJogo() {
		jogo.ganharJogo(territorioAtacante.getDono());
	}
	
	public void atualizaTela(){
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.JOGO);
		GerenciadorDeTelas.getInstancia().atualizaBarraInformacoes(jogo);
		
	}

	public TipoFase getFase() {
		return jogo.faseAtual;
	}

	public void setFase(TipoFase fase) {
		jogo.faseAtual = fase;
	}

	/** M�todo respons�vel por descobrir quantas tropas um dado jogador ainda tem a distribuir
	 * @param jogador
	 * @return Se o jogador passado for a da vez retorna a quantidade de tropas dele sen�o retorna 0
	 */
	public int getTropasADitribuir(Jogador jogador) {
		if(jogador.getNome().equals(getJogadorDaVez().getNome()))
			return jogo.getQuantidadeDeTropas();
		return 0;
	}

	public void setQuantidadeDeTropas(int tropas) {
		jogo.setQuantidadeDeTropas(tropas);
	}

	public int getJogada() {
		return jogo.getJogada();
	}

	public void setJogada(int jogada) {
		jogo.setJogada(jogada);
	}

	public File salvarArquivo() {
		String myDocuments = ""; 
		FileChooser fileChooser = new FileChooser();
		try {
		     //Pega caminho para Meus Documentos
			 Process p =  Runtime.getRuntime().exec("reg query \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\" /v personal");
		     p.waitFor();

		     InputStream in = p.getInputStream();
		     byte[] b = new byte[in.available()];
		     in.read(b);
		     in.close();

		     myDocuments = new String(b);
		     myDocuments = myDocuments.split("\\s\\s+")[4];
		     //Adiciona diretorio para saves do jogo
		     File diretorio = new File(myDocuments+"\\BatalhaDoPassinho");
		     //Caso n�o exista ele cria e seta como default
		     diretorio.mkdirs();
			 fileChooser.setInitialDirectory(diretorio); 

			 //Seleciona o filtro para a exten��o
			 FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			 fileChooser.getExtensionFilters().add(extFilter);
			 //Mostra o painel de Salvar que retorna o path aonde o arquivo vai ser salvo
			 return fileChooser.showSaveDialog(GerenciadorDeTelas.getInstancia().getStagePrincipal());		
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao salvar Arquivo");
			return fileChooser.getInitialDirectory();
		}
     
	}

	public void jogadaIA() {
		if (jogo.getJogadorDaVez() instanceof JogadorIA) {
			JogadorIA jogadorIA = (JogadorIA) jogo.getJogadorDaVez();
			jogadorIA.acaoFase1();
		}
	}
}
