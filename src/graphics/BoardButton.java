package graphics;

import java.awt.Color;

import javax.accessibility.Accessible;
import javax.swing.AbstractButton;
import javax.swing.DefaultButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.prism.Graphics;

/**
 * Button of the board
 * @author Srihari Subramanian
 *
 */
public class BoardButton extends JButton implements Accessible {

	private static final long serialVersionUID = 1L;

	public BoardButton() {
		this(null, null);
	}
	
	public BoardButton(String text) {
		this(text, null);
	}

	public BoardButton(String text, Icon icon) {
		setModel(new DefaultButtonModel());

		init(text, icon);
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		JPanel p = new JPanel();/*
		Graphics g = (Graphics) p.getGraphics();*/
		ImageIcon myImage = new ImageIcon("Sprites/SpriteTest");
		BoardButton b = new BoardButton("Test button", myImage);
		b.setBackground(Color.CYAN);
		b.setSize(500, 500);
		p.add(b);
		JButton jb = new JButton(myImage);
		jb.setSize(500, 500);
		p.add(jb);
		f.add(p);
		f.setSize(600, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
