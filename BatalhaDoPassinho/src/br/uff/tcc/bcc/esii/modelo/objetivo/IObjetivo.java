package br.uff.tcc.bcc.esii.modelo.objetivo;

import br.uff.tcc.bcc.esii.modelo.Jogador;

public interface IObjetivo {
	/**
	 * @param atacante 
	 * @param defensor 
	 * @return se territorio foi conquistado
	 */
	public boolean concluido(Jogador atacante, Jogador defensor);
	
	public String getNomeObjetivo();
}
