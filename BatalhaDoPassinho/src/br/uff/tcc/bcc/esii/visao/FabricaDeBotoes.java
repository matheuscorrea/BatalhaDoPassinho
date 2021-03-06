package br.uff.tcc.bcc.esii.visao;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.esii.visao.telas.ConstantesTelas;

/**
 * F�brica respons�vel por criar objetos do tipo {@link Botao}
 *
 */
public class FabricaDeBotoes {

	/**
	 * M�todo que retorna uma inst�ncia de Botao de acordo com os par�metros
	 * passados
	 * 
	 * @param id
	 * @param texto
	 * @param acao
	 * @return
	 */
	public static Button criaBotao(String id, String texto,
			EventHandler<ActionEvent> acao) {
		Button botao = new Button();
		botao.setId(id);
		botao.setText(texto);
		botao.setOnAction(acao);
		return botao;
	}

	public static Button criaBotaoComImagem(String id, String texto,
			EventHandler<ActionEvent> acao, Image imagem) {
		Button botao = new Button(texto, new ImageView(imagem));
		botao.setId(id);
		botao.setOnAction(acao);
		return botao;
	}

	/**
	 * M�todo que retorna uma inst�ncia de Botao de acordo com os par�metros
	 * passados
	 * 
	 * @param id
	 * @param texto
	 * @param acao
	 * @return
	 */
	//TODO Verificar m�todo deprecated
	public static Button criaBotaoTerritorio(Territorio territorio,
			EventHandler<ActionEvent> acao) {

		final String imagemAzul = "file:media/imagens/peoes/PAWNBLUE.png";
		final String imagemVerde = "file:media/imagens/peoes/PAWNGREEN.png";
		final String imagemCinza = "file:media/imagens/peoes/PAWNGREY.png";
		final String imagemRosa = "file:media/imagens/peoes/PAWNPINK.png";
		final String imagemVermelha = "file:media/imagens/peoes/PAWNRED.png";
		final String imagemAmarelo = "file:media/imagens/peoes/PAWNYELLOW.png";
		String imagemURL = imagemAzul;

		switch (territorio.getCor()) {
		case AZUL:
			imagemURL = imagemAzul;
			break;
		case VERDE:
			imagemURL = imagemVerde;
			break;
		case CINZA:
			imagemURL = imagemCinza;
			break;
		case ROSA:
			imagemURL = imagemRosa;
			break;
		case VERMELHO:
			imagemURL = imagemVermelha;
			break;
		case AMARELO:
			imagemURL = imagemAmarelo;
			break;
		}
		Image image = new Image(imagemURL);
		ImageView imageView = new ImageView();
		imageView.setFitHeight(20);
		imageView.setFitWidth(20);
		imageView.setImage(image);

		Button botao = new Button(territorio.getQuantidadeTropa() + "");
		botao.setId(territorio.getNome());
		botao.setGraphic(imageView);
		botao.setOnAction(acao);
		botao.setMaxHeight(20);
		botao.setMaxWidth(100);
		ConstanteDoTerritorio constanteDoTerritorio = ConstanteDoTerritorio
				.fromString(territorio.getNome());
		//botao.setLayoutX(constanteDoTerritorio.getX());
		//botao.setLayoutY(constanteDoTerritorio.getY());
		botao.setLayoutX((constanteDoTerritorio.getX()/ConstantesTelas.TAMANHO_IMG_TELA_JOGO_X)*ConstantesTelas.resolucaoX);
		botao.setLayoutY((constanteDoTerritorio.getY()/(ConstantesTelas.TAMANHO_IMG_TELA_JOGO_Y-ConstantesTelas.TAMANHO_BARRA_TELA_JOGO_Y))*
				(ConstantesTelas.resolucaoY - ConstantesTelas.TAMANHO_BARRA_TELA_JOGO_Y*2));
		
		return botao;
	}

