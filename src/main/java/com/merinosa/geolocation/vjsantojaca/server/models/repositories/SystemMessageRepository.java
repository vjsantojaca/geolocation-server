package com.merinosa.geolocation.vjsantojaca.server.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.SystemMessageEntity;

@Repository
public interface SystemMessageRepository extends CrudRepository<SystemMessageEntity, Long> 
{
	@Query("select s from SystemMessageEntity s where s.idDevice = ?1 ORDER BY s.date DESC")
	List<SystemMessageEntity> findSystemMessageByIdDeviceOrderByDateDesc(int idDevice);
	
	@Query("select s from SystemMessageEntity s where s.read = 0 ORDER BY s.date DESC")
	List<SystemMessageEntity> findSystemMessageByReadByDateDesc();
	
	@Query("select s from SystemMessageEntity s where s.typeMessage = 2 and s.read = 0 ORDER BY s.date DESC")
	List<SystemMessageEntity> findRemoveByReadByDateDesc();
	
	@Query("select s from SystemMessageEntity s where (s.typeMessage = 0 or s.typeMessage = 1) and s.idDevice = ?1 ORDER BY s.date DESC")
	List<SystemMessageEntity> findStartOrShutdownByIdDeviceOrderByDateDesc(int idDevice);
	
	@Query("select s from SystemMessageEntity s where s.typeMessage = 1 and s.idDevice = ?1 ORDER BY s.date DESC")
	List<SystemMessageEntity> findStartByIdDeviceOrderByDateDesc(int idDevice);
	
	@Query("select s from SystemMessageEntity s where s.typeMessage = 0 and s.idDevice = ?1 ORDER BY s.date DESC")
	List<SystemMessageEntity> findShutdownByIdDeviceOrderByDateDesc(int idDevice);
	
	@Query("select s from SystemMessageEntity s where s.read = 0 ORDER BY s.date DESC")
	List<SystemMessageEntity> findSystemMessageOrderByDateDesc();
	
	@Query("select count(*) from SystemMessageEntity s where s.typeMessage = 2 and s.read = 0")
	int findNumberSystemMessageWithourRead();

	@Modifying
	@Transactional
	@Query("update SystemMessageEntity s set s.read = 1")
	void setRead();
	
	@Modifying
	@Transactional
	@Query("update SystemMessageEntity s set s.read = 1 where s.typeMessage = 2")
	void setReadDelete();
}