package br.uff.tcc.bcc.esii.visao.telas;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.ia.JogadorIA;
import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTelaInicial;

public class TelaFimJogo implements ITela {

	private Jogador jogadorVencedor;
	public TelaFimJogo(Jogador jogadorVencedor) {
		this.jogadorVencedor = jogadorVencedor;
	}
	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		final String imagemURL = "file:media/imagens/mcs/catraGanhou.jpg";

		Image image = new Image(imagemURL,ConstantesTelas.resolucaoX,ConstantesTelas.resolucaoY,false,true);
		ImageView imageView = new ImageView();
		imageView.setImage(image);

		Button botaoMenuPricipal = FabricaDeBotoes.criaBotaoComImagem("Menu_Principal","",new EventoTelaInicial(),new Image("file:media/imagens/botoes/BTMAINMENU.png",100,100,true,true));
		botaoMenuPricipal.setStyle("-fx-background-color: transparent");
		botaoMenuPricipal.setLayoutX(ConstantesTelas.resolucaoX/5 - 50);
		botaoMenuPricipal.setLayoutY(ConstantesTelas.resolucaoY/5 - 50);
        
		
		Label labelGanhou;
		if(jogadorVencedor instanceof JogadorIA){
			labelGanhou = new Label("AQUI A IA É SINISTRA! BOA "+jogadorVencedor.getNome());
		}else{
			labelGanhou = new Label("VOCÊ GANHOU! PARABÉNS "+jogadorVencedor.getNome());
		}
		labelGanhou.setLayoutX(10);
		labelGanhou.setLayoutY(0);
        		
		Group grupoFimJogo = new Group();
		
		grupoFimJogo.getChildren().addAll(imageView);
		grupoFimJogo.getChildren().addAll(botaoMenuPricipal,labelGanhou);
        
		return new Scene(grupoFimJogo);
		
	}
	


}
