package br.uff.tcc.bcc.visao.eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import br.uff.tcc.bcc.visao.GerenciadorDeTelas;
import br.uff.tcc.bcc.visao.GerenciadorDeTelas.TipoDaTela;

public class EventoOpcoes implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent arg0) {
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.opcoes);

	}
}
