package com.merinosa.geolocation.vjsantojaca.server.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.LocationEntity;

@Repository
public interface LocationRepository extends CrudRepository<LocationEntity, Long> 
{
	@Query("select l from LocationEntity l where l.idUser = ?1 and l.date >= ?2 and l.date <= ?3 ORDER BY l.date ASC")
	List<LocationEntity> findLocationByIdUserAndDateOrderByDateAsc( int idUser, long dateBefore, long dateAfter );
	
	@Query("select l from LocationEntity l where l.idUser = ?1 ORDER BY l.date DESC")
	List<LocationEntity> findLocationByIdUserOrderByDateDesc (int idUser);
	
	List<LocationEntity> findLocationByOrderByDateDesc();
}