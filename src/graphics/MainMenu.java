package graphics;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import main.Audio;
import main.Game;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

import javax.swing.JLabel;

/**
 * 
 * 
 * @author Santiago Callegari, Sri
 *
 */
public class MainMenu implements ActionListener {

	private JFrame frame;
	JTextField ip;
	JButton host;
	JButton join;
	JButton quit;

	String writtenIP = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
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
	public MainMenu() {
		initialize();
		new Audio("MainMenu");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(0, 0, 1280, 1024);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		ip = new JTextField();
		ip.setFont(new Font("Tahoma", Font.BOLD, 20));
		ip.setBounds(10, 174, 180, 52);
		frame.getContentPane().add(ip);
		ip.setColumns(10);
		ip.setActionCommand("ip");
		ip.addActionListener(this);

		host = new JButton("Host Game");
		host.setFont(new Font("Tahoma", Font.BOLD, 20));
		host.setBounds(10, 66, 180, 97);
		frame.getContentPane().add(host);
		host.setActionCommand("host");
		host.addActionListener(this);

		join = new JButton("Join Game");
		join.setFont(new Font("Tahoma", Font.BOLD, 20));
		join.setBounds(10, 236, 180, 97);
		frame.getContentPane().add(join);
		join.setActionCommand("join");
		join.addActionListener(this);
		join.setEnabled(false);

		quit = new JButton("Quit Game");
		quit.setFont(new Font("Tahoma", Font.BOLD, 20));
		quit.setBounds(10, 344, 180, 97);
		frame.getContentPane().add(quit);
		quit.setActionCommand("quit");
		quit.addActionListener(this);

		JLabel wallpaper = new JLabel(new ImageIcon("Sprites/unknown.png"));
		wallpaper.setBounds(0, 0, 1264, 985);
		frame.getContentPane().add(wallpaper);

		frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String a = e.getActionCommand();
		if (a.equals("host")) {
			System.out.println("Hosting game...");
			try {
				Game.createHost(java.net.InetAddress.getLocalHost().getHostAddress());
				Game.game.setGameMainMenu(this);
				frame.setVisible(false);
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
		} else if (a.equals("ip")) {
			System.out.println("Typing ip...");
			join.setEnabled(true);
			writtenIP = ip.getText();
			System.out.println(writtenIP);
		} else if (a.equals("join")) {
			System.out.println("Searching for host...");
			Game.createClient(writtenIP);
			Game.game.setGameMainMenu(this);
			frame.setVisible(false);
		} else if (a.equals("quit")) {
			System.out.println("Shutting down...");
			System.exit(0);
		}
	}
}
