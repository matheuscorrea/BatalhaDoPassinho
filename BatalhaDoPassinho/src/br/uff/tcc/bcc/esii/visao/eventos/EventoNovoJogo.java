package br.uff.tcc.bcc.esii.visao.eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import br.uff.tcc.bcc.esii.controlador.ControladorJogo;

/**
 * Evento que descreve as a��es do bot�o de Novo Jogo
 * 
 *
 */
public class EventoNovoJogo implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent arg0) {
		ControladorJogo.getInstancia().iniciaPartida();
	}
}
