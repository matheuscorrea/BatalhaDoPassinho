package br.uff.tcc.bcc.visao;

import javafx.scene.control.Button;

public class ControladorTelaInicial {
	
	private static ControladorTelaInicial controlador;
	private static int numeroDeCliques;
	
	private ControladorTelaInicial(){
		numeroDeCliques=0;
	}
	
	public static ControladorTelaInicial getInstancia(){
		if (controlador == null){
			controlador = new ControladorTelaInicial();
		}
		return controlador;
	}
	
	    
	public void acao(Button botao){
    	botao.setText("Clicado: "+ ++numeroDeCliques);

	}
}
