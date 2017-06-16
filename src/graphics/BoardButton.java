package graphics;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.accessibility.Accessible;
import javax.swing.DefaultButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cards.Entity;
import main.Game;

/**
 * Button of the board
 * @author Srihari Subramanian, Luke Letourneau
 *
 */
public class BoardButton extends JButton implements Accessible, ActionListener {

	private static final long serialVersionUID = 1L;
	
	private int xPos, yPos;

	public BoardButton(int x, int y) {
		this(x, y, null, null);
	}
	
	public BoardButton(int x, int y, String text) {
		this(x, y, text, null);
	}

	public BoardButton(int x, int y, String text, Icon icon) {
		setModel(new DefaultButtonModel());
		xPos = x;
		yPos = y;
		init(text, icon);
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		JPanel p = new JPanel();/*
		Graphics g = (Graphics) p.getGraphics();*/
		ImageIcon myImage = new ImageIcon("Sprites/SpriteTest");
		BoardButton b = new BoardButton(0, 0, "Test button", myImage);
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
	
	public int getX()
	{
		return xPos;
	}
	
	public int getY()
	{
		return yPos;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Entity entity = Game.game.getBoard()[xPos][yPos];
	}
}
