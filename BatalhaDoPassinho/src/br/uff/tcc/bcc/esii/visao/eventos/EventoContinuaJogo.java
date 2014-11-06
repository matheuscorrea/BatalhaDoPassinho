package br.uff.tcc.bcc.esii.visao.eventos;

import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EventoContinuaJogo implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		GerenciadorDeTelas.getInstancia().continuaJogo();
	}
}
