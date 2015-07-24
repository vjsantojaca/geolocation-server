package com.merinosa.geolocation.vjsantojaca.server.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.LocationEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.DeviceRepository;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.LocationRepository;
import com.merinosa.geolocation.vjsantojaca.server.responses.LocationResponse;


/*
 * Controlador para las peticiones REST de Geolocalización
 */

@RestController
@RequestMapping(value="/api/location")
public class LocationController 
{
	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@RequestMapping(method= RequestMethod.POST, headers = "content-type=application/json")
	public boolean geolocation ( @RequestBody String request ) {
		
		return true;
	}
	
	@RequestMapping(value="/last", method= RequestMethod.GET, headers = "content-type=application/json")
	public ResponseEntity<String> getLastLocation() 
	{
		Iterable<LocationEntity> findAll = locationRepository.findLocationOrderByDateDesc();
		List<LocationResponse> locationsResponse = new ArrayList<>();
		List<LocationEntity> locations = Lists.newArrayList(findAll);
		
		if( locations != null && !locations.isEmpty() ) {
			Map<Integer, LocationEntity> locationsMap = new HashMap();
			for( LocationEntity location : locations ) {
				if( !locationsMap.containsKey(location.getIdUser()) ) {
					locationsMap.put(location.getIdUser(), location);
				}
			}
			
			for( LocationEntity location : locationsMap.values() ) {
				LocationResponse locationResponse = new LocationResponse(deviceRepository.findByNumberDevice(location.getIdUser()), location);
				locationsResponse.add(locationResponse);
			}
			
			return new ResponseEntity<String>((new JSONArray(locationsResponse)).toString(), HttpStatus.OK);
		} else
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);		
	}
}