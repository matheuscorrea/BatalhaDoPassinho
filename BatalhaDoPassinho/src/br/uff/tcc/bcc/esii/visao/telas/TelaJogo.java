package br.uff.tcc.bcc.esii.visao.telas;

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
import br.uff.tcc.bcc.esii.modelo.Jogo;
import br.uff.tcc.bcc.esii.modelo.Mapa;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoAtaque;
import br.uff.tcc.bcc.esii.visao.eventos.EventoMostraObjetivo;
import br.uff.tcc.bcc.esii.visao.eventos.EventoMove;
import br.uff.tcc.bcc.esii.visao.eventos.EventoProximaFase;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTelaCartas;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTerritorio;

public class TelaJogo implements ITela {

	private Mapa mapa;

	private Group grupo;
	private HBox barraInformacoes;
	
	//private static TelaJogo telaJogo;
	

	/**
	 * Construtor da classe TelaJogo <br>
	 * Recebe o mapa como parametro para desenhar os botões
	 * 
	 * @param mapa
	 */
	public TelaJogo(Mapa mapa) {
		this.mapa = mapa;
		this.barraInformacoes = new HBox(20);
	}
	
//	public static TelaJogo getInstancia(){
//		if (telaJogo == null){
//			telaJogo = new TelaJogo();
//		}
//		return telaJogo;
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.uff.tcc.bcc.visao.telas.ITela#getScene()
	 */
	@Override
	public Scene getScene() {

		final String imagemURL = "file:media/imagens/mapa/mapa.jpg";

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

		barraInformacoes.getChildren().addAll(new Label("Novo Jogo"));

		VBox vBox = new VBox(10, grupo, barraInformacoes);
		// 1123x554 mapa
		return new Scene(vBox);
	}

	public Scene atualizaBarraInformacoes(Jogo jogo){
		barraInformacoes.getChildren().clear();
		Button botaoFase = FabricaDeBotoes.criaBotao("Proxima_fase", "ACABAR FASE 1", new EventoProximaFase());;
		Button botaoMover = FabricaDeBotoes.criaBotao("Mover_tropas", "MOVER UMA TROPA", new EventoMove());
		Button botaoAtaque = FabricaDeBotoes.criaBotao("Ataque", "Ataque", new EventoAtaque());
		Button botaoTroca = FabricaDeBotoes.criaBotao("Trocar_cartas", "TROCAR CARTAS", new EventoTelaCartas());
		Button botaoObjetivo = FabricaDeBotoes.criaBotao("Ver_objetivo", "VER OBJETIVO", new EventoMostraObjetivo());
		
		switch (jogo.faseAtual) {
		case FASE_1:
			if(jogo.getQuantidadeDeTropas()>0)
				botaoFase.setDisable(true);
			else
				botaoFase.setDisable(false);
			
			barraInformacoes.getChildren().addAll(new Label(jogo.faseAtual.name()),
					new Label(jogo.getJogadorDaVez().getNome()+" "+jogo.getQuantidadeDeTropas()),botaoFase,botaoTroca,botaoObjetivo);
			break;
		case FASE_2:
			botaoFase.setText("ACABAR FASE 2");
			if(jogo.getQuantidadeDeTropas()>0)
				botaoFase.setDisable(true);
			else
				botaoFase.setDisable(false);
			
			barraInformacoes.getChildren().addAll(new Label(jogo.faseAtual.name()),
					new Label(jogo.getJogadorDaVez().getNome(),botaoFase),botaoAtaque,botaoObjetivo);
			break;
		case FASE_3:
			botaoFase.setText("PASSAR A VEZ");
			barraInformacoes.getChildren().addAll(new Label(jogo.faseAtual.name()),
					new Label(jogo.getJogadorDaVez().getNome()+" "+jogo.getQuantidadeDeTropas()),botaoMover,botaoFase,botaoObjetivo);
			break;
		default:
			barraInformacoes.getChildren().addAll(new Label(jogo.faseAtual.name()),
					new Label(jogo.getJogadorDaVez().getNome()),botaoFase,botaoObjetivo);
			break;
		}
		VBox vBox = new VBox(10, grupo, barraInformacoes);
		return new Scene(vBox);
	}
}