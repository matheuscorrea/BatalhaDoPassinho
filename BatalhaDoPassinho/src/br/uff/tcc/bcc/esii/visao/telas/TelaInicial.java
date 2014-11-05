package br.uff.tcc.bcc.esii.visao.telas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoOpcoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoSair;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTelaCarregar;

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

        Image image = new Image("file:media/imagens/botoes/BTINICIAR.png",100,100,true,true);
        Button botaoNovoJogo = FabricaDeBotoes.criaBotaoComImagem("Novo_Jogo", "", new EventoTelaCarregar(), image);
        botaoNovoJogo.setStyle("-fx-background-color: transparent");
        
        Image imageSair = new Image("file:media/imagens/botoes/BTSAIR.png",100,100,true,true);
        Button botaoSair = FabricaDeBotoes.criaBotaoComImagem("Botao_Sair", "", new EventoSair(), imageSair);
        botaoSair.setStyle("-fx-background-color: transparent");
        
        Image imageRegras = new Image("file:media/imagens/botoes/BTREGRAS.png",100,100,true,true);
        Button botaoRegras = FabricaDeBotoes.criaBotaoComImagem("Botao_Regras", "", new EventoOpcoes(), imageRegras);
        botaoRegras.setStyle("-fx-background-color: transparent");
        
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