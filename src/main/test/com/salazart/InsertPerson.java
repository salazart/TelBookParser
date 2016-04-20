package com.salazart;

import com.salazart.db.models.Person;
import com.salazart.db.services.PersonService;

public class InsertPerson {

	public static void main(String[] args) {
		Person person = new Person();
		person.setLastName("ВКонтакте");
		
		PersonService pDAO = new PersonService();
		System.out.println(pDAO.addPerson(person));
	}

}
