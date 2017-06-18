package graphics;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import cards.Cyborg;
import cards.Troop;
import cards.TroopEnum;
import main.Game;

public class HandPanel extends JPanel implements ActionListener {
	public HandPanel() {
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		JButton cardSlot1 = new JButton("Open Slot");
		cardSlot1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.game.getGameMenu().getInfo().newDisplay(new Cyborg(0, 0, 4, TroopEnum.RANGER));
			}
		});
		cardSlot1.setBounds(82, 11, 122, 177);
		add(cardSlot1);

		JButton cardSlot2 = new JButton("Open Slot");
		cardSlot2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cardSlot2.setBounds(214, 11, 122, 177);
		add(cardSlot2);

		JButton cardSlot3 = new JButton("Open Slot");
		cardSlot3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cardSlot3.setBounds(346, 11, 122, 177);
		add(cardSlot3);

		JButton cardSlot4 = new JButton("Open Slot");
		cardSlot4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cardSlot4.setBounds(478, 11, 122, 177);
		add(cardSlot4);

		JButton cardSlot5 = new JButton("Open Slot");
		cardSlot5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cardSlot5.setBounds(610, 11, 122, 177);
		add(cardSlot5);

		JButton cardSlot6 = new JButton("Open Slot");
		cardSlot6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cardSlot6.setBounds(742, 11, 122, 177);
		add(cardSlot6);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
	}
}
