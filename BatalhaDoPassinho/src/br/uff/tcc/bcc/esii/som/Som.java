package br.uff.tcc.bcc.esii.som;

import java.net.URL;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Som {
	
	final URL resource;
    final Media media;
    final MediaPlayer mediaPlayer;
	
	public Som(){
	    resource = getClass().getResource("Zen.mp3");
	    media = new Media(resource.toString());
	    mediaPlayer = new MediaPlayer(media);
	    
	    
		
	}
    
    public void toca(){
    	mediaPlayer.play();
	    mediaPlayer.setCycleCount(2);
    }
}
