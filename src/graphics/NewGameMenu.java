package graphics;

import java.awt.EventQueue;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cards.Deck.DeckEnum;
import main.Game;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

public class NewGameMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGameMenu window = new NewGameMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewGameMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			Game.game = new Game();
			Game.game.init(DeckEnum.DJ);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame = new ListenerFrame();
		frame.setBounds(0, 0, 1280, 1024);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel board = new JPanel();
		board.setBounds(147, 156, 904, 552);
		frame.getContentPane().add(board);
		
		JPanel hand = new JPanel();
		hand.setBounds(147, 741, 922, 131);
		frame.getContentPane().add(hand);
		
		StatsPanel stats = new StatsPanel();
		stats.setBounds(1079, 631, 157, 178);
		frame.getContentPane().add(stats);
		
		DeckQuitPanel deck = new DeckQuitPanel();
		deck.setBounds(1079, 883, 157, 59);
		frame.getContentPane().add(deck);
		
		AmpPanel amp = new AmpPanel();
		amp.setBounds(10, 741, 127, 201);
		frame.getContentPane().add(amp);
		
		CommandLog log = new CommandLog();
		log.setBounds(147, 883, 922, 59);
		log.init();
		frame.getContentPane().add(log);
		
		JButton nxtPhase = new JButton("Next Phase");
		nxtPhase.setFont(new Font("Tahoma", Font.BOLD, 20));
		nxtPhase.setBounds(1079, 821, 157, 49);
		frame.getContentPane().add(nxtPhase);
		
		JPanel wallpaper = new JPanel();
		wallpaper.setBounds(0, -20, 1280, 1024);
		JLabel wallpaper2 = new JLabel(new ImageIcon("Sprites/JustEffects.png"));
		wallpaper.add(wallpaper2);
		frame.getContentPane().add(wallpaper);
	}
}
