package com.merinosa.geolocation.vjsantojaca.server.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.SMSEntity;

@Repository
public interface SmsRepository extends CrudRepository<SMSEntity, Long>{

}
