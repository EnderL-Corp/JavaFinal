package graphics;

import java.awt.EventQueue;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cards.Deck.DeckEnum;
import main.Game;

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
			Game g = new Game();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame = new ListenerFrame();
		frame.setBounds(0, 0, 1280, 1024);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel board = new JPanel();
		board.setBounds(37, 11, 122, 77);
		frame.getContentPane().add(board);
		
		JPanel hand = new JPanel();
		hand.setBounds(37, 152, 100, 106);
		frame.getContentPane().add(hand);
		
		StatsPanel stats = new StatsPanel();
		stats.setBounds(1079, 579, 171, 229);
		frame.getContentPane().add(stats);
		
		InfoPanel info = new InfoPanel();
		info.setBounds(60, 423, 171, 124);
		frame.getContentPane().add(info);
		
		DeckQuitPanel deck = new DeckQuitPanel();
		deck.setBounds(1079, 883, 171, 59);
		frame.getContentPane().add(deck);
		
		AmpPanel amp = new AmpPanel();
		amp.setBounds(901, 503, 349, 66);
		frame.getContentPane().add(amp);
		
		CommandLog log = new CommandLog();
		log.setBounds(915, 806, 154, 136);
		log.init();
		frame.getContentPane().add(log);
		
		JButton nxtPhase = new JButton("Next Phase");
		nxtPhase.setFont(new Font("Tahoma", Font.BOLD, 20));
		nxtPhase.setBounds(1079, 821, 171, 49);
		frame.getContentPane().add(nxtPhase);
	}
}
