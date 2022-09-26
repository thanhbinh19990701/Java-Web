package com.web.services;

import java.util.List;

import javax.servlet.http.Cookie;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.web.models.ResponseObject;
import com.web.models.UserAcount;

//import com.ivs.models.Account;
//import com.ivs.models.InfomationUser;
//import com.ivs.models.ResponseObject;

@Service
public interface AuthService {
	String checkCookie(String cookieName, Cookie[] cookies);
	
	boolean setCookie(String cookie);
	
	ResponseObject<UserAcount> getProfile(String cookie);
	
	ResponseObject<UserAcount> login(UserAcount userAcount);
		
	ResponseObject<List<UserAcount>> getListUser(String cookie);

	ResponseObject<UserAcount> register(UserAcount userAcount);

	ResponseEntity<byte[]> getEntityData(String username, String pathAvatar);

	User getProfileAsUser(String cookie);
	
	ResponseObject<UserAcount> updateProfile(UserAcount userAcount, String cookie);
	ResponseObject<String> changePassword(UserAcount userAcount, String cookie);
}
