package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AmpPanel extends JPanel implements ActionListener {
	private JButton Amp1;
	private JButton Amp2;
	private JButton Amp3;
	private JButton Amp4;
	private JButton Amp5;
	private JButton Sac;
	private boolean sacActive = false;

	public AmpPanel() {
		// add a phat layout
		Amp1 = new JButton("open");
		Amp1.setActionCommand("Amp1");
		Amp1.addActionListener(this);
		Amp2 = new JButton("open");
		Amp2.setActionCommand("Amp2");
		Amp2.addActionListener(this);
		Amp3 = new JButton("open");
		Amp3.setActionCommand("Amp3");
		Amp3.addActionListener(this);
		Amp4 = new JButton("open");
		Amp4.setActionCommand("Amp4");
		Amp4.addActionListener(this);
		Amp5 = new JButton("open");
		Amp5.setActionCommand("Amp5");
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
		if (!sacActive && a.equals("Amp1")) {
			// amp in hand that was selected with p is placed in the arry slot 0
			Amp1.setText("ampSlot.name1");
			Amp1.setEnabled(false);
		} else if (!sacActive && a.equals("Amp2")) {
			// amp in hand that was selected with p is placed in the arry slot 1
			Amp2.setText("ampSlot.name2");
			Amp2.setEnabled(false);
		} else if (!sacActive && a.equals("Amp3")) {
			// amp in hand that was selected with p is placed in the arry slot 2
			Amp3.setText("ampSlot.name3");
			Amp3.setEnabled(false);
		} else if (!sacActive && a.equals("Amp4")) {
			// amp in hand that was selected with p is placed in the arry slot 3
			Amp4.setText("ampSlot.name4");
			Amp4.setEnabled(false);
		} else if (!sacActive && a.equals("Amp5")) {
			// amp in hand that was selected with p is placed in the arry slot 4
			Amp5.setText("ampSlot.name5");
			Amp5.setEnabled(false);
		} else if (sacActive && a.equals("Amp1")) {
			// removes amp from amp array and places it in graveyard
			Amp1.setText("open");
			sacActive = false;
		} else if (sacActive && a.equals("Amp2")) {
			// removes amp from amp array and places it in graveyard
			Amp2.setText("open");
			sacActive = false;
		} else if (sacActive && a.equals("Amp3")) {
			// removes amp from amp array and places it in graveyard
			Amp3.setText("open");
			sacActive = false;
		} else if (sacActive && a.equals("Amp4")) {
			// removes amp from amp array and places it in graveyard
			Amp4.setText("open");
			sacActive = false;
		} else if (sacActive && a.equals("Amp5")) {
			// removes amp from amp array and places it in graveyard
			Amp5.setText("open");
			sacActive = false;
		} else if (a.equals("Sac")) {
		}
		if ((Amp1.getText() != "Amp1") && (Amp2.getText() != "Amp2") && (Amp3.getText() != "Amp3")
				&& (Amp5.getText() != "Amp5") && (Amp4.getText() != "Amp4")) {
			Amp1.setEnabled(true);
			Amp2.setEnabled(true);
			Amp3.setEnabled(true);
			Amp4.setEnabled(true);
			Amp5.setEnabled(true);
			sacActive = true;

		}

	}

}
