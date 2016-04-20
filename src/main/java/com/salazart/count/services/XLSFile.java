package com.salazart.count.services;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.salazart.TelBookParser;
import com.salazart.count.interfaces.CountPhone;
import com.salazart.folder.services.SheetService;

public class XLSFile implements CountPhone{
private SheetService sheetService = new SheetService();
	
	public List<String> getPhones(String pathName){
		List<String> phones = new ArrayList<String>();
		try {
			FileInputStream file = new FileInputStream(new File(pathName));
			
			HSSFWorkbook xlsWorkbook = new HSSFWorkbook(file);
			int indexSheet = xlsWorkbook.getActiveSheetIndex();
			HSSFSheet hssfSheet = xlsWorkbook.getSheetAt(indexSheet);
			
			phones = sheetService.getPhones(hssfSheet);
			
			file.close();
		} catch (Exception e) {
			TelBookParser.log4j.error(e.getMessage());
		}
		return phones;
	}
}
