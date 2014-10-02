package br.uff.tcc.bcc.visao;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Classe que distribui os botões na tela inicial
 *
 */
public class TelaInicial extends Application{
	
	public TelaInicial(){
	    
	}    
	
	public void inicia(String... args){
		launch(args);	
	}
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Botao botaoNovoJogo = new Botao(TipoBotao.NOVO_JOGO,"Novo Jogo");
        Botao botaoOpcoes = new Botao(TipoBotao.OPCOES,"Opções");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(botaoNovoJogo, 0, 0);
        grid.add(botaoOpcoes, 1, 0);
        
        primaryStage.setScene(new Scene(grid, 300, 250));
        primaryStage.show();
    }
}












