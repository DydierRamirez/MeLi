package com.meli.ipvalidator.controller;

import org.junit.jupiter.api.Test;

public class ValidatorControllerTest  extends ApplicationEndpointTest{
	
	private String endpoint_micro = "/api/MeLi/v1";
	
	@Test
	void getOkValidator() throws Exception {
		assertResponse("GET",endpoint_micro+"/ipValidator?ip=186.84.90.212","",200);
	}
	
	@Test
	void getNoMethodValidator() throws Exception {
		assertResponse("POST",endpoint_micro+"/ipValidator?ip=186.84.90.212","",405);
	}
	
	@Test
	void getNoIpValidator() throws Exception {
		assertResponse("GET",endpoint_micro+"/ipValidator?ip=","",400);
	}
	
	@Test
	void postRegisterBlackListIp() throws Exception {
		assertResponse("POST",endpoint_micro+"/blacklist?ip=186.84.90.225","",200);
	}
	
	@Test
	void postNullBlackListIp() throws Exception {
		assertResponse("POST",endpoint_micro+"/blacklist?ip=","",400);
	}
	

}
