package br.uff.tcc.bcc.esii.modelo;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import br.uff.tcc.bcc.visao.ConstanteDoTerritorio;

/**
 * @author Thadeu Jose
 * 
 */
public class Jogo {
	/**
	 * Numero da rodada atual
	 */
	private int rodada;
	/**
	 * Jogadores que estao atualmente jogando
	 */
	private Queue<Jogador> jogadores;
	/**
	 * Valor da troca de cartas
	 */
	private int valorTrocaAtual;
	/**
	 * Se o jogador dominou territorio essa rodada
	 */
	private boolean jogadorDominouTerritorio;
	/**
	 *Quantidade de tropas ganhas nesse turno 
	 */
	private int quantidadeDeTropas;
	/**
	 * Mapa com todos os territorios do jogo
	 */
	private Mapa mapa;
	/**
	 * Deck das carta do jogo
	 */
	private List<Carta> deck;
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
		inicializaMapa();
	}

	/**
	 * Inicia uma nova rodada
	 */
	public void proximaRodada()
	{
		rodada++;
		faseAtual = TipoFase.FASE_1;
		jogadorDominouTerritorio=false;
		Jogador jogador = jogadores.poll();
		calculaTropa(jogador);
		jogadores.add(jogador);
		
	}
	
	public void proximaFase(){
		if(faseAtual.equals(TipoFase.FASE_1)){
			faseAtual = TipoFase.FASE_2;
		}else if(faseAtual.equals(TipoFase.FASE_2)){
			faseAtual = TipoFase.FASE_3;
		}else if(faseAtual.equals(TipoFase.FASE_3)){
			proximaRodada();
		}
			
	}
	
	
	/**
	 * Metodo que simula o lançamento de um dado
	 * @return um inteiro de 1 a 6 
	 */
	public int dado(){
		Random random = new Random();
		return random.nextInt(6)+1; 
	}
	
	public Mapa getMapa()
	{
		return mapa;
	}
	
	/**
	 * Metodo que calcula a quantidade total de tropas a serem ganhas
	 * @param jogador Jogador que vai ganhar as tropas
	 * @return inteiro que representa todas as tropas que vão ser ganhas
	 */
	private int ganhaTropa(Jogador jogador)	{
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
	
	public Jogador getJogadorDaVez(){
		return jogadores.peek();
	}
	
	/**
	 * Redistribui as tropas entre os paises do jogador.
	 * Os paises tem que ser vizinhos 	 
	 * (Supondo A,B,C) Se mover de A para B, não pode mais mover de B para ninguém. Ainda pode mover de C para A ou de A para C.
	 * Considerando que os dois territorios passados são vizinhos. 
	 * Considerando que o territorio destino ainda não recebeu tropas de outros territorios.
	 * Considerando que a quantidade de tropas a serem movidas é menor que o total de tropas no territorio origem.
	 * @param jogador Jogador que redistribuira as tropas
	 * @param origem Territorio de onde sairão as tropas, pego pelo controlador
	 * @param destino Territorio para onde irão as tropas, pego pelo controlador
	 * @param quantidadeDeTropas quantas tropas serão movidas, deve ser pego pelo controlador.
	 */
	public void redistribuiTropa(Jogador jogador, Territorio origem, Territorio destino, int quantidadeDeTropas){			
	
		destino.setQuantidadeTropa(destino.getQuantidadeTropa()+quantidadeDeTropas);
		origem.setQuantidadeTropa(origem.getQuantidadeTropa()-quantidadeDeTropas);						
			
		
	}
	
	/**
	 * Metodo responsavel pela distribuição das tropas
	 * @param nometerritorio  Nome do Territorio que vai ser acrescentado as tropas
	 * @param tropas          Numero de tropas que serão acrescentados
	 */
	public void distribuirTropas(String nomeTerritorio, int tropas){
		Territorio territorio = mapa.getTerritorio(nomeTerritorio);
		territorio.setQuantidadeTropa(territorio.getQuantidadeTropa()+tropas);		
	}
	
	public int getTropas(String nomeTerritorio) {
		Territorio territorio = mapa.getTerritorio(nomeTerritorio);
		return territorio.getQuantidadeTropa();
	}
	
	
	/**
	 * Método rensponsável pelos ataques
	 * @param atacante Território de onde se origina o ataque
	 * @param defensor Território sendo atacado
	 * @return True se o territorio atacante conseguiu destruir todas as tropas do territorio atacante,falso senão.
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
	/**
	 * Metodo que calcula quantidade de tropas que o jogador da vez vai ganhar
	 * @param jogador Jogador da vez
	 */
	public void calculaTropa(Jogador jogador){
		quantidadeDeTropas=ganhaTropa(jogador);
	}
	/**
	 * Metodo que verifica se o jogador ainda tem tropas para distribuir
	 * @return true se o jogador ainda tem tropas para distribuir e false se não
	 */
	public boolean temTropas(){
		return (quantidadeDeTropas>0);
	}
	/**
	 * Metodo que decrementa o numero de tropas para distribuir
	 */
	public void decrementaTropas(){
		quantidadeDeTropas--;
	}
	/**
	 * Metodo que retorna a quantidade de tropas
	 * @return quantidades de tropas que o jogador tem para distribuir
	 */
	public int getQuantidadeDeTropas(){
		return quantidadeDeTropas;
	}
	
	public void distribuiTerritorio(){
		Object[] jogador = jogadores.toArray();
		Random random = new Random();
		for(Territorio t:mapa.territorios.values()){
			int numSorteado = random.nextInt(jogadores.size());
			t.setDono((Jogador)jogador[numSorteado]);
			System.out.println(numSorteado+" "+((Jogador)jogador[numSorteado]).getNome());
		}
	}
	
	/**
	 * Inicializa o mapa.
	 */
	public void inicializaMapa() {
		//Region Territorio Niteroi 
		Territorio viradouro = new Territorio(ConstanteDoTerritorio.VIRADOURO.toString(), "Niteroi");
		Territorio waxy = new Territorio(ConstanteDoTerritorio.WAXY.toString(), "Niteroi");
		Territorio touche = new Territorio(ConstanteDoTerritorio.TOUCHE.toString(), "Niteroi");
		Territorio saoFirmino = new Territorio(ConstanteDoTerritorio.SAO_FIRMINO.toString(), "Niteroi");
		Territorio casa = new Territorio(ConstanteDoTerritorio.CASA.toString(), "Niteroi");
		Territorio flamboyant = new Territorio(ConstanteDoTerritorio.FLAMBOYANT.toString(), "Niteroi");
		Territorio woods = new Territorio(ConstanteDoTerritorio.WOODS.toString(), "Niteroi");
		//EndRegion Territorio Niteroi
		
		//Region Territorio Centro
		Territorio fundicao = new Territorio(ConstanteDoTerritorio.FUNDICAO.toString(), "Centro");
		Territorio circo = new Territorio(ConstanteDoTerritorio.CIRCO.toString(), "Centro");
		Territorio six = new Territorio(ConstanteDoTerritorio.SIX.toString(), "Centro");
		Territorio laPaz = new Territorio(ConstanteDoTerritorio.LA_PAZ.toString(), "Centro");
		Territorio laPassion = new Territorio(ConstanteDoTerritorio.LA_PASSION.toString(), "Centro");
		//EndRegion Territorio Centro
		
		//Region Territorio Zona Norte
		Territorio olimpo = new Territorio(ConstanteDoTerritorio.OLIMPO.toString(), "Zona Norte");
		Territorio provisorioClub = new Territorio(ConstanteDoTerritorio.PROVISORIO_CLUB.toString(), "Zona Norte");
		Territorio casteloBonsucesso = new Territorio(ConstanteDoTerritorio.CASTELO_BONSUCESSO.toString(), "Zona Norte");
		Territorio raioDeSol = new Territorio(ConstanteDoTerritorio.RAIO_DE_SOL.toString(), "Zona Norte");
		Territorio simpatia = new Territorio(ConstanteDoTerritorio.SIMPATIA.toString(), "Zona Norte");
		Territorio imperator = new Territorio(ConstanteDoTerritorio.IMPERATOR.toString(), "Zona Norte");
		Territorio openLounge = new Territorio(ConstanteDoTerritorio.OPEN_LOUNGE.toString(), "Zona Norte");
		Territorio buxixo = new Territorio(ConstanteDoTerritorio.BUXIXO.toString(), "Zona Norte");
		//EndRegion Territorio Zona Norte
		
		//Region Territorio Zona Sul
		Territorio pista3 = new Territorio(ConstanteDoTerritorio.PISTA_3.toString(), "Zona Sul");
		Territorio matriz = new Territorio(ConstanteDoTerritorio.MATRIZ.toString(), "Zona Sul");
		Territorio kalabria = new Territorio(ConstanteDoTerritorio.KALABRIA.toString(), "Zona Sul");
		Territorio zozo = new Territorio(ConstanteDoTerritorio.ZOZO.toString(), "Zona Sul");
		Territorio praia = new Territorio(ConstanteDoTerritorio.PRAIA.toString(), "Zona Sul");
		Territorio mariuzzin = new Territorio(ConstanteDoTerritorio.MARIUZZIN.toString(), "Zona Sul");
		Territorio baronetti = new Territorio(ConstanteDoTerritorio.BARONETTI.toString(), "Zona Sul");
		Territorio zeroZero = new Territorio(ConstanteDoTerritorio.ZEROZERO.toString(), "Zona Sul");
		//EndRegion Territorio Zona Sul
		
		//Region Territorio Zona Oeste
		Territorio saoNunca = new Territorio(ConstanteDoTerritorio.SAO_NUNCA.toString(), "Zona Oeste");
		Territorio laIsla = new Territorio(ConstanteDoTerritorio.LA_ISLA.toString(), "Zona Oeste");
		Territorio capitonne = new Territorio(ConstanteDoTerritorio.CAPITONNE.toString(), "Zona Oeste");
		Territorio kissEFly = new Territorio(ConstanteDoTerritorio.KISS_E_FLY.toString(), "Zona Oeste");
		Territorio casteloDasPedras = new Territorio(ConstanteDoTerritorio.CASTELO_DAS_PEDRAS.toString(), "Zona Oeste");
		Territorio zeroVinteUm = new Territorio(ConstanteDoTerritorio.ZEROVINTEUM.toString(), "Zona Oeste");
		Territorio platinum = new Territorio(ConstanteDoTerritorio.PLATINUM.toString(), "Zona Oeste");
		Territorio nuth = new Territorio(ConstanteDoTerritorio.NUTH.toString(), "Zona Oeste");
		Territorio zax = new Territorio(ConstanteDoTerritorio.ZAX.toString(), "Zona Oeste");
		Territorio barraMusic = new Territorio(ConstanteDoTerritorio.BARRA_MUSIC.toString(), "Zona Oeste");
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

}
