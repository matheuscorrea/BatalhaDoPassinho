package br.uff.tcc.bcc.esii.modelo.objetivo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.esii.visao.ConstanteDaCor;

public class ObjetivoConquistarTest {

	Jogador jogador;
	String C1,C2;
	
	@Before
	public void inicia(){
		C1="C1";
		C2="C2";
		jogador= new Jogador("Teste", ConstanteDaCor.CINZA);
		jogador.adicionaConquistados(new Territorio("T1", C1));
		jogador.adicionaConquistados(new Territorio("T2", C1));
		jogador.adicionaConquistados(new Territorio("T3", C1));
		jogador.adicionaConquistados(new Territorio("T4", C1));
	
		jogador.adicionaConquistados(new Territorio("T5", C2));
		jogador.adicionaConquistados(new Territorio("T6", C2));
		jogador.adicionaConquistados(new Territorio("T7", C2));
		jogador.adicionaConquistados(new Territorio("T8", C2));
	}
	
	/**
	 * 
	 */
	@Test
	public void testOutro() { 
		ObjetivoConquistar objetivoConquistar = new ObjetivoConquistar(8);
		assertTrue(objetivoConquistar.concluido(jogador, null));
	}
	
	/**
	 * Testa se o jogador dominou 4 territorios de C1 e 4 de C2 
	 */
	@Test
	public void testCaso1() { 
		ObjetivoConquistar objetivoConquistar = new ObjetivoConquistar(C1,4,C2,4,0);
		assertTrue(objetivoConquistar.concluido(jogador, null));
	}
	
	/**
	 * Testa se o objetivo esta sendo calculado corretamente 
	 */
	@Test
	public void testCaso2() { 
		ObjetivoConquistar objetivoConquistar = new ObjetivoConquistar(C1,4,C2,5,0);
		assertFalse(objetivoConquistar.concluido(jogador, null));
	}

	
	/**
	 * Testa se o objetivo está contando os outros como um territorio pertencente a algum continente 
	 */
	@Test
	public void testCaso3() { 
		ObjetivoConquistar objetivoConquistar = new ObjetivoConquistar(C1,4,C2,4,1);
		assertFalse(objetivoConquistar.concluido(jogador, null));
	}

	
}
