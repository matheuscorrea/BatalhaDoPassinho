package br.uff.tcc.bcc.esii.modelo.objetivo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.uff.tcc.bcc.esii.visao.ConstanteDaCor;

public class FabricaDeObjetivo {

	private List<IObjetivo> objetivos;
	
	public FabricaDeObjetivo(){
		this.objetivos = new ArrayList<>();
		this.objetivos.add(criaObjetivoConquistar("Zona Sul", 8, "Niteroi", 7, 5));
		this.objetivos.add(criaObjetivoConquistar("Zona Norte", 8, "Centro", 5, 7));
		this.objetivos.add(criaObjetivoConquistar("Zona Oeste", 10, "Centro", 5, 5));
		this.objetivos.add(criaObjetivoConquistar("", 0, "", 0, 20));
		this.objetivos.add(criaObjetivoConquistar("Zona Oeste", 10, "", 0, 9));		
		this.objetivos.add(criaObjetivoEliminar(ConstanteDaCor.AZUL));
		this.objetivos.add(criaObjetivoEliminar(ConstanteDaCor.AMARELO));
		this.objetivos.add(criaObjetivoEliminar(ConstanteDaCor.CINZA));
		this.objetivos.add(criaObjetivoEliminar(ConstanteDaCor.ROSA));
		this.objetivos.add(criaObjetivoEliminar(ConstanteDaCor.VERDE));
		this.objetivos.add(criaObjetivoEliminar(ConstanteDaCor.VERMELHO));
		Collections.shuffle(this.objetivos);
	}	
	
	private ObjetivoConquistar criaObjetivoConquistar(String continente1, int tamContinente1, String continente2, int tamContinente2, int outros){
		return new ObjetivoConquistar(continente1, tamContinente1, continente2, tamContinente2, outros);
		
	}
	private ObjetivoEliminar criaObjetivoEliminar(ConstanteDaCor cor){
		return new ObjetivoEliminar(cor);
	}
	
	public IObjetivo criaObjetivo(int index){
		return this.objetivos.get(index);
	}
}
