package com.merinosa.geolocation.vjsantojaca.server.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.context.annotation.ComponentScan;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.SMSEntity.SMSPK;

@ComponentScan
@Entity
@Table(name="Sms")
@IdClass(SMSPK.class)
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

	public SMSEntity() {
	}

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
	
	public class SMSPK implements Serializable 
	{
		private static final long serialVersionUID = 9185140255622399681L;
		
		protected Integer idSms;
	    protected Integer idDevice;
	    protected Long date;
	 
	    public SMSPK() {} 
	 
	    public SMSPK(Integer idSms, Integer idDevice, Long date) {
	        this.idSms = idSms;
	        this.idDevice = idDevice;
	        this.date = date;
	    }
	} 
	
}