package graphics;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class NewGameMenu {

	private ListenerFrame frame;

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
		frame = new ListenerFrame();
		frame.setBounds(0, 0, 1280, 1024);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JFrame board = new JFrame();
		board.setBounds(37, 11, 122, 77);
		frame.getContentPane().add(board);
		
		JPanel hand = new JPanel();
		hand.setBounds(37, 152, 100, 106);
		frame.getContentPane().add(hand);
		
		JPanel stats = new JPanel();
		stats.setBounds(37, 293, 82, 77);
		frame.getContentPane().add(stats);
		
		InfoPanel info = new InfoPanel();
		info.setBounds(60, 423, 171, 124);
		frame.getContentPane().add(info);
		
		DeckQuitPanel deck = new DeckQuitPanel();
		deck.setBounds(111, 597, 171, 101);
		frame.getContentPane().add(deck);
		
		AmpPanel amp = new AmpPanel();
		amp.setBounds(405, 207, 183, 209);
		frame.getContentPane().add(amp);
		
		JPanel log = new JPanel();
		log.setBounds(405, 529, 154, 178);
		frame.getContentPane().add(log);
		
		JButton endTurn = new JButton("End Turn");
		endTurn.setBounds(287, 81, 89, 23);
		frame.getContentPane().add(endTurn);
	}
}
