package br.uff.tcc.bcc.esii;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Thadeu Jose
 *
 */
public class Mapa {
	/**
	 *Todos os territorios que estao no mapa 
	 */
	Set<Territorio> territorios;
	
	public Mapa() {
		this.territorios = new HashSet<>();
	}
	/**
	 * @param territorio territorio que pertence a esse mapa 
	 * @return se a inser��o foi concluida com exito ou n�o. 
	 */
	public boolean adicionaTerritorio(Territorio territorio){
		return territorios.add(territorio);
	}

}
