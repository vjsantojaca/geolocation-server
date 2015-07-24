package com.merinosa.geolocation.vjsantojaca.server.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.SystemMessageEntity;

@Repository
public interface SystemMessageRepository extends CrudRepository<SystemMessageEntity, Long> 
{
	@Query("select s from SystemMessageEntity s where s.idDevice = ?1 ORDER BY s.date DESC")
	List<SystemMessageEntity> findSystemMessageByIdDeviceOrderByDateDesc(int idDevice);
}
