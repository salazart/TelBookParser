package com.salazart.gui.model;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LogPanel extends JScrollPane{
	
	private JTextArea textArea;
	
	public LogPanel() {
		
		textArea = new JTextArea();

		textArea.setEditable(false);
		setViewportView(textArea);
	}
	
	public void addLog(String text){
		textArea.append(text + "\r\n");
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	
	public void clearLog(){
		textArea.setText("");
	}
}

