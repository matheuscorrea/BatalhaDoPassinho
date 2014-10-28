package br.uff.tcc.bcc.esii.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import br.uff.tcc.bcc.esii.modelo.objetivo.IObjetivo;
import br.uff.tcc.bcc.esii.visao.ConstanteDaCor;

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
	private ConstanteDaCor cor;
	/**
	 * Territorios que foram possuidos pelos jogador.
	 */
	
	private IObjetivo objetivo;
	
	
	private Map<String, Territorio> conquistados;//
	/**
	 * Cartas que o jogador possue.
	 */
	private List<Carta> mao;//
	/**
	 * @param nome nome do jogador
	 * @param cor  cor que identifica o jogador
	 */
	public Jogador(String nome, ConstanteDaCor cor) {
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
	public ConstanteDaCor getCor() {
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
	
	public boolean possuiTerritorio(String territorio){
		return conquistados.containsKey(territorio);
	}
	
	public Territorio getTerritorioConquistado(String territorio){
		return conquistados.get(territorio);
	}
	
	public IObjetivo getObjetivo(){
		return objetivo;
	}
	
	public void setObjetivo(IObjetivo objetivo) {
		this.objetivo = objetivo;
	}
	/**
	 * Adiciona uma carta aleatoria à mão do jogador.
	 */
	public void ganhaCarta(){
		
		int random = 1 + (int)(Math.random()*44);
		System.out.println(random);
		
		if(random <= 14 ){			
			mao.add(new Carta(Carta.Tipo.TIRO));
		}else if(random > 14 && random <= 28){		
			mao.add(new Carta(Carta.Tipo.PORRADA));
		}else if(random > 28 && random <= 42){
			mao.add(new Carta(Carta.Tipo.BOMBA));
		}else	
			mao.add(new Carta(Carta.Tipo.VALESCA));		
	}
	/**
	 * Descarta uma carta aleatória da mão do jogador.
	 */
	public void descartar(){
		int descarte = 1 + (int)(Math.random()*mao.size());
		mao.remove(descarte);
	}
	
}