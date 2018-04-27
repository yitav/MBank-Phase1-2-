package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormPanel extends JPanel {
	
	private String[] fields;
	private JTextField[] textFields;
	
	private final Insets WEST_INSETS = new Insets(5, 0, 5, 5);
	private final Insets EAST_INSETS = new Insets(5, 5, 5, 0);
	
	JLabel westLabel; 
	JLabel eastLabel; 
	JPanel centerPanel;
	
	public FormPanel(String... fields) {
		super();
		this.fields = fields;

		
		this.setLayout(new GridBagLayout() );
		
		westLabel = new JLabel(" ");
		eastLabel = new JLabel(" ");
		centerPanel = new JPanel();
		
		centerPanel.setLayout(new GridBagLayout());
		
		
		textFields = new JTextField[fields.length];
		GridBagConstraints gbc;
		for(int i=0; i<fields.length; i++) {
				

			gbc = createGbc(0 , i);
			centerPanel.add(new JLabel(fields[i] + ":   "), gbc);
			
			gbc = createGbc(1 , i);
			textFields[i] = new JTextField(10);
			centerPanel.add(textFields[i], gbc);
			
		}
		
		GridBagConstraints gbcFormPanel = new GridBagConstraints();
		gbcFormPanel.gridx = 0;
		gbcFormPanel.gridy = 0;
		gbcFormPanel.weightx = 0.4;
		gbcFormPanel.anchor = GridBagConstraints.WEST;
		gbcFormPanel.fill = GridBagConstraints.BOTH;
		this.add(westLabel, gbcFormPanel);
		
		gbcFormPanel.gridx = 1;
		gbcFormPanel.gridy = 0;
		gbcFormPanel.weightx = 0.45;
		gbcFormPanel.anchor = GridBagConstraints.CENTER;
		gbcFormPanel.fill = GridBagConstraints.BOTH;
		this.add(centerPanel, gbcFormPanel);
		
		gbcFormPanel.gridx = 2;
		gbcFormPanel.gridy = 0;
		gbcFormPanel.weightx = 0.4;
		gbcFormPanel.anchor = GridBagConstraints.EAST;
		gbcFormPanel.fill = GridBagConstraints.BOTH;
		this.add(eastLabel, gbcFormPanel);
		
	}

	private GridBagConstraints createGbc(int x, int y) {
	      GridBagConstraints gbc = new GridBagConstraints();
	      gbc.gridx = x;
	      gbc.gridy = y;
	      gbc.gridwidth = 1;
	      gbc.gridheight = 1;

	      gbc.anchor = (x == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
	      if(x == 0){
	    	  gbc.anchor = GridBagConstraints.WEST;
	      }
	      gbc.fill = (x == 0) ? GridBagConstraints.BOTH
	            : GridBagConstraints.HORIZONTAL;
	     
	      gbc.insets = (x == 0) ? WEST_INSETS : EAST_INSETS;
	     
	      if(x == 0){
	    	  gbc.weightx = 0.1;
	      }else{
	    	  gbc.weightx = 1.0;
	      }
	      gbc.weighty = 1.0;
	      return gbc;
	   }
	public String[] getFields() {
		return fields;
	}

	
	public JTextField[] getTextFields() {
		return textFields;
	}

	
}