package com.web.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.web.models.UserAcount;
@Service
@Component
public interface CallApiService {
	String getEntityJSON(String URL_GET, String cookie);
	String postEntityJSONWithAuth(UserAcount account, String URL_POST);
	String postEntityJSON(String modelJson, String URL_POST, String cookie);
	ResponseEntity<byte[]> getEntityData(String URL_GET, String rangeList);
	String putEntityJSON(String jsonModel, String URL_PUT, String cookie);
}
