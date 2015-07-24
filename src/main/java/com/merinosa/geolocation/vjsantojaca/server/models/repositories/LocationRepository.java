package com.merinosa.geolocation.vjsantojaca.server.models.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.LocationEntity;

@Repository
public interface LocationRepository extends CrudRepository<LocationEntity, Long> 
{
	List<LocationEntity> findLocationByIdUserOrderByDateDesc( int idUser );
}
