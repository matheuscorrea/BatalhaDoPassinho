package br.uff.tcc.bcc.esii.visao;

/**
 * Enum para representar as constantes envolvendo territorios possíveis da aplicação
 */
public enum ConstanteDoTerritorio {
	
	//Territorio Niteroi
	VIRADOURO("Viradouro",990,35),
	WAXY ("Waxy",1050,75),
	TOUCHE ("Touché",1075,125),
	SAO_FIRMINO("São Firmino",935,115),
	CASA ("Casa",1000,97),
	FLAMBOYANT ("Flamboyant",1090,190),
	WOODS ("Woods",1010,240),
	
	 //Territorio Centro
	FUNDICAO("Fundição",670,70),
	CIRCO("Circo",720,160),
	SIX("Six",775,130),
	LA_PAZ("La Paz",810,120),
	LA_PASSION("La Passion",710,200),
	
	//Territorio Zona Norte
	OLIMPO("Olimpo",605,5),
	PROVISORIO_CLUB("Provisório Club",630,50),
	CASTELO_BONSUCESSO("Castelo Bonsucesso",650,170),
	RAIO_DE_SOL("Raio de Sol",570,100),
	SIMPATIA("Simpatia",470,150),
	IMPERATOR("Imperator",510,170),
	OPEN_LOUNGE("Open Lounge",620,200),
	BUXIXO("Buxixo",660,215),
	
	//Territorio Zona Sul
	PISTA_3("Pista 3",785,230),
	MATRIZ("Matriz",720,240),
	KALABRIA("Kalabria",745,257),
	ZOZO("Zozo",820,265),
	PRAIA("Praia",678,325),
	MARIUZZIN("Mariuzzin",740,305),
	BARONETTI("Baronetti",740,373),
	ZEROZERO("00",620,380),
	
	//Territorio Zona Oeste
	SAO_NUNCA("São Nunca",330,430),
	LA_ISLA("La Isla",350,390),
	CAPITONNE("Capitonné",320,380),
	KISS_E_FLY("Kiss e Fly",300,270),
	CASTELO_DAS_PEDRAS("Castelo das Pedras",200,300),
	ZEROVINTEUM("021",230,360),
	PLATINUM("Platinum",230,430),
	NUTH("Nuth",150,380),
	ZAX("Zax",150,430),
	BARRA_MUSIC("Barra Music",90,400);

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
