package br.uff.tcc.bcc.esii;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import sun.security.util.Length;
import br.uff.tcc.bcc.esii.modelo.Carta;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Mapa;
import br.uff.tcc.bcc.esii.modelo.Territorio;

/**
 * @author Thadeu Jose
 * REVER
 */
public class Jogo {
	/**
	 * Numero da rodada atual
	 */
	int rodada;
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
	 * Construtor da classe jogo
	 */
	public Jogo() {
		rodada=0;
		faseAtual = TipoFase.FASE_1;
		jogadores=new LinkedList<Jogador>();
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

	/**
	 * Inicializa o mapa.
	 */
	private void inicializaMapa() {
		//TODO Adicionar a coordenada dos territórios
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
		Territorio kalabria = new Territorio("Kalabria", "Zona Sul");
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
				
		//Region adicionaVizinhos Niteroi
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
		//EndRegion adicionaVizinhos Niteroi
		
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
		olimpo.adicionaVizinhos(provisorioClub);
		olimpo.adicionaVizinhos(raioDeSol);
		provisorioClub.adicionaVizinhos(fundicao);
		provisorioClub.adicionaVizinhos(olimpo);
		provisorioClub.adicionaVizinhos(casteloBonsucesso);
		provisorioClub.adicionaVizinhos(raioDeSol);
		raioDeSol.adicionaVizinhos(olimpo);
		raioDeSol.adicionaVizinhos(provisorioClub);
		raioDeSol.adicionaVizinhos(casteloBonsucesso);
		raioDeSol.adicionaVizinhos(simpatia);
		raioDeSol.adicionaVizinhos(imperator);
		raioDeSol.adicionaVizinhos(openLounge);
		simpatia.adicionaVizinhos(kissEFly);
		simpatia.adicionaVizinhos(imperator);
		simpatia.adicionaVizinhos(raioDeSol);
		imperator.adicionaVizinhos(simpatia);
		imperator.adicionaVizinhos(openLounge);
		imperator.adicionaVizinhos(raioDeSol);
		openLounge.adicionaVizinhos(imperator);
		openLounge.adicionaVizinhos(raioDeSol);
		openLounge.adicionaVizinhos(casteloBonsucesso);
		openLounge.adicionaVizinhos(buxixo);
		casteloBonsucesso.adicionaVizinhos(fundicao);
		casteloBonsucesso.adicionaVizinhos(circo);
		casteloBonsucesso.adicionaVizinhos(openLounge);
		casteloBonsucesso.adicionaVizinhos(raioDeSol);
		casteloBonsucesso.adicionaVizinhos(provisorioClub);
		casteloBonsucesso.adicionaVizinhos(buxixo);
		buxixo.adicionaVizinhos(laIsla);
		buxixo.adicionaVizinhos(openLounge);
		buxixo.adicionaVizinhos(casteloBonsucesso);
		buxixo.adicionaVizinhos(laPassion);
		//EndRegion adicionaVizinhos Zona Norte
		
		//Region adicionaVizinhos Zona Sul				
		zeroZero.adicionaVizinhos(saoNunca);
		zeroZero.adicionaVizinhos(baronetti);
		baronetti.adicionaVizinhos(zeroZero);
		baronetti.adicionaVizinhos(praia);
		baronetti.adicionaVizinhos(mariuzzin);
		praia.adicionaVizinhos(baronetti);
		praia.adicionaVizinhos(mariuzzin);
		praia.adicionaVizinhos(kalabria);
		mariuzzin.adicionaVizinhos(baronetti);
		mariuzzin.adicionaVizinhos(praia);
		mariuzzin.adicionaVizinhos(kalabria);
		mariuzzin.adicionaVizinhos(zozo);
		kalabria.adicionaVizinhos(praia);
		kalabria.adicionaVizinhos(mariuzzin);
		kalabria.adicionaVizinhos(zozo);
		kalabria.adicionaVizinhos(matriz);
		zozo.adicionaVizinhos(kalabria);
		zozo.adicionaVizinhos(mariuzzin);
		matriz.adicionaVizinhos(laPassion);
		matriz.adicionaVizinhos(pista3);
		matriz.adicionaVizinhos(kalabria);
		pista3.adicionaVizinhos(matriz);
		pista3.adicionaVizinhos(laPassion);		
		//EndRegion adicionaVizinhos Zona Sul
		
		//Region adicionaVizinhos Zona Oeste
		barraMusic.adicionaVizinhos(zax);
		barraMusic.adicionaVizinhos(nuth);
		barraMusic.adicionaVizinhos(casteloDasPedras);
		zax.adicionaVizinhos(barraMusic);
		zax.adicionaVizinhos(nuth);
		zax.adicionaVizinhos(platinum);
		platinum.adicionaVizinhos(zeroVinteUm);
		platinum.adicionaVizinhos(nuth);
		platinum.adicionaVizinhos(zax);
		platinum.adicionaVizinhos(saoNunca);
		platinum.adicionaVizinhos(capitonne);
		saoNunca.adicionaVizinhos(zeroZero);
		saoNunca.adicionaVizinhos(laIsla);
		saoNunca.adicionaVizinhos(capitonne);
		saoNunca.adicionaVizinhos(platinum);
		nuth.adicionaVizinhos(barraMusic);
		nuth.adicionaVizinhos(platinum);
		nuth.adicionaVizinhos(casteloDasPedras);
		nuth.adicionaVizinhos(zax);
		nuth.adicionaVizinhos(zeroVinteUm);
		zeroVinteUm.adicionaVizinhos(casteloDasPedras);
		zeroVinteUm.adicionaVizinhos(nuth);
		zeroVinteUm.adicionaVizinhos(zax);
		zeroVinteUm.adicionaVizinhos(platinum);
		zeroVinteUm.adicionaVizinhos(capitonne);
		capitonne.adicionaVizinhos(kissEFly);
		capitonne.adicionaVizinhos(casteloDasPedras);
		capitonne.adicionaVizinhos(zeroVinteUm);
		capitonne.adicionaVizinhos(platinum);
		capitonne.adicionaVizinhos(saoNunca);
		capitonne.adicionaVizinhos(laIsla);
		laIsla.adicionaVizinhos(buxixo);
		laIsla.adicionaVizinhos(capitonne);
		laIsla.adicionaVizinhos(saoNunca);
		casteloDasPedras.adicionaVizinhos(kissEFly);
		casteloDasPedras.adicionaVizinhos(capitonne);
		casteloDasPedras.adicionaVizinhos(zeroVinteUm);
		casteloDasPedras.adicionaVizinhos(nuth);
		casteloDasPedras.adicionaVizinhos(barraMusic);
		kissEFly.adicionaVizinhos(simpatia);
		kissEFly.adicionaVizinhos(casteloDasPedras);
		kissEFly.adicionaVizinhos(capitonne);
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
		mapa.adicionaTerritorio(kalabria);
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
	
	/**
	 * Metodo que calcula a quantidade total de tropas a serem ganhas
	 * @param jogador Jogador que vai ganhar as tropas
	 * @return inteiro que representa todas as tropas que vão ser ganhas
	 */
	public int ganhaTropa(Jogador jogador)	{
		return Math.max(3,jogador.numeroDeConquistados()/2)+ganhaBonusTerritorio(jogador);
	}

	/**
	 * Metodo que calcula a quantidade de tropas a serem ganhas por ter dominio de um ou mais continente
	 * @param jogador que vai ganhar as tropas
	 * @return inteiro que representa as tropas que vão ser ganhas por ter dominio de um ou mais continente
	 */
	private int ganhaBonusTerritorio(Jogador jogador) {
		int quantBoatesPorContinente[]=new int[5];
		int resposta=0,bonusZonaNorte=1,bonusZonaSul=2,bonusZonaOeste=3,bonusCentro=4,bonusNiteroi=5;
		for(Map.Entry<String, Territorio> entry : jogador.getConquistados().entrySet()){
			Territorio boate = entry.getValue();
			if(boate.getContinente()=="Zona Norte"){
				quantBoatesPorContinente[0]++;
			}
			if(boate.getContinente()=="Zona Sul"){
				quantBoatesPorContinente[1]++;
			}
			if(boate.getContinente()=="Zona Oeste"){
				quantBoatesPorContinente[2]++;
			}
			if(boate.getContinente()=="Centro"){
				quantBoatesPorContinente[3]++;
			}
			if(boate.getContinente()=="Niteroi"){
				quantBoatesPorContinente[4]++;
			}
		}
		if(quantBoatesPorContinente[0]==8)
		{
			resposta+=bonusZonaNorte;
		}
		if(quantBoatesPorContinente[1]==8)
		{
			resposta+=bonusZonaSul;
		}
		if(quantBoatesPorContinente[2]==10)
		{
			resposta+=bonusZonaOeste;
		}
		if(quantBoatesPorContinente[3]==5)
		{
			resposta+=bonusCentro;
		}
		if(quantBoatesPorContinente[4]==7)
		{
			resposta+=bonusNiteroi;
		}
		return resposta;
	}
	
	/**
	 * Adiciona um jogador a fila de espera
	 * @param jogador que vai ser adicionado ao final da fila
	 */
	public void adicionaJogador(Jogador jogador){
		jogadores.add(jogador);
	}
	
	/**
	 * Redistribui as tropas entre os paises do jogador.
	 * Os paises tem que ser vizinhos 
	 * So sai uma vez o exercito de um pais 
	 * @param jogador Jogador que redistribuira as tropas
	 */
	public void redistribuiTropa(Jogador jogador){
		Map<String, Territorio> mapa=jogador.getConquistados();
		Set<Territorio> jaUsados = new HashSet<>();
		
		//Função que pega o nome do territorio da visão
		String nomeDoTerritorioOrigem = "";
		Territorio territorioOrigem=mapa.get(nomeDoTerritorioOrigem);
	
		//Função que pega o nome do territorio da visão
		String nomeDoTerritorioDestino = "";
		Territorio territorioDestino=mapa.get(nomeDoTerritorioDestino);
	
		if(territorioOrigem.getVizinhos().contains(territorioDestino)){
			if(!jaUsados.contains(territorioOrigem)){
				//Função que pega a quantidade de tropas da visão
				int quantidadeDeTropas=0;
				if(territorioOrigem.getQuantidadeTropa()>=quantidadeDeTropas+1){
					territorioDestino.setQuantidadeTropa(territorioDestino.getQuantidadeTropa()+quantidadeDeTropas);
					territorioOrigem.setQuantidadeTropa(territorioOrigem.getQuantidadeTropa()-quantidadeDeTropas);
					jaUsados.add(territorioOrigem);
				}else{
					//ERRO:Quantidade de tropas maior que o permitido
				}				
			}else{
				//ERRO:Ja partiu tropas desse territorio
			}
		}else{
			//ERRO:Territorios não são vizinhos
		}
	}
	
	/**
	 *  Metodo responsavel pela administração do jogo
	 */
	public void joga(){
		Jogador jogador = jogadores.poll();
		Map<String, Territorio> mapa=jogador.getConquistados();
		if(faseAtual==TipoFase.FASE_1){
			int quantidadeDeTropas=ganhaTropa(jogador);
			while(quantidadeDeTropas>0){
				//Função que pega o nome do territorio da visão
				String nomeDoTerritorio = "";
				Territorio territorio=mapa.get(nomeDoTerritorio);
				
				if(territorio!=null){
					territorio.setQuantidadeTropa(territorio.getQuantidadeTropa()+1);
					quantidadeDeTropas--;
				}
				else
				{
					//ERRO:Territorio não encontrado
				}
				
				//Atualiza a visão
			}
		}			
		jogadores.add(jogador);
		
	}
	
	/**
	 * Método rensponsável pelos ataques
	 * @param atacante Território de onde se origina o ataque
	 * @param defensor Território sendo atacado
	 */
	public boolean ataque(Territorio atacante, Territorio defensor){
		//Pode atacar
		int qtd_tropas_atacante = Math.min(3, atacante.getQuantidadeTropa());
		int qtd_tropas_defensor = Math.min(3, defensor.getQuantidadeTropa());
		List<Integer>dados_atacante = new LinkedList<>();;
		List<Integer>dados_defensor = new LinkedList<>();
		for(int i = 0; i < qtd_tropas_atacante; i++){
			dados_atacante.add(dado());
		}
		for(int i = 0; i < qtd_tropas_defensor; i++){
			dados_defensor.add(dado());
		}
		Collections.sort(dados_atacante, Collections.reverseOrder());
		Collections.sort(dados_defensor, Collections.reverseOrder());
		
		
		for(int i = 0; i < dados_atacante.size() && i < dados_defensor.size(); i++){
			if(dados_atacante.get(i) > dados_defensor.get(i)){
				defensor.setQuantidadeTropa(defensor.getQuantidadeTropa()-1);
			}else{
				atacante.setQuantidadeTropa(atacante.getQuantidadeTropa()-1);
			}
		}			
		
		
		//Jogador da vez conquistou territorio defensor					
		if(defensor.getQuantidadeTropa() == 0){
			jogadorDominouTerritorio = true;
			return true;						
		}else{
			return false;
		}
	}
	
	/**
	 * Método que passa o território defensor para o dono do território atacante
	 * @param atacante território de onde se originou o ataque que dominou o território
	 * @param defensor território dominado pelo ataque
	 * @param qtd_tropas quantidade de tropas a serem passadas do territorio atacante para o território defensor. Máx. 3 tropas 
	 */
	public void dominarTerritorio(Territorio atacante, Territorio defensor, int qtd_tropas){
		Jogador perdeuT = defensor.getDono();
		Jogador ganhouT = atacante.getDono();
		perdeuT.removeConquistados(defensor);
		ganhouT.adicionaConquistados(defensor);		
		defensor.setQuantidadeTropa(qtd_tropas);
		atacante.setQuantidadeTropa(atacante.getQuantidadeTropa() - qtd_tropas);
		
	}
	
	/**
	 * Método responsável por eliminar um jogador do jogo
	 * @param jogador Jogador eliminado do jogo
	 */
	public void eliminaJogador(Jogador jogador){
		jogadores.remove(jogador);	
	}
	
	/**
	 * Método responsável por passar as cartas de um jogador eliminado para o jogador que o eliminou
	 * @param eliminado Jogador eliminado
	 * @param atacante Jogador que eliminou
	 */
	public void passaCartas(Jogador eliminado, Jogador atacante){
		List<Carta> cartas = eliminado.getMao();
		for(Carta c : cartas){
			atacante.adicionaCarta(c);
			eliminado.removeCarta(c);
		}
	}
}
