package br.uff.tcc.bcc.visao;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TelaInicial extends Application{
	
	public TelaInicial(){
	    
	}    
	
	public void inicia(String... args){
		launch(args);
	
	}
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        BotaoTelaInicial botao = new BotaoTelaInicial("botão tela inicial");
              
        StackPane root = new StackPane();
        root.getChildren().add(botao);
        
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
    
   
}
