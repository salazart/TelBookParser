package com.salazart.count.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.salazart.TelBookParser;
import com.salazart.count.interfaces.CountPhone;
import com.salazart.folder.services.SheetService;

/**
 * Reading xlsx document and getting all phones from file
 */
public class XLSXFile implements CountPhone{
	private SheetService sheetService = new SheetService();
	
	public List<String> getPhones(String pathName){
		List<String> phones = new ArrayList<String>();
		try {
			FileInputStream file = new FileInputStream(new File(pathName));
			
			XSSFWorkbook xlsxWorkbook = new XSSFWorkbook(file);
			int indexSheet = xlsxWorkbook.getActiveSheetIndex();
			XSSFSheet xssfSheet = xlsxWorkbook.getSheetAt(indexSheet);
			
			phones = sheetService.getPhones(xssfSheet);
			
			file.close();
		} catch (FileNotFoundException e) {
			TelBookParser.log4j.error(e.getMessage());
		} catch (IOException e) {
			TelBookParser.log4j.error(e.getMessage());
		}
		return phones;
	}
}
