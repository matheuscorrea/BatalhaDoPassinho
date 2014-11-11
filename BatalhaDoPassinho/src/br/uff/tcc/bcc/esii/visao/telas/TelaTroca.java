package br.uff.tcc.bcc.esii.visao.telas;


import com.sun.prism.paint.Color;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import br.uff.tcc.bcc.esii.controlador.ControladorJogo;
import br.uff.tcc.bcc.esii.modelo.Jogo;
import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoChamaTelaAtaque;
import br.uff.tcc.bcc.esii.visao.eventos.EventoCartaBomba;
import br.uff.tcc.bcc.esii.visao.eventos.EventoCartaPorrada;
import br.uff.tcc.bcc.esii.visao.eventos.EventoCartaTiro;
import br.uff.tcc.bcc.esii.visao.eventos.EventoCartaValesca;
import br.uff.tcc.bcc.esii.visao.eventos.EventoMove;
import br.uff.tcc.bcc.esii.visao.eventos.EventoProximaFase;
import br.uff.tcc.bcc.esii.visao.eventos.EventoRealizaTroca;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTelaCartas;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTelaJogo;

public class TelaTroca implements ITela{
	
	private Button cartaTiro;
	private Button cartaPorrada;
	private Button cartaBomba;
	private Button cartaValesca;
	private Button trocar;
	private Button cancelar;
	private GridPane grid;
	private ImageView fundo;
	private int tiro,porrada,bomba,valesca;
	private Label cartasSelecionadas;
	private Image image = new Image("file:media/imagens/mapa/mapaSemBrilho.png");
	
	
	
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
		

		
		fundo = new ImageView();
		fundo.setImage(image);
		
		inferior = new HBox(40);
		botoes = new HBox(40);
		
		
		
		Image iTiro = new Image("file:media/imagens/cartas/tiro.png",300,300,true,true);	
		cartaTiro = FabricaDeBotoes.criaBotaoComImagem("Botao_Tiro", ""+this.tiro, new EventoCartaTiro(), iTiro);		
		cartaTiro.setStyle("-fx-background-color: gold;");
		Image iPorrada = new Image("file:media/imagens/cartas/porrada.png",300,300,true,true);
		cartaPorrada = FabricaDeBotoes.criaBotaoComImagem("Botao_Porrada", ""+this.porrada, new EventoCartaPorrada(), iPorrada);		
		cartaPorrada.setStyle("-fx-background-color: gold;");
		Image iBomba = new Image("file:media/imagens/cartas/bomba.png",300,300,true,true);
		cartaBomba = FabricaDeBotoes.criaBotaoComImagem("Botao_Tiro", ""+this.bomba, new EventoCartaBomba(), iBomba);
		cartaBomba.setStyle("-fx-background-color: gold;");
		Image iValesca = new Image("file:media/imagens/cartas/valesca.png",300,300,true,true);
		cartaValesca = FabricaDeBotoes.criaBotaoComImagem("Botao_Tiro", ""+this.valesca, new EventoCartaValesca(), iValesca);
		cartaValesca.setStyle("-fx-background-color: gold;");
		
		int [] controleDeCartas = ControladorJogo.getInstancia().getCartasSelecionadas();
		trocar = FabricaDeBotoes.criaBotao("Trocar_Cartas", "TROCAR", new EventoRealizaTroca());
		cancelar = FabricaDeBotoes.criaBotao("Voltar_Cartas", "VOLTAR",new EventoTelaJogo());
		trocar.setStyle("-fx-border-color: red;");
		cancelar.setStyle("-fx-border-color: red;");
		botoes.getChildren().addAll(cartaTiro, cartaPorrada, cartaBomba, cartaValesca);
		
		cartasSelecionadas = new Label("Cartas selecionadas: Tiro x"+ controleDeCartas[0] +"  Porrada x"+ controleDeCartas[1] +"  Bomba x"+ controleDeCartas[2] +"  Valesca x"+ controleDeCartas[3]);
		cartasSelecionadas.setStyle("-fx-background-color: greenyellow;");
		inferior.getChildren().addAll(cartasSelecionadas,trocar, cancelar);
		inferior.setStyle("-fx-background-color: cornsilk;");
		
		
		botoes.setAlignment(Pos.BASELINE_CENTER);
		grid = new GridPane();
		grid.add(fundo,2,1);
		grid.add(botoes, 2, 1);
		grid.add(inferior, 2, 2);
        
		return new Scene(grid);
	}
	
	public Scene atualizaBarraTroca(){
		inferior.getChildren().clear();
		
		int [] controleDeCartas = ControladorJogo.getInstancia().getCartasSelecionadas();
		trocar = FabricaDeBotoes.criaBotao("Trocar_Cartas", "TROCAR", new EventoRealizaTroca());
		cancelar = FabricaDeBotoes.criaBotao("Voltar_Cartas", "VOLTAR",new EventoTelaJogo());
		trocar.setStyle("-fx-border-color: red;");
		cancelar.setStyle("-fx-border-color: red;");
		cartasSelecionadas = new Label("Cartas selecionadas: Tiro x"+ controleDeCartas[0] +"  Porrada x"+ controleDeCartas[1] +"  Bomba x"+ controleDeCartas[2] +"  Valesca x"+ controleDeCartas[3]);
		cartasSelecionadas.setStyle("-fx-background-color: greenyellow;");
		
		
		inferior.getChildren().addAll(cartasSelecionadas,trocar, cancelar);	
		inferior.setStyle("-fx-background-color: cornsilk;");
		
		grid = new GridPane();
		grid.add(fundo,2,1);
		grid.add(botoes, 2, 1);
		grid.add(inferior, 2, 2);
        
		return new Scene(grid);
	}

}
