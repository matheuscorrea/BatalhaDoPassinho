package br.uff.tcc.bcc.esii.modelo.objetivo;

import br.uff.tcc.bcc.esii.controlador.ControladorJogo;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Jogo;
import br.uff.tcc.bcc.esii.visao.ConstanteDaCor;

public class ObjetivoEliminar implements IObjetivo {

	ConstanteDaCor cor;
	
	/**
	 * @param corOponente a cor do jogador a ser eliminado
	 */	
	public ObjetivoEliminar(ConstanteDaCor cor){
		this.cor = cor;
	}
	
	@Override 
	public boolean concluido(Jogador jogador){
		// TODO Auto-generated method stub
		//if(jogador.getCor().equals(cor))
		
		
		return false;
	}

}
