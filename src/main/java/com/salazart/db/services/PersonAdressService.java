package com.salazart.db.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.salazart.TelBookParser;
import com.salazart.db.interfaces.IPersonAdress;
import com.salazart.db.models.PersonAddr;

public class PersonAdressService implements IPersonAdress{

	public void addPersonAdress(PersonAddr personAddr) {
		String insertToOsobaImeiTbl = "INSERT INTO osoba_addr_tbl (id_osoba_addr, id_osoba, addr) "
				+ "VALUES (DEFAULT, ?, ?);";
		Connection conn = ConnectionService.getInstance().getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(insertToOsobaImeiTbl);
			
			ps.setInt(1, personAddr.getIdPerson());
			ps.setString(2, personAddr.getAddr());
			
			ps.executeUpdate();
			TelBookParser.log4j.info(this.getClass().toString() + " Row inserted into osoba_addr_tbl sucesfully");
		} catch (SQLException e1) {
			TelBookParser.log4j.error(this.getClass().toString() + e1.getMessage());
		} finally {
			try {
				if(ps!=null){
					ps.close();
				}
				//if(conn!=null){
				//	conn.close();
				//}
			} catch (SQLException e) {
				TelBookParser.log4j.error(this.getClass().toString() + e.getMessage());
			}
		}
	}

}
