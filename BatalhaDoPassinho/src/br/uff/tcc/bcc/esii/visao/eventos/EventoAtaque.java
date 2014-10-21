package br.uff.tcc.bcc.esii.visao.eventos;

import br.uff.tcc.bcc.esii.controlador.ControladorJogo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class EventoAtaque implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		Object source = event.getSource();
		if (source instanceof Button) { //should always be true in your example
		    Button clickedBtn = (Button) source; // that's the button that was clicked
		    ControladorJogo.getInstancia().acaoAtaque(clickedBtn);
		}
	}

}
