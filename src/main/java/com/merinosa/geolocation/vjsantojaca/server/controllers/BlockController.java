package com.merinosa.geolocation.vjsantojaca.server.controllers;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.BlockEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.DeviceEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.BlockRepository;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.DeviceRepository;
import com.merinosa.geolocation.vjsantojaca.server.responses.DeviceResponse;

@RestController
@RequestMapping(value="/centinela/api/block")
public class BlockController 
{
	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private BlockRepository blockRepository;

	private static final String SENDER_ID = "AIzaSyDdpKKykeVa-5z0ext2htYT_G-WVfd8DmA";

	@RequestMapping(method= RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> newBlock (@RequestBody String request) {
		JSONObject object = new JSONObject(request);
		int number = object.getInt("number");
		int pass = object.getInt("pass");
		
		DeviceEntity device = deviceRepository.findByNumberDevice(number);

		BlockEntity blockEntity = new BlockEntity();
		blockEntity.setIdDevice(device.getIdDevice());
		blockEntity.setPass(pass);
		
		blockRepository.save(blockEntity);
		
		try {
			String gcm = device.getGcm();
			
			String url = "https://android.googleapis.com/gcm/send";
			URL obj = new URL(url);
			
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Authorization", "key="+SENDER_ID);
			
			JSONObject objectData = new JSONObject();
			objectData.put("type", "block");
			objectData.put("pass", pass);
			
			JSONArray arrayids = new JSONArray();
			arrayids.put(gcm);
			
			JSONObject objectSend = new JSONObject();
			objectSend.put("data", objectData);
			objectSend.put("registration_ids", arrayids);
			objectSend.put("collapse_key", SENDER_ID);
			
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(objectSend.toString());
			wr.flush();
			wr.close();
			
			int responseCode = con.getResponseCode();
			
			if( responseCode == 200)
				return new ResponseEntity<String>(HttpStatus.OK);
			else
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<String> getBlocks () {
		List<DeviceResponse> devices = new ArrayList<>();
		List<BlockEntity> blocksList = Lists.newArrayList(blockRepository.findAll());
		
		for(BlockEntity block :  blocksList) {
			DeviceEntity device = deviceRepository.findByIdDevice(block.getIdDevice());
			DeviceResponse deviceResponse = new DeviceResponse(device);
			deviceResponse.setPassBlock(block.getPass());
			devices.add(deviceResponse);
		}
		
		Gson gson = new GsonBuilder().create();
		JsonArray myobject = gson.toJsonTree(devices).getAsJsonArray();
		
		return new ResponseEntity<String>(myobject.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete", method= RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> deleteBlock (@RequestBody String request) {
		JSONObject object = new JSONObject(request);
		int number = object.getInt("number");
		int pass = object.getInt("pass");
		
		DeviceEntity device = deviceRepository.findByNumberDevice(number);
		BlockEntity deviceBlock = blockRepository.findBlockyByIdDevice(deviceRepository.findByNumberDevice(number).getIdDevice());
		if( deviceBlock.getPass() == pass )
		{
			blockRepository.delete(deviceBlock);
			try {
				String gcm = device.getGcm();
				
				String url = "https://android.googleapis.com/gcm/send";
				URL obj = new URL(url);
				
				HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
				
				con.setRequestMethod("POST");
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Authorization", "key="+SENDER_ID);
				
				JSONObject objectData = new JSONObject();
				objectData.put("type", "unBlock");
				objectData.put("pass", pass);
				
				JSONArray arrayids = new JSONArray();
				arrayids.put(gcm);
				
				JSONObject objectSend = new JSONObject();
				objectSend.put("data", objectData);
				objectSend.put("registration_ids", arrayids);
				objectSend.put("collapse_key", SENDER_ID);
				
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.writeBytes(objectSend.toString());
				wr.flush();
				wr.close();
				
				int responseCode = con.getResponseCode();
				
				if( responseCode == 200) {
					Logger.getLogger(BlockController.class.getName()).log(Level.INFO, "Response: " + con.getResponseMessage());
					return new ResponseEntity<String>(HttpStatus.OK);
				}
				else
					return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
}