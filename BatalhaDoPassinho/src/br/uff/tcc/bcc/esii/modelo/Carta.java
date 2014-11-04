package br.uff.tcc.bcc.esii.modelo;


/**
 * @author Thadeu Jose
 *
 */
public class Carta {
	
	/**
	 * Tipo de cartas possiveis.
	 */	
	public enum Tipo{TIRO,PORRADA,BOMBA,VALESCA,BLANK}; 
	
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

	public static Carta fromString(String string){
		switch(string){
			case "Tiro":
				return new Carta(Tipo.TIRO);
			case "Porrada":
				return new Carta(Tipo.PORRADA);
			case "Bomba":
				return new Carta(Tipo.BOMBA);
			case "Valesca":
				return new Carta(Tipo.VALESCA);
			default:
				return new Carta(Tipo.BLANK);
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		switch(this.tipo){
		case TIRO:
			return "Tiro";
		case PORRADA:
			return "Porrada";
		case BOMBA:
			return "Bomba";
		case VALESCA:
			return "Valesca";
		default:
			return "Blank";
		}
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
