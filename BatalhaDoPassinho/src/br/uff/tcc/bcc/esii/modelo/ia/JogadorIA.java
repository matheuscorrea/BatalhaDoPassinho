package br.uff.tcc.bcc.esii.modelo.ia;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import br.uff.tcc.bcc.esii.controlador.ControladorJogo;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.esii.visao.ConstanteDaCor;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas;
/**
 * Classe que controla as ações da Inteligência Artificial (IA) do jogo.<P>
 * A classe possui 3 métodos principais, que contém os algoritmos para
 * as 3 fases do jogo.<br>
 * Os algoritmos consistem em verificar todas opções de movimentos que a IA
 * pode fazer, e dar uma nota para cada movimento possível, baseando-se em
 * diferentes heurísticas.
 */
public class JogadorIA extends Jogador {

	/**
	 * Nota mínima para ataque
	 */
	private int notaDeCorteAtaque = 6;
	
	/**
	 * Nota mínima para mover tropas
	 */
	private int notaDeCorteRemanejamento = 1;
	
	public JogadorIA(String nome, ConstanteDaCor cor) {
		super(nome, cor);
		estadoFase2 = EstadoFase2.PARADO;
		estadoFase3 = EstadoFase3.PARADO;
	}

	/**
	 * Metodo da IA que toma as decisões da fase 1. Usa o controladorJogo para
	 * interagir com o jogo.<p>
	 * O método fica em um loop, que cada iteração é uma ação da IA,
	 * ele ve todas possibilidades de distribuição de tropas do JogadorIa atual, e
	 * baseando-se nas heurísticas da fase 3, escolhe o território com maior
	 * nota para distribuir uma tropa. <br>
	 */
	public void fase1() {
	
		//Territorio territorioAlvo = null;
		while (ControladorJogo.getInstancia().FaseUmTemTropasParaDistribuir()) {
			Territorio territorioAlvo = obtemTerritorioFase1();
        	chamaControladorFase1(territorioAlvo);
		} 
		//Simula o clique na proxima fase
		ControladorJogo.getInstancia().proximaFase();
//		ControladorJogo.getInstancia().atualizaTela();

	}
	
	public void acaoFase1(){
		if(ControladorJogo.getInstancia().FaseUmTemTropasParaDistribuir()) {
			Territorio territorioAlvo = obtemTerritorioFase1();
        	chamaControladorFase1(territorioAlvo);
		}else{
			ControladorJogo.getInstancia().proximaFase();
		}
	}

	/**
	 * Obtém o território com melhor nota na fase 1 com base
	 * nas heurísticas.
	 * @return
	 */
	private Territorio obtemTerritorioFase1() {
		float maiorNota;
		Territorio territorioAlvo;
		maiorNota = -1;
		territorioAlvo = null;
		List<Territorio> meusTerritorios = new ArrayList<Territorio>(this
				.getConquistados().values());
		//Escolhe a maior nota
		for (Territorio territorioProprio : meusTerritorios) {
			int nota = calculaNotaFaseUm(territorioProprio);
			if (nota > maiorNota) {
				maiorNota = nota;
				territorioAlvo = territorioProprio;
			}					
		}
		return territorioAlvo;
	}

	/**
	 * Método que simula o clique no território escolhido
	 * @param territorioAlvo
	 */
	private void chamaControladorFase1(Territorio territorioAlvo) {
		// Simula o clique do botão para distribuir a tropa
		if (territorioAlvo != null) {
			for (Button botao : ControladorJogo.getInstancia()
					.getListaDeBotoesTerritorios()) {
				if (botao.getId().equals(territorioAlvo.getNome())) {
					ControladorJogo.getInstancia().acaoTerritorio(botao);
				}
			}
		}
		GerenciadorDeTelas.getInstancia().atualizaTelaJogo();
	}

