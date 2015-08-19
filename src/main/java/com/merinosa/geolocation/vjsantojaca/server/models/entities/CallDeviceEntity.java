package com.merinosa.geolocation.vjsantojaca.server.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.context.annotation.ComponentScan;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.CallDeviceEntity.CallDevicePK;

@ComponentScan
@Entity
@Table(name="CallDevice")
@IdClass(CallDevicePK.class)
public class CallDeviceEntity implements Serializable 
{
	private static final long serialVersionUID = 767117523030997109L;
	
	@Id
	@Column(name="idCall")
	private int idCall;
	
	@Id
	@Column(name="idDevice")
	private int idDevice;
	
	@Id
	@Column(name="date")
	private long date;
	
	@Column(name="number")
	private String number;
	
	@Column(name="duration")
	private int duration;
	
	@Column(name="type")
	private int type;


	public CallDeviceEntity() {
		super();
	}
	
	public int getIdCall() {
		return idCall;
	}

	public void setIdCall(int idCall) {
		this.idCall = idCall;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public static class CallDevicePK implements Serializable 
	{
		private static final long serialVersionUID = 9185140255622399681L;
		
		protected Integer idCall;
	    protected Integer idDevice;
	    protected Long date;
	 
	    public CallDevicePK() {} 
	 
	    public CallDevicePK(Integer idCall, Integer idDevice, Long date) {
	        this.idCall = idCall;
	        this.idDevice = idDevice;
	        this.date = date;
	    }
	} 
	
}