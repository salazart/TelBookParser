package com.salazart.db.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.salazart.db.interfaces.IPersonMtLink;
import com.salazart.db.models.PersonMtLink;
import com.salazart.db.utils.RequestDictionary;
import com.salazart.folder.models.Contact;

public class PersonMtLinkService extends QueryService implements IPersonMtLink {
	
	public void addPersonMtLink(PersonMtLink personMtLink) {
		
		try {
			PreparedStatement ps = getConnection().prepareStatement(RequestDictionary.CONTACT_INSERT);

			ps.setInt(1, personMtLink.getIdPersonMt());
			ps.setInt(2, personMtLink.getIdTypMtLink());
			ps.setString(3, personMtLink.getPhone());
			ps.setString(4, personMtLink.getName());
			ps.setString(5, personMtLink.getTextMtLink());

			ps.executeUpdate();
			
			//log.debug("Row inserted successfully into table osoba_mt_link_tbl");
		} catch (SQLException e) {
			log.error(e);
		}
	}
	
	public boolean isContactInDB(Contact contact){
		boolean isContact = false;
		try {
			ResultSet rs = createRequestForIsContactInDB(contact.getPhone());

			while (rs.next()) {
				if (StringUtils.containsIgnoreCase(rs.getString(1), contact.getName())) {
					isContact = true;
					break;
				}
			}

		} catch (SQLException e) {
			log.error(e);
		}
		return isContact;
	}
	
	private ResultSet createRequestForIsContactInDB(String phone) throws SQLException {
		PreparedStatement ps = getConnection().prepareStatement(RequestDictionary.CONTACT_GET_NAME_BY_PHONE);
		ps.setString(1, phone);
		return ps.executeQuery();
	}

	public boolean isPersonMtLink(PersonMtLink personMtLink) {
		boolean isPersonMtLink = false;
		
		try {
			ResultSet rs = createRequest(personMtLink);

			while (rs.next()) {
				String text = rs.getString(1);
				if (StringUtils.containsIgnoreCase(text, personMtLink.getName())) {
					isPersonMtLink = true;
					break;
				}
			}

		} catch (SQLException e) {
			log.error(e);
		} finally {
			closeConnection();
		}
		return isPersonMtLink;
	}

	private ResultSet createRequest(PersonMtLink personMtLink) throws SQLException {
		PreparedStatement ps = getConnection().prepareStatement(RequestDictionary.CONTACT_GET_NAME_BY_PHONE);
		ps.setString(1, personMtLink.getPhone());
		return ps.executeQuery();
	}
}
