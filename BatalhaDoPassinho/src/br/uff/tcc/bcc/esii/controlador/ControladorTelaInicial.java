package br.uff.tcc.bcc.esii.controlador;

import javafx.scene.control.Button;

/**
 * Controlador respons�vel por realizar o interm�dio entre o modelo e a vis�o.<P>
 * � um Singleton
 *
 */
public class ControladorTelaInicial  {
	
	private static ControladorTelaInicial controlador;
	
	private ControladorTelaInicial(){
		
	}
	
	public static ControladorTelaInicial getInstancia(){
		if (controlador == null){
			controlador = new ControladorTelaInicial();
		}
		return controlador;
	}
	
	/**
	 * Recebe um bot�o e identifica qual a��o tomar quando este for apertado
	 * @param botao
	 */
	public void acao(Button botao){
    	if(botao.getId().equals("NOVO_JOGO")){
    		iniciaJogo();
    	}
	}
	
	private void iniciaJogo(){
		
	}
}
