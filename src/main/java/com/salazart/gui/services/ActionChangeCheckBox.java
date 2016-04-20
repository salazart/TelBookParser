package com.salazart.gui.services;

import java.awt.Checkbox;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.salazart.folder.services.PropertyService;


public class ActionChangeCheckBox implements ItemListener{
	private String valueProperty;
	
	public ActionChangeCheckBox(String valueProperty) {
		this.valueProperty = valueProperty;
	}
	
	public void itemStateChanged(ItemEvent arg0) {
		if(arg0.getItemSelectable() instanceof Checkbox){
			Object source = (Checkbox) arg0.getItemSelectable();
			
			PropertyService.setValueProperties(valueProperty, String.valueOf(((Checkbox) source).getState()));		
		}
	}
}
