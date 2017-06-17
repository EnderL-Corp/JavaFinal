package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;

import graphics.BoardButton;

public class SpriteTester {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		JPanel p = new JPanel();
		JButton b = new JButton();
		b.setIcon(Utilities.combineImages("Android.png", "AndroidBlaster.png"));
		p.add(b);
		f.add(p);
		f.setSize(500, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		Graphics g = (Graphics)p.getGraphics();
	}
}
