package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import utils.LoginManager;

public class LoginFrame extends AbstractFrame {

	private static final int xPrefSize = 380;
	private static final int yPrefSize = 280;
	private static final int margin = yPrefSize/35;
	
	public LoginFrame() {
		super();
		setTitle("Login");
	}
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(xPrefSize , yPrefSize);
		
	}
	@Override
	  public Dimension getMaximumSize() {
		return new Dimension(xPrefSize , yPrefSize);
	}
	@Override
	public Dimension getMinimumSize() {
		return new Dimension(xPrefSize , yPrefSize);
	}
	@Override
	public void prepareGUI() {
		final Insets TEXT_INSETS = new Insets(0 , 0 , 0 , 0);
		final Insets TEXTFIELD_INSETS = new Insets(margin , 0 , margin , 0);
		
		setPreferredSize(new Dimension(xPrefSize, yPrefSize));
		//setLayout(new GridLayout(2, 1));
		
		//setLayout(new BorderLayout());
		// header
		//JLabel header = new JLabel("Administration Application", JLabel.CENTER);
		setLayout(new GridBagLayout());
		
		JLabel header = new JLabel("Administration Application");
		header.setHorizontalAlignment(JLabel.CENTER);
		//header.setVerticalAlignment(JLabel.BOTTOM);

		// administration panel
		JPanel administrationPanel = new JPanel();
		GridBagLayout administrationLayout = new GridBagLayout();
		administrationPanel.setLayout(administrationLayout);
		//administrationPanel.setBackground(Color.gray);

		
		// login panel
		//JPanel loginPanel = new JPanel();
		JPanel loginPanel = new JPanel(){
			 @Override
			  public Dimension getMaximumSize() {
				 return new Dimension((int)LoginFrame.this.getPreferredSize().getWidth() , 
							(int)(LoginFrame.this.getPreferredSize().height*0.2));
			  }
			 @Override
			  public Dimension getMinimumSize() {
				 return new Dimension((int)LoginFrame.this.getPreferredSize().getWidth() , 
							(int)(LoginFrame.this.getPreferredSize().height*0.2));
			  }
			 @Override
			 public Dimension getPreferredSize(){
					return new Dimension((int)LoginFrame.this.getPreferredSize().getWidth() , 
											(int)(LoginFrame.this.getPreferredSize().height*0.2));
					
			}	 
		};
		//loginPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		GridBagLayout loginLayout = new GridBagLayout();
		loginPanel.setLayout(loginLayout);
		loginPanel.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createEtchedBorder(),
	            BorderFactory.createEtchedBorder()));
	
		//bottom empty label
		JLabel bottomLabel = new JLabel(" ");
		//bottomLabel.setBackground(Color.magenta);
		//bottomLabel.setOpaque(true);
		GridBagConstraints gbc;
		
		//main grid 3X1
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0.6;
		//gbc.gridheight = 3;
		gbc.fill = GridBagConstraints.BOTH;
		add(administrationPanel , gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0.6;
		gbc.fill = GridBagConstraints.BOTH;
		add(loginPanel , gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 0.08;
		gbc.fill = GridBagConstraints.BOTH;
		add(bottomLabel , gbc);
		
		
		

		
		//top cell(index 0,0) in main grid - 1X3 grid
		
		
		//empty cell
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.3;
		gbc.weighty = 1.0;
		//gbc.gridwidth = 1;
		
		JLabel j00 = new JLabel(" ");
		//j00.setBackground(Color.blue);
		//j00.setOpaque(true);
		//j00.setLayout(new BorderLayout());
		//gbc.ipadx = 30;
		//gbc.ipady = 40;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		administrationPanel.add(j00 , gbc);
		
		//header
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.6;
		gbc.weighty = 1.0;
		//gbc.gridwidth = 2;
		//gbc.ipadx = 60;
		//gbc.ipady = 40;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		administrationPanel.add(header, gbc);
		
		//empty cell
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 0.3;
		gbc.weighty = 1.0;
		//gbc.gridwidth = 1;
		JLabel j03 = new JLabel(" ");
		//j03.setBackground(Color.black);
		//j03.setOpaque(true);
		//j03.setLayout(new BorderLayout());
		//gbc.ipadx = 30;
		//gbc.ipady = 40;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.BOTH;
		administrationPanel.add(j03 , gbc);
		
		
		
		// login components
		//center cell(index 1,0) in main grid - 4X4 grid
		
		//1st line
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0.05;
		gbc.gridwidth = 4;
		JLabel jlabel00 = new JLabel(" ");
		//jlabel00.setBackground(Color.magenta);
		//jlabel00.setOpaque(true);
		//gbc.anchor = GridBagConstraints.WEST;
		loginPanel.add(jlabel00, gbc);
		
		// 1st line
		//empty cell 
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		//gbc.gridwidth = 1;
		gbc.weightx = 0.27;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		JLabel jlabel10 = new JLabel(" ");
		//jlabel10.setBackground(Color.blue);
		//jlabel10.setOpaque(true);
		//gbc.anchor = GridBagConstraints.WEST;
		loginPanel.add(jlabel10, gbc);
		
		//user name
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0.15;
		gbc.weighty = 1;
		gbc.insets = TEXT_INSETS;
		loginPanel.add(new JLabel("user name:   "), gbc);
		
		//username textfield
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 2;
	    gbc.gridy = 1;
	    gbc.weightx = 0.25;
	    gbc.weighty = 1;
	    gbc.gridwidth = 1;
	    gbc.insets = TEXTFIELD_INSETS;
	    JTextField username = new JTextField(10);
	   
	    loginPanel.add(username,gbc);
	    
	    //empty cell 
	    gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.weightx = 0.27;
		gbc.gridwidth = 1;
		gbc.weighty = 1;
		JLabel jlabel03 = new JLabel(" ");
		//jlabel03.setBackground(Color.black);
		//jlabel03.setOpaque(true);
		
		loginPanel.add(jlabel03, gbc);
	    
		// 2nd line
		//empty cell 
		gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 2;
		//gbc.gridwidth = 1;
		gbc.weightx = 0.27;
		gbc.weighty = 1;
		JLabel j10 = new JLabel(" ");
		//j10.setBackground(Color.blue);
		//j10.setOpaque(true);
		//gbc.anchor = GridBagConstraints.WEST;
		loginPanel.add(j10, gbc);
		
	    //password
		gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 0.15;
		gbc.weighty = 1;
		gbc.insets = TEXT_INSETS;
		loginPanel.add(new JLabel("password:   "), gbc);
		
		//password field
		gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 2;
	    gbc.gridy = 2;
	    gbc.weightx = 0.25;
	    gbc.weighty = 1;
	    gbc.insets = TEXTFIELD_INSETS;
	    JPasswordField password = new JPasswordField(10);
	    loginPanel.add(password,gbc);
	    
	    //empty cell 
	    gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.weightx = 0.27;
		gbc.weighty = 1;
		JLabel j13 = new JLabel(" ");
		//j13.setBackground(Color.black);
		//j13.setOpaque(true);
		
		loginPanel.add(j13, gbc);
		
		// 3rd line
		//empty cell
		gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 3;
		//gbc.gridwidth = 1;
		gbc.weightx = 0.27;
		gbc.weighty = 1;
		JLabel j20 = new JLabel(" ");
		//j20.setBackground(Color.blue);
		//j20.setOpaque(true);
		//gbc.anchor = GridBagConstraints.WEST;
		loginPanel.add(j20, gbc);
		
	    //login button
	    JButton loginBtn = new JButton("Login");
	    loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(LoginManager.getInstance().tryLoginAsAdmin(username.getText(), password.getText())) {
						setVisible(false);
						new ActivitiesFrame();
				}
				else JOptionPane.showMessageDialog(null, "User name or password are wrong");

				 
			}
		});
	    
	    gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 1;
	    gbc.gridy = 3;
	    gbc.weightx = 0.15;
	    gbc.weighty = 1;
	    gbc.insets = TEXT_INSETS;
	    loginPanel.add(loginBtn, gbc);
	    
	    //empty cell 
	    gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.weighty = 1;
		//gbc.weightx = 0.25;
		gbc.gridwidth = 2;
		JLabel j22 = new JLabel(" ");
		//j22.setBackground(Color.yellow);
		//j22.setOpaque(true);
		loginPanel.add(j22, gbc);
		
		
		
		
		
		
	    //user name
//  		gbc.fill = GridBagConstraints.HORIZONTAL;
//  		gbc.gridx = 0;
//  		gbc.gridy = 0;
//  		loginPanel.add(new JLabel("user name:   "), gbc);
//
//  		gbc.gridx = 1;
//  	    gbc.gridy = 0;
//  	    JTextField username = new JTextField(10);
//  	    loginPanel.add(username,gbc);
//  	    
//  	    //password
//  	    gbc.fill = GridBagConstraints.HORIZONTAL;
//  		gbc.gridx = 0;
//  		gbc.gridy = 1;
//  		loginPanel.add(new JLabel("password:   "), gbc);
//
//  		gbc.gridx = 1;
//  	    gbc.gridy = 1;
//  	    JPasswordField password = new JPasswordField(10);
//  	    loginPanel.add(password,gbc);
//	    
//	    
//	    gbc.gridx = 0;
//	    gbc.gridy = 2;
//	    loginPanel.add(loginBtn, gbc);
		
		
		//botoom cell in main grid - empty label
		
		//add(header);
		//add(loginPanel);
	    
		
	}
	

}
