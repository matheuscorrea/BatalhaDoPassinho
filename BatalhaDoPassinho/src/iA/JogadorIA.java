package iA;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import br.uff.tcc.bcc.esii.controlador.ControladorJogo;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.esii.visao.ConstanteDaCor;

public class JogadorIA extends Jogador {

	private int notaDeCorteAtaque = 6;
	private int notaDeCorteRemanejamento = 1;
	
	
	public JogadorIA(String nome, ConstanteDaCor cor) {
		super(nome, cor);
	}

	/**
	 * Metodo da IA que toma as decisões da fase 1. Usa o controladorJogo para
	 * interagir com o jogo
	 */
	public void fase1() {
	
		//Territorio territorioAlvo = null;
		while (ControladorJogo.getInstancia().FaseUmTemTropasParaDistribuir()) {
			Territorio territorioAlvo = obtemTerritorio();
        	chamaControlador(territorioAlvo);
		} 
		//Simula o clique na proxima fase
		ControladorJogo.getInstancia().proximaFase();
	}

	private Territorio obtemTerritorio() {
		float maiorNota;
		Territorio territorioAlvo;
		maiorNota = 0;
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

	private void chamaControlador(Territorio territorioAlvo) {
		// Simula o clique do botão para distribuir a tropa
		if (territorioAlvo != null) {
			for (Button botao : ControladorJogo.getInstancia()
					.getListaDeBotoesTerritorios()) {
				if (botao.getId().equals(territorioAlvo.getNome())) {
					ControladorJogo.getInstancia().acaoTerritorio(botao);
				}
			}
		}
	}

	/**
	 * Metodo da IA que toma as decisões da fase 2 <p>
	 * O método fica em um loop, que cada iteração é uma ação da IA,
	 * ele ve todas possibilidades de ataque do JogadorIa atual, e
	 * baseando-se nas heurísticas de ataque, escolhe a maior nota. <br>Caso
	 * essa nota seja maior que a nota de corte, ele faz o ataque, simulando 
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
			}
		} while (maiorNota >= notaDeCorteAtaque);
		//Simula o clique na proxima fase
		ControladorJogo.getInstancia().proximaFase();
	}


	/**
	 * Metodo da IA que toma as decisões da fase 3 Usa o controladorJogo para
	 * interagir com o jogo
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

	/**
	 * Metodo da IA que toma as decisões da rodada 0 Usa o controladorJogo para
	 * interagir com o jogo
	 */
	public void rodada0() {
	}


	private int calculaNotaFaseDois(Territorio territorioProprio,
			Territorio territorioInimigo) {
		int nota = Heuristicas.ataquePorTropas(
				territorioProprio, territorioInimigo);
		return nota;
	}
	
	private int calculaNotaFaseUm(Territorio territorioProprio) {
		int nota = Heuristicas.faseUmPorChanceDeAtaque(territorioProprio) + Heuristicas.faseUmPorDefesaDeTerritorio(territorioProprio) ;
		return nota;
	}
	
	private int calculaNotaFaseTres(Territorio territorioFonte, Territorio territorioDestino) {
		int nota = Heuristicas.faseTresPorDefesaDeTerritorio(territorioFonte, territorioDestino);
		return nota;
	}
}