package com.salazart.db.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.salazart.db.interfaces.IPerson;
import com.salazart.db.models.Person;
import com.salazart.db.utils.RequestDictionary;
import com.salazart.folder.services.PropertyService;

public class PersonService extends QueryService implements IPerson{
	private static final String FLAG_FIND_PERSON = "findPerson";
	
	private boolean flagFindPerson = Boolean.valueOf(
			PropertyService.getValueProperties(FLAG_FIND_PERSON));
	
	public int addPerson(Person person){
		int idPerson = 0;
		if(flagFindPerson){
			idPerson = getIdPerson(person);
		}
		
		if(idPerson == 0){
			idPerson = insertPerson(person);
		}
		
		return idPerson;
	}
	
	public int getIdPerson(Person person){
		int idPerson = 0;
		Connection conn = ConnectionService.getInstance().getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(RequestDictionary.PERSON_GET_ID);
			ps.setString(1, person.getLastName().toLowerCase());
			ps.setString(2, person.getFirstName().toLowerCase());
			ps.setString(3, person.getPatrName().toLowerCase());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				idPerson = rs.getInt(1);
			}
		} catch (SQLException e) {
			log.error(e);
		} finally {
			closeConnection();
		}
		return idPerson;
	}
	private int insertPerson(Person person) {
		String insertPerson;
		if(person.getBrtd() == 0){
			insertPerson = "INSERT INTO osoba_tbl (id_osoba, fam_osoba, name_osoba, patr_osoba, id_viddil, cur_user, date_osoba, boo_cas, text_osoba) "
					+ "VALUES (DEFAULT, ?, ?, ?, ?, DEFAULT, TIMESTAMP '" + person.getDatePersonTimeStamp() + "', ?, ?) RETURNING id_osoba;";
		} else {
			insertPerson = "INSERT INTO osoba_tbl (id_osoba, fam_osoba, name_osoba, patr_osoba, id_viddil, cur_user, date_osoba, boo_cas, text_osoba, brtd) "
					+ "VALUES (DEFAULT, ?, ?, ?, ?, DEFAULT, TIMESTAMP '" + person.getDatePersonTimeStamp() + "', ?, ?, ?) RETURNING id_osoba;";
		}
		
		int index = 0;
		try {
			PreparedStatement ps = getConnection().prepareStatement(insertPerson);
			
			ps.setString(1, person.getLastName());
			ps.setString(2, person.getFirstName());
			ps.setString(3, person.getPatrName());
			ps.setInt(4, person.getIdSection());
			ps.setBoolean(5, person.getBooCas());
			ps.setString(6, person.getTextPerson());
			if(person.getBrtd() != 0){
				ps.setInt(7, person.getBrtd());
			}
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				index = rs.getInt(1);
			}
			log.debug("Row " + index + " inserted into osoba_tbl sucesfully");
		} catch (SQLException e) {
			log.error(e);
		} finally {
			closeConnection();
		}
		return index;
	}
}
