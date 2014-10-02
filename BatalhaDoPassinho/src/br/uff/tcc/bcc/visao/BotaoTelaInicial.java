package br.uff.tcc.bcc.visao;


import javafx.scene.control.Button;

public class BotaoTelaInicial extends Button{
	
	public BotaoTelaInicial(String texto){
		this.setOnAction(new AcaoChamaControladorJogo(this));
		this.setText(texto);
	}
}
