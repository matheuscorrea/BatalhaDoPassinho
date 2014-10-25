package br.uff.tcc.bcc.esii;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
		GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.INICIO);
		stage.show();
	}
	
	public static void main(String[] args) {
		inicia();
		
	}
}
