package com.audio;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class AudioFile implements LineListener{
	
	private File soundFile;
	private AudioInputStream ais;
	private AudioFormat format;
	private DataLine.Info info;
	private Clip clip;
	private FloatControl gainControl;
	private volatile boolean playing;
	public boolean toPlay;
	
	private float volume = 0f;
	
	public AudioFile(String fileName) {
		soundFile = new File(fileName);
		try {
			ais = AudioSystem.getAudioInputStream(soundFile);
			format = ais.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.addLineListener(this);
			clip.open(ais);
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	public void play() {
		if(playing) {
			clip.stop();
			clip.flush();
			clip.setFramePosition(0);
			playing = false;
		}
		gainControl.setValue(volume);
		clip.start();
		playing = true;
	}
	
	public void play(float v) {
		if(playing) {
			clip.stop();
			clip.flush();
			clip.setFramePosition(0);
			playing = false;
		}
		gainControl.setValue(v);
		clip.start();
		playing = true;
	}
	
	
	public boolean isPlaying() {
		return playing;
	}

	@Override
	public void update(LineEvent event) {
		if(event.getType() == LineEvent.Type.START)
			playing = true;
		else if(event.getType() == LineEvent.Type.STOP) {
			clip.stop();
			clip.flush();
			clip.setFramePosition(0);
			playing = false;
		}
	}

	public void setVolume(float volume) {
		if(volume < -40f) this.volume = -40f;
		else if(volume > 0f) this.volume = 0f;
		else this.volume = volume;
	}

}
