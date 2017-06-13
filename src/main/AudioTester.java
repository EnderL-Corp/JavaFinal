/**
 * @author Luke
 * The tester for Milestone "Make audio play off events" - COMPLETED
 */

package main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class AudioTester extends JFrame implements ActionListener 
{

	Audio au = null;
	
	private static final long serialVersionUID = 1L;
	JButton range;
	JButton melee;
	JButton mm;
	JButton gm;

	public static void main(String[] args) {
		new AudioTester().setVisible(true);
	}

	private AudioTester() {
		super("Audio Tester");

		setSize(200, 300);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new FlowLayout());

		range = new JButton("Range");
		range.setActionCommand("r");
		range.addActionListener(this);
		melee = new JButton("Melee");
		melee.setActionCommand("m");
		melee.addActionListener(this);
		mm = new JButton("Main Menu");
		mm.setActionCommand("mmm");
		mm.addActionListener(this);
		gm = new JButton("Game Music");
		gm.setActionCommand("gm");
		gm.addActionListener(this);

		add(range);
		add(melee);
		add(mm);
		add(gm);
}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String a = e.getActionCommand();
		if (a.equals("r")) {
			au = new Audio("RangeHit");
			au.start();
		} else if (a.equals("mmm")) {
			au = new Audio("MainMenu");
			au.start();
			au = null;
		} else if (a.equals("m")) {
			au = new Audio("MeleeHit");
			au.start();
			au = null;
		} else if (a.equals("gm")){
			au = new Audio("InGame");
			au.start();
			au = null;
		}

	}

}