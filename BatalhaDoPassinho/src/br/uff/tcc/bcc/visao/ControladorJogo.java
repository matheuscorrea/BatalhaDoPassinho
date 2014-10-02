package br.uff.tcc.bcc.visao;

import javafx.scene.Scene;
import br.uff.tcc.bcc.esii.Jogo;

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

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return null;
	}
}
