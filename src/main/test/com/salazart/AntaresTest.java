package com.salazart;

import com.salazart.db.services.PersonMtLinkService;
import com.salazart.folder.models.Contact;

public class AntaresTest {

	public static void main(String[] args) {
		Contact contact = new Contact();
		contact.setPhone("380501641920");
		
		PersonMtLinkService personMtLinkService = new PersonMtLinkService();
		boolean flag = personMtLinkService.isContactInDB(contact);
		System.out.println(flag);
	}

}
