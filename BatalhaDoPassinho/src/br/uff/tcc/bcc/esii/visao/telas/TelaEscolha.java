package br.uff.tcc.bcc.esii.visao.telas;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import br.uff.tcc.bcc.esii.controlador.ControladorTelaEscolha;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.ia.JogadorIA;
import br.uff.tcc.bcc.esii.som.Musicas;
import br.uff.tcc.bcc.esii.som.Som;
import br.uff.tcc.bcc.esii.visao.ConstanteDaCor;
import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoCarregaJogo;
import br.uff.tcc.bcc.esii.visao.eventos.EventoCheckBoxIA;
import br.uff.tcc.bcc.esii.visao.eventos.EventoNovoJogo;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTelaInicial;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTrocaPersonagem;

public class TelaEscolha implements ITela  {

	private VBox avatar[];
	
	private HBox hbSuperior;
	private HBox hbInferior;
	private HBox hbBotoes;	
	private int numJogadores;
	private Button iniciar;
	
	public TelaEscolha() {
		numJogadores = 0;
		this.avatar = new VBox[6];
		iniciar = FabricaDeBotoes.criaBotaoComImagem("Iniciar", "", new EventoNovoJogo(), new Image("file:media/imagens/botoes/BTINICIAR.png",100,100,true,true));
        iniciar.setStyle("-fx-background-color: transparent");
	}
	
	@Override
	public Scene getScene() {
		Som.getInstancia().toca(Musicas.ESCOLHA.getID());
		
		Button voltar = FabricaDeBotoes.criaBotaoComImagem("Voltar", "", new EventoTelaInicial(), new Image("file:media/imagens/botoes/BTVOLTAR.png",100,100,true,true));
		voltar.setStyle("-fx-background-color: transparent");
		
		iniciar.setDisable(true);
		String imagemAvatar[] = new String[6];
		Image image[] = new Image[6];
		ImageView imageView[] = new ImageView[6];
		 		
		imagemAvatar[0]=  "file:media/imagens/peoes/PAWNBLUE.png";
		imagemAvatar[1] = "file:media/imagens/peoes/PAWNGREEN.png";
		imagemAvatar[2] = "file:media/imagens/peoes/PAWNGREY.png";
		imagemAvatar[3] = "file:media/imagens/peoes/PAWNPINK.png";
		imagemAvatar[4] = "file:media/imagens/peoes/PAWNRED.png";
		imagemAvatar[5] = "file:media/imagens/peoes/PAWNYELLOW.png";
		
		for (int i = 0; i < imagemAvatar.length; i++) {
			image[i] = new Image(imagemAvatar[i]);
			imageView[i] = new ImageView();
			imageView[i].setFitHeight(150);
			imageView[i].setFitWidth(150);
			imageView[i].setImage(image[i]);
		}
		
		ComboBox vetorComboBox[] = new ComboBox[6];
		String nomePersonagem[]={"-","Jogador","Catra","Anitta","Nego Bam","Claudinho & Buchecha","Mc Koringa","Perlla"};
		CheckBox vetorCheckBox[] = new CheckBox[6];
		 
		for (int i = 0; i < vetorComboBox.length; i++) {
			vetorComboBox[i] = new ComboBox();
			vetorCheckBox[i] = new CheckBox("IA");
			vetorCheckBox[i].setSelected(false);			
			vetorComboBox[i].getItems().addAll(nomePersonagem);
			avatar[i] = new VBox(5,imageView[i],vetorComboBox[i],vetorCheckBox[i]);
		}
		
		vetorComboBox[0].getSelectionModel().select(0);
		vetorComboBox[1].getSelectionModel().select(0);
		vetorComboBox[2].getSelectionModel().select(0);
		vetorComboBox[3].getSelectionModel().select(0);
		vetorComboBox[4].getSelectionModel().select(0);
		vetorComboBox[5].getSelectionModel().select(0);
		
		for (int i = 0; i < vetorComboBox.length; i++) {
			vetorComboBox[i].getSelectionModel().selectedItemProperty().addListener(new EventoTrocaPersonagem(i));	
			vetorCheckBox[i].selectedProperty().addListener(new EventoCheckBoxIA());	
		}
		
		hbSuperior = new HBox(80,avatar[0],avatar[1],avatar[2]);
		hbInferior = new HBox(80,avatar[3],avatar[4],avatar[5]);
		hbBotoes = new HBox(100,voltar,iniciar);

		VBox raiz = new VBox(10,hbSuperior,hbInferior,hbBotoes); 
		atualizaListaJogadores();
		numJogadores = 3;
		raiz.setTranslateX(280);
		
		
		Group grupo = new Group();
		Image fundo = new Image("file:media/imagens/plateia.png",1120,580,true,true);
	    ImageView imv = new ImageView(fundo);
	       
	    grupo.getChildren().addAll(imv);
		grupo.getChildren().addAll(raiz);
		
		
		return new Scene(grupo);
	}

