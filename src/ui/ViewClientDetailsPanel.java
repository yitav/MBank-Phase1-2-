package ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dtoIfc.ClientDTOIfc;
import utils.LoginManager;
import actionsIfc.AdminActionIfc;

public class ViewClientDetailsPanel extends JPanel {

	JFrame frame;
	Object[][] clientsDetails = null;
	JTable table;
	Object[] columns = { "name", "type", "address", "email", "phone", "comment" };
	JButton showAllClientsBtn;


	public ViewClientDetailsPanel(JFrame parent) {
		super();	
		this.frame = parent;
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		// JPanel
		JPanel container = getTablePanel();

		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		JLabel createLabel = new JLabel("View client details");
		createLabel.setHorizontalAlignment(JLabel.CENTER);
		add(createLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 0.1;
		add(getSelectClientPanel(), gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weighty = 1;
		add(container, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weighty = 0.3;
		
		add(getshowAllClientsBtnPanel(), gbc);
	}

	private Component getshowAllClientsBtnPanel() {
		JPanel showAllClientsBtnPanel = new JPanel();
		showAllClientsBtnPanel.setLayout(new FlowLayout());
		showAllClientsBtn = new JButton("Display all clients details");
		showAllClientsBtn.setVisible(false);
		showAllClientsBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshClientsDetails();
				showAllClientsBtn.setVisible(false);
			}
		});
		
		showAllClientsBtnPanel.add(showAllClientsBtn);
		return showAllClientsBtnPanel;
	}

	private Component getSelectClientPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));

		FormPanel formPanel = new FormPanel(new String[] { "name", "password" });
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());
		JButton searchBtn = new JButton("search client");
		btnPanel.add(searchBtn);

		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = formPanel.getTextFields()[0].getText();
				String password = formPanel.getTextFields()[1].getText();

				AdminActionIfc admin = LoginManager.getInstance().getAdmin();
				ClientDTOIfc client;
				try {
					client = admin.viewClientDetails(name, password);
					Object[][] clientRows = new Object[][] { getClientDetails(client) };
					table.setModel(new DefaultTableModel(clientRows, columns));
					showAllClientsBtn.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Connection refused");
				}

			}
		});

		p.add(formPanel);
		p.add(btnPanel);

		return p;
	}

	private static Object[] getClientDetails(ClientDTOIfc client) {
		Object[] clientDetails = new Object[6];
		clientDetails[0] = client.getClient_name();
		clientDetails[1] = client.getType();
		clientDetails[2] = client.getAddress();
		clientDetails[3] = client.getEmail();
		clientDetails[4] = client.getPhone();
		clientDetails[5] = client.getComment();

		return clientDetails;
	}

	private JPanel getTablePanel() {
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new FlowLayout());
		// table
		if(LoginManager.getInstance().getAdmin() != null){
			table = new JTable(getAllClientsDetails(), columns);
		}else{//for developing and testing purposes
			table = new JTable();
		}
		
		
		JScrollPane scrollPaneTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		Dimension frameDim = frame.getSize();
		
		int width = (int) (frameDim.width*0.9);
		int height = (int) (frameDim.width*0.3);
		table.setPreferredScrollableViewportSize(new Dimension(width, height));
		
		tablePanel.add(scrollPaneTable);
		return tablePanel;
	}	

	private Object[][] getAllClientsDetails() {
		updateClientDetails();
		return clientsDetails;
	}

	public void refreshClientsDetails() {
		table.setModel(new DefaultTableModel(getAllClientsDetails(), columns));
	}

	public void updateClientDetails() {
		AdminActionIfc admin = LoginManager.getInstance().getAdmin();
		try {
			ClientDTOIfc[] clients = admin.viewAllClientsDetails();
			clientsDetails = new Object[clients.length][6];
			for (int i = 0; i < clients.length; i++)
				clientsDetails[i] = getClientDetails(clients[i]);
			
		} catch (RemoteException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Connection refused");
		}
	}


}
