package br.uff.tcc.bcc.esii.modelo;

import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * @author Thadeu Jose
 *
 */
public class Jogo {
	/**
	 * Numero do turno atual
	 */
	int turno;
	/**
	 * Jogadores que estao atualmente jogando
	 */
	Queue<Jogador> jogadores;
	/**
	 * Valor da troca de cartas
	 */
	int valorTrocaAtual;
	/**
	 * Se o jogador dominou territorio essa rodada
	 */
	boolean jogadorDominouTerritorio;
	/**
	 * Mapa com todos os territorios do jogo
	 */
	Mapa mapa;
	/**
	 * Deck das carta do jogo
	 */
	List<Carta> deck;
	/**
	 * Todos os tipos de fase que um jogo pode ter
	 */
	public enum TipoFase{FASE_1,FASE_2,FASE_3};
	/**
	 * Marcador da fase atual
	 */
	public TipoFase faseAtual; 
		
	/**
	 * 
	 */
	public Jogo() {
	}

	/**
	 * @return um inteiro de 1 a 6 correspondente ao lançamento de um dado
	 */
	public int dado(){
		Random random = new Random();
		return random.nextInt(6)+1; 
	}
}
