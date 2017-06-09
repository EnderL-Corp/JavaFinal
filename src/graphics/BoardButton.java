package graphics;

import javax.accessibility.Accessible;
import javax.swing.AbstractButton;
import javax.swing.DefaultButtonModel;
import javax.swing.Icon;

public class BoardButton extends AbstractButton implements Accessible {

	private static final long serialVersionUID = 1L;

	public BoardButton(String text) {
		this(text, null);
	}

	public BoardButton(String text, Icon icon) {
		setModel(new DefaultButtonModel());

		init(text, icon);
	}
}
