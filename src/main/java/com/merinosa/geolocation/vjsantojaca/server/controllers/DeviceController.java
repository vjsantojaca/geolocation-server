package com.merinosa.geolocation.vjsantojaca.server.controllers;

import java.util.ArrayList;
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
import com.merinosa.geolocation.vjsantojaca.server.models.entities.BlockEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.CallEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.DeviceEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.LocationEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.SMSEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.SystemMessageEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.BlockRepository;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.CallRepository;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.DeviceRepository;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.LocationRepository;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.SmsRepository;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.SystemMessageRepository;
import com.merinosa.geolocation.vjsantojaca.server.responses.DeviceResponse;

@RestController
@RequestMapping(value="/api/device")
public class DeviceController 
{
	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private CallRepository callRepository;
	
	@Autowired
	private SmsRepository smsRepository;
	
	@Autowired
	private SystemMessageRepository systemMessageRepository;
	
	@Autowired
	private BlockRepository blockRepository;
	
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
			device.setCalls(callRepository.findCallByIdDeviceOrderDateDesc(device.getIdDevice()));
			device.setSms(smsRepository.findSmsByIdDeviceOrderDateDesc(device.getIdDevice()));
			device.setMessages(systemMessageRepository.findSystemMessageByIdDeviceOrderByDateDesc(device.getIdDevice()));
			return new ResponseEntity<String>((new JSONObject(device)).toString(), HttpStatus.OK);
		}
		else {
			 findOne = deviceRepository.findByNumberDevice(id);
			 if ( findOne != null ) {
				 DeviceResponse device = new DeviceResponse(findOne);
				 device.setLocations(locationRepository.findLocationByIdUserOrderByDateDesc(device.getIdDevice()));
				 device.setCalls(callRepository.findCallByIdDeviceOrderDateDesc(device.getIdDevice()));
				 device.setSms(smsRepository.findSmsByIdDeviceOrderDateDesc(device.getIdDevice()));
				 device.setMessages(systemMessageRepository.findSystemMessageByIdDeviceOrderByDateDesc(device.getIdDevice()));
				 return new ResponseEntity<String>((new JSONObject(device)).toString(), HttpStatus.OK);
			 }
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/number",method= RequestMethod.GET)
	public ResponseEntity<String> getDeviceByNumber (@RequestParam(value="number", required=true, defaultValue="0") int number) {
		DeviceEntity findByNumber = deviceRepository.findByNumberDevice(number);
		if( findByNumber != null ) {
			DeviceResponse device = new DeviceResponse(findByNumber);
			device.setLocations(locationRepository.findLocationByIdUserOrderByDateDesc(device.getIdDevice()));
			device.setCalls(callRepository.findCallByIdDeviceOrderDateDesc(device.getIdDevice()));
			device.setSms(smsRepository.findSmsByIdDeviceOrderDateDesc(device.getIdDevice()));
			device.setMessages(systemMessageRepository.findSystemMessageByIdDeviceOrderByDateDesc(device.getIdDevice()));
			return new ResponseEntity<String>((new JSONObject(device)).toString(), HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/name", method= RequestMethod.GET)
	public ResponseEntity<String> getDevicesByName (@RequestParam(value="name", required=true, defaultValue="") String name) {
		List<DeviceEntity> findByName = deviceRepository.findByNickDeviceOrNameDevice(name, name);
		if( findByName != null && findByName.size() != 0 ) {
			List<DeviceResponse> devices = new ArrayList<>();
			for( DeviceEntity dev : findByName ) {
				DeviceResponse device = new DeviceResponse(dev);
				device.setLocations(locationRepository.findLocationByIdUserOrderByDateDesc(device.getIdDevice()));
				device.setCalls(callRepository.findCallByIdDeviceOrderDateDesc(device.getIdDevice()));
				device.setSms(smsRepository.findSmsByIdDeviceOrderDateDesc(device.getIdDevice()));
				device.setMessages(systemMessageRepository.findSystemMessageByIdDeviceOrderByDateDesc(device.getIdDevice()));
				devices.add(device);
			}
			return new ResponseEntity<String>((new JSONArray(devices)).toString(), HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/delete", method= RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> deleteDevice (@RequestBody String request) {
		int numberDevice = (new JSONObject(request)).getInt("number");
		
		DeviceEntity device = deviceRepository.findByNumberDevice(numberDevice);
		deviceRepository.delete((long) device.getIdDevice());
		
		List<LocationEntity> locationsByDevice = locationRepository.findLocationByIdUserOrderByDateDesc(numberDevice);
		locationRepository.delete(locationsByDevice);
		
		List<SMSEntity> smsByDevice = smsRepository.findSmsByIdDeviceOrderDateDesc(numberDevice);
		smsRepository.delete(smsByDevice);
		
		List<SystemMessageEntity> systemMessageDevice = systemMessageRepository.findSystemMessageByIdDeviceOrderByDateDesc(numberDevice);
		systemMessageRepository.delete(systemMessageDevice);
		
		List<CallEntity> callByDevice = callRepository.findCallByIdDeviceOrderDateDesc(numberDevice);
		callRepository.delete(callByDevice);
		
		BlockEntity blockDevice = blockRepository.findBlockyByIdDevice(numberDevice);
		blockRepository.delete(blockDevice);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
//	@RequestMapping(value="/update", method= RequestMethod.POST, headers = "content-type=application/json")
//	public Response updateDevice (@RequestBody String request) {
//		
//	}
	
	@RequestMapping(value="/all", method= RequestMethod.GET)
	public ResponseEntity<String> getListDevice () {
		List<DeviceEntity> devices = Lists.newArrayList(deviceRepository.findAll());
		if( devices!= null ){
			List<DeviceResponse> deviceResponse = new ArrayList<>();
			for( DeviceEntity dev : devices ) {
				DeviceResponse device = new DeviceResponse(dev);
				device.setLocations(locationRepository.findLocationByIdUserOrderByDateDesc(device.getIdDevice()));
				device.setCalls(callRepository.findCallByIdDeviceOrderDateDesc(device.getIdDevice()));
				device.setSms(smsRepository.findSmsByIdDeviceOrderDateDesc(device.getIdDevice()));
				device.setMessages(systemMessageRepository.findSystemMessageByIdDeviceOrderByDateDesc(device.getIdDevice()));
				deviceResponse.add(device);
			}
			return new ResponseEntity<String>((new JSONArray(deviceResponse)).toString(), HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/checkPass", method= RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> checkPass (@RequestBody String request) {
		JSONObject object = new JSONObject(request);
		String pass = object.getString("pass");
		int number = object.getInt("id");
		
		List<DeviceEntity> devices = deviceRepository.findByPassAndNumberDevice(pass, number);
		if( devices != null && devices.size() != 0 ) 
		{
			deviceRepository.setGcmDeviceById(object.getString("model"), number);
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
			deviceRepository.setGcmDeviceById(regId, number);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
//	@RequestMapping(value="/gcm/send", method= RequestMethod.POST, headers = "content-type=application/json")
//	public Response sendGcm (@RequestBody String request) {
//		
//	}
}