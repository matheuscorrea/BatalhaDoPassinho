package br.uff.tcc.bcc.esii.modelo.objetivo;

import br.uff.tcc.bcc.esii.controlador.ControladorJogo;
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
		for (Jogador jogador : ControladorJogo.getInstancia().getJogadores()) {
			//Se Existir Jogador com a cor que deve ser eliminada ele faz a l�gica antiga de conclus�o.
			if(ConstanteDaCor.equalsConstante(this.cor, jogador.getCor())){
				//Se meu objetivo for me eliminar
				if(atacante.getCor().equals(this.cor))
					return new  ObjetivoConquistar(20).concluido(atacante, alvo);		
				if(alvo.getCor().equals(cor)){
					return alvo.getConquistados().size()==1;
				}
				return false;
			}
		}
		return new ObjetivoConquistar(20).concluido(atacante, alvo);
	}
	
	@Override
	public String getNomeObjetivo(){
		return "Seu Objetivo �:\nEliminar o jogador de cor "+cor.toString()+"\nOu conquistar 20 territ�rios.";
	
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
