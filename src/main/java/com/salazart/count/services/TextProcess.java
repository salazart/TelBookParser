package com.salazart.count.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.salazart.folder.services.PhoneService;

public class TextProcess {
	private static final String SEPARATE_SYMBOL_SPACE = " ";
	private static final String SEPARATE_SYMBOL_PARAGRAPH = "\n";
	private Logger log = LogManager.getRootLogger();
	private PhoneService phoneService = new PhoneService();
	
	protected List<String> findPhoneWithName(String text){
		List<String> phones = new ArrayList<String>();
        String[] paragraphValues = text.split(SEPARATE_SYMBOL_PARAGRAPH);
        for(int i = 0; i < paragraphValues.length; i++){
        	String phone = findPhone(paragraphValues[i]);
        	String name = paragraphValues[i].replaceAll(phone, SEPARATE_SYMBOL_SPACE);
        	name = name.replaceAll("[+,-,\u2013,\t]", SEPARATE_SYMBOL_SPACE);
        	name = name.replaceAll("-?\\d+(\\.)?", "");
        	name = name.trim();
        	if(!phone.isEmpty()){
        		log.debug("Phone and name is not empty: " + phone + "\t" + name);
        	}
        	//phone = phoneService.modifyPhone(phone);
        	//if(!phone.isEmpty()){
        	//	phones.add(phone);
        	//}
        }
        
        //phones = removeDublicates(phones);
        
        return phones;
	}
	
	protected List<String> findPhones(String text){
		List<String> phones = new ArrayList<String>();
		text = text.replaceAll("[^0-9,^-]+"," ");
        String[] numericValue = text.split(SEPARATE_SYMBOL_SPACE);
        for(int i = 0; i < numericValue.length; i++){
        	String phone = numericValue[i].replaceAll("[^0-9]+","");
        	phone = phoneService.modifyPhone(phone);
        	if(!phone.isEmpty()){
        		phones.add(phone);
        	}
        }
        
        phones = removeDublicates(phones);
        
        return phones;
	}
	
	private String findPhone(String text){
		text = text.replaceAll("[^0-9,^-]+"," ");
		String[] numericValue = text.split(SEPARATE_SYMBOL_SPACE);
		for(int i = 0; i < numericValue.length; i++){
			String phone = numericValue[i].replaceAll("[^0-9]+","");
        	String modyfyedPhone = phoneService.modifyPhone(phone);
        	if(!modyfyedPhone.isEmpty()){
        		return phone;
        	}
		}
		return "";
	}
	
	/**
	 * Removes duplicates from phone list
	 * @param phones
	 * @return
	 */
	private List<String> removeDublicates(List<String> phones){
		for(int i = 0; i < phones.size(); i++){
			for(int j = i + 1; j < phones.size(); j++){
				if(phones.get(i).equals(phones.get(j))){
					phones.remove(j);
					j--;
					continue;
				}
			}
		}
		return phones;
	}
}
