package br.uff.tcc.bcc.visao;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class FabricaDeBotoes {
	public static Botao criaBotao( String id, String texto, EventHandler<ActionEvent> acao){
		return new Botao(TipoBotao.NOVO_JOGO,id,texto,acao);
	}
}
