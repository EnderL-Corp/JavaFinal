package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	private int numTiles = 15;
	private int commanderPosX, commanderPosY;
	private BoardButton[][] buttons = {
			{ new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton() },
			{ new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton() },
			{ new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton() },
			{ new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton() },
			{ new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton() },
			{ new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton() },
			{ new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton() },
			{ new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton() },
			{ new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton() },
			{ new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton() },
			{ new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton() },
			{ new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton() },
			{ new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton() },
			{ new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton() },
			{ new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(),
					new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton(), new BoardButton() } };
	/*
	 * private Color[][] players = { { Color.BLACK, Color.BLACK, Color.BLACK,
	 * Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
	 * Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
	 * Color.BLACK, Color.BLACK }, { Color.BLACK, Color.BLACK, Color.BLACK,
	 * Color.BLACK, Color.BLACK, Color.WHITE, Color.WHITE, Color.WHITE,
	 * Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK, Color.BLACK,
	 * Color.BLACK, Color.BLACK }, { Color.BLACK, Color.BLACK, Color.BLACK,
	 * Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE,
	 * Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.BLACK,
	 * Color.BLACK, Color.BLACK }, { Color.BLACK, Color.BLACK, Color.WHITE,
	 * Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE,
	 * Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE,
	 * Color.BLACK, Color.BLACK }, { Color.BLACK, Color.BLACK, Color.WHITE,
	 * Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE,
	 * Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE,
	 * Color.BLACK, Color.BLACK }, { Color.BLACK, Color.WHITE, Color.WHITE,
	 * Color.WHITE, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK,
	 * Color.BLACK, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE,
	 * Color.WHITE, Color.BLACK }, { Color.BLACK, Color.WHITE, Color.WHITE,
	 * Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK, Color.BLACK,
	 * Color.BLACK, Color.BLACK, Color.WHITE, Color.WHITE, Color.WHITE,
	 * Color.WHITE, Color.BLACK }, { Color.BLACK, Color.WHITE, Color.WHITE,
	 * Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK, Color.BLACK,
	 * Color.BLACK, Color.BLACK, Color.WHITE, Color.WHITE, Color.WHITE,
	 * Color.WHITE, Color.BLACK }, { Color.BLACK, Color.WHITE, Color.WHITE,
	 * Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK, Color.BLACK,
	 * Color.BLACK, Color.BLACK, Color.WHITE, Color.WHITE, Color.WHITE,
	 * Color.WHITE, Color.BLACK }, { Color.BLACK, Color.WHITE, Color.WHITE,
	 * Color.WHITE, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK,
	 * Color.BLACK, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE,
	 * Color.WHITE, Color.BLACK }, { Color.BLACK, Color.BLACK, Color.WHITE,
	 * Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE,
	 * Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE,
	 * Color.BLACK, Color.BLACK }, { Color.BLACK, Color.BLACK, Color.WHITE,
	 * Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE,
	 * Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE,
	 * Color.BLACK, Color.BLACK }, { Color.BLACK, Color.BLACK, Color.BLACK,
	 * Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE,
	 * Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.BLACK,
	 * Color.BLACK, Color.BLACK }, { Color.BLACK, Color.BLACK, Color.BLACK,
	 * Color.BLACK, Color.BLACK, Color.WHITE, Color.WHITE, Color.WHITE,
	 * Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK, Color.BLACK,
	 * Color.BLACK, Color.BLACK }, { Color.BLACK, Color.BLACK, Color.BLACK,
	 * Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
	 * Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
	 * Color.BLACK, Color.BLACK } };
	 */

	public BoardPanel() {
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

	/*
	 * public void paintComponent(Graphics g) { int height = 650; int width =
	 * 650; int tileHeight = height / numTiles; int tileWidth = width /
	 * numTiles;
	 * 
	 * boolean fill = false;
	 * 
	 * for (int i = 0; i < numTiles; i++) { for (int j = 0; j < numTiles; j++) {
	 * // Graphics2D g = new Graphics2D() // Graphics2D g2 = (Graphics2D) g;
	 * g.setColor(Color.black);
	 * 
	 * if ((i == 0 || i == numTiles - 1 || j == 0 || j == numTiles - 1) //
	 * border || ((i == 1 || i == numTiles - 2) && (j == 1 || j == 2 || j == 3
	 * || j == 4 || j == numTiles - 2 || j == numTiles - 3 || j == numTiles - 4
	 * || j == numTiles - 5)) // next // col // in || ((i == 2 || i == numTiles
	 * - 3) && (j == 1 || j == 2 || j == numTiles - 2 || j == numTiles - 3)) //
	 * next // col // in || ((i == 3 || i == 4 || i == numTiles - 4 || i ==
	 * numTiles - 5) && (j == 1 || j == numTiles - 2)) // next col in || ((i ==
	 * 5 || i == numTiles - 6) && (j == 6 || j == 7 || j == numTiles - 7)) //
	 * middle // out || ((i == 6 || i == 7 || i == numTiles - 7) && (j == 5 || j
	 * == 6 || j == 7 || j == numTiles - 7 || j == numTiles - 6))) // middle {
	 * g.fillRect(i * tileWidth, j * tileHeight, tileWidth, tileHeight); }
	 * 
	 * if (((i == 0 || i == numTiles - 1) && (j == 0 || j == 1 || j == numTiles
	 * - 2 || j == numTiles - 1)) || ((i == 1 || i == numTiles - 2) && (j == 0
	 * || j == numTiles - 1))) { g.fillRect(i * tileWidth, j * tileHeight,
	 * tileWidth, tileHeight); }
	 * 
	 * 
	 * if (fill) { g.fillRect(i*tileWidth,j*tileHeight,tileWidth,tileHeight); }
	 * 
	 * else { g.drawRect(i * tileWidth, j * tileHeight, tileWidth, tileHeight);
	 * }
	 * 
	 * if (j == commanderPosX && i == commanderPosY) { g.setColor(Color.red);
	 * g.fillRect(i * tileWidth, j * tileHeight, tileWidth, tileHeight); }
	 * 
	 * fill = !fill; }
	 * 
	 * if (numTiles%2 == 0) { fill = !fill;
	 * 
	 * } } }
	 */

	public void paintComponent(Graphics g) {
		int height = 650;
		int width = 650;
		int tileHeight = height / numTiles;
		int tileWidth = width / numTiles;

		for (int i = 0; i < numTiles; i++) {
			for (int j = 0; j < numTiles; j++) {
				// Graphics2D g = new Graphics2D() // Graphics2D g2 =
				// (Graphics2D) g;

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
					bb.setBackground(Color.RED);
					bb.setIcon(new ImageIcon("Sprites/TestSprite.png"));
					bb.setBounds(i * tileWidth, j * tileHeight, tileWidth, tileHeight);
				}
			}
		}

	}

	public void changeCommanderPos(int x, int y) {
		if (x < 0 || y < 0 || x > numTiles - 1 || y > numTiles - 1)
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
