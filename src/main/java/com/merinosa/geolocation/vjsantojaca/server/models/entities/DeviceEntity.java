package com.merinosa.geolocation.vjsantojaca.server.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Device")
public class DeviceEntity implements Serializable {

	private static final long serialVersionUID = -6804316028082895196L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idDevice")
	private int idDevice;
	
	@Column(name="nameDevice")
	private String nameDevice;
	
	@Column(name="emailDevice")
	private String emailDevice;
	
	@Column(name="nickDevice")
	private String nickDevice;
	
	@Column(name="numberDevice")
	private String numberDevice;
	
	@Column(name="gcm")
	private String gcm;

	public DeviceEntity() {
		super();
	}

	public int getIdDevice() {
		return idDevice;
	}

	public void setIdDevice(int idDevice) {
		this.idDevice = idDevice;
	}

	public String getNameDevice() {
		return nameDevice;
	}

	public void setNameDevice(String nameDevice) {
		this.nameDevice = nameDevice;
	}

	public String getEmailDevice() {
		return emailDevice;
	}

	public void setEmailDevice(String emailDevice) {
		this.emailDevice = emailDevice;
	}

	public String getNickDevice() {
		return nickDevice;
	}

	public void setNickDevice(String nickDevice) {
		this.nickDevice = nickDevice;
	}

	public String getNumberDevice() {
		return numberDevice;
	}

	public void setNumberDevice(String numberDevice) {
		this.numberDevice = numberDevice;
	}

	public String getGcm() {
		return gcm;
	}

	public void setGcm(String gcm) {
		this.gcm = gcm;
	}
}