	/**
	 * Metodo da IA que toma as decisões da fase 2 <p>
	 * O método fica em um loop, que cada iteração é uma ação da IA,
	 * ele ve todas possibilidades de ataque do JogadorIa atual, e
	 * baseando-se nas heurísticas de ataque, escolhe a maior nota. <br>
	 * Caso essa nota seja maior que a nota de corte, ele faz o ataque, simulando 
	 * o clique nos botões que são obtidos pelo controlador. No final,
	 * simula o clique no botão de passar a fase.
	 */
	public void fase2() {
		//TODO disparar uma thread para fazer esses calculos
		
		float maiorNota = 0;
		Territorio alvo = null;
		Territorio atacante = null;
		
		do {
			maiorNota = 0;
			alvo = null;
			atacante = null;
			List<Territorio> meusTerritorios = new ArrayList<Territorio>(this
					.getConquistados().values());
			//Escolhe a maior nota
			for (Territorio territorioProprio : meusTerritorios) {
				List<Territorio> territoriosVizinhos = new ArrayList<Territorio>(
						territorioProprio.getVizinhos().values());
				for (Territorio territorioInimigo : territoriosVizinhos) {
					if (!this.possuiTerritorio(territorioInimigo.getNome())) {
						int nota = calculaNotaFaseDois(territorioProprio,
								territorioInimigo);
						if (nota > maiorNota) {
							maiorNota = nota;
							alvo = territorioInimigo;
							atacante = territorioProprio;
						}
					}
				}
			}
			
			// Simula o clique dos botões para o ataque
			if (alvo != null && maiorNota >= notaDeCorteAtaque) {
				//Para o atacante
				for (Button botao : ControladorJogo.getInstancia()
						.getListaDeBotoesTerritorios()) {
					if (botao.getId().equals(atacante.getNome())) {
						ControladorJogo.getInstancia().acaoTerritorio(botao);
					}
				}
				//Para o alvo
				for (Button botao : ControladorJogo.getInstancia()
						.getListaDeBotoesTerritorios()) {
					if (botao.getId().equals(alvo.getNome())) {
						ControladorJogo.getInstancia().acaoTerritorio(botao);
					}
				}
				//Botão ataque
				ControladorJogo.getInstancia().acaoAtaque();
				GerenciadorDeTelas.getInstancia().ataque();
			}
		} while (maiorNota >= notaDeCorteAtaque && !ControladorJogo.getInstancia().acabouJogo());

		if(ControladorJogo.getInstancia().acabouJogo()){
			ControladorJogo.getInstancia().fimDeJogo();
		}else{
			//Simula o clique na proxima fase
			ControladorJogo.getInstancia().proximaFase();
		}
	}

	private enum EstadoFase2{PARADO,SELECIONOU_TERRITORIOS};
	private EstadoFase2 estadoFase2;
	
	private float maiorNota = 0;
	private Territorio alvo = null;
	private Territorio atacante = null;
	
	public void acaoFase2(){

		switch (estadoFase2){
		case PARADO:
		
			maiorNota = 0;
			alvo = null;
			atacante = null;
			List<Territorio> meusTerritorios = new ArrayList<Territorio>(this
					.getConquistados().values());
			//Escolhe a maior nota
			for (Territorio territorioProprio : meusTerritorios) {
				List<Territorio> territoriosVizinhos = new ArrayList<Territorio>(
						territorioProprio.getVizinhos().values());
				for (Territorio territorioInimigo : territoriosVizinhos) {
					if (!this.possuiTerritorio(territorioInimigo.getNome())) {
						int nota = calculaNotaFaseDois(territorioProprio,
								territorioInimigo);
						if (nota > maiorNota) {
							maiorNota = nota;
							alvo = territorioInimigo;
							atacante = territorioProprio;
						}
					}
				}
			}
			
			// Se não tem mais oque atacar
			if (alvo == null || maiorNota < notaDeCorteAtaque) {
				//Simula o clique na proxima fase
				ControladorJogo.getInstancia().proximaFase();
			//Se ainda pode atacar
			}else{
				//Para o atacante
				for (Button botao : ControladorJogo.getInstancia()
						.getListaDeBotoesTerritorios()) {
					if (botao.getId().equals(atacante.getNome())) {
						ControladorJogo.getInstancia().acaoTerritorio(botao);
					}
				}
				//Para o alvo
				for (Button botao : ControladorJogo.getInstancia()
						.getListaDeBotoesTerritorios()) {
					if (botao.getId().equals(alvo.getNome())) {
						ControladorJogo.getInstancia().acaoTerritorio(botao);
					}
				}
				estadoFase2 = EstadoFase2.SELECIONOU_TERRITORIOS;
			}			
			break;
			
		case SELECIONOU_TERRITORIOS:
			//Botão ataque
			ControladorJogo.getInstancia().acaoAtaque();
			GerenciadorDeTelas.getInstancia().ataque();
		
			if(atacante.getDono().getObjetivo().concluido(atacante.getDono(),alvo.getDono())){
				ControladorJogo.getInstancia().fimDeJogo();
			}else{
				ControladorJogo.getInstancia().voltaAoJogo();
			}
			estadoFase2 = EstadoFase2.PARADO;

			break;
		}
		
		
	}
	
