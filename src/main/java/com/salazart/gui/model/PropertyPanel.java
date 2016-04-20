package com.salazart.gui.model;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.salazart.gui.services.CountTelBookAction;
import com.salazart.gui.services.RunTelBookAction;

public class PropertyPanel extends JPanel{

    private JButton runButton;
    
    private JButton countTel;

    public PropertyPanel() {
		setLayout(new BorderLayout(0, 0));
		
		ResourceBSPanel checkPanel = new ResourceBSPanel();
		add(checkPanel, BorderLayout.WEST);
		
		AdditionalPropertyPanel additionalPanel = new AdditionalPropertyPanel();
		add(additionalPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.EAST);
		
		runButton = new JButton("Старт");
		buttonPanel.add(runButton);
		runButton.addActionListener(new RunTelBookAction());
		
		countTel = new JButton("CountTel");
		buttonPanel.add(countTel);
		countTel.addActionListener(new CountTelBookAction());
		
		JPanel statusPanel = new JPanel();
		add(statusPanel, BorderLayout.NORTH);
		statusPanel.setLayout(new BorderLayout(0, 0));
	}
}
