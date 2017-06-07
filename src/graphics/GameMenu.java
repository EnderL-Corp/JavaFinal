package graphics;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;



public class GameMenu extends JFrame implements ActionListener {

	private BoardPanel b;
	private static final long serialVersionUID = 2L;
	public static void main(String[] args) {
		new GameMenu().setVisible(true);
	}
	private GameMenu() {
		super("Twisted Remix");
			
		setSize(1920,1080);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new FlowLayout());
		b = new BoardPanel();
		add(b);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
