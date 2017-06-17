package graphics;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * 
 * @author Santiago Callegari, Srihari Subramanian
 *
 */
public class GameMenu implements java.io.Serializable{
	private static final long serialVersionUID = -203776595464586989L;
	
	private JFrame frame;
	private CommandLog log;
	private BoardPanel board;
	private AmpPanel amps;
	private JPanel panel;
	

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
		frame.setBounds(5, 5, 1280, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		log = new CommandLog();
		log.init();
		frame.getContentPane().add(log);
		
		board = new BoardPanel();
		board.setBounds(400, 25, 480, 480);
		frame.getContentPane().add(board);
		
		amps = new AmpPanel();
		amps.setBounds(25, 760, 400, 66);
		frame.getContentPane().add(amps);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 1280, 850);
		JLabel wallpaper = new JLabel(new ImageIcon("Sprites/JustEffects.png"));
		panel.add(wallpaper);
		frame.getContentPane().add(panel);
	}
	
	public void refresh() {
		board.repaint();
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
}
