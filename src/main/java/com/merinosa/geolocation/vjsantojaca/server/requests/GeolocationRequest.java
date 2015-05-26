package com.merinosa.geolocation.vjsantojaca.server.requests;

import javax.validation.constraints.NotNull;

public class GeolocationRequest {
	
	@NotNull
	private long latitude;
	
	@NotNull
	private long longitude;
	
	@NotNull
	private long date;
	
	@NotNull
	private int user;

	public long getLatitude() {
		return latitude;
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	public long getLongitude() {
		return longitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}
}