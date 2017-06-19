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

public class AudioTester extends JFrame implements ActionListener {

	Audio au = null;

	private static final long serialVersionUID = 1L;
	JButton range;
	JButton blast;
	JButton melee;
	JButton deflect;
	JButton mirror;
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
		blast = new JButton("Blast");
		blast.setActionCommand("b");
		blast.addActionListener(this);
		deflect = new JButton("Deflect");
		deflect.setActionCommand("d");
		deflect.addActionListener(this);
		mirror = new JButton("Mirror");
		mirror.setActionCommand("mi");
		mirror.addActionListener(this);
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
		add(blast);
		add(deflect);
		add(mirror);
		add(mm);
		add(gm);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String a = e.getActionCommand();
		if (a.equals("r")) {
			new Audio("FX", "Range");
		} else if (a.equals("mmm")) {
			new Audio("MainMenu");
		} else if (a.equals("m")) {
			new Audio("FX", "Melee");
		} else if (a.equals("gm")) {
			new Audio("InGame");
		} else if (a.equals("b")) {
			new Audio("FX", "Blast");
		} else if (a.equals("d")) {
			new Audio("FX", "Deflect");
		} else if (a.equals("mi")) {
			new Audio("FX", "Mirror");
		}

	}

}