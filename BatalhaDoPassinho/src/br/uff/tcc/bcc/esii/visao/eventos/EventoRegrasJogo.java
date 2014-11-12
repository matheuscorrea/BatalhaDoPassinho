package br.uff.tcc.bcc.esii.visao.eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import br.uff.tcc.bcc.esii.controlador.ControladorJogo;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas.TipoDaTela;

public class EventoRegrasJogo implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.REGRASJOGO);
	}

}
