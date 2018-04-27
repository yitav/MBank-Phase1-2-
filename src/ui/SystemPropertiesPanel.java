package ui;

import java.awt.event.*;
import java.beans.*;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.LoginManager;
import actionsIfc.AdminActionIfc;
import dtoIfc.PropertyDTOIfc;
import dtos.PropertyDTO;

public class SystemPropertiesPanel extends JPanel {

	// Table Example
	// setLayout(new GridLayout(3,1));
	//
	// //table panel
	//
	// //table
	// Object[] columns = {"","Property name", "Property Value"};
	// // Object[][] rows = getRows{{"1","name", "value"}};
	// JTable table = new JTable(getRows(), columns);
	// JScrollPane scrollPaneTable = new JScrollPane(table);
	//
	//
	//
	// add(new JLabel("System Properties", JLabel.CENTER));
	// add(scrollPaneTable);
	// add(new JLabel());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame parent;
	private JTextField propertyNameField = null;
	private JLabel valueLabel = new JLabel("", JLabel.CENTER);

	public SystemPropertiesPanel(JFrame parent) {
		this.parent = parent;

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(new JLabel("System Properties", JLabel.CENTER), gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.ipady = 200;
		JPanel container = getContainerPanel();
		add(container, gbc);
	}

	private JPanel getContainerPanel() {
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(5, 1));

		container.add(new JLabel());
		container.add(getPropertyPanel());
		container.add(valueLabel);
		container.add(getButtonsPanel());
		container.add(new JLabel());

		return container;
	}

	private Component getPropertyPanel() {
		JPanel propertyPanel = new JPanel();
		// JPanel namePanel = new JPanel();
		// JPanel valuePanel = new JPanel();

		propertyPanel.setLayout(new FlowLayout());
		propertyNameField = new JTextField(10); 

		propertyPanel.add(new JLabel("Property Name :  "));
		propertyPanel.add(propertyNameField);

		return propertyPanel;

	}

	private Component getButtonsPanel() {
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		JButton viewBtn = new JButton("View");
		JButton changeBtn = new JButton("Change");

		viewBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AdminActionIfc admin = LoginManager.getInstance().getAdmin();
				if (admin != null)
					try {
						PropertyDTOIfc property = admin
								.viewSystemProperty(propertyNameField.getText());
						
						if (property != null){
							String value = property.getProp_value();
							valueLabel.setText("value : " + value);
						}
						else
							JOptionPane.showMessageDialog(
									null,
									"Property name : "
											+ propertyNameField.getText()
											+ ", does not exist");
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
		});

		changeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AdminActionIfc admin = LoginManager.getInstance().getAdmin();
				if (admin != null)
					try {
						PropertyDTOIfc property = admin
								.viewSystemProperty(propertyNameField.getText());
						
						if (property == null) {
							JOptionPane.showMessageDialog(null, "Property name : "
									+ propertyNameField.getText()+ ", does not exist");
						}
						else {
							String value = property.getProp_value();
							new ChangePropertyValueDialog(parent, "change system property",
									propertyNameField.getText(), value);
						}
						
					} catch (RemoteException | NullPointerException e1) {
						setVisible(false);
					}
				else{
					JOptionPane.showMessageDialog(
							null,
							"Error in communication");
				}
			}
		});

		p.add(viewBtn);
		p.add(changeBtn);

		return p;
	}

	private Object[][] getRows() {

		return null;
	}

	private class ChangePropertyValueDialog extends JDialog 
											implements ActionListener , PropertyChangeListener {

		private JOptionPane optionPane;
		private String btnString = "OK";
		private String typedText = null;
		private JTextField valueTextField;
		private String propertyName;
		
		public ChangePropertyValueDialog(JFrame parent, String title,
				String propertyName, String value) {
			super(parent, title,true);
			this.propertyName = propertyName;
			//setLayout(new GridLayout(3, 1));
			//JLabel label = new JLabel(
			//		"please enter a new value for property : " + propertyName,
			//		JLabel.CENTER);
			
			//JButton okBtn = new JButton("OK");
			//JTextField 
			valueTextField = new JTextField(10);
			valueTextField.setText(value);
			
			
			String msgString = "please enter a new value for property : " + propertyName;
			Object[] array = {msgString, valueTextField};
			Object[] options = {btnString};
			
			//Create the JOptionPane.
	        optionPane = new JOptionPane(array,
	                JOptionPane.PLAIN_MESSAGE,
	                JOptionPane.YES_NO_OPTION,
	                null,
	                options,
	                options[0]);
	        
	      //Make this dialog display it.
	        setContentPane(optionPane);
	      //Handle window closing correctly.
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        addComponentListener(new ComponentAdapter() {
	            @Override
	            public void componentShown(ComponentEvent ce) {
	            	valueTextField.requestFocusInWindow();
	            }
	        });
	        
	      //Register an event handler that puts the text into the option pane.
	        valueTextField.addActionListener(this);

	        //Register an event handler that reacts to option pane state changes.
	        optionPane.addPropertyChangeListener(this);
	        pack();
	        
	        

//			okBtn.addActionListener(new ActionListener() {
//
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					AdminActionIfc admin = LoginManager.getInstance().getAdmin();
//					if (admin != null)
//						try {
//							PropertyDTOIfc property = new PropertyDTO(
//									propertyName, valueTextField.getText());
//							admin.updateSystemProperty(property,
//									property.getProp_key());
//						} catch (RemoteException | NullPointerException e1) {
//							JOptionPane
//									.showMessageDialog(null,
//											"Error while trying to change system property");
//						}
//					setVisible(false);
//				}
//			});

//			JPanel labelPanel = new JPanel();
//			labelPanel.add(label);
//
//			JPanel textPanel = new JPanel();
//			textPanel.add(valueTextField);
//			textPanel.setLayout(new FlowLayout());
//
//			JPanel okBtnPanel = new JPanel();
//			okBtnPanel.add(okBtn);
//			okBtnPanel.setLayout(new FlowLayout());
//
//			add(labelPanel);
//			add(textPanel);
//			add(okBtnPanel);

			//setSize(400, 200);
	        int centerX = (int)(parent.getLocation().getX()+parent.getSize().getWidth()/2) ;
	        int centerY = (int)(parent.getLocation().getY()+parent.getSize().getHeight()/2);
	        int x = (int)(centerX - this.getSize().getWidth()/2);
	        int y = (int)(centerY - this.getSize().getHeight()/2);
	        this.setLocation(x,y);
			setVisible(true);
	       // JOptionPane.showMessageDialog(this , );
			
		}

		@Override
	    public void actionPerformed(ActionEvent e) {
	        optionPane.setValue(btnString);
	    }
		
		@Override
	    public void propertyChange(PropertyChangeEvent e) {
	        String prop = e.getPropertyName();

	        if (isVisible()
	                && (e.getSource() == optionPane)
	                && (JOptionPane.VALUE_PROPERTY.equals(prop)
	                || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
	            Object value = optionPane.getValue();

	            if (value == JOptionPane.UNINITIALIZED_VALUE) {
	                //ignore reset
	                return;
	            }

	            //Reset the JOptionPane's value.
	            //If you don't do this, then if the user
	            //presses the same button next time, no
	            //property change event will be fired.
	            optionPane.setValue(
	                    JOptionPane.UNINITIALIZED_VALUE);

	            if (btnString.equals(value)) {
	                typedText = valueTextField.getText();

	                AdminActionIfc admin = LoginManager.getInstance().getAdmin();
					if (admin != null)
						try {
							PropertyDTOIfc property = new PropertyDTO();
									property.setProp_key(propertyName);
									property.setProp_value(valueTextField.getText()); 
							admin.updateSystemProperty(property,
									property.getProp_key());
						} catch (RemoteException | NullPointerException e1) {
							JOptionPane
									.showMessageDialog(null,
											"Error while trying to change system property");
						}
	                    
	                    dispose();
	                
	            } else { //user closed dialog or clicked cancel
	                JOptionPane.showMessageDialog(this, "It's OK.  "
	                        + "We won't force you to type ");
	                typedText = null;
	                dispose();
	            }
	        }
	    }
		
	}

}
