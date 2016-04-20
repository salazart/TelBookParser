package com.salazart.db.interfaces;

import com.salazart.db.models.PersonMtLink;
import com.salazart.folder.models.Contact;

public interface IPersonMtLink {
	void addPersonMtLink(PersonMtLink personMtLink);
	boolean isContactInDB(Contact contact);
	boolean isPersonMtLink(PersonMtLink personMtLink);
}
