package br.uff.tcc.bcc.esii.som;


public enum Musicas {

	INICIAL(0,"audio/Mc_Orelha/Faixa_de_Gaza.mp3"),
	ESCOLHA(1,"audio/Mc_Smith/Apaixonado_Bossa_Nova.mp3"),
	FIM(2,"audio/Mr_Catra/O_Papai_chegou.mp3"),	
	TROCA(3,"audio/Valesca/Beijinho_no_ombro.mp3"),
	
	JOGO1(5,"audio/Mc_Pedrinho/Dom_dom_dom.mp3"),
	JOGO2(6,"audio/Mc_Bola/Soltinha.mp3" ),
	JOGO3(7,"audio/Funk_da_Antiga/Rap_da_Diferença.mp3" ),
	JOGO4(8,"audio/Anitta/Menina_Ma.mp3" ),
	//Adicione as musica antes desse ID
	ULTIMO_JOGO(9,"audio/Mc_Koringa/Kika_no_calcanhar.mp3"),
	
	BATIDA1(21,"audio/Batidas/batida01.mp3" ),
	BATIDA2(22,"audio/Batidas/batida02.mp3" ),
	BATIDA3(23,"audio/Batidas/batida02.mp3" ),
	//Adicione as musica antes desse ID
	ULTIMA_BATIDA(24,"audio/Batidas/batida03.mp3" ),
	
	EFEITO_SUCESSO1(30,"audio/Efeitos/Risada_Catra.mp3"),
	EFEITO_FRACASSO1(31,"audio/Efeitos/Toasty.mp3");
	
	
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
    
    public int getID(){
    	return id;
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
