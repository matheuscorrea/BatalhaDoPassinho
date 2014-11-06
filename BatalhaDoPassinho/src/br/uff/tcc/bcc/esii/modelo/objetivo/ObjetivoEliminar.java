package br.uff.tcc.bcc.esii.modelo.objetivo;

import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.visao.ConstanteDaCor;

public class ObjetivoEliminar extends Objetivo {

	ConstanteDaCor cor;
	private int index;
	
	/**
	 * @param corOponente a cor do jogador a ser eliminado
	 */	
	public ObjetivoEliminar(ConstanteDaCor cor){
		this.cor = cor;
	}
	//TODO 
	@Override 
	public boolean concluido(Jogador atacante, Jogador alvo){
		//Se meu objetivo for me eliminar
		if(atacante.getCor().equals(this.cor))
			return new  ObjetivoConquistar(20).concluido(atacante, alvo);		
		if(alvo.getCor().equals(cor)){
			return alvo.getConquistados().size()==1;
		}		
		return false;
	}
	
	@Override
	public String getNomeObjetivo(){
		return "Seu Objetivo é:\nEliminar o jogador de cor "+cor.toString()+"\nOu conquistar 20 territórios.";
	
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
