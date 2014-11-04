package br.uff.tcc.bcc.esii.modelo.objetivo;

import br.uff.tcc.bcc.esii.modelo.Jogador;

public abstract class Objetivo {

	private int index;
	
	/**
	 * @param atacante 
	 * @param defensor 
	 * @return se territorio foi conquistado
	 */
	public abstract boolean concluido(Jogador atacante, Jogador defensor);
	
	public abstract String getNomeObjetivo();

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
