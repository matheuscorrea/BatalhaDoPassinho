package br.uff.tcc.bcc.esii.visao.telas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import br.uff.tcc.bcc.esii.som.Som;
import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoCarregaJogo;
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
		
		Som.getInstancia().toca(0);
		
		Image image = new Image("file:media/imagens/botoes/BTINICIAR.png",100,100,true,true);
        Button botaoNovoJogo = FabricaDeBotoes.criaBotaoComImagem("Novo_Jogo", "", new EventoTelaCarregar(), image);
        botaoNovoJogo.setStyle("-fx-background-color: transparent");
        

        Button botaoCarrega = FabricaDeBotoes.criaBotaoComImagem("Carrega_Partida", "", new EventoCarregaJogo(), new Image("file:media/imagens/botoes/BTCARREGAR.png",100,100,true,true));
        botaoCarrega.setStyle("-fx-background-color: transparent");
        
        Button botaoOpcoes = FabricaDeBotoes.criaBotaoComImagem("Opcoes", "", new EventoOpcoes(), new Image("file:media/imagens/botoes/BTOPCOES.png",100,100,true,true));
        botaoOpcoes.setStyle("-fx-background-color: transparent");

        
        Image imageRegras = new Image("file:media/imagens/botoes/BTREGRAS.png",100,100,true,true);
        Button botaoRegras = FabricaDeBotoes.criaBotaoComImagem("Botao_Regras", "", new EventoOpcoes(), imageRegras);
        botaoRegras.setStyle("-fx-background-color: transparent");
                
        Image imageSair = new Image("file:media/imagens/botoes/BTSAIR.png",100,100,true,true);
        Button botaoSair = FabricaDeBotoes.criaBotaoComImagem("Botao_Sair", "", new EventoSair(), imageSair);
        botaoSair.setStyle("-fx-background-color: transparent");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        
        grid.setHgap(218);
        grid.setMinHeight(650);
        grid.setVgap(5);
        grid.setPadding(new Insets(25, 25, 25, 25));        
		
        grid.add(botaoNovoJogo, 2, 1);

        grid.add(botaoCarrega, 2, 2);
        grid.add(botaoRegras, 2, 3);
        grid.add(botaoOpcoes,2, 4);
        grid.add(botaoSair,2, 5);
        
        Group grupo = new Group();
        
        Image telaInicial = new Image("file:media/imagens/telaInicial.png",1120,580,true,true);
        ImageView imv = new ImageView(telaInicial);
        grupo.getChildren().addAll(imv);
		grupo.getChildren().addAll(grid);

        
		return new Scene(grupo, 1048,580);
	}
}