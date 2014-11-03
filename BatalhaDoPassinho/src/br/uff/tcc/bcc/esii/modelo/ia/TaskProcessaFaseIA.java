package br.uff.tcc.bcc.esii.modelo.ia;

import br.uff.tcc.bcc.esii.modelo.Jogo.TipoFase;
import javafx.concurrent.Task;

public class TaskProcessaFaseIA extends Task {

	private JogadorIA jogadorIA;
	private TipoFase fase;
	
	public TaskProcessaFaseIA(JogadorIA jogadorIA, TipoFase fase){
		this.jogadorIA = jogadorIA;
		this.fase = fase;
	}
	
	@Override
	protected Object call() throws Exception {
		switch(fase){
	    case FASE_1:
	    	System.out.println("chamou fase1 dentro da task");
	    	jogadorIA.fase1();
	    	//TODO outras fases
	    }
		return null;
	}
}
