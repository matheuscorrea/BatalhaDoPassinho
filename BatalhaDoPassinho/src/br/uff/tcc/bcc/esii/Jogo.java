package br.uff.tcc.bcc.esii;

import java.util.List;
import java.util.Queue;
import java.util.Random;

import br.uff.tcc.bcc.esii.modelo.Carta;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Mapa;
import br.uff.tcc.bcc.esii.modelo.Territorio;

/**
 * @author Thadeu Jose
 *
 */
/**
 * @author Thadeu Jose
 *
 */
/**
 * @author Thadeu Jose
 *
 */
public class Jogo {
	/**
	 * Numero do turno atual
	 */
	int turno;
	/**
	 * Jogadores que estao atualmente jogando
	 */
	Queue<Jogador> jogadores;
	/**
	 * Valor da troca de cartas
	 */
	int valorTrocaAtual;
	/**
	 * Se o jogador dominou territorio essa rodada
	 */
	boolean jogadorDominouTerritorio;
	/**
	 * Mapa com todos os territorios do jogo
	 */
	Mapa mapa;
	/**
	 * Deck das carta do jogo
	 */
	List<Carta> deck;
	/**
	 * Todos os tipos de fase que um jogo pode ter
	 */
	public enum TipoFase{FASE_1,FASE_2,FASE_3};
	/**
	 * Marcador da fase atual
	 */
	public TipoFase faseAtual; 
		
	/**
	 * 
	 */
	public Jogo() {
		mapa=new Mapa();
	}

	/**
	 * @return um inteiro de 1 a 6 correspondente ao lançamento de um dado
	 */
	public int dado(){
		Random random = new Random();
		return random.nextInt(6)+1; 
	}
	
	/**
	 * Inicializa o jogo.
	 */
	public void inicializa()
	{
		inicializaMapa();
	}

