package br.uff.tcc.bcc.esii.visao.telas;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import br.uff.tcc.bcc.esii.modelo.Carta;
import br.uff.tcc.bcc.esii.modelo.Jogo;
import br.uff.tcc.bcc.esii.modelo.Mapa;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.esii.modelo.ia.JogadorIA;
import br.uff.tcc.bcc.esii.som.eventos.EventoMutaJogo;
import br.uff.tcc.bcc.esii.som.eventos.EventoRestauraVolume;
import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoChamaTelaAtaque;
import br.uff.tcc.bcc.esii.visao.eventos.EventoContinuaJogo;
import br.uff.tcc.bcc.esii.visao.eventos.EventoJogadaIA;
import br.uff.tcc.bcc.esii.visao.eventos.EventoMostraObjetivo;
import br.uff.tcc.bcc.esii.visao.eventos.EventoMove;
import br.uff.tcc.bcc.esii.visao.eventos.EventoPausaJogo;
import br.uff.tcc.bcc.esii.visao.eventos.EventoProximaFase;
import br.uff.tcc.bcc.esii.visao.eventos.EventoRegrasJogo;
import br.uff.tcc.bcc.esii.visao.eventos.EventoSair;
import br.uff.tcc.bcc.esii.visao.eventos.EventoSalvar;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTelaCartas;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTelaInicial;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTerritorio;

public class TelaJogo implements ITela {

	private Mapa mapa;
	private List<Button> listaDeBotoesTerritorios;
	private List<Group> listaDeGroupTerritorios;
	private Group grupo;
	private HBox barraInformacoes;
	
	
	private enum Estado{JOGANDO,PAUSADO};
	private Estado estadoAtual;

	/**
	 * Construtor da classe TelaJogo <br>
	 * Recebe o mapa como parametro para desenhar os bot�es
	 * 
	 * @param mapa
	 */
	public TelaJogo(Mapa mapa) {
		this.mapa = mapa;
		this.barraInformacoes = new HBox(20);
		this.grupo = new Group();
		listaDeBotoesTerritorios = new ArrayList<Button>();
		listaDeGroupTerritorios = new ArrayList<Group>();
		estadoAtual=Estado.JOGANDO;
	}
	
	public List<Button> getListaDeBotoesTerritorios() {
		return listaDeBotoesTerritorios;
	}
	
	public void pausaJogo(){
		estadoAtual=Estado.PAUSADO;
	}
	
