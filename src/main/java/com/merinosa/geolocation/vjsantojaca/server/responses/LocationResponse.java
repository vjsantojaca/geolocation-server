package com.merinosa.geolocation.vjsantojaca.server.responses;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.DeviceEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.LocationEntity;

public class LocationResponse {
	private DeviceEntity device;
	private LocationEntity location;
	
	public LocationResponse() {
		super();
	}
	
	public LocationResponse(DeviceEntity device, LocationEntity location) {
		this.device = device;
		this.location = location;
	}

	public DeviceEntity getDevice() {
		return device;
	}
	public void setDevice(DeviceEntity device) {
		this.device = device;
	}
	public LocationEntity getLocation() {
		return location;
	}
	public void setLocation(LocationEntity location) {
		this.location = location;
	}
}