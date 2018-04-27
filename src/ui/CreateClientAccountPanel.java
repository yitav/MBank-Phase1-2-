package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.LoginManager;
import actionsIfc.AdminActionIfc;
import dtoIfc.AccountDTOIfc;
import dtoIfc.ClientDTOIfc;
import dtos.AccountDTO;
import dtos.ClientDTO;

public class CreateClientAccountPanel extends JPanel{

	
	
	private FormPanel clientPanel;	//needs to be at class level 
	private FormPanel accountPanel;	//because we need the data from user 
	 								//to be handled in the event handler
	
	private JPanel containerPanel;	//uses card layout
	private JPanel btnsPanel;		//uses card layout
	
	private ClientDTOIfc client; 	 
	
	private static final String clientStr = "client"; 
	private static final String accountStr = "account";
	
	public CreateClientAccountPanel() {
		super();
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		//JPanel 
		JPanel container = getContainerPanel();
		//JPanel 
		btnsPanel = getBtnsPanel();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipady = 50;
		gbc.ipadx = 300;
		JLabel createLabel = new JLabel("Create new client and account" );
		createLabel.setHorizontalAlignment(JLabel.CENTER);
		createLabel.setVerticalAlignment(JLabel.BOTTOM);
		//createLabel.setBackground(Color.blue);
		//createLabel.setOpaque(true);
		add(createLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.ipadx = 300;
		add(container, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.ipady = 70;
		add(btnsPanel, gbc);
	}

	private JPanel getBtnsPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new CardLayout());
		
		JButton addClientBtn = new JButton("Add new Client");
		JButton addAccountBtn = new JButton("Add new account");
		JButton accountBackToClientBtn = new JButton("Back");
		accountBackToClientBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout clContainer = (CardLayout)containerPanel.getLayout();
				CardLayout clBtn = (CardLayout)btnsPanel.getLayout();
				clContainer.show(containerPanel, clientStr);
				clBtn.show(btnsPanel, clientStr);
				
			}
		});
		
		addClientBtn.addActionListener(new AddClientBtnHandler());
		addAccountBtn.addActionListener(new AddAccountBtnHandler());
		
		JPanel addClientBtnPanel = new JPanel();
		JPanel addAccountBtnPanel = new JPanel();
		addClientBtnPanel.setLayout(new FlowLayout());
		addAccountBtnPanel.setLayout(new FlowLayout());
		addClientBtnPanel.add(addClientBtn);
		addAccountBtnPanel.add(addAccountBtn);
		addAccountBtnPanel.add(accountBackToClientBtn);
		
		
		panel.add(addClientBtnPanel , clientStr);
		panel.add(addAccountBtnPanel , accountStr);
		
		return panel;
	}

	private JPanel getContainerPanel() {
		// TODO Auto-generated method stub
		containerPanel = new JPanel();
		CardLayout layout = new CardLayout();
		containerPanel.setLayout(layout);
		
		// client panel
		clientPanel = new FormPanel("client_name","password", "address", "email", "phone", "comment");
		clientPanel.centerPanel.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createTitledBorder("Client Details"),
	            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		//type field is not needed in BL layer and if it needs to be disabled it can be done 
//		clientPanel.getTextFields()[2].setEditable(false);
		
//		clientPanel.getTextFields()[0].setInputVerifier(new MyTextVerifier());
//		clientPanel.getTextFields()[1].setInputVerifier(new MyTextVerifier());
//		clientPanel.getTextFields()[3].setInputVerifier(new MyTextVerifier());
//		clientPanel.getTextFields()[4].setInputVerifier(new InputVerifier() {
//			
//			@Override
//			public boolean verify(JComponent input) {
//				String text = ((JTextField) input).getText();
//				return text.matches("[a-zA-Z0-9]+@[a-zA-Z][.[a-zA-Z]+]+");
//			}
//		});
//		clientPanel.getTextFields()[5].setInputVerifier(new InputVerifier() {
//			
//			@Override
//			public boolean verify(JComponent input) {
//				String text = ((JTextField) input).getText();
//				
//				return text.matches("[0-9]+");
//			}
//		});
//		clientPanel.getTextFields()[6].setInputVerifier(new MyTextVerifier());
		
		// account panel
		accountPanel = new FormPanel("first deposit" , "comment");
		accountPanel.centerPanel.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createTitledBorder("Account Details"),
	            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		//accountPanel.getTextFields()[1].setEditable(false);
		//accountPanel.getTextFields()[2].setEditable(false);
		
		containerPanel.add(clientPanel, clientStr);
		containerPanel.add(accountPanel, accountStr);
		layout.show(containerPanel, "client");
		return containerPanel;
	}
	
	private class AddClientBtnHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField[] clientDetails = clientPanel.getTextFields();
			
				//validate fields except type
				//type does not need validation because it is calculated in the BL
				
			if(clientDetails[0].getText() == null || clientDetails[0].getText() =="" ||
					!clientDetails[0].getText().matches("[a-zA-Z0-9 ]+")){
				JOptionPane.showMessageDialog(
						null,
						"'"+clientDetails[0].getText()+"' - is invalid client name");
				return;
			}
			if(clientDetails[1].getText() == null || clientDetails[1].getText() =="" || 
					!clientDetails[1].getText().matches("[a-zA-Z0-9]+")){
				JOptionPane.showMessageDialog(
						null,
						"'"+clientDetails[1].getText()+"' - is invalid password");
				return;
			}
			if(clientDetails[2].getText() == null || clientDetails[2].getText() =="" || 
					!clientDetails[2].getText().matches("[a-zA-Z0-9 ]+")){
				
				JOptionPane.showMessageDialog(
						null,
						"'"+clientDetails[2].getText()+"' - is invalid address");
				return;
			}
			//validate email separately because it has different string structure
			if(clientDetails[3].getText() == null || clientDetails[3].getText() =="" || 
					!(clientDetails[3].getText().matches("[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]+") || 
							clientDetails[3].getText().matches("[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]+\\.[a-zA-Z]+"))
			){
					
					JOptionPane.showMessageDialog(
						null,
						"'"+clientDetails[3].getText()+"' - is invalid email");
					return;
			}
			if(clientDetails[4].getText() == null || clientDetails[4].getText() =="" || 
					!clientDetails[4].getText().matches("[0-9]+")){
				
					JOptionPane.showMessageDialog(
						null,
						"'"+clientDetails[4].getText()+"' - is invalid phone number");
					return;
			}
			if(clientDetails[5].getText() == null || clientDetails[5].getText() =="" || 
					!clientDetails[5].getText().matches("[a-zA-Z0-9 ]*")){
				
					JOptionPane.showMessageDialog(
							null,
							"'"+clientDetails[5].getText()+"' - is invalid comment");
					return;
			}
			
			//ClientDTOIfc
			client = new ClientDTO();
			client.setClient_name(clientDetails[0].getText());
			client.setPassword(clientDetails[1].getText());
			//client.setType(clientDetails[2].getText());
			client.setAddress(clientDetails[2].getText());
			client.setEmail(clientDetails[3].getText());
			client.setPhone(clientDetails[4].getText());
			client.setComment(clientDetails[5].getText());
			
			CardLayout clContainer = (CardLayout)containerPanel.getLayout();
			CardLayout clBtn = (CardLayout)btnsPanel.getLayout();
			clContainer.show(containerPanel, accountStr);
			clBtn.show(btnsPanel, accountStr);
			
			
		}
		
	}
	private class AddAccountBtnHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JTextField[] accountDetails = accountPanel.getTextFields();
			double firstDeposit;
			//validate first Deposit field
			try{
				firstDeposit = Double.parseDouble(accountDetails[0].getText());
			}catch(Exception ex){
				JOptionPane.showMessageDialog(
						null,
						"'"+accountDetails[0].getText()+"' - is invalid first Deposit");
				return;
			}
			//validate comment
			if(accountDetails[1].getText() == null || accountDetails[1].getText() =="" ||
					!accountDetails[1].getText().matches("[a-zA-Z0-9 ]*")){
				JOptionPane.showMessageDialog(
						null,
						"'"+accountDetails[1].getText()+"' - is invalid comment");
				return;
			}
			
			AccountDTOIfc accountdto = new AccountDTO();
			accountdto.setComment(accountDetails[1].getText());
			AdminActionIfc admin = LoginManager.getInstance().getAdmin();
			if (admin != null)
				try {
					accountdto =  admin.addNewClient(client, accountdto, firstDeposit);
					if (accountdto != null){
							
							if( !admin.createNewAccount(client, accountdto) ){
									JOptionPane.showMessageDialog(
											null,
											"Add Account failed");
								admin.removeClient(client);
							}
							else{
								JOptionPane.showMessageDialog(
										null,
										"Add Client And Account Succeeded");
								for(int i = 0 ; i < clientPanel.getTextFields().length; i++){
										clientPanel.getTextFields()[i].setText("");
								}
								for(int i = 0; i < accountPanel.getTextFields().length; i++){
										accountPanel.getTextFields()[i].setText("");
								}
								CardLayout clContainer = (CardLayout)containerPanel.getLayout();
								CardLayout clBtn = (CardLayout)btnsPanel.getLayout();
								clContainer.show(containerPanel, clientStr);
								clBtn.show(btnsPanel, clientStr);
							}
							
					}else
						JOptionPane.showMessageDialog(
								null,
								"Add client failed");
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			else{
				JOptionPane.showMessageDialog(
						null,
						"Error in communication");
			}
			
			
		}
		
	}
	private class MyTextVerifier extends InputVerifier{

		@Override
		public boolean verify(JComponent arg0) {
			String text = ((JTextField) arg0).getText();
			
			return text.matches("[a-zA-Z0-9]+");
		}
		
	}
}
