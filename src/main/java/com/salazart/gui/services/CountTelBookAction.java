package com.salazart.gui.services;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.salazart.count.services.CountTelBookService;
import com.salazart.folder.services.PropertyService;

public class CountTelBookAction implements ActionListener{
	private final static String PROPERTIES_VALUE = "defaultFolder";
	
	public void actionPerformed(ActionEvent arg0) {
		String pathFolder = PropertyService.getValueProperties(PROPERTIES_VALUE);
		CountTelBookService countTelBookService = new CountTelBookService();
		countTelBookService.handleFolder(pathFolder);
	}

}
