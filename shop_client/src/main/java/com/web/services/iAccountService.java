package com.web.services;

import com.web.models.ResponseObject;
import com.web.models.UserAcount;

public interface iAccountService {
	public ResponseObject<UserAcount> findUserByID(Integer id,String cookie);
}
