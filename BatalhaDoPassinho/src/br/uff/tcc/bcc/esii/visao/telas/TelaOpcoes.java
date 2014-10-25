package br.uff.tcc.bcc.esii.visao.telas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTelaInicial;

public class TelaOpcoes implements ITela{

	/*
	 * (non-Javadoc)
	 * @see br.uff.tcc.bcc.visao.telas.ITela#getScene()
	 */
	@Override
	public Scene getScene() {
		Button voltar = FabricaDeBotoes.criaBotao("VOLTAR", "VOLTAR",
				new EventoTelaInicial());

		Label lblTitulo = new Label("Teste para escrever algo");
		lblTitulo.setUnderline(true); 

		TextField txtNome = new TextField();
		HBox hbNome = new HBox(10); 
		hbNome.getChildren().addAll(new Label("Escreva ao lado"), txtNome);

		VBox raiz = new VBox(30,lblTitulo,hbNome,voltar); 
		//raiz.getChildren().addAll(lblTitulo,hbNome,voltar);
		  
		return new Scene(raiz, 300, 250);	
	}
}