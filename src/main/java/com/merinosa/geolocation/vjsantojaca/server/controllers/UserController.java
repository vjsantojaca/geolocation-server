package com.merinosa.geolocation.vjsantojaca.server.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.merinosa.geolocation.vjsantojaca.server.requests.UserRequest;
import com.merinosa.geolocation.vjsantojaca.server.responses.UserResponse;

/*
 * Controlador para las peticiones REST de los usuarios
 */

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@RequestMapping(method= RequestMethod.POST)
	public ResponseEntity<UserResponse> newUser ( @RequestBody @Valid UserRequest user ) {
		
		
		return new ResponseEntity<UserResponse>(HttpStatus.CREATED);
	}
}
