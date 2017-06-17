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
		board.setBounds(159, 161, 674, 547);
		frame.getContentPane().add(board);
		
		JPanel hand = new JPanel();
		hand.setBounds(147, 741, 922, 131);
		frame.getContentPane().add(hand);
		
		StatsPanel stats = new StatsPanel();
		stats.setBounds(1079, 657, 157, 152);
		frame.getContentPane().add(stats);
		
		InfoPanel info = new InfoPanel();
		info.setBounds(1019, 348, 217, 298);
		frame.getContentPane().add(info);
		
		DeckQuitPanel deck = new DeckQuitPanel();
		deck.setBounds(1079, 883, 157, 59);
		frame.getContentPane().add(deck);
		
		AmpPanel amp = new AmpPanel();
		amp.setBounds(10, 777, 127, 165);
		frame.getContentPane().add(amp);
		
		CommandLog log = new CommandLog();
		log.setBounds(147, 883, 922, 59);
		log.init();
		frame.getContentPane().add(log);
		
		JButton nxtPhase = new JButton("Next Phase");
		nxtPhase.setFont(new Font("Tahoma", Font.BOLD, 20));
		nxtPhase.setBounds(1079, 821, 157, 49);
		frame.getContentPane().add(nxtPhase);
	}
}
