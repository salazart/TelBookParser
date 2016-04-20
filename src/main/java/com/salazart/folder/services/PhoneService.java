package com.salazart.folder.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.salazart.TelBookParser;
import com.salazart.folder.models.Phone;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;


/**
 * This service modify phone number and returning it;
 * @author Dr
 *
 */
public class PhoneService {
	private final int MIN_LENGHT_PHONE = 9;
	private final int MAX_LENGHT_PHONE = 12;
	private final int IMEI_LENGHT = 14;
	private final String PATH_FILE_BASE_PROPERTY = "pathPhoneBase";
	private final String DEFAULT_PATH_FILE_BASE = "src/main/resources/PhoneBase.xml";
	private String fileName = getFileName();
	
	private String getFileName(){
		String fileName = PropertyService.getValueProperties(PATH_FILE_BASE_PROPERTY);
		if(fileName.isEmpty()){
			TelBookParser.trafficDroverUI.addLog("Не знайдено файл для завантаження зразків заголовка трафіку");
			return fileName;
		} else {
			return DEFAULT_PATH_FILE_BASE;
		}
	}
	
	private List<Phone> getPhonesRule(){
		XStream xstream = new XStream(new StaxDriver());
		
	    xstream.alias("phones", List.class);
	    xstream.processAnnotations(Phone.class);
		
		try{
			XMLPropertyService xmlService = new XMLPropertyService();
			String xmlText = xmlService.readXMLFile(new File(fileName));
			
			return (List<Phone>) xstream.fromXML(xmlText);
		} catch (Exception e) {
			TelBookParser.log4j.error(this.getClass().toString() + " " + e.getMessage());
			return new ArrayList<Phone>();
		}
	}
	
	public boolean isPhoneModel(String phone){
		phone = clearPhone(phone);
		return (phone.length() >= MIN_LENGHT_PHONE && phone.length() <= MAX_LENGHT_PHONE);
	}
	
	public String modifyPhone(String phone){
		if(isPhoneModel(phone)){
			String tempPhone = clearPhone(phone);
			
			List<Phone> phones = getPhonesRule();
			for (int i = 0; i < phones.size(); i++) {
				int lenght = phones.get(i).getLenght();
				String kod = phones.get(i).getCode();
				String prefix = phones.get(i).getPrefix();
				
				if(tempPhone.length() == lenght && tempPhone.startsWith(prefix)){
					return kod + tempPhone;
				}
			}
			TelBookParser.log4j.info("Невідомий формат номеру телефону: " + phone);
			return "";
		} else {
			return "";
		}
	}
	
	/**
	 * This method check imei of phone
	 */
	public String modifyImei(String imei) {
		imei = clearPhone(imei);
		
		if(imei.startsWith("12") || imei.startsWith("13")){
			return "0" + imei;
		} else if (imei.length() >= IMEI_LENGHT) {
			imei = imei.substring(0, IMEI_LENGHT);
			return imei + "0";
		} else
			return "";
	}
	
	public String clearPhone(String phone){
		phone = phone.trim();
		return phone.replaceAll("[^0-9]+","");
	}
}
