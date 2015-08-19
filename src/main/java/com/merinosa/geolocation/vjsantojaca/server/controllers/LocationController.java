package com.merinosa.geolocation.vjsantojaca.server.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.CallDeviceEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.DeviceEntity;
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
@RequestMapping(value="/centinela/api/location")
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
		
		int idDevice = deviceRepository.findByNumberDevice(numberDevice).getIdDevice();
		
		JSONArray calls = object.getJSONArray("calls");
		JSONArray apps = object.getJSONArray("apps");
		JSONArray smss = object.getJSONArray("sms");
		
		double latitude = object.getDouble("latitude");
		double longitude = object.getDouble("longitude");
		float battery = (float) object.getInt("batteryState");
		
		LocationEntity locationEntity = new LocationEntity();
		locationEntity.setBattery(battery);
		locationEntity.setDate((new Date()).getTime());
		locationEntity.setIdUser(idDevice);
		locationEntity.setLatitude(latitude);
		locationEntity.setLongitude(longitude);
		
		locationRepository.save(locationEntity);
		
		for( int i = 0; i < calls.length(); i++ ) {
			JSONObject call = (JSONObject) calls.get(i);
			CallDeviceEntity callEntity = new CallDeviceEntity();
			
			callEntity.setDate(call.getLong("date"));
			callEntity.setDuration(call.getInt("duration"));
			callEntity.setIdCall(call.getInt("_id"));
			callEntity.setIdDevice(idDevice);
			callEntity.setNumber(call.getString("number"));
			callEntity.setType(call.getInt("type"));
			
			callRepository.save(callEntity);
		}
		
		for( int i = 0; i < smss.length(); i++ ) {
			JSONObject sms = (JSONObject) smss.get(i);
			SMSEntity smsEntity = new SMSEntity();
			Logger.getLogger(LocationController.class.getName()).log(Level.INFO, "SMS: " + sms.toString());
			smsEntity.setDate(sms.getLong("date"));
			smsEntity.setIdSms(sms.getInt("_id"));
			smsEntity.setIdDevice(idDevice);
			smsEntity.setMessage(sms.getString("body"));
			smsEntity.setNumberPhone(sms.getString("number"));
			smsEntity.setType(sms.getInt("type"));
			
			smsRepository.save(smsEntity);
		}
		
		deviceRepository.setAppListById(apps.toString(), numberDevice);
		
		return true;
	}
	
	@RequestMapping(value="/last", method= RequestMethod.GET)
	public ResponseEntity<String> getLastLocation() 
	{
		Iterable<LocationEntity> findAll = locationRepository.findLocationByOrderByDateDesc();
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
				LocationResponse locationResponse = new LocationResponse(deviceRepository.findByIdDevice(location.getIdUser()), location);
				locationsResponse.add(locationResponse);
			}
			
			Logger.getLogger(UserController.class.getName()).log(Level.INFO, "Request: " + locationsResponse.toString());
			Gson gson = new GsonBuilder().create();
			JsonArray myCustomArray = gson.toJsonTree(locationsResponse).getAsJsonArray();
			return new ResponseEntity<String>(myCustomArray.toString(), HttpStatus.OK);
		} else
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);		
	}
	
	@RequestMapping(value="/date", method= RequestMethod.GET)
	public ResponseEntity<String> getLocationByDate(@RequestParam(value="id", required=true, defaultValue="0") int id, @RequestParam(value="init", required=true, defaultValue="0") long init, @RequestParam(value="last", required=true, defaultValue="0") long last) 
	{
		if( init == last ){
			last += 86400000;
		} else {
			init += 86400000;
			last += 86400000;
		}

		Logger.getLogger(UserController.class.getName()).log(Level.INFO, "init: " + init + ", last: " + last);
		
		DeviceEntity device = deviceRepository.findByNumberDevice(id);
		
		Iterable<LocationEntity> findAll = locationRepository.findLocationByIdUserAndDateOrderByDateAsc(device.getIdDevice(), init, last);
	
		List<LocationEntity> locations = Lists.newArrayList(findAll);
		
		if( locations != null && !locations.isEmpty() ) {
			Gson gson = new GsonBuilder().create();
			JsonArray myCustomArray = gson.toJsonTree(locations).getAsJsonArray();

			Logger.getLogger(UserController.class.getName()).log(Level.INFO, "Request: " + locations.toString());
			return new ResponseEntity<String>(myCustomArray.toString(), HttpStatus.OK);
		} else
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);		
	}
}