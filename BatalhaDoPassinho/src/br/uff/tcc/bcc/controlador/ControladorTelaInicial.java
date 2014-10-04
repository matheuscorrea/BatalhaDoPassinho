package br.uff.tcc.bcc.controlador;

import br.uff.tcc.bcc.visao.Botao;
import br.uff.tcc.bcc.visao.TerritorioBotao;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * Controlador responsável por realizar o intermédio entre o modelo e a visão.<P>
 * É um Singleton
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
	 * Recebe um botão e identifica qual ação tomar quando este for apertado
	 * @param botao
	 */
	public void acao(Botao botao){
    	if(botao.getTipoBotao().equals(TerritorioBotao.OPCOES)){
    		botao.setText("Clicado: "+ ++numeroDeCliques);
    	}
    	if(botao.getTipoBotao().equals(TerritorioBotao.NOVO_JOGO)){
    		
    	}
	}
	
	public void iniciaJogo(){
		
	}
}
