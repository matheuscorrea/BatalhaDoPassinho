package br.uff.tcc.bcc.esii;

import javafx.application.Application;
import javafx.stage.Stage;
import br.uff.tcc.bcc.esii.save.Save;
import br.uff.tcc.bcc.esii.som.Musicas;
import br.uff.tcc.bcc.esii.som.Som;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas.TipoDaTela;
import br.uff.tcc.bcc.esii.visao.telas.ConstantesTelas;

public class BatalhaDoPassinho extends Application {

	public static void inicia(String ... args){
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
//	    final URL resource = getClass().getResource("Zen.mp3");
//	    final Media media = new Media(resource.toString());
//	    final MediaPlayer mediaPlayer = new MediaPlayer(media);
//	    
//	    mediaPlayer.play();
//	    mediaPlayer.setCycleCount(2);
		
		Som.getInstancia().toca(Musicas.INICIAL.getID());
				
	    GerenciadorDeTelas.getInstancia().setPrimaryStage(stage);
	    ConstantesTelas.setResolucao();
 		try {
 			GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.INICIO);			
		} catch (Exception e) {
			// TODO: handle exception
			Save save = new Save();
			save.save();
		}
 		stage.show();
	}
	
	public static void main(String[] args) {
		inicia();
	}
}
