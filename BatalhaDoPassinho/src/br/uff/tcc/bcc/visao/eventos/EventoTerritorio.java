package br.uff.tcc.bcc.visao.eventos;

import br.uff.tcc.bcc.controlador.ControladorTelaInicial;
import br.uff.tcc.bcc.visao.Botao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
		    ControladorTelaInicial.getInstancia().acao(clickedBtn);
		}
	}

}
