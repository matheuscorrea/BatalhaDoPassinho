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
import br.uff.tcc.bcc.esii.modelo.ia.JogadorIA;
import br.uff.tcc.bcc.esii.save.Save;
import br.uff.tcc.bcc.esii.som.Som;
import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas.TipoDaTela;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTerritorio;

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
	//Atributos criados para evento território
	//TODO Verificar com cuidado se há necessidade de tantos atributos do mesmo tipo (reutilizar boolean's por exemplo) 
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
	private int tropasMovendo;
	private int [] controleDeCartas;
	private int cartasTiroSelecionadas = 0;
	private int cartasPorradaSelecionadas = 0;
	private int cartasBombaSelecionadas = 0;
	private int cartasValescaSelecionadas = 0;
	private int totalCartasSelecionadas = cartasTiroSelecionadas + cartasPorradaSelecionadas + cartasBombaSelecionadas + cartasValescaSelecionadas;

	// Atributos criados para evento território

	public void acaoTerritorio(Button botao) {

		Jogador jogador = jogo.getJogadorDaVez();

		switch (jogo.faseAtual) {
		case FASE_1:
			if (distribuiTropa(jogador, botao.getId())) {
				// Atualiza a visão
				botao.setText(jogo.getTropas(botao.getId()) + "");

				GerenciadorDeTelas.getInstancia()
				.atualizaBarraInformacoes(jogo);
			}
			break;
		case FASE_2:
			if (!selecionouTerritorioProprio) {
				if (jogador.possuiTerritorio(botao.getId())
						&& jogador.getTerritorioConquistado(botao.getId())
								.getQuantidadeTropa() > 1) {
					botao.setDisable(true);
					selecionouTerritorioProprio = true;
					territorioAtacante = jogador.getTerritorioConquistado(botao
							.getId());
					btAtacante = botao;
				}
			//Já selecionou 1 
			} else if (!selecionouTerritorioInimigo) {
				//clicou no territorio inimigo
				if (!jogador.possuiTerritorio(botao.getId())) {
					if (territorioAtacante.getVizinhos().containsKey(
							botao.getId())) {
						botao.setDisable(true);
						btDefensor = botao;
						selecionouTerritorioInimigo = true;
						territorioDefensor = territorioAtacante.getVizinhos()
								.get(botao.getId());
					}else{
						btAtacante.setDisable(false);
						selecionouTerritorioProprio = false;
					}
				//clicou no proprio territorio
				}else{
					btAtacante.setDisable(false);
					//se poder atacar
					if(jogador.getTerritorioConquistado(botao.getId())
								.getQuantidadeTropa() > 1){
						botao.setDisable(true);
						territorioAtacante = jogador.getTerritorioConquistado(botao
								.getId());
						btAtacante = botao;
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
					btAtacante.setDisable(false);
					btDefensor.setDisable(false);
					selecionouTerritorioInimigo = false;
					
					botao.setDisable(true);
					selecionouTerritorioProprio = true;
					territorioAtacante = jogador.getTerritorioConquistado(botao
							.getId());
					btAtacante = botao;
				}else{
					btAtacante.setDisable(false);
					btDefensor.setDisable(false);
					selecionouTerritorioProprio = false;
					selecionouTerritorioInimigo = false;
				}
			}
			break;
		case FASE_3:
			if (selecionouTerritorioDestino) {
				selecionouTerritorioFonte = false;
				selecionouTerritorioDestino = false;
				btDestino.setDisable(false);
				btFonte.setDisable(false);
				if (jogador.possuiTerritorio(botao.getId())) {
					if (!jaMovidos.contains(botao.getId())) {
						selecionouTerritorioFonte = true;
						territorioFonte = jogador
								.getTerritorioConquistado(botao.getId());
						btFonte = botao;
						btFonte.setDisable(true);
						GerenciadorDeTelas.getInstancia()
								.atualizaBarraInformacoes(jogo);
					}
				}
			} else if (!selecionouTerritorioFonte) {
				if (jogador.possuiTerritorio(botao.getId())) {
					if (!jaMovidos.contains(botao.getId())) {
						
						selecionouTerritorioFonte = true;
						territorioFonte = jogador
								.getTerritorioConquistado(botao.getId());
						btFonte = botao;
						btFonte.setDisable(true);
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
						btDestino.setDisable(true);
						GerenciadorDeTelas.getInstancia()
								.atualizaBarraInformacoes(jogo);
					}else{
						btFonte.setDisable(false);
						if (!jaMovidos.contains(botao.getId())) {
							territorioFonte = jogador
									.getTerritorioConquistado(botao.getId());
							btFonte = botao;
							btFonte.setDisable(true);
							GerenciadorDeTelas.getInstancia()
									.atualizaBarraInformacoes(jogo);
						}					
					}
				}else{
					btFonte.setDisable(false);
					selecionouTerritorioFonte = false;
					GerenciadorDeTelas.getInstancia()
								.atualizaBarraInformacoes(jogo);
				}
			}
			break;
		}
	}

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
//			// Jogador acabou de perder seu último território
//			if (territorioDefensor.getDono().numeroDeConquistados() == 1) {
//				jogo.eliminaJogador(territorioAtacante.getDono(),
//						territorioDefensor.getDono());
//			}
//
//			// TODO Rever com cuidado
//			// TODO Pegar da visão quantas tropas passar para o territorio
//			// dominado
//			jogo.dominarTerritorio(territorioAtacante, territorioDefensor, 1);
//			return true;
//		}
//		return false;
//	}

	public void mover(Territorio territorioFonte, Territorio territorioDestino) {
		if (territorioFonte.getQuantidadeTropa() > 1) {
			jogo.redistribuiTropa(territorioFonte, territorioDestino, 1);
			btFonte.setText(territorioFonte.getQuantidadeTropa() + "");
			btDestino.setText(territorioDestino.getQuantidadeTropa() + "");
		}
		jaMovidos.add(territorioDestino.getNome());
	}

	public void proximaFase() {

		switch (jogo.faseAtual) {
		case FASE_1:

			break;
		case FASE_2:

			if (selecionouTerritorioInimigo) {
				selecionouTerritorioInimigo = false;
				btDefensor.setDisable(false);
			}
			if (selecionouTerritorioProprio) {
				selecionouTerritorioProprio = false;
				btAtacante.setDisable(false);
			}
			break;
		case FASE_3:

			if (selecionouTerritorioFonte) {
				selecionouTerritorioFonte = false;
				btFonte.setDisable(false);
			}
			if (selecionouTerritorioDestino) {
				selecionouTerritorioDestino = false;
				btDestino.setDisable(false);
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
				jogadorIA.fase1();
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
	
	public void acaoAtaque() {
		if (selecionouTerritorioInimigo && selecionouTerritorioProprio) {
//			dominouTerrritorio = jogo.ataque(territorioAtacante,
//					territorioDefensor);
			btAtacante.setText("" + territorioAtacante.getQuantidadeTropa());
			btDefensor.setText("" + territorioDefensor.getQuantidadeTropa());
			selecionouTerritorioInimigo = false;
			selecionouTerritorioProprio = false;
			btAtacante.setDisable(false);
			btDefensor.setDisable(false);
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
//				// Jogador acabou de perder seu último território
//				if (territorioDefensor.getDono().numeroDeConquistados() == 1) {
//					jogo.eliminaJogador(territorioAtacante.getDono(),
//							territorioDefensor.getDono());
//				}
//
//				// TODO Rever com cuidado
//				// TODO Pegar da visão quantas tropas passar para o territorio
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
	}

	public void fimDeJogo() {
		jogo.ganharJogo(territorioAtacante.getDono());
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.FIM_JOGO);
	}

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

	public void dominarTerritorio(){ 
		jogo.dominarTerritorio(territorioAtacante, territorioDefensor,1);
		btAtacante.setText(territorioAtacante.getQuantidadeTropa() + "");
		btDefensor.setText(territorioDefensor.getQuantidadeTropa() + "");

	}
	
	public Jogador getJogadorDaVez()
	{
		return jogo.getJogadorDaVez();
	}
	
	public void eliminaJogador(){
		jogo.eliminaJogador(territorioAtacante.getDono(),territorioDefensor.getDono());
	}
	
	public void acaoTelaTroca(Button Btn){		
		controleDeCartas = getNumeroCartasJogador();
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.TROCA);

	}

	public void acaoTelaObjetivo() {
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.OBJETIVO);
	}

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

	public void selecionouCartaTiro(Button moveBtn) {
		 
		if(controleDeCartas[0] > 0 && totalCartasSelecionadas <= 3){
			cartasTiroSelecionadas++;
			System.out.println("selecionou tiro");
			GerenciadorDeTelas.getInstancia().atualizaBarraTroca();
			controleDeCartas[0]--;
		}
	}

	public void selecionouCartaPorrada(Button moveBtn) {
		if(controleDeCartas[1] > 0 && totalCartasSelecionadas <= 3){ 
			cartasPorradaSelecionadas++;
			System.out.println("selecionou porrada");
			GerenciadorDeTelas.getInstancia().atualizaBarraTroca();
			controleDeCartas[1]--;
		}
	}

	public void selecionouCartaBomba(Button moveBtn) {
		if(controleDeCartas[2] > 0 && totalCartasSelecionadas <= 3){ 
			cartasBombaSelecionadas++;
			System.out.println("selecionou bomba");
			GerenciadorDeTelas.getInstancia().atualizaBarraTroca();
			controleDeCartas[2]--;
		}
	}

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

	public String objetivoDoJogadorAtual(){
		Jogador jogador = jogo.getJogadorDaVez();
		return jogador.getObjetivo().getNomeObjetivo();
	}

	public void acaoSair(Button moveBtn) {
		GerenciadorDeTelas.getInstancia().sair();

	}

	public List<Button> getListaDeBotoesTerritorios() {
		return GerenciadorDeTelas.getInstancia().getListaDeBotoesTerritorios();
	}
	
	public boolean jaMoveuTerritorio(String nomeTerritorio){
		return jaMovidos.contains(nomeTerritorio);
	}
	
	public void limpaBotoesFase3(){
		if (selecionouTerritorioFonte) {
			selecionouTerritorioFonte = false;
			btFonte.setDisable(false);
		}
		if (selecionouTerritorioDestino) {
			selecionouTerritorioDestino = false;
			btDestino.setDisable(false);
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
	}

	public void recomecaAtributos() {
		this.jogo = new Jogo();
		selecionouTerritorioProprio = false;
		selecionouTerritorioInimigo = false;
		selecionouTerritorioFonte = false;
		selecionouTerritorioDestino = false;
		dominouTerrritorio = false;
		jaMovidos = new LinkedList<String>();
		tropasMovendo=0;
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
}