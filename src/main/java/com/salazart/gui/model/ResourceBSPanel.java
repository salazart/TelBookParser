package com.salazart.gui.model;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

public class ResourceBSPanel extends JPanel{
	private static final String FLAG_USE_LOCAL_BASE = "useLocalBase";
	
    private JRadioButton localCheckBox;
    private JRadioButton fileCheckBox;
    
    //private boolean flagLocalBase = Boolean.valueOf(PropertyService.getInstance().getValueProperties(FLAG_USE_LOCAL_BASE));
    
	public ResourceBSPanel(){
		setLayout(new GridLayout(2,1));
		Border border = BorderFactory.createTitledBorder("Ресурс адрес базових станції");
		setBorder(border);
		
		localCheckBox = new JRadioButton("З локальної бази");
		//add(localCheckBox);
		//localCheckBox.addActionListener(new ActionChangeRadioButton(true));
		
		fileCheckBox = new JRadioButton("Із файла");
		//add(fileCheckBox);
		//fileCheckBox.addActionListener(new ActionChangeRadioButton(false));
		
		ButtonGroup group = new ButtonGroup();
		group.add(localCheckBox);
		group.add(fileCheckBox);
		
		//if(flagLocalBase == true){
		//	localCheckBox.setSelected(true);
		//} else {
		//	fileCheckBox.setSelected(true);
		//}
	}

}
