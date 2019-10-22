package com.lagoon.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lagoon.model.User;

public interface UserService {
	Page<User> findAllUsers(Pageable pageable);
	
	User findByUserName(String userName);
	
	void save(User user);
	
	List<User> findAll();

}