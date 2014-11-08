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
    
    public static boolean equalsConstante(ConstanteDaCor cor1, ConstanteDaCor cor2){
    	return (cor1 == null || cor2 ==null)? false:cor1.equalsName(cor2.toString());
    	
    }
    
    @Override
    public String toString(){
       return nome;
    }
    
    public static ConstanteDaCor fromString(String text) {
        if (text != null) {
          for (ConstanteDaCor b : ConstanteDaCor.values()) {
            if (text.equalsIgnoreCase(b.toString())) {
              return b;
            }
          }
        }
        return null;
    } 
}
