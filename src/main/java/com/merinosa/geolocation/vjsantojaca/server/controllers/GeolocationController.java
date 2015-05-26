package com.merinosa.geolocation.vjsantojaca.server.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.merinosa.geolocation.vjsantojaca.server.requests.GeolocationRequest;

/*
 * Controlador para las peticiones REST de Geolocalización
 */

@RestController
@RequestMapping("/api/geolocation")
public class GeolocationController {

	@RequestMapping(method= RequestMethod.POST)
	public boolean geolocation ( @RequestBody @Valid GeolocationRequest geolocation ) {
		
		return true;
	}
	
}