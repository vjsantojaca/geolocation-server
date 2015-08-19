package com.merinosa.geolocation.vjsantojaca.server.controllers;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.DeviceEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.SystemMessageEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.TypeMessage;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.DeviceRepository;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.SystemMessageRepository;
import com.merinosa.geolocation.vjsantojaca.server.responses.SystemMessageResponse;

@RestController
@RequestMapping(value="/centinela/api/system")
public class SystemMessageController 
{
	@Autowired
	private SystemMessageRepository systemMessageRepository;
	
	@Autowired
	private DeviceRepository deviceRepository;

	@RequestMapping(method= RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> system (@RequestBody String request) 
	{
		JSONObject object = new JSONObject(request);
		int numberDevice = object.getInt("numberPhone");
		TypeMessage type = TypeMessage.valueOf(object.getString("type"));
		
		DeviceEntity device = deviceRepository.findByNumberDevice(numberDevice);
		if( device != null ) {
			SystemMessageEntity sm = new SystemMessageEntity();
			sm.setDate((new Date()).getTime());
			sm.setIdDevice(device.getIdDevice());
			sm.setTypeMessage(type);
			
			systemMessageRepository.save(sm);
		}
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<String> getNotif () 
	{
		List<SystemMessageEntity> findSystemMessageOrderByDateDesc = systemMessageRepository.findSystemMessageByReadByDateDesc();
		List<SystemMessageResponse> responses = new ArrayList<>();
		
		for( SystemMessageEntity sm : findSystemMessageOrderByDateDesc ) {
			SystemMessageResponse smr = new SystemMessageResponse(deviceRepository.findByIdDevice(sm.getIdDevice()), sm);
			responses.add(smr);
		}
		Gson gson = new GsonBuilder().create();
		JsonArray myArray = gson.toJsonTree(responses).getAsJsonArray();
		return new ResponseEntity<String>(myArray.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/notRead", method= RequestMethod.GET)
	public ResponseEntity<String> notRead () 
	{
		int read = systemMessageRepository.findNumberSystemMessageWithourRead();
		
		return new ResponseEntity<String>(String.valueOf(read), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getDelete", method= RequestMethod.GET)
	public ResponseEntity<String> getNotifDelete () 
	{
		List<SystemMessageEntity> findSystemMessageOrderByDateDesc = systemMessageRepository.findRemoveByReadByDateDesc();
		List<SystemMessageResponse> responses = new ArrayList<>();
		
		for( SystemMessageEntity sm : findSystemMessageOrderByDateDesc ) {
			SystemMessageResponse smr = new SystemMessageResponse(deviceRepository.findByIdDevice(sm.getIdDevice()), sm);
			responses.add(smr);
		}
		
		Gson gson = new GsonBuilder().create();
		JsonArray myArray = gson.toJsonTree(responses).getAsJsonArray();
		
		return new ResponseEntity<String>(myArray.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/readDelete", method= RequestMethod.POST)
	public ResponseEntity<String> readDelete () 
	{
		systemMessageRepository.setReadDelete();
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/readSystemMessage", method= RequestMethod.POST)
	public ResponseEntity<String> readSystemMessage () 
	{
		systemMessageRepository.setRead();
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}