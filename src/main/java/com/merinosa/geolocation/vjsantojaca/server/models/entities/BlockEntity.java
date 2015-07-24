package com.merinosa.geolocation.vjsantojaca.server.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.context.annotation.ComponentScan;

import com.merinosa.geolocation.vjsantojaca.server.models.entities.BlockEntity.BlockPK;

@ComponentScan
@Entity
@Table(name="Block")
@IdClass(BlockPK.class)
public class BlockEntity implements Serializable 
{
	private static final long serialVersionUID = -5504150158311658177L;
	
	@Id
	@Column(name="idBlock")
	private int idBlock;
	
	@Id
	@Column(name="idDevice")
	private int idDevice;
	
	@Column(name="pass")
	private String pass;

	public int getIdBlock() {
		return idBlock;
	}

	public void setIdBlock(int idBlock) {
		this.idBlock = idBlock;
	}

	public int getIdDevice() {
		return idDevice;
	}

	public void setIdDevice(int idDevice) {
		this.idDevice = idDevice;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public class BlockPK implements Serializable 
	{
		private static final long serialVersionUID = 9185140255622399681L;
		
		protected Integer idBlock;
	    protected Integer idDevice;
	 
	    public BlockPK() {} 
	 
	    public BlockPK(Integer idBlock, Integer idDevice) {
	        this.idBlock = idBlock;
	        this.idDevice = idDevice;
	    }
	} 
	
}