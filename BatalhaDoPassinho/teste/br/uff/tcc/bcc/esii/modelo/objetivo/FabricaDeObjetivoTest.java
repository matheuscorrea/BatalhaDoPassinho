package br.uff.tcc.bcc.esii.modelo.objetivo;

import static org.junit.Assert.*;

import org.junit.Test;

public class FabricaDeObjetivoTest {

	@Test
	public void testCriaObjetivoQuant() {
		FabricaDeObjetivo teste = new FabricaDeObjetivo();
		for(int i=0;i<1000;i++)
			teste.criaObjetivo(i);
	}

	@Test
	public void testCriaObjetivoLimiteSuperior() {
		FabricaDeObjetivo teste = new FabricaDeObjetivo();
		int i=10;
		assertNotEquals(teste.criaObjetivo(i).getNomeObjetivo(),null);
	}
	
	@Test
	public void testCriaObjetivoLimiteInferior() {
		FabricaDeObjetivo teste = new FabricaDeObjetivo();
		int i=0;
		assertNotEquals(teste.criaObjetivo(i).getNomeObjetivo(),null);	
	}
	
	/**
	 * Testa se o mesmo index dado para duas fabricas diferentes geram o mesmo objetivo
	 * Isso não pode acontecer 
	 */
	@Test
	public void testCriaObjetivoIgual() {
		FabricaDeObjetivo teste = new FabricaDeObjetivo();
		FabricaDeObjetivo teste2 = new FabricaDeObjetivo();
		for (int i = 0; i < 10; i++) {
			assertNotEquals(teste.criaObjetivo(i), teste2.criaObjetivo(i));
		}
		
	}
	
	//TODO Fazer mesmos teste para o carrega

}
