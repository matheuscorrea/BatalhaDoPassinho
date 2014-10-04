package br.uff.tcc.bcc.esii;

import javafx.application.Application;
import javafx.stage.Stage;
import br.uff.tcc.bcc.visao.GerenciadorDeTelas;
import br.uff.tcc.bcc.visao.GerenciadorDeTelas.TipoDaTela;

public class BatalhaDoPassinho extends Application {

	public static void inicia(String ... args){
		launch(args);
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		GerenciadorDeTelas.getInstancia().setPrimaryStage(arg0);
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.inicio);
		arg0.show();
	}
	
	public static void main(String[] args) {
		inicia();
	}
}
