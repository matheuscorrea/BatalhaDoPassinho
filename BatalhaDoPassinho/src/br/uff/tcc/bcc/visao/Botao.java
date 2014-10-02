package br.uff.tcc.bcc.visao;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Classe que representa os botões da tela inicial
 * 
 *
 */
public class Botao extends Button{
	
	/**
	 * Atributo que indica o tipo do botão
	 */
	private TipoBotao tipoBotao;
	
	/**
	 * Construtor da classe que determina a ação do botão ao ser clicado
	 * e suas configurações
	 * @param texto
	 */
	public Botao(TipoBotao tipoBotao, String texto){
		
		Botao instancia = this;
		this.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent arg0) {
				ControladorTelaInicial.getInstancia().acao(instancia);
			}
		}
		);
		this.setText(texto);
		this.tipoBotao = tipoBotao;
	}
	
	public TipoBotao getTipoBotao(){
		return tipoBotao;
	}
}
