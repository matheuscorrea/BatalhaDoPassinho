package br.uff.tcc.bcc.visao.telas;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Jogo.TipoFase;
import br.uff.tcc.bcc.esii.modelo.Mapa;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.visao.eventos.EventoProximaFase;
import br.uff.tcc.bcc.visao.eventos.EventoTerritorio;

public class TelaJogo implements ITela {

	private Mapa mapa;

	private Group grupo;

	/**
	 * Construtor da classe TelaJogo <br>
	 * Recebe o mapa como parametro para desenhar os botões
	 * 
	 * @param mapa
	 */
	public TelaJogo(Mapa mapa) {
		this.mapa = mapa;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.uff.tcc.bcc.visao.telas.ITela#getScene()
	 */
	@Override
	public Scene getScene() {

		final String imagemURL = "imagens/mapa.jpg";

		Image image = new Image(imagemURL);
		ImageView imageView = new ImageView();
		imageView.setImage(image);

		List<Button> listaDeBotoesTerritorios = new ArrayList<Button>();
		for (Territorio territorio : mapa.getTerritorios()) {
			listaDeBotoesTerritorios.add(FabricaDeBotoes.criaBotaoTerritorio(
					territorio, new EventoTerritorio()));
		}

		grupo = new Group();
		grupo.getChildren().addAll(imageView);
		grupo.getChildren().addAll(listaDeBotoesTerritorios);

		HBox barraInformacoes = new HBox(20);
		barraInformacoes.getChildren().addAll(new Label("Novo Jogo"));

		VBox vBox = new VBox(10, grupo, barraInformacoes);
		// 1123x554 mapa
		return new Scene(vBox, 1123, 600);
	}

	public Scene atualizaBarraInformacoes(TipoFase fase, Jogador jogador){
		HBox barraInformacoes = new HBox(20);
		Button botaoFase;
		if(fase.equals(TipoFase.FASE_1)){
			botaoFase = FabricaDeBotoes.criaBotao("Proxima_fase", "ACABAR FASE 1", new EventoProximaFase());			
//			barraInformacoes.getChildren().addAll(new Label(fase.name()),new Label(jogador.getNome()));
			
		}else if(fase.equals(TipoFase.FASE_2)){
			botaoFase = FabricaDeBotoes.criaBotao("Proxima_fase", "ACABAR FASE 2", new EventoProximaFase());			
			
//			barraInformacoes.getChildren().addAll(new Label(fase.name()),new Label(jogador.getNome()));

		}else{// if(fase.equals(TipoFase.FASE_3)){

			botaoFase = FabricaDeBotoes.criaBotao("Proxima_fase", "PASSAR A VEZ", new EventoProximaFase());			
			
//			barraInformacoes.getChildren().addAll(new Label(fase.name()),new Label(jogador.getNome()));
			
		}
		barraInformacoes.getChildren().addAll(new Label(fase.name()),new Label(jogador.getNome()),botaoFase);
		VBox vBox = new VBox(10, grupo, barraInformacoes);
		return new Scene(vBox, 1123, 600);
	}
}
