package com.merinosa.geolocation.vjsantojaca.server.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.DeviceEntity;

@Repository
public interface DeviceRepository extends CrudRepository<DeviceEntity, Long> 
{
	DeviceEntity findByNumberDevice(int numberDevice);
	List<DeviceEntity> findByPassAndNumberDevice(String pass, int numberDevice);
	List<DeviceEntity> findByNickDeviceOrNameDevice(String nickDevice, String nameDevice);
	
	@Modifying
	@Query("update DeviceEntity d set d.gcm = ?1 where d.numberDevice = ?2")
	void setGcmDeviceById(String gcm, int numberDevice);
	
	@Modifying 
	@Query("update DeviceEntity d set d.typeDevice = ?1 where d.numberDevice = ?2")
	void setTypeDeviceById(String typeDevice, int numberDevice);
}
