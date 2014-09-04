package br.uff.tcc.bcc.esii.modelo;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Thadeu Jose
 *
 */
public class Territorio {
	
	/**
	 * Nome do territorio.
	 */
	private String nome;
	/**
	 * Jogador que e o atual dono do territorio
	 */
	private Jogador dono;
	/**
	 * Quantidade de tropas que se encontram nesse territorio
	 */
	private int quantidadeTropa;	
	/**
	 * Continente ao qual esse territorio faz parte
	 */
	private String continente;
	/**
	 * Todos os vizinhos desse territorio
	 */
	private Set<Territorio> vizinhos;
	
	/**
	 * @param nome Nome do territorio
	 * @param continente Nome do continente ao qual o territorio faz parte
	 */
	public Territorio(String nome, String continente) {
		this.nome = nome;
		this.continente = continente;
		vizinhos = new HashSet<>();
	}

	/**
	 * @return o jogador que e dono do territorio
	 */
	public Jogador getDono() {
		return dono;
	}

	/**
	 * @param dono o jogador que e dono do territorio
	 */
	public void setDono(Jogador dono) {
		this.dono = dono;
	}

	/**
	 * @return a quantidade de tropas que se encontra naquele territorio 
	 */
	public int getQuantidadeTropa() {
		return quantidadeTropa;
	}

	/**
	 * @param quantidadeTropa a quantidade atual de tropas dentro daquele territorio
	 */
	public void setQuantidadeTropa(int quantidadeTropa) {
		this.quantidadeTropa = quantidadeTropa;
	}

	/**
	 * @return o nome do territorio
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return o continente que o territorio esta localizado
	 */
	public String getContinente() {
		return continente;
	}

	/**
	 * @return o conjunto(Set) de vizinhos desse territorio
	 */
	public Set<Territorio> getVizinhos() {
		return vizinhos;
	}	
		
	/**
	 * @param territorio territorio que e vizinho desse territorio
	 * @return se a inserção foi concluida com exito ou não. 
	 */
	public boolean adicionaVizinhos(Territorio territorio){
		return vizinhos.add(territorio);		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Territorio))
			return false;
		Territorio other = (Territorio) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
