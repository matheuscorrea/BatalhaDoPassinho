package br.uff.tcc.bcc.esii.modelo.objetivo;

import java.util.Map;

import br.uff.tcc.bcc.esii.modelo.Jogador;
import br.uff.tcc.bcc.esii.modelo.Territorio;

/**
 * Conquistar totalidade nos continentes recebidos e mais a quantidade recebida como outros
 * @author Rempto
 */

public class ObjetivoConquistar extends Objetivo {

	String continente1, continente2;
	int outros, qnt1, qnt2;
	private int index;
	
	public ObjetivoConquistar(String cont1, int qnt1, String cont2, int qnt2 , int outros){
		this.continente1 = cont1;
		this.qnt1 = qnt1;
		this.continente2 = cont2;
		this.qnt2 = qnt2;
		this.outros = outros;	
	}
	
	public ObjetivoConquistar(int outros){ 
		this.continente1 = "NaN";
		this.qnt1 = 0;
		this.continente2 = "NaN";
		this.qnt2 = 0;
		this.outros = outros;
	}
	
	@Override
	public boolean concluido(Jogador jogador, Jogador semuso) {
		int quantBoatesPorContinente[] = new int[2];
		int outros= 0;
		for (Map.Entry<String, Territorio> entry : jogador.getConquistados()
				.entrySet()) {
			Territorio boate = entry.getValue();
			if (boate.getContinente() == continente1) {
				quantBoatesPorContinente[0]++;
			} else {
				if (boate.getContinente() == continente2) {
					quantBoatesPorContinente[1]++;
				} else {
					outros++;
				}
			}
		}		
		return (quantBoatesPorContinente[0]== qnt1 && quantBoatesPorContinente[1] == qnt2 && outros >= this.outros);
	}
	
	@Override
	public String getNomeObjetivo(){
		String objetivoTexto="Seu objetivo é:\n";
		if(qnt1 != 0){
			objetivoTexto += "Conquistar o continente "+continente1+"\n";
		}
		if(qnt2 != 0){
			objetivoTexto += "Conquistar o continente "+continente2+"\n";
		}
		if(outros != 0){
			objetivoTexto += "Conquistar mais "+outros+" territórios a sua escolha";
		}
		return objetivoTexto;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
