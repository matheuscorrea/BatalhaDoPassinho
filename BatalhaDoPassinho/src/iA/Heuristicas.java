package iA;

import br.uff.tcc.bcc.esii.modelo.Territorio;

public class Heuristicas {
	
	protected static int ataquePorTropas(Territorio atacante, Territorio atacado){
		int tropasAtacante = atacante.getQuantidadeTropa();
		int tropasAtacado = atacado.getQuantidadeTropa();
		double calculo;
		
		//Se o território atacado tiver 1 tropa
		if(tropasAtacado == 1){
			if(tropasAtacante > 4){
				return 10;
			}else if(tropasAtacante >3){
				return 8;
			}else if(tropasAtacante >2){
				return 6;
			}else if(tropasAtacante >1){
				return 4;
			}
		//Se o território atacado tiver 2 tropas
		}else if(tropasAtacado == 2){
			if(tropasAtacante > 8){
				return 10;
			}else if(tropasAtacante > 6){
				return 8;
			}else if(tropasAtacante > 4){
				return 6;
			}else if(tropasAtacante > 2){
				return 4;
			}else if(tropasAtacante > 1){
				return 2;
			}
		//Se o território atacado tiver 3 ou mais tropas
		//E o atacante for maior que o atacado
		}else if((tropasAtacado > 2)&&(tropasAtacante>tropasAtacado)){
			//(x^1,5 - y^1,5)^(1/1,5)
			calculo = Math.pow(
						Math.pow(tropasAtacante,1.5) - Math.pow(tropasAtacado,1.5),
						(1/1.5)) + 3;
			//verifica se está entre 0 e 10
			calculo = Math.min(10,calculo);
			calculo = Math.max(0,calculo);
			
			return (int)Math.round(calculo);
		}
		return 0;
	}
}
