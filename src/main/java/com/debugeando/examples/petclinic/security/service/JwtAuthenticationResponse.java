package com.debugeando.examples.petclinic.security.service;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String token;

  public JwtAuthenticationResponse(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

}
