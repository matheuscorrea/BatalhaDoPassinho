package br.uff.tcc.bcc.visao.eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import br.uff.tcc.bcc.controlador.ControladorJogo;

/**
 * Evento que descreve as ações do botão de Novo Jogo
 *
 */
public class EventoTerritorio implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent event) {
		Object source = event.getSource();
		if (source instanceof Button) { //should always be true in your example
		    Button clickedBtn = (Button) source; // that's the button that was clicked
		    ControladorJogo.getInstancia().acaoTerritorio(clickedBtn);
		}
	}

}
