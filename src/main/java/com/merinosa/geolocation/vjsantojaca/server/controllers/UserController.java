package com.merinosa.geolocation.vjsantojaca.server.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
 * Controlador para las peticiones REST de los usuarios
 */

@RestController
@RequestMapping(value="/api/user")
public class UserController 
{
	@RequestMapping(method= RequestMethod.POST, headers = "content-type=application/json")
	public boolean login ( @RequestBody String request ) 
	{
		
		return true;
	}
	
	@RequestMapping(method= RequestMethod.POST, headers = "content-type=application/json")
	public boolean isAdmin ( @RequestBody String request ) 
	{
		
		return true;
	}
}