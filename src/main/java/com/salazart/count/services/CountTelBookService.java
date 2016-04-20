package com.salazart.count.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.salazart.TelBookParser;
import com.salazart.folder.models.TelBook;

public class CountTelBookService {
	private final String XLSX = "xlsx";
	private final String XLS = "xls";
	private final String DOC = "doc";
	private final String DOCX = "docx";
	private int count;
	
	private Logger log = LogManager.getRootLogger();
	
	public List<TelBook> handleFolder(String pathFolder) {

		List<String> files = getFilesFromFolder(pathFolder);

		List<TelBook> listTelBook = new ArrayList<TelBook>();
		
		for (int i = 0; i < files.size(); i++) {
			log.debug("Handle file : " + files.get(i));
			TelBookParser.trafficDroverUI.addLog("Handle file : " + files.get(i));
			processFile(files.get(i));
		}
		TelBookParser.trafficDroverUI.addLog("Всього: " + count);
		return listTelBook;
	}
	
	public void processFile(String fileName){
		
		String fileType = StringUtils.substringAfterLast(fileName, ".");
		
		switch(fileType){
		case XLSX:
			XLSXFile xlsxFile = new XLSXFile();
			int countXlsx = xlsxFile.getPhones(fileName).size();
			TelBookParser.trafficDroverUI.addLog(String.valueOf(countXlsx));
			count += countXlsx;
		break;
		case XLS:
			XLSFile xlsFile = new XLSFile();
			int countXls = xlsFile.getPhones(fileName).size();
			TelBookParser.trafficDroverUI.addLog(String.valueOf(countXls));
			count += countXls;
			break;
		case DOCX:
			DocxFile docxFile = new DocxFile();
			int countDocx = docxFile.getPhones(fileName).size();
			TelBookParser.trafficDroverUI.addLog(String.valueOf(countDocx));
			count += countDocx;
			break;
		case DOC:
			DocFile docFile = new DocFile();
			int countDoc = docFile.getPhones(fileName).size();
			TelBookParser.trafficDroverUI.addLog(String.valueOf(countDoc));
			count += countDoc;
			break;
		}
	}
	
	private List<String> getFilesFromFolder(String folder){
		File fFolder = new File(folder);
		if(fFolder != null && fFolder.isDirectory()){
			List<String> filesFromFolder = Arrays.asList(fFolder.list());
			for (int i = 0; i < filesFromFolder.size(); i++) {
				filesFromFolder.set(i, folder + File.separator + filesFromFolder.get(i));
			}
			return filesFromFolder;
		}
		return new ArrayList<String>();
	}
}
