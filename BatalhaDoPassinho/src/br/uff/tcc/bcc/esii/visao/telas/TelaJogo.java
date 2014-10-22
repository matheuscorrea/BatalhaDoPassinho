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
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Jogo;
import br.uff.tcc.bcc.esii.modelo.Jogo.TipoFase;
import br.uff.tcc.bcc.esii.modelo.Mapa;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoMove;
import br.uff.tcc.bcc.esii.visao.eventos.EventoAtaque;
import br.uff.tcc.bcc.esii.visao.eventos.EventoProximaFase;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTerritorio;

public class TelaJogo implements ITela {

	private Mapa mapa;

	private Group grupo;
	private HBox barraInformacoes;

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
		Button botaoAtaque = FabricaDeBotoes.criaBotao("Ataque", "Ataque", new EventoAtaque());;

		if(jogo.faseAtual.equals(TipoFase.FASE_1)){
			if(jogo.getQuantidadeDeTropas()>0)
				botaoFase.setDisable(true);
			else
				botaoFase.setDisable(false);
		}		
		if(jogo.faseAtual.equals(TipoFase.FASE_2)){
			botaoFase.setText("ACABAR FASE 2");
			
		}else if(jogo.faseAtual.equals(TipoFase.FASE_3)){
			botaoFase.setText("PASSAR A VEZ");			
		}
		if(jogo.faseAtual.equals(TipoFase.FASE_1))
			barraInformacoes.getChildren().addAll(new Label(jogo.faseAtual.name()),new Label(jogo.getJogadorDaVez().getNome()+" "+jogo.getQuantidadeDeTropas()),botaoFase);
	
		else if(jogo.faseAtual.equals(TipoFase.FASE_3))
				barraInformacoes.getChildren().addAll(new Label(jogo.faseAtual.name()),new Label(jogo.getJogadorDaVez().getNome()+" "+jogo.getQuantidadeDeTropas()),botaoMover,botaoFase);

		else if(jogo.faseAtual.equals(TipoFase.FASE_2)){
			barraInformacoes.getChildren().addAll(new Label(jogo.faseAtual.name()),new Label(jogo.getJogadorDaVez().getNome(),botaoFase),botaoAtaque);
			//barraInformacoes.getChildren().addAll(new Label(jogo.faseAtual.name()),new Label(jogo.getJogadorDaVez().getNome()),botaoAtaque);
		}else {
			barraInformacoes.getChildren().addAll(new Label(jogo.faseAtual.name()),new Label(jogo.getJogadorDaVez().getNome()),botaoFase);
		}

		VBox vBox = new VBox(10, grupo, barraInformacoes);
		return new Scene(vBox);
	}

}
