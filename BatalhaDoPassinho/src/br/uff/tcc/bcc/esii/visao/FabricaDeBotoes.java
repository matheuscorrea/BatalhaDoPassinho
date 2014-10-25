package br.uff.tcc.bcc.esii.visao;

import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.esii.visao.ConstanteDaCor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Fábrica responsável por criar objetos do tipo  {@link Botao}
 *
 */
public class FabricaDeBotoes {
	
	/**
	 * Método que retorna uma instância de Botao de acordo com os parâmetros passados
	 * @param id 
	 * @param texto
	 * @param acao
	 * @return
	 */
	public static Button criaBotao(String id, String texto, EventHandler<ActionEvent> acao){
		Button botao = new Button();
		botao.setId(id);
		botao.setText(texto);
		botao.setOnAction(acao);
		return botao;		
	}
	
	/**
	 * Método que retorna uma instância de Botao de acordo com os parâmetros passados
	 * @param id 
	 * @param texto
	 * @param acao
	 * @return
	 */
	public static Button criaBotaoTerritorio(Territorio territorio,EventHandler<ActionEvent> acao){
		
		final String imagemAzul =     "file:media/imagens/peoes/PAWNBLUE.png";
		final String imagemVerde =    "file:media/imagens/peoes/PAWNGREEN.png";
		final String imagemCinza =    "file:media/imagens/peoes/PAWNGREY.png";
		final String imagemRosa =     "file:media/imagens/peoes/PAWNPINK.png";
		final String imagemVermelha = "file:media/imagens/peoes/PAWNRED.png";
		final String imagemAmarelo =  "file:media/imagens/peoes/PAWNYELLOW.png";
		String imagemURL=imagemAzul;		
		
		switch(territorio.getCor()){
			case AZUL:
				imagemURL=imagemAzul;
				break;
			case VERDE:
				imagemURL=imagemVerde;				
				break;
			case CINZA:
				imagemURL=imagemCinza;
				break;
			case ROSA:
				imagemURL=imagemRosa;
				break;
			case VERMELHO:
				imagemURL=imagemVermelha;
				break;
			case AMARELO:
				imagemURL=imagemAmarelo;
				break;
		}		
		Image image = new Image(imagemURL);
		ImageView imageView = new ImageView();
		imageView.setFitHeight(20);
		imageView.setFitWidth(20);
		imageView.setImage(image);
				
		Button botao =new Button(territorio.getQuantidadeTropa()+"");
		botao.setId(territorio.getNome());
		botao.setGraphic(imageView);
		botao.setOnAction(acao);
		botao.setMaxHeight(20);
		botao.setMaxWidth(100);
		ConstanteDoTerritorio constanteDoTerritorio = ConstanteDoTerritorio.fromString(territorio.getNome());
		botao.setLayoutX(constanteDoTerritorio.getX());
		botao.setLayoutY(constanteDoTerritorio.getY());		
		return botao;
	}
	
	public static ImageView criaImageView(Territorio t){
		
		final String imagemAzul =     "file:media/imagens/peoes/PAWNBLUE.png";
		final String imagemVerde =    "file:media/imagens/peoes/PAWNGREEN.png";
		final String imagemCinza =    "file:media/imagens/peoes/PAWNGREY.png";
		final String imagemRosa =     "file:media/imagens/peoes/PAWNPINK.png";
		final String imagemVermelha = "file:media/imagens/peoes/PAWNRED.png";
		final String imagemAmarelo =  "file:media/imagens/peoes/PAWNYELLOW.png";
		String imagemURL=imagemAzul;
				
		switch(t.getCor()){
			case AZUL:
				imagemURL=imagemAzul;
				break;
			case VERDE:
				imagemURL=imagemVerde;				
				break;
			case CINZA:
				imagemURL=imagemCinza;
				break;
			case ROSA:
				imagemURL=imagemRosa;
				break;
			case VERMELHO:
				imagemURL=imagemVermelha;
				break;
			case AMARELO:
				imagemURL=imagemAmarelo;
				break;
		}		
		Image image = new Image(imagemURL);
		ImageView imageView = new ImageView();
		imageView.setFitHeight(20);
		imageView.setFitWidth(20);
		imageView.setImage(image);

		return imageView;
	}	
}
