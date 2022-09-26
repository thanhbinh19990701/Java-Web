package com.web.services;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ICommonService {
	// MethodGET
		String getEntityJSON(String URL_GET, String cookie);
		
		//MethodPOST
		String postEntityJSON(String URL_POST, Object object, String cookie) throws JsonProcessingException;
		
		//MethodPUT
		String putEntityJSON(String URL_PUT, Object object, String cookie) throws JsonProcessingException;
		
		//MethodDELETE
		String deleteEntityJSON(String URL_DELETE, String cookie);
}
