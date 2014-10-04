package br.uff.tcc.bcc.controlador;

import javafx.scene.Scene;
import br.uff.tcc.bcc.esii.Jogo;
import br.uff.tcc.bcc.visao.Botao;

public class ControladorJogo implements Controlador {
	
	private static ControladorJogo controlador;
	
	private Jogo jogo;
	
	private ControladorJogo(){
		jogo = new Jogo();
	}
	
	public static Controlador getInstancia(){
		if (controlador == null){
			controlador = new ControladorJogo();
		}
		return controlador;
	}

	@Override
	public void acao(Botao botao) {
		
	}

}
