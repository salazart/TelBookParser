package com.salazart.gui.model;

import java.awt.Checkbox;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.salazart.folder.services.PropertyService;
import com.salazart.gui.services.ActionChangeCheckBox;

public class AdditionalPropertyPanel extends JPanel{
	private static final String FLAG_SHOW_TEL_BOOK = "flagShowTelBook";
	private static final String FLAG_INSERT_TEL_BOOK = "flagInsertTelBook";
	private static final String FLAG_FIND_PERSON = "findPerson";
	
	private Checkbox showTelBook;
    private Checkbox insertTelBook;
    private Checkbox findPerson;
	
    private boolean flagShowTelBook = Boolean.valueOf(
    		PropertyService.getValueProperties(FLAG_SHOW_TEL_BOOK));
	private boolean flagInsertTelBook = Boolean.valueOf(
			PropertyService.getValueProperties(FLAG_INSERT_TEL_BOOK));
	private boolean flagFindPerson = Boolean.valueOf(
			PropertyService.getValueProperties(FLAG_FIND_PERSON));
	
	public AdditionalPropertyPanel(){
		setLayout(new GridLayout(2,1));
		Border border = BorderFactory.createTitledBorder("Додаткові можливості:");
		setBorder(border);
		
		showTelBook = new Checkbox("Перегляд тел. книги");
		add(showTelBook);
		showTelBook.setState(flagShowTelBook);
		showTelBook.addItemListener(new ActionChangeCheckBox(FLAG_SHOW_TEL_BOOK));
		
		
		insertTelBook = new Checkbox("Занесення тел. книги до бази");
		add(insertTelBook);
		insertTelBook.setState(flagInsertTelBook);
		insertTelBook.addItemListener(new ActionChangeCheckBox(FLAG_INSERT_TEL_BOOK));
		
		findPerson = new Checkbox("Шукати особу в базі");
		add(findPerson);
		findPerson.setState(flagFindPerson);
		findPerson.addItemListener(new ActionChangeCheckBox(FLAG_FIND_PERSON));
	}

}
