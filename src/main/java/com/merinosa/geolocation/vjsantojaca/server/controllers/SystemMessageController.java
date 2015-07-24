package com.merinosa.geolocation.vjsantojaca.server.controllers;


import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.DeviceEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.SystemMessageEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.TypeMessage;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.DeviceRepository;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.SystemMessageRepository;

@RestController
@RequestMapping(value="/api/system")
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
}