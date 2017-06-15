package graphics;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

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
		frame.setBounds(100, 100, 1280, 1024);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		CommandLog log = new CommandLog();
		log.setBounds(38, 881, 402, 55);
		frame.getContentPane().add(log);
		
		BoardPanel board = new BoardPanel();
		board.setBounds(59, -24, 648, 641);
		frame.getContentPane().add(board);
		
		AmpPanel amps = new AmpPanel();
		amps.setBounds(38, 788, 402, 66);
		frame.getContentPane().add(amps);
		
		JPanel panel = new JPanel();
		panel.setBounds(1424, 861, 460, 171);
		frame.getContentPane().add(panel);
		
		JLabel wallpaper = new JLabel(new ImageIcon("Sprites/JustEffects.png"));
		panel.add(wallpaper);
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
}
