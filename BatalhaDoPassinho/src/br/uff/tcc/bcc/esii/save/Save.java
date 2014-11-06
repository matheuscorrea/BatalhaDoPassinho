package br.uff.tcc.bcc.esii.save;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sun.util.calendar.LocalGregorianCalendar.Date;
import br.uff.tcc.bcc.esii.controlador.ControladorJogo;
import br.uff.tcc.bcc.esii.modelo.Carta;
import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Territorio;
import br.uff.tcc.bcc.esii.modelo.objetivo.FabricaDeObjetivo;
import br.uff.tcc.bcc.esii.visao.ConstanteDaCor;
import br.uff.tcc.bcc.esii.visao.FabricaDeBotoes;

public class Save {

	private final String NOME_ARQ="save.txt";
	
	public void save() throws JSONException {
		
		JSONObject meuArq = new JSONObject();
		JSONArray jogadores = new JSONArray();
		JSONArray mapaJson = new JSONArray();


		for(Jogador jogador:ControladorJogo.getInstancia().getJogadores()){
			
		    //instancia um novo JSONObject
			JSONObject jogadorJson = new JSONObject();
			JSONArray mao = new JSONArray();
						
			//preenche o objeto com os campos:cor,nome,objetivo
			jogadorJson.put("cor",jogador.getCor());
			jogadorJson.put("nome",jogador.getNome());
			jogadorJson.put("objetivo",jogador.getObjetivo().getIndex());
		
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
			PrintWriter arquivo = new PrintWriter(NOME_ARQ);
			arquivo.print(jsonString);
			arquivo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void carregaJogo(){
		try {
			Scanner arquivo = new Scanner(new File(NOME_ARQ));
			String jogo = arquivo.nextLine();
			//System.out.println(jogo);
			JSONObject jogoJson = new JSONObject(jogo);
			carregaJogo(jogoJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void carregaJogo(JSONObject jsonObjeto){
		JSONArray jsonJogadores = jsonObjeto.getJSONArray("jogadores");
		for(int i=0;i<jsonJogadores.length();i++){
			JSONObject jsonJogador = jsonJogadores.getJSONObject(i);
			Jogador jogador = new Jogador(jsonJogador.getString("nome"),ConstanteDaCor.fromString(jsonJogador.getString("cor")));
			JSONArray mao = jsonJogador.getJSONArray("mao");
			for (int j = 0; j < mao.length(); j++) {
				jogador.adicionaCarta(Carta.fromString(mao.getString(j)));
			}
			FabricaDeObjetivo fabrica = new FabricaDeObjetivo();
			jogador.setObjetivo(fabrica.carregaObjetivo(jsonJogador.getInt("objetivo")));
			ControladorJogo.getInstancia().AdicionaJogador(jogador);
			
		}
		
		JSONArray jsonMapa = jsonObjeto.getJSONArray("mapa");
		for(int i=0;i<jsonMapa.length();i++){
			JSONObject jsonTerritorio = jsonMapa.getJSONObject(i);
			ControladorJogo.getInstancia().getMapa().getTerritorio(jsonTerritorio.getString("nome")).setQuantidadeTropa(jsonTerritorio.getInt("qtdTropas"));
			System.out.println(jsonTerritorio.toString(1));
		}
	}
	
}

