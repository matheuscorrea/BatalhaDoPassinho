package br.uff.tcc.bcc.esii.controlador;

import iA.JogadorIA;
import iA.ProcessaFaseIa;
import iA.TaskProcessaFaseIA;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.hamcrest.core.IsInstanceOf;

import javafx.scene.control.Button;
import br.uff.tcc.bcc.esii.modelo.Carta;
import br.uff.tcc.bcc.esii.modelo.Carta.Tipo;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Jogo;
import br.uff.tcc.bcc.esii.modelo.Jogo.TipoFase;
import br.uff.tcc.bcc.esii.modelo.Mapa;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.esii.visao.ConstanteDaCor;
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

	// Atributos criados para evento território
	// TODO Verificar com cuidado se há necessidade de tantos atributos do mesmo
	// tipo (reutilizar boolean's por exemplo)
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
	public Territorio territorioFonte;
	public Territorio territorioDestino;
	private List<String> jaMovidos = new LinkedList<String>();
	int tropasMovendo;
	int[] controleDeCartas;
	int cartasTiroSelecionadas = 0;
	int cartasPorradaSelecionadas = 0;
	int cartasBombaSelecionadas = 0;
	int cartasValescaSelecionadas = 0;

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
			} else if (!selecionouTerritorioInimigo) {
				if (!jogador.possuiTerritorio(botao.getId())) {
					if (territorioAtacante.getVizinhos().containsKey(
							botao.getId())) {
						botao.setDisable(true);
						btDefensor = botao;
						selecionouTerritorioInimigo = true;
						territorioDefensor = territorioAtacante.getVizinhos()
								.get(botao.getId());
					}
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

	public boolean DominouTerritorio(Territorio territorioAtacante,
			Territorio territorioDefensor) {
		if (jogo.ataque(territorioAtacante, territorioDefensor)) {

			if (territorioAtacante
					.getDono()
					.getObjetivo()
					.concluido(territorioAtacante.getDono(),
							territorioDefensor.getDono())) {
				// TODO Implementar lógica do método ganharJogo() e como o
				// controlador deve se comportar nesse caso
				jogo.ganharJogo(territorioAtacante.getDono());
			}
			// Jogador acabou de perder seu último território
			if (territorioDefensor.getDono().numeroDeConquistados() == 1) {
				jogo.eliminaJogador(territorioAtacante.getDono(),
						territorioDefensor.getDono());
			}

			// TODO Rever com cuidado
			// TODO Pegar da visão quantas tropas passar para o territorio
			// dominado
			jogo.dominarTerritorio(territorioAtacante, territorioDefensor, 1);
			return true;
		}
		return false;
	}

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
//				ProcessaFaseIa processa = new ProcessaFaseIa(jogadorIA, TipoFase.FASE_1);
//				processa.run();
				
//				TaskProcessaFaseIA task = new TaskProcessaFaseIA(jogadorIA, TipoFase.FASE_1);
//				Thread th = new Thread(task);
//		         th.setDaemon(true);
//		         th.start();

//				new Thread(task).start();
				
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
		GerenciadorDeTelas.getInstancia().atualizaBarraInformacoes(jogo);
	}

	public void iniciaPartida() {

		List<Jogador> listaJogadores = ControladorTelaEscolha.getInstancia()
				.getListaJogadores();
//		listaJogadores
//				.add(new JogadorIA("Mc PocaIAhontas", ConstanteDaCor.ROSA));
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

	public void acaoAtaque() {
		if (selecionouTerritorioInimigo && selecionouTerritorioProprio) {
			dominouTerrritorio = jogo.ataque(territorioAtacante,
					territorioDefensor);
			btAtacante.setText("" + territorioAtacante.getQuantidadeTropa());
			btDefensor.setText("" + territorioDefensor.getQuantidadeTropa());
			selecionouTerritorioInimigo = false;
			selecionouTerritorioProprio = false;
			btAtacante.setDisable(false);
			btDefensor.setDisable(false);
			if (dominouTerrritorio) {

				if (territorioAtacante
						.getDono()
						.getObjetivo()
						.concluido(territorioAtacante.getDono(),
								territorioDefensor.getDono())) {
					// TODO Implementar lógica do método ganharJogo() e como o
					// controlador deve se comportar nesse caso
					jogo.ganharJogo(territorioAtacante.getDono());
				}
				// Jogador acabou de perder seu último território
				if (territorioDefensor.getDono().numeroDeConquistados() == 1) {
					jogo.eliminaJogador(territorioAtacante.getDono(),
							territorioDefensor.getDono());
				}

				// TODO Rever com cuidado
				// TODO Pegar da visão quantas tropas passar para o territorio
				// dominado
				jogo.dominarTerritorio(territorioAtacante, territorioDefensor,
						1);
				btAtacante
						.setText(territorioAtacante.getQuantidadeTropa() + "");
				btDefensor
						.setText(territorioDefensor.getQuantidadeTropa() + "");
				btDefensor.setGraphic(FabricaDeBotoes
						.criaImageView(territorioDefensor));
			}
		}
	}

	public void acaoMover() {
		if (selecionouTerritorioFonte && selecionouTerritorioDestino) {
			// ver se fonte tem pelo menos 2 tropas
			mover(territorioFonte, territorioDestino);
		}
	}

	public void acaoTelaTroca(Button Btn) {
		controleDeCartas = getNumeroCartasJogador();
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.TROCA);

	}

	public void acaoTelaObjetivo() {
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.OBJETIVO);
	}

	public void voltaAoJogo() {
		int cartasTiroSelecionadas = 0;
		int cartasPorradaSelecionadas = 0;
		int cartasBombaSelecionadas = 0;
		int cartasValescaSelecionadas = 0;
		controleDeCartas = new int[4];
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
		if (controleDeCartas[0] > 0) {
			cartasTiroSelecionadas++;
			System.out.println("selecionou tiro");
			GerenciadorDeTelas.getInstancia().atualizaBarraTroca();
			controleDeCartas[0]--;
		}
	}

	public void selecionouCartaPorrada(Button moveBtn) {
		if (controleDeCartas[1] > 0) {
			cartasPorradaSelecionadas++;
			System.out.println("selecionou porrada");
			GerenciadorDeTelas.getInstancia().atualizaBarraTroca();
			controleDeCartas[1]--;
		}
	}

	public void selecionouCartaBomba(Button moveBtn) {
		if (controleDeCartas[2] > 0) {
			cartasBombaSelecionadas++;
			System.out.println("selecionou bomba");
			GerenciadorDeTelas.getInstancia().atualizaBarraTroca();
			controleDeCartas[2]--;
		}
	}

	public void selecionouCartaValesca(Button moveBtn) {
		if (controleDeCartas[3] > 0) {
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
		int[] selecionadas = getCartasSelecionadas();

		// if(selecionadas[3] == 0){
		//
		// if(selecionadas[0] == 3){
		// jogo.adicionaTropasTroca();
		// int counter = 3;
		// for(Carta carta : jogo.getJogadorDaVez().getMao()){
		// if(carta.getTipo().equals(Tipo.TIRO) && counter >0){
		// jogo.getJogadorDaVez().getMao().remove(carta);
		// counter--;
		// }
		// }
		// }
		//
		// else if(selecionadas[1] == 3){
		// jogo.adicionaTropasTroca();
		// int counter = 3;
		// for(Carta carta : jogo.getJogadorDaVez().getMao()){
		// if(carta.getTipo().equals(Tipo.TIRO) && counter >0){
		// jogo.getJogadorDaVez().getMao().remove(carta);
		// counter--;
		// }
		// }
		// }
		//
		// else if(selecionadas[2] == 3){
		// jogo.adicionaTropasTroca();
		// int counter = 3;
		// for(Carta carta : jogo.getJogadorDaVez().getMao()){
		// if(carta.getTipo().equals(Tipo.TIRO) && counter >0){
		// jogo.getJogadorDaVez().getMao().remove(carta);
		// counter--;
		// }
		// }
		// }
		//
		// else if(selecionadas[0] == 1 && selecionadas[1] == 1 &&
		// selecionadas[2] == 1){
		// jogo.adicionaTropasTroca();
		// int counterTiro = 1;
		// int counterPorrada = 1;
		// int counterBomba = 1;
		//
		// for(Carta carta : jogo.getJogadorDaVez().getMao()){
		// if(carta.getTipo().equals(Tipo.TIRO) && counterTiro >0){
		// jogo.getJogadorDaVez().getMao().remove(carta);
		// counterTiro--;
		// }
		// if(carta.getTipo().equals(Tipo.PORRADA) && counterPorrada >0){
		// jogo.getJogadorDaVez().getMao().remove(carta);
		// counterPorrada--;
		// }
		// if(carta.getTipo().equals(Tipo.BOMBA) && counterBomba >0){
		// jogo.getJogadorDaVez().getMao().remove(carta);
		// counterBomba--;
		// }
		// }
		// }
		//
		// }else if(selecionadas[3] == 1){
		//
		// if(selecionadas[0] == 2 ){
		// jogo.adicionaTropasTroca();
		// int counter = 2;
		// for(Carta carta : jogo.getJogadorDaVez().getMao()){
		// if(carta.getTipo().equals(Tipo.TIRO) && counter >0){
		// jogo.getJogadorDaVez().getMao().remove(carta);
		// counter--;
		// }
		// }
		// }
		//
		// else if(selecionadas[1] == 2){
		// jogo.adicionaTropasTroca();
		// int counter = 2;
		// for(Carta carta : jogo.getJogadorDaVez().getMao()){
		// if(carta.getTipo().equals(Tipo.PORRADA) && counter >0){
		// jogo.getJogadorDaVez().getMao().remove(carta);
		// counter--;
		// }
		// }
		// }
		//
		// else if(selecionadas[2] == 2){
		// jogo.adicionaTropasTroca();
		// int counter = 2;
		// for(Carta carta : jogo.getJogadorDaVez().getMao()){
		// if(carta.getTipo().equals(Tipo.BOMBA) && counter >0){
		// jogo.getJogadorDaVez().getMao().remove(carta);
		// counter--;
		// }
		// }
		// }
		// else if(selecionadas[0] == 1 && (selecionadas[1]==1 ||
		// selecionadas[2] ==1 )){
		// jogo.adicionaTropasTroca();
		// int counterTiro = 1;
		// for(Carta carta : jogo.getJogadorDaVez().getMao()){
		// if(carta.getTipo().equals(Tipo.TIRO) && counterTiro >0){
		// jogo.getJogadorDaVez().getMao().remove(carta);
		// counterTiro--;
		// }
		// }
		// if(selecionadas[1]==1){
		// int counterPorrada = 1;
		// for(Carta carta : jogo.getJogadorDaVez().getMao()){
		// if(carta.getTipo().equals(Tipo.TIRO) && counterPorrada >0){
		// jogo.getJogadorDaVez().getMao().remove(carta);
		// counterPorrada--;
		// }
		// }
		// }else{
		// int counterBomba = 1;
		// for(Carta carta : jogo.getJogadorDaVez().getMao()){
		// if(carta.getTipo().equals(Tipo.TIRO) && counterBomba >0){
		// jogo.getJogadorDaVez().getMao().remove(carta);
		// counterBomba--;
		// }
		// }
		// }
		// }else if(selecionadas[1] == 0 && selecionadas[2] == 0){
		// int counterPorrada = 1;
		// for(Carta carta : jogo.getJogadorDaVez().getMao()){
		// if(carta.getTipo().equals(Tipo.TIRO) && counterPorrada >0){
		// jogo.getJogadorDaVez().getMao().remove(carta);
		// counterPorrada--;
		// }
		// }
		// int counterBomba = 1;
		// for(Carta carta : jogo.getJogadorDaVez().getMao()){
		// if(carta.getTipo().equals(Tipo.TIRO) && counterBomba >0){
		// jogo.getJogadorDaVez().getMao().remove(carta);
		// counterBomba--;
		// }
		// }
		// }
		// }

		jogo.adicionaTropasTroca();
		cartasTiroSelecionadas = 0;
		cartasPorradaSelecionadas = 0;
		cartasBombaSelecionadas = 0;
		cartasValescaSelecionadas = 0;
		GerenciadorDeTelas.getInstancia().atualizaBarraTroca();

	}

	public String objetivoDoJogadorAtual() {
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
	
}