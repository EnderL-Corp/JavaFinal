package graphics;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cards.Entity;
import main.Game;

public class BoardPanel extends JPanel implements ActionListener {
	private int numTiles = 15;
	private int commanderPosX = 7, commanderPosY = 1;
	private JButton[][] buttons = new JButton[15][15];
	private ArrayList<JButton> activeButton = new ArrayList<JButton>();

	public BoardPanel() {
		/*for(int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons[0].length; j++) {
				buttons[i][j] = new BoardButton();
			}
		}*/
		setLayout(new GridLayout());
		init();
	}

	public void init() {
		int height = 480;
		int width = 480;
		int tileHeight = height / numTiles;
		int tileWidth = width / numTiles;

		for (int i = 0; i < numTiles; i++) {
			for (int j = 0; j < numTiles; j++) {
				JButton bb = buttons[i][j] = new JButton();
				bb.setActionCommand(i + ", " + j);
				bb.addActionListener(this);
				bb.setBackground(Color.WHITE);
				bb.setBounds(i * tileWidth, j * tileHeight, tileWidth, tileHeight);
				this.add(bb);
			}
		}
	}

	public void paintComponent(Graphics g) {
		int height = 480;
		int width = 480;
		int tileHeight = height / numTiles;
		int tileWidth = width / numTiles;

		for (int i = 0; i < numTiles; i++) {
			for (int j = 0; j < numTiles; j++) {

				// BLACK STUFF
				if ((i == 0 || i == numTiles - 1 || j == 0 || j == numTiles - 1)
						|| ((i == 1 || i == numTiles - 2) && (j == 1 || j == 2 || j == 3 || j == 4 || j == numTiles - 2
								|| j == numTiles - 3 || j == numTiles - 4 || j == numTiles - 5))
						|| ((i == 2 || i == numTiles - 3)
								&& (j == 1 || j == 2 || j == numTiles - 2 || j == numTiles - 3))
						|| ((i == 3 || i == 4 || i == numTiles - 4 || i == numTiles - 5)
								&& (j == 1 || j == numTiles - 2))
						|| ((i == 5 || i == numTiles - 6) && (j == 6 || j == 7 || j == numTiles - 7))
						|| ((i == 6 || i == 7 || i == numTiles - 7)
								&& (j == 5 || j == 6 || j == 7 || j == numTiles - 7 || j == numTiles - 6))) {
					JButton bb = buttons[i][j];
					bb.setBackground(Color.BLACK);
					bb.setIcon(null);
					bb.setBounds(i * tileWidth, j * tileHeight, tileWidth, tileHeight);
				} else {
					JButton bb = buttons[i][j];
					bb.setBackground(Color.WHITE);
					bb.setIcon(null);
					Entity e = Game.game.getEntityAt(i, j);
					if(e != null) {
						bb.setIcon(new ImageIcon("Sprites/" + e.getName() + ".png"));
						if(!isActive(bb))
							activeButton.add(bb);
					}
					else
						if(isActive(bb))
							activeButton.remove(bb);
							
					bb.setBounds(i * tileWidth, j * tileHeight, tileWidth, tileHeight);
				}

				if (j == commanderPosX && i == commanderPosY) {
					JButton bb = buttons[i][j];
					//bb.setBackground(Color.RED);
					bb.setIcon(new ImageIcon("Sprites/DJ.png"));
					bb.setBounds(i * tileWidth, j * tileHeight, tileWidth, tileHeight);
				}
			}
		}

	}

	public void changeCommanderPos(int x, int y) {
		if ((x < 0 || y < 0 || x > numTiles - 1 || y > numTiles - 1) //if out of bounds
				|| (buttons[x][y]).getBackground() == Color.BLACK) // if "void"
			return;
		commanderPosX = x;
		commanderPosY = y;
	}

	/**
	 * @return commander x position
	 */
	public int getCommanderX() {
		return commanderPosX;
	}

	/**
	 * @return commander y position
	 */
	public int getCommanderY() {
		return commanderPosY;
	}

	public void changeMapSize(String size) {
		switch (size) {
		case "small":
			numTiles = 9;
			break;
		case "medium":
			numTiles = 15;
			break;
		case "large":
			numTiles = 21;
			break;
		default:
			numTiles = 9;
			break;
		}
		commanderPosX = numTiles / 2;
	}
	
	private boolean isActive(JButton b) {
		for(JButton jb : activeButton) {
			if(jb.equals(b))
				return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new BoardPanel());
		f.setSize(700, 700);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons[0].length; j++) {
				if(e.getSource() == buttons[i][j]) {
					Game.game.addToPlayerActionQueue(Game.game.getEntityAt(i, j));
				}
			}
		}
	}
}
