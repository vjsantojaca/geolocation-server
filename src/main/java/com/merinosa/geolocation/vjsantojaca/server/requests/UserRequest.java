package com.merinosa.geolocation.vjsantojaca.server.requests;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class UserRequest {
	
	@NotNull
	private String name;
	
	@NotNull
	private String surname;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	private int num_telef;
	
	@NotNull
	private long password;
	
	@NotNull
	private long imei;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNum_telef() {
		return num_telef;
	}

	public void setNum_telef(int num_telef) {
		this.num_telef = num_telef;
	}

	public long getPassword() {
		return password;
	}

	public void setPassword(long password) {
		this.password = password;
	}

	public long getImei() {
		return imei;
	}

	public void setImei(long imei) {
		this.imei = imei;
	}
}