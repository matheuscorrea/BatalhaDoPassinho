package br.uff.tcc.bcc.esii.som;

import java.net.URL;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Som {
	
//	//final String musicURL = "file:///media/audio/Anitta/Zen.mp3";
//	//Media media;
//	final URL resource = getClass().getResource("Carrapato.mp3");
//    final Media media = new Media(resource.toString());
//	
//	MediaPlayer mediaPlayer;
//	MediaView mediaView;
//	
	public Som(){
		//media = new Media(musicURL);
	    //mediaPlayer = new MediaPlayer(media);
	    //mediaView = new MediaView(mediaPlayer);
	}
    
    public void toca(){
   	 AudioClip plonkSound = new AudioClip("file:BatalhaDoPassinho/BatalhaDoPassinho/media/audio/Anitta/Zen.mp3");
    	 plonkSound.play();
//    	mediaPlayer.play();
//    	return mediaView;
    }
}
