package br.uff.tcc.bcc.esii;


/**
 * @author Thadeu Jose
 *
 */
public class Carta {
	
	/**
	 * Tipo de cartas possiveis.
	 */	
	public enum Tipo{TIRO,PORRADA,BOMBA,VALESCA}; 
	
	/**
	 * Tipo da carta.
	 */
	Tipo tipo;

	/**
	 * @param tipo Tipo que a carta vai ser
	 */
	public Carta(Carta.Tipo tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return o tipo da carta
	 */
	public Tipo getTipo() {
		return tipo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		if (!(obj instanceof Carta))
			return false;
		Carta other = (Carta) obj;
		if (tipo != other.tipo)
			return false;
		return true;
	}
	
}
