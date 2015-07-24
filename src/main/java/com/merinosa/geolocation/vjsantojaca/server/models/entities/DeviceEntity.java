package com.merinosa.geolocation.vjsantojaca.server.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
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
	private int numberDevice;
	
	@Column(name="typeDevice")
	private String typeDevice;
	
	@Column(name="gcm")
	private String gcm;
	
	@Column(name="appList")
	private String appList;
	
	@Column(name="pass")
	private String pass;

	public DeviceEntity() {
	}
	
	public DeviceEntity(JSONObject object) {
		if( object.has("nameDevice") )
			this.nameDevice = object.getString("nameDevice");
		else 
			this.nameDevice = "";
		
		if( object.has("emailDevice") )
			this.emailDevice = object.getString("emailDevice");
		else 
			this.emailDevice = "";
		
		if( object.has("nickDevice") )
			this.nickDevice = object.getString("nickDevice");
		else 
			this.nickDevice = "";
		
		if( object.has("numberDevice") )
			this.numberDevice = object.getInt("numberDevice");
		
		if( object.has("pass") )
			this.pass = object.getString("pass");
		else 
			this.pass = "";
		
		this.gcm = "";
		this.appList = "";
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

	public int getNumberDevice() {
		return numberDevice;
	}

	public void setNumberDevice(int numberDevice) {
		this.numberDevice = numberDevice;
	}

	public String getGcm() {
		return gcm;
	}

	public void setGcm(String gcm) {
		this.gcm = gcm;
	}

	public String getTypeDevice() {
		return typeDevice;
	}

	public void setTypeDevice(String typeDevice) {
		this.typeDevice = typeDevice;
	}

	public String getAppList() {
		return appList;
	}

	public void setAppList(String appList) {
		this.appList = appList;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}