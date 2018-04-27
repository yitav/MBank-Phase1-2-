package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ActivitiesFrame extends AbstractFrame{

	public ActivitiesFrame() {
		super();
		setTitle("Administrator Management");
	}

	@Override
	public void prepareGUI() {
		setSize(500,500);
		add(new TabsPane(this));
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("options");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("Log out");
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new LoginFrame();
				setVisible(false);
			}
		});
		
		menu.add(menuItem);
		setJMenuBar(menuBar);
	}

}