	public void continuaJogo(){
		estadoAtual=Estado.JOGANDO;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.uff.tcc.bcc.visao.telas.ITela#getScene()
	 */
	@Override
	public Scene getScene() {

		if(estadoAtual==Estado.JOGANDO){
			return getSceneJogo();
		}
		else{
			return getScenePausa();
		}
			
	}

	private Scene getScenePausa(){
		final String imagemURL = "file:media/imagens/mapa/mapaSemBrilho.png";

		Image image = new Image(imagemURL,ConstantesTelas.resolucaoX,ConstantesTelas.resolucaoY,false,true);
		ImageView imageView = new ImageView();
		imageView.setImage(image);

		Button botaoContinua = FabricaDeBotoes.criaBotaoComImagem("Continua", "", new EventoContinuaJogo(),new Image("file:media/imagens/botoes/BTVOLTARPAUSA.png",100,100,true,true));
		Button botaoMenuPricipal = FabricaDeBotoes.criaBotaoComImagem("Menu_Principal","",new EventoTelaInicial(),new Image("file:media/imagens/botoes/BTMAINMENU.png",100,100,true,true));
		Button botaoRegras = FabricaDeBotoes.criaBotaoComImagem("Regras2", "", new EventoRegrasJogo(), new Image("file:media/imagens/botoes/BTREGRASINGAME.png",100,100,true,true));
		Button botaoSalvar = FabricaDeBotoes.criaBotaoComImagem("Salvar_Jogo", "", new EventoSalvar(), new Image("file:media/imagens/botoes/BTSALVAR.png",100,100,true,true));
		Button botaoSairInGame = FabricaDeBotoes.criaBotaoComImagem("Sair_In_Game", "", new EventoSair(), new Image("file:media/imagens/botoes/BTEXIT2.png",100,100,true,true));
		Button botaoVolume = FabricaDeBotoes.criaBotaoComImagem("Permitir_Volume", "", new EventoRestauraVolume(), new Image("file:media/imagens/botoes/BTSOUND.png",50,50,true,true));
	    Button botaoMudo = FabricaDeBotoes.criaBotaoComImagem("Tirar_Volume", "", new EventoMutaJogo(), new Image("file:media/imagens/botoes/BTMUTE.png",50,50,true,true));
		
	    
		botaoContinua.setStyle("-fx-background-color: transparent");
		botaoMenuPricipal.setStyle("-fx-background-color: transparent");
		botaoRegras.setStyle("-fx-background-color: transparent");
		botaoSalvar.setStyle("-fx-background-color: transparent");
		botaoSairInGame.setStyle("-fx-background-color: transparent");
		botaoVolume.setStyle("-fx-background-color: transparent");
		botaoMudo.setStyle("-fx-background-color: transparent");
		
		HBox volume = new HBox();
		volume.getChildren().addAll(botaoVolume, botaoMudo);
		volume.setTranslateX(-6);
				
		GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
//        grid.setGridLinesVisible(true);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(25, 25, 25, 25));
        
        grid.add(volume,0,1);
        grid.add(botaoContinua, 0, 2);
        grid.add(botaoMenuPricipal, 0,3);
        grid.add(botaoRegras,0,4);
        grid.add(botaoSalvar,0,5);
        grid.add(botaoSairInGame,0,6);
        
        grid.setLayoutX(ConstantesTelas.resolucaoX/2 - 50);
        grid.setLayoutY(ConstantesTelas.resolucaoY/5);

		
        Group grupoPausa = new Group();
        
        grupoPausa.getChildren().addAll(imageView);
		grupoPausa.getChildren().addAll(grid);
        
		return new Scene(grupoPausa);
	}
	
	

	public void atualizaImageNoBotao(String idBotao,ImageView imageView){
		for (Group gTerritorio:listaDeGroupTerritorios){
			Object[] vetorElementoGroup =  gTerritorio.getChildren().toArray();
			if (vetorElementoGroup[1] instanceof Button) {
				if(idBotao.equals(((Button)vetorElementoGroup[1]).getId())){
					gTerritorio.getChildren().clear();
					gTerritorio.getChildren().addAll(imageView,(Button)vetorElementoGroup[1]);
				}
			}
		}	
	}
	
	private Scene getSceneJogo(){

		final String imagemURL = "file:media/imagens/mapa/mapa.jpg";
		Image image = new Image(imagemURL,ConstantesTelas.resolucaoX,ConstantesTelas.resolucaoY-120,false,true);
		ImageView imageView = new ImageView();
		imageView.setImage(image);

		listaDeBotoesTerritorios.clear();
		for (Territorio territorio : mapa.getTerritorios()) {
			listaDeBotoesTerritorios.add(FabricaDeBotoes.criaBotaoTerritorio(
					territorio, new EventoTerritorio()));
		}
		
		listaDeGroupTerritorios.clear();
		for (Territorio territorio : mapa.getTerritorios()) {
			listaDeGroupTerritorios.add(FabricaDeBotoes.criaGroupTerritorio(
					territorio, new EventoTerritorio()));
		}		
		
		grupo.getChildren().clear();
		grupo.getChildren().addAll(imageView);
		grupo.getChildren().addAll(listaDeGroupTerritorios);
		
		VBox vBox = new VBox(10, grupo, barraInformacoes);
		vBox.setStyle("-fx-background-color: grey");

		// 1123x554 mapa
		return new Scene(vBox);
		
	}

	public Scene attJogo(){
		return getSceneJogo();
	}

	public Scene atualizaBarraInformacoes(Jogo jogo) {
		barraInformacoes.getChildren().clear();
		Button botaoFase = FabricaDeBotoes.criaBotao("Proxima_fase",
				"", new EventoProximaFase());
	
		Button botaoMover = FabricaDeBotoes.criaBotaoComImagem("Mover_tropas",
				"", new EventoMove(),new Image("file:media/imagens/botoes/BTMOVE.png",125,125,true,true));
		botaoMover.setStyle("-fx-background-color: transparent");
		
		Button botaoAtaque = FabricaDeBotoes.criaBotaoComImagem("Ataque", "",
				new EventoChamaTelaAtaque(),new Image("file:media/imagens/botoes/BTATACAR.png",125,125,true,true) );
		botaoAtaque.setStyle("-fx-background-color: transparent");
		
		Button botaoTroca = FabricaDeBotoes.criaBotaoComImagem("Trocar_cartas",
				"", new EventoTelaCartas(),new Image("file:media/imagens/botoes/BTCARTAS.png",125,125,true,true) );
		botaoTroca.setStyle("-fx-background-color: transparent");
		
		Button botaoObjetivo = FabricaDeBotoes.criaBotaoComImagem("Ver_objetivo",
				"", new EventoMostraObjetivo(), new Image("file:media/imagens/botoes/BTOBJETIVO.png",125,125,true,true));
		
		botaoObjetivo.setStyle("-fx-background-color: transparent");
		
		Button botaoPausar = FabricaDeBotoes.criaBotaoComImagem("Pausar",
				"", new EventoPausaJogo(), new Image("file:media/imagens/botoes/BTMENU.png",125,40,true,true));
		botaoPausar.setStyle("-fx-background-color: transparent");
		
		Button botaoJogadaIA = FabricaDeBotoes.criaBotaoComImagem("PROXIMO_PASSO",
				"", new EventoJogadaIA(), new Image("file:media/imagens/botoes/BTPROXIMOPASSO.png",125,40,true,true));
		botaoJogadaIA.setStyle("-fx-background-color: transparent");

		
		Image iTiro = new Image("file:media/imagens/cartas/tiro.png",32,32,true,true);	
		Image iPorrada = new Image("file:media/imagens/cartas/porrada.png",32,32,true,true);		
		Image iBomba = new Image("file:media/imagens/cartas/bomba.png",32,32,true,true);
		Image iValesca = new Image("file:media/imagens/cartas/valesca.png",32,32,true,true);		
		int[] vetorCartas = new int[4];		
		List<Carta> listaCartasDoJogador = jogo.getJogadorDaVez().getMao();
		atualizaVetorCartas(vetorCartas, listaCartasDoJogador);
		
		Label carta1 = new Label("x"+vetorCartas[0]+"  ");
		carta1.setTextFill(Color.WHITE);
		Label carta2 = new Label("x"+vetorCartas[1]+"  ");
		carta2.setTextFill(Color.WHITE);
		Label carta3 = new Label("x"+vetorCartas[2]+"  ");
		carta3.setTextFill(Color.WHITE);
		Label carta4 = new Label("x"+vetorCartas[3]+"  ");
		carta4.setTextFill(Color.WHITE);
		
		HBox cartas = new HBox(new ImageView(iTiro), carta1, 
				new ImageView(iPorrada), carta2, 
				new ImageView(iBomba), carta3, 
				new ImageView(iValesca), carta4);
		
		Image corJogador;
		corJogador = obtemImageCorJogador(jogo);
		
		Label tropasParaDistribuir = new Label("Tropas para distribuir: "+jogo.getQuantidadeDeTropas());
		tropasParaDistribuir.setTextFill(Color.WHITE);
		
		switch (jogo.faseAtual) {
		case FASE_1:
			botaoFase = FabricaDeBotoes.criaBotaoComImagem("Proxima_fase",
				"", new EventoProximaFase(), new Image("file:media/imagens/botoes/BTTROPASDISTRIBUIDAS.png",125,125,true,true));
			botaoFase.setStyle("-fx-background-color: transparent");
			
			if(jogo.getJogadorDaVez() instanceof JogadorIA){
				barraInformacoes.getChildren().addAll(new ImageView(corJogador),
						jogo.getJogadorDaVez().getFoto(),
						tropasParaDistribuir, 
						botaoJogadaIA,
						botaoPausar,
						new ImageView(new Image("file:media/imagens/botoes/BANNERIA1.png",125,125,true,true)));
			}else{
				
				if (jogo.getQuantidadeDeTropas() > 0)
					botaoFase.setDisable(true);
				else
					botaoFase.setDisable(false);
	
				barraInformacoes.getChildren().addAll(new ImageView(corJogador),
						jogo.getJogadorDaVez().getFoto(),
						tropasParaDistribuir, 
						botaoFase,
						botaoObjetivo,
						botaoTroca,
						botaoPausar,
						cartas);
			}
			break;
		case FASE_2:
			botaoFase = FabricaDeBotoes.criaBotaoComImagem("Proxima_fase",
					"", new EventoProximaFase(), new Image("file:media/imagens/botoes/BTFINALIZARATAQUE.png",125,125,true,true));
			botaoFase.setStyle("-fx-background-color: transparent");
			
			if(jogo.getJogadorDaVez() instanceof JogadorIA){
				barraInformacoes.getChildren().addAll(new ImageView(corJogador),
						jogo.getJogadorDaVez().getFoto(),
						botaoJogadaIA,
						botaoPausar,
						new ImageView(new Image("file:media/imagens/botoes/BANNERIA2.png",125,125,true,true)));
			}else{
				barraInformacoes.getChildren().addAll(
						new ImageView(corJogador),
						jogo.getJogadorDaVez().getFoto(),
						botaoFase,
						botaoAtaque, 
						botaoObjetivo,
						botaoPausar,
						cartas);
			}
			break;
		case FASE_3:
			botaoFase = FabricaDeBotoes.criaBotaoComImagem("Proxima_fase",
					"", new EventoProximaFase(), new Image("file:media/imagens/botoes/BTMOVIMENTOS COMPLETOS.png",135,125,true,true));
			botaoFase.setStyle("-fx-background-color: transparent");
			
			if(jogo.getJogadorDaVez() instanceof JogadorIA){
				barraInformacoes.getChildren().addAll(new ImageView(corJogador),
						jogo.getJogadorDaVez().getFoto(),
						botaoJogadaIA,
						botaoPausar,
						new ImageView(new Image("file:media/imagens/botoes/BANNERIA3.png",125,125,true,true)));
			}else{
				barraInformacoes.getChildren().addAll(
						new ImageView(corJogador),
						jogo.getJogadorDaVez().getFoto(),
						botaoFase,			
						botaoMover,
						botaoObjetivo,
						botaoPausar,
						cartas);
			}
			break;
		default:
			barraInformacoes.getChildren().addAll(
					new ImageView(corJogador),
					new Label(jogo.getJogadorDaVez().getNome()),
					new Label(jogo.faseAtual.name()),
					botaoFase,			
					botaoObjetivo,
					botaoPausar,
					cartas);
			break;
		}
	
		VBox vBox = new VBox(10, grupo, barraInformacoes);
		vBox.setStyle("-fx-background-color: grey");
		if(estadoAtual==Estado.JOGANDO){
			return new Scene(vBox);
		}
		else{
			return new Scene(new VBox(10,grupo));
		}
	}

	private void atualizaVetorCartas(int[] vetorCartas,
			List<Carta> listaCartasDoJogador) {
		for(Carta carta : listaCartasDoJogador){
			switch (carta.getTipo()){
			case TIRO:
				vetorCartas[0]++;
				break;
			case PORRADA:
				vetorCartas[1]++;
				break;
			case BOMBA:
				vetorCartas[2]++;
				break;
			case VALESCA:
				vetorCartas[3]++;
				break;
			}			
		}
	}

	private Image obtemImageCorJogador(Jogo jogo) {
		Image corJogador;
		switch (jogo.getJogadorDaVez().getCor()){
			case AZUL: corJogador = new Image("file:media/imagens/peoes/PAWNBLUE.png",20,20,true,true);
				break;
			case VERDE: corJogador = new Image("file:media/imagens/peoes/PAWNGREEN.png",20,20,true,true);	
				break;
			case CINZA: corJogador = new Image("file:media/imagens/peoes/PAWNGREY.png",20,20,true,true);	
				break;
			case ROSA: corJogador = new Image("file:media/imagens/peoes/PAWNPINK.png",20,20,true,true);	
				break;
			case VERMELHO: corJogador = new Image("file:media/imagens/peoes/PAWNRED.png",20,20,true,true);	
				break;
			default: corJogador = new Image("file:media/imagens/peoes/PAWNYELLOW.png",20,20,true,true);				
		}
		return corJogador;
	}	
}
