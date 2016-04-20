package com.salazart.folder.services;

import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.salazart.TelBookParser;
import com.salazart.db.services.DataBaseService;
import com.salazart.folder.models.TelBook;

public class ScanFolderService {
	private final static int SLEEP_TIMER = 10000;
	private final static String INSERT_DB_PROPERTIES = "flagInsertTelBook";
	private final static String FOLDER_PROPERTIES = "defaultFolder";
	
	private boolean flagInsertTelBook = Boolean.valueOf(PropertyService.getValueProperties(INSERT_DB_PROPERTIES));
	private String folderPath = PropertyService.getValueProperties(FOLDER_PROPERTIES);
	
	private FileService fileService = new FileService();
	private ZipService zipService = new ZipService();
	private DataBaseService dbService = new DataBaseService();
	private TelBookService xmlTelBook = new TelBookService();
	
	private Logger log = LogManager.getRootLogger();
	
	public void runScanFolder(){
		Thread t1 = new Thread(new Runnable() {
		    public void run()
		    {
		    	while(true){
					List<File> files = fileService.getFilesFromFolder(folderPath);

					for (int i = 0; i < files.size(); i++) {
						File file = files.get(i);
						log.debug("Handle file: " + file.getAbsolutePath());
						TelBookParser.trafficDroverUI.clearLog();
						file = zipService.extractZipFile(file);
						
						TelBook telBook = xmlTelBook.readTelBook(file);
						
						if(!telBook.isEmpty() && flagInsertTelBook){
							dbService.insertTelBook(telBook);
						}
						file = fileService.moveFile(file);
					}
					
					try {
						Thread.sleep(SLEEP_TIMER);
					} catch (InterruptedException e) {
						TelBookParser.log4j.error(e.getMessage());
						JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
					}
		    	}

		    }
		});  
		t1.start();
	}
}
