package com.salazart.db.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.salazart.db.interfaces.IPersonImei;
import com.salazart.db.models.PersonImei;
import com.salazart.db.utils.RequestDictionary;

public class PersonImeiService extends QueryService implements IPersonImei{

	public void addPersonImeis(List<PersonImei> personImeis) {
		try {
			for(int i = 0; i < personImeis.size(); i++){
				PreparedStatement ps = getConnection().prepareStatement(RequestDictionary.MT_INSERT);
				
				ps.setInt(1, personImeis.get(i).getIdPerson());
				ps.setString(2, personImeis.get(i).getImei());
				
				ps.executeUpdate();
			}
			log.info("Row inserted into osoba_number_tbl sucesfully");
		} catch (SQLException e) {
			log.error(e);
		} finally {
			closeConnection();
		}
	}

		

}
