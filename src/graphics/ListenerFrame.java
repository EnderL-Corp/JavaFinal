package graphics;

import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class ListenerFrame extends JFrame
{
	private static final long serialVersionUID = -3927017161571715947L;

	public ListenerFrame()
	{
		KeyListener listener = new KBListener();
		addKeyListener(listener);
		setFocusable(true);
	}
}
