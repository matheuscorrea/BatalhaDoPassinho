package br.uff.tcc.bcc.visao;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * F�brica respons�vel por criar objetos do tipo  {@link Botao}
 *
 */
public class FabricaDeBotoes {
	
	/**
	 * M�todo que retorna uma inst�ncia de Botao de acordo com os par�metros passados
	 * @param id 
	 * @param texto
	 * @param acao
	 * @return
	 */
	public static Botao criaBotao( String id, String texto, EventHandler<ActionEvent> acao){
		return new Botao(id,texto,acao);
	}
	
	/**
	 * M�todo que retorna uma inst�ncia de Botao de acordo com os par�metros passados
	 * @param id 
	 * @param texto
	 * @param acao
	 * @return
	 */
	public static Botao criaBotaoTerritorio( String id, String texto, EventHandler<ActionEvent> acao, TipoDoTerritorio territorio, Integer coordenadaX, Integer coordenadaY){
		Botao botao = new Botao(id,texto,acao);
		botao.setMaxHeight(20);
		botao.setMaxWidth(30);
		botao.setLayoutX(coordenadaX);
		botao.setLayoutY(coordenadaY);
		return botao;
	}
	
	
}