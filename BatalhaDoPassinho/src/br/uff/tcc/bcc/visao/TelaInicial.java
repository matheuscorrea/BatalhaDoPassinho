package br.uff.tcc.bcc.visao;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Classe que distribui os botões na tela inicial
 *
 */
public class TelaInicial implements Tela {

	public TelaInicial() {

	}

	@Override
	public Scene getScene() {

		Botao x = FabricaDeBotoes.criaBotao("NovoJogo", "Novo Jogo",
				new EventoNovoJogo());

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		grid.add(x, 0, 0);
		return new Scene(grid, 300, 250);

	}
}
