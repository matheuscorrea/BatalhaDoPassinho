package br.uff.tcc.bcc.visao.eventos;

import br.uff.tcc.bcc.visao.Botao;
import br.uff.tcc.bcc.visao.GerenciadorDeTelas;
import br.uff.tcc.bcc.visao.GerenciadorDeTelas.TipoDaTela;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Evento que descreve as ações do botão de Novo Jogo
 * 
 *
 */
public class EventoNovoJogo implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent arg0) {
		Object source = arg0.getSource();
		if (source instanceof Botao) { //should always be true in your example
		    Botao clickedBtn = (Botao) source; // that's the button\ that was clicked
		    GerenciadorDeTelas.getInstancia().mudaTela(TipoDaTela.jogo);
		    
		}
	}
}
