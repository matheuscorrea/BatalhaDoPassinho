package br.uff.tcc.bcc.esii.visao;

import br.uff.tcc.bcc.esii.controlador.ControladorJogo;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Jogo;
import br.uff.tcc.bcc.esii.modelo.Jogo.TipoFase;
import br.uff.tcc.bcc.esii.visao.telas.ITela;
import br.uff.tcc.bcc.esii.visao.telas.TelaInicial;
import br.uff.tcc.bcc.esii.visao.telas.TelaJogo;
import br.uff.tcc.bcc.esii.visao.telas.TelaOpcoes;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe que gerencia a troca de telas da aplica��o.<br>
 * Implementa o padr�o Singleton, para todas classes que tenham acesso a ela
 * obtenham a mesma inst�ncia.
 * 
 */
public class GerenciadorDeTelas{
	
	/**
	 * Stage que ser� usado pela Aplica��o.
	 */
	private Stage stagePrincipal;
	
	/**
	 * Inst�ncia para o padr�o Singleton.
	 */
	private static GerenciadorDeTelas gerenciador;
	
	private TelaJogo telaJogo;
	
	/**
	 * Construtor privado para outras classes n�o instanciarem.
	 */
	private GerenciadorDeTelas(){
		
	}

	/**
	 * Retorna a inst�ncia do Gerenciador de telas caso este j� tenha 
	 * sido inst�nciado, caso contr�rio, cria uma nova inst�ncia e retorna
	 * a mesma.
	 * @return inst�ncia �nica de GerenciadorDeTelas
	 */
	public static GerenciadorDeTelas getInstancia(){
		if (gerenciador == null){
			gerenciador = new GerenciadorDeTelas();
		}
		return gerenciador;
	}

	/**
	 * M�todo para determinar o Stage que ser� gerenciado.
	 * @param stage que ser� utilizado.
	 */
	public void setPrimaryStage(Stage stage){
		this.stagePrincipal = stage;
		this.stagePrincipal.setTitle("Batalha do Passinho");
	}
	
	/**
	 * M�todo que muda a tela que ser� exibida.<br>
	 * Recebe um tipo de tela e obt�m a Scene referente
	 * a esta tela.
	 * @param tela Enum que informa o tipo da tela.
	 */
	public void mudaTela(TipoDaTela tela){
		if(TipoDaTela.inicio.equals(tela)){
			ITela telaInicial = new TelaInicial();			
			Scene cena = telaInicial.getScene();
			stagePrincipal.setScene(cena);
			
		}else if(TipoDaTela.jogo.equals(tela)){
			//Para iniciar a tela de jogo � preciso passar o mapa
			telaJogo= new TelaJogo(ControladorJogo.getInstancia().getMapa());		
			
			Scene cena = telaJogo.getScene();
			stagePrincipal.setScene(cena);
			
		}else if(TipoDaTela.opcoes.equals(tela)){
			ITela telaOpcoes= new TelaOpcoes();			
			Scene cena = telaOpcoes.getScene();
			stagePrincipal.setScene(cena);
			
		}
	}

	public void atualizaBarraInformacoes(Jogo jogo){
		
		Scene cena = telaJogo.atualizaBarraInformacoes(jogo);
		stagePrincipal.setScene(cena);
		
	}
	
	/**
	 * Enum que representa os tipos de tela da aplica��o
	 */
	public enum TipoDaTela{
		inicio,
		jogo,
		opcoes
	}

}
