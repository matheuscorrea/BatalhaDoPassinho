package br.uff.tcc.bcc.esii.som;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Som {
	private final int TOTAL_MUSICA = 31;
	
	//Guarda todas as musicas
	final URL resource[];
    final Media media[];
    final MediaPlayer mediaPlayer[];
    //Guarda a musica atualmente sendo tocada
    MediaPlayer mediaPlayerAtual;
	MediaPlayer mediaPlayerEfeito;
	MediaPlayer mediaPlayerBatida;
    private static Som som;
    
	private Som(){
		resource = new URL[TOTAL_MUSICA];
	    media = new Media[TOTAL_MUSICA];
	    mediaPlayer = new MediaPlayer[TOTAL_MUSICA];    
	    		
	    for (int i = 0; i < TOTAL_MUSICA; i++) {
	    	if(Musicas.fromID(i)!=null){
	    		System.out.println(Musicas.fromID(i).toString());
				resource[i] = getClass().getResource(Musicas.fromID(i).toString());
				media[i] = new Media(resource[i].toString());
				mediaPlayer[i] = new MediaPlayer(media[i]);
			}
		}    
	    mediaPlayerAtual = mediaPlayer[0];
	    mediaPlayerBatida = mediaPlayer[0];
	}

	public static Som getInstancia() {
		if (som == null) {
			som = new Som();
		}
		return som;
	}
	
	public void toca() {
		toca(0);
	}
	
    public void toca(int id){
    	if(id>=0&&id<TOTAL_MUSICA){
	    	mediaPlayerAtual.stop();
	    	mediaPlayerAtual=mediaPlayer[id];
	    	mediaPlayerAtual.setVolume(1.0);
	    	mediaPlayerAtual.play();
		    mediaPlayerAtual.setCycleCount(1);
    	}
    }
    
    public void tocaEfeito(int id){
    	if(id>=0&&id<TOTAL_MUSICA){
	    	mediaPlayerEfeito=mediaPlayer[id];
	    	mediaPlayerEfeito.setVolume(1.0);
	    	mediaPlayerEfeito.play();
		    mediaPlayerEfeito.setCycleCount(1);
    	}
    }
    public void tocaBatida(int id){
    	mediaPlayerBatida.stop();
    	mediaPlayerBatida=mediaPlayer[id];
    	mediaPlayerBatida.setVolume(0.5);
    	mediaPlayerBatida.play();
	    mediaPlayerBatida.setCycleCount(MediaPlayer.INDEFINITE);
    }
    
    
}
