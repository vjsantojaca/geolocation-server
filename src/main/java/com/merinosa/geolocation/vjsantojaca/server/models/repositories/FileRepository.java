package com.merinosa.geolocation.vjsantojaca.server.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.FileEntity;

public interface FileRepository extends CrudRepository<FileEntity, Long> 
{
	@Query("select f from FileEntity f where f.typeFile=0 and f.idDevice = ?1 ORDER BY f.date DESC")
	List<FileEntity> findImagesByIdDeviceOrderByDateDesc(int idDevice);
	
	@Query("select f from FileEntity f where f.typeFile=1 and f.idDevice = ?1 ORDER BY f.date DESC")
	List<FileEntity> findSoundsByIdDeviceOrderByDateDesc(int idDevice);
}