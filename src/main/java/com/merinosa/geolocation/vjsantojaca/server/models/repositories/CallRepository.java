package com.merinosa.geolocation.vjsantojaca.server.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.CallEntity;

@Repository
public interface CallRepository extends CrudRepository<CallEntity, Long> {
	
}
