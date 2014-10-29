package br.uff.tcc.bcc.esii.visao.telas;

import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoNovoJogo;
import br.uff.tcc.bcc.esii.visao.eventos.EventoOpcoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTelaCarregar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Classe que implementa a distribuição dos botões na tela inicial
 *
 */
public class TelaInicial implements ITela {

	/*
	 * (non-Javadoc)
	 * @see br.uff.tcc.bcc.visao.telas.ITela#getScene()
	 */
	@Override
	public Scene getScene() {

        Button botaoNovoJogo = FabricaDeBotoes.criaBotao("NOVO_JOGO", "Novo jogo", new EventoTelaCarregar());
        Button botaoOpcoes = FabricaDeBotoes.criaBotao("OPCOES", "Opcoes", new EventoOpcoes());
        
        
//        Image image = new Image("file:media/imagens/botoes/BTINICIAR.png",200,200,true,true);
//		ImageView imageView = new ImageView();
//		//imageView.setFitHeight(214);
//		//imageView.setFitWidth(132);
//		imageView.setImage(image);
//		Button botaoNovoJogo = new Button();
//		botaoNovoJogo.setCenterShape(true);
//        botaoNovoJogo.setGraphic(imageView);
//        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(botaoNovoJogo, 0, 0);
        grid.add(botaoOpcoes, 1, 0);        
   
		return new Scene(grid, 300, 250);
	}
}