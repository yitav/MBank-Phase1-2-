package ui;

import javax.swing.JFrame;

public abstract class AbstractFrame extends JFrame{

	public AbstractFrame() {
		prepareGUI();
		///////added later
		pack();
		setLocationRelativeTo(null);
		////////end of added
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public abstract void prepareGUI();
	
}
