package com.web.services;

import java.util.Optional;

import com.web.entities.User;

public interface IUserService {
	/**
	 * lấy thông tin user bằng ID
	 * @param Id
	 * @return
	 */
	Optional<User> findById(Integer Id);
}
