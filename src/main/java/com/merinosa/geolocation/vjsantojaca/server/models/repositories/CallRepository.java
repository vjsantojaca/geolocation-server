package com.merinosa.geolocation.vjsantojaca.server.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.CallDeviceEntity;

@Repository
public interface CallRepository extends CrudRepository<CallDeviceEntity, Long> 
{
//	@Query("select c from CallEntity c where c.idDevice = ?1 ORDER BY c.date DESC")
	List<CallDeviceEntity> findCallByIdDeviceOrderByDateDesc(int idDevice);
}