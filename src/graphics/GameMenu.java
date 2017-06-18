package graphics;

import java.awt.EventQueue;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cards.Deck.DeckEnum;
import main.Audio;
import main.Game;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * For interfacing with the player while the game is in progress.
 * @author Santiago Callegari, Srihari Subramanian
 *
 */
public class GameMenu {

	private ListenerFrame frame;
	//private BoardPanel board;
	private HandPanel hand;
	private StatsPanel stats;
	private DeckQuitPanel deck;
	private AmpPanel amp;
	private CommandLog log;
	private InfoPanel info;
	private JButton nxtPhase;
	private JPanel background;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameMenu window = new GameMenu();
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
	public GameMenu() {
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
			
		
		/*board = new BoardPanel();
		board.setBounds(341, 68, 480, 480);
		frame.getContentPane().add(board);
		board.repaint();*/
		
		hand = new HandPanel();
		hand.setBounds(147, 579, 922, 200);
		frame.getContentPane().add(hand);
		
		stats = new StatsPanel();
		stats.setBounds(1079, 541, 157, 178);
		frame.getContentPane().add(stats);
		
		deck = new DeckQuitPanel();
		deck.setBounds(1079, 790, 157, 59);
		frame.getContentPane().add(deck);
		
		amp = new AmpPanel();
		amp.setBounds(10, 648, 127, 201);
		frame.getContentPane().add(amp);
		
		log = new CommandLog();
		log.setBounds(147, 790, 922, 59);
		log.init();
		frame.getContentPane().add(log);
		
		info = new InfoPanel();
		info.setBounds(966, 391, 267, 139);
		frame.getContentPane().add(info);
		
		nxtPhase = new JButton("Next Phase");
		nxtPhase.setFont(new Font("Tahoma", Font.BOLD, 20));
		nxtPhase.setBounds(1079, 730, 157, 49);
		nxtPhase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.game.changePhase();
			}
		});
		frame.getContentPane().add(nxtPhase);
		
		background = new JPanel();
		background.setBounds(0, 0, 1280, 1024);
		JLabel wallpaper = new JLabel(new ImageIcon("Sprites/JustEffects.png"));
		background.add(wallpaper);
		frame.getContentPane().add(background);
		
		new Audio("InGame");
	}
	
	public void refresh() {
		board.repaint();
		hand.refresh();
		amp.refresh();
		stats.statRefresh();
		frame.requestFocus();
	}

	public JFrame getFrame() {
		return frame;
	}

	public JPanel getBoard() {
		return board;
	}

	public HandPanel getHand() {
		return hand;
	}

	public StatsPanel getStats() {
		return stats;
	}

	public DeckQuitPanel getDeck() {
		return deck;
	}

	public AmpPanel getAmp() {
		return amp;
	}

	public CommandLog getLog() {
		return log;
	}

	public InfoPanel getInfo() {
		return info;
	}

	public JButton getNxtPhase() {
		return nxtPhase;
	}
	
	
}
