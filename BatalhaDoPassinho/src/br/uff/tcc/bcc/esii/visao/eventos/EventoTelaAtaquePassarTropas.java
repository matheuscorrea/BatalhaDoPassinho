package br.uff.tcc.bcc.esii.visao.eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import br.uff.tcc.bcc.esii.controlador.ControladorJogo;
import br.uff.tcc.bcc.esii.visao.telas.TelaAtaque;

public class EventoTelaAtaquePassarTropas implements EventHandler<ActionEvent> {
	
	public void handle(ActionEvent event) {
		Object source = event.getSource();
		if(source instanceof Button){
			Button btPressionado = (Button) source;
			switch (btPressionado.getId()) {
			case "1":
				ControladorJogo.getInstancia().dominarTerritorio(1);
				break;
			case "2":
				ControladorJogo.getInstancia().dominarTerritorio(2);
				break;
				
			case "3":
				ControladorJogo.getInstancia().dominarTerritorio(3);
				break;
			}
		}
			
		 EventHandler<ActionEvent> eventoEmulado = new EventoTelaJogo();
		 eventoEmulado.handle(null);
	}
}