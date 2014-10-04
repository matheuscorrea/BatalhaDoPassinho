package br.uff.tcc.bcc.visao.telas;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import br.uff.tcc.bcc.esii.modelo.Mapa;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.visao.Botao;
import br.uff.tcc.bcc.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.visao.TipoDoTerritorio;
import br.uff.tcc.bcc.visao.eventos.EventoTerritorio;

public class TelaJogo implements ITela{

	private Mapa mapa;
		
	/**
	 * Construtor da classe TelaJogo
	 * <br>Recebe o mapa como parametro para desenhar os botões
	 * @param mapa
	 */
	public  TelaJogo(Mapa mapa){
		this.mapa = mapa;

	}
	/*
	 * (non-Javadoc)
	 * @see br.uff.tcc.bcc.visao.telas.ITela#getScene()
	 */
	@Override
	public Scene getScene() {
//		Botao x10y30 = FabricaDeBotoes.criaBotao(TipoDoTerritorio.nome1.name(), "25",
//				new EventoTerritorio());
//		x10y30.setMaxHeight(20);
//		x10y30.setMaxWidth(30);
//		x10y30.setLayoutX(10);
//		x10y30.setLayoutY(30);
//		Botao x0100y0 = FabricaDeBotoes.criaBotao("JOGO", "JOGO",
//				new EventoTerritorio());
//		x0100y0.setLayoutX(100);
//		x0100y0.setLayoutY(0);
//		Botao x200y200 = FabricaDeBotoes.criaBotao("JOGO", "JOGO",
//				new EventoTerritorio());
//		x200y200.setLayoutX(200);
//		x200y200.setLayoutY(200);
		
		List<Botao> listaDeBotoesTerritorios = new ArrayList<Botao>();
		for(Territorio territorio : mapa.getTerritorios()){
			listaDeBotoesTerritorios.add(
			FabricaDeBotoes.criaBotaoTerritorio(territorio.getNome(), 
					""+territorio.getQuantidadeTropa(), new EventoTerritorio(),TipoDoTerritorio.nome1, 
					territorio.getCoordenadaX(),territorio.getCoordenadaY()));

		}
		
		Group grupo = new Group();		
		grupo.getChildren().addAll(listaDeBotoesTerritorios);

		return new Scene(grupo, 500, 500);	
	}

}
