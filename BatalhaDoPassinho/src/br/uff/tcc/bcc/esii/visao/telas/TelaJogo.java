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
import br.uff.tcc.bcc.esii.modelo.Carta;
import br.uff.tcc.bcc.esii.modelo.Carta.Tipo;
import br.uff.tcc.bcc.esii.modelo.Jogo;
import br.uff.tcc.bcc.esii.modelo.Mapa;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.esii.visao.ConstanteDoTerritorio;
import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoAtaque;
import br.uff.tcc.bcc.esii.visao.eventos.EventoContinuaJogo;
import br.uff.tcc.bcc.esii.visao.eventos.EventoMostraObjetivo;
import br.uff.tcc.bcc.esii.visao.eventos.EventoMove;
import br.uff.tcc.bcc.esii.visao.eventos.EventoOpcoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoPausaJogo;
import br.uff.tcc.bcc.esii.visao.eventos.EventoProximaFase;
import br.uff.tcc.bcc.esii.visao.eventos.EventoSair;
import br.uff.tcc.bcc.esii.visao.eventos.EventoSalvar;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTelaCarregar;
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
	 * Recebe o mapa como parametro para desenhar os botões
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

		Image image = new Image(imagemURL);
		ImageView imageView = new ImageView();
		imageView.setImage(image);

		Button botaoContinua = FabricaDeBotoes.criaBotaoComImagem("Continua", "", new EventoContinuaJogo(),new Image("file:media/imagens/botoes/BTVOLTARPAUSA.png",100,100,true,true));
		Button botaoMenuPricipal = FabricaDeBotoes.criaBotaoComImagem("Menu_Principal","",new EventoTelaInicial(),new Image("file:media/imagens/botoes/BTMAINMENU.png",100,100,true,true));
		Button botaoRegras = FabricaDeBotoes.criaBotaoComImagem("Regras_In_Game", "", new EventoOpcoes(), new Image("file:media/imagens/botoes/BTREGRASINGAME.png",100,100,true,true));
		Button botaoSalvar = FabricaDeBotoes.criaBotaoComImagem("Salvar_Jogo", "", new EventoOpcoes(), new Image("file:media/imagens/botoes/BTSALVAR.png",100,100,true,true));
		Button botaoSairInGame = FabricaDeBotoes.criaBotaoComImagem("Sair_In_Game", "", new EventoSair(), new Image("file:media/imagens/botoes/BTEXIT2.png",100,100,true,true));
		botaoContinua.setStyle("-fx-background-color: transparent");
		botaoMenuPricipal.setStyle("-fx-background-color: transparent");
		botaoRegras.setStyle("-fx-background-color: transparent");
		botaoSalvar.setStyle("-fx-background-color: transparent");
		botaoSairInGame.setStyle("-fx-background-color: transparent");
		
		
		
		
		
		GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        
        grid.add(botaoContinua, 2, 1);
        grid.add(botaoMenuPricipal, 2,2);
        grid.add(botaoRegras,2,3);
        grid.add(botaoSalvar,2,4);
        grid.add(botaoSairInGame,2,5);
		
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

		Image image = new Image(imagemURL);
		ImageView imageView = new ImageView();
		imageView.setImage(image);

		for (Territorio territorio : mapa.getTerritorios()) {
			listaDeBotoesTerritorios.add(FabricaDeBotoes.criaBotaoTerritorio(
					territorio, new EventoTerritorio()));
		}
		
		for (Territorio territorio : mapa.getTerritorios()) {
			listaDeGroupTerritorios.add(FabricaDeBotoes.criaGroupTerritorio(
					territorio, new EventoTerritorio()));
		}
		
		
		
		grupo.getChildren().clear();
		
		grupo.getChildren().addAll(imageView);
		//grupo.getChildren().addAll(listaDeBotoesTerritorios);
		grupo.getChildren().addAll(listaDeGroupTerritorios);
		

		VBox vBox = new VBox(10, grupo, barraInformacoes);
		// 1123x554 mapa
		return new Scene(vBox);
		
	}
	public Scene atualizaBarraInformacoes(Jogo jogo) {
		barraInformacoes.getChildren().clear();
		Button botaoFase = FabricaDeBotoes.criaBotao("Proxima_fase",
				"ACABAR FASE 1", new EventoProximaFase());
		;
		Button botaoMover = FabricaDeBotoes.criaBotao("Mover_tropas",
				"MOVER UMA TROPA", new EventoMove());
		Button botaoAtaque = FabricaDeBotoes.criaBotao("Ataque", "Ataque",
				new EventoAtaque());
		Button botaoTroca = FabricaDeBotoes.criaBotao("Trocar_cartas",
				"TROCAR CARTAS", new EventoTelaCartas());
		Button botaoObjetivo = FabricaDeBotoes.criaBotao("Ver_objetivo",
				"VER OBJETIVO", new EventoMostraObjetivo());
		
		Button botaoPausar = FabricaDeBotoes.criaBotao("Pausar",
				"Pausar", new EventoPausaJogo());
		
		Button botaoSalvar = FabricaDeBotoes.criaBotao("Salvar",
				"Salvar", new EventoSalvar());
	
		
		Image iTiro = new Image("file:media/imagens/cartas/tiro.png",32,32,true,true);	
		Image iPorrada = new Image("file:media/imagens/cartas/porrada.png",32,32,true,true);		
		Image iBomba = new Image("file:media/imagens/cartas/bomba.png",32,32,true,true);
		Image iValesca = new Image("file:media/imagens/cartas/valesca.png",32,32,true,true);		
		int[] vetorCartas = new int[4];		
		List<Carta> listaCartasDoJogador = jogo.getJogadorDaVez().getMao();
		atualizaVetorCartas(vetorCartas, listaCartasDoJogador);
		
		HBox cartas = new HBox(new ImageView(iTiro), new Label("x"+vetorCartas[0]+"  "), 
				new ImageView(iPorrada), new Label("x"+vetorCartas[1]+"  "), 
				new ImageView(iBomba), new Label("x"+vetorCartas[2]+"  "), 
				new ImageView(iValesca), new Label("x"+vetorCartas[3]+"  "));
		
		Image corJogador;
		corJogador = obtemImageCorJogador(jogo);
		
		switch (jogo.faseAtual) {
		case FASE_1:
			if (jogo.getQuantidadeDeTropas() > 0)
				botaoFase.setDisable(true);
			else
				botaoFase.setDisable(false);

			barraInformacoes.getChildren().addAll(new ImageView(corJogador),
					new Label(jogo.getJogadorDaVez().getNome()),
					new Label(jogo.faseAtual.name()),
					new Label("Tropas para distribuir: "+jogo.getQuantidadeDeTropas()), 
					botaoFase,
					botaoObjetivo,
					botaoPausar,
					botaoTroca,
					botaoSalvar,
					cartas);
			break;
		case FASE_2:
			botaoFase.setText("ACABAR FASE 2");
			if (jogo.getQuantidadeDeTropas() > 0)
				botaoFase.setDisable(true);
			else
				botaoFase.setDisable(false);

			barraInformacoes.getChildren().addAll(
					new ImageView(corJogador),
					new Label(jogo.getJogadorDaVez().getNome()),
					new Label(jogo.faseAtual.name()),
					botaoFase,
					botaoAtaque, 
					botaoObjetivo,
					botaoPausar,
					cartas);
			break;
		case FASE_3:
			botaoFase.setText("PASSAR A VEZ");
			barraInformacoes.getChildren().addAll(
					new ImageView(corJogador),
					new Label(jogo.getJogadorDaVez().getNome()),
					new Label(jogo.faseAtual.name()),
					botaoFase,			
					botaoMover,
					botaoObjetivo,
					botaoPausar,
					cartas);
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