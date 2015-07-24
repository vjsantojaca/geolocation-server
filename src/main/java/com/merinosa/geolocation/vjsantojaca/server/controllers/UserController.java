package com.merinosa.geolocation.vjsantojaca.server.controllers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.Rol;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.UserEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.UserRepository;

/*
 * Controlador para las peticiones REST de los usuarios
 */

@RestController
@RequestMapping(value="/api/user")
public class UserController 
{
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/login", method= RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> login ( @RequestBody String request ) 
	{
		JSONObject object = new JSONObject(request);
		String pass = object.getString("pass");
		String nick = object.getString("nick");
		
		List<UserEntity> users = userRepository.findUserByNickAndPass(nick, pass);
		
		if( !users.isEmpty() ) 
			return new ResponseEntity<String>(HttpStatus.OK);
		else
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/admin",method= RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> isAdmin ( @RequestBody String request ) 
	{
		JSONObject object = new JSONObject(request);
		String pass = object.getString("pass");
		String nick = object.getString("nick");
		
		List<UserEntity> users = userRepository.findUserByNickAndPass(nick, pass);
		
		if( !users.isEmpty() ) {
			UserEntity user = users.get(0);
			if( user.getRol() == Rol.admin) {
				return new ResponseEntity<String>(HttpStatus.OK);
			}
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		else
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/newUser", method= RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> newUser ( @RequestBody String request ) 
	{
		JSONObject object = new JSONObject(request);
		String pass = object.getString("pass");
		String nick = object.getString("nick");
		String rol = object.getString("rol");
		
		UserEntity entity = new UserEntity();
		entity.setNick(nick);
		entity.setPass(pass);
		
		if( rol.contains("admin") )
			entity.setRol(Rol.admin);
		else
			entity.setRol(Rol.comun);
		
		List<UserEntity> nickUser = userRepository.findUserByNick(nick);
		if( nickUser != null && nickUser.size() != 0) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} else {
			userRepository.save(entity);
			return new ResponseEntity<String>(HttpStatus.OK);
		}		
	}
}