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
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.BlockEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.DeviceEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.BlockRepository;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.DeviceRepository;
import com.merinosa.geolocation.vjsantojaca.server.responses.DeviceResponse;

@RestController
@RequestMapping(value="/api/block")
public class BlockController 
{
	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private BlockRepository blockRepository;

	@RequestMapping(method= RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> newBlock (@RequestBody String request) {
		JSONObject object = new JSONObject(request);
		int number = object.getInt("number");
		String pass = object.getString("pass");
		
		DeviceEntity device = deviceRepository.findByNumberDevice(number);

		BlockEntity blockEntity = new BlockEntity();
		blockEntity.setIdDevice(device.getIdDevice());
		blockEntity.setPass(pass);
		
		blockRepository.save(blockEntity);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<String> getBlocks () {
		List<DeviceResponse> devices = new ArrayList<>();
		List<BlockEntity> blocksList = Lists.newArrayList(blockRepository.findAll());
		
		for(BlockEntity block :  blocksList) {
			DeviceEntity device = deviceRepository.findOne((long) block.getIdDevice());
			DeviceResponse deviceResponse = new DeviceResponse(device);
			deviceResponse.setPassBlock(block.getPass());
			devices.add(deviceResponse);
		}
		
		return new ResponseEntity<String>((new JSONArray(devices)).toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete", method= RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> deleteBlock (@RequestBody String request) {
		JSONObject object = new JSONObject(request);
		int number = object.getInt("number");
		String pass = object.getString("pass");
		
		
		BlockEntity deviceBlock = blockRepository.findBlockyByIdDevice(deviceRepository.findByNumberDevice(number).getIdDevice());
		if( deviceBlock.getPass().equals(pass) )
		{
			blockRepository.delete(deviceBlock);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
}