	public Scene atualizaPersonagem(String jogador,int index){
		for (int i = 0; i < avatar.length; i++) {
			if(i!=index){
				ComboBox comboBox = (ComboBox)avatar[i].getChildren().get(1);
				String nomePersonagem = (String)comboBox.getSelectionModel().getSelectedItem();
				if(jogador.equals(nomePersonagem)){
					comboBox.getSelectionModel().select(0);					
				}
			}else{
				ComboBox comboBox = (ComboBox)avatar[i].getChildren().get(1);
				String nomePersonagem = (String)comboBox.getSelectionModel().getSelectedItem();
				ImageView imageView =(ImageView) avatar[i].getChildren().get(0);
				Image image = new Image("file:media/imagens/mcs/"+nomePersonagem+".png");
				imageView.setImage(image);
			}
			
		}	
		VBox raiz = new VBox(10,hbSuperior,hbInferior,hbBotoes);	
		raiz.setLayoutX(200);
		Group grupo = new Group();
		Image fundo = new Image("file:media/imagens/plateia.png",1120,580,true,true);
	    ImageView imv = new ImageView(fundo);
	       
	    grupo.getChildren().addAll(imv);
		grupo.getChildren().addAll(raiz);
		
		atualizaListaJogadores();
		
		return new Scene(grupo);
	}
	
	public Scene atualizaCheckBox(){
		atualizaListaJogadores();
		VBox raiz = new VBox(10,hbSuperior,hbInferior,hbBotoes);	
		raiz.setLayoutX(200);
		Group grupo = new Group();
		Image fundo = new Image("file:media/imagens/plateia.png",1120,580,true,true);
	    ImageView imv = new ImageView(fundo);
	       
	    grupo.getChildren().addAll(imv);
		grupo.getChildren().addAll(raiz);
		
		return new Scene(grupo);
	}
	
	/**
	 * Confere quais personagens estão selecionados e atualiza a lista de 
	 * jogadores do ControladorTelaEscolha
	 */
	private void atualizaListaJogadores(){
		List<Jogador> listaJogadores = new ArrayList<Jogador>();
		numJogadores=0;
		ConstanteDaCor[] vetorConstanteDaCor = ConstanteDaCor.values();
		for (int i = 0; i < avatar.length; i++) {
			ComboBox comboBox = (ComboBox)avatar[i].getChildren().get(1);
			CheckBox checkBox = (CheckBox)avatar[i].getChildren().get(2);
			
			String nomePersonagem = (String)comboBox.getSelectionModel().getSelectedItem();
			if(!"-".equals(nomePersonagem)){
				numJogadores++;
				if(checkBox.selectedProperty().get())
					listaJogadores.add(new JogadorIA(nomePersonagem,vetorConstanteDaCor[i]));	
				else	
					listaJogadores.add(new Jogador(nomePersonagem,vetorConstanteDaCor[i]));	
			}
		}
		if(numJogadores > 1){		
			iniciar.setDisable(false);
		}else{
			iniciar.setDisable(true);
		}
		ControladorTelaEscolha.getInstancia().setListaJogadores(listaJogadores);
	}	
	
	
}
