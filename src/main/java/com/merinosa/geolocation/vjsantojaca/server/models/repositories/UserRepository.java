package com.merinosa.geolocation.vjsantojaca.server.models.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> 
{
	List<UserEntity> findUserByNickAndPass(String nick, String pass);
	List<UserEntity> findUserByNick(String nick);
}
