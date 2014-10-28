package br.uff.tcc.bcc.esii.visao.telas;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTelaJogo;

public class TelaObjetivo implements ITela{
	
	private String objetivo;	

	public TelaObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	
	

	@Override
	public Scene getScene() {
		Button voltar = FabricaDeBotoes.criaBotao("Voltar_Objetivo", "VOLTAR",new EventoTelaJogo());
		Image cartaObjetivo = new Image("file:media/imagens/cartas/valesca.png",320,320,true,true);	
		
		ImageView imageView = new ImageView();
		imageView.setImage(cartaObjetivo);


		//Group grupo = new Group();
		Label textoObjetivo = new Label(objetivo);
		//TODO Coordenadas relativas
		//textoObjetivo.setLayoutX(100);
		//textoObjetivo.setLayoutY(100);
		
//		grupo.getChildren().addAll(imageView);
//		grupo.getChildren().addAll(new Label(objetivo));
		VBox raiz = new VBox(10, new Label("Objetivo:"),imageView,textoObjetivo, voltar); 	
		
		return new Scene(raiz);
	}

}
