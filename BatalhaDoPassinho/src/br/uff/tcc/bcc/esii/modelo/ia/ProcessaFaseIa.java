package br.uff.tcc.bcc.esii.modelo.ia;

import br.uff.tcc.bcc.esii.modelo.Jogo.TipoFase;

public class ProcessaFaseIa extends Thread{
	private JogadorIA jogadorIA;
	private TipoFase fase;
	
	public ProcessaFaseIa(JogadorIA jogadorIA, TipoFase fase){
		this.jogadorIA = jogadorIA;
		this.fase = fase;
	}
	
	public void run () {
	    switch(fase){
	    case FASE_1:
	    	
	    	jogadorIA.fase1();
	    	//TODO outras fases
	    }
	}

}
