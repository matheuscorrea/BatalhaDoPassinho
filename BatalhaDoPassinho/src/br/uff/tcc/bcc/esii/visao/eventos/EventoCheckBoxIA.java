package br.uff.tcc.bcc.esii.visao.eventos;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import br.uff.tcc.bcc.esii.controlador.ControladorTelaEscolha;

public class EventoCheckBoxIA implements ChangeListener{
	
	@Override
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
			
		ControladorTelaEscolha.getInstancia().mudaCheckBox();
	}
}
