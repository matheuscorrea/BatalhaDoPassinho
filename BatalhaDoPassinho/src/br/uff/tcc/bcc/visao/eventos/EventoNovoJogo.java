package br.uff.tcc.bcc.visao.eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import br.uff.tcc.bcc.controlador.ControladorJogo;

/**
 * Evento que descreve as ações do botão de Novo Jogo
 * 
 *
 */
public class EventoNovoJogo implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent arg0) {
		ControladorJogo.getInstancia().iniciaPartida();
	}
}
