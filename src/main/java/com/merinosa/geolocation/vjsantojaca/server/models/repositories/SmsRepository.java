package com.merinosa.geolocation.vjsantojaca.server.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.SMSEntity;

@Repository
public interface SmsRepository extends CrudRepository<SMSEntity, Long>
{
	@Query("select s from SMSEntity s where s.idDevice = ?1 ORDER BY s.date DESC")
	List<SMSEntity> findSmsByIdDeviceOrderDateDesc(int idDevice);
}