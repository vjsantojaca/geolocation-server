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
@Table(name="User")
public class UserEntity implements Serializable 
{
	private static final long serialVersionUID = -1215413599408330025L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idUser")
	private int idUser;
	
	@Column(name="nick")
	private String nick;
	
	@Column(name="pass")
	private String pass;
	
	@Column(name="rol")
	private Rol rol;

	public UserEntity() {
		super();
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
}