package ui;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabsPane extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public TabsPane(JFrame parent) {
        super(new GridLayout(1, 1));
         
        JTabbedPane tabbedPane = new JTabbedPane();
         
        JComponent systemPropsPanel = new SystemPropertiesPanel(parent);
        tabbedPane.addTab("System Properties", systemPropsPanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
         
        JComponent newClientAccountPanel = new CreateClientAccountPanel();
        tabbedPane.addTab("Add new client and account", newClientAccountPanel);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
         
        ViewClientDetailsPanel viewClientDetailsPanel = new ViewClientDetailsPanel(parent);
        tabbedPane.addTab("View clients details", viewClientDetailsPanel);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
         
        add(tabbedPane);
         
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				viewClientDetailsPanel.refreshClientsDetails();
			}
		});
    }
}