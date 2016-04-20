package com.salazart.gui.model;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainMenu extends JMenuBar{
		private JMenuItem workItem;
		private JMenuItem propertyItem;
		private JMenuItem clientItem;
				
	public MainMenu(){
		
		JMenu mnNewMenu = new JMenu("Головне");
		add(mnNewMenu);
		
		workItem = new JMenuItem("Телефонні книжки");
		mnNewMenu.add(workItem);
		
		propertyItem = new JMenuItem("Налаштування");
		mnNewMenu.add(propertyItem);
		
		clientItem = new JMenuItem("Створити тел. книгу");
		mnNewMenu.add(clientItem);
		
		JMenuItem menuItem = new JMenuItem("Вихід");
		mnNewMenu.add(menuItem);
	}
	
	public void addActionListenerWork(ActionListener actionListener){
		workItem.addActionListener(actionListener);
	}
	
	public void addActionListenerProperty(ActionListener actionListener){
		propertyItem.addActionListener(actionListener);
	}

	public void addActionListenerClient(ActionListener actionListener){
		clientItem.addActionListener(actionListener);
	}
}
