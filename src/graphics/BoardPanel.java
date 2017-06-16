package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	private int numTiles = 15;
	private int commanderPosX = 7, commanderPosY = 1;
	private BoardButton[][] buttons = new BoardButton[15][15];

	public BoardPanel() {
		for(int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons[0].length; j++) {
				buttons[i][j] = new BoardButton(i, j);
			}
		}
		setLayout(new GridLayout());
		init();
	}

	public void init() {
		int height = 650;
		int width = 650;
		int tileHeight = height / numTiles;
		int tileWidth = width / numTiles;

		for (int i = 0; i < numTiles; i++) {
			for (int j = 0; j < numTiles; j++) {
				BoardButton bb = buttons[i][j];
				bb.setBackground(Color.WHITE);
				bb.setBounds(i * tileWidth, j * tileHeight, tileWidth, tileHeight);
				this.add(bb);
			}
		}
	}

	public void paintComponent(Graphics g) {
		int height = 650;
		int width = 650;
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
					BoardButton bb = buttons[i][j];
					bb.setBackground(Color.BLACK);
					bb.setIcon(null);
					bb.setBounds(i * tileWidth, j * tileHeight, tileWidth, tileHeight);
				} else {
					BoardButton bb = buttons[i][j];
					bb.setBackground(Color.WHITE);
					bb.setIcon(null);
					bb.setBounds(i * tileWidth, j * tileHeight, tileWidth, tileHeight);
				}

				if (j == commanderPosX && i == commanderPosY) {
					BoardButton bb = buttons[i][j];
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
}