	/**
	 * Metodo da IA que toma as decisões da fase 3 Usa o controladorJogo para
	 * interagir com o jogo<p>
	 * O método fica em um loop, que cada iteração é uma ação da IA,
	 * ele ve todas possibilidades de movimento de tropas do JogadorIa atual, e
	 * baseando-se nas heurísticas da fase 3, escolhe o território com maior nota. 
	 */
	public void fase3() {
		//TODO disparar uma thread para fazer esses calculos
		float maiorNota = 0;
		Territorio destino = null;
		Territorio fonte = null;
		
		do {
			maiorNota = 0;
			destino = null;
			fonte = null;
			List<Territorio> meusTerritorios = new ArrayList<Territorio>(this
					.getConquistados().values());
			//Escolhe a maior nota
			for (Territorio territorioFonteCandidato : meusTerritorios) {
				if(!ControladorJogo.getInstancia().jaMoveuTerritorio(territorioFonteCandidato.getNome())){
					List<Territorio> territoriosVizinhos = new ArrayList<Territorio>(
							territorioFonteCandidato.getVizinhos().values());
					for (Territorio territorioDestinoCandidato : territoriosVizinhos) {
						if (this.possuiTerritorio(territorioDestinoCandidato.getNome())) {
							int nota = calculaNotaFaseTres(territorioFonteCandidato,
									territorioDestinoCandidato);
							if (nota > maiorNota) {
								maiorNota = nota;
								destino = territorioDestinoCandidato;
								fonte = territorioFonteCandidato;
							}
						}
					}
				}
			}
			// Simula o clique dos botões para o remanejamento
			if (destino != null && maiorNota >= notaDeCorteRemanejamento) {
				//Para o território fonte
				for (Button botao : ControladorJogo.getInstancia()
						.getListaDeBotoesTerritorios()) {
					if (botao.getId().equals(fonte.getNome())) {
						ControladorJogo.getInstancia().acaoTerritorio(botao);
					}
				}
				//Para o destino
				for (Button botao : ControladorJogo.getInstancia()
						.getListaDeBotoesTerritorios()) {
					if (botao.getId().equals(destino.getNome())) {
						ControladorJogo.getInstancia().acaoTerritorio(botao);
					}
				}
				//Botão mover
				ControladorJogo.getInstancia().acaoMover();
				ControladorJogo.getInstancia().limpaBotoesFase3();				
			}
		} while (maiorNota >= notaDeCorteRemanejamento);
		//Simula o clique na proxima fase
		ControladorJogo.getInstancia().proximaFase();
	}

	private Territorio destino = null;
	private Territorio fonte = null;
	private enum EstadoFase3{PARADO,SELECIONOU_TERRITORIOS};
	private EstadoFase3 estadoFase3;
	
