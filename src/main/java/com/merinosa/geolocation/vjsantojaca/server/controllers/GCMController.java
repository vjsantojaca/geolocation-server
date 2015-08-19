package com.merinosa.geolocation.vjsantojaca.server.controllers;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;

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

import com.merinosa.geolocation.vjsantojaca.server.models.entities.DeviceEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.repositories.DeviceRepository;

@RestController
@RequestMapping(value="/centinela/api/gcm")
public class GCMController {

	@Autowired
	private DeviceRepository deviceRepository;
	
	private static final String SENDER_ID = "AIzaSyDdpKKykeVa-5z0ext2htYT_G-WVfd8DmA";
	
	@RequestMapping(method= RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> sendGcm (@RequestBody String request) 
	{
		JSONObject object = new JSONObject(request);
		
		int number = object.getInt("number");
		String message = object.getString("message");
		
		DeviceEntity device = deviceRepository.findByNumberDevice(number);
		
		if( device != null )
		{
			try {
				String gcm = device.getGcm();
				
				String url = "https://android.googleapis.com/gcm/send";
				URL obj = new URL(url);
				
				HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
				
				con.setRequestMethod("POST");
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Authorization", "key="+SENDER_ID);
				
				JSONObject objectData = new JSONObject();
				objectData.put("message", message);
				objectData.put("type", "message");
				
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
			
		} else
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}	
}
