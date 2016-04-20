package com.salazart.gui.services;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import com.salazart.TelBookParser;
import com.salazart.db.services.DataBaseService;
import com.salazart.folder.models.TelBook;
import com.salazart.folder.services.FileService;
import com.salazart.folder.services.PropertyService;
import com.salazart.folder.services.TelBookService;
import com.salazart.folder.services.ZipService;

public class RunTelBookAction implements ActionListener{
	private final static String FOLDER_PROPERTIES = "defaultFolder";
	private final static String INSERT_DB_PROPERTIES = "flagInsertTelBook";
	
	private String folderPath = PropertyService.getValueProperties(FOLDER_PROPERTIES);
	
	private boolean flagInsertTelBook = Boolean.valueOf(PropertyService.getValueProperties(INSERT_DB_PROPERTIES));
	
	public void actionPerformed(ActionEvent e) {
		TelBookParser.trafficDroverUI.clearLog();
		Thread t1 = new Thread(new Runnable() {
		    public void run()
		    {
		    	FileService fileService = new FileService();
				List<File> files = fileService.getFilesFromFolder(folderPath);
				
				DataBaseService dbService = new DataBaseService();
				for (File file : files) {
					ZipService zipService = new ZipService();
					file = zipService.extractZipFile(file);
					
					TelBookService xmlTelBook = new TelBookService();
					TelBook telBook = xmlTelBook.readTelBook(file);
					
					if(!telBook.isEmpty() && flagInsertTelBook){
						dbService.insertTelBook(telBook);
					}
				}
		    }});  
		    t1.start();
	}
}
