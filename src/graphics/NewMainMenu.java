package graphics;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import main.Game;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.net.UnknownHostException;

import javax.swing.JLabel;

public class NewMainMenu {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewMainMenu window = new NewMainMenu();
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
	public NewMainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1280, 1024);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextField ip = new JTextField();
		ip.setFont(new Font("Tahoma", Font.BOLD, 20));
		ip.setBounds(10, 174, 180, 52);
		frame.getContentPane().add(ip);
		ip.setColumns(10);
		
		JButton host = new JButton("Host Game");
		host.setFont(new Font("Tahoma", Font.BOLD, 20));
		host.setBounds(10, 66, 180, 97);
		frame.getContentPane().add(host);
		
		JButton join = new JButton("Join Game");
		join.setFont(new Font("Tahoma", Font.BOLD, 20));
		join.setBounds(10, 236, 180, 97);
		frame.getContentPane().add(join);
		
		JButton quit = new JButton("Quit Game");
		quit.setFont(new Font("Tahoma", Font.BOLD, 20));
		quit.setBounds(10, 344, 180, 97);
		frame.getContentPane().add(quit);
		
		JLabel wallpaper = new JLabel(new ImageIcon("Sprites/unknown.png"));
		wallpaper.setBounds(0, 0, 1264, 985);
		frame.getContentPane().add(wallpaper);
	}
}
