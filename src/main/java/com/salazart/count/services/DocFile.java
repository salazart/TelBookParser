package com.salazart.count.services;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import com.salazart.TelBookParser;
import com.salazart.count.interfaces.CountPhone;

public class DocFile extends TextProcess implements CountPhone{
	private List<String> phones = new ArrayList<String>();
	public List<String> getPhones(String pathName) {
		try {
			FileInputStream file = new FileInputStream(new File(pathName));
			
	        HWPFDocument doc = new HWPFDocument(file);
	        WordExtractor extractor = new WordExtractor(doc);
	        
	        phones = this.findPhones(extractor.getText());
	        findPhoneWithName(extractor.getText());
	        
			file.close();
		} catch (Exception e) {
			TelBookParser.log4j.error(e.getMessage());
		}
		return phones;
	}
}
