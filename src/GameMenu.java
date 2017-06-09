import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import graphics.AmpPanel;
import graphics.BoardPanel;
import graphics.CommandLog;

public class GameMenu {

	private JFrame frame;

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
		frame = new JFrame();
		frame.setBounds(100, 100, 1920, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		CommandLog log = new CommandLog();
		log.setBounds(25, 966, 1829, 66);
		frame.getContentPane().add(log);
		
		BoardPanel board = new BoardPanel();
		board.setBounds(593, 32, 646, 646);
		frame.getContentPane().add(board);
		
		AmpPanel amps = new AmpPanel();
		amps.setBounds(40, 803, 400, 150);
		frame.getContentPane().add(amps);
	}
}
