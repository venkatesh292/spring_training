package com.bugtracker.model;

import java.io.Serializable;

public class JwtRequest implements Serializable {


	private static final long serialVersionUID = -5413220215092210446L;
	
	
	private String username;
    private String password;
    
    
	public JwtRequest() {

	}
	
	public JwtRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
    
    
}
