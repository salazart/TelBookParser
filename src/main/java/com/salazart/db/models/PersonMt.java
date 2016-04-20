package com.salazart.db.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonMt {
	private final int DEFAULT_VALUE = 4;
	private final int MT_SIM = 1;
	private final int ONLY_SIM = 3;
	private final int ONLY_MT = 2;
	private final int DEFAULT_ZLOCH = 6735;
	
	private int idPersonMt;
	private String phone = "";
	private String imei = "";
	private int idTypMt = DEFAULT_VALUE;
	private String textMt;
	private int idPerson;
	private Date dateIn = new Date();
	private int idZloch = DEFAULT_ZLOCH;
	private boolean booArh = false;
	private int idSection;
	private String curUser;
	private Date curDate;
	private int countNums;
	private boolean booCas = false;
	
	public int getIdPersonMt() {
		return idPersonMt;
	}
	public void setIdPersonMt(int idPersonMt) {
		this.idPersonMt = idPersonMt;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
		changeIdTypMt();
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
		changeIdTypMt();
	}
	public int getIdTypMt() {
		return idTypMt;
	}
	public void setIdTypMt(int idTypMt) {
		this.idTypMt = idTypMt;
	}
	public String getTextMt() {
		return textMt;
	}
	public void setTextMt(String textMt) {
		this.textMt = textMt;
	}
	public int getIdPerson() {
		return idPerson;
	}
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	public Date getDateIn() {
		return dateIn;
	}
	public String getDateInTimeStamp() {
		return parseDateToTimeStamp(dateIn);
	}
	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}
	public int getIdZloch() {
		return idZloch;
	}
	public void setIdZloch(int idZloch) {
		this.idZloch = idZloch;
	}
	public boolean isBooArh() {
		return booArh;
	}
	public void setBooArh(boolean booArh) {
		this.booArh = booArh;
	}
	public int getIdSection() {
		return idSection;
	}
	public void setIdSection(int idSection) {
		this.idSection = idSection;
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
	public int getCountNums() {
		return countNums;
	}
	public void setCountNums(int countNums) {
		this.countNums = countNums;
	}
	public boolean getBooCas() {
		return booCas;
	}
	public void setBooCas(boolean booCas) {
		this.booCas = booCas;
	}
	private void changeIdTypMt(){
		if(!getPhone().isEmpty()){
			if(!getImei().isEmpty()){
				setIdTypMt(MT_SIM);
			} else {
				setIdTypMt(ONLY_SIM);
			}
		} else {
			setIdTypMt(ONLY_MT);
		}
	}
	private String parseDateToTimeStamp(Date date){
		final String DATE_OUT = "yyyy-MM-dd";
		SimpleDateFormat df = new SimpleDateFormat(DATE_OUT);
		String timeStampDate = df.format(date);
		return timeStampDate;
	}
}
