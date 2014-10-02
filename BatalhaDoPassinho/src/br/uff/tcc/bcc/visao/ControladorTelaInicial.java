package br.uff.tcc.bcc.visao;

import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * Controlador respons�vel por realizar o interm�dio entre o modelo e a vis�o.<P>
 * � um Singleton
 *
 */
public class ControladorTelaInicial implements Controlador {
	
	private static ControladorTelaInicial controlador;
	private static int numeroDeCliques;
	
	private ControladorTelaInicial(){
		numeroDeCliques=0;
	}
	
	public static Controlador getInstancia(){
		if (controlador == null){
			controlador = new ControladorTelaInicial();
		}
		return controlador;
	}
	
	/**
	 * Recebe um bot�o e identifica qual a��o tomar quando este for apertado
	 * @param botao
	 */
	public void acao(Botao botao){
    	if(botao.getTipoBotao().equals(TipoBotao.OPCOES)){
    		botao.setText("Clicado: "+ ++numeroDeCliques);
    	}
    	if(botao.getTipoBotao().equals(TipoBotao.NOVO_JOGO)){
    		
    	}
	}
	
	public void iniciaJogo(){
		
	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return null;
	}
}
