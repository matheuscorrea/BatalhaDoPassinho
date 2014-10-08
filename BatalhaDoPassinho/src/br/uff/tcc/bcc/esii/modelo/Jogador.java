package br.uff.tcc.bcc.esii.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * REVER
	 */
	private Map<String, Territorio> conquistados;//
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
		conquistados= new HashMap<>();
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
	public Map<String,Territorio> getConquistados() {
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
	 * Remove uma carta da mão do jogador
	 * @param c Carta a ser removida
	 */
	public void removeCarta(Carta c){
		mao.remove(c);		
	}
	/**
	 * @return o numero de territorios conquistados
	 */
	public int numeroDeConquistados(){
		return conquistados.size();
	}
	/**
	 * Adiciona território conquistado
	 * @param territorio territorio que o jogador conquistou  
	 */
	public void adicionaConquistados(Territorio territorio){
		territorio.setDono(this);
		conquistados.put(territorio.getNome(),territorio);		
	}
	
	/**
	 * Remove território da lista de conquistados
	 * @param territorio
	 */
	public void removeConquistados(Territorio territorio){
		conquistados.remove(territorio.getNome());		
	}
	
			
}
