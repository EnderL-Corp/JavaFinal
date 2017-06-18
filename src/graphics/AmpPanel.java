package graphics;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import cards.Amplifier;
import cards.Amplifier.AmpEnum;
import main.Game;

public class AmpPanel extends JPanel implements ActionListener {

	private JButton Amp1;
	private JButton Amp2;
	private JButton Amp3;
	private JButton Amp4;
	private JButton Amp5;
	private JButton Sac;
	private boolean sacActive = false;

	public AmpPanel() {
		setBackground(new Color(0, 0, 0, 0));
		// add a phat layout
		Amp1 = new JButton("open");
		Amp1.setActionCommand("0");
		Amp1.addActionListener(this);
		Amp2 = new JButton("open");
		Amp2.setActionCommand("1");
		Amp2.addActionListener(this);
		Amp3 = new JButton("open");
		Amp3.setActionCommand("2");
		Amp3.addActionListener(this);
		Amp4 = new JButton("open");
		Amp4.setActionCommand("3");
		Amp4.addActionListener(this);
		Amp5 = new JButton("open");
		Amp5.setActionCommand("4");
		Amp5.addActionListener(this);
		Sac = new JButton("Sacrifice");
		Sac.setActionCommand("Sac");
		Sac.addActionListener(this);

		add(Amp1);
		add(Amp2);
		add(Amp3);
		add(Amp4);
		add(Amp5);
		add(Sac);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String a = e.getActionCommand();
		if(Game.game.getAmpAt(Integer.parseInt(a)) == null)
		{
			Game.game.addToPlayerActionQueue(new Amplifier(AmpEnum.NONE));
			((JButton)e.getSource()).setText("empty");
		}
		else
		{
			((JButton)e.getSource()).setText(Game.game.getAmpAt(Integer.parseInt(a)).getName());
			Game.game.addToPlayerActionQueue(Game.game.getAmpAt(Integer.parseInt(a)));
			Game.game.getGameMenu().getInfo().newDisplay(Game.game.getAmpAt(Integer.parseInt(a)));
		}
	}
}
