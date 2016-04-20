package com.salazart.gui.model;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MainPanel extends JPanel{
	private ReviewPanel reviewPanel = new ReviewPanel();
	private LogPanel logScrollPane = new LogPanel();
	private PropertyPanel propertyPanel = new PropertyPanel();
	
	public MainPanel() {
		setLayout(new BorderLayout(0, 0));
		add(reviewPanel, BorderLayout.NORTH);
		add(logScrollPane, BorderLayout.CENTER);
		add(propertyPanel, BorderLayout.SOUTH);
		
		Border border = BorderFactory.createTitledBorder("Лог подій:");
		logScrollPane.setBorder(border);
	}
	
	public void addLog(String logText) {
		logScrollPane.addLog(logText);
	}
	
	public void clearLog(){
		logScrollPane.clearLog();
	}

}
