package br.uff.tcc.bcc.esii.visao.telas;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import br.uff.tcc.bcc.esii.controlador.ControladorJogo;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.esii.modelo.ia.JogadorIA;
import br.uff.tcc.bcc.esii.som.Musicas;
import br.uff.tcc.bcc.esii.som.Som;
import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas;
import br.uff.tcc.bcc.esii.visao.eventos.EventoAtaque;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTelaAtaquePassarTropas;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTelaJogo;

public class TelaAtaque implements ITela {

	Territorio territorioAtacante;
	Territorio territorioDefensor;
	Jogador jogadorAtacante;
	Jogador jogadorDefensor;

	List<Integer> dados_atacante;
	List<Integer> dados_defensor;

	boolean venceu=false;
	
	Button botaoAtaque;

	public TelaAtaque(Territorio territorioAtacante,
			Territorio territorioDefensor) {
		this.territorioAtacante = territorioAtacante;
		this.territorioDefensor = territorioDefensor;
		this.jogadorAtacante = territorioAtacante.getDono();
		this.jogadorDefensor = territorioDefensor.getDono();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.uff.tcc.bcc.esii.visao.telas.ITela#getScene()
	 */
	@Override
	public Scene getScene() {

		final String imagemMapa = "file:media/imagens/mapa/mapaSemBrilho.png";

		Image image = new Image(imagemMapa,ConstantesTelas.resolucaoX,ConstantesTelas.resolucaoY,false,true);
		ImageView imageView = new ImageView();
		imageView.setImage(image);

		Label msgVitoria = new Label("Você venceu");
		msgVitoria.setTextFill(Color.WHITE);
		
		Label nomeJogadorAtacante = new Label(jogadorAtacante.getNome());
		Label qtdTropasJogadorAtacante = new Label(
				territorioAtacante.getQuantidadeTropa() + "");
		nomeJogadorAtacante.setTextFill(Color.WHITE);
		qtdTropasJogadorAtacante.setTextFill(Color.WHITE);

		Label nomeJogadorDefensor = new Label(jogadorDefensor.getNome());
		Label qtdTropasJogadorDefensor = new Label(
				territorioDefensor.getQuantidadeTropa() + "");
		nomeJogadorDefensor.setTextFill(Color.WHITE);
		qtdTropasJogadorDefensor.setTextFill(Color.WHITE);

		int indiceDaColuna = 0;
		int indiceDaLinha = 0;

		botaoAtaque = FabricaDeBotoes.criaBotaoComImagem("Ataque", "",
				new EventoAtaque(), new Image(
						"file:media/imagens/botoes/BTROLL.png", 100, 100,
						true, true));
		botaoAtaque.setStyle("-fx-background-color: transparent");
		Button botaoSair = FabricaDeBotoes.criaBotaoComImagem("Sair", "",
				new EventoTelaJogo(), new Image("file:media/imagens/botoes/BTRECUAR.png", 100,100,true,true));
		botaoSair.setStyle("-fx-background-color: transparent");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		//grid.setGridLinesVisible(true);

		grid.add(nomeJogadorAtacante, 0, 0);
		grid.add(qtdTropasJogadorAtacante, 0, 1);
		
		indiceDaLinha = 2;
		if (dados_atacante != null) {
			for (Integer i : dados_atacante) {
				grid.add(intToDadoAtq(i.intValue()), 0,
						indiceDaLinha++);
			}
		}

//		indiceDaColuna = 0;
//		indiceDaLinha = 1;
//					
		if(venceu){			
			grid.add(msgVitoria, 1, 0);

		}
		
		grid.add(botaoAtaque, 1, 1);
		grid.add(botaoSair, 1, 2);

		indiceDaColuna = 3;
		indiceDaLinha = 2;
		grid.add(nomeJogadorDefensor, 2, 0);
		grid.add(qtdTropasJogadorDefensor,2, 1);

		indiceDaLinha = 2;
		if (dados_defensor != null) {
			for (Integer i : dados_defensor) {
				grid.add(intToDadoDef(i.intValue()), 2,
						indiceDaLinha++);
			}
		}

		if(venceu){
			botaoAtaque.setDisable(true);
			botaoSair.setDisable(true);
			Som.getInstancia().tocaEfeito(Musicas.EFEITO_SUCESSO1.getID());
			if(territorioAtacante.getQuantidadeTropa()>1){
				Button btUm = FabricaDeBotoes.criaBotaoComImagem("1", "",
						new EventoTelaAtaquePassarTropas(), new Image("file:media/imagens/peoes/tropas1.png", 100,100,false,false));
				btUm.setStyle("-fx-background-color: transparent");
				grid.add(btUm, 0, 5);
			}if(territorioAtacante.getQuantidadeTropa()>2){				
				Button btDois = FabricaDeBotoes.criaBotaoComImagem("2", "",
						new EventoTelaAtaquePassarTropas(), new Image("file:media/imagens/peoes/tropas2.png", 100,100,false,false));
				btDois.setStyle("-fx-background-color: transparent");
				grid.add(btDois, 1, 5);
			}if(territorioAtacante.getQuantidadeTropa()>3){
				Button btTres = FabricaDeBotoes.criaBotaoComImagem("3", "",
						new EventoTelaAtaquePassarTropas(), new Image("file:media/imagens/peoes/tropas3.png", 100,100,false,false));
				btTres.setStyle("-fx-background-color: transparent");
				grid.add(btTres, 2, 5);
			}
			
		}
		grid.setLayoutX(ConstantesTelas.resolucaoX/2 - 200);
		grid.getColumnConstraints().addAll(new ColumnConstraints(100));
		grid.getRowConstraints().addAll(new RowConstraints(100));

		Group grupo = new Group();

		grupo.getChildren().addAll(imageView);
		grupo.getChildren().addAll(grid);
		
		if (territorioAtacante.getDono().getObjetivo().concluido(territorioAtacante.getDono(),territorioDefensor.getDono())) {
			TelaFimJogo telaFimJogo = new TelaFimJogo(jogadorAtacante);
			return telaFimJogo.getScene();
		}
		
		return new Scene(grupo);
	}

	public void ataque() {

		if (validaAtaque()) {

			dados_atacante = calculaDado(territorioAtacante
					.getQuantidadeTropa() - 1);
			dados_defensor = calculaDado(territorioDefensor
					.getQuantidadeTropa());

			if (ataque(territorioAtacante, territorioDefensor, dados_atacante,
					dados_defensor)) {

				// Jogador acabou de perder seu último território
				if (territorioDefensor.getDono().numeroDeConquistados() == 1) {
					ControladorJogo.getInstancia().eliminaJogador();
				}
				
				//ControladorJogo.getInstancia().dominarTerritorio(1);
				if (ControladorJogo.getInstancia().getJogadorDaVez() instanceof JogadorIA){
					ControladorJogo.getInstancia().dominarTerritorio(Math.min(territorioAtacante.getQuantidadeTropa()-1, 3));
				}
				
				GerenciadorDeTelas.getInstancia().atualizaImageBotao(
						territorioDefensor.getNome(),
						FabricaDeBotoes.criaImagemDoBotaoTerritorio(territorioDefensor));
				if(territorioAtacante.getQuantidadeTropa()>1){
					venceu=true;
				}
			}
		} else {
			botaoAtaque.setDisable(true);			
		}

	}

	private boolean validaAtaque() {
		if (territorioAtacante.getDono() != territorioDefensor.getDono()) {
			if (territorioAtacante.getQuantidadeTropa() > 1) {
				return true;
			}
		}
		return false;
	}

	private List<Integer> calculaDado(int qtdTropas) {
		int qtd_dados = Math.min(3, qtdTropas);
		List<Integer> dados = new LinkedList<>();
		for (int i = 0; i < qtd_dados; i++) {
			dados.add(dado());
		}
		Collections.sort(dados, Collections.reverseOrder());
		return dados;
	}

	private int dado() {
		Random random = new Random();
		return random.nextInt(6) + 1;
	}

	private boolean ataque(Territorio atacante, Territorio defensor,
			List<Integer> dados_atacante, List<Integer> dados_defensor) {
		for (int i = 0; i < dados_atacante.size() && i < dados_defensor.size(); i++) {
			if (dados_atacante.get(i) > dados_defensor.get(i)) {
				defensor.setQuantidadeTropa(defensor.getQuantidadeTropa() - 1);
			} else {
				atacante.setQuantidadeTropa(atacante.getQuantidadeTropa() - 1);
			}
		}
		// Jogador da vez conquistou territorio defensor
		if (defensor.getQuantidadeTropa() == 0) {
			// jogadorDominouTerritorio = true;
			ControladorJogo.getInstancia().dominouTerrritorio = true;
			return true;
		} else {
			return false;
		}
	}

	private ImageView intToDado(String complemento, int valorDado) {

		String imagemURL = "file:media/imagens/dados/d" + complemento
				+ valorDado + ".png";

		Image image = new Image(imagemURL);
		ImageView imageView = new ImageView();
		imageView.setFitHeight(20);
		imageView.setFitWidth(20);
		imageView.setImage(image);

		return imageView;
	}

	private ImageView intToDadoAtq(int valorDado) {
		return intToDado("atq", valorDado);
	}

	private ImageView intToDadoDef(int valorDado) {
		return intToDado("def", valorDado);
	}
}
