package br.uff.tcc.bcc.esii.som;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Som {
	private final int TOTAL_MUSICA=2;
	
	//Guarda todas as musicas
	final URL resource[];
    final Media media[];
    final MediaPlayer mediaPlayer[];
    //Guarda a musica atualmente sendo tocada
    MediaPlayer mediaPlayerAtual;
	
    private static Som som;
    
	private Som(){
		resource = new URL[TOTAL_MUSICA];
	    media = new Media[TOTAL_MUSICA];
	    mediaPlayer = new MediaPlayer[TOTAL_MUSICA];    
	    		
	    for (int i = 0; i < TOTAL_MUSICA; i++) {
	    	System.out.println(Musicas.fromID(i).toString());
			resource[i] = getClass().getResource(Musicas.fromID(i).toString());
			media[i] = new Media(resource[i].toString());
			mediaPlayer[i] = new MediaPlayer(media[i]);
		}    
	    mediaPlayerAtual = mediaPlayer[0];

	    
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
	    	mediaPlayerAtual.play();
		    mediaPlayerAtual.setCycleCount(2);
    	}
    }
    
    
}
