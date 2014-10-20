package br.uff.tcc.bcc.esii.visao;

public enum ConstanteDaCor {
	
	AZUL("Azul"),
	VERDE("Verde"),
	CINZA("Cinza"),
	ROSA("Rosa"),
	VERMELHO("Vermelho"),
	AMARELO("Amarelo");
	
	private final String nome;

    private ConstanteDaCor(String nome) {
        this.nome = nome;
    }

    public boolean equalsName(String otherName){
        return (otherName == null)? false:nome.equals(otherName);
    }
    
    @Override
    public String toString(){
       return nome;
    }
    
    public static ConstanteDoTerritorio fromString(String text) {
        if (text != null) {
          for (ConstanteDoTerritorio b : ConstanteDoTerritorio.values()) {
            if (text.equalsIgnoreCase(b.toString())) {
              return b;
            }
          }
        }
        return null;
    }
 
}
