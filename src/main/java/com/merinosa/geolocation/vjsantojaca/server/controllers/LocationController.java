package com.merinosa.geolocation.vjsantojaca.server.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.CallEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.LocationEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.SMSEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.CallRepository;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.DeviceRepository;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.LocationRepository;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.SmsRepository;
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
	
	@Autowired
	private CallRepository callRepository;
	
	@Autowired
	private SmsRepository smsRepository;
	
	@RequestMapping(method= RequestMethod.POST, headers = "content-type=application/json")
	public boolean geolocation ( @RequestBody String request ) {
		JSONObject object = new JSONObject(request);
		
		int numberDevice = object.getInt("id");
		
		JSONArray calls = object.getJSONArray("calls");
		JSONArray apps = object.getJSONArray("apps");
		JSONArray smss = object.getJSONArray("sms");
		
		double latitude = object.getDouble("latitude");
		double longitude = object.getDouble("longitude");
		float battery = (float) object.get("batteryState");
		
		LocationEntity locationEntity = new LocationEntity();
		locationEntity.setBattery(battery);
		locationEntity.setDate((new Date()).getTime());
		locationEntity.setIdUser(numberDevice);
		locationEntity.setLatitude(latitude);
		locationEntity.setLongitude(longitude);
		
		locationRepository.save(locationEntity);
		
		for( int i = 0; i < calls.length(); i++ ) {
			JSONObject call = (JSONObject) calls.get(i);
			CallEntity callEntity = new CallEntity();
			
			callEntity.setDate(call.getLong("date"));
			callEntity.setDuration(call.getInt("duration"));
			callEntity.setIdCall(call.getInt("_id"));
			callEntity.setIdDevice(numberDevice);
			callEntity.setNumber(call.getInt("number"));
			callEntity.setType(call.getInt("type"));
			
			callRepository.save(callEntity);
		}
		
		for( int i = 0; i < smss.length(); i++ ) {
			JSONObject sms = (JSONObject) calls.get(i);
			SMSEntity smsEntity = new SMSEntity();
			
			smsEntity.setDate(sms.getLong("date"));
			smsEntity.setIdSms(sms.getInt("_id"));
			smsEntity.setIdDevice(numberDevice);
			smsEntity.setMessage(sms.getString("body"));
			smsEntity.setNumberPhone(sms.getInt("number"));
			smsEntity.setType(sms.getInt("type"));
			
			smsRepository.save(smsEntity);
		}
		
		deviceRepository.setAppsById(apps.toString(), numberDevice);
		
		return true;
	}
	
	@RequestMapping(value="/last", method= RequestMethod.GET, headers = "content-type=application/json")
	public ResponseEntity<String> getLastLocation() 
	{
		Iterable<LocationEntity> findAll = locationRepository.findLocationOrderByDateDesc();
		List<LocationResponse> locationsResponse = new ArrayList<>();
		List<LocationEntity> locations = Lists.newArrayList(findAll);
		
		if( locations != null && !locations.isEmpty() ) {
			Map<Integer, LocationEntity> locationsMap = new HashMap<Integer, LocationEntity>();
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