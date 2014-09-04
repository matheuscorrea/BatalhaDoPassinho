package br.uff.tcc.bcc.esii.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Thadeu Jose
 *
 */
public class Jogador {

	/**
	 * Nome do jogador.
	 */
	private String nome;
	/**
	 * Cor que identifica esse jogador.
	 */
	private String cor;
	/**
	 * Territorios que foram possuidos pelos jogador.
	 */
	private Set<Territorio> conquistados;//
	/**
	 * Cartas que o jogador possue.
	 */
	private List<Carta> mao;//
	/**
	 * @param nome nome do jogador
	 * @param cor  cor que identifica o jogador
	 */
	public Jogador(String nome, String cor) {
		this.nome = nome;
		this.cor = cor;
		conquistados=new HashSet<>();
		mao = new ArrayList<>();
	}
	/**
	 * @return o nome desse jogador
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @return a cor desse jogador
	 */
	public String getCor() {
		return cor;
	}
	/**
	 * @return os territorios conquistados por esse jogador
	 */
	public Set<Territorio> getConquistados() {
		return conquistados;
	}
	/**
	 * @return a mao atual do jogador
	 */
	public List<Carta> getMao() {
		return mao;
	}	
	/**
	 * @param carta carta a ser adicionada
	 * @return se a inserção foi concluida com exito ou não.
	 */
	public boolean adicionaCarta(Carta carta){
		return mao.add(carta);
	}
	/**
	 * @return o numero de territorios conquistados
	 */
	public int numeroDeConquistados(){
		return conquistados.size();
	}
	/**
	 * @param territorio territorio que o jogador conquistou
	 * @return se a inserção foi concluida com exito ou não.
	 */
	public boolean adicionadConquistados(Territorio territorio){
		return conquistados.add(territorio);
	}
			
}
