package com.salazart.count.services;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.salazart.TelBookParser;
import com.salazart.count.interfaces.CountPhone;

public class DocxFile extends TextProcess implements CountPhone{
	
	private List<String> phones = new ArrayList<String>();
	
	public List<String> getPhones(String pathName){
		try {
			FileInputStream file = new FileInputStream(new File(pathName));
			
	        OPCPackage opc = OPCPackage.open(file);
	        
	        XWPFDocument docx = new XWPFDocument(opc);
	        XWPFWordExtractor ex = new XWPFWordExtractor(docx);
	        String text = ex.getText();
	        
	        phones = this.findPhones(text);
	        findPhoneWithName(text);
	        
	        ex.close();
			file.close();
		} catch (Exception e) {
			TelBookParser.log4j.error(e.getMessage());
		}
		return phones;
	}
}
