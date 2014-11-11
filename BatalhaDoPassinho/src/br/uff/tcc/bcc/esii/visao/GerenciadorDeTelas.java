package br.uff.tcc.bcc.esii.visao;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import br.uff.tcc.bcc.esii.controlador.ControladorJogo;
import br.uff.tcc.bcc.esii.modelo.Jogo;
import br.uff.tcc.bcc.esii.visao.telas.TelaAtaque;
import br.uff.tcc.bcc.esii.visao.telas.TelaEscolha;
import br.uff.tcc.bcc.esii.visao.telas.TelaFimJogo;
import br.uff.tcc.bcc.esii.visao.telas.TelaInicial;
import br.uff.tcc.bcc.esii.visao.telas.TelaJogo;
import br.uff.tcc.bcc.esii.visao.telas.TelaObjetivo;
import br.uff.tcc.bcc.esii.visao.telas.TelaOpcoes;
import br.uff.tcc.bcc.esii.visao.telas.TelaRegras;
import br.uff.tcc.bcc.esii.visao.telas.TelaTroca;

/**
 * Classe que gerencia a troca de telas da aplicação.<br>
 * Implementa o padrão Singleton, para todas classes que tenham acesso a ela
 * obtenham a mesma instância.
 * 
 */
public class GerenciadorDeTelas{
	
	/**
	 * Stage que será usado pela Aplicação.
	 */
	private Stage stagePrincipal;
	
	

	/**
	 * Instância para o padrão Singleton.
	 */
	private static GerenciadorDeTelas gerenciador;
	
	private TelaJogo telaJogo;
	private TelaEscolha telaEscolha;
	private TelaOpcoes telaOpcoes;
	private TelaInicial telaInicial;
	private TelaTroca telaTroca;
	private TelaObjetivo telaObjetivo;
	private TelaFimJogo telaFimJogo;
	private TelaRegras telaRegras;
	
	private TelaAtaque telaAtaque;
	
	/**
	 * Construtor privado para outras classes não instanciarem.
	 */
	private GerenciadorDeTelas(){
		
	}

	/**
	 * Retorna a instância do Gerenciador de telas caso este já tenha 
	 * sido instânciado, caso contrário, cria uma nova instância e retorna
	 * a mesma.
	 * @return instância única de GerenciadorDeTelas
	 */
	public static GerenciadorDeTelas getInstancia(){
		if (gerenciador == null){
			gerenciador = new GerenciadorDeTelas();
		}
		return gerenciador;
	}

	/**
	 * Método para determinar o Stage que será gerenciado.
	 * @param stage que será utilizado.
	 */
	public void setPrimaryStage(Stage stage){
		this.stagePrincipal = stage;
		this.stagePrincipal.initStyle(StageStyle.UNDECORATED);
		this.stagePrincipal.setMaximized(true);
		this.stagePrincipal.setTitle("Batalha do Passinho");
	}
	
