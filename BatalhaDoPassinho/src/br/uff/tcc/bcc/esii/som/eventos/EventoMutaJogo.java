package br.uff.tcc.bcc.esii.som.eventos;

import br.uff.tcc.bcc.esii.controlador.ControladorJogo;
import br.uff.tcc.bcc.esii.som.Som;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EventoMutaJogo implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent arg0) {
		Som.getInstancia().mutaVolume();
		
	}

}
