package br.uff.tcc.bcc.esii.save;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.uff.tcc.bcc.esii.controlador.ControladorJogo;
import br.uff.tcc.bcc.esii.controlador.ControladorTelaInicial;
import br.uff.tcc.bcc.esii.modelo.Carta;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Jogo.TipoFase;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.esii.modelo.objetivo.FabricaDeObjetivo;
import br.uff.tcc.bcc.esii.visao.ConstanteDaCor;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas;

public class Save {

	//private final String NOME_ARQ="save.txt";
	
	public void save() throws JSONException {
		String nomeArq = "";
		do {
			nomeArq = obterNomeArq();
			System.out.print(nomeArq);
		} while ("".equals(nomeArq));
		
		JSONObject meuArq = new JSONObject();
		JSONArray jogadores = new JSONArray();
		JSONArray mapaJson = new JSONArray();

		meuArq.put("fase", ControladorJogo.getInstancia().getFase());
		meuArq.put("jogada", ControladorJogo.getInstancia().getJogada());

		for(Jogador jogador:ControladorJogo.getInstancia().getJogadores()){
			
		    //instancia um novo JSONObject
			JSONObject jogadorJson = new JSONObject();
			JSONArray mao = new JSONArray();
						
			//preenche o objeto com os campos:cor,nome,objetivo
			jogadorJson.put("cor",jogador.getCor());
			jogadorJson.put("nome",jogador.getNome());
			jogadorJson.put("objetivo",jogador.getObjetivo().getIndex());
			jogadorJson.put("tropas", ControladorJogo.getInstancia().getTropasADitribuir(jogador));
		
			//Preenche a mao do jogador		
			for(Carta c:jogador.getMao()){
				mao.put(c.toString());
			}
		
			//Insere o array no JSONObject com o rótulo "mao"
			jogadorJson.put("mao",mao);
		
			jogadores.put(jogadorJson);
			
		}
		
		meuArq.put("jogadores", jogadores);
		
		//Salva a quantidade de tropas por territorio
		for(Territorio territorio:ControladorJogo.getInstancia().getMapa().getTerritorios()){
			
			JSONObject territorioJson = new JSONObject();
			
			territorioJson.put("nome", territorio.getNome());
			territorioJson.put("qtdTropas", territorio.getQuantidadeTropa());
			territorioJson.put("cor", territorio.getCor().toString());
			
			mapaJson.put(territorioJson);
		}
		
		meuArq.put("mapa", mapaJson);
		
		
		//serializa para uma string e imprime
		String jsonString = meuArq.toString();
		//System.out.println("objeto original -> " + jsonString);
		System.out.println();
		
		try {
			PrintWriter arquivo = new PrintWriter(nomeArq);
			arquivo.print(jsonString);
			arquivo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String obterNomeArq() {	
		return ControladorJogo.getInstancia().salvarArquivo().getAbsolutePath();
		
	}

	public void carregaJogo(){
		try {
			String caminho = "";
			caminho = ControladorTelaInicial.getInstancia().obtemArquivo();
			Scanner arquivo = new Scanner(new File(caminho));
			String jogo = arquivo.nextLine();
			JSONObject jogoJson = new JSONObject(jogo);
			carregaJogo(jogoJson);
			arquivo.close();
		} catch (Exception e) {
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Jogo não Encontrado", "", 0);
		}
	}
	
	private void carregaJogo(JSONObject jsonObjeto){
		JSONArray jsonJogadores = jsonObjeto.getJSONArray("jogadores");
		ControladorJogo.getInstancia().setFase(TipoFase.FromString(jsonObjeto.getString("fase")));
		ControladorJogo.getInstancia().setJogada(jsonObjeto.getInt("jogada"));
		
		for(int i=0;i<jsonJogadores.length();i++){
			JSONObject jsonJogador = jsonJogadores.getJSONObject(i);
			Jogador jogador = new Jogador(jsonJogador.getString("nome"),ConstanteDaCor.fromString(jsonJogador.getString("cor")));
			JSONArray mao = jsonJogador.getJSONArray("mao");
			for (int j = 0; j < mao.length(); j++) {
				jogador.adicionaCarta(Carta.fromString(mao.getString(j)));
			}
			if(jsonJogador.getInt("tropas")>0){
				ControladorJogo.getInstancia().setQuantidadeDeTropas(jsonJogador.getInt("tropas"));
			}
			FabricaDeObjetivo fabrica = new FabricaDeObjetivo();
			jogador.setObjetivo(fabrica.carregaObjetivo(jsonJogador.getInt("objetivo")));
			ControladorJogo.getInstancia().AdicionaJogador(jogador);
		}
		
		JSONArray jsonMapa = jsonObjeto.getJSONArray("mapa");
		for(int i=0;i<jsonMapa.length();i++){
			JSONObject jsonTerritorio = jsonMapa.getJSONObject(i);
			Territorio territorio = ControladorJogo.getInstancia().getMapa().getTerritorio(jsonTerritorio.getString("nome"));
			territorio.setQuantidadeTropa(jsonTerritorio.getInt("qtdTropas"));
			//System.out.println(jsonTerritorio.toString(1));
			List<Jogador> jogadores = ControladorJogo.getInstancia().getJogadores();
			for (Jogador jogador : jogadores) {
				if(ConstanteDaCor.equalsConstante(jogador.getCor(), ConstanteDaCor.fromString(jsonTerritorio.getString("cor")))){
					territorio.setDono(jogador);
					jogador.adicionaConquistados(territorio);
				}
			}
		}
	}
	
}

