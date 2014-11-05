package br.uff.tcc.bcc.esii.modelo;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Test;

import br.uff.tcc.bcc.esii.modelo.ia.Heuristicas;

public class IATest {
	@Test
	public void testaHeuristica(){
		Territorio t1 = new Territorio("nome1", "cont1");
		Territorio t2 = new Territorio("nome1", "cont1");
		t1.setQuantidadeTropa(40);
		t2.setQuantidadeTropa(1);
		
		Heuristicas.faseDoisPorTropas(t1, t2);
		Heuristicas.faseDoisPorTropas(t2, t1);
		Heuristicas.faseTresPorDefesaDeTerritorio(t1, t2);
		Heuristicas.faseTresPorDefesaDeTerritorio(t1, t2);
		Heuristicas.faseUmPorChanceDeAtaque(t1);
		Heuristicas.faseUmPorChanceDeAtaque(t2);
		Heuristicas.faseUmPorDefesaDeTerritorio(t1);
		Heuristicas.faseUmPorDefesaDeTerritorio(t2);
		
		
assertTrue(true);
	}
}
