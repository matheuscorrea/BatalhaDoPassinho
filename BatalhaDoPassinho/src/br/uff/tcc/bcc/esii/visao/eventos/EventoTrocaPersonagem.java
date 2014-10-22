package br.uff.tcc.bcc.esii.visao.eventos;

import br.uff.tcc.bcc.esii.controlador.ControladorTelaEscolha;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class EventoTrocaPersonagem implements ChangeListener{

	private int index;
	
	public EventoTrocaPersonagem(int index) {
		this.index=index;
	}
	
	@Override
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
			ControladorTelaEscolha.getInstancia().acao((String)arg2,index);
	}

}
