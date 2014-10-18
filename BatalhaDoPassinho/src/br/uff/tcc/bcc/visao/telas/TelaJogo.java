package br.uff.tcc.bcc.visao.telas;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import br.uff.tcc.bcc.esii.modelo.Mapa;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.visao.eventos.EventoTerritorio;

public class TelaJogo implements ITela{

	private Mapa mapa;
		
	/**
	 * Construtor da classe TelaJogo
	 * <br>Recebe o mapa como parametro para desenhar os botões
	 * @param mapa
	 */
	public  TelaJogo(Mapa mapa){
		this.mapa = mapa;

	}
	/*
	 * (non-Javadoc)
	 * @see br.uff.tcc.bcc.visao.telas.ITela#getScene()
	 */
	@Override
	public Scene getScene() {
	
		final String imagemURL = "imagens/mapa.jpg";
		
		Image image = new Image(imagemURL);
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		
		
		
		List<Button> listaDeBotoesTerritorios = new ArrayList<Button>();
		for(Territorio territorio : mapa.getTerritorios()){
			listaDeBotoesTerritorios.add(FabricaDeBotoes.criaBotaoTerritorio(territorio,new EventoTerritorio()));
		}
		
		Group grupo = new Group();
		grupo.getChildren().addAll(imageView);
		grupo.getChildren().addAll(listaDeBotoesTerritorios);

		return new Scene(grupo, 1123, 554);	
	}

}
