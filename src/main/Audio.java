package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * @author Luke
 * Runs audio, based on the name of the thread. Legal names are 
 * "MainMenu", "InGame", "MeleeHit", "RangeHit"
 */
public class Audio extends Thread
{
	private Thread t;
	private String threadName;
	private boolean onMenu;
	
	private List<String> menuMusic;
	   
	public static void main(String[] args)
	{
		//Audio main = new Audio("MainMenu");
		//main.start();
		
		//Audio game = new Audio("InGame");
		//game.start();
		
		Audio r = new Audio("RangeHit");
		r.start();
		
		//Audio m = new Audio("MeleeHit");
		//m.start();
	}
	
	public Audio(String name)
	{
		threadName = name;
		System.out.println("Created an audio process with name " + name);
	}

	public void run() 
	{
		System.out.println("Running an audio process...");
		switch (threadName)
		{
			case "MainMenu":
				onMenu = true;
				mainMenuMusic(); //Lol alliteration
				break;	
				
			case "InGame":
				onMenu = false;
				gameMusic(); //Aww.. No alliteration
				break;
				
			case "MeleeHit":
				meleeHit();
				break;
				
			case "RangeHit":
				rangeHit();
				break;
		}
	}

	public void start()
	{
		System.out.println("Started an audio process");
		if (t == null) 
		{
	         t = new Thread(this, threadName);
	         t.start ();
	    }
	}
	
	public void meleeHit()
	{
		System.out.print("reached line");
		try 
		{
			Clip clip = AudioSystem.getClip();
			File file = new File("Audio/SoundFX/Melee.wav/");
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}
	
	public void rangeHit()
	{
		try 
		{
			Clip clip = AudioSystem.getClip();
			File file = new File("Audio/SoundFX/Range.wav/");
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}
	
	public void mainMenuMusic()
	{
		try 
		{
			Clip clip = AudioSystem.getClip();
			File[] songs = shuffleMusic("MenuMusic");
			for(int i = 0; i < songs.length; i++)
			{
				System.out.println("Currently Playing " + songs[i].getName());
				File file = new File("Audio/MenuMusic/" + songs[i].getName());
			    clip.open(AudioSystem.getAudioInputStream(file));
			    clip.start();
				Thread.sleep(clip.getMicrosecondLength());
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}
	
	
	private void gameMusic()
	{
		try
		{
			File[] songs = shuffleMusic("GameMusic");
			for(int i = 0; i < songs.length; i++)
			{
				System.out.println("Currently Playing" + songs[i].getName());
				File file = new File("Audio/GameMusic/" + songs[i].getName());
			    Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(file));
				clip.start();
				Thread.sleep(clip.getMicrosecondLength());
			}
		}
		catch (Exception e) 
		{	
			e.printStackTrace();
		}
	}
	
	/**
	 * Shuffles the game music so it plays randomly. 
	 * This is called once per "InGame" thread, meaning every time you go into a game
	 * The music loops in the same order throughout each game
	 */
	public File[] shuffleMusic(String folderName)
	{
		File musicFolder = new File("Audio/" + folderName + "/");
		File[] files = musicFolder.listFiles();
		List<File> randSongs = new ArrayList<File>();
		for(int i = 0; i < files.length; i++)
		{
			randSongs.add(files[i]);
		}	
		Collections.shuffle(randSongs);
		return randSongs.toArray(new File[files.length]);
	}
}
