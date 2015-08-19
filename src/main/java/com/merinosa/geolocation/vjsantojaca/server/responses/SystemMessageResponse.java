package com.merinosa.geolocation.vjsantojaca.server.responses;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.DeviceEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.SystemMessageEntity;

public class SystemMessageResponse 
{
	private DeviceEntity device;
	private SystemMessageEntity smEntity;
	
	public SystemMessageResponse() {
		super();
	}
	
	public SystemMessageResponse(DeviceEntity device, SystemMessageEntity smEntity) {
		super();
		this.device = device;
		this.smEntity = smEntity;
	}

	public DeviceEntity getDevice() {
		return device;
	}
	public void setDevice(DeviceEntity device) {
		this.device = device;
	}
	public SystemMessageEntity getSmEntity() {
		return smEntity;
	}
	public void setSmEntity(SystemMessageEntity smEntity) {
		this.smEntity = smEntity;
	}
}
