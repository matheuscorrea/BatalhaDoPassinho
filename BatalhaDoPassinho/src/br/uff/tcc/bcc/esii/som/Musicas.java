package br.uff.tcc.bcc.esii.som;


public enum Musicas {

	ZEN(0,"Zen.mp3"),
	MEIGA(1,"Meiga_e_Abusada.mp3");
	
	
	private final int id;
	private final String nome;

    private Musicas(int id,String nome) {
    	this.id=id;
        this.nome = nome;
    }

    public boolean equalsName(String otherName){
        return (otherName == null)? false:nome.equals(otherName);
    }
    
    @Override
    public String toString(){
       return nome;
    }
    
    public static Musicas fromString(String text) {
        if (text != null) {
          for (Musicas b : Musicas.values()) {
            if (text.equalsIgnoreCase(b.toString())) {
              return b;
            }
          }
        }
        return null;
    } 
    
    public static Musicas fromID(int id) {
          for (Musicas b : Musicas.values()) {
            if (b.id==id) {
              return b;
            }
          }
        return null;
    }
}
