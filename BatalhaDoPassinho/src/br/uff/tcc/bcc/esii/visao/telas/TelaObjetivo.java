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
		Image cartaObjetivo = new Image("file:media/imagens/cartas/template.png",400,400,true,true);	
		
		ImageView imageView = new ImageView();
		imageView.setImage(cartaObjetivo);

		Group grupo = new Group();
		//TODO Coordenadas relativas
		Label textoObjetivo = new Label(objetivo);
		textoObjetivo.setMaxWidth(200);
		textoObjetivo.setWrapText(true);
		textoObjetivo.setLayoutX(25);
		textoObjetivo.setLayoutY(120);
		
		grupo.getChildren().addAll(imageView);
		grupo.getChildren().addAll(textoObjetivo);
		VBox raiz = new VBox(10, grupo, voltar); 	

		return new Scene(raiz);
	}

}
