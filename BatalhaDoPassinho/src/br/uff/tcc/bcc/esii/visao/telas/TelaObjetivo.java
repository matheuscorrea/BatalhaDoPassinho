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
		Button voltar = FabricaDeBotoes.criaBotaoComImagem("Voltar_Objetivo", "",new EventoTelaJogo(), new Image("file:media/imagens/botoes/BTVOLTAR.png",125,125,true,true));
		voltar.setStyle("-fx-background-color: transparent");

		Image cartaObjetivo = new Image("file:media/imagens/cartas/template.png",400,400,true,true);	
		
		ImageView imageView = new ImageView();
		imageView.setImage(cartaObjetivo);
		//imageView.setLayoutX(ConstantesTelas.resolucaoX/2 - 100);

		ImageView fundo = new ImageView();
		fundo.setImage(new Image("file:media/imagens/mapa/mapaSemBrilho.png",ConstantesTelas.resolucaoX,ConstantesTelas.resolucaoY-80,false,true));
		
		Group grupo = new Group();
		Label textoObjetivo = new Label(objetivo);
		textoObjetivo.setMaxWidth(200);
		textoObjetivo.setWrapText(true);
		textoObjetivo.setLayoutX(25);
		textoObjetivo.setLayoutY(120);
		//textoObjetivo.setLayoutX(ConstantesTelas.resolucaoX/2 - 100);
		
		grupo.getChildren().addAll(imageView,textoObjetivo);
		
		GridPane grid = new GridPane();
		grid.add(fundo,2,0);
		grid.add(grupo, 2, 0);
		grid.add(voltar,2,1);
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setStyle("-fx-background-color: black;");
		grid.setMinSize(1120, 580);
		//grid.setGridLinesVisible(true);

		
		
		//VBox raiz = new VBox(grupo, voltar);
		//raiz.setMinSize(1137, 600);
		//raiz.setAlignment(Pos.TOP_CENTER);
		//return new Scene(raiz);

		return new Scene(grid);
	}

}
