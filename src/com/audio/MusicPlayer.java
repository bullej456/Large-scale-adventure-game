package com.audio;

import java.util.ArrayList;

import com.Main.Main;

public class MusicPlayer implements Runnable{
	
	private ArrayList<AudioFile> musicFiles = new ArrayList<AudioFile>();
	private int currentIndex = 0;
	private ArrayList<AudioFile> soundFiles = new ArrayList<AudioFile>();
	
	public MusicPlayer(String... files) {
		for(String f: files) {
			musicFiles.add(new AudioFile("./res/audio/songs/" + f + ".wav"));
		}
		currentIndex = 0;
		
		////ADD SOUNDS
		soundFiles.add(new AudioFile("./res/audio/sounds/" + "cartoon001" + ".wav"));
		soundFiles.add(new AudioFile("./res/audio/sounds/" + "SUCCESS PICKUP Collect Chime 01" + ".wav"));
		soundFiles.add(new AudioFile("./res/audio/sounds/" + "confirm_style_5_001" + ".wav"));
		soundFiles.add(new AudioFile("./res/audio/sounds/" + "back_style_5_001" + ".wav"));
		soundFiles.add(new AudioFile("./res/audio/sounds/" + "SUCCESS PICKUP Retro Collect Beeps 01" + ".wav"));
		soundFiles.add(new AudioFile("./res/audio/sounds/" + "SUCCESS PICKUP Collect Chime 04" + ".wav"));
		soundFiles.add(new AudioFile("./res/audio/sounds/" + "POP Brust 10" + ".wav"));
		soundFiles.add(new AudioFile("./res/audio/sounds/" + "POP Brust 01" + ".wav"));
		soundFiles.add(new AudioFile("./res/audio/sounds/" + "SPLAT Squelch 01" + ".wav"));
		soundFiles.add(new AudioFile("./res/audio/sounds/" + "SPLAT Short 03" + ".wav"));
		soundFiles.add(new AudioFile("./res/audio/sounds/" + "SPLAT Crush 01" + ".wav"));
		soundFiles.add(new AudioFile("./res/audio/sounds/" + "VOCAL EVIL Impact Hit AHHH Short 01" + ".wav"));
		soundFiles.add(new AudioFile("./res/audio/sounds/" + "EXPLOSION Bang Muffled 01" + ".wav"));
		soundFiles.add(new AudioFile("./res/audio/sounds/" + "SUCCESS BEEPS Single Tone Short 04" + ".wav"));
		soundFiles.add(new AudioFile("./res/audio/sounds/" + "SUCCESS CHIME Mystery Magic Mana Swell 05" + ".wav"));
		soundFiles.add(new AudioFile("./res/audio/sounds/" + "VOCAL CUTE Distress Pain Short 11" + ".wav"));
		soundFiles.add(new AudioFile("./res/audio/sounds/" + "SUCCESS TUNE Happy Sticks Short 01" + ".wav"));
		soundFiles.add(new AudioFile("./res/audio/sounds/" + "ALARM Alert Ringer Short 03" + ".wav"));
		soundFiles.add(new AudioFile("./res/audio/sounds/" + "SUCCESS PICKUP Collect Beep Short 03" + ".wav"));
		soundFiles.add(new AudioFile("./res/audio/sounds/" + "SUCCESS TUNE Win Complete Short 04" + ".wav"));
		/////
		for(int i = 0; i < soundFiles.size(); i++) {
			soundFiles.get(i).toPlay = false;
		}
	}
	
	//1. cartoon001
	//2. SUCCESS PICKUP Collect Chime 01
	//3. confirm_style_5_001
	//4. back_style_5_001
	//5. SUCCESS PICKUP Retro Collect Beeps 01
	//6. SUCCESS PICKUP Collect Chime 04
	//7. POP Brust 10
	//8. POP Brust 01
	//9. SPLAT Squelch 01
	//10. SPLAT Short 03
	//11. SPLAT Crush 01
	//12. VOCAL EVIL Impact Hit AHHH Short 01
	//13. EXPLOSION Bang Muffled 01
	//14. SUCCESS BEEPS Single Tone Short 04
	//15. SUCCESS CHIME Mystery Magic Mana Swell 05
	//16. VOCAL CUTE Distress Pain Short 11
	//17. SUCCESS TUNE Happy Sticks Short 01
	//18. ALARM Alert Ringer Short 03
	//19. SUCCESS PICKUP Collect Beep Short 03
	//20. SUCCESS TUNE Win Complete Short 04
	public void playSound(int id, float volume) {
		if(id < soundFiles.size()) {
			soundFiles.get(id).toPlay = true;
			soundFiles.get(id).setVolume(volume);
		}
	}
	
	@Override
	public void run() {
		AudioFile song = musicFiles.get(currentIndex);
		song.play();
		while(Main.running) {
			if(!song.isPlaying()) {
				currentIndex++;
				if(currentIndex >= musicFiles.size())
					currentIndex = 0;
				song = musicFiles.get(currentIndex);
				song.play();
			}
			
			///PLAY CALLED SOUNDS?!
			for(AudioFile a: soundFiles) {
				if(a.toPlay) {
					a.play();
					a.toPlay = false;
				}
			}
			///////////////////////
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
