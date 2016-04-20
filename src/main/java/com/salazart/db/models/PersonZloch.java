package com.salazart.db.models;

import java.util.Date;

public class PersonZloch {
	private int idOsobaZloch;
	private int idOsoba;
	private int idZloch;
	private String curUser;
	private Date curDate;
	
	public int getIdOsobaZloch() {
		return idOsobaZloch;
	}
	public void setIdOsobaZloch(int idOsobaZloch) {
		this.idOsobaZloch = idOsobaZloch;
	}
	public int getIdOsoba() {
		return idOsoba;
	}
	public void setIdOsoba(int idOsoba) {
		this.idOsoba = idOsoba;
	}
	public int getIdZloch() {
		return idZloch;
	}
	public void setIdZloch(int idZloch) {
		this.idZloch = idZloch;
	}
	public String getCurUser() {
		return curUser;
	}
	public void setCurUser(String curUser) {
		this.curUser = curUser;
	}
	public Date getCurDate() {
		return curDate;
	}
	public void setCurDate(Date curDate) {
		this.curDate = curDate;
	}
}