	private void inicializaMapa() {
		//Region Territorio Niteroi 
		Territorio viradouro = new Territorio("Viradouro", "Niteroi");
		Territorio waxy = new Territorio("Waxy", "Niteroi");
		Territorio touche = new Territorio("Touché", "Niteroi");
		Territorio saoFirmino = new Territorio("São Firmino", "Niteroi");
		Territorio casa = new Territorio("Casa", "Niteroi");
		Territorio flamboyant = new Territorio("Flamboyant", "Niteroi");
		Territorio woods = new Territorio("Woods", "Niteroi");
		//EndRegion Territorio Niteroi
		
		//Region Territorio Centro
		Territorio fundicao = new Territorio("Fundição", "Centro");
		Territorio circo = new Territorio("Circo", "Centro");
		Territorio six = new Territorio("Six", "Centro");
		Territorio laPaz = new Territorio("La Paz", "Centro");
		Territorio laPassion = new Territorio("La Passion", "Centro");
		//EndRegion Territorio Centro
		
		//Region Territorio Zona Norte
		Territorio olimpo = new Territorio("Olimpo", "Zona Norte");
		Territorio provisorioClub = new Territorio("Provisório Club", "Zona Norte");
		Territorio casteloBonsucesso = new Territorio("Castelo Bonsucesso", "Zona Norte");
		Territorio raioDeSol = new Territorio("Raio de Sol", "Zona Norte");
		Territorio simpatia = new Territorio("Simpatia", "Zona Norte");
		Territorio imperator = new Territorio("Imperator", "Zona Norte");
		Territorio openLounge = new Territorio("Open Lounge", "Zona Norte");
		Territorio buxixo = new Territorio("Buxixo", "Zona Norte");
		//EndRegion Territorio Zona Norte
		
		//Region Territorio Zona Sul
		Territorio pista3 = new Territorio("Pista 3", "Zona Sul");
		Territorio matriz = new Territorio("Matriz", "Zona Sul");
		Territorio kallabria = new Territorio("Kallabria", "Zona Sul");
		Territorio zozo = new Territorio("Zozo", "Zona Sul");
		Territorio praia = new Territorio("Praia", "Zona Sul");
		Territorio mariuzzin = new Territorio("Mariuzzin", "Zona Sul");
		Territorio baronetti = new Territorio("Baronetti", "Zona Sul");
		Territorio zeroZero = new Territorio("00", "Zona Sul");
		//EndRegion Territorio Zona Sul
		
		//Region Territorio Zona Oeste
		Territorio saoNunca = new Territorio("São Nunca", "Zona Oeste");
		Territorio laIsla = new Territorio("La Isla", "Zona Oeste");
		Territorio capitonne = new Territorio("Capitonné", "Zona Oeste");
		Territorio kissEFly = new Territorio("Kiss e Fly", "Zona Oeste");
		Territorio casteloDasPedras = new Territorio("Castelo das Pedras", "Zona Oeste");
		Territorio zeroVinteUm = new Territorio("021", "Zona Oeste");
		Territorio platinum = new Territorio("Platinum", "Zona Oeste");
		Territorio nuth = new Territorio("Nuth", "Zona Oeste");
		Territorio zax = new Territorio("Zax", "Zona Oeste");
		Territorio barraMusic = new Territorio("Barra Music", "Zona Oeste");
		//EndRegion Territorio Zona Oeste
				
		//Region adiciona Vizinho Niteroi
		viradouro.adicionaVizinhos(saoFirmino);
		viradouro.adicionaVizinhos(casa);
		viradouro.adicionaVizinhos(waxy);
		viradouro.adicionaVizinhos(fundicao);
		
		waxy.adicionaVizinhos(viradouro);
		waxy.adicionaVizinhos(casa);
		waxy.adicionaVizinhos(touche);
		
		touche.adicionaVizinhos(waxy);
		touche.adicionaVizinhos(flamboyant);
		touche.adicionaVizinhos(casa);
		
		saoFirmino.adicionaVizinhos(viradouro);
		saoFirmino.adicionaVizinhos(casa);
		
		casa.adicionaVizinhos(saoFirmino);
		casa.adicionaVizinhos(waxy);
		casa.adicionaVizinhos(viradouro);
		casa.adicionaVizinhos(touche);
		
		flamboyant.adicionaVizinhos(touche);
		flamboyant.adicionaVizinhos(woods);
		
		woods.adicionaVizinhos(flamboyant);
		woods.adicionaVizinhos(circo);
		//EndRegion adiciona Vizinho Niteroi
		
		//Region adicionaVizinhos Centro
		fundicao.adicionaVizinhos(provisorioClub);
		fundicao.adicionaVizinhos(casteloBonsucesso);
		fundicao.adicionaVizinhos(viradouro);
		fundicao.adicionaVizinhos(circo);
		
		circo.adicionaVizinhos(casteloBonsucesso);
		circo.adicionaVizinhos(six);
		circo.adicionaVizinhos(laPaz);
		circo.adicionaVizinhos(laPassion);
		
		laPaz.adicionaVizinhos(circo);
		laPaz.adicionaVizinhos(six);
		
		six.adicionaVizinhos(laPaz);
		six.adicionaVizinhos(circo);
		six.adicionaVizinhos(laPassion);
				
		laPassion.adicionaVizinhos(buxixo);
		laPassion.adicionaVizinhos(matriz);
		laPassion.adicionaVizinhos(pista3);
		laPassion.adicionaVizinhos(circo);
		laPassion.adicionaVizinhos(six);
		//EndRegion adicionaVizinhos Centro
		
		//Region adicionaVizinhos Zona Norte
		//EndRegion adicionaVizinhos Zona Norte
		
		//Region adicionaVizinhos Zona Sul
		//EndRegion adicionaVizinhos Zona Sul
		
		//Region adicionaVizinhos Zona Oeste
		//EndRegion adicionaVizinhos Zona Oeste
		
		//Region adicionaTerritorio Niteroi
		mapa.adicionaTerritorio(viradouro);
		mapa.adicionaTerritorio(waxy);
		mapa.adicionaTerritorio(touche);
		mapa.adicionaTerritorio(saoFirmino);
		mapa.adicionaTerritorio(casa);
		mapa.adicionaTerritorio(flamboyant);
		mapa.adicionaTerritorio(woods);
		//EndRegion adicionaTerritorio Niteroi
		
		//Region adicionaTerritorio Centro
		mapa.adicionaTerritorio(fundicao);
		mapa.adicionaTerritorio(circo);
		mapa.adicionaTerritorio(six);
		mapa.adicionaTerritorio(laPaz);
		mapa.adicionaTerritorio(laPassion);
		//EndRegion adicionaTerritorio Centro
		
		//Region adicionaTerritorio Zona Norte
		mapa.adicionaTerritorio(olimpo);
		mapa.adicionaTerritorio(provisorioClub);
		mapa.adicionaTerritorio(casteloBonsucesso);
		mapa.adicionaTerritorio(raioDeSol);
		mapa.adicionaTerritorio(simpatia);
		mapa.adicionaTerritorio(imperator);
		mapa.adicionaTerritorio(openLounge);
		mapa.adicionaTerritorio(buxixo);
		//EndRegion adicionaTerritorio Zona Norte
		
		//Region adicionaTerritorio Zona Sul
		mapa.adicionaTerritorio(pista3);
		mapa.adicionaTerritorio(matriz);
		mapa.adicionaTerritorio(kallabria);
		mapa.adicionaTerritorio(zozo);
		mapa.adicionaTerritorio(praia);
		mapa.adicionaTerritorio(mariuzzin);
		mapa.adicionaTerritorio(baronetti);
		mapa.adicionaTerritorio(zeroZero);
		//EndRegion adicionaTerritorio Zona Sul
		
		//Region adicionaTerritorio Zona Oeste
		mapa.adicionaTerritorio(saoNunca);
		mapa.adicionaTerritorio(laIsla);
		mapa.adicionaTerritorio(capitonne);
		mapa.adicionaTerritorio(kissEFly);
		mapa.adicionaTerritorio(casteloDasPedras);
		mapa.adicionaTerritorio(zeroVinteUm);
		mapa.adicionaTerritorio(platinum);
		mapa.adicionaTerritorio(nuth);
		mapa.adicionaTerritorio(zax);
		mapa.adicionaTerritorio(barraMusic);
		//EndRegion adicionaTerritorio Zona Oeste
		
	}
	
}
