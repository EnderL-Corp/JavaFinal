package graphics;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CommandLog extends JPanel {
	JTextArea log;
public CommandLog(){
	log = new JTextArea ("command.logImplemented");
    JScrollPane scroll = new JScrollPane (log, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    add(scroll);
    setVisible (true);
}
public void logRefresh(){
	log.setText(log.getText() + "\n" + "command.logImplemented");
	setVisible (false);
	setVisible (true);
}

}
