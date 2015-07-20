package com.merinosa.geolocation.vjsantojaca.server.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GroupDevice")
public class GroupDeviceEntity implements Serializable 
{

	private static final long serialVersionUID = -5146905297026279232L;
	
	@Id
	@Column(name="idGroup")
	private int idGroup;
	
	@Id
	@Column(name="idDevice")
	private int idDevice;

	public GroupDeviceEntity() {
		super();
	}

	public int getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}

	public int getIdDevice() {
		return idDevice;
	}

	public void setIdDevice(int idDevice) {
		this.idDevice = idDevice;
	}
}