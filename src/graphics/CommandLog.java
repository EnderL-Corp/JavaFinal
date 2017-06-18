package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * For logging what the other client has done in a panel.
 * @author Srihari Subramanian
 *
 */
public class CommandLog extends JPanel {
	private static final long serialVersionUID = -8782011634375723048L;
	private static JTextArea log;
	private JScrollPane scroll;

	public CommandLog() {
		log = new JTextArea();
		scroll = new JScrollPane(log, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		setLayout(new BorderLayout());
	}
	
	public void init() {
		log.setEditable(false);
		scroll.setBounds(super.getBounds());
		add(scroll);
		setVisible(true);
		publish("Welcome to the game.");
	}

	public void logRefresh() {
		log.append("\nRefreshing...");
		repaint();
	}
	
	public static void publish(String toLog) {
		CommandLog.log.append("\n" + toLog);
	}
}
