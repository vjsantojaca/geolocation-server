package com.merinosa.geolocation.vjsantojaca.server.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.google.gson.JsonObject;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.BlockEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.CallDeviceEntity;
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
@RequestMapping(value="/centinela/api/device")
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
		DeviceEntity findOne = deviceRepository.findByIdDevice(id);
		if( findOne != null ) {
			DeviceResponse device = new DeviceResponse(findOne);
		    SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");
		    String dateBefore = sf.format(new Date());
		    dateBefore += " 00:00:00";
			sf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			try {
				device.setLocations(locationRepository.findLocationByIdUserAndDateOrderByDateAsc(device.getIdDevice(), sf.parse(dateBefore).getTime(), (sf.parse(dateBefore).getTime() + 86400000)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			List<SystemMessageEntity> messages = new ArrayList<SystemMessageEntity>();
			if( systemMessageRepository.findStartByIdDeviceOrderByDateDesc(device.getIdDevice()).size() > 0 )
				messages.add(systemMessageRepository.findStartByIdDeviceOrderByDateDesc(device.getIdDevice()).get(0));
			if( systemMessageRepository.findShutdownByIdDeviceOrderByDateDesc(device.getIdDevice()).size() > 0 )
				messages.add(systemMessageRepository.findShutdownByIdDeviceOrderByDateDesc(device.getIdDevice()).get(0));
			
			device.setMessages(messages);
			Gson gson = new GsonBuilder().create();
			JsonObject myobject = gson.toJsonTree(device).getAsJsonObject();
			return new ResponseEntity<String>(myobject.toString(), HttpStatus.OK);
		}
		else {
			 findOne = deviceRepository.findByNumberDevice(id);
			 if ( findOne != null ) {
				DeviceResponse device = new DeviceResponse(findOne);
			    SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");
				String dateBefore = sf.format(new Date());
				dateBefore += " 00:00:00";
				sf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				
				try {
					device.setLocations(locationRepository.findLocationByIdUserAndDateOrderByDateAsc(device.getIdDevice(), sf.parse(dateBefore).getTime(), (sf.parse(dateBefore).getTime() + 86400000)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				List<SystemMessageEntity> messages = new ArrayList<SystemMessageEntity>();
				if( systemMessageRepository.findStartByIdDeviceOrderByDateDesc(device.getIdDevice()).size() > 0 )
					messages.add(systemMessageRepository.findStartByIdDeviceOrderByDateDesc(device.getIdDevice()).get(0));
				if( systemMessageRepository.findShutdownByIdDeviceOrderByDateDesc(device.getIdDevice()).size() > 0 )
					messages.add(systemMessageRepository.findShutdownByIdDeviceOrderByDateDesc(device.getIdDevice()).get(0));
				
				device.setMessages(messages);
				Gson gson = new GsonBuilder().create();
				JsonObject myobject = gson.toJsonTree(device).getAsJsonObject();
				return new ResponseEntity<String>(myobject.toString(), HttpStatus.OK);
			 }
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/smsCallInfo", method= RequestMethod.GET)
	public ResponseEntity<String> getDeviceSmsCallInfo (@RequestParam(value="id", required=true, defaultValue="0") int id) {
		DeviceEntity findOne = deviceRepository.findByIdDevice(id);
		if( findOne != null ) {
			DeviceResponse device = new DeviceResponse(findOne);
			device.setCalls(callRepository.findCallByIdDeviceOrderByDateDesc(device.getIdDevice()));
			device.setSms(smsRepository.findSmsByIdDeviceOrderDateDesc(device.getIdDevice()));
			Gson gson = new GsonBuilder().create();
			JsonObject myobject = gson.toJsonTree(device).getAsJsonObject();
			return new ResponseEntity<String>(myobject.toString(), HttpStatus.OK);
		}
		else {
			 findOne = deviceRepository.findByNumberDevice(id);
			 if ( findOne != null ) {
				 DeviceResponse device = new DeviceResponse(findOne);
				 device.setCalls(callRepository.findCallByIdDeviceOrderByDateDesc(device.getIdDevice()));
				 device.setSms(smsRepository.findSmsByIdDeviceOrderDateDesc(device.getIdDevice()));
				 Gson gson = new GsonBuilder().create();
				 JsonObject myobject = gson.toJsonTree(device).getAsJsonObject();
				 return new ResponseEntity<String>(myobject.toString(), HttpStatus.OK);
			 }
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/number",method= RequestMethod.GET)
	public ResponseEntity<String> getDeviceByNumber (@RequestParam(value="number", required=true, defaultValue="0") int number) {
		DeviceEntity findByNumber = deviceRepository.findByNumberDevice(number);
		if( findByNumber != null ) {
			DeviceResponse device = new DeviceResponse(findByNumber);
			List<SystemMessageEntity> messages = new ArrayList<SystemMessageEntity>();
			if( systemMessageRepository.findStartByIdDeviceOrderByDateDesc(device.getIdDevice()).size() > 0 )
				messages.add(systemMessageRepository.findStartByIdDeviceOrderByDateDesc(device.getIdDevice()).get(0));
			if( systemMessageRepository.findShutdownByIdDeviceOrderByDateDesc(device.getIdDevice()).size() > 0 )
				messages.add(systemMessageRepository.findShutdownByIdDeviceOrderByDateDesc(device.getIdDevice()).get(0));
			
			device.setMessages(messages);
			Gson gson = new GsonBuilder().create();
			JsonObject myobject = gson.toJsonTree(device).getAsJsonObject();
			return new ResponseEntity<String>(myobject.toString(), HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/name", method= RequestMethod.GET)
	public ResponseEntity<String> getDevicesByName (@RequestParam(value="name", required=true, defaultValue="") String name) {
		List<DeviceEntity> findByName = deviceRepository.findByNickDeviceOrNameDeviceorEmailDevice(name, name, name);
		if( findByName != null && findByName.size() != 0 ) {
			List<DeviceResponse> devices = new ArrayList<>();
			for( DeviceEntity dev : findByName ) {
				DeviceResponse device = new DeviceResponse(dev);
				List<SystemMessageEntity> messages = new ArrayList<SystemMessageEntity>();
				if( systemMessageRepository.findStartByIdDeviceOrderByDateDesc(device.getIdDevice()).size() > 0 )
					messages.add(systemMessageRepository.findStartByIdDeviceOrderByDateDesc(device.getIdDevice()).get(0));
				if( systemMessageRepository.findShutdownByIdDeviceOrderByDateDesc(device.getIdDevice()).size() > 0 )
					messages.add(systemMessageRepository.findShutdownByIdDeviceOrderByDateDesc(device.getIdDevice()).get(0));
				
				device.setMessages(messages);
				devices.add(device);
			}
			Gson gson = new GsonBuilder().create();
			JsonArray myobject = gson.toJsonTree(devices).getAsJsonArray();
			return new ResponseEntity<String>(myobject.toString(), HttpStatus.OK);
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
		
		List<CallDeviceEntity> callByDevice = callRepository.findCallByIdDeviceOrderByDateDesc(numberDevice);
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
				//device.setLocations(locationRepository.findLocationByIdUserOrderByDateDesc(device.getIdDevice()));
				//device.setCalls(callRepository.findCallByIdDeviceOrderDateDesc(device.getIdDevice()));
				//device.setSms(smsRepository.findSmsByIdDeviceOrderDateDesc(device.getIdDevice()));
				device.setMessages(systemMessageRepository.findStartOrShutdownByIdDeviceOrderByDateDesc(device.getIdDevice()));
				deviceResponse.add(device);
			}
			Gson gson = new GsonBuilder().create();
			JsonArray myCustomArray = gson.toJsonTree(deviceResponse).getAsJsonArray();
			return new ResponseEntity<String>(myCustomArray.toString(), HttpStatus.OK);
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
			deviceRepository.setTypeDeviceById(object.getString("model"), number);
			if( object.has("email") ) {
				DeviceEntity device = devices.get(0);
				if( device.getEmailDevice() != null && device.getEmailDevice().equals("")) {
					deviceRepository.setEmailDeviceById(object.getString("email"), number);
				}
			}
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
}