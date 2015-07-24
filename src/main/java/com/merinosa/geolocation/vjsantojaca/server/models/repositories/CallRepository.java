package com.merinosa.geolocation.vjsantojaca.server.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.CallEntity;

@Repository
public interface CallRepository extends CrudRepository<CallEntity, Long> 
{
	@Query("select c from CallEntity c where c.idDevice = ?1 ORDER BY c.date DESC")
	List<CallEntity> findCallByIdDeviceOrderDateDesc(int idDevice);
}