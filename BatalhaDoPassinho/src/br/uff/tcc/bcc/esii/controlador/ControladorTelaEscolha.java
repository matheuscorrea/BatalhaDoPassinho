package br.uff.tcc.bcc.esii.controlador;

import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas;
import javafx.scene.control.Button;

public class ControladorTelaEscolha {

	private static ControladorTelaEscolha controlador;
	
	private ControladorTelaEscolha(){
	}
	
	public static ControladorTelaEscolha getInstancia(){
		if (controlador == null){
			controlador = new ControladorTelaEscolha();
		}
		return controlador;
	}
	
	/**
	 * Recebe um botão e identifica qual ação tomar quando este for apertado
	 * @param botao
	 */
	public void acao(String jogador,int index){
		GerenciadorDeTelas.getInstancia().atualizaPersonagem(jogador, index);
	}
	
}