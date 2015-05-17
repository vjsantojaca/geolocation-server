package com.merinosa.geolocation.vjsantojaca.server.requests;

import javax.validation.constraints.NotNull;

public class GeolocationRequest {
	
	@NotNull
	private String id;
	
	@NotNull
	private String latitude;
	
	@NotNull
	private String longitude;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}