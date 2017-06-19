package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import graphics.CommandLog;

/**
 * @author Luke Runs audio, based on the name of the thread. Legal names are
 *         "MenuMusic", "InGame", "FX" If running FX, a second String is
 *         required, legal arguments are "Blast", "Deflect", "Melee", "Mirror",
 *         "Range"
 */
public class Audio extends Thread {
	private Thread t;
	private static int ID;
	private String threadName;
	private String effect;
	private static boolean onMenu;

	public static void main(String[] args) {
		// Audio main = new Audio("MainMenu");

		// Audio game = new Audio("InGame");

		// Audio fx = new Audio("FX", "Range");
		// Audio fx2 = new Audio("FX", "Deflect");
		// Audio fx3 = new Audio("FX", "Mirror");
	}

	public Audio(String name) {
		threadName = name;
		ID++;
		start();
	}

	public Audio(String name, String eff) {
		threadName = name;
		ID++;
		effect = eff;
		start();
	}

	public void run() {
		switch (threadName) {
		case "MainMenu":
			onMenu = true;
			mainMenuMusic(); // Lol alliteration
			break;

		case "InGame":
			onMenu = false;
			gameMusic(); // Aww.. No alliteration
			break;

		case "FX":
			soundFX(effect);
			break;
		}
	}

	public void start() {
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
		if (!t.isAlive()) {
			ID--;
		}
		t = null;

	}

	public synchronized void soundFX(String effect) {
		try {
			Clip clip = AudioSystem.getClip();
			File file = new File("Audio/SoundFX/" + effect + ".wav/");
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
			Thread.sleep((long) ((double) clip.getMicrosecondLength() / 1000));
			clip.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Plays Main Menu Music
	 */
	public synchronized void mainMenuMusic() {
		try {
			long startTime;
			Clip clip = AudioSystem.getClip();
			File[] songs = shuffleMusic("MenuMusic");
			while (true) {
				for (int i = 0; i < songs.length; i++) {
					System.out.println("Currently Playing " + songs[i].getName());
					File file = new File("Audio/MenuMusic/" + songs[i].getName());
					clip.open(AudioSystem.getAudioInputStream(file));
					clip.start();
					startTime = System.currentTimeMillis();
					while (System.currentTimeMillis() < startTime
							+ (long) ((double) clip.getMicrosecondLength() / 1000)) {
						if (onMenu) {
							Thread.sleep(1);
						} else {
							clip.stop();
							clip.close();
							return;
						}
					}
					clip.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private synchronized void gameMusic() {
		try {
			long startTime;
			Clip clip = AudioSystem.getClip();
			File[] songs = shuffleMusic("GameMusic");
			while (true) {
				for (int i = 0; i < songs.length; i++) {
					CommandLog.publish(
							"Currently playing: " + songs[i].getName().substring(0, songs[i].getName().indexOf('.')));
					System.out.println("Currently Playing" + songs[i].getName());
					File file = new File("Audio/GameMusic/" + songs[i].getName());
					clip.open(AudioSystem.getAudioInputStream(file));
					clip.start();
					startTime = System.currentTimeMillis();
					while (System.currentTimeMillis() < startTime
							+ (long) ((double) clip.getMicrosecondLength() / 1000)) {
						if (!onMenu) {
							Thread.sleep(1);
						} else {
							clip.stop();
							clip.close();
							return;
						}
					}
					clip.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shuffles the game music so it plays randomly. This is called once per
	 * "InGame" thread, meaning every time you go into a game The music loops in
	 * the same order throughout each game
	 */
	public File[] shuffleMusic(String folderName) {
		File musicFolder = new File("Audio/" + folderName + "/");
		File[] files = musicFolder.listFiles();
		List<File> randSongs = new ArrayList<File>();
		for (int i = 0; i < files.length; i++) {
			randSongs.add(files[i]);
		}
		Collections.shuffle(randSongs);
		return randSongs.toArray(new File[files.length]);
	}
}
