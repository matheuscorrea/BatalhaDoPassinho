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
import br.uff.tcc.bcc.esii.modelo.Carta;
import br.uff.tcc.bcc.esii.modelo.Carta.Tipo;
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
	private List<Button> listaDeBotoesTerritorios;
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
		listaDeBotoesTerritorios = new ArrayList<Button>();
	}
	
	public List<Button> getListaDeBotoesTerritorios() {
		return listaDeBotoesTerritorios;
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

	public Scene atualizaBarraInformacoes(Jogo jogo) {
		barraInformacoes.getChildren().clear();
		Button botaoFase = FabricaDeBotoes.criaBotao("Proxima_fase",
				"ACABAR FASE 1", new EventoProximaFase());
		;
		Button botaoMover = FabricaDeBotoes.criaBotao("Mover_tropas",
				"MOVER UMA TROPA", new EventoMove());
		Button botaoAtaque = FabricaDeBotoes.criaBotao("Ataque", "Ataque",
				new EventoAtaque());
		Button botaoTroca = FabricaDeBotoes.criaBotao("Trocar_cartas",
				"TROCAR CARTAS", new EventoTelaCartas());
		Button botaoObjetivo = FabricaDeBotoes.criaBotao("Ver_objetivo",
				"VER OBJETIVO", new EventoMostraObjetivo());
		
		Image iTiro = new Image("file:media/imagens/cartas/tiro.png",32,32,true,true);	
		Image iPorrada = new Image("file:media/imagens/cartas/porrada.png",32,32,true,true);		
		Image iBomba = new Image("file:media/imagens/cartas/bomba.png",32,32,true,true);
		Image iValesca = new Image("file:media/imagens/cartas/valesca.png",32,32,true,true);		
		int[] vetorCartas = new int[4];		
		List<Carta> listaCartasDoJogador = jogo.getJogadorDaVez().getMao();
		atualizaVetorCartas(vetorCartas, listaCartasDoJogador);
		
		HBox cartas = new HBox(new ImageView(iTiro), new Label("x"+vetorCartas[0]+"  "), 
				new ImageView(iPorrada), new Label("x"+vetorCartas[1]+"  "), 
				new ImageView(iBomba), new Label("x"+vetorCartas[2]+"  "), 
				new ImageView(iValesca), new Label("x"+vetorCartas[3]+"  "));
		
		Image corJogador;
		corJogador = obtemImageCorJogador(jogo);
		
		switch (jogo.faseAtual) {
		case FASE_1:
			if (jogo.getQuantidadeDeTropas() > 0)
				botaoFase.setDisable(true);
			else
				botaoFase.setDisable(false);

			barraInformacoes.getChildren().addAll(new ImageView(corJogador),
					new Label(jogo.getJogadorDaVez().getNome()),
					new Label(jogo.faseAtual.name()),
					new Label("Tropas para distribuir: "+jogo.getQuantidadeDeTropas()), 
					botaoFase,
					botaoObjetivo,
					botaoTroca, 
					cartas);
			break;
		case FASE_2:
			botaoFase.setText("ACABAR FASE 2");
			if (jogo.getQuantidadeDeTropas() > 0)
				botaoFase.setDisable(true);
			else
				botaoFase.setDisable(false);

			barraInformacoes.getChildren().addAll(
					new ImageView(corJogador),
					new Label(jogo.getJogadorDaVez().getNome()),
					new Label(jogo.faseAtual.name()),
					botaoFase,
					botaoAtaque, 
					botaoObjetivo,
					cartas);
			break;
		case FASE_3:
			botaoFase.setText("PASSAR A VEZ");
			barraInformacoes.getChildren().addAll(
					new ImageView(corJogador),
					new Label(jogo.getJogadorDaVez().getNome()),
					new Label(jogo.faseAtual.name()),
					botaoFase,			
					botaoMover,
					botaoObjetivo,
					cartas);
			break;
		default:
			barraInformacoes.getChildren().addAll(
					new ImageView(corJogador),
					new Label(jogo.getJogadorDaVez().getNome()),
					new Label(jogo.faseAtual.name()),
					botaoFase,			
					botaoObjetivo,
					cartas);
			break;
		}
		VBox vBox = new VBox(10, grupo, barraInformacoes);
		return new Scene(vBox);
	}

	private void atualizaVetorCartas(int[] vetorCartas,
			List<Carta> listaCartasDoJogador) {
		for(Carta carta : listaCartasDoJogador){
			switch (carta.getTipo()){
			case TIRO:
				vetorCartas[0]++;
				break;
			case PORRADA:
				vetorCartas[1]++;
				break;
			case BOMBA:
				vetorCartas[2]++;
				break;
			case VALESCA:
				vetorCartas[3]++;
				break;
			}			
		}
	}

	private Image obtemImageCorJogador(Jogo jogo) {
		Image corJogador;
		switch (jogo.getJogadorDaVez().getCor()){
			case AZUL: corJogador = new Image("file:media/imagens/peoes/PAWNBLUE.png",20,20,true,true);
				break;
			case VERDE: corJogador = new Image("file:media/imagens/peoes/PAWNGREEN.png",20,20,true,true);	
				break;
			case CINZA: corJogador = new Image("file:media/imagens/peoes/PAWNGREY.png",20,20,true,true);	
				break;
			case ROSA: corJogador = new Image("file:media/imagens/peoes/PAWNPINK.png",20,20,true,true);	
				break;
			case VERMELHO: corJogador = new Image("file:media/imagens/peoes/PAWNRED.png",20,20,true,true);	
				break;
			default: corJogador = new Image("file:media/imagens/peoes/PAWNYELLOW.png",20,20,true,true);				
		}
		return corJogador;
	}
	
	
}