package br.uff.tcc.bcc.visao;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Fábrica responsável por criar objetos do tipo  {@link Botao}
 *
 */
public class FabricaDeBotoes {
	
	/**
	 * Método que retorna uma instância de Botao de acordo com os parâmetros passados
	 * @param id 
	 * @param texto
	 * @param acao
	 * @return
	 */
	public static Botao criaBotao( String id, String texto, EventHandler<ActionEvent> acao){
		return new Botao(id,texto,acao);
	}
	
	/**
	 * Método que retorna uma instância de Botao de acordo com os parâmetros passados
	 * @param id 
	 * @param texto
	 * @param acao
	 * @return
	 */
	public static Botao criaBotaoTerritorio( String id, String texto, EventHandler<ActionEvent> acao, TerritorioBotao territorio){
		return new Botao(id,texto,acao);
	}
	
	
}
