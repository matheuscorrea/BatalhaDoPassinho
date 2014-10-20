package br.uff.tcc.bcc.esii;

import javafx.application.Application;
import javafx.stage.Stage;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas.TipoDaTela;

public class BatalhaDoPassinho extends Application {

	public static void inicia(String ... args){
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		GerenciadorDeTelas.getInstancia().setPrimaryStage(stage);
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.inicio);
		stage.show();
	}
	
	public static void main(String[] args) {
		inicia();
	}
}
