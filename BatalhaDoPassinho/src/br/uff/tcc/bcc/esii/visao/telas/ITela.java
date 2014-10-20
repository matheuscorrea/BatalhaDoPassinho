package br.uff.tcc.bcc.esii.visao.telas;

import javafx.scene.Scene;

/**
 * Interface para todas telas do jogo
 * @author Visagio
 *
 */
public interface ITela {
	
	/**
	 * Método que retorna a Scene que será inserida no Stage principal do jogo
	 * @return {@link Scene}
	 */
	public Scene getScene();
}
