package br.uff.tcc.bcc.esii.modelo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Thadeu Jose
 *
 */
public class Mapa {
	/**
	 *Todos os territorios que estao no mapa 
	 */
	Map<String, Territorio> territorios;
	
	public Mapa() {
		this.territorios = new HashMap<>();
	}
	/**
	 * Adiciona um territorio a esse mapa
	 * @param territorio Territorio que pertence a esse mapa 
	 * @return se a inser��o foi concluida com exito ou n�o. 
	 */
	public boolean adicionaTerritorio(Territorio territorio){
		return (territorios.put(territorio.getNome(),territorio)!=null);
	}

	/**
	 * Retorna Territorio se tiver um territorio com aquele nome,null sen�o tiver
	 * @param nomeTerritorio Nome do territorio que vai ser buscado 
	 * @return Territorio se ele estiver contido,null se n�o estiver
	 */
	public Territorio getTerritorio(String nomeTerritorio)
	{
		return territorios.get(nomeTerritorio);
	}
}
