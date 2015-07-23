package com.merinosa.geolocation.vjsantojaca.server.models.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.LocationEntity;

public interface LocationRepository extends CrudRepository<LocationEntity, Long> 
{
	List<LocationEntity> findLocationByidDeviceOrderByDateDesc( int idDevice );
}
