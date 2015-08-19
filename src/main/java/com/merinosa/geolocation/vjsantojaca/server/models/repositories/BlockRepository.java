package com.merinosa.geolocation.vjsantojaca.server.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.BlockEntity;

public interface BlockRepository extends CrudRepository<BlockEntity, Integer>
{
	BlockEntity findBlockyByIdDevice(int idDevice);
}