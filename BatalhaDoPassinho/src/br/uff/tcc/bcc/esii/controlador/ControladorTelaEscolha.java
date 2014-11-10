package br.uff.tcc.bcc.esii.controlador;

import java.util.List;

import javafx.scene.image.ImageView;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas;

public class ControladorTelaEscolha {

	private static ControladorTelaEscolha controlador;
	
	private List<Jogador> listaJogadores;
	
	private ControladorTelaEscolha(){
	}
	
	/**Singleton do controlador tela escolha
	 * @return Instância de tela escolha
	 */
	public static ControladorTelaEscolha getInstancia(){
		if (controlador == null){
			controlador = new ControladorTelaEscolha();
		}
		return controlador;
	}
	
	/**
	 * É chamado quando o usuário altera o personagem na tela de escolha
	 * @param botao
	 */
	public void acao(String jogador,int index){
		GerenciadorDeTelas.getInstancia().atualizaPersonagem(jogador, index);
	}
	
	public void mudaCheckBox(){
		GerenciadorDeTelas.getInstancia().atualizaCheckBox();
	}
	
	public void setListaJogadores(List<Jogador> listaJogadores){
		this.listaJogadores = listaJogadores;
	}
	
	public List<Jogador>  getListaJogadores(){
		return this.listaJogadores;
	}
	
}