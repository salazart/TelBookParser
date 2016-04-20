package com.salazart.db.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.salazart.TelBookParser;
import com.salazart.db.interfaces.IDataBaseService;
import com.salazart.db.interfaces.IPerson;
import com.salazart.db.interfaces.IPersonAdress;
import com.salazart.db.interfaces.IPersonImei;
import com.salazart.db.interfaces.IPersonMt;
import com.salazart.db.interfaces.IPersonMtLink;
import com.salazart.db.interfaces.IPersonPhone;
import com.salazart.db.models.Person;
import com.salazart.db.models.PersonAddr;
import com.salazart.db.models.PersonImei;
import com.salazart.db.models.PersonMt;
import com.salazart.db.models.PersonMtLink;
import com.salazart.db.models.PersonsPhone;
import com.salazart.folder.models.Contact;
import com.salazart.folder.models.TelBook;

public class DataBaseService implements IDataBaseService {
	private Logger log = LogManager.getRootLogger();
	
	private PrepareService prepareService = new PrepareService();
	private IPersonMtLink pmlDAO = new PersonMtLinkService();
	private IPersonMt pmDAO = new PersonMtService();

	public void insertListTelBook(List<TelBook> listTelBook) {
		if (listTelBook != null && !listTelBook.isEmpty()) {
			for (int i = 0; i < listTelBook.size(); i++) {
				insertTelBook(listTelBook.get(i));
			}
		}
	}

	public void insertTelBook(TelBook telBook) {

		int idPerson = insertPerson(telBook);

		PersonMt personMt = prepareService.getPersonMt(telBook, idPerson);
		
		int countContacts = addContacts(personMt , telBook);
		TelBookParser.trafficDroverUI.addLog("Занесено " + countContacts + " телефонів без дублікатів");
		log.info(countContacts + " phones inserted without dublicate");
	}

	private int addContacts(PersonMt personMt, TelBook telBook) {
		List<Contact> contacts = prepareService.filterDublicate(telBook.getContacts());
		if(!contacts.isEmpty()){
			int idPersonMt = pmDAO.addPersonMt(personMt);
			List<PersonMtLink> personMtLinks = prepareService.convertContactToPersonMtLink(idPersonMt, contacts);
			for (PersonMtLink personMtLink : personMtLinks) {
				pmlDAO.addPersonMtLink(personMtLink);
			}
			return personMtLinks.size();
		}
		return 0;
	}
	
	private int insertPerson(TelBook telBook) {
		Person person = prepareService.getPerson(telBook);
		
		int idPerson = 0;
		if(!person.isEmpty()){
			IPerson pDAO = new PersonService();
			idPerson = pDAO.addPerson(person);
		}
		

		if (idPerson != 0) {
			
			addPersonPhones(telBook, idPerson);

			addPersonImeis(telBook, idPerson);

			addPersonAddress(telBook, idPerson);

			return idPerson;
		} else {
			return 0;
		}
	}

	private void addPersonAddress(TelBook telBook, int idPerson) {
		PersonAddr personAddr = prepareService.getPersonAddr(telBook, idPerson);
		if(!personAddr.isEmpty()){
			IPersonAdress paDAO = new PersonAdressService();
			paDAO.addPersonAdress(personAddr);
		}
	}

	private void addPersonImeis(TelBook telBook, int idPerson) {
		List<PersonImei> personImeis = prepareService.getPersonImei(telBook, idPerson);
		if(!personImeis.isEmpty()){
			IPersonImei piDAO = new PersonImeiService();
			piDAO.addPersonImeis(personImeis);
		}
	}

	private void addPersonPhones(TelBook telBook, int idPerson) {
		List<PersonsPhone> personPhones = prepareService.getPersonPhone(telBook, idPerson);
		if(!personPhones.isEmpty()){
			IPersonPhone ppDAO = new PersonPhoneService();
			ppDAO.addPersonsPhones(personPhones);
		}
	}
}
