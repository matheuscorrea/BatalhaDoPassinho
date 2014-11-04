package br.uff.tcc.bcc.esii.visao.telas;


import org.w3c.dom.Node;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
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

		ImageView fundo = new ImageView();
		fundo.setImage(new Image("file:media/imagens/mapa/mapaSemBrilho.png"));
		
		Group grupo = new Group();
		//TODO Coordenadas relativas
		Label textoObjetivo = new Label(objetivo);
		textoObjetivo.setMaxWidth(200);
		textoObjetivo.setWrapText(true);
		textoObjetivo.setLayoutX(25);
		textoObjetivo.setLayoutY(120);
		
			
		
		grupo.getChildren().addAll(imageView);
		grupo.getChildren().addAll(textoObjetivo);
		
		GridPane grid = new GridPane();
		grid.add(fundo,2,0);
		grid.add(grupo, 2, 0);
		grid.add(voltar,2,1);
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setStyle("-fx-background-color: black;");
		grid.setMinSize(1120, 580);
		
		
		//VBox raiz = new VBox(grupo, voltar);
		//raiz.setMinSize(1137, 600);
		//raiz.setAlignment(Pos.TOP_CENTER);
		//return new Scene(raiz);

		return new Scene(grid);
	}

}
