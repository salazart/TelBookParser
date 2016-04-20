package com.salazart.db.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.salazart.TelBookParser;
import com.salazart.db.interfaces.IPersonPhone;
import com.salazart.db.models.PersonsPhone;
import com.salazart.db.utils.RequestDictionary;

public class PersonPhoneService implements IPersonPhone{

	public void addPersonsPhones(List<PersonsPhone> personPhones) {
		Connection conn = ConnectionService.getInstance().getConnection();
		try {
			for(int i = 0; i < personPhones.size(); i++){
				PreparedStatement ps = conn.prepareStatement(RequestDictionary.PERSON_PHONE_INSERT);;
				
				ps.setInt(1, personPhones.get(i).getIdPerson());
				ps.setString(2, personPhones.get(i).getPhone());
				
				ps.executeUpdate();
				
				if(ps!=null){
					ps.close();
				}
			}
			TelBookParser.log4j.info(this.getClass().toString() + " Row inserted into osoba_number_tbl sucesfully");
		} catch (SQLException e) {
			TelBookParser.log4j.error(this.getClass().toString() + e.getMessage());
		} finally {
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					TelBookParser.log4j.error(this.getClass().toString() + e.getMessage());
				}
			}
		}
	}
}
