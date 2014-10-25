package br.uff.tcc.bcc.esii.visao.telas;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import br.uff.tcc.bcc.esii.controlador.ControladorTelaEscolha;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.visao.ConstanteDaCor;
import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoNovoJogo;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTelaInicial;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTrocaPersonagem;

public class TelaEscolha implements ITela  {

	private VBox avatar[];
	
	private HBox hbSuperior;
	private HBox hbInferior;
	private HBox hbButoes;	

	public TelaEscolha() {
		this.avatar = new VBox[6];		
	}
	
	@Override
	public Scene getScene() {
		Button iniciar = FabricaDeBotoes.criaBotao("Iniciar", "Iniciar",new EventoNovoJogo());
		Button voltar = FabricaDeBotoes.criaBotao("Voltar", "Voltar",new EventoTelaInicial());
		
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
			imageView[i].setFitHeight(100);
			imageView[i].setFitWidth(100);
			imageView[i].setImage(image[i]);
		}
		
		ComboBox vetorComboBox[] = new ComboBox[6];
		String nomePersonagem[]={"-","Jogador","Catra","Anitta","Nego Bam","Claudinho&Buchecha","Mc Koringa","PerLla"};
		
		for (int i = 0; i < vetorComboBox.length; i++) {
			vetorComboBox[i] = new ComboBox();
			vetorComboBox[i].getItems().addAll(nomePersonagem);
			avatar[i] = new VBox(5,imageView[i],vetorComboBox[i]);
		}
		
		vetorComboBox[0].getSelectionModel().select(1);
		vetorComboBox[1].getSelectionModel().select(2);
		vetorComboBox[2].getSelectionModel().select(3);
		vetorComboBox[3].getSelectionModel().select(0);
		vetorComboBox[4].getSelectionModel().select(0);
		vetorComboBox[5].getSelectionModel().select(0);
		
		for (int i = 0; i < vetorComboBox.length; i++) {
			vetorComboBox[i].getSelectionModel().selectedItemProperty().addListener(new EventoTrocaPersonagem(i));			
		}
		
		hbSuperior = new HBox(10,avatar[0],avatar[1],avatar[2]);
		hbInferior = new HBox(10,avatar[3],avatar[4],avatar[5]);
		hbButoes = new HBox(100,voltar,iniciar);

		VBox raiz = new VBox(10,hbSuperior,hbInferior,hbButoes); 
		atualizaListaJogadores();
		
		return new Scene(raiz);
	}

	public Scene atualizaPersonagem(String jogador,int index){
		
		for (int i = 0; i < avatar.length; i++) {
			if(i!=index){
				ComboBox comboBox = (ComboBox)avatar[i].getChildren().get(1);
				String nomePersonagem = (String)comboBox.getSelectionModel().getSelectedItem();
				if(jogador.equals(nomePersonagem)){
					comboBox.getSelectionModel().select(0);					
				}
			}
		}		
		VBox raiz = new VBox(10,hbSuperior,hbInferior,hbButoes);		
		atualizaListaJogadores();
		
		return new Scene(raiz);
	}
	
	/**
	 * Confere quais personagens estão selecionados e atualiza a lista de 
	 * jogadores do ControladorTelaEscolha
	 */
	private void atualizaListaJogadores(){
		List<Jogador> listaJogadores = new ArrayList<Jogador>();

		ConstanteDaCor[] vetorConstanteDaCor = ConstanteDaCor.values();
		for (int i = 0; i < avatar.length; i++) {
			ComboBox comboBox = (ComboBox)avatar[i].getChildren().get(1);
			String nomePersonagem = (String)comboBox.getSelectionModel().getSelectedItem();
			if(!"-".equals(nomePersonagem)){
				listaJogadores.add(new Jogador(nomePersonagem,vetorConstanteDaCor[i]));	
			}
		}
		ControladorTelaEscolha.getInstancia().setListaJogadores(listaJogadores);
	}	
}
