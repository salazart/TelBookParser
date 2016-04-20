package com.salazart.db.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.salazart.TelBookParser;
import com.salazart.db.interfaces.IPersonMt;
import com.salazart.db.models.PersonMt;

public class PersonMtService implements IPersonMt{
	public int addPersonMt(PersonMt personMt) {
		String insertToOsoba;
		if(personMt.getIdPerson() == 0){
			insertToOsoba = "INSERT INTO osoba_mt_tbl (id_osoba_mt, number, imei, id_typ_mt, date_in, id_zloch, id_viddil, boo_cas, text_mt) "
					+ "VALUES (DEFAULT, ?, ?, ?, TIMESTAMP '" + personMt.getDateInTimeStamp() + "', ?, ?, ?, ?) RETURNING id_osoba_mt;";
		} else {
			insertToOsoba = "INSERT INTO osoba_mt_tbl (id_osoba_mt, number, imei, id_typ_mt, date_in, id_zloch, id_viddil, boo_cas, text_mt, id_osoba) "
					+ "VALUES (DEFAULT, ?, ?, ?, TIMESTAMP '" + personMt.getDateInTimeStamp() + "', ?, ?, ?, ?, ?) RETURNING id_osoba_mt;";
		}
		
		int index = 0;
		Connection conn = ConnectionService.getInstance().getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(insertToOsoba);
			
			ps.setString(1, personMt.getPhone());
			ps.setString(2, personMt.getImei());
			ps.setInt(3, personMt.getIdTypMt());
			ps.setInt(4, personMt.getIdZloch());
			ps.setInt(5, personMt.getIdSection());
			ps.setBoolean(6, personMt.getBooCas());
			ps.setString(7, personMt.getTextMt());
			
			if(personMt.getIdPerson() != 0)
				ps.setInt(8, personMt.getIdPerson());
			
			ResultSet rs = ps.executeQuery();
			// return index id file in table osoba_tbl
			if (rs.next()) {
				index = rs.getInt(1);
			}
			TelBookParser.log4j.info(this.getClass().toString() + " Row " + index + " inserted into osoba_mt_tbl sucesfully");
		} catch (SQLException e1) {
			TelBookParser.log4j.error(this.getClass().toString()+ " " +e1.getMessage());
		} finally {
			try {
				if(ps!=null){
					ps.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				TelBookParser.log4j.error(this.getClass().toString()+ " " +e.getMessage());
			}
		}
		return index;
	}
}
