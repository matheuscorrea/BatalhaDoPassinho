package br.uff.tcc.bcc.visao;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AcaoChamaControladorJogo implements EventHandler<ActionEvent>{

	private BotaoTelaInicial botao;
	
	public AcaoChamaControladorJogo(BotaoTelaInicial botao) {
		this.botao = botao;
	}
	
	@Override
	public void handle(ActionEvent actionEvent) {
		ControladorTelaInicial.getInstancia().acao(botao);
	}
	
}
