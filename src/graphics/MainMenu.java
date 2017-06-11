package graphics;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import main.Game;

public class MainMenu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JTextField ip;
	JButton host;
	JButton join;
	JButton quit;

	public static void main(String[] args) {
		new MainMenu().setVisible(true);
	}

	private MainMenu() {
		super("Main Menu");

		setSize(1920, 1080);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new FlowLayout());

		ip = new JTextField("Enter IP", 30);
		ip.setActionCommand("ip");
		ip.addActionListener(this);
		host = new JButton("Host Game");
		host.setActionCommand("host");
		host.addActionListener(this);
		join = new JButton("Join Game");
		join.setActionCommand("join");
		join.addActionListener(this);
		join.setEnabled(false);
		quit = new JButton("Quit Game");
		quit.setActionCommand("quit");
		quit.addActionListener(this);

		add(host);
		add(join);
		add(ip);
		add(quit);
		
		JLabel wallpaper = new JLabel(new ImageIcon("Sprites/unknown.png"));
		add(wallpaper);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String writtenIP = "";
		String a = e.getActionCommand();
		if (a.equals("host")) {
			System.out.println("game hosting");
			// launch server
			// launch game connecting to server via PC ip
		} else if (a.equals("ip")) {
			System.out.println("typing ip");
			join.setEnabled(true);
			writtenIP = ip.getText();
			System.out.println(writtenIP);
		} else if (a.equals("join")) {
			System.out.println("searching for host");
			// launch game searching for specified ip
		} else if (a.equals("quit")) {
			System.out.println("am quiting");
			System.exit(0);
		}

	}

}
