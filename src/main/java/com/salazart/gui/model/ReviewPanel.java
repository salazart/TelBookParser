package com.salazart.gui.model;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.salazart.folder.services.PropertyService;

public class ReviewPanel extends JPanel{
	private final static String PROPERTIES_VALUE = "defaultFolder";
	private String pathFolder = PropertyService.getValueProperties(PROPERTIES_VALUE);
	private JTextField reviewText;
	
	private JButton reviewButton;
	
	public ReviewPanel() {
		setLayout(new BorderLayout(0, 0));
		
		reviewButton = new JButton("Обзор");
		reviewButton.addActionListener(new ActionReviewButton());
		add(reviewButton, BorderLayout.EAST);
		
		reviewText = new JTextField();
		add(reviewText, BorderLayout.CENTER);
		reviewText.setColumns(10);
		reviewText.setText(pathFolder);
		reviewText.setEditable(false);
	}
	
	public class ActionReviewButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			FolderChooser fc = new FolderChooser();
			String folder = fc.getSelectedDirectory();
			
			if(!folder.isEmpty()){
				reviewText.setText(folder);
				PropertyService.setValueProperties(PROPERTIES_VALUE, folder);
			}
		}
	}
	
	public String getFolderPath(){
		return reviewText.getText();
	}
}
