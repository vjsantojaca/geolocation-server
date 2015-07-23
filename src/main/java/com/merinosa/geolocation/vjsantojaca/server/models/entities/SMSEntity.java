package com.merinosa.geolocation.vjsantojaca.server.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Sms")
public class SMSEntity implements Serializable {

	private static final long serialVersionUID = 4597371444450690019L;

	@Id
	@Column(name="idSms")
	private int idSms;
	
	@Id
	@Column(name="idDevice")
	private int idDevice;
	
	@Id
	@Column(name="date")
	private long date;
	
	@Column(name="numberPhone")
	private int numberPhone;
	
	@Column(name="message")
	private String message;
	
	@Column(name="type")
	private int type;

	public int getIdSms() {
		return idSms;
	}

	public void setIdSms(int idSms) {
		this.idSms = idSms;
	}

	public int getIdDevice() {
		return idDevice;
	}

	public void setIdDevice(int idDevice) {
		this.idDevice = idDevice;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public int getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(int numberPhone) {
		this.numberPhone = numberPhone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}