package com.lagoon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lagoon.dao.UserDao;
import com.lagoon.model.User;
import com.lagoon.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public Page<User> findAllUsers(Pageable pageable) {
		
		return userDao.findAll(pageable);
	}

	@Override
	public User findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}

	@Override
	public void save(User user) {
		userDao.save(user);		
	}
}