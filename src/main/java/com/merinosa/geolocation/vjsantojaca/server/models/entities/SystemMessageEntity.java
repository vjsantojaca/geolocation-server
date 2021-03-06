package com.merinosa.geolocation.vjsantojaca.server.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@Entity
@Table(name="SystemMessage")
public class SystemMessageEntity implements Serializable 
{

	private static final long serialVersionUID = 3327440374087265056L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idMessage")
	private int idMessage;
	
	@Column(name="idDevice")
	private int idDevice;
	
	@Column(name="date")
	private long date;
	
	@Column(name="typeMessage")
	private TypeMessage typeMessage;
	
	@Column(name="readSM")
	private Boolean read;

	public SystemMessageEntity() {
		super();
	}

	public int getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
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

	public TypeMessage getTypeMessage() {
		return typeMessage;
	}

	public void setTypeMessage(TypeMessage typeMessage) {
		this.typeMessage = typeMessage;
	}

	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}
}