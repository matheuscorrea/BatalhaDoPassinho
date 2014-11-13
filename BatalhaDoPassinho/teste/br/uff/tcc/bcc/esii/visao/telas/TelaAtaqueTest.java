package br.uff.tcc.bcc.esii.visao.telas;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.uff.tcc.bcc.esii.modelo.Territorio;

public class TelaAtaqueTest {

	Territorio atacante, defensor;
	List<Integer> dados_atacante, dados_defensor;
	
	@Before 
	public void inicia(){
		dados_atacante=new ArrayList<Integer>();
		dados_defensor=new ArrayList<Integer>();
		
		atacante = new Territorio("Teste1", "ContTeste1");
		defensor = new Territorio("Teste2", "ContTeste2");
		
		
	}
	
	
	
	/**
	 * Testa se o ataque funciona so com um dado da defesa
	 */
	@Test
	public void testAtaqueCaso1() {
		atacante.setQuantidadeTropa(1);
		defensor.setQuantidadeTropa(1);
				
		dados_defensor.add(1);
		
		TelaAtaque tela= new TelaAtaque(atacante, defensor);
		tela.ataque(atacante, defensor, dados_atacante, dados_defensor);
		assertEquals(1,defensor.getQuantidadeTropa());
	}

	
	/**
	 * Ataque válido
	 */
	@Test
	public void testAtaqueCaso2() {
		atacante.setQuantidadeTropa(2);
		defensor.setQuantidadeTropa(1);
				
		dados_atacante.add(2);
		
		dados_defensor.add(1);
		
		TelaAtaque tela= new TelaAtaque(atacante, defensor);
		tela.ataque(atacante, defensor, dados_atacante, dados_defensor);
		assertEquals(0,defensor.getQuantidadeTropa());
	}
	
	/**
	 * Testa se o ataque é válido e se o atacante fica com uma so tropa 
	 */
	@Test
	public void testAtaqueCaso3() {
		atacante.setQuantidadeTropa(4);
		defensor.setQuantidadeTropa(3);
				
		dados_atacante.add(3);
		dados_atacante.add(2);
		dados_atacante.add(1);
		
		dados_defensor.add(3);
		dados_defensor.add(2);
		dados_defensor.add(1);
		
		TelaAtaque tela= new TelaAtaque(atacante, defensor);
		tela.ataque(atacante, defensor, dados_atacante, dados_defensor);
		assertEquals(1,atacante.getQuantidadeTropa());
	}

	/**
	 * Testa se o ataque é válido e se o defensor fica com todas as suas tropas
	 */
	@Test
	public void testAtaqueCaso4() {
		atacante.setQuantidadeTropa(4);
		defensor.setQuantidadeTropa(3);
				
		dados_atacante.add(3);
		dados_atacante.add(2);
		dados_atacante.add(1);
		
		dados_defensor.add(3);
		dados_defensor.add(2);
		dados_defensor.add(1);
		
		TelaAtaque tela= new TelaAtaque(atacante, defensor);
		tela.ataque(atacante, defensor, dados_atacante, dados_defensor);
		assertEquals(3,defensor.getQuantidadeTropa());
	}
	
	/**
	 * Testa se o atacante domina um territorio
	 */
	@Test
	public void testAtaqueCaso5() {
		atacante.setQuantidadeTropa(4);
		defensor.setQuantidadeTropa(3);
				
		dados_atacante.add(4);
		dados_atacante.add(4);
		dados_atacante.add(4);
		
		dados_defensor.add(3);
		dados_defensor.add(3);
		dados_defensor.add(3);
		
		TelaAtaque tela= new TelaAtaque(atacante, defensor);
		assertTrue(tela.ataque(atacante, defensor, dados_atacante, dados_defensor));
		
	}
	
	/**
	 * Testa se o ataque deixa com tropas negativas no defensor
	 */
	@Test
	public void testAtaqueCaso6() {
		atacante.setQuantidadeTropa(4);
		defensor.setQuantidadeTropa(1);
				
		dados_atacante.add(6);
		dados_atacante.add(6);
		dados_atacante.add(6);
		
		dados_defensor.add(1);
		
		TelaAtaque tela= new TelaAtaque(atacante, defensor);
		tela.ataque(atacante, defensor, dados_atacante, dados_defensor);
		assertEquals(0, defensor.getQuantidadeTropa());
	}
	
}
