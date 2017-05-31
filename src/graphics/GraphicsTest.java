package graphics;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class GraphicsTest extends JFrame implements ActionListener {
	public static void main(String[] args) {
		new GraphicsTest().setVisible(true);			
	}
		private GraphicsTest() {
			super("Testing");
				
			setSize(1280,720);
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			setResizable(false);
			
			setLayout(new FlowLayout());
			JButton host = new JButton("Host Game");
			host.setActionCommand("host");
			host.addActionListener(this);
			JButton join = new JButton("Join Game");
			join.setActionCommand("join");
			join.addActionListener(this);
			JButton quit = new JButton("Quit Game");
			quit.setActionCommand("quit");
			quit.addActionListener(this);
			add(host);
			add(join);
			add(quit);
		
		}
		public void actionPreformed(ActionEvent e){
		 String a = e.getActionCommand();
		 if(a.equals("host"))
			System.out.println("game hosting");
		 
		 else if(a.equals("join"))
			 System.out.println("searching for host");
		 
		 else
			 System.out.println("am quiting");
		}
		
}
