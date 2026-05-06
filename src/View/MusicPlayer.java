package View;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class MusicPlayer {
	
	public static void main(String[] args) {
		String chemin="/musique/musique.wav";
		playMusic(chemin);
		JOptionPane.showMessageDialog(null, "press OK to stop playing");
	}

	public static void playMusic(String chemin) {
		try {
			AudioInputStream audioInput=AudioSystem.getAudioInputStream(MusicPlayer.class.getResource("/musique/musique.wav"));
			Clip clip=AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
