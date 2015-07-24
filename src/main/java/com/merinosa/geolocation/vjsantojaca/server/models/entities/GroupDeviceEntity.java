package com.merinosa.geolocation.vjsantojaca.server.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.context.annotation.ComponentScan;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.GroupDeviceEntity.GroupDevicekPK;

@ComponentScan
@Entity
@Table(name="GroupDevice")
@IdClass(GroupDevicekPK.class)
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
	
	public class GroupDevicekPK implements Serializable 
	{
		private static final long serialVersionUID = 9185140255622399681L;
		
		protected Integer idGroup;
	    protected Integer idDevice;
	 
	    public GroupDevicekPK() {} 
	 
	    public GroupDevicekPK(Integer idGroup, Integer idDevice) {
	        this.idGroup = idGroup;
	        this.idDevice = idDevice;
	    }
	} 
}