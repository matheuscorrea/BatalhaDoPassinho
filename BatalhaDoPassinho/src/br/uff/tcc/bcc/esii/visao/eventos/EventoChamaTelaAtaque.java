package br.uff.tcc.bcc.esii.visao.eventos;

import br.uff.tcc.bcc.esii.controlador.ControladorJogo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class EventoChamaTelaAtaque implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
	    ControladorJogo.getInstancia().acaoAtaque();
	}
}
