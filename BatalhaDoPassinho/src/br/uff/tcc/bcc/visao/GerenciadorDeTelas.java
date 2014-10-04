package br.uff.tcc.bcc.visao;

import br.uff.tcc.bcc.visao.telas.ITela;
import br.uff.tcc.bcc.visao.telas.TelaInicial;
import br.uff.tcc.bcc.visao.telas.TelaJogo;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
	}
	
	/**
	 * Método que muda a tela que será exibida.<br>
	 * Recebe um tipo de tela e obtém a Scene referente
	 * a esta tela.
	 * @param tela Enum que informa o tipo da tela.
	 */
	public void mudaTela(TipoDaTela tela){
		if(TipoDaTela.inicio.equals(tela)){
			ITela telaInicial = new TelaInicial();			
			Scene cena = telaInicial.getScene();
			stagePrincipal.setScene(cena);
			
		}else if(TipoDaTela.jogo.equals(tela)){
			ITela telaJogo= new TelaJogo();			
			Scene cena = telaJogo.getScene();
			stagePrincipal.setScene(cena);
			
		}else if(TipoDaTela.opcoes.equals(tela)){
			
		}
	}

//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		this.primaryStage = primaryStage;
//		Tela telaInicial = new TelaInicial();
//		
//		Scene cena = telaInicial.getScene();
//		this.primaryStage.setScene(cena);
//	};
//	
	/**
	 * Enum que representa os tipos de tela da aplicação
	 */
	public enum TipoDaTela{
		inicio,
		jogo,
		opcoes
	}

}
