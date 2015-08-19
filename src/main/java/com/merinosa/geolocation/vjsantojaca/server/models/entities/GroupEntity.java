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
@Table(name="Group")
public class GroupEntity implements Serializable {

	private static final long serialVersionUID = 1484470861115600646L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idGroup")
	private int idGroup;
	
	@Column(name="nameGroup")
	private String nameGroup;

	public GroupEntity(int idGroup, String nameGroup) {
		super();
		this.idGroup = idGroup;
		this.nameGroup = nameGroup;
	}
	
	public GroupEntity() {
		super();
	}

	public int getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}

	public String getNameGroup() {
		return nameGroup;
	}

	public void setNameGroup(String nameGroup) {
		this.nameGroup = nameGroup;
	}
	
}
