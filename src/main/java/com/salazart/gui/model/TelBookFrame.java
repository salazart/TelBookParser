package com.salazart.gui.model;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TelBookFrame extends JFrame{
	
	private String MAIN_PANEL = "MAIN_PANEL";
	private String PROPERTY_PANEL = "PROPERTY_PANEL";
	private String CLIENT_PANEL = "CLIENT_PANEL";
	
	private JPanel contentPane;
	
	private MainPanel mainPanel;
	private JPanel propertyPanel;

	public TelBookFrame() {
		setTitle("Парсер телефонних книг");
		setBounds(100, 100, 800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MainMenu menuBar = new MainMenu();
		setJMenuBar(menuBar);
		
		menuBar.addActionListenerWork(new ActionListenerWork());
		menuBar.addActionListenerProperty(new ActionListenerProperty());
		menuBar.addActionListenerClient(new ActionListenerClient());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		mainPanel = new MainPanel();
		contentPane.add(mainPanel, MAIN_PANEL);
		
		propertyPanel = new PreferencesPanel();
		contentPane.add(propertyPanel, PROPERTY_PANEL);
	}
	
	public void addLog(String logText) {
		mainPanel.addLog(logText);
	}
	
	public void clearLog(){
		mainPanel.clearLog();
	}
	
	private void setPanel(String namePanel){
		CardLayout cl = (CardLayout)(contentPane.getLayout());
        cl.show(contentPane, namePanel);
	}
	
	private class ActionListenerWork implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			setPanel(MAIN_PANEL);
		}
	}
	
	private class ActionListenerProperty implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			setPanel(PROPERTY_PANEL);
		}
	}
	
	private class ActionListenerClient implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			setPanel(CLIENT_PANEL);
		}
	}
}
