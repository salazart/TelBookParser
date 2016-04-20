package com.salazart.db.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.salazart.db.interfaces.IPersonMtLink;
import com.salazart.db.models.Person;
import com.salazart.db.models.PersonAddr;
import com.salazart.db.models.PersonImei;
import com.salazart.db.models.PersonMt;
import com.salazart.db.models.PersonMtLink;
import com.salazart.db.models.PersonsPhone;
import com.salazart.folder.models.Contact;
import com.salazart.folder.models.TelBook;

public class PrepareService {
	private Logger log = LogManager.getRootLogger();
	
	public Person getPerson(TelBook telBook) {
		Person person = new Person();

		person.setLastName(firstLetterUpperCase(telBook.getLastName()));
		person.setFirstName(firstLetterUpperCase(telBook.getFirstName()));
		person.setPatrName(firstLetterUpperCase(telBook.getPatrName()));
		person.setBrtd(telBook.getYear());
		person.setIdSection(Integer.valueOf(telBook.getSection()));
		person.setTextPerson(telBook.getNotePerson());
		
		return person;
	}
	
	private String firstLetterUpperCase(String text){
		if(text != null && !text.isEmpty()){
			return text.substring(0, 1).toUpperCase() + text.substring(1);
		} else {
			return text;
		}
	}

	public List<PersonsPhone> getPersonPhone(TelBook telBook, int idPerson) {
		List<PersonsPhone> personPhones = new ArrayList<PersonsPhone>();

		for (int i = 0; i < telBook.getPhonesPerson().size(); i++) {
			PersonsPhone personPhone = new PersonsPhone();
			personPhone.setIdPerson(idPerson);
			personPhone.setPhone(telBook.getPhonesPerson().get(i));
			personPhones.add(personPhone);
		}
		return personPhones;
	}

	public List<PersonImei> getPersonImei(TelBook telBook, int idPerson) {
		List<PersonImei> personImeis = new ArrayList<PersonImei>();

		for (int i = 0; i < telBook.getImeisPerson().size(); i++) {
			PersonImei personImei = new PersonImei();
			personImei.setIdPerson(idPerson);
			personImei.setImei(telBook.getImeisPerson().get(i));
			personImeis.add(personImei);
		}
		return personImeis;
	}

	public PersonAddr getPersonAddr(TelBook telBook, int idPerson) {
		PersonAddr personAddr = new PersonAddr();
		personAddr.setIdPerson(idPerson);
		personAddr.setAddr(telBook.getAdressPerson());
		return personAddr;
	}

	public PersonMt getPersonMt(TelBook telBook, int idPerson) {
		PersonMt personMt = new PersonMt();
		personMt.setIdPerson(idPerson);
		personMt.setIdSection(telBook.getSection());
		
		if(idPerson != 0){
			personMt.setTextMt(telBook.getNotePerson());
		}
		
		if(telBook.getPhonesPerson().size() != 0){
			personMt.setPhone(telBook.getPhonesPerson().get(0));
		}
		
		if(telBook.getImeisPerson().size() != 0){
			personMt.setImei(telBook.getImeisPerson().get(0));
		}
		return personMt;
	}

	public List<PersonMtLink> convertContactToPersonMtLink(int idPersonMt, List<Contact> contacts) {
		List<PersonMtLink> personMtLinks = new ArrayList<PersonMtLink>();
		
		for (int i = 0; i < contacts.size(); i++) {
			Contact contact = contacts.get(i);
			
			personMtLinks.add(
					new PersonMtLink(idPersonMt, contact.getPhone(), 
							contact.getName(), contact.getNote()));
		}
		
		return personMtLinks;
	}
	
	public List<Contact> filterDublicate(List<Contact> contacts){
		log.info("Start search dublicate");
		contacts = removeDublicate(contacts);
		contacts = filterDublicateInDB(contacts);
		log.info("Finish search dublicate");
		return contacts;
	}
	
	private List<Contact> removeDublicate(List<Contact> contacts){
		for (int i = 0; i < contacts.size(); i++) {
			for (int j = i + 1; j < contacts.size(); j++) {
				if(isEqualContact(contacts.get(i), contacts.get(j))){
					contacts.remove(j);
					j--;
				}
			}
		}
		return contacts;
	}
	
	private boolean isEqualContact(Contact first, Contact second){
		return (StringUtils.equalsIgnoreCase(first.getName(), second.getName()) 
				&& StringUtils.equalsIgnoreCase(first.getPhone(), second.getPhone()));
	}
	
	private List<Contact> filterDublicateInDB(List<Contact> contacts){
		IPersonMtLink pmlDAO = new PersonMtLinkService();
		for (int i = 0; i < contacts.size(); i++) {
			if(pmlDAO.isContactInDB(contacts.get(i))){
				contacts.remove(i);
				i--;
			}
		}
		return contacts;
	}
}
