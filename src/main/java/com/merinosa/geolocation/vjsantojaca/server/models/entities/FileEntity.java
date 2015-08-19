package com.merinosa.geolocation.vjsantojaca.server.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.ComponentScan;


@ComponentScan
@Entity
@Table(name="File")
public class FileEntity implements Serializable 
{
	private static final long serialVersionUID = 5547555299585315079L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idFile")
	private int idFile;
	
	@Column(name="idUser")
	private int idDevice;
	
	@Column(name="path")
	private String path;
	
	@Column(name="date")
	private long date;
	
	@Column(name="typeFile")
	private TypeFile typeFile;

	public int getIdFile() {
		return idFile;
	}

	public void setIdFile(int idFile) {
		this.idFile = idFile;
	}

	public int getIdUser() {
		return idDevice;
	}

	public void setIdUser(int idDevice) {
		this.idDevice = idDevice;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public TypeFile getType() {
		return typeFile;
	}

	public void setType(TypeFile typeFile) {
		this.typeFile = typeFile;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}
}