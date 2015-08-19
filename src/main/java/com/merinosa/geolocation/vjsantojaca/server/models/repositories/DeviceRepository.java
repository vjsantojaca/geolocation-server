package com.merinosa.geolocation.vjsantojaca.server.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.DeviceEntity;

@Repository
public interface DeviceRepository extends CrudRepository<DeviceEntity, Long> 
{
	DeviceEntity findByNumberDevice(int numberDevice);
	DeviceEntity findByIdDevice(int idDevice);
	List<DeviceEntity> findByPassAndNumberDevice(String pass, int numberDevice);
	
	@Query("select d from DeviceEntity d where d.nickDevice LIKE CONCAT('%',?1,'%') OR d.nameDevice LIKE CONCAT('%',?2,'%')")
	List<DeviceEntity> findByNickDeviceOrNameDevice(String nickDevice, String nameDevice);
	
	@Modifying
	@Transactional
	@Query("update DeviceEntity d set d.gcm = ?1 where d.numberDevice = ?2")
	void setGcmDeviceById(String gcm, int numberDevice);
	
	@Modifying
	@Transactional
	@Query("update DeviceEntity d set d.typeDevice = ?1 where d.numberDevice = ?2")
	void setTypeDeviceById(String typeDevice, int numberDevice);
	
	@Modifying 
	@Transactional
	@Query("update DeviceEntity d set d.appList = ?1 where d.numberDevice = ?2")
	void setAppListById(String appList, int numberDevice);
}
