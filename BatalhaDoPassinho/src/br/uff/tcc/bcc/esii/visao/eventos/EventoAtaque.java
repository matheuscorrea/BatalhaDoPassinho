package br.uff.tcc.bcc.esii.visao.eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas.TipoDaTela;

public class EventoAtaque implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		GerenciadorDeTelas.getInstancia().ataque();		
	}
}