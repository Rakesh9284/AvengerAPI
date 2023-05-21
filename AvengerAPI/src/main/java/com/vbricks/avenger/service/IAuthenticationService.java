package com.vbricks.avenger.service;

import java.util.HashMap;

public interface IAuthenticationService {

	public abstract HashMap<String, String> doAuthentication(String APIkey,String secretkey);
}
