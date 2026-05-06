package View;

import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {
	
	public Clip clip;
	public boolean active=false;
	
	public void playMusic() {
		try {
			AudioInputStream audioInput=AudioSystem.getAudioInputStream(MusicPlayer.class.getResource("/musique/musique.wav"));
			clip=AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
			active=true;
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void stopMusic() {
		clip.stop();
		active=false;
	}
	
}
