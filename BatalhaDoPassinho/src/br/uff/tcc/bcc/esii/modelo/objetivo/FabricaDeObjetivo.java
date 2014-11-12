package br.uff.tcc.bcc.esii.modelo.objetivo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.uff.tcc.bcc.esii.visao.ConstanteDaCor;

public class FabricaDeObjetivo {

	private List<Objetivo> objetivos;
	
	public FabricaDeObjetivo(){
		this.objetivos = new ArrayList<>();
		this.objetivos.add(criaObjetivoConquistar(1, "Zona Sul", 8, "Niteroi", 7, 5));
		this.objetivos.add(criaObjetivoConquistar(2, "Zona Norte", 8, "Centro", 5, 7));
		this.objetivos.add(criaObjetivoConquistar(3, "Zona Oeste", 10, "Centro", 5, 5));
		this.objetivos.add(criaObjetivoConquistar(4, "", 0, "", 0, 20));
		this.objetivos.add(criaObjetivoConquistar(5, "Zona Oeste", 10, "", 0, 9));		
		this.objetivos.add(criaObjetivoEliminar(6, ConstanteDaCor.AZUL));
		this.objetivos.add(criaObjetivoEliminar(7, ConstanteDaCor.AMARELO));
		this.objetivos.add(criaObjetivoEliminar(8, ConstanteDaCor.CINZA));
		this.objetivos.add(criaObjetivoEliminar(9, ConstanteDaCor.ROSA));
		this.objetivos.add(criaObjetivoEliminar(10, ConstanteDaCor.VERDE));
		this.objetivos.add(criaObjetivoEliminar(11, ConstanteDaCor.VERMELHO));
		Collections.shuffle(this.objetivos);
	}	
	
	private ObjetivoConquistar criaObjetivoConquistar(int index, String continente1, int tamContinente1, String continente2, int tamContinente2, int outros){
		ObjetivoConquistar obj = new ObjetivoConquistar(continente1, tamContinente1, continente2, tamContinente2, outros);
		obj.setIndex(index);
		return obj;
		
	}
	private ObjetivoEliminar criaObjetivoEliminar(int index, ConstanteDaCor cor){
		ObjetivoEliminar obj =  new ObjetivoEliminar(cor);
		obj.setIndex(index);
		return obj;
	}
	
	public Objetivo criaObjetivo(int index){
		if(index>=0 && index<=10){
				System.out.println(index);
				return this.objetivos.get(index);
		}		
		return null;		
	}
	public Objetivo carregaObjetivo(int index){
		for (Objetivo objetivo : objetivos) {
			if( objetivo.getIndex()==index)
				return objetivo;
		}
		return  objetivos.get(index);
	}
	
}