	/**
	 * Método que muda a tela que será exibida.<br>
	 * Recebe um tipo de tela e obtém a Scene referente
	 * a esta tela.
	 * @param tela Enum que informa o tipo da tela.
	 */
	public void mudaTela(TipoDaTela tela){
		Scene cena;
		switch (tela) {
		case INICIO:
			telaInicial = new TelaInicial();			
			cena = telaInicial.getScene();
			ControladorJogo.getInstancia().recomecaAtributos();
			stagePrincipal.setScene(cena);
			break;
		case JOGO:
			//Para iniciar a tela de jogo é preciso passar o mapa
			telaJogo= new TelaJogo(ControladorJogo.getInstancia().getMapa());		
			cena = telaJogo.getScene();
			cena.widthProperty().addListener(new ChangeListener<Number>() {
			    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
			        System.out.println("Width: " + newSceneWidth);
			    }
			});
			cena.heightProperty().addListener(new ChangeListener<Number>() {
			    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
			        System.out.println("Height: " + newSceneHeight);
			    }
			});
			stagePrincipal.setScene(cena);
			break;
		case OPCOES:
			telaOpcoes= new TelaOpcoes();			
			cena = telaOpcoes.getScene();
			stagePrincipal.setScene(cena);
			break;
		case ESCOLHA:
			telaEscolha= new TelaEscolha();			
			cena = telaEscolha.getScene();
			stagePrincipal.setScene(cena);
			break;
		case REGRASINICIO:
			telaRegras = new TelaRegras(false);
			cena= telaRegras.getScene();
			stagePrincipal.setScene(cena);
			break;
		case REGRASJOGO:
			telaRegras = new TelaRegras(true);
			cena= telaRegras.getScene();
			stagePrincipal.setScene(cena);
			break;
		case TROCA:
			telaTroca = new TelaTroca(ControladorJogo.getInstancia().getNumeroCartasJogador());
			cena = telaTroca.getScene();
			stagePrincipal.setScene(cena);
			break;
		case OBJETIVO:
			telaObjetivo = new TelaObjetivo(ControladorJogo.getInstancia().objetivoDoJogadorAtual());
			cena = telaObjetivo.getScene();
			stagePrincipal.setScene(cena);
			break;
		case ATAQUE:
			telaAtaque = new TelaAtaque(ControladorJogo.getInstancia().territorioAtacante,ControladorJogo.getInstancia().territorioDefensor);
			cena = telaAtaque.getScene();
			stagePrincipal.setScene(cena);
			break;
		case FIM_JOGO:
			telaFimJogo = new TelaFimJogo(ControladorJogo.getInstancia().jogadorVencedor());
			cena = telaFimJogo.getScene();
			stagePrincipal.setScene(cena);
			break;
		}
	}
	public void atualizaBarraTroca(){
		Scene cena = telaTroca.atualizaBarraTroca();
		stagePrincipal.setScene(cena);
	}
	public void atualizaBarraInformacoes(Jogo jogo){
		Scene cena = telaJogo.atualizaBarraInformacoes(jogo);
		stagePrincipal.setScene(cena);
          
	}
	public void atualizaPersonagem(String jogador,int index){
		Scene cena = telaEscolha.atualizaPersonagem(jogador, index);
		stagePrincipal.setScene(cena);
	}
	
	public void atualizaCheckBox() {
		Scene cena = telaEscolha.atualizaCheckBox();
		stagePrincipal.setScene(cena);
	}
	
	/**
	 * Enum que representa os tipos de tela da aplicação
	 */
	public enum TipoDaTela{
		INICIO,
		ESCOLHA,
		JOGO,
		OPCOES,
		TROCA,
		OBJETIVO,
		FIM_JOGO,
		ATAQUE,
		REGRASINICIO,
		REGRASJOGO
	}
	public void sair() {
		stagePrincipal.close();
		
	}
	
	public Stage getStagePrincipal() {
		return stagePrincipal;
	}
	
	public List<Button> getListaDeBotoesTerritorios(){
		if(telaJogo != null){
			return telaJogo.getListaDeBotoesTerritorios();		
		}else{
			return null;
		}
	}

	public void pausaJogo(){
		telaJogo.pausaJogo();
		stagePrincipal.setScene(telaJogo.getScene());
	}
	
	public void continuaJogo(){
		telaJogo.continuaJogo();
		stagePrincipal.setScene(telaJogo.getScene());
	}
	
	public void atualizaImageBotao(String idBotao, ImageView imageView){
		telaJogo.atualizaImageNoBotao(idBotao, imageView);
	}
	
	public void ataque(){
		telaAtaque.ataque();
		stagePrincipal.setScene(telaAtaque.getScene());
	}
	
	public void fim(){
		stagePrincipal.setScene(telaFimJogo.getScene());
	}
	public void proximaPaginaRegras(){
		stagePrincipal.setScene(telaRegras.proximaPagina());
	}
	public void voltaPaginaRegras(){
		stagePrincipal.setScene(telaRegras.paginaAnterior());
	}
}
