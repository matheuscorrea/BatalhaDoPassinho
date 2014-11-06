package br.uff.tcc.bcc.esii.visao.telas;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.sun.xml.internal.ws.api.pipe.NextAction;

import br.uff.tcc.bcc.esii.controlador.ControladorJogo;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.esii.modelo.ia.JogadorIA;
import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas;
import br.uff.tcc.bcc.esii.visao.eventos.EventoAtaque;
import br.uff.tcc.bcc.esii.visao.eventos.EventoChamaTelaAtaque;
import br.uff.tcc.bcc.esii.visao.eventos.EventoContinuaJogo;
import br.uff.tcc.bcc.esii.visao.eventos.EventoOpcoes;
import br.uff.tcc.bcc.esii.visao.eventos.EventoSair;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTelaInicial;
import br.uff.tcc.bcc.esii.visao.eventos.EventoTelaJogo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class TelaAtaque implements ITela{

	Territorio territorioAtacante;
	Territorio territorioDefensor;
	Jogador jogadorAtacante;
	Jogador jogadorDefensor;
	
	List<Integer>dados_atacante;
	List<Integer>dados_defensor;
	
	Button botaoAtaque;
	
	public TelaAtaque(Territorio territorioAtacante,Territorio territorioDefensor) {
		this.territorioAtacante = territorioAtacante;
		this.territorioDefensor = territorioDefensor;
		this.jogadorAtacante = territorioAtacante.getDono();
		this.jogadorDefensor = territorioDefensor.getDono();
	}
	
	/* (non-Javadoc)
	 * @see br.uff.tcc.bcc.esii.visao.telas.ITela#getScene()
	 */
	@Override
	public Scene getScene() {
			
			final String imagemMapa = "file:media/imagens/mapa/mapaSemBrilho.png";

			Image image = new Image(imagemMapa);
			ImageView imageView = new ImageView();
			imageView.setImage(image);
			
			Label nomeJogadorAtacante = new Label(jogadorAtacante.getNome());
			Label qtdTropasJogadorAtacante = new Label(territorioAtacante.getQuantidadeTropa()+"");
						
			Label nomeJogadorDefensor = new Label(jogadorDefensor.getNome());
			Label qtdTropasJogadorDefensor = new Label(territorioDefensor.getQuantidadeTropa()+"");
						
			int indiceDaColuna=1;
			int indiceDaLinha=1; 
			
			botaoAtaque = FabricaDeBotoes.criaBotaoComImagem("Ataque", "", new EventoAtaque(), new Image("file:media/imagens/botoes/BTATACAR.png",100,100,true,true));
			botaoAtaque.setStyle("-fx-background-color: transparent");
			Button botaoSair = FabricaDeBotoes.criaBotao("Sair", "Sair",new EventoTelaJogo());
			
			GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER_LEFT);
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(25, 25, 25, 25));
	        
	        grid.add(nomeJogadorAtacante, indiceDaColuna, indiceDaLinha);
	        grid.add(qtdTropasJogadorAtacante, indiceDaColuna, ++indiceDaLinha);
	    
	        if(dados_atacante!=null){
		        for(Integer i:dados_atacante){
		        	grid.add(intToDadoAtq(i.intValue()), indiceDaColuna, ++indiceDaLinha);
		        }
	        }
	        
	        indiceDaColuna=2;
	        indiceDaLinha=1;
	        grid.add(botaoAtaque, indiceDaColuna,++indiceDaLinha);
	        grid.add(botaoSair, indiceDaColuna,++indiceDaLinha);
	        
	        indiceDaColuna=3;
	        indiceDaLinha=1; 
	        grid.add(nomeJogadorDefensor, indiceDaColuna, indiceDaLinha++);
	        grid.add(qtdTropasJogadorDefensor, indiceDaColuna, indiceDaLinha++);
	        
	        if(dados_defensor!=null){
		        for(Integer i:dados_defensor){
		        	grid.add(intToDadoDef(i.intValue()), indiceDaColuna, indiceDaLinha++);
		        }
	        }
	        
	        Group grupo = new Group();
	        
	        grupo.getChildren().addAll(imageView);
			grupo.getChildren().addAll(grid);
			
			return new Scene(grupo);
	}
	
	public void ataque(){
		
		if(validaAtaque()){
		
			dados_atacante = calculaDado(territorioAtacante.getQuantidadeTropa()-1);
			dados_defensor = calculaDado(territorioDefensor.getQuantidadeTropa());
			
			if(ataque(territorioAtacante,territorioDefensor,dados_atacante,dados_defensor)){
							
					if (territorioAtacante
							.getDono()
							.getObjetivo()
							.concluido(territorioAtacante.getDono(),
									territorioDefensor.getDono())) {
						if(!(ControladorJogo.getInstancia().getJogadorDaVez() instanceof JogadorIA)){
								ControladorJogo.getInstancia().fimDeJogo();
						}
					}
					// Jogador acabou de perder seu último território
					if (territorioDefensor.getDono().numeroDeConquistados() == 1) {
						ControladorJogo.getInstancia().eliminaJogador();
					}
	
					// TODO Rever com cuidado
					// TODO Pegar da visão quantas tropas passar para o territorio
					// dominado
					ControladorJogo.getInstancia().dominarTerritorio();
					
					GerenciadorDeTelas.getInstancia()
						.atualizaImageBotao(territorioDefensor.getNome(), FabricaDeBotoes
								.criaImageView(territorioDefensor));
				}
		}
		else
		{
			botaoAtaque.setDisable(true);
		}
		
	}
	
	private boolean validaAtaque() {
		if(territorioAtacante.getDono()!=territorioDefensor.getDono()){
			if(territorioAtacante.getQuantidadeTropa()>1){
				return true;
			}
		}
		return false;
	}

	private List<Integer> calculaDado(int qtdTropas){
		int qtd_dados = Math.min(3, qtdTropas);
		List<Integer>dados = new LinkedList<>();
		for(int i = 0; i < qtd_dados; i++){
			dados.add(dado());
		}
		Collections.sort(dados, Collections.reverseOrder());
		return dados;
	}

	private int dado(){
		Random random = new Random();
		return random.nextInt(6)+1; 
	}
	
	private boolean ataque(Territorio atacante, Territorio defensor,List<Integer> dados_atacante, List<Integer> dados_defensor){
		for(int i = 0; i < dados_atacante.size() && i < dados_defensor.size(); i++){
			if(dados_atacante.get(i) > dados_defensor.get(i)){
				defensor.setQuantidadeTropa(defensor.getQuantidadeTropa()-1);
			}else{
				atacante.setQuantidadeTropa(atacante.getQuantidadeTropa()-1);
			}
		}				
		//Jogador da vez conquistou territorio defensor	
		if(defensor.getQuantidadeTropa() == 0){
			//jogadorDominouTerritorio = true;
			ControladorJogo.getInstancia().dominouTerrritorio=true;
			return true;						
		}else{
			return false;
		}
	}
	
	private ImageView intToDado(String complemento, int valorDado){
			
		String imagemURL="file:media/imagens/dados/d"+complemento+valorDado+".png";		
			
		Image image = new Image(imagemURL);
		ImageView imageView = new ImageView();
		imageView.setFitHeight(20);
		imageView.setFitWidth(20);
		imageView.setImage(image);
		
		return imageView;
	}
	
	private ImageView intToDadoAtq(int valorDado){
		return intToDado("atq",valorDado);
	}
	
	private ImageView intToDadoDef(int valorDado){
		return intToDado("def",valorDado);
	}
}
