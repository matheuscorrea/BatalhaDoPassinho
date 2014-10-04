package br.uff.tcc.bcc.visao.telas;

import br.uff.tcc.bcc.visao.Botao;
import br.uff.tcc.bcc.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.visao.eventos.EventoNovoJogo;
import br.uff.tcc.bcc.visao.eventos.EventoOpcoes;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Classe que implementa a distribuição dos botões na tela inicial
 *
 */
public class TelaInicial implements ITela {

	public TelaInicial() {

	}

	/*
	 * (non-Javadoc)
	 * @see br.uff.tcc.bcc.visao.telas.ITela#getScene()
	 */
	@Override
	public Scene getScene() {

        Botao botaoNovoJogo = FabricaDeBotoes.criaBotao("NOVO_JOGO", "Novo jogo", new EventoNovoJogo());
        Botao botaoOpcoes = FabricaDeBotoes.criaBotao("OPCOES", "Opcoes", new EventoOpcoes());
        
        
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
