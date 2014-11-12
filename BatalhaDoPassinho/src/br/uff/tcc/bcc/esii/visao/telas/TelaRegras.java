package br.uff.tcc.bcc.esii.visao.telas;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoPausaJogo;
import br.uff.tcc.bcc.esii.visao.eventos.EventoProximaPaginaRegras;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTelaInicial;
import br.uff.tcc.bcc.esii.visao.eventos.EventoVoltaPaginaRegras;

public class TelaRegras implements ITela{
	
	private int pagina = 1;
	private Button avancar;
	private Button voltar;
	private boolean estajogando;
	private String acaoVoltar;
	
	private Image imagemAvancar = new Image("file:media/imagens/botoes/BTAVANCAR.png",100,100,true,true);
	private Image imagemVoltar = new Image("file:media/imagens/botoes/BTVOLTAR.png",100,100,true,true);
	
public TelaRegras(boolean estaJogando){
		
		this.estajogando = estaJogando;
	}
	
	@Override
	public Scene getScene() {
		
		avancar = FabricaDeBotoes.criaBotaoComImagem("Anvancar_Regras", "", new EventoProximaPaginaRegras(), imagemAvancar);
		
		if(pagina == 3){
			if(estajogando){
				avancar = FabricaDeBotoes.criaBotaoComImagem("Avancar_regras", "", new EventoPausaJogo(), new Image("file:media/imagens/botoes/BTSAIR.png",100,100,true,true));
			}else{
				avancar = FabricaDeBotoes.criaBotaoComImagem("Avancar_regras", "", new EventoTelaInicial(), new Image("file:media/imagens/botoes/BTSAIR.png",100,100,true,true));
			}
		}		
		
		if(pagina == 1){
			if(estajogando){
					voltar = FabricaDeBotoes.criaBotaoComImagem("Voltar_Regras", "", new EventoPausaJogo(), imagemVoltar);
				}
				else{
					voltar = FabricaDeBotoes.criaBotaoComImagem("Voltar_Regras", "", new EventoTelaInicial(), imagemVoltar);
			}				
		}else{
			voltar = FabricaDeBotoes.criaBotaoComImagem("Voltar_Regras", "", new EventoVoltaPaginaRegras(), imagemVoltar);
		}
		
		String caminho = "file:media/imagens/regras/regras"+pagina+".png";
		ImageView fundo = new ImageView();
		fundo.setImage(new Image(caminho));
		
		avancar.setStyle("-fx-background-color: transparent");
		voltar.setStyle("-fx-background-color: transparent");
		
		GridPane grid= new GridPane();
		grid.add(voltar,0,2);
		grid.add(avancar,0,1);
		
		Group grupo = new Group();
		grupo.getChildren().add(fundo);
		grupo.getChildren().add(grid);
		grid.setHgap(50);
		grid.setVgap(125);
		
		return new Scene(grupo);

	}
	
	public Scene proximaPagina(){
		pagina++;
		if(pagina == 3){
			if(estajogando){
				avancar = FabricaDeBotoes.criaBotaoComImagem("Avancar_regras", "", new EventoPausaJogo(), new Image("file:media/imagens/botoes/BTSAIR.png",100,100,true,true));
			}else{
				avancar = FabricaDeBotoes.criaBotaoComImagem("Avancar_regras", "", new EventoTelaInicial(), new Image("file:media/imagens/botoes/BTSAIR.png",100,100,true,true));
			}
		}
		
		String caminho = "file:media/imagens/regras/regras"+pagina+".png";
		ImageView fundo = new ImageView();
		fundo.setImage(new Image(caminho));
		
		if(pagina == 1){
			if(estajogando){
					voltar = FabricaDeBotoes.criaBotaoComImagem("Voltar_Regras", "", new EventoPausaJogo(), imagemVoltar);
				}
				else{
					voltar = FabricaDeBotoes.criaBotaoComImagem("Voltar_Regras", "", new EventoTelaInicial(), imagemVoltar);
			}				
		}else{
			voltar = FabricaDeBotoes.criaBotaoComImagem("Voltar_Regras", "", new EventoVoltaPaginaRegras(), imagemVoltar);
		}
		
		avancar.setStyle("-fx-background-color: transparent");
		voltar.setStyle("-fx-background-color: transparent");
		
		
		GridPane grid= new GridPane();
		grid.add(voltar,0,2);
		grid.add(avancar,0,1);
		
		Group grupo = new Group();
		grupo.getChildren().add(fundo);
		grupo.getChildren().add(grid);
		grid.setHgap(50);
		grid.setVgap(125);
		
		return new Scene(grupo);		
	}

	public Scene paginaAnterior() {
		pagina--;
		if(pagina == 3){
			if(estajogando){
				avancar = FabricaDeBotoes.criaBotaoComImagem("Avancar_regras", "", new EventoPausaJogo(), new Image("file:media/imagens/botoes/BTSAIR.png",100,100,true,true));
			}else{
				avancar = FabricaDeBotoes.criaBotaoComImagem("Avancar_regras", "", new EventoTelaInicial(), new Image("file:media/imagens/botoes/BTSAIR.png",100,100,true,true));
			}
		}
		
		
		String caminho = "file:media/imagens/regras/regras"+pagina+".png";
		ImageView fundo = new ImageView();
		fundo.setImage(new Image(caminho));
		
		if(pagina == 1){
			if(estajogando){
					voltar = FabricaDeBotoes.criaBotaoComImagem("Voltar_Regras", "", new EventoPausaJogo(), imagemVoltar);
				}
				else{
					voltar = FabricaDeBotoes.criaBotaoComImagem("Voltar_Regras", "", new EventoTelaInicial(), imagemVoltar);
			}				
		}else{
			voltar = FabricaDeBotoes.criaBotaoComImagem("Voltar_Regras", "", new EventoVoltaPaginaRegras(), imagemVoltar);
		}
		
		avancar.setStyle("-fx-background-color: transparent");
		voltar.setStyle("-fx-background-color: transparent");
		
		GridPane grid= new GridPane();
		grid.add(voltar,0,2);
		grid.add(avancar,0,1);
		
		Group grupo = new Group();
		grupo.getChildren().add(fundo);
		grupo.getChildren().add(grid);
		grid.setHgap(50);
		grid.setVgap(125);
		
		return new Scene(grupo);
		
	}
	

}
