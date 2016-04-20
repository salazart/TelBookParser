package com.salazart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.salazart.folder.services.ScanFolderService;
import com.salazart.gui.model.TelBookParserUI;

public class TelBookParser {
	public static final Logger log4j = LogManager.getRootLogger();
	public static TelBookParserUI trafficDroverUI = TelBookParserUI.getInstance();
	
	public static void main(String[] args) {
		
		ScanFolderService scanFolderService = new ScanFolderService();
		scanFolderService.runScanFolder();
		
	}
}
