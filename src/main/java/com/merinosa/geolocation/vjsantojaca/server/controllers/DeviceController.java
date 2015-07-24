package com.merinosa.geolocation.vjsantojaca.server.controllers;

import java.util.List;

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
import com.merinosa.geolocation.vjsantojaca.server.models.entities.DeviceEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.DeviceRepository;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.LocationRepository;
import com.merinosa.geolocation.vjsantojaca.server.responses.DeviceResponse;

@RestController
@RequestMapping(value="/api/device")
public class DeviceController 
{
	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@RequestMapping(method= RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> newDevice (@RequestBody String request) {
		JSONObject object = new JSONObject(request);
		DeviceEntity deviceSave = deviceRepository.save(new DeviceEntity(object));
		if( deviceSave != null ) {
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<String> getDevice (@RequestParam(value="id", required=true, defaultValue="0") int id) {
		DeviceEntity findOne = deviceRepository.findOne((long) id);
		if( findOne != null ) {
			DeviceResponse device = new DeviceResponse(findOne);
			device.setLocations(locationRepository.findLocationByIdUserOrderByDateDesc(device.getIdDevice()));
			return new ResponseEntity<String>((new JSONObject(device)).toString(), HttpStatus.OK);
		}
		else {
			 findOne = deviceRepository.findByNumberDevice(id);
			 if ( findOne != null ) {
				 DeviceResponse device = new DeviceResponse(findOne);
				 device.setLocations(locationRepository.findLocationByIdUserOrderByDateDesc(device.getIdDevice()));
				 return new ResponseEntity<String>((new JSONObject(device)).toString(), HttpStatus.OK);
			 }
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/number",method= RequestMethod.GET)
	public ResponseEntity<String> getDeviceByNumber (@RequestParam(value="number", required=true, defaultValue="0") int number) {
		DeviceEntity findByNumber = deviceRepository.findByNumberDevice(number);
		if( findByNumber != null ) 
			return new ResponseEntity<String>((new JSONObject(findByNumber)).toString(), HttpStatus.OK);
		
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/name", method= RequestMethod.GET)
	public ResponseEntity<String> getDevicesByName (@RequestParam(value="name", required=true, defaultValue="") String name) {
		List<DeviceEntity> findByNumber = deviceRepository.findByNickDeviceOrNameDevice(name, name);
		if( findByNumber != null && findByNumber.size() != 0 ) 
			return new ResponseEntity<String>((new JSONArray(findByNumber)).toString(), HttpStatus.OK);
		
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
//	@RequestMapping(value="/update", method= RequestMethod.POST, headers = "content-type=application/json")
//	public Response updateDevice (@RequestBody String request) {
//		
//	}
	
	@RequestMapping(value="/all", method= RequestMethod.GET)
	public ResponseEntity<String> getListDevice () {
		List<DeviceEntity> devices = Lists.newArrayList(deviceRepository.findAll());
		return new ResponseEntity<String>((new JSONArray(devices)).toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/checkPass", method= RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> checkPass (@RequestBody String request) {
		JSONObject object = new JSONObject(request);
		String pass = object.getString("pass");
		int number = object.getInt("id");
		
		List<DeviceEntity> devices = deviceRepository.findByPassAndNumberDevice(pass, number);
		if( devices != null  && devices.size() != 0 ) 
		{
//			deviceRepository.setGcmDeviceById(object.getString("model"), number);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/gcm", method= RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> saveGcm (@RequestBody String request) {
		JSONObject object = new JSONObject(request);
		String regId = object.getString("regId");
		int number = object.getInt("idUser");
		
		DeviceEntity device = deviceRepository.findByNumberDevice(number);
		if( device != null ) 
		{
//			deviceRepository.setGcmDeviceById(regId, number);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
//	@RequestMapping(value="/gcm/send", method= RequestMethod.POST, headers = "content-type=application/json")
//	public Response sendGcm (@RequestBody String request) {
//		
//	}
}