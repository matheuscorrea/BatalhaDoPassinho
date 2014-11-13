package br.uff.tcc.bcc.esii.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import br.uff.tcc.bcc.esii.modelo.Carta.Tipo;
import br.uff.tcc.bcc.esii.modelo.objetivo.Objetivo;
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
	
	
	private Objetivo objetivo;
	
	/**
	 * Territorios que foram possuidos pelos jogador.
	 */
	private Map<String, Territorio> conquistados;//
	/**
	 * Cartas que o jogador possui.
	 */
	private List<Carta> mao;//
	/**
	 * 
	 */
	private ImageView foto;
	
	
	
	/**
	 * @param nome nome do jogador
	 * @param cor  cor que identifica o jogador
	 */
	public Jogador(String nome, ConstanteDaCor cor) {
		this.nome = nome;
		this.cor = cor;
		conquistados= new HashMap<>();
		mao = new ArrayList<>();
		String caminho = "file:media/imagens/mcs/"+nome+".png";
		foto = new ImageView(new Image(caminho, 110,110,true,true));
		
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
	
	public ImageView getFoto(){
		return foto;
	}
	
	public boolean possuiTerritorio(String territorio){
		return conquistados.containsKey(territorio);
	}
	
	public Territorio getTerritorioConquistado(String territorio){
		return conquistados.get(territorio);
	}
	
	public Objetivo getObjetivo(){
		return objetivo;
	}
	
	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}
	/**
	 * Adiciona uma carta aleatoria à mão do jogador.
	 */
	public void ganhaCarta(){
		
		int random = 1 + (int)(Math.random()*44);
		
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
		int descarte = (int)(Math.random()*mao.size());
		mao.remove(descarte);
	}
	public void removeCartaTiro() {
		Carta remove = new Carta(Tipo.BLANK);
		for (Carta c : mao){
			if(c.getTipo().equals(Carta.Tipo.TIRO)){
				remove = c;
			}
		}
		mao.remove(remove);
		
	}
	public void removeCartaPorrada() {
		Carta remove = new Carta(Tipo.BLANK);
		for (Carta c : mao){
			if(c.getTipo().equals(Carta.Tipo.PORRADA)){
				remove = c;
			}
		}
		mao.remove(remove);
		
	}
	public void removeCartaBomba() {
		Carta remove = new Carta(Tipo.BLANK);
		for (Carta c : mao){
			if(c.getTipo().equals(Carta.Tipo.BOMBA)){
				remove = c;
			}
		}
		mao.remove(remove);
		
	}
	public void removeCartaValesca() {
		Carta remove = new Carta(Tipo.BLANK);
		for (Carta c : mao){
			if(c.getTipo().equals(Carta.Tipo.VALESCA)){
				remove = c;
			}
		}
		mao.remove(remove);
		
	}
	
}