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

import cards.Card;
import cards.Commander;
import cards.Entity;
import cards.MovePoint;
import cards.Troop;
import main.Game;

/**
 * Panel representing the most recent version of the board. A player whose turn it is can also 
 * interact with this board to change contents of board in game.
 * @author Srihari Subramanian
 *
 */
public class BoardPanel extends JPanel implements ActionListener {
	private int numTiles = 15;
	private JButton[][] buttons = new JButton[15][15];

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
					if(e != null && e instanceof Troop) {
						bb.setIcon(((Troop)e).getIcon());
						bb.setBackground(Game.game.getColor());
					}
					else if(e != null && e instanceof Commander) {
						bb.setIcon(new ImageIcon("Sprites/" + Game.game.getTypeAsString() + ".png"));
						bb.setBackground(Game.game.getColor());
					}
							
					bb.setBounds(i * tileWidth, j * tileHeight, tileWidth, tileHeight);
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons[0].length; j++) {
				if(e.getSource() == buttons[i][j]) {
					if(Game.game.getEntityAt(i, j) == null) {
						Game.game.addToPlayerActionQueue(new MovePoint(i, j));
					}
					else {
						Game.game.addToPlayerActionQueue(Game.game.getEntityAt(i, j));
						Game.game.getGameMenu().getInfo().newDisplay(Game.game.getEntityAt(i, j));
					}
				}
			}
		}
	}
}
