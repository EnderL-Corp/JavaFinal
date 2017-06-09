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

import com.sun.prism.Graphics;

import graphics.BoardButton;

public class SpriteTester {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		JPanel p = new JPanel();/*
		Graphics g = (Graphics) p.getGraphics();*/
		ImageIcon myImage = new ImageIcon("Sprites/TestSprite.png");
		BoardButton b = new BoardButton("Hello", myImage);
		b.setBackground(Color.BLACK);
		//b.setIcon(myImage);
		p.add(b);
		f.add(p);
		f.setSize(500, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
