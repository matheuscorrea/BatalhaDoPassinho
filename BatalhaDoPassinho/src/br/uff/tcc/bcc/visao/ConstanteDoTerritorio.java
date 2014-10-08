package br.uff.tcc.bcc.visao;

/**
 * Enum para representar as constantes envolvendo territorios possíveis da aplicação
 */
public enum ConstanteDoTerritorio {
	
	//Territorio Niteroi
	VIRADOURO("Viradouro",0,0),
	WAXY ("Waxy",0,0),
	TOUCHE ("Touché",0,0),
	SAO_FIRMINO("São Firmino",0,0),
	CASA ("Casa",0,0),
	FLAMBOYANT ("Flamboyant",0,0),
	WOODS ("Woods",0,0),
	
	 //Territorio Centro
	FUNDICAO("Fundição",0,0),
	CIRCO("Circo",0,0),
	SIX("Six",0,0),
	LA_PAZ("La Paz",0,0),
	LA_PASSION("La Passion",0,0),
	
	//Territorio Zona Norte
	OLIMPO("Olimpo",0,0),
	PROVISORIO_CLUB("Provisório Club",0,0),
	CASTELO_BONSUCESSO("Castelo Bonsucesso",0,0),
	RAIO_DE_SOL("Raio de Sol",0,0),
	SIMPATIA("Simpatia",0,0),
	IMPERATOR("Imperator",0,0),
	OPEN_LOUNGE("Open Lounge",0,0),
	BUXIXO("Buxixo",0,0),
	
	//Territorio Zona Sul
	PISTA_3("Pista 3",0,0),
	MATRIZ("Matriz",0,0),
	KALABRIA("Kalabria",0,0),
	ZOZO("Zozo",0,0),
	PRAIA("Praia",0,0),
	MARIUZZIN("Mariuzzin",0,0),
	BARONETTI("Baronetti",0,0),
	ZEROZERO("00",0,0),
	
	//Territorio Zona Oeste
	SAO_NUNCA("São Nunca",0,0),
	LA_ISLA("La Isla",0,0),
	CAPITONNE("Capitonné",0,0),
	KISS_E_FLY("Kiss e Fly",0,0),
	CASTELO_DAS_PEDRAS("Castelo das Pedras",0,0),
	ZEROVINTEUM("021",0,0),
	PLATINUM("Platinum",0,0),
	NUTH("Nuth",0,0),
	ZAX("Zax",0,0),
	BARRA_MUSIC("Barra Music",0,500);

	private final String nome;
	private final int x,y;

    private ConstanteDoTerritorio(String nome,int x,int y) {
        this.nome = nome;
        this.x=x;
        this.y=y;
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
    
    public int getX(){
    	return x;
    }
    
    public int getY(){
    	return y;
    }
}
