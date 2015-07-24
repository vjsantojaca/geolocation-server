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
@Table(name="Location")
public class LocationEntity implements Serializable {

	private static final long serialVersionUID = -7353668217759089384L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idLocation")
	private int idLocation;
	
	@Column(name="idUser")
	private int idUser;
	
	@Column(name="latitude")
	private float latitude;
	
	@Column(name="longitude")
	private float longitude;
	
	@Column(name="date")
	private long date;	
	
	@Column(name="battery")
	private float battery;
	
	public float getBattery() {
		return battery;
	}

	public void setBattery(float battery) {
		this.battery = battery;
	}

	public LocationEntity() {
		super();
	}

	public int getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(int idLocation) {
		this.idLocation = idLocation;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}
}