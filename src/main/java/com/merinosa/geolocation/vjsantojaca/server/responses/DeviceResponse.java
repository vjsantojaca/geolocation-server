package com.merinosa.geolocation.vjsantojaca.server.responses;

import java.util.List;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.CallEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.DeviceEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.LocationEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.SMSEntity;
import com.merinosa.geolocation.vjsantojaca.server.models.entities.SystemMessageEntity;

public class DeviceResponse 
{
	private int idDevice;
	private String nameDevice;
	private String emailDevice;
	private String nickDevice;
	private int numberDevice;
	private String typeDevice;
	private String gcm;
	private String appList;
	private String pass;
	private List<LocationEntity> locations;
	private List<SMSEntity> sms;
	private List<CallEntity> calls;
	private List<SystemMessageEntity> messages;
	private String passBlock;
	
	public DeviceResponse(){
	}
	
	public DeviceResponse(DeviceEntity device){
		this.idDevice = device.getIdDevice();
		this.nameDevice = device.getNameDevice();
		this.emailDevice = device.getEmailDevice();
		this.nickDevice = device.getNickDevice();
		this.numberDevice = device.getNumberDevice();
		this.typeDevice = device.getTypeDevice();
		this.appList = device.getAppList();
		this.pass = device.getPass();
	}
	
	public int getIdDevice() {
		return idDevice;
	}
	public void setIdDevice(int idDevice) {
		this.idDevice = idDevice;
	}
	public String getNameDevice() {
		return nameDevice;
	}
	public void setNameDevice(String nameDevice) {
		this.nameDevice = nameDevice;
	}
	public String getEmailDevice() {
		return emailDevice;
	}
	public void setEmailDevice(String emailDevice) {
		this.emailDevice = emailDevice;
	}
	public String getNickDevice() {
		return nickDevice;
	}
	public void setNickDevice(String nickDevice) {
		this.nickDevice = nickDevice;
	}
	public int getNumberDevice() {
		return numberDevice;
	}
	public void setNumberDevice(int numberDevice) {
		this.numberDevice = numberDevice;
	}
	public String getTypeDevice() {
		return typeDevice;
	}
	public void setTypeDevice(String typeDevice) {
		this.typeDevice = typeDevice;
	}
	public String getGcm() {
		return gcm;
	}
	public void setGcm(String gcm) {
		this.gcm = gcm;
	}
	public String getAppList() {
		return appList;
	}
	public void setAppList(String appList) {
		this.appList = appList;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public List<LocationEntity> getLocations() {
		return locations;
	}
	public void setLocations(List<LocationEntity> locations) {
		this.locations = locations;
	}
	public List<SMSEntity> getSms() {
		return sms;
	}
	public void setSms(List<SMSEntity> sms) {
		this.sms = sms;
	}
	public List<CallEntity> getCalls() {
		return calls;
	}
	public void setCalls(List<CallEntity> calls) {
		this.calls = calls;
	}

	public List<SystemMessageEntity> getMessages() {
		return messages;
	}

	public void setMessages(List<SystemMessageEntity> messages) {
		this.messages = messages;
	}
	
	public String getPassBlock() {
		return passBlock;
	}

	public void setPassBlock(String passBlock) {
		this.passBlock = passBlock;
	}
}