	public void acaoFase3() {
		
		switch (estadoFase3){
		case PARADO:

			maiorNota = 0;
			destino = null;
			fonte = null;
			List<Territorio> meusTerritorios = new ArrayList<Territorio>(this
					.getConquistados().values());
			//Escolhe a maior nota
			for (Territorio territorioFonteCandidato : meusTerritorios) {
				if(!ControladorJogo.getInstancia().jaMoveuTerritorio(territorioFonteCandidato.getNome())){
					List<Territorio> territoriosVizinhos = new ArrayList<Territorio>(
							territorioFonteCandidato.getVizinhos().values());
					for (Territorio territorioDestinoCandidato : territoriosVizinhos) {
						if (this.possuiTerritorio(territorioDestinoCandidato.getNome())) {
							int nota = calculaNotaFaseTres(territorioFonteCandidato,
									territorioDestinoCandidato);
							if (nota > maiorNota) {
								maiorNota = nota;
								destino = territorioDestinoCandidato;
								fonte = territorioFonteCandidato;
							}
						}
					}
				}
			}
			// Simula o clique dos botões para o remanejamento
			if (destino != null && maiorNota >= notaDeCorteRemanejamento) {
				//Para o território fonte
				for (Button botao : ControladorJogo.getInstancia()
						.getListaDeBotoesTerritorios()) {
					if (botao.getId().equals(fonte.getNome())) {
						ControladorJogo.getInstancia().acaoTerritorio(botao);
					}
				}
				//Para o destino
				for (Button botao : ControladorJogo.getInstancia()
						.getListaDeBotoesTerritorios()) {
					if (botao.getId().equals(destino.getNome())) {
						ControladorJogo.getInstancia().acaoTerritorio(botao);
					}
				}

				estadoFase2 = EstadoFase2.SELECIONOU_TERRITORIOS;
			}else{
				ControladorJogo.getInstancia().proximaFase();
			}

			break;
		case SELECIONOU_TERRITORIOS:
			//Botão mover
			ControladorJogo.getInstancia().acaoMover();
			ControladorJogo.getInstancia().limpaBotoesFase3();
			estadoFase3 = EstadoFase3.PARADO;

			break;
		}
		
		
		
//		} while (maiorNota >= notaDeCorteRemanejamento);
		//Simula o clique na proxima fase
		//ControladorJogo.getInstancia().proximaFase();
	}

	
	/**
	 * Metodo da IA que toma as decisões da rodada 0 Usa o controladorJogo para
	 * interagir com o jogo
	 */
	public void rodada0() {
	}

	/**
	 * Método que utiliza as Heurísticas para calcular a nota de 
	 * um ataque de um território próprio para um inimigo.
	 * @param territorioProprio
	 * @param territorioInimigo
	 * @return nota de 0 a 10.
	 */
	private int calculaNotaFaseDois(Territorio territorioProprio,
			Territorio territorioInimigo) {
		int nota = Heuristicas.faseDoisPorTropas(
				territorioProprio, territorioInimigo);
		return nota;
	}
	
	/**
	 * Método que utiliza as Heurísticas para calcular a nota de 
	 * de um território próprio para receber tropas.
	 * @param territorioProprio
	 * @return nota de 0 a 10.
	 */
	private int calculaNotaFaseUm(Territorio territorioProprio) {
		int nota = Heuristicas.faseUmPorChanceDeAtaque(territorioProprio) + Heuristicas.faseUmPorDefesaDeTerritorio(territorioProprio) ;
		return nota;
	}
	
	/**
	 * Método que utiliza as Heurísticas para calcular a nota de 
	 * um remanejamento de um território fonte para um destino.
	 * @param territorioFonte
	 * @param territorioDestino
	 * @return nota de 0 a 10.
	 */
	private int calculaNotaFaseTres(Territorio territorioFonte, Territorio territorioDestino) {
		int nota = Heuristicas.faseTresPorDefesaDeTerritorio(territorioFonte, territorioDestino);
		return nota;
	}
}