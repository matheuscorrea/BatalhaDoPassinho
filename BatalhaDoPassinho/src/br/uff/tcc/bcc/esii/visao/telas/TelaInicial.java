package br.uff.tcc.bcc.esii.visao.telas;

import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoNovoJogo;
import br.uff.tcc.bcc.esii.visao.eventos.EventoOpcoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoSair;
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

        //Button botaoNovoJogo = FabricaDeBotoes.criaBotao("NOVO_JOGO", "Novo jogo", new EventoTelaCarregar());
        //Button botaoOpcoes = FabricaDeBotoes.criaBotao("OPCOES", "Opcoes", new EventoOpcoes());
        
        
        Image image = new Image("file:media/imagens/botoes/BTINICIAR.png",100,100,true,true);
        Button botaoNovoJogo = FabricaDeBotoes.criaBotaoComImagem("Novo_Jogo", "", new EventoTelaCarregar(), image);
        Image imageSair = new Image("file:media/imagens/botoes/BTSAIR.png",100,100,true,true);
        Button botaoSair = FabricaDeBotoes.criaBotaoComImagem("Botao_Sair", "", new EventoSair(), imageSair);
        Image imageRegras = new Image("file:media/imagens/botoes/BTREGRAS.png",100,100,true,true);
        Button botaoRegras = FabricaDeBotoes.criaBotaoComImagem("Botao_Regras", "", new EventoOpcoes(), imageRegras);
//		ImageView imageView = new ImageView();
//		//imageView.setFitHeight(214);
//		//imageView.setFitWidth(132);
//		imageView.setImage(image);
		
		
//		botaoNovoJogo.setStyle("-fx-focus-color: transparent");
		
//		botaoNovoJogo.setCenterShape(true);
//      botaoNovoJogo.setGraphic(imageView);
//        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        
        grid.add(botaoNovoJogo, 2, 1);
        grid.add(botaoRegras, 2, 2);
        grid.add(botaoSair,2, 3);
   
		return new Scene(grid, 800, 600);
	}
}