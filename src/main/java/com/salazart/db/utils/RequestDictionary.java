package com.salazart.db.utils;

public class RequestDictionary {
	public static final String PERSON_GET_ID = "SELECT id_osoba "
			+ "FROM osoba_tbl "
			+ "WHERE lower(fam_osoba)=? AND lower(name_osoba)=? AND lower(patr_osoba)=? "
			+ "ORDER BY cur_date DESC "
			+ "LIMIT 1";
	
	public static final String PERSON_PHONE_INSERT = "INSERT INTO osoba_number_tbl (id_osoba_number, id_osoba, number) "
			+ "VALUES (DEFAULT, ?, ?);";
	
	public static final String CONTACT_GET_NAME_BY_PHONE = "SELECT name_mt_link FROM osoba_mt_link_tbl "
			+ "WHERE number = ?;";
	
	public static final String CONTACT_INSERT = "INSERT INTO osoba_mt_link_tbl (id_osoba_mt_link, id_osoba_mt, id_typ_mt_link, number, name_mt_link, text_mt_link) "
			+ "VALUES (DEFAULT, ?, ?, ?, ?, ?);";
	
	public static final String MT_INSERT = "INSERT INTO osoba_imei_tbl (id_osoba_imei, id_osoba, imei) "
			+ "VALUES (DEFAULT, ?, ?);";
	
}
