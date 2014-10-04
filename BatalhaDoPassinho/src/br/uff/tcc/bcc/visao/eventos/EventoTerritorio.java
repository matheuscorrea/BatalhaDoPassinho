package br.uff.tcc.bcc.visao.eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import br.uff.tcc.bcc.controlador.ControladorJogo;
import br.uff.tcc.bcc.visao.Botao;

/**
 * Evento que descreve as ações do botão de Novo Jogo
 *
 */
public class EventoTerritorio implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent arg0) {
		Object source = arg0.getSource();
		if (source instanceof Botao) { //should always be true in your example
		    Botao clickedBtn = (Botao) source; // that's the button that was clicked
		    ControladorJogo.getInstancia().acao(clickedBtn);
		}
	}

}
