package com.web.services.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.entities.User;
import com.web.repository.IUserRepository;
import com.web.services.IUserService;
@Service
public class UserService implements IUserService{
	@Autowired
	IUserRepository iUserRepository;
	@Override
	public Optional<User> findById(Integer Id) {
		// TODO Auto-generated method stub
		return iUserRepository.findById(Id);
	}
	
}
