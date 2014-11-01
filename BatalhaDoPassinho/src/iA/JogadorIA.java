package iA;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import br.uff.tcc.bcc.esii.controlador.ControladorJogo;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Mapa;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.esii.visao.ConstanteDaCor;

public class JogadorIA extends Jogador {

	private int notaDeCorteAtaque = 6;
	// TODO necessários?
	private Mapa mapa;
	List<Territorio> todosTerritorios;

	public JogadorIA(String nome, ConstanteDaCor cor) {
		super(nome, cor);
	}

	/**
	 * Metodo da IA que toma as decisões da fase 1 Usa o controladorJogo para
	 * interagir com o jogo
	 */
	public void fase1() {

	}

	/**
	 * Metodo da IA que toma as decisões da fase 2 Usa o controladorJogo para
	 * interagir com o jogo
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
			for (Territorio territorioProprio : meusTerritorios) {
				List<Territorio> territoriosVizinhos = new ArrayList<Territorio>(
						territorioProprio.getVizinhos().values());
				for (Territorio territorioInimigo : territoriosVizinhos) {
					if (!this.possuiTerritorio(territorioInimigo.getNome())) {
						int nota = calculaNotaAtaque(territorioProprio,
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
				ControladorJogo.getInstancia().acaoAtaque(null);
			}
		} while (maiorNota >= notaDeCorteAtaque);
		//Simula o clique na proxima fase
		ControladorJogo.getInstancia().proximaFase();
	}

	private int calculaNotaAtaque(Territorio territorioProprio,
			Territorio territorioInimigo) {
		int nota = Heuristicas.ataquePorTropas(
				territorioProprio, territorioInimigo);
		return nota;
	}

	/**
	 * Metodo da IA que toma as decisões da fase 3 Usa o controladorJogo para
	 * interagir com o jogo
	 */
	public void fase3() {
		
	}

	/**
	 * Metodo da IA que toma as decisões da rodada 0 Usa o controladorJogo para
	 * interagir com o jogo
	 */
	public void rodada0() {
	}

	
}
