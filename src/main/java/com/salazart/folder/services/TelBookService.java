package com.salazart.folder.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.salazart.TelBookParser;
import com.salazart.folder.models.Contact;
import com.salazart.folder.models.TelBook;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class TelBookService {
	
	private TextService textService = new TextService();
	
	private PhoneService phoneService = new PhoneService();

	public TelBook readTelBook(File file){
		XStream xstream = new XStream(new StaxDriver());
		
		xstream.processAnnotations(TelBook.class);
		xstream.processAnnotations(Contact.class);
		
		try{
			XMLPropertyService xmlService = new XMLPropertyService();
			String xmlText = xmlService.readXMLFile(file);
			
			TelBook telBook = (TelBook) xstream.fromXML(xmlText);
			return prepareTelBook(telBook);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
			return new TelBook();
		}
	}
	
	public TelBook prepareTelBook(TelBook telBook){
		TelBookParser.trafficDroverUI.addLog("FirstName: " + telBook.getLastName());
		TelBookParser.trafficDroverUI.addLog("LastName: " + telBook.getFirstName());
		TelBookParser.trafficDroverUI.addLog("Patronymic: " + telBook.getPatrName());
		TelBookParser.trafficDroverUI.addLog("Year: " + telBook.getYear());
		
		TelBookParser.trafficDroverUI.addLog("Section: " + telBook.getSection());
		
		telBook.setPhonesPerson(processPhones(telBook.getPhonesPerson()));
		telBook.setImeisPerson(processImeis(telBook.getImeisPerson()));
			
		TelBookParser.trafficDroverUI.addLog("Addr In: " + telBook.getAdressPerson());
		String adress = textService.clearText(telBook.getAdressPerson());
		telBook.setAdressPerson(adress);
		TelBookParser.trafficDroverUI.addLog("Addr Out: " + telBook.getAdressPerson());
		
		if(telBook.getNotePerson() == null){
			telBook.setNotePerson("");
		}
		TelBookParser.trafficDroverUI.addLog("Note: " + telBook.getNotePerson());
		
		telBook.setContacts(processContacts(telBook.getContacts()));
		return telBook;
	}
	
	private List<String> processPhones(List<String> inPhones){
		List<String> outPhones = new ArrayList<String>();
		for(int i = 0; i < inPhones.size(); i++){
			TelBookParser.trafficDroverUI.addLog("Phone In: " + inPhones.get(i));
			String phone = phoneService.modifyPhone(inPhones.get(i));
			if(!phone.isEmpty()){
				TelBookParser.trafficDroverUI.addLog("Phone Out: " + phone);
				outPhones.add(phone);
			}
		}
		return outPhones;
	}
	
	private List<String> processImeis(List<String> inImeis){
		List<String> outImeis = new ArrayList<String>();
		for(int i = 0; i < inImeis.size(); i++){
			TelBookParser.trafficDroverUI.addLog("Imei In: " + inImeis.get(i));
			String imei = phoneService.modifyImei(inImeis.get(i));
			if(!imei.isEmpty()){
				TelBookParser.trafficDroverUI.addLog("Imei Out: " + imei);
				outImeis.add(imei);
			}
		}
		return outImeis;
	}
	
	private List<Contact> processContacts(List<Contact> inContacts){
		List<Contact> outContacts = new ArrayList<Contact>();
		for (int i = 0; i < inContacts.size(); i++) {

			Contact contact = inContacts.get(i);
			TelBookParser.trafficDroverUI.addLog("In:\t" + contact.getPhone() + "\t"
					+ contact.getName() + "\t" + contact.getNote());
			
			contact.setPhone(phoneService.modifyPhone(contact.getPhone()));
			if (!contact.isEmpty()) {
				contact.setName(textService.clearText(contact.getName()));
				contact.setNote(textService.clearText(contact.getNote()));
				outContacts.add(contact);
			}
			
			TelBookParser.trafficDroverUI.addLog("Out:\t" + contact.getPhone() + "\t"
					+ contact.getName() + "\t" + contact.getNote());
			}
		
		return outContacts;
	}
	
}
