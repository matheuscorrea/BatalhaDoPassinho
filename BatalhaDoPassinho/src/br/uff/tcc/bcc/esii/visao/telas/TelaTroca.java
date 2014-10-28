package br.uff.tcc.bcc.esii.visao.telas;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTelaJogo;

public class TelaTroca implements ITela{
	
	private Button cartaTiro;
	private Button cartaPorrada;
	private Button cartaBomba;
	private Button cartaValesca;
	private int tiro,porrada,bomba,valesca;
	
	
	private HBox botoes;
	private HBox inferior;
		

	public TelaTroca(int [] valores) {
		this.tiro = valores[0];
		this.porrada = valores[1];
		this.bomba = valores[2];
		this.valesca = valores[3];
	}
	
	

	@Override
	public Scene getScene() {
		inferior = new HBox(40);
		botoes = new HBox(40);
		
		Image tiro = new Image("file:media/imagens/cartas/tiro.png",320,320,true,true);	
		cartaTiro = new Button(""+this.tiro,new ImageView(tiro));		
		
		Image porrada = new Image("file:media/imagens/cartas/porrada.png",320,320,true,true);
		cartaPorrada = new Button(""+this.porrada,new ImageView(porrada));		
		
		Image bomba = new Image("file:media/imagens/cartas/bomba.png",320,320,true,true);
		cartaBomba = new Button(""+this.bomba,new ImageView(bomba));
		
		Image valesca = new Image("file:media/imagens/cartas/valesca.png",320,320,true,true);
		cartaValesca = new Button(""+this.valesca,new ImageView(valesca));
		
		
//		Button trocar = FabricaDeBotoes.criaBotao("Trocar_Cartas", "TROCAR", new EventoOpcoes());
		Button cancelar = FabricaDeBotoes.criaBotao("Voltar_Cartas", "VOLTAR",new EventoTelaJogo());
		botoes.getChildren().addAll(cartaTiro, cartaPorrada, cartaBomba, cartaValesca);
		inferior.getChildren().addAll(cancelar);
		VBox raiz = new VBox(10, botoes, inferior); 	
		
		return new Scene(raiz);
	}

}
