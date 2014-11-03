package br.uff.tcc.bcc.esii.modelo.ia;

import br.uff.tcc.bcc.esii.modelo.Territorio;

import java.util.ArrayList;
import java.util.List;

import br.uff.tcc.bcc.esii.modelo.Jogador;

/**
 * Classe que possui as heurísticas que serão utilizadas pela IA
 * para determinar uma nota para uma determinada situação do jogo.
 *
 */
public class Heuristicas {

	/**
	 * Método que calcula a nota de um determinado ataque de um território 
	 * atacante em um território atacado baseando-se somente na quantidade de tropas
	 * de ambos.
	 * @param atacante
	 * @param atacado
	 * @return nota de 0 a 10
	 */
	protected static int ataquePorTropas(Territorio atacante, Territorio atacado) {
		int tropasAtacante = atacante.getQuantidadeTropa();
		int tropasAtacado = atacado.getQuantidadeTropa();
		double calculo;

		// Se o território atacado tiver 1 tropa
		if (tropasAtacado == 1) {
			if (tropasAtacante > 4) {
				return 10;
			} else if (tropasAtacante > 3) {
				return 8;
			} else if (tropasAtacante > 2) {
				return 6;
			} else if (tropasAtacante > 1) {
				return 4;
			}
			// Se o território atacado tiver 2 tropas
		} else if (tropasAtacado == 2) {
			if (tropasAtacante > 8) {
				return 10;
			} else if (tropasAtacante > 6) {
				return 8;
			} else if (tropasAtacante > 4) {
				return 6;
			} else if (tropasAtacante > 2) {
				return 4;
			} else if (tropasAtacante > 1) {
				return 2;
			}
			// Se o território atacado tiver 3 ou mais tropas
			// E o atacante for maior que o atacado
		} else if ((tropasAtacado > 2) && (tropasAtacante > tropasAtacado)) {
			// (x^1,5 - y^1,5)^(1/1,5)
			calculo = Math.pow(
					Math.pow(tropasAtacante, 1.5)
							- Math.pow(tropasAtacado, 1.5), (1 / 1.5)) + 3;
			// verifica se está entre 0 e 10
			calculo = Math.min(10, calculo);
			calculo = Math.max(0, calculo);

			return (int) Math.round(calculo);
		}
		return 0;
	}

	/**
	 * Método que calcula a nota de um determinado território para 
	 * receber tropas, baseando-se na quantidade de tropas dos territórios vizinhos.
	 * <p>A nota será maior se, ao incrementar tropas no território, a chance de
	 * conquistar um território vizinho aumente.
	 * @param territorioProprio
	 * @return nota de 0 a 10
	 */
	public static int faseUmPorChanceDeAtaque(Territorio territorioProprio) {
		List<Territorio> territoriosVizinhos = new ArrayList<Territorio>(
				territorioProprio.getVizinhos().values());
		float notaMedia=0;
		int vizinhosInimigos = 0;
		Jogador dono = territorioProprio.getDono();
		Territorio territorioComMaisTropas= new Territorio(territorioProprio.getNome(), territorioProprio.getContinente());
		territorioComMaisTropas.setQuantidadeTropa(territorioProprio.getQuantidadeTropa()+2);
		for (Territorio territorioInimigo : territoriosVizinhos) {
			if (!dono.possuiTerritorio(territorioInimigo.getNome())) {
				vizinhosInimigos++;
				int diferenca = ataquePorTropas(territorioComMaisTropas,territorioInimigo) - ataquePorTropas(territorioProprio,territorioInimigo);
				if(diferenca>=1){
					if(diferenca >3){
						notaMedia += 10;
					}else if(diferenca >2){
						notaMedia += 8;
					}else if(diferenca >1){
						notaMedia += 6;
					}else{
						notaMedia += 4;
					}
				}
			}
		}
		notaMedia = notaMedia/vizinhosInimigos;
		return (int) Math.round(notaMedia);
	}
	
	/**
	 * Método que calcula a nota de um determinado território para 
	 * receber tropas, baseando-se na quantidade de tropas dos territórios vizinhos.
	 * <p>A nota será maior se, ao incrementar tropas no território, a chance de
	 * defender o próprio território dos vizinhos aumente.
	 * @param territorioProprio
	 * @return nota de 0 a 10.
	 */
	public static int faseUmPorDefesaDeTerritorio(Territorio territorioProprio) {
		List<Territorio> territoriosVizinhos = new ArrayList<Territorio>(
				territorioProprio.getVizinhos().values());
		float notaMedia=0;
		int vizinhosInimigos = 0;
		Jogador dono = territorioProprio.getDono();
		Territorio territorioComMaisTropas= new Territorio(territorioProprio.getNome(), territorioProprio.getContinente());
		territorioComMaisTropas.setQuantidadeTropa(territorioProprio.getQuantidadeTropa()+2);
		for (Territorio territorioInimigo : territoriosVizinhos) {
			if (!dono.possuiTerritorio(territorioInimigo.getNome())) {
				vizinhosInimigos++;
				int diferenca = ataquePorTropas(territorioInimigo,territorioComMaisTropas) - ataquePorTropas(territorioInimigo,territorioProprio) ;
				if(diferenca >= 1){
					if(diferenca > 3){
						notaMedia += 10;
					}else if(diferenca >2){
						notaMedia += 8;
					}else if(diferenca >1){
						notaMedia += 6;
					}else{
						notaMedia += 4;
					}
				}
			}
		}
		notaMedia = notaMedia/vizinhosInimigos;
		return (int) Math.round(notaMedia);
	}
	
	/**
	 * Método que calcula a nota de um território destino para 
	 * receber tropas de um território fonte, baseando-se na quantidade de tropas dos 
	 * territórios vizinhos.
	 * <p>A nota será maior se, ao incrementar tropas no território destino, a chance de
	 * defender seja maior que a nota do território fonte decrementando o mesmo número de tropas.
	 * @param territorioFonte
	 * @param territorioDestino
	 * @returnnota de 0 a 10.
	 */
	public static int faseTresPorDefesaDeTerritorio(Territorio territorioFonte, Territorio territorioDestino) {
		if (territorioFonte.getQuantidadeTropa() > 1){
			int diferenca = faseUmPorDefesaDeTerritorio(territorioDestino) - faseUmPorDefesaDeTerritorio(territorioFonte);
			if (diferenca >=1){
				return diferenca;
			}
		}
		return 0;
	}
}
