package br.uff.tcc.bcc.esii.visao;

import br.uff.tcc.bcc.esii.controlador.ControladorJogo;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Jogo;
import br.uff.tcc.bcc.esii.modelo.Jogo.TipoFase;
import br.uff.tcc.bcc.esii.visao.telas.ITela;
import br.uff.tcc.bcc.esii.visao.telas.TelaEscolha;
import br.uff.tcc.bcc.esii.visao.telas.TelaInicial;
import br.uff.tcc.bcc.esii.visao.telas.TelaJogo;
import br.uff.tcc.bcc.esii.visao.telas.TelaOpcoes;
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
	
	private TelaJogo telaJogo;
	private TelaEscolha telaEscolha;
	
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
		this.stagePrincipal.setTitle("Batalha do Passinho");
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
			//Para iniciar a tela de jogo é preciso passar o mapa
			telaJogo= new TelaJogo(ControladorJogo.getInstancia().getMapa());		
			Scene cena = telaJogo.getScene();
			stagePrincipal.setScene(cena);
		}else if(TipoDaTela.opcoes.equals(tela)){
			ITela telaOpcoes= new TelaOpcoes();			
			Scene cena = telaOpcoes.getScene();
			stagePrincipal.setScene(cena);
		}else if(TipoDaTela.escolha.equals(tela)){
			telaEscolha= new TelaEscolha();			
			Scene cena = telaEscolha.getScene();
			stagePrincipal.setScene(cena);
		}
	}
	public void atualizaBarraInformacoes(Jogo jogo){
		Scene cena = telaJogo.atualizaBarraInformacoes(jogo);
		stagePrincipal.setScene(cena);
	}
	public void atualizaPersonagem(String jogador,int index){
		Scene cena = telaEscolha.atualizaPersonagem(jogador, index);
		stagePrincipal.setScene(cena);
	}
	/**
	 * Enum que representa os tipos de tela da aplicação
	 */
	public enum TipoDaTela{
		inicio,
		escolha,
		jogo,
		opcoes
	}

}
