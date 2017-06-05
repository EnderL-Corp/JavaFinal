package graphics;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeckQuitPanel extends JPanel implements ActionListener {
	JButton quit;
	JLabel deckSize;
	private DeckQuitPanel(){
		setLayout(new FlowLayout());
		deckSize = new JLabel(intDeckSize + " / 26")
		add(deckSize);
				
		quit = new JButton("Quit Game");
		quit.setActionCommand("quit");
		quit.addActionListener(this);
		add(quit);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("am quiting");
	   GameMenu.this.setVisible(false);
	    GameMenu.this.dispose();
		
	}
}
