package com.merinosa.geolocation.vjsantojaca.server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/*
 * Controlador para las peticiones REST de Geolocalización
 */

@RestController
@RequestMapping(value="/api/location")
public class LocationController 
{
	@RequestMapping(method= RequestMethod.POST, headers = "content-type=application/json")
	public boolean geolocation ( @RequestBody String request ) {
		
		return true;
	}
	
	@RequestMapping(value="/last", method= RequestMethod.GET, headers = "content-type=application/json")
	public ResponseEntity<String> getLastLocation() {
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}