package graphics;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class GraphicsTest extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JTextField  ip;
	JButton host;
	JButton join;
	JButton quit;
	public static void main(String[] args) {
		new GraphicsTest().setVisible(true);			
	}
	
	private GraphicsTest() {
		super("Testing");
			
		setSize(1280,720);
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
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 String a = e.getActionCommand();
		 if(a.equals("host"))
			System.out.println("game hosting");
		 //launch server
		 //launch game connecting to server via PC ip
		 
		 else if(a.equals("ip")){
			 System.out.println("typing ip");
			 join.setEnabled(true); 
			 System.out.println(ip.getText());
		 }
		 else if(a.equals("join")){
			 System.out.println("searching for host");
			 //launch game searching for specified ip
		 }
		 else if(a.equals("quit")){
			 System.out.println("am quiting");
		     setVisible(false);
		     dispose();
	     }
	}
		
}
