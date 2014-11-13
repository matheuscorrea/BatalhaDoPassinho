package br.uff.tcc.bcc.esii.visao.telas;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class ConstantesTelas {

	public static final double TAMANHO_IMG_TELA_INICIAL_X = 1120;
	public static final double TAMANHO_IMG_TELA_INICIAL_Y = 580;
	public static final double TAMANHO_BT_TELA_INICIAL = 100;

	public static final double TAMANHO_IMG_TELA_ESCOLHA_X = 1123;
	public static final double TAMANHO_IMG_TELA_ESCOLHA_Y = 554;
	public static final double TAMANHO_QUADRO_TELA_ESCOLHA = 0;
	
	public static final double TAMANHO_IMG_TELA_JOGO_X = 1123;
	public static final double TAMANHO_IMG_TELA_JOGO_Y = 554;
	public static final double TAMANHO_BARRA_TELA_JOGO_Y = 120;

	//PEGAR TAMANHO DA IMAGEM
	public static final double TAMANHO_IMG_TELA_OPCOES_X = 0;
	public static final double TAMANHO_IMG_TELA_OPCOES_Y = 0;

	//PEGAR TAMANHO DA IMAGEM
	public static final double TAMANHO_IMG_TELA_TROCA_X = 0;
	public static final double TAMANHO_IMG_TELA_TROCA_Y = 0;
	
	//PEGAR TAMANHO DA IMAGEM
	public static final double TAMANHO_IMG_TELA_OBJETIVO_X = 0;
	public static final double TAMANHO_IMG_TELA_OBJETIVO_Y = 0;
	
	public static final double TAMANHO_IMG_TELA_FIM_JOGO_X = 500;
	public static final double TAMANHO_IMG_TELA_FIM_JOGO_Y = 285;
	
	//PEGAR TAMANHO DA IMAGEM
	public static final double TAMANHO_IMG_TELA_ATAQUE_X = 0;
	public static final double TAMANHO_IMG_TELA_ATAQUE_Y = 0;
	
	public static double resolucaoX;
	public static double resolucaoY;
	
	public static void setResolucao(){
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		resolucaoX = bounds.getWidth();
		resolucaoY = bounds.getHeight();
	}	
	
}
