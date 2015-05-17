package com.merinosa.geolocation.vjsantojaca.server.responses;

import javax.validation.constraints.NotNull;

public class GeolocationResponses {
	
	@NotNull
	private int id;
	
	@NotNull
	private long latitude;
	
	@NotNull
	private long longitude;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
}