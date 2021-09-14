package com.bugtracker.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {


	private static final long serialVersionUID = -2304790961861865558L;

	private final String jwttoken;

	
	public JwtResponse(String jwttoken) {
		
		this.jwttoken = jwttoken;
	}


	public String getJwttoken() {
		return this.jwttoken;
	}
	
	
}