	/**
	 * M�todo que retorna uma inst�ncia de Botao de acordo com os par�metros
	 * passados
	 * 
	 * @param id
	 * @param texto
	 * @param acao
	 * @return
	 */
	public static Group criaGroupTerritorio(Territorio territorio,
			EventHandler<ActionEvent> acao) {

		final String imagemAzul = "file:media/imagens/peoes/PAWNBLUE.png";
		final String imagemVerde = "file:media/imagens/peoes/PAWNGREEN.png";
		final String imagemCinza = "file:media/imagens/peoes/PAWNGREY.png";
		final String imagemRosa = "file:media/imagens/peoes/PAWNPINK.png";
		final String imagemVermelha = "file:media/imagens/peoes/PAWNRED.png";
		final String imagemAmarelo = "file:media/imagens/peoes/PAWNYELLOW.png";
		String imagemURL = imagemAzul;

		switch (territorio.getCor()) {
		case AZUL:
			imagemURL = imagemAzul;
			break;
		case VERDE:
			imagemURL = imagemVerde;
			break;
		case CINZA:
			imagemURL = imagemCinza;
			break;
		case ROSA:
			imagemURL = imagemRosa;
			break;
		case VERMELHO:
			imagemURL = imagemVermelha;
			break;
		case AMARELO:
			imagemURL = imagemAmarelo;
			break;
		}
		Image image = new Image(imagemURL);
		ImageView imageView = new ImageView();
		imageView.setFitHeight(20);
		imageView.setFitWidth(20);
		imageView.setLayoutX(4);
		imageView.setLayoutY(2);

		imageView.setImage(image);

		Button botao = new Button(territorio.getQuantidadeTropa() + "");
		botao.setId(territorio.getNome());
		botao.setOnAction(acao);
		botao.setMaxHeight(20);
		botao.setMaxWidth(100);
		ConstanteDoTerritorio constanteDoTerritorio = ConstanteDoTerritorio
				.fromString(territorio.getNome());

		botao.setStyle("-fx-background-color: transparent");

		Group g = new Group();
		g.getChildren().addAll(imageView, botao);

		//g.setLayoutX(constanteDoTerritorio.getX());
		//g.setLayoutY(constanteDoTerritorio.getY());
		g.setLayoutX((constanteDoTerritorio.getX()/ConstantesTelas.TAMANHO_IMG_TELA_JOGO_X)*ConstantesTelas.resolucaoX);
		g.setLayoutY((constanteDoTerritorio.getY()/(ConstantesTelas.TAMANHO_IMG_TELA_JOGO_Y-ConstantesTelas.TAMANHO_BARRA_TELA_JOGO_Y))*
				(ConstantesTelas.resolucaoY - ConstantesTelas.TAMANHO_BARRA_TELA_JOGO_Y*2));
		g.setId(territorio.getNome());

		return g;
	}

	public static ImageView criaImagemDoBotaoTerritorio(Territorio t) {

		final String imagemAzul = "file:media/imagens/peoes/PAWNBLUE.png";
		final String imagemVerde = "file:media/imagens/peoes/PAWNGREEN.png";
		final String imagemCinza = "file:media/imagens/peoes/PAWNGREY.png";
		final String imagemRosa = "file:media/imagens/peoes/PAWNPINK.png";
		final String imagemVermelha = "file:media/imagens/peoes/PAWNRED.png";
		final String imagemAmarelo = "file:media/imagens/peoes/PAWNYELLOW.png";
		String imagemURL = imagemAzul;

		switch (t.getCor()) {
		case AZUL:
			imagemURL = imagemAzul;
			break;
		case VERDE:
			imagemURL = imagemVerde;
			break;
		case CINZA:
			imagemURL = imagemCinza;
			break;
		case ROSA:
			imagemURL = imagemRosa;
			break;
		case VERMELHO:
			imagemURL = imagemVermelha;
			break;
		case AMARELO:
			imagemURL = imagemAmarelo;
			break;
		}
		Image image = new Image(imagemURL);
		ImageView imageView = new ImageView();
		imageView.setFitHeight(20);
		imageView.setFitWidth(20);
		imageView.setImage(image);
		imageView.setLayoutX(4);
		imageView.setLayoutY(2);

		return imageView;
	}

	public static ImageView criaImagemDoBotaoTerritorioComHighLight(Territorio t) {

		final String imagemAzul = "file:media/imagens/peoes/PAWNBLUEGLOWHITE.png";
		final String imagemVerde = "file:media/imagens/peoes/PAWNGREENGLOWHITE.png";
		final String imagemCinza = "file:media/imagens/peoes/PAWNGREYGLOWHITE.png";
		final String imagemRosa = "file:media/imagens/peoes/PAWNPINKGLOWHITE.png";
		final String imagemVermelha = "file:media/imagens/peoes/PAWNREDGLOWHITE.png";
		final String imagemAmarelo = "file:media/imagens/peoes/PAWNYELLOWGLOWHITE.png";
		String imagemURL = imagemAzul;

		switch (t.getCor()) {
		case AZUL:
			imagemURL = imagemAzul;
			break;
		case VERDE:
			imagemURL = imagemVerde;
			break;
		case CINZA:
			imagemURL = imagemCinza;
			break;
		case ROSA:
			imagemURL = imagemRosa;
			break;
		case VERMELHO:
			imagemURL = imagemVermelha;
			break;
		case AMARELO:
			imagemURL = imagemAmarelo;
			break;
		}
		Image image = new Image(imagemURL);
		ImageView imageView = new ImageView();
		imageView.setFitHeight(20);
		imageView.setFitWidth(20);
		imageView.setImage(image);
		imageView.setLayoutX(4);
		imageView.setLayoutY(2);

		return imageView;
	}
	
	
}
