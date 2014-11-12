package br.uff.tcc.bcc.esii.visao.eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import br.uff.tcc.bcc.esii.controlador.ControladorJogo;

public class EventoSair implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
	    ControladorJogo.getInstancia().acaoSair();
	}
}