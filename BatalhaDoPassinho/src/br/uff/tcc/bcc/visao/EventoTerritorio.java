package br.uff.tcc.bcc.visao;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EventoTerritorio implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent arg0) {
		Object source = arg0.getSource();
		if (source instanceof Botao) { //should always be true in your example
		    Botao clickedBtn = (Botao) source; // that's the button that was clicked
		    ControladorTelaInicial.getInstancia().acao(clickedBtn);
		}
	}

}
