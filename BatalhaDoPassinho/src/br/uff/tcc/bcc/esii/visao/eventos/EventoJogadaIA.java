package br.uff.tcc.bcc.esii.visao.eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import br.uff.tcc.bcc.esii.controlador.ControladorJogo;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas;

public class EventoJogadaIA implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		ControladorJogo.getInstancia().jogadaIA();
	}
}
