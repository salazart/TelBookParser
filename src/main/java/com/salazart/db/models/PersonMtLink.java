package com.salazart.db.models;

import java.util.Date;

public class PersonMtLink {
	private final int DEFAULT_TYPE_MT_LINK = 1;
	
	private int idPersonMtLink;
	private int idPersonMt;
	private int idTypMtLink = DEFAULT_TYPE_MT_LINK;
	private Date dateMtLink;
	private String phone;
	private String name;
	private String smsMtLink;
	private String imei;
	private boolean booVlasn;
	private String textMtLink = "";
	
	public PersonMtLink() {
	}
	
	public PersonMtLink(int idPersonMt, String phone, String name, String note) {
		setIdPersonMt(idPersonMt);
		setPhone(phone);
		setName(name);
		setTextMtLink(note);
	}
	
	public int getIdPersonMtLink() {
		return idPersonMtLink;
	}
	public void setIdPersonMtLink(int idOsobaMtLink) {
		this.idPersonMtLink = idOsobaMtLink;
	}
	public int getIdPersonMt() {
		return idPersonMt;
	}
	public void setIdPersonMt(int idPersonMt) {
		this.idPersonMt = idPersonMt;
	}
	public int getIdTypMtLink() {
		return idTypMtLink;
	}
	public void setIdTypMtLink(int idTypMtLink) {
		this.idTypMtLink = idTypMtLink;
	}
	public Date getDateMtLink() {
		return dateMtLink;
	}
	public void setDateMtLink(Date dateMtLink) {
		this.dateMtLink = dateMtLink;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSmsMtLink() {
		return smsMtLink;
	}
	public void setSmsMtLink(String smsMtLink) {
		this.smsMtLink = smsMtLink;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public boolean isBooVlasn() {
		return booVlasn;
	}
	public void setBooVlasn(boolean booVlasn) {
		this.booVlasn = booVlasn;
	}
	public String getTextMtLink() {
		return textMtLink;
	}
	public void setTextMtLink(String textMtLink) {
		this.textMtLink = textMtLink;
	}

}
