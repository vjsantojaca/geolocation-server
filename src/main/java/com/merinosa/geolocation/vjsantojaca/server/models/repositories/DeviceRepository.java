package com.merinosa.geolocation.vjsantojaca.server.models.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.DeviceEntity;

public interface DeviceRepository extends CrudRepository<DeviceEntity, Long> 
{
	DeviceEntity findByNumberDevice(int numberDevice);
	DeviceEntity findByPassAndNumberDevice(String pass, int numberDevice);
	DeviceEntity findByNickDeviceOrNameDevice(String nickDevice, String nameDevice);
	
	@Modifying 
	@Query("update Device d set d.gcm = ?1 where d.numberDevice = ?2")
	void setGcmDeviceById(String gcm, int numberDevice);
	
	@Modifying 
	@Query("update Device d set d.typeDevice  = ?1 where d.numberDevice = ?2")
	void setTypeDeviceById(String typeDevice, int numberDevice);
}
