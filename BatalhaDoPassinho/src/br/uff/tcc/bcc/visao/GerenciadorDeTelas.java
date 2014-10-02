package br.uff.tcc.bcc.visao;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GerenciadorDeTelas extends Application{
	
	private Stage primaryStage;
	
	private GerenciadorDeTelas gerenciador;
	
	private GerenciadorDeTelas(){
		
	}

	public GerenciadorDeTelas getInstancia(){
		if (gerenciador == null){
			gerenciador = new GerenciadorDeTelas();
		}
		return gerenciador;
	}
	
	public void mudaTela(Tela tela){
		if(Tela.inicio.equals(tela)){
			Scene cena = ControladorTelaInicial.getInstancia().getScene();
			primaryStage.setScene(cena);
		}else if(Tela.jogo.equals(tela)){
			
		}else if(Tela.opcoes.equals(tela)){
			
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		Scene cena = ControladorTelaInicial.getScene();
		this.primaryStage.setScene(cena);
	};
	
	public enum Tela{
		inicio,
		jogo,
		opcoes
	}

}
