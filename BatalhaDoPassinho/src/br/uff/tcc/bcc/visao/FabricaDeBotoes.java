package br.uff.tcc.bcc.visao;

import br.uff.tcc.bcc.esii.modelo.Territorio;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * F�brica respons�vel por criar objetos do tipo  {@link Botao}
 *
 */
public class FabricaDeBotoes {
	
	/**
	 * M�todo que retorna uma inst�ncia de Botao de acordo com os par�metros passados
	 * @param id 
	 * @param texto
	 * @param acao
	 * @return
	 */
	public static Button criaBotao(String id, String texto, EventHandler<ActionEvent> acao){
		Button botao = new Button();
		botao.setId(id);
		botao.setText(texto);
		botao.setOnAction(acao);
		return botao;
		
	}
	
	/**
	 * M�todo que retorna uma inst�ncia de Botao de acordo com os par�metros passados
	 * @param id 
	 * @param texto
	 * @param acao
	 * @return
	 */
	public static Button criaBotaoTerritorio(Territorio territorio,EventHandler<ActionEvent> acao){
		Button botao = criaBotao(territorio.getNome(),territorio.getQuantidadeTropa()+"",acao);
		botao.setMaxHeight(20);
		botao.setMaxWidth(30);
		ConstanteDoTerritorio constanteDoTerritorio = ConstanteDoTerritorio.fromString(territorio.getNome());
		botao.setLayoutX(constanteDoTerritorio.getX());
		botao.setLayoutY(constanteDoTerritorio.getY());
		return botao;
	}
	
	